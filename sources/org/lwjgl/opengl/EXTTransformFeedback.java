package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTTransformFeedback.class */
public class EXTTransformFeedback {
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_EXT = 35982;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_START_EXT = 35972;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_SIZE_EXT = 35973;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_BINDING_EXT = 35983;
    public static final int GL_INTERLEAVED_ATTRIBS_EXT = 35980;
    public static final int GL_SEPARATE_ATTRIBS_EXT = 35981;
    public static final int GL_PRIMITIVES_GENERATED_EXT = 35975;
    public static final int GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN_EXT = 35976;
    public static final int GL_RASTERIZER_DISCARD_EXT = 35977;
    public static final int GL_MAX_TRANSFORM_FEEDBACK_INTERLEAVED_COMPONENTS_EXT = 35978;
    public static final int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_ATTRIBS_EXT = 35979;
    public static final int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_COMPONENTS_EXT = 35968;
    public static final int GL_TRANSFORM_FEEDBACK_VARYINGS_EXT = 35971;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_MODE_EXT = 35967;
    public static final int GL_TRANSFORM_FEEDBACK_VARYING_MAX_LENGTH_EXT = 35958;

    public static native void glBindBufferRangeEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2);

    public static native void glBindBufferOffsetEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLintptr") long j);

    public static native void glBindBufferBaseEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3);

    public static native void glBeginTransformFeedbackEXT(@NativeType("GLenum") int i);

    public static native void glEndTransformFeedbackEXT();

    public static native void nglTransformFeedbackVaryingsEXT(int i, int i2, long j, int i3);

    public static native void nglGetTransformFeedbackVaryingEXT(int i, int i2, int i3, long j, long j2, long j3, long j4);

    static {
        GL.initialize();
    }

    protected EXTTransformFeedback() {
        throw new UnsupportedOperationException();
    }

    public static void glTransformFeedbackVaryingsEXT(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") PointerBuffer pointerBuffer, @NativeType("GLenum") int i2) {
        nglTransformFeedbackVaryingsEXT(i, pointerBuffer.remaining(), MemoryUtil.memAddress(pointerBuffer), i2);
    }

    public static void glTransformFeedbackVaryingsEXT(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") CharSequence[] charSequenceArr, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            long apiArray = APIUtil.apiArray(stackGet, MemoryUtil::memASCII, charSequenceArr);
            nglTransformFeedbackVaryingsEXT(i, charSequenceArr.length, apiArray, i2);
            APIUtil.apiArrayFree(apiArray, charSequenceArr.length);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glTransformFeedbackVaryingsEXT(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") CharSequence charSequence, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            long apiArray = APIUtil.apiArray(stackGet, MemoryUtil::memASCII, charSequence);
            nglTransformFeedbackVaryingsEXT(i, 1, apiArray, i2);
            APIUtil.apiArrayFree(apiArray, 1);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTransformFeedbackVaryingEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLsizei *") IntBuffer intBuffer2, @NativeType("GLenum *") IntBuffer intBuffer3, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
        }
        nglGetTransformFeedbackVaryingEXT(i, i2, byteBuffer.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void")
    public static String glGetTransformFeedbackVaryingEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer ints = stackGet.ints(0);
            ByteBuffer malloc = stackGet.malloc(i3);
            nglGetTransformFeedbackVaryingEXT(i, i2, i3, MemoryUtil.memAddress(ints), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(malloc));
            return MemoryUtil.memASCII(malloc, ints.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void")
    public static String glGetTransformFeedbackVaryingEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2) {
        int glGetObjectParameteriARB;
        if (GL.getCapabilities().OpenGL20) {
            glGetObjectParameteriARB = GL20.glGetProgrami(i, 35958);
        } else {
            glGetObjectParameteriARB = ARBShaderObjects.glGetObjectParameteriARB(i, 35958);
        }
        return glGetTransformFeedbackVaryingEXT(i, i2, glGetObjectParameteriARB, intBuffer, intBuffer2);
    }

    public static void nglGetIntegerIndexedvEXT(int i, int i2, long j) {
        EXTDrawBuffers2.nglGetIntegerIndexedvEXT(i, i2, j);
    }

    public static void glGetIntegerIndexedvEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        EXTDrawBuffers2.glGetIntegerIndexedvEXT(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetIntegerIndexedEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        return EXTDrawBuffers2.glGetIntegerIndexedEXT(i, i2);
    }

    public static void nglGetBooleanIndexedvEXT(int i, int i2, long j) {
        EXTDrawBuffers2.nglGetBooleanIndexedvEXT(i, i2, j);
    }

    public static void glGetBooleanIndexedvEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLboolean *") ByteBuffer byteBuffer) {
        EXTDrawBuffers2.glGetBooleanIndexedvEXT(i, i2, byteBuffer);
    }

    @NativeType("void")
    public static boolean glGetBooleanIndexedEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        return EXTDrawBuffers2.glGetBooleanIndexedEXT(i, i2);
    }

    public static void glGetTransformFeedbackVaryingEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") int[] iArr, @NativeType("GLsizei *") int[] iArr2, @NativeType("GLenum *") int[] iArr3, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetTransformFeedbackVaryingEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
        }
        JNI.callPPPPV(i, i2, byteBuffer.remaining(), iArr, iArr2, iArr3, MemoryUtil.memAddress(byteBuffer), j);
    }

    public static void glGetIntegerIndexedvEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLint *") int[] iArr) {
        EXTDrawBuffers2.glGetIntegerIndexedvEXT(i, i2, iArr);
    }
}
