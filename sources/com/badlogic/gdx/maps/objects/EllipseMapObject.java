package com.badlogic.gdx.maps.objects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Ellipse;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/objects/EllipseMapObject.class */
public class EllipseMapObject extends MapObject {
    private Ellipse ellipse;

    public Ellipse getEllipse() {
        return this.ellipse;
    }

    public EllipseMapObject() {
        this(0.0f, 0.0f, 1.0f, 1.0f);
    }

    public EllipseMapObject(float f, float f2, float f3, float f4) {
        this.ellipse = new Ellipse(f, f2, f3, f4);
    }
}
