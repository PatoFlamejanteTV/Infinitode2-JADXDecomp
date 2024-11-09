package com.badlogic.gdx.ai.steer.behaviors;

import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/BlendedSteering.class */
public class BlendedSteering<T extends Vector<T>> extends SteeringBehavior<T> {
    protected Array<BehaviorAndWeight<T>> list;
    private SteeringAcceleration<T> steering;

    public BlendedSteering(Steerable<T> steerable) {
        super(steerable);
        this.list = new Array<>();
        this.steering = new SteeringAcceleration<>(newVector(steerable));
    }

    public BlendedSteering<T> add(SteeringBehavior<T> steeringBehavior, float f) {
        return add(new BehaviorAndWeight<>(steeringBehavior, f));
    }

    public BlendedSteering<T> add(BehaviorAndWeight<T> behaviorAndWeight) {
        behaviorAndWeight.behavior.setOwner(this.owner);
        this.list.add(behaviorAndWeight);
        return this;
    }

    public void remove(BehaviorAndWeight<T> behaviorAndWeight) {
        this.list.removeValue(behaviorAndWeight, true);
    }

    public void remove(SteeringBehavior<T> steeringBehavior) {
        for (int i = 0; i < this.list.size; i++) {
            if (this.list.get(i).behavior == steeringBehavior) {
                this.list.removeIndex(i);
                return;
            }
        }
    }

    public BehaviorAndWeight<T> get(int i) {
        return this.list.get(i);
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    protected SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
        steeringAcceleration.setZero();
        int i = this.list.size;
        for (int i2 = 0; i2 < i; i2++) {
            BehaviorAndWeight<T> behaviorAndWeight = this.list.get(i2);
            behaviorAndWeight.behavior.calculateSteering(this.steering);
            steeringAcceleration.mulAdd(this.steering, behaviorAndWeight.weight);
        }
        Limiter actualLimiter = getActualLimiter();
        steeringAcceleration.linear.limit(actualLimiter.getMaxLinearAcceleration());
        if (steeringAcceleration.angular > actualLimiter.getMaxAngularAcceleration()) {
            steeringAcceleration.angular = actualLimiter.getMaxAngularAcceleration();
        }
        return steeringAcceleration;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public BlendedSteering<T> setOwner(Steerable<T> steerable) {
        this.owner = steerable;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public BlendedSteering<T> setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public BlendedSteering<T> setLimiter(Limiter limiter) {
        this.limiter = limiter;
        return this;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/BlendedSteering$BehaviorAndWeight.class */
    public static class BehaviorAndWeight<T extends Vector<T>> {
        protected SteeringBehavior<T> behavior;
        protected float weight;

        public BehaviorAndWeight(SteeringBehavior<T> steeringBehavior, float f) {
            this.behavior = steeringBehavior;
            this.weight = f;
        }

        public SteeringBehavior<T> getBehavior() {
            return this.behavior;
        }

        public void setBehavior(SteeringBehavior<T> steeringBehavior) {
            this.behavior = steeringBehavior;
        }

        public float getWeight() {
            return this.weight;
        }

        public void setWeight(float f) {
            this.weight = f;
        }
    }
}
