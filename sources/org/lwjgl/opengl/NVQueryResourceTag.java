package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVQueryResourceTag.class */
public class NVQueryResourceTag {
    public static native void nglGenQueryResourceTagNV(int i, long j);

    public static native void nglDeleteQueryResourceTagNV(int i, long j);

    public static native void nglQueryResourceTagNV(int i, long j);

    static {
        GL.initialize();
    }

    protected NVQueryResourceTag() {
        throw new UnsupportedOperationException();
    }

    public static void glGenQueryResourceTagNV(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglGenQueryResourceTagNV(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGenQueryResourceTagNV() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGenQueryResourceTagNV(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDeleteQueryResourceTagNV(@NativeType("GLuint const *") IntBuffer intBuffer) {
        nglDeleteQueryResourceTagNV(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glDeleteQueryResourceTagNV(@NativeType("GLuint const *") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDeleteQueryResourceTagNV(1, MemoryUtil.memAddress(stackGet.ints(i)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glQueryResourceTagNV(@NativeType("GLuint") int i, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nglQueryResourceTagNV(i, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glQueryResourceTagNV(@NativeType("GLuint") int i, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            nglQueryResourceTagNV(i, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGenQueryResourceTagNV(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGenQueryResourceTagNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glDeleteQueryResourceTagNV(@NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glDeleteQueryResourceTagNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }
}
