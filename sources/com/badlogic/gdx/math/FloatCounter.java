package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.Pool;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/FloatCounter.class */
public class FloatCounter implements Pool.Poolable {
    public int count;
    public float total;
    public float min;
    public float max;
    public float average;
    public float latest;
    public float value;
    public final WindowedMean mean;

    public FloatCounter(int i) {
        this.mean = i > 1 ? new WindowedMean(i) : null;
        reset();
    }

    public void put(float f) {
        this.latest = f;
        this.total += f;
        this.count++;
        this.average = this.total / this.count;
        if (this.mean != null) {
            this.mean.addValue(f);
            this.value = this.mean.getMean();
        } else {
            this.value = this.latest;
        }
        if (this.mean == null || this.mean.hasEnoughData()) {
            if (this.value < this.min) {
                this.min = this.value;
            }
            if (this.value > this.max) {
                this.max = this.value;
            }
        }
    }

    @Override // com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        this.count = 0;
        this.total = 0.0f;
        this.min = Float.MAX_VALUE;
        this.max = -3.4028235E38f;
        this.average = 0.0f;
        this.latest = 0.0f;
        this.value = 0.0f;
        if (this.mean != null) {
            this.mean.clear();
        }
    }

    public String toString() {
        return "FloatCounter{count=" + this.count + ", total=" + this.total + ", min=" + this.min + ", max=" + this.max + ", average=" + this.average + ", latest=" + this.latest + ", value=" + this.value + '}';
    }
}
