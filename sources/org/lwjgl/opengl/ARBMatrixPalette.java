package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBMatrixPalette.class */
public class ARBMatrixPalette {
    public static final int GL_MATRIX_PALETTE_ARB = 34880;
    public static final int GL_MAX_MATRIX_PALETTE_STACK_DEPTH_ARB = 34881;
    public static final int GL_MAX_PALETTE_MATRICES_ARB = 34882;
    public static final int GL_CURRENT_PALETTE_MATRIX_ARB = 34883;
    public static final int GL_MATRIX_INDEX_ARRAY_ARB = 34884;
    public static final int GL_CURRENT_MATRIX_INDEX_ARB = 34885;
    public static final int GL_MATRIX_INDEX_ARRAY_SIZE_ARB = 34886;
    public static final int GL_MATRIX_INDEX_ARRAY_TYPE_ARB = 34887;
    public static final int GL_MATRIX_INDEX_ARRAY_STRIDE_ARB = 34888;
    public static final int GL_MATRIX_INDEX_ARRAY_POINTER_ARB = 34889;

    public static native void glCurrentPaletteMatrixARB(@NativeType("GLint") int i);

    public static native void nglMatrixIndexuivARB(int i, long j);

    public static native void nglMatrixIndexubvARB(int i, long j);

    public static native void nglMatrixIndexusvARB(int i, long j);

    public static native void nglMatrixIndexPointerARB(int i, int i2, int i3, long j);

    static {
        GL.initialize();
    }

    protected ARBMatrixPalette() {
        throw new UnsupportedOperationException();
    }

    public static void glMatrixIndexuivARB(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglMatrixIndexuivARB(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glMatrixIndexubvARB(@NativeType("GLubyte *") ByteBuffer byteBuffer) {
        nglMatrixIndexubvARB(byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glMatrixIndexusvARB(@NativeType("GLushort *") ShortBuffer shortBuffer) {
        nglMatrixIndexusvARB(shortBuffer.remaining(), MemoryUtil.memAddress(shortBuffer));
    }

    public static void glMatrixIndexPointerARB(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglMatrixIndexPointerARB(i, i2, i3, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glMatrixIndexPointerARB(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") long j) {
        nglMatrixIndexPointerARB(i, i2, i3, j);
    }

    public static void glMatrixIndexPointerARB(@NativeType("GLint") int i, @NativeType("GLsizei") int i2, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglMatrixIndexPointerARB(i, 5121, i2, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glMatrixIndexPointerARB(@NativeType("GLint") int i, @NativeType("GLsizei") int i2, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglMatrixIndexPointerARB(i, 5123, i2, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glMatrixIndexPointerARB(@NativeType("GLint") int i, @NativeType("GLsizei") int i2, @NativeType("void const *") IntBuffer intBuffer) {
        nglMatrixIndexPointerARB(i, 5125, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glMatrixIndexuivARB(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glMatrixIndexuivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glMatrixIndexusvARB(@NativeType("GLushort *") short[] sArr) {
        long j = GL.getICD().glMatrixIndexusvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(sArr.length, sArr, j);
    }
}
