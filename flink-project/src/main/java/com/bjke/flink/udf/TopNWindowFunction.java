package com.bjke.flink.udf;

import com.bjke.flink.domain.PlayerGroupCount;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

public class TopNWindowFunction implements WindowFunction<Long, PlayerGroupCount, Tuple3<String, String, String>, TimeWindow> {
    @Override
    public void apply(Tuple3<String, String, String> value, TimeWindow window, Iterable<Long> input, Collector<PlayerGroupCount> out) throws Exception {
        String name = value.f0;
        String group = value.f1;
        String score = value.f2;
        Long count = input.iterator().next();
        long start = window.getStart();
        long end = window.getEnd();
        out.collect(new PlayerGroupCount(name, group, score, count, start, end));
    }
}
