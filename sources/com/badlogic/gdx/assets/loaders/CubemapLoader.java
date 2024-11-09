package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Cubemap;
import com.badlogic.gdx.graphics.CubemapData;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.KTXTextureData;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/CubemapLoader.class */
public class CubemapLoader extends AsynchronousAssetLoader<Cubemap, CubemapParameter> {
    CubemapLoaderInfo info;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/CubemapLoader$CubemapLoaderInfo.class */
    public static class CubemapLoaderInfo {
        String filename;
        CubemapData data;
        Cubemap cubemap;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/CubemapLoader$CubemapParameter.class */
    public static class CubemapParameter extends AssetLoaderParameters<Cubemap> {
        public Pixmap.Format format = null;
        public Cubemap cubemap = null;
        public CubemapData cubemapData = null;
        public Texture.TextureFilter minFilter = Texture.TextureFilter.Nearest;
        public Texture.TextureFilter magFilter = Texture.TextureFilter.Nearest;
        public Texture.TextureWrap wrapU = Texture.TextureWrap.ClampToEdge;
        public Texture.TextureWrap wrapV = Texture.TextureWrap.ClampToEdge;
    }

    public CubemapLoader(FileHandleResolver fileHandleResolver) {
        super(fileHandleResolver);
        this.info = new CubemapLoaderInfo();
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager, String str, FileHandle fileHandle, CubemapParameter cubemapParameter) {
        this.info.filename = str;
        if (cubemapParameter == null || cubemapParameter.cubemapData == null) {
            this.info.cubemap = null;
            if (cubemapParameter != null) {
                this.info.cubemap = cubemapParameter.cubemap;
            }
            if (str.contains(".ktx") || str.contains(".zktx")) {
                this.info.data = new KTXTextureData(fileHandle, false);
            }
        } else {
            this.info.data = cubemapParameter.cubemapData;
            this.info.cubemap = cubemapParameter.cubemap;
        }
        if (!this.info.data.isPrepared()) {
            this.info.data.prepare();
        }
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public Cubemap loadSync(AssetManager assetManager, String str, FileHandle fileHandle, CubemapParameter cubemapParameter) {
        if (this.info == null) {
            return null;
        }
        Cubemap cubemap = this.info.cubemap;
        Cubemap cubemap2 = cubemap;
        if (cubemap != null) {
            cubemap2.load(this.info.data);
        } else {
            cubemap2 = new Cubemap(this.info.data);
        }
        if (cubemapParameter != null) {
            cubemap2.setFilter(cubemapParameter.minFilter, cubemapParameter.magFilter);
            cubemap2.setWrap(cubemapParameter.wrapU, cubemapParameter.wrapV);
        }
        return cubemap2;
    }

    @Override // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array<AssetDescriptor> getDependencies(String str, FileHandle fileHandle, CubemapParameter cubemapParameter) {
        return null;
    }
}
