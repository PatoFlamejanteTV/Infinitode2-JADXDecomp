package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBDrawIndirect.class */
public class ARBDrawIndirect {
    public static final int GL_DRAW_INDIRECT_BUFFER = 36671;
    public static final int GL_DRAW_INDIRECT_BUFFER_BINDING = 36675;

    static {
        GL.initialize();
    }

    protected ARBDrawIndirect() {
        throw new UnsupportedOperationException();
    }

    public static void nglDrawArraysIndirect(int i, long j) {
        GL40C.nglDrawArraysIndirect(i, j);
    }

    public static void glDrawArraysIndirect(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL40C.glDrawArraysIndirect(i, byteBuffer);
    }

    public static void glDrawArraysIndirect(@NativeType("GLenum") int i, @NativeType("void const *") long j) {
        GL40C.glDrawArraysIndirect(i, j);
    }

    public static void glDrawArraysIndirect(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer) {
        GL40C.glDrawArraysIndirect(i, intBuffer);
    }

    public static void nglDrawElementsIndirect(int i, int i2, long j) {
        GL40C.nglDrawElementsIndirect(i, i2, j);
    }

    public static void glDrawElementsIndirect(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL40C.glDrawElementsIndirect(i, i2, byteBuffer);
    }

    public static void glDrawElementsIndirect(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") long j) {
        GL40C.glDrawElementsIndirect(i, i2, j);
    }

    public static void glDrawElementsIndirect(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") IntBuffer intBuffer) {
        GL40C.glDrawElementsIndirect(i, i2, intBuffer);
    }

    public static void glDrawArraysIndirect(@NativeType("GLenum") int i, @NativeType("void const *") int[] iArr) {
        GL40C.glDrawArraysIndirect(i, iArr);
    }

    public static void glDrawElementsIndirect(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") int[] iArr) {
        GL40C.glDrawElementsIndirect(i, i2, iArr);
    }
}
