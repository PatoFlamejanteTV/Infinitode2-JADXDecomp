package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/values/ParticleValue.class */
public class ParticleValue implements Json.Serializable {
    public boolean active;

    public ParticleValue() {
    }

    public ParticleValue(ParticleValue particleValue) {
        this.active = particleValue.active;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean z) {
        this.active = z;
    }

    public void load(ParticleValue particleValue) {
        this.active = particleValue.active;
    }

    @Override // com.badlogic.gdx.utils.Json.Serializable
    public void write(Json json) {
        json.writeValue("active", Boolean.valueOf(this.active));
    }

    @Override // com.badlogic.gdx.utils.Json.Serializable
    public void read(Json json, JsonValue jsonValue) {
        this.active = ((Boolean) json.readValue("active", Boolean.class, jsonValue)).booleanValue();
    }
}
