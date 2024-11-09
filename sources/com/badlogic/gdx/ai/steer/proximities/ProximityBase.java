package com.badlogic.gdx.ai.steer.proximities;

import com.badlogic.gdx.ai.steer.Proximity;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/proximities/ProximityBase.class */
public abstract class ProximityBase<T extends Vector<T>> implements Proximity<T> {
    protected Steerable<T> owner;
    protected Iterable<? extends Steerable<T>> agents;

    public ProximityBase(Steerable<T> steerable, Iterable<? extends Steerable<T>> iterable) {
        this.owner = steerable;
        this.agents = iterable;
    }

    @Override // com.badlogic.gdx.ai.steer.Proximity
    public Steerable<T> getOwner() {
        return this.owner;
    }

    @Override // com.badlogic.gdx.ai.steer.Proximity
    public void setOwner(Steerable<T> steerable) {
        this.owner = steerable;
    }

    public Iterable<? extends Steerable<T>> getAgents() {
        return this.agents;
    }

    public void setAgents(Iterable<Steerable<T>> iterable) {
        this.agents = iterable;
    }
}
