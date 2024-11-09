package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBShadingLanguageInclude.class */
public class ARBShadingLanguageInclude {
    public static final int GL_SHADER_INCLUDE_ARB = 36270;
    public static final int GL_NAMED_STRING_LENGTH_ARB = 36329;
    public static final int GL_NAMED_STRING_TYPE_ARB = 36330;

    public static native void nglNamedStringARB(int i, int i2, long j, int i3, long j2);

    public static native void nglDeleteNamedStringARB(int i, long j);

    public static native void nglCompileShaderIncludeARB(int i, int i2, long j, long j2);

    public static native boolean nglIsNamedStringARB(int i, long j);

    public static native void nglGetNamedStringARB(int i, long j, int i2, long j2, long j3);

    public static native void nglGetNamedStringivARB(int i, long j, int i2, long j2);

    static {
        GL.initialize();
    }

    protected ARBShadingLanguageInclude() {
        throw new UnsupportedOperationException();
    }

    public static void glNamedStringARB(@NativeType("GLenum") int i, @NativeType("GLchar const *") ByteBuffer byteBuffer, @NativeType("GLchar const *") ByteBuffer byteBuffer2) {
        nglNamedStringARB(i, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), byteBuffer2.remaining(), MemoryUtil.memAddress(byteBuffer2));
    }

    public static void glNamedStringARB(@NativeType("GLenum") int i, @NativeType("GLchar const *") CharSequence charSequence, @NativeType("GLchar const *") CharSequence charSequence2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglNamedStringARB(i, stackGet.nASCII(charSequence, false), stackGet.getPointerAddress(), stackGet.nUTF8(charSequence2, false), stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDeleteNamedStringARB(@NativeType("GLchar const *") ByteBuffer byteBuffer) {
        nglDeleteNamedStringARB(byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glDeleteNamedStringARB(@NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDeleteNamedStringARB(stackGet.nASCII(charSequence, false), stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glCompileShaderIncludeARB(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") PointerBuffer pointerBuffer, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, pointerBuffer.remaining());
        }
        nglCompileShaderIncludeARB(i, pointerBuffer.remaining(), MemoryUtil.memAddress(pointerBuffer), MemoryUtil.memAddressSafe(intBuffer));
    }

    @NativeType("GLboolean")
    public static boolean glIsNamedStringARB(@NativeType("GLchar const *") ByteBuffer byteBuffer) {
        return nglIsNamedStringARB(byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("GLboolean")
    public static boolean glIsNamedStringARB(@NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            return nglIsNamedStringARB(stackGet.nASCII(charSequence, false), stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetNamedStringARB(@NativeType("GLchar const *") ByteBuffer byteBuffer, @NativeType("GLint *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
        }
        nglGetNamedStringARB(byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), byteBuffer2.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(byteBuffer2));
    }

    public static void glGetNamedStringARB(@NativeType("GLchar const *") CharSequence charSequence, @NativeType("GLint *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglGetNamedStringARB(stackGet.nASCII(charSequence, false), stackGet.getPointerAddress(), byteBuffer.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(byteBuffer));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void")
    public static String glGetNamedStringARB(@NativeType("GLchar const *") CharSequence charSequence, @NativeType("GLsizei") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            int nASCII = stackGet.nASCII(charSequence, false);
            long pointerAddress = stackGet.getPointerAddress();
            IntBuffer ints = stackGet.ints(0);
            ByteBuffer malloc = stackGet.malloc(i);
            nglGetNamedStringARB(nASCII, pointerAddress, i, MemoryUtil.memAddress(ints), MemoryUtil.memAddress(malloc));
            return MemoryUtil.memUTF8(malloc, ints.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void")
    public static String glGetNamedStringARB(@NativeType("GLchar const *") CharSequence charSequence) {
        return glGetNamedStringARB(charSequence, glGetNamedStringiARB(charSequence, GL_NAMED_STRING_LENGTH_ARB));
    }

    public static void glGetNamedStringivARB(@NativeType("GLchar const *") ByteBuffer byteBuffer, @NativeType("GLenum") int i, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetNamedStringivARB(byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetNamedStringivARB(@NativeType("GLchar const *") CharSequence charSequence, @NativeType("GLenum") int i, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglGetNamedStringivARB(stackGet.nASCII(charSequence, false), stackGet.getPointerAddress(), i, MemoryUtil.memAddress(intBuffer));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void")
    public static int glGetNamedStringiARB(@NativeType("GLchar const *") CharSequence charSequence, @NativeType("GLenum") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            int nASCII = stackGet.nASCII(charSequence, false);
            long pointerAddress = stackGet.getPointerAddress();
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetNamedStringivARB(nASCII, pointerAddress, i, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glCompileShaderIncludeARB(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") PointerBuffer pointerBuffer, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glCompileShaderIncludeARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, pointerBuffer.remaining());
        }
        JNI.callPPV(i, pointerBuffer.remaining(), MemoryUtil.memAddress(pointerBuffer), iArr, j);
    }

    public static void glGetNamedStringARB(@NativeType("GLchar const *") ByteBuffer byteBuffer, @NativeType("GLint *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer2) {
        long j = GL.getICD().glGetNamedStringARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
        }
        JNI.callPPPV(byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), byteBuffer2.remaining(), iArr, MemoryUtil.memAddress(byteBuffer2), j);
    }

    public static void glGetNamedStringARB(@NativeType("GLchar const *") CharSequence charSequence, @NativeType("GLint *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetNamedStringARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            JNI.callPPPV(stackGet.nASCII(charSequence, false), stackGet.getPointerAddress(), byteBuffer.remaining(), iArr, MemoryUtil.memAddress(byteBuffer), j);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetNamedStringivARB(@NativeType("GLchar const *") ByteBuffer byteBuffer, @NativeType("GLenum") int i, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetNamedStringivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPPV(byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), i, iArr, j);
    }

    public static void glGetNamedStringivARB(@NativeType("GLchar const *") CharSequence charSequence, @NativeType("GLenum") int i, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetNamedStringivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            JNI.callPPV(stackGet.nASCII(charSequence, false), stackGet.getPointerAddress(), i, iArr, j);
        } finally {
            stackGet.setPointer(pointer);
        }
    }
}
