package com.badlogic.gdx.maps.tiled.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/tiled/renderers/OrthoCachedTiledMapRenderer.class */
public class OrthoCachedTiledMapRenderer implements TiledMapRenderer, Disposable {
    private static final float tolerance = 1.0E-5f;
    protected static final int NUM_VERTICES = 20;
    protected final TiledMap map;
    protected final SpriteCache spriteCache;
    protected final float[] vertices;
    protected boolean blending;
    protected float unitScale;
    protected final Rectangle viewBounds;
    protected final Rectangle cacheBounds;
    protected float overCache;
    protected float maxTileWidth;
    protected float maxTileHeight;
    protected boolean cached;
    protected int count;
    protected boolean canCacheMoreN;
    protected boolean canCacheMoreE;
    protected boolean canCacheMoreW;
    protected boolean canCacheMoreS;

    public OrthoCachedTiledMapRenderer(TiledMap tiledMap) {
        this(tiledMap, 1.0f, 2000);
    }

    public OrthoCachedTiledMapRenderer(TiledMap tiledMap, float f) {
        this(tiledMap, f, 2000);
    }

    public OrthoCachedTiledMapRenderer(TiledMap tiledMap, float f, int i) {
        this.vertices = new float[20];
        this.viewBounds = new Rectangle();
        this.cacheBounds = new Rectangle();
        this.overCache = 0.5f;
        this.map = tiledMap;
        this.unitScale = f;
        this.spriteCache = new SpriteCache(i, true);
    }

    @Override // com.badlogic.gdx.maps.MapRenderer
    public void setView(OrthographicCamera orthographicCamera) {
        this.spriteCache.setProjectionMatrix(orthographicCamera.combined);
        float f = (orthographicCamera.viewportWidth * orthographicCamera.zoom) + (this.maxTileWidth * 2.0f * this.unitScale);
        float f2 = (orthographicCamera.viewportHeight * orthographicCamera.zoom) + (this.maxTileHeight * 2.0f * this.unitScale);
        this.viewBounds.set(orthographicCamera.position.x - (f / 2.0f), orthographicCamera.position.y - (f2 / 2.0f), f, f2);
        if ((this.canCacheMoreW && this.viewBounds.x < this.cacheBounds.x - tolerance) || ((this.canCacheMoreS && this.viewBounds.y < this.cacheBounds.y - tolerance) || ((this.canCacheMoreE && this.viewBounds.x + this.viewBounds.width > this.cacheBounds.x + this.cacheBounds.width + tolerance) || (this.canCacheMoreN && this.viewBounds.y + this.viewBounds.height > this.cacheBounds.y + this.cacheBounds.height + tolerance)))) {
            this.cached = false;
        }
    }

    @Override // com.badlogic.gdx.maps.MapRenderer
    public void setView(Matrix4 matrix4, float f, float f2, float f3, float f4) {
        this.spriteCache.setProjectionMatrix(matrix4);
        this.viewBounds.set(f - (this.maxTileWidth * this.unitScale), f2 - (this.maxTileHeight * this.unitScale), f3 + (this.maxTileWidth * 2.0f * this.unitScale), f4 + (this.maxTileHeight * 2.0f * this.unitScale));
        if ((this.canCacheMoreW && this.viewBounds.x < this.cacheBounds.x - tolerance) || ((this.canCacheMoreS && this.viewBounds.y < this.cacheBounds.y - tolerance) || ((this.canCacheMoreE && this.viewBounds.x + this.viewBounds.width > this.cacheBounds.x + this.cacheBounds.width + tolerance) || (this.canCacheMoreN && this.viewBounds.y + this.viewBounds.height > this.cacheBounds.y + this.cacheBounds.height + tolerance)))) {
            this.cached = false;
        }
    }

    @Override // com.badlogic.gdx.maps.MapRenderer
    public void render() {
        if (!this.cached) {
            this.cached = true;
            this.count = 0;
            this.spriteCache.clear();
            float f = this.viewBounds.width * this.overCache;
            float f2 = this.viewBounds.height * this.overCache;
            this.cacheBounds.x = this.viewBounds.x - f;
            this.cacheBounds.y = this.viewBounds.y - f2;
            this.cacheBounds.width = this.viewBounds.width + (f * 2.0f);
            this.cacheBounds.height = this.viewBounds.height + (f2 * 2.0f);
            Iterator<MapLayer> it = this.map.getLayers().iterator();
            while (it.hasNext()) {
                MapLayer next = it.next();
                this.spriteCache.beginCache();
                if (next instanceof TiledMapTileLayer) {
                    renderTileLayer((TiledMapTileLayer) next);
                } else if (next instanceof TiledMapImageLayer) {
                    renderImageLayer((TiledMapImageLayer) next);
                }
                this.spriteCache.endCache();
            }
        }
        if (this.blending) {
            Gdx.gl.glEnable(3042);
            Gdx.gl.glBlendFunc(770, 771);
        }
        this.spriteCache.begin();
        MapLayers layers = this.map.getLayers();
        int count = layers.getCount();
        for (int i = 0; i < count; i++) {
            MapLayer mapLayer = layers.get(i);
            if (mapLayer.isVisible()) {
                this.spriteCache.draw(i);
                renderObjects(mapLayer);
            }
        }
        this.spriteCache.end();
        if (this.blending) {
            Gdx.gl.glDisable(3042);
        }
    }

