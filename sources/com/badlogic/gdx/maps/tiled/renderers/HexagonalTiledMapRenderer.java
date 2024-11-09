package com.badlogic.gdx.maps.tiled.renderers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/tiled/renderers/HexagonalTiledMapRenderer.class */
public class HexagonalTiledMapRenderer extends BatchTiledMapRenderer {
    private boolean staggerAxisX;
    private boolean staggerIndexEven;
    private float hexSideLength;

    public HexagonalTiledMapRenderer(TiledMap tiledMap) {
        super(tiledMap);
        this.staggerAxisX = true;
        this.staggerIndexEven = false;
        this.hexSideLength = 0.0f;
        init(tiledMap);
    }

    public HexagonalTiledMapRenderer(TiledMap tiledMap, float f) {
        super(tiledMap, f);
        this.staggerAxisX = true;
        this.staggerIndexEven = false;
        this.hexSideLength = 0.0f;
        init(tiledMap);
    }

    public HexagonalTiledMapRenderer(TiledMap tiledMap, Batch batch) {
        super(tiledMap, batch);
        this.staggerAxisX = true;
        this.staggerIndexEven = false;
        this.hexSideLength = 0.0f;
        init(tiledMap);
    }

    public HexagonalTiledMapRenderer(TiledMap tiledMap, float f, Batch batch) {
        super(tiledMap, f, batch);
        this.staggerAxisX = true;
        this.staggerIndexEven = false;
        this.hexSideLength = 0.0f;
        init(tiledMap);
    }

    private void init(TiledMap tiledMap) {
        String str = (String) tiledMap.getProperties().get("staggeraxis", String.class);
        if (str != null) {
            if (str.equals("x")) {
                this.staggerAxisX = true;
            } else {
                this.staggerAxisX = false;
            }
        }
        String str2 = (String) tiledMap.getProperties().get("staggerindex", String.class);
        if (str2 != null) {
            if (str2.equals("even")) {
                this.staggerIndexEven = true;
            } else {
                this.staggerIndexEven = false;
            }
        }
        if (!this.staggerAxisX && ((Integer) tiledMap.getProperties().get("height", Integer.class)).intValue() % 2 == 0) {
            this.staggerIndexEven = !this.staggerIndexEven;
        }
        if (((Integer) tiledMap.getProperties().get("hexsidelength", Integer.class)) != null) {
            this.hexSideLength = r0.intValue();
            return;
        }
        if (this.staggerAxisX) {
            if (((Integer) tiledMap.getProperties().get("tilewidth", Integer.class)) != null) {
                this.hexSideLength = 0.5f * r0.intValue();
                return;
            } else {
                this.hexSideLength = 0.5f * ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getTileWidth();
                return;
            }
        }
        if (((Integer) tiledMap.getProperties().get("tileheight", Integer.class)) != null) {
            this.hexSideLength = 0.5f * r0.intValue();
        } else {
            this.hexSideLength = 0.5f * ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getTileHeight();
        }
    }

