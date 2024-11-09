package com.badlogic.gdx.ai.steer.limiters;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/limiters/LinearLimiter.class */
public class LinearLimiter extends NullLimiter {
    private float maxLinearAcceleration;
    private float maxLinearSpeed;

    public LinearLimiter(float f, float f2) {
        this.maxLinearAcceleration = f;
        this.maxLinearSpeed = f2;
    }

    @Override // com.badlogic.gdx.ai.steer.limiters.NullLimiter, com.badlogic.gdx.ai.steer.Limiter
    public float getMaxLinearSpeed() {
        return this.maxLinearSpeed;
    }

    @Override // com.badlogic.gdx.ai.steer.limiters.NullLimiter, com.badlogic.gdx.ai.steer.Limiter
    public void setMaxLinearSpeed(float f) {
        this.maxLinearSpeed = f;
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
