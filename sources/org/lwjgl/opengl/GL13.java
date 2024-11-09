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

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL13.class */
public class GL13 extends GL12 {
    public static final int GL_COMPRESSED_ALPHA = 34025;
    public static final int GL_COMPRESSED_LUMINANCE = 34026;
    public static final int GL_COMPRESSED_LUMINANCE_ALPHA = 34027;
    public static final int GL_COMPRESSED_INTENSITY = 34028;
    public static final int GL_COMPRESSED_RGB = 34029;
    public static final int GL_COMPRESSED_RGBA = 34030;
    public static final int GL_TEXTURE_COMPRESSION_HINT = 34031;
    public static final int GL_TEXTURE_COMPRESSED_IMAGE_SIZE = 34464;
    public static final int GL_TEXTURE_COMPRESSED = 34465;
    public static final int GL_NUM_COMPRESSED_TEXTURE_FORMATS = 34466;
    public static final int GL_COMPRESSED_TEXTURE_FORMATS = 34467;
    public static final int GL_NORMAL_MAP = 34065;
    public static final int GL_REFLECTION_MAP = 34066;
    public static final int GL_TEXTURE_CUBE_MAP = 34067;
    public static final int GL_TEXTURE_BINDING_CUBE_MAP = 34068;
    public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_X = 34069;
    public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_X = 34070;
    public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_Y = 34071;
    public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_Y = 34072;
    public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_Z = 34073;
    public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_Z = 34074;
    public static final int GL_PROXY_TEXTURE_CUBE_MAP = 34075;
    public static final int GL_MAX_CUBE_MAP_TEXTURE_SIZE = 34076;
    public static final int GL_MULTISAMPLE = 32925;
    public static final int GL_SAMPLE_ALPHA_TO_COVERAGE = 32926;
    public static final int GL_SAMPLE_ALPHA_TO_ONE = 32927;
    public static final int GL_SAMPLE_COVERAGE = 32928;
    public static final int GL_MULTISAMPLE_BIT = 536870912;
    public static final int GL_SAMPLE_BUFFERS = 32936;
    public static final int GL_SAMPLES = 32937;
    public static final int GL_SAMPLE_COVERAGE_VALUE = 32938;
    public static final int GL_SAMPLE_COVERAGE_INVERT = 32939;
    public static final int GL_TEXTURE0 = 33984;
    public static final int GL_TEXTURE1 = 33985;
    public static final int GL_TEXTURE2 = 33986;
    public static final int GL_TEXTURE3 = 33987;
    public static final int GL_TEXTURE4 = 33988;
    public static final int GL_TEXTURE5 = 33989;
    public static final int GL_TEXTURE6 = 33990;
    public static final int GL_TEXTURE7 = 33991;
    public static final int GL_TEXTURE8 = 33992;
    public static final int GL_TEXTURE9 = 33993;
    public static final int GL_TEXTURE10 = 33994;
    public static final int GL_TEXTURE11 = 33995;
    public static final int GL_TEXTURE12 = 33996;
    public static final int GL_TEXTURE13 = 33997;
    public static final int GL_TEXTURE14 = 33998;
    public static final int GL_TEXTURE15 = 33999;
    public static final int GL_TEXTURE16 = 34000;
    public static final int GL_TEXTURE17 = 34001;
    public static final int GL_TEXTURE18 = 34002;
    public static final int GL_TEXTURE19 = 34003;
    public static final int GL_TEXTURE20 = 34004;
    public static final int GL_TEXTURE21 = 34005;
    public static final int GL_TEXTURE22 = 34006;
    public static final int GL_TEXTURE23 = 34007;
    public static final int GL_TEXTURE24 = 34008;
    public static final int GL_TEXTURE25 = 34009;
    public static final int GL_TEXTURE26 = 34010;
    public static final int GL_TEXTURE27 = 34011;
    public static final int GL_TEXTURE28 = 34012;
    public static final int GL_TEXTURE29 = 34013;
    public static final int GL_TEXTURE30 = 34014;
    public static final int GL_TEXTURE31 = 34015;
    public static final int GL_ACTIVE_TEXTURE = 34016;
    public static final int GL_CLIENT_ACTIVE_TEXTURE = 34017;
    public static final int GL_MAX_TEXTURE_UNITS = 34018;
    public static final int GL_COMBINE = 34160;
    public static final int GL_COMBINE_RGB = 34161;
    public static final int GL_COMBINE_ALPHA = 34162;
    public static final int GL_SOURCE0_RGB = 34176;
    public static final int GL_SOURCE1_RGB = 34177;
    public static final int GL_SOURCE2_RGB = 34178;
    public static final int GL_SOURCE0_ALPHA = 34184;
    public static final int GL_SOURCE1_ALPHA = 34185;
    public static final int GL_SOURCE2_ALPHA = 34186;
    public static final int GL_OPERAND0_RGB = 34192;
    public static final int GL_OPERAND1_RGB = 34193;
    public static final int GL_OPERAND2_RGB = 34194;
    public static final int GL_OPERAND0_ALPHA = 34200;
    public static final int GL_OPERAND1_ALPHA = 34201;
    public static final int GL_OPERAND2_ALPHA = 34202;
    public static final int GL_RGB_SCALE = 34163;
    public static final int GL_ADD_SIGNED = 34164;
    public static final int GL_INTERPOLATE = 34165;
    public static final int GL_SUBTRACT = 34023;
    public static final int GL_CONSTANT = 34166;
    public static final int GL_PRIMARY_COLOR = 34167;
    public static final int GL_PREVIOUS = 34168;
    public static final int GL_DOT3_RGB = 34478;
    public static final int GL_DOT3_RGBA = 34479;
    public static final int GL_CLAMP_TO_BORDER = 33069;
    public static final int GL_TRANSPOSE_MODELVIEW_MATRIX = 34019;
    public static final int GL_TRANSPOSE_PROJECTION_MATRIX = 34020;
    public static final int GL_TRANSPOSE_TEXTURE_MATRIX = 34021;
    public static final int GL_TRANSPOSE_COLOR_MATRIX = 34022;

