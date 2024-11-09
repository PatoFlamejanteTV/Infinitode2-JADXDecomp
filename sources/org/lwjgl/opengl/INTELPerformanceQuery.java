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

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/INTELPerformanceQuery.class */
public class INTELPerformanceQuery {
    public static final int GL_PERFQUERY_SINGLE_CONTEXT_INTEL = 0;
    public static final int GL_PERFQUERY_GLOBAL_CONTEXT_INTEL = 1;
    public static final int GL_PERFQUERY_WAIT_INTEL = 33787;
    public static final int GL_PERFQUERY_FLUSH_INTEL = 33786;
    public static final int GL_PERFQUERY_DONOT_FLUSH_INTEL = 33785;
    public static final int GL_PERFQUERY_COUNTER_EVENT_INTEL = 38128;
    public static final int GL_PERFQUERY_COUNTER_DURATION_NORM_INTEL = 38129;
    public static final int GL_PERFQUERY_COUNTER_DURATION_RAW_INTEL = 38130;
    public static final int GL_PERFQUERY_COUNTER_THROUGHPUT_INTEL = 38131;
    public static final int GL_PERFQUERY_COUNTER_RAW_INTEL = 38132;
    public static final int GL_PERFQUERY_COUNTER_TIMESTAMP_INTEL = 38133;
    public static final int GL_PERFQUERY_COUNTER_DATA_UINT32_INTEL = 38136;
    public static final int GL_PERFQUERY_COUNTER_DATA_UINT64_INTEL = 38137;
    public static final int GL_PERFQUERY_COUNTER_DATA_FLOAT_INTEL = 38138;
    public static final int GL_PERFQUERY_COUNTER_DATA_DOUBLE_INTEL = 38139;
    public static final int GL_PERFQUERY_COUNTER_DATA_BOOL32_INTEL = 38140;
    public static final int GL_PERFQUERY_QUERY_NAME_LENGTH_MAX_INTEL = 38141;
    public static final int GL_PERFQUERY_COUNTER_NAME_LENGTH_MAX_INTEL = 38142;
    public static final int GL_PERFQUERY_COUNTER_DESC_LENGTH_MAX_INTEL = 38143;
    public static final int GL_PERFQUERY_GPA_EXTENDED_COUNTERS_INTEL = 38144;

    public static native void glBeginPerfQueryINTEL(@NativeType("GLuint") int i);

    public static native void nglCreatePerfQueryINTEL(int i, long j);

    public static native void glDeletePerfQueryINTEL(@NativeType("GLuint") int i);

    public static native void glEndPerfQueryINTEL(@NativeType("GLuint") int i);

    public static native void nglGetFirstPerfQueryIdINTEL(long j);

    public static native void nglGetNextPerfQueryIdINTEL(int i, long j);

    public static native void nglGetPerfCounterInfoINTEL(int i, int i2, int i3, long j, int i4, long j2, long j3, long j4, long j5, long j6, long j7);

    public static native void nglGetPerfQueryDataINTEL(int i, int i2, int i3, long j, long j2);

    public static native void nglGetPerfQueryIdByNameINTEL(long j, long j2);

    public static native void nglGetPerfQueryInfoINTEL(int i, int i2, long j, long j2, long j3, long j4, long j5);

    static {
        GL.initialize();
    }

    protected INTELPerformanceQuery() {
        throw new UnsupportedOperationException();
    }

