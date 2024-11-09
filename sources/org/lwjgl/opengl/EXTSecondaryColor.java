package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTSecondaryColor.class */
public class EXTSecondaryColor {
    public static final int GL_COLOR_SUM_EXT = 33880;
    public static final int GL_CURRENT_SECONDARY_COLOR_EXT = 33881;
    public static final int GL_SECONDARY_COLOR_ARRAY_SIZE_EXT = 33882;
    public static final int GL_SECONDARY_COLOR_ARRAY_TYPE_EXT = 33883;
    public static final int GL_SECONDARY_COLOR_ARRAY_STRIDE_EXT = 33884;
    public static final int GL_SECONDARY_COLOR_ARRAY_POINTER_EXT = 33885;
    public static final int GL_SECONDARY_COLOR_ARRAY_EXT = 33886;

    public static native void glSecondaryColor3bEXT(@NativeType("GLbyte") byte b2, @NativeType("GLbyte") byte b3, @NativeType("GLbyte") byte b4);

    public static native void glSecondaryColor3sEXT(@NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3);

    public static native void glSecondaryColor3iEXT(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3);

    public static native void glSecondaryColor3fEXT(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3);

    public static native void glSecondaryColor3dEXT(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3);

    public static native void glSecondaryColor3ubEXT(@NativeType("GLubyte") byte b2, @NativeType("GLubyte") byte b3, @NativeType("GLubyte") byte b4);

    public static native void glSecondaryColor3usEXT(@NativeType("GLushort") short s, @NativeType("GLushort") short s2, @NativeType("GLushort") short s3);

    public static native void glSecondaryColor3uiEXT(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3);

    public static native void nglSecondaryColor3bvEXT(long j);

    public static native void nglSecondaryColor3svEXT(long j);

    public static native void nglSecondaryColor3ivEXT(long j);

    public static native void nglSecondaryColor3fvEXT(long j);

    public static native void nglSecondaryColor3dvEXT(long j);

    public static native void nglSecondaryColor3ubvEXT(long j);

    public static native void nglSecondaryColor3usvEXT(long j);

    public static native void nglSecondaryColor3uivEXT(long j);

    public static native void nglSecondaryColorPointerEXT(int i, int i2, int i3, long j);

    static {
        GL.initialize();
    }

    protected EXTSecondaryColor() {
        throw new UnsupportedOperationException();
    }

    public static void glSecondaryColor3bvEXT(@NativeType("GLbyte const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 3);
        }
        nglSecondaryColor3bvEXT(MemoryUtil.memAddress(byteBuffer));
    }

    public static void glSecondaryColor3svEXT(@NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 3);
        }
        nglSecondaryColor3svEXT(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glSecondaryColor3ivEXT(@NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 3);
        }
        nglSecondaryColor3ivEXT(MemoryUtil.memAddress(intBuffer));
    }

    public static void glSecondaryColor3fvEXT(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 3);
        }
        nglSecondaryColor3fvEXT(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glSecondaryColor3dvEXT(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 3);
        }
        nglSecondaryColor3dvEXT(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glSecondaryColor3ubvEXT(@NativeType("GLubyte const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 3);
        }
        nglSecondaryColor3ubvEXT(MemoryUtil.memAddress(byteBuffer));
    }

    public static void glSecondaryColor3usvEXT(@NativeType("GLushort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 3);
        }
        nglSecondaryColor3usvEXT(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glSecondaryColor3uivEXT(@NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 3);
        }
        nglSecondaryColor3uivEXT(MemoryUtil.memAddress(intBuffer));
    }

    public static void glSecondaryColorPointerEXT(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglSecondaryColorPointerEXT(i, i2, i3, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glSecondaryColorPointerEXT(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") long j) {
        nglSecondaryColorPointerEXT(i, i2, i3, j);
    }

    public static void glSecondaryColorPointerEXT(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglSecondaryColorPointerEXT(i, i2, i3, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glSecondaryColorPointerEXT(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") IntBuffer intBuffer) {
        nglSecondaryColorPointerEXT(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glSecondaryColorPointerEXT(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglSecondaryColorPointerEXT(i, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glSecondaryColor3svEXT(@NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glSecondaryColor3svEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 3);
        }
        JNI.callPV(sArr, j);
    }

    public static void glSecondaryColor3ivEXT(@NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glSecondaryColor3ivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 3);
        }
        JNI.callPV(iArr, j);
    }

    public static void glSecondaryColor3fvEXT(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glSecondaryColor3fvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 3);
        }
        JNI.callPV(fArr, j);
    }

    public static void glSecondaryColor3dvEXT(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glSecondaryColor3dvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 3);
        }
        JNI.callPV(dArr, j);
    }

    public static void glSecondaryColor3usvEXT(@NativeType("GLushort const *") short[] sArr) {
        long j = GL.getICD().glSecondaryColor3usvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 3);
        }
        JNI.callPV(sArr, j);
    }

    public static void glSecondaryColor3uivEXT(@NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glSecondaryColor3uivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 3);
        }
        JNI.callPV(iArr, j);
    }

    public static void glSecondaryColorPointerEXT(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glSecondaryColorPointerEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, sArr, j);
    }

    public static void glSecondaryColorPointerEXT(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glSecondaryColorPointerEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glSecondaryColorPointerEXT(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glSecondaryColorPointerEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, fArr, j);
    }
}
