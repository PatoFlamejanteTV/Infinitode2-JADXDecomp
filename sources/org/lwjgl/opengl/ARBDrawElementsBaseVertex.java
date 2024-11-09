package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBDrawElementsBaseVertex.class */
public class ARBDrawElementsBaseVertex {
    static {
        GL.initialize();
    }

    protected ARBDrawElementsBaseVertex() {
        throw new UnsupportedOperationException();
    }

    public static void nglDrawElementsBaseVertex(int i, int i2, int i3, long j, int i4) {
        GL32C.nglDrawElementsBaseVertex(i, i2, i3, j, i4);
    }

    public static void glDrawElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("void const *") long j, @NativeType("GLint") int i4) {
        GL32C.glDrawElementsBaseVertex(i, i2, i3, j, i4);
    }

    public static void glDrawElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLint") int i3) {
        GL32C.glDrawElementsBaseVertex(i, i2, byteBuffer, i3);
    }

    public static void glDrawElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLint") int i2) {
        GL32C.glDrawElementsBaseVertex(i, byteBuffer, i2);
    }

    public static void glDrawElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLint") int i2) {
        GL32C.glDrawElementsBaseVertex(i, shortBuffer, i2);
    }

    public static void glDrawElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLint") int i2) {
        GL32C.glDrawElementsBaseVertex(i, intBuffer, i2);
    }

    public static void nglDrawRangeElementsBaseVertex(int i, int i2, int i3, int i4, int i5, long j, int i6) {
        GL32C.nglDrawRangeElementsBaseVertex(i, i2, i3, i4, i5, j, i6);
    }

    public static void glDrawRangeElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("void const *") long j, @NativeType("GLint") int i6) {
        GL32C.glDrawRangeElementsBaseVertex(i, i2, i3, i4, i5, j, i6);
    }

    public static void glDrawRangeElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLint") int i5) {
        GL32C.glDrawRangeElementsBaseVertex(i, i2, i3, i4, byteBuffer, i5);
    }

    public static void glDrawRangeElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLint") int i4) {
        GL32C.glDrawRangeElementsBaseVertex(i, i2, i3, byteBuffer, i4);
    }

    public static void glDrawRangeElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLint") int i4) {
        GL32C.glDrawRangeElementsBaseVertex(i, i2, i3, shortBuffer, i4);
    }

    public static void glDrawRangeElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLint") int i4) {
        GL32C.glDrawRangeElementsBaseVertex(i, i2, i3, intBuffer, i4);
    }

    public static void nglDrawElementsInstancedBaseVertex(int i, int i2, int i3, long j, int i4, int i5) {
        GL32C.nglDrawElementsInstancedBaseVertex(i, i2, i3, j, i4, i5);
    }

    public static void glDrawElementsInstancedBaseVertex(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("void const *") long j, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5) {
        GL32C.glDrawElementsInstancedBaseVertex(i, i2, i3, j, i4, i5);
    }

    public static void glDrawElementsInstancedBaseVertex(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i3, @NativeType("GLint") int i4) {
        GL32C.glDrawElementsInstancedBaseVertex(i, i2, byteBuffer, i3, i4);
    }

    public static void glDrawElementsInstancedBaseVertex(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i2, @NativeType("GLint") int i3) {
        GL32C.glDrawElementsInstancedBaseVertex(i, byteBuffer, i2, i3);
    }

    public static void glDrawElementsInstancedBaseVertex(@NativeType("GLenum") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLsizei") int i2, @NativeType("GLint") int i3) {
        GL32C.glDrawElementsInstancedBaseVertex(i, shortBuffer, i2, i3);
    }

    public static void glDrawElementsInstancedBaseVertex(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLsizei") int i2, @NativeType("GLint") int i3) {
        GL32C.glDrawElementsInstancedBaseVertex(i, intBuffer, i2, i3);
    }

    public static void nglMultiDrawElementsBaseVertex(int i, long j, int i2, long j2, int i3, long j3) {
        GL32C.nglMultiDrawElementsBaseVertex(i, j, i2, j2, i3, j3);
    }

    public static void glMultiDrawElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLsizei const *") IntBuffer intBuffer, @NativeType("GLenum") int i2, @NativeType("void const * const *") PointerBuffer pointerBuffer, @NativeType("GLint *") IntBuffer intBuffer2) {
        GL32C.glMultiDrawElementsBaseVertex(i, intBuffer, i2, pointerBuffer, intBuffer2);
    }

    public static void glMultiDrawElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLsizei const *") int[] iArr, @NativeType("GLenum") int i2, @NativeType("void const * const *") PointerBuffer pointerBuffer, @NativeType("GLint *") int[] iArr2) {
        GL32C.glMultiDrawElementsBaseVertex(i, iArr, i2, pointerBuffer, iArr2);
    }
}
