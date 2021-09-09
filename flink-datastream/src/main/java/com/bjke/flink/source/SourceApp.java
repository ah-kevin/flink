package com.bjke.flink.source;

import com.bjke.flink.transformation.Access;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.NumberSequenceIterator;

import java.util.Properties;

public class SourceApp {
    public static void main(String[] args) throws Exception {
        //创建上下文
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //   test01(env);
        //   test02(env);
//        test05(env);
        test04(env);
        env.execute("sourceApp");
    }

    private static void test05(StreamExecutionEnvironment env) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("group.id", "test");
        DataStream<String> stream = env
                .addSource(new FlinkKafkaConsumer<>("flinkTopic", new SimpleStringSchema(), properties));
        System.out.println(stream.getParallelism());
        stream.print();
    }

    public static void test03(StreamExecutionEnvironment env) {
        DataStreamSource<Access> accessDataStreamSource = env.addSource(new AccessSource());
        System.out.println(accessDataStreamSource.getParallelism());
        accessDataStreamSource.print();
    }

    public static void test04(StreamExecutionEnvironment env) {
        DataStreamSource<Student> source = env.addSource(new StudentSource());
        System.out.println(source.getParallelism());
        source.print();
    }

    public static void test02(StreamExecutionEnvironment env) {
        DataStreamSource<Long> source = env.fromParallelCollection(
                new NumberSequenceIterator(1, 10), Long.class
        );
        System.out.println("source:" + source.getParallelism());

        // 接受socket过来的数据，一行一个档次
        SingleOutputStreamOperator<Long> filterStream = source.filter(new FilterFunction<Long>() {
            @Override
            public boolean filter(Long v) throws Exception {
                return v >= 5;
            }
        });
        System.out.println("filter..." + filterStream.getParallelism());
        filterStream.print();
    }

    public static void test01(StreamExecutionEnvironment env) {
        DataStreamSource<String> source = env.socketTextStream("localhost", 9527);
        System.out.println(source.getParallelism());
        // 接受socket过来的数据，一行一个档次
        SingleOutputStreamOperator<String> filterStream = source.filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String s) throws Exception {
                return !"pk".equals(s);
            }
        }).setParallelism(2);
        System.out.println("filter..." + filterStream.getParallelism());
        filterStream.print();
    }
}