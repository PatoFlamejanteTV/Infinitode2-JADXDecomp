package com.badlogic.gdx.ai.steer.behaviors;

import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/Wander.class */
public class Wander<T extends Vector<T>> extends Face<T> {
    protected float wanderOffset;
    protected float wanderRadius;
    protected float wanderRate;
    protected float lastTime;
    protected float wanderOrientation;
    protected boolean faceEnabled;
    private T internalTargetPosition;
    private T wanderCenter;

    public Wander(Steerable<T> steerable) {
        super(steerable);
        this.internalTargetPosition = newVector(steerable);
        this.wanderCenter = newVector(steerable);
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Face, com.badlogic.gdx.ai.steer.behaviors.ReachOrientation, com.badlogic.gdx.ai.steer.SteeringBehavior
    protected SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
        float time = GdxAI.getTimepiece().getTime();
        if (this.lastTime > 0.0f) {
            this.wanderOrientation += MathUtils.randomTriangular(this.wanderRate * (time - this.lastTime));
        }
        this.lastTime = time;
        float orientation = this.wanderOrientation + this.owner.getOrientation();
        this.wanderCenter.set(this.owner.getPosition()).mulAdd(this.owner.angleToVector(steeringAcceleration.linear, this.owner.getOrientation()), this.wanderOffset);
        this.internalTargetPosition.set(this.wanderCenter).mulAdd(this.owner.angleToVector(steeringAcceleration.linear, orientation), this.wanderRadius);
        float maxLinearAcceleration = getActualLimiter().getMaxLinearAcceleration();
        if (this.faceEnabled) {
            face(steeringAcceleration, this.internalTargetPosition);
            this.owner.angleToVector(steeringAcceleration.linear, this.owner.getOrientation()).scl(maxLinearAcceleration);
        } else {
            steeringAcceleration.linear.set(this.internalTargetPosition).sub(this.owner.getPosition()).nor().scl(maxLinearAcceleration);
            steeringAcceleration.angular = 0.0f;
        }
        return steeringAcceleration;
    }

    public float getWanderOffset() {
        return this.wanderOffset;
    }

    public Wander<T> setWanderOffset(float f) {
        this.wanderOffset = f;
        return this;
    }

    public float getWanderRadius() {
        return this.wanderRadius;
    }

    public Wander<T> setWanderRadius(float f) {
        this.wanderRadius = f;
        return this;
    }

    public float getWanderRate() {
        return this.wanderRate;
    }

    public Wander<T> setWanderRate(float f) {
        this.wanderRate = f;
        return this;
    }

    public float getWanderOrientation() {
        return this.wanderOrientation;
    }

    public Wander<T> setWanderOrientation(float f) {
        this.wanderOrientation = f;
        return this;
    }

    public boolean isFaceEnabled() {
        return this.faceEnabled;
    }

    public Wander<T> setFaceEnabled(boolean z) {
        this.faceEnabled = z;
        return this;
    }

    public T getInternalTargetPosition() {
        return this.internalTargetPosition;
    }

    public T getWanderCenter() {
        return this.wanderCenter;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Face, com.badlogic.gdx.ai.steer.behaviors.ReachOrientation, com.badlogic.gdx.ai.steer.SteeringBehavior
    public Wander<T> setOwner(Steerable<T> steerable) {
        this.owner = steerable;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Face, com.badlogic.gdx.ai.steer.behaviors.ReachOrientation, com.badlogic.gdx.ai.steer.SteeringBehavior
    public Wander<T> setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Face, com.badlogic.gdx.ai.steer.behaviors.ReachOrientation, com.badlogic.gdx.ai.steer.SteeringBehavior
    public Wander<T> setLimiter(Limiter limiter) {
        this.limiter = limiter;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Face, com.badlogic.gdx.ai.steer.behaviors.ReachOrientation
    public Wander<T> setTarget(Location<T> location) {
        this.target = location;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Face, com.badlogic.gdx.ai.steer.behaviors.ReachOrientation
    public Wander<T> setAlignTolerance(float f) {
        this.alignTolerance = f;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Face, com.badlogic.gdx.ai.steer.behaviors.ReachOrientation
    public Wander<T> setDecelerationRadius(float f) {
        this.decelerationRadius = f;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Face, com.badlogic.gdx.ai.steer.behaviors.ReachOrientation
    public Wander<T> setTimeToTarget(float f) {
        this.timeToTarget = f;
        return this;
    }
}
