package com.badlogic.gdx.ai.steer.proximities;

import com.badlogic.gdx.ai.steer.Proximity;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/proximities/InfiniteProximity.class */
public class InfiniteProximity<T extends Vector<T>> extends ProximityBase<T> {
    public InfiniteProximity(Steerable<T> steerable, Iterable<? extends Steerable<T>> iterable) {
        super(steerable, iterable);
    }

    @Override // com.badlogic.gdx.ai.steer.Proximity
    public int findNeighbors(Proximity.ProximityCallback<T> proximityCallback) {
        int i = 0;
        for (Steerable<T> steerable : this.agents) {
            if (steerable != this.owner && proximityCallback.reportNeighbor(steerable)) {
                i++;
            }
        }
        return i;
    }
}
