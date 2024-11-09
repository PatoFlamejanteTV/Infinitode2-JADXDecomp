package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/AMDDebugOutput.class */
public class AMDDebugOutput {
    public static final int GL_MAX_DEBUG_MESSAGE_LENGTH_AMD = 37187;
    public static final int GL_MAX_DEBUG_LOGGED_MESSAGES_AMD = 37188;
    public static final int GL_DEBUG_LOGGED_MESSAGES_AMD = 37189;
    public static final int GL_DEBUG_SEVERITY_HIGH_AMD = 37190;
    public static final int GL_DEBUG_SEVERITY_MEDIUM_AMD = 37191;
    public static final int GL_DEBUG_SEVERITY_LOW_AMD = 37192;
    public static final int GL_DEBUG_CATEGORY_API_ERROR_AMD = 37193;
    public static final int GL_DEBUG_CATEGORY_WINDOW_SYSTEM_AMD = 37194;
    public static final int GL_DEBUG_CATEGORY_DEPRECATION_AMD = 37195;
    public static final int GL_DEBUG_CATEGORY_UNDEFINED_BEHAVIOR_AMD = 37196;
    public static final int GL_DEBUG_CATEGORY_PERFORMANCE_AMD = 37197;
    public static final int GL_DEBUG_CATEGORY_SHADER_COMPILER_AMD = 37198;
    public static final int GL_DEBUG_CATEGORY_APPLICATION_AMD = 37199;
    public static final int GL_DEBUG_CATEGORY_OTHER_AMD = 37200;

    public static native void nglDebugMessageEnableAMD(int i, int i2, int i3, long j, boolean z);

    public static native void nglDebugMessageInsertAMD(int i, int i2, int i3, int i4, long j);

    public static native void nglDebugMessageCallbackAMD(long j, long j2);

    public static native int nglGetDebugMessageLogAMD(int i, int i2, long j, long j2, long j3, long j4, long j5);

    static {
        GL.initialize();
    }

    protected AMDDebugOutput() {
        throw new UnsupportedOperationException();
    }

    public static void glDebugMessageEnableAMD(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLboolean") boolean z) {
        nglDebugMessageEnableAMD(i, i2, Checks.remainingSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer), z);
    }

    public static void glDebugMessageEnableAMD(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") int i3, @NativeType("GLboolean") boolean z) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDebugMessageEnableAMD(i, i2, 1, MemoryUtil.memAddress(stackGet.ints(i3)), z);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDebugMessageInsertAMD(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        nglDebugMessageInsertAMD(i, i2, i3, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glDebugMessageInsertAMD(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDebugMessageInsertAMD(i, i2, i3, stackGet.nUTF8(charSequence, false), stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDebugMessageCallbackAMD(@NativeType("GLDEBUGPROCAMD") GLDebugMessageAMDCallbackI gLDebugMessageAMDCallbackI, @NativeType("void *") long j) {
        nglDebugMessageCallbackAMD(MemoryUtil.memAddressSafe(gLDebugMessageAMDCallbackI), j);
    }

    @NativeType("GLuint")
    public static int glGetDebugMessageLogAMD(@NativeType("GLuint") int i, @NativeType("GLenum *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2, @NativeType("GLuint *") IntBuffer intBuffer3, @NativeType("GLsizei *") IntBuffer intBuffer4, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, i);
            Checks.checkSafe((Buffer) intBuffer2, i);
            Checks.checkSafe((Buffer) intBuffer3, i);
            Checks.checkSafe((Buffer) intBuffer4, i);
        }
        return nglGetDebugMessageLogAMD(i, Checks.remainingSafe(byteBuffer), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2), MemoryUtil.memAddressSafe(intBuffer3), MemoryUtil.memAddressSafe(intBuffer4), MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glDebugMessageEnableAMD(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") int[] iArr, @NativeType("GLboolean") boolean z) {
        long j = GL.getICD().glDebugMessageEnableAMD;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, Checks.lengthSafe(iArr), iArr, z, j);
    }

    @NativeType("GLuint")
    public static int glGetDebugMessageLogAMD(@NativeType("GLuint") int i, @NativeType("GLenum *") int[] iArr, @NativeType("GLenum *") int[] iArr2, @NativeType("GLuint *") int[] iArr3, @NativeType("GLsizei *") int[] iArr4, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetDebugMessageLogAMD;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, i);
            Checks.checkSafe(iArr2, i);
            Checks.checkSafe(iArr3, i);
            Checks.checkSafe(iArr4, i);
        }
        return JNI.callPPPPPI(i, Checks.remainingSafe(byteBuffer), iArr, iArr2, iArr3, iArr4, MemoryUtil.memAddressSafe(byteBuffer), j);
    }
}
