package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/NinePatch.class */
public class NinePatch {
    public static final int TOP_LEFT = 0;
    public static final int TOP_CENTER = 1;
    public static final int TOP_RIGHT = 2;
    public static final int MIDDLE_LEFT = 3;
    public static final int MIDDLE_CENTER = 4;
    public static final int MIDDLE_RIGHT = 5;
    public static final int BOTTOM_LEFT = 6;
    public static final int BOTTOM_CENTER = 7;
    public static final int BOTTOM_RIGHT = 8;
    private static final Color tmpDrawColor = new Color();
    private Texture texture;
    private int bottomLeft;
    private int bottomCenter;
    private int bottomRight;
    private int middleLeft;
    private int middleCenter;
    private int middleRight;
    private int topLeft;
    private int topCenter;
    private int topRight;
    private float leftWidth;
    private float rightWidth;
    private float middleWidth;
    private float middleHeight;
    private float topHeight;
    private float bottomHeight;
    private float[] vertices;
    private int idx;
    private final Color color;
    private float padLeft;
    private float padRight;
    private float padTop;
    private float padBottom;

    public NinePatch(Texture texture, int i, int i2, int i3, int i4) {
        this(new TextureRegion(texture), i, i2, i3, i4);
    }

    public NinePatch(TextureRegion textureRegion, int i, int i2, int i3, int i4) {
        this.vertices = new float[180];
        this.color = new Color(Color.WHITE);
        this.padLeft = -1.0f;
        this.padRight = -1.0f;
        this.padTop = -1.0f;
        this.padBottom = -1.0f;
        if (textureRegion == null) {
            throw new IllegalArgumentException("region cannot be null.");
        }
        int regionWidth = (textureRegion.getRegionWidth() - i) - i2;
        int regionHeight = (textureRegion.getRegionHeight() - i3) - i4;
        TextureRegion[] textureRegionArr = new TextureRegion[9];
        if (i3 > 0) {
            if (i > 0) {
                textureRegionArr[0] = new TextureRegion(textureRegion, 0, 0, i, i3);
            }
            if (regionWidth > 0) {
                textureRegionArr[1] = new TextureRegion(textureRegion, i, 0, regionWidth, i3);
            }
            if (i2 > 0) {
                textureRegionArr[2] = new TextureRegion(textureRegion, i + regionWidth, 0, i2, i3);
            }
        }
        if (regionHeight > 0) {
            if (i > 0) {
                textureRegionArr[3] = new TextureRegion(textureRegion, 0, i3, i, regionHeight);
            }
            if (regionWidth > 0) {
                textureRegionArr[4] = new TextureRegion(textureRegion, i, i3, regionWidth, regionHeight);
            }
            if (i2 > 0) {
                textureRegionArr[5] = new TextureRegion(textureRegion, i + regionWidth, i3, i2, regionHeight);
            }
        }
        if (i4 > 0) {
            if (i > 0) {
                textureRegionArr[6] = new TextureRegion(textureRegion, 0, i3 + regionHeight, i, i4);
            }
            if (regionWidth > 0) {
                textureRegionArr[7] = new TextureRegion(textureRegion, i, i3 + regionHeight, regionWidth, i4);
            }
            if (i2 > 0) {
                textureRegionArr[8] = new TextureRegion(textureRegion, i + regionWidth, i3 + regionHeight, i2, i4);
            }
        }
        if (i == 0 && regionWidth == 0) {
            textureRegionArr[1] = textureRegionArr[2];
            textureRegionArr[4] = textureRegionArr[5];
            textureRegionArr[7] = textureRegionArr[8];
            textureRegionArr[2] = null;
            textureRegionArr[5] = null;
            textureRegionArr[8] = null;
        }
        if (i3 == 0 && regionHeight == 0) {
            textureRegionArr[3] = textureRegionArr[6];
            textureRegionArr[4] = textureRegionArr[7];
            textureRegionArr[5] = textureRegionArr[8];
            textureRegionArr[6] = null;
            textureRegionArr[7] = null;
            textureRegionArr[8] = null;
        }
        load(textureRegionArr);
    }

