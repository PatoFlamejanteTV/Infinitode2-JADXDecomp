package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.scene2d.ui.Widget;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/QuadActor.class */
public class QuadActor extends Widget {
    private float[] j = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    private int[] k = {255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255};
    private float[] l = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    private TextureRegion m;

    public QuadActor(float[] fArr, int[] iArr) {
        setVertices(fArr);
        setVertexColorsDirect(iArr);
    }

    public QuadActor(float[] fArr, Color color) {
        setVertices(fArr);
        setVertexColorsSingle(color);
    }

    public QuadActor(Color color, float[] fArr) {
        setVertexPositions(fArr);
        setVertexColorsSingle(color);
    }

    public QuadActor(Color[] colorArr, float[] fArr) {
        setVertexPositions(fArr);
        setVertexColors(colorArr[0], colorArr[1], colorArr[2], colorArr[3]);
    }

    public QuadActor(float[] fArr, Color color, Color color2, Color color3, Color color4) {
        setVertices(fArr);
        setVertexColors(color, color2, color3, color4);
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.m = textureRegion;
        setVertices(this.j);
    }

    public TextureRegion getTextureRegion() {
        if (this.m == null) {
            setTextureRegion(Game.i.assetManager.getBlankWhiteTextureRegion());
        }
        return this.m;
    }

    public float[] getVertexPositions() {
        return this.j;
    }

    public void setVertexPositions(float[] fArr) {
        float max = StrictMath.max(fArr[4], fArr[6]);
        float max2 = StrictMath.max(fArr[3], fArr[5]);
        setWidth(max);
        setHeight(max2);
        float[] fArr2 = new float[8];
        for (int i = 0; i < 4; i++) {
            fArr2[i << 1] = fArr[i << 1] / max;
            fArr2[(i << 1) + 1] = fArr[(i << 1) + 1] / max2;
        }
        setVertices(fArr2);
    }

    public void setVertices(float[] fArr) {
        if (fArr.length != 8) {
            throw new RuntimeException("vertices must be an array of 8 floats, " + fArr.length + " passed");
        }
        System.arraycopy(fArr, 0, this.j, 0, this.j.length);
        TextureRegion textureRegion = getTextureRegion();
        float u = textureRegion.getU();
        float v = textureRegion.getV();
        float u2 = textureRegion.getU2();
        float v2 = textureRegion.getV2();
        this.l[3] = u;
        this.l[4] = v2;
        this.l[8] = u;
        this.l[9] = v;
        this.l[13] = u2;
        this.l[14] = v;
        this.l[18] = u2;
        this.l[19] = v2;
    }

    public void setVertexColorsDirect(int[] iArr) {
        if (iArr.length != 16) {
            throw new RuntimeException("colors must be an array of 16 ints, " + iArr.length + " passed");
        }
        System.arraycopy(iArr, 0, this.k, 0, this.k.length);
    }

    public void setVertexColors(Color color, Color color2, Color color3, Color color4) {
        this.k[0] = (int) (color.r * 255.0f);
        this.k[1] = (int) (color.g * 255.0f);
        this.k[2] = (int) (color.f888b * 255.0f);
        this.k[3] = (int) (color.f889a * 255.0f);
        this.k[4] = (int) (color2.r * 255.0f);
        this.k[5] = (int) (color2.g * 255.0f);
        this.k[6] = (int) (color2.f888b * 255.0f);
        this.k[7] = (int) (color2.f889a * 255.0f);
        this.k[8] = (int) (color3.r * 255.0f);
        this.k[9] = (int) (color3.g * 255.0f);
        this.k[10] = (int) (color3.f888b * 255.0f);
        this.k[11] = (int) (color3.f889a * 255.0f);
        this.k[12] = (int) (color4.r * 255.0f);
        this.k[13] = (int) (color4.g * 255.0f);
        this.k[14] = (int) (color4.f888b * 255.0f);
        this.k[15] = (int) (color4.f889a * 255.0f);
    }

    public void setVertexColorsSingle(Color color) {
        setVertexColors(color, color, color, color);
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        super.validate();
        TextureRegion textureRegion = getTextureRegion();
        float x = getX();
        float y = getY();
        float width = getWidth();
        float height = getHeight();
        Color color = getColor();
        for (int i = 0; i < 4; i++) {
            this.l[i * 5] = x + (width * this.j[i << 1]);
            this.l[(i * 5) + 1] = y + (height * this.j[(i << 1) + 1]);
            this.l[(i * 5) + 2] = Color.toFloatBits((int) (this.k[i << 2] * color.r), (int) (this.k[(i << 2) + 1] * color.g), (int) (this.k[(i << 2) + 2] * color.f888b), (int) (this.k[(i << 2) + 3] * f * color.f889a));
        }
        batch.draw(textureRegion.getTexture(), this.l, 0, 20);
    }
}
