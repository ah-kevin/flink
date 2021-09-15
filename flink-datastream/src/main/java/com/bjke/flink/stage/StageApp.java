package com.bjke.flink.stage;

import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.List;

public class StageApp {
    public static void main(String[] args) throws Exception {
        //创建上下文
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        test01(env);
        env.execute("StageApp");
    }

    /**
     * 实现平均数
     */
    private static void test01(StreamExecutionEnvironment env) {
        List<Tuple2<Long, Long>> list = new ArrayList<Tuple2<Long, Long>>();
        list.add(Tuple2.of(1L, 3L));
        list.add(Tuple2.of(1L, 7L));
        list.add(Tuple2.of(2L, 4L));
        list.add(Tuple2.of(1L, 5L));
        list.add(Tuple2.of(2L, 2L));
        list.add(Tuple2.of(2L, 5L));
        env.fromCollection(list).keyBy(x -> x.f0)
                .flatMap(new AvgWithValueStage());
    }

}

class AvgWithValueStage extends RichFlatMapFunction<Tuple2<Long, Long>, Tuple2<Long, Double>> {
    // 求平均数：记录条数  总和
    private transient ValueState<Tuple2<Long, Long>> sum;

    @Override
    public void open(Configuration parameters) throws Exception {
        ValueStateDescriptor<Tuple2<Long, Long>> descriptor = new ValueStateDescriptor<>("avg", Types.TUPLE(Types.LONG, Types.LONG));
        sum = getRuntimeContext().getState(descriptor);
    }

    @Override
    public void flatMap(Tuple2<Long, Long> value, Collector<Tuple2<Long, Double>> out) throws Exception {
        // ==>stage 次数和总和
        Tuple2<Long, Long> currentState = sum.value();
        if (null == currentState) {
            currentState = Tuple2.of(0L, 0L);
        }
        currentState.f0 += 1; // 次数
        currentState.f1 += value.f1; //求和
    }
}