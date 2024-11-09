package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/values/CylinderSpawnShapeValue.class */
public final class CylinderSpawnShapeValue extends PrimitiveSpawnShapeValue {
    public CylinderSpawnShapeValue(CylinderSpawnShapeValue cylinderSpawnShapeValue) {
        super(cylinderSpawnShapeValue);
        load(cylinderSpawnShapeValue);
    }

    public CylinderSpawnShapeValue() {
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public final void spawnAux(Vector3 vector3, float f) {
        float random;
        float random2;
        float f2;
        float scale = this.spawnWidth + (this.spawnWidthDiff * this.spawnWidthValue.getScale(f));
        float scale2 = this.spawnHeight + (this.spawnHeightDiff * this.spawnHeightValue.getScale(f));
        float scale3 = this.spawnDepth + (this.spawnDepthDiff * this.spawnDepthValue.getScale(f));
        float random3 = MathUtils.random(scale2) - (scale2 / 2.0f);
        if (this.edges) {
            random = scale / 2.0f;
            random2 = scale3 / 2.0f;
        } else {
            random = MathUtils.random(scale) / 2.0f;
            random2 = MathUtils.random(scale3) / 2.0f;
        }
        float f3 = 0.0f;
        boolean z = random == 0.0f;
        boolean z2 = random2 == 0.0f;
        if (!z && !z2) {
            f2 = MathUtils.random(360.0f);
        } else {
            if (z) {
                f3 = MathUtils.random(1) == 0 ? -90.0f : 90.0f;
            } else if (z2) {
                f2 = MathUtils.random(1) == 0 ? 0.0f : 180.0f;
            }
            vector3.set(random * MathUtils.cosDeg(f3), random3, random2 * MathUtils.sinDeg(f3));
        }
        f3 = f2;
        vector3.set(random * MathUtils.cosDeg(f3), random3, random2 * MathUtils.sinDeg(f3));
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public final SpawnShapeValue copy() {
        return new CylinderSpawnShapeValue(this);
    }
}
