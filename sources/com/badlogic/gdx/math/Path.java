package com.badlogic.gdx.math;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Path.class */
public interface Path<T> {
    T derivativeAt(T t, float f);

    T valueAt(T t, float f);

    float approximate(T t);

    float locate(T t);

    float approxLength(int i);
}
