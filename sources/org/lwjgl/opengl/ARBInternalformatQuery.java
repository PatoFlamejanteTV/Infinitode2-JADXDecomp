package org.lwjgl.opengl;

import java.nio.IntBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBInternalformatQuery.class */
public class ARBInternalformatQuery {
    public static final int GL_NUM_SAMPLE_COUNTS = 37760;

    static {
        GL.initialize();
    }

    protected ARBInternalformatQuery() {
        throw new UnsupportedOperationException();
    }

    public static void nglGetInternalformativ(int i, int i2, int i3, int i4, long j) {
        GL42C.nglGetInternalformativ(i, i2, i3, i4, j);
    }

    public static void glGetInternalformativ(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        GL42C.glGetInternalformativ(i, i2, i3, intBuffer);
    }

    @NativeType("void")
    public static int glGetInternalformati(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        return GL42C.glGetInternalformati(i, i2, i3);
    }

    public static void glGetInternalformativ(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        GL42C.glGetInternalformativ(i, i2, i3, iArr);
    }
}
