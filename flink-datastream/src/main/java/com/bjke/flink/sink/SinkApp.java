package com.bjke.flink.sink;

import com.bjke.flink.transformation.Access;
import org.apache.flink.api.common.functions.MapFunction;
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
    public static void toMySQL(StreamExecutionEnvironment env) {
//
//        DataStreamSource<String> source = env.readTextFile("data/access.log");
//
//        SingleOutputStreamOperator<Access> mapStream = source.map(new MapFunction<String, Access>() {
//            @Override
//            public Access map(String value) throws Exception {
//                String[] splits = value.split(",");
//                Long time = Long.parseLong(splits[0].trim());
//                String domain = splits[1].trim();
//                Double traffic = Double.parseDouble(splits[2].trim());
//
//                return new Access(time, domain, traffic);
//            }
//        });
//
//        SingleOutputStreamOperator<Access> result = mapStream.keyBy(new KeySelector<Access, String>() {
//            @Override
//            public String getKey(Access value) throws Exception {
//                return value.getDomain();
//            }
//        }).sum("traffic");
//
//        result.print();
//
//
//        FlinkJedisPoolConfig conf = new FlinkJedisPoolConfig.Builder().setHost("127.0.0.1").build();
//
//        result.map(new MapFunction<Access, Tuple2<String, Double>>() {
//                    @Override
//                    public Tuple2<String, Double> map(Access value) throws Exception {
//                        return Tuple2.of(value.getDomain(), value.getTraffic());
//                    }
//                }) // .addSink(new PKMySQLSink());
//                .addSink(new RedisSink<Tuple2<String, Double>>(conf, new PKRedisSink()));
    }

}
