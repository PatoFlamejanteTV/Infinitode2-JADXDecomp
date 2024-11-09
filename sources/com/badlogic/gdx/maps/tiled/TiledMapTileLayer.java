package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.maps.MapLayer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/tiled/TiledMapTileLayer.class */
public class TiledMapTileLayer extends MapLayer {
    private int width;
    private int height;
    private int tileWidth;
    private int tileHeight;
    private Cell[][] cells;

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getTileWidth() {
        return this.tileWidth;
    }

    public int getTileHeight() {
        return this.tileHeight;
    }

    public TiledMapTileLayer(int i, int i2, int i3, int i4) {
        this.width = i;
        this.height = i2;
        this.tileWidth = i3;
        this.tileHeight = i4;
        this.cells = new Cell[i][i2];
    }

    public Cell getCell(int i, int i2) {
        if (i < 0 || i >= this.width || i2 < 0 || i2 >= this.height) {
            return null;
        }
        return this.cells[i][i2];
    }

    public void setCell(int i, int i2, Cell cell) {
        if (i < 0 || i >= this.width || i2 < 0 || i2 >= this.height) {
            return;
        }
        this.cells[i][i2] = cell;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/tiled/TiledMapTileLayer$Cell.class */
    public static class Cell {
        private TiledMapTile tile;
        private boolean flipHorizontally;
        private boolean flipVertically;
        private int rotation;
        public static final int ROTATE_0 = 0;
        public static final int ROTATE_90 = 1;
        public static final int ROTATE_180 = 2;
        public static final int ROTATE_270 = 3;

        public TiledMapTile getTile() {
            return this.tile;
        }

        public Cell setTile(TiledMapTile tiledMapTile) {
            this.tile = tiledMapTile;
            return this;
        }

        public boolean getFlipHorizontally() {
            return this.flipHorizontally;
        }

        public Cell setFlipHorizontally(boolean z) {
            this.flipHorizontally = z;
            return this;
        }

        public boolean getFlipVertically() {
            return this.flipVertically;
        }

        public Cell setFlipVertically(boolean z) {
            this.flipVertically = z;
            return this;
        }

        public int getRotation() {
            return this.rotation;
        }

        public Cell setRotation(int i) {
            this.rotation = i;
            return this;
        }
    }
}
