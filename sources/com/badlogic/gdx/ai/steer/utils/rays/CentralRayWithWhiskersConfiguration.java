package com.badlogic.gdx.ai.steer.utils.rays;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/utils/rays/CentralRayWithWhiskersConfiguration.class */
public class CentralRayWithWhiskersConfiguration<T extends Vector<T>> extends RayConfigurationBase<T> {
    private float rayLength;
    private float whiskerLength;
    private float whiskerAngle;

    public CentralRayWithWhiskersConfiguration(Steerable<T> steerable, float f, float f2, float f3) {
        super(steerable, 3);
        this.rayLength = f;
        this.whiskerLength = f2;
        this.whiskerAngle = f3;
    }

    @Override // com.badlogic.gdx.ai.steer.utils.RayConfiguration
    public Ray<T>[] updateRays() {
        T position = this.owner.getPosition();
        T linearVelocity = this.owner.getLinearVelocity();
        float vectorToAngle = this.owner.vectorToAngle(linearVelocity);
        this.rays[0].start.set(position);
        this.rays[0].end.set(linearVelocity).nor().scl(this.rayLength).add(position);
        this.rays[1].start.set(position);
        this.owner.angleToVector(this.rays[1].end, vectorToAngle - this.whiskerAngle).scl(this.whiskerLength).add(position);
        this.rays[2].start.set(position);
        this.owner.angleToVector(this.rays[2].end, vectorToAngle + this.whiskerAngle).scl(this.whiskerLength).add(position);
        return this.rays;
    }

    public float getRayLength() {
        return this.rayLength;
    }

    public void setRayLength(float f) {
        this.rayLength = f;
    }

    public float getWhiskerLength() {
        return this.whiskerLength;
    }

    public void setWhiskerLength(float f) {
        this.whiskerLength = f;
    }

    public float getWhiskerAngle() {
        return this.whiskerAngle;
    }

    public void setWhiskerAngle(float f) {
        this.whiskerAngle = f;
    }
}
