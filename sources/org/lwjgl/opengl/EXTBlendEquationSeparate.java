package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTBlendEquationSeparate.class */
public class EXTBlendEquationSeparate {
    public static final int GL_BLEND_EQUATION_RGB_EXT = 32777;
    public static final int GL_BLEND_EQUATION_ALPHA_EXT = 34877;

    public static native void glBlendEquationSeparateEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2);

    static {
        GL.initialize();
    }

    protected EXTBlendEquationSeparate() {
        throw new UnsupportedOperationException();
    }
}
