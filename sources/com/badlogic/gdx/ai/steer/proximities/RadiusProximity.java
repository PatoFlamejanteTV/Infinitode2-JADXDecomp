package com.badlogic.gdx.ai.steer.proximities;

import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.ai.steer.Proximity;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/proximities/RadiusProximity.class */
public class RadiusProximity<T extends Vector<T>> extends ProximityBase<T> {
    protected float radius;
    private float lastTime;

    public RadiusProximity(Steerable<T> steerable, Iterable<? extends Steerable<T>> iterable, float f) {
        super(steerable, iterable);
        this.radius = f;
        this.lastTime = 0.0f;
    }

    public float getRadius() {
        return this.radius;
    }

    public void setRadius(float f) {
        this.radius = f;
    }

    @Override // com.badlogic.gdx.ai.steer.Proximity
    public int findNeighbors(Proximity.ProximityCallback<T> proximityCallback) {
        int i = 0;
        float time = GdxAI.getTimepiece().getTime();
        if (this.lastTime != time) {
            this.lastTime = time;
            T position = this.owner.getPosition();
            for (Steerable<T> steerable : this.agents) {
                if (steerable != this.owner) {
                    float dst2 = position.dst2(steerable.getPosition());
                    float boundingRadius = this.radius + steerable.getBoundingRadius();
                    if (dst2 < boundingRadius * boundingRadius && proximityCallback.reportNeighbor(steerable)) {
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
