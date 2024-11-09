package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/values/ScaledNumericValue.class */
public class ScaledNumericValue extends RangedNumericValue {
    private float highMin;
    private float highMax;
    private float[] scaling = {1.0f};
    public float[] timeline = {0.0f};
    private boolean relative = false;

    public float newHighValue() {
        return this.highMin + ((this.highMax - this.highMin) * MathUtils.random());
    }

    public void setHigh(float f) {
        this.highMin = f;
        this.highMax = f;
    }

    public void setHigh(float f, float f2) {
        this.highMin = f;
        this.highMax = f2;
    }

    public float getHighMin() {
        return this.highMin;
    }

    public void setHighMin(float f) {
        this.highMin = f;
    }

    public float getHighMax() {
        return this.highMax;
    }

    public void setHighMax(float f) {
        this.highMax = f;
    }

    public float[] getScaling() {
        return this.scaling;
    }

    public void setScaling(float[] fArr) {
        this.scaling = fArr;
    }

    public float[] getTimeline() {
        return this.timeline;
    }

    public void setTimeline(float[] fArr) {
        this.timeline = fArr;
    }

    public boolean isRelative() {
        return this.relative;
    }

    public void setRelative(boolean z) {
        this.relative = z;
    }

    public float getScale(float f) {
        int i = -1;
        int length = this.timeline.length;
        int i2 = 1;
        while (true) {
            if (i2 >= length) {
                break;
            }
            if (this.timeline[i2] <= f) {
                i2++;
            } else {
                i = i2;
                break;
            }
        }
        if (i == -1) {
            return this.scaling[length - 1];
        }
        int i3 = i - 1;
        float f2 = this.scaling[i3];
        float f3 = this.timeline[i3];
        return f2 + ((this.scaling[i] - f2) * ((f - f3) / (this.timeline[i] - f3)));
    }

    public void load(ScaledNumericValue scaledNumericValue) {
        super.load((RangedNumericValue) scaledNumericValue);
        this.highMax = scaledNumericValue.highMax;
        this.highMin = scaledNumericValue.highMin;
        this.scaling = new float[scaledNumericValue.scaling.length];
        System.arraycopy(scaledNumericValue.scaling, 0, this.scaling, 0, this.scaling.length);
        this.timeline = new float[scaledNumericValue.timeline.length];
        System.arraycopy(scaledNumericValue.timeline, 0, this.timeline, 0, this.timeline.length);
        this.relative = scaledNumericValue.relative;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.RangedNumericValue, com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue, com.badlogic.gdx.utils.Json.Serializable
    public void write(Json json) {
        super.write(json);
        json.writeValue("highMin", Float.valueOf(this.highMin));
        json.writeValue("highMax", Float.valueOf(this.highMax));
        json.writeValue("relative", Boolean.valueOf(this.relative));
        json.writeValue("scaling", this.scaling);
        json.writeValue("timeline", this.timeline);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.RangedNumericValue, com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue, com.badlogic.gdx.utils.Json.Serializable
    public void read(Json json, JsonValue jsonValue) {
        super.read(json, jsonValue);
        this.highMin = ((Float) json.readValue("highMin", Float.TYPE, jsonValue)).floatValue();
        this.highMax = ((Float) json.readValue("highMax", Float.TYPE, jsonValue)).floatValue();
        this.relative = ((Boolean) json.readValue("relative", Boolean.TYPE, jsonValue)).booleanValue();
        this.scaling = (float[]) json.readValue("scaling", float[].class, jsonValue);
        this.timeline = (float[]) json.readValue("timeline", float[].class, jsonValue);
    }
}
