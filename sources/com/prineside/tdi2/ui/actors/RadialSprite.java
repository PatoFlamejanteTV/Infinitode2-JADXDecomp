package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.utils.IgnoreMethodOverloadLuaDefWarning;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/RadialSprite.class */
public class RadialSprite implements Drawable {

    /* renamed from: a, reason: collision with root package name */
    private Texture f3230a;
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private float i;
    private float j;
    private float k;
    private float l;
    private float m;
    private float q;
    private float r;

    /* renamed from: b, reason: collision with root package name */
    private final float[] f3231b = new float[60];
    private boolean n = true;
    private int o = 0;
    private float p = 270.0f;
    private float s = 1.0f;
    private float t = 1.0f;
    private float u = 0.0f;
    private float v = 0.0f;
    private float w = 0.0f;
    private float x = 0.0f;
    private float y = 0.0f;
    private float z = 0.0f;

    public RadialSprite(TextureRegion textureRegion) {
        this.f3230a = textureRegion.getTexture();
        this.h = textureRegion.getU();
        this.j = textureRegion.getV();
        this.i = textureRegion.getU2();
        this.k = textureRegion.getV2();
        this.l = this.i - this.h;
        this.m = this.k - this.j;
        this.f = textureRegion.getRegionWidth();
        this.g = textureRegion.getRegionHeight();
        a(Config.WHITE_COLOR_CACHED_FLOAT_BITS.toFloatBits());
    }

    private void a(float f) {
        for (int i = 0; i < 12; i++) {
            this.f3231b[(i * 5) + 2] = f;
        }
    }

    private final void a(float[] fArr, int i, float f, float f2) {
        a(fArr, i, f, f2, this.h + (this.l * ((f - this.c) / this.f)), this.j + (this.m * (1.0f - ((f2 - this.d) / this.g))));
    }

    private final void a(float[] fArr, int i, float f, float f2, float f3, float f4) {
        fArr[i] = this.c + this.q + (((f - this.c) - this.q) * this.s);
        fArr[i + 1] = this.d + this.r + (((f2 - this.d) - this.r) * this.t);
        fArr[i + 3] = f3;
        fArr[i + 4] = f4;
    }

    private void a(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        if (!this.n && this.c == f && this.d == f2 && this.e == f5 && this.f == f3 && this.g == f4 && this.h == f6 && this.k == f9 && this.i == f8 && this.k == f9) {
            return;
        }
        this.c = f;
        this.d = f2;
        this.f = f3;
        this.g = f4;
        this.e = f5;
        this.h = f6;
        this.j = f7;
        this.i = f8;
        this.k = f9;
        float f10 = f3 * 0.5f;
        float f11 = f4 * 0.5f;
        float f12 = f + f3;
        float f13 = f2 + f4;
        float f14 = f + f10;
        float f15 = f2 + f11;
        float cosDeg = MathUtils.cosDeg(f5 + this.p);
        float sinDeg = MathUtils.sinDeg(f5 + this.p);
        float abs = cosDeg != 0.0f ? StrictMath.abs(f10 / cosDeg) : 1.0E8f;
        float abs2 = sinDeg != 0.0f ? StrictMath.abs(f11 / sinDeg) : 1.0E8f;
        float min = StrictMath.min(abs, abs2);
        float f16 = min * cosDeg;
        float f17 = min * sinDeg;
        a(this.f3231b, 5, f + f10, f2);
        if (cosDeg >= 0.0f) {
            a(this.f3231b, 15, f, f13);
            a(this.f3231b, 0, f14, f13);
            a(this.f3231b, 10, f, f2);
            a(this.f3231b, 30, f14, f15);
            a(this.f3231b, 35, f14, f13);
            if (abs < abs2) {
                a(this.f3231b, 20, f12, f13);
                a(this.f3231b, 25, f12, f15 + f17);
                this.o = 2;
            } else if (sinDeg > 0.0f) {
                a(this.f3231b, 25, f14 + f16, f13);
                a(this.f3231b, 20, f14 + (f16 * 0.5f), f13);
                this.o = 2;
            } else {
                a(this.f3231b, 20, f12, f13);
                a(this.f3231b, 25, f12, f2);
                a(this.f3231b, 55, f14, f15);
                a(this.f3231b, 40, f12, f2);
                a(this.f3231b, 50, f14 + f16, f2);
                a(this.f3231b, 45, f14 + (f16 * 0.5f), f2);
                this.o = 3;
            }
        } else {
            a(this.f3231b, 0, f + f10, f2 + f11);
            if (abs < abs2) {
                a(this.f3231b, 10, f, f2);
                a(this.f3231b, 15, f, f15 + f17);
                this.o = 1;
            } else if (sinDeg < 0.0f) {
                a(this.f3231b, 15, f14 + f16, f2);
                a(this.f3231b, 10, f14 + (f16 * 0.5f), f2);
                this.o = 1;
            } else {
                a(this.f3231b, 15, f, f13);
                a(this.f3231b, 10, f, f2);
                a(this.f3231b, 25, f14, f15);
                a(this.f3231b, 30, f, f13);
                a(this.f3231b, 35, f14 + (f16 * 0.5f), f13);
                a(this.f3231b, 20, f14 + f16, f13);
                this.o = 2;
            }
        }
        this.n = false;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public void draw(Batch batch, float f, float f2, float f3, float f4, float f5) {
        if (f3 < 0.0f) {
            this.s = -1.0f;
            f3 = -f3;
        }
        if (f4 < 0.0f) {
            this.t = -1.0f;
            f4 = -f4;
        }
        a(f, f2, f3, f4, f5, this.h, this.j, this.i, this.k);
        a(batch.getPackedColor());
        batch.draw(this.f3230a, this.f3231b, 0, 20 * this.o);
    }

    @IgnoreMethodOverloadLuaDefWarning
    public void draw(Batch batch, float f, float f2, float f3) {
        draw(batch, f, f2, this.f, this.g, f3);
    }

    public void setOrigin(float f, float f2) {
        if (this.q == f && this.r == f2) {
            return;
        }
        this.q = f;
        this.r = f2;
        this.n = true;
    }

    public void setScale(float f, float f2) {
        if (this.s == f && this.t == f2) {
            return;
        }
        this.s = f;
        this.t = f2;
        this.n = true;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    @IgnoreMethodOverloadLuaDefWarning
    public void draw(Batch batch, float f, float f2, float f3, float f4) {
        draw((SpriteBatch) batch, f, f2, f3, f4, this.e);
    }

    public float getAngle() {
        return this.e;
    }

    public void setAngle(float f) {
        if (this.e == f) {
            return;
        }
        this.e = f;
        this.n = true;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public float getLeftWidth() {
        return this.u;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public void setLeftWidth(float f) {
        this.u = f;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public float getRightWidth() {
        return this.v;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public void setRightWidth(float f) {
        this.v = f;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public float getTopHeight() {
        return this.w;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public void setTopHeight(float f) {
        this.w = f;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public float getBottomHeight() {
        return this.x;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public void setBottomHeight(float f) {
        this.x = f;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public float getMinWidth() {
        return this.y;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public void setMinWidth(float f) {
        this.y = f;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public float getMinHeight() {
        return this.z;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public void setMinHeight(float f) {
        this.z = f;
    }

    public Texture getTexture() {
        return this.f3230a;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.f3230a = textureRegion.getTexture();
        this.h = textureRegion.getU();
        this.j = textureRegion.getV();
        this.i = textureRegion.getU2();
        this.k = textureRegion.getV2();
        this.n = true;
    }
}
