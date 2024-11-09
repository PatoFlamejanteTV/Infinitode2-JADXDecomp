package org.lwjgl.opengl;

import java.nio.IntBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBTransformFeedback2.class */
public class ARBTransformFeedback2 {
    public static final int GL_TRANSFORM_FEEDBACK = 36386;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_PAUSED = 36387;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_ACTIVE = 36388;
    public static final int GL_TRANSFORM_FEEDBACK_BINDING = 36389;

    static {
        GL.initialize();
    }

    protected ARBTransformFeedback2() {
        throw new UnsupportedOperationException();
    }

    public static void glBindTransformFeedback(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL40C.glBindTransformFeedback(i, i2);
    }

    public static void nglDeleteTransformFeedbacks(int i, long j) {
        GL40C.nglDeleteTransformFeedbacks(i, j);
    }

    public static void glDeleteTransformFeedbacks(@NativeType("GLuint const *") IntBuffer intBuffer) {
        GL40C.glDeleteTransformFeedbacks(intBuffer);
    }

    public static void glDeleteTransformFeedbacks(@NativeType("GLuint const *") int i) {
        GL40C.glDeleteTransformFeedbacks(i);
    }

    public static void nglGenTransformFeedbacks(int i, long j) {
        GL40C.nglGenTransformFeedbacks(i, j);
    }

    public static void glGenTransformFeedbacks(@NativeType("GLuint *") IntBuffer intBuffer) {
        GL40C.glGenTransformFeedbacks(intBuffer);
    }

    @NativeType("void")
    public static int glGenTransformFeedbacks() {
        return GL40C.glGenTransformFeedbacks();
    }

    @NativeType("GLboolean")
    public static boolean glIsTransformFeedback(@NativeType("GLuint") int i) {
        return GL40C.glIsTransformFeedback(i);
    }

    public static void glPauseTransformFeedback() {
        GL40C.glPauseTransformFeedback();
    }

    public static void glResumeTransformFeedback() {
        GL40C.glResumeTransformFeedback();
    }

    public static void glDrawTransformFeedback(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL40C.glDrawTransformFeedback(i, i2);
    }

    public static void glDeleteTransformFeedbacks(@NativeType("GLuint const *") int[] iArr) {
        GL40C.glDeleteTransformFeedbacks(iArr);
    }

    public static void glGenTransformFeedbacks(@NativeType("GLuint *") int[] iArr) {
        GL40C.glGenTransformFeedbacks(iArr);
    }
}
