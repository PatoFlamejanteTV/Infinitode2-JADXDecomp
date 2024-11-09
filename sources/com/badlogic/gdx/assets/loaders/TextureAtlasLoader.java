package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/TextureAtlasLoader.class */
public class TextureAtlasLoader extends SynchronousAssetLoader<TextureAtlas, TextureAtlasParameter> {
    TextureAtlas.TextureAtlasData data;

    public TextureAtlasLoader(FileHandleResolver fileHandleResolver) {
        super(fileHandleResolver);
    }

    @Override // com.badlogic.gdx.assets.loaders.SynchronousAssetLoader
    public TextureAtlas load(AssetManager assetManager, String str, FileHandle fileHandle, TextureAtlasParameter textureAtlasParameter) {
        Array.ArrayIterator<TextureAtlas.TextureAtlasData.Page> it = this.data.getPages().iterator();
        while (it.hasNext()) {
            TextureAtlas.TextureAtlasData.Page next = it.next();
            next.texture = (Texture) assetManager.get(next.textureFile.path().replaceAll("\\\\", "/"), Texture.class);
        }
        TextureAtlas textureAtlas = new TextureAtlas(this.data);
        this.data = null;
        return textureAtlas;
    }

    @Override // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array<AssetDescriptor> getDependencies(String str, FileHandle fileHandle, TextureAtlasParameter textureAtlasParameter) {
        FileHandle parent = fileHandle.parent();
        if (textureAtlasParameter != null) {
            this.data = new TextureAtlas.TextureAtlasData(fileHandle, parent, textureAtlasParameter.flip);
        } else {
            this.data = new TextureAtlas.TextureAtlasData(fileHandle, parent, false);
        }
        Array<AssetDescriptor> array = new Array<>();
        Array.ArrayIterator<TextureAtlas.TextureAtlasData.Page> it = this.data.getPages().iterator();
        while (it.hasNext()) {
            TextureAtlas.TextureAtlasData.Page next = it.next();
            TextureLoader.TextureParameter textureParameter = new TextureLoader.TextureParameter();
            textureParameter.format = next.format;
            textureParameter.genMipMaps = next.useMipMaps;
            textureParameter.minFilter = next.minFilter;
            textureParameter.magFilter = next.magFilter;
            array.add(new AssetDescriptor(next.textureFile, Texture.class, textureParameter));
        }
        return array;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/TextureAtlasLoader$TextureAtlasParameter.class */
    public static class TextureAtlasParameter extends AssetLoaderParameters<TextureAtlas> {
        public boolean flip;

        public TextureAtlasParameter() {
            this.flip = false;
        }

        public TextureAtlasParameter(boolean z) {
            this.flip = false;
            this.flip = z;
        }
    }
}
