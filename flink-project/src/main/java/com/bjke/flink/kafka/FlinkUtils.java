package com.bjke.flink.kafka;

import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class FlinkUtils {
    public static StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

    public static <T> DataStream<T> createKafkaStreamV2(String[] args, Class<? extends DeserializationSchema<T>> deserializer) throws Exception {
        ParameterTool tool = ParameterTool.fromPropertiesFile(args[0]);
        String groupId = tool.get("group.id", "test");
        String servers = tool.getRequired("bootstrap.servers");
        List<String> topics = Arrays.asList(tool.getRequired("kafka.input.topics").split(","));
        String autoCommit = tool.get("enable.auto.commit", "false");
        String offsetReset = tool.get("auto.offset.reset", "earliest");

        int interval = tool.getInt("checkpoint.interval", 5000);
        String checkpointFilePath = tool.getRequired("checkpoint.filePath");

        // kafka相关参数
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", servers);
        properties.setProperty("group.id", groupId);
        properties.setProperty("enable.auto.commit", autoCommit);
        properties.setProperty("auto.offset.reset", offsetReset);

        // checkPoint 参数
        env.enableCheckpointing(interval);
        env.setStateBackend(new FsStateBackend(checkpointFilePath));
        env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
        env.setRestartStrategy(RestartStrategies.fixedDelayRestart(2, Time.of(5, TimeUnit.SECONDS)));

        FlinkKafkaConsumer<T> kafkaConsumer = new FlinkKafkaConsumer<>(topics, deserializer.newInstance(), properties);
        return env.addSource(kafkaConsumer);
    }

    public static DataStream<String> createKafkaStreamV1(String[] args) throws Exception {
        ParameterTool tool = ParameterTool.fromPropertiesFile(args[0]);
        String groupId = tool.get("group.id", "test");
        String servers = tool.getRequired("bootstrap.servers");
        List<String> topics = Arrays.asList(tool.getRequired("kafka.input.topics").split(","));
        String autoCommit = tool.get("enable.auto.commit", "false");
        String offsetReset = tool.get("auto.offset.reset", "earliest");

        int interval = tool.getInt("checkpoint.interval", 5000);
        String checkpointFilePath = tool.getRequired("checkpoint.filePath");

        // kafka相关参数
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", servers);
        properties.setProperty("group.id", groupId);
        properties.setProperty("enable.auto.commit", autoCommit);
        properties.setProperty("auto.offset.reset", offsetReset);

        // checkPoint 参数
        env.enableCheckpointing(interval);
        env.setStateBackend(new FsStateBackend(checkpointFilePath));
        env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
        env.setRestartStrategy(RestartStrategies.fixedDelayRestart(2, Time.of(5, TimeUnit.SECONDS)));

        FlinkKafkaConsumer<String> kafkaConsumer = new FlinkKafkaConsumer<>(topics, new SimpleStringSchema(), properties);
        return env.addSource(kafkaConsumer);
    }

    public static void main(String[] args) throws IOException {
        ParameterTool tool = ParameterTool.fromPropertiesFile(args[0]);
        String groupId = tool.get("group.id", "test");
        String servers = tool.getRequired("bootstrap.servers");
        System.out.println(groupId);
        System.out.println(servers);
    }

    public static <T> DataStream<T> createKafkaStreamV3(String[] args, Class<? extends KafkaDeserializationSchema<T>> deserializer) throws Exception {
        ParameterTool tool = ParameterTool.fromPropertiesFile(args[0]);
        String groupId = tool.get("group.id", "test");
        String servers = tool.getRequired("bootstrap.servers");
        List<String> topics = Arrays.asList(tool.getRequired("kafka.input.topics").split(","));
        String autoCommit = tool.get("enable.auto.commit", "false");
        String offsetReset = tool.get("auto.offset.reset", "earliest");

        int interval = tool.getInt("checkpoint.interval", 5000);
        String checkpointFilePath = tool.getRequired("checkpoint.filePath");

        // kafka相关参数
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", servers);
        properties.setProperty("group.id", groupId);
        properties.setProperty("enable.auto.commit", autoCommit);
        properties.setProperty("auto.offset.reset", offsetReset);

        // checkPoint 参数
        env.enableCheckpointing(interval);
        env.setStateBackend(new FsStateBackend(checkpointFilePath));
        env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
        env.setRestartStrategy(RestartStrategies.fixedDelayRestart(2, Time.of(5, TimeUnit.SECONDS)));

        FlinkKafkaConsumer<T> kafkaConsumer = new FlinkKafkaConsumer<>(topics, deserializer.newInstance(), properties);
        return env.addSource(kafkaConsumer);
    }

}
