package com.badlogic.gdx.ai.steer.limiters;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/limiters/LinearSpeedLimiter.class */
public class LinearSpeedLimiter extends NullLimiter {
    private float maxLinearSpeed;

    public LinearSpeedLimiter(float f) {
        this.maxLinearSpeed = f;
    }

    @Override // com.badlogic.gdx.ai.steer.limiters.NullLimiter, com.badlogic.gdx.ai.steer.Limiter
    public float getMaxLinearSpeed() {
        return this.maxLinearSpeed;
    }

    @Override // com.badlogic.gdx.ai.steer.limiters.NullLimiter, com.badlogic.gdx.ai.steer.Limiter
    public void setMaxLinearSpeed(float f) {
        this.maxLinearSpeed = f;
    }
}
