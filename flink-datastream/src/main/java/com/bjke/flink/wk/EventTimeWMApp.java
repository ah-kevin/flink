package com.bjke.flink.wk;

import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

/**
 * EventTime结合WM的使用
 * 输入格式：时间字段，单词，字数
 */
public class EventTimeWMApp {
    public static void main(String[] args) throws Exception {
        //创建上下文
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        test01(env);
        env.execute("EventTimeWMApp");
    }

    private static void test01(StreamExecutionEnvironment env) {
        DataStreamSource<String> source = env.socketTextStream("localhost", 9527);
        OutputTag<Tuple2<String, Integer>> outputTag = new OutputTag<Tuple2<String, Integer>>("late-data"){};
        SingleOutputStreamOperator<String> lines = source.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor<String>(Time.seconds(0)) {
            @Override
            public long extractTimestamp(String element) {
                return Long.parseLong(element.split(",")[0]);
            }
        });
        // [0000,5000)
        SingleOutputStreamOperator<String> window = lines.map(new MapFunction<String, Tuple2<String, Integer>>() {
                    @Override
                    public Tuple2<String, Integer> map(String value) throws Exception {
                        String[] split = value.split(",");
                        return Tuple2.of(split[1].trim(), Integer.parseInt(split[2].trim()));
                    }
                }).keyBy(x -> x.f0)
                .window(TumblingEventTimeWindows.of(Time.seconds(5)))
                .sideOutputLateData(outputTag)
//                .sum(1)
                .reduce(new ReduceFunction<Tuple2<String, Integer>>() {
                    @Override
                    public Tuple2<String, Integer> reduce(Tuple2<String, Integer> value1, Tuple2<String, Integer> value2) throws Exception {
                        System.out.println("----reduce invoked----" + value1.f0 + "==>" + (value1.f1 + value2.f1));
                        return Tuple2.of(value1.f0, value1.f1 + value2.f1);
                    }
                }, new ProcessWindowFunction<Tuple2<String, Integer>, String, String, TimeWindow>() {
                    FastDateFormat format = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

                    @Override
                    public void process(String s, ProcessWindowFunction<Tuple2<String, Integer>, String, String, TimeWindow>.Context context, Iterable<Tuple2<String, Integer>> elements, Collector<String> out) throws Exception {
                        for (Tuple2<String, Integer> element : elements) {
                            System.out.println(format.format(context.currentWatermark()));
                            out.collect("[" + format.format(context.window().getStart()) + "===>" + format.format(context.window().getEnd()) + "]," + element.f0 + "===>" + element.f1);
                        }
                    }
                });
        window.print();
        DataStream<Tuple2<String, Integer>> sideOutput = window.getSideOutput(outputTag);
        sideOutput.print();
        /**
         * WM其实就是延迟触发的一种机制
         * WM = 数据携带的时间（窗口中最大的时间）- 延迟执行的时间
         */
    }
}
