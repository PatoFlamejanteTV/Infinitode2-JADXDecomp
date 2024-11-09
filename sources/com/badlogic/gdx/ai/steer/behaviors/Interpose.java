package com.badlogic.gdx.ai.steer.behaviors;

import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/Interpose.class */
public class Interpose<T extends Vector<T>> extends Arrive<T> {
    protected Steerable<T> agentA;
    protected Steerable<T> agentB;
    protected float interpositionRatio;
    private T internalTargetPosition;

    public Interpose(Steerable<T> steerable, Steerable<T> steerable2, Steerable<T> steerable3) {
        this(steerable, steerable2, steerable3, 0.5f);
    }

    public Interpose(Steerable<T> steerable, Steerable<T> steerable2, Steerable<T> steerable3, float f) {
        super(steerable);
        this.agentA = steerable2;
        this.agentB = steerable3;
        this.interpositionRatio = f;
        this.internalTargetPosition = newVector(steerable);
    }

    public Steerable<T> getAgentA() {
        return this.agentA;
    }

    public Interpose<T> setAgentA(Steerable<T> steerable) {
        this.agentA = steerable;
        return this;
    }

    public Steerable<T> getAgentB() {
        return this.agentB;
    }

    public Interpose<T> setAgentB(Steerable<T> steerable) {
        this.agentB = steerable;
        return this;
    }

    public float getInterpositionRatio() {
        return this.interpositionRatio;
    }

    public Interpose<T> setInterpositionRatio(float f) {
        this.interpositionRatio = f;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Arrive, com.badlogic.gdx.ai.steer.SteeringBehavior
    protected SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
        this.internalTargetPosition.set(this.agentB.getPosition()).sub(this.agentA.getPosition()).scl(this.interpositionRatio).add(this.agentA.getPosition());
        float dst = this.owner.getPosition().dst(this.internalTargetPosition) / getActualLimiter().getMaxLinearSpeed();
        steeringAcceleration.linear.set(this.agentA.getPosition()).mulAdd(this.agentA.getLinearVelocity(), dst);
        this.internalTargetPosition.set(this.agentB.getPosition()).mulAdd(this.agentB.getLinearVelocity(), dst);
        this.internalTargetPosition.sub(steeringAcceleration.linear).scl(this.interpositionRatio).add(steeringAcceleration.linear);
        return arrive(steeringAcceleration, this.internalTargetPosition);
    }

    public T getInternalTargetPosition() {
        return this.internalTargetPosition;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Arrive, com.badlogic.gdx.ai.steer.SteeringBehavior
    public Interpose<T> setOwner(Steerable<T> steerable) {
        this.owner = steerable;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Arrive, com.badlogic.gdx.ai.steer.SteeringBehavior
    public Interpose<T> setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Arrive, com.badlogic.gdx.ai.steer.SteeringBehavior
    public Interpose<T> setLimiter(Limiter limiter) {
        this.limiter = limiter;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Arrive
    public Interpose<T> setTarget(Location<T> location) {
        this.target = location;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Arrive
    public Interpose<T> setArrivalTolerance(float f) {
        this.arrivalTolerance = f;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Arrive
    public Interpose<T> setDecelerationRadius(float f) {
        this.decelerationRadius = f;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Arrive
    public Interpose<T> setTimeToTarget(float f) {
        this.timeToTarget = f;
        return this;
    }
}
