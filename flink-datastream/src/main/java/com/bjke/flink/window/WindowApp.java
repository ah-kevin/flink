package com.bjke.flink.window;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

public class WindowApp {
    public static void main(String[] args) throws Exception {
        //创建上下文
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        test01(env);
        test02(env);
        env.execute("WindowApp");
    }

    private static void test02(StreamExecutionEnvironment env) {
        env.socketTextStream("localhost", 9527)
                .map(new MapFunction<String, Tuple2<String, Integer>>() {
                    @Override
                    public Tuple2<String, Integer> map(String value) throws Exception {
                        return Tuple2.of("pk", Integer.parseInt(value));
                    }
                }).keyBy(x -> x.f0)
                .window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
                .process(new PKProcessWindowFunction())
                .print();
    }

    private static void test01(StreamExecutionEnvironment env) {
//        env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);
//        env.socketTextStream("localhost", 9527)
//                .map(new MapFunction<String, Integer>() {
//                    @Override
//                    public Integer map(String value) throws Exception {
//                        return Integer.parseInt(value);
//                    }
//                }).windowAll(TumblingProcessingTimeWindows.of(Time.seconds(5)))
//                .sum(0).print();
//        //.timeWindowAll(Time.seconds(5)).sum(0).print();

        env.socketTextStream("localhost", 9527)
                .map(new MapFunction<String, Tuple2<String, Integer>>() {
                    @Override
                    public Tuple2<String, Integer> map(String value) throws Exception {
                        String[] splits = value.split(",");
                        return Tuple2.of(splits[0].trim(), Integer.parseInt(splits[1].trim()));
                    }
                }).keyBy(x -> x.f0)
                .window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
                .sum(1).print();
    }

}
