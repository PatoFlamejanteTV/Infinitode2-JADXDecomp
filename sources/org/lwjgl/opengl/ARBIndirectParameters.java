package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBIndirectParameters.class */
public class ARBIndirectParameters {
    public static final int GL_PARAMETER_BUFFER_ARB = 33006;
    public static final int GL_PARAMETER_BUFFER_BINDING_ARB = 33007;

    public static native void nglMultiDrawArraysIndirectCountARB(int i, long j, long j2, int i2, int i3);

    public static native void nglMultiDrawElementsIndirectCountARB(int i, int i2, long j, long j2, int i3, int i4);

    static {
        GL.initialize();
    }

    protected ARBIndirectParameters() {
        throw new UnsupportedOperationException();
    }

    public static void glMultiDrawArraysIndirectCountARB(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, i2 * (i3 == 0 ? 16 : i3));
        }
        nglMultiDrawArraysIndirectCountARB(i, MemoryUtil.memAddress(byteBuffer), j, i2, i3);
    }

    public static void glMultiDrawArraysIndirectCountARB(@NativeType("GLenum") int i, @NativeType("void const *") long j, @NativeType("GLintptr") long j2, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3) {
        nglMultiDrawArraysIndirectCountARB(i, j, j2, i2, i3);
    }

    public static void glMultiDrawArraysIndirectCountARB(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, (i2 * (i3 == 0 ? 16 : i3)) >> 2);
        }
        nglMultiDrawArraysIndirectCountARB(i, MemoryUtil.memAddress(intBuffer), j, i2, i3);
    }

    public static void glMultiDrawElementsIndirectCountARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, i3 * (i4 == 0 ? 20 : i4));
        }
        nglMultiDrawElementsIndirectCountARB(i, i2, MemoryUtil.memAddress(byteBuffer), j, i3, i4);
    }

    public static void glMultiDrawElementsIndirectCountARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") long j, @NativeType("GLintptr") long j2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        nglMultiDrawElementsIndirectCountARB(i, i2, j, j2, i3, i4);
    }

    public static void glMultiDrawElementsIndirectCountARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, (i3 * (i4 == 0 ? 20 : i4)) >> 2);
        }
        nglMultiDrawElementsIndirectCountARB(i, i2, MemoryUtil.memAddress(intBuffer), j, i3, i4);
    }

    public static void glMultiDrawArraysIndirectCountARB(@NativeType("GLenum") int i, @NativeType("void const *") int[] iArr, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3) {
        long j2 = GL.getICD().glMultiDrawArraysIndirectCountARB;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(iArr, (i2 * (i3 == 0 ? 16 : i3)) >> 2);
        }
        JNI.callPPV(i, iArr, j, i2, i3, j2);
    }

    public static void glMultiDrawElementsIndirectCountARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") int[] iArr, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        long j2 = GL.getICD().glMultiDrawElementsIndirectCountARB;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(iArr, (i3 * (i4 == 0 ? 20 : i4)) >> 2);
        }
        JNI.callPPV(i, i2, iArr, j, i3, i4, j2);
    }
}
