package com.bjke.flink.sink;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class SinkApp {
    public static void main(String[] args) throws Exception {
        //创建上下文
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> source = env.socketTextStream("localhost", 9527);
        System.out.println("source:" + source.getParallelism());
        source.print();
        env.execute("SinkApp");
    }

}
