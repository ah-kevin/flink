package com.bjke.flink.app;

import com.alibaba.fastjson.JSON;
import com.bjke.flink.domain.RecordDetailsV2;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcExecutionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class FlinkEtlApp {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        FastDateFormat format = FastDateFormat.getInstance("yyyMMdd-HH");
        SingleOutputStreamOperator<RecordDetailsV2> source = env.readTextFile("data/record_details.json").map(new MapFunction<String, RecordDetailsV2>() {
            @Override
            public RecordDetailsV2 map(String value) throws Exception {
                try {
                    RecordDetailsV2 bean = JSON.parseObject(value, RecordDetailsV2.class);
                    Long date = bean.date;
                    String[] split = format.format(date).split("-");
                    String day = split[0];
                    String hour = split[1];
                    bean.dateDay = day;
                    bean.hour = hour;
                    return bean;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
        //.addSink(JdbcSink.sink("insert into record_details_test values(?)", (preparedStatement, s) -> {
//                    preparedStatement.setInt(1, s.id);
//                },
//                JdbcExecutionOptions.builder().withBatchSize(50).withBatchIntervalMs(4000).build(),
//                new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
//                        .withUrl("jdbc:clickhouse://47.117.114.66:8123/werewolf")
//                        .withUsername("default")
//                        .withPassword("890728")
//                        .withDriverName("ru.yandex.clickhouse.ClickHouseDriver").build()));
//        ;
        source.print();
        env.execute();
    }
}
