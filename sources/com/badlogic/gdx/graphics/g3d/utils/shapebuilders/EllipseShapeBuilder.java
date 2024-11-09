package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.GdxRuntimeException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/shapebuilders/EllipseShapeBuilder.class */
public class EllipseShapeBuilder extends BaseShapeBuilder {
    public static void build(MeshPartBuilder meshPartBuilder, float f, int i, float f2, float f3, float f4, float f5, float f6, float f7) {
        build(meshPartBuilder, f, i, f2, f3, f4, f5, f6, f7, 0.0f, 360.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f, int i, Vector3 vector3, Vector3 vector32) {
        build(meshPartBuilder, f, i, vector3.x, vector3.y, vector3.z, vector32.x, vector32.y, vector32.z);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f, int i, Vector3 vector3, Vector3 vector32, Vector3 vector33, Vector3 vector34) {
        build(meshPartBuilder, f, i, vector3.x, vector3.y, vector3.z, vector32.x, vector32.y, vector32.z, vector33.x, vector33.y, vector33.z, vector34.x, vector34.y, vector34.z);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f, int i, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13) {
        build(meshPartBuilder, f, i, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, 0.0f, 360.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f, int i, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        build(meshPartBuilder, f * 2.0f, f * 2.0f, i, f2, f3, f4, f5, f6, f7, f8, f9);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f, int i, Vector3 vector3, Vector3 vector32, float f2, float f3) {
        build(meshPartBuilder, f, i, vector3.x, vector3.y, vector3.z, vector32.x, vector32.y, vector32.z, f2, f3);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f, int i, Vector3 vector3, Vector3 vector32, Vector3 vector33, Vector3 vector34, float f2, float f3) {
        build(meshPartBuilder, f, i, vector3.x, vector3.y, vector3.z, vector32.x, vector32.y, vector32.z, vector33.x, vector33.y, vector33.z, vector34.x, vector34.y, vector34.z, f2, f3);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f, int i, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15) {
        build(meshPartBuilder, f * 2.0f, f * 2.0f, 0.0f, 0.0f, i, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f, float f2, int i, float f3, float f4, float f5, float f6, float f7, float f8) {
        build(meshPartBuilder, f, f2, i, f3, f4, f5, f6, f7, f8, 0.0f, 360.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f, float f2, int i, Vector3 vector3, Vector3 vector32) {
        build(meshPartBuilder, f, f2, i, vector3.x, vector3.y, vector3.z, vector32.x, vector32.y, vector32.z);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f, float f2, int i, Vector3 vector3, Vector3 vector32, Vector3 vector33, Vector3 vector34) {
        build(meshPartBuilder, f, f2, i, vector3.x, vector3.y, vector3.z, vector32.x, vector32.y, vector32.z, vector33.x, vector33.y, vector33.z, vector34.x, vector34.y, vector34.z);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f, float f2, int i, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14) {
        build(meshPartBuilder, f, f2, i, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, 0.0f, 360.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f, float f2, int i, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        build(meshPartBuilder, f, f2, 0.0f, 0.0f, i, f3, f4, f5, f6, f7, f8, f9, f10);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f, float f2, int i, Vector3 vector3, Vector3 vector32, float f3, float f4) {
        build(meshPartBuilder, f, f2, 0.0f, 0.0f, i, vector3.x, vector3.y, vector3.z, vector32.x, vector32.y, vector32.z, f3, f4);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f, float f2, int i, Vector3 vector3, Vector3 vector32, Vector3 vector33, Vector3 vector34, float f3, float f4) {
        build(meshPartBuilder, f, f2, 0.0f, 0.0f, i, vector3.x, vector3.y, vector3.z, vector32.x, vector32.y, vector32.z, vector33.x, vector33.y, vector33.z, vector34.x, vector34.y, vector34.z, f3, f4);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f, float f2, int i, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) {
        build(meshPartBuilder, f, f2, 0.0f, 0.0f, i, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f, float f2, float f3, float f4, int i, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12) {
        tmpV1.set(f8, f9, f10).crs(0.0f, 0.0f, 1.0f);
        tmpV2.set(f8, f9, f10).crs(0.0f, 1.0f, 0.0f);
        if (tmpV2.len2() > tmpV1.len2()) {
            tmpV1.set(tmpV2);
        }
        tmpV2.set(tmpV1.nor()).crs(f8, f9, f10).nor();
        build(meshPartBuilder, f, f2, f3, f4, i, f5, f6, f7, f8, f9, f10, tmpV1.x, tmpV1.y, tmpV1.z, tmpV2.x, tmpV2.y, tmpV2.z, f11, f12);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f, float f2, float f3, float f4, int i, float f5, float f6, float f7, float f8, float f9, float f10) {
        build(meshPartBuilder, f, f2, f3, f4, i, f5, f6, f7, f8, f9, f10, 0.0f, 360.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f, float f2, float f3, float f4, int i, Vector3 vector3, Vector3 vector32) {
        build(meshPartBuilder, f, f2, f3, f4, i, vector3.x, vector3.y, vector3.z, vector32.x, vector32.y, vector32.z, 0.0f, 360.0f);
    }

    public static void build(MeshPartBuilder meshPartBuilder, float f, float f2, float f3, float f4, int i, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18) {
        if (f3 <= 0.0f || f4 <= 0.0f) {
            meshPartBuilder.ensureVertices(i + 2);
            meshPartBuilder.ensureTriangleIndices(i);
        } else if (f3 == f && f4 == f2) {
            meshPartBuilder.ensureVertices(i + 1);
            meshPartBuilder.ensureIndices(i + 1);
            if (meshPartBuilder.getPrimitiveType() != 1) {
                throw new GdxRuntimeException("Incorrect primitive type : expect GL_LINES because innerWidth == width && innerHeight == height");
            }
        } else {
            meshPartBuilder.ensureVertices((i + 1) << 1);
            meshPartBuilder.ensureRectangleIndices(i + 1);
        }
        float f19 = 0.017453292f * f17;
        float f20 = (0.017453292f * (f18 - f17)) / i;
        Vector3 scl = tmpV1.set(f11, f12, f13).scl(f * 0.5f);
        Vector3 scl2 = tmpV2.set(f14, f15, f16).scl(f2 * 0.5f);
        Vector3 scl3 = tmpV3.set(f11, f12, f13).scl(f3 * 0.5f);
        Vector3 scl4 = tmpV4.set(f14, f15, f16).scl(f4 * 0.5f);
        MeshPartBuilder.VertexInfo vertexInfo = vertTmp3.set(null, null, null, null);
        vertexInfo.hasNormal = true;
        vertexInfo.hasPosition = true;
        vertexInfo.hasUV = true;
        vertexInfo.uv.set(0.5f, 0.5f);
        vertexInfo.position.set(f5, f6, f7);
        vertexInfo.normal.set(f8, f9, f10);
        MeshPartBuilder.VertexInfo vertexInfo2 = vertTmp4.set(null, null, null, null);
        vertexInfo2.hasNormal = true;
        vertexInfo2.hasPosition = true;
        vertexInfo2.hasUV = true;
        vertexInfo2.uv.set(0.5f, 0.5f);
        vertexInfo2.position.set(f5, f6, f7);
        vertexInfo2.normal.set(f8, f9, f10);
        short vertex = meshPartBuilder.vertex(vertexInfo2);
        float f21 = 0.5f * (f3 / f);
        float f22 = 0.5f * (f4 / f2);
        short s = 0;
        short s2 = 0;
        short s3 = 0;
        for (int i2 = 0; i2 <= i; i2++) {
            float f23 = f19 + (f20 * i2);
            float cos = MathUtils.cos(f23);
            float sin = MathUtils.sin(f23);
            vertexInfo2.position.set(f5, f6, f7).add((scl.x * cos) + (scl2.x * sin), (scl.y * cos) + (scl2.y * sin), (scl.z * cos) + (scl2.z * sin));
            vertexInfo2.uv.set(0.5f + (0.5f * cos), 0.5f + (0.5f * sin));
            short vertex2 = meshPartBuilder.vertex(vertexInfo2);
            if (f3 <= 0.0f || f4 <= 0.0f) {
                if (i2 != 0) {
                    meshPartBuilder.triangle(vertex2, s, vertex);
                }
                s = vertex2;
            } else if (f3 == f && f4 == f2) {
                if (i2 != 0) {
                    meshPartBuilder.line(vertex2, s);
                }
                s = vertex2;
            } else {
                vertexInfo.position.set(f5, f6, f7).add((scl3.x * cos) + (scl4.x * sin), (scl3.y * cos) + (scl4.y * sin), (scl3.z * cos) + (scl4.z * sin));
                vertexInfo.uv.set(0.5f + (f21 * cos), 0.5f + (f22 * sin));
                s = vertex2;
                short vertex3 = meshPartBuilder.vertex(vertexInfo);
                if (i2 != 0) {
                    meshPartBuilder.rect(vertex3, s, s3, s2);
                }
                s3 = s;
                s2 = vertex3;
            }
        }
    }
}
