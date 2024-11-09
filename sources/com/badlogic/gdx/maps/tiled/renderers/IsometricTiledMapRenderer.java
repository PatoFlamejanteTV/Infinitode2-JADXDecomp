package com.badlogic.gdx.maps.tiled.renderers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/tiled/renderers/IsometricTiledMapRenderer.class */
public class IsometricTiledMapRenderer extends BatchTiledMapRenderer {
    private Matrix4 isoTransform;
    private Matrix4 invIsotransform;
    private Vector3 screenPos;
    private Vector2 topRight;
    private Vector2 bottomLeft;
    private Vector2 topLeft;
    private Vector2 bottomRight;

    public IsometricTiledMapRenderer(TiledMap tiledMap) {
        super(tiledMap);
        this.screenPos = new Vector3();
        this.topRight = new Vector2();
        this.bottomLeft = new Vector2();
        this.topLeft = new Vector2();
        this.bottomRight = new Vector2();
        init();
    }

    public IsometricTiledMapRenderer(TiledMap tiledMap, Batch batch) {
        super(tiledMap, batch);
        this.screenPos = new Vector3();
        this.topRight = new Vector2();
        this.bottomLeft = new Vector2();
        this.topLeft = new Vector2();
        this.bottomRight = new Vector2();
        init();
    }

    public IsometricTiledMapRenderer(TiledMap tiledMap, float f) {
        super(tiledMap, f);
        this.screenPos = new Vector3();
        this.topRight = new Vector2();
        this.bottomLeft = new Vector2();
        this.topLeft = new Vector2();
        this.bottomRight = new Vector2();
        init();
    }

    public IsometricTiledMapRenderer(TiledMap tiledMap, float f, Batch batch) {
        super(tiledMap, f, batch);
        this.screenPos = new Vector3();
        this.topRight = new Vector2();
        this.bottomLeft = new Vector2();
        this.topLeft = new Vector2();
        this.bottomRight = new Vector2();
        init();
    }

    private void init() {
        this.isoTransform = new Matrix4();
        this.isoTransform.idt();
        this.isoTransform.scale((float) (Math.sqrt(2.0d) / 2.0d), (float) (Math.sqrt(2.0d) / 4.0d), 1.0f);
        this.isoTransform.rotate(0.0f, 0.0f, 1.0f, -45.0f);
        this.invIsotransform = new Matrix4(this.isoTransform);
        this.invIsotransform.inv();
    }

