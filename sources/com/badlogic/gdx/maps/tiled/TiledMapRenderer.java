package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapRenderer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/tiled/TiledMapRenderer.class */
public interface TiledMapRenderer extends MapRenderer {
    void renderObjects(MapLayer mapLayer);

    void renderObject(MapObject mapObject);

    void renderTileLayer(TiledMapTileLayer tiledMapTileLayer);

    void renderImageLayer(TiledMapImageLayer tiledMapImageLayer);
}
