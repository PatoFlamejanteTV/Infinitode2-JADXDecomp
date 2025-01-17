package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/AsynchronousAssetLoader.class */
public abstract class AsynchronousAssetLoader<T, P extends AssetLoaderParameters<T>> extends AssetLoader<T, P> {
    public abstract void loadAsync(AssetManager assetManager, String str, FileHandle fileHandle, P p);

    public abstract T loadSync(AssetManager assetManager, String str, FileHandle fileHandle, P p);

    public AsynchronousAssetLoader(FileHandleResolver fileHandleResolver) {
        super(fileHandleResolver);
    }

    public void unloadAsync(AssetManager assetManager, String str, FileHandle fileHandle, P p) {
    }
}
