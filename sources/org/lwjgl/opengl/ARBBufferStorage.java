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

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBBufferStorage.class */
public class ARBBufferStorage {
    public static final int GL_MAP_PERSISTENT_BIT = 64;
    public static final int GL_MAP_COHERENT_BIT = 128;
    public static final int GL_DYNAMIC_STORAGE_BIT = 256;
    public static final int GL_CLIENT_STORAGE_BIT = 512;
    public static final int GL_BUFFER_IMMUTABLE_STORAGE = 33311;
    public static final int GL_BUFFER_STORAGE_FLAGS = 33312;
    public static final int GL_CLIENT_MAPPED_BUFFER_BARRIER_BIT = 16384;

    public static native void nglNamedBufferStorageEXT(int i, long j, long j2, int i2);

    static {
        GL.initialize();
    }

    protected ARBBufferStorage() {
        throw new UnsupportedOperationException();
    }

    public static void nglBufferStorage(int i, long j, long j2, int i2) {
        GL44C.nglBufferStorage(i, j, j2, i2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("GLsizeiptr") long j, @NativeType("GLbitfield") int i2) {
        GL44C.glBufferStorage(i, j, i2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLbitfield") int i2) {
        GL44C.glBufferStorage(i, byteBuffer, i2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLbitfield") int i2) {
        GL44C.glBufferStorage(i, shortBuffer, i2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLbitfield") int i2) {
        GL44C.glBufferStorage(i, intBuffer, i2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") FloatBuffer floatBuffer, @NativeType("GLbitfield") int i2) {
        GL44C.glBufferStorage(i, floatBuffer, i2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") DoubleBuffer doubleBuffer, @NativeType("GLbitfield") int i2) {
        GL44C.glBufferStorage(i, doubleBuffer, i2);
    }

    public static void glNamedBufferStorageEXT(@NativeType("GLuint") int i, @NativeType("GLsizeiptr") long j, @NativeType("GLbitfield") int i2) {
        nglNamedBufferStorageEXT(i, j, 0L, i2);
    }

    public static void glNamedBufferStorageEXT(@NativeType("GLuint") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLbitfield") int i2) {
        nglNamedBufferStorageEXT(i, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), i2);
    }

    public static void glNamedBufferStorageEXT(@NativeType("GLuint") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLbitfield") int i2) {
        nglNamedBufferStorageEXT(i, Integer.toUnsignedLong(shortBuffer.remaining()) << 1, MemoryUtil.memAddress(shortBuffer), i2);
    }

    public static void glNamedBufferStorageEXT(@NativeType("GLuint") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLbitfield") int i2) {
        nglNamedBufferStorageEXT(i, Integer.toUnsignedLong(intBuffer.remaining()) << 2, MemoryUtil.memAddress(intBuffer), i2);
    }

    public static void glNamedBufferStorageEXT(@NativeType("GLuint") int i, @NativeType("void const *") FloatBuffer floatBuffer, @NativeType("GLbitfield") int i2) {
        nglNamedBufferStorageEXT(i, Integer.toUnsignedLong(floatBuffer.remaining()) << 2, MemoryUtil.memAddress(floatBuffer), i2);
    }

    public static void glNamedBufferStorageEXT(@NativeType("GLuint") int i, @NativeType("void const *") DoubleBuffer doubleBuffer, @NativeType("GLbitfield") int i2) {
        nglNamedBufferStorageEXT(i, Integer.toUnsignedLong(doubleBuffer.remaining()) << 3, MemoryUtil.memAddress(doubleBuffer), i2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") short[] sArr, @NativeType("GLbitfield") int i2) {
        GL44C.glBufferStorage(i, sArr, i2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") int[] iArr, @NativeType("GLbitfield") int i2) {
        GL44C.glBufferStorage(i, iArr, i2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") float[] fArr, @NativeType("GLbitfield") int i2) {
        GL44C.glBufferStorage(i, fArr, i2);
    }

    public static void glBufferStorage(@NativeType("GLenum") int i, @NativeType("void const *") double[] dArr, @NativeType("GLbitfield") int i2) {
        GL44C.glBufferStorage(i, dArr, i2);
    }

    public static void glNamedBufferStorageEXT(@NativeType("GLuint") int i, @NativeType("void const *") short[] sArr, @NativeType("GLbitfield") int i2) {
        long j = GL.getICD().glNamedBufferStorageEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(sArr.length) << 1, sArr, i2, j);
    }

    public static void glNamedBufferStorageEXT(@NativeType("GLuint") int i, @NativeType("void const *") int[] iArr, @NativeType("GLbitfield") int i2) {
        long j = GL.getICD().glNamedBufferStorageEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(iArr.length) << 2, iArr, i2, j);
    }

    public static void glNamedBufferStorageEXT(@NativeType("GLuint") int i, @NativeType("void const *") float[] fArr, @NativeType("GLbitfield") int i2) {
        long j = GL.getICD().glNamedBufferStorageEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(fArr.length) << 2, fArr, i2, j);
    }

    public static void glNamedBufferStorageEXT(@NativeType("GLuint") int i, @NativeType("void const *") double[] dArr, @NativeType("GLbitfield") int i2) {
        long j = GL.getICD().glNamedBufferStorageEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(dArr.length) << 3, dArr, i2, j);
    }
}
