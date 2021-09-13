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
 * 按照省份统计分析=
 */
public class OsUserAppV3 {
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
        SingleOutputStreamOperator<Tuple3<String, String, Integer>> result = cleanStream.map(new MapFunction<Access, Tuple3<String, String, Integer>>() {
            @Override
            public Tuple3<String, String, Integer> map(Access value) throws Exception {
                return Tuple3.of(value.get$city(), value.get$is_first_day(), 1);
            }
        }).keyBy(new KeySelector<Tuple3<String, String, Integer>, Tuple2<String, String>>() {
            @Override
            public Tuple2<String, String> getKey(Tuple3<String, String, Integer> value) throws Exception {
                return Tuple2.of(value.f0, value.f1);
            }
        }).sum(2);//.print("省份纬度统计新老用户: ");
        FlinkJedisPoolConfig conf = new FlinkJedisPoolConfig.Builder().setHost("127.0.0.1").build();
        result.addSink(new RedisSink<Tuple3<String, String, Integer>>(conf, new RedisExampleMapper()));
        env.execute("OsUserAppV1");
    }


    static class RedisExampleMapper implements RedisMapper<Tuple3<String, String, Integer>> {

        @Override
        public RedisCommandDescription getCommandDescription() {
            return new RedisCommandDescription(RedisCommand.HSET, "os-province");
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
