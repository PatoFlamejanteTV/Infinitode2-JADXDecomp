package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVTransformFeedback2.class */
public class NVTransformFeedback2 {
    public static final int GL_TRANSFORM_FEEDBACK_NV = 36386;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_PAUSED_NV = 36387;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_ACTIVE_NV = 36388;
    public static final int GL_TRANSFORM_FEEDBACK_BINDING_NV = 36389;

    public static native void glBindTransformFeedbackNV(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void nglDeleteTransformFeedbacksNV(int i, long j);

    public static native void nglGenTransformFeedbacksNV(int i, long j);

    @NativeType("GLboolean")
    public static native boolean glIsTransformFeedbackNV(@NativeType("GLuint") int i);

    public static native void glPauseTransformFeedbackNV();

    public static native void glResumeTransformFeedbackNV();

    public static native void glDrawTransformFeedbackNV(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    static {
        GL.initialize();
    }

    protected NVTransformFeedback2() {
        throw new UnsupportedOperationException();
    }

    public static void glDeleteTransformFeedbacksNV(@NativeType("GLuint const *") IntBuffer intBuffer) {
        nglDeleteTransformFeedbacksNV(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glDeleteTransformFeedbacksNV(@NativeType("GLuint const *") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDeleteTransformFeedbacksNV(1, MemoryUtil.memAddress(stackGet.ints(i)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGenTransformFeedbacksNV(@NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGenTransformFeedbacksNV(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGenTransformFeedbacksNV() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGenTransformFeedbacksNV(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDeleteTransformFeedbacksNV(@NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glDeleteTransformFeedbacksNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glGenTransformFeedbacksNV(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGenTransformFeedbacksNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(iArr.length, iArr, j);
    }
}
