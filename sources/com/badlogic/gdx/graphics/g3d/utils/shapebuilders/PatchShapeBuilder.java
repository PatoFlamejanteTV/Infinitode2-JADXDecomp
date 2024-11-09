package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.GdxRuntimeException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/shapebuilders/PatchShapeBuilder.class */
public class PatchShapeBuilder extends BaseShapeBuilder {
    public static void build(MeshPartBuilder meshPartBuilder, MeshPartBuilder.VertexInfo vertexInfo, MeshPartBuilder.VertexInfo vertexInfo2, MeshPartBuilder.VertexInfo vertexInfo3, MeshPartBuilder.VertexInfo vertexInfo4, int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            throw new GdxRuntimeException("divisionsU and divisionV must be > 0, u,v: " + i + ", " + i2);
        }
        meshPartBuilder.ensureVertices((i2 + 1) * (i + 1));
        meshPartBuilder.ensureRectangleIndices(i2 * i);
        for (int i3 = 0; i3 <= i; i3++) {
            float f = i3 / i;
            vertTmp5.set(vertexInfo).lerp(vertexInfo2, f);
            vertTmp6.set(vertexInfo4).lerp(vertexInfo3, f);
            for (int i4 = 0; i4 <= i2; i4++) {
                short vertex = meshPartBuilder.vertex(vertTmp7.set(vertTmp5).lerp(vertTmp6, i4 / i2));
                if (i3 > 0 && i4 > 0) {
                    meshPartBuilder.rect((short) ((vertex - i2) - 2), (short) (vertex - 1), vertex, (short) ((vertex - i2) - 1));
                }
            }
        }
    }

    public static void build(MeshPartBuilder meshPartBuilder, Vector3 vector3, Vector3 vector32, Vector3 vector33, Vector3 vector34, Vector3 vector35, int i, int i2) {
        build(meshPartBuilder, vertTmp1.set(vector3, vector35, null, null).setUV(0.0f, 1.0f), vertTmp2.set(vector32, vector35, null, null).setUV(1.0f, 1.0f), vertTmp3.set(vector33, vector35, null, null).setUV(1.0f, 0.0f), vertTmp4.set(vector34, vector35, null, null).setUV(0.0f, 0.0f), i, i2);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, int i, int i2) {
        build(meshPartBuilder, vertTmp1.set(null).setPos(f, f2, f3).setNor(f13, f14, f15).setUV(0.0f, 1.0f), vertTmp2.set(null).setPos(f4, f5, f6).setNor(f13, f14, f15).setUV(1.0f, 1.0f), vertTmp3.set(null).setPos(f7, f8, f9).setNor(f13, f14, f15).setUV(1.0f, 0.0f), vertTmp4.set(null).setPos(f10, f11, f12).setNor(f13, f14, f15).setUV(0.0f, 0.0f), i, i2);
    }
}
