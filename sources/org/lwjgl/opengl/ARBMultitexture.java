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

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBMultitexture.class */
public class ARBMultitexture {
    public static final int GL_TEXTURE0_ARB = 33984;
    public static final int GL_TEXTURE1_ARB = 33985;
    public static final int GL_TEXTURE2_ARB = 33986;
    public static final int GL_TEXTURE3_ARB = 33987;
    public static final int GL_TEXTURE4_ARB = 33988;
    public static final int GL_TEXTURE5_ARB = 33989;
    public static final int GL_TEXTURE6_ARB = 33990;
    public static final int GL_TEXTURE7_ARB = 33991;
    public static final int GL_TEXTURE8_ARB = 33992;
    public static final int GL_TEXTURE9_ARB = 33993;
    public static final int GL_TEXTURE10_ARB = 33994;
    public static final int GL_TEXTURE11_ARB = 33995;
    public static final int GL_TEXTURE12_ARB = 33996;
    public static final int GL_TEXTURE13_ARB = 33997;
    public static final int GL_TEXTURE14_ARB = 33998;
    public static final int GL_TEXTURE15_ARB = 33999;
    public static final int GL_TEXTURE16_ARB = 34000;
    public static final int GL_TEXTURE17_ARB = 34001;
    public static final int GL_TEXTURE18_ARB = 34002;
    public static final int GL_TEXTURE19_ARB = 34003;
    public static final int GL_TEXTURE20_ARB = 34004;
    public static final int GL_TEXTURE21_ARB = 34005;
    public static final int GL_TEXTURE22_ARB = 34006;
    public static final int GL_TEXTURE23_ARB = 34007;
    public static final int GL_TEXTURE24_ARB = 34008;
    public static final int GL_TEXTURE25_ARB = 34009;
    public static final int GL_TEXTURE26_ARB = 34010;
    public static final int GL_TEXTURE27_ARB = 34011;
    public static final int GL_TEXTURE28_ARB = 34012;
    public static final int GL_TEXTURE29_ARB = 34013;
    public static final int GL_TEXTURE30_ARB = 34014;
    public static final int GL_TEXTURE31_ARB = 34015;
    public static final int GL_ACTIVE_TEXTURE_ARB = 34016;
    public static final int GL_CLIENT_ACTIVE_TEXTURE_ARB = 34017;
    public static final int GL_MAX_TEXTURE_UNITS_ARB = 34018;

    public static native void glActiveTextureARB(@NativeType("GLenum") int i);

    public static native void glClientActiveTextureARB(@NativeType("GLenum") int i);

    public static native void glMultiTexCoord1fARB(@NativeType("GLenum") int i, @NativeType("GLfloat") float f);

    public static native void glMultiTexCoord1sARB(@NativeType("GLenum") int i, @NativeType("GLshort") short s);

