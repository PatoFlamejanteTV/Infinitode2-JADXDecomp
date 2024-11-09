package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/values/RectangleSpawnShapeValue.class */
public final class RectangleSpawnShapeValue extends PrimitiveSpawnShapeValue {
    public RectangleSpawnShapeValue(RectangleSpawnShapeValue rectangleSpawnShapeValue) {
        super(rectangleSpawnShapeValue);
        load(rectangleSpawnShapeValue);
    }

    public RectangleSpawnShapeValue() {
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public final void spawnAux(Vector3 vector3, float f) {
        float f2;
        float random;
        float random2;
        float scale = this.spawnWidth + (this.spawnWidthDiff * this.spawnWidthValue.getScale(f));
        float scale2 = this.spawnHeight + (this.spawnHeightDiff * this.spawnHeightValue.getScale(f));
        float scale3 = this.spawnDepth + (this.spawnDepthDiff * this.spawnDepthValue.getScale(f));
        if (this.edges) {
            int random3 = MathUtils.random(-1, 1);
            if (random3 == -1) {
                float f3 = MathUtils.random(1) == 0 ? (-scale) / 2.0f : scale / 2.0f;
                random = f3;
                if (f3 == 0.0f) {
                    f2 = MathUtils.random(1) == 0 ? (-scale2) / 2.0f : scale2 / 2.0f;
                    random2 = MathUtils.random(1) == 0 ? (-scale3) / 2.0f : scale3 / 2.0f;
                } else {
                    f2 = MathUtils.random(scale2) - (scale2 / 2.0f);
                    random2 = MathUtils.random(scale3) - (scale3 / 2.0f);
                }
            } else if (random3 == 0) {
                float f4 = MathUtils.random(1) == 0 ? (-scale3) / 2.0f : scale3 / 2.0f;
                random2 = f4;
                if (f4 == 0.0f) {
                    f2 = MathUtils.random(1) == 0 ? (-scale2) / 2.0f : scale2 / 2.0f;
                    random = MathUtils.random(1) == 0 ? (-scale) / 2.0f : scale / 2.0f;
                } else {
                    f2 = MathUtils.random(scale2) - (scale2 / 2.0f);
                    random = MathUtils.random(scale) - (scale / 2.0f);
                }
            } else {
                float f5 = MathUtils.random(1) == 0 ? (-scale2) / 2.0f : scale2 / 2.0f;
                f2 = f5;
                if (f5 == 0.0f) {
                    random = MathUtils.random(1) == 0 ? (-scale) / 2.0f : scale / 2.0f;
                    random2 = MathUtils.random(1) == 0 ? (-scale3) / 2.0f : scale3 / 2.0f;
                } else {
                    random = MathUtils.random(scale) - (scale / 2.0f);
                    random2 = MathUtils.random(scale3) - (scale3 / 2.0f);
                }
            }
            vector3.x = random;
            vector3.y = f2;
            vector3.z = random2;
            return;
        }
        vector3.x = MathUtils.random(scale) - (scale / 2.0f);
        vector3.y = MathUtils.random(scale2) - (scale2 / 2.0f);
        vector3.z = MathUtils.random(scale3) - (scale3 / 2.0f);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public final SpawnShapeValue copy() {
        return new RectangleSpawnShapeValue(this);
    }
}
