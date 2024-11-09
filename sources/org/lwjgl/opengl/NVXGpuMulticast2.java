package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVXGpuMulticast2.class */
public class NVXGpuMulticast2 {
    public static native int nglAsyncCopyImageSubDataNVX(int i, long j, long j2, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, long j3, long j4);

    public static native long nglAsyncCopyBufferSubDataNVX(int i, long j, long j2, int i2, int i3, int i4, int i5, long j3, long j4, long j5, int i6, long j6, long j7);

    public static native void glUploadGpuMaskNVX(@NativeType("GLbitfield") int i);

    public static native void nglMulticastViewportArrayvNVX(int i, int i2, int i3, long j);

    public static native void nglMulticastScissorArrayvNVX(int i, int i2, int i3, long j);

    public static native void glMulticastViewportPositionWScaleNVX(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2);

    static {
        GL.initialize();
    }

    protected NVXGpuMulticast2() {
        throw new UnsupportedOperationException();
    }

    @NativeType("GLuint")
    public static int glAsyncCopyImageSubDataNVX(@NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLuint64 const *") LongBuffer longBuffer, @NativeType("GLuint") int i, @NativeType("GLbitfield") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLint") int i7, @NativeType("GLint") int i8, @NativeType("GLuint") int i9, @NativeType("GLenum") int i10, @NativeType("GLint") int i11, @NativeType("GLint") int i12, @NativeType("GLint") int i13, @NativeType("GLint") int i14, @NativeType("GLsizei") int i15, @NativeType("GLsizei") int i16, @NativeType("GLsizei") int i17, @NativeType("GLuint const *") IntBuffer intBuffer2, @NativeType("GLuint64 const *") LongBuffer longBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, intBuffer.remaining());
            Checks.check((Buffer) longBuffer2, intBuffer2.remaining());
        }
        return nglAsyncCopyImageSubDataNVX(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(longBuffer), i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, intBuffer2.remaining(), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(longBuffer2));
    }

    @NativeType("GLsync")
    public static long glAsyncCopyBufferSubDataNVX(@NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLuint64 const *") LongBuffer longBuffer, @NativeType("GLuint") int i, @NativeType("GLbitfield") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint") int i4, @NativeType("GLintptr") long j, @NativeType("GLintptr") long j2, @NativeType("GLsizeiptr") long j3, @NativeType("GLuint const *") IntBuffer intBuffer2, @NativeType("GLuint64 const *") LongBuffer longBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, intBuffer.remaining());
            Checks.check((Buffer) longBuffer2, intBuffer2.remaining());
        }
        return nglAsyncCopyBufferSubDataNVX(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(longBuffer), i, i2, i3, i4, j, j2, j3, intBuffer2.remaining(), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(longBuffer2));
    }

    public static void glMulticastViewportArrayvNVX(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglMulticastViewportArrayvNVX(i, i2, floatBuffer.remaining() >> 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMulticastScissorArrayvNVX(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        nglMulticastScissorArrayvNVX(i, i2, intBuffer.remaining() >> 2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("GLuint")
    public static int glAsyncCopyImageSubDataNVX(@NativeType("GLuint const *") int[] iArr, @NativeType("GLuint64 const *") long[] jArr, @NativeType("GLuint") int i, @NativeType("GLbitfield") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLint") int i7, @NativeType("GLint") int i8, @NativeType("GLuint") int i9, @NativeType("GLenum") int i10, @NativeType("GLint") int i11, @NativeType("GLint") int i12, @NativeType("GLint") int i13, @NativeType("GLint") int i14, @NativeType("GLsizei") int i15, @NativeType("GLsizei") int i16, @NativeType("GLsizei") int i17, @NativeType("GLuint const *") int[] iArr2, @NativeType("GLuint64 const *") long[] jArr2) {
        long j = GL.getICD().glAsyncCopyImageSubDataNVX;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, iArr.length);
            Checks.check(jArr2, iArr2.length);
        }
        return JNI.callPPPPI(iArr.length, iArr, jArr, i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, iArr2.length, iArr2, jArr2, j);
    }

    @NativeType("GLsync")
    public static long glAsyncCopyBufferSubDataNVX(@NativeType("GLuint const *") int[] iArr, @NativeType("GLuint64 const *") long[] jArr, @NativeType("GLuint") int i, @NativeType("GLbitfield") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint") int i4, @NativeType("GLintptr") long j, @NativeType("GLintptr") long j2, @NativeType("GLsizeiptr") long j3, @NativeType("GLuint const *") int[] iArr2, @NativeType("GLuint64 const *") long[] jArr2) {
        long j4 = GL.getICD().glAsyncCopyBufferSubDataNVX;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(jArr, iArr.length);
            Checks.check(jArr2, iArr2.length);
        }
        return JNI.callPPPPPPPP(iArr.length, iArr, jArr, i, i2, i3, i4, j, j2, j3, iArr2.length, iArr2, jArr2, j4);
    }

    public static void glMulticastViewportArrayvNVX(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMulticastViewportArrayvNVX;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length >> 2, fArr, j);
    }

    public static void glMulticastScissorArrayvNVX(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glMulticastScissorArrayvNVX;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr.length >> 2, iArr, j);
    }
}