    public static native void glClientActiveTexture(@NativeType("GLenum") int i);

    public static native void glMultiTexCoord1f(@NativeType("GLenum") int i, @NativeType("GLfloat") float f);

    public static native void glMultiTexCoord1s(@NativeType("GLenum") int i, @NativeType("GLshort") short s);

    public static native void glMultiTexCoord1i(@NativeType("GLenum") int i, @NativeType("GLint") int i2);

    public static native void glMultiTexCoord1d(@NativeType("GLenum") int i, @NativeType("GLdouble") double d);

    public static native void nglMultiTexCoord1fv(int i, long j);

    public static native void nglMultiTexCoord1sv(int i, long j);

    public static native void nglMultiTexCoord1iv(int i, long j);

    public static native void nglMultiTexCoord1dv(int i, long j);

    public static native void glMultiTexCoord2f(@NativeType("GLenum") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2);

    public static native void glMultiTexCoord2s(@NativeType("GLenum") int i, @NativeType("GLshort") short s, @NativeType("GLshort") short s2);

    public static native void glMultiTexCoord2i(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3);

    public static native void glMultiTexCoord2d(@NativeType("GLenum") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2);

    public static native void nglMultiTexCoord2fv(int i, long j);

    public static native void nglMultiTexCoord2sv(int i, long j);

    public static native void nglMultiTexCoord2iv(int i, long j);

    public static native void nglMultiTexCoord2dv(int i, long j);

    public static native void glMultiTexCoord3f(@NativeType("GLenum") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3);

    public static native void glMultiTexCoord3s(@NativeType("GLenum") int i, @NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3);

    public static native void glMultiTexCoord3i(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4);

    public static native void glMultiTexCoord3d(@NativeType("GLenum") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3);

    public static native void nglMultiTexCoord3fv(int i, long j);

    public static native void nglMultiTexCoord3sv(int i, long j);

    public static native void nglMultiTexCoord3iv(int i, long j);

    public static native void nglMultiTexCoord3dv(int i, long j);

    public static native void glMultiTexCoord4f(@NativeType("GLenum") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4);

    public static native void glMultiTexCoord4s(@NativeType("GLenum") int i, @NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3, @NativeType("GLshort") short s4);

    public static native void glMultiTexCoord4i(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5);

