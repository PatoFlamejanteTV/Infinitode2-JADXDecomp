package com.badlogic.gdx.ai.steer.behaviors;

import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/PrioritySteering.class */
public class PrioritySteering<T extends Vector<T>> extends SteeringBehavior<T> {
    protected float epsilon;
    protected Array<SteeringBehavior<T>> behaviors;
    protected int selectedBehaviorIndex;

    public PrioritySteering(Steerable<T> steerable) {
        this(steerable, 0.001f);
    }

    public PrioritySteering(Steerable<T> steerable, float f) {
        super(steerable);
        this.behaviors = new Array<>();
        this.epsilon = f;
    }

    public PrioritySteering<T> add(SteeringBehavior<T> steeringBehavior) {
        this.behaviors.add(steeringBehavior);
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    protected SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
        float f = this.epsilon * this.epsilon;
        int i = this.behaviors.size;
        this.selectedBehaviorIndex = -1;
        for (int i2 = 0; i2 < i; i2++) {
            this.selectedBehaviorIndex = i2;
            this.behaviors.get(i2).calculateSteering(steeringAcceleration);
            if (steeringAcceleration.calculateSquareMagnitude() > f) {
                return steeringAcceleration;
            }
        }
        return i > 0 ? steeringAcceleration : steeringAcceleration.setZero();
    }

    public int getSelectedBehaviorIndex() {
        return this.selectedBehaviorIndex;
    }

    public float getEpsilon() {
        return this.epsilon;
    }

    public PrioritySteering<T> setEpsilon(float f) {
        this.epsilon = f;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public PrioritySteering<T> setOwner(Steerable<T> steerable) {
        this.owner = steerable;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public PrioritySteering<T> setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public PrioritySteering<T> setLimiter(Limiter limiter) {
        this.limiter = limiter;
        return this;
    }
}
