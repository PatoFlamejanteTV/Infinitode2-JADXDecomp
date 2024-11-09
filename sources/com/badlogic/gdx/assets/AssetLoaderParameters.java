package com.badlogic.gdx.assets;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/AssetLoaderParameters.class */
public class AssetLoaderParameters<T> {
    public LoadedCallback loadedCallback;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/AssetLoaderParameters$LoadedCallback.class */
    public interface LoadedCallback {
        void finishedLoading(AssetManager assetManager, String str, Class cls);
    }
}
