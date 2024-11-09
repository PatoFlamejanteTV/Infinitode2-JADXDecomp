package com.badlogic.gdx.math.collision;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.NumberUtils;
import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/collision/Sphere.class */
public class Sphere implements Serializable {
    private static final long serialVersionUID = -6487336868908521596L;
    public float radius;
    public final Vector3 center;
    private static final float PI_4_3 = 4.1887903f;

    public Sphere(Vector3 vector3, float f) {
        this.center = new Vector3(vector3);
        this.radius = f;
    }

    public boolean overlaps(Sphere sphere) {
        return this.center.dst2(sphere.center) < (this.radius + sphere.radius) * (this.radius + sphere.radius);
    }

    public int hashCode() {
        return ((71 + this.center.hashCode()) * 71) + NumberUtils.floatToRawIntBits(this.radius);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Sphere sphere = (Sphere) obj;
        return this.radius == sphere.radius && this.center.equals(sphere.center);
    }

    public float volume() {
        return PI_4_3 * this.radius * this.radius * this.radius;
    }

    public float surfaceArea() {
        return 12.566371f * this.radius * this.radius;
    }
}
