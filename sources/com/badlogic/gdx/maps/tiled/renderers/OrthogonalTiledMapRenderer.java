package com.badlogic.gdx.maps.tiled.renderers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/tiled/renderers/OrthogonalTiledMapRenderer.class */
public class OrthogonalTiledMapRenderer extends BatchTiledMapRenderer {
    public OrthogonalTiledMapRenderer(TiledMap tiledMap) {
        super(tiledMap);
    }

    public OrthogonalTiledMapRenderer(TiledMap tiledMap, Batch batch) {
        super(tiledMap, batch);
    }

    public OrthogonalTiledMapRenderer(TiledMap tiledMap, float f) {
        super(tiledMap, f);
    }

    public OrthogonalTiledMapRenderer(TiledMap tiledMap, float f, Batch batch) {
        super(tiledMap, f, batch);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:20:0x0299. Please report as an issue. */
    @Override // com.badlogic.gdx.maps.tiled.TiledMapRenderer
    public void renderTileLayer(TiledMapTileLayer tiledMapTileLayer) {
        TiledMapTile tile;
        Color color = this.batch.getColor();
        float floatBits = Color.toFloatBits(color.r, color.g, color.f888b, color.f889a * tiledMapTileLayer.getOpacity());
        int width = tiledMapTileLayer.getWidth();
        int height = tiledMapTileLayer.getHeight();
        float tileWidth = tiledMapTileLayer.getTileWidth() * this.unitScale;
        float tileHeight = tiledMapTileLayer.getTileHeight() * this.unitScale;
        float renderOffsetX = (tiledMapTileLayer.getRenderOffsetX() * this.unitScale) - (this.viewBounds.x * (tiledMapTileLayer.getParallaxX() - 1.0f));
        float parallaxY = ((-tiledMapTileLayer.getRenderOffsetY()) * this.unitScale) - (this.viewBounds.y * (tiledMapTileLayer.getParallaxY() - 1.0f));
        int max = Math.max(0, (int) ((this.viewBounds.x - renderOffsetX) / tileWidth));
        int min = Math.min(width, (int) ((((this.viewBounds.x + this.viewBounds.width) + tileWidth) - renderOffsetX) / tileWidth));
        int max2 = Math.max(0, (int) ((this.viewBounds.y - parallaxY) / tileHeight));
        int min2 = Math.min(height, (int) ((((this.viewBounds.y + this.viewBounds.height) + tileHeight) - parallaxY) / tileHeight));
        float f = (min2 * tileHeight) + parallaxY;
        float f2 = (max * tileWidth) + renderOffsetX;
        float[] fArr = this.vertices;
        for (int i = min2; i >= max2; i--) {
            float f3 = f2;
            for (int i2 = max; i2 < min; i2++) {
                TiledMapTileLayer.Cell cell = tiledMapTileLayer.getCell(i2, i);
                if (cell != null && (tile = cell.getTile()) != null) {
                    boolean flipHorizontally = cell.getFlipHorizontally();
                    boolean flipVertically = cell.getFlipVertically();
                    int rotation = cell.getRotation();
                    TextureRegion textureRegion = tile.getTextureRegion();
                    float offsetX = f3 + (tile.getOffsetX() * this.unitScale);
                    float offsetY = f + (tile.getOffsetY() * this.unitScale);
                    float regionWidth = offsetX + (textureRegion.getRegionWidth() * this.unitScale);
                    float regionHeight = offsetY + (textureRegion.getRegionHeight() * this.unitScale);
                    float u = textureRegion.getU();
                    float v2 = textureRegion.getV2();
                    float u2 = textureRegion.getU2();
                    float v = textureRegion.getV();
                    fArr[0] = offsetX;
                    fArr[1] = offsetY;
                    fArr[2] = floatBits;
                    fArr[3] = u;
                    fArr[4] = v2;
                    fArr[5] = offsetX;
                    fArr[6] = regionHeight;
                    fArr[7] = floatBits;
                    fArr[8] = u;
                    fArr[9] = v;
                    fArr[10] = regionWidth;
                    fArr[11] = regionHeight;
                    fArr[12] = floatBits;
                    fArr[13] = u2;
                    fArr[14] = v;
                    fArr[15] = regionWidth;
                    fArr[16] = offsetY;
                    fArr[17] = floatBits;
                    fArr[18] = u2;
                    fArr[19] = v2;
                    if (flipHorizontally) {
                        float f4 = fArr[3];
                        fArr[3] = fArr[13];
                        fArr[13] = f4;
                        float f5 = fArr[8];
                        fArr[8] = fArr[18];
                        fArr[18] = f5;
                    }
                    if (flipVertically) {
                        float f6 = fArr[4];
                        fArr[4] = fArr[14];
                        fArr[14] = f6;
                        float f7 = fArr[9];
                        fArr[9] = fArr[19];
                        fArr[19] = f7;
                    }
                    if (rotation != 0) {
                        switch (rotation) {
                            case 1:
                                float f8 = fArr[4];
                                fArr[4] = fArr[9];
                                fArr[9] = fArr[14];
                                fArr[14] = fArr[19];
                                fArr[19] = f8;
                                float f9 = fArr[3];
                                fArr[3] = fArr[8];
                                fArr[8] = fArr[13];
                                fArr[13] = fArr[18];
                                fArr[18] = f9;
                                break;
                            case 2:
                                float f10 = fArr[3];
                                fArr[3] = fArr[13];
                                fArr[13] = f10;
                                float f11 = fArr[8];
                                fArr[8] = fArr[18];
                                fArr[18] = f11;
                                float f12 = fArr[4];
                                fArr[4] = fArr[14];
                                fArr[14] = f12;
                                float f13 = fArr[9];
                                fArr[9] = fArr[19];
                                fArr[19] = f13;
                                break;
                            case 3:
                                float f14 = fArr[4];
                                fArr[4] = fArr[19];
                                fArr[19] = fArr[14];
                                fArr[14] = fArr[9];
                                fArr[9] = f14;
                                float f15 = fArr[3];
                                fArr[3] = fArr[18];
                                fArr[18] = fArr[13];
                                fArr[13] = fArr[8];
                                fArr[8] = f15;
                                break;
                        }
                    }
                    this.batch.draw(textureRegion.getTexture(), fArr, 0, 20);
                }
                f3 += tileWidth;
            }
            f -= tileHeight;
        }
    }
}
