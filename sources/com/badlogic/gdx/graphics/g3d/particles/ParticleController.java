package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.graphics.g3d.particles.emitters.Emitter;
import com.badlogic.gdx.graphics.g3d.particles.influencers.Influencer;
import com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.vladsch.flexmark.util.html.Attribute;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/ParticleController.class */
public class ParticleController implements ResourceData.Configurable, Json.Serializable {
    protected static final float DEFAULT_TIME_STEP = 0.016666668f;
    public String name;
    public Emitter emitter;
    public Array<Influencer> influencers;
    public ParticleControllerRenderer<?, ?> renderer;
    public ParallelArray particles;
    public ParticleChannels particleChannels;
    public Matrix4 transform;
    public Vector3 scale;
    protected BoundingBox boundingBox;
    public float deltaTime;
    public float deltaTimeSqr;

    public ParticleController() {
        this.transform = new Matrix4();
        this.scale = new Vector3(1.0f, 1.0f, 1.0f);
        this.influencers = new Array<>(true, 3, Influencer.class);
        setTimeStep(DEFAULT_TIME_STEP);
    }

    public ParticleController(String str, Emitter emitter, ParticleControllerRenderer<?, ?> particleControllerRenderer, Influencer... influencerArr) {
        this();
        this.name = str;
        this.emitter = emitter;
        this.renderer = particleControllerRenderer;
        this.particleChannels = new ParticleChannels();
        this.influencers = new Array<>(influencerArr);
    }

    private void setTimeStep(float f) {
        this.deltaTime = f;
        this.deltaTimeSqr = this.deltaTime * this.deltaTime;
    }

    public void setTransform(Matrix4 matrix4) {
        this.transform.set(matrix4);
        matrix4.getScale(this.scale);
    }

    public void setTransform(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        this.transform.set(f, f2, f3, f4, f5, f6, f7, f8, f8, f8);
        this.scale.set(f8, f8, f8);
    }

    public void rotate(Quaternion quaternion) {
        this.transform.rotate(quaternion);
    }

    public void rotate(Vector3 vector3, float f) {
        this.transform.rotate(vector3, f);
    }

    public void translate(Vector3 vector3) {
        this.transform.translate(vector3);
    }

    public void setTranslation(Vector3 vector3) {
        this.transform.setTranslation(vector3);
    }

    public void scale(float f, float f2, float f3) {
        this.transform.scale(f, f2, f3);
        this.transform.getScale(this.scale);
    }

    public void scale(Vector3 vector3) {
        scale(vector3.x, vector3.y, vector3.z);
    }

    public void mul(Matrix4 matrix4) {
        this.transform.mul(matrix4);
        this.transform.getScale(this.scale);
    }

    public void getTransform(Matrix4 matrix4) {
        matrix4.set(this.transform);
    }

    public boolean isComplete() {
        return this.emitter.isComplete();
    }

    public void init() {
        bind();
        if (this.particles != null) {
            end();
            this.particleChannels.resetIds();
        }
        allocateChannels(this.emitter.maxParticleCount);
        this.emitter.init();
        Array.ArrayIterator<Influencer> it = this.influencers.iterator();
        while (it.hasNext()) {
            it.next().init();
        }
        this.renderer.init();
    }

    protected void allocateChannels(int i) {
        this.particles = new ParallelArray(i);
        this.emitter.allocateChannels();
        Array.ArrayIterator<Influencer> it = this.influencers.iterator();
        while (it.hasNext()) {
            it.next().allocateChannels();
        }
        this.renderer.allocateChannels();
    }

    protected void bind() {
        this.emitter.set(this);
        Array.ArrayIterator<Influencer> it = this.influencers.iterator();
        while (it.hasNext()) {
            it.next().set(this);
        }
        this.renderer.set(this);
    }

    public void start() {
        this.emitter.start();
        Array.ArrayIterator<Influencer> it = this.influencers.iterator();
        while (it.hasNext()) {
            it.next().start();
        }
    }

    public void reset() {
        end();
        start();
    }

    public void end() {
        Array.ArrayIterator<Influencer> it = this.influencers.iterator();
        while (it.hasNext()) {
            it.next().end();
        }
        this.emitter.end();
    }

    public void activateParticles(int i, int i2) {
        this.emitter.activateParticles(i, i2);
        Array.ArrayIterator<Influencer> it = this.influencers.iterator();
        while (it.hasNext()) {
            it.next().activateParticles(i, i2);
        }
    }

    public void killParticles(int i, int i2) {
        this.emitter.killParticles(i, i2);
        Array.ArrayIterator<Influencer> it = this.influencers.iterator();
        while (it.hasNext()) {
            it.next().killParticles(i, i2);
        }
    }

