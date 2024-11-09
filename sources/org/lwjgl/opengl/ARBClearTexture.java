package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBClearTexture.class */
public class ARBClearTexture {
    public static final int GL_CLEAR_TEXTURE = 37733;

    static {
        GL.initialize();
    }

    protected ARBClearTexture() {
        throw new UnsupportedOperationException();
    }

    public static void nglClearTexSubImage(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, long j) {
        GL44C.nglClearTexSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, j);
    }

    public static void glClearTexSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL44C.glClearTexSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, byteBuffer);
    }

    public static void glClearTexSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") ShortBuffer shortBuffer) {
        GL44C.glClearTexSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, shortBuffer);
    }

    public static void glClearTexSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") IntBuffer intBuffer) {
        GL44C.glClearTexSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, intBuffer);
    }

    public static void glClearTexSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") FloatBuffer floatBuffer) {
        GL44C.glClearTexSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, floatBuffer);
    }

    public static void glClearTexSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        GL44C.glClearTexSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, doubleBuffer);
    }

    public static void nglClearTexImage(int i, int i2, int i3, int i4, long j) {
        GL44C.nglClearTexImage(i, i2, i3, i4, j);
    }

    public static void glClearTexImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL44C.glClearTexImage(i, i2, i3, i4, byteBuffer);
    }

    public static void glClearTexImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ShortBuffer shortBuffer) {
        GL44C.glClearTexImage(i, i2, i3, i4, shortBuffer);
    }

    public static void glClearTexImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") IntBuffer intBuffer) {
        GL44C.glClearTexImage(i, i2, i3, i4, intBuffer);
    }

    public static void glClearTexImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") FloatBuffer floatBuffer) {
        GL44C.glClearTexImage(i, i2, i3, i4, floatBuffer);
    }

    public static void glClearTexImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        GL44C.glClearTexImage(i, i2, i3, i4, doubleBuffer);
    }

    public static void glClearTexSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") short[] sArr) {
        GL44C.glClearTexSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, sArr);
    }

    public static void glClearTexSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") int[] iArr) {
        GL44C.glClearTexSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, iArr);
    }

    public static void glClearTexSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") float[] fArr) {
        GL44C.glClearTexSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, fArr);
    }

    public static void glClearTexSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") double[] dArr) {
        GL44C.glClearTexSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, dArr);
    }

    public static void glClearTexImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") short[] sArr) {
        GL44C.glClearTexImage(i, i2, i3, i4, sArr);
    }

    public static void glClearTexImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") int[] iArr) {
        GL44C.glClearTexImage(i, i2, i3, i4, iArr);
    }

    public static void glClearTexImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") float[] fArr) {
        GL44C.glClearTexImage(i, i2, i3, i4, fArr);
    }

    public static void glClearTexImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") double[] dArr) {
        GL44C.glClearTexImage(i, i2, i3, i4, dArr);
    }
}
