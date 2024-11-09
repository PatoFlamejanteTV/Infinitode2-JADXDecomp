package com.badlogic.gdx.maps.tiled.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.TimeUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/tiled/tiles/AnimatedTiledMapTile.class */
public class AnimatedTiledMapTile implements TiledMapTile {
    private int id;
    private TiledMapTile.BlendMode blendMode;
    private MapProperties properties;
    private MapObjects objects;
    private StaticTiledMapTile[] frameTiles;
    private int[] animationIntervals;
    private int loopDuration;
    private static long lastTiledMapRenderTime = 0;
    private static final long initialTimeOffset = TimeUtils.millis();

    @Override // com.badlogic.gdx.maps.tiled.TiledMapTile
    public int getId() {
        return this.id;
    }

    @Override // com.badlogic.gdx.maps.tiled.TiledMapTile
    public void setId(int i) {
        this.id = i;
    }

    @Override // com.badlogic.gdx.maps.tiled.TiledMapTile
    public TiledMapTile.BlendMode getBlendMode() {
        return this.blendMode;
    }

    @Override // com.badlogic.gdx.maps.tiled.TiledMapTile
    public void setBlendMode(TiledMapTile.BlendMode blendMode) {
        this.blendMode = blendMode;
    }

    public int getCurrentFrameIndex() {
        int i = (int) (lastTiledMapRenderTime % this.loopDuration);
        for (int i2 = 0; i2 < this.animationIntervals.length; i2++) {
            int i3 = this.animationIntervals[i2];
            if (i <= i3) {
                return i2;
            }
            i -= i3;
        }
        throw new GdxRuntimeException("Could not determine current animation frame in AnimatedTiledMapTile.  This should never happen.");
    }

    public TiledMapTile getCurrentFrame() {
        return this.frameTiles[getCurrentFrameIndex()];
    }

    @Override // com.badlogic.gdx.maps.tiled.TiledMapTile
    public TextureRegion getTextureRegion() {
        return getCurrentFrame().getTextureRegion();
    }

    @Override // com.badlogic.gdx.maps.tiled.TiledMapTile
    public void setTextureRegion(TextureRegion textureRegion) {
        throw new GdxRuntimeException("Cannot set the texture region of AnimatedTiledMapTile.");
    }

    @Override // com.badlogic.gdx.maps.tiled.TiledMapTile
    public float getOffsetX() {
        return getCurrentFrame().getOffsetX();
    }

    @Override // com.badlogic.gdx.maps.tiled.TiledMapTile
    public void setOffsetX(float f) {
        throw new GdxRuntimeException("Cannot set offset of AnimatedTiledMapTile.");
    }

    @Override // com.badlogic.gdx.maps.tiled.TiledMapTile
    public float getOffsetY() {
        return getCurrentFrame().getOffsetY();
    }

    @Override // com.badlogic.gdx.maps.tiled.TiledMapTile
    public void setOffsetY(float f) {
        throw new GdxRuntimeException("Cannot set offset of AnimatedTiledMapTile.");
    }

    public int[] getAnimationIntervals() {
        return this.animationIntervals;
    }

    public void setAnimationIntervals(int[] iArr) {
        if (iArr.length == this.animationIntervals.length) {
            this.animationIntervals = iArr;
            this.loopDuration = 0;
            for (int i : iArr) {
                this.loopDuration += i;
            }
            return;
        }
        throw new GdxRuntimeException("Cannot set " + iArr.length + " frame intervals. The given int[] must have a size of " + this.animationIntervals.length + ".");
    }

    @Override // com.badlogic.gdx.maps.tiled.TiledMapTile
    public MapProperties getProperties() {
        if (this.properties == null) {
            this.properties = new MapProperties();
        }
        return this.properties;
    }

    @Override // com.badlogic.gdx.maps.tiled.TiledMapTile
    public MapObjects getObjects() {
        if (this.objects == null) {
            this.objects = new MapObjects();
        }
        return this.objects;
    }

    public static void updateAnimationBaseTime() {
        lastTiledMapRenderTime = TimeUtils.millis() - initialTimeOffset;
    }

    public AnimatedTiledMapTile(float f, Array<StaticTiledMapTile> array) {
        this.blendMode = TiledMapTile.BlendMode.ALPHA;
        this.frameTiles = new StaticTiledMapTile[array.size];
        this.loopDuration = array.size * ((int) (f * 1000.0f));
        this.animationIntervals = new int[array.size];
        for (int i = 0; i < array.size; i++) {
            this.frameTiles[i] = array.get(i);
            this.animationIntervals[i] = (int) (f * 1000.0f);
        }
    }

    public AnimatedTiledMapTile(IntArray intArray, Array<StaticTiledMapTile> array) {
        this.blendMode = TiledMapTile.BlendMode.ALPHA;
        this.frameTiles = new StaticTiledMapTile[array.size];
        this.animationIntervals = intArray.toArray();
        this.loopDuration = 0;
        for (int i = 0; i < intArray.size; i++) {
            this.frameTiles[i] = array.get(i);
            this.loopDuration += intArray.get(i);
        }
    }

    public StaticTiledMapTile[] getFrameTiles() {
        return this.frameTiles;
    }
}
