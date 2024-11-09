package com.badlogic.gdx.maps;

import com.badlogic.gdx.utils.Disposable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/Map.class */
public class Map implements Disposable {
    private MapLayers layers = new MapLayers();
    private MapProperties properties = new MapProperties();

    public MapLayers getLayers() {
        return this.layers;
    }

    public MapProperties getProperties() {
        return this.properties;
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }
}
