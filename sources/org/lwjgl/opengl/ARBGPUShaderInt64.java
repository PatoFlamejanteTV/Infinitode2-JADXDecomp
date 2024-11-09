package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.LongBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBGPUShaderInt64.class */
public class ARBGPUShaderInt64 {
    public static final int GL_INT64_ARB = 5134;
    public static final int GL_UNSIGNED_INT64_ARB = 5135;
    public static final int GL_INT64_VEC2_ARB = 36841;
    public static final int GL_INT64_VEC3_ARB = 36842;
    public static final int GL_INT64_VEC4_ARB = 36843;
    public static final int GL_UNSIGNED_INT64_VEC2_ARB = 36853;
    public static final int GL_UNSIGNED_INT64_VEC3_ARB = 36854;
    public static final int GL_UNSIGNED_INT64_VEC4_ARB = 36855;

    public static native void glUniform1i64ARB(@NativeType("GLint") int i, @NativeType("GLint64") long j);

    public static native void nglUniform1i64vARB(int i, int i2, long j);

    public static native void glProgramUniform1i64ARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64") long j);

    public static native void nglProgramUniform1i64vARB(int i, int i2, int i3, long j);

    public static native void glUniform2i64ARB(@NativeType("GLint") int i, @NativeType("GLint64") long j, @NativeType("GLint64") long j2);

    public static native void nglUniform2i64vARB(int i, int i2, long j);

    public static native void glProgramUniform2i64ARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64") long j, @NativeType("GLint64") long j2);

    public static native void nglProgramUniform2i64vARB(int i, int i2, int i3, long j);

    public static native void glUniform3i64ARB(@NativeType("GLint") int i, @NativeType("GLint64") long j, @NativeType("GLint64") long j2, @NativeType("GLint64") long j3);

    public static native void nglUniform3i64vARB(int i, int i2, long j);

    public static native void glProgramUniform3i64ARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64") long j, @NativeType("GLint64") long j2, @NativeType("GLint64") long j3);

    public static native void nglProgramUniform3i64vARB(int i, int i2, int i3, long j);

    public static native void glUniform4i64ARB(@NativeType("GLint") int i, @NativeType("GLint64") long j, @NativeType("GLint64") long j2, @NativeType("GLint64") long j3, @NativeType("GLint64") long j4);

    public static native void nglUniform4i64vARB(int i, int i2, long j);

    public static native void glProgramUniform4i64ARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64") long j, @NativeType("GLint64") long j2, @NativeType("GLint64") long j3, @NativeType("GLint64") long j4);

    public static native void nglProgramUniform4i64vARB(int i, int i2, int i3, long j);

    public static native void glUniform1ui64ARB(@NativeType("GLint") int i, @NativeType("GLuint64") long j);

    public static native void nglUniform1ui64vARB(int i, int i2, long j);

    public static native void glProgramUniform1ui64ARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64") long j);

    public static native void nglProgramUniform1ui64vARB(int i, int i2, int i3, long j);

    public static native void glUniform2ui64ARB(@NativeType("GLint") int i, @NativeType("GLuint64") long j, @NativeType("GLuint64") long j2);

    public static native void nglUniform2ui64vARB(int i, int i2, long j);

    public static native void glProgramUniform2ui64ARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64") long j, @NativeType("GLuint64") long j2);

    public static native void nglProgramUniform2ui64vARB(int i, int i2, int i3, long j);

    public static native void glUniform3ui64ARB(@NativeType("GLint") int i, @NativeType("GLuint64") long j, @NativeType("GLuint64") long j2, @NativeType("GLuint64") long j3);

    public static native void nglUniform3ui64vARB(int i, int i2, long j);

    public static native void glProgramUniform3ui64ARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64") long j, @NativeType("GLuint64") long j2, @NativeType("GLuint64") long j3);

    public static native void nglProgramUniform3ui64vARB(int i, int i2, int i3, long j);

    public static native void glUniform4ui64ARB(@NativeType("GLint") int i, @NativeType("GLuint64") long j, @NativeType("GLuint64") long j2, @NativeType("GLuint64") long j3, @NativeType("GLuint64") long j4);

    public static native void nglUniform4ui64vARB(int i, int i2, long j);

    public static native void glProgramUniform4ui64ARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64") long j, @NativeType("GLuint64") long j2, @NativeType("GLuint64") long j3, @NativeType("GLuint64") long j4);

    public static native void nglProgramUniform4ui64vARB(int i, int i2, int i3, long j);

    public static native void nglGetUniformi64vARB(int i, int i2, long j);

    public static native void nglGetUniformui64vARB(int i, int i2, long j);

