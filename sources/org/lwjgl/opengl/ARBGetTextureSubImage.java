package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBGetTextureSubImage.class */
public class ARBGetTextureSubImage {
    static {
        GL.initialize();
    }

    protected ARBGetTextureSubImage() {
        throw new UnsupportedOperationException();
    }

    public static void nglGetTextureSubImage(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, long j) {
        GL45C.nglGetTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, j);
    }

    public static void glGetTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("GLsizei") int i11, @NativeType("void *") long j) {
        GL45C.glGetTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, j);
    }

    public static void glGetTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void *") ByteBuffer byteBuffer) {
        GL45C.glGetTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, byteBuffer);
    }

    public static void glGetTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void *") ShortBuffer shortBuffer) {
        GL45C.glGetTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, shortBuffer);
    }

    public static void glGetTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void *") IntBuffer intBuffer) {
        GL45C.glGetTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, intBuffer);
    }

    public static void glGetTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void *") FloatBuffer floatBuffer) {
        GL45C.glGetTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, floatBuffer);
    }

    public static void glGetTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void *") DoubleBuffer doubleBuffer) {
        GL45C.glGetTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, doubleBuffer);
    }

    public static void nglGetCompressedTextureSubImage(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, long j) {
        GL45C.nglGetCompressedTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, j);
    }

    public static void glGetCompressedTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("void *") long j) {
        GL45C.glGetCompressedTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, j);
    }

    public static void glGetCompressedTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("void *") ByteBuffer byteBuffer) {
        GL45C.glGetCompressedTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, byteBuffer);
    }

    public static void glGetCompressedTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("void *") ShortBuffer shortBuffer) {
        GL45C.glGetCompressedTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, shortBuffer);
    }

    public static void glGetCompressedTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("void *") IntBuffer intBuffer) {
        GL45C.glGetCompressedTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, intBuffer);
    }

    public static void glGetCompressedTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("void *") FloatBuffer floatBuffer) {
        GL45C.glGetCompressedTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, floatBuffer);
    }

    public static void glGetCompressedTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("void *") DoubleBuffer doubleBuffer) {
        GL45C.glGetCompressedTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, doubleBuffer);
    }

    public static void glGetTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void *") short[] sArr) {
        GL45C.glGetTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, sArr);
    }

    public static void glGetTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void *") int[] iArr) {
        GL45C.glGetTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, iArr);
    }

    public static void glGetTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void *") float[] fArr) {
        GL45C.glGetTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, fArr);
    }

    public static void glGetTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void *") double[] dArr) {
        GL45C.glGetTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, dArr);
    }

    public static void glGetCompressedTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("void *") short[] sArr) {
        GL45C.glGetCompressedTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, sArr);
    }

    public static void glGetCompressedTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("void *") int[] iArr) {
        GL45C.glGetCompressedTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, iArr);
    }

    public static void glGetCompressedTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("void *") float[] fArr) {
        GL45C.glGetCompressedTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, fArr);
    }

    public static void glGetCompressedTextureSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("void *") double[] dArr) {
        GL45C.glGetCompressedTextureSubImage(i, i2, i3, i4, i5, i6, i7, i8, dArr);
    }
}
