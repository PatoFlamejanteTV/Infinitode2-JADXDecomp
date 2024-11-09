package com.badlogic.gdx.math;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/BSpline.class */
public class BSpline<T extends Vector<T>> implements Path<T> {
    private static final float d6 = 0.16666667f;
    public T[] controlPoints;
    public Array<T> knots;
    public int degree;
    public boolean continuous;
    public int spanCount;
    private T tmp;
    private T tmp2;
    private T tmp3;

    public static <T extends Vector<T>> T cubic(T t, float f, T[] tArr, boolean z, T t2) {
        int length = z ? tArr.length : tArr.length - 3;
        float f2 = f * length;
        int i = f >= 1.0f ? length - 1 : (int) f2;
        return (T) cubic(t, i, f2 - i, tArr, z, t2);
    }

    public static <T extends Vector<T>> T cubic_derivative(T t, float f, T[] tArr, boolean z, T t2) {
        int length = z ? tArr.length : tArr.length - 3;
        float f2 = f * length;
        int i = f >= 1.0f ? length - 1 : (int) f2;
        return (T) cubic(t, i, f2 - i, tArr, z, t2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends Vector<T>> T cubic(T t, int i, float f, T[] tArr, boolean z, T t2) {
        int length = tArr.length;
        float f2 = 1.0f - f;
        float f3 = f * f;
        float f4 = f3 * f;
        t.set(tArr[i]).scl((((3.0f * f4) - (6.0f * f3)) + 4.0f) * d6);
        if (z || i > 0) {
            t.add(t2.set(tArr[((length + i) - 1) % length]).scl(f2 * f2 * f2 * d6));
        }
        if (z || i < length - 1) {
            t.add(t2.set(tArr[(i + 1) % length]).scl((((-3.0f) * f4) + (3.0f * f3) + (3.0f * f) + 1.0f) * d6));
        }
        if (z || i < length - 2) {
            t.add(t2.set(tArr[(i + 2) % length]).scl(f4 * d6));
        }
        return t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends Vector<T>> T cubic_derivative(T t, int i, float f, T[] tArr, boolean z, T t2) {
        int length = tArr.length;
        float f2 = 1.0f - f;
        float f3 = f * f;
        t.set(tArr[i]).scl((1.5f * f3) - (f * 2.0f));
        if (z || i > 0) {
            t.add(t2.set(tArr[((length + i) - 1) % length]).scl((-0.5f) * f2 * f2));
        }
        if (z || i < length - 1) {
            t.add(t2.set(tArr[(i + 1) % length]).scl(((-1.5f) * f3) + f + 0.5f));
        }
        if (z || i < length - 2) {
            t.add(t2.set(tArr[(i + 2) % length]).scl(0.5f * f3));
        }
        return t;
    }

    public static <T extends Vector<T>> T calculate(T t, float f, T[] tArr, int i, boolean z, T t2) {
        int length = z ? tArr.length : tArr.length - i;
        float f2 = f * length;
        int i2 = f >= 1.0f ? length - 1 : (int) f2;
        return (T) calculate(t, i2, f2 - i2, tArr, i, z, t2);
    }

    public static <T extends Vector<T>> T derivative(T t, float f, T[] tArr, int i, boolean z, T t2) {
        int length = z ? tArr.length : tArr.length - i;
        float f2 = f * length;
        int i2 = f >= 1.0f ? length - 1 : (int) f2;
        return (T) derivative(t, i2, f2 - i2, tArr, i, z, t2);
    }

    public static <T extends Vector<T>> T calculate(T t, int i, float f, T[] tArr, int i2, boolean z, T t2) {
        switch (i2) {
            case 3:
                return (T) cubic(t, i, f, tArr, z, t2);
            default:
                throw new IllegalArgumentException();
        }
    }

    public static <T extends Vector<T>> T derivative(T t, int i, float f, T[] tArr, int i2, boolean z, T t2) {
        switch (i2) {
            case 3:
                return (T) cubic_derivative(t, i, f, tArr, z, t2);
            default:
                throw new IllegalArgumentException();
        }
    }

    public BSpline() {
    }

    public BSpline(T[] tArr, int i, boolean z) {
        set(tArr, i, z);
    }

    public BSpline set(T[] tArr, int i, boolean z) {
        if (this.tmp == null) {
            this.tmp = (T) tArr[0].cpy();
        }
        if (this.tmp2 == null) {
            this.tmp2 = (T) tArr[0].cpy();
        }
        if (this.tmp3 == null) {
            this.tmp3 = (T) tArr[0].cpy();
        }
        this.controlPoints = tArr;
        this.degree = i;
        this.continuous = z;
        this.spanCount = z ? tArr.length : tArr.length - i;
        if (this.knots == null) {
            this.knots = new Array<>(this.spanCount);
        } else {
            this.knots.clear();
            this.knots.ensureCapacity(this.spanCount);
        }
        for (int i2 = 0; i2 < this.spanCount; i2++) {
            this.knots.add(calculate(tArr[0].cpy(), z ? i2 : (int) (i2 + (0.5f * i)), 0.0f, tArr, i, z, this.tmp));
        }
        return this;
    }

    @Override // com.badlogic.gdx.math.Path
    public T valueAt(T t, float f) {
        int i = this.spanCount;
        float f2 = f * i;
        int i2 = f >= 1.0f ? i - 1 : (int) f2;
        return valueAt(t, i2, f2 - i2);
    }

    public T valueAt(T t, int i, float f) {
        return (T) calculate(t, this.continuous ? i : i + ((int) (this.degree * 0.5f)), f, this.controlPoints, this.degree, this.continuous, this.tmp);
    }

    @Override // com.badlogic.gdx.math.Path
    public T derivativeAt(T t, float f) {
        int i = this.spanCount;
        float f2 = f * i;
        int i2 = f >= 1.0f ? i - 1 : (int) f2;
        return derivativeAt(t, i2, f2 - i2);
    }

    public T derivativeAt(T t, int i, float f) {
        return (T) derivative(t, this.continuous ? i : i + ((int) (this.degree * 0.5f)), f, this.controlPoints, this.degree, this.continuous, this.tmp);
    }

    public int nearest(T t) {
        return nearest(t, 0, this.spanCount);
    }

    public int nearest(T t, int i, int i2) {
        while (i < 0) {
            i += this.spanCount;
        }
        int i3 = i % this.spanCount;
        float dst2 = t.dst2(this.knots.get(i3));
        for (int i4 = 1; i4 < i2; i4++) {
            int i5 = (i + i4) % this.spanCount;
            float dst22 = t.dst2(this.knots.get(i5));
            if (dst22 < dst2) {
                dst2 = dst22;
                i3 = i5;
            }
        }
        return i3;
    }

    @Override // com.badlogic.gdx.math.Path
    public float approximate(T t) {
        return approximate(t, nearest(t));
    }

    public float approximate(T t, int i, int i2) {
        return approximate(t, nearest(t, i, i2));
    }

    public float approximate(T t, int i) {
        T t2;
        T t3;
        T t4;
        int i2 = i;
        T t5 = this.knots.get(i);
        T t6 = this.knots.get(i > 0 ? i - 1 : this.spanCount - 1);
        T t7 = this.knots.get((i + 1) % this.spanCount);
        if (t.dst2(t7) < t.dst2(t6)) {
            t2 = t5;
            t3 = t7;
            t4 = t;
        } else {
            t2 = t6;
            t3 = t5;
            t4 = t;
            i2 = i > 0 ? i - 1 : this.spanCount - 1;
        }
        float dst2 = t2.dst2(t3);
        float dst22 = t4.dst2(t3);
        float dst23 = t4.dst2(t2);
        float sqrt = (float) Math.sqrt(dst2);
        return (i2 + MathUtils.clamp((sqrt - (((dst22 + dst2) - dst23) / (sqrt * 2.0f))) / sqrt, 0.0f, 1.0f)) / this.spanCount;
    }

    @Override // com.badlogic.gdx.math.Path
    public float locate(T t) {
        return approximate((BSpline<T>) t);
    }

    @Override // com.badlogic.gdx.math.Path
    public float approxLength(int i) {
        float f = 0.0f;
        for (int i2 = 0; i2 < i; i2++) {
            this.tmp2.set(this.tmp3);
            valueAt((BSpline<T>) this.tmp3, i2 / (i - 1.0f));
            if (i2 > 0) {
                f += this.tmp2.dst(this.tmp3);
            }
        }
        return f;
    }
}