    public static native void glMultiTexCoord4d(@NativeType("GLenum") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4);

    public static native void nglMultiTexCoord4fv(int i, long j);

    public static native void nglMultiTexCoord4sv(int i, long j);

    public static native void nglMultiTexCoord4iv(int i, long j);

    public static native void nglMultiTexCoord4dv(int i, long j);

    public static native void nglLoadTransposeMatrixf(long j);

    public static native void nglLoadTransposeMatrixd(long j);

    public static native void nglMultTransposeMatrixf(long j);

    public static native void nglMultTransposeMatrixd(long j);

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL13() {
        throw new UnsupportedOperationException();
    }

    public static void nglCompressedTexImage3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j) {
        GL13C.nglCompressedTexImage3D(i, i2, i3, i4, i5, i6, i7, i8, j);
    }

    public static void glCompressedTexImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLsizei") int i8, @NativeType("void const *") long j) {
        GL13C.glCompressedTexImage3D(i, i2, i3, i4, i5, i6, i7, i8, j);
    }

    public static void glCompressedTexImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL13C.glCompressedTexImage3D(i, i2, i3, i4, i5, i6, i7, byteBuffer);
    }

    public static void nglCompressedTexImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, long j) {
        GL13C.nglCompressedTexImage2D(i, i2, i3, i4, i5, i6, i7, j);
    }

    public static void glCompressedTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("void const *") long j) {
        GL13C.glCompressedTexImage2D(i, i2, i3, i4, i5, i6, i7, j);
    }

    public static void glCompressedTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL13C.glCompressedTexImage2D(i, i2, i3, i4, i5, i6, byteBuffer);
    }

    public static void nglCompressedTexImage1D(int i, int i2, int i3, int i4, int i5, int i6, long j) {
        GL13C.nglCompressedTexImage1D(i, i2, i3, i4, i5, i6, j);
    }

    public static void glCompressedTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("void const *") long j) {
        GL13C.glCompressedTexImage1D(i, i2, i3, i4, i5, i6, j);
    }

    public static void glCompressedTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL13C.glCompressedTexImage1D(i, i2, i3, i4, i5, byteBuffer);
    }

    public static void nglCompressedTexSubImage3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, long j) {
        GL13C.nglCompressedTexSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, j);
    }

    public static void glCompressedTexSubImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLsizei") int i10, @NativeType("void const *") long j) {
        GL13C.glCompressedTexSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, j);
    }

    public static void glCompressedTexSubImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL13C.glCompressedTexSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, byteBuffer);
    }

    public static void nglCompressedTexSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j) {
        GL13C.nglCompressedTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, j);
    }

    public static void glCompressedTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLsizei") int i8, @NativeType("void const *") long j) {
        GL13C.glCompressedTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, j);
    }

    public static void glCompressedTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL13C.glCompressedTexSubImage2D(i, i2, i3, i4, i5, i6, i7, byteBuffer);
    }

    public static void nglCompressedTexSubImage1D(int i, int i2, int i3, int i4, int i5, int i6, long j) {
        GL13C.nglCompressedTexSubImage1D(i, i2, i3, i4, i5, i6, j);
    }

    public static void glCompressedTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLsizei") int i6, @NativeType("void const *") long j) {
        GL13C.glCompressedTexSubImage1D(i, i2, i3, i4, i5, i6, j);
    }

    public static void glCompressedTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL13C.glCompressedTexSubImage1D(i, i2, i3, i4, i5, byteBuffer);
    }

    public static void nglGetCompressedTexImage(int i, int i2, long j) {
        GL13C.nglGetCompressedTexImage(i, i2, j);
    }

    public static void glGetCompressedTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("void *") ByteBuffer byteBuffer) {
        GL13C.glGetCompressedTexImage(i, i2, byteBuffer);
    }

    public static void glGetCompressedTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("void *") long j) {
        GL13C.glGetCompressedTexImage(i, i2, j);
    }

    public static void glSampleCoverage(@NativeType("GLfloat") float f, @NativeType("GLboolean") boolean z) {
        GL13C.glSampleCoverage(f, z);
    }

    public static void glActiveTexture(@NativeType("GLenum") int i) {
        GL13C.glActiveTexture(i);
    }

    public static void glMultiTexCoord1fv(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglMultiTexCoord1fv(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMultiTexCoord1sv(@NativeType("GLenum") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 1);
        }
        nglMultiTexCoord1sv(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glMultiTexCoord1iv(@NativeType("GLenum") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglMultiTexCoord1iv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glMultiTexCoord1dv(@NativeType("GLenum") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 1);
        }
        nglMultiTexCoord1dv(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glMultiTexCoord2fv(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 2);
        }
        nglMultiTexCoord2fv(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMultiTexCoord2sv(@NativeType("GLenum") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 2);
        }
        nglMultiTexCoord2sv(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glMultiTexCoord2iv(@NativeType("GLenum") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 2);
        }
        nglMultiTexCoord2iv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glMultiTexCoord2dv(@NativeType("GLenum") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 2);
        }
        nglMultiTexCoord2dv(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glMultiTexCoord3fv(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 3);
        }
        nglMultiTexCoord3fv(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMultiTexCoord3sv(@NativeType("GLenum") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 3);
        }
        nglMultiTexCoord3sv(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glMultiTexCoord3iv(@NativeType("GLenum") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 3);
        }
        nglMultiTexCoord3iv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glMultiTexCoord3dv(@NativeType("GLenum") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 3);
        }
        nglMultiTexCoord3dv(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glMultiTexCoord4fv(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglMultiTexCoord4fv(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMultiTexCoord4sv(@NativeType("GLenum") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 4);
        }
        nglMultiTexCoord4sv(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glMultiTexCoord4iv(@NativeType("GLenum") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglMultiTexCoord4iv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glMultiTexCoord4dv(@NativeType("GLenum") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglMultiTexCoord4dv(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glLoadTransposeMatrixf(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 16);
        }
        nglLoadTransposeMatrixf(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glLoadTransposeMatrixd(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 16);
        }
        nglLoadTransposeMatrixd(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glMultTransposeMatrixf(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 16);
        }
        nglMultTransposeMatrixf(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMultTransposeMatrixd(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 16);
        }
        nglMultTransposeMatrixd(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glMultiTexCoord1fv(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMultiTexCoord1fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glMultiTexCoord1sv(@NativeType("GLenum") int i, @NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glMultiTexCoord1sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 1);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glMultiTexCoord1iv(@NativeType("GLenum") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glMultiTexCoord1iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glMultiTexCoord1dv(@NativeType("GLenum") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glMultiTexCoord1dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 1);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glMultiTexCoord2fv(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMultiTexCoord2fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 2);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glMultiTexCoord2sv(@NativeType("GLenum") int i, @NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glMultiTexCoord2sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 2);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glMultiTexCoord2iv(@NativeType("GLenum") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glMultiTexCoord2iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 2);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glMultiTexCoord2dv(@NativeType("GLenum") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glMultiTexCoord2dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 2);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glMultiTexCoord3fv(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMultiTexCoord3fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 3);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glMultiTexCoord3sv(@NativeType("GLenum") int i, @NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glMultiTexCoord3sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 3);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glMultiTexCoord3iv(@NativeType("GLenum") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glMultiTexCoord3iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 3);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glMultiTexCoord3dv(@NativeType("GLenum") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glMultiTexCoord3dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 3);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glMultiTexCoord4fv(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMultiTexCoord4fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glMultiTexCoord4sv(@NativeType("GLenum") int i, @NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glMultiTexCoord4sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 4);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glMultiTexCoord4iv(@NativeType("GLenum") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glMultiTexCoord4iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glMultiTexCoord4dv(@NativeType("GLenum") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glMultiTexCoord4dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glLoadTransposeMatrixf(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glLoadTransposeMatrixf;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 16);
        }
        JNI.callPV(fArr, j);
    }

    public static void glLoadTransposeMatrixd(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glLoadTransposeMatrixd;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 16);
        }
        JNI.callPV(dArr, j);
    }

    public static void glMultTransposeMatrixf(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMultTransposeMatrixf;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 16);
        }
        JNI.callPV(fArr, j);
    }

    public static void glMultTransposeMatrixd(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glMultTransposeMatrixd;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 16);
        }
        JNI.callPV(dArr, j);
    }
}
