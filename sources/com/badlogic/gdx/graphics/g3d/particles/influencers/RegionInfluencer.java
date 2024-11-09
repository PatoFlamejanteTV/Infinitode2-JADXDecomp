package com.badlogic.gdx.graphics.g3d.particles.influencers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/RegionInfluencer.class */
public abstract class RegionInfluencer extends Influencer {
    public Array<AspectTextureRegion> regions;
    ParallelArray.FloatChannel regionChannel;
    public String atlasName;
    private static final String ASSET_DATA = "atlasAssetData";

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/RegionInfluencer$Single.class */
    public static class Single extends RegionInfluencer {
        public Single() {
        }

        public Single(Single single) {
            super(single);
        }

        public Single(TextureRegion textureRegion) {
            super(textureRegion);
        }

        public Single(Texture texture) {
            super(texture);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void init() {
            AspectTextureRegion aspectTextureRegion = this.regions.items[0];
            int i = 0;
            int i2 = this.controller.emitter.maxParticleCount * this.regionChannel.strideSize;
            while (i < i2) {
                this.regionChannel.data[i] = aspectTextureRegion.u;
                this.regionChannel.data[i + 1] = aspectTextureRegion.v;
                this.regionChannel.data[i + 2] = aspectTextureRegion.u2;
                this.regionChannel.data[i + 3] = aspectTextureRegion.v2;
                this.regionChannel.data[i + 4] = 0.5f;
                this.regionChannel.data[i + 5] = aspectTextureRegion.halfInvAspectRatio;
                i += this.regionChannel.strideSize;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public Single copy() {
            return new Single(this);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/RegionInfluencer$Random.class */
    public static class Random extends RegionInfluencer {
        public Random() {
        }

        public Random(Random random) {
            super(random);
        }

        public Random(TextureRegion textureRegion) {
            super(textureRegion);
        }

        public Random(Texture texture) {
            super(texture);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void activateParticles(int i, int i2) {
            int i3 = i * this.regionChannel.strideSize;
            int i4 = i3;
            int i5 = i3 + (i2 * this.regionChannel.strideSize);
            while (i4 < i5) {
                AspectTextureRegion random = this.regions.random();
                this.regionChannel.data[i4] = random.u;
                this.regionChannel.data[i4 + 1] = random.v;
                this.regionChannel.data[i4 + 2] = random.u2;
                this.regionChannel.data[i4 + 3] = random.v2;
                this.regionChannel.data[i4 + 4] = 0.5f;
                this.regionChannel.data[i4 + 5] = random.halfInvAspectRatio;
                i4 += this.regionChannel.strideSize;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public Random copy() {
            return new Random(this);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/RegionInfluencer$Animated.class */
    public static class Animated extends RegionInfluencer {
        ParallelArray.FloatChannel lifeChannel;

        public Animated() {
        }

        public Animated(Animated animated) {
            super(animated);
        }

        public Animated(TextureRegion textureRegion) {
            super(textureRegion);
        }

        public Animated(Texture texture) {
            super(texture);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.RegionInfluencer, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void allocateChannels() {
            super.allocateChannels();
            this.lifeChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Life);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            int i = 0;
            int i2 = 2;
            int i3 = this.controller.particles.size * this.regionChannel.strideSize;
            while (i < i3) {
                AspectTextureRegion aspectTextureRegion = this.regions.get((int) (this.lifeChannel.data[i2] * (this.regions.size - 1)));
                this.regionChannel.data[i] = aspectTextureRegion.u;
                this.regionChannel.data[i + 1] = aspectTextureRegion.v;
                this.regionChannel.data[i + 2] = aspectTextureRegion.u2;
                this.regionChannel.data[i + 3] = aspectTextureRegion.v2;
                this.regionChannel.data[i + 4] = 0.5f;
                this.regionChannel.data[i + 5] = aspectTextureRegion.halfInvAspectRatio;
                i += this.regionChannel.strideSize;
                i2 += this.lifeChannel.strideSize;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public Animated copy() {
            return new Animated(this);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/RegionInfluencer$AspectTextureRegion.class */
    public static class AspectTextureRegion {
        public float u;
        public float v;
        public float u2;
        public float v2;
        public float halfInvAspectRatio;
        public String imageName;

        public AspectTextureRegion() {
        }

        public AspectTextureRegion(AspectTextureRegion aspectTextureRegion) {
            set(aspectTextureRegion);
        }

        public AspectTextureRegion(TextureRegion textureRegion) {
            set(textureRegion);
        }

        public void set(TextureRegion textureRegion) {
            this.u = textureRegion.getU();
            this.v = textureRegion.getV();
            this.u2 = textureRegion.getU2();
            this.v2 = textureRegion.getV2();
            this.halfInvAspectRatio = 0.5f * (textureRegion.getRegionHeight() / textureRegion.getRegionWidth());
            if (textureRegion instanceof TextureAtlas.AtlasRegion) {
                this.imageName = ((TextureAtlas.AtlasRegion) textureRegion).name;
            }
        }

        public void set(AspectTextureRegion aspectTextureRegion) {
            this.u = aspectTextureRegion.u;
            this.v = aspectTextureRegion.v;
            this.u2 = aspectTextureRegion.u2;
            this.v2 = aspectTextureRegion.v2;
            this.halfInvAspectRatio = aspectTextureRegion.halfInvAspectRatio;
            this.imageName = aspectTextureRegion.imageName;
        }

        public void updateUV(TextureAtlas textureAtlas) {
            if (this.imageName == null) {
                return;
            }
            TextureAtlas.AtlasRegion findRegion = textureAtlas.findRegion(this.imageName);
            this.u = findRegion.getU();
            this.v = findRegion.getV();
            this.u2 = findRegion.getU2();
            this.v2 = findRegion.getV2();
            this.halfInvAspectRatio = 0.5f * (findRegion.getRegionHeight() / findRegion.getRegionWidth());
        }
    }

    public RegionInfluencer(int i) {
        this.regions = new Array<>(false, i, AspectTextureRegion.class);
    }

    public RegionInfluencer() {
        this(1);
        AspectTextureRegion aspectTextureRegion = new AspectTextureRegion();
        aspectTextureRegion.v = 0.0f;
        aspectTextureRegion.u = 0.0f;
        aspectTextureRegion.v2 = 1.0f;
        aspectTextureRegion.u2 = 1.0f;
        aspectTextureRegion.halfInvAspectRatio = 0.5f;
        this.regions.add(aspectTextureRegion);
    }

    public RegionInfluencer(TextureRegion... textureRegionArr) {
        setAtlasName(null);
        this.regions = new Array<>(false, textureRegionArr.length, AspectTextureRegion.class);
        add(textureRegionArr);
    }

    public RegionInfluencer(Texture texture) {
        this(new TextureRegion(texture));
    }

    public RegionInfluencer(RegionInfluencer regionInfluencer) {
        this(regionInfluencer.regions.size);
        this.regions.ensureCapacity(regionInfluencer.regions.size);
        for (int i = 0; i < regionInfluencer.regions.size; i++) {
            this.regions.add(new AspectTextureRegion(regionInfluencer.regions.get(i)));
        }
    }

    public void setAtlasName(String str) {
        this.atlasName = str;
    }

    public void add(TextureRegion... textureRegionArr) {
        this.regions.ensureCapacity(textureRegionArr.length);
        for (TextureRegion textureRegion : textureRegionArr) {
            this.regions.add(new AspectTextureRegion(textureRegion));
        }
    }

    public void clear() {
        this.atlasName = null;
        this.regions.clear();
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void load(AssetManager assetManager, ResourceData resourceData) {
        super.load(assetManager, resourceData);
        ResourceData.SaveData saveData = resourceData.getSaveData(ASSET_DATA);
        if (saveData == null) {
            return;
        }
        TextureAtlas textureAtlas = (TextureAtlas) assetManager.get(saveData.loadAsset());
        Array.ArrayIterator<AspectTextureRegion> it = this.regions.iterator();
        while (it.hasNext()) {
            it.next().updateUV(textureAtlas);
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void save(AssetManager assetManager, ResourceData resourceData) {
        super.save(assetManager, resourceData);
        if (this.atlasName != null) {
            ResourceData.SaveData saveData = resourceData.getSaveData(ASSET_DATA);
            ResourceData.SaveData saveData2 = saveData;
            if (saveData == null) {
                saveData2 = resourceData.createSaveData(ASSET_DATA);
            }
            saveData2.saveAsset(this.atlasName, TextureAtlas.class);
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.regionChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.TextureRegion);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.Serializable
    public void write(Json json) {
        json.writeValue("regions", this.regions, Array.class, AspectTextureRegion.class);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.Serializable
    public void read(Json json, JsonValue jsonValue) {
        this.regions.clear();
        this.regions.addAll((Array<? extends AspectTextureRegion>) json.readValue("regions", Array.class, AspectTextureRegion.class, jsonValue));
    }
}
