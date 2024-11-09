package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBRobustness.class */
public class ARBRobustness {
    public static final int GL_GUILTY_CONTEXT_RESET_ARB = 33363;
    public static final int GL_INNOCENT_CONTEXT_RESET_ARB = 33364;
    public static final int GL_UNKNOWN_CONTEXT_RESET_ARB = 33365;
    public static final int GL_RESET_NOTIFICATION_STRATEGY_ARB = 33366;
    public static final int GL_LOSE_CONTEXT_ON_RESET_ARB = 33362;
    public static final int GL_NO_RESET_NOTIFICATION_ARB = 33377;
    public static final int GL_CONTEXT_FLAG_ROBUST_ACCESS_BIT_ARB = 4;

    @NativeType("GLenum")
    public static native int glGetGraphicsResetStatusARB();

    public static native void nglGetnMapdvARB(int i, int i2, int i3, long j);

    public static native void nglGetnMapfvARB(int i, int i2, int i3, long j);

    public static native void nglGetnMapivARB(int i, int i2, int i3, long j);

    public static native void nglGetnPixelMapfvARB(int i, int i2, long j);

    public static native void nglGetnPixelMapuivARB(int i, int i2, long j);

    public static native void nglGetnPixelMapusvARB(int i, int i2, long j);

    public static native void nglGetnPolygonStippleARB(int i, long j);

    public static native void nglGetnTexImageARB(int i, int i2, int i3, int i4, int i5, long j);

    public static native void nglReadnPixelsARB(int i, int i2, int i3, int i4, int i5, int i6, int i7, long j);

    public static native void nglGetnColorTableARB(int i, int i2, int i3, int i4, long j);

    public static native void nglGetnConvolutionFilterARB(int i, int i2, int i3, int i4, long j);

    public static native void nglGetnSeparableFilterARB(int i, int i2, int i3, int i4, long j, int i5, long j2, long j3);

    public static native void nglGetnHistogramARB(int i, boolean z, int i2, int i3, int i4, long j);

    public static native void nglGetnMinmaxARB(int i, boolean z, int i2, int i3, int i4, long j);

    public static native void nglGetnCompressedTexImageARB(int i, int i2, int i3, long j);

    public static native void nglGetnUniformfvARB(int i, int i2, int i3, long j);

    public static native void nglGetnUniformivARB(int i, int i2, int i3, long j);

    public static native void nglGetnUniformuivARB(int i, int i2, int i3, long j);

    public static native void nglGetnUniformdvARB(int i, int i2, int i3, long j);

    static {
        GL.initialize();
    }

    protected ARBRobustness() {
        throw new UnsupportedOperationException();
    }

