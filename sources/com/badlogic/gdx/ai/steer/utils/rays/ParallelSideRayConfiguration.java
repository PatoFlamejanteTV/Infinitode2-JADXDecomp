package com.badlogic.gdx.ai.steer.utils.rays;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/utils/rays/ParallelSideRayConfiguration.class */
public class ParallelSideRayConfiguration<T extends Vector<T>> extends RayConfigurationBase<T> {
    private static final float HALF_PI = 1.5707964f;
    private float length;
    private float sideOffset;

    public ParallelSideRayConfiguration(Steerable<T> steerable, float f, float f2) {
        super(steerable, 2);
        this.length = f;
        this.sideOffset = f2;
    }

    @Override // com.badlogic.gdx.ai.steer.utils.RayConfiguration
    public Ray<T>[] updateRays() {
        float vectorToAngle = this.owner.vectorToAngle(this.owner.getLinearVelocity());
        this.owner.angleToVector(this.rays[0].start, vectorToAngle - 1.5707964f).scl(this.sideOffset).add(this.owner.getPosition());
        this.rays[0].end.set(this.owner.getLinearVelocity()).nor().scl(this.length);
        this.owner.angleToVector(this.rays[1].start, vectorToAngle + 1.5707964f).scl(this.sideOffset).add(this.owner.getPosition());
        this.rays[1].end.set(this.rays[0].end).add(this.rays[1].start);
        this.rays[0].end.add(this.rays[0].start);
        return this.rays;
    }

    public float getLength() {
        return this.length;
    }

    public void setLength(float f) {
        this.length = f;
    }

    public float getSideOffset() {
        return this.sideOffset;
    }

    public void setSideOffset(float f) {
        this.sideOffset = f;
    }
}
