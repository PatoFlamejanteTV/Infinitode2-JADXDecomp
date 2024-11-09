package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.utils.GdxRuntimeException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/shapebuilders/CapsuleShapeBuilder.class */
public class CapsuleShapeBuilder extends BaseShapeBuilder {
    public static void build(MeshPartBuilder meshPartBuilder, float f, float f2, int i) {
        if (f2 < f * 2.0f) {
            throw new GdxRuntimeException("Height must be at least twice the radius");
        }
        float f3 = f * 2.0f;
        CylinderShapeBuilder.build(meshPartBuilder, f3, f2 - f3, f3, i, 0.0f, 360.0f, false);
        SphereShapeBuilder.build(meshPartBuilder, matTmp1.setToTranslation(0.0f, 0.5f * (f2 - f3), 0.0f), f3, f3, f3, i, i, 0.0f, 360.0f, 0.0f, 90.0f);
        SphereShapeBuilder.build(meshPartBuilder, matTmp1.setToTranslation(0.0f, (-0.5f) * (f2 - f3), 0.0f), f3, f3, f3, i, i, 0.0f, 360.0f, 90.0f, 180.0f);
    }
}
