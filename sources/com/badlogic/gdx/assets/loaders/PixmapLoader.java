package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/PixmapLoader.class */
public class PixmapLoader extends AsynchronousAssetLoader<Pixmap, PixmapParameter> {
    Pixmap pixmap;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/PixmapLoader$PixmapParameter.class */
    public static class PixmapParameter extends AssetLoaderParameters<Pixmap> {
    }

    public PixmapLoader(FileHandleResolver fileHandleResolver) {
        super(fileHandleResolver);
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager, String str, FileHandle fileHandle, PixmapParameter pixmapParameter) {
        this.pixmap = null;
        this.pixmap = new Pixmap(fileHandle);
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public Pixmap loadSync(AssetManager assetManager, String str, FileHandle fileHandle, PixmapParameter pixmapParameter) {
        Pixmap pixmap = this.pixmap;
        this.pixmap = null;
        return pixmap;
    }

    @Override // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array<AssetDescriptor> getDependencies(String str, FileHandle fileHandle, PixmapParameter pixmapParameter) {
        return null;
    }
}
