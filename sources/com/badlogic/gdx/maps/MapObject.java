package com.badlogic.gdx.maps;

import com.badlogic.gdx.graphics.Color;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/MapObject.class */
public class MapObject {
    private String name = "";
    private float opacity = 1.0f;
    private boolean visible = true;
    private MapProperties properties = new MapProperties();
    private Color color = Color.WHITE.cpy();

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getOpacity() {
        return this.opacity;
    }

    public void setOpacity(float f) {
        this.opacity = f;
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
}
