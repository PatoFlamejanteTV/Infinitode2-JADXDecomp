package com.badlogic.gdx.maps.tiled.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTile;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/tiled/objects/TiledMapTileMapObject.class */
public class TiledMapTileMapObject extends TextureMapObject {
    private boolean flipHorizontally;
    private boolean flipVertically;
    private TiledMapTile tile;

    public TiledMapTileMapObject(TiledMapTile tiledMapTile, boolean z, boolean z2) {
        this.flipHorizontally = z;
        this.flipVertically = z2;
        this.tile = tiledMapTile;
        TextureRegion textureRegion = new TextureRegion(tiledMapTile.getTextureRegion());
        textureRegion.flip(z, z2);
        setTextureRegion(textureRegion);
    }

    public boolean isFlipHorizontally() {
        return this.flipHorizontally;
    }

    public void setFlipHorizontally(boolean z) {
        this.flipHorizontally = z;
    }

    public boolean isFlipVertically() {
        return this.flipVertically;
    }

    public void setFlipVertically(boolean z) {
        this.flipVertically = z;
    }

    public TiledMapTile getTile() {
        return this.tile;
    }

    public void setTile(TiledMapTile tiledMapTile) {
        this.tile = tiledMapTile;
    }
}
