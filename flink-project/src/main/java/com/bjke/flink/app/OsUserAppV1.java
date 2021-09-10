package com.bjke.flink.app;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class OsUserAppV1 {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.execute("OsUserAppV1");
    }
}
