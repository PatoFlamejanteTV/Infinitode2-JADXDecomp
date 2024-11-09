package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBTransformFeedbackInstanced.class */
public class ARBTransformFeedbackInstanced {
    static {
        GL.initialize();
    }

    protected ARBTransformFeedbackInstanced() {
        throw new UnsupportedOperationException();
    }

    public static void glDrawTransformFeedbackInstanced(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei") int i3) {
        GL42C.glDrawTransformFeedbackInstanced(i, i2, i3);
    }

    public static void glDrawTransformFeedbackStreamInstanced(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei") int i4) {
        GL42C.glDrawTransformFeedbackStreamInstanced(i, i2, i3, i4);
    }
}
