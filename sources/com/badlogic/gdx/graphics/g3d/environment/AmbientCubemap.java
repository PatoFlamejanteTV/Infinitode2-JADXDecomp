package com.badlogic.gdx.graphics.g3d.environment;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/environment/AmbientCubemap.class */
public class AmbientCubemap {
    private static final int NUM_VALUES = 18;
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

    public AmbientCubemap() {
        this.data = new float[18];
    }

    public AmbientCubemap(float[] fArr) {
        if (fArr.length != 18) {
            throw new GdxRuntimeException("Incorrect array size");
        }
        this.data = new float[fArr.length];
        System.arraycopy(fArr, 0, this.data, 0, this.data.length);
    }

    public AmbientCubemap(AmbientCubemap ambientCubemap) {
        this(ambientCubemap.data);
    }

    public AmbientCubemap set(float[] fArr) {
        for (int i = 0; i < this.data.length; i++) {
            this.data[i] = fArr[i];
        }
        return this;
    }

    public AmbientCubemap set(AmbientCubemap ambientCubemap) {
        return set(ambientCubemap.data);
    }

    public AmbientCubemap set(Color color) {
        return set(color.r, color.g, color.f888b);
    }

    public AmbientCubemap set(float f, float f2, float f3) {
        for (int i = 0; i < 18; i += 3) {
            this.data[i] = f;
            this.data[i + 1] = f2;
            this.data[i + 2] = f3;
        }
        return this;
    }

    public Color getColor(Color color, int i) {
        int i2 = i * 3;
        return color.set(this.data[i2], this.data[i2 + 1], this.data[i2 + 2], 1.0f);
    }

    public AmbientCubemap clear() {
        for (int i = 0; i < this.data.length; i++) {
            this.data[i] = 0.0f;
        }
        return this;
    }

    public AmbientCubemap clamp() {
        for (int i = 0; i < this.data.length; i++) {
            float[] fArr = this.data;
            fArr[i] = clamp(fArr[i]);
        }
        return this;
    }

    public AmbientCubemap add(float f, float f2, float f3) {
        int i = 0;
        while (i < this.data.length) {
            float[] fArr = this.data;
            int i2 = i;
            int i3 = i + 1;
            fArr[i2] = fArr[i2] + f;
            float[] fArr2 = this.data;
            int i4 = i3 + 1;
            fArr2[i3] = fArr2[i3] + f2;
            float[] fArr3 = this.data;
            i = i4 + 1;
            fArr3[i4] = fArr3[i4] + f3;
        }
        return this;
    }

    public AmbientCubemap add(Color color) {
        return add(color.r, color.g, color.f888b);
    }

    public AmbientCubemap add(float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = f4 * f4;
        float f8 = f5 * f5;
        float f9 = f6 * f6;
        float f10 = f7 + f8 + f9;
        if (f10 == 0.0f) {
            return this;
        }
        float f11 = (1.0f / f10) * (f10 + 1.0f);
        float f12 = f * f11;
        float f13 = f2 * f11;
        float f14 = f3 * f11;
        int i = f4 > 0.0f ? 0 : 3;
        float[] fArr = this.data;
        fArr[i] = fArr[i] + (f7 * f12);
        float[] fArr2 = this.data;
        int i2 = i + 1;
        fArr2[i2] = fArr2[i2] + (f7 * f13);
        float[] fArr3 = this.data;
        int i3 = i + 2;
        fArr3[i3] = fArr3[i3] + (f7 * f14);
        int i4 = f5 > 0.0f ? 6 : 9;
        float[] fArr4 = this.data;
        fArr4[i4] = fArr4[i4] + (f8 * f12);
        float[] fArr5 = this.data;
        int i5 = i4 + 1;
        fArr5[i5] = fArr5[i5] + (f8 * f13);
        float[] fArr6 = this.data;
        int i6 = i4 + 2;
        fArr6[i6] = fArr6[i6] + (f8 * f14);
        int i7 = f6 > 0.0f ? 12 : 15;
        float[] fArr7 = this.data;
        fArr7[i7] = fArr7[i7] + (f9 * f12);
        float[] fArr8 = this.data;
        int i8 = i7 + 1;
        fArr8[i8] = fArr8[i8] + (f9 * f13);
        float[] fArr9 = this.data;
        int i9 = i7 + 2;
        fArr9[i9] = fArr9[i9] + (f9 * f14);
        return this;
    }

    public AmbientCubemap add(Color color, Vector3 vector3) {
        return add(color.r, color.g, color.f888b, vector3.x, vector3.y, vector3.z);
    }

    public AmbientCubemap add(float f, float f2, float f3, Vector3 vector3) {
        return add(f, f2, f3, vector3.x, vector3.y, vector3.z);
    }

    public AmbientCubemap add(Color color, float f, float f2, float f3) {
        return add(color.r, color.g, color.f888b, f, f2, f3);
    }

    public AmbientCubemap add(Color color, Vector3 vector3, Vector3 vector32) {
        return add(color.r, color.g, color.f888b, vector32.x - vector3.x, vector32.y - vector3.y, vector32.z - vector3.z);
    }

    public AmbientCubemap add(Color color, Vector3 vector3, Vector3 vector32, float f) {
        float dst = f / (1.0f + vector32.dst(vector3));
        return add(color.r * dst, color.g * dst, color.f888b * dst, vector32.x - vector3.x, vector32.y - vector3.y, vector32.z - vector3.z);
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < this.data.length; i += 3) {
            str = str + Float.toString(this.data[i]) + ", " + Float.toString(this.data[i + 1]) + ", " + Float.toString(this.data[i + 2]) + SequenceUtils.EOL;
        }
        return str;
    }
}
