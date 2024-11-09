package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVGPUMulticast.class */
public class NVGPUMulticast {
    public static final int GL_PER_GPU_STORAGE_BIT_NV = 2048;
    public static final int GL_MULTICAST_GPUS_NV = 37562;
    public static final int GL_RENDER_GPU_MASK_NV = 38232;
    public static final int GL_PER_GPU_STORAGE_NV = 38216;
    public static final int GL_MULTICAST_PROGRAMMABLE_SAMPLE_LOCATION_NV = 38217;

    public static native void glRenderGpuMaskNV(@NativeType("GLbitfield") int i);

    public static native void nglMulticastBufferSubDataNV(int i, int i2, long j, long j2, long j3);

    public static native void glMulticastCopyBufferSubDataNV(@NativeType("GLuint") int i, @NativeType("GLbitfield") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint") int i4, @NativeType("GLintptr") long j, @NativeType("GLintptr") long j2, @NativeType("GLsizeiptr") long j3);

    public static native void glMulticastCopyImageSubDataNV(@NativeType("GLuint") int i, @NativeType("GLbitfield") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLint") int i7, @NativeType("GLint") int i8, @NativeType("GLuint") int i9, @NativeType("GLenum") int i10, @NativeType("GLint") int i11, @NativeType("GLint") int i12, @NativeType("GLint") int i13, @NativeType("GLint") int i14, @NativeType("GLsizei") int i15, @NativeType("GLsizei") int i16, @NativeType("GLsizei") int i17);

    public static native void glMulticastBlitFramebufferNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLint") int i7, @NativeType("GLint") int i8, @NativeType("GLint") int i9, @NativeType("GLint") int i10, @NativeType("GLbitfield") int i11, @NativeType("GLenum") int i12);

    public static native void nglMulticastFramebufferSampleLocationsfvNV(int i, int i2, int i3, int i4, long j);

    public static native void glMulticastBarrierNV();

    public static native void glMulticastWaitSyncNV(@NativeType("GLuint") int i, @NativeType("GLbitfield") int i2);

    public static native void nglMulticastGetQueryObjectivNV(int i, int i2, int i3, long j);

    public static native void nglMulticastGetQueryObjectuivNV(int i, int i2, int i3, long j);

    public static native void nglMulticastGetQueryObjecti64vNV(int i, int i2, int i3, long j);

    public static native void nglMulticastGetQueryObjectui64vNV(int i, int i2, int i3, long j);

    static {
        GL.initialize();
    }

    protected NVGPUMulticast() {
        throw new UnsupportedOperationException();
    }

    public static void glMulticastBufferSubDataNV(@NativeType("GLbitfield") int i, @NativeType("GLuint") int i2, @NativeType("GLintptr") long j, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglMulticastBufferSubDataNV(i, i2, j, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glMulticastBufferSubDataNV(@NativeType("GLbitfield") int i, @NativeType("GLuint") int i2, @NativeType("GLintptr") long j, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglMulticastBufferSubDataNV(i, i2, j, Integer.toUnsignedLong(shortBuffer.remaining()) << 1, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glMulticastBufferSubDataNV(@NativeType("GLbitfield") int i, @NativeType("GLuint") int i2, @NativeType("GLintptr") long j, @NativeType("void const *") IntBuffer intBuffer) {
        nglMulticastBufferSubDataNV(i, i2, j, Integer.toUnsignedLong(intBuffer.remaining()) << 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glMulticastBufferSubDataNV(@NativeType("GLbitfield") int i, @NativeType("GLuint") int i2, @NativeType("GLintptr") long j, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglMulticastBufferSubDataNV(i, i2, j, Integer.toUnsignedLong(floatBuffer.remaining()) << 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMulticastBufferSubDataNV(@NativeType("GLbitfield") int i, @NativeType("GLuint") int i2, @NativeType("GLintptr") long j, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglMulticastBufferSubDataNV(i, i2, j, Integer.toUnsignedLong(doubleBuffer.remaining()) << 3, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glMulticastFramebufferSampleLocationsfvNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglMulticastFramebufferSampleLocationsfvNV(i, i2, i3, floatBuffer.remaining() >> 1, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMulticastGetQueryObjectivNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglMulticastGetQueryObjectivNV(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glMulticastGetQueryObjectiNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglMulticastGetQueryObjectivNV(i, i2, i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glMulticastGetQueryObjectuivNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglMulticastGetQueryObjectuivNV(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glMulticastGetQueryObjectuiNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglMulticastGetQueryObjectuivNV(i, i2, i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glMulticastGetQueryObjecti64vNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint64 *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglMulticastGetQueryObjecti64vNV(i, i2, i3, MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("void")
    public static long glMulticastGetQueryObjecti64NV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglMulticastGetQueryObjecti64vNV(i, i2, i3, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glMulticastGetQueryObjectui64vNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint64 *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglMulticastGetQueryObjectui64vNV(i, i2, i3, MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("void")
    public static long glMulticastGetQueryObjectui64NV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglMulticastGetQueryObjectui64vNV(i, i2, i3, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glMulticastBufferSubDataNV(@NativeType("GLbitfield") int i, @NativeType("GLuint") int i2, @NativeType("GLintptr") long j, @NativeType("void const *") short[] sArr) {
        long j2 = GL.getICD().glMulticastBufferSubDataNV;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, i2, j, Integer.toUnsignedLong(sArr.length) << 1, sArr, j2);
    }

    public static void glMulticastBufferSubDataNV(@NativeType("GLbitfield") int i, @NativeType("GLuint") int i2, @NativeType("GLintptr") long j, @NativeType("void const *") int[] iArr) {
        long j2 = GL.getICD().glMulticastBufferSubDataNV;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, i2, j, Integer.toUnsignedLong(iArr.length) << 2, iArr, j2);
    }

    public static void glMulticastBufferSubDataNV(@NativeType("GLbitfield") int i, @NativeType("GLuint") int i2, @NativeType("GLintptr") long j, @NativeType("void const *") float[] fArr) {
        long j2 = GL.getICD().glMulticastBufferSubDataNV;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, i2, j, Integer.toUnsignedLong(fArr.length) << 2, fArr, j2);
    }

    public static void glMulticastBufferSubDataNV(@NativeType("GLbitfield") int i, @NativeType("GLuint") int i2, @NativeType("GLintptr") long j, @NativeType("void const *") double[] dArr) {
        long j2 = GL.getICD().glMulticastBufferSubDataNV;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, i2, j, Integer.toUnsignedLong(dArr.length) << 3, dArr, j2);
    }

    public static void glMulticastFramebufferSampleLocationsfvNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMulticastFramebufferSampleLocationsfvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, fArr.length >> 1, fArr, j);
    }

    public static void glMulticastGetQueryObjectivNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glMulticastGetQueryObjectivNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glMulticastGetQueryObjectuivNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glMulticastGetQueryObjectuivNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glMulticastGetQueryObjecti64vNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint64 *") long[] jArr) {
        long j = GL.getICD().glMulticastGetQueryObjecti64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, i2, i3, jArr, j);
    }

    public static void glMulticastGetQueryObjectui64vNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint64 *") long[] jArr) {
        long j = GL.getICD().glMulticastGetQueryObjectui64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, i2, i3, jArr, j);
    }
}
