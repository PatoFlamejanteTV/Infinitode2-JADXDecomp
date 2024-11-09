package com.badlogic.gdx.ai.steer.behaviors;

import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.ArithmeticUtils;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/ReachOrientation.class */
public class ReachOrientation<T extends Vector<T>> extends SteeringBehavior<T> {
    protected Location<T> target;
    protected float alignTolerance;
    protected float decelerationRadius;
    protected float timeToTarget;

    public ReachOrientation(Steerable<T> steerable) {
        this(steerable, null);
    }

    public ReachOrientation(Steerable<T> steerable, Location<T> location) {
        super(steerable);
        this.timeToTarget = 0.1f;
        this.target = location;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    protected SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
        return reachOrientation(steeringAcceleration, this.target.getOrientation());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SteeringAcceleration<T> reachOrientation(SteeringAcceleration<T> steeringAcceleration, float f) {
        float wrapAngleAroundZero = ArithmeticUtils.wrapAngleAroundZero(f - this.owner.getOrientation());
        float f2 = wrapAngleAroundZero < 0.0f ? -wrapAngleAroundZero : wrapAngleAroundZero;
        float f3 = f2;
        if (f2 <= this.alignTolerance) {
            return steeringAcceleration.setZero();
        }
        Limiter actualLimiter = getActualLimiter();
        float maxAngularSpeed = actualLimiter.getMaxAngularSpeed();
        if (f3 <= this.decelerationRadius) {
            maxAngularSpeed *= f3 / this.decelerationRadius;
        }
        steeringAcceleration.angular = ((maxAngularSpeed * (wrapAngleAroundZero / f3)) - this.owner.getAngularVelocity()) / this.timeToTarget;
        float f4 = steeringAcceleration.angular < 0.0f ? -steeringAcceleration.angular : steeringAcceleration.angular;
        float f5 = f4;
        if (f4 > actualLimiter.getMaxAngularAcceleration()) {
            steeringAcceleration.angular *= actualLimiter.getMaxAngularAcceleration() / f5;
        }
        steeringAcceleration.linear.setZero();
        return steeringAcceleration;
    }

    public Location<T> getTarget() {
        return this.target;
    }

    public ReachOrientation<T> setTarget(Location<T> location) {
        this.target = location;
        return this;
    }

    public float getAlignTolerance() {
        return this.alignTolerance;
    }

    public ReachOrientation<T> setAlignTolerance(float f) {
        this.alignTolerance = f;
        return this;
    }

    public float getDecelerationRadius() {
        return this.decelerationRadius;
    }

    public ReachOrientation<T> setDecelerationRadius(float f) {
        this.decelerationRadius = f;
        return this;
    }

    public float getTimeToTarget() {
        return this.timeToTarget;
    }

    public ReachOrientation<T> setTimeToTarget(float f) {
        this.timeToTarget = f;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public ReachOrientation<T> setOwner(Steerable<T> steerable) {
        this.owner = steerable;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public ReachOrientation<T> setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public ReachOrientation<T> setLimiter(Limiter limiter) {
        this.limiter = limiter;
        return this;
    }
}