    public NinePatch(Texture texture, Color color) {
        this(texture);
        setColor(color);
    }

    public NinePatch(Texture texture) {
        this(new TextureRegion(texture));
    }

    public NinePatch(TextureRegion textureRegion, Color color) {
        this(textureRegion);
        setColor(color);
    }

    public NinePatch(TextureRegion textureRegion) {
        this.vertices = new float[180];
        this.color = new Color(Color.WHITE);
        this.padLeft = -1.0f;
        this.padRight = -1.0f;
        this.padTop = -1.0f;
        this.padBottom = -1.0f;
        load(new TextureRegion[]{null, null, null, null, textureRegion, null, null, null, null});
    }

    public NinePatch(TextureRegion... textureRegionArr) {
        this.vertices = new float[180];
        this.color = new Color(Color.WHITE);
        this.padLeft = -1.0f;
        this.padRight = -1.0f;
        this.padTop = -1.0f;
        this.padBottom = -1.0f;
        if (textureRegionArr == null || textureRegionArr.length != 9) {
            throw new IllegalArgumentException("NinePatch needs nine TextureRegions");
        }
        load(textureRegionArr);
        if ((textureRegionArr[0] != null && textureRegionArr[0].getRegionWidth() != this.leftWidth) || ((textureRegionArr[3] != null && textureRegionArr[3].getRegionWidth() != this.leftWidth) || (textureRegionArr[6] != null && textureRegionArr[6].getRegionWidth() != this.leftWidth))) {
            throw new GdxRuntimeException("Left side patches must have the same width");
        }
        if ((textureRegionArr[2] != null && textureRegionArr[2].getRegionWidth() != this.rightWidth) || ((textureRegionArr[5] != null && textureRegionArr[5].getRegionWidth() != this.rightWidth) || (textureRegionArr[8] != null && textureRegionArr[8].getRegionWidth() != this.rightWidth))) {
            throw new GdxRuntimeException("Right side patches must have the same width");
        }
        if ((textureRegionArr[6] != null && textureRegionArr[6].getRegionHeight() != this.bottomHeight) || ((textureRegionArr[7] != null && textureRegionArr[7].getRegionHeight() != this.bottomHeight) || (textureRegionArr[8] != null && textureRegionArr[8].getRegionHeight() != this.bottomHeight))) {
            throw new GdxRuntimeException("Bottom side patches must have the same height");
        }
        if ((textureRegionArr[0] != null && textureRegionArr[0].getRegionHeight() != this.topHeight) || ((textureRegionArr[1] != null && textureRegionArr[1].getRegionHeight() != this.topHeight) || (textureRegionArr[2] != null && textureRegionArr[2].getRegionHeight() != this.topHeight))) {
            throw new GdxRuntimeException("Top side patches must have the same height");
        }
    }

    public NinePatch(NinePatch ninePatch) {
        this(ninePatch, ninePatch.color);
    }

    public NinePatch(NinePatch ninePatch, Color color) {
        this.vertices = new float[180];
        this.color = new Color(Color.WHITE);
        this.padLeft = -1.0f;
        this.padRight = -1.0f;
        this.padTop = -1.0f;
        this.padBottom = -1.0f;
        this.texture = ninePatch.texture;
        this.bottomLeft = ninePatch.bottomLeft;
        this.bottomCenter = ninePatch.bottomCenter;
        this.bottomRight = ninePatch.bottomRight;
        this.middleLeft = ninePatch.middleLeft;
        this.middleCenter = ninePatch.middleCenter;
        this.middleRight = ninePatch.middleRight;
        this.topLeft = ninePatch.topLeft;
        this.topCenter = ninePatch.topCenter;
        this.topRight = ninePatch.topRight;
        this.leftWidth = ninePatch.leftWidth;
        this.rightWidth = ninePatch.rightWidth;
        this.middleWidth = ninePatch.middleWidth;
        this.middleHeight = ninePatch.middleHeight;
        this.topHeight = ninePatch.topHeight;
        this.bottomHeight = ninePatch.bottomHeight;
        this.padLeft = ninePatch.padLeft;
        this.padTop = ninePatch.padTop;
        this.padBottom = ninePatch.padBottom;
        this.padRight = ninePatch.padRight;
        this.vertices = new float[ninePatch.vertices.length];
        System.arraycopy(ninePatch.vertices, 0, this.vertices, 0, ninePatch.vertices.length);
        this.idx = ninePatch.idx;
        this.color.set(color);
    }

