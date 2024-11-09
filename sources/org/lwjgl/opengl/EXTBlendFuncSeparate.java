package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTBlendFuncSeparate.class */
public class EXTBlendFuncSeparate {
    public static final int GL_BLEND_DST_RGB_EXT = 32968;
    public static final int GL_BLEND_SRC_RGB_EXT = 32969;
    public static final int GL_BLEND_DST_ALPHA_EXT = 32970;
    public static final int GL_BLEND_SRC_ALPHA_EXT = 32971;

    public static native void glBlendFuncSeparateEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4);

    static {
        GL.initialize();
    }

    protected EXTBlendFuncSeparate() {
        throw new UnsupportedOperationException();
    }
}
