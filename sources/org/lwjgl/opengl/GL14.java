package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL14.class */
public class GL14 extends GL13 {
    public static final int GL_GENERATE_MIPMAP = 33169;
    public static final int GL_GENERATE_MIPMAP_HINT = 33170;
    public static final int GL_CONSTANT_COLOR = 32769;
    public static final int GL_ONE_MINUS_CONSTANT_COLOR = 32770;
    public static final int GL_CONSTANT_ALPHA = 32771;
    public static final int GL_ONE_MINUS_CONSTANT_ALPHA = 32772;
    public static final int GL_BLEND_COLOR = 32773;
    public static final int GL_FUNC_ADD = 32774;
    public static final int GL_MIN = 32775;
    public static final int GL_MAX = 32776;
    public static final int GL_BLEND_EQUATION = 32777;
    public static final int GL_FUNC_SUBTRACT = 32778;
    public static final int GL_FUNC_REVERSE_SUBTRACT = 32779;
    public static final int GL_DEPTH_COMPONENT16 = 33189;
    public static final int GL_DEPTH_COMPONENT24 = 33190;
    public static final int GL_DEPTH_COMPONENT32 = 33191;
    public static final int GL_TEXTURE_DEPTH_SIZE = 34890;
    public static final int GL_DEPTH_TEXTURE_MODE = 34891;
    public static final int GL_TEXTURE_COMPARE_MODE = 34892;
    public static final int GL_TEXTURE_COMPARE_FUNC = 34893;
    public static final int GL_COMPARE_R_TO_TEXTURE = 34894;
    public static final int GL_FOG_COORDINATE_SOURCE = 33872;
    public static final int GL_FOG_COORDINATE = 33873;
    public static final int GL_FRAGMENT_DEPTH = 33874;
    public static final int GL_CURRENT_FOG_COORDINATE = 33875;
    public static final int GL_FOG_COORDINATE_ARRAY_TYPE = 33876;
    public static final int GL_FOG_COORDINATE_ARRAY_STRIDE = 33877;
    public static final int GL_FOG_COORDINATE_ARRAY_POINTER = 33878;
    public static final int GL_FOG_COORDINATE_ARRAY = 33879;
    public static final int GL_POINT_SIZE_MIN = 33062;
    public static final int GL_POINT_SIZE_MAX = 33063;
    public static final int GL_POINT_FADE_THRESHOLD_SIZE = 33064;
    public static final int GL_POINT_DISTANCE_ATTENUATION = 33065;
    public static final int GL_COLOR_SUM = 33880;
    public static final int GL_CURRENT_SECONDARY_COLOR = 33881;
    public static final int GL_SECONDARY_COLOR_ARRAY_SIZE = 33882;
    public static final int GL_SECONDARY_COLOR_ARRAY_TYPE = 33883;
    public static final int GL_SECONDARY_COLOR_ARRAY_STRIDE = 33884;
    public static final int GL_SECONDARY_COLOR_ARRAY_POINTER = 33885;
    public static final int GL_SECONDARY_COLOR_ARRAY = 33886;
    public static final int GL_BLEND_DST_RGB = 32968;
    public static final int GL_BLEND_SRC_RGB = 32969;
    public static final int GL_BLEND_DST_ALPHA = 32970;
    public static final int GL_BLEND_SRC_ALPHA = 32971;
    public static final int GL_INCR_WRAP = 34055;
    public static final int GL_DECR_WRAP = 34056;
    public static final int GL_TEXTURE_FILTER_CONTROL = 34048;
    public static final int GL_TEXTURE_LOD_BIAS = 34049;
    public static final int GL_MAX_TEXTURE_LOD_BIAS = 34045;
    public static final int GL_MIRRORED_REPEAT = 33648;

    public static native void glFogCoordf(@NativeType("GLfloat") float f);

    public static native void glFogCoordd(@NativeType("GLdouble") double d);

    public static native void nglFogCoordfv(long j);

    public static native void nglFogCoorddv(long j);

    public static native void nglFogCoordPointer(int i, int i2, long j);

    public static native void glSecondaryColor3b(@NativeType("GLbyte") byte b2, @NativeType("GLbyte") byte b3, @NativeType("GLbyte") byte b4);

    public static native void glSecondaryColor3s(@NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3);

    public static native void glSecondaryColor3i(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3);

    public static native void glSecondaryColor3f(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3);

    public static native void glSecondaryColor3d(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3);

    public static native void glSecondaryColor3ub(@NativeType("GLubyte") byte b2, @NativeType("GLubyte") byte b3, @NativeType("GLubyte") byte b4);

    public static native void glSecondaryColor3us(@NativeType("GLushort") short s, @NativeType("GLushort") short s2, @NativeType("GLushort") short s3);