    private void load(TextureRegion[] textureRegionArr) {
        if (textureRegionArr[6] != null) {
            this.bottomLeft = add(textureRegionArr[6], false, false);
            this.leftWidth = textureRegionArr[6].getRegionWidth();
            this.bottomHeight = textureRegionArr[6].getRegionHeight();
        } else {
            this.bottomLeft = -1;
        }
        if (textureRegionArr[7] != null) {
            this.bottomCenter = add(textureRegionArr[7], (textureRegionArr[6] == null && textureRegionArr[8] == null) ? false : true, false);
            this.middleWidth = Math.max(this.middleWidth, textureRegionArr[7].getRegionWidth());
            this.bottomHeight = Math.max(this.bottomHeight, textureRegionArr[7].getRegionHeight());
        } else {
            this.bottomCenter = -1;
        }
        if (textureRegionArr[8] != null) {
            this.bottomRight = add(textureRegionArr[8], false, false);
            this.rightWidth = Math.max(this.rightWidth, textureRegionArr[8].getRegionWidth());
            this.bottomHeight = Math.max(this.bottomHeight, textureRegionArr[8].getRegionHeight());
        } else {
            this.bottomRight = -1;
        }
        if (textureRegionArr[3] != null) {
            this.middleLeft = add(textureRegionArr[3], false, (textureRegionArr[0] == null && textureRegionArr[6] == null) ? false : true);
            this.leftWidth = Math.max(this.leftWidth, textureRegionArr[3].getRegionWidth());
            this.middleHeight = Math.max(this.middleHeight, textureRegionArr[3].getRegionHeight());
        } else {
            this.middleLeft = -1;
        }
        if (textureRegionArr[4] != null) {
            this.middleCenter = add(textureRegionArr[4], (textureRegionArr[3] == null && textureRegionArr[5] == null) ? false : true, (textureRegionArr[1] == null && textureRegionArr[7] == null) ? false : true);
            this.middleWidth = Math.max(this.middleWidth, textureRegionArr[4].getRegionWidth());
            this.middleHeight = Math.max(this.middleHeight, textureRegionArr[4].getRegionHeight());
        } else {
            this.middleCenter = -1;
        }
        if (textureRegionArr[5] != null) {
            this.middleRight = add(textureRegionArr[5], false, (textureRegionArr[2] == null && textureRegionArr[8] == null) ? false : true);
            this.rightWidth = Math.max(this.rightWidth, textureRegionArr[5].getRegionWidth());
            this.middleHeight = Math.max(this.middleHeight, textureRegionArr[5].getRegionHeight());
        } else {
            this.middleRight = -1;
        }
        if (textureRegionArr[0] != null) {
            this.topLeft = add(textureRegionArr[0], false, false);
            this.leftWidth = Math.max(this.leftWidth, textureRegionArr[0].getRegionWidth());
            this.topHeight = Math.max(this.topHeight, textureRegionArr[0].getRegionHeight());
        } else {
            this.topLeft = -1;
        }
        if (textureRegionArr[1] != null) {
            this.topCenter = add(textureRegionArr[1], (textureRegionArr[0] == null && textureRegionArr[2] == null) ? false : true, false);
            this.middleWidth = Math.max(this.middleWidth, textureRegionArr[1].getRegionWidth());
            this.topHeight = Math.max(this.topHeight, textureRegionArr[1].getRegionHeight());
        } else {
            this.topCenter = -1;
        }
        if (textureRegionArr[2] != null) {
            this.topRight = add(textureRegionArr[2], false, false);
            this.rightWidth = Math.max(this.rightWidth, textureRegionArr[2].getRegionWidth());
            this.topHeight = Math.max(this.topHeight, textureRegionArr[2].getRegionHeight());
        } else {
            this.topRight = -1;
        }
        if (this.idx < this.vertices.length) {
            float[] fArr = new float[this.idx];
            System.arraycopy(this.vertices, 0, fArr, 0, this.idx);
            this.vertices = fArr;
        }
    }

