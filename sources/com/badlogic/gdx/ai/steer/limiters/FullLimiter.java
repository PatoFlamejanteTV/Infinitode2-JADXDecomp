package com.badlogic.gdx.ai.steer.limiters;

import com.badlogic.gdx.ai.steer.Limiter;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/limiters/FullLimiter.class */
public class FullLimiter implements Limiter {
    private float maxLinearAcceleration;
    private float maxLinearSpeed;
    private float maxAngularAcceleration;
    private float maxAngularSpeed;
    private float zeroLinearSpeedThreshold;

    public FullLimiter(float f, float f2, float f3, float f4) {
        this.maxLinearAcceleration = f;
        this.maxLinearSpeed = f2;
        this.maxAngularAcceleration = f3;
        this.maxAngularSpeed = f4;
    }

    @Override // com.badlogic.gdx.ai.steer.Limiter
    public float getMaxLinearSpeed() {
        return this.maxLinearSpeed;
    }

    @Override // com.badlogic.gdx.ai.steer.Limiter
    public void setMaxLinearSpeed(float f) {
        this.maxLinearSpeed = f;
    }

    @Override // com.badlogic.gdx.ai.steer.Limiter
    public float getMaxLinearAcceleration() {
        return this.maxLinearAcceleration;
    }

    @Override // com.badlogic.gdx.ai.steer.Limiter
    public void setMaxLinearAcceleration(float f) {
        this.maxLinearAcceleration = f;
    }

    @Override // com.badlogic.gdx.ai.steer.Limiter
    public float getMaxAngularSpeed() {
        return this.maxAngularSpeed;
    }

    @Override // com.badlogic.gdx.ai.steer.Limiter
    public void setMaxAngularSpeed(float f) {
        this.maxAngularSpeed = f;
    }

    @Override // com.badlogic.gdx.ai.steer.Limiter
    public float getMaxAngularAcceleration() {
        return this.maxAngularAcceleration;
    }

    @Override // com.badlogic.gdx.ai.steer.Limiter
    public void setMaxAngularAcceleration(float f) {
        this.maxAngularAcceleration = f;
    }

    @Override // com.badlogic.gdx.ai.steer.Limiter
    public float getZeroLinearSpeedThreshold() {
        return this.zeroLinearSpeedThreshold;
    }

    @Override // com.badlogic.gdx.ai.steer.Limiter
    public void setZeroLinearSpeedThreshold(float f) {
        this.zeroLinearSpeedThreshold = f;
    }
}
