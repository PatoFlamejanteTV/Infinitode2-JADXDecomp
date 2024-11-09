package com.badlogic.gdx.maps.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/objects/TextureMapObject.class */
public class TextureMapObject extends MapObject {
    private float x;
    private float y;
    private float originX;
    private float originY;
    private float scaleX;
    private float scaleY;
    private float rotation;
    private TextureRegion textureRegion;

    public float getX() {
        return this.x;
    }

    public void setX(float f) {
        this.x = f;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float f) {
        this.y = f;
    }

    public float getOriginX() {
        return this.originX;
    }

    public void setOriginX(float f) {
        this.originX = f;
    }

    public float getOriginY() {
        return this.originY;
    }

    public void setOriginY(float f) {
        this.originY = f;
    }

    public float getScaleX() {
        return this.scaleX;
    }

    public void setScaleX(float f) {
        this.scaleX = f;
    }

    public float getScaleY() {
        return this.scaleY;
    }

    public void setScaleY(float f) {
        this.scaleY = f;
    }

    public float getRotation() {
        return this.rotation;
    }

    public void setRotation(float f) {
        this.rotation = f;
    }

    public TextureRegion getTextureRegion() {
        return this.textureRegion;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public TextureMapObject() {
        this(null);
    }

    public TextureMapObject(TextureRegion textureRegion) {
        this.x = 0.0f;
        this.y = 0.0f;
        this.originX = 0.0f;
        this.originY = 0.0f;
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.rotation = 0.0f;
        this.textureRegion = null;
        this.textureRegion = textureRegion;
    }
}
