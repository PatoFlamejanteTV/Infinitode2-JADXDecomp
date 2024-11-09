package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTBlendMinmax.class */
public class EXTBlendMinmax {
    public static final int GL_FUNC_ADD_EXT = 32774;
    public static final int GL_MIN_EXT = 32775;
    public static final int GL_MAX_EXT = 32776;
    public static final int GL_BLEND_EQUATION_EXT = 32777;

    public static native void glBlendEquationEXT(@NativeType("GLenum") int i);

    static {
        GL.initialize();
    }

    protected EXTBlendMinmax() {
        throw new UnsupportedOperationException();
    }
}
