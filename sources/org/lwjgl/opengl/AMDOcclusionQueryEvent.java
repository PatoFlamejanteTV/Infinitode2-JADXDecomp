package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/AMDOcclusionQueryEvent.class */
public class AMDOcclusionQueryEvent {
    public static final int GL_OCCLUSION_QUERY_EVENT_MASK_AMD = 34639;
    public static final int GL_QUERY_DEPTH_PASS_EVENT_BIT_AMD = 1;
    public static final int GL_QUERY_DEPTH_FAIL_EVENT_BIT_AMD = 2;
    public static final int GL_QUERY_STENCIL_FAIL_EVENT_BIT_AMD = 4;
    public static final int GL_QUERY_DEPTH_BOUNDS_FAIL_EVENT_BIT_AMD = 8;
    public static final int GL_QUERY_ALL_EVENT_BITS_AMD = -1;

    public static native void glQueryObjectParameteruiAMD(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4);

    static {
        GL.initialize();
    }

    protected AMDOcclusionQueryEvent() {
        throw new UnsupportedOperationException();
    }
}
