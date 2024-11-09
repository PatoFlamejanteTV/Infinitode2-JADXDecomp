package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVConditionalRender.class */
public class NVConditionalRender {
    public static final int GL_QUERY_WAIT_NV = 36371;
    public static final int GL_QUERY_NO_WAIT_NV = 36372;
    public static final int GL_QUERY_BY_REGION_WAIT_NV = 36373;
    public static final int GL_QUERY_BY_REGION_NO_WAIT_NV = 36374;

    public static native void glBeginConditionalRenderNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2);

    public static native void glEndConditionalRenderNV();

    static {
        GL.initialize();
    }

    protected NVConditionalRender() {
        throw new UnsupportedOperationException();
    }
}
