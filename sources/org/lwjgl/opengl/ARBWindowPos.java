package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBWindowPos.class */
public class ARBWindowPos {
    public static native void glWindowPos2iARB(@NativeType("GLint") int i, @NativeType("GLint") int i2);

    public static native void glWindowPos2sARB(@NativeType("GLshort") short s, @NativeType("GLshort") short s2);

    public static native void glWindowPos2fARB(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2);

    public static native void glWindowPos2dARB(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2);

    public static native void nglWindowPos2ivARB(long j);

    public static native void nglWindowPos2svARB(long j);

    public static native void nglWindowPos2fvARB(long j);

    public static native void nglWindowPos2dvARB(long j);

    public static native void glWindowPos3iARB(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3);

    public static native void glWindowPos3sARB(@NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3);

    public static native void glWindowPos3fARB(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3);

    public static native void glWindowPos3dARB(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3);

    public static native void nglWindowPos3ivARB(long j);

    public static native void nglWindowPos3svARB(long j);

    public static native void nglWindowPos3fvARB(long j);

    public static native void nglWindowPos3dvARB(long j);

    static {
        GL.initialize();
    }

    protected ARBWindowPos() {
        throw new UnsupportedOperationException();
    }

    public static void glWindowPos2ivARB(@NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 2);
        }
        nglWindowPos2ivARB(MemoryUtil.memAddress(intBuffer));
    }

    public static void glWindowPos2svARB(@NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 2);
        }
        nglWindowPos2svARB(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glWindowPos2fvARB(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 2);
        }
        nglWindowPos2fvARB(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glWindowPos2dvARB(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 2);
        }
        nglWindowPos2dvARB(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glWindowPos3ivARB(@NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 3);
        }
        nglWindowPos3ivARB(MemoryUtil.memAddress(intBuffer));
    }

    public static void glWindowPos3svARB(@NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 3);
        }
        nglWindowPos3svARB(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glWindowPos3fvARB(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 3);
        }
        nglWindowPos3fvARB(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glWindowPos3dvARB(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 3);
        }
        nglWindowPos3dvARB(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glWindowPos2ivARB(@NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glWindowPos2ivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 2);
        }
        JNI.callPV(iArr, j);
    }

    public static void glWindowPos2svARB(@NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glWindowPos2svARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 2);
        }
        JNI.callPV(sArr, j);
    }

    public static void glWindowPos2fvARB(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glWindowPos2fvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 2);
        }
        JNI.callPV(fArr, j);
    }

    public static void glWindowPos2dvARB(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glWindowPos2dvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 2);
        }
        JNI.callPV(dArr, j);
    }

    public static void glWindowPos3ivARB(@NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glWindowPos3ivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 3);
        }
        JNI.callPV(iArr, j);
    }

    public static void glWindowPos3svARB(@NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glWindowPos3svARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 3);
        }
        JNI.callPV(sArr, j);
    }

    public static void glWindowPos3fvARB(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glWindowPos3fvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 3);
        }
        JNI.callPV(fArr, j);
    }

    public static void glWindowPos3dvARB(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glWindowPos3dvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 3);
        }
        JNI.callPV(dArr, j);
    }
}
