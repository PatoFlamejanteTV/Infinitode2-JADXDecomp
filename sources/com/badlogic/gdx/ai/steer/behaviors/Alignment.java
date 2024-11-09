package com.badlogic.gdx.ai.steer.behaviors;

import com.badlogic.gdx.ai.steer.GroupBehavior;
import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Proximity;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/Alignment.class */
public class Alignment<T extends Vector<T>> extends GroupBehavior<T> implements Proximity.ProximityCallback<T> {
    private T averageVelocity;

    public Alignment(Steerable<T> steerable, Proximity<T> proximity) {
        super(steerable, proximity);
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    protected SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
        steeringAcceleration.setZero();
        this.averageVelocity = steeringAcceleration.linear;
        int findNeighbors = this.proximity.findNeighbors(this);
        if (findNeighbors > 0) {
            this.averageVelocity.scl(1.0f / findNeighbors);
            this.averageVelocity.sub(this.owner.getLinearVelocity()).limit(getActualLimiter().getMaxLinearAcceleration());
        }
        return steeringAcceleration;
    }

    @Override // com.badlogic.gdx.ai.steer.Proximity.ProximityCallback
    public boolean reportNeighbor(Steerable<T> steerable) {
        this.averageVelocity.add(steerable.getLinearVelocity());
        return true;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public Alignment<T> setOwner(Steerable<T> steerable) {
        this.owner = steerable;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public Alignment<T> setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public Alignment<T> setLimiter(Limiter limiter) {
        this.limiter = limiter;
        return this;
    }
}
