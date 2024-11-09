package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBBlendFuncExtended.class */
public class ARBBlendFuncExtended {
    public static final int GL_SRC1_COLOR = 35065;
    public static final int GL_ONE_MINUS_SRC1_COLOR = 35066;
    public static final int GL_ONE_MINUS_SRC1_ALPHA = 35067;
    public static final int GL_MAX_DUAL_SOURCE_DRAW_BUFFERS = 35068;

    static {
        GL.initialize();
    }

    protected ARBBlendFuncExtended() {
        throw new UnsupportedOperationException();
    }

    public static void nglBindFragDataLocationIndexed(int i, int i2, int i3, long j) {
        GL33C.nglBindFragDataLocationIndexed(i, i2, i3, j);
    }

    public static void glBindFragDataLocationIndexed(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        GL33C.glBindFragDataLocationIndexed(i, i2, i3, byteBuffer);
    }

    public static void glBindFragDataLocationIndexed(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLchar const *") CharSequence charSequence) {
        GL33C.glBindFragDataLocationIndexed(i, i2, i3, charSequence);
    }

    public static int nglGetFragDataIndex(int i, long j) {
        return GL33C.nglGetFragDataIndex(i, j);
    }

    @NativeType("GLint")
    public static int glGetFragDataIndex(@NativeType("GLuint") int i, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        return GL33C.glGetFragDataIndex(i, byteBuffer);
    }

    @NativeType("GLint")
    public static int glGetFragDataIndex(@NativeType("GLuint") int i, @NativeType("GLchar const *") CharSequence charSequence) {
        return GL33C.glGetFragDataIndex(i, charSequence);
    }
}
