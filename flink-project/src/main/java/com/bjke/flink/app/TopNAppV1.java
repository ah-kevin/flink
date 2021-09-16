package com.bjke.flink.app;

import com.alibaba.fastjson.JSON;
import com.bjke.flink.domain.PlayerGroupCount;
import com.bjke.flink.domain.RecordDetails;
import com.bjke.flink.domain.RecordDetails;
import com.bjke.flink.udf.TopNAggregateFunction;
import com.bjke.flink.udf.TopNWindowFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.shaded.guava18.com.google.common.collect.Lists;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.windowing.assigners.SlidingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 按照手机系统维度
 */
public class TopNAppV1 {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        SingleOutputStreamOperator<RecordDetails> cleanStream = env.readTextFile("data/record_details.json").map(new MapFunction<String, RecordDetails>() {
            @Override
            public RecordDetails map(String value) throws Exception {
                try {
                    return JSON.parseObject(value, RecordDetails.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }).filter(Objects::nonNull).assignTimestampsAndWatermarks(
                new BoundedOutOfOrdernessTimestampExtractor<RecordDetails>(Time.seconds(20)) {
                    @Override
                    public long extractTimestamp(RecordDetails element) {
                        return Long.parseLong(element.getId());
                    }
                }
        );
        WindowedStream<RecordDetails, Tuple3<String, String, String>, TimeWindow> windowStream = cleanStream.keyBy(new KeySelector<RecordDetails, Tuple3<String, String, String>>() {
            @Override
            public Tuple3<String, String, String> getKey(RecordDetails value) throws Exception {
                return Tuple3.of(value.getPlayer_name(), value.getGroup(), value.getScore());
            }
        }).window(SlidingEventTimeWindows.of(Time.minutes(5), Time.minutes(1)));
        // 作用上windowFunction
        SingleOutputStreamOperator<PlayerGroupCount> agg$ = windowStream.aggregate(new TopNAggregateFunction(), new TopNWindowFunction());
        agg$.keyBy(new KeySelector<PlayerGroupCount, Tuple4<String, String, Long, Long>>() {
            @Override
            public Tuple4<String, String, Long, Long> getKey(PlayerGroupCount value) throws Exception {
                return Tuple4.of(value.name, value.group, value.start, value.end);
            }
        }).process(new KeyedProcessFunction<Tuple4<String, String, Long, Long>, PlayerGroupCount, List<PlayerGroupCount>>() {
            private transient ListState<PlayerGroupCount> listState;

            @Override
            public void open(Configuration parameters) throws Exception {
                listState = getRuntimeContext().getListState(new ListStateDescriptor<PlayerGroupCount>("cnt-state", PlayerGroupCount.class));
            }

            @Override
            public void processElement(PlayerGroupCount value, KeyedProcessFunction<Tuple4<String, String, Long, Long>, PlayerGroupCount, List<PlayerGroupCount>>.Context ctx, Collector<List<PlayerGroupCount>> out) throws Exception {
                listState.add(value);
                // 注册一个定时器
                ctx.timerService().registerEventTimeTimer(value.end + 1);
            }

            // 在这里完成TopN操作
            @Override
            public void onTimer(long timestamp, KeyedProcessFunction<Tuple4<String, String, Long, Long>, PlayerGroupCount, List<PlayerGroupCount>>.OnTimerContext ctx, Collector<List<PlayerGroupCount>> out) throws Exception {
                ArrayList<PlayerGroupCount> list = Lists.newArrayList(listState.get());
                list.sort((x, y) -> Long.compare(y.count, x.count));
                ArrayList<PlayerGroupCount> sorted = new ArrayList<>();
                for (int i = 0; i < Math.min(3, list.size()); i++) {
                    PlayerGroupCount bean = list.get(i);
                    sorted.add(bean);
                }
                out.collect(sorted);
            }
        }).print();
//        agg$.print();
        env.execute("TopNAppV1");
    }

}
