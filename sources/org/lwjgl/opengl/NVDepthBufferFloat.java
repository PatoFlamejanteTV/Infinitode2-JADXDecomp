package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVDepthBufferFloat.class */
public class NVDepthBufferFloat {
    public static final int GL_DEPTH_COMPONENT32F_NV = 36267;
    public static final int GL_DEPTH32F_STENCIL8_NV = 36268;
    public static final int GL_FLOAT_32_UNSIGNED_INT_24_8_REV_NV = 36269;
    public static final int GL_DEPTH_BUFFER_FLOAT_MODE_NV = 36271;

    public static native void glDepthRangedNV(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2);

    public static native void glClearDepthdNV(@NativeType("GLdouble") double d);

    public static native void glDepthBoundsdNV(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2);

    static {
        GL.initialize();
    }

    protected NVDepthBufferFloat() {
        throw new UnsupportedOperationException();
    }
}
