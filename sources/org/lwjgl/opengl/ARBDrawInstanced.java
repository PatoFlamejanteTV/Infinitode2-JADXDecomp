package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBDrawInstanced.class */
public class ARBDrawInstanced {
    public static native void glDrawArraysInstancedARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4);

    public static native void nglDrawElementsInstancedARB(int i, int i2, int i3, long j, int i4);

    static {
        GL.initialize();
    }

    protected ARBDrawInstanced() {
        throw new UnsupportedOperationException();
    }

    public static void glDrawElementsInstancedARB(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("void const *") long j, @NativeType("GLsizei") int i4) {
        nglDrawElementsInstancedARB(i, i2, i3, j, i4);
    }

    public static void glDrawElementsInstancedARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i3) {
        nglDrawElementsInstancedARB(i, byteBuffer.remaining() >> GLChecks.typeToByteShift(i2), i2, MemoryUtil.memAddress(byteBuffer), i3);
    }

    public static void glDrawElementsInstancedARB(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i2) {
        nglDrawElementsInstancedARB(i, byteBuffer.remaining(), 5121, MemoryUtil.memAddress(byteBuffer), i2);
    }

    public static void glDrawElementsInstancedARB(@NativeType("GLenum") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLsizei") int i2) {
        nglDrawElementsInstancedARB(i, shortBuffer.remaining(), 5123, MemoryUtil.memAddress(shortBuffer), i2);
    }

    public static void glDrawElementsInstancedARB(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLsizei") int i2) {
        nglDrawElementsInstancedARB(i, intBuffer.remaining(), 5125, MemoryUtil.memAddress(intBuffer), i2);
    }
}
