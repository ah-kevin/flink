package com.bjke.flink.domain;

public class PlayerGroupCount {
    public String name;
    public String group;
    public String score;
    public long count;
    public long start;
    public long end;

    public PlayerGroupCount(String name, String group, String score, long count, long start, long end) {
        this.name = name;
        this.group = group;
        this.score = score;
        this.count = count;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "PlayerGroupCount{" +
                "name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", score='" + score + '\'' +
                ", count=" + count +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    public PlayerGroupCount() {
    }
}
