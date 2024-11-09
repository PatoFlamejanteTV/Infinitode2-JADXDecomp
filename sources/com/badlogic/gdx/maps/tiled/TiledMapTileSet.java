package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.utils.IntMap;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/tiled/TiledMapTileSet.class */
public class TiledMapTileSet implements Iterable<TiledMapTile> {
    private String name;
    private IntMap<TiledMapTile> tiles = new IntMap<>();
    private MapProperties properties = new MapProperties();

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public MapProperties getProperties() {
        return this.properties;
    }

    public TiledMapTile getTile(int i) {
        return this.tiles.get(i);
    }

    @Override // java.lang.Iterable
    public Iterator<TiledMapTile> iterator() {
        return this.tiles.values().iterator();
    }

    public void putTile(int i, TiledMapTile tiledMapTile) {
        this.tiles.put(i, tiledMapTile);
    }

    public void removeTile(int i) {
        this.tiles.remove(i);
    }

    public int size() {
        return this.tiles.size;
    }
}
