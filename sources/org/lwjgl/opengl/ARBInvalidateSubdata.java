package org.lwjgl.opengl;

import java.nio.IntBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBInvalidateSubdata.class */
public class ARBInvalidateSubdata {
    static {
        GL.initialize();
    }

    protected ARBInvalidateSubdata() {
        throw new UnsupportedOperationException();
    }

    public static void glInvalidateTexSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8) {
        GL43C.glInvalidateTexSubImage(i, i2, i3, i4, i5, i6, i7, i8);
    }

    public static void glInvalidateTexImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        GL43C.glInvalidateTexImage(i, i2);
    }

    public static void glInvalidateBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2) {
        GL43C.glInvalidateBufferSubData(i, j, j2);
    }

    public static void glInvalidateBufferData(@NativeType("GLuint") int i) {
        GL43C.glInvalidateBufferData(i);
    }

    public static void nglInvalidateFramebuffer(int i, int i2, long j) {
        GL43C.nglInvalidateFramebuffer(i, i2, j);
    }

    public static void glInvalidateFramebuffer(@NativeType("GLenum") int i, @NativeType("GLenum const *") IntBuffer intBuffer) {
        GL43C.glInvalidateFramebuffer(i, intBuffer);
    }

    public static void glInvalidateFramebuffer(@NativeType("GLenum") int i, @NativeType("GLenum const *") int i2) {
        GL43C.glInvalidateFramebuffer(i, i2);
    }

    public static void nglInvalidateSubFramebuffer(int i, int i2, long j, int i3, int i4, int i5, int i6) {
        GL43C.nglInvalidateSubFramebuffer(i, i2, j, i3, i4, i5, i6);
    }

    public static void glInvalidateSubFramebuffer(@NativeType("GLenum") int i, @NativeType("GLenum const *") IntBuffer intBuffer, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5) {
        GL43C.glInvalidateSubFramebuffer(i, intBuffer, i2, i3, i4, i5);
    }

    public static void glInvalidateSubFramebuffer(@NativeType("GLenum") int i, @NativeType("GLenum const *") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6) {
        GL43C.glInvalidateSubFramebuffer(i, i2, i3, i4, i5, i6);
    }

    public static void glInvalidateFramebuffer(@NativeType("GLenum") int i, @NativeType("GLenum const *") int[] iArr) {
        GL43C.glInvalidateFramebuffer(i, iArr);
    }

    public static void glInvalidateSubFramebuffer(@NativeType("GLenum") int i, @NativeType("GLenum const *") int[] iArr, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5) {
        GL43C.glInvalidateSubFramebuffer(i, iArr, i2, i3, i4, i5);
    }
}
