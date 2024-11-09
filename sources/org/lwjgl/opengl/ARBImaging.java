package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBImaging.class */
public class ARBImaging {
    public static final int GL_COLOR_TABLE = 32976;
    public static final int GL_POST_CONVOLUTION_COLOR_TABLE = 32977;
    public static final int GL_POST_COLOR_MATRIX_COLOR_TABLE = 32978;
    public static final int GL_PROXY_COLOR_TABLE = 32979;
    public static final int GL_PROXY_POST_CONVOLUTION_COLOR_TABLE = 32980;
    public static final int GL_PROXY_POST_COLOR_MATRIX_COLOR_TABLE = 32981;
    public static final int GL_COLOR_TABLE_SCALE = 32982;
    public static final int GL_COLOR_TABLE_BIAS = 32983;
    public static final int GL_COLOR_TABLE_FORMAT = 32984;
    public static final int GL_COLOR_TABLE_WIDTH = 32985;
    public static final int GL_COLOR_TABLE_RED_SIZE = 32986;
    public static final int GL_COLOR_TABLE_GREEN_SIZE = 32987;
    public static final int GL_COLOR_TABLE_BLUE_SIZE = 32988;
    public static final int GL_COLOR_TABLE_ALPHA_SIZE = 32989;
    public static final int GL_COLOR_TABLE_LUMINANCE_SIZE = 32990;
    public static final int GL_COLOR_TABLE_INTENSITY_SIZE = 32991;
    public static final int GL_TABLE_TOO_LARGE = 32817;
    public static final int GL_CONVOLUTION_1D = 32784;
    public static final int GL_CONVOLUTION_2D = 32785;
    public static final int GL_SEPARABLE_2D = 32786;
    public static final int GL_CONVOLUTION_BORDER_MODE = 32787;
    public static final int GL_CONVOLUTION_FILTER_SCALE = 32788;
    public static final int GL_CONVOLUTION_FILTER_BIAS = 32789;
    public static final int GL_REDUCE = 32790;
    public static final int GL_CONVOLUTION_FORMAT = 32791;
    public static final int GL_CONVOLUTION_WIDTH = 32792;
    public static final int GL_CONVOLUTION_HEIGHT = 32793;
    public static final int GL_MAX_CONVOLUTION_WIDTH = 32794;
    public static final int GL_MAX_CONVOLUTION_HEIGHT = 32795;
    public static final int GL_POST_CONVOLUTION_RED_SCALE = 32796;
    public static final int GL_POST_CONVOLUTION_GREEN_SCALE = 32797;
    public static final int GL_POST_CONVOLUTION_BLUE_SCALE = 32798;
    public static final int GL_POST_CONVOLUTION_ALPHA_SCALE = 32799;
    public static final int GL_POST_CONVOLUTION_RED_BIAS = 32800;
    public static final int GL_POST_CONVOLUTION_GREEN_BIAS = 32801;
    public static final int GL_POST_CONVOLUTION_BLUE_BIAS = 32802;
    public static final int GL_POST_CONVOLUTION_ALPHA_BIAS = 32803;
    public static final int GL_CONSTANT_BORDER = 33105;
    public static final int GL_REPLICATE_BORDER = 33107;
    public static final int GL_CONVOLUTION_BORDER_COLOR = 33108;
    public static final int GL_COLOR_MATRIX = 32945;
    public static final int GL_COLOR_MATRIX_STACK_DEPTH = 32946;
    public static final int GL_MAX_COLOR_MATRIX_STACK_DEPTH = 32947;
    public static final int GL_POST_COLOR_MATRIX_RED_SCALE = 32948;
    public static final int GL_POST_COLOR_MATRIX_GREEN_SCALE = 32949;
    public static final int GL_POST_COLOR_MATRIX_BLUE_SCALE = 32950;
    public static final int GL_POST_COLOR_MATRIX_ALPHA_SCALE = 32951;
    public static final int GL_POST_COLOR_MATRIX_RED_BIAS = 32952;
    public static final int GL_POST_COLOR_MATRIX_GREEN_BIAS = 32953;
    public static final int GL_POST_COLOR_MATRIX_BLUE_BIAS = 32954;
    public static final int GL_POST_COLOR_MATRIX_ALPHA_BIAS = 32955;
    public static final int GL_HISTOGRAM = 32804;
    public static final int GL_PROXY_HISTOGRAM = 32805;
    public static final int GL_HISTOGRAM_WIDTH = 32806;
    public static final int GL_HISTOGRAM_FORMAT = 32807;
    public static final int GL_HISTOGRAM_RED_SIZE = 32808;
    public static final int GL_HISTOGRAM_GREEN_SIZE = 32809;
    public static final int GL_HISTOGRAM_BLUE_SIZE = 32810;
    public static final int GL_HISTOGRAM_ALPHA_SIZE = 32811;
    public static final int GL_HISTOGRAM_LUMINANCE_SIZE = 32812;
    public static final int GL_HISTOGRAM_SINK = 32813;
    public static final int GL_MINMAX = 32814;
    public static final int GL_MINMAX_FORMAT = 32815;
    public static final int GL_MINMAX_SINK = 32816;
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

