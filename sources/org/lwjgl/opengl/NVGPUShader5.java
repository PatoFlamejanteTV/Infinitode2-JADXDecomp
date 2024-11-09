package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.LongBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVGPUShader5.class */
public class NVGPUShader5 {
    public static final int GL_INT64_NV = 5134;
    public static final int GL_UNSIGNED_INT64_NV = 5135;
    public static final int GL_INT8_NV = 36832;
    public static final int GL_INT8_VEC2_NV = 36833;
    public static final int GL_INT8_VEC3_NV = 36834;
    public static final int GL_INT8_VEC4_NV = 36835;
    public static final int GL_INT16_NV = 36836;
    public static final int GL_INT16_VEC2_NV = 36837;
    public static final int GL_INT16_VEC3_NV = 36838;
    public static final int GL_INT16_VEC4_NV = 36839;
    public static final int GL_INT64_VEC2_NV = 36841;
    public static final int GL_INT64_VEC3_NV = 36842;
    public static final int GL_INT64_VEC4_NV = 36843;
    public static final int GL_UNSIGNED_INT8_NV = 36844;
    public static final int GL_UNSIGNED_INT8_VEC2_NV = 36845;
    public static final int GL_UNSIGNED_INT8_VEC3_NV = 36846;
    public static final int GL_UNSIGNED_INT8_VEC4_NV = 36847;
    public static final int GL_UNSIGNED_INT16_NV = 36848;
    public static final int GL_UNSIGNED_INT16_VEC2_NV = 36849;
    public static final int GL_UNSIGNED_INT16_VEC3_NV = 36850;
    public static final int GL_UNSIGNED_INT16_VEC4_NV = 36851;
    public static final int GL_UNSIGNED_INT64_VEC2_NV = 36853;
    public static final int GL_UNSIGNED_INT64_VEC3_NV = 36854;
    public static final int GL_UNSIGNED_INT64_VEC4_NV = 36855;
    public static final int GL_FLOAT16_NV = 36856;
    public static final int GL_FLOAT16_VEC2_NV = 36857;
    public static final int GL_FLOAT16_VEC3_NV = 36858;
    public static final int GL_FLOAT16_VEC4_NV = 36859;

    public static native void glUniform1i64NV(@NativeType("GLint") int i, @NativeType("GLint64EXT") long j);

    public static native void glUniform2i64NV(@NativeType("GLint") int i, @NativeType("GLint64EXT") long j, @NativeType("GLint64EXT") long j2);

    public static native void glUniform3i64NV(@NativeType("GLint") int i, @NativeType("GLint64EXT") long j, @NativeType("GLint64EXT") long j2, @NativeType("GLint64EXT") long j3);

    public static native void glUniform4i64NV(@NativeType("GLint") int i, @NativeType("GLint64EXT") long j, @NativeType("GLint64EXT") long j2, @NativeType("GLint64EXT") long j3, @NativeType("GLint64EXT") long j4);

    public static native void nglUniform1i64vNV(int i, int i2, long j);

    public static native void nglUniform2i64vNV(int i, int i2, long j);

    public static native void nglUniform3i64vNV(int i, int i2, long j);

    public static native void nglUniform4i64vNV(int i, int i2, long j);

    public static native void glUniform1ui64NV(@NativeType("GLint") int i, @NativeType("GLuint64EXT") long j);

    public static native void glUniform2ui64NV(@NativeType("GLint") int i, @NativeType("GLuint64EXT") long j, @NativeType("GLuint64EXT") long j2);

    public static native void glUniform3ui64NV(@NativeType("GLint") int i, @NativeType("GLuint64EXT") long j, @NativeType("GLuint64EXT") long j2, @NativeType("GLuint64EXT") long j3);

    public static native void glUniform4ui64NV(@NativeType("GLint") int i, @NativeType("GLuint64EXT") long j, @NativeType("GLuint64EXT") long j2, @NativeType("GLuint64EXT") long j3, @NativeType("GLuint64EXT") long j4);

    public static native void nglUniform1ui64vNV(int i, int i2, long j);

    public static native void nglUniform2ui64vNV(int i, int i2, long j);

    public static native void nglUniform3ui64vNV(int i, int i2, long j);

    public static native void nglUniform4ui64vNV(int i, int i2, long j);

    public static native void nglGetUniformi64vNV(int i, int i2, long j);

    public static native void glProgramUniform1i64NV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT") long j);

    public static native void glProgramUniform2i64NV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT") long j, @NativeType("GLint64EXT") long j2);

    public static native void glProgramUniform3i64NV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT") long j, @NativeType("GLint64EXT") long j2, @NativeType("GLint64EXT") long j3);

    public static native void glProgramUniform4i64NV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT") long j, @NativeType("GLint64EXT") long j2, @NativeType("GLint64EXT") long j3, @NativeType("GLint64EXT") long j4);

