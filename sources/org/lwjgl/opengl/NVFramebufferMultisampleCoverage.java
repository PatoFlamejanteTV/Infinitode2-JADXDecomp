package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVFramebufferMultisampleCoverage.class */
public class NVFramebufferMultisampleCoverage {
    public static final int GL_RENDERBUFFER_COVERAGE_SAMPLES_NV = 36011;
    public static final int GL_RENDERBUFFER_COLOR_SAMPLES_NV = 36368;
    public static final int GL_MAX_MULTISAMPLE_COVERAGE_MODES_NV = 36369;
    public static final int GL_MULTISAMPLE_COVERAGE_MODES_NV = 36370;

    public static native void glRenderbufferStorageMultisampleCoverageNV(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6);

    static {
        GL.initialize();
    }

    protected NVFramebufferMultisampleCoverage() {
        throw new UnsupportedOperationException();
    }
}
