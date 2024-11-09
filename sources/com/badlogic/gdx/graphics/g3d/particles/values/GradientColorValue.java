package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/values/GradientColorValue.class */
public class GradientColorValue extends ParticleValue {
    private static float[] temp = new float[3];
    private float[] colors = {1.0f, 1.0f, 1.0f};
    public float[] timeline = {0.0f};

    public float[] getTimeline() {
        return this.timeline;
    }

    public void setTimeline(float[] fArr) {
        this.timeline = fArr;
    }

    public float[] getColors() {
        return this.colors;
    }

    public void setColors(float[] fArr) {
        this.colors = fArr;
    }

    public float[] getColor(float f) {
        getColor(f, temp, 0);
        return temp;
    }

    public void getColor(float f, float[] fArr, int i) {
        int i2 = 0;
        int i3 = -1;
        float[] fArr2 = this.timeline;
        int length = fArr2.length;
        int i4 = 1;
        while (true) {
            if (i4 >= length) {
                break;
            }
            if (fArr2[i4] > f) {
                i3 = i4;
                break;
            } else {
                i2 = i4;
                i4++;
            }
        }
        float f2 = fArr2[i2];
        int i5 = i2 * 3;
        float f3 = this.colors[i5];
        float f4 = this.colors[i5 + 1];
        float f5 = this.colors[i5 + 2];
        if (i3 != -1) {
            float f6 = (f - f2) / (fArr2[i3] - f2);
            int i6 = i3 * 3;
            fArr[i] = f3 + ((this.colors[i6] - f3) * f6);
            fArr[i + 1] = f4 + ((this.colors[i6 + 1] - f4) * f6);
            fArr[i + 2] = f5 + ((this.colors[i6 + 2] - f5) * f6);
            return;
        }
        fArr[i] = f3;
        fArr[i + 1] = f4;
        fArr[i + 2] = f5;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue, com.badlogic.gdx.utils.Json.Serializable
    public void write(Json json) {
        super.write(json);
        json.writeValue("colors", this.colors);
        json.writeValue("timeline", this.timeline);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue, com.badlogic.gdx.utils.Json.Serializable
    public void read(Json json, JsonValue jsonValue) {
        super.read(json, jsonValue);
        this.colors = (float[]) json.readValue("colors", float[].class, jsonValue);
        this.timeline = (float[]) json.readValue("timeline", float[].class, jsonValue);
    }

    public void load(GradientColorValue gradientColorValue) {
        super.load((ParticleValue) gradientColorValue);
        this.colors = new float[gradientColorValue.colors.length];
        System.arraycopy(gradientColorValue.colors, 0, this.colors, 0, this.colors.length);
        this.timeline = new float[gradientColorValue.timeline.length];
        System.arraycopy(gradientColorValue.timeline, 0, this.timeline, 0, this.timeline.length);
    }
}
