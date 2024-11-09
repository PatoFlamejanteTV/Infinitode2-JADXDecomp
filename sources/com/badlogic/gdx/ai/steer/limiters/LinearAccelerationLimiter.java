package com.badlogic.gdx.ai.steer.limiters;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/limiters/LinearAccelerationLimiter.class */
public class LinearAccelerationLimiter extends NullLimiter {
    private float maxLinearAcceleration;

    public LinearAccelerationLimiter(float f) {
        this.maxLinearAcceleration = f;
    }

    @Override // com.badlogic.gdx.ai.steer.limiters.NullLimiter, com.badlogic.gdx.ai.steer.Limiter
    public float getMaxLinearAcceleration() {
        return this.maxLinearAcceleration;
    }

    @Override // com.badlogic.gdx.ai.steer.limiters.NullLimiter, com.badlogic.gdx.ai.steer.Limiter
    public void setMaxLinearAcceleration(float f) {
        this.maxLinearAcceleration = f;
    }
}
