package com.badlogic.gdx.ai.utils;

import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/RaycastCollisionDetector.class */
public interface RaycastCollisionDetector<T extends Vector<T>> {
    boolean collides(Ray<T> ray);

    boolean findCollision(Collision<T> collision, Ray<T> ray);
}
