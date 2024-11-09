package com.badlogic.gdx.maps.tiled.renderers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapGroupLayer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/tiled/renderers/BatchTiledMapRenderer.class */
public abstract class BatchTiledMapRenderer implements TiledMapRenderer, Disposable {
    protected static final int NUM_VERTICES = 20;
    protected TiledMap map;
    protected float unitScale;
    protected Batch batch;
    protected Rectangle viewBounds;
    protected Rectangle imageBounds;
    protected boolean ownsBatch;
    protected float[] vertices;

    public TiledMap getMap() {
        return this.map;
    }

    public void setMap(TiledMap tiledMap) {
        this.map = tiledMap;
    }

    public float getUnitScale() {
        return this.unitScale;
    }

    public Batch getBatch() {
        return this.batch;
    }

    public Rectangle getViewBounds() {
        return this.viewBounds;
    }

    public BatchTiledMapRenderer(TiledMap tiledMap) {
        this(tiledMap, 1.0f);
    }

    public BatchTiledMapRenderer(TiledMap tiledMap, float f) {
        this.imageBounds = new Rectangle();
        this.vertices = new float[20];
        this.map = tiledMap;
        this.unitScale = f;
        this.viewBounds = new Rectangle();
        this.batch = new SpriteBatch();
        this.ownsBatch = true;
    }

    public BatchTiledMapRenderer(TiledMap tiledMap, Batch batch) {
        this(tiledMap, 1.0f, batch);
    }

    public BatchTiledMapRenderer(TiledMap tiledMap, float f, Batch batch) {
        this.imageBounds = new Rectangle();
        this.vertices = new float[20];
        this.map = tiledMap;
        this.unitScale = f;
        this.viewBounds = new Rectangle();
        this.batch = batch;
        this.ownsBatch = false;
    }

    @Override // com.badlogic.gdx.maps.MapRenderer
    public void setView(OrthographicCamera orthographicCamera) {
        this.batch.setProjectionMatrix(orthographicCamera.combined);
        float f = orthographicCamera.viewportWidth * orthographicCamera.zoom;
        float f2 = orthographicCamera.viewportHeight * orthographicCamera.zoom;
        float abs = (f * Math.abs(orthographicCamera.up.y)) + (f2 * Math.abs(orthographicCamera.up.x));
        float abs2 = (f2 * Math.abs(orthographicCamera.up.y)) + (f * Math.abs(orthographicCamera.up.x));
        this.viewBounds.set(orthographicCamera.position.x - (abs / 2.0f), orthographicCamera.position.y - (abs2 / 2.0f), abs, abs2);
    }

    @Override // com.badlogic.gdx.maps.MapRenderer
    public void setView(Matrix4 matrix4, float f, float f2, float f3, float f4) {
        this.batch.setProjectionMatrix(matrix4);
        this.viewBounds.set(f, f2, f3, f4);
    }

    @Override // com.badlogic.gdx.maps.MapRenderer
    public void render() {
        beginRender();
        Iterator<MapLayer> it = this.map.getLayers().iterator();
        while (it.hasNext()) {
            renderMapLayer(it.next());
        }
        endRender();
    }

    @Override // com.badlogic.gdx.maps.MapRenderer
    public void render(int[] iArr) {
        beginRender();
        for (int i : iArr) {
            renderMapLayer(this.map.getLayers().get(i));
        }
        endRender();
    }

    protected void renderMapLayer(MapLayer mapLayer) {
        if (mapLayer.isVisible()) {
            if (mapLayer instanceof MapGroupLayer) {
                MapLayers layers = ((MapGroupLayer) mapLayer).getLayers();
                for (int i = 0; i < layers.size(); i++) {
                    MapLayer mapLayer2 = layers.get(i);
                    if (mapLayer2.isVisible()) {
                        renderMapLayer(mapLayer2);
                    }
                }
                return;
            }
            if (mapLayer instanceof TiledMapTileLayer) {
                renderTileLayer((TiledMapTileLayer) mapLayer);
            } else if (mapLayer instanceof TiledMapImageLayer) {
                renderImageLayer((TiledMapImageLayer) mapLayer);
            } else {
                renderObjects(mapLayer);
            }
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

    @Override // com.badlogic.gdx.maps.tiled.TiledMapRenderer
    public void renderImageLayer(TiledMapImageLayer tiledMapImageLayer) {
        Color color = this.batch.getColor();
        float floatBits = Color.toFloatBits(color.r, color.g, color.f888b, color.f889a * tiledMapImageLayer.getOpacity());
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
        this.imageBounds.set(parallaxX, parallaxY, regionWidth - parallaxX, regionHeight - parallaxY);
        if (this.viewBounds.contains(this.imageBounds) || this.viewBounds.overlaps(this.imageBounds)) {
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
            this.batch.draw(textureRegion.getTexture(), fArr, 0, 20);
        }
    }

    protected void beginRender() {
        AnimatedTiledMapTile.updateAnimationBaseTime();
        this.batch.begin();
    }

    protected void endRender() {
        this.batch.end();
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if (this.ownsBatch) {
            this.batch.dispose();
        }
    }
}
