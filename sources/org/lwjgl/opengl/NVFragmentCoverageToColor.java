package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVFragmentCoverageToColor.class */
public class NVFragmentCoverageToColor {
    public static final int GL_FRAGMENT_COVERAGE_TO_COLOR_NV = 37597;
    public static final int GL_FRAGMENT_COVERAGE_COLOR_NV = 37598;

    public static native void glFragmentCoverageColorNV(@NativeType("GLuint") int i);

    static {
        GL.initialize();
    }

    protected NVFragmentCoverageToColor() {
        throw new UnsupportedOperationException();
    }
}