    private int add(TextureRegion textureRegion, boolean z, boolean z2) {
        if (this.texture == null) {
            this.texture = textureRegion.getTexture();
        } else if (this.texture != textureRegion.getTexture()) {
            throw new IllegalArgumentException("All regions must be from the same texture.");
        }
        float f = textureRegion.u;
        float f2 = textureRegion.v2;
        float f3 = textureRegion.u2;
        float f4 = textureRegion.v;
        if (this.texture.getMagFilter() == Texture.TextureFilter.Linear || this.texture.getMinFilter() == Texture.TextureFilter.Linear) {
            if (z) {
                float width = 0.5f / this.texture.getWidth();
                f += width;
                f3 -= width;
            }
            if (z2) {
                float height = 0.5f / this.texture.getHeight();
                f2 -= height;
                f4 += height;
            }
        }
        float[] fArr = this.vertices;
        int i = this.idx;
        fArr[i + 3] = f;
        fArr[i + 4] = f2;
        fArr[i + 8] = f;
        fArr[i + 9] = f4;
        fArr[i + 13] = f3;
        fArr[i + 14] = f4;
        fArr[i + 18] = f3;
        fArr[i + 19] = f2;
        this.idx += 20;
        return i;
    }

    private void set(int i, float f, float f2, float f3, float f4, float f5) {
        float f6 = f + f3;
        float f7 = f2 + f4;
        float[] fArr = this.vertices;
        fArr[i] = f;
        fArr[i + 1] = f2;
        fArr[i + 2] = f5;
        fArr[i + 5] = f;
        fArr[i + 6] = f7;
        fArr[i + 7] = f5;
        fArr[i + 10] = f6;
        fArr[i + 11] = f7;
        fArr[i + 12] = f5;
        fArr[i + 15] = f6;
        fArr[i + 16] = f2;
        fArr[i + 17] = f5;
    }

    private void prepareVertices(Batch batch, float f, float f2, float f3, float f4) {
        float f5 = f + this.leftWidth;
        float f6 = f2 + this.bottomHeight;
        float f7 = (f3 - this.rightWidth) - this.leftWidth;
        float f8 = (f4 - this.topHeight) - this.bottomHeight;
        float f9 = (f + f3) - this.rightWidth;
        float f10 = (f2 + f4) - this.topHeight;
        float floatBits = tmpDrawColor.set(this.color).mul(batch.getColor()).toFloatBits();
        if (this.bottomLeft != -1) {
            set(this.bottomLeft, f, f2, this.leftWidth, this.bottomHeight, floatBits);
        }
        if (this.bottomCenter != -1) {
            set(this.bottomCenter, f5, f2, f7, this.bottomHeight, floatBits);
        }
        if (this.bottomRight != -1) {
            set(this.bottomRight, f9, f2, this.rightWidth, this.bottomHeight, floatBits);
        }
        if (this.middleLeft != -1) {
            set(this.middleLeft, f, f6, this.leftWidth, f8, floatBits);
        }
        if (this.middleCenter != -1) {
            set(this.middleCenter, f5, f6, f7, f8, floatBits);
        }
        if (this.middleRight != -1) {
            set(this.middleRight, f9, f6, this.rightWidth, f8, floatBits);
        }
        if (this.topLeft != -1) {
            set(this.topLeft, f, f10, this.leftWidth, this.topHeight, floatBits);
        }
        if (this.topCenter != -1) {
            set(this.topCenter, f5, f10, f7, this.topHeight, floatBits);
        }
        if (this.topRight != -1) {
            set(this.topRight, f9, f10, this.rightWidth, this.topHeight, floatBits);
        }
    }

