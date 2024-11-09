package com.badlogic.gdx.graphics.g3d.environment;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.GdxRuntimeException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/environment/SphericalHarmonics.class */
public class SphericalHarmonics {
    private static final float[] coeff = {0.282095f, 0.488603f, 0.488603f, 0.488603f, 1.092548f, 1.092548f, 1.092548f, 0.315392f, 0.546274f};
    public final float[] data;

    private static final float clamp(float f) {
        if (f < 0.0f) {
            return 0.0f;
        }
        if (f > 1.0f) {
            return 1.0f;
        }
        return f;
    }

    public SphericalHarmonics() {
        this.data = new float[27];
    }

    public SphericalHarmonics(float[] fArr) {
        if (fArr.length != 27) {
            throw new GdxRuntimeException("Incorrect array size");
        }
        this.data = (float[]) fArr.clone();
    }

    public SphericalHarmonics set(float[] fArr) {
        for (int i = 0; i < this.data.length; i++) {
            this.data[i] = fArr[i];
        }
        return this;
    }

    public SphericalHarmonics set(AmbientCubemap ambientCubemap) {
        return set(ambientCubemap.data);
    }

    public SphericalHarmonics set(Color color) {
        return set(color.r, color.g, color.f888b);
    }

    public SphericalHarmonics set(float f, float f2, float f3) {
        int i = 0;
        while (i < this.data.length) {
            int i2 = i;
            int i3 = i + 1;
            this.data[i2] = f;
            int i4 = i3 + 1;
            this.data[i3] = f2;
            i = i4 + 1;
            this.data[i4] = f3;
        }
        return this;
    }
}
