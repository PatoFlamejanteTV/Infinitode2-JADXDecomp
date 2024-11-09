package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVClipSpaceWScaling.class */
public class NVClipSpaceWScaling {
    public static final int GL_VIEWPORT_POSITION_W_SCALE_NV = 37756;
    public static final int GL_VIEWPORT_POSITION_W_SCALE_X_COEFF = 37757;
    public static final int GL_VIEWPORT_POSITION_W_SCALE_Y_COEFF = 37758;

    public static native void glViewportPositionWScaleNV(@NativeType("GLuint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2);

    static {
        GL.initialize();
    }

    protected NVClipSpaceWScaling() {
        throw new UnsupportedOperationException();
    }
}