    public void draw(Batch batch, float f, float f2, float f3, float f4) {
        prepareVertices(batch, f, f2, f3, f4);
        batch.draw(this.texture, this.vertices, 0, this.idx);
    }

    public void draw(Batch batch, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        prepareVertices(batch, f, f2, f5, f6);
        float f10 = f + f3;
        float f11 = f2 + f4;
        int i = this.idx;
        float[] fArr = this.vertices;
        if (f9 != 0.0f) {
            for (int i2 = 0; i2 < i; i2 += 5) {
                float f12 = (fArr[i2] - f10) * f7;
                float f13 = (fArr[i2 + 1] - f11) * f8;
                float cosDeg = MathUtils.cosDeg(f9);
                float sinDeg = MathUtils.sinDeg(f9);
                fArr[i2] = ((cosDeg * f12) - (sinDeg * f13)) + f10;
                fArr[i2 + 1] = (sinDeg * f12) + (cosDeg * f13) + f11;
            }
        } else if (f7 != 1.0f || f8 != 1.0f) {
            for (int i3 = 0; i3 < i; i3 += 5) {
                fArr[i3] = ((fArr[i3] - f10) * f7) + f10;
                fArr[i3 + 1] = ((fArr[i3 + 1] - f11) * f8) + f11;
            }
        }
        batch.draw(this.texture, fArr, 0, i);
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public Color getColor() {
        return this.color;
    }

    public float getLeftWidth() {
        return this.leftWidth;
    }

    public void setLeftWidth(float f) {
        this.leftWidth = f;
    }

    public float getRightWidth() {
        return this.rightWidth;
    }

    public void setRightWidth(float f) {
        this.rightWidth = f;
    }

    public float getTopHeight() {
        return this.topHeight;
    }

    public void setTopHeight(float f) {
        this.topHeight = f;
    }

    public float getBottomHeight() {
        return this.bottomHeight;
    }

    public void setBottomHeight(float f) {
        this.bottomHeight = f;
    }

    public float getMiddleWidth() {
        return this.middleWidth;
    }

    public void setMiddleWidth(float f) {
        this.middleWidth = f;
    }

    public float getMiddleHeight() {
        return this.middleHeight;
    }

    public void setMiddleHeight(float f) {
        this.middleHeight = f;
    }

    public float getTotalWidth() {
        return this.leftWidth + this.middleWidth + this.rightWidth;
    }

    public float getTotalHeight() {
        return this.topHeight + this.middleHeight + this.bottomHeight;
    }

    public void setPadding(float f, float f2, float f3, float f4) {
        this.padLeft = f;
        this.padRight = f2;
        this.padTop = f3;
        this.padBottom = f4;
    }

    public float getPadLeft() {
        return this.padLeft == -1.0f ? getLeftWidth() : this.padLeft;
    }

    public void setPadLeft(float f) {
        this.padLeft = f;
    }

    public float getPadRight() {
        return this.padRight == -1.0f ? getRightWidth() : this.padRight;
    }

    public void setPadRight(float f) {
        this.padRight = f;
    }

    public float getPadTop() {
        return this.padTop == -1.0f ? getTopHeight() : this.padTop;
    }

    public void setPadTop(float f) {
        this.padTop = f;
    }

    public float getPadBottom() {
        return this.padBottom == -1.0f ? getBottomHeight() : this.padBottom;
    }

    public void setPadBottom(float f) {
        this.padBottom = f;
    }

    public void scale(float f, float f2) {
        this.leftWidth *= f;
        this.rightWidth *= f;
        this.topHeight *= f2;
        this.bottomHeight *= f2;
        this.middleWidth *= f;
        this.middleHeight *= f2;
        if (this.padLeft != -1.0f) {
            this.padLeft *= f;
        }
        if (this.padRight != -1.0f) {
            this.padRight *= f;
        }
        if (this.padTop != -1.0f) {
            this.padTop *= f2;
        }
        if (this.padBottom != -1.0f) {
            this.padBottom *= f2;
        }
    }

    public Texture getTexture() {
        return this.texture;
    }
}