    public static void glCreatePerfQueryINTEL(@NativeType("GLuint") int i, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglCreatePerfQueryINTEL(i, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glCreatePerfQueryINTEL(@NativeType("GLuint") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglCreatePerfQueryINTEL(i, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetFirstPerfQueryIdINTEL(@NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetFirstPerfQueryIdINTEL(MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetFirstPerfQueryIdINTEL() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetFirstPerfQueryIdINTEL(MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetNextPerfQueryIdINTEL(@NativeType("GLuint") int i, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetNextPerfQueryIdINTEL(i, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetNextPerfQueryIdINTEL(@NativeType("GLuint") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetNextPerfQueryIdINTEL(i, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetPerfCounterInfoINTEL(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLchar *") ByteBuffer byteBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer2, @NativeType("GLuint *") IntBuffer intBuffer, @NativeType("GLuint *") IntBuffer intBuffer2, @NativeType("GLuint *") IntBuffer intBuffer3, @NativeType("GLuint *") IntBuffer intBuffer4, @NativeType("GLuint64 *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
            Checks.check((Buffer) intBuffer4, 1);
            Checks.check((Buffer) longBuffer, 1);
        }
        nglGetPerfCounterInfoINTEL(i, i2, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), byteBuffer2.remaining(), MemoryUtil.memAddress(byteBuffer2), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), MemoryUtil.memAddress(intBuffer4), MemoryUtil.memAddress(longBuffer));
    }

    public static void glGetPerfQueryDataINTEL(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("void *") ByteBuffer byteBuffer, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetPerfQueryDataINTEL(i, i2, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetPerfQueryIdByNameINTEL(@NativeType("GLchar *") ByteBuffer byteBuffer, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetPerfQueryIdByNameINTEL(MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetPerfQueryIdByNameINTEL(@NativeType("GLchar *") CharSequence charSequence, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            nglGetPerfQueryIdByNameINTEL(stackGet.getPointerAddress(), MemoryUtil.memAddress(intBuffer));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void")
    public static int glGetPerfQueryIdByNameINTEL(@NativeType("GLchar *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            long pointerAddress = stackGet.getPointerAddress();
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetPerfQueryIdByNameINTEL(pointerAddress, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetPerfQueryInfoINTEL(@NativeType("GLuint") int i, @NativeType("GLchar *") ByteBuffer byteBuffer, @NativeType("GLuint *") IntBuffer intBuffer, @NativeType("GLuint *") IntBuffer intBuffer2, @NativeType("GLuint *") IntBuffer intBuffer3, @NativeType("GLuint *") IntBuffer intBuffer4) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
            Checks.check((Buffer) intBuffer4, 1);
        }
        nglGetPerfQueryInfoINTEL(i, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), MemoryUtil.memAddress(intBuffer4));
    }

    public static void glCreatePerfQueryINTEL(@NativeType("GLuint") int i, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glCreatePerfQueryINTEL;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glGetFirstPerfQueryIdINTEL(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetFirstPerfQueryIdINTEL;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(iArr, j);
    }

    public static void glGetNextPerfQueryIdINTEL(@NativeType("GLuint") int i, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetNextPerfQueryIdINTEL;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glGetPerfCounterInfoINTEL(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLchar *") ByteBuffer byteBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer2, @NativeType("GLuint *") int[] iArr, @NativeType("GLuint *") int[] iArr2, @NativeType("GLuint *") int[] iArr3, @NativeType("GLuint *") int[] iArr4, @NativeType("GLuint64 *") long[] jArr) {
        long j = GL.getICD().glGetPerfCounterInfoINTEL;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
            Checks.check(iArr4, 1);
            Checks.check(jArr, 1);
        }
        JNI.callPPPPPPPV(i, i2, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), byteBuffer2.remaining(), MemoryUtil.memAddress(byteBuffer2), iArr, iArr2, iArr3, iArr4, jArr, j);
    }

    public static void glGetPerfQueryDataINTEL(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("void *") ByteBuffer byteBuffer, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetPerfQueryDataINTEL;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPPV(i, i2, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), iArr, j);
    }

    public static void glGetPerfQueryIdByNameINTEL(@NativeType("GLchar *") ByteBuffer byteBuffer, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetPerfQueryIdByNameINTEL;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkNT1(byteBuffer);
            Checks.check(iArr, 1);
        }
        JNI.callPPV(MemoryUtil.memAddress(byteBuffer), iArr, j);
    }

    public static void glGetPerfQueryIdByNameINTEL(@NativeType("GLchar *") CharSequence charSequence, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetPerfQueryIdByNameINTEL;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            JNI.callPPV(stackGet.getPointerAddress(), iArr, j);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetPerfQueryInfoINTEL(@NativeType("GLuint") int i, @NativeType("GLchar *") ByteBuffer byteBuffer, @NativeType("GLuint *") int[] iArr, @NativeType("GLuint *") int[] iArr2, @NativeType("GLuint *") int[] iArr3, @NativeType("GLuint *") int[] iArr4) {
        long j = GL.getICD().glGetPerfQueryInfoINTEL;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
            Checks.check(iArr4, 1);
        }
        JNI.callPPPPPV(i, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), iArr, iArr2, iArr3, iArr4, j);
    }
}
