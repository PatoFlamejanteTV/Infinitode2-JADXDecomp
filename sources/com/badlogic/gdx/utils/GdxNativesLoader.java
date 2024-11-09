package com.badlogic.gdx.utils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/GdxNativesLoader.class */
public class GdxNativesLoader {
    public static boolean disableNativesLoading = false;
    private static boolean nativesLoaded;

    public static synchronized void load() {
        if (nativesLoaded || disableNativesLoading) {
            return;
        }
        new SharedLibraryLoader().load("gdx");
        nativesLoaded = true;
    }
}
