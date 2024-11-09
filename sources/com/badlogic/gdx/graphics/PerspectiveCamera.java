package com.badlogic.gdx.graphics;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/PerspectiveCamera.class */
public class PerspectiveCamera extends Camera {
    public float fieldOfView;
    final Vector3 tmp;

    public PerspectiveCamera() {
        this.fieldOfView = 67.0f;
        this.tmp = new Vector3();
    }

    public PerspectiveCamera(float f, float f2, float f3) {
        this.fieldOfView = 67.0f;
        this.tmp = new Vector3();
        this.fieldOfView = f;
        this.viewportWidth = f2;
        this.viewportHeight = f3;
        update();
    }

    @Override // com.badlogic.gdx.graphics.Camera
    public void update() {
        update(true);
    }

    @Override // com.badlogic.gdx.graphics.Camera
    public void update(boolean z) {
        this.projection.setToProjection(Math.abs(this.near), Math.abs(this.far), this.fieldOfView, this.viewportWidth / this.viewportHeight);
        this.view.setToLookAt(this.position, this.tmp.set(this.position).add(this.direction), this.up);
        this.combined.set(this.projection);
        Matrix4.mul(this.combined.val, this.view.val);
        if (z) {
            this.invProjectionView.set(this.combined);
            Matrix4.inv(this.invProjectionView.val);
            this.frustum.update(this.invProjectionView);
        }
    }
}
