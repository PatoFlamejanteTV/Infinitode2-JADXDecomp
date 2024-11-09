package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBGLSPIRV.class */
public class ARBGLSPIRV {
    public static final int GL_SHADER_BINARY_FORMAT_SPIR_V_ARB = 38225;
    public static final int GL_SPIR_V_BINARY_ARB = 38226;

    public static native void nglSpecializeShaderARB(int i, long j, int i2, long j2, long j3);

    static {
        GL.initialize();
    }

    protected ARBGLSPIRV() {
        throw new UnsupportedOperationException();
    }

    public static void glSpecializeShaderARB(@NativeType("GLuint") int i, @NativeType("GLchar const *") ByteBuffer byteBuffer, @NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLuint const *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.check((Buffer) intBuffer2, intBuffer.remaining());
        }
        nglSpecializeShaderARB(i, MemoryUtil.memAddress(byteBuffer), intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2));
    }

    public static void glSpecializeShaderARB(@NativeType("GLuint") int i, @NativeType("GLchar const *") CharSequence charSequence, @NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLuint const *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer2, intBuffer.remaining());
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            nglSpecializeShaderARB(i, stackGet.getPointerAddress(), intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glSpecializeShaderARB(@NativeType("GLuint") int i, @NativeType("GLchar const *") ByteBuffer byteBuffer, @NativeType("GLuint const *") int[] iArr, @NativeType("GLuint const *") int[] iArr2) {
        long j = GL.getICD().glSpecializeShaderARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkNT1(byteBuffer);
            Checks.check(iArr2, iArr.length);
        }
        JNI.callPPPV(i, MemoryUtil.memAddress(byteBuffer), iArr.length, iArr, iArr2, j);
    }

    public static void glSpecializeShaderARB(@NativeType("GLuint") int i, @NativeType("GLchar const *") CharSequence charSequence, @NativeType("GLuint const *") int[] iArr, @NativeType("GLuint const *") int[] iArr2) {
        long j = GL.getICD().glSpecializeShaderARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr2, iArr.length);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            JNI.callPPPV(i, stackGet.getPointerAddress(), iArr.length, iArr, iArr2, j);
        } finally {
            stackGet.setPointer(pointer);
        }
    }
}
