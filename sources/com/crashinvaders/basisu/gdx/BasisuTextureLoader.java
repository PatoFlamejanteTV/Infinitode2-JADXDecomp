package com.crashinvaders.basisu.gdx;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/crashinvaders/basisu/gdx/BasisuTextureLoader.class */
public class BasisuTextureLoader extends AsynchronousAssetLoader<Texture, TextureLoader.TextureParameter> {
    BasisuTextureData textureData;

    /* loaded from: infinitode-2.jar:com/crashinvaders/basisu/gdx/BasisuTextureLoader$BasisuTextureParameter.class */
    public static class BasisuTextureParameter extends TextureLoader.TextureParameter {
        public int imageIndex = 0;
        public int mipmapLevel = 0;
        public BasisuTextureFormatSelector formatSelector = null;
    }

    public BasisuTextureLoader(FileHandleResolver fileHandleResolver) {
        super(fileHandleResolver);
        BasisuGdxUtils.initSupportedGlTextureFormats();
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager, String str, FileHandle fileHandle, TextureLoader.TextureParameter textureParameter) {
        BasisuTextureData basisuTextureData;
        if (textureParameter instanceof BasisuTextureParameter) {
            BasisuTextureParameter basisuTextureParameter = (BasisuTextureParameter) textureParameter;
            basisuTextureData = new BasisuTextureData(fileHandle, basisuTextureParameter.imageIndex, basisuTextureParameter.mipmapLevel);
            if (basisuTextureParameter.formatSelector != null) {
                basisuTextureData.setTextureFormatSelector(basisuTextureParameter.formatSelector);
            }
        } else {
            basisuTextureData = new BasisuTextureData(fileHandle);
        }
        basisuTextureData.prepare();
        this.textureData = basisuTextureData;
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public Texture loadSync(AssetManager assetManager, String str, FileHandle fileHandle, TextureLoader.TextureParameter textureParameter) {
        Texture texture = new Texture(this.textureData);
        this.textureData = null;
        if (textureParameter != null) {
            texture.setFilter(textureParameter.minFilter, textureParameter.magFilter);
            texture.setWrap(textureParameter.wrapU, textureParameter.wrapV);
        }
        return texture;
    }

    @Override // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array<AssetDescriptor> getDependencies(String str, FileHandle fileHandle, TextureLoader.TextureParameter textureParameter) {
        return null;
    }
}