    public static void glGetnMapdvARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        nglGetnMapdvARB(i, i2, doubleBuffer.remaining(), MemoryUtil.memAddress(doubleBuffer));
    }

    @NativeType("void")
    public static double glGetnMapdARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            DoubleBuffer callocDouble = stackGet.callocDouble(1);
            nglGetnMapdvARB(i, i2, 1, MemoryUtil.memAddress(callocDouble));
            return callocDouble.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetnMapfvARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        nglGetnMapfvARB(i, i2, floatBuffer.remaining(), MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetnMapfARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetnMapfvARB(i, i2, 1, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetnMapivARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        nglGetnMapivARB(i, i2, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetnMapiARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetnMapivARB(i, i2, 1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetnPixelMapfvARB(@NativeType("GLenum") int i, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        nglGetnPixelMapfvARB(i, floatBuffer.remaining(), MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetnPixelMapuivARB(@NativeType("GLenum") int i, @NativeType("GLuint *") IntBuffer intBuffer) {
        nglGetnPixelMapuivARB(i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetnPixelMapusvARB(@NativeType("GLenum") int i, @NativeType("GLushort *") ShortBuffer shortBuffer) {
        nglGetnPixelMapusvARB(i, shortBuffer.remaining(), MemoryUtil.memAddress(shortBuffer));
    }

    public static void glGetnPolygonStippleARB(@NativeType("GLsizei") int i, @NativeType("GLubyte *") long j) {
        nglGetnPolygonStippleARB(i, j);
    }

    public static void glGetnPolygonStippleARB(@NativeType("GLubyte *") ByteBuffer byteBuffer) {
        nglGetnPolygonStippleARB(byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetnTexImageARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("void *") long j) {
        nglGetnTexImageARB(i, i2, i3, i4, i5, j);
    }

    public static void glGetnTexImageARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") ByteBuffer byteBuffer) {
        nglGetnTexImageARB(i, i2, i3, i4, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetnTexImageARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") ShortBuffer shortBuffer) {
        nglGetnTexImageARB(i, i2, i3, i4, shortBuffer.remaining() << 1, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glGetnTexImageARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") IntBuffer intBuffer) {
        nglGetnTexImageARB(i, i2, i3, i4, intBuffer.remaining() << 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetnTexImageARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") FloatBuffer floatBuffer) {
        nglGetnTexImageARB(i, i2, i3, i4, floatBuffer.remaining() << 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetnTexImageARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") DoubleBuffer doubleBuffer) {
        nglGetnTexImageARB(i, i2, i3, i4, doubleBuffer.remaining() << 3, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glReadnPixelsARB(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("GLsizei") int i7, @NativeType("void *") long j) {
        nglReadnPixelsARB(i, i2, i3, i4, i5, i6, i7, j);
    }

    public static void glReadnPixelsARB(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") ByteBuffer byteBuffer) {
        nglReadnPixelsARB(i, i2, i3, i4, i5, i6, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glReadnPixelsARB(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") ShortBuffer shortBuffer) {
        nglReadnPixelsARB(i, i2, i3, i4, i5, i6, shortBuffer.remaining() << 1, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glReadnPixelsARB(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") IntBuffer intBuffer) {
        nglReadnPixelsARB(i, i2, i3, i4, i5, i6, intBuffer.remaining() << 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glReadnPixelsARB(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") FloatBuffer floatBuffer) {
        nglReadnPixelsARB(i, i2, i3, i4, i5, i6, floatBuffer.remaining() << 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetnColorTableARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void *") long j) {
        nglGetnColorTableARB(i, i2, i3, i4, j);
    }

    public static void glGetnColorTableARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") ByteBuffer byteBuffer) {
        nglGetnColorTableARB(i, i2, i3, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetnColorTableARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") ShortBuffer shortBuffer) {
        nglGetnColorTableARB(i, i2, i3, shortBuffer.remaining() << 1, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glGetnColorTableARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") IntBuffer intBuffer) {
        nglGetnColorTableARB(i, i2, i3, intBuffer.remaining() << 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetnColorTableARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") FloatBuffer floatBuffer) {
        nglGetnColorTableARB(i, i2, i3, floatBuffer.remaining() << 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetnConvolutionFilterARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void *") long j) {
        nglGetnConvolutionFilterARB(i, i2, i3, i4, j);
    }

    public static void glGetnConvolutionFilterARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") ByteBuffer byteBuffer) {
        nglGetnConvolutionFilterARB(i, i2, i3, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetnSeparableFilterARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void *") long j, @NativeType("GLsizei") int i5, @NativeType("void *") long j2, @NativeType("void *") ByteBuffer byteBuffer) {
        nglGetnSeparableFilterARB(i, i2, i3, i4, j, i5, j2, MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glGetnSeparableFilterARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") ByteBuffer byteBuffer, @NativeType("void *") ByteBuffer byteBuffer2, @NativeType("void *") ByteBuffer byteBuffer3) {
        nglGetnSeparableFilterARB(i, i2, i3, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), byteBuffer2.remaining(), MemoryUtil.memAddress(byteBuffer2), MemoryUtil.memAddressSafe(byteBuffer3));
    }

    public static void glGetnHistogramARB(@NativeType("GLenum") int i, @NativeType("GLboolean") boolean z, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void *") long j) {
        nglGetnHistogramARB(i, z, i2, i3, i4, j);
    }

    public static void glGetnHistogramARB(@NativeType("GLenum") int i, @NativeType("GLboolean") boolean z, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") ByteBuffer byteBuffer) {
        nglGetnHistogramARB(i, z, i2, i3, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetnMinmaxARB(@NativeType("GLenum") int i, @NativeType("GLboolean") boolean z, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void *") long j) {
        nglGetnMinmaxARB(i, z, i2, i3, i4, j);
    }

    public static void glGetnMinmaxARB(@NativeType("GLenum") int i, @NativeType("GLboolean") boolean z, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") ByteBuffer byteBuffer) {
        nglGetnMinmaxARB(i, z, i2, i3, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetnCompressedTexImageARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("void *") long j) {
        nglGetnCompressedTexImageARB(i, i2, i3, j);
    }

    public static void glGetnCompressedTexImageARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("void *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS && Checks.DEBUG) {
            Checks.check((Buffer) byteBuffer, GL11.glGetTexLevelParameteri(i, i2, 34464));
        }
        nglGetnCompressedTexImageARB(i, i2, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetnUniformfvARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        nglGetnUniformfvARB(i, i2, floatBuffer.remaining(), MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetnUniformfARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetnUniformfvARB(i, i2, 1, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetnUniformivARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        nglGetnUniformivARB(i, i2, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetnUniformiARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetnUniformivARB(i, i2, 1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetnUniformuivARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint *") IntBuffer intBuffer) {
        nglGetnUniformuivARB(i, i2, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetnUniformuiARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetnUniformuivARB(i, i2, 1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetnUniformdvARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        nglGetnUniformdvARB(i, i2, doubleBuffer.remaining(), MemoryUtil.memAddress(doubleBuffer));
    }

    @NativeType("void")
    public static double glGetnUniformdARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            DoubleBuffer callocDouble = stackGet.callocDouble(1);
            nglGetnUniformdvARB(i, i2, 1, MemoryUtil.memAddress(callocDouble));
            return callocDouble.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetnMapdvARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble *") double[] dArr) {
        long j = GL.getICD().glGetnMapdvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length, dArr, j);
    }

    public static void glGetnMapfvARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetnMapfvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length, fArr, j);
    }

    public static void glGetnMapivARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetnMapivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr.length, iArr, j);
    }

    public static void glGetnPixelMapfvARB(@NativeType("GLenum") int i, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetnPixelMapfvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length, fArr, j);
    }

    public static void glGetnPixelMapuivARB(@NativeType("GLenum") int i, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetnPixelMapuivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length, iArr, j);
    }

    public static void glGetnPixelMapusvARB(@NativeType("GLenum") int i, @NativeType("GLushort *") short[] sArr) {
        long j = GL.getICD().glGetnPixelMapusvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, sArr.length, sArr, j);
    }

    public static void glGetnTexImageARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") short[] sArr) {
        long j = GL.getICD().glGetnTexImageARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, sArr.length << 1, sArr, j);
    }

    public static void glGetnTexImageARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") int[] iArr) {
        long j = GL.getICD().glGetnTexImageARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, iArr.length << 2, iArr, j);
    }

    public static void glGetnTexImageARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") float[] fArr) {
        long j = GL.getICD().glGetnTexImageARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, fArr.length << 2, fArr, j);
    }

    public static void glGetnTexImageARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") double[] dArr) {
        long j = GL.getICD().glGetnTexImageARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, dArr.length << 3, dArr, j);
    }

    public static void glReadnPixelsARB(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") short[] sArr) {
        long j = GL.getICD().glReadnPixelsARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, sArr.length << 1, sArr, j);
    }

    public static void glReadnPixelsARB(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") int[] iArr) {
        long j = GL.getICD().glReadnPixelsARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, iArr.length << 2, iArr, j);
    }

    public static void glReadnPixelsARB(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") float[] fArr) {
        long j = GL.getICD().glReadnPixelsARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, fArr.length << 2, fArr, j);
    }

    public static void glGetnColorTableARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") short[] sArr) {
        long j = GL.getICD().glGetnColorTableARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, sArr.length << 1, sArr, j);
    }

    public static void glGetnColorTableARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") int[] iArr) {
        long j = GL.getICD().glGetnColorTableARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, iArr.length << 2, iArr, j);
    }

    public static void glGetnColorTableARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") float[] fArr) {
        long j = GL.getICD().glGetnColorTableARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, fArr.length << 2, fArr, j);
    }

    public static void glGetnUniformfvARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetnUniformfvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length, fArr, j);
    }

    public static void glGetnUniformivARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetnUniformivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr.length, iArr, j);
    }

    public static void glGetnUniformuivARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetnUniformuivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr.length, iArr, j);
    }

    public static void glGetnUniformdvARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble *") double[] dArr) {
        long j = GL.getICD().glGetnUniformdvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length, dArr, j);
    }
}
