package com.badlogic.gdx.ai.steer;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/Steerable.class */
public interface Steerable<T extends Vector<T>> extends Limiter, Location<T> {
    T getLinearVelocity();

    float getAngularVelocity();

    float getBoundingRadius();

    boolean isTagged();

    void setTagged(boolean z);
}
