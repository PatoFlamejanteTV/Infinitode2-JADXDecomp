package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVConservativeRasterDilate.class */
public class NVConservativeRasterDilate {
    public static final int GL_CONSERVATIVE_RASTER_DILATE_NV = 37753;
    public static final int GL_CONSERVATIVE_RASTER_DILATE_RANGE_NV = 37754;
    public static final int GL_CONSERVATIVE_RASTER_DILATE_GRANULARITY_NV = 37755;

    public static native void glConservativeRasterParameterfNV(@NativeType("GLenum") int i, @NativeType("GLfloat") float f);

    static {
        GL.initialize();
    }

    protected NVConservativeRasterDilate() {
        throw new UnsupportedOperationException();
    }
}
