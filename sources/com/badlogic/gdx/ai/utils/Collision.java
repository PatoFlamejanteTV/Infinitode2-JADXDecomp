package com.badlogic.gdx.ai.utils;

import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/Collision.class */
public class Collision<T extends Vector<T>> {
    public T point;
    public T normal;

    public Collision(T t, T t2) {
        this.point = t;
        this.normal = t2;
    }

    public Collision<T> set(Collision<T> collision) {
        this.point.set(collision.point);
        this.normal.set(collision.normal);
        return this;
    }

    public Collision<T> set(T t, T t2) {
        this.point.set(t);
        this.normal.set(t2);
        return this;
    }
}
