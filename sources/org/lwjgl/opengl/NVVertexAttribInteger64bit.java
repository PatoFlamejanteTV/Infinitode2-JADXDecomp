package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.LongBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVVertexAttribInteger64bit.class */
public class NVVertexAttribInteger64bit {
    public static final int GL_INT64_NV = 5134;
    public static final int GL_UNSIGNED_INT64_NV = 5135;

    public static native void glVertexAttribL1i64NV(@NativeType("GLuint") int i, @NativeType("GLint64EXT") long j);

    public static native void glVertexAttribL2i64NV(@NativeType("GLuint") int i, @NativeType("GLint64EXT") long j, @NativeType("GLint64EXT") long j2);

    public static native void glVertexAttribL3i64NV(@NativeType("GLuint") int i, @NativeType("GLint64EXT") long j, @NativeType("GLint64EXT") long j2, @NativeType("GLint64EXT") long j3);

    public static native void glVertexAttribL4i64NV(@NativeType("GLuint") int i, @NativeType("GLint64EXT") long j, @NativeType("GLint64EXT") long j2, @NativeType("GLint64EXT") long j3, @NativeType("GLint64EXT") long j4);

    public static native void nglVertexAttribL1i64vNV(int i, long j);

    public static native void nglVertexAttribL2i64vNV(int i, long j);

    public static native void nglVertexAttribL3i64vNV(int i, long j);

    public static native void nglVertexAttribL4i64vNV(int i, long j);

    public static native void glVertexAttribL1ui64NV(@NativeType("GLuint") int i, @NativeType("GLuint64EXT") long j);

    public static native void glVertexAttribL2ui64NV(@NativeType("GLuint") int i, @NativeType("GLuint64EXT") long j, @NativeType("GLuint64EXT") long j2);

    public static native void glVertexAttribL3ui64NV(@NativeType("GLuint") int i, @NativeType("GLuint64EXT") long j, @NativeType("GLuint64EXT") long j2, @NativeType("GLuint64EXT") long j3);

    public static native void glVertexAttribL4ui64NV(@NativeType("GLuint") int i, @NativeType("GLuint64EXT") long j, @NativeType("GLuint64EXT") long j2, @NativeType("GLuint64EXT") long j3, @NativeType("GLuint64EXT") long j4);

    public static native void nglVertexAttribL1ui64vNV(int i, long j);

    public static native void nglVertexAttribL2ui64vNV(int i, long j);

    public static native void nglVertexAttribL3ui64vNV(int i, long j);

    public static native void nglVertexAttribL4ui64vNV(int i, long j);

    public static native void nglGetVertexAttribLi64vNV(int i, int i2, long j);

    public static native void nglGetVertexAttribLui64vNV(int i, int i2, long j);

