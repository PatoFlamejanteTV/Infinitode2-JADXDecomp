package org.lwjgl.stb;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBPerlin.class */
public class STBPerlin {
    public static native float stb_perlin_noise3(float f, float f2, float f3, int i, int i2, int i3);

    public static native float stb_perlin_noise3_seed(float f, float f2, float f3, int i, int i2, int i3, int i4);

    public static native float stb_perlin_ridge_noise3(float f, float f2, float f3, float f4, float f5, float f6, int i);

    public static native float stb_perlin_fbm_noise3(float f, float f2, float f3, float f4, float f5, int i);

    public static native float stb_perlin_turbulence_noise3(float f, float f2, float f3, float f4, float f5, int i);

    public static native float stb_perlin_noise3_wrap_nonpow2(float f, float f2, float f3, int i, int i2, int i3, @NativeType("unsigned char") byte b2);

    static {
        LibSTB.initialize();
    }

    protected STBPerlin() {
        throw new UnsupportedOperationException();
    }
}
