package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBVertexAttrib64Bit.class */
public class ARBVertexAttrib64Bit {
    public static final int GL_DOUBLE_VEC2 = 36860;
    public static final int GL_DOUBLE_VEC3 = 36861;
    public static final int GL_DOUBLE_VEC4 = 36862;
    public static final int GL_DOUBLE_MAT2 = 36678;
    public static final int GL_DOUBLE_MAT3 = 36679;
    public static final int GL_DOUBLE_MAT4 = 36680;
    public static final int GL_DOUBLE_MAT2x3 = 36681;
    public static final int GL_DOUBLE_MAT2x4 = 36682;
    public static final int GL_DOUBLE_MAT3x2 = 36683;
    public static final int GL_DOUBLE_MAT3x4 = 36684;
    public static final int GL_DOUBLE_MAT4x2 = 36685;
    public static final int GL_DOUBLE_MAT4x3 = 36686;

    public static native void glVertexArrayVertexAttribLOffsetEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4, @NativeType("GLenum") int i5, @NativeType("GLsizei") int i6, @NativeType("GLintptr") long j);

    static {
        GL.initialize();
    }

    protected ARBVertexAttrib64Bit() {
        throw new UnsupportedOperationException();
    }

    public static void glVertexAttribL1d(@NativeType("GLuint") int i, @NativeType("GLdouble") double d) {
        GL41C.glVertexAttribL1d(i, d);
    }

    public static void glVertexAttribL2d(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2) {
        GL41C.glVertexAttribL2d(i, d, d2);
    }

    public static void glVertexAttribL3d(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3) {
        GL41C.glVertexAttribL3d(i, d, d2, d3);
    }

    public static void glVertexAttribL4d(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4) {
        GL41C.glVertexAttribL4d(i, d, d2, d3, d4);
    }

    public static void nglVertexAttribL1dv(int i, long j) {
        GL41C.nglVertexAttribL1dv(i, j);
    }

    public static void glVertexAttribL1dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL41C.glVertexAttribL1dv(i, doubleBuffer);
    }

    public static void nglVertexAttribL2dv(int i, long j) {
        GL41C.nglVertexAttribL2dv(i, j);
    }

    public static void glVertexAttribL2dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL41C.glVertexAttribL2dv(i, doubleBuffer);
    }

    public static void nglVertexAttribL3dv(int i, long j) {
        GL41C.nglVertexAttribL3dv(i, j);
    }

    public static void glVertexAttribL3dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL41C.glVertexAttribL3dv(i, doubleBuffer);
    }

    public static void nglVertexAttribL4dv(int i, long j) {
        GL41C.nglVertexAttribL4dv(i, j);
    }

    public static void glVertexAttribL4dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL41C.glVertexAttribL4dv(i, doubleBuffer);
    }

    public static void nglVertexAttribLPointer(int i, int i2, int i3, int i4, long j) {
        GL41C.nglVertexAttribLPointer(i, i2, i3, i4, j);
    }

    public static void glVertexAttribLPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL41C.glVertexAttribLPointer(i, i2, i3, i4, byteBuffer);
    }

    public static void glVertexAttribLPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") long j) {
        GL41C.glVertexAttribLPointer(i, i2, i3, i4, j);
    }

    public static void glVertexAttribLPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        GL41C.glVertexAttribLPointer(i, i2, i3, doubleBuffer);
    }

    public static void nglGetVertexAttribLdv(int i, int i2, long j) {
        GL41C.nglGetVertexAttribLdv(i, i2, j);
    }

    public static void glGetVertexAttribLdv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        GL41C.glGetVertexAttribLdv(i, i2, doubleBuffer);
    }

    public static void glVertexAttribL1dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        GL41C.glVertexAttribL1dv(i, dArr);
    }

    public static void glVertexAttribL2dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        GL41C.glVertexAttribL2dv(i, dArr);
    }

    public static void glVertexAttribL3dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        GL41C.glVertexAttribL3dv(i, dArr);
    }

    public static void glVertexAttribL4dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        GL41C.glVertexAttribL4dv(i, dArr);
    }

    public static void glGetVertexAttribLdv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble *") double[] dArr) {
        GL41C.glGetVertexAttribLdv(i, i2, dArr);
    }
}
