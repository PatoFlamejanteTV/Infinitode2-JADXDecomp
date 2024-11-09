package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/tiled/TiledMapImageLayer.class */
public class TiledMapImageLayer extends MapLayer {
    private TextureRegion region;
    private float x;
    private float y;

    public TiledMapImageLayer(TextureRegion textureRegion, float f, float f2) {
        this.region = textureRegion;
        this.x = f;
        this.y = f2;
    }

    public TextureRegion getTextureRegion() {
        return this.region;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.region = textureRegion;
    }

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
}
