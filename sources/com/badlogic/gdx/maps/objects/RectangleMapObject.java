package com.badlogic.gdx.maps.objects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/objects/RectangleMapObject.class */
public class RectangleMapObject extends MapObject {
    private Rectangle rectangle;

    public Rectangle getRectangle() {
        return this.rectangle;
    }

    public RectangleMapObject() {
        this(0.0f, 0.0f, 1.0f, 1.0f);
    }

    public RectangleMapObject(float f, float f2, float f3, float f4) {
        this.rectangle = new Rectangle(f, f2, f3, f4);
    }
}
