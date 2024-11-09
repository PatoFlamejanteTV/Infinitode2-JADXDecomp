package org.lwjgl.opengl;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBSamplerObjects.class */
public class ARBSamplerObjects {
    public static final int GL_SAMPLER_BINDING = 35097;

    static {
        GL.initialize();
    }

    protected ARBSamplerObjects() {
        throw new UnsupportedOperationException();
    }

    public static void nglGenSamplers(int i, long j) {
        GL33C.nglGenSamplers(i, j);
    }

    public static void glGenSamplers(@NativeType("GLuint *") IntBuffer intBuffer) {
        GL33C.glGenSamplers(intBuffer);
    }

    @NativeType("void")
    public static int glGenSamplers() {
        return GL33C.glGenSamplers();
    }

    public static void nglDeleteSamplers(int i, long j) {
        GL33C.nglDeleteSamplers(i, j);
    }

    public static void glDeleteSamplers(@NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33C.glDeleteSamplers(intBuffer);
    }

    public static void glDeleteSamplers(@NativeType("GLuint const *") int i) {
        GL33C.glDeleteSamplers(i);
    }

    @NativeType("GLboolean")
    public static boolean glIsSampler(@NativeType("GLuint") int i) {
        return GL33C.glIsSampler(i);
    }

    public static void glBindSampler(@NativeType("GLuint") int i, @NativeType("GLuint") int i2) {
        GL33C.glBindSampler(i, i2);
    }

    public static void glSamplerParameteri(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3) {
        GL33C.glSamplerParameteri(i, i2, i3);
    }

    public static void glSamplerParameterf(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat") float f) {
        GL33C.glSamplerParameterf(i, i2, f);
    }

    public static void nglSamplerParameteriv(int i, int i2, long j) {
        GL33C.nglSamplerParameteriv(i, i2, j);
    }

    public static void glSamplerParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL33C.glSamplerParameteriv(i, i2, intBuffer);
    }

    public static void nglSamplerParameterfv(int i, int i2, long j) {
        GL33C.nglSamplerParameterfv(i, i2, j);
    }

    public static void glSamplerParameterfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL33C.glSamplerParameterfv(i, i2, floatBuffer);
    }

    public static void nglSamplerParameterIiv(int i, int i2, long j) {
        GL33C.nglSamplerParameterIiv(i, i2, j);
    }

    public static void glSamplerParameterIiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL33C.glSamplerParameterIiv(i, i2, intBuffer);
    }

    public static void nglSamplerParameterIuiv(int i, int i2, long j) {
        GL33C.nglSamplerParameterIuiv(i, i2, j);
    }

    public static void glSamplerParameterIuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33C.glSamplerParameterIuiv(i, i2, intBuffer);
    }

    public static void nglGetSamplerParameteriv(int i, int i2, long j) {
        GL33C.nglGetSamplerParameteriv(i, i2, j);
    }

    public static void glGetSamplerParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL33C.glGetSamplerParameteriv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetSamplerParameteri(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL33C.glGetSamplerParameteri(i, i2);
    }

    public static void nglGetSamplerParameterfv(int i, int i2, long j) {
        GL33C.nglGetSamplerParameterfv(i, i2, j);
    }

    public static void glGetSamplerParameterfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        GL33C.glGetSamplerParameterfv(i, i2, floatBuffer);
    }

    @NativeType("void")
    public static float glGetSamplerParameterf(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL33C.glGetSamplerParameterf(i, i2);
    }

    public static void nglGetSamplerParameterIiv(int i, int i2, long j) {
        GL33C.nglGetSamplerParameterIiv(i, i2, j);
    }

    public static void glGetSamplerParameterIiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL33C.glGetSamplerParameterIiv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetSamplerParameterIi(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL33C.glGetSamplerParameterIi(i, i2);
    }

    public static void nglGetSamplerParameterIuiv(int i, int i2, long j) {
        GL33C.nglGetSamplerParameterIuiv(i, i2, j);
    }

    public static void glGetSamplerParameterIuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") IntBuffer intBuffer) {
        GL33C.glGetSamplerParameterIuiv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetSamplerParameterIui(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL33C.glGetSamplerParameterIui(i, i2);
    }

    public static void glGenSamplers(@NativeType("GLuint *") int[] iArr) {
        GL33C.glGenSamplers(iArr);
    }

    public static void glDeleteSamplers(@NativeType("GLuint const *") int[] iArr) {
        GL33C.glDeleteSamplers(iArr);
    }

    public static void glSamplerParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int[] iArr) {
        GL33C.glSamplerParameteriv(i, i2, iArr);
    }

    public static void glSamplerParameterfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") float[] fArr) {
        GL33C.glSamplerParameterfv(i, i2, fArr);
    }

    public static void glSamplerParameterIiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int[] iArr) {
        GL33C.glSamplerParameterIiv(i, i2, iArr);
    }

    public static void glSamplerParameterIuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") int[] iArr) {
        GL33C.glSamplerParameterIuiv(i, i2, iArr);
    }

    public static void glGetSamplerParameteriv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL33C.glGetSamplerParameteriv(i, i2, iArr);
    }

    public static void glGetSamplerParameterfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        GL33C.glGetSamplerParameterfv(i, i2, fArr);
    }

    public static void glGetSamplerParameterIiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL33C.glGetSamplerParameterIiv(i, i2, iArr);
    }

    public static void glGetSamplerParameterIuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") int[] iArr) {
        GL33C.glGetSamplerParameterIuiv(i, i2, iArr);
    }
}
