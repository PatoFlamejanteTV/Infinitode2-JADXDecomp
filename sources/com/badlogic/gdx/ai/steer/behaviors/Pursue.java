package com.badlogic.gdx.ai.steer.behaviors;

import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/Pursue.class */
public class Pursue<T extends Vector<T>> extends SteeringBehavior<T> {
    protected Steerable<T> target;
    protected float maxPredictionTime;

    public Pursue(Steerable<T> steerable, Steerable<T> steerable2) {
        this(steerable, steerable2, 1.0f);
    }

    public Pursue(Steerable<T> steerable, Steerable<T> steerable2, float f) {
        super(steerable);
        this.target = steerable2;
        this.maxPredictionTime = f;
    }

    protected float getActualMaxLinearAcceleration() {
        return getActualLimiter().getMaxLinearAcceleration();
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    protected SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
        T position = this.target.getPosition();
        float len2 = steeringAcceleration.linear.set(position).sub(this.owner.getPosition()).len2();
        float len22 = this.owner.getLinearVelocity().len2();
        float f = this.maxPredictionTime;
        if (len22 > 0.0f) {
            float f2 = len2 / len22;
            if (f2 < this.maxPredictionTime * this.maxPredictionTime) {
                f = (float) Math.sqrt(f2);
            }
        }
        steeringAcceleration.linear.set(position).mulAdd(this.target.getLinearVelocity(), f).sub(this.owner.getPosition()).nor().scl(getActualMaxLinearAcceleration());
        steeringAcceleration.angular = 0.0f;
        return steeringAcceleration;
    }

    public Steerable<T> getTarget() {
        return this.target;
    }

    public Pursue<T> setTarget(Steerable<T> steerable) {
        this.target = steerable;
        return this;
    }

    public float getMaxPredictionTime() {
        return this.maxPredictionTime;
    }

    public Pursue<T> setMaxPredictionTime(float f) {
        this.maxPredictionTime = f;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public Pursue<T> setOwner(Steerable<T> steerable) {
        this.owner = steerable;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public Pursue<T> setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public Pursue<T> setLimiter(Limiter limiter) {
        this.limiter = limiter;
        return this;
    }
}
