package com.bjke.flink.kafka;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;
import redis.clients.jedis.Tuple;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class FlinkKafkaApp {
    public static void main(String[] args) throws Exception {
//        DataStream<String> stream = FlinkUtils.createKafkaStreamV1(args);
        DataStream<String> stream = FlinkUtils.createKafkaStreamV2(args, SimpleStringSchema.class);
        stream.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String value, Collector<String> out) throws Exception {
                String[] split = value.split(",");
                for (String s : split) {
                    out.collect(s);
                }
            }
        }).map(new MapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(String value) throws Exception {
                return Tuple2.of(value, 1);
            }
        }).keyBy(x -> x.f0).sum(1).print("wc:");


        FlinkUtils.env.socketTextStream("localhost", 9527)
                .map(new MapFunction<String, String>() {
                    @Override
                    public String map(String value) throws Exception {
                        if (value.contains("pk")) throw new RuntimeException("pk died");
                        return value.toUpperCase();
                    }
                }).print("from socket: ");

        FlinkUtils.env.execute("FlinkKafkaApp");
    }

    public static void test01() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // kafka相关参数
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("group.id", "test");
        properties.setProperty("enable.auto.commit", "false");
        properties.setProperty("auto.offset.reset", "earliest");
        String topic = "pk10";

        // checkPoint 参数
        env.enableCheckpointing(5000);
        env.setStateBackend(new FsStateBackend("file:///Users/lennon/workspaces/clickhouse/flink/state"));
        env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
        env.setRestartStrategy(RestartStrategies.fixedDelayRestart(2, Time.of(5, TimeUnit.SECONDS)));

        FlinkKafkaConsumer<String> kafkaConsumer = new FlinkKafkaConsumer<>(topic, new SimpleStringSchema(), properties);
        DataStream<String> stream = env
                .addSource(kafkaConsumer);
        stream.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String value, Collector<String> out) throws Exception {
                String[] split = value.split(",");
                for (String s : split) {
                    out.collect(s);
                }
            }
        }).map(new MapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(String value) throws Exception {
                return Tuple2.of(value, 1);
            }
        }).keyBy(x -> x.f0).sum(1).print("wc:");


        env.socketTextStream("localhost", 9527)
                .map(new MapFunction<String, String>() {
                    @Override
                    public String map(String value) throws Exception {
                        if (value.contains("pk")) throw new RuntimeException("pk died");
                        return value.toUpperCase();
                    }
                }).print("from socket: ");
        env.execute("FlinkKafkaApp");
    }
}