    public static native void nglColorTable(int i, int i2, int i3, int i4, int i5, long j);

    public static native void glCopyColorTable(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5);

    public static native void nglColorTableParameteriv(int i, int i2, long j);

    public static native void nglColorTableParameterfv(int i, int i2, long j);

    public static native void nglGetColorTable(int i, int i2, int i3, long j);

    public static native void nglGetColorTableParameteriv(int i, int i2, long j);

    public static native void nglGetColorTableParameterfv(int i, int i2, long j);

    public static native void nglColorSubTable(int i, int i2, int i3, int i4, int i5, long j);

    public static native void glCopyColorSubTable(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5);

    public static native void nglConvolutionFilter1D(int i, int i2, int i3, int i4, int i5, long j);

    public static native void nglConvolutionFilter2D(int i, int i2, int i3, int i4, int i5, int i6, long j);

    public static native void glCopyConvolutionFilter1D(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5);

    public static native void glCopyConvolutionFilter2D(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6);

    public static native void nglGetConvolutionFilter(int i, int i2, int i3, long j);

    public static native void nglSeparableFilter2D(int i, int i2, int i3, int i4, int i5, int i6, long j, long j2);

    public static native void nglGetSeparableFilter(int i, int i2, int i3, long j, long j2, long j3);

    public static native void glConvolutionParameteri(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3);

    public static native void nglConvolutionParameteriv(int i, int i2, long j);

