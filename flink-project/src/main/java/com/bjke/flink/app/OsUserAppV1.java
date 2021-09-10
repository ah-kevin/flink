package com.bjke.flink.app;

import com.alibaba.fastjson.JSON;
import com.bjke.flink.domain.Access;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Objects;

/**
 * 按照手机系统维度
 */
public class OsUserAppV1 {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        SingleOutputStreamOperator<Access> cleanStream = env.readTextFile("data/access.json").map(new MapFunction<String, Access>() {
            @Override
            public Access map(String value) throws Exception {
                try {
                    return JSON.parseObject(value, Access.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
//                .filter(Objects::nonNull).filter(new FilterFunction<Access>() {
//            @Override
//            public boolean filter(Access value) throws Exception {
//                return "$is_first_day".equals(value.get$is_first_day());
//            }
//        });
        cleanStream.print();
        env.execute("OsUserAppV1");
    }
}
