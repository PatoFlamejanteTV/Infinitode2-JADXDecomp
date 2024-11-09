package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/values/SpawnShapeValue.class */
public abstract class SpawnShapeValue extends ParticleValue implements ResourceData.Configurable, Json.Serializable {
    public RangedNumericValue xOffsetValue;
    public RangedNumericValue yOffsetValue;
    public RangedNumericValue zOffsetValue;

    public abstract void spawnAux(Vector3 vector3, float f);

    public abstract SpawnShapeValue copy();

    public SpawnShapeValue() {
        this.xOffsetValue = new RangedNumericValue();
        this.yOffsetValue = new RangedNumericValue();
        this.zOffsetValue = new RangedNumericValue();
    }

    public SpawnShapeValue(SpawnShapeValue spawnShapeValue) {
        this();
    }

    public final Vector3 spawn(Vector3 vector3, float f) {
        spawnAux(vector3, f);
        if (this.xOffsetValue.active) {
            vector3.x += this.xOffsetValue.newLowValue();
        }
        if (this.yOffsetValue.active) {
            vector3.y += this.yOffsetValue.newLowValue();
        }
        if (this.zOffsetValue.active) {
            vector3.z += this.zOffsetValue.newLowValue();
        }
        return vector3;
    }

    public void init() {
    }

    public void start() {
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue
    public void load(ParticleValue particleValue) {
        super.load(particleValue);
        SpawnShapeValue spawnShapeValue = (SpawnShapeValue) particleValue;
        this.xOffsetValue.load(spawnShapeValue.xOffsetValue);
        this.yOffsetValue.load(spawnShapeValue.yOffsetValue);
        this.zOffsetValue.load(spawnShapeValue.zOffsetValue);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue, com.badlogic.gdx.utils.Json.Serializable
    public void write(Json json) {
        super.write(json);
        json.writeValue("xOffsetValue", this.xOffsetValue);
        json.writeValue("yOffsetValue", this.yOffsetValue);
        json.writeValue("zOffsetValue", this.zOffsetValue);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue, com.badlogic.gdx.utils.Json.Serializable
    public void read(Json json, JsonValue jsonValue) {
        super.read(json, jsonValue);
        this.xOffsetValue = (RangedNumericValue) json.readValue("xOffsetValue", RangedNumericValue.class, jsonValue);
        this.yOffsetValue = (RangedNumericValue) json.readValue("yOffsetValue", RangedNumericValue.class, jsonValue);
        this.zOffsetValue = (RangedNumericValue) json.readValue("zOffsetValue", RangedNumericValue.class, jsonValue);
    }

    public void save(AssetManager assetManager, ResourceData resourceData) {
    }

    public void load(AssetManager assetManager, ResourceData resourceData) {
    }
}
