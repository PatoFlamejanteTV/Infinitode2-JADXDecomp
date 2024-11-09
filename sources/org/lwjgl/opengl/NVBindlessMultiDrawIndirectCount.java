package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVBindlessMultiDrawIndirectCount.class */
public class NVBindlessMultiDrawIndirectCount {
    public static native void nglMultiDrawArraysIndirectBindlessCountNV(int i, long j, long j2, int i2, int i3, int i4);

    public static native void nglMultiDrawElementsIndirectBindlessCountNV(int i, int i2, long j, long j2, int i3, int i4, int i5);

    static {
        GL.initialize();
    }

    protected NVBindlessMultiDrawIndirectCount() {
        throw new UnsupportedOperationException();
    }

    public static void glMultiDrawArraysIndirectBindlessCountNV(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3, @NativeType("GLint") int i4) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, i2 * (i3 == 0 ? 16 + (i4 * 24) : i3));
        }
        nglMultiDrawArraysIndirectBindlessCountNV(i, MemoryUtil.memAddress(byteBuffer), j, i2, i3, i4);
    }

    public static void glMultiDrawArraysIndirectBindlessCountNV(@NativeType("GLenum") int i, @NativeType("void const *") long j, @NativeType("GLintptr") long j2, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3, @NativeType("GLint") int i4) {
        nglMultiDrawArraysIndirectBindlessCountNV(i, j, j2, i2, i3, i4);
    }

    public static void glMultiDrawElementsIndirectBindlessCountNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, i3 * (i4 == 0 ? (i5 + 2) * 24 : i4));
        }
        nglMultiDrawElementsIndirectBindlessCountNV(i, i2, MemoryUtil.memAddress(byteBuffer), j, i3, i4, i5);
    }

    public static void glMultiDrawElementsIndirectBindlessCountNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") long j, @NativeType("GLintptr") long j2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5) {
        nglMultiDrawElementsIndirectBindlessCountNV(i, i2, j, j2, i3, i4, i5);
    }
}