    public void update() {
        update(Gdx.graphics.getDeltaTime());
    }

    public void update(float f) {
        setTimeStep(f);
        this.emitter.update();
        Array.ArrayIterator<Influencer> it = this.influencers.iterator();
        while (it.hasNext()) {
            it.next().update();
        }
    }

    public void draw() {
        if (this.particles.size > 0) {
            this.renderer.update();
        }
    }

    public ParticleController copy() {
        Emitter emitter = (Emitter) this.emitter.copy();
        Influencer[] influencerArr = new Influencer[this.influencers.size];
        int i = 0;
        Array.ArrayIterator<Influencer> it = this.influencers.iterator();
        while (it.hasNext()) {
            int i2 = i;
            i++;
            influencerArr[i2] = (Influencer) it.next().copy();
        }
        return new ParticleController(new String(this.name), emitter, (ParticleControllerRenderer) this.renderer.copy(), influencerArr);
    }

    public void dispose() {
        this.emitter.dispose();
        Array.ArrayIterator<Influencer> it = this.influencers.iterator();
        while (it.hasNext()) {
            it.next().dispose();
        }
    }

    public BoundingBox getBoundingBox() {
        if (this.boundingBox == null) {
            this.boundingBox = new BoundingBox();
        }
        calculateBoundingBox();
        return this.boundingBox;
    }

    protected void calculateBoundingBox() {
        this.boundingBox.clr();
        ParallelArray.FloatChannel floatChannel = (ParallelArray.FloatChannel) this.particles.getChannel(ParticleChannels.Position);
        int i = floatChannel.strideSize * this.particles.size;
        for (int i2 = 0; i2 < i; i2 += floatChannel.strideSize) {
            this.boundingBox.ext(floatChannel.data[i2], floatChannel.data[i2 + 1], floatChannel.data[i2 + 2]);
        }
    }

    private <K extends Influencer> int findIndex(Class<K> cls) {
        for (int i = 0; i < this.influencers.size; i++) {
            if (ClassReflection.isAssignableFrom(cls, this.influencers.get(i).getClass())) {
                return i;
            }
        }
        return -1;
    }

    public <K extends Influencer> K findInfluencer(Class<K> cls) {
        int findIndex = findIndex(cls);
        if (findIndex >= 0) {
            return (K) this.influencers.get(findIndex);
        }
        return null;
    }

    public <K extends Influencer> void removeInfluencer(Class<K> cls) {
        int findIndex = findIndex(cls);
        if (findIndex >= 0) {
            this.influencers.removeIndex(findIndex);
        }
    }

    public <K extends Influencer> boolean replaceInfluencer(Class<K> cls, K k) {
        int findIndex = findIndex(cls);
        if (findIndex >= 0) {
            this.influencers.insert(findIndex, k);
            this.influencers.removeIndex(findIndex + 1);
            return true;
        }
        return false;
    }

    @Override // com.badlogic.gdx.utils.Json.Serializable
    public void write(Json json) {
        json.writeValue(Attribute.NAME_ATTR, this.name);
        json.writeValue("emitter", this.emitter, Emitter.class);
        json.writeValue("influencers", this.influencers, Array.class, Influencer.class);
        json.writeValue("renderer", this.renderer, ParticleControllerRenderer.class);
    }

    @Override // com.badlogic.gdx.utils.Json.Serializable
    public void read(Json json, JsonValue jsonValue) {
        this.name = (String) json.readValue(Attribute.NAME_ATTR, String.class, jsonValue);
        this.emitter = (Emitter) json.readValue("emitter", Emitter.class, jsonValue);
        this.influencers.addAll((Array<? extends Influencer>) json.readValue("influencers", Array.class, Influencer.class, jsonValue));
        this.renderer = (ParticleControllerRenderer) json.readValue("renderer", ParticleControllerRenderer.class, jsonValue);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void save(AssetManager assetManager, ResourceData resourceData) {
        this.emitter.save(assetManager, resourceData);
        Array.ArrayIterator<Influencer> it = this.influencers.iterator();
        while (it.hasNext()) {
            it.next().save(assetManager, resourceData);
        }
        this.renderer.save(assetManager, resourceData);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void load(AssetManager assetManager, ResourceData resourceData) {
        this.emitter.load(assetManager, resourceData);
        Array.ArrayIterator<Influencer> it = this.influencers.iterator();
        while (it.hasNext()) {
            it.next().load(assetManager, resourceData);
        }
        this.renderer.load(assetManager, resourceData);
    }
}
