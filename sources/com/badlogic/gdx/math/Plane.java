package com.badlogic.gdx.math;

import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Plane.class */
public class Plane implements Serializable {
    private static final long serialVersionUID = -1240652082930747866L;
    public final Vector3 normal;
    public float d;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Plane$PlaneSide.class */
    public enum PlaneSide {
        OnPlane,
        Back,
        Front
    }

    public Plane() {
        this.normal = new Vector3();
        this.d = 0.0f;
    }

    public Plane(Vector3 vector3, float f) {
        this.normal = new Vector3();
        this.d = 0.0f;
        this.normal.set(vector3).nor();
        this.d = f;
    }

    public Plane(Vector3 vector3, Vector3 vector32) {
        this.normal = new Vector3();
        this.d = 0.0f;
        this.normal.set(vector3).nor();
        this.d = -this.normal.dot(vector32);
    }

    public Plane(Vector3 vector3, Vector3 vector32, Vector3 vector33) {
        this.normal = new Vector3();
        this.d = 0.0f;
        set(vector3, vector32, vector33);
    }

    public void set(Vector3 vector3, Vector3 vector32, Vector3 vector33) {
        this.normal.set(vector3).sub(vector32).crs(vector32.x - vector33.x, vector32.y - vector33.y, vector32.z - vector33.z).nor();
        this.d = -vector3.dot(this.normal);
    }

    public void set(float f, float f2, float f3, float f4) {
        this.normal.set(f, f2, f3);
        this.d = f4;
    }

    public float distance(Vector3 vector3) {
        return this.normal.dot(vector3) + this.d;
    }

    public PlaneSide testPoint(Vector3 vector3) {
        float dot = this.normal.dot(vector3) + this.d;
        if (dot == 0.0f) {
            return PlaneSide.OnPlane;
        }
        if (dot < 0.0f) {
            return PlaneSide.Back;
        }
        return PlaneSide.Front;
    }

    public PlaneSide testPoint(float f, float f2, float f3) {
        float dot = this.normal.dot(f, f2, f3) + this.d;
        if (dot == 0.0f) {
            return PlaneSide.OnPlane;
        }
        if (dot < 0.0f) {
            return PlaneSide.Back;
        }
        return PlaneSide.Front;
    }

    public boolean isFrontFacing(Vector3 vector3) {
        return this.normal.dot(vector3) <= 0.0f;
    }

    public Vector3 getNormal() {
        return this.normal;
    }

    public float getD() {
        return this.d;
    }

    public void set(Vector3 vector3, Vector3 vector32) {
        this.normal.set(vector32);
        this.d = -vector3.dot(vector32);
    }

    public void set(float f, float f2, float f3, float f4, float f5, float f6) {
        this.normal.set(f4, f5, f6);
        this.d = -((f * f4) + (f2 * f5) + (f3 * f6));
    }

    public void set(Plane plane) {
        this.normal.set(plane.normal);
        this.d = plane.d;
    }

    public String toString() {
        return this.normal.toString() + ", " + this.d;
    }
}
