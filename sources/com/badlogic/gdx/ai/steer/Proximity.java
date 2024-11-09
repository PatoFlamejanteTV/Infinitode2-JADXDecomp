package com.badlogic.gdx.ai.steer;

import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/Proximity.class */
public interface Proximity<T extends Vector<T>> {

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/Proximity$ProximityCallback.class */
    public interface ProximityCallback<T extends Vector<T>> {
        boolean reportNeighbor(Steerable<T> steerable);
    }

    Steerable<T> getOwner();

    void setOwner(Steerable<T> steerable);

    int findNeighbors(ProximityCallback<T> proximityCallback);
}
