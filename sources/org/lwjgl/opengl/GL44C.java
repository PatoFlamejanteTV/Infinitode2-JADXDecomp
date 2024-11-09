package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL44C.class */
public class GL44C extends GL43C {
    public static final int GL_MAX_VERTEX_ATTRIB_STRIDE = 33509;
    public static final int GL_PRIMITIVE_RESTART_FOR_PATCHES_SUPPORTED = 33313;
    public static final int GL_TEXTURE_BUFFER_BINDING = 35882;
    public static final int GL_MAP_PERSISTENT_BIT = 64;
    public static final int GL_MAP_COHERENT_BIT = 128;
    public static final int GL_DYNAMIC_STORAGE_BIT = 256;
    public static final int GL_CLIENT_STORAGE_BIT = 512;
    public static final int GL_BUFFER_IMMUTABLE_STORAGE = 33311;
    public static final int GL_BUFFER_STORAGE_FLAGS = 33312;
    public static final int GL_CLIENT_MAPPED_BUFFER_BARRIER_BIT = 16384;
    public static final int GL_CLEAR_TEXTURE = 37733;
    public static final int GL_LOCATION_COMPONENT = 37706;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_INDEX = 37707;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_STRIDE = 37708;
    public static final int GL_QUERY_RESULT_NO_WAIT = 37268;
    public static final int GL_QUERY_BUFFER = 37266;
    public static final int GL_QUERY_BUFFER_BINDING = 37267;
    public static final int GL_QUERY_BUFFER_BARRIER_BIT = 32768;
    public static final int GL_MIRROR_CLAMP_TO_EDGE = 34627;

    public static native void nglBufferStorage(int i, long j, long j2, int i2);

    public static native void nglClearTexSubImage(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, long j);

    public static native void nglClearTexImage(int i, int i2, int i3, int i4, long j);

    public static native void nglBindBuffersBase(int i, int i2, int i3, long j);

    public static native void nglBindBuffersRange(int i, int i2, int i3, long j, long j2, long j3);

    public static native void nglBindTextures(int i, int i2, long j);

    public static native void nglBindSamplers(int i, int i2, long j);

    public static native void nglBindImageTextures(int i, int i2, long j);

    public static native void nglBindVertexBuffers(int i, int i2, long j, long j2, long j3);

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL44C() {
        throw new UnsupportedOperationException();
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("GLsizeiptr") long j, @NativeType("GLbitfield") int i2) {
        nglBufferStorage(i, j, 0L, i2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLbitfield") int i2) {
        nglBufferStorage(i, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), i2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLbitfield") int i2) {
        nglBufferStorage(i, Integer.toUnsignedLong(shortBuffer.remaining()) << 1, MemoryUtil.memAddress(shortBuffer), i2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLbitfield") int i2) {
        nglBufferStorage(i, Integer.toUnsignedLong(intBuffer.remaining()) << 2, MemoryUtil.memAddress(intBuffer), i2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") FloatBuffer floatBuffer, @NativeType("GLbitfield") int i2) {
        nglBufferStorage(i, Integer.toUnsignedLong(floatBuffer.remaining()) << 2, MemoryUtil.memAddress(floatBuffer), i2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") DoubleBuffer doubleBuffer, @NativeType("GLbitfield") int i2) {
        nglBufferStorage(i, Integer.toUnsignedLong(doubleBuffer.remaining()) << 3, MemoryUtil.memAddress(doubleBuffer), i2);
    }

    public static void glClearTexSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglClearTexSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glClearTexSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglClearTexSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddressSafe(shortBuffer));
    }

    public static void glClearTexSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") IntBuffer intBuffer) {
        nglClearTexSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glClearTexSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglClearTexSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddressSafe(floatBuffer));
    }

