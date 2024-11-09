package com.badlogic.gdx.maps.tiled.renderers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/tiled/renderers/IsometricStaggeredTiledMapRenderer.class */
public class IsometricStaggeredTiledMapRenderer extends BatchTiledMapRenderer {
    public IsometricStaggeredTiledMapRenderer(TiledMap tiledMap) {
        super(tiledMap);
    }

    public IsometricStaggeredTiledMapRenderer(TiledMap tiledMap, Batch batch) {
        super(tiledMap, batch);
    }

    public IsometricStaggeredTiledMapRenderer(TiledMap tiledMap, float f) {
        super(tiledMap, f);
    }

    public IsometricStaggeredTiledMapRenderer(TiledMap tiledMap, float f, Batch batch) {
        super(tiledMap, f, batch);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.badlogic.gdx.maps.tiled.TiledMapRenderer
    public void renderTileLayer(TiledMapTileLayer tiledMapTileLayer) {
        TiledMapTile tile;
        Color color = this.batch.getColor();
        float floatBits = Color.toFloatBits(color.r, color.g, color.f888b, color.f889a * tiledMapTileLayer.getOpacity());
        int width = tiledMapTileLayer.getWidth();
        int height = tiledMapTileLayer.getHeight();
        float renderOffsetX = (tiledMapTileLayer.getRenderOffsetX() * this.unitScale) - (this.viewBounds.x * (tiledMapTileLayer.getParallaxX() - 1.0f));
        float parallaxY = ((-tiledMapTileLayer.getRenderOffsetY()) * this.unitScale) - (this.viewBounds.y * (tiledMapTileLayer.getParallaxY() - 1.0f));
        float tileWidth = tiledMapTileLayer.getTileWidth() * this.unitScale;
        float tileHeight = tiledMapTileLayer.getTileHeight() * this.unitScale;
        float f = tileWidth * 0.5f;
        float f2 = tileHeight * 0.5f;
        int max = Math.max(0, (int) (((this.viewBounds.x - f) - renderOffsetX) / tileWidth));
        int min = Math.min(width, (int) (((((this.viewBounds.x + this.viewBounds.width) + tileWidth) + f) - renderOffsetX) / tileWidth));
        int max2 = Math.max(0, (int) (((this.viewBounds.y - tileHeight) - parallaxY) / tileHeight));
        for (int min2 = Math.min(height, (int) ((((this.viewBounds.y + this.viewBounds.height) + tileHeight) - parallaxY) / f2)) - 1; min2 >= max2; min2--) {
            float f3 = min2 % 2 == 1 ? f : 0.0f;
            for (int i = min - 1; i >= max; i--) {
                TiledMapTileLayer.Cell cell = tiledMapTileLayer.getCell(i, min2);
                if (cell != null && (tile = cell.getTile()) != null) {
                    boolean flipHorizontally = cell.getFlipHorizontally();
                    boolean flipVertically = cell.getFlipVertically();
                    int rotation = cell.getRotation();
                    TextureRegion textureRegion = tile.getTextureRegion();
                    float offsetX = ((i * tileWidth) - f3) + (tile.getOffsetX() * this.unitScale) + renderOffsetX;
                    float offsetY = (min2 * f2) + (tile.getOffsetY() * this.unitScale) + parallaxY;
                    float regionWidth = offsetX + (textureRegion.getRegionWidth() * this.unitScale);
                    float regionHeight = offsetY + (textureRegion.getRegionHeight() * this.unitScale);
                    float u = textureRegion.getU();
                    float v2 = textureRegion.getV2();
                    float u2 = textureRegion.getU2();
                    float v = textureRegion.getV();
                    this.vertices[0] = offsetX;
                    this.vertices[1] = offsetY;
                    this.vertices[2] = floatBits;
                    this.vertices[3] = u;
                    this.vertices[4] = v2;
                    this.vertices[5] = offsetX;
                    this.vertices[6] = regionHeight;
                    this.vertices[7] = floatBits;
                    this.vertices[8] = u;
                    this.vertices[9] = v;
                    this.vertices[10] = regionWidth;
                    this.vertices[11] = regionHeight;
                    this.vertices[12] = floatBits;
                    this.vertices[13] = u2;
                    this.vertices[14] = v;
                    this.vertices[15] = regionWidth;
                    this.vertices[16] = offsetY;
                    this.vertices[17] = floatBits;
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
                    if (rotation != 0) {
                        switch (rotation) {
                            case 1:
                                float f8 = this.vertices[4];
                                this.vertices[4] = this.vertices[9];
                                this.vertices[9] = this.vertices[14];
                                this.vertices[14] = this.vertices[19];
                                this.vertices[19] = f8;
                                float f9 = this.vertices[3];
                                this.vertices[3] = this.vertices[8];
                                this.vertices[8] = this.vertices[13];
                                this.vertices[13] = this.vertices[18];
                                this.vertices[18] = f9;
                                break;
                            case 2:
                                float f10 = this.vertices[3];
                                this.vertices[3] = this.vertices[13];
                                this.vertices[13] = f10;
                                float f11 = this.vertices[8];
                                this.vertices[8] = this.vertices[18];
                                this.vertices[18] = f11;
                                float f12 = this.vertices[4];
                                this.vertices[4] = this.vertices[14];
                                this.vertices[14] = f12;
                                float f13 = this.vertices[9];
                                this.vertices[9] = this.vertices[19];
                                this.vertices[19] = f13;
                                break;
                            case 3:
                                float f14 = this.vertices[4];
                                this.vertices[4] = this.vertices[19];
                                this.vertices[19] = this.vertices[14];
                                this.vertices[14] = this.vertices[9];
                                this.vertices[9] = f14;
                                float f15 = this.vertices[3];
                                this.vertices[3] = this.vertices[18];
                                this.vertices[18] = this.vertices[13];
                                this.vertices[13] = this.vertices[8];
                                this.vertices[8] = f15;
                                break;
                        }
                    }
                    this.batch.draw(textureRegion.getTexture(), this.vertices, 0, 20);
                }
            }
        }
    }
}
