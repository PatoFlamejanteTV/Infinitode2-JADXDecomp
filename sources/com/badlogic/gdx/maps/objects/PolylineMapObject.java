package com.badlogic.gdx.maps.objects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Polyline;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/objects/PolylineMapObject.class */
public class PolylineMapObject extends MapObject {
    private Polyline polyline;

    public Polyline getPolyline() {
        return this.polyline;
    }

    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;
    }

    public PolylineMapObject() {
        this(new float[0]);
    }

    public PolylineMapObject(float[] fArr) {
        this.polyline = new Polyline(fArr);
    }

    public PolylineMapObject(Polyline polyline) {
        this.polyline = polyline;
    }
}