    @Override // com.badlogic.gdx.maps.MapRenderer
    public void render(int[] iArr) {
        if (!this.cached) {
            this.cached = true;
            this.count = 0;
            this.spriteCache.clear();
            float f = this.viewBounds.width * this.overCache;
            float f2 = this.viewBounds.height * this.overCache;
            this.cacheBounds.x = this.viewBounds.x - f;
            this.cacheBounds.y = this.viewBounds.y - f2;
            this.cacheBounds.width = this.viewBounds.width + (f * 2.0f);
            this.cacheBounds.height = this.viewBounds.height + (f2 * 2.0f);
            Iterator<MapLayer> it = this.map.getLayers().iterator();
            while (it.hasNext()) {
                MapLayer next = it.next();
                this.spriteCache.beginCache();
                if (next instanceof TiledMapTileLayer) {
                    renderTileLayer((TiledMapTileLayer) next);
                } else if (next instanceof TiledMapImageLayer) {
                    renderImageLayer((TiledMapImageLayer) next);
                }
                this.spriteCache.endCache();
            }
        }
        if (this.blending) {
            Gdx.gl.glEnable(3042);
            Gdx.gl.glBlendFunc(770, 771);
        }
        this.spriteCache.begin();
        MapLayers layers = this.map.getLayers();
        for (int i : iArr) {
            MapLayer mapLayer = layers.get(i);
            if (mapLayer.isVisible()) {
                this.spriteCache.draw(i);
                renderObjects(mapLayer);
            }
        }
        this.spriteCache.end();
        if (this.blending) {
            Gdx.gl.glDisable(3042);
        }
    }

    @Override // com.badlogic.gdx.maps.tiled.TiledMapRenderer
    public void renderObjects(MapLayer mapLayer) {
        Iterator<MapObject> it = mapLayer.getObjects().iterator();
        while (it.hasNext()) {
            renderObject(it.next());
        }
    }