    public static native void nglProgramUniform1i64vNV(int i, int i2, int i3, long j);

    public static native void nglProgramUniform2i64vNV(int i, int i2, int i3, long j);

    public static native void nglProgramUniform3i64vNV(int i, int i2, int i3, long j);

    public static native void nglProgramUniform4i64vNV(int i, int i2, int i3, long j);

    public static native void glProgramUniform1ui64NV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT") long j);

    public static native void glProgramUniform2ui64NV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT") long j, @NativeType("GLuint64EXT") long j2);

    public static native void glProgramUniform3ui64NV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT") long j, @NativeType("GLuint64EXT") long j2, @NativeType("GLuint64EXT") long j3);

    public static native void glProgramUniform4ui64NV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT") long j, @NativeType("GLuint64EXT") long j2, @NativeType("GLuint64EXT") long j3, @NativeType("GLuint64EXT") long j4);

    public static native void nglProgramUniform1ui64vNV(int i, int i2, int i3, long j);

    public static native void nglProgramUniform2ui64vNV(int i, int i2, int i3, long j);

    public static native void nglProgramUniform3ui64vNV(int i, int i2, int i3, long j);

    public static native void nglProgramUniform4ui64vNV(int i, int i2, int i3, long j);

    static {
        GL.initialize();
    }

    protected NVGPUShader5() {
        throw new UnsupportedOperationException();
    }

    public static void glUniform1i64vNV(@NativeType("GLint") int i, @NativeType("GLint64EXT const *") LongBuffer longBuffer) {
        nglUniform1i64vNV(i, longBuffer.remaining(), MemoryUtil.memAddress(longBuffer));
    }

    public static void glUniform2i64vNV(@NativeType("GLint") int i, @NativeType("GLint64EXT const *") LongBuffer longBuffer) {
        nglUniform2i64vNV(i, longBuffer.remaining() >> 1, MemoryUtil.memAddress(longBuffer));
    }

    public static void glUniform3i64vNV(@NativeType("GLint") int i, @NativeType("GLint64EXT const *") LongBuffer longBuffer) {
        nglUniform3i64vNV(i, longBuffer.remaining() / 3, MemoryUtil.memAddress(longBuffer));
    }

    public static void glUniform4i64vNV(@NativeType("GLint") int i, @NativeType("GLint64EXT const *") LongBuffer longBuffer) {
        nglUniform4i64vNV(i, longBuffer.remaining() >> 2, MemoryUtil.memAddress(longBuffer));
    }

    public static void glUniform1ui64vNV(@NativeType("GLint") int i, @NativeType("GLuint64EXT const *") LongBuffer longBuffer) {
        nglUniform1ui64vNV(i, longBuffer.remaining(), MemoryUtil.memAddress(longBuffer));
    }

    public static void glUniform2ui64vNV(@NativeType("GLint") int i, @NativeType("GLuint64EXT *") LongBuffer longBuffer) {
        nglUniform2ui64vNV(i, longBuffer.remaining() >> 1, MemoryUtil.memAddress(longBuffer));
    }

    public static void glUniform3ui64vNV(@NativeType("GLint") int i, @NativeType("GLuint64EXT const *") LongBuffer longBuffer) {
        nglUniform3ui64vNV(i, longBuffer.remaining() / 3, MemoryUtil.memAddress(longBuffer));
    }

    public static void glUniform4ui64vNV(@NativeType("GLint") int i, @NativeType("GLuint64EXT const *") LongBuffer longBuffer) {
        nglUniform4ui64vNV(i, longBuffer.remaining() >> 2, MemoryUtil.memAddress(longBuffer));
    }

    public static void glGetUniformi64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglGetUniformi64vNV(i, i2, MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("void")
    public static long glGetUniformi64NV(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetUniformi64vNV(i, i2, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void nglGetUniformui64vNV(int i, int i2, long j) {
        NVShaderBufferLoad.nglGetUniformui64vNV(i, i2, j);
    }

    public static void glGetUniformui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT *") LongBuffer longBuffer) {
        NVShaderBufferLoad.glGetUniformui64vNV(i, i2, longBuffer);
    }

    @NativeType("void")
    public static long glGetUniformui64NV(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        return NVShaderBufferLoad.glGetUniformui64NV(i, i2);
    }

    public static void glProgramUniform1i64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT const *") LongBuffer longBuffer) {
        nglProgramUniform1i64vNV(i, i2, longBuffer.remaining(), MemoryUtil.memAddress(longBuffer));
    }

    public static void glProgramUniform2i64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT const *") LongBuffer longBuffer) {
        nglProgramUniform2i64vNV(i, i2, longBuffer.remaining() >> 1, MemoryUtil.memAddress(longBuffer));
    }