    public static void glClearTexSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglClearTexSubImage(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddressSafe(doubleBuffer));
    }

    public static void glClearTexImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglClearTexImage(i, i2, i3, i4, MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glClearTexImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglClearTexImage(i, i2, i3, i4, MemoryUtil.memAddressSafe(shortBuffer));
    }

    public static void glClearTexImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") IntBuffer intBuffer) {
        nglClearTexImage(i, i2, i3, i4, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glClearTexImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglClearTexImage(i, i2, i3, i4, MemoryUtil.memAddressSafe(floatBuffer));
    }

    public static void glClearTexImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglClearTexImage(i, i2, i3, i4, MemoryUtil.memAddressSafe(doubleBuffer));
    }

    public static void glBindBuffersBase(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        nglBindBuffersBase(i, i2, Checks.remainingSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glBindBuffersRange(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLintptr const *") PointerBuffer pointerBuffer, @NativeType("GLsizeiptr const *") PointerBuffer pointerBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkSafe((CustomBuffer<?>) pointerBuffer, Checks.remainingSafe(intBuffer));
            Checks.checkSafe((CustomBuffer<?>) pointerBuffer2, Checks.remainingSafe(intBuffer));
        }
        nglBindBuffersRange(i, i2, Checks.remainingSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(pointerBuffer), MemoryUtil.memAddressSafe(pointerBuffer2));
    }

    public static void glBindTextures(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        nglBindTextures(i, Checks.remainingSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glBindSamplers(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        nglBindSamplers(i, Checks.remainingSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glBindImageTextures(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        nglBindImageTextures(i, Checks.remainingSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glBindVertexBuffers(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLintptr const *") PointerBuffer pointerBuffer, @NativeType("GLsizei const *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkSafe((CustomBuffer<?>) pointerBuffer, Checks.remainingSafe(intBuffer));
            Checks.checkSafe((Buffer) intBuffer2, Checks.remainingSafe(intBuffer));
        }
        nglBindVertexBuffers(i, Checks.remainingSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(pointerBuffer), MemoryUtil.memAddressSafe(intBuffer2));
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") short[] sArr, @NativeType("GLbitfield") int i2) {
        long j = GL.getICD().glBufferStorage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(sArr.length) << 1, sArr, i2, j);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") int[] iArr, @NativeType("GLbitfield") int i2) {
        long j = GL.getICD().glBufferStorage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(iArr.length) << 2, iArr, i2, j);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") float[] fArr, @NativeType("GLbitfield") int i2) {
        long j = GL.getICD().glBufferStorage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(fArr.length) << 2, fArr, i2, j);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") double[] dArr, @NativeType("GLbitfield") int i2) {
        long j = GL.getICD().glBufferStorage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(dArr.length) << 3, dArr, i2, j);
    }

    public static void glClearTexSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glClearTexSubImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, sArr, j);
    }

    public static void glClearTexSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glClearTexSubImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, iArr, j);
    }

    public static void glClearTexSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glClearTexSubImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, fArr, j);
    }

    public static void glClearTexSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") double[] dArr) {
        long j = GL.getICD().glClearTexSubImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, dArr, j);
    }

    public static void glClearTexImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glClearTexImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, sArr, j);
    }

    public static void glClearTexImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glClearTexImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, iArr, j);
    }

    public static void glClearTexImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glClearTexImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, fArr, j);
    }

    public static void glClearTexImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") double[] dArr) {
        long j = GL.getICD().glClearTexImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, dArr, j);
    }

    public static void glBindBuffersBase(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glBindBuffersBase;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, Checks.lengthSafe(iArr), iArr, j);
    }

    public static void glBindBuffersRange(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint const *") int[] iArr, @NativeType("GLintptr const *") PointerBuffer pointerBuffer, @NativeType("GLsizeiptr const *") PointerBuffer pointerBuffer2) {
        long j = GL.getICD().glBindBuffersRange;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe((CustomBuffer<?>) pointerBuffer, Checks.lengthSafe(iArr));
            Checks.checkSafe((CustomBuffer<?>) pointerBuffer2, Checks.lengthSafe(iArr));
        }
        JNI.callPPPV(i, i2, Checks.lengthSafe(iArr), iArr, MemoryUtil.memAddressSafe(pointerBuffer), MemoryUtil.memAddressSafe(pointerBuffer2), j);
    }

    public static void glBindTextures(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glBindTextures;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, Checks.lengthSafe(iArr), iArr, j);
    }

    public static void glBindSamplers(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glBindSamplers;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, Checks.lengthSafe(iArr), iArr, j);
    }

    public static void glBindImageTextures(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glBindImageTextures;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, Checks.lengthSafe(iArr), iArr, j);
    }

    public static void glBindVertexBuffers(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr, @NativeType("GLintptr const *") PointerBuffer pointerBuffer, @NativeType("GLsizei const *") int[] iArr2) {
        long j = GL.getICD().glBindVertexBuffers;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe((CustomBuffer<?>) pointerBuffer, Checks.lengthSafe(iArr));
            Checks.checkSafe(iArr2, Checks.lengthSafe(iArr));
        }
        JNI.callPPPV(i, Checks.lengthSafe(iArr), iArr, MemoryUtil.memAddressSafe(pointerBuffer), iArr2, j);
    }
}
