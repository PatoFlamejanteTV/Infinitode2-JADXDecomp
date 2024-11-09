package com.prineside.tdi2.shapes;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.Shape;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/shapes/Circle.class */
public final class Circle extends Shape {

    /* renamed from: a, reason: collision with root package name */
    private float[] f2905a;

    /* renamed from: b, reason: collision with root package name */
    private int f2906b;
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private final Vector2 i;
    private final Vector2 j;
    private final Vector2 k;
    private final Vector2 l;
    private final Vector2 m;
    private boolean n;
    private boolean o;

    /* synthetic */ Circle(byte b2) {
        this();
    }

    private Circle() {
        this.f2905a = new float[0];
        this.i = new Vector2();
        this.j = new Vector2();
        this.k = new Vector2();
        this.l = new Vector2();
        this.m = new Vector2();
        this.o = true;
    }

    public final float getX() {
        return this.c;
    }

    public final float getY() {
        return this.d;
    }

    public final float getInnerColor() {
        return this.g;
    }

    public final float getOuterColor() {
        return this.h;
    }

    public final int getSegmentCount() {
        return this.f2906b;
    }

    public final void setSegmentCount(int i) {
        if (i < 3) {
            throw new IllegalArgumentException("Min segment count is 3, " + i + " given");
        }
        if (this.f2906b != i) {
            this.f2906b = i;
            this.o = true;
        }
    }

    public final void setSkipOddSegments(boolean z) {
        if (this.n != z) {
            this.n = z;
            this.o = true;
        }
    }

    public final float getInnerRadius() {
        return this.e;
    }

    public final void setInnerRadius(float f) {
        if (this.e != f) {
            this.e = f;
            this.o = true;
        }
    }

    public final float getOuterRadius() {
        return this.f;
    }

    public final void setOuterRadius(float f) {
        if (this.f != f) {
            this.f = f;
            this.o = true;
        }
    }

    private int a() {
        return (this.n ? this.f2906b / 2 : this.f2906b) * 20;
    }

    public final void setPosition(float f, float f2) {
        if (this.c != f || this.d != f2) {
            this.c = f;
            this.d = f2;
            this.o = true;
        }
    }

    public final void setColor(float f, float f2) {
        if (this.g != f || this.h != f2) {
            this.g = f;
            this.h = f2;
            this.o = true;
        }
    }

    public final void setup(float f, float f2, float f3, float f4, int i, float f5, float f6) {
        setSegmentCount(i);
        setPosition(f, f2);
        setInnerRadius(f3);
        setOuterRadius(f4);
        setColor(f5, f6);
    }

    private void b() {
        float f = 6.2831855f / this.f2906b;
        if (this.n && this.f2906b % 2 == 1) {
            this.f2906b++;
        }
        int a2 = a();
        if (this.f2905a.length < a2) {
            this.f2905a = new float[a2];
        }
        ResourcePack.AtlasTextureRegion blankWhiteTextureRegion = Game.i.assetManager.getBlankWhiteTextureRegion();
        float u2 = blankWhiteTextureRegion.getU2() - blankWhiteTextureRegion.getU();
        float v2 = blankWhiteTextureRegion.getV2() - blankWhiteTextureRegion.getV();
        float u = blankWhiteTextureRegion.getU() + (u2 * 0.5f);
        float v = blankWhiteTextureRegion.getV() + (v2 * 0.5f);
        int i = 0;
        for (int i2 = 0; i2 < this.f2906b; i2++) {
            if (!this.n || i2 % 2 != 0) {
                this.m.set(1.0f, 0.0f).rotateRad(f * i2);
                this.i.set(this.m).scl(this.e).add(this.c, this.d);
                this.k.set(this.m).scl(this.f).add(this.c, this.d);
                this.m.rotateRad(f);
                this.j.set(this.m).scl(this.e).add(this.c, this.d);
                this.l.set(this.m).scl(this.f).add(this.c, this.d);
                int i3 = i;
                int i4 = i + 1;
                this.f2905a[i3] = this.i.x;
                int i5 = i4 + 1;
                this.f2905a[i4] = this.i.y;
                int i6 = i5 + 1;
                this.f2905a[i5] = this.g;
                int i7 = i6 + 1;
                this.f2905a[i6] = u;
                int i8 = i7 + 1;
                this.f2905a[i7] = v;
                int i9 = i8 + 1;
                this.f2905a[i8] = this.j.x;
                int i10 = i9 + 1;
                this.f2905a[i9] = this.j.y;
                int i11 = i10 + 1;
                this.f2905a[i10] = this.g;
                int i12 = i11 + 1;
                this.f2905a[i11] = u;
                int i13 = i12 + 1;
                this.f2905a[i12] = v;
                int i14 = i13 + 1;
                this.f2905a[i13] = this.l.x;
                int i15 = i14 + 1;
                this.f2905a[i14] = this.l.y;
                int i16 = i15 + 1;
                this.f2905a[i15] = this.h;
                int i17 = i16 + 1;
                this.f2905a[i16] = u;
                int i18 = i17 + 1;
                this.f2905a[i17] = v;
                int i19 = i18 + 1;
                this.f2905a[i18] = this.k.x;
                int i20 = i19 + 1;
                this.f2905a[i19] = this.k.y;
                int i21 = i20 + 1;
                this.f2905a[i20] = this.h;
                int i22 = i21 + 1;
                this.f2905a[i21] = u;
                i = i22 + 1;
                this.f2905a[i22] = v;
            }
        }
        this.o = false;
    }

    @Override // com.prineside.tdi2.Shape, com.prineside.tdi2.ProjectileTrail
    public final void draw(Batch batch) {
        if (this.o) {
            b();
        }
        batch.draw(Game.i.assetManager.getBlankWhiteTextureRegion().getTexture(), this.f2905a, 0, a());
    }

    @Override // com.badlogic.gdx.utils.Pool.Poolable
    public final void reset() {
        this.n = false;
        this.o = true;
    }

    public final void free() {
        Game.i.shapeManager.F.CIRCLE.free(this);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/shapes/Circle$CircleFactory.class */
    public static class CircleFactory extends Shape.Factory<Circle> {
        @Override // com.prineside.tdi2.Shape.Factory
        protected final /* synthetic */ Circle a() {
            return b();
        }

        @Override // com.prineside.tdi2.Shape.Factory
        public void setup() {
        }

        private static Circle b() {
            return new Circle((byte) 0);
        }
    }
}
