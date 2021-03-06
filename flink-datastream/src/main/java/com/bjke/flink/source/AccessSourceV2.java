package com.bjke.flink.source;

import com.bjke.flink.transformation.Access;
import org.apache.flink.streaming.api.functions.source.ParallelSourceFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.Random;

public class AccessSourceV2 implements ParallelSourceFunction<Access> {
    boolean running = true;

    @Override
    public void run(SourceContext<Access> ctx) throws Exception {
        Random random = new Random();
        String[] domains = {"imooc.com", "a.com", "b.com"};
        while (running) {
            for (int i = 0; i < 10; i++) {
                Access access = new Access();
                access.setTime(1234567L);
                access.setDomain(domains[random.nextInt(domains.length)]);
                access.setTraffic(random.nextDouble() + 100);
                ctx.collect(access);
            }
            Thread.sleep(5000);
        }
    }

    @Override
    public void cancel() {
        running = false;
    }
}
