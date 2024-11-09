package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.math.MathUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/shapebuilders/ConeShapeBuilder.class */
public class ConeShapeBuilder extends BaseShapeBuilder {
    public static void build(MeshPartBuilder meshPartBuilder, float f, float f2, float f3, int i) {
        build(meshPartBuilder, f, f2, f3, i, 0.0f, 360.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f, float f2, float f3, int i, float f4, float f5) {
        build(meshPartBuilder, f, f2, f3, i, f4, f5, true);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f, float f2, float f3, int i, float f4, float f5, boolean z) {
        meshPartBuilder.ensureVertices(i + 2);
        meshPartBuilder.ensureTriangleIndices(i);
        float f6 = f * 0.5f;
        float f7 = f2 * 0.5f;
        float f8 = f3 * 0.5f;
        float f9 = 0.017453292f * f4;
        float f10 = (0.017453292f * (f5 - f4)) / i;
        float f11 = 1.0f / i;
        MeshPartBuilder.VertexInfo vertexInfo = vertTmp3.set(null, null, null, null);
        vertexInfo.hasNormal = true;
        vertexInfo.hasPosition = true;
        vertexInfo.hasUV = true;
        short vertex = meshPartBuilder.vertex(vertTmp4.set(null, null, null, null).setPos(0.0f, f7, 0.0f).setNor(0.0f, 1.0f, 0.0f).setUV(0.5f, 0.0f));
        short s = 0;
        for (int i2 = 0; i2 <= i; i2++) {
            float f12 = f9 + (f10 * i2);
            vertexInfo.position.set(MathUtils.cos(f12) * f6, 0.0f, MathUtils.sin(f12) * f8);
            vertexInfo.normal.set(vertexInfo.position).nor();
            vertexInfo.position.y = -f7;
            vertexInfo.uv.set(1.0f - (f11 * i2), 1.0f);
            short vertex2 = meshPartBuilder.vertex(vertexInfo);
            if (i2 != 0) {
                meshPartBuilder.triangle(vertex, vertex2, s);
            }
            s = vertex2;
        }
        if (z) {
            EllipseShapeBuilder.build(meshPartBuilder, f, f3, 0.0f, 0.0f, i, 0.0f, -f7, 0.0f, 0.0f, -1.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 180.0f - f5, 180.0f - f4);
        }
    }
}
