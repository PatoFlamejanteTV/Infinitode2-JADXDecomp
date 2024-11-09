package com.vladsch.flexmark.util.misc;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/misc/MinMaxAvgDouble.class */
public class MinMaxAvgDouble {
    private double min = Double.MAX_VALUE;
    private double max = Double.MIN_VALUE;
    private double total = 0.0d;

    public void add(double d) {
        this.total += d;
        this.min = Math.min(this.min, d);
        this.max = Math.max(this.max, d);
    }

    public void add(MinMaxAvgDouble minMaxAvgDouble) {
        this.total += minMaxAvgDouble.total;
        this.min = Math.min(this.min, minMaxAvgDouble.min);
        this.max = Math.max(this.max, minMaxAvgDouble.max);
    }

    public void diff(double d, double d2) {
        add(d2 - d);
    }

    public double getMin() {
        return this.min;
    }

    public double getMax() {
        return this.max;
    }

    public double getTotal() {
        return this.total;
    }

    public double getAvg(double d) {
        if (d == 0.0d) {
            return 0.0d;
        }
        return this.total / d;
    }
}
