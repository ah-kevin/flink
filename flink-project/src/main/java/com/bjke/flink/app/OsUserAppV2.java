package com.bjke.flink.app;

import com.alibaba.fastjson.JSON;
import com.bjke.flink.domain.Access;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;

/**
 * 按照新老用户统计分析=
 */
public class OsUserAppV2 {
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
        //4> (iOS,1,14)
     cleanStream.map(new MapFunction<Access, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(Access value) throws Exception {
                return Tuple2.of(value.get$is_first_day(), 1);
            }
        }).keyBy(x->x.f0).sum(1).print("总的新老用户: ").setParallelism(1);

        env.execute("OsUserAppV1");
    }

}
