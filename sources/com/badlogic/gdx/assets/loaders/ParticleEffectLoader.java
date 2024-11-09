package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/ParticleEffectLoader.class */
public class ParticleEffectLoader extends SynchronousAssetLoader<ParticleEffect, ParticleEffectParameter> {

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/ParticleEffectLoader$ParticleEffectParameter.class */
    public static class ParticleEffectParameter extends AssetLoaderParameters<ParticleEffect> {
        public String atlasFile;
        public String atlasPrefix;
        public FileHandle imagesDir;
    }

    public ParticleEffectLoader(FileHandleResolver fileHandleResolver) {
        super(fileHandleResolver);
    }

    @Override // com.badlogic.gdx.assets.loaders.SynchronousAssetLoader
    public ParticleEffect load(AssetManager assetManager, String str, FileHandle fileHandle, ParticleEffectParameter particleEffectParameter) {
        ParticleEffect particleEffect = new ParticleEffect();
        if (particleEffectParameter != null && particleEffectParameter.atlasFile != null) {
            particleEffect.load(fileHandle, (TextureAtlas) assetManager.get(particleEffectParameter.atlasFile, TextureAtlas.class), particleEffectParameter.atlasPrefix);
        } else if (particleEffectParameter != null && particleEffectParameter.imagesDir != null) {
            particleEffect.load(fileHandle, particleEffectParameter.imagesDir);
        } else {
            particleEffect.load(fileHandle, fileHandle.parent());
        }
        return particleEffect;
    }

    @Override // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array<AssetDescriptor> getDependencies(String str, FileHandle fileHandle, ParticleEffectParameter particleEffectParameter) {
        Array<AssetDescriptor> array = null;
        if (particleEffectParameter != null && particleEffectParameter.atlasFile != null) {
            Array<AssetDescriptor> array2 = new Array<>();
            array = array2;
            array2.add(new AssetDescriptor(particleEffectParameter.atlasFile, TextureAtlas.class));
        }
        return array;
    }
}