    public static void glProgramUniform3i64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT const *") LongBuffer longBuffer) {
        nglProgramUniform3i64vNV(i, i2, longBuffer.remaining() / 3, MemoryUtil.memAddress(longBuffer));
    }

    public static void glProgramUniform4i64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT const *") LongBuffer longBuffer) {
        nglProgramUniform4i64vNV(i, i2, longBuffer.remaining() >> 2, MemoryUtil.memAddress(longBuffer));
    }

    public static void glProgramUniform1ui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT const *") LongBuffer longBuffer) {
        nglProgramUniform1ui64vNV(i, i2, longBuffer.remaining(), MemoryUtil.memAddress(longBuffer));
    }

    public static void glProgramUniform2ui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT const *") LongBuffer longBuffer) {
        nglProgramUniform2ui64vNV(i, i2, longBuffer.remaining() >> 1, MemoryUtil.memAddress(longBuffer));
    }

    public static void glProgramUniform3ui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT const *") LongBuffer longBuffer) {
        nglProgramUniform3ui64vNV(i, i2, longBuffer.remaining() / 3, MemoryUtil.memAddress(longBuffer));
    }

    public static void glProgramUniform4ui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT const *") LongBuffer longBuffer) {
        nglProgramUniform4ui64vNV(i, i2, longBuffer.remaining() >> 2, MemoryUtil.memAddress(longBuffer));
    }

    public static void glUniform1i64vNV(@NativeType("GLint") int i, @NativeType("GLint64EXT const *") long[] jArr) {
        long j = GL.getICD().glUniform1i64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, jArr.length, jArr, j);
    }

    public static void glUniform2i64vNV(@NativeType("GLint") int i, @NativeType("GLint64EXT const *") long[] jArr) {
        long j = GL.getICD().glUniform2i64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, jArr.length >> 1, jArr, j);
    }

    public static void glUniform3i64vNV(@NativeType("GLint") int i, @NativeType("GLint64EXT const *") long[] jArr) {
        long j = GL.getICD().glUniform3i64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, jArr.length / 3, jArr, j);
    }

    public static void glUniform4i64vNV(@NativeType("GLint") int i, @NativeType("GLint64EXT const *") long[] jArr) {
        long j = GL.getICD().glUniform4i64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, jArr.length >> 2, jArr, j);
    }

    public static void glUniform1ui64vNV(@NativeType("GLint") int i, @NativeType("GLuint64EXT const *") long[] jArr) {
        long j = GL.getICD().glUniform1ui64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, jArr.length, jArr, j);
    }

    public static void glUniform2ui64vNV(@NativeType("GLint") int i, @NativeType("GLuint64EXT *") long[] jArr) {
        long j = GL.getICD().glUniform2ui64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, jArr.length >> 1, jArr, j);
    }

    public static void glUniform3ui64vNV(@NativeType("GLint") int i, @NativeType("GLuint64EXT const *") long[] jArr) {
        long j = GL.getICD().glUniform3ui64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, jArr.length / 3, jArr, j);
    }

    public static void glUniform4ui64vNV(@NativeType("GLint") int i, @NativeType("GLuint64EXT const *") long[] jArr) {
        long j = GL.getICD().glUniform4ui64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, jArr.length >> 2, jArr, j);
    }

    public static void glGetUniformi64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT *") long[] jArr) {
        long j = GL.getICD().glGetUniformi64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, i2, jArr, j);
    }

    public static void glGetUniformui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT *") long[] jArr) {
        NVShaderBufferLoad.glGetUniformui64vNV(i, i2, jArr);
    }

    public static void glProgramUniform1i64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT const *") long[] jArr) {
        long j = GL.getICD().glProgramUniform1i64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, jArr.length, jArr, j);
    }

    public static void glProgramUniform2i64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT const *") long[] jArr) {
        long j = GL.getICD().glProgramUniform2i64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, jArr.length >> 1, jArr, j);
    }

    public static void glProgramUniform3i64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT const *") long[] jArr) {
        long j = GL.getICD().glProgramUniform3i64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, jArr.length / 3, jArr, j);
    }

    public static void glProgramUniform4i64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT const *") long[] jArr) {
        long j = GL.getICD().glProgramUniform4i64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, jArr.length >> 2, jArr, j);
    }

    public static void glProgramUniform1ui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT const *") long[] jArr) {
        long j = GL.getICD().glProgramUniform1ui64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, jArr.length, jArr, j);
    }

    public static void glProgramUniform2ui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT const *") long[] jArr) {
        long j = GL.getICD().glProgramUniform2ui64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, jArr.length >> 1, jArr, j);
    }

    public static void glProgramUniform3ui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT const *") long[] jArr) {
        long j = GL.getICD().glProgramUniform3ui64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, jArr.length / 3, jArr, j);
    }

    public static void glProgramUniform4ui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT const *") long[] jArr) {
        long j = GL.getICD().glProgramUniform4ui64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, jArr.length >> 2, jArr, j);
    }
}
