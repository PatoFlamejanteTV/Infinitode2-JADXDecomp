package com.badlogic.gdx.graphics.g3d.particles.influencers;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/ModelInfluencer.class */
public abstract class ModelInfluencer extends Influencer {
    public Array<Model> models;
    ParallelArray.ObjectChannel<ModelInstance> modelChannel;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/ModelInfluencer$Single.class */
    public static class Single extends ModelInfluencer {
        public Single() {
        }

        public Single(Single single) {
            super(single);
        }

        public Single(Model... modelArr) {
            super(modelArr);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void init() {
            Model first = this.models.first();
            int i = this.controller.emitter.maxParticleCount;
            for (int i2 = 0; i2 < i; i2++) {
                this.modelChannel.data[i2] = new ModelInstance(first);
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public Single copy() {
            return new Single(this);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/ModelInfluencer$Random.class */
    public static class Random extends ModelInfluencer {
        ModelInstancePool pool;

        /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/ModelInfluencer$Random$ModelInstancePool.class */
        private class ModelInstancePool extends Pool<ModelInstance> {
            public ModelInstancePool() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.badlogic.gdx.utils.Pool
            public ModelInstance newObject() {
                return new ModelInstance(Random.this.models.random());
            }
        }

        public Random() {
            this.pool = new ModelInstancePool();
        }

        public Random(Random random) {
            super(random);
            this.pool = new ModelInstancePool();
        }

        public Random(Model... modelArr) {
            super(modelArr);
            this.pool = new ModelInstancePool();
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void init() {
            this.pool.clear();
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void activateParticles(int i, int i2) {
            int i3 = i + i2;
            for (int i4 = i; i4 < i3; i4++) {
                this.modelChannel.data[i4] = this.pool.obtain();
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void killParticles(int i, int i2) {
            int i3 = i + i2;
            for (int i4 = i; i4 < i3; i4++) {
                this.pool.free(this.modelChannel.data[i4]);
                this.modelChannel.data[i4] = null;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public Random copy() {
            return new Random(this);
        }
    }

    public ModelInfluencer() {
        this.models = new Array<>(true, 1, Model.class);
    }

    public ModelInfluencer(Model... modelArr) {
        this.models = new Array<>(modelArr);
    }

    public ModelInfluencer(ModelInfluencer modelInfluencer) {
        this((Model[]) modelInfluencer.models.toArray(Model.class));
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.modelChannel = (ParallelArray.ObjectChannel) this.controller.particles.addChannel(ParticleChannels.ModelInstance);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void save(AssetManager assetManager, ResourceData resourceData) {
        ResourceData.SaveData createSaveData = resourceData.createSaveData();
        Array.ArrayIterator<Model> it = this.models.iterator();
        while (it.hasNext()) {
            createSaveData.saveAsset(assetManager.getAssetFileName(it.next()), Model.class);
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void load(AssetManager assetManager, ResourceData resourceData) {
        ResourceData.SaveData saveData = resourceData.getSaveData();
        while (true) {
            AssetDescriptor loadAsset = saveData.loadAsset();
            if (loadAsset != null) {
                Model model = (Model) assetManager.get(loadAsset);
                if (model == null) {
                    throw new RuntimeException("Model is null");
                }
                this.models.add(model);
            } else {
                return;
            }
        }
    }
}
