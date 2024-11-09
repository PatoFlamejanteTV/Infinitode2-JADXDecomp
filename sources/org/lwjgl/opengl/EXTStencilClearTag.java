package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTStencilClearTag.class */
public class EXTStencilClearTag {
    public static final int GL_STENCIL_TAG_BITS_EXT = 35058;
    public static final int GL_STENCIL_CLEAR_TAG_VALUE_EXT = 35059;

    public static native void glStencilClearTagEXT(@NativeType("GLsizei") int i, @NativeType("GLuint") int i2);

    static {
        GL.initialize();
    }

    protected EXTStencilClearTag() {
        throw new UnsupportedOperationException();
    }
}
