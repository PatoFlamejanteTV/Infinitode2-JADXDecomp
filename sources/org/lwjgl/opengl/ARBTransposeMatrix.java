package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBTransposeMatrix.class */
public class ARBTransposeMatrix {
    public static final int GL_TRANSPOSE_MODELVIEW_MATRIX_ARB = 34019;
    public static final int GL_TRANSPOSE_PROJECTION_MATRIX_ARB = 34020;
    public static final int GL_TRANSPOSE_TEXTURE_MATRIX_ARB = 34021;
    public static final int GL_TRANSPOSE_COLOR_MATRIX_ARB = 34022;

    public static native void nglLoadTransposeMatrixfARB(long j);

    public static native void nglLoadTransposeMatrixdARB(long j);

    public static native void nglMultTransposeMatrixfARB(long j);

    public static native void nglMultTransposeMatrixdARB(long j);

    static {
        GL.initialize();
    }

    protected ARBTransposeMatrix() {
        throw new UnsupportedOperationException();
    }

    public static void glLoadTransposeMatrixfARB(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 16);
        }
        nglLoadTransposeMatrixfARB(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glLoadTransposeMatrixdARB(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 16);
        }
        nglLoadTransposeMatrixdARB(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glMultTransposeMatrixfARB(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 16);
        }
        nglMultTransposeMatrixfARB(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMultTransposeMatrixdARB(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 16);
        }
        nglMultTransposeMatrixdARB(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glLoadTransposeMatrixfARB(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glLoadTransposeMatrixfARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 16);
        }
        JNI.callPV(fArr, j);
    }

    public static void glLoadTransposeMatrixdARB(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glLoadTransposeMatrixdARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 16);
        }
        JNI.callPV(dArr, j);
    }

    public static void glMultTransposeMatrixfARB(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMultTransposeMatrixfARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 16);
        }
        JNI.callPV(fArr, j);
    }

    public static void glMultTransposeMatrixdARB(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glMultTransposeMatrixdARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 16);
        }
        JNI.callPV(dArr, j);
    }
}
