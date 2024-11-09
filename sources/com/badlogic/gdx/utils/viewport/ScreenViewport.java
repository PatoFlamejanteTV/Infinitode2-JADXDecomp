package com.badlogic.gdx.utils.viewport;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/viewport/ScreenViewport.class */
public class ScreenViewport extends Viewport {
    private float unitsPerPixel;

    public ScreenViewport() {
        this(new OrthographicCamera());
    }

    public ScreenViewport(Camera camera) {
        this.unitsPerPixel = 1.0f;
        setCamera(camera);
    }

    @Override // com.badlogic.gdx.utils.viewport.Viewport
    public void update(int i, int i2, boolean z) {
        setScreenBounds(0, 0, i, i2);
        setWorldSize(i * this.unitsPerPixel, i2 * this.unitsPerPixel);
        apply(z);
    }

    public float getUnitsPerPixel() {
        return this.unitsPerPixel;
    }

    public void setUnitsPerPixel(float f) {
        this.unitsPerPixel = f;
    }
}
