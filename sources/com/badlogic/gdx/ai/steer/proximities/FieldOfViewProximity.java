package com.badlogic.gdx.ai.steer.proximities;

import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.ai.steer.Proximity;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/proximities/FieldOfViewProximity.class */
public class FieldOfViewProximity<T extends Vector<T>> extends ProximityBase<T> {
    protected float radius;
    protected float angle;
    private float coneThreshold;
    private float lastTime;
    private T ownerOrientation;
    private T toAgent;

    public FieldOfViewProximity(Steerable<T> steerable, Iterable<? extends Steerable<T>> iterable, float f, float f2) {
        super(steerable, iterable);
        this.radius = f;
        setAngle(f2);
        this.lastTime = 0.0f;
        this.ownerOrientation = (T) steerable.getPosition().cpy().setZero();
        this.toAgent = (T) steerable.getPosition().cpy().setZero();
    }

    public float getRadius() {
        return this.radius;
    }

    public void setRadius(float f) {
        this.radius = f;
    }

    public float getAngle() {
        return this.angle;
    }

    public void setAngle(float f) {
        this.angle = f;
        this.coneThreshold = (float) Math.cos(f * 0.5f);
    }

    @Override // com.badlogic.gdx.ai.steer.Proximity
    public int findNeighbors(Proximity.ProximityCallback<T> proximityCallback) {
        int i = 0;
        float time = GdxAI.getTimepiece().getTime();
        if (this.lastTime != time) {
            this.lastTime = time;
            T position = this.owner.getPosition();
            this.owner.angleToVector(this.ownerOrientation, this.owner.getOrientation());
            for (Steerable<T> steerable : this.agents) {
                if (steerable != this.owner) {
                    this.toAgent.set(steerable.getPosition()).sub(position);
                    float boundingRadius = this.radius + steerable.getBoundingRadius();
                    if (this.toAgent.len2() < boundingRadius * boundingRadius && this.ownerOrientation.dot(this.toAgent) > this.coneThreshold && proximityCallback.reportNeighbor(steerable)) {
                        steerable.setTagged(true);
                        i++;
                    }
                }
                steerable.setTagged(false);
            }
        } else {
            for (Steerable<T> steerable2 : this.agents) {
                if (steerable2 != this.owner && steerable2.isTagged() && proximityCallback.reportNeighbor(steerable2)) {
                    i++;
                }
            }
        }
        return i;
    }
}
