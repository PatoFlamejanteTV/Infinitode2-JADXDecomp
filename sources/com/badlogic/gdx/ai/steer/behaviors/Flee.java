package com.badlogic.gdx.ai.steer.behaviors;

import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/Flee.class */
public class Flee<T extends Vector<T>> extends Seek<T> {
    public Flee(Steerable<T> steerable) {
        this(steerable, null);
    }

    public Flee(Steerable<T> steerable, Location<T> location) {
        super(steerable, location);
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Seek, com.badlogic.gdx.ai.steer.SteeringBehavior
    protected SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
        steeringAcceleration.linear.set(this.owner.getPosition()).sub(this.target.getPosition()).nor().scl(getActualLimiter().getMaxLinearAcceleration());
        steeringAcceleration.angular = 0.0f;
        return steeringAcceleration;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Seek, com.badlogic.gdx.ai.steer.SteeringBehavior
    public Flee<T> setOwner(Steerable<T> steerable) {
        this.owner = steerable;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Seek, com.badlogic.gdx.ai.steer.SteeringBehavior
    public Flee<T> setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Seek, com.badlogic.gdx.ai.steer.SteeringBehavior
    public Flee<T> setLimiter(Limiter limiter) {
        this.limiter = limiter;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.Seek
    public Flee<T> setTarget(Location<T> location) {
        this.target = location;
        return this;
    }
}
