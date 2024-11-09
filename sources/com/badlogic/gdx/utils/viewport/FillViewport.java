package com.badlogic.gdx.utils.viewport;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.Scaling;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/viewport/FillViewport.class */
public class FillViewport extends ScalingViewport {
    public FillViewport(float f, float f2) {
        super(Scaling.fill, f, f2);
    }

    public FillViewport(float f, float f2, Camera camera) {
        super(Scaling.fill, f, f2, camera);
    }
}
