package com.badlogic.gdx.ai.steer;

import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/GroupBehavior.class */
public abstract class GroupBehavior<T extends Vector<T>> extends SteeringBehavior<T> {
    protected Proximity<T> proximity;

    public GroupBehavior(Steerable<T> steerable, Proximity<T> proximity) {
        super(steerable);
        this.proximity = proximity;
    }

    public Proximity<T> getProximity() {
        return this.proximity;
    }

    public void setProximity(Proximity<T> proximity) {
        this.proximity = proximity;
    }
}
