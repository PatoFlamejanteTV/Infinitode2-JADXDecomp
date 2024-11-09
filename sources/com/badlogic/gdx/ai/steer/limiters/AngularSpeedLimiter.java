package com.badlogic.gdx.ai.steer.limiters;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/limiters/AngularSpeedLimiter.class */
public class AngularSpeedLimiter extends NullLimiter {
    private float maxAngularSpeed;

    public AngularSpeedLimiter(float f) {
        this.maxAngularSpeed = f;
    }

    @Override // com.badlogic.gdx.ai.steer.limiters.NullLimiter, com.badlogic.gdx.ai.steer.Limiter
    public float getMaxAngularSpeed() {
        return this.maxAngularSpeed;
    }

    @Override // com.badlogic.gdx.ai.steer.limiters.NullLimiter, com.badlogic.gdx.ai.steer.Limiter
    public void setMaxAngularSpeed(float f) {
        this.maxAngularSpeed = f;
    }
}
