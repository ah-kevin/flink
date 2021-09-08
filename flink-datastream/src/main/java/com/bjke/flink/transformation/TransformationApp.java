package com.bjke.flink.transformation;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class TransformationApp {
    public static void main(String[] args) throws Exception {
        //创建上下文
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        map(env);
//        flatMap(env);
        keyBy(env);
        env.execute("TransformationApp");
    }

    /**
     * 按照domain 分组 求和
     */
    private static void keyBy(StreamExecutionEnvironment env) {
        DataStreamSource<String> source = env.readTextFile("data/access.log");
        SingleOutputStreamOperator<Access> mapStream = source.map(new MapFunction<String, Access>() {
            @Override
            public Access map(String value) throws Exception {
                String[] splits = value.split(",");
                Long time = Long.parseLong(splits[0].trim());
                String domain = splits[1].trim();
                Double traffic = Double.parseDouble(splits[2].trim());
                return new Access(time, domain, traffic);
            }
        });
//        mapStream.keyBy("domain").sum("traffic").print();
        mapStream.keyBy(new KeySelector<Access, String>() {
            @Override
            public String getKey(Access access) throws Exception {
                return access.getDomain();
            }
        }).sum("traffic").print();
    }

    public static void map(StreamExecutionEnvironment env) {
        DataStreamSource<String> source = env.readTextFile("data/access.log");
        SingleOutputStreamOperator<Access> mapStream = source.map(new MapFunction<String, Access>() {
            @Override
            public Access map(String value) throws Exception {
                String[] splits = value.split(",");
                Long time = Long.parseLong(splits[0].trim());
                String domain = splits[1].trim();
                Double traffic = Double.parseDouble(splits[2].trim());
                return new Access(time, domain, traffic);
            }
        });
        mapStream.print();
    }

    /**
     * 进来一行行的数据
     * 1. 逗号分割
     * 2. 过滤掉pk
     */
    public static void flatMap(StreamExecutionEnvironment env) {
        DataStreamSource<String> source = env.socketTextStream("localhost", 9527);
        source.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String value, Collector<String> collector) throws Exception {
                String[] splits = value.split(",");
                for (String split : splits) {
                    collector.collect(split);
                }
            }
        }).filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String s) throws Exception {
                return !"pk".equals(s);
            }
        }).print();
    }
}
