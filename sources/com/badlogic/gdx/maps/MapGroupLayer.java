package com.badlogic.gdx.maps;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/MapGroupLayer.class */
public class MapGroupLayer extends MapLayer {
    private MapLayers layers = new MapLayers();

    public MapLayers getLayers() {
        return this.layers;
    }

    @Override // com.badlogic.gdx.maps.MapLayer
    public void invalidateRenderOffset() {
        super.invalidateRenderOffset();
        for (int i = 0; i < this.layers.size(); i++) {
            this.layers.get(i).invalidateRenderOffset();
        }
    }
}
