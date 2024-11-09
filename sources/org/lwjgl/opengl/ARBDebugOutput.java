package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBDebugOutput.class */
public class ARBDebugOutput {
    public static final int GL_DEBUG_OUTPUT_SYNCHRONOUS_ARB = 33346;
    public static final int GL_MAX_DEBUG_MESSAGE_LENGTH_ARB = 37187;
    public static final int GL_MAX_DEBUG_LOGGED_MESSAGES_ARB = 37188;
    public static final int GL_DEBUG_LOGGED_MESSAGES_ARB = 37189;
    public static final int GL_DEBUG_NEXT_LOGGED_MESSAGE_LENGTH_ARB = 33347;
    public static final int GL_DEBUG_CALLBACK_FUNCTION_ARB = 33348;
    public static final int GL_DEBUG_CALLBACK_USER_PARAM_ARB = 33349;
    public static final int GL_DEBUG_SOURCE_API_ARB = 33350;
    public static final int GL_DEBUG_SOURCE_WINDOW_SYSTEM_ARB = 33351;
    public static final int GL_DEBUG_SOURCE_SHADER_COMPILER_ARB = 33352;
    public static final int GL_DEBUG_SOURCE_THIRD_PARTY_ARB = 33353;
    public static final int GL_DEBUG_SOURCE_APPLICATION_ARB = 33354;
    public static final int GL_DEBUG_SOURCE_OTHER_ARB = 33355;
    public static final int GL_DEBUG_TYPE_ERROR_ARB = 33356;
    public static final int GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR_ARB = 33357;
    public static final int GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR_ARB = 33358;
    public static final int GL_DEBUG_TYPE_PORTABILITY_ARB = 33359;
    public static final int GL_DEBUG_TYPE_PERFORMANCE_ARB = 33360;
    public static final int GL_DEBUG_TYPE_OTHER_ARB = 33361;
    public static final int GL_DEBUG_SEVERITY_HIGH_ARB = 37190;
    public static final int GL_DEBUG_SEVERITY_MEDIUM_ARB = 37191;
    public static final int GL_DEBUG_SEVERITY_LOW_ARB = 37192;

    public static native void nglDebugMessageControlARB(int i, int i2, int i3, int i4, long j, boolean z);

    public static native void nglDebugMessageInsertARB(int i, int i2, int i3, int i4, int i5, long j);

    public static native void nglDebugMessageCallbackARB(long j, long j2);

    public static native int nglGetDebugMessageLogARB(int i, int i2, long j, long j2, long j3, long j4, long j5, long j6);

    static {
        GL.initialize();
    }

    protected ARBDebugOutput() {
        throw new UnsupportedOperationException();
    }

    public static void glDebugMessageControlARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLboolean") boolean z) {
        nglDebugMessageControlARB(i, i2, i3, Checks.remainingSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer), z);
    }

    public static void glDebugMessageControlARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint const *") int i4, @NativeType("GLboolean") boolean z) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDebugMessageControlARB(i, i2, i3, 1, MemoryUtil.memAddress(stackGet.ints(i4)), z);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDebugMessageInsertARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        nglDebugMessageInsertARB(i, i2, i3, i4, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glDebugMessageInsertARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDebugMessageInsertARB(i, i2, i3, i4, stackGet.nUTF8(charSequence, false), stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDebugMessageCallbackARB(@NativeType("GLDEBUGPROCARB") GLDebugMessageARBCallbackI gLDebugMessageARBCallbackI, @NativeType("void const *") long j) {
        nglDebugMessageCallbackARB(MemoryUtil.memAddressSafe(gLDebugMessageARBCallbackI), j);
    }

    @NativeType("GLuint")
    public static int glGetDebugMessageLogARB(@NativeType("GLuint") int i, @NativeType("GLenum *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2, @NativeType("GLuint *") IntBuffer intBuffer3, @NativeType("GLenum *") IntBuffer intBuffer4, @NativeType("GLsizei *") IntBuffer intBuffer5, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, i);
            Checks.checkSafe((Buffer) intBuffer2, i);
            Checks.checkSafe((Buffer) intBuffer3, i);
            Checks.checkSafe((Buffer) intBuffer4, i);
            Checks.checkSafe((Buffer) intBuffer5, i);
        }
        return nglGetDebugMessageLogARB(i, Checks.remainingSafe(byteBuffer), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2), MemoryUtil.memAddressSafe(intBuffer3), MemoryUtil.memAddressSafe(intBuffer4), MemoryUtil.memAddressSafe(intBuffer5), MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glDebugMessageControlARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint const *") int[] iArr, @NativeType("GLboolean") boolean z) {
        long j = GL.getICD().glDebugMessageControlARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, Checks.lengthSafe(iArr), iArr, z, j);
    }

    @NativeType("GLuint")
    public static int glGetDebugMessageLogARB(@NativeType("GLuint") int i, @NativeType("GLenum *") int[] iArr, @NativeType("GLenum *") int[] iArr2, @NativeType("GLuint *") int[] iArr3, @NativeType("GLenum *") int[] iArr4, @NativeType("GLsizei *") int[] iArr5, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetDebugMessageLogARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, i);
            Checks.checkSafe(iArr2, i);
            Checks.checkSafe(iArr3, i);
            Checks.checkSafe(iArr4, i);
            Checks.checkSafe(iArr5, i);
        }
        return JNI.callPPPPPPI(i, Checks.remainingSafe(byteBuffer), iArr, iArr2, iArr3, iArr4, iArr5, MemoryUtil.memAddressSafe(byteBuffer), j);
    }
}
