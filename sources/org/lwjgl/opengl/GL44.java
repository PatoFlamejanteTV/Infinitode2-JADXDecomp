package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL44.class */
public class GL44 extends GL43 {
    public static final int GL_MAX_VERTEX_ATTRIB_STRIDE = 33509;
    public static final int GL_PRIMITIVE_RESTART_FOR_PATCHES_SUPPORTED = 33313;
    public static final int GL_TEXTURE_BUFFER_BINDING = 35882;
    public static final int GL_MAP_PERSISTENT_BIT = 64;
    public static final int GL_MAP_COHERENT_BIT = 128;
    public static final int GL_DYNAMIC_STORAGE_BIT = 256;
    public static final int GL_CLIENT_STORAGE_BIT = 512;
    public static final int GL_BUFFER_IMMUTABLE_STORAGE = 33311;
    public static final int GL_BUFFER_STORAGE_FLAGS = 33312;
    public static final int GL_CLIENT_MAPPED_BUFFER_BARRIER_BIT = 16384;
    public static final int GL_CLEAR_TEXTURE = 37733;
    public static final int GL_LOCATION_COMPONENT = 37706;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_INDEX = 37707;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_STRIDE = 37708;
    public static final int GL_QUERY_RESULT_NO_WAIT = 37268;
    public static final int GL_QUERY_BUFFER = 37266;
    public static final int GL_QUERY_BUFFER_BINDING = 37267;
    public static final int GL_QUERY_BUFFER_BARRIER_BIT = 32768;
    public static final int GL_MIRROR_CLAMP_TO_EDGE = 34627;

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL44() {
        throw new UnsupportedOperationException();
    }

    public static void nglBufferStorage(int i, long j, long j2, int i2) {
        GL44C.nglBufferStorage(i, j, j2, i2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("GLsizeiptr") long j, @NativeType("GLbitfield") int i2) {
        GL44C.glBufferStorage(i, j, i2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLbitfield") int i2) {
        GL44C.glBufferStorage(i, byteBuffer, i2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLbitfield") int i2) {
        GL44C.glBufferStorage(i, shortBuffer, i2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLbitfield") int i2) {
        GL44C.glBufferStorage(i, intBuffer, i2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") FloatBuffer floatBuffer, @NativeType("GLbitfield") int i2) {
        GL44C.glBufferStorage(i, floatBuffer, i2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") DoubleBuffer doubleBuffer, @NativeType("GLbitfield") int i2) {
        GL44C.glBufferStorage(i, doubleBuffer, i2);
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

    public static void nglBindBuffersBase(int i, int i2, int i3, long j) {
        GL44C.nglBindBuffersBase(i, i2, i3, j);
    }

    public static void glBindBuffersBase(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL44C.glBindBuffersBase(i, i2, intBuffer);
    }

    public static void nglBindBuffersRange(int i, int i2, int i3, long j, long j2, long j3) {
        GL44C.nglBindBuffersRange(i, i2, i3, j, j2, j3);
    }

    public static void glBindBuffersRange(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLintptr const *") PointerBuffer pointerBuffer, @NativeType("GLsizeiptr const *") PointerBuffer pointerBuffer2) {
        GL44C.glBindBuffersRange(i, i2, intBuffer, pointerBuffer, pointerBuffer2);
    }

    public static void nglBindTextures(int i, int i2, long j) {
        GL44C.nglBindTextures(i, i2, j);
    }

    public static void glBindTextures(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL44C.glBindTextures(i, intBuffer);
    }

    public static void nglBindSamplers(int i, int i2, long j) {
        GL44C.nglBindSamplers(i, i2, j);
    }

    public static void glBindSamplers(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL44C.glBindSamplers(i, intBuffer);
    }

    public static void nglBindImageTextures(int i, int i2, long j) {
        GL44C.nglBindImageTextures(i, i2, j);
    }

    public static void glBindImageTextures(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL44C.glBindImageTextures(i, intBuffer);
    }

    public static void nglBindVertexBuffers(int i, int i2, long j, long j2, long j3) {
        GL44C.nglBindVertexBuffers(i, i2, j, j2, j3);
    }

    public static void glBindVertexBuffers(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLintptr const *") PointerBuffer pointerBuffer, @NativeType("GLsizei const *") IntBuffer intBuffer2) {
        GL44C.glBindVertexBuffers(i, intBuffer, pointerBuffer, intBuffer2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") short[] sArr, @NativeType("GLbitfield") int i2) {
        GL44C.glBufferStorage(i, sArr, i2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") int[] iArr, @NativeType("GLbitfield") int i2) {
        GL44C.glBufferStorage(i, iArr, i2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") float[] fArr, @NativeType("GLbitfield") int i2) {
        GL44C.glBufferStorage(i, fArr, i2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") double[] dArr, @NativeType("GLbitfield") int i2) {
        GL44C.glBufferStorage(i, dArr, i2);
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

    public static void glBindBuffersBase(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint const *") int[] iArr) {
        GL44C.glBindBuffersBase(i, i2, iArr);
    }

    public static void glBindBuffersRange(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint const *") int[] iArr, @NativeType("GLintptr const *") PointerBuffer pointerBuffer, @NativeType("GLsizeiptr const *") PointerBuffer pointerBuffer2) {
        GL44C.glBindBuffersRange(i, i2, iArr, pointerBuffer, pointerBuffer2);
    }

    public static void glBindTextures(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        GL44C.glBindTextures(i, iArr);
    }

    public static void glBindSamplers(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        GL44C.glBindSamplers(i, iArr);
    }

    public static void glBindImageTextures(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        GL44C.glBindImageTextures(i, iArr);
    }

    public static void glBindVertexBuffers(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr, @NativeType("GLintptr const *") PointerBuffer pointerBuffer, @NativeType("GLsizei const *") int[] iArr2) {
        GL44C.glBindVertexBuffers(i, iArr, pointerBuffer, iArr2);
    }
}
