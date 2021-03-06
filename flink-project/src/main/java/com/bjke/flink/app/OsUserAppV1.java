package com.bjke.flink.app;

import com.alibaba.fastjson.JSON;
import com.bjke.flink.domain.Access;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;

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
//                return "1".equals(value.get$is_first_day());
//            }
//        });
        // 设置系统纬度，是否首次登录
        // 3> (Android,1,43)
        //4> (iOS,1,14)
        SingleOutputStreamOperator<Tuple3<String, String, Integer>> result = cleanStream.map(new MapFunction<Access, Tuple3<String, String, Integer>>() {
            @Override
            public Tuple3<String, String, Integer> map(Access value) throws Exception {
                return Tuple3.of(value.get$os(), value.get$is_first_day(), 1);
            }
        }).keyBy(new KeySelector<Tuple3<String, String, Integer>, Tuple2<String, String>>() {
            @Override
            public Tuple2<String, String> getKey(Tuple3<String, String, Integer> value) throws Exception {
                return Tuple2.of(value.f0, value.f1);
            }
        }).sum(2);//.print();
        FlinkJedisPoolConfig conf = new FlinkJedisPoolConfig.Builder().setHost("127.0.0.1").build();
        result.addSink(new RedisSink<Tuple3<String, String, Integer>>(conf, new RedisExampleMapper()));
        env.execute("OsUserAppV1");
    }

    static class RedisExampleMapper implements RedisMapper<Tuple3<String, String, Integer>> {

        @Override
        public RedisCommandDescription getCommandDescription() {
            return new RedisCommandDescription(RedisCommand.HSET, "pk-traffic");
        }

        @Override
        public String getKeyFromData(Tuple3<String, String, Integer> data) {
            return data.f0 + "_" + data.f1;
        }

        @Override
        public String getValueFromData(Tuple3<String, String, Integer> data) {
            return data.f2 + "";
        }
    }
}
