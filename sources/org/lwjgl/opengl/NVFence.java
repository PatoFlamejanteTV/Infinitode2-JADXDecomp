package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVFence.class */
public class NVFence {
    public static final int GL_ALL_COMPLETED_NV = 34034;
    public static final int GL_FENCE_STATUS_NV = 34035;
    public static final int GL_FENCE_CONDITION_NV = 34036;

    public static native void nglDeleteFencesNV(int i, long j);

    public static native void nglGenFencesNV(int i, long j);

    @NativeType("GLboolean")
    public static native boolean glIsFenceNV(@NativeType("GLuint") int i);

    @NativeType("GLboolean")
    public static native boolean glTestFenceNV(@NativeType("GLuint") int i);

    public static native void nglGetFenceivNV(int i, int i2, long j);

    public static native void glFinishFenceNV(@NativeType("GLuint") int i);

    public static native void glSetFenceNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2);

    static {
        GL.initialize();
    }

    protected NVFence() {
        throw new UnsupportedOperationException();
    }

    public static void glDeleteFencesNV(@NativeType("GLuint const *") IntBuffer intBuffer) {
        nglDeleteFencesNV(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glDeleteFencesNV(@NativeType("GLuint const *") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDeleteFencesNV(1, MemoryUtil.memAddress(stackGet.ints(i)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGenFencesNV(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglGenFencesNV(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGenFencesNV() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGenFencesNV(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetFenceivNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetFenceivNV(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetFenceiNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetFenceivNV(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDeleteFencesNV(@NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glDeleteFencesNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glGenFencesNV(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGenFencesNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glGetFenceivNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetFenceivNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }
}
