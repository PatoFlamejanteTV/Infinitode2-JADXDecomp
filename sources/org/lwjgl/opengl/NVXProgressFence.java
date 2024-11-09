package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVXProgressFence.class */
public class NVXProgressFence {
    @NativeType("GLuint")
    public static native int glCreateProgressFenceNVX();

    public static native void nglSignalSemaphoreui64NVX(int i, int i2, long j, long j2);

    public static native void nglWaitSemaphoreui64NVX(int i, int i2, long j, long j2);

    public static native void nglClientWaitSemaphoreui64NVX(int i, long j, long j2);

    static {
        GL.initialize();
    }

    protected NVXProgressFence() {
        throw new UnsupportedOperationException();
    }

    public static void glSignalSemaphoreui64NVX(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLuint64 const *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, intBuffer.remaining());
        }
        nglSignalSemaphoreui64NVX(i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(longBuffer));
    }

    public static void glWaitSemaphoreui64NVX(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLuint64 const *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, intBuffer.remaining());
        }
        nglWaitSemaphoreui64NVX(i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(longBuffer));
    }

    public static void glClientWaitSemaphoreui64NVX(@NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLuint64 const *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, intBuffer.remaining());
        }
        nglClientWaitSemaphoreui64NVX(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(longBuffer));
    }

    public static void glSignalSemaphoreui64NVX(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr, @NativeType("GLuint64 const *") long[] jArr) {
        long j = GL.getICD().glSignalSemaphoreui64NVX;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, iArr.length);
        }
        JNI.callPPV(i, iArr.length, iArr, jArr, j);
    }

    public static void glWaitSemaphoreui64NVX(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr, @NativeType("GLuint64 const *") long[] jArr) {
        long j = GL.getICD().glWaitSemaphoreui64NVX;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, iArr.length);
        }
        JNI.callPPV(i, iArr.length, iArr, jArr, j);
    }

    public static void glClientWaitSemaphoreui64NVX(@NativeType("GLuint const *") int[] iArr, @NativeType("GLuint64 const *") long[] jArr) {
        long j = GL.getICD().glClientWaitSemaphoreui64NVX;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, iArr.length);
        }
        JNI.callPPV(iArr.length, iArr, jArr, j);
    }
}
