package com.badlogic.gdx.math;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Bezier.class */
public class Bezier<T extends Vector<T>> implements Path<T> {
    public Array<T> points = new Array<>();
    private T tmp;
    private T tmp2;
    private T tmp3;

    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends Vector<T>> T linear(T t, float f, T t2, T t3, T t4) {
        return (T) t.set(t2).scl(1.0f - f).add(t4.set(t3).scl(f));
    }

    public static <T extends Vector<T>> T linear_derivative(T t, float f, T t2, T t3, T t4) {
        return (T) t.set(t3).sub(t2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends Vector<T>> T quadratic(T t, float f, T t2, T t3, T t4, T t5) {
        float f2 = 1.0f - f;
        return (T) t.set(t2).scl(f2 * f2).add(t5.set(t3).scl(f2 * 2.0f * f)).add(t5.set(t4).scl(f * f));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends Vector<T>> T quadratic_derivative(T t, float f, T t2, T t3, T t4, T t5) {
        return (T) t.set(t3).sub(t2).scl(2.0f).scl(1.0f - f).add(t5.set(t4).sub(t3).scl(f).scl(2.0f));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends Vector<T>> T cubic(T t, float f, T t2, T t3, T t4, T t5, T t6) {
        float f2 = 1.0f - f;
        float f3 = f2 * f2;
        float f4 = f * f;
        return (T) t.set(t2).scl(f3 * f2).add(t6.set(t3).scl(3.0f * f3 * f)).add(t6.set(t4).scl(3.0f * f2 * f4)).add(t6.set(t5).scl(f4 * f));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends Vector<T>> T cubic_derivative(T t, float f, T t2, T t3, T t4, T t5, T t6) {
        float f2 = 1.0f - f;
        return (T) t.set(t3).sub(t2).scl(f2 * f2 * 3.0f).add(t6.set(t4).sub(t3).scl(f2 * f * 6.0f)).add(t6.set(t5).sub(t4).scl(f * f * 3.0f));
    }

    public Bezier() {
    }

    public Bezier(T... tArr) {
        set(tArr);
    }

    public Bezier(T[] tArr, int i, int i2) {
        set(tArr, i, i2);
    }

    public Bezier(Array<T> array, int i, int i2) {
        set(array, i, i2);
    }

    public Bezier set(T... tArr) {
        return set(tArr, 0, tArr.length);
    }

    public Bezier set(T[] tArr, int i, int i2) {
        if (i2 < 2 || i2 > 4) {
            throw new GdxRuntimeException("Only first, second and third degree Bezier curves are supported.");
        }
        if (this.tmp == null) {
            this.tmp = (T) tArr[0].cpy();
        }
        if (this.tmp2 == null) {
            this.tmp2 = (T) tArr[0].cpy();
        }
        if (this.tmp3 == null) {
            this.tmp3 = (T) tArr[0].cpy();
        }
        this.points.clear();
        this.points.addAll(tArr, i, i2);
        return this;
    }

    public Bezier set(Array<T> array, int i, int i2) {
        if (i2 < 2 || i2 > 4) {
            throw new GdxRuntimeException("Only first, second and third degree Bezier curves are supported.");
        }
        if (this.tmp == null) {
            this.tmp = (T) array.get(0).cpy();
        }
        if (this.tmp2 == null) {
            this.tmp2 = (T) array.get(0).cpy();
        }
        if (this.tmp3 == null) {
            this.tmp3 = (T) array.get(0).cpy();
        }
        this.points.clear();
        this.points.addAll((Array<? extends T>) array, i, i2);
        return this;
    }

    @Override // com.badlogic.gdx.math.Path
    public T valueAt(T t, float f) {
        int i = this.points.size;
        if (i == 2) {
            linear(t, f, this.points.get(0), this.points.get(1), this.tmp);
        } else if (i == 3) {
            quadratic(t, f, this.points.get(0), this.points.get(1), this.points.get(2), this.tmp);
        } else if (i == 4) {
            cubic(t, f, this.points.get(0), this.points.get(1), this.points.get(2), this.points.get(3), this.tmp);
        }
        return t;
    }

    @Override // com.badlogic.gdx.math.Path
    public T derivativeAt(T t, float f) {
        int i = this.points.size;
        if (i == 2) {
            linear_derivative(t, f, this.points.get(0), this.points.get(1), this.tmp);
        } else if (i == 3) {
            quadratic_derivative(t, f, this.points.get(0), this.points.get(1), this.points.get(2), this.tmp);
        } else if (i == 4) {
            cubic_derivative(t, f, this.points.get(0), this.points.get(1), this.points.get(2), this.points.get(3), this.tmp);
        }
        return t;
    }

    @Override // com.badlogic.gdx.math.Path
    public float approximate(T t) {
        T t2 = this.points.get(0);
        T t3 = this.points.get(this.points.size - 1);
        float dst2 = t2.dst2(t3);
        float dst22 = t.dst2(t3);
        float dst23 = t.dst2(t2);
        float sqrt = (float) Math.sqrt(dst2);
        return MathUtils.clamp((sqrt - (((dst22 + dst2) - dst23) / (sqrt * 2.0f))) / sqrt, 0.0f, 1.0f);
    }

    @Override // com.badlogic.gdx.math.Path
    public float locate(T t) {
        return approximate((Bezier<T>) t);
    }

    @Override // com.badlogic.gdx.math.Path
    public float approxLength(int i) {
        float f = 0.0f;
        for (int i2 = 0; i2 < i; i2++) {
            this.tmp2.set(this.tmp3);
            valueAt((Bezier<T>) this.tmp3, i2 / (i - 1.0f));
            if (i2 > 0) {
                f += this.tmp2.dst(this.tmp3);
            }
        }
        return f;
    }
}
