package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.LongBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVShaderBufferLoad.class */
public class NVShaderBufferLoad {
    public static final int GL_BUFFER_GPU_ADDRESS_NV = 36637;
    public static final int GL_GPU_ADDRESS_NV = 36660;
    public static final int GL_MAX_SHADER_BUFFER_ADDRESS_NV = 36661;

    public static native void glMakeBufferResidentNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2);

    public static native void glMakeBufferNonResidentNV(@NativeType("GLenum") int i);

    @NativeType("GLboolean")
    public static native boolean glIsBufferResidentNV(@NativeType("GLenum") int i);

    public static native void glMakeNamedBufferResidentNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2);

    public static native void glMakeNamedBufferNonResidentNV(@NativeType("GLuint") int i);

    @NativeType("GLboolean")
    public static native boolean glIsNamedBufferResidentNV(@NativeType("GLuint") int i);

    public static native void nglGetBufferParameterui64vNV(int i, int i2, long j);

    public static native void nglGetNamedBufferParameterui64vNV(int i, int i2, long j);

    public static native void nglGetIntegerui64vNV(int i, long j);

    public static native void glUniformui64NV(@NativeType("GLint") int i, @NativeType("GLuint64EXT") long j);

    public static native void nglUniformui64vNV(int i, int i2, long j);

    public static native void nglGetUniformui64vNV(int i, int i2, long j);

    public static native void glProgramUniformui64NV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT") long j);

    public static native void nglProgramUniformui64vNV(int i, int i2, int i3, long j);

    static {
        GL.initialize();
    }

    protected NVShaderBufferLoad() {
        throw new UnsupportedOperationException();
    }

    public static void glGetBufferParameterui64vNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64EXT *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglGetBufferParameterui64vNV(i, i2, MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("void")
    public static long glGetBufferParameterui64NV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetBufferParameterui64vNV(i, i2, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetNamedBufferParameterui64vNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64EXT *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglGetNamedBufferParameterui64vNV(i, i2, MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("void")
    public static long glGetNamedBufferParameterui64NV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetNamedBufferParameterui64vNV(i, i2, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetIntegerui64vNV(@NativeType("GLenum") int i, @NativeType("GLuint64EXT *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglGetIntegerui64vNV(i, MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("void")
    public static long glGetIntegerui64NV(@NativeType("GLenum") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetIntegerui64vNV(i, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glUniformui64vNV(@NativeType("GLint") int i, @NativeType("GLuint64EXT const *") LongBuffer longBuffer) {
        nglUniformui64vNV(i, longBuffer.remaining(), MemoryUtil.memAddress(longBuffer));
    }

    public static void glGetUniformui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglGetUniformui64vNV(i, i2, MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("void")
    public static long glGetUniformui64NV(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetUniformui64vNV(i, i2, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glProgramUniformui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT const *") LongBuffer longBuffer) {
        nglProgramUniformui64vNV(i, i2, longBuffer.remaining(), MemoryUtil.memAddress(longBuffer));
    }

    public static void glGetBufferParameterui64vNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64EXT *") long[] jArr) {
        long j = GL.getICD().glGetBufferParameterui64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, i2, jArr, j);
    }

    public static void glGetNamedBufferParameterui64vNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64EXT *") long[] jArr) {
        long j = GL.getICD().glGetNamedBufferParameterui64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, i2, jArr, j);
    }

    public static void glGetIntegerui64vNV(@NativeType("GLenum") int i, @NativeType("GLuint64EXT *") long[] jArr) {
        long j = GL.getICD().glGetIntegerui64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, jArr, j);
    }

    public static void glUniformui64vNV(@NativeType("GLint") int i, @NativeType("GLuint64EXT const *") long[] jArr) {
        long j = GL.getICD().glUniformui64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, jArr.length, jArr, j);
    }

    public static void glGetUniformui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT *") long[] jArr) {
        long j = GL.getICD().glGetUniformui64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, i2, jArr, j);
    }

    public static void glProgramUniformui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT const *") long[] jArr) {
        long j = GL.getICD().glProgramUniformui64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, jArr.length, jArr, j);
    }
}
