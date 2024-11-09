package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBVertexBlend.class */
public class ARBVertexBlend {
    public static final int GL_MAX_VERTEX_UNITS_ARB = 34468;
    public static final int GL_ACTIVE_VERTEX_UNITS_ARB = 34469;
    public static final int GL_WEIGHT_SUM_UNITY_ARB = 34470;
    public static final int GL_VERTEX_BLEND_ARB = 34471;
    public static final int GL_MODELVIEW0_ARB = 5888;
    public static final int GL_MODELVIEW1_ARB = 34058;
    public static final int GL_MODELVIEW2_ARB = 34594;
    public static final int GL_MODELVIEW3_ARB = 34595;
    public static final int GL_MODELVIEW4_ARB = 34596;
    public static final int GL_MODELVIEW5_ARB = 34597;
    public static final int GL_MODELVIEW6_ARB = 34598;
    public static final int GL_MODELVIEW7_ARB = 34599;
    public static final int GL_MODELVIEW8_ARB = 34600;
    public static final int GL_MODELVIEW9_ARB = 34601;
    public static final int GL_MODELVIEW10_ARB = 34602;
    public static final int GL_MODELVIEW11_ARB = 34603;
    public static final int GL_MODELVIEW12_ARB = 34604;
    public static final int GL_MODELVIEW13_ARB = 34605;
    public static final int GL_MODELVIEW14_ARB = 34606;
    public static final int GL_MODELVIEW15_ARB = 34607;
    public static final int GL_MODELVIEW16_ARB = 34608;
    public static final int GL_MODELVIEW17_ARB = 34609;
    public static final int GL_MODELVIEW18_ARB = 34610;
    public static final int GL_MODELVIEW19_ARB = 34611;
    public static final int GL_MODELVIEW20_ARB = 34612;
    public static final int GL_MODELVIEW21_ARB = 34613;
    public static final int GL_MODELVIEW22_ARB = 34614;
    public static final int GL_MODELVIEW23_ARB = 34615;
    public static final int GL_MODELVIEW24_ARB = 34616;
    public static final int GL_MODELVIEW25_ARB = 34617;
    public static final int GL_MODELVIEW26_ARB = 34618;
    public static final int GL_MODELVIEW27_ARB = 34619;
    public static final int GL_MODELVIEW28_ARB = 34620;
    public static final int GL_MODELVIEW29_ARB = 34621;
    public static final int GL_MODELVIEW30_ARB = 34622;
    public static final int GL_MODELVIEW31_ARB = 34623;
    public static final int GL_CURRENT_WEIGHT_ARB = 34472;
    public static final int GL_WEIGHT_ARRAY_TYPE_ARB = 34473;
    public static final int GL_WEIGHT_ARRAY_STRIDE_ARB = 34474;
    public static final int GL_WEIGHT_ARRAY_SIZE_ARB = 34475;
    public static final int GL_WEIGHT_ARRAY_POINTER_ARB = 34476;
    public static final int GL_WEIGHT_ARRAY_ARB = 34477;

    public static native void nglWeightfvARB(int i, long j);

    public static native void nglWeightbvARB(int i, long j);

    public static native void nglWeightubvARB(int i, long j);

    public static native void nglWeightsvARB(int i, long j);

    public static native void nglWeightusvARB(int i, long j);

    public static native void nglWeightivARB(int i, long j);

    public static native void nglWeightuivARB(int i, long j);

    public static native void nglWeightdvARB(int i, long j);

    public static native void nglWeightPointerARB(int i, int i2, int i3, long j);

    public static native void glVertexBlendARB(@NativeType("GLint") int i);

    static {
        GL.initialize();
    }

    protected ARBVertexBlend() {
        throw new UnsupportedOperationException();
    }

    public static void glWeightfvARB(@NativeType("GLfloat *") FloatBuffer floatBuffer) {
        nglWeightfvARB(floatBuffer.remaining(), MemoryUtil.memAddress(floatBuffer));
    }

    public static void glWeightbvARB(@NativeType("GLbyte *") ByteBuffer byteBuffer) {
        nglWeightbvARB(byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glWeightubvARB(@NativeType("GLubyte *") ByteBuffer byteBuffer) {
        nglWeightubvARB(byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glWeightsvARB(@NativeType("GLshort *") ShortBuffer shortBuffer) {
        nglWeightsvARB(shortBuffer.remaining(), MemoryUtil.memAddress(shortBuffer));
    }

    public static void glWeightusvARB(@NativeType("GLushort *") ShortBuffer shortBuffer) {
        nglWeightusvARB(shortBuffer.remaining(), MemoryUtil.memAddress(shortBuffer));
    }

    public static void glWeightivARB(@NativeType("GLint *") IntBuffer intBuffer) {
        nglWeightivARB(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glWeightuivARB(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglWeightuivARB(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glWeightdvARB(@NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        nglWeightdvARB(doubleBuffer.remaining(), MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glWeightPointerARB(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglWeightPointerARB(i, i2, i3, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glWeightPointerARB(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") long j) {
        nglWeightPointerARB(i, i2, i3, j);
    }

    public static void glWeightPointerARB(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglWeightPointerARB(i, i2, i3, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glWeightPointerARB(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") IntBuffer intBuffer) {
        nglWeightPointerARB(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glWeightPointerARB(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglWeightPointerARB(i, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glWeightfvARB(@NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glWeightfvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(fArr.length, fArr, j);
    }

    public static void glWeightsvARB(@NativeType("GLshort *") short[] sArr) {
        long j = GL.getICD().glWeightsvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(sArr.length, sArr, j);
    }

    public static void glWeightusvARB(@NativeType("GLushort *") short[] sArr) {
        long j = GL.getICD().glWeightusvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(sArr.length, sArr, j);
    }

    public static void glWeightivARB(@NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glWeightivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glWeightuivARB(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glWeightuivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glWeightdvARB(@NativeType("GLdouble *") double[] dArr) {
        long j = GL.getICD().glWeightdvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(dArr.length, dArr, j);
    }

    public static void glWeightPointerARB(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glWeightPointerARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, sArr, j);
    }

    public static void glWeightPointerARB(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glWeightPointerARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glWeightPointerARB(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glWeightPointerARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, fArr, j);
    }
}
