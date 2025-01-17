package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/ParticleControllerComponent.class */
public abstract class ParticleControllerComponent implements ResourceData.Configurable, Disposable, Json.Serializable {
    protected static final Vector3 TMP_V1 = new Vector3();
    protected static final Vector3 TMP_V2 = new Vector3();
    protected static final Vector3 TMP_V3 = new Vector3();
    protected static final Vector3 TMP_V4 = new Vector3();
    protected static final Vector3 TMP_V5 = new Vector3();
    protected static final Vector3 TMP_V6 = new Vector3();
    protected static final Quaternion TMP_Q = new Quaternion();
    protected static final Quaternion TMP_Q2 = new Quaternion();
    protected static final Matrix3 TMP_M3 = new Matrix3();
    protected static final Matrix4 TMP_M4 = new Matrix4();
    protected ParticleController controller;

    public abstract ParticleControllerComponent copy();

    public void activateParticles(int i, int i2) {
    }

    public void killParticles(int i, int i2) {
    }

    public void update() {
    }

    public void init() {
    }

    public void start() {
    }

    public void end() {
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }

    public void allocateChannels() {
    }

    public void set(ParticleController particleController) {
        this.controller = particleController;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void save(AssetManager assetManager, ResourceData resourceData) {
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void load(AssetManager assetManager, ResourceData resourceData) {
    }

    @Override // com.badlogic.gdx.utils.Json.Serializable
    public void write(Json json) {
    }

    @Override // com.badlogic.gdx.utils.Json.Serializable
    public void read(Json json, JsonValue jsonValue) {
    }
}
