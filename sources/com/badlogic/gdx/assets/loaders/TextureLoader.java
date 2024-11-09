package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/TextureLoader.class */
public class TextureLoader extends AsynchronousAssetLoader<Texture, TextureParameter> {
    TextureLoaderInfo info;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/TextureLoader$TextureLoaderInfo.class */
    public static class TextureLoaderInfo {
        String filename;
        TextureData data;
        Texture texture;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/TextureLoader$TextureParameter.class */
    public static class TextureParameter extends AssetLoaderParameters<Texture> {
        public Pixmap.Format format = null;
        public boolean genMipMaps = false;
        public Texture texture = null;
        public TextureData textureData = null;
        public Texture.TextureFilter minFilter = Texture.TextureFilter.Nearest;
        public Texture.TextureFilter magFilter = Texture.TextureFilter.Nearest;
        public Texture.TextureWrap wrapU = Texture.TextureWrap.ClampToEdge;
        public Texture.TextureWrap wrapV = Texture.TextureWrap.ClampToEdge;
    }

    public TextureLoader(FileHandleResolver fileHandleResolver) {
        super(fileHandleResolver);
        this.info = new TextureLoaderInfo();
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager, String str, FileHandle fileHandle, TextureParameter textureParameter) {
        this.info.filename = str;
        if (textureParameter == null || textureParameter.textureData == null) {
            Pixmap.Format format = null;
            boolean z = false;
            this.info.texture = null;
            if (textureParameter != null) {
                format = textureParameter.format;
                z = textureParameter.genMipMaps;
                this.info.texture = textureParameter.texture;
            }
            this.info.data = TextureData.Factory.loadFromFile(fileHandle, format, z);
        } else {
            this.info.data = textureParameter.textureData;
            this.info.texture = textureParameter.texture;
        }
        if (!this.info.data.isPrepared()) {
            this.info.data.prepare();
        }
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public Texture loadSync(AssetManager assetManager, String str, FileHandle fileHandle, TextureParameter textureParameter) {
        if (this.info == null) {
            return null;
        }
        Texture texture = this.info.texture;
        Texture texture2 = texture;
        if (texture != null) {
            texture2.load(this.info.data);
        } else {
            texture2 = new Texture(this.info.data);
        }
        if (textureParameter != null) {
            texture2.setFilter(textureParameter.minFilter, textureParameter.magFilter);
            texture2.setWrap(textureParameter.wrapU, textureParameter.wrapV);
        }
        return texture2;
    }

    @Override // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array<AssetDescriptor> getDependencies(String str, FileHandle fileHandle, TextureParameter textureParameter) {
        return null;
    }
}