    private Vector3 translateScreenToIso(Vector2 vector2) {
        this.screenPos.set(vector2.x, vector2.y, 0.0f);
        this.screenPos.mul(this.invIsotransform);
        return this.screenPos;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:20:0x0358. Please report as an issue. */
    @Override // com.badlogic.gdx.maps.tiled.TiledMapRenderer
    public void renderTileLayer(TiledMapTileLayer tiledMapTileLayer) {
        TiledMapTile tile;
        Color color = this.batch.getColor();
        float floatBits = Color.toFloatBits(color.r, color.g, color.f888b, color.f889a * tiledMapTileLayer.getOpacity());
        float tileWidth = tiledMapTileLayer.getTileWidth() * this.unitScale;
        float tileHeight = tiledMapTileLayer.getTileHeight() * this.unitScale;
        float renderOffsetX = (tiledMapTileLayer.getRenderOffsetX() * this.unitScale) - (this.viewBounds.x * (tiledMapTileLayer.getParallaxX() - 1.0f));
        float parallaxY = ((-tiledMapTileLayer.getRenderOffsetY()) * this.unitScale) - (this.viewBounds.y * (tiledMapTileLayer.getParallaxY() - 1.0f));
        float f = tileWidth * 0.5f;
        float f2 = tileHeight * 0.5f;
        this.topRight.set((this.viewBounds.x + this.viewBounds.width) - renderOffsetX, this.viewBounds.y - parallaxY);
        this.bottomLeft.set(this.viewBounds.x - renderOffsetX, (this.viewBounds.y + this.viewBounds.height) - parallaxY);
        this.topLeft.set(this.viewBounds.x - renderOffsetX, this.viewBounds.y - parallaxY);
        this.bottomRight.set((this.viewBounds.x + this.viewBounds.width) - renderOffsetX, (this.viewBounds.y + this.viewBounds.height) - parallaxY);
        int i = ((int) (translateScreenToIso(this.topLeft).y / tileWidth)) - 2;
        int i2 = ((int) (translateScreenToIso(this.bottomRight).y / tileWidth)) + 2;
        int i3 = ((int) (translateScreenToIso(this.bottomLeft).x / tileWidth)) - 2;
        int i4 = ((int) (translateScreenToIso(this.topRight).x / tileWidth)) + 2;
        for (int i5 = i2; i5 >= i; i5--) {
            for (int i6 = i3; i6 <= i4; i6++) {
                float f3 = (i6 * f) + (i5 * f);
                float f4 = (i5 * f2) - (i6 * f2);
                TiledMapTileLayer.Cell cell = tiledMapTileLayer.getCell(i6, i5);
                if (cell != null && (tile = cell.getTile()) != null) {
                    boolean flipHorizontally = cell.getFlipHorizontally();
                    boolean flipVertically = cell.getFlipVertically();
                    int rotation = cell.getRotation();
                    TextureRegion textureRegion = tile.getTextureRegion();
                    float offsetX = f3 + (tile.getOffsetX() * this.unitScale) + renderOffsetX;
                    float offsetY = f4 + (tile.getOffsetY() * this.unitScale) + parallaxY;
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
                        float f5 = this.vertices[3];
                        this.vertices[3] = this.vertices[13];
                        this.vertices[13] = f5;
                        float f6 = this.vertices[8];
                        this.vertices[8] = this.vertices[18];
                        this.vertices[18] = f6;
                    }
                    if (flipVertically) {
                        float f7 = this.vertices[4];
                        this.vertices[4] = this.vertices[14];
                        this.vertices[14] = f7;
                        float f8 = this.vertices[9];
                        this.vertices[9] = this.vertices[19];
                        this.vertices[19] = f8;
                    }
                    if (rotation != 0) {
                        switch (rotation) {
                            case 1:
                                float f9 = this.vertices[4];
                                this.vertices[4] = this.vertices[9];
                                this.vertices[9] = this.vertices[14];
                                this.vertices[14] = this.vertices[19];
                                this.vertices[19] = f9;
                                float f10 = this.vertices[3];
                                this.vertices[3] = this.vertices[8];
                                this.vertices[8] = this.vertices[13];
                                this.vertices[13] = this.vertices[18];
                                this.vertices[18] = f10;
                                break;
                            case 2:
                                float f11 = this.vertices[3];
                                this.vertices[3] = this.vertices[13];
                                this.vertices[13] = f11;
                                float f12 = this.vertices[8];
                                this.vertices[8] = this.vertices[18];
                                this.vertices[18] = f12;
                                float f13 = this.vertices[4];
                                this.vertices[4] = this.vertices[14];
                                this.vertices[14] = f13;
                                float f14 = this.vertices[9];
                                this.vertices[9] = this.vertices[19];
                                this.vertices[19] = f14;
                                break;
                            case 3:
                                float f15 = this.vertices[4];
                                this.vertices[4] = this.vertices[19];
                                this.vertices[19] = this.vertices[14];
                                this.vertices[14] = this.vertices[9];
                                this.vertices[9] = f15;
                                float f16 = this.vertices[3];
                                this.vertices[3] = this.vertices[18];
                                this.vertices[18] = this.vertices[13];
                                this.vertices[13] = this.vertices[8];
                                this.vertices[8] = f16;
                                break;
                        }
                    }
                    this.batch.draw(textureRegion.getTexture(), this.vertices, 0, 20);
                }
            }
        }
    }
}
