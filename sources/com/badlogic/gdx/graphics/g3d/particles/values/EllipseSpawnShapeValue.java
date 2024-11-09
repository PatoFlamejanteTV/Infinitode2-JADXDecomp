package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.graphics.g3d.particles.values.PrimitiveSpawnShapeValue;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/values/EllipseSpawnShapeValue.class */
public final class EllipseSpawnShapeValue extends PrimitiveSpawnShapeValue {
    PrimitiveSpawnShapeValue.SpawnSide side;

    public EllipseSpawnShapeValue(EllipseSpawnShapeValue ellipseSpawnShapeValue) {
        super(ellipseSpawnShapeValue);
        this.side = PrimitiveSpawnShapeValue.SpawnSide.both;
        load(ellipseSpawnShapeValue);
    }

    public EllipseSpawnShapeValue() {
        this.side = PrimitiveSpawnShapeValue.SpawnSide.both;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public final void spawnAux(Vector3 vector3, float f) {
        float random;
        float random2;
        float random3;
        float scale = this.spawnWidth + (this.spawnWidthDiff * this.spawnWidthValue.getScale(f));
        float scale2 = this.spawnHeight + (this.spawnHeightDiff * this.spawnHeightValue.getScale(f));
        float scale3 = this.spawnDepth + (this.spawnDepthDiff * this.spawnDepthValue.getScale(f));
        float f2 = 6.2831855f;
        if (this.side == PrimitiveSpawnShapeValue.SpawnSide.top) {
            f2 = 3.1415927f;
        } else if (this.side == PrimitiveSpawnShapeValue.SpawnSide.bottom) {
            f2 = -3.1415927f;
        }
        float random4 = MathUtils.random(0.0f, f2);
        if (!this.edges) {
            random = MathUtils.random(scale / 2.0f);
            random2 = MathUtils.random(scale2 / 2.0f);
            random3 = MathUtils.random(scale3 / 2.0f);
        } else {
            if (scale == 0.0f) {
                vector3.set(0.0f, (scale2 / 2.0f) * MathUtils.sin(random4), (scale3 / 2.0f) * MathUtils.cos(random4));
                return;
            }
            if (scale2 == 0.0f) {
                vector3.set((scale / 2.0f) * MathUtils.cos(random4), 0.0f, (scale3 / 2.0f) * MathUtils.sin(random4));
                return;
            } else if (scale3 == 0.0f) {
                vector3.set((scale / 2.0f) * MathUtils.cos(random4), (scale2 / 2.0f) * MathUtils.sin(random4), 0.0f);
                return;
            } else {
                random = scale / 2.0f;
                random2 = scale2 / 2.0f;
                random3 = scale3 / 2.0f;
            }
        }
        float random5 = MathUtils.random(-1.0f, 1.0f);
        float sqrt = (float) Math.sqrt(1.0f - (random5 * random5));
        vector3.set(random * sqrt * MathUtils.cos(random4), random2 * sqrt * MathUtils.sin(random4), random3 * random5);
    }

    public final PrimitiveSpawnShapeValue.SpawnSide getSide() {
        return this.side;
    }

    public final void setSide(PrimitiveSpawnShapeValue.SpawnSide spawnSide) {
        this.side = spawnSide;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.PrimitiveSpawnShapeValue, com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue, com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue
    public final void load(ParticleValue particleValue) {
        super.load(particleValue);
        this.side = ((EllipseSpawnShapeValue) particleValue).side;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public final SpawnShapeValue copy() {
        return new EllipseSpawnShapeValue(this);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.PrimitiveSpawnShapeValue, com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue, com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue, com.badlogic.gdx.utils.Json.Serializable
    public final void write(Json json) {
        super.write(json);
        json.writeValue("side", this.side);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.PrimitiveSpawnShapeValue, com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue, com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue, com.badlogic.gdx.utils.Json.Serializable
    public final void read(Json json, JsonValue jsonValue) {
        super.read(json, jsonValue);
        this.side = (PrimitiveSpawnShapeValue.SpawnSide) json.readValue("side", PrimitiveSpawnShapeValue.SpawnSide.class, jsonValue);
    }
}
