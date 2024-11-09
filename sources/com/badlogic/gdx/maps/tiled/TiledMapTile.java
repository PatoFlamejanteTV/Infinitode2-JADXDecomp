package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/tiled/TiledMapTile.class */
public interface TiledMapTile {

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/tiled/TiledMapTile$BlendMode.class */
    public enum BlendMode {
        NONE,
        ALPHA
    }

    int getId();

    void setId(int i);

    BlendMode getBlendMode();

    void setBlendMode(BlendMode blendMode);

    TextureRegion getTextureRegion();

    void setTextureRegion(TextureRegion textureRegion);

    float getOffsetX();

    void setOffsetX(float f);

    float getOffsetY();

    void setOffsetY(float f);

    MapProperties getProperties();

    MapObjects getObjects();
}
