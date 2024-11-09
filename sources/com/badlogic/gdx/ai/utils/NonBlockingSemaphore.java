package com.badlogic.gdx.ai.utils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/NonBlockingSemaphore.class */
public interface NonBlockingSemaphore {

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/NonBlockingSemaphore$Factory.class */
    public interface Factory {
        NonBlockingSemaphore createSemaphore(String str, int i);
    }

    boolean acquire();

    boolean acquire(int i);

    boolean release();

    boolean release(int i);
}
