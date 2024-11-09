package com.badlogic.gdx.ai.steer.behaviors;

import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/Seek.class */
public class Seek<T extends Vector<T>> extends SteeringBehavior<T> {
    protected Location<T> target;

    public Seek(Steerable<T> steerable) {
        this(steerable, null);
    }

    public Seek(Steerable<T> steerable, Location<T> location) {
        super(steerable);
        this.target = location;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    protected SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
        steeringAcceleration.linear.set(this.target.getPosition()).sub(this.owner.getPosition()).nor().scl(getActualLimiter().getMaxLinearAcceleration());
        steeringAcceleration.angular = 0.0f;
        return steeringAcceleration;
    }

    public Location<T> getTarget() {
        return this.target;
    }

    public Seek<T> setTarget(Location<T> location) {
        this.target = location;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public Seek<T> setOwner(Steerable<T> steerable) {
        this.owner = steerable;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public Seek<T> setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public Seek<T> setLimiter(Limiter limiter) {
        this.limiter = limiter;
        return this;
    }
}
