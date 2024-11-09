package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/AssetLoader.class */
public abstract class AssetLoader<T, P extends AssetLoaderParameters<T>> {
    private FileHandleResolver resolver;

    public abstract Array<AssetDescriptor> getDependencies(String str, FileHandle fileHandle, P p);

    public AssetLoader(FileHandleResolver fileHandleResolver) {
        this.resolver = fileHandleResolver;
    }

    public FileHandle resolve(String str) {
        return this.resolver.resolve(str);
    }
}
