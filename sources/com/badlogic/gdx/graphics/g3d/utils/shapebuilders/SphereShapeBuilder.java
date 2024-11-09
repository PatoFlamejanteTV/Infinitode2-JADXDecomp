package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.ShortArray;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/shapebuilders/SphereShapeBuilder.class */
public class SphereShapeBuilder extends BaseShapeBuilder {
    private static final ShortArray tmpIndices = new ShortArray();
    private static final Matrix3 normalTransform = new Matrix3();

    public static void build(MeshPartBuilder meshPartBuilder, float f, float f2, float f3, int i, int i2) {
        build(meshPartBuilder, f, f2, f3, i, i2, 0.0f, 360.0f, 0.0f, 180.0f);
    }

    @Deprecated
    public static void build(MeshPartBuilder meshPartBuilder, Matrix4 matrix4, float f, float f2, float f3, int i, int i2) {
        build(meshPartBuilder, matrix4, f, f2, f3, i, i2, 0.0f, 360.0f, 0.0f, 180.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f, float f2, float f3, int i, int i2, float f4, float f5, float f6, float f7) {
        build(meshPartBuilder, matTmp1.idt(), f, f2, f3, i, i2, f4, f5, f6, f7);
    }

    @Deprecated
    public static void build(MeshPartBuilder meshPartBuilder, Matrix4 matrix4, float f, float f2, float f3, int i, int i2, float f4, float f5, float f6, float f7) {
        float f8;
        boolean isEqual = MathUtils.isEqual(f6, 0.0f);
        boolean isEqual2 = MathUtils.isEqual(f7, 180.0f);
        float f9 = f * 0.5f;
        float f10 = f2 * 0.5f;
        float f11 = f3 * 0.5f;
        float f12 = 0.017453292f * f4;
        float f13 = (0.017453292f * (f5 - f4)) / i;
        float f14 = 0.017453292f * f6;
        float f15 = (0.017453292f * (f7 - f6)) / i2;
        float f16 = 1.0f / i;
        float f17 = 1.0f / i2;
        MeshPartBuilder.VertexInfo vertexInfo = vertTmp3.set(null, null, null, null);
        vertexInfo.hasNormal = true;
        vertexInfo.hasPosition = true;
        vertexInfo.hasUV = true;
        normalTransform.set(matrix4);
        int i3 = i + 3;
        tmpIndices.clear();
        tmpIndices.ensureCapacity(i << 1);
        tmpIndices.size = i3;
        int i4 = 0;
        meshPartBuilder.ensureVertices((i2 + 1) * (i + 1));
        meshPartBuilder.ensureRectangleIndices(i);
        for (int i5 = 0; i5 <= i2; i5++) {
            float f18 = f14 + (f15 * i5);
            float f19 = f17 * i5;
            float sin = MathUtils.sin(f18);
            float cos = MathUtils.cos(f18) * f10;
            for (int i6 = 0; i6 <= i; i6++) {
                float f20 = f12 + (f13 * i6);
                if ((i5 == 0 && isEqual) || (i5 == i2 && isEqual2)) {
                    f8 = 1.0f - (f16 * (i6 - 0.5f));
                } else {
                    f8 = 1.0f - (f16 * i6);
                }
                vertexInfo.position.set(MathUtils.cos(f20) * f9 * sin, cos, MathUtils.sin(f20) * f11 * sin);
                vertexInfo.normal.set(vertexInfo.position).mul(normalTransform).nor();
                vertexInfo.position.mul(matrix4);
                vertexInfo.uv.set(f8, f19);
                tmpIndices.set(i4, meshPartBuilder.vertex(vertexInfo));
                int i7 = i4 + i3;
                if (i5 > 0 && i6 > 0) {
                    if (i5 == 1 && isEqual) {
                        meshPartBuilder.triangle(tmpIndices.get(i4), tmpIndices.get((i7 - 1) % i3), tmpIndices.get((i7 - (i + 1)) % i3));
                    } else if (i5 == i2 && isEqual2) {
                        meshPartBuilder.triangle(tmpIndices.get(i4), tmpIndices.get((i7 - (i + 2)) % i3), tmpIndices.get((i7 - (i + 1)) % i3));
                    } else {
                        meshPartBuilder.rect(tmpIndices.get(i4), tmpIndices.get((i7 - 1) % i3), tmpIndices.get((i7 - (i + 2)) % i3), tmpIndices.get((i7 - (i + 1)) % i3));
                    }
                }
                i4 = (i4 + 1) % tmpIndices.size;
            }
        }
    }
}
