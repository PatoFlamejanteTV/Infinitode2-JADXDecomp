package com.badlogic.gdx.ai.steer.limiters;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/limiters/AngularLimiter.class */
public class AngularLimiter extends NullLimiter {
    private float maxAngularAcceleration;
    private float maxAngularSpeed;

    public AngularLimiter(float f, float f2) {
        this.maxAngularAcceleration = f;
        this.maxAngularSpeed = f2;
    }

    @Override // com.badlogic.gdx.ai.steer.limiters.NullLimiter, com.badlogic.gdx.ai.steer.Limiter
    public float getMaxAngularSpeed() {
        return this.maxAngularSpeed;
    }

    @Override // com.badlogic.gdx.ai.steer.limiters.NullLimiter, com.badlogic.gdx.ai.steer.Limiter
    public void setMaxAngularSpeed(float f) {
        this.maxAngularSpeed = f;
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
