package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTMemoryObject.class */
public class EXTMemoryObject {
    public static final int GL_TEXTURE_TILING_EXT = 38272;
    public static final int GL_DEDICATED_MEMORY_OBJECT_EXT = 38273;
    public static final int GL_NUM_TILING_TYPES_EXT = 38274;
    public static final int GL_TILING_TYPES_EXT = 38275;
    public static final int GL_OPTIMAL_TILING_EXT = 38276;
    public static final int GL_LINEAR_TILING_EXT = 38277;
    public static final int GL_NUM_DEVICE_UUIDS_EXT = 38294;
    public static final int GL_DEVICE_UUID_EXT = 38295;
    public static final int GL_DRIVER_UUID_EXT = 38296;
    public static final int GL_UUID_SIZE_EXT = 16;

    public static native void nglGetUnsignedBytevEXT(int i, long j);

    public static native void nglGetUnsignedBytei_vEXT(int i, int i2, long j);

    public static native void nglDeleteMemoryObjectsEXT(int i, long j);

    @NativeType("GLboolean")
    public static native boolean glIsMemoryObjectEXT(@NativeType("GLuint") int i);

    public static native void nglCreateMemoryObjectsEXT(int i, long j);

    public static native void nglMemoryObjectParameterivEXT(int i, int i2, long j);

    public static native void nglGetMemoryObjectParameterivEXT(int i, int i2, long j);

    public static native void glTexStorageMem2DEXT(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLuint") int i6, @NativeType("GLuint64") long j);

    public static native void glTexStorageMem2DMultisampleEXT(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLboolean") boolean z, @NativeType("GLuint") int i6, @NativeType("GLuint64") long j);

    public static native void glTexStorageMem3DEXT(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLuint") int i7, @NativeType("GLuint64") long j);

    public static native void glTexStorageMem3DMultisampleEXT(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLboolean") boolean z, @NativeType("GLuint") int i7, @NativeType("GLuint64") long j);

    public static native void glBufferStorageMemEXT(@NativeType("GLenum") int i, @NativeType("GLsizeiptr") long j, @NativeType("GLuint") int i2, @NativeType("GLuint64") long j2);

    public static native void glTextureStorageMem2DEXT(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLuint") int i6, @NativeType("GLuint64") long j);

    public static native void glTextureStorageMem2DMultisampleEXT(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLboolean") boolean z, @NativeType("GLuint") int i6, @NativeType("GLuint64") long j);

    public static native void glTextureStorageMem3DEXT(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLuint") int i7, @NativeType("GLuint64") long j);

    public static native void glTextureStorageMem3DMultisampleEXT(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLboolean") boolean z, @NativeType("GLuint") int i7, @NativeType("GLuint64") long j);

    public static native void glNamedBufferStorageMemEXT(@NativeType("GLuint") int i, @NativeType("GLsizeiptr") long j, @NativeType("GLuint") int i2, @NativeType("GLuint64") long j2);

    public static native void glTexStorageMem1DEXT(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLuint") int i5, @NativeType("GLuint64") long j);

    public static native void glTextureStorageMem1DEXT(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLuint") int i5, @NativeType("GLuint64") long j);

    static {
        GL.initialize();
    }

    protected EXTMemoryObject() {
        throw new UnsupportedOperationException();
    }

    public static void glGetUnsignedBytevEXT(@NativeType("GLenum") int i, @NativeType("GLubyte *") ByteBuffer byteBuffer) {
        nglGetUnsignedBytevEXT(i, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetUnsignedBytei_vEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLubyte *") ByteBuffer byteBuffer) {
        nglGetUnsignedBytei_vEXT(i, i2, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glDeleteMemoryObjectsEXT(@NativeType("GLuint const *") IntBuffer intBuffer) {
        nglDeleteMemoryObjectsEXT(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glDeleteMemoryObjectsEXT(@NativeType("GLuint const *") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDeleteMemoryObjectsEXT(1, MemoryUtil.memAddress(stackGet.ints(i)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glCreateMemoryObjectsEXT(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglCreateMemoryObjectsEXT(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glCreateMemoryObjectsEXT() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglCreateMemoryObjectsEXT(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glMemoryObjectParameterivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglMemoryObjectParameterivEXT(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glMemoryObjectParameteriEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglMemoryObjectParameterivEXT(i, i2, MemoryUtil.memAddress(stackGet.ints(i3)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetMemoryObjectParameterivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetMemoryObjectParameterivEXT(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetMemoryObjectParameteriEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetMemoryObjectParameterivEXT(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDeleteMemoryObjectsEXT(@NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glDeleteMemoryObjectsEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glCreateMemoryObjectsEXT(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glCreateMemoryObjectsEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glMemoryObjectParameterivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glMemoryObjectParameterivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetMemoryObjectParameterivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetMemoryObjectParameterivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }
}