    public static native void glConvolutionParameterf(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat") float f);

    public static native void nglConvolutionParameterfv(int i, int i2, long j);

    public static native void nglGetConvolutionParameteriv(int i, int i2, long j);

    public static native void nglGetConvolutionParameterfv(int i, int i2, long j);

    public static native void glHistogram(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z);

    public static native void glResetHistogram(@NativeType("GLenum") int i);

    public static native void nglGetHistogram(int i, boolean z, int i2, int i3, long j);

    public static native void nglGetHistogramParameteriv(int i, int i2, long j);

    public static native void nglGetHistogramParameterfv(int i, int i2, long j);

    public static native void glMinmax(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z);

    public static native void glResetMinmax(@NativeType("GLenum") int i);

    public static native void nglGetMinmax(int i, boolean z, int i2, int i3, long j);

    public static native void nglGetMinmaxParameteriv(int i, int i2, long j);

    public static native void nglGetMinmaxParameterfv(int i, int i2, long j);

    static {
        GL.initialize();
    }

    protected ARBImaging() {
        throw new UnsupportedOperationException();
    }

    public static void glColorTable(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglColorTable(i, i2, i3, i4, i5, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glColorTable(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void const *") long j) {
        nglColorTable(i, i2, i3, i4, i5, j);
    }

    public static void glColorTable(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglColorTable(i, i2, i3, i4, i5, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glColorTable(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void const *") IntBuffer intBuffer) {
        nglColorTable(i, i2, i3, i4, i5, MemoryUtil.memAddress(intBuffer));
    }

    public static void glColorTable(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglColorTable(i, i2, i3, i4, i5, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glColorTableParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglColorTableParameteriv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glColorTableParameterfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglColorTableParameterfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetColorTable(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") ByteBuffer byteBuffer) {
        nglGetColorTable(i, i2, i3, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetColorTable(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") long j) {
        nglGetColorTable(i, i2, i3, j);
    }

    public static void glGetColorTable(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") ShortBuffer shortBuffer) {
        nglGetColorTable(i, i2, i3, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glGetColorTable(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") IntBuffer intBuffer) {
        nglGetColorTable(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetColorTable(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") FloatBuffer floatBuffer) {
        nglGetColorTable(i, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetColorTableParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglGetColorTableParameteriv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetColorTableParameteri(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetColorTableParameteriv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetColorTableParameterfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglGetColorTableParameterfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetColorTableParameterf(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetColorTableParameterfv(i, i2, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glColorSubTable(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglColorSubTable(i, i2, i3, i4, i5, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glColorSubTable(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void const *") long j) {
        nglColorSubTable(i, i2, i3, i4, i5, j);
    }

    public static void glConvolutionFilter1D(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglConvolutionFilter1D(i, i2, i3, i4, i5, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glConvolutionFilter1D(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void const *") long j) {
        nglConvolutionFilter1D(i, i2, i3, i4, i5, j);
    }

    public static void glConvolutionFilter2D(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglConvolutionFilter2D(i, i2, i3, i4, i5, i6, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glConvolutionFilter2D(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") long j) {
        nglConvolutionFilter2D(i, i2, i3, i4, i5, i6, j);
    }

    public static void glGetConvolutionFilter(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") ByteBuffer byteBuffer) {
        nglGetConvolutionFilter(i, i2, i3, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetConvolutionFilter(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") long j) {
        nglGetConvolutionFilter(i, i2, i3, j);
    }

    public static void glSeparableFilter2D(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("void const *") ByteBuffer byteBuffer2) {
        nglSeparableFilter2D(i, i2, i3, i4, i5, i6, MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(byteBuffer2));
    }

    public static void glSeparableFilter2D(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") long j, @NativeType("void const *") long j2) {
        nglSeparableFilter2D(i, i2, i3, i4, i5, i6, j, j2);
    }

    public static void glGetSeparableFilter(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") ByteBuffer byteBuffer, @NativeType("void *") ByteBuffer byteBuffer2, @NativeType("void *") ByteBuffer byteBuffer3) {
        nglGetSeparableFilter(i, i2, i3, MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(byteBuffer2), MemoryUtil.memAddressSafe(byteBuffer3));
    }

    public static void glGetSeparableFilter(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") long j, @NativeType("void *") long j2, @NativeType("void *") ByteBuffer byteBuffer) {
        nglGetSeparableFilter(i, i2, i3, j, j2, MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glConvolutionParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglConvolutionParameteriv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glConvolutionParameterfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglConvolutionParameterfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetConvolutionParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglGetConvolutionParameteriv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetConvolutionParameteri(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetConvolutionParameteriv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetConvolutionParameterfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglGetConvolutionParameterfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetConvolutionParameterf(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetConvolutionParameterfv(i, i2, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetHistogram(@NativeType("GLenum") int i, @NativeType("GLboolean") boolean z, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") ByteBuffer byteBuffer) {
        nglGetHistogram(i, z, i2, i3, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetHistogram(@NativeType("GLenum") int i, @NativeType("GLboolean") boolean z, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") long j) {
        nglGetHistogram(i, z, i2, i3, j);
    }

    public static void glGetHistogramParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetHistogramParameteriv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetHistogramParameteri(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetHistogramParameteriv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetHistogramParameterfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetHistogramParameterfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetHistogramParameterf(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetHistogramParameterfv(i, i2, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetMinmax(@NativeType("GLenum") int i, @NativeType("GLboolean") boolean z, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") ByteBuffer byteBuffer) {
        nglGetMinmax(i, z, i2, i3, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetMinmax(@NativeType("GLenum") int i, @NativeType("GLboolean") boolean z, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") long j) {
        nglGetMinmax(i, z, i2, i3, j);
    }

    public static void glGetMinmaxParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetMinmaxParameteriv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetMinmaxParameteri(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetMinmaxParameteriv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetMinmaxParameterfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetMinmaxParameterfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetMinmaxParameterf(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetMinmaxParameterfv(i, i2, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glBlendColor(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4) {
        GL14C.glBlendColor(f, f2, f3, f4);
    }

    public static void glBlendEquation(@NativeType("GLenum") int i) {
        GL14C.glBlendEquation(i);
    }

    public static void glColorTable(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glColorTable;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, sArr, j);
    }

    public static void glColorTable(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glColorTable;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, iArr, j);
    }

    public static void glColorTable(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glColorTable;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, fArr, j);
    }

    public static void glColorTableParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glColorTableParameteriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glColorTableParameterfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glColorTableParameterfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetColorTable(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") short[] sArr) {
        long j = GL.getICD().glGetColorTable;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, sArr, j);
    }

    public static void glGetColorTable(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") int[] iArr) {
        long j = GL.getICD().glGetColorTable;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glGetColorTable(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") float[] fArr) {
        long j = GL.getICD().glGetColorTable;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, fArr, j);
    }

    public static void glGetColorTableParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetColorTableParameteriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetColorTableParameterfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetColorTableParameterfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glConvolutionParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glConvolutionParameteriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glConvolutionParameterfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glConvolutionParameterfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetConvolutionParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetConvolutionParameteriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetConvolutionParameterfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetConvolutionParameterfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetHistogramParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetHistogramParameteriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetHistogramParameterfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetHistogramParameterfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetMinmaxParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetMinmaxParameteriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetMinmaxParameterfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetMinmaxParameterfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, fArr, j);
    }
}
