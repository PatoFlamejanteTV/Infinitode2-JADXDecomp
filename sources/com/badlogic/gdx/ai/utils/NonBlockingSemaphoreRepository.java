package com.badlogic.gdx.ai.utils;

import com.badlogic.gdx.ai.utils.NonBlockingSemaphore;
import com.badlogic.gdx.ai.utils.SimpleNonBlockingSemaphore;
import com.badlogic.gdx.utils.ObjectMap;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/utils/NonBlockingSemaphoreRepository.class */
public class NonBlockingSemaphoreRepository {
    private static final ObjectMap<String, NonBlockingSemaphore> REPO = new ObjectMap<>();
    private static NonBlockingSemaphore.Factory FACTORY = new SimpleNonBlockingSemaphore.Factory();

    public static void setFactory(NonBlockingSemaphore.Factory factory) {
        FACTORY = factory;
    }

    public static NonBlockingSemaphore addSemaphore(String str, int i) {
        NonBlockingSemaphore createSemaphore = FACTORY.createSemaphore(str, i);
        REPO.put(str, createSemaphore);
        return createSemaphore;
    }

    public static NonBlockingSemaphore getSemaphore(String str) {
        return REPO.get(str);
    }

    public static NonBlockingSemaphore removeSemaphore(String str) {
        return REPO.remove(str);
    }

    public static void clear() {
        REPO.clear();
    }
}
