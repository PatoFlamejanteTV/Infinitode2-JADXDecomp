package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBBaseInstance.class */
public class ARBBaseInstance {
    static {
        GL.initialize();
    }

    protected ARBBaseInstance() {
        throw new UnsupportedOperationException();
    }

    public static void glDrawArraysInstancedBaseInstance(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLuint") int i5) {
        GL42C.glDrawArraysInstancedBaseInstance(i, i2, i3, i4, i5);
    }

    public static void nglDrawElementsInstancedBaseInstance(int i, int i2, int i3, long j, int i4, int i5) {
        GL42C.nglDrawElementsInstancedBaseInstance(i, i2, i3, j, i4, i5);
    }

    public static void glDrawElementsInstancedBaseInstance(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("void const *") long j, @NativeType("GLsizei") int i4, @NativeType("GLuint") int i5) {
        GL42C.glDrawElementsInstancedBaseInstance(i, i2, i3, j, i4, i5);
    }

    public static void glDrawElementsInstancedBaseInstance(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i3, @NativeType("GLuint") int i4) {
        GL42C.glDrawElementsInstancedBaseInstance(i, i2, byteBuffer, i3, i4);
    }

    public static void glDrawElementsInstancedBaseInstance(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i2, @NativeType("GLuint") int i3) {
        GL42C.glDrawElementsInstancedBaseInstance(i, byteBuffer, i2, i3);
    }

    public static void glDrawElementsInstancedBaseInstance(@NativeType("GLenum") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLsizei") int i2, @NativeType("GLuint") int i3) {
        GL42C.glDrawElementsInstancedBaseInstance(i, shortBuffer, i2, i3);
    }

    public static void glDrawElementsInstancedBaseInstance(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLsizei") int i2, @NativeType("GLuint") int i3) {
        GL42C.glDrawElementsInstancedBaseInstance(i, intBuffer, i2, i3);
    }

    public static void nglDrawElementsInstancedBaseVertexBaseInstance(int i, int i2, int i3, long j, int i4, int i5, int i6) {
        GL42C.nglDrawElementsInstancedBaseVertexBaseInstance(i, i2, i3, j, i4, i5, i6);
    }

    public static void glDrawElementsInstancedBaseVertexBaseInstance(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("void const *") long j, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLuint") int i6) {
        GL42C.glDrawElementsInstancedBaseVertexBaseInstance(i, i2, i3, j, i4, i5, i6);
    }

    public static void glDrawElementsInstancedBaseVertexBaseInstance(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i3, @NativeType("GLint") int i4, @NativeType("GLuint") int i5) {
        GL42C.glDrawElementsInstancedBaseVertexBaseInstance(i, i2, byteBuffer, i3, i4, i5);
    }

    public static void glDrawElementsInstancedBaseVertexBaseInstance(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i2, @NativeType("GLint") int i3, @NativeType("GLuint") int i4) {
        GL42C.glDrawElementsInstancedBaseVertexBaseInstance(i, byteBuffer, i2, i3, i4);
    }

    public static void glDrawElementsInstancedBaseVertexBaseInstance(@NativeType("GLenum") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLsizei") int i2, @NativeType("GLint") int i3, @NativeType("GLuint") int i4) {
        GL42C.glDrawElementsInstancedBaseVertexBaseInstance(i, shortBuffer, i2, i3, i4);
    }

    public static void glDrawElementsInstancedBaseVertexBaseInstance(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLsizei") int i2, @NativeType("GLint") int i3, @NativeType("GLuint") int i4) {
        GL42C.glDrawElementsInstancedBaseVertexBaseInstance(i, intBuffer, i2, i3, i4);
    }
}
