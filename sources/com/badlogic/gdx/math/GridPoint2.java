package com.badlogic.gdx.math;

import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/GridPoint2.class */
public class GridPoint2 implements Serializable {
    private static final long serialVersionUID = -4019969926331717380L;
    public int x;
    public int y;

    public GridPoint2() {
    }

    public GridPoint2(int i, int i2) {
        this.x = i;
        this.y = i2;
    }

    public GridPoint2(GridPoint2 gridPoint2) {
        this.x = gridPoint2.x;
        this.y = gridPoint2.y;
    }

    public GridPoint2 set(GridPoint2 gridPoint2) {
        this.x = gridPoint2.x;
        this.y = gridPoint2.y;
        return this;
    }

    public GridPoint2 set(int i, int i2) {
        this.x = i;
        this.y = i2;
        return this;
    }

    public float dst2(GridPoint2 gridPoint2) {
        int i = gridPoint2.x - this.x;
        int i2 = gridPoint2.y - this.y;
        return (i * i) + (i2 * i2);
    }

    public float dst2(int i, int i2) {
        int i3 = i - this.x;
        int i4 = i2 - this.y;
        return (i3 * i3) + (i4 * i4);
    }

    public float dst(GridPoint2 gridPoint2) {
        int i = gridPoint2.x - this.x;
        int i2 = gridPoint2.y - this.y;
        return (float) Math.sqrt((i * i) + (i2 * i2));
    }

    public float dst(int i, int i2) {
        int i3 = i - this.x;
        int i4 = i2 - this.y;
        return (float) Math.sqrt((i3 * i3) + (i4 * i4));
    }

    public GridPoint2 add(GridPoint2 gridPoint2) {
        this.x += gridPoint2.x;
        this.y += gridPoint2.y;
        return this;
    }

    public GridPoint2 add(int i, int i2) {
        this.x += i;
        this.y += i2;
        return this;
    }

    public GridPoint2 sub(GridPoint2 gridPoint2) {
        this.x -= gridPoint2.x;
        this.y -= gridPoint2.y;
        return this;
    }

    public GridPoint2 sub(int i, int i2) {
        this.x -= i;
        this.y -= i2;
        return this;
    }

    public GridPoint2 cpy() {
        return new GridPoint2(this);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        GridPoint2 gridPoint2 = (GridPoint2) obj;
        return this.x == gridPoint2.x && this.y == gridPoint2.y;
    }

    public int hashCode() {
        return ((53 + this.x) * 53) + this.y;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
