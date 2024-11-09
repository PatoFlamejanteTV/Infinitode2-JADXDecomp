package com.badlogic.gdx.maps;

import com.badlogic.gdx.utils.GdxRuntimeException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/MapLayer.class */
public class MapLayer {
    private float offsetX;
    private float offsetY;
    private float renderOffsetX;
    private float renderOffsetY;
    private MapLayer parent;
    private String name = "";
    private float opacity = 1.0f;
    private boolean visible = true;
    private float parallaxX = 1.0f;
    private float parallaxY = 1.0f;
    private boolean renderOffsetDirty = true;
    private MapObjects objects = new MapObjects();
    private MapProperties properties = new MapProperties();

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public float getOpacity() {
        return this.opacity;
    }

    public void setOpacity(float f) {
        this.opacity = f;
    }

    public float getOffsetX() {
        return this.offsetX;
    }

    public void setOffsetX(float f) {
        this.offsetX = f;
        invalidateRenderOffset();
    }

    public float getOffsetY() {
        return this.offsetY;
    }

    public void setOffsetY(float f) {
        this.offsetY = f;
        invalidateRenderOffset();
    }

    public float getParallaxX() {
        return this.parallaxX;
    }

    public void setParallaxX(float f) {
        this.parallaxX = f;
    }

    public float getParallaxY() {
        return this.parallaxY;
    }

    public void setParallaxY(float f) {
        this.parallaxY = f;
    }

    public float getRenderOffsetX() {
        if (this.renderOffsetDirty) {
            calculateRenderOffsets();
        }
        return this.renderOffsetX;
    }

    public float getRenderOffsetY() {
        if (this.renderOffsetDirty) {
            calculateRenderOffsets();
        }
        return this.renderOffsetY;
    }

    public void invalidateRenderOffset() {
        this.renderOffsetDirty = true;
    }

    public MapLayer getParent() {
        return this.parent;
    }

    public void setParent(MapLayer mapLayer) {
        if (mapLayer == this) {
            throw new GdxRuntimeException("Can't set self as the parent");
        }
        this.parent = mapLayer;
    }

    public MapObjects getObjects() {
        return this.objects;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setVisible(boolean z) {
        this.visible = z;
    }

    public MapProperties getProperties() {
        return this.properties;
    }

    protected void calculateRenderOffsets() {
        if (this.parent != null) {
            this.parent.calculateRenderOffsets();
            this.renderOffsetX = this.parent.getRenderOffsetX() + this.offsetX;
            this.renderOffsetY = this.parent.getRenderOffsetY() + this.offsetY;
        } else {
            this.renderOffsetX = this.offsetX;
            this.renderOffsetY = this.offsetY;
        }
        this.renderOffsetDirty = false;
    }
}
