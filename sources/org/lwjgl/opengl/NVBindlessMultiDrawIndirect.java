package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVBindlessMultiDrawIndirect.class */
public class NVBindlessMultiDrawIndirect {
    public static native void nglMultiDrawArraysIndirectBindlessNV(int i, long j, int i2, int i3, int i4);

    public static native void nglMultiDrawElementsIndirectBindlessNV(int i, int i2, long j, int i3, int i4, int i5);

    static {
        GL.initialize();
    }

    protected NVBindlessMultiDrawIndirect() {
        throw new UnsupportedOperationException();
    }

    public static void glMultiDrawArraysIndirectBindlessNV(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3, @NativeType("GLint") int i4) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, i2 * (i3 == 0 ? 16 + (i4 * 24) : i3));
        }
        nglMultiDrawArraysIndirectBindlessNV(i, MemoryUtil.memAddress(byteBuffer), i2, i3, i4);
    }

    public static void glMultiDrawArraysIndirectBindlessNV(@NativeType("GLenum") int i, @NativeType("void const *") long j, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3, @NativeType("GLint") int i4) {
        nglMultiDrawArraysIndirectBindlessNV(i, j, i2, i3, i4);
    }

    public static void glMultiDrawElementsIndirectBindlessNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, i3 * (i4 == 0 ? (i5 + 2) * 24 : i4));
        }
        nglMultiDrawElementsIndirectBindlessNV(i, i2, MemoryUtil.memAddress(byteBuffer), i3, i4, i5);
    }

    public static void glMultiDrawElementsIndirectBindlessNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") long j, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5) {
        nglMultiDrawElementsIndirectBindlessNV(i, i2, j, i3, i4, i5);
    }
}