    public static native void glVertexAttribLFormatNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4);

    static {
        GL.initialize();
    }

    protected NVVertexAttribInteger64bit() {
        throw new UnsupportedOperationException();
    }

    public static void glVertexAttribL1i64vNV(@NativeType("GLuint") int i, @NativeType("GLint64EXT const *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglVertexAttribL1i64vNV(i, MemoryUtil.memAddress(longBuffer));
    }

    public static void glVertexAttribL2i64vNV(@NativeType("GLuint") int i, @NativeType("GLint64EXT const *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 2);
        }
        nglVertexAttribL2i64vNV(i, MemoryUtil.memAddress(longBuffer));
    }

    public static void glVertexAttribL3i64vNV(@NativeType("GLuint") int i, @NativeType("GLint64EXT const *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 3);
        }
        nglVertexAttribL3i64vNV(i, MemoryUtil.memAddress(longBuffer));
    }

    public static void glVertexAttribL4i64vNV(@NativeType("GLuint") int i, @NativeType("GLint64EXT const *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 4);
        }
        nglVertexAttribL4i64vNV(i, MemoryUtil.memAddress(longBuffer));
    }

    public static void glVertexAttribL1ui64vNV(@NativeType("GLuint") int i, @NativeType("GLuint64EXT const *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglVertexAttribL1ui64vNV(i, MemoryUtil.memAddress(longBuffer));
    }

    public static void glVertexAttribL2ui64vNV(@NativeType("GLuint") int i, @NativeType("GLuint64EXT const *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 2);
        }
        nglVertexAttribL2ui64vNV(i, MemoryUtil.memAddress(longBuffer));
    }

    public static void glVertexAttribL3ui64vNV(@NativeType("GLuint") int i, @NativeType("GLuint64EXT const *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 3);
        }
        nglVertexAttribL3ui64vNV(i, MemoryUtil.memAddress(longBuffer));
    }

    public static void glVertexAttribL4ui64vNV(@NativeType("GLuint") int i, @NativeType("GLuint64EXT const *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 4);
        }
        nglVertexAttribL4ui64vNV(i, MemoryUtil.memAddress(longBuffer));
    }

    public static void glGetVertexAttribLi64vNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint64EXT *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglGetVertexAttribLi64vNV(i, i2, MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("void")
    public static long glGetVertexAttribLi64NV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetVertexAttribLi64vNV(i, i2, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetVertexAttribLui64vNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64EXT *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglGetVertexAttribLui64vNV(i, i2, MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("void")
    public static long glGetVertexAttribLui64NV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetVertexAttribLui64vNV(i, i2, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glVertexAttribL1i64vNV(@NativeType("GLuint") int i, @NativeType("GLint64EXT const *") long[] jArr) {
        long j = GL.getICD().glVertexAttribL1i64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, jArr, j);
    }

    public static void glVertexAttribL2i64vNV(@NativeType("GLuint") int i, @NativeType("GLint64EXT const *") long[] jArr) {
        long j = GL.getICD().glVertexAttribL2i64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 2);
        }
        JNI.callPV(i, jArr, j);
    }

    public static void glVertexAttribL3i64vNV(@NativeType("GLuint") int i, @NativeType("GLint64EXT const *") long[] jArr) {
        long j = GL.getICD().glVertexAttribL3i64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 3);
        }
        JNI.callPV(i, jArr, j);
    }

    public static void glVertexAttribL4i64vNV(@NativeType("GLuint") int i, @NativeType("GLint64EXT const *") long[] jArr) {
        long j = GL.getICD().glVertexAttribL4i64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 4);
        }
        JNI.callPV(i, jArr, j);
    }

    public static void glVertexAttribL1ui64vNV(@NativeType("GLuint") int i, @NativeType("GLuint64EXT const *") long[] jArr) {
        long j = GL.getICD().glVertexAttribL1ui64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, jArr, j);
    }

    public static void glVertexAttribL2ui64vNV(@NativeType("GLuint") int i, @NativeType("GLuint64EXT const *") long[] jArr) {
        long j = GL.getICD().glVertexAttribL2ui64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 2);
        }
        JNI.callPV(i, jArr, j);
    }

    public static void glVertexAttribL3ui64vNV(@NativeType("GLuint") int i, @NativeType("GLuint64EXT const *") long[] jArr) {
        long j = GL.getICD().glVertexAttribL3ui64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 3);
        }
        JNI.callPV(i, jArr, j);
    }

    public static void glVertexAttribL4ui64vNV(@NativeType("GLuint") int i, @NativeType("GLuint64EXT const *") long[] jArr) {
        long j = GL.getICD().glVertexAttribL4ui64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 4);
        }
        JNI.callPV(i, jArr, j);
    }

    public static void glGetVertexAttribLi64vNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint64EXT *") long[] jArr) {
        long j = GL.getICD().glGetVertexAttribLi64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, i2, jArr, j);
    }

    public static void glGetVertexAttribLui64vNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint64EXT *") long[] jArr) {
        long j = GL.getICD().glGetVertexAttribLui64vNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, i2, jArr, j);
    }
}
