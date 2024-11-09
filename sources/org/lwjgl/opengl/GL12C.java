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

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL12C.class */
public class GL12C extends GL11C {
    public static final int GL_ALIASED_LINE_WIDTH_RANGE = 33902;
    public static final int GL_SMOOTH_POINT_SIZE_RANGE = 2834;
    public static final int GL_SMOOTH_POINT_SIZE_GRANULARITY = 2835;
    public static final int GL_SMOOTH_LINE_WIDTH_RANGE = 2850;
    public static final int GL_SMOOTH_LINE_WIDTH_GRANULARITY = 2851;
    public static final int GL_TEXTURE_BINDING_3D = 32874;
    public static final int GL_PACK_SKIP_IMAGES = 32875;
    public static final int GL_PACK_IMAGE_HEIGHT = 32876;
    public static final int GL_UNPACK_SKIP_IMAGES = 32877;
    public static final int GL_UNPACK_IMAGE_HEIGHT = 32878;
    public static final int GL_TEXTURE_3D = 32879;
    public static final int GL_PROXY_TEXTURE_3D = 32880;
    public static final int GL_TEXTURE_DEPTH = 32881;
    public static final int GL_TEXTURE_WRAP_R = 32882;
    public static final int GL_MAX_3D_TEXTURE_SIZE = 32883;
    public static final int GL_BGR = 32992;
    public static final int GL_BGRA = 32993;
    public static final int GL_UNSIGNED_BYTE_3_3_2 = 32818;
    public static final int GL_UNSIGNED_BYTE_2_3_3_REV = 33634;
    public static final int GL_UNSIGNED_SHORT_5_6_5 = 33635;
    public static final int GL_UNSIGNED_SHORT_5_6_5_REV = 33636;
    public static final int GL_UNSIGNED_SHORT_4_4_4_4 = 32819;
    public static final int GL_UNSIGNED_SHORT_4_4_4_4_REV = 33637;
    public static final int GL_UNSIGNED_SHORT_5_5_5_1 = 32820;
    public static final int GL_UNSIGNED_SHORT_1_5_5_5_REV = 33638;
    public static final int GL_UNSIGNED_INT_8_8_8_8 = 32821;
    public static final int GL_UNSIGNED_INT_8_8_8_8_REV = 33639;
    public static final int GL_UNSIGNED_INT_10_10_10_2 = 32822;
    public static final int GL_UNSIGNED_INT_2_10_10_10_REV = 33640;
    public static final int GL_CLAMP_TO_EDGE = 33071;
    public static final int GL_TEXTURE_MIN_LOD = 33082;
    public static final int GL_TEXTURE_MAX_LOD = 33083;
    public static final int GL_TEXTURE_BASE_LEVEL = 33084;
    public static final int GL_TEXTURE_MAX_LEVEL = 33085;
    public static final int GL_MAX_ELEMENTS_VERTICES = 33000;
    public static final int GL_MAX_ELEMENTS_INDICES = 33001;

    public static native void nglTexImage3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, long j);

    public static native void nglTexSubImage3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, long j);

    public static native void glCopyTexSubImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLint") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9);

    public static native void nglDrawRangeElements(int i, int i2, int i3, int i4, int i5, long j);

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL12C() {
        throw new UnsupportedOperationException();
    }

    public static void glTexImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglTexImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glTexImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") long j) {
        nglTexImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, j);
    }

    public static void glTexImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglTexImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddressSafe(shortBuffer));
    }

    public static void glTexImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") IntBuffer intBuffer) {
        nglTexImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glTexImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglTexImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddressSafe(floatBuffer));
    }

    public static void glTexImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglTexImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddressSafe(doubleBuffer));
    }

    public static void glTexSubImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglTexSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glTexSubImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") long j) {
        nglTexSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, j);
    }

    public static void glTexSubImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglTexSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glTexSubImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") IntBuffer intBuffer) {
        nglTexSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTexSubImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglTexSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glTexSubImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglTexSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glDrawRangeElements(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("void const *") long j) {
        nglDrawRangeElements(i, i2, i3, i4, i5, j);
    }

    public static void glDrawRangeElements(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglDrawRangeElements(i, i2, i3, byteBuffer.remaining() >> GLChecks.typeToByteShift(i4), i4, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glDrawRangeElements(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglDrawRangeElements(i, i2, i3, byteBuffer.remaining(), 5121, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glDrawRangeElements(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglDrawRangeElements(i, i2, i3, shortBuffer.remaining(), 5123, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glDrawRangeElements(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("void const *") IntBuffer intBuffer) {
        nglDrawRangeElements(i, i2, i3, intBuffer.remaining(), 5125, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTexImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glTexImage3D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, sArr, j);
    }

    public static void glTexImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glTexImage3D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, iArr, j);
    }

    public static void glTexImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glTexImage3D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, fArr, j);
    }

    public static void glTexImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") double[] dArr) {
        long j = GL.getICD().glTexImage3D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, dArr, j);
    }

    public static void glTexSubImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glTexSubImage3D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, sArr, j);
    }

    public static void glTexSubImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glTexSubImage3D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, iArr, j);
    }

    public static void glTexSubImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glTexSubImage3D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, fArr, j);
    }

    public static void glTexSubImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") double[] dArr) {
        long j = GL.getICD().glTexSubImage3D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, dArr, j);
    }
}
