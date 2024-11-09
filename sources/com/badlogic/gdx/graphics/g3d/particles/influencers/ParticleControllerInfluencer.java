package com.badlogic.gdx.graphics.g3d.particles.influencers;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleController;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Pool;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/ParticleControllerInfluencer.class */
public abstract class ParticleControllerInfluencer extends Influencer {
    public Array<ParticleController> templates;
    ParallelArray.ObjectChannel<ParticleController> particleControllerChannel;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/ParticleControllerInfluencer$Single.class */
    public static class Single extends ParticleControllerInfluencer {
        public Single(ParticleController... particleControllerArr) {
            super(particleControllerArr);
        }

        public Single() {
        }

        public Single(Single single) {
            super(single);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void init() {
            ParticleController first = this.templates.first();
            int i = this.controller.particles.capacity;
            for (int i2 = 0; i2 < i; i2++) {
                ParticleController copy = first.copy();
                copy.init();
                this.particleControllerChannel.data[i2] = copy;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void activateParticles(int i, int i2) {
            int i3 = i + i2;
            for (int i4 = i; i4 < i3; i4++) {
                this.particleControllerChannel.data[i4].start();
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void killParticles(int i, int i2) {
            int i3 = i + i2;
            for (int i4 = i; i4 < i3; i4++) {
                this.particleControllerChannel.data[i4].end();
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public Single copy() {
            return new Single(this);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/ParticleControllerInfluencer$Random.class */
    public static class Random extends ParticleControllerInfluencer {
        ParticleControllerPool pool;

        /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/ParticleControllerInfluencer$Random$ParticleControllerPool.class */
        private class ParticleControllerPool extends Pool<ParticleController> {
            public ParticleControllerPool() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.badlogic.gdx.utils.Pool
            public ParticleController newObject() {
                ParticleController copy = Random.this.templates.random().copy();
                copy.init();
                return copy;
            }

            @Override // com.badlogic.gdx.utils.Pool
            public void clear() {
                int free = Random.this.pool.getFree();
                for (int i = 0; i < free; i++) {
                    Random.this.pool.obtain().dispose();
                }
                super.clear();
            }
        }

        public Random() {
            this.pool = new ParticleControllerPool();
        }

        public Random(ParticleController... particleControllerArr) {
            super(particleControllerArr);
            this.pool = new ParticleControllerPool();
        }

        public Random(Random random) {
            super(random);
            this.pool = new ParticleControllerPool();
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void init() {
            this.pool.clear();
            for (int i = 0; i < this.controller.emitter.maxParticleCount; i++) {
                this.pool.free(this.pool.newObject());
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.ParticleControllerInfluencer, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Disposable
        public void dispose() {
            this.pool.clear();
            super.dispose();
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void activateParticles(int i, int i2) {
            int i3 = i + i2;
            for (int i4 = i; i4 < i3; i4++) {
                ParticleController obtain = this.pool.obtain();
                obtain.start();
                this.particleControllerChannel.data[i4] = obtain;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void killParticles(int i, int i2) {
            int i3 = i + i2;
            for (int i4 = i; i4 < i3; i4++) {
                ParticleController particleController = this.particleControllerChannel.data[i4];
                particleController.end();
                this.pool.free(particleController);
                this.particleControllerChannel.data[i4] = null;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public Random copy() {
            return new Random(this);
        }
    }

    public ParticleControllerInfluencer() {
        this.templates = new Array<>(true, 1, ParticleController.class);
    }

    public ParticleControllerInfluencer(ParticleController... particleControllerArr) {
        this.templates = new Array<>(particleControllerArr);
    }

    public ParticleControllerInfluencer(ParticleControllerInfluencer particleControllerInfluencer) {
        this(particleControllerInfluencer.templates.items);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.particleControllerChannel = (ParallelArray.ObjectChannel) this.controller.particles.addChannel(ParticleChannels.ParticleController);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void end() {
        for (int i = 0; i < this.controller.particles.size; i++) {
            this.particleControllerChannel.data[i].end();
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if (this.controller != null) {
            for (int i = 0; i < this.controller.particles.size; i++) {
                ParticleController particleController = this.particleControllerChannel.data[i];
                if (particleController != null) {
                    particleController.dispose();
                    this.particleControllerChannel.data[i] = null;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void save(AssetManager assetManager, ResourceData resourceData) {
        ResourceData.SaveData createSaveData = resourceData.createSaveData();
        Array all = assetManager.getAll(ParticleEffect.class, new Array());
        Array array = new Array(this.templates);
        Array array2 = new Array();
        for (int i = 0; i < all.size && array.size > 0; i++) {
            ParticleEffect particleEffect = (ParticleEffect) all.get(i);
            Array<ParticleController> controllers = particleEffect.getControllers();
            Array.ArrayIterator it = array.iterator();
            IntArray intArray = null;
            while (it.hasNext()) {
                int indexOf = controllers.indexOf((ParticleController) it.next(), true);
                if (indexOf >= 0) {
                    if (intArray == null) {
                        intArray = new IntArray();
                    }
                    it.remove();
                    intArray.add(indexOf);
                }
            }
            if (intArray != null) {
                createSaveData.saveAsset(assetManager.getAssetFileName(particleEffect), ParticleEffect.class);
                array2.add(intArray);
            }
        }
        createSaveData.save("indices", array2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void load(AssetManager assetManager, ResourceData resourceData) {
        ResourceData.SaveData saveData = resourceData.getSaveData();
        Array.ArrayIterator it = ((Array) saveData.load("indices")).iterator();
        while (true) {
            AssetDescriptor loadAsset = saveData.loadAsset();
            if (loadAsset != null) {
                ParticleEffect particleEffect = (ParticleEffect) assetManager.get(loadAsset);
                if (particleEffect == null) {
                    throw new RuntimeException("Template is null");
                }
                Array<ParticleController> controllers = particleEffect.getControllers();
                IntArray intArray = (IntArray) it.next();
                int i = intArray.size;
                for (int i2 = 0; i2 < i; i2++) {
                    this.templates.add(controllers.get(intArray.get(i2)));
                }
            } else {
                return;
            }
        }
    }
}
