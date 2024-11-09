package org.lwjgl.opengl;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBViewportArray.class */
public class ARBViewportArray {
    public static final int GL_MAX_VIEWPORTS = 33371;
    public static final int GL_VIEWPORT_SUBPIXEL_BITS = 33372;
    public static final int GL_VIEWPORT_BOUNDS_RANGE = 33373;
    public static final int GL_LAYER_PROVOKING_VERTEX = 33374;
    public static final int GL_VIEWPORT_INDEX_PROVOKING_VERTEX = 33375;
    public static final int GL_UNDEFINED_VERTEX = 33376;

    static {
        GL.initialize();
    }

    protected ARBViewportArray() {
        throw new UnsupportedOperationException();
    }

    public static void nglViewportArrayv(int i, int i2, long j) {
        GL41C.nglViewportArrayv(i, i2, j);
    }

    public static void glViewportArrayv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL41C.glViewportArrayv(i, floatBuffer);
    }

    public static void glViewportIndexedf(@NativeType("GLuint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4) {
        GL41C.glViewportIndexedf(i, f, f2, f3, f4);
    }

    public static void nglViewportIndexedfv(int i, long j) {
        GL41C.nglViewportIndexedfv(i, j);
    }

    public static void glViewportIndexedfv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL41C.glViewportIndexedfv(i, floatBuffer);
    }

    public static void nglScissorArrayv(int i, int i2, long j) {
        GL41C.nglScissorArrayv(i, i2, j);
    }

    public static void glScissorArrayv(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL41C.glScissorArrayv(i, intBuffer);
    }

    public static void glScissorIndexed(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5) {
        GL41C.glScissorIndexed(i, i2, i3, i4, i5);
    }

    public static void nglScissorIndexedv(int i, long j) {
        GL41C.nglScissorIndexedv(i, j);
    }

    public static void glScissorIndexedv(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL41C.glScissorIndexedv(i, intBuffer);
    }

    public static void nglDepthRangeArrayv(int i, int i2, long j) {
        GL41C.nglDepthRangeArrayv(i, i2, j);
    }

    public static void glDepthRangeArrayv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL41C.glDepthRangeArrayv(i, doubleBuffer);
    }

    public static void glDepthRangeIndexed(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2) {
        GL41C.glDepthRangeIndexed(i, d, d2);
    }

    public static void nglGetFloati_v(int i, int i2, long j) {
        GL41C.nglGetFloati_v(i, i2, j);
    }

    public static void glGetFloati_v(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        GL41C.glGetFloati_v(i, i2, floatBuffer);
    }

    @NativeType("void")
    public static float glGetFloati(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        return GL41C.glGetFloati(i, i2);
    }

    public static void nglGetDoublei_v(int i, int i2, long j) {
        GL41C.nglGetDoublei_v(i, i2, j);
    }

    public static void glGetDoublei_v(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        GL41C.glGetDoublei_v(i, i2, doubleBuffer);
    }

    @NativeType("void")
    public static double glGetDoublei(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        return GL41C.glGetDoublei(i, i2);
    }

    public static void glViewportArrayv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") float[] fArr) {
        GL41C.glViewportArrayv(i, fArr);
    }

    public static void glViewportIndexedfv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") float[] fArr) {
        GL41C.glViewportIndexedfv(i, fArr);
    }

    public static void glScissorArrayv(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        GL41C.glScissorArrayv(i, iArr);
    }

    public static void glScissorIndexedv(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        GL41C.glScissorIndexedv(i, iArr);
    }

    public static void glDepthRangeArrayv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        GL41C.glDepthRangeArrayv(i, dArr);
    }

    public static void glGetFloati_v(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat *") float[] fArr) {
        GL41C.glGetFloati_v(i, i2, fArr);
    }

    public static void glGetDoublei_v(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLdouble *") double[] dArr) {
        GL41C.glGetDoublei_v(i, i2, dArr);
    }
}
