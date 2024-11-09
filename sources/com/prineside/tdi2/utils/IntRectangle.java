package com.prineside.tdi2.utils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/IntRectangle.class */
public final class IntRectangle {
    public int minX;
    public int minY;
    public int maxX;
    public int maxY;

    public IntRectangle() {
    }

    public IntRectangle(int i, int i2, int i3, int i4) {
        this.minX = i;
        this.maxX = i2;
        this.minY = i3;
        this.maxY = i4;
    }

    public final void set(int i, int i2, int i3, int i4) {
        this.minX = i;
        this.maxX = i2;
        this.minY = i3;
        this.maxY = i4;
    }

    public final void setRect(IntRectangle intRectangle) {
        this.minX = intRectangle.minX;
        this.maxX = intRectangle.maxX;
        this.minY = intRectangle.minY;
        this.maxY = intRectangle.maxY;
    }

    public final boolean contains(int i, int i2) {
        return this.minX <= i && this.maxX >= i && this.minY <= i2 && this.maxY >= i2;
    }

    public final boolean overlapsRect(IntRectangle intRectangle) {
        return this.minX < intRectangle.maxX && this.maxX > intRectangle.minX && this.minY < intRectangle.maxY && this.maxY > intRectangle.minY;
    }

    public final boolean overlaps(int i, int i2, int i3, int i4) {
        return this.minX < i3 && this.maxX > i && this.minY < i4 && this.maxY > i2;
    }

    public final void extendToContain(int i, int i2) {
        if (i < this.minX) {
            this.minX = i;
        }
        if (i2 < this.minY) {
            this.minY = i2;
        }
        if (i > this.maxX) {
            this.maxX = i;
        }
        if (i2 > this.maxY) {
            this.maxY = i2;
        }
    }

    public final void extendToContainRect(IntRectangle intRectangle) {
        if (intRectangle.minX < this.minX) {
            this.minX = intRectangle.minX;
        }
        if (intRectangle.minY < this.minY) {
            this.minY = intRectangle.minY;
        }
        if (intRectangle.maxX > this.maxX) {
            this.maxX = intRectangle.maxX;
        }
        if (intRectangle.maxY > this.maxY) {
            this.maxY = intRectangle.maxY;
        }
    }
}
