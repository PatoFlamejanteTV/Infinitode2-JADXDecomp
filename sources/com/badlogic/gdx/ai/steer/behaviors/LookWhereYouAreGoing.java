package com.badlogic.gdx.ai.steer.behaviors;

import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/LookWhereYouAreGoing.class */
public class LookWhereYouAreGoing<T extends Vector<T>> extends ReachOrientation<T> {
    public LookWhereYouAreGoing(Steerable<T> steerable) {
        super(steerable);
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.ReachOrientation, com.badlogic.gdx.ai.steer.SteeringBehavior
    protected SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
        return this.owner.getLinearVelocity().isZero(getActualLimiter().getZeroLinearSpeedThreshold()) ? steeringAcceleration.setZero() : reachOrientation(steeringAcceleration, this.owner.vectorToAngle(this.owner.getLinearVelocity()));
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.ReachOrientation, com.badlogic.gdx.ai.steer.SteeringBehavior
    public LookWhereYouAreGoing<T> setOwner(Steerable<T> steerable) {
        this.owner = steerable;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.ReachOrientation, com.badlogic.gdx.ai.steer.SteeringBehavior
    public LookWhereYouAreGoing<T> setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.ReachOrientation, com.badlogic.gdx.ai.steer.SteeringBehavior
    public LookWhereYouAreGoing<T> setLimiter(Limiter limiter) {
        this.limiter = limiter;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.ReachOrientation
    public LookWhereYouAreGoing<T> setTarget(Location<T> location) {
        this.target = location;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.ReachOrientation
    public LookWhereYouAreGoing<T> setAlignTolerance(float f) {
        this.alignTolerance = f;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.ReachOrientation
    public LookWhereYouAreGoing<T> setDecelerationRadius(float f) {
        this.decelerationRadius = f;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.ReachOrientation
    public LookWhereYouAreGoing<T> setTimeToTarget(float f) {
        this.timeToTarget = f;
        return this;
    }
}
