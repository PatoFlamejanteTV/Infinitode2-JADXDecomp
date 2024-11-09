package com.prineside.tdi2.utils.simulation;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/simulation/Simulation.class */
public interface Simulation {
    CharSequence getName();

    void setSimLogListener(SimLogListener simLogListener);

    void setSimFinishListener(Runnable runnable);

    float getProgress();

    void start();

    boolean isRunning();

    boolean isReadyToStart();

    boolean isSuccessful();

    void stop();
}
