package org.lwjgl.opengl;

import java.nio.DoubleBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBGPUShaderFP64.class */
public class ARBGPUShaderFP64 {
    public static final int GL_DOUBLE_VEC2 = 36860;
    public static final int GL_DOUBLE_VEC3 = 36861;
    public static final int GL_DOUBLE_VEC4 = 36862;
    public static final int GL_DOUBLE_MAT2 = 36678;
    public static final int GL_DOUBLE_MAT3 = 36679;
    public static final int GL_DOUBLE_MAT4 = 36680;
    public static final int GL_DOUBLE_MAT2x3 = 36681;
    public static final int GL_DOUBLE_MAT2x4 = 36682;
    public static final int GL_DOUBLE_MAT3x2 = 36683;
    public static final int GL_DOUBLE_MAT3x4 = 36684;
    public static final int GL_DOUBLE_MAT4x2 = 36685;
    public static final int GL_DOUBLE_MAT4x3 = 36686;

    public static native void glProgramUniform1dEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble") double d);

    public static native void glProgramUniform2dEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2);

    public static native void glProgramUniform3dEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3);

    public static native void glProgramUniform4dEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4);

    public static native void nglProgramUniform1dvEXT(int i, int i2, int i3, long j);

    public static native void nglProgramUniform2dvEXT(int i, int i2, int i3, long j);

    public static native void nglProgramUniform3dvEXT(int i, int i2, int i3, long j);

    public static native void nglProgramUniform4dvEXT(int i, int i2, int i3, long j);

    public static native void nglProgramUniformMatrix2dvEXT(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix3dvEXT(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix4dvEXT(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix2x3dvEXT(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix2x4dvEXT(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix3x2dvEXT(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix3x4dvEXT(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix4x2dvEXT(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix4x3dvEXT(int i, int i2, int i3, boolean z, long j);

    static {
        GL.initialize();
    }

    protected ARBGPUShaderFP64() {
        throw new UnsupportedOperationException();
    }

    public static void glUniform1d(@NativeType("GLint") int i, @NativeType("GLdouble") double d) {
        GL40C.glUniform1d(i, d);
    }

    public static void glUniform2d(@NativeType("GLint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2) {
        GL40C.glUniform2d(i, d, d2);
    }

    public static void glUniform3d(@NativeType("GLint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3) {
        GL40C.glUniform3d(i, d, d2, d3);
    }

    public static void glUniform4d(@NativeType("GLint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4) {
        GL40C.glUniform4d(i, d, d2, d3, d4);
    }

    public static void nglUniform1dv(int i, int i2, long j) {
        GL40C.nglUniform1dv(i, i2, j);
    }

    public static void glUniform1dv(@NativeType("GLint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniform1dv(i, doubleBuffer);
    }

    public static void nglUniform2dv(int i, int i2, long j) {
        GL40C.nglUniform2dv(i, i2, j);
    }

    public static void glUniform2dv(@NativeType("GLint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniform2dv(i, doubleBuffer);
    }

    public static void nglUniform3dv(int i, int i2, long j) {
        GL40C.nglUniform3dv(i, i2, j);
    }

    public static void glUniform3dv(@NativeType("GLint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniform3dv(i, doubleBuffer);
    }

    public static void nglUniform4dv(int i, int i2, long j) {
        GL40C.nglUniform4dv(i, i2, j);
    }

    public static void glUniform4dv(@NativeType("GLint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniform4dv(i, doubleBuffer);
    }

    public static void nglUniformMatrix2dv(int i, int i2, boolean z, long j) {
        GL40C.nglUniformMatrix2dv(i, i2, z, j);
    }

    public static void glUniformMatrix2dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniformMatrix2dv(i, z, doubleBuffer);
    }

    public static void nglUniformMatrix3dv(int i, int i2, boolean z, long j) {
        GL40C.nglUniformMatrix3dv(i, i2, z, j);
    }

    public static void glUniformMatrix3dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniformMatrix3dv(i, z, doubleBuffer);
    }

    public static void nglUniformMatrix4dv(int i, int i2, boolean z, long j) {
        GL40C.nglUniformMatrix4dv(i, i2, z, j);
    }

    public static void glUniformMatrix4dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniformMatrix4dv(i, z, doubleBuffer);
    }

    public static void nglUniformMatrix2x3dv(int i, int i2, boolean z, long j) {
        GL40C.nglUniformMatrix2x3dv(i, i2, z, j);
    }

    public static void glUniformMatrix2x3dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniformMatrix2x3dv(i, z, doubleBuffer);
    }

    public static void nglUniformMatrix2x4dv(int i, int i2, boolean z, long j) {
        GL40C.nglUniformMatrix2x4dv(i, i2, z, j);
    }

    public static void glUniformMatrix2x4dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniformMatrix2x4dv(i, z, doubleBuffer);
    }

    public static void nglUniformMatrix3x2dv(int i, int i2, boolean z, long j) {
        GL40C.nglUniformMatrix3x2dv(i, i2, z, j);
    }

    public static void glUniformMatrix3x2dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniformMatrix3x2dv(i, z, doubleBuffer);
    }

    public static void nglUniformMatrix3x4dv(int i, int i2, boolean z, long j) {
        GL40C.nglUniformMatrix3x4dv(i, i2, z, j);
    }

    public static void glUniformMatrix3x4dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniformMatrix3x4dv(i, z, doubleBuffer);
    }

    public static void nglUniformMatrix4x2dv(int i, int i2, boolean z, long j) {
        GL40C.nglUniformMatrix4x2dv(i, i2, z, j);
    }

    public static void glUniformMatrix4x2dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniformMatrix4x2dv(i, z, doubleBuffer);
    }

    public static void nglUniformMatrix4x3dv(int i, int i2, boolean z, long j) {
        GL40C.nglUniformMatrix4x3dv(i, i2, z, j);
    }

    public static void glUniformMatrix4x3dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniformMatrix4x3dv(i, z, doubleBuffer);
    }

    public static void nglGetUniformdv(int i, int i2, long j) {
        GL40C.nglGetUniformdv(i, i2, j);
    }

    public static void glGetUniformdv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        GL40C.glGetUniformdv(i, i2, doubleBuffer);
    }

    @NativeType("void")
    public static double glGetUniformd(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        return GL40C.glGetUniformd(i, i2);
    }

    public static void glProgramUniform1dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniform1dvEXT(i, i2, doubleBuffer.remaining(), MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniform2dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniform2dvEXT(i, i2, doubleBuffer.remaining() >> 1, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniform3dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniform3dvEXT(i, i2, doubleBuffer.remaining() / 3, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniform4dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniform4dvEXT(i, i2, doubleBuffer.remaining() >> 2, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniformMatrix2dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniformMatrix2dvEXT(i, i2, doubleBuffer.remaining() >> 2, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniformMatrix3dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniformMatrix3dvEXT(i, i2, doubleBuffer.remaining() / 9, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniformMatrix4dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniformMatrix4dvEXT(i, i2, doubleBuffer.remaining() >> 4, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniformMatrix2x3dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniformMatrix2x3dvEXT(i, i2, doubleBuffer.remaining() / 6, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniformMatrix2x4dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniformMatrix2x4dvEXT(i, i2, doubleBuffer.remaining() >> 3, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniformMatrix3x2dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniformMatrix3x2dvEXT(i, i2, doubleBuffer.remaining() / 6, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniformMatrix3x4dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniformMatrix3x4dvEXT(i, i2, doubleBuffer.remaining() / 12, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniformMatrix4x2dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniformMatrix4x2dvEXT(i, i2, doubleBuffer.remaining() >> 3, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniformMatrix4x3dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniformMatrix4x3dvEXT(i, i2, doubleBuffer.remaining() / 12, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glUniform1dv(@NativeType("GLint") int i, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniform1dv(i, dArr);
    }

    public static void glUniform2dv(@NativeType("GLint") int i, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniform2dv(i, dArr);
    }

    public static void glUniform3dv(@NativeType("GLint") int i, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniform3dv(i, dArr);
    }

    public static void glUniform4dv(@NativeType("GLint") int i, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniform4dv(i, dArr);
    }

    public static void glUniformMatrix2dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniformMatrix2dv(i, z, dArr);
    }

    public static void glUniformMatrix3dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniformMatrix3dv(i, z, dArr);
    }

    public static void glUniformMatrix4dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniformMatrix4dv(i, z, dArr);
    }

    public static void glUniformMatrix2x3dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniformMatrix2x3dv(i, z, dArr);
    }

    public static void glUniformMatrix2x4dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniformMatrix2x4dv(i, z, dArr);
    }

    public static void glUniformMatrix3x2dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniformMatrix3x2dv(i, z, dArr);
    }

    public static void glUniformMatrix3x4dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniformMatrix3x4dv(i, z, dArr);
    }

    public static void glUniformMatrix4x2dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniformMatrix4x2dv(i, z, dArr);
    }

    public static void glUniformMatrix4x3dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniformMatrix4x3dv(i, z, dArr);
    }

    public static void glGetUniformdv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble *") double[] dArr) {
        GL40C.glGetUniformdv(i, i2, dArr);
    }

    public static void glProgramUniform1dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniform1dvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length, dArr, j);
    }

    public static void glProgramUniform2dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniform2dvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length >> 1, dArr, j);
    }

    public static void glProgramUniform3dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniform3dvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length / 3, dArr, j);
    }

    public static void glProgramUniform4dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniform4dvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length >> 2, dArr, j);
    }

    public static void glProgramUniformMatrix2dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniformMatrix2dvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length >> 2, z, dArr, j);
    }

    public static void glProgramUniformMatrix3dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniformMatrix3dvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length / 9, z, dArr, j);
    }

    public static void glProgramUniformMatrix4dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniformMatrix4dvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length >> 4, z, dArr, j);
    }

    public static void glProgramUniformMatrix2x3dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniformMatrix2x3dvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length / 6, z, dArr, j);
    }

    public static void glProgramUniformMatrix2x4dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniformMatrix2x4dvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length >> 3, z, dArr, j);
    }

    public static void glProgramUniformMatrix3x2dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniformMatrix3x2dvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length / 6, z, dArr, j);
    }

    public static void glProgramUniformMatrix3x4dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniformMatrix3x4dvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length / 12, z, dArr, j);
    }

    public static void glProgramUniformMatrix4x2dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniformMatrix4x2dvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length >> 3, z, dArr, j);
    }

    public static void glProgramUniformMatrix4x3dvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniformMatrix4x3dvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length / 12, z, dArr, j);
    }
}
