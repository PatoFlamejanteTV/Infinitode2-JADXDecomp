package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVAlphaToCoverageDitherControl.class */
public class NVAlphaToCoverageDitherControl {
    public static final int GL_ALPHA_TO_COVERAGE_DITHER_DEFAULT_NV = 37709;
    public static final int GL_ALPHA_TO_COVERAGE_DITHER_ENABLE_NV = 37710;
    public static final int GL_ALPHA_TO_COVERAGE_DITHER_DISABLE_NV = 37711;
    public static final int GL_ALPHA_TO_COVERAGE_DITHER_MODE_NV = 37567;

    public static native void glAlphaToCoverageDitherControlNV(@NativeType("GLenum") int i);

    static {
        GL.initialize();
    }

    protected NVAlphaToCoverageDitherControl() {
        throw new UnsupportedOperationException();
    }
}
