package com.badlogic.gdx.ai.steer;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/SteeringBehavior.class */
public abstract class SteeringBehavior<T extends Vector<T>> {
    protected Steerable<T> owner;
    protected Limiter limiter;
    protected boolean enabled;

    protected abstract SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration);

    public SteeringBehavior(Steerable<T> steerable) {
        this(steerable, null, true);
    }

    public SteeringBehavior(Steerable<T> steerable, Limiter limiter) {
        this(steerable, limiter, true);
    }

    public SteeringBehavior(Steerable<T> steerable, boolean z) {
        this(steerable, null, z);
    }

    public SteeringBehavior(Steerable<T> steerable, Limiter limiter, boolean z) {
        this.owner = steerable;
        this.limiter = limiter;
        this.enabled = z;
    }

    public SteeringAcceleration<T> calculateSteering(SteeringAcceleration<T> steeringAcceleration) {
        return isEnabled() ? calculateRealSteering(steeringAcceleration) : steeringAcceleration.setZero();
    }

    public Steerable<T> getOwner() {
        return this.owner;
    }

    public SteeringBehavior<T> setOwner(Steerable<T> steerable) {
        this.owner = steerable;
        return this;
    }

    public Limiter getLimiter() {
        return this.limiter;
    }

    public SteeringBehavior<T> setLimiter(Limiter limiter) {
        this.limiter = limiter;
        return this;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public SteeringBehavior<T> setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Limiter getActualLimiter() {
        return this.limiter == null ? this.owner : this.limiter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T newVector(Location<T> location) {
        return (T) location.getPosition().cpy().setZero();
    }
}
