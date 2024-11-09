package com.prineside.tdi2.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.FloatArray;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.ProjectileTrail;
import com.prineside.tdi2.Shape;
import com.prineside.tdi2.enums.ShapeType;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.PMath;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/shapes/BulletSmokeMultiLine.class */
public final class BulletSmokeMultiLine extends Shape implements ProjectileTrail {

    /* renamed from: a, reason: collision with root package name */
    private final MultiLine f2901a;

    /* renamed from: b, reason: collision with root package name */
    private int f2902b;
    private float[] c;
    private float[] d;
    private float[] e;
    private float[] f;
    private float[] g;
    private boolean h;
    private boolean i;
    private final Color j;
    public float nodesDisperseTime;
    public float maxSegmentWidth;
    public float maxAlpha;
    public boolean fadeInOut;
    public float minSegmentWidth;
    private final FloatArray k;
    private final Vector2 l;

    /* synthetic */ BulletSmokeMultiLine(byte b2) {
        this();
    }

    private BulletSmokeMultiLine() {
        this.f2902b = 0;
        this.j = new Color(-1);
        this.minSegmentWidth = 0.1f;
        this.k = new FloatArray();
        this.l = new Vector2();
        this.f2901a = (MultiLine) Game.i.shapeManager.getFactory(ShapeType.MULTI_LINE).obtain();
        reset();
    }

    @Override // com.badlogic.gdx.utils.Pool.Poolable
    public final void reset() {
        this.h = false;
        this.nodesDisperseTime = 3.0f;
        this.maxSegmentWidth = 96.0f;
        this.maxAlpha = 0.14f;
        this.minSegmentWidth = 0.1f;
        this.fadeInOut = false;
        this.f2901a.setTint(Color.WHITE);
    }

    public final void setup(float f, float f2, float f3, float f4) {
        float distanceBetweenPoints = PMath.getDistanceBetweenPoints(f, f2, f3, f4);
        int i = (int) (distanceBetweenPoints / 96.0f);
        int i2 = i;
        if (i < 3) {
            i2 = 3;
        }
        if (this.f2902b < i2) {
            this.c = new float[i2 << 2];
            this.d = new float[i2];
            this.e = new float[i2];
            this.f = new float[i2];
            this.g = new float[i2];
        }
        this.f2902b = i2;
        this.k.clear();
        float f5 = 0.0f;
        this.k.add(0.0f);
        for (int i3 = 1; i3 < i2; i3++) {
            float f6 = 64.0f + (64.0f * FastRandom.getFloat());
            if (i3 == 1 || i3 == i2 - 1) {
                f6 *= 0.1f;
            }
            f5 += f6;
            this.k.add(f6);
        }
        float f7 = distanceBetweenPoints / f5;
        this.l.set(f3 - f, f4 - f2);
        this.l.nor();
        float f8 = 0.0f;
        for (int i4 = 0; i4 < this.f2902b; i4++) {
            float f9 = this.k.get(i4) * f7;
            this.c[i4 << 2] = f + (this.l.x * (f9 + f8));
            this.c[(i4 << 2) + 1] = f2 + (this.l.y * (f9 + f8));
            f8 += f9;
        }
        float f10 = 0.0f;
        for (int i5 = 0; i5 < this.f2902b; i5++) {
            this.e[i5] = 0.0f;
            this.d[i5] = f10;
            f10 += 0.017f;
        }
        for (int i6 = 0; i6 < this.f2902b; i6++) {
            this.f[i6] = (FastRandom.getFloat() - 0.5f) * 20.0f;
            this.g[i6] = (FastRandom.getFloat() - 0.5f) * 20.0f;
        }
    }

    public final void setTexture(TextureRegion textureRegion, boolean z, boolean z2) {
        this.f2901a.setTextureRegion(textureRegion, z, z2);
    }

    public final void setColor(Color color) {
        this.j.set(color);
    }

    @Override // com.prineside.tdi2.ProjectileTrail
    public final void update(float f) {
        float f2;
        this.i = true;
        for (int i = 0; i < this.f2902b; i++) {
            float[] fArr = this.e;
            int i2 = i;
            fArr[i2] = fArr[i2] + f;
            if (this.e[i] - this.d[i] < this.nodesDisperseTime) {
                this.i = false;
            }
        }
        if (this.i) {
            return;
        }
        for (int i3 = 0; i3 < this.f2902b; i3++) {
            float f3 = (this.e[i3] - this.d[i3]) / this.nodesDisperseTime;
            if (f3 <= 0.0f) {
                f2 = 0.0f;
                this.j.f889a = 0.0f;
            } else if (f3 >= 1.0f) {
                this.j.f889a = 0.0f;
                f2 = 1.0f;
            } else {
                if (!this.fadeInOut) {
                    this.j.f889a = (1.0f - f3) * this.maxAlpha;
                } else {
                    this.j.f889a = MathUtils.sin(f3 * 3.1415927f) * this.maxAlpha;
                }
                f2 = Interpolation.pow2.apply(f3);
                float[] fArr2 = this.c;
                int i4 = i3 << 2;
                fArr2[i4] = fArr2[i4] + (this.f[i3] * f);
                float[] fArr3 = this.c;
                int i5 = (i3 << 2) + 1;
                fArr3[i5] = fArr3[i5] + (this.g[i3] * f);
            }
            this.c[(i3 << 2) + 2] = (this.minSegmentWidth + Interpolation.pow2Out.apply(f2)) * this.maxSegmentWidth;
            this.c[(i3 << 2) + 3] = this.j.toFloatBits();
        }
        this.j.f889a = 0.0f;
        this.c[3] = this.j.toFloatBits();
        this.c[((this.f2902b - 1) << 2) + 3] = this.c[3];
        this.f2901a.setNodes(this.c);
        this.h = true;
        this.f2901a.bakeVerticesColorIfNeeded();
    }

    @Override // com.prineside.tdi2.Shape, com.prineside.tdi2.ProjectileTrail
    public final void draw(Batch batch) {
        if (this.h) {
            this.f2901a.draw(batch);
        }
    }

    @Override // com.prineside.tdi2.ProjectileTrail
    public final boolean isFinished() {
        return this.i;
    }

    @Override // com.prineside.tdi2.ProjectileTrail
    public final void free() {
        Game.i.shapeManager.F.BULLET_SMOKE_MULTI_LINE.free(this);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/shapes/BulletSmokeMultiLine$BulletSmokeMultiLineFactory.class */
    public static class BulletSmokeMultiLineFactory extends Shape.Factory<BulletSmokeMultiLine> {
        @Override // com.prineside.tdi2.Shape.Factory
        protected final /* synthetic */ BulletSmokeMultiLine a() {
            return b();
        }

        @Override // com.prineside.tdi2.Shape.Factory
        public void setup() {
        }

        private static BulletSmokeMultiLine b() {
            return new BulletSmokeMultiLine((byte) 0);
        }
    }
}
