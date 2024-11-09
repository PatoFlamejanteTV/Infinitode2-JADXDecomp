package com.badlogic.gdx.ai.steer.limiters;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/limiters/AngularAccelerationLimiter.class */
public class AngularAccelerationLimiter extends NullLimiter {
    private float maxAngularAcceleration;

    public AngularAccelerationLimiter(float f) {
        this.maxAngularAcceleration = f;
    }

    @Override // com.badlogic.gdx.ai.steer.limiters.NullLimiter, com.badlogic.gdx.ai.steer.Limiter
    public float getMaxAngularAcceleration() {
        return this.maxAngularAcceleration;
    }

    @Override // com.badlogic.gdx.ai.steer.limiters.NullLimiter, com.badlogic.gdx.ai.steer.Limiter
    public void setMaxAngularAcceleration(float f) {
        this.maxAngularAcceleration = f;
    }
}
