package com.vladsch.flexmark.util.misc;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/misc/MinMaxAvgInt.class */
public class MinMaxAvgInt {
    private int min = Integer.MAX_VALUE;
    private int max = Integer.MIN_VALUE;
    private int total = 0;

    public void add(int i) {
        this.total += i;
        this.min = Math.min(this.min, i);
        this.max = Math.max(this.max, i);
    }

    public void add(MinMaxAvgInt minMaxAvgInt) {
        this.total += minMaxAvgInt.total;
        this.min = Math.min(this.min, minMaxAvgInt.min);
        this.max = Math.max(this.max, minMaxAvgInt.max);
    }

    public void diff(int i, int i2) {
        add(i2 - i);
    }

    public int getMin() {
        return this.min;
    }

    public int getMax() {
        return this.max;
    }

    public int getTotal() {
        return this.total;
    }

    public int getAvg(int i) {
        if (i == 0) {
            return 0;
        }
        return this.total / i;
    }
}
