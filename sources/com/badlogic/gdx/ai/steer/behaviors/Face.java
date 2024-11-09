package com.badlogic.gdx.ai.steer.behaviors;

import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/Face.class */
public class Face<T extends Vector<T>> extends ReachOrientation<T> {
    public Face(Steerable<T> steerable) {
        this(steerable, null);
    }

    public Face(Steerable<T> steerable, Location<T> location) {
        super(steerable, location);
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.ReachOrientation, com.badlogic.gdx.ai.steer.SteeringBehavior
    protected SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
        return face(steeringAcceleration, this.target.getPosition());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public SteeringAcceleration<T> face(SteeringAcceleration<T> steeringAcceleration, T t) {
        Vector sub = steeringAcceleration.linear.set(t).sub(this.owner.getPosition());
        return sub.isZero(getActualLimiter().getZeroLinearSpeedThreshold()) ? steeringAcceleration.setZero() : reachOrientation(steeringAcceleration, this.owner.vectorToAngle(sub));
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.ReachOrientation, com.badlogic.gdx.ai.steer.SteeringBehavior
    public Face<T> setOwner(Steerable<T> steerable) {
        this.owner = steerable;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.ReachOrientation, com.badlogic.gdx.ai.steer.SteeringBehavior
    public Face<T> setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.ReachOrientation, com.badlogic.gdx.ai.steer.SteeringBehavior
    public Face<T> setLimiter(Limiter limiter) {
        this.limiter = limiter;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.ReachOrientation
    public Face<T> setTarget(Location<T> location) {
        this.target = location;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.ReachOrientation
    public Face<T> setAlignTolerance(float f) {
        this.alignTolerance = f;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.ReachOrientation
    public Face<T> setDecelerationRadius(float f) {
        this.decelerationRadius = f;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.ReachOrientation
    public Face<T> setTimeToTarget(float f) {
        this.timeToTarget = f;
        return this;
    }
}
