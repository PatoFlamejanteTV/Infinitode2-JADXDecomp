package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ModelLoader.ModelParameters;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.model.data.ModelData;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMaterial;
import com.badlogic.gdx.graphics.g3d.model.data.ModelTexture;
import com.badlogic.gdx.graphics.g3d.utils.TextureProvider;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/ModelLoader.class */
public abstract class ModelLoader<P extends ModelParameters> extends AsynchronousAssetLoader<Model, P> {
    protected Array<ObjectMap.Entry<String, ModelData>> items;
    protected ModelParameters defaultParameters;

    public abstract ModelData loadModelData(FileHandle fileHandle, P p);

    public ModelLoader(FileHandleResolver fileHandleResolver) {
        super(fileHandleResolver);
        this.items = new Array<>();
        this.defaultParameters = new ModelParameters();
    }

    public ModelData loadModelData(FileHandle fileHandle) {
        return loadModelData(fileHandle, null);
    }

    public Model loadModel(FileHandle fileHandle, TextureProvider textureProvider, P p) {
        ModelData loadModelData = loadModelData(fileHandle, p);
        if (loadModelData == null) {
            return null;
        }
        return new Model(loadModelData, textureProvider);
    }

    public Model loadModel(FileHandle fileHandle, P p) {
        return loadModel(fileHandle, new TextureProvider.FileTextureProvider(), p);
    }

    public Model loadModel(FileHandle fileHandle, TextureProvider textureProvider) {
        return loadModel(fileHandle, textureProvider, null);
    }

    public Model loadModel(FileHandle fileHandle) {
        return loadModel(fileHandle, new TextureProvider.FileTextureProvider(), null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [V, com.badlogic.gdx.graphics.g3d.model.data.ModelData] */
    @Override // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array<AssetDescriptor> getDependencies(String str, FileHandle fileHandle, P p) {
        Array<AssetDescriptor> array = new Array<>();
        ?? loadModelData = loadModelData(fileHandle, p);
        if (loadModelData == 0) {
            return array;
        }
        ObjectMap.Entry<String, ModelData> entry = new ObjectMap.Entry<>();
        entry.key = str;
        entry.value = loadModelData;
        synchronized (this.items) {
            this.items.add(entry);
        }
        TextureLoader.TextureParameter textureParameter = p != null ? p.textureParameter : this.defaultParameters.textureParameter;
        Array.ArrayIterator<ModelMaterial> it = loadModelData.materials.iterator();
        while (it.hasNext()) {
            ModelMaterial next = it.next();
            if (next.textures != null) {
                Array.ArrayIterator<ModelTexture> it2 = next.textures.iterator();
                while (it2.hasNext()) {
                    array.add(new AssetDescriptor(it2.next().fileName, Texture.class, textureParameter));
                }
            }
        }
        return array;
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager, String str, FileHandle fileHandle, P p) {
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public Model loadSync(AssetManager assetManager, String str, FileHandle fileHandle, P p) {
        ModelData modelData = null;
        synchronized (this.items) {
            for (int i = 0; i < this.items.size; i++) {
                if (this.items.get(i).key.equals(str)) {
                    modelData = this.items.get(i).value;
                    this.items.removeIndex(i);
                }
            }
        }
        if (modelData == null) {
            return null;
        }
        Model model = new Model(modelData, new TextureProvider.AssetTextureProvider(assetManager));
        Iterator<Disposable> it = model.getManagedDisposables().iterator();
        while (it.hasNext()) {
            if (it.next() instanceof Texture) {
                it.remove();
            }
        }
        return model;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/ModelLoader$ModelParameters.class */
    public static class ModelParameters extends AssetLoaderParameters<Model> {
        public TextureLoader.TextureParameter textureParameter = new TextureLoader.TextureParameter();

        public ModelParameters() {
            TextureLoader.TextureParameter textureParameter = this.textureParameter;
            TextureLoader.TextureParameter textureParameter2 = this.textureParameter;
            Texture.TextureFilter textureFilter = Texture.TextureFilter.Linear;
            textureParameter2.magFilter = textureFilter;
            textureParameter.minFilter = textureFilter;
            TextureLoader.TextureParameter textureParameter3 = this.textureParameter;
            TextureLoader.TextureParameter textureParameter4 = this.textureParameter;
            Texture.TextureWrap textureWrap = Texture.TextureWrap.Repeat;
            textureParameter4.wrapV = textureWrap;
            textureParameter3.wrapU = textureWrap;
        }
    }
}
