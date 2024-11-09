package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.reflect.ClassReflection;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/ParticleEffectLoader.class */
public class ParticleEffectLoader extends AsynchronousAssetLoader<ParticleEffect, ParticleEffectLoadParameter> {
    protected Array<ObjectMap.Entry<String, ResourceData<ParticleEffect>>> items;

    public ParticleEffectLoader(FileHandleResolver fileHandleResolver) {
        super(fileHandleResolver);
        this.items = new Array<>();
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager, String str, FileHandle fileHandle, ParticleEffectLoadParameter particleEffectLoadParameter) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.badlogic.gdx.graphics.g3d.particles.ResourceData, V] */
    @Override // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array<AssetDescriptor> getDependencies(String str, FileHandle fileHandle, ParticleEffectLoadParameter particleEffectLoadParameter) {
        Array<ResourceData.AssetData> assets;
        ?? r0 = (ResourceData) new Json().fromJson(ResourceData.class, fileHandle);
        synchronized (this.items) {
            ObjectMap.Entry<String, ResourceData<ParticleEffect>> entry = new ObjectMap.Entry<>();
            entry.key = str;
            entry.value = r0;
            this.items.add(entry);
            assets = r0.getAssets();
        }
        Array<AssetDescriptor> array = new Array<>();
        Array.ArrayIterator<ResourceData.AssetData> it = assets.iterator();
        while (it.hasNext()) {
            ResourceData.AssetData next = it.next();
            if (!resolve(next.filename).exists()) {
                next.filename = fileHandle.parent().child(Gdx.files.internal(next.filename).name()).path();
            }
            if (next.type == ParticleEffect.class) {
                array.add(new AssetDescriptor(next.filename, next.type, particleEffectLoadParameter));
            } else {
                array.add(new AssetDescriptor(next.filename, next.type));
            }
        }
        return array;
    }

    public void save(ParticleEffect particleEffect, ParticleEffectSaveParameter particleEffectSaveParameter) {
        ResourceData resourceData = new ResourceData(particleEffect);
        particleEffect.save(particleEffectSaveParameter.manager, resourceData);
        if (particleEffectSaveParameter.batches != null) {
            Array.ArrayIterator<ParticleBatch<?>> it = particleEffectSaveParameter.batches.iterator();
            while (it.hasNext()) {
                ParticleBatch<?> next = it.next();
                boolean z = false;
                Array.ArrayIterator<ParticleController> it2 = particleEffect.getControllers().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    } else if (it2.next().renderer.isCompatible(next)) {
                        z = true;
                        break;
                    }
                }
                if (z) {
                    next.save(particleEffectSaveParameter.manager, resourceData);
                }
            }
        }
        Json json = new Json(particleEffectSaveParameter.jsonOutputType);
        if (particleEffectSaveParameter.prettyPrint) {
            particleEffectSaveParameter.file.writeString(json.prettyPrint(resourceData), false);
        } else {
            json.toJson(resourceData, particleEffectSaveParameter.file);
        }
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public ParticleEffect loadSync(AssetManager assetManager, String str, FileHandle fileHandle, ParticleEffectLoadParameter particleEffectLoadParameter) {
        ResourceData<ParticleEffect> resourceData = null;
        synchronized (this.items) {
            int i = 0;
            while (true) {
                if (i >= this.items.size) {
                    break;
                }
                ObjectMap.Entry<String, ResourceData<ParticleEffect>> entry = this.items.get(i);
                if (!entry.key.equals(str)) {
                    i++;
                } else {
                    resourceData = entry.value;
                    this.items.removeIndex(i);
                    break;
                }
            }
        }
        resourceData.resource.load(assetManager, resourceData);
        if (particleEffectLoadParameter != null) {
            if (particleEffectLoadParameter.batches != null) {
                Array.ArrayIterator<ParticleBatch<?>> it = particleEffectLoadParameter.batches.iterator();
                while (it.hasNext()) {
                    it.next().load(assetManager, resourceData);
                }
            }
            resourceData.resource.setBatch(particleEffectLoadParameter.batches);
        }
        return resourceData.resource;
    }

    private <T> T find(Array<?> array, Class<T> cls) {
        Array.ArrayIterator<?> it = array.iterator();
        while (it.hasNext()) {
            T t = (T) it.next();
            if (ClassReflection.isAssignableFrom(cls, t.getClass())) {
                return t;
            }
        }
        return null;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/ParticleEffectLoader$ParticleEffectLoadParameter.class */
    public static class ParticleEffectLoadParameter extends AssetLoaderParameters<ParticleEffect> {
        Array<ParticleBatch<?>> batches;

        public ParticleEffectLoadParameter(Array<ParticleBatch<?>> array) {
            this.batches = array;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/ParticleEffectLoader$ParticleEffectSaveParameter.class */
    public static class ParticleEffectSaveParameter extends AssetLoaderParameters<ParticleEffect> {
        Array<ParticleBatch<?>> batches;
        FileHandle file;
        AssetManager manager;
        JsonWriter.OutputType jsonOutputType;
        boolean prettyPrint;

        public ParticleEffectSaveParameter(FileHandle fileHandle, AssetManager assetManager, Array<ParticleBatch<?>> array) {
            this(fileHandle, assetManager, array, JsonWriter.OutputType.minimal, false);
        }

        public ParticleEffectSaveParameter(FileHandle fileHandle, AssetManager assetManager, Array<ParticleBatch<?>> array, JsonWriter.OutputType outputType, boolean z) {
            this.batches = array;
            this.file = fileHandle;
            this.manager = assetManager;
            this.jsonOutputType = outputType;
            this.prettyPrint = z;
        }
    }
}
