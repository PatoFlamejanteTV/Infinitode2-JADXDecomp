package com.badlogic.gdx.ai.steer.behaviors;

import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/Arrive.class */
public class Arrive<T extends Vector<T>> extends SteeringBehavior<T> {
    protected Location<T> target;
    protected float arrivalTolerance;
    protected float decelerationRadius;
    protected float timeToTarget;

    public Arrive(Steerable<T> steerable) {
        this(steerable, null);
    }

    public Arrive(Steerable<T> steerable, Location<T> location) {
        super(steerable);
        this.timeToTarget = 0.1f;
        this.target = location;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    protected SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
        return arrive(steeringAcceleration, this.target.getPosition());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SteeringAcceleration<T> arrive(SteeringAcceleration<T> steeringAcceleration, T t) {
        Vector sub = steeringAcceleration.linear.set(t).sub(this.owner.getPosition());
        float len = sub.len();
        if (len <= this.arrivalTolerance) {
            return steeringAcceleration.setZero();
        }
        Limiter actualLimiter = getActualLimiter();
        float maxLinearSpeed = actualLimiter.getMaxLinearSpeed();
        if (len <= this.decelerationRadius) {
            maxLinearSpeed *= len / this.decelerationRadius;
        }
        sub.scl(maxLinearSpeed / len).sub(this.owner.getLinearVelocity()).scl(1.0f / this.timeToTarget).limit(actualLimiter.getMaxLinearAcceleration());
        steeringAcceleration.angular = 0.0f;
        return steeringAcceleration;
    }

    public Location<T> getTarget() {
        return this.target;
    }

    public Arrive<T> setTarget(Location<T> location) {
        this.target = location;
        return this;
    }

    public float getArrivalTolerance() {
        return this.arrivalTolerance;
    }

    public Arrive<T> setArrivalTolerance(float f) {
        this.arrivalTolerance = f;
        return this;
    }

    public float getDecelerationRadius() {
        return this.decelerationRadius;
    }

    public Arrive<T> setDecelerationRadius(float f) {
        this.decelerationRadius = f;
        return this;
    }

    public float getTimeToTarget() {
        return this.timeToTarget;
    }

    public Arrive<T> setTimeToTarget(float f) {
        this.timeToTarget = f;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public Arrive<T> setOwner(Steerable<T> steerable) {
        this.owner = steerable;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public Arrive<T> setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public Arrive<T> setLimiter(Limiter limiter) {
        this.limiter = limiter;
        return this;
    }
}
