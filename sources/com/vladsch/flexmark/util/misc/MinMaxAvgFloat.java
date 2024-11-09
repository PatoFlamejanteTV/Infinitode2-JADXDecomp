package com.vladsch.flexmark.util.misc;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/misc/MinMaxAvgFloat.class */
public class MinMaxAvgFloat {
    private float min = Float.MAX_VALUE;
    private float max = Float.MIN_VALUE;
    private float total = 0.0f;

    public void add(float f) {
        this.total += f;
        this.min = Math.min(this.min, f);
        this.max = Math.max(this.max, f);
    }

    public void add(MinMaxAvgFloat minMaxAvgFloat) {
        this.total += minMaxAvgFloat.total;
        this.min = Math.min(this.min, minMaxAvgFloat.min);
        this.max = Math.max(this.max, minMaxAvgFloat.max);
    }

    public void diff(float f, float f2) {
        add(f2 - f);
    }

    public float getMin() {
        return this.min;
    }

    public float getMax() {
        return this.max;
    }

    public float getTotal() {
        return this.total;
    }

    public float getAvg(float f) {
        if (f == 0.0f) {
            return 0.0f;
        }
        return this.total / f;
    }
}
