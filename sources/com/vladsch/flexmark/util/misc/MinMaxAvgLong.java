package com.vladsch.flexmark.util.misc;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/misc/MinMaxAvgLong.class */
public class MinMaxAvgLong {
    private long min = Long.MAX_VALUE;
    private long max = Long.MIN_VALUE;
    private long total = 0;

    public void add(long j) {
        this.total += j;
        this.min = Math.min(this.min, j);
        this.max = Math.max(this.max, j);
    }

    public void add(MinMaxAvgLong minMaxAvgLong) {
        this.total += minMaxAvgLong.total;
        this.min = Math.min(this.min, minMaxAvgLong.min);
        this.max = Math.max(this.max, minMaxAvgLong.max);
    }

    public void diff(long j, long j2) {
        add(j2 - j);
    }

    public long getMin() {
        return this.min;
    }

    public long getMax() {
        return this.max;
    }

    public long getTotal() {
        return this.total;
    }

    public long getAvg(long j) {
        if (j == 0) {
            return 0L;
        }
        return this.total / j;
    }
}
