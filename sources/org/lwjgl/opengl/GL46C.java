package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL46C.class */
public class GL46C extends GL45C {
    public static final int GL_PARAMETER_BUFFER = 33006;
    public static final int GL_PARAMETER_BUFFER_BINDING = 33007;
    public static final int GL_VERTICES_SUBMITTED = 33518;
    public static final int GL_PRIMITIVES_SUBMITTED = 33519;
    public static final int GL_VERTEX_SHADER_INVOCATIONS = 33520;
    public static final int GL_TESS_CONTROL_SHADER_PATCHES = 33521;
    public static final int GL_TESS_EVALUATION_SHADER_INVOCATIONS = 33522;
    public static final int GL_GEOMETRY_SHADER_PRIMITIVES_EMITTED = 33523;
    public static final int GL_FRAGMENT_SHADER_INVOCATIONS = 33524;
    public static final int GL_COMPUTE_SHADER_INVOCATIONS = 33525;
    public static final int GL_CLIPPING_INPUT_PRIMITIVES = 33526;
    public static final int GL_CLIPPING_OUTPUT_PRIMITIVES = 33527;
    public static final int GL_POLYGON_OFFSET_CLAMP = 36379;
    public static final int GL_CONTEXT_FLAG_NO_ERROR_BIT = 8;
    public static final int GL_SHADER_BINARY_FORMAT_SPIR_V = 38225;
    public static final int GL_SPIR_V_BINARY = 38226;
    public static final int GL_SPIR_V_EXTENSIONS = 38227;
    public static final int GL_NUM_SPIR_V_EXTENSIONS = 38228;
    public static final int GL_TEXTURE_MAX_ANISOTROPY = 34046;
    public static final int GL_MAX_TEXTURE_MAX_ANISOTROPY = 34047;
    public static final int GL_TRANSFORM_FEEDBACK_OVERFLOW = 33516;
    public static final int GL_TRANSFORM_FEEDBACK_STREAM_OVERFLOW = 33517;

    public static native void nglMultiDrawArraysIndirectCount(int i, long j, long j2, int i2, int i3);

    public static native void nglMultiDrawElementsIndirectCount(int i, int i2, long j, long j2, int i3, int i4);

    public static native void glPolygonOffsetClamp(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3);

    public static native void nglSpecializeShader(int i, long j, int i2, long j2, long j3);

    static {
        GL.initialize();
    }

    protected GL46C() {
        throw new UnsupportedOperationException();
    }

    public static void glMultiDrawArraysIndirectCount(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, i2 * (i3 == 0 ? 16 : i3));
        }
        nglMultiDrawArraysIndirectCount(i, MemoryUtil.memAddress(byteBuffer), j, i2, i3);
    }

    public static void glMultiDrawArraysIndirectCount(@NativeType("GLenum") int i, @NativeType("void const *") long j, @NativeType("GLintptr") long j2, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3) {
        nglMultiDrawArraysIndirectCount(i, j, j2, i2, i3);
    }

    public static void glMultiDrawArraysIndirectCount(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, (i2 * (i3 == 0 ? 16 : i3)) >> 2);
        }
        nglMultiDrawArraysIndirectCount(i, MemoryUtil.memAddress(intBuffer), j, i2, i3);
    }

    public static void glMultiDrawElementsIndirectCount(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, i3 * (i4 == 0 ? 20 : i4));
        }
        nglMultiDrawElementsIndirectCount(i, i2, MemoryUtil.memAddress(byteBuffer), j, i3, i4);
    }

    public static void glMultiDrawElementsIndirectCount(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") long j, @NativeType("GLintptr") long j2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        nglMultiDrawElementsIndirectCount(i, i2, j, j2, i3, i4);
    }

    public static void glMultiDrawElementsIndirectCount(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, (i3 * (i4 == 0 ? 20 : i4)) >> 2);
        }
        nglMultiDrawElementsIndirectCount(i, i2, MemoryUtil.memAddress(intBuffer), j, i3, i4);
    }

    public static void glSpecializeShader(@NativeType("GLuint") int i, @NativeType("GLchar const *") ByteBuffer byteBuffer, @NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLuint const *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.checkSafe((Buffer) intBuffer2, Checks.remainingSafe(intBuffer));
        }
        nglSpecializeShader(i, MemoryUtil.memAddress(byteBuffer), Checks.remainingSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2));
    }

    public static void glSpecializeShader(@NativeType("GLuint") int i, @NativeType("GLchar const *") CharSequence charSequence, @NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLuint const *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer2, Checks.remainingSafe(intBuffer));
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            nglSpecializeShader(i, stackGet.getPointerAddress(), Checks.remainingSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glMultiDrawArraysIndirectCount(@NativeType("GLenum") int i, @NativeType("void const *") int[] iArr, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3) {
        long j2 = GL.getICD().glMultiDrawArraysIndirectCount;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(iArr, (i2 * (i3 == 0 ? 16 : i3)) >> 2);
        }
        JNI.callPPV(i, iArr, j, i2, i3, j2);
    }

    public static void glMultiDrawElementsIndirectCount(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") int[] iArr, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        long j2 = GL.getICD().glMultiDrawElementsIndirectCount;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(iArr, (i3 * (i4 == 0 ? 20 : i4)) >> 2);
        }
        JNI.callPPV(i, i2, iArr, j, i3, i4, j2);
    }

    public static void glSpecializeShader(@NativeType("GLuint") int i, @NativeType("GLchar const *") ByteBuffer byteBuffer, @NativeType("GLuint const *") int[] iArr, @NativeType("GLuint const *") int[] iArr2) {
        long j = GL.getICD().glSpecializeShader;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkNT1(byteBuffer);
            Checks.checkSafe(iArr2, Checks.lengthSafe(iArr));
        }
        JNI.callPPPV(i, MemoryUtil.memAddress(byteBuffer), Checks.lengthSafe(iArr), iArr, iArr2, j);
    }

    public static void glSpecializeShader(@NativeType("GLuint") int i, @NativeType("GLchar const *") CharSequence charSequence, @NativeType("GLuint const *") int[] iArr, @NativeType("GLuint const *") int[] iArr2) {
        long j = GL.getICD().glSpecializeShader;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr2, Checks.lengthSafe(iArr));
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            JNI.callPPPV(i, stackGet.getPointerAddress(), Checks.lengthSafe(iArr), iArr, iArr2, j);
        } finally {
            stackGet.setPointer(pointer);
        }
    }
}
