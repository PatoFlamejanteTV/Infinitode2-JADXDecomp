package com.badlogic.gdx.ai.btree;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/TaskCloner.class */
public interface TaskCloner {
    <T> Task<T> cloneTask(Task<T> task);

    <T> void freeTask(Task<T> task);
}