    @Override // com.badlogic.gdx.maps.tiled.TiledMapRenderer
    public void renderObject(MapObject mapObject) {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.badlogic.gdx.maps.tiled.TiledMapRenderer
    public void renderTileLayer(TiledMapTileLayer tiledMapTileLayer) {
        TiledMapTile tile;
        float floatBits = Color.toFloatBits(1.0f, 1.0f, 1.0f, tiledMapTileLayer.getOpacity());
        int width = tiledMapTileLayer.getWidth();
        int height = tiledMapTileLayer.getHeight();
        float tileWidth = tiledMapTileLayer.getTileWidth() * this.unitScale;
        float tileHeight = tiledMapTileLayer.getTileHeight() * this.unitScale;
        float renderOffsetX = (tiledMapTileLayer.getRenderOffsetX() * this.unitScale) - (this.viewBounds.x * (tiledMapTileLayer.getParallaxX() - 1.0f));
        float parallaxY = ((-tiledMapTileLayer.getRenderOffsetY()) * this.unitScale) - (this.viewBounds.y * (tiledMapTileLayer.getParallaxY() - 1.0f));
        int max = Math.max(0, (int) ((this.cacheBounds.x - renderOffsetX) / tileWidth));
        int min = Math.min(width, (int) ((((this.cacheBounds.x + this.cacheBounds.width) + tileWidth) - renderOffsetX) / tileWidth));
        int max2 = Math.max(0, (int) ((this.cacheBounds.y - parallaxY) / tileHeight));
        int min2 = Math.min(height, (int) ((((this.cacheBounds.y + this.cacheBounds.height) + tileHeight) - parallaxY) / tileHeight));
        this.canCacheMoreN = min2 < height;
        this.canCacheMoreE = min < width;
        this.canCacheMoreW = max > 0;
        this.canCacheMoreS = max2 > 0;
        float[] fArr = this.vertices;
        for (int i = min2; i >= max2; i--) {
            for (int i2 = max; i2 < min; i2++) {
                TiledMapTileLayer.Cell cell = tiledMapTileLayer.getCell(i2, i);
                if (cell != null && (tile = cell.getTile()) != null) {
                    this.count++;
                    boolean flipHorizontally = cell.getFlipHorizontally();
                    boolean flipVertically = cell.getFlipVertically();
                    int rotation = cell.getRotation();
                    TextureRegion textureRegion = tile.getTextureRegion();
                    Texture texture = textureRegion.getTexture();
                    float offsetX = (i2 * tileWidth) + (tile.getOffsetX() * this.unitScale) + renderOffsetX;
                    float offsetY = (i * tileHeight) + (tile.getOffsetY() * this.unitScale) + parallaxY;
                    float regionWidth = offsetX + (textureRegion.getRegionWidth() * this.unitScale);
                    float regionHeight = offsetY + (textureRegion.getRegionHeight() * this.unitScale);
                    float width2 = 0.5f / texture.getWidth();
                    float height2 = 0.5f / texture.getHeight();
                    float u = textureRegion.getU() + width2;
                    float v2 = textureRegion.getV2() - height2;
                    float u2 = textureRegion.getU2() - width2;
                    float v = textureRegion.getV() + height2;
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
                        float f = fArr[3];
                        fArr[3] = fArr[13];
                        fArr[13] = f;
                        float f2 = fArr[8];
                        fArr[8] = fArr[18];
                        fArr[18] = f2;
                    }
                    if (flipVertically) {
                        float f3 = fArr[4];
                        fArr[4] = fArr[14];
                        fArr[14] = f3;
                        float f4 = fArr[9];
                        fArr[9] = fArr[19];
                        fArr[19] = f4;
                    }
                    if (rotation != 0) {
                        switch (rotation) {
                            case 1:
                                float f5 = fArr[4];
                                fArr[4] = fArr[9];
                                fArr[9] = fArr[14];
                                fArr[14] = fArr[19];
                                fArr[19] = f5;
                                float f6 = fArr[3];
                                fArr[3] = fArr[8];
                                fArr[8] = fArr[13];
                                fArr[13] = fArr[18];
                                fArr[18] = f6;
                                break;
                            case 2:
                                float f7 = fArr[3];
                                fArr[3] = fArr[13];
                                fArr[13] = f7;
                                float f8 = fArr[8];
                                fArr[8] = fArr[18];
                                fArr[18] = f8;
                                float f9 = fArr[4];
                                fArr[4] = fArr[14];
                                fArr[14] = f9;
                                float f10 = fArr[9];
                                fArr[9] = fArr[19];
                                fArr[19] = f10;
                                break;
                            case 3:
                                float f11 = fArr[4];
                                fArr[4] = fArr[19];
                                fArr[19] = fArr[14];
                                fArr[14] = fArr[9];
                                fArr[9] = f11;
                                float f12 = fArr[3];
                                fArr[3] = fArr[18];
                                fArr[18] = fArr[13];
                                fArr[13] = fArr[8];
                                fArr[8] = f12;
                                break;
                        }
                    }
                    this.spriteCache.add(texture, fArr, 0, 20);
                }
            }
        }
    }

    @Override // com.badlogic.gdx.maps.tiled.TiledMapRenderer
    public void renderImageLayer(TiledMapImageLayer tiledMapImageLayer) {
        float floatBits = Color.toFloatBits(1.0f, 1.0f, 1.0f, tiledMapImageLayer.getOpacity());
        float[] fArr = this.vertices;
        TextureRegion textureRegion = tiledMapImageLayer.getTextureRegion();
        if (textureRegion == null) {
            return;
        }
        float x = tiledMapImageLayer.getX();
        float y = tiledMapImageLayer.getY();
        float parallaxX = (x * this.unitScale) - (this.viewBounds.x * (tiledMapImageLayer.getParallaxX() - 1.0f));
        float parallaxY = (y * this.unitScale) - (this.viewBounds.y * (tiledMapImageLayer.getParallaxY() - 1.0f));
        float regionWidth = parallaxX + (textureRegion.getRegionWidth() * this.unitScale);
        float regionHeight = parallaxY + (textureRegion.getRegionHeight() * this.unitScale);
        float u = textureRegion.getU();
        float v2 = textureRegion.getV2();
        float u2 = textureRegion.getU2();
        float v = textureRegion.getV();
        fArr[0] = parallaxX;
        fArr[1] = parallaxY;
        fArr[2] = floatBits;
        fArr[3] = u;
        fArr[4] = v2;
        fArr[5] = parallaxX;
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
        fArr[16] = parallaxY;
        fArr[17] = floatBits;
        fArr[18] = u2;
        fArr[19] = v2;
        this.spriteCache.add(textureRegion.getTexture(), fArr, 0, 20);
    }

    public void invalidateCache() {
        this.cached = false;
    }

    public boolean isCached() {
        return this.cached;
    }

    public void setOverCache(float f) {
        this.overCache = f;
    }

    public void setMaxTileSize(float f, float f2) {
        this.maxTileWidth = f;
        this.maxTileHeight = f2;
    }

    public void setBlending(boolean z) {
        this.blending = z;
    }

    public SpriteCache getSpriteCache() {
        return this.spriteCache;
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.spriteCache.dispose();
    }
}
