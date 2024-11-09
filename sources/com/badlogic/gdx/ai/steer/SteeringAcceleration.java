package com.badlogic.gdx.ai.steer;

import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/SteeringAcceleration.class */
public class SteeringAcceleration<T extends Vector<T>> {
    public T linear;
    public float angular;

    public SteeringAcceleration(T t) {
        this(t, 0.0f);
    }

    public SteeringAcceleration(T t, float f) {
        if (t == null) {
            throw new IllegalArgumentException("Linear acceleration cannot be null");
        }
        this.linear = t;
        this.angular = f;
    }

    public boolean isZero() {
        return this.angular == 0.0f && this.linear.isZero();
    }

    public SteeringAcceleration<T> setZero() {
        this.linear.setZero();
        this.angular = 0.0f;
        return this;
    }

    public SteeringAcceleration<T> add(SteeringAcceleration<T> steeringAcceleration) {
        this.linear.add(steeringAcceleration.linear);
        this.angular += steeringAcceleration.angular;
        return this;
    }

    public SteeringAcceleration<T> scl(float f) {
        this.linear.scl(f);
        this.angular *= f;
        return this;
    }

    public SteeringAcceleration<T> mulAdd(SteeringAcceleration<T> steeringAcceleration, float f) {
        this.linear.mulAdd(steeringAcceleration.linear, f);
        this.angular += steeringAcceleration.angular * f;
        return this;
    }

    public float calculateSquareMagnitude() {
        return this.linear.len2() + (this.angular * this.angular);
    }

    public float calculateMagnitude() {
        return (float) Math.sqrt(calculateSquareMagnitude());
    }
}
