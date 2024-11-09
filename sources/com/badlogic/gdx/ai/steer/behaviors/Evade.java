package com.badlogic.gdx.ai.steer.behaviors;

import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/Evade.class */
public class Evade<T extends Vector<T>> extends Pursue<T> {
    public Evade(Steerable<T> steerable, Steerable<T> steerable2) {
        this(steerable, steerable2, 1.0f);
    }

    public Evade(Steerable<T> steerable, Steerable<T> steerable2, float f) {
        super(steerable, steerable2, f);
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Pursue
    protected float getActualMaxLinearAcceleration() {
        return -getActualLimiter().getMaxLinearAcceleration();
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Pursue, com.badlogic.gdx.ai.steer.SteeringBehavior
    public Evade<T> setOwner(Steerable<T> steerable) {
        this.owner = steerable;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Pursue, com.badlogic.gdx.ai.steer.SteeringBehavior
    public Evade<T> setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Pursue, com.badlogic.gdx.ai.steer.SteeringBehavior
    public Evade<T> setLimiter(Limiter limiter) {
        this.limiter = limiter;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Pursue
    public Evade<T> setTarget(Steerable<T> steerable) {
        this.target = steerable;
        return this;
    }
}
