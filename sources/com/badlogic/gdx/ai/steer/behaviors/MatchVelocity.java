package com.badlogic.gdx.ai.steer.behaviors;

import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/MatchVelocity.class */
public class MatchVelocity<T extends Vector<T>> extends SteeringBehavior<T> {
    protected Steerable<T> target;
    protected float timeToTarget;

    public MatchVelocity(Steerable<T> steerable) {
        this(steerable, null);
    }

    public MatchVelocity(Steerable<T> steerable, Steerable<T> steerable2) {
        this(steerable, steerable2, 0.1f);
    }

    public MatchVelocity(Steerable<T> steerable, Steerable<T> steerable2, float f) {
        super(steerable);
        this.target = steerable2;
        this.timeToTarget = f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
        steeringAcceleration.linear.set(this.target.getLinearVelocity()).sub(this.owner.getLinearVelocity()).scl(1.0f / this.timeToTarget).limit(getActualLimiter().getMaxLinearAcceleration());
        steeringAcceleration.angular = 0.0f;
        return steeringAcceleration;
    }

    public Steerable<T> getTarget() {
        return this.target;
    }

    public MatchVelocity<T> setTarget(Steerable<T> steerable) {
        this.target = steerable;
        return this;
    }

    public float getTimeToTarget() {
        return this.timeToTarget;
    }

    public MatchVelocity<T> setTimeToTarget(float f) {
        this.timeToTarget = f;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public MatchVelocity<T> setOwner(Steerable<T> steerable) {
        this.owner = steerable;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public MatchVelocity<T> setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public MatchVelocity<T> setLimiter(Limiter limiter) {
        this.limiter = limiter;
        return this;
    }
}
