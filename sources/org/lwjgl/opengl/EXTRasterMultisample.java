package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTRasterMultisample.class */
public class EXTRasterMultisample {
    public static final int GL_RASTER_MULTISAMPLE_EXT = 37671;
    public static final int GL_RASTER_SAMPLES_EXT = 37672;
    public static final int GL_MAX_RASTER_SAMPLES_EXT = 37673;
    public static final int GL_RASTER_FIXED_SAMPLE_LOCATIONS_EXT = 37674;
    public static final int GL_MULTISAMPLE_RASTERIZATION_ALLOWED_EXT = 37675;
    public static final int GL_EFFECTIVE_RASTER_SAMPLES_EXT = 37676;

    public static native void glRasterSamplesEXT(@NativeType("GLuint") int i, @NativeType("GLboolean") boolean z);

    static {
        GL.initialize();
    }

    protected EXTRasterMultisample() {
        throw new UnsupportedOperationException();
    }
}
