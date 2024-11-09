package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/OrthographicCamera.class */
public class OrthographicCamera extends Camera {
    public float zoom = 1.0f;
    private final Vector3 tmp = new Vector3();

    public OrthographicCamera() {
        this.near = 0.0f;
    }

    public OrthographicCamera(float f, float f2) {
        this.viewportWidth = f;
        this.viewportHeight = f2;
        this.near = 0.0f;
        update();
    }

    @Override // com.badlogic.gdx.graphics.Camera
    public void update() {
        update(true);
    }

    @Override // com.badlogic.gdx.graphics.Camera
    public void update(boolean z) {
        this.projection.setToOrtho((this.zoom * (-this.viewportWidth)) / 2.0f, this.zoom * (this.viewportWidth / 2.0f), this.zoom * (-(this.viewportHeight / 2.0f)), (this.zoom * this.viewportHeight) / 2.0f, this.near, this.far);
        this.view.setToLookAt(this.direction, this.up);
        this.view.translate(-this.position.x, -this.position.y, -this.position.z);
        this.combined.set(this.projection);
        Matrix4.mul(this.combined.val, this.view.val);
        if (z) {
            this.invProjectionView.set(this.combined);
            Matrix4.inv(this.invProjectionView.val);
            this.frustum.update(this.invProjectionView);
        }
    }

    public void setToOrtho(boolean z) {
        setToOrtho(z, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void setToOrtho(boolean z, float f, float f2) {
        if (z) {
            this.up.set(0.0f, -1.0f, 0.0f);
            this.direction.set(0.0f, 0.0f, 1.0f);
        } else {
            this.up.set(0.0f, 1.0f, 0.0f);
            this.direction.set(0.0f, 0.0f, -1.0f);
        }
        this.position.set((this.zoom * f) / 2.0f, (this.zoom * f2) / 2.0f, 0.0f);
        this.viewportWidth = f;
        this.viewportHeight = f2;
        update();
    }

    public void rotate(float f) {
        rotate(this.direction, f);
    }

    public void translate(float f, float f2) {
        translate(f, f2, 0.0f);
    }

    public void translate(Vector2 vector2) {
        translate(vector2.x, vector2.y, 0.0f);
    }
}
