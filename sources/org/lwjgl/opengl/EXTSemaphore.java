package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTSemaphore.class */
public class EXTSemaphore {
    public static final int GL_NUM_DEVICE_UUIDS_EXT = 38294;
    public static final int GL_DEVICE_UUID_EXT = 38295;
    public static final int GL_DRIVER_UUID_EXT = 38296;
    public static final int GL_UUID_SIZE_EXT = 16;
    public static final int GL_LAYOUT_GENERAL_EXT = 38285;
    public static final int GL_LAYOUT_COLOR_ATTACHMENT_EXT = 38286;
    public static final int GL_LAYOUT_DEPTH_STENCIL_ATTACHMENT_EXT = 38287;
    public static final int GL_LAYOUT_DEPTH_STENCIL_READ_ONLY_EXT = 38288;
    public static final int GL_LAYOUT_SHADER_READ_ONLY_EXT = 38289;
    public static final int GL_LAYOUT_TRANSFER_SRC_EXT = 38290;
    public static final int GL_LAYOUT_TRANSFER_DST_EXT = 38291;
    public static final int GL_LAYOUT_DEPTH_READ_ONLY_STENCIL_ATTACHMENT_EXT = 38192;
    public static final int GL_LAYOUT_DEPTH_ATTACHMENT_STENCIL_READ_ONLY_EXT = 38193;

    public static native void nglGenSemaphoresEXT(int i, long j);

    public static native void nglDeleteSemaphoresEXT(int i, long j);

    @NativeType("GLboolean")
    public static native boolean glIsSemaphoreEXT(@NativeType("GLuint") int i);

    public static native void nglSemaphoreParameterui64vEXT(int i, int i2, long j);

    public static native void nglGetSemaphoreParameterui64vEXT(int i, int i2, long j);

    public static native void nglWaitSemaphoreEXT(int i, int i2, long j, int i3, long j2, long j3);

    public static native void nglSignalSemaphoreEXT(int i, int i2, long j, int i3, long j2, long j3);

    static {
        GL.initialize();
    }

    protected EXTSemaphore() {
        throw new UnsupportedOperationException();
    }

    public static void nglGetUnsignedBytevEXT(int i, long j) {
        EXTMemoryObject.nglGetUnsignedBytevEXT(i, j);
    }

    public static void glGetUnsignedBytevEXT(@NativeType("GLenum") int i, @NativeType("GLubyte *") ByteBuffer byteBuffer) {
        EXTMemoryObject.glGetUnsignedBytevEXT(i, byteBuffer);
    }

    public static void nglGetUnsignedBytei_vEXT(int i, int i2, long j) {
        EXTMemoryObject.nglGetUnsignedBytei_vEXT(i, i2, j);
    }

    public static void glGetUnsignedBytei_vEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLubyte *") ByteBuffer byteBuffer) {
        EXTMemoryObject.glGetUnsignedBytei_vEXT(i, i2, byteBuffer);
    }

    public static void glGenSemaphoresEXT(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglGenSemaphoresEXT(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGenSemaphoresEXT() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGenSemaphoresEXT(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDeleteSemaphoresEXT(@NativeType("GLuint const *") IntBuffer intBuffer) {
        nglDeleteSemaphoresEXT(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glDeleteSemaphoresEXT(@NativeType("GLuint const *") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDeleteSemaphoresEXT(1, MemoryUtil.memAddress(stackGet.ints(i)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glSemaphoreParameterui64vEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64 const *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglSemaphoreParameterui64vEXT(i, i2, MemoryUtil.memAddress(longBuffer));
    }

    public static void glSemaphoreParameterui64EXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64 const *") long j) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglSemaphoreParameterui64vEXT(i, i2, MemoryUtil.memAddress(stackGet.longs(j)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetSemaphoreParameterui64vEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64 *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglGetSemaphoreParameterui64vEXT(i, i2, MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("void")
    public static long glGetSemaphoreParameterui64EXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetSemaphoreParameterui64vEXT(i, i2, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glWaitSemaphoreEXT(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLuint const *") IntBuffer intBuffer2, @NativeType("GLenum const *") IntBuffer intBuffer3) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer3, intBuffer2.remaining());
        }
        nglWaitSemaphoreEXT(i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), intBuffer2.remaining(), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3));
    }

    public static void glSignalSemaphoreEXT(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLuint const *") IntBuffer intBuffer2, @NativeType("GLenum const *") IntBuffer intBuffer3) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer3, intBuffer2.remaining());
        }
        nglSignalSemaphoreEXT(i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), intBuffer2.remaining(), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3));
    }

    public static void glGenSemaphoresEXT(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGenSemaphoresEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glDeleteSemaphoresEXT(@NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glDeleteSemaphoresEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glSemaphoreParameterui64vEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64 const *") long[] jArr) {
        long j = GL.getICD().glSemaphoreParameterui64vEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, i2, jArr, j);
    }

    public static void glGetSemaphoreParameterui64vEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64 *") long[] jArr) {
        long j = GL.getICD().glGetSemaphoreParameterui64vEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, i2, jArr, j);
    }

    public static void glWaitSemaphoreEXT(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr, @NativeType("GLuint const *") int[] iArr2, @NativeType("GLenum const *") int[] iArr3) {
        long j = GL.getICD().glWaitSemaphoreEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr3, iArr2.length);
        }
        JNI.callPPPV(i, iArr.length, iArr, iArr2.length, iArr2, iArr3, j);
    }

    public static void glSignalSemaphoreEXT(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr, @NativeType("GLuint const *") int[] iArr2, @NativeType("GLenum const *") int[] iArr3) {
        long j = GL.getICD().glSignalSemaphoreEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr3, iArr2.length);
        }
        JNI.callPPPV(i, iArr.length, iArr, iArr2.length, iArr2, iArr3, j);
    }
}
