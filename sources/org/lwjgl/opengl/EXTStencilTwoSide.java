package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTStencilTwoSide.class */
public class EXTStencilTwoSide {
    public static final int GL_STENCIL_TEST_TWO_SIDE_EXT = 35088;
    public static final int GL_ACTIVE_STENCIL_FACE_EXT = 35089;

    public static native void glActiveStencilFaceEXT(@NativeType("GLenum") int i);

    static {
        GL.initialize();
    }

    protected EXTStencilTwoSide() {
        throw new UnsupportedOperationException();
    }
}
