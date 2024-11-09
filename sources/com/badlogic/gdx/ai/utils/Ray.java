package com.badlogic.gdx.ai.utils;

import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/Ray.class */
public class Ray<T extends Vector<T>> {
    public T start;
    public T end;

    public Ray(T t, T t2) {
        this.start = t;
        this.end = t2;
    }

    public Ray<T> set(Ray<T> ray) {
        this.start.set(ray.start);
        this.end.set(ray.end);
        return this;
    }

    public Ray<T> set(T t, T t2) {
        this.start.set(t);
        this.end.set(t2);
        return this;
    }
}