    public static native void glSecondaryColor3ui(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3);

    public static native void nglSecondaryColor3bv(long j);

    public static native void nglSecondaryColor3sv(long j);

    public static native void nglSecondaryColor3iv(long j);

    public static native void nglSecondaryColor3fv(long j);

    public static native void nglSecondaryColor3dv(long j);

    public static native void nglSecondaryColor3ubv(long j);

    public static native void nglSecondaryColor3usv(long j);

    public static native void nglSecondaryColor3uiv(long j);

    public static native void nglSecondaryColorPointer(int i, int i2, int i3, long j);

    public static native void glWindowPos2i(@NativeType("GLint") int i, @NativeType("GLint") int i2);

    public static native void glWindowPos2s(@NativeType("GLshort") short s, @NativeType("GLshort") short s2);

    public static native void glWindowPos2f(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2);

    public static native void glWindowPos2d(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2);

    public static native void nglWindowPos2iv(long j);

    public static native void nglWindowPos2sv(long j);

    public static native void nglWindowPos2fv(long j);

    public static native void nglWindowPos2dv(long j);

    public static native void glWindowPos3i(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3);

    public static native void glWindowPos3s(@NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3);

    public static native void glWindowPos3f(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3);

    public static native void glWindowPos3d(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3);

    public static native void nglWindowPos3iv(long j);

    public static native void nglWindowPos3sv(long j);

    public static native void nglWindowPos3fv(long j);

    public static native void nglWindowPos3dv(long j);

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL14() {
        throw new UnsupportedOperationException();
    }

    public static void glBlendColor(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4) {
        GL14C.glBlendColor(f, f2, f3, f4);
    }

    public static void glBlendEquation(@NativeType("GLenum") int i) {
        GL14C.glBlendEquation(i);
    }

