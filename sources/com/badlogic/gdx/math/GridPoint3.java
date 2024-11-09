package com.badlogic.gdx.math;

import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/GridPoint3.class */
public class GridPoint3 implements Serializable {
    private static final long serialVersionUID = 5922187982746752830L;
    public int x;
    public int y;
    public int z;

    public GridPoint3() {
    }

    public GridPoint3(int i, int i2, int i3) {
        this.x = i;
        this.y = i2;
        this.z = i3;
    }

    public GridPoint3(GridPoint3 gridPoint3) {
        this.x = gridPoint3.x;
        this.y = gridPoint3.y;
        this.z = gridPoint3.z;
    }

    public GridPoint3 set(GridPoint3 gridPoint3) {
        this.x = gridPoint3.x;
        this.y = gridPoint3.y;
        this.z = gridPoint3.z;
        return this;
    }

    public GridPoint3 set(int i, int i2, int i3) {
        this.x = i;
        this.y = i2;
        this.z = i3;
        return this;
    }

    public float dst2(GridPoint3 gridPoint3) {
        int i = gridPoint3.x - this.x;
        int i2 = gridPoint3.y - this.y;
        int i3 = gridPoint3.z - this.z;
        return (i * i) + (i2 * i2) + (i3 * i3);
    }

    public float dst2(int i, int i2, int i3) {
        int i4 = i - this.x;
        int i5 = i2 - this.y;
        int i6 = i3 - this.z;
        return (i4 * i4) + (i5 * i5) + (i6 * i6);
    }

    public float dst(GridPoint3 gridPoint3) {
        int i = gridPoint3.x - this.x;
        int i2 = gridPoint3.y - this.y;
        int i3 = gridPoint3.z - this.z;
        return (float) Math.sqrt((i * i) + (i2 * i2) + (i3 * i3));
    }

    public float dst(int i, int i2, int i3) {
        int i4 = i - this.x;
        int i5 = i2 - this.y;
        int i6 = i3 - this.z;
        return (float) Math.sqrt((i4 * i4) + (i5 * i5) + (i6 * i6));
    }

    public GridPoint3 add(GridPoint3 gridPoint3) {
        this.x += gridPoint3.x;
        this.y += gridPoint3.y;
        this.z += gridPoint3.z;
        return this;
    }

    public GridPoint3 add(int i, int i2, int i3) {
        this.x += i;
        this.y += i2;
        this.z += i3;
        return this;
    }

    public GridPoint3 sub(GridPoint3 gridPoint3) {
        this.x -= gridPoint3.x;
        this.y -= gridPoint3.y;
        this.z -= gridPoint3.z;
        return this;
    }

    public GridPoint3 sub(int i, int i2, int i3) {
        this.x -= i;
        this.y -= i2;
        this.z -= i3;
        return this;
    }

    public GridPoint3 cpy() {
        return new GridPoint3(this);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        GridPoint3 gridPoint3 = (GridPoint3) obj;
        return this.x == gridPoint3.x && this.y == gridPoint3.y && this.z == gridPoint3.z;
    }

    public int hashCode() {
        return ((((17 + this.x) * 17) + this.y) * 17) + this.z;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }
}
