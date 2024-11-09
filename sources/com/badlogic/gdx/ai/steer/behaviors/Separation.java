package com.badlogic.gdx.ai.steer.behaviors;

import com.badlogic.gdx.ai.steer.GroupBehavior;
import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Proximity;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/Separation.class */
public class Separation<T extends Vector<T>> extends GroupBehavior<T> implements Proximity.ProximityCallback<T> {
    float decayCoefficient;
    private T toAgent;
    private T linear;

    public Separation(Steerable<T> steerable, Proximity<T> proximity) {
        super(steerable, proximity);
        this.decayCoefficient = 1.0f;
        this.toAgent = newVector(steerable);
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    protected SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
        steeringAcceleration.setZero();
        this.linear = steeringAcceleration.linear;
        this.proximity.findNeighbors(this);
        return steeringAcceleration;
    }

    @Override // com.badlogic.gdx.ai.steer.Proximity.ProximityCallback
    public boolean reportNeighbor(Steerable<T> steerable) {
        this.toAgent.set(this.owner.getPosition()).sub(steerable.getPosition());
        float len2 = this.toAgent.len2();
        if (len2 == 0.0f) {
            return true;
        }
        float maxLinearAcceleration = getActualLimiter().getMaxLinearAcceleration();
        float decayCoefficient = getDecayCoefficient() / len2;
        float f = decayCoefficient;
        if (decayCoefficient > maxLinearAcceleration) {
            f = maxLinearAcceleration;
        }
        this.linear.mulAdd(this.toAgent, f / ((float) Math.sqrt(len2)));
        return true;
    }

    public float getDecayCoefficient() {
        return this.decayCoefficient;
    }

    public Separation<T> setDecayCoefficient(float f) {
        this.decayCoefficient = f;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public Separation<T> setOwner(Steerable<T> steerable) {
        this.owner = steerable;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public Separation<T> setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public Separation<T> setLimiter(Limiter limiter) {
        this.limiter = limiter;
        return this;
    }
}
