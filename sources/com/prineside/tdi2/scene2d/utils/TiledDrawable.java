package com.prineside.tdi2.scene2d.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Align;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/utils/TiledDrawable.class */
public class TiledDrawable extends TextureRegionDrawable {

    /* renamed from: a, reason: collision with root package name */
    private final Color f2737a;

    /* renamed from: b, reason: collision with root package name */
    private float f2738b;
    private int c;

    public TiledDrawable() {
        this.f2737a = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.f2738b = 1.0f;
        this.c = 12;
    }

    public TiledDrawable(TextureRegion textureRegion) {
        super(textureRegion);
        this.f2737a = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.f2738b = 1.0f;
        this.c = 12;
    }

    public TiledDrawable(TextureRegionDrawable textureRegionDrawable) {
        super(textureRegionDrawable);
        this.f2737a = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.f2738b = 1.0f;
        this.c = 12;
    }

    @Override // com.prineside.tdi2.scene2d.utils.TextureRegionDrawable, com.prineside.tdi2.scene2d.utils.BaseDrawable, com.prineside.tdi2.scene2d.utils.Drawable
    public void draw(Batch batch, float f, float f2, float f3, float f4) {
        float packedColor = batch.getPackedColor();
        batch.setColor(batch.getColor().mul(this.f2737a));
        draw(batch, getRegion(), f, f2, f3, f4, this.f2738b, this.c);
        batch.setPackedColor(packedColor);
    }

