package com.bjke.flink.udf;

import com.bjke.flink.domain.RecordDetails;
import org.apache.flink.api.common.functions.AggregateFunction;

public class TopNAggregateFunction implements AggregateFunction<RecordDetails, Long, Long> {
    @Override
    public Long createAccumulator() {
        return 0L;
    }

    @Override
    public Long add(RecordDetails value, Long accumulator) {
        return accumulator + 1;
    }

    @Override
    public Long getResult(Long accumulator) {
        return accumulator;
    }

    @Override
    public Long merge(Long a, Long b) {
        return null;
    }
}