    public static void glFogCoordfv(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglFogCoordfv(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glFogCoorddv(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 1);
        }
        nglFogCoorddv(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glFogCoordPointer(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglFogCoordPointer(i, i2, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glFogCoordPointer(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("void const *") long j) {
        nglFogCoordPointer(i, i2, j);
    }

    public static void glFogCoordPointer(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglFogCoordPointer(i, i2, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glFogCoordPointer(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglFogCoordPointer(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void nglMultiDrawArrays(int i, long j, long j2, int i2) {
        GL14C.nglMultiDrawArrays(i, j, j2, i2);
    }

    public static void glMultiDrawArrays(@NativeType("GLenum") int i, @NativeType("GLint const *") IntBuffer intBuffer, @NativeType("GLsizei const *") IntBuffer intBuffer2) {
        GL14C.glMultiDrawArrays(i, intBuffer, intBuffer2);
    }

    public static void nglMultiDrawElements(int i, long j, int i2, long j2, int i3) {
        GL14C.nglMultiDrawElements(i, j, i2, j2, i3);
    }

    public static void glMultiDrawElements(@NativeType("GLenum") int i, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLenum") int i2, @NativeType("void const **") PointerBuffer pointerBuffer) {
        GL14C.glMultiDrawElements(i, intBuffer, i2, pointerBuffer);
    }

    public static void glPointParameterf(@NativeType("GLenum") int i, @NativeType("GLfloat") float f) {
        GL14C.glPointParameterf(i, f);
    }

    public static void glPointParameteri(@NativeType("GLenum") int i, @NativeType("GLint") int i2) {
        GL14C.glPointParameteri(i, i2);
    }

    public static void nglPointParameterfv(int i, long j) {
        GL14C.nglPointParameterfv(i, j);
    }

    public static void glPointParameterfv(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL14C.glPointParameterfv(i, floatBuffer);
    }

    public static void nglPointParameteriv(int i, long j) {
        GL14C.nglPointParameteriv(i, j);
    }

    public static void glPointParameteriv(@NativeType("GLenum") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL14C.glPointParameteriv(i, intBuffer);
    }

    public static void glSecondaryColor3bv(@NativeType("GLbyte const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 3);
        }
        nglSecondaryColor3bv(MemoryUtil.memAddress(byteBuffer));
    }

    public static void glSecondaryColor3sv(@NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 3);
        }
        nglSecondaryColor3sv(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glSecondaryColor3iv(@NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 3);
        }
        nglSecondaryColor3iv(MemoryUtil.memAddress(intBuffer));
    }

    public static void glSecondaryColor3fv(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 3);
        }
        nglSecondaryColor3fv(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glSecondaryColor3dv(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 3);
        }
        nglSecondaryColor3dv(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glSecondaryColor3ubv(@NativeType("GLubyte const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 3);
        }
        nglSecondaryColor3ubv(MemoryUtil.memAddress(byteBuffer));
    }

    public static void glSecondaryColor3usv(@NativeType("GLushort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 3);
        }
        nglSecondaryColor3usv(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glSecondaryColor3uiv(@NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 3);
        }
        nglSecondaryColor3uiv(MemoryUtil.memAddress(intBuffer));
    }

    public static void glSecondaryColorPointer(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglSecondaryColorPointer(i, i2, i3, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glSecondaryColorPointer(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") long j) {
        nglSecondaryColorPointer(i, i2, i3, j);
    }

    public static void glSecondaryColorPointer(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglSecondaryColorPointer(i, i2, i3, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glSecondaryColorPointer(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") IntBuffer intBuffer) {
        nglSecondaryColorPointer(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glSecondaryColorPointer(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglSecondaryColorPointer(i, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glBlendFuncSeparate(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4) {
        GL14C.glBlendFuncSeparate(i, i2, i3, i4);
    }

    public static void glWindowPos2iv(@NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 2);
        }
        nglWindowPos2iv(MemoryUtil.memAddress(intBuffer));
    }

    public static void glWindowPos2sv(@NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 2);
        }
        nglWindowPos2sv(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glWindowPos2fv(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 2);
        }
        nglWindowPos2fv(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glWindowPos2dv(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 2);
        }
        nglWindowPos2dv(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glWindowPos3iv(@NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 3);
        }
        nglWindowPos3iv(MemoryUtil.memAddress(intBuffer));
    }

    public static void glWindowPos3sv(@NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 3);
        }
        nglWindowPos3sv(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glWindowPos3fv(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 3);
        }
        nglWindowPos3fv(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glWindowPos3dv(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 3);
        }
        nglWindowPos3dv(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glFogCoordfv(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glFogCoordfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(fArr, j);
    }

    public static void glFogCoorddv(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glFogCoorddv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 1);
        }
        JNI.callPV(dArr, j);
    }

    public static void glMultiDrawArrays(@NativeType("GLenum") int i, @NativeType("GLint const *") int[] iArr, @NativeType("GLsizei const *") int[] iArr2) {
        GL14C.glMultiDrawArrays(i, iArr, iArr2);
    }

    public static void glMultiDrawElements(@NativeType("GLenum") int i, @NativeType("GLsizei *") int[] iArr, @NativeType("GLenum") int i2, @NativeType("void const **") PointerBuffer pointerBuffer) {
        GL14C.glMultiDrawElements(i, iArr, i2, pointerBuffer);
    }

    public static void glPointParameterfv(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        GL14C.glPointParameterfv(i, fArr);
    }

    public static void glPointParameteriv(@NativeType("GLenum") int i, @NativeType("GLint const *") int[] iArr) {
        GL14C.glPointParameteriv(i, iArr);
    }

    public static void glSecondaryColor3sv(@NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glSecondaryColor3sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 3);
        }
        JNI.callPV(sArr, j);
    }

    public static void glSecondaryColor3iv(@NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glSecondaryColor3iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 3);
        }
        JNI.callPV(iArr, j);
    }

    public static void glSecondaryColor3fv(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glSecondaryColor3fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 3);
        }
        JNI.callPV(fArr, j);
    }

    public static void glSecondaryColor3dv(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glSecondaryColor3dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 3);
        }
        JNI.callPV(dArr, j);
    }

    public static void glSecondaryColor3usv(@NativeType("GLushort const *") short[] sArr) {
        long j = GL.getICD().glSecondaryColor3usv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 3);
        }
        JNI.callPV(sArr, j);
    }

    public static void glSecondaryColor3uiv(@NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glSecondaryColor3uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 3);
        }
        JNI.callPV(iArr, j);
    }

    public static void glWindowPos2iv(@NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glWindowPos2iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 2);
        }
        JNI.callPV(iArr, j);
    }

    public static void glWindowPos2sv(@NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glWindowPos2sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 2);
        }
        JNI.callPV(sArr, j);
    }

    public static void glWindowPos2fv(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glWindowPos2fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 2);
        }
        JNI.callPV(fArr, j);
    }

    public static void glWindowPos2dv(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glWindowPos2dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 2);
        }
        JNI.callPV(dArr, j);
    }

    public static void glWindowPos3iv(@NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glWindowPos3iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 3);
        }
        JNI.callPV(iArr, j);
    }

    public static void glWindowPos3sv(@NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glWindowPos3sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 3);
        }
        JNI.callPV(sArr, j);
    }

    public static void glWindowPos3fv(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glWindowPos3fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 3);
        }
        JNI.callPV(fArr, j);
    }

    public static void glWindowPos3dv(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glWindowPos3dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 3);
        }
        JNI.callPV(dArr, j);
    }
}
