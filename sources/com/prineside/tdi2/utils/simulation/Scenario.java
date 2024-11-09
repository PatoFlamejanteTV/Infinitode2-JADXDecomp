package com.prineside.tdi2.utils.simulation;

import com.prineside.tdi2.GameSystemProvider;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/simulation/Scenario.class */
public interface Scenario {
    void start(GameSystemProvider gameSystemProvider);

    void setGSP(GameSystemProvider gameSystemProvider);

    boolean isFinished();

    float getProgress();

    void onUpdate();

    Scenario cpy();
}
