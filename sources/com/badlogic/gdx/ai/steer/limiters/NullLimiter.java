package com.badlogic.gdx.ai.steer.limiters;

import com.badlogic.gdx.ai.steer.Limiter;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/limiters/NullLimiter.class */
public class NullLimiter implements Limiter {
    public static final NullLimiter NEUTRAL_LIMITER = new NullLimiter() { // from class: com.badlogic.gdx.ai.steer.limiters.NullLimiter.1
        @Override // com.badlogic.gdx.ai.steer.limiters.NullLimiter, com.badlogic.gdx.ai.steer.Limiter
        public final float getMaxLinearSpeed() {
            return Float.POSITIVE_INFINITY;
        }

        @Override // com.badlogic.gdx.ai.steer.limiters.NullLimiter, com.badlogic.gdx.ai.steer.Limiter
        public final float getMaxLinearAcceleration() {
            return Float.POSITIVE_INFINITY;
        }

        @Override // com.badlogic.gdx.ai.steer.limiters.NullLimiter, com.badlogic.gdx.ai.steer.Limiter
        public final float getMaxAngularSpeed() {
            return Float.POSITIVE_INFINITY;
        }

        @Override // com.badlogic.gdx.ai.steer.limiters.NullLimiter, com.badlogic.gdx.ai.steer.Limiter
        public final float getMaxAngularAcceleration() {
            return Float.POSITIVE_INFINITY;
        }
    };

    @Override // com.badlogic.gdx.ai.steer.Limiter
    public float getMaxLinearSpeed() {
        throw new UnsupportedOperationException();
    }

    @Override // com.badlogic.gdx.ai.steer.Limiter
    public void setMaxLinearSpeed(float f) {
        throw new UnsupportedOperationException();
    }

    @Override // com.badlogic.gdx.ai.steer.Limiter
    public float getMaxLinearAcceleration() {
        throw new UnsupportedOperationException();
    }

    @Override // com.badlogic.gdx.ai.steer.Limiter
    public void setMaxLinearAcceleration(float f) {
        throw new UnsupportedOperationException();
    }

    @Override // com.badlogic.gdx.ai.steer.Limiter
    public float getMaxAngularSpeed() {
        throw new UnsupportedOperationException();
    }

    @Override // com.badlogic.gdx.ai.steer.Limiter
    public void setMaxAngularSpeed(float f) {
        throw new UnsupportedOperationException();
    }

    @Override // com.badlogic.gdx.ai.steer.Limiter
    public float getMaxAngularAcceleration() {
        throw new UnsupportedOperationException();
    }

    @Override // com.badlogic.gdx.ai.steer.Limiter
    public void setMaxAngularAcceleration(float f) {
        throw new UnsupportedOperationException();
    }

    @Override // com.badlogic.gdx.ai.steer.Limiter
    public float getZeroLinearSpeedThreshold() {
        return 0.001f;
    }

    @Override // com.badlogic.gdx.ai.steer.Limiter
    public void setZeroLinearSpeedThreshold(float f) {
        throw new UnsupportedOperationException();
    }
}
