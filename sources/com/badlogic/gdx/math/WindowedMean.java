package com.badlogic.gdx.math;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/WindowedMean.class */
public final class WindowedMean {
    float[] values;
    int last_value;
    int added_values = 0;
    float mean = 0.0f;
    boolean dirty = true;

    public WindowedMean(int i) {
        this.values = new float[i];
    }

    public final boolean hasEnoughData() {
        return this.added_values >= this.values.length;
    }

    public final void clear() {
        this.added_values = 0;
        this.last_value = 0;
        for (int i = 0; i < this.values.length; i++) {
            this.values[i] = 0.0f;
        }
        this.dirty = true;
    }

    public final void addValue(float f) {
        if (this.added_values < this.values.length) {
            this.added_values++;
        }
        float[] fArr = this.values;
        int i = this.last_value;
        this.last_value = i + 1;
        fArr[i] = f;
        if (this.last_value > this.values.length - 1) {
            this.last_value = 0;
        }
        this.dirty = true;
    }

    public final float getMean() {
        if (hasEnoughData()) {
            if (this.dirty) {
                float f = 0.0f;
                for (int i = 0; i < this.values.length; i++) {
                    f += this.values[i];
                }
                this.mean = f / this.values.length;
                this.dirty = false;
            }
            return this.mean;
        }
        return 0.0f;
    }

    public final float getOldest() {
        return this.added_values < this.values.length ? this.values[0] : this.values[this.last_value];
    }

    public final float getLatest() {
        return this.values[this.last_value - 1 == -1 ? this.values.length - 1 : this.last_value - 1];
    }

    public final float standardDeviation() {
        if (!hasEnoughData()) {
            return 0.0f;
        }
        float mean = getMean();
        float f = 0.0f;
        for (int i = 0; i < this.values.length; i++) {
            f += (this.values[i] - mean) * (this.values[i] - mean);
        }
        return (float) Math.sqrt(f / this.values.length);
    }

    public final float getLowest() {
        float f = Float.MAX_VALUE;
        for (int i = 0; i < this.values.length; i++) {
            f = Math.min(f, this.values[i]);
        }
        return f;
    }

    public final float getHighest() {
        float f = Float.MIN_NORMAL;
        for (int i = 0; i < this.values.length; i++) {
            f = Math.max(f, this.values[i]);
        }
        return f;
    }

    public final int getValueCount() {
        return this.added_values;
    }

    public final int getWindowSize() {
        return this.values.length;
    }

    public final float[] getWindowValues() {
        float[] fArr = new float[this.added_values];
        if (hasEnoughData()) {
            for (int i = 0; i < fArr.length; i++) {
                fArr[i] = this.values[(i + this.last_value) % this.values.length];
            }
        } else {
            System.arraycopy(this.values, 0, fArr, 0, this.added_values);
        }
        return fArr;
    }
}
