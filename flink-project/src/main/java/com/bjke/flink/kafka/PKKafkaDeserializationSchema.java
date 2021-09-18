package com.bjke.flink.kafka;

import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;
import org.apache.flink.util.Collector;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.nio.charset.StandardCharsets;

public class PKKafkaDeserializationSchema implements KafkaDeserializationSchema<Tuple2<String, String>> {
    @Override
    public boolean isEndOfStream(Tuple2<String, String> stringStringTuple2) {
        return false;
    }

    @Override
    public Tuple2<String, String> deserialize(ConsumerRecord<byte[], byte[]> records) throws Exception {
        String topic = records.topic();
        int partition = records.partition();
        long offset = records.offset();
        String id = topic + "_" + partition + "_" + offset;
        String value = new String(records.value(), StandardCharsets.UTF_8);
        return Tuple2.of(id, value);
    }

    @Override
    public TypeInformation<Tuple2<String, String>> getProducedType() {
        return TypeInformation.of(new TypeHint<Tuple2<String, String>>() {
            @Override
            public TypeInformation<Tuple2<String, String>> getTypeInfo() {
                return super.getTypeInfo();
            }
        });
    }
}