    public static native void glMultiTexCoord1iARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2);

    public static native void glMultiTexCoord1dARB(@NativeType("GLenum") int i, @NativeType("GLdouble") double d);

    public static native void nglMultiTexCoord1fvARB(int i, long j);

    public static native void nglMultiTexCoord1svARB(int i, long j);

    public static native void nglMultiTexCoord1ivARB(int i, long j);

    public static native void nglMultiTexCoord1dvARB(int i, long j);

    public static native void glMultiTexCoord2fARB(@NativeType("GLenum") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2);

    public static native void glMultiTexCoord2sARB(@NativeType("GLenum") int i, @NativeType("GLshort") short s, @NativeType("GLshort") short s2);

    public static native void glMultiTexCoord2iARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3);

    public static native void glMultiTexCoord2dARB(@NativeType("GLenum") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2);

    public static native void nglMultiTexCoord2fvARB(int i, long j);

    public static native void nglMultiTexCoord2svARB(int i, long j);

    public static native void nglMultiTexCoord2ivARB(int i, long j);

    public static native void nglMultiTexCoord2dvARB(int i, long j);

    public static native void glMultiTexCoord3fARB(@NativeType("GLenum") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3);

    public static native void glMultiTexCoord3sARB(@NativeType("GLenum") int i, @NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3);

    public static native void glMultiTexCoord3iARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4);

    public static native void glMultiTexCoord3dARB(@NativeType("GLenum") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3);

    public static native void nglMultiTexCoord3fvARB(int i, long j);

    public static native void nglMultiTexCoord3svARB(int i, long j);

    public static native void nglMultiTexCoord3ivARB(int i, long j);

    public static native void nglMultiTexCoord3dvARB(int i, long j);

    public static native void glMultiTexCoord4fARB(@NativeType("GLenum") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4);

    public static native void glMultiTexCoord4sARB(@NativeType("GLenum") int i, @NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3, @NativeType("GLshort") short s4);

    public static native void glMultiTexCoord4iARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5);

    public static native void glMultiTexCoord4dARB(@NativeType("GLenum") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4);

    public static native void nglMultiTexCoord4fvARB(int i, long j);

    public static native void nglMultiTexCoord4svARB(int i, long j);

    public static native void nglMultiTexCoord4ivARB(int i, long j);

    public static native void nglMultiTexCoord4dvARB(int i, long j);

    static {
        GL.initialize();
    }

    protected ARBMultitexture() {
        throw new UnsupportedOperationException();
    }

    public static void glMultiTexCoord1fvARB(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglMultiTexCoord1fvARB(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMultiTexCoord1svARB(@NativeType("GLenum") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 1);
        }
        nglMultiTexCoord1svARB(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glMultiTexCoord1ivARB(@NativeType("GLenum") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglMultiTexCoord1ivARB(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glMultiTexCoord1dvARB(@NativeType("GLenum") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 1);
        }
        nglMultiTexCoord1dvARB(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glMultiTexCoord2fvARB(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 2);
        }
        nglMultiTexCoord2fvARB(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMultiTexCoord2svARB(@NativeType("GLenum") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 2);
        }
        nglMultiTexCoord2svARB(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glMultiTexCoord2ivARB(@NativeType("GLenum") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 2);
        }
        nglMultiTexCoord2ivARB(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glMultiTexCoord2dvARB(@NativeType("GLenum") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 2);
        }
        nglMultiTexCoord2dvARB(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glMultiTexCoord3fvARB(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 3);
        }
        nglMultiTexCoord3fvARB(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMultiTexCoord3svARB(@NativeType("GLenum") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 3);
        }
        nglMultiTexCoord3svARB(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glMultiTexCoord3ivARB(@NativeType("GLenum") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 3);
        }
        nglMultiTexCoord3ivARB(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glMultiTexCoord3dvARB(@NativeType("GLenum") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 3);
        }
        nglMultiTexCoord3dvARB(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glMultiTexCoord4fvARB(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglMultiTexCoord4fvARB(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMultiTexCoord4svARB(@NativeType("GLenum") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 4);
        }
        nglMultiTexCoord4svARB(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glMultiTexCoord4ivARB(@NativeType("GLenum") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglMultiTexCoord4ivARB(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glMultiTexCoord4dvARB(@NativeType("GLenum") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglMultiTexCoord4dvARB(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glMultiTexCoord1fvARB(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMultiTexCoord1fvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glMultiTexCoord1svARB(@NativeType("GLenum") int i, @NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glMultiTexCoord1svARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 1);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glMultiTexCoord1ivARB(@NativeType("GLenum") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glMultiTexCoord1ivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glMultiTexCoord1dvARB(@NativeType("GLenum") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glMultiTexCoord1dvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 1);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glMultiTexCoord2fvARB(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMultiTexCoord2fvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 2);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glMultiTexCoord2svARB(@NativeType("GLenum") int i, @NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glMultiTexCoord2svARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 2);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glMultiTexCoord2ivARB(@NativeType("GLenum") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glMultiTexCoord2ivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 2);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glMultiTexCoord2dvARB(@NativeType("GLenum") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glMultiTexCoord2dvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 2);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glMultiTexCoord3fvARB(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMultiTexCoord3fvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 3);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glMultiTexCoord3svARB(@NativeType("GLenum") int i, @NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glMultiTexCoord3svARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 3);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glMultiTexCoord3ivARB(@NativeType("GLenum") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glMultiTexCoord3ivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 3);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glMultiTexCoord3dvARB(@NativeType("GLenum") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glMultiTexCoord3dvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 3);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glMultiTexCoord4fvARB(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMultiTexCoord4fvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glMultiTexCoord4svARB(@NativeType("GLenum") int i, @NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glMultiTexCoord4svARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 4);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glMultiTexCoord4ivARB(@NativeType("GLenum") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glMultiTexCoord4ivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glMultiTexCoord4dvARB(@NativeType("GLenum") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glMultiTexCoord4dvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(i, dArr, j);
    }
}
