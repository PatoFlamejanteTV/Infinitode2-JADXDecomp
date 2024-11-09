package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.utils.Array;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/tiled/TiledMapTileSets.class */
public class TiledMapTileSets implements Iterable<TiledMapTileSet> {
    private Array<TiledMapTileSet> tilesets = new Array<>();

    public TiledMapTileSet getTileSet(int i) {
        return this.tilesets.get(i);
    }

    public TiledMapTileSet getTileSet(String str) {
        Array.ArrayIterator<TiledMapTileSet> it = this.tilesets.iterator();
        while (it.hasNext()) {
            TiledMapTileSet next = it.next();
            if (str.equals(next.getName())) {
                return next;
            }
        }
        return null;
    }

    public void addTileSet(TiledMapTileSet tiledMapTileSet) {
        this.tilesets.add(tiledMapTileSet);
    }

    public void removeTileSet(int i) {
        this.tilesets.removeIndex(i);
    }

    public void removeTileSet(TiledMapTileSet tiledMapTileSet) {
        this.tilesets.removeValue(tiledMapTileSet, true);
    }

    public TiledMapTile getTile(int i) {
        for (int i2 = this.tilesets.size - 1; i2 >= 0; i2--) {
            TiledMapTile tile = this.tilesets.get(i2).getTile(i);
            if (tile != null) {
                return tile;
            }
        }
        return null;
    }

    @Override // java.lang.Iterable
    public Iterator<TiledMapTileSet> iterator() {
        return this.tilesets.iterator();
    }
}
