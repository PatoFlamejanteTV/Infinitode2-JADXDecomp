package com.badlogic.gdx.ai.steer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/Limiter.class */
public interface Limiter {
    float getZeroLinearSpeedThreshold();

    void setZeroLinearSpeedThreshold(float f);

    float getMaxLinearSpeed();

    void setMaxLinearSpeed(float f);

    float getMaxLinearAcceleration();

    void setMaxLinearAcceleration(float f);

    float getMaxAngularSpeed();

    void setMaxAngularSpeed(float f);

    float getMaxAngularAcceleration();

    void setMaxAngularAcceleration(float f);
}
