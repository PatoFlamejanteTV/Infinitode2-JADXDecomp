package com.badlogic.gdx.graphics.g3d.particles.emitters;

import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/emitters/Emitter.class */
public abstract class Emitter extends ParticleControllerComponent implements Json.Serializable {
    public int minParticleCount;
    public int maxParticleCount = 4;
    public float percent;

    public Emitter(Emitter emitter) {
        set(emitter);
    }

    public Emitter() {
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void init() {
        this.controller.particles.size = 0;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void end() {
        this.controller.particles.size = 0;
    }

    public boolean isComplete() {
        return this.percent >= 1.0f;
    }

    public int getMinParticleCount() {
        return this.minParticleCount;
    }

    public void setMinParticleCount(int i) {
        this.minParticleCount = i;
    }

    public int getMaxParticleCount() {
        return this.maxParticleCount;
    }

    public void setMaxParticleCount(int i) {
        this.maxParticleCount = i;
    }

    public void setParticleCount(int i, int i2) {
        setMinParticleCount(i);
        setMaxParticleCount(i2);
    }

    public void set(Emitter emitter) {
        this.minParticleCount = emitter.minParticleCount;
        this.maxParticleCount = emitter.maxParticleCount;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.Serializable
    public void write(Json json) {
        json.writeValue("minParticleCount", Integer.valueOf(this.minParticleCount));
        json.writeValue("maxParticleCount", Integer.valueOf(this.maxParticleCount));
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.Serializable
    public void read(Json json, JsonValue jsonValue) {
        this.minParticleCount = ((Integer) json.readValue("minParticleCount", Integer.TYPE, jsonValue)).intValue();
        this.maxParticleCount = ((Integer) json.readValue("maxParticleCount", Integer.TYPE, jsonValue)).intValue();
    }
}
