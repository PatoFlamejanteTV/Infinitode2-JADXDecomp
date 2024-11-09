package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/tiled/TiledMap.class */
public class TiledMap extends Map {
    private TiledMapTileSets tilesets = new TiledMapTileSets();
    private Array<? extends Disposable> ownedResources;

    public TiledMapTileSets getTileSets() {
        return this.tilesets;
    }

    public void setOwnedResources(Array<? extends Disposable> array) {
        this.ownedResources = array;
    }

    @Override // com.badlogic.gdx.maps.Map, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if (this.ownedResources != null) {
            Array.ArrayIterator<? extends Disposable> it = this.ownedResources.iterator();
            while (it.hasNext()) {
                it.next().dispose();
            }
        }
    }
}
