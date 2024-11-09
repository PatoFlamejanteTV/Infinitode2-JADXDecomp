package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/KHRRobustness.class */
public class KHRRobustness {
    public static final int GL_NO_ERROR = 0;
    public static final int GL_GUILTY_CONTEXT_RESET = 33363;
    public static final int GL_INNOCENT_CONTEXT_RESET = 33364;
    public static final int GL_UNKNOWN_CONTEXT_RESET = 33365;
    public static final int GL_CONTEXT_ROBUST_ACCESS = 37107;
    public static final int GL_RESET_NOTIFICATION_STRATEGY = 33366;
    public static final int GL_LOSE_CONTEXT_ON_RESET = 33362;
    public static final int GL_NO_RESET_NOTIFICATION = 33377;
    public static final int GL_CONTEXT_LOST = 1287;

    static {
        GL.initialize();
    }

    protected KHRRobustness() {
        throw new UnsupportedOperationException();
    }

    @NativeType("GLenum")
    public static int glGetGraphicsResetStatus() {
        return GL45C.glGetGraphicsResetStatus();
    }

    public static void nglReadnPixels(int i, int i2, int i3, int i4, int i5, int i6, int i7, long j) {
        GL45C.nglReadnPixels(i, i2, i3, i4, i5, i6, i7, j);
    }

    public static void glReadnPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("GLsizei") int i7, @NativeType("void *") long j) {
        GL45C.glReadnPixels(i, i2, i3, i4, i5, i6, i7, j);
    }

    public static void glReadnPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") ByteBuffer byteBuffer) {
        GL45C.glReadnPixels(i, i2, i3, i4, i5, i6, byteBuffer);
    }

    public static void glReadnPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") ShortBuffer shortBuffer) {
        GL45C.glReadnPixels(i, i2, i3, i4, i5, i6, shortBuffer);
    }

    public static void glReadnPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") IntBuffer intBuffer) {
        GL45C.glReadnPixels(i, i2, i3, i4, i5, i6, intBuffer);
    }

    public static void glReadnPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") FloatBuffer floatBuffer) {
        GL45C.glReadnPixels(i, i2, i3, i4, i5, i6, floatBuffer);
    }

    public static void nglGetnUniformfv(int i, int i2, int i3, long j) {
        GL45C.nglGetnUniformfv(i, i2, i3, j);
    }

    public static void glGetnUniformfv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        GL45C.glGetnUniformfv(i, i2, floatBuffer);
    }

    @NativeType("void")
    public static float glGetnUniformf(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        return GL45C.glGetnUniformf(i, i2);
    }

    public static void nglGetnUniformiv(int i, int i2, int i3, long j) {
        GL45C.nglGetnUniformiv(i, i2, i3, j);
    }

    public static void glGetnUniformiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL45C.glGetnUniformiv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetnUniformi(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        return GL45C.glGetnUniformi(i, i2);
    }

    public static void nglGetnUniformuiv(int i, int i2, int i3, long j) {
        GL45C.nglGetnUniformuiv(i, i2, i3, j);
    }

    public static void glGetnUniformuiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint *") IntBuffer intBuffer) {
        GL45C.glGetnUniformuiv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetnUniformui(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        return GL45C.glGetnUniformui(i, i2);
    }

    public static void glReadnPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") short[] sArr) {
        GL45C.glReadnPixels(i, i2, i3, i4, i5, i6, sArr);
    }

    public static void glReadnPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") int[] iArr) {
        GL45C.glReadnPixels(i, i2, i3, i4, i5, i6, iArr);
    }

    public static void glReadnPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") float[] fArr) {
        GL45C.glReadnPixels(i, i2, i3, i4, i5, i6, fArr);
    }

    public static void glGetnUniformfv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat *") float[] fArr) {
        GL45C.glGetnUniformfv(i, i2, fArr);
    }

    public static void glGetnUniformiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint *") int[] iArr) {
        GL45C.glGetnUniformiv(i, i2, iArr);
    }

    public static void glGetnUniformuiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint *") int[] iArr) {
        GL45C.glGetnUniformuiv(i, i2, iArr);
    }
}
