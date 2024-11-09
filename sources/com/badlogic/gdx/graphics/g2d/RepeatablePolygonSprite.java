package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/RepeatablePolygonSprite.class */
public class RepeatablePolygonSprite {
    private TextureRegion region;
    private float density;
    private int cols;
    private int rows;
    private float gridWidth;
    private float gridHeight;
    private boolean dirty = true;
    private Array<float[]> parts = new Array<>();
    private Array<float[]> vertices = new Array<>();
    private Array<short[]> indices = new Array<>();
    public float x = 0.0f;
    public float y = 0.0f;
    private Color color = Color.WHITE;
    private Vector2 offset = new Vector2();

    public void setPolygon(TextureRegion textureRegion, float[] fArr) {
        setPolygon(textureRegion, fArr, -1.0f);
    }

    public void setPolygon(TextureRegion textureRegion, float[] fArr, float f) {
        this.region = textureRegion;
        Polygon polygon = new Polygon(offset(fArr));
        Polygon polygon2 = new Polygon();
        Polygon polygon3 = new Polygon();
        EarClippingTriangulator earClippingTriangulator = new EarClippingTriangulator();
        Rectangle boundingRectangle = polygon.getBoundingRectangle();
        if (f == -1.0f) {
            f = boundingRectangle.getWidth() / textureRegion.getRegionWidth();
        }
        this.cols = (int) Math.ceil(f);
        this.gridWidth = boundingRectangle.getWidth() / f;
        this.gridHeight = (textureRegion.getRegionHeight() / textureRegion.getRegionWidth()) * this.gridWidth;
        this.rows = (int) Math.ceil(boundingRectangle.getHeight() / this.gridHeight);
        for (int i = 0; i < this.cols; i++) {
            for (int i2 = 0; i2 < this.rows; i2++) {
                polygon2.setVertices(new float[]{i * this.gridWidth, i2 * this.gridHeight, i * this.gridWidth, (i2 + 1) * this.gridHeight, (i + 1) * this.gridWidth, (i2 + 1) * this.gridHeight, (i + 1) * this.gridWidth, i2 * this.gridHeight});
                Intersector.intersectPolygons(polygon, polygon2, polygon3);
                float[] vertices = polygon3.getVertices();
                if (vertices.length > 0) {
                    this.parts.add(snapToGrid(vertices));
                    this.indices.add(earClippingTriangulator.computeTriangles(vertices).toArray());
                } else {
                    this.parts.add(null);
                }
            }
        }
        buildVertices();
    }

    private float[] snapToGrid(float[] fArr) {
        for (int i = 0; i < fArr.length; i += 2) {
            float f = (fArr[i] / this.gridWidth) % 1.0f;
            float f2 = (fArr[i + 1] / this.gridHeight) % 1.0f;
            if (f > 0.99f || f < 0.01f) {
                fArr[i] = this.gridWidth * Math.round(fArr[i] / this.gridWidth);
            }
            if (f2 > 0.99f || f2 < 0.01f) {
                fArr[i + 1] = this.gridHeight * Math.round(fArr[i + 1] / this.gridHeight);
            }
        }
        return fArr;
    }

    private float[] offset(float[] fArr) {
        this.offset.set(fArr[0], fArr[1]);
        for (int i = 0; i < fArr.length - 1; i += 2) {
            if (this.offset.x > fArr[i]) {
                this.offset.x = fArr[i];
            }
            if (this.offset.y > fArr[i + 1]) {
                this.offset.y = fArr[i + 1];
            }
        }
        for (int i2 = 0; i2 < fArr.length; i2 += 2) {
            int i3 = i2;
            fArr[i3] = fArr[i3] - this.offset.x;
            int i4 = i2 + 1;
            fArr[i4] = fArr[i4] - this.offset.y;
        }
        return fArr;
    }

    private void buildVertices() {
        this.vertices.clear();
        for (int i = 0; i < this.parts.size; i++) {
            float[] fArr = this.parts.get(i);
            if (fArr != null) {
                float[] fArr2 = new float[(5 * fArr.length) / 2];
                int i2 = 0;
                int i3 = i / this.rows;
                int i4 = i % this.rows;
                for (int i5 = 0; i5 < fArr.length; i5 += 2) {
                    int i6 = i2;
                    int i7 = i2 + 1;
                    fArr2[i6] = fArr[i5] + this.offset.x + this.x;
                    int i8 = i7 + 1;
                    fArr2[i7] = fArr[i5 + 1] + this.offset.y + this.y;
                    int i9 = i8 + 1;
                    fArr2[i8] = this.color.toFloatBits();
                    float f = (fArr[i5] % this.gridWidth) / this.gridWidth;
                    float f2 = (fArr[i5 + 1] % this.gridHeight) / this.gridHeight;
                    if (fArr[i5] == i3 * this.gridWidth) {
                        f = 0.0f;
                    }
                    if (fArr[i5] == (i3 + 1) * this.gridWidth) {
                        f = 1.0f;
                    }
                    if (fArr[i5 + 1] == i4 * this.gridHeight) {
                        f2 = 0.0f;
                    }
                    if (fArr[i5 + 1] == (i4 + 1) * this.gridHeight) {
                        f2 = 1.0f;
                    }
                    float u = this.region.getU() + ((this.region.getU2() - this.region.getU()) * f);
                    float v = this.region.getV() + ((this.region.getV2() - this.region.getV()) * f2);
                    int i10 = i9 + 1;
                    fArr2[i9] = u;
                    i2 = i10 + 1;
                    fArr2[i10] = v;
                }
                this.vertices.add(fArr2);
            }
        }
        this.dirty = false;
    }

    public void draw(PolygonSpriteBatch polygonSpriteBatch) {
        if (this.dirty) {
            buildVertices();
        }
        for (int i = 0; i < this.vertices.size; i++) {
            polygonSpriteBatch.draw(this.region.getTexture(), this.vertices.get(i), 0, this.vertices.get(i).length, this.indices.get(i), 0, this.indices.get(i).length);
        }
    }

    public void setColor(Color color) {
        this.color = color;
        this.dirty = true;
    }

    public void setPosition(float f, float f2) {
        this.x = f;
        this.y = f2;
        this.dirty = true;
    }
}
