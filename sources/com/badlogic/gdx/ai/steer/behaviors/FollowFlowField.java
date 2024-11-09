package com.badlogic.gdx.ai.steer.behaviors;

import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/FollowFlowField.class */
public class FollowFlowField<T extends Vector<T>> extends SteeringBehavior<T> {
    protected FlowField<T> flowField;
    protected float predictionTime;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/FollowFlowField$FlowField.class */
    public interface FlowField<T extends Vector<T>> {
        T lookup(T t);
    }

    public FollowFlowField(Steerable<T> steerable) {
        this(steerable, null);
    }

    public FollowFlowField(Steerable<T> steerable, FlowField<T> flowField) {
        this(steerable, flowField, 0.0f);
    }

    public FollowFlowField(Steerable<T> steerable, FlowField<T> flowField, float f) {
        super(steerable);
        this.flowField = flowField;
        this.predictionTime = f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    protected SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
        Vector mulAdd;
        if (this.predictionTime == 0.0f) {
            mulAdd = this.owner.getPosition();
        } else {
            mulAdd = steeringAcceleration.linear.set(this.owner.getPosition()).mulAdd(this.owner.getLinearVelocity(), this.predictionTime);
        }
        T lookup = this.flowField.lookup(mulAdd);
        steeringAcceleration.setZero();
        if (lookup != null && !lookup.isZero()) {
            Limiter actualLimiter = getActualLimiter();
            steeringAcceleration.linear.mulAdd(lookup, actualLimiter.getMaxLinearSpeed()).sub(this.owner.getLinearVelocity()).limit(actualLimiter.getMaxLinearAcceleration());
        }
        return steeringAcceleration;
    }

    public FlowField<T> getFlowField() {
        return this.flowField;
    }

    public FollowFlowField<T> setFlowField(FlowField<T> flowField) {
        this.flowField = flowField;
        return this;
    }

    public float getPredictionTime() {
        return this.predictionTime;
    }

    public FollowFlowField<T> setPredictionTime(float f) {
        this.predictionTime = f;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public FollowFlowField<T> setOwner(Steerable<T> steerable) {
        this.owner = steerable;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public FollowFlowField<T> setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public FollowFlowField<T> setLimiter(Limiter limiter) {
        this.limiter = limiter;
        return this;
    }
}
