package com.badlogic.gdx.assets;

import com.badlogic.gdx.files.FileHandle;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/AssetDescriptor.class */
public class AssetDescriptor<T> {
    public final String fileName;
    public final Class<T> type;
    public final AssetLoaderParameters params;
    public FileHandle file;

    public AssetDescriptor(String str, Class<T> cls) {
        this(str, cls, (AssetLoaderParameters) null);
    }

    public AssetDescriptor(FileHandle fileHandle, Class<T> cls) {
        this(fileHandle, cls, (AssetLoaderParameters) null);
    }

    public AssetDescriptor(String str, Class<T> cls, AssetLoaderParameters<T> assetLoaderParameters) {
        this.fileName = str;
        this.type = cls;
        this.params = assetLoaderParameters;
    }

    public AssetDescriptor(FileHandle fileHandle, Class<T> cls, AssetLoaderParameters<T> assetLoaderParameters) {
        this.fileName = fileHandle.path();
        this.file = fileHandle;
        this.type = cls;
        this.params = assetLoaderParameters;
    }

    public String toString() {
        return this.fileName + ", " + this.type.getName();
    }
}
