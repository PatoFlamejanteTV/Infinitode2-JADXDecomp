package com.badlogic.gdx.ai.steer.behaviors;

import com.badlogic.gdx.ai.steer.GroupBehavior;
import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Proximity;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/CollisionAvoidance.class */
public class CollisionAvoidance<T extends Vector<T>> extends GroupBehavior<T> implements Proximity.ProximityCallback<T> {
    private float shortestTime;
    private Steerable<T> firstNeighbor;
    private float firstMinSeparation;
    private float firstDistance;
    private T firstRelativePosition;
    private T firstRelativeVelocity;
    private T relativePosition;
    private T relativeVelocity;

    public CollisionAvoidance(Steerable<T> steerable, Proximity<T> proximity) {
        super(steerable, proximity);
        this.firstRelativePosition = newVector(steerable);
        this.firstRelativeVelocity = newVector(steerable);
        this.relativeVelocity = newVector(steerable);
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    protected SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
        this.shortestTime = Float.POSITIVE_INFINITY;
        this.firstNeighbor = null;
        this.firstMinSeparation = 0.0f;
        this.firstDistance = 0.0f;
        this.relativePosition = steeringAcceleration.linear;
        if (this.proximity.findNeighbors(this) == 0 || this.firstNeighbor == null) {
            return steeringAcceleration.setZero();
        }
        if (this.firstMinSeparation <= 0.0f || this.firstDistance < this.owner.getBoundingRadius() + this.firstNeighbor.getBoundingRadius()) {
            this.relativePosition.set(this.firstNeighbor.getPosition()).sub(this.owner.getPosition());
        } else {
            this.relativePosition.set(this.firstRelativePosition).mulAdd(this.firstRelativeVelocity, this.shortestTime);
        }
        this.relativePosition.nor().scl(-getActualLimiter().getMaxLinearAcceleration());
        steeringAcceleration.angular = 0.0f;
        return steeringAcceleration;
    }

    @Override // com.badlogic.gdx.ai.steer.Proximity.ProximityCallback
    public boolean reportNeighbor(Steerable<T> steerable) {
        this.relativePosition.set(steerable.getPosition()).sub(this.owner.getPosition());
        this.relativeVelocity.set(steerable.getLinearVelocity()).sub(this.owner.getLinearVelocity());
        float len2 = this.relativeVelocity.len2();
        if (len2 == 0.0f) {
            return false;
        }
        float f = (-this.relativePosition.dot(this.relativeVelocity)) / len2;
        if (f <= 0.0f || f >= this.shortestTime) {
            return false;
        }
        float len = this.relativePosition.len();
        float sqrt = len - (((float) Math.sqrt(len2)) * f);
        if (sqrt > this.owner.getBoundingRadius() + steerable.getBoundingRadius()) {
            return false;
        }
        this.shortestTime = f;
        this.firstNeighbor = steerable;
        this.firstMinSeparation = sqrt;
        this.firstDistance = len;
        this.firstRelativePosition.set(this.relativePosition);
        this.firstRelativeVelocity.set(this.relativeVelocity);
        return true;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public CollisionAvoidance<T> setOwner(Steerable<T> steerable) {
        this.owner = steerable;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public CollisionAvoidance<T> setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public CollisionAvoidance<T> setLimiter(Limiter limiter) {
        this.limiter = limiter;
        return this;
    }
}