    @Override // com.badlogic.gdx.maps.tiled.TiledMapRenderer
    public void renderTileLayer(TiledMapTileLayer tiledMapTileLayer) {
        float f;
        Color color = this.batch.getColor();
        float floatBits = Color.toFloatBits(color.r, color.g, color.f888b, color.f889a * tiledMapTileLayer.getOpacity());
        int width = tiledMapTileLayer.getWidth();
        int height = tiledMapTileLayer.getHeight();
        float tileWidth = tiledMapTileLayer.getTileWidth() * this.unitScale;
        float tileHeight = tiledMapTileLayer.getTileHeight() * this.unitScale;
        float renderOffsetX = (tiledMapTileLayer.getRenderOffsetX() * this.unitScale) - (this.viewBounds.x * (tiledMapTileLayer.getParallaxX() - 1.0f));
        float parallaxY = ((-tiledMapTileLayer.getRenderOffsetY()) * this.unitScale) - (this.viewBounds.y * (tiledMapTileLayer.getParallaxY() - 1.0f));
        float f2 = this.hexSideLength * this.unitScale;
        if (this.staggerAxisX) {
            float f3 = (tileWidth - f2) / 2.0f;
            float f4 = (tileWidth + f2) / 2.0f;
            float f5 = tileHeight * 0.5f;
            int max = Math.max(0, (int) (((this.viewBounds.y - f5) - renderOffsetX) / tileHeight));
            int min = Math.min(height, (int) ((((this.viewBounds.y + this.viewBounds.height) + tileHeight) - renderOffsetX) / tileHeight));
            int max2 = Math.max(0, (int) (((this.viewBounds.x - f3) - parallaxY) / f4));
            int min2 = Math.min(width, (int) ((((this.viewBounds.x + this.viewBounds.width) + f4) - parallaxY) / f4));
            int i = this.staggerIndexEven == (max2 % 2 == 0) ? max2 + 1 : max2;
            int i2 = this.staggerIndexEven == (max2 % 2 == 0) ? max2 : max2 + 1;
            for (int i3 = min - 1; i3 >= max; i3--) {
                for (int i4 = i; i4 < min2; i4 += 2) {
                    renderCell(tiledMapTileLayer.getCell(i4, i3), (f4 * i4) + renderOffsetX, f5 + (tileHeight * i3) + parallaxY, floatBits);
                }
                for (int i5 = i2; i5 < min2; i5 += 2) {
                    renderCell(tiledMapTileLayer.getCell(i5, i3), (f4 * i5) + renderOffsetX, (tileHeight * i3) + parallaxY, floatBits);
                }
            }
            return;
        }
        float f6 = (tileHeight - f2) / 2.0f;
        float f7 = (tileHeight + f2) / 2.0f;
        float f8 = tileWidth * 0.5f;
        int max3 = Math.max(0, (int) (((this.viewBounds.y - f6) - renderOffsetX) / f7));
        int min3 = Math.min(height, (int) ((((this.viewBounds.y + this.viewBounds.height) + f7) - renderOffsetX) / f7));
        int max4 = Math.max(0, (int) (((this.viewBounds.x - f8) - parallaxY) / tileWidth));
        int min4 = Math.min(width, (int) ((((this.viewBounds.x + this.viewBounds.width) + tileWidth) - parallaxY) / tileWidth));
        for (int i6 = min3 - 1; i6 >= max3; i6--) {
            if ((i6 % 2 == 0) == this.staggerIndexEven) {
                f = f8;
            } else {
                f = 0.0f;
            }
            for (int i7 = max4; i7 < min4; i7++) {
                renderCell(tiledMapTileLayer.getCell(i7, i6), (tileWidth * i7) + f + renderOffsetX, (f7 * i6) + parallaxY, floatBits);
            }
        }
    }

    private void renderCell(TiledMapTileLayer.Cell cell, float f, float f2, float f3) {
        TiledMapTile tile;
        if (cell == null || (tile = cell.getTile()) == null || (tile instanceof AnimatedTiledMapTile)) {
            return;
        }
        boolean flipHorizontally = cell.getFlipHorizontally();
        boolean flipVertically = cell.getFlipVertically();
        int rotation = cell.getRotation();
        TextureRegion textureRegion = tile.getTextureRegion();
        float offsetX = f + (tile.getOffsetX() * this.unitScale);
        float offsetY = f2 + (tile.getOffsetY() * this.unitScale);
        float regionWidth = offsetX + (textureRegion.getRegionWidth() * this.unitScale);
        float regionHeight = offsetY + (textureRegion.getRegionHeight() * this.unitScale);
        float u = textureRegion.getU();
        float v2 = textureRegion.getV2();
        float u2 = textureRegion.getU2();
        float v = textureRegion.getV();
        this.vertices[0] = offsetX;
        this.vertices[1] = offsetY;
        this.vertices[2] = f3;
        this.vertices[3] = u;
        this.vertices[4] = v2;
        this.vertices[5] = offsetX;
        this.vertices[6] = regionHeight;
        this.vertices[7] = f3;
        this.vertices[8] = u;
        this.vertices[9] = v;
        this.vertices[10] = regionWidth;
        this.vertices[11] = regionHeight;
        this.vertices[12] = f3;
        this.vertices[13] = u2;
        this.vertices[14] = v;
        this.vertices[15] = regionWidth;
        this.vertices[16] = offsetY;
        this.vertices[17] = f3;
        this.vertices[18] = u2;
        this.vertices[19] = v2;
        if (flipHorizontally) {
            float f4 = this.vertices[3];
            this.vertices[3] = this.vertices[13];
            this.vertices[13] = f4;
            float f5 = this.vertices[8];
            this.vertices[8] = this.vertices[18];
            this.vertices[18] = f5;
        }
        if (flipVertically) {
            float f6 = this.vertices[4];
            this.vertices[4] = this.vertices[14];
            this.vertices[14] = f6;
            float f7 = this.vertices[9];
            this.vertices[9] = this.vertices[19];
            this.vertices[19] = f7;
        }
        if (rotation == 2) {
            float f8 = this.vertices[3];
            this.vertices[3] = this.vertices[13];
            this.vertices[13] = f8;
            float f9 = this.vertices[8];
            this.vertices[8] = this.vertices[18];
            this.vertices[18] = f9;
            float f10 = this.vertices[4];
            this.vertices[4] = this.vertices[14];
            this.vertices[14] = f10;
            float f11 = this.vertices[9];
            this.vertices[9] = this.vertices[19];
            this.vertices[19] = f11;
        }
        this.batch.draw(textureRegion.getTexture(), this.vertices, 0, 20);
    }
}
