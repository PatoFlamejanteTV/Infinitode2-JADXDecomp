package com.badlogic.gdx.ai.utils;

import com.badlogic.gdx.ai.utils.NonBlockingSemaphore;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/SimpleNonBlockingSemaphore.class */
public class SimpleNonBlockingSemaphore implements NonBlockingSemaphore {
    String name;
    int maxResources;
    int acquiredResources = 0;

    public SimpleNonBlockingSemaphore(String str, int i) {
        this.name = str;
        this.maxResources = i;
    }

    @Override // com.badlogic.gdx.ai.utils.NonBlockingSemaphore
    public boolean acquire() {
        return acquire(1);
    }

    @Override // com.badlogic.gdx.ai.utils.NonBlockingSemaphore
    public boolean acquire(int i) {
        if (this.acquiredResources + i <= this.maxResources) {
            this.acquiredResources += i;
            return true;
        }
        return false;
    }

    @Override // com.badlogic.gdx.ai.utils.NonBlockingSemaphore
    public boolean release() {
        return release(1);
    }

    @Override // com.badlogic.gdx.ai.utils.NonBlockingSemaphore
    public boolean release(int i) {
        if (this.acquiredResources - i >= 0) {
            this.acquiredResources -= i;
            return true;
        }
        return false;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/SimpleNonBlockingSemaphore$Factory.class */
    public static class Factory implements NonBlockingSemaphore.Factory {
        @Override // com.badlogic.gdx.ai.utils.NonBlockingSemaphore.Factory
        public NonBlockingSemaphore createSemaphore(String str, int i) {
            return new SimpleNonBlockingSemaphore(str, i);
        }
    }
}
