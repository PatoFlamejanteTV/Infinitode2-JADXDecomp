package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/MeshPartBuilder.class */
public interface MeshPartBuilder {
    MeshPart getMeshPart();

    int getPrimitiveType();

    VertexAttributes getAttributes();

    void setColor(Color color);

    void setColor(float f, float f2, float f3, float f4);

    void setUVRange(float f, float f2, float f3, float f4);

    void setUVRange(TextureRegion textureRegion);

    Matrix4 getVertexTransform(Matrix4 matrix4);

    void setVertexTransform(Matrix4 matrix4);

    boolean isVertexTransformationEnabled();

    void setVertexTransformationEnabled(boolean z);

    void ensureVertices(int i);

    void ensureIndices(int i);

    void ensureCapacity(int i, int i2);

    void ensureTriangleIndices(int i);

    void ensureRectangleIndices(int i);

    short vertex(float... fArr);

    short vertex(Vector3 vector3, Vector3 vector32, Color color, Vector2 vector2);

    short vertex(VertexInfo vertexInfo);

    int lastIndex();

    void index(short s);

    void index(short s, short s2);

    void index(short s, short s2, short s3);

    void index(short s, short s2, short s3, short s4);

    void index(short s, short s2, short s3, short s4, short s5, short s6);

    void index(short s, short s2, short s3, short s4, short s5, short s6, short s7, short s8);

    void line(short s, short s2);

    void line(VertexInfo vertexInfo, VertexInfo vertexInfo2);

    void line(Vector3 vector3, Vector3 vector32);

    void line(float f, float f2, float f3, float f4, float f5, float f6);

    void line(Vector3 vector3, Color color, Vector3 vector32, Color color2);

    void triangle(short s, short s2, short s3);

    void triangle(VertexInfo vertexInfo, VertexInfo vertexInfo2, VertexInfo vertexInfo3);

    void triangle(Vector3 vector3, Vector3 vector32, Vector3 vector33);

    void triangle(Vector3 vector3, Color color, Vector3 vector32, Color color2, Vector3 vector33, Color color3);

    void rect(short s, short s2, short s3, short s4);

    void rect(VertexInfo vertexInfo, VertexInfo vertexInfo2, VertexInfo vertexInfo3, VertexInfo vertexInfo4);

    void rect(Vector3 vector3, Vector3 vector32, Vector3 vector33, Vector3 vector34, Vector3 vector35);

    void rect(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15);

    void addMesh(Mesh mesh);

    void addMesh(MeshPart meshPart);

    void addMesh(Mesh mesh, int i, int i2);

    void addMesh(float[] fArr, short[] sArr);

    void addMesh(float[] fArr, short[] sArr, int i, int i2);

    @Deprecated
    void patch(VertexInfo vertexInfo, VertexInfo vertexInfo2, VertexInfo vertexInfo3, VertexInfo vertexInfo4, int i, int i2);

    @Deprecated
    void patch(Vector3 vector3, Vector3 vector32, Vector3 vector33, Vector3 vector34, Vector3 vector35, int i, int i2);

    @Deprecated
    void patch(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, int i, int i2);

    @Deprecated
    void box(VertexInfo vertexInfo, VertexInfo vertexInfo2, VertexInfo vertexInfo3, VertexInfo vertexInfo4, VertexInfo vertexInfo5, VertexInfo vertexInfo6, VertexInfo vertexInfo7, VertexInfo vertexInfo8);

    @Deprecated
    void box(Vector3 vector3, Vector3 vector32, Vector3 vector33, Vector3 vector34, Vector3 vector35, Vector3 vector36, Vector3 vector37, Vector3 vector38);

    @Deprecated
    void box(Matrix4 matrix4);

    @Deprecated
    void box(float f, float f2, float f3);

    @Deprecated
    void box(float f, float f2, float f3, float f4, float f5, float f6);

    @Deprecated
    void circle(float f, int i, float f2, float f3, float f4, float f5, float f6, float f7);

    @Deprecated
    void circle(float f, int i, Vector3 vector3, Vector3 vector32);

    @Deprecated
    void circle(float f, int i, Vector3 vector3, Vector3 vector32, Vector3 vector33, Vector3 vector34);

    @Deprecated
    void circle(float f, int i, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13);

    @Deprecated
    void circle(float f, int i, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9);

    @Deprecated
    void circle(float f, int i, Vector3 vector3, Vector3 vector32, float f2, float f3);

    @Deprecated
    void circle(float f, int i, Vector3 vector3, Vector3 vector32, Vector3 vector33, Vector3 vector34, float f2, float f3);

    @Deprecated
    void circle(float f, int i, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15);

    @Deprecated
    void ellipse(float f, float f2, int i, float f3, float f4, float f5, float f6, float f7, float f8);

    @Deprecated
    void ellipse(float f, float f2, int i, Vector3 vector3, Vector3 vector32);

    @Deprecated
    void ellipse(float f, float f2, int i, Vector3 vector3, Vector3 vector32, Vector3 vector33, Vector3 vector34);

    @Deprecated
    void ellipse(float f, float f2, int i, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14);

    @Deprecated
    void ellipse(float f, float f2, int i, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10);

    @Deprecated
    void ellipse(float f, float f2, int i, Vector3 vector3, Vector3 vector32, float f3, float f4);

    @Deprecated
    void ellipse(float f, float f2, int i, Vector3 vector3, Vector3 vector32, Vector3 vector33, Vector3 vector34, float f3, float f4);

