package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVTransformFeedback.class */
public class NVTransformFeedback {
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_NV = 35982;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_START_NV = 35972;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_SIZE_NV = 35973;
    public static final int GL_TRANSFORM_FEEDBACK_RECORD_NV = 35974;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_BINDING_NV = 35983;
    public static final int GL_INTERLEAVED_ATTRIBS_NV = 35980;
    public static final int GL_SEPARATE_ATTRIBS_NV = 35981;
    public static final int GL_PRIMITIVES_GENERATED_NV = 35975;
    public static final int GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN_NV = 35976;
    public static final int GL_RASTERIZER_DISCARD_NV = 35977;
    public static final int GL_MAX_TRANSFORM_FEEDBACK_INTERLEAVED_COMPONENTS_NV = 35978;
    public static final int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_ATTRIBS_NV = 35979;
    public static final int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_COMPONENTS_NV = 35968;
    public static final int GL_TRANSFORM_FEEDBACK_ATTRIBS_NV = 35966;
    public static final int GL_ACTIVE_VARYINGS_NV = 35969;
    public static final int GL_ACTIVE_VARYING_MAX_LENGTH_NV = 35970;
    public static final int GL_TRANSFORM_FEEDBACK_VARYINGS_NV = 35971;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_MODE_NV = 35967;
    public static final int GL_BACK_PRIMARY_COLOR_NV = 35959;
    public static final int GL_BACK_SECONDARY_COLOR_NV = 35960;
    public static final int GL_TEXTURE_COORD_NV = 35961;
    public static final int GL_CLIP_DISTANCE_NV = 35962;
    public static final int GL_VERTEX_ID_NV = 35963;
    public static final int GL_PRIMITIVE_ID_NV = 35964;
    public static final int GL_GENERIC_ATTRIB_NV = 35965;
    public static final int GL_SECONDARY_COLOR_NV = 34093;
    public static final int GL_LAYER_NV = 36266;

    public static native void glBeginTransformFeedbackNV(@NativeType("GLenum") int i);

    public static native void glEndTransformFeedbackNV();

    public static native void nglTransformFeedbackAttribsNV(int i, long j, int i2);

    public static native void glBindBufferRangeNV(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2);

    public static native void glBindBufferOffsetNV(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLintptr") long j);

    public static native void glBindBufferBaseNV(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3);

    public static native void nglTransformFeedbackVaryingsNV(int i, int i2, long j, int i3);

    public static native void nglActiveVaryingNV(int i, long j);

    public static native int nglGetVaryingLocationNV(int i, long j);

    public static native void nglGetActiveVaryingNV(int i, int i2, int i3, long j, long j2, long j3, long j4);

    public static native void nglGetTransformFeedbackVaryingNV(int i, int i2, long j);

    public static native void nglTransformFeedbackStreamAttribsNV(int i, long j, int i2, long j2, int i3);

    static {
        GL.initialize();
    }

    protected NVTransformFeedback() {
        throw new UnsupportedOperationException();
    }

    public static void glTransformFeedbackAttribsNV(@NativeType("GLint const *") IntBuffer intBuffer, @NativeType("GLenum") int i) {
        nglTransformFeedbackAttribsNV(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), i);
    }

    public static void glTransformFeedbackVaryingsNV(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer, @NativeType("GLenum") int i2) {
        nglTransformFeedbackVaryingsNV(i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), i2);
    }

    public static void glActiveVaryingNV(@NativeType("GLuint") int i, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nglActiveVaryingNV(i, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glActiveVaryingNV(@NativeType("GLuint") int i, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            nglActiveVaryingNV(i, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("GLint")
    public static int glGetVaryingLocationNV(@NativeType("GLuint") int i, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nglGetVaryingLocationNV(i, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("GLint")
    public static int glGetVaryingLocationNV(@NativeType("GLuint") int i, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nglGetVaryingLocationNV(i, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetActiveVaryingNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLsizei *") IntBuffer intBuffer2, @NativeType("GLenum *") IntBuffer intBuffer3, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
        }
        nglGetActiveVaryingNV(i, i2, byteBuffer.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetTransformFeedbackVaryingNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetTransformFeedbackVaryingNV(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetTransformFeedbackVaryingNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetTransformFeedbackVaryingNV(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glTransformFeedbackStreamAttribsNV(@NativeType("GLint const *") IntBuffer intBuffer, @NativeType("GLint const *") IntBuffer intBuffer2, @NativeType("GLenum") int i) {
        nglTransformFeedbackStreamAttribsNV(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), intBuffer2.remaining(), MemoryUtil.memAddress(intBuffer2), i);
    }

    public static void glTransformFeedbackAttribsNV(@NativeType("GLint const *") int[] iArr, @NativeType("GLenum") int i) {
        long j = GL.getICD().glTransformFeedbackAttribsNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, i, j);
    }

    public static void glTransformFeedbackVaryingsNV(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr, @NativeType("GLenum") int i2) {
        long j = GL.getICD().glTransformFeedbackVaryingsNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length, iArr, i2, j);
    }

    public static void glGetActiveVaryingNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") int[] iArr, @NativeType("GLsizei *") int[] iArr2, @NativeType("GLenum *") int[] iArr3, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetActiveVaryingNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
        }
        JNI.callPPPPV(i, i2, byteBuffer.remaining(), iArr, iArr2, iArr3, MemoryUtil.memAddress(byteBuffer), j);
    }

    public static void glGetTransformFeedbackVaryingNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetTransformFeedbackVaryingNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glTransformFeedbackStreamAttribsNV(@NativeType("GLint const *") int[] iArr, @NativeType("GLint const *") int[] iArr2, @NativeType("GLenum") int i) {
        long j = GL.getICD().glTransformFeedbackStreamAttribsNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(iArr.length, iArr, iArr2.length, iArr2, i, j);
    }
}
