package com.crashinvaders.basisu.gdx;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.SharedLibraryLoader;

/* loaded from: infinitode-2.jar:com/crashinvaders/basisu/gdx/BasisuNativeLibLoader.class */
public class BasisuNativeLibLoader {
    private static boolean nativeLibLoaded = false;

    public static synchronized void loadIfNeeded() {
        if (nativeLibLoaded) {
            return;
        }
        if (Gdx.app.getType() == Application.ApplicationType.WebGL) {
            nativeLibLoaded = true;
        } else {
            new SharedLibraryLoader().load("gdx-basis-universal");
            nativeLibLoaded = true;
        }
    }
}
