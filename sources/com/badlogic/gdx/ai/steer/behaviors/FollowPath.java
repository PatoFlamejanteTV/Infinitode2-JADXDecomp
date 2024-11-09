package com.badlogic.gdx.ai.steer.behaviors;

import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.utils.Path;
import com.badlogic.gdx.ai.steer.utils.Path.PathParam;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/FollowPath.class */
public class FollowPath<T extends Vector<T>, P extends Path.PathParam> extends Arrive<T> {
    protected Path<T, P> path;
    protected float pathOffset;
    protected P pathParam;
    protected boolean arriveEnabled;
    protected float predictionTime;
    private T internalTargetPosition;

    public FollowPath(Steerable<T> steerable, Path<T, P> path) {
        this(steerable, path, 0.0f);
    }

    public FollowPath(Steerable<T> steerable, Path<T, P> path, float f) {
        this(steerable, path, f, 0.0f);
    }

    public FollowPath(Steerable<T> steerable, Path<T, P> path, float f, float f2) {
        super(steerable);
        this.path = path;
        this.pathParam = path.createParam();
        this.pathOffset = f;
        this.predictionTime = f2;
        this.arriveEnabled = true;
        this.internalTargetPosition = newVector(steerable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.badlogic.gdx.ai.steer.behaviors.Arrive, com.badlogic.gdx.ai.steer.SteeringBehavior
    protected SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
        Vector mulAdd;
        if (this.predictionTime == 0.0f) {
            mulAdd = this.owner.getPosition();
        } else {
            mulAdd = steeringAcceleration.linear.set(this.owner.getPosition()).mulAdd(this.owner.getLinearVelocity(), this.predictionTime);
        }
        float calculateDistance = this.path.calculateDistance(mulAdd, this.pathParam) + this.pathOffset;
        this.path.calculateTargetPosition(this.internalTargetPosition, this.pathParam, calculateDistance);
        if (this.arriveEnabled && this.path.isOpen()) {
            if (this.pathOffset >= 0.0f) {
                if (calculateDistance > this.path.getLength() - this.decelerationRadius) {
                    return arrive(steeringAcceleration, this.internalTargetPosition);
                }
            } else if (calculateDistance < this.decelerationRadius) {
                return arrive(steeringAcceleration, this.internalTargetPosition);
            }
        }
        steeringAcceleration.linear.set(this.internalTargetPosition).sub(this.owner.getPosition()).nor().scl(getActualLimiter().getMaxLinearAcceleration());
        steeringAcceleration.angular = 0.0f;
        return steeringAcceleration;
    }

    public Path<T, P> getPath() {
        return this.path;
    }

    public FollowPath<T, P> setPath(Path<T, P> path) {
        this.path = path;
        return this;
    }

    public float getPathOffset() {
        return this.pathOffset;
    }

    public boolean isArriveEnabled() {
        return this.arriveEnabled;
    }

    public float getPredictionTime() {
        return this.predictionTime;
    }

    public FollowPath<T, P> setPredictionTime(float f) {
        this.predictionTime = f;
        return this;
    }

    public FollowPath<T, P> setArriveEnabled(boolean z) {
        this.arriveEnabled = z;
        return this;
    }

    public FollowPath<T, P> setPathOffset(float f) {
        this.pathOffset = f;
        return this;
    }

    public P getPathParam() {
        return this.pathParam;
    }

    public T getInternalTargetPosition() {
        return this.internalTargetPosition;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Arrive, com.badlogic.gdx.ai.steer.SteeringBehavior
    public FollowPath<T, P> setOwner(Steerable<T> steerable) {
        this.owner = steerable;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Arrive, com.badlogic.gdx.ai.steer.SteeringBehavior
    public FollowPath<T, P> setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Arrive, com.badlogic.gdx.ai.steer.SteeringBehavior
    public FollowPath<T, P> setLimiter(Limiter limiter) {
        this.limiter = limiter;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Arrive
    public FollowPath<T, P> setTarget(Location<T> location) {
        this.target = location;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Arrive
    public FollowPath<T, P> setArrivalTolerance(float f) {
        this.arrivalTolerance = f;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Arrive
    public FollowPath<T, P> setDecelerationRadius(float f) {
        this.decelerationRadius = f;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Arrive
    public FollowPath<T, P> setTimeToTarget(float f) {
        this.timeToTarget = f;
        return this;
    }
}
