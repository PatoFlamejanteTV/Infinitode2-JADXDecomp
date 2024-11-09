package com.badlogic.gdx.graphics.g2d;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/PolygonRegion.class */
public class PolygonRegion {
    final float[] textureCoords;
    final float[] vertices;
    final short[] triangles;
    final TextureRegion region;

    public PolygonRegion(TextureRegion textureRegion, float[] fArr, short[] sArr) {
        this.region = textureRegion;
        this.vertices = fArr;
        this.triangles = sArr;
        float[] fArr2 = new float[fArr.length];
        this.textureCoords = fArr2;
        float f = textureRegion.u;
        float f2 = textureRegion.v;
        float f3 = textureRegion.u2 - f;
        float f4 = textureRegion.v2 - f2;
        int i = textureRegion.regionWidth;
        int i2 = textureRegion.regionHeight;
        int length = fArr.length;
        for (int i3 = 0; i3 < length; i3 += 2) {
            fArr2[i3] = f + (f3 * (fArr[i3] / i));
            fArr2[i3 + 1] = f2 + (f4 * (1.0f - (fArr[i3 + 1] / i2)));
        }
    }

    public float[] getVertices() {
        return this.vertices;
    }

    public short[] getTriangles() {
        return this.triangles;
    }

    public float[] getTextureCoords() {
        return this.textureCoords;
    }

    public TextureRegion getRegion() {
        return this.region;
    }
}
