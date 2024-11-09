package com.badlogic.gdx.math;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Polyline.class */
public class Polyline implements Shape2D {
    private float[] localVertices;
    private float[] worldVertices;
    private float x;
    private float y;
    private float originX;
    private float originY;
    private float rotation;
    private float scaleX;
    private float scaleY;
    private float length;
    private float scaledLength;
    private boolean calculateScaledLength;
    private boolean calculateLength;
    private boolean dirty;
    private Rectangle bounds;

    public Polyline() {
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.calculateScaledLength = true;
        this.calculateLength = true;
        this.dirty = true;
        this.localVertices = new float[0];
    }

    public Polyline(float[] fArr) {
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.calculateScaledLength = true;
        this.calculateLength = true;
        this.dirty = true;
        if (fArr.length < 4) {
            throw new IllegalArgumentException("polylines must contain at least 2 points.");
        }
        this.localVertices = fArr;
    }

    public float[] getVertices() {
        return this.localVertices;
    }

    public float[] getTransformedVertices() {
        if (!this.dirty) {
            return this.worldVertices;
        }
        this.dirty = false;
        float[] fArr = this.localVertices;
        if (this.worldVertices == null || this.worldVertices.length < fArr.length) {
            this.worldVertices = new float[fArr.length];
        }
        float[] fArr2 = this.worldVertices;
        float f = this.x;
        float f2 = this.y;
        float f3 = this.originX;
        float f4 = this.originY;
        float f5 = this.scaleX;
        float f6 = this.scaleY;
        boolean z = (f5 == 1.0f && f6 == 1.0f) ? false : true;
        float f7 = this.rotation;
        float cosDeg = MathUtils.cosDeg(f7);
        float sinDeg = MathUtils.sinDeg(f7);
        int length = fArr.length;
        for (int i = 0; i < length; i += 2) {
            float f8 = fArr[i] - f3;
            float f9 = fArr[i + 1] - f4;
            if (z) {
                f8 *= f5;
                f9 *= f6;
            }
            if (f7 != 0.0f) {
                float f10 = f8;
                f8 = (cosDeg * f8) - (sinDeg * f9);
                f9 = (sinDeg * f10) + (cosDeg * f9);
            }
            fArr2[i] = f + f8 + f3;
            fArr2[i + 1] = f2 + f9 + f4;
        }
        return fArr2;
    }

    public float getLength() {
        if (!this.calculateLength) {
            return this.length;
        }
        this.calculateLength = false;
        this.length = 0.0f;
        int length = this.localVertices.length - 2;
        for (int i = 0; i < length; i += 2) {
            float f = this.localVertices[i + 2] - this.localVertices[i];
            float f2 = this.localVertices[i + 1] - this.localVertices[i + 3];
            this.length += (float) Math.sqrt((f * f) + (f2 * f2));
        }
        return this.length;
    }

    public float getScaledLength() {
        if (!this.calculateScaledLength) {
            return this.scaledLength;
        }
        this.calculateScaledLength = false;
        this.scaledLength = 0.0f;
        int length = this.localVertices.length - 2;
        for (int i = 0; i < length; i += 2) {
            float f = (this.localVertices[i + 2] * this.scaleX) - (this.localVertices[i] * this.scaleX);
            float f2 = (this.localVertices[i + 1] * this.scaleY) - (this.localVertices[i + 3] * this.scaleY);
            this.scaledLength += (float) Math.sqrt((f * f) + (f2 * f2));
        }
        return this.scaledLength;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getOriginX() {
        return this.originX;
    }

    public float getOriginY() {
        return this.originY;
    }

    public float getRotation() {
        return this.rotation;
    }

    public float getScaleX() {
        return this.scaleX;
    }

    public float getScaleY() {
        return this.scaleY;
    }

    public void setOrigin(float f, float f2) {
        this.originX = f;
        this.originY = f2;
        this.dirty = true;
    }

    public void setPosition(float f, float f2) {
        this.x = f;
        this.y = f2;
        this.dirty = true;
    }

    public void setVertices(float[] fArr) {
        if (fArr.length < 4) {
            throw new IllegalArgumentException("polylines must contain at least 2 points.");
        }
        this.localVertices = fArr;
        this.dirty = true;
    }

    public void setRotation(float f) {
        this.rotation = f;
        this.dirty = true;
    }

    public void rotate(float f) {
        this.rotation += f;
        this.dirty = true;
    }

    public void setScale(float f, float f2) {
        this.scaleX = f;
        this.scaleY = f2;
        this.dirty = true;
        this.calculateScaledLength = true;
    }

    public void scale(float f) {
        this.scaleX += f;
        this.scaleY += f;
        this.dirty = true;
        this.calculateScaledLength = true;
    }

    public void calculateLength() {
        this.calculateLength = true;
    }

    public void calculateScaledLength() {
        this.calculateScaledLength = true;
    }

    public void dirty() {
        this.dirty = true;
    }

    public void translate(float f, float f2) {
        this.x += f;
        this.y += f2;
        this.dirty = true;
    }

    public Rectangle getBoundingRectangle() {
        float[] transformedVertices = getTransformedVertices();
        float f = transformedVertices[0];
        float f2 = transformedVertices[1];
        float f3 = transformedVertices[0];
        float f4 = transformedVertices[1];
        int length = transformedVertices.length;
        for (int i = 2; i < length; i += 2) {
            f = f > transformedVertices[i] ? transformedVertices[i] : f;
            f2 = f2 > transformedVertices[i + 1] ? transformedVertices[i + 1] : f2;
            f3 = f3 < transformedVertices[i] ? transformedVertices[i] : f3;
            f4 = f4 < transformedVertices[i + 1] ? transformedVertices[i + 1] : f4;
        }
        if (this.bounds == null) {
            this.bounds = new Rectangle();
        }
        this.bounds.x = f;
        this.bounds.y = f2;
        this.bounds.width = f3 - f;
        this.bounds.height = f4 - f2;
        return this.bounds;
    }

    @Override // com.badlogic.gdx.math.Shape2D
    public boolean contains(Vector2 vector2) {
        return false;
    }

    @Override // com.badlogic.gdx.math.Shape2D
    public boolean contains(float f, float f2) {
        return false;
    }
}
