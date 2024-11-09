package com.crashinvaders.basisu.gdx;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/crashinvaders/basisu/gdx/Ktx2TextureLoader.class */
public class Ktx2TextureLoader extends AsynchronousAssetLoader<Texture, TextureLoader.TextureParameter> {
    Ktx2TextureData textureData;

    /* loaded from: infinitode-2.jar:com/crashinvaders/basisu/gdx/Ktx2TextureLoader$Ktx2TextureParameter.class */
    public static class Ktx2TextureParameter extends TextureLoader.TextureParameter {
        public int mipmapLevel = 0;
        public BasisuTextureFormatSelector formatSelector = null;
    }

    public Ktx2TextureLoader(FileHandleResolver fileHandleResolver) {
        super(fileHandleResolver);
        BasisuGdxUtils.initSupportedGlTextureFormats();
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager, String str, FileHandle fileHandle, TextureLoader.TextureParameter textureParameter) {
        Ktx2TextureData ktx2TextureData;
        if (textureParameter instanceof Ktx2TextureParameter) {
            Ktx2TextureParameter ktx2TextureParameter = (Ktx2TextureParameter) textureParameter;
            ktx2TextureData = new Ktx2TextureData(fileHandle, ktx2TextureParameter.mipmapLevel);
            if (ktx2TextureParameter.formatSelector != null) {
                ktx2TextureData.setTextureFormatSelector(ktx2TextureParameter.formatSelector);
            }
        } else {
            ktx2TextureData = new Ktx2TextureData(fileHandle);
        }
        ktx2TextureData.prepare();
        this.textureData = ktx2TextureData;
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
