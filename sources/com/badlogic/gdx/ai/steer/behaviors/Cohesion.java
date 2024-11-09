package com.badlogic.gdx.ai.steer.behaviors;

import com.badlogic.gdx.ai.steer.GroupBehavior;
import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Proximity;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/Cohesion.class */
public class Cohesion<T extends Vector<T>> extends GroupBehavior<T> implements Proximity.ProximityCallback<T> {
    private T centerOfMass;

    public Cohesion(Steerable<T> steerable, Proximity<T> proximity) {
        super(steerable, proximity);
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    protected SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
        steeringAcceleration.setZero();
        this.centerOfMass = steeringAcceleration.linear;
        int findNeighbors = this.proximity.findNeighbors(this);
        if (findNeighbors > 0) {
            this.centerOfMass.scl(1.0f / findNeighbors);
            this.centerOfMass.sub(this.owner.getPosition()).nor().scl(getActualLimiter().getMaxLinearAcceleration());
        }
        return steeringAcceleration;
    }

    @Override // com.badlogic.gdx.ai.steer.Proximity.ProximityCallback
    public boolean reportNeighbor(Steerable<T> steerable) {
        this.centerOfMass.add(steerable.getPosition());
        return true;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public Cohesion<T> setOwner(Steerable<T> steerable) {
        this.owner = steerable;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public Cohesion<T> setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public Cohesion<T> setLimiter(Limiter limiter) {
        this.limiter = limiter;
        return this;
    }
}