    @Deprecated
    void ellipse(float f, float f2, int i, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16);

    @Deprecated
    void ellipse(float f, float f2, float f3, float f4, int i, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18);

    @Deprecated
    void ellipse(float f, float f2, float f3, float f4, int i, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12);

    @Deprecated
    void ellipse(float f, float f2, float f3, float f4, int i, float f5, float f6, float f7, float f8, float f9, float f10);

    @Deprecated
    void ellipse(float f, float f2, float f3, float f4, int i, Vector3 vector3, Vector3 vector32);

    @Deprecated
    void cylinder(float f, float f2, float f3, int i);

    @Deprecated
    void cylinder(float f, float f2, float f3, int i, float f4, float f5);

    @Deprecated
    void cylinder(float f, float f2, float f3, int i, float f4, float f5, boolean z);

    @Deprecated
    void cone(float f, float f2, float f3, int i);

    @Deprecated
    void cone(float f, float f2, float f3, int i, float f4, float f5);

    @Deprecated
    void sphere(float f, float f2, float f3, int i, int i2);

    @Deprecated
    void sphere(Matrix4 matrix4, float f, float f2, float f3, int i, int i2);

    @Deprecated
    void sphere(float f, float f2, float f3, int i, int i2, float f4, float f5, float f6, float f7);

    @Deprecated
    void sphere(Matrix4 matrix4, float f, float f2, float f3, int i, int i2, float f4, float f5, float f6, float f7);

    @Deprecated
    void capsule(float f, float f2, int i);

    @Deprecated
    void arrow(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int i);

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/MeshPartBuilder$VertexInfo.class */
    public static class VertexInfo implements Pool.Poolable {
        public boolean hasPosition;
        public boolean hasNormal;
        public boolean hasColor;
        public boolean hasUV;
        public final Vector3 position = new Vector3();
        public final Vector3 normal = new Vector3(0.0f, 1.0f, 0.0f);
        public final Color color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        public final Vector2 uv = new Vector2();

        @Override // com.badlogic.gdx.utils.Pool.Poolable
        public void reset() {
            this.position.set(0.0f, 0.0f, 0.0f);
            this.normal.set(0.0f, 1.0f, 0.0f);
            this.color.set(1.0f, 1.0f, 1.0f, 1.0f);
            this.uv.set(0.0f, 0.0f);
        }

        public VertexInfo set(Vector3 vector3, Vector3 vector32, Color color, Vector2 vector2) {
            reset();
            this.hasPosition = vector3 != null;
            if (this.hasPosition) {
                this.position.set(vector3);
            }
            this.hasNormal = vector32 != null;
            if (this.hasNormal) {
                this.normal.set(vector32);
            }
            this.hasColor = color != null;
            if (this.hasColor) {
                this.color.set(color);
            }
            this.hasUV = vector2 != null;
            if (this.hasUV) {
                this.uv.set(vector2);
            }
            return this;
        }

        public VertexInfo set(VertexInfo vertexInfo) {
            if (vertexInfo == null) {
                return set(null, null, null, null);
            }
            this.hasPosition = vertexInfo.hasPosition;
            this.position.set(vertexInfo.position);
            this.hasNormal = vertexInfo.hasNormal;
            this.normal.set(vertexInfo.normal);
            this.hasColor = vertexInfo.hasColor;
            this.color.set(vertexInfo.color);
            this.hasUV = vertexInfo.hasUV;
            this.uv.set(vertexInfo.uv);
            return this;
        }

        public VertexInfo setPos(float f, float f2, float f3) {
            this.position.set(f, f2, f3);
            this.hasPosition = true;
            return this;
        }

        public VertexInfo setPos(Vector3 vector3) {
            this.hasPosition = vector3 != null;
            if (this.hasPosition) {
                this.position.set(vector3);
            }
            return this;
        }

        public VertexInfo setNor(float f, float f2, float f3) {
            this.normal.set(f, f2, f3);
            this.hasNormal = true;
            return this;
        }

        public VertexInfo setNor(Vector3 vector3) {
            this.hasNormal = vector3 != null;
            if (this.hasNormal) {
                this.normal.set(vector3);
            }
            return this;
        }

        public VertexInfo setCol(float f, float f2, float f3, float f4) {
            this.color.set(f, f2, f3, f4);
            this.hasColor = true;
            return this;
        }

        public VertexInfo setCol(Color color) {
            this.hasColor = color != null;
            if (this.hasColor) {
                this.color.set(color);
            }
            return this;
        }

        public VertexInfo setUV(float f, float f2) {
            this.uv.set(f, f2);
            this.hasUV = true;
            return this;
        }

        public VertexInfo setUV(Vector2 vector2) {
            this.hasUV = vector2 != null;
            if (this.hasUV) {
                this.uv.set(vector2);
            }
            return this;
        }

        public VertexInfo lerp(VertexInfo vertexInfo, float f) {
            if (this.hasPosition && vertexInfo.hasPosition) {
                this.position.lerp(vertexInfo.position, f);
            }
            if (this.hasNormal && vertexInfo.hasNormal) {
                this.normal.lerp(vertexInfo.normal, f);
            }
            if (this.hasColor && vertexInfo.hasColor) {
                this.color.lerp(vertexInfo.color, f);
            }
            if (this.hasUV && vertexInfo.hasUV) {
                this.uv.lerp(vertexInfo.uv, f);
            }
            return this;
        }
    }
}
