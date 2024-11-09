package com.badlogic.gdx.ai.sched;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/sched/Scheduler.class */
public interface Scheduler extends Schedulable {
    void addWithAutomaticPhasing(Schedulable schedulable, int i);

    void add(Schedulable schedulable, int i, int i2);
}