    public static native void nglGetnUniformi64vARB(int i, int i2, int i3, long j);

    public static native void nglGetnUniformui64vARB(int i, int i2, int i3, long j);

    static {
        GL.initialize();
    }

    protected ARBGPUShaderInt64() {
        throw new UnsupportedOperationException();
    }

    public static void glUniform1i64vARB(@NativeType("GLint") int i, @NativeType("GLint64 *") LongBuffer longBuffer) {
        nglUniform1i64vARB(i, longBuffer.remaining(), MemoryUtil.memAddress(longBuffer));
    }

    public static void glProgramUniform1i64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64 *") LongBuffer longBuffer) {
        nglProgramUniform1i64vARB(i, i2, longBuffer.remaining(), MemoryUtil.memAddress(longBuffer));
    }

    public static void glUniform2i64vARB(@NativeType("GLint") int i, @NativeType("GLint64 *") LongBuffer longBuffer) {
        nglUniform2i64vARB(i, longBuffer.remaining() >> 1, MemoryUtil.memAddress(longBuffer));
    }

    public static void glProgramUniform2i64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64 *") LongBuffer longBuffer) {
        nglProgramUniform2i64vARB(i, i2, longBuffer.remaining() >> 1, MemoryUtil.memAddress(longBuffer));
    }

    public static void glUniform3i64vARB(@NativeType("GLint") int i, @NativeType("GLint64 *") LongBuffer longBuffer) {
        nglUniform3i64vARB(i, longBuffer.remaining() / 3, MemoryUtil.memAddress(longBuffer));
    }

    public static void glProgramUniform3i64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64 *") LongBuffer longBuffer) {
        nglProgramUniform3i64vARB(i, i2, longBuffer.remaining() / 3, MemoryUtil.memAddress(longBuffer));
    }

    public static void glUniform4i64vARB(@NativeType("GLint") int i, @NativeType("GLint64 *") LongBuffer longBuffer) {
        nglUniform4i64vARB(i, longBuffer.remaining() >> 2, MemoryUtil.memAddress(longBuffer));
    }

    public static void glProgramUniform4i64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64 *") LongBuffer longBuffer) {
        nglProgramUniform4i64vARB(i, i2, longBuffer.remaining() >> 2, MemoryUtil.memAddress(longBuffer));
    }

    public static void glUniform1ui64vARB(@NativeType("GLint") int i, @NativeType("GLuint64 const *") LongBuffer longBuffer) {
        nglUniform1ui64vARB(i, longBuffer.remaining(), MemoryUtil.memAddress(longBuffer));
    }

    public static void glProgramUniform1ui64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64 const *") LongBuffer longBuffer) {
        nglProgramUniform1ui64vARB(i, i2, longBuffer.remaining(), MemoryUtil.memAddress(longBuffer));
    }

    public static void glUniform2ui64vARB(@NativeType("GLint") int i, @NativeType("GLuint64 const *") LongBuffer longBuffer) {
        nglUniform2ui64vARB(i, longBuffer.remaining() >> 1, MemoryUtil.memAddress(longBuffer));
    }

    public static void glProgramUniform2ui64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64 const *") LongBuffer longBuffer) {
        nglProgramUniform2ui64vARB(i, i2, longBuffer.remaining() >> 1, MemoryUtil.memAddress(longBuffer));
    }

    public static void glUniform3ui64vARB(@NativeType("GLint") int i, @NativeType("GLuint64 const *") LongBuffer longBuffer) {
        nglUniform3ui64vARB(i, longBuffer.remaining() / 3, MemoryUtil.memAddress(longBuffer));
    }

    public static void glProgramUniform3ui64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64 const *") LongBuffer longBuffer) {
        nglProgramUniform3ui64vARB(i, i2, longBuffer.remaining() / 3, MemoryUtil.memAddress(longBuffer));
    }

    public static void glUniform4ui64vARB(@NativeType("GLint") int i, @NativeType("GLuint64 const *") LongBuffer longBuffer) {
        nglUniform4ui64vARB(i, longBuffer.remaining() >> 2, MemoryUtil.memAddress(longBuffer));
    }

    public static void glProgramUniform4ui64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64 const *") LongBuffer longBuffer) {
        nglProgramUniform4ui64vARB(i, i2, longBuffer.remaining() >> 2, MemoryUtil.memAddress(longBuffer));
    }

    public static void glGetUniformi64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64 *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglGetUniformi64vARB(i, i2, MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("void")
    public static long glGetUniformi64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetUniformi64vARB(i, i2, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetUniformui64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64 *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglGetUniformui64vARB(i, i2, MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("void")
    public static long glGetUniformui64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetUniformui64vARB(i, i2, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetnUniformi64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64 *") LongBuffer longBuffer) {
        nglGetnUniformi64vARB(i, i2, longBuffer.remaining(), MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("void")
    public static long glGetnUniformi64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetnUniformi64vARB(i, i2, 1, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetnUniformui64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64 *") LongBuffer longBuffer) {
        nglGetnUniformui64vARB(i, i2, longBuffer.remaining(), MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("void")
    public static long glGetnUniformui64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetnUniformui64vARB(i, i2, 1, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glUniform1i64vARB(@NativeType("GLint") int i, @NativeType("GLint64 *") long[] jArr) {
        long j = GL.getICD().glUniform1i64vARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, jArr.length, jArr, j);
    }

    public static void glProgramUniform1i64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64 *") long[] jArr) {
        long j = GL.getICD().glProgramUniform1i64vARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, jArr.length, jArr, j);
    }

    public static void glUniform2i64vARB(@NativeType("GLint") int i, @NativeType("GLint64 *") long[] jArr) {
        long j = GL.getICD().glUniform2i64vARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, jArr.length >> 1, jArr, j);
    }

    public static void glProgramUniform2i64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64 *") long[] jArr) {
        long j = GL.getICD().glProgramUniform2i64vARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, jArr.length >> 1, jArr, j);
    }

    public static void glUniform3i64vARB(@NativeType("GLint") int i, @NativeType("GLint64 *") long[] jArr) {
        long j = GL.getICD().glUniform3i64vARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, jArr.length / 3, jArr, j);
    }

    public static void glProgramUniform3i64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64 *") long[] jArr) {
        long j = GL.getICD().glProgramUniform3i64vARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, jArr.length / 3, jArr, j);
    }

    public static void glUniform4i64vARB(@NativeType("GLint") int i, @NativeType("GLint64 *") long[] jArr) {
        long j = GL.getICD().glUniform4i64vARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, jArr.length >> 2, jArr, j);
    }

    public static void glProgramUniform4i64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64 *") long[] jArr) {
        long j = GL.getICD().glProgramUniform4i64vARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, jArr.length >> 2, jArr, j);
    }

    public static void glUniform1ui64vARB(@NativeType("GLint") int i, @NativeType("GLuint64 const *") long[] jArr) {
        long j = GL.getICD().glUniform1ui64vARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, jArr.length, jArr, j);
    }

    public static void glProgramUniform1ui64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64 const *") long[] jArr) {
        long j = GL.getICD().glProgramUniform1ui64vARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, jArr.length, jArr, j);
    }

    public static void glUniform2ui64vARB(@NativeType("GLint") int i, @NativeType("GLuint64 const *") long[] jArr) {
        long j = GL.getICD().glUniform2ui64vARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, jArr.length >> 1, jArr, j);
    }

    public static void glProgramUniform2ui64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64 const *") long[] jArr) {
        long j = GL.getICD().glProgramUniform2ui64vARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, jArr.length >> 1, jArr, j);
    }

    public static void glUniform3ui64vARB(@NativeType("GLint") int i, @NativeType("GLuint64 const *") long[] jArr) {
        long j = GL.getICD().glUniform3ui64vARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, jArr.length / 3, jArr, j);
    }

    public static void glProgramUniform3ui64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64 const *") long[] jArr) {
        long j = GL.getICD().glProgramUniform3ui64vARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, jArr.length / 3, jArr, j);
    }

    public static void glUniform4ui64vARB(@NativeType("GLint") int i, @NativeType("GLuint64 const *") long[] jArr) {
        long j = GL.getICD().glUniform4ui64vARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, jArr.length >> 2, jArr, j);
    }

    public static void glProgramUniform4ui64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64 const *") long[] jArr) {
        long j = GL.getICD().glProgramUniform4ui64vARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, jArr.length >> 2, jArr, j);
    }

    public static void glGetUniformi64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64 *") long[] jArr) {
        long j = GL.getICD().glGetUniformi64vARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, i2, jArr, j);
    }

    public static void glGetUniformui64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64 *") long[] jArr) {
        long j = GL.getICD().glGetUniformui64vARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, i2, jArr, j);
    }

    public static void glGetnUniformi64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64 *") long[] jArr) {
        long j = GL.getICD().glGetnUniformi64vARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, jArr.length, jArr, j);
    }

    public static void glGetnUniformui64vARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64 *") long[] jArr) {
        long j = GL.getICD().glGetnUniformui64vARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, jArr.length, jArr, j);
    }
}
