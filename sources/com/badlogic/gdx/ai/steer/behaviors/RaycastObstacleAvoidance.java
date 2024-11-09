package com.badlogic.gdx.ai.steer.behaviors;

import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.steer.utils.RayConfiguration;
import com.badlogic.gdx.ai.utils.Collision;
import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.ai.utils.RaycastCollisionDetector;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/behaviors/RaycastObstacleAvoidance.class */
public class RaycastObstacleAvoidance<T extends Vector<T>> extends SteeringBehavior<T> {
    protected RayConfiguration<T> rayConfiguration;
    protected RaycastCollisionDetector<T> raycastCollisionDetector;
    protected float distanceFromBoundary;
    private Collision<T> outputCollision;
    private Collision<T> minOutputCollision;

    public RaycastObstacleAvoidance(Steerable<T> steerable) {
        this(steerable, null);
    }

    public RaycastObstacleAvoidance(Steerable<T> steerable, RayConfiguration<T> rayConfiguration) {
        this(steerable, rayConfiguration, null);
    }

    public RaycastObstacleAvoidance(Steerable<T> steerable, RayConfiguration<T> rayConfiguration, RaycastCollisionDetector<T> raycastCollisionDetector) {
        this(steerable, rayConfiguration, raycastCollisionDetector, 0.0f);
    }

    public RaycastObstacleAvoidance(Steerable<T> steerable, RayConfiguration<T> rayConfiguration, RaycastCollisionDetector<T> raycastCollisionDetector, float f) {
        super(steerable);
        this.rayConfiguration = rayConfiguration;
        this.raycastCollisionDetector = raycastCollisionDetector;
        this.distanceFromBoundary = f;
        this.outputCollision = new Collision<>(newVector(steerable), newVector(steerable));
        this.minOutputCollision = new Collision<>(newVector(steerable), newVector(steerable));
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    protected SteeringAcceleration<T> calculateRealSteering(SteeringAcceleration<T> steeringAcceleration) {
        T position = this.owner.getPosition();
        float f = Float.POSITIVE_INFINITY;
        for (Ray<T> ray : this.rayConfiguration.updateRays()) {
            if (this.raycastCollisionDetector.findCollision(this.outputCollision, ray)) {
                float dst2 = position.dst2(this.outputCollision.point);
                if (dst2 < f) {
                    f = dst2;
                    Collision<T> collision = this.outputCollision;
                    this.outputCollision = this.minOutputCollision;
                    this.minOutputCollision = collision;
                }
            }
        }
        if (f == Float.POSITIVE_INFINITY) {
            return steeringAcceleration.setZero();
        }
        steeringAcceleration.linear.set(this.minOutputCollision.point).mulAdd(this.minOutputCollision.normal, this.owner.getBoundingRadius() + this.distanceFromBoundary).sub(this.owner.getPosition()).nor().scl(getActualLimiter().getMaxLinearAcceleration());
        steeringAcceleration.angular = 0.0f;
        return steeringAcceleration;
    }

    public RayConfiguration<T> getRayConfiguration() {
        return this.rayConfiguration;
    }

    public RaycastObstacleAvoidance<T> setRayConfiguration(RayConfiguration<T> rayConfiguration) {
        this.rayConfiguration = rayConfiguration;
        return this;
    }

    public RaycastCollisionDetector<T> getRaycastCollisionDetector() {
        return this.raycastCollisionDetector;
    }

    public RaycastObstacleAvoidance<T> setRaycastCollisionDetector(RaycastCollisionDetector<T> raycastCollisionDetector) {
        this.raycastCollisionDetector = raycastCollisionDetector;
        return this;
    }

    public float getDistanceFromBoundary() {
        return this.distanceFromBoundary;
    }

    public RaycastObstacleAvoidance<T> setDistanceFromBoundary(float f) {
        this.distanceFromBoundary = f;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public RaycastObstacleAvoidance<T> setOwner(Steerable<T> steerable) {
        this.owner = steerable;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public RaycastObstacleAvoidance<T> setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Override // com.badlogic.gdx.ai.steer.SteeringBehavior
    public RaycastObstacleAvoidance<T> setLimiter(Limiter limiter) {
        this.limiter = limiter;
        return this;
    }
}
