package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/CumulativeDistribution.class */
public class CumulativeDistribution<T> {
    private Array<CumulativeDistribution<T>.CumulativeValue> values = new Array<>(false, 10, CumulativeValue.class);

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/math/CumulativeDistribution$CumulativeValue.class */
    public class CumulativeValue {
        public T value;
        public float frequency;
        public float interval;

        public CumulativeValue(T t, float f, float f2) {
            this.value = t;
            this.frequency = f;
            this.interval = f2;
        }
    }

    public void add(T t, float f) {
        this.values.add(new CumulativeValue(t, 0.0f, f));
    }

    public void add(T t) {
        this.values.add(new CumulativeValue(t, 0.0f, 0.0f));
    }

    public void generate() {
        float f = 0.0f;
        for (int i = 0; i < this.values.size; i++) {
            f += this.values.items[i].interval;
            this.values.items[i].frequency = f;
        }
    }

    public void generateNormalized() {
        float f = 0.0f;
        for (int i = 0; i < this.values.size; i++) {
            f += this.values.items[i].interval;
        }
        float f2 = 0.0f;
        for (int i2 = 0; i2 < this.values.size; i2++) {
            f2 += this.values.items[i2].interval / f;
            this.values.items[i2].frequency = f2;
        }
    }

    public void generateUniform() {
        float f = 1.0f / this.values.size;
        for (int i = 0; i < this.values.size; i++) {
            this.values.items[i].interval = f;
            this.values.items[i].frequency = (i + 1) * f;
        }
    }

    public T value(float f) {
        int i = this.values.size - 1;
        int i2 = 0;
        while (i2 <= i) {
            int i3 = i2 + ((i - i2) / 2);
            CumulativeDistribution<T>.CumulativeValue cumulativeValue = this.values.items[i3];
            if (f >= cumulativeValue.frequency) {
                if (f <= cumulativeValue.frequency) {
                    break;
                }
                i2 = i3 + 1;
            } else {
                i = i3 - 1;
            }
        }
        return this.values.items[i2].value;
    }

    public T value() {
        return value(MathUtils.random());
    }

    public int size() {
        return this.values.size;
    }

    public float getInterval(int i) {
        return this.values.items[i].interval;
    }

    public T getValue(int i) {
        return this.values.items[i].value;
    }

    public void setInterval(T t, float f) {
        Array.ArrayIterator<CumulativeDistribution<T>.CumulativeValue> it = this.values.iterator();
        while (it.hasNext()) {
            CumulativeDistribution<T>.CumulativeValue next = it.next();
            if (next.value == t) {
                next.interval = f;
                return;
            }
        }
    }

    public void setInterval(int i, float f) {
        this.values.items[i].interval = f;
    }

    public void clear() {
        this.values.clear();
    }
}
