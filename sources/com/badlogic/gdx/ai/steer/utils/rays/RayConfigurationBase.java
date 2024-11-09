package com.badlogic.gdx.ai.steer.utils.rays;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.utils.RayConfiguration;
import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/utils/rays/RayConfigurationBase.class */
public abstract class RayConfigurationBase<T extends Vector<T>> implements RayConfiguration<T> {
    protected Steerable<T> owner;
    protected Ray<T>[] rays;

    public RayConfigurationBase(Steerable<T> steerable, int i) {
        this.owner = steerable;
        this.rays = new Ray[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.rays[i2] = new Ray<>(steerable.getPosition().cpy().setZero(), steerable.getPosition().cpy().setZero());
        }
    }

    public Steerable<T> getOwner() {
        return this.owner;
    }

    public void setOwner(Steerable<T> steerable) {
        this.owner = steerable;
    }

    public Ray<T>[] getRays() {
        return this.rays;
    }

    public void setRays(Ray<T>[] rayArr) {
        this.rays = rayArr;
    }
}
