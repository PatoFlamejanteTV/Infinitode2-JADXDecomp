package com.badlogic.gdx.ai.utils;

import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/Location.class */
public interface Location<T extends Vector<T>> {
    T getPosition();

    float getOrientation();

    void setOrientation(float f);

    float vectorToAngle(T t);

    T angleToVector(T t, float f);

    Location<T> newLocation();
}
