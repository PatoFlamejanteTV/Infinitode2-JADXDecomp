package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.IntIntMap;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/FirstPersonCameraController.class */
public class FirstPersonCameraController extends InputAdapter {
    protected final Camera camera;
    protected final IntIntMap keys = new IntIntMap();
    public int strafeLeftKey = 29;
    public int strafeRightKey = 32;
    public int forwardKey = 51;
    public int backwardKey = 47;
    public int upKey = 45;
    public int downKey = 33;
    public boolean autoUpdate = true;
    protected float velocity = 5.0f;
    protected float degreesPerPixel = 0.5f;
    protected final Vector3 tmp = new Vector3();

    public FirstPersonCameraController(Camera camera) {
        this.camera = camera;
    }

    @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
    public boolean keyDown(int i) {
        this.keys.put(i, i);
        return true;
    }

    @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
    public boolean keyUp(int i) {
        this.keys.remove(i, 0);
        return true;
    }

    public void setVelocity(float f) {
        this.velocity = f;
    }

    public void setDegreesPerPixel(float f) {
        this.degreesPerPixel = f;
    }

    @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
    public boolean touchDragged(int i, int i2, int i3) {
        float f = (-Gdx.input.getDeltaX()) * this.degreesPerPixel;
        float f2 = (-Gdx.input.getDeltaY()) * this.degreesPerPixel;
        this.camera.direction.rotate(this.camera.up, f);
        this.tmp.set(this.camera.direction).crs(this.camera.up).nor();
        this.camera.direction.rotate(this.tmp, f2);
        return true;
    }

    public void update() {
        update(Gdx.graphics.getDeltaTime());
    }

    public void update(float f) {
        if (this.keys.containsKey(this.forwardKey)) {
            this.tmp.set(this.camera.direction).nor().scl(f * this.velocity);
            this.camera.position.add(this.tmp);
        }
        if (this.keys.containsKey(this.backwardKey)) {
            this.tmp.set(this.camera.direction).nor().scl((-f) * this.velocity);
            this.camera.position.add(this.tmp);
        }
        if (this.keys.containsKey(this.strafeLeftKey)) {
            this.tmp.set(this.camera.direction).crs(this.camera.up).nor().scl((-f) * this.velocity);
            this.camera.position.add(this.tmp);
        }
        if (this.keys.containsKey(this.strafeRightKey)) {
            this.tmp.set(this.camera.direction).crs(this.camera.up).nor().scl(f * this.velocity);
            this.camera.position.add(this.tmp);
        }
        if (this.keys.containsKey(this.upKey)) {
            this.tmp.set(this.camera.up).nor().scl(f * this.velocity);
            this.camera.position.add(this.tmp);
        }
        if (this.keys.containsKey(this.downKey)) {
            this.tmp.set(this.camera.up).nor().scl((-f) * this.velocity);
            this.camera.position.add(this.tmp);
        }
        if (this.autoUpdate) {
            this.camera.update(true);
        }
    }
}
