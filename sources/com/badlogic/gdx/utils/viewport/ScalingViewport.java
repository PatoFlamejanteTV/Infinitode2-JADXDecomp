package com.badlogic.gdx.utils.viewport;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/viewport/ScalingViewport.class */
public class ScalingViewport extends Viewport {
    private Scaling scaling;

    public ScalingViewport(Scaling scaling, float f, float f2) {
        this(scaling, f, f2, new OrthographicCamera());
    }

    public ScalingViewport(Scaling scaling, float f, float f2, Camera camera) {
        this.scaling = scaling;
        setWorldSize(f, f2);
        setCamera(camera);
    }

    @Override // com.badlogic.gdx.utils.viewport.Viewport
    public void update(int i, int i2, boolean z) {
        Vector2 apply = this.scaling.apply(getWorldWidth(), getWorldHeight(), i, i2);
        int round = Math.round(apply.x);
        int round2 = Math.round(apply.y);
        setScreenBounds((i - round) / 2, (i2 - round2) / 2, round, round2);
        apply(z);
    }

    public Scaling getScaling() {
        return this.scaling;
    }

    public void setScaling(Scaling scaling) {
        this.scaling = scaling;
    }
}