    public static void draw(Batch batch, TextureRegion textureRegion, float f, float f2, float f3, float f4, float f5, int i) {
        float f6;
        float f7;
        float f8;
        float f9;
        float regionWidth = textureRegion.getRegionWidth() * f5;
        float regionHeight = textureRegion.getRegionHeight() * f5;
        Texture texture = textureRegion.getTexture();
        float width = texture.getWidth() * f5;
        float height = texture.getHeight() * f5;
        float u = textureRegion.getU();
        float v = textureRegion.getV();
        float u2 = textureRegion.getU2();
        float v2 = textureRegion.getV2();
        int i2 = (int) (f3 / regionWidth);
        if (Align.isLeft(i)) {
            f6 = 0.0f;
            f7 = f3 - (regionWidth * i2);
        } else if (Align.isRight(i)) {
            f6 = f3 - (regionWidth * i2);
            f7 = 0.0f;
        } else if (i2 != 0) {
            i2 = i2 % 2 == 1 ? i2 : i2 - 1;
            float f10 = 0.5f * (f3 - (regionWidth * i2));
            f6 = f10;
            f7 = f10;
        } else {
            f6 = 0.0f;
            f7 = 0.0f;
        }
        int i3 = (int) (f4 / regionHeight);
        if (Align.isTop(i)) {
            f8 = 0.0f;
            f9 = f4 - (regionHeight * i3);
        } else if (Align.isBottom(i)) {
            f8 = f4 - (regionHeight * i3);
            f9 = 0.0f;
        } else if (i3 != 0) {
            i3 = i3 % 2 == 1 ? i3 : i3 - 1;
            float f11 = 0.5f * (f4 - (regionHeight * i3));
            f8 = f11;
            f9 = f11;
        } else {
            f8 = 0.0f;
            f9 = 0.0f;
        }
        float f12 = f2;
        if (f6 > 0.0f) {
            float f13 = u2 - (f6 / width);
            if (f9 > 0.0f) {
                batch.draw(texture, f, f2, f6, f9, f13, v + (f9 / height), u2, v);
                f12 = f2 + f9;
            }
            if (i3 == 0 && Align.isCenterVertical(i)) {
                float f14 = 0.5f * (v2 - v) * (1.0f - (f4 / regionHeight));
                batch.draw(texture, f, f12, f6, f4, f13, v2 - f14, u2, v + f14);
                f12 += f4;
            } else {
                for (int i4 = 0; i4 < i3; i4++) {
                    batch.draw(texture, f, f12, f6, regionHeight, f13, v2, u2, v);
                    f12 += regionHeight;
                }
            }
            if (f8 > 0.0f) {
                batch.draw(texture, f, f12, f6, f8, f13, v2, u2, v2 - (f8 / height));
            }
        }
        if (f9 > 0.0f) {
            float f15 = f + f6;
            f12 = f2;
            float f16 = v + (f9 / height);
            if (i2 == 0 && Align.isCenterHorizontal(i)) {
                float f17 = 0.5f * (u2 - u) * (1.0f - (f3 / regionWidth));
                batch.draw(texture, f15, f2, f3, f9, u + f17, f16, u2 - f17, v);
            } else {
                for (int i5 = 0; i5 < i2; i5++) {
                    batch.draw(texture, f15, f2, regionWidth, f9, u, f16, u2, v);
                    f15 += regionWidth;
                }
            }
        }
        float f18 = f + f6;
        int i6 = i2;
        int i7 = i3;
        float f19 = regionWidth;
        float f20 = regionHeight;
        float f21 = u;
        float f22 = u2;
        float f23 = v2;
        float f24 = v;
        if (i2 == 0 && Align.isCenterHorizontal(i)) {
            i2 = 1;
            f19 = f3;
            float f25 = 0.5f * (u2 - u) * (1.0f - (f3 / regionWidth));
            f21 = u + f25;
            f22 = u2 - f25;
        }
        if (i3 == 0 && Align.isCenterVertical(i)) {
            i3 = 1;
            f20 = f4;
            float f26 = 0.5f * (v2 - v) * (1.0f - (f4 / regionHeight));
            f23 = v2 - f26;
            f24 = v + f26;
        }
        for (int i8 = 0; i8 < i2; i8++) {
            f12 = f2 + f9;
            for (int i9 = 0; i9 < i3; i9++) {
                batch.draw(texture, f18, f12, f19, f20, f21, f23, f22, f24);
                f12 += f20;
            }
            f18 += f19;
        }
        if (f8 > 0.0f) {
            f18 = f + f6;
            float f27 = v2 - (f8 / height);
            if (i6 == 0 && Align.isCenterHorizontal(i)) {
                float f28 = 0.5f * (u2 - u) * (1.0f - (f3 / regionWidth));
                batch.draw(texture, f18, f12, f3, f8, u + f28, v2, u2 - f28, f27);
                f18 += f3;
            } else {
                for (int i10 = 0; i10 < i6; i10++) {
                    batch.draw(texture, f18, f12, regionWidth, f8, u, v2, u2, f27);
                    f18 += regionWidth;
                }
            }
        }
        if (f7 > 0.0f) {
            float f29 = f2;
            float f30 = u + (f7 / width);
            if (f9 > 0.0f) {
                batch.draw(texture, f18, f2, f7, f9, u, v + (f9 / height), f30, v);
                f29 = f2 + f9;
            }
            if (i7 == 0 && Align.isCenterVertical(i)) {
                float f31 = 0.5f * (v2 - v) * (1.0f - (f4 / regionHeight));
                batch.draw(texture, f18, f29, f7, f4, u, v2 - f31, f30, v + f31);
                f29 += f4;
            } else {
                for (int i11 = 0; i11 < i7; i11++) {
                    batch.draw(texture, f18, f29, f7, regionHeight, u, v2, f30, v);
                    f29 += regionHeight;
                }
            }
            if (f8 > 0.0f) {
                batch.draw(texture, f18, f29, f7, f8, u, v2, f30, v2 - (f8 / height));
            }
        }
    }

    @Override // com.prineside.tdi2.scene2d.utils.TextureRegionDrawable, com.prineside.tdi2.scene2d.utils.TransformDrawable
    public void draw(Batch batch, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        throw new UnsupportedOperationException();
    }

    public Color getColor() {
        return this.f2737a;
    }

    public void setScale(float f) {
        this.f2738b = f;
    }

    public float getScale() {
        return this.f2738b;
    }

    public int getAlign() {
        return this.c;
    }

    public void setAlign(int i) {
        this.c = i;
    }

    @Override // com.prineside.tdi2.scene2d.utils.TextureRegionDrawable
    public TiledDrawable tint(Color color) {
        TiledDrawable tiledDrawable = new TiledDrawable(this);
        tiledDrawable.f2737a.set(color);
        tiledDrawable.setLeftWidth(getLeftWidth());
        tiledDrawable.setRightWidth(getRightWidth());
        tiledDrawable.setTopHeight(getTopHeight());
        tiledDrawable.setBottomHeight(getBottomHeight());
        return tiledDrawable;
    }
}
