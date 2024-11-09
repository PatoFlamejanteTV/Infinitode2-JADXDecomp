package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBMultiDrawIndirect.class */
public class ARBMultiDrawIndirect {
    static {
        GL.initialize();
    }

    protected ARBMultiDrawIndirect() {
        throw new UnsupportedOperationException();
    }

    public static void nglMultiDrawArraysIndirect(int i, long j, int i2, int i3) {
        GL43C.nglMultiDrawArraysIndirect(i, j, i2, i3);
    }

    public static void glMultiDrawArraysIndirect(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3) {
        GL43C.glMultiDrawArraysIndirect(i, byteBuffer, i2, i3);
    }

    public static void glMultiDrawArraysIndirect(@NativeType("GLenum") int i, @NativeType("void const *") long j, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3) {
        GL43C.glMultiDrawArraysIndirect(i, j, i2, i3);
    }

    public static void glMultiDrawArraysIndirect(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3) {
        GL43C.glMultiDrawArraysIndirect(i, intBuffer, i2, i3);
    }

    public static void nglMultiDrawElementsIndirect(int i, int i2, long j, int i3, int i4) {
        GL43C.nglMultiDrawElementsIndirect(i, i2, j, i3, i4);
    }

    public static void glMultiDrawElementsIndirect(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        GL43C.glMultiDrawElementsIndirect(i, i2, byteBuffer, i3, i4);
    }

    public static void glMultiDrawElementsIndirect(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") long j, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        GL43C.glMultiDrawElementsIndirect(i, i2, j, i3, i4);
    }

    public static void glMultiDrawElementsIndirect(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        GL43C.glMultiDrawElementsIndirect(i, i2, intBuffer, i3, i4);
    }

    public static void glMultiDrawArraysIndirect(@NativeType("GLenum") int i, @NativeType("void const *") int[] iArr, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3) {
        GL43C.glMultiDrawArraysIndirect(i, iArr, i2, i3);
    }

    public static void glMultiDrawElementsIndirect(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") int[] iArr, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        GL43C.glMultiDrawElementsIndirect(i, i2, iArr, i3, i4);
    }
}
