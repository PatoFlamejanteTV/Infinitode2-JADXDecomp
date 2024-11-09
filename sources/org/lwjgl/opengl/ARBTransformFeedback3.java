package org.lwjgl.opengl;

import java.nio.IntBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBTransformFeedback3.class */
public class ARBTransformFeedback3 {
    public static final int GL_MAX_TRANSFORM_FEEDBACK_BUFFERS = 36464;
    public static final int GL_MAX_VERTEX_STREAMS = 36465;

    static {
        GL.initialize();
    }

    protected ARBTransformFeedback3() {
        throw new UnsupportedOperationException();
    }

    public static void glDrawTransformFeedbackStream(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3) {
        GL40C.glDrawTransformFeedbackStream(i, i2, i3);
    }

    public static void glBeginQueryIndexed(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3) {
        GL40C.glBeginQueryIndexed(i, i2, i3);
    }

    public static void glEndQueryIndexed(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL40C.glEndQueryIndexed(i, i2);
    }

    public static void nglGetQueryIndexediv(int i, int i2, int i3, long j) {
        GL40C.nglGetQueryIndexediv(i, i2, i3, j);
    }

    public static void glGetQueryIndexediv(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        GL40C.glGetQueryIndexediv(i, i2, i3, intBuffer);
    }

    @NativeType("void")
    public static int glGetQueryIndexedi(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3) {
        return GL40C.glGetQueryIndexedi(i, i2, i3);
    }

    public static void glGetQueryIndexediv(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        GL40C.glGetQueryIndexediv(i, i2, i3, iArr);
    }
}
