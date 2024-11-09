package com.badlogic.gdx.math.collision;

import com.badlogic.gdx.math.Vector3;
import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/collision/Segment.class */
public class Segment implements Serializable {
    private static final long serialVersionUID = 2739667069736519602L;

    /* renamed from: a, reason: collision with root package name */
    public final Vector3 f890a = new Vector3();

    /* renamed from: b, reason: collision with root package name */
    public final Vector3 f891b = new Vector3();

    public Segment(Vector3 vector3, Vector3 vector32) {
        this.f890a.set(vector3);
        this.f891b.set(vector32);
    }

    public Segment(float f, float f2, float f3, float f4, float f5, float f6) {
        this.f890a.set(f, f2, f3);
        this.f891b.set(f4, f5, f6);
    }

    public float len() {
        return this.f890a.dst(this.f891b);
    }

    public float len2() {
        return this.f890a.dst2(this.f891b);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Segment segment = (Segment) obj;
        return this.f890a.equals(segment.f890a) && this.f891b.equals(segment.f891b);
    }

    public int hashCode() {
        return ((71 + this.f890a.hashCode()) * 71) + this.f891b.hashCode();
    }
}
