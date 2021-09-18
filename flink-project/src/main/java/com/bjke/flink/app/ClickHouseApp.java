package com.bjke.flink.app;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcExecutionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class ClickHouseApp {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // col1,col2,col3
        env.socketTextStream("localhost", 9527)
                .map(new MapFunction<String, Tuple3<String, String, String>>() {
                    @Override
                    public Tuple3<String, String, String> map(String value) throws Exception {
                        String[] split = value.split(",");
                        return Tuple3.of(split[0].trim(), split[1].trim(), split[2].trim());
                    }
                }).addSink(JdbcSink.sink("insert into ch_test values(?,?,?)", (preparedStatement, s) -> {
                            preparedStatement.setString(1, s.f0);
                            preparedStatement.setString(2, s.f1);
                            preparedStatement.setString(3, s.f2);
                        },
                        JdbcExecutionOptions.builder().withBatchSize(3).withBatchIntervalMs(4000).build(),
                        new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
                                .withUrl("jdbc:clickhouse://47.117.114.66:8123/werewolf")
                                .withUsername("default")
                                .withPassword("890728")
                                .withDriverName("ru.yandex.clickhouse.ClickHouseDriver").build()));
        env.execute();
    }
}
