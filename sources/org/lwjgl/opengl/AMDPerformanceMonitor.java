package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/AMDPerformanceMonitor.class */
public class AMDPerformanceMonitor {
    public static final int GL_COUNTER_TYPE_AMD = 35776;
    public static final int GL_COUNTER_RANGE_AMD = 35777;
    public static final int GL_UNSIGNED_INT64_AMD = 35778;
    public static final int GL_PERCENTAGE_AMD = 35779;
    public static final int GL_PERFMON_RESULT_AVAILABLE_AMD = 35780;
    public static final int GL_PERFMON_RESULT_SIZE_AMD = 35781;
    public static final int GL_PERFMON_RESULT_AMD = 35782;

    public static native void nglGetPerfMonitorGroupsAMD(long j, int i, long j2);

    public static native void nglGetPerfMonitorCountersAMD(int i, long j, long j2, int i2, long j3);

    public static native void nglGetPerfMonitorGroupStringAMD(int i, int i2, long j, long j2);

    public static native void nglGetPerfMonitorCounterStringAMD(int i, int i2, int i3, long j, long j2);

    public static native void nglGetPerfMonitorCounterInfoAMD(int i, int i2, int i3, long j);

    public static native void nglGenPerfMonitorsAMD(int i, long j);

    public static native void nglDeletePerfMonitorsAMD(int i, long j);

    public static native void nglSelectPerfMonitorCountersAMD(int i, boolean z, int i2, int i3, long j);

    public static native void glBeginPerfMonitorAMD(@NativeType("GLuint") int i);

    public static native void glEndPerfMonitorAMD(@NativeType("GLuint") int i);

    public static native void nglGetPerfMonitorCounterDataAMD(int i, int i2, int i3, long j, long j2);

    static {
        GL.initialize();
    }

    protected AMDPerformanceMonitor() {
        throw new UnsupportedOperationException();
    }

    public static void glGetPerfMonitorGroupsAMD(@NativeType("GLint *") IntBuffer intBuffer, @NativeType("GLuint *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
        }
        nglGetPerfMonitorGroupsAMD(MemoryUtil.memAddressSafe(intBuffer), Checks.remainingSafe(intBuffer2), MemoryUtil.memAddressSafe(intBuffer2));
    }

    public static void glGetPerfMonitorCountersAMD(@NativeType("GLuint") int i, @NativeType("GLint *") IntBuffer intBuffer, @NativeType("GLint *") IntBuffer intBuffer2, @NativeType("GLuint *") IntBuffer intBuffer3) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
        }
        nglGetPerfMonitorCountersAMD(i, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), intBuffer3.remaining(), MemoryUtil.memAddress(intBuffer3));
    }

    public static void glGetPerfMonitorGroupStringAMD(@NativeType("GLuint") int i, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetPerfMonitorGroupStringAMD(i, byteBuffer.remaining(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetPerfMonitorCounterStringAMD(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
        }
        nglGetPerfMonitorCounterStringAMD(i, i2, Checks.remainingSafe(byteBuffer), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glGetPerfMonitorCounterInfoAMD(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("void *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 4);
        }
        nglGetPerfMonitorCounterInfoAMD(i, i2, i3, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetPerfMonitorCounterInfoAMD(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("void *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetPerfMonitorCounterInfoAMD(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetPerfMonitorCounterInfoAMD(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("void *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetPerfMonitorCounterInfoAMD(i, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGenPerfMonitorsAMD(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglGenPerfMonitorsAMD(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGenPerfMonitorsAMD() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGenPerfMonitorsAMD(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDeletePerfMonitorsAMD(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglDeletePerfMonitorsAMD(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glDeletePerfMonitorsAMD(@NativeType("GLuint *") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDeletePerfMonitorsAMD(1, MemoryUtil.memAddress(stackGet.ints(i)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glSelectPerfMonitorCountersAMD(@NativeType("GLuint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLuint") int i2, @NativeType("GLuint *") IntBuffer intBuffer) {
        nglSelectPerfMonitorCountersAMD(i, z, i2, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetPerfMonitorCounterDataAMD(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") IntBuffer intBuffer, @NativeType("GLint *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer2, 1);
        }
        nglGetPerfMonitorCounterDataAMD(i, i2, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddressSafe(intBuffer2));
    }

    public static void glGetPerfMonitorGroupsAMD(@NativeType("GLint *") int[] iArr, @NativeType("GLuint *") int[] iArr2) {
        long j = GL.getICD().glGetPerfMonitorGroupsAMD;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
        }
        JNI.callPPV(iArr, Checks.lengthSafe(iArr2), iArr2, j);
    }

    public static void glGetPerfMonitorCountersAMD(@NativeType("GLuint") int i, @NativeType("GLint *") int[] iArr, @NativeType("GLint *") int[] iArr2, @NativeType("GLuint *") int[] iArr3) {
        long j = GL.getICD().glGetPerfMonitorCountersAMD;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
        }
        JNI.callPPPV(i, iArr, iArr2, iArr3.length, iArr3, j);
    }

    public static void glGetPerfMonitorGroupStringAMD(@NativeType("GLuint") int i, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetPerfMonitorGroupStringAMD;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPPV(i, byteBuffer.remaining(), iArr, MemoryUtil.memAddress(byteBuffer), j);
    }

    public static void glGetPerfMonitorCounterStringAMD(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetPerfMonitorCounterStringAMD;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
        }
        JNI.callPPV(i, i2, Checks.remainingSafe(byteBuffer), iArr, MemoryUtil.memAddressSafe(byteBuffer), j);
    }

    public static void glGetPerfMonitorCounterInfoAMD(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("void *") int[] iArr) {
        long j = GL.getICD().glGetPerfMonitorCounterInfoAMD;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glGetPerfMonitorCounterInfoAMD(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("void *") float[] fArr) {
        long j = GL.getICD().glGetPerfMonitorCounterInfoAMD;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, i3, fArr, j);
    }

    public static void glGenPerfMonitorsAMD(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGenPerfMonitorsAMD;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glDeletePerfMonitorsAMD(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glDeletePerfMonitorsAMD;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glSelectPerfMonitorCountersAMD(@NativeType("GLuint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLuint") int i2, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glSelectPerfMonitorCountersAMD;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, z, i2, iArr.length, iArr, j);
    }

    public static void glGetPerfMonitorCounterDataAMD(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") int[] iArr, @NativeType("GLint *") int[] iArr2) {
        long j = GL.getICD().glGetPerfMonitorCounterDataAMD;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr2, 1);
        }
        JNI.callPPV(i, i2, iArr.length, iArr, iArr2, j);
    }
}
