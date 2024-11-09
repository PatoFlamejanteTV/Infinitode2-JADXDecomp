package com.badlogic.gdx.backends.lwjgl3;

import com.badlogic.gdx.utils.GdxNativesLoader;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Lwjgl3NativesLoader.class */
public final class Lwjgl3NativesLoader {
    static {
        System.setProperty("org.lwjgl.input.Mouse.allowNegativeMouseCoords", "true");
    }

    public static void load() {
        GdxNativesLoader.load();
    }
}
