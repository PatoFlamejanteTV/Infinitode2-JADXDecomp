package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVTimelineSemaphore.class */
public class NVTimelineSemaphore {
    public static final int GL_SEMAPHORE_TYPE_NV = 38323;
    public static final int GL_SEMAPHORE_TYPE_BINARY_NV = 38324;
    public static final int GL_SEMAPHORE_TYPE_TIMELINE_NV = 38325;
    public static final int GL_TIMELINE_SEMAPHORE_VALUE_NV = 38293;
    public static final int GL_MAX_TIMELINE_SEMAPHORE_VALUE_DIFFERENCE_NV = 38326;

    public static native void nglCreateSemaphoresNV(int i, long j);

    public static native void nglSemaphoreParameterivNV(int i, int i2, long j);

    public static native void nglGetSemaphoreParameterivNV(int i, int i2, long j);

    static {
        GL.initialize();
    }

    protected NVTimelineSemaphore() {
        throw new UnsupportedOperationException();
    }

    public static void glCreateSemaphoresNV(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglCreateSemaphoresNV(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glCreateSemaphoresNV() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglCreateSemaphoresNV(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glSemaphoreParameterivNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglSemaphoreParameterivNV(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetSemaphoreParameterivNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetSemaphoreParameterivNV(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glCreateSemaphoresNV(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glCreateSemaphoresNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glSemaphoreParameterivNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glSemaphoreParameterivNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetSemaphoreParameterivNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetSemaphoreParameterivNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }
}
