package com.badlogic.gdx.utils.viewport;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/viewport/ExtendViewport.class */
public class ExtendViewport extends Viewport {
    private float minWorldWidth;
    private float minWorldHeight;
    private float maxWorldWidth;
    private float maxWorldHeight;
    private Scaling scaling;

    public ExtendViewport(float f, float f2) {
        this(f, f2, 0.0f, 0.0f, new OrthographicCamera());
    }

    public ExtendViewport(float f, float f2, Camera camera) {
        this(f, f2, 0.0f, 0.0f, camera);
    }

    public ExtendViewport(float f, float f2, float f3, float f4) {
        this(f, f2, f3, f4, new OrthographicCamera());
    }

    public ExtendViewport(float f, float f2, float f3, float f4, Camera camera) {
        this.scaling = Scaling.fit;
        this.minWorldWidth = f;
        this.minWorldHeight = f2;
        this.maxWorldWidth = f3;
        this.maxWorldHeight = f4;
        setCamera(camera);
    }

    @Override // com.badlogic.gdx.utils.viewport.Viewport
    public void update(int i, int i2, boolean z) {
        float f = this.minWorldWidth;
        float f2 = this.minWorldHeight;
        Vector2 apply = this.scaling.apply(f, f2, i, i2);
        int round = Math.round(apply.x);
        int round2 = Math.round(apply.y);
        if (round < i) {
            float f3 = round2 / f2;
            float f4 = (i - round) * (f2 / round2);
            if (this.maxWorldWidth > 0.0f) {
                f4 = Math.min(f4, this.maxWorldWidth - this.minWorldWidth);
            }
            f += f4;
            round += Math.round(f4 * f3);
        }
        if (round2 < i2) {
            float f5 = round / f;
            float f6 = (i2 - round2) * (f / round);
            if (this.maxWorldHeight > 0.0f) {
                f6 = Math.min(f6, this.maxWorldHeight - this.minWorldHeight);
            }
            f2 += f6;
            round2 += Math.round(f6 * f5);
        }
        setWorldSize(f, f2);
        setScreenBounds((i - round) / 2, (i2 - round2) / 2, round, round2);
        apply(z);
    }

    public float getMinWorldWidth() {
        return this.minWorldWidth;
    }

    public void setMinWorldWidth(float f) {
        this.minWorldWidth = f;
    }

    public float getMinWorldHeight() {
        return this.minWorldHeight;
    }

    public void setMinWorldHeight(float f) {
        this.minWorldHeight = f;
    }

    public float getMaxWorldWidth() {
        return this.maxWorldWidth;
    }

    public void setMaxWorldWidth(float f) {
        this.maxWorldWidth = f;
    }

    public float getMaxWorldHeight() {
        return this.maxWorldHeight;
    }

    public void setMaxWorldHeight(float f) {
        this.maxWorldHeight = f;
    }

    public void setScaling(Scaling scaling) {
        this.scaling = scaling;
    }
}
