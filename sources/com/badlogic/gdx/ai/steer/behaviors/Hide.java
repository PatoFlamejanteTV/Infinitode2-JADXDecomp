package com.badlogic.gdx.ai.steer.behaviors;

import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Proximity;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/Hide.class */
public class Hide<T extends Vector<T>> extends Arrive<T> implements Proximity.ProximityCallback<T> {
    protected Proximity<T> proximity;
    protected float distanceFromBoundary;
    private T toObstacle;
    private T bestHidingSpot;
    private float distance2ToClosest;

    public Hide(Steerable<T> steerable) {
        this(steerable, null);
    }

    public Hide(Steerable<T> steerable, Location<T> location) {
        this(steerable, location, null);
    }

    public Hide(Steerable<T> steerable, Location<T> location, Proximity<T> proximity) {
        super(steerable, location);
        this.proximity = proximity;
        this.bestHidingSpot = newVector(steerable);
        this.toObstacle = null;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Arrive, com.badlogic.gdx.ai.steer.SteeringBehavior
    protected SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
        this.distance2ToClosest = Float.POSITIVE_INFINITY;
        this.toObstacle = steeringAcceleration.linear;
        return this.proximity.findNeighbors(this) == 0 ? steeringAcceleration.setZero() : arrive(steeringAcceleration, this.bestHidingSpot);
    }

    @Override // com.badlogic.gdx.ai.steer.Proximity.ProximityCallback
    public boolean reportNeighbor(Steerable<T> steerable) {
        T hidingPosition = getHidingPosition(steerable.getPosition(), steerable.getBoundingRadius(), this.target.getPosition());
        float dst2 = hidingPosition.dst2(this.owner.getPosition());
        if (dst2 < this.distance2ToClosest) {
            this.distance2ToClosest = dst2;
            this.bestHidingSpot.set(hidingPosition);
            return true;
        }
        return false;
    }

    public Proximity<T> getProximity() {
        return this.proximity;
    }

    public Hide<T> setProximity(Proximity<T> proximity) {
        this.proximity = proximity;
        return this;
    }

    public float getDistanceFromBoundary() {
        return this.distanceFromBoundary;
    }

    public Hide<T> setDistanceFromBoundary(float f) {
        this.distanceFromBoundary = f;
        return this;
    }

    protected T getHidingPosition(T t, float f, T t2) {
        float f2 = f + this.distanceFromBoundary;
        this.toObstacle.set(t).sub(t2).nor();
        return (T) this.toObstacle.scl(f2).add(t);
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Arrive, com.badlogic.gdx.ai.steer.SteeringBehavior
    public Hide<T> setOwner(Steerable<T> steerable) {
        this.owner = steerable;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Arrive, com.badlogic.gdx.ai.steer.SteeringBehavior
    public Hide<T> setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Arrive, com.badlogic.gdx.ai.steer.SteeringBehavior
    public Hide<T> setLimiter(Limiter limiter) {
        this.limiter = limiter;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Arrive
    public Hide<T> setTarget(Location<T> location) {
        this.target = location;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Arrive
    public Hide<T> setArrivalTolerance(float f) {
        this.arrivalTolerance = f;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Arrive
    public Hide<T> setDecelerationRadius(float f) {
        this.decelerationRadius = f;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Arrive
    public Hide<T> setTimeToTarget(float f) {
        this.timeToTarget = f;
        return this;
    }
}
