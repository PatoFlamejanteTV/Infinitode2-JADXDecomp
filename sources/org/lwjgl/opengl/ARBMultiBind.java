package org.lwjgl.opengl;

import java.nio.IntBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBMultiBind.class */
public class ARBMultiBind {
    static {
        GL.initialize();
    }

    protected ARBMultiBind() {
        throw new UnsupportedOperationException();
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
