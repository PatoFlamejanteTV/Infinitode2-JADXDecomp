package com.badlogic.gdx.ai.steer.utils.rays;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/utils/rays/SingleRayConfiguration.class */
public class SingleRayConfiguration<T extends Vector<T>> extends RayConfigurationBase<T> {
    private float length;

    public SingleRayConfiguration(Steerable<T> steerable, float f) {
        super(steerable, 1);
        this.length = f;
    }

    @Override // com.badlogic.gdx.ai.steer.utils.RayConfiguration
    public Ray<T>[] updateRays() {
        this.rays[0].start.set(this.owner.getPosition());
        this.rays[0].end.set(this.owner.getLinearVelocity()).nor().scl(this.length).add(this.rays[0].start);
        return this.rays;
    }

    public float getLength() {
        return this.length;
    }

    public void setLength(float f) {
        this.length = f;
    }
}
