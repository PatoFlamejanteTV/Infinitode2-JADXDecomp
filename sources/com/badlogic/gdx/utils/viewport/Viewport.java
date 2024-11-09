package com.badlogic.gdx.utils.viewport;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.HdpiUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/viewport/Viewport.class */
public abstract class Viewport {
    private Camera camera;
    private float worldWidth;
    private float worldHeight;
    private int screenX;
    private int screenY;
    private int screenWidth;
    private int screenHeight;
    private final Vector3 tmp = new Vector3();

    public void apply() {
        apply(false);
    }

    public void apply(boolean z) {
        HdpiUtils.glViewport(this.screenX, this.screenY, this.screenWidth, this.screenHeight);
        this.camera.viewportWidth = this.worldWidth;
        this.camera.viewportHeight = this.worldHeight;
        if (z) {
            this.camera.position.set(this.worldWidth / 2.0f, this.worldHeight / 2.0f, 0.0f);
        }
        this.camera.update();
    }

    public final void update(int i, int i2) {
        update(i, i2, false);
    }

    public void update(int i, int i2, boolean z) {
        apply(z);
    }

    public Vector2 unproject(Vector2 vector2) {
        this.tmp.set(vector2.x, vector2.y, 1.0f);
        this.camera.unproject(this.tmp, this.screenX, this.screenY, this.screenWidth, this.screenHeight);
        vector2.set(this.tmp.x, this.tmp.y);
        return vector2;
    }

    public Vector2 project(Vector2 vector2) {
        this.tmp.set(vector2.x, vector2.y, 1.0f);
        this.camera.project(this.tmp, this.screenX, this.screenY, this.screenWidth, this.screenHeight);
        vector2.set(this.tmp.x, this.tmp.y);
        return vector2;
    }

    public Vector3 unproject(Vector3 vector3) {
        this.camera.unproject(vector3, this.screenX, this.screenY, this.screenWidth, this.screenHeight);
        return vector3;
    }

    public Vector3 project(Vector3 vector3) {
        this.camera.project(vector3, this.screenX, this.screenY, this.screenWidth, this.screenHeight);
        return vector3;
    }

    public Ray getPickRay(float f, float f2) {
        return this.camera.getPickRay(f, f2, this.screenX, this.screenY, this.screenWidth, this.screenHeight);
    }

    public void calculateScissors(Matrix4 matrix4, Rectangle rectangle, Rectangle rectangle2) {
        ScissorStack.calculateScissors(this.camera, this.screenX, this.screenY, this.screenWidth, this.screenHeight, matrix4, rectangle, rectangle2);
    }

    public Vector2 toScreenCoordinates(Vector2 vector2, Matrix4 matrix4) {
        this.tmp.set(vector2.x, vector2.y, 0.0f);
        this.tmp.mul(matrix4);
        this.camera.project(this.tmp, this.screenX, this.screenY, this.screenWidth, this.screenHeight);
        this.tmp.y = Gdx.graphics.getHeight() - this.tmp.y;
        vector2.x = this.tmp.x;
        vector2.y = this.tmp.y;
        return vector2;
    }

    public Camera getCamera() {
        return this.camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public float getWorldWidth() {
        return this.worldWidth;
    }

    public void setWorldWidth(float f) {
        this.worldWidth = f;
    }

    public float getWorldHeight() {
        return this.worldHeight;
    }

    public void setWorldHeight(float f) {
        this.worldHeight = f;
    }

    public void setWorldSize(float f, float f2) {
        this.worldWidth = f;
        this.worldHeight = f2;
    }

    public int getScreenX() {
        return this.screenX;
    }

    public void setScreenX(int i) {
        this.screenX = i;
    }

    public int getScreenY() {
        return this.screenY;
    }

    public void setScreenY(int i) {
        this.screenY = i;
    }

    public int getScreenWidth() {
        return this.screenWidth;
    }

    public void setScreenWidth(int i) {
        this.screenWidth = i;
    }

    public int getScreenHeight() {
        return this.screenHeight;
    }

    public void setScreenHeight(int i) {
        this.screenHeight = i;
    }

    public void setScreenPosition(int i, int i2) {
        this.screenX = i;
        this.screenY = i2;
    }

    public void setScreenSize(int i, int i2) {
        this.screenWidth = i;
        this.screenHeight = i2;
    }

    public void setScreenBounds(int i, int i2, int i3, int i4) {
        this.screenX = i;
        this.screenY = i2;
        this.screenWidth = i3;
        this.screenHeight = i4;
    }

    public int getLeftGutterWidth() {
        return this.screenX;
    }

    public int getRightGutterX() {
        return this.screenX + this.screenWidth;
    }

    public int getRightGutterWidth() {
        return Gdx.graphics.getWidth() - (this.screenX + this.screenWidth);
    }

    public int getBottomGutterHeight() {
        return this.screenY;
    }

    public int getTopGutterY() {
        return this.screenY + this.screenHeight;
    }

    public int getTopGutterHeight() {
        return Gdx.graphics.getHeight() - (this.screenY + this.screenHeight);
    }
}
