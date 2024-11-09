package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTDrawBuffers2.class */
public class EXTDrawBuffers2 {
    public static native void glColorMaskIndexedEXT(@NativeType("GLuint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLboolean") boolean z2, @NativeType("GLboolean") boolean z3, @NativeType("GLboolean") boolean z4);

    public static native void nglGetBooleanIndexedvEXT(int i, int i2, long j);

    public static native void nglGetIntegerIndexedvEXT(int i, int i2, long j);

    public static native void glEnableIndexedEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void glDisableIndexedEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    @NativeType("GLboolean")
    public static native boolean glIsEnabledIndexedEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    static {
        GL.initialize();
    }

    protected EXTDrawBuffers2() {
        throw new UnsupportedOperationException();
    }

    public static void glGetBooleanIndexedvEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLboolean *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 1);
        }
        nglGetBooleanIndexedvEXT(i, i2, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void")
    public static boolean glGetBooleanIndexedEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            ByteBuffer calloc = stackGet.calloc(1);
            nglGetBooleanIndexedvEXT(i, i2, MemoryUtil.memAddress(calloc));
            return calloc.get(0) != 0;
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetIntegerIndexedvEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetIntegerIndexedvEXT(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetIntegerIndexedEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetIntegerIndexedvEXT(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetIntegerIndexedvEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetIntegerIndexedvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }
}
