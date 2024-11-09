package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVConservativeRaster.class */
public class NVConservativeRaster {
    public static final int GL_CONSERVATIVE_RASTERIZATION_NV = 37702;
    public static final int GL_SUBPIXEL_PRECISION_BIAS_X_BITS_NV = 37703;
    public static final int GL_SUBPIXEL_PRECISION_BIAS_Y_BITS_NV = 37704;
    public static final int GL_MAX_SUBPIXEL_PRECISION_BIAS_BITS_NV = 37705;

    public static native void glSubpixelPrecisionBiasNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2);

    static {
        GL.initialize();
    }

    protected NVConservativeRaster() {
        throw new UnsupportedOperationException();
    }
}
