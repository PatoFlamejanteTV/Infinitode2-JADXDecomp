package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTVertexAttrib64bit.class */
public class EXTVertexAttrib64bit {
    public static final int GL_DOUBLE_VEC2_EXT = 36860;
    public static final int GL_DOUBLE_VEC3_EXT = 36861;
    public static final int GL_DOUBLE_VEC4_EXT = 36862;
    public static final int GL_DOUBLE_MAT2_EXT = 36678;
    public static final int GL_DOUBLE_MAT3_EXT = 36679;
    public static final int GL_DOUBLE_MAT4_EXT = 36680;
    public static final int GL_DOUBLE_MAT2x3_EXT = 36681;
    public static final int GL_DOUBLE_MAT2x4_EXT = 36682;
    public static final int GL_DOUBLE_MAT3x2_EXT = 36683;
    public static final int GL_DOUBLE_MAT3x4_EXT = 36684;
    public static final int GL_DOUBLE_MAT4x2_EXT = 36685;
    public static final int GL_DOUBLE_MAT4x3_EXT = 36686;

    public static native void glVertexAttribL1dEXT(@NativeType("GLuint") int i, @NativeType("GLdouble") double d);

    public static native void glVertexAttribL2dEXT(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2);

    public static native void glVertexAttribL3dEXT(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3);

    public static native void glVertexAttribL4dEXT(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4);

    public static native void nglVertexAttribL1dvEXT(int i, long j);

    public static native void nglVertexAttribL2dvEXT(int i, long j);

    public static native void nglVertexAttribL3dvEXT(int i, long j);

    public static native void nglVertexAttribL4dvEXT(int i, long j);

    public static native void nglVertexAttribLPointerEXT(int i, int i2, int i3, int i4, long j);

    public static native void nglGetVertexAttribLdvEXT(int i, int i2, long j);

    static {
        GL.initialize();
    }

    protected EXTVertexAttrib64bit() {
        throw new UnsupportedOperationException();
    }

    public static void glVertexAttribL1dvEXT(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 1);
        }
        nglVertexAttribL1dvEXT(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glVertexAttribL2dvEXT(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 2);
        }
        nglVertexAttribL2dvEXT(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glVertexAttribL3dvEXT(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 3);
        }
        nglVertexAttribL3dvEXT(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glVertexAttribL4dvEXT(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglVertexAttribL4dvEXT(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glVertexAttribLPointerEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglVertexAttribLPointerEXT(i, i2, i3, i4, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glVertexAttribLPointerEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") long j) {
        nglVertexAttribLPointerEXT(i, i2, i3, i4, j);
    }

    public static void glVertexAttribLPointerEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglVertexAttribLPointerEXT(i, i2, 5130, i3, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glGetVertexAttribLdvEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglGetVertexAttribLdvEXT(i, i2, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glVertexArrayVertexAttribLOffsetEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4, @NativeType("GLenum") int i5, @NativeType("GLsizei") int i6, @NativeType("GLintptr") long j) {
        ARBVertexAttrib64Bit.glVertexArrayVertexAttribLOffsetEXT(i, i2, i3, i4, i5, i6, j);
    }

    public static void glVertexAttribL1dvEXT(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glVertexAttribL1dvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 1);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glVertexAttribL2dvEXT(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glVertexAttribL2dvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 2);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glVertexAttribL3dvEXT(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glVertexAttribL3dvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 3);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glVertexAttribL4dvEXT(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glVertexAttribL4dvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glGetVertexAttribLdvEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble *") double[] dArr) {
        long j = GL.getICD().glGetVertexAttribLdvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(i, i2, dArr, j);
    }
}
