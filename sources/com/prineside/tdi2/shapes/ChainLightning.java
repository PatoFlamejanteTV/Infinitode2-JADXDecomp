package com.prineside.tdi2.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.FloatArray;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Shape;
import com.prineside.tdi2.enums.ShapeType;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.PMath;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/shapes/ChainLightning.class */
public final class ChainLightning extends Shape {
    public static final float AVERAGE_SEGMENT_LENGTH = 16.0f;
    public static final float MIN_SEGMENT_WIDTH = 8.96f;
    public static final float MAX_SEGMENT_WIDTH = 44.8f;

    /* renamed from: a, reason: collision with root package name */
    private float f2903a;

    /* renamed from: b, reason: collision with root package name */
    private float f2904b;
    private float c;
    private float d;
    private float e;
    private float f;
    private final Vector2 g;
    private final Vector2 h;
    private float i;
    private final MultiLine j;
    private int k;
    private float[] l;
    private boolean m;
    private float n;
    private float o;
    private float p;
    private boolean q;
    private boolean r;
    private float s;
    private static final Color t = Color.WHITE.cpy();
    private final Color u;
    private final FloatArray v;
    private final Vector2 w;
    private final Color x;

    /* synthetic */ ChainLightning(byte b2) {
        this();
    }

    private ChainLightning() {
        this.g = new Vector2();
        this.h = new Vector2();
        this.i = 1.0f;
        this.k = 0;
        this.u = new Color(-1);
        this.v = new FloatArray(8);
        this.w = new Vector2();
        this.x = new Color(-1);
        this.j = (MultiLine) Game.i.shapeManager.getFactory(ShapeType.MULTI_LINE).obtain();
        reset();
    }

    public final void setFadingToEnd(boolean z) {
        this.r = z;
    }

    public final float getExistsTime() {
        return this.s;
    }

    public final float getStartX() {
        return this.d;
    }

    public final float getStartY() {
        return this.e;
    }

    @Override // com.badlogic.gdx.utils.Pool.Poolable
    public final void reset() {
        this.m = false;
        this.r = false;
    }

    public final void setPosition(float f, float f2, float f3, float f4) {
        this.d = f;
        this.e = f2;
        this.f = PMath.getDistanceBetweenPoints(f, f2, f3, f4);
        this.g.set(f3 - f, f4 - f2);
        this.h.set(f3 - f, f4 - f2);
        this.h.nor();
        int i = (int) (this.f / this.f2903a);
        int i2 = i;
        if (i < 3) {
            i2 = 3;
        }
        if (this.k < i2) {
            this.l = new float[i2 << 2];
        }
        this.k = i2;
        for (int i3 = 0; i3 < this.k; i3++) {
            float f5 = (i3 / this.k) * 2.0f;
            float f6 = f5;
            if (f5 > 1.0f) {
                f6 = 1.0f - (f6 - 1.0f);
            }
            this.l[(i3 << 2) + 2] = this.f2904b + ((this.c - this.f2904b) * f6);
        }
        setColor(this.u);
    }

    public final void setup(float f, float f2, float f3, float f4, float f5, float f6, boolean z, float f7, float f8, float f9) {
        this.o = f5;
        this.s = 0.0f;
        this.p = f6;
        this.q = z;
        this.f2904b = f7;
        this.c = f8;
        this.f2903a = f9;
        this.i = f9 / 16.0f;
        setPosition(f, f2, f3, f4);
    }

    public final void setTexture(TextureRegion textureRegion, boolean z, boolean z2) {
        this.j.setTextureRegion(textureRegion, z, z2);
    }

    public final void setColor(Color color) {
        this.u.set(color);
        if (this.r) {
            this.x.set(color);
            for (int i = 0; i < this.k; i++) {
                this.x.f889a = color.f889a * (1.0f - (i / this.k));
                this.l[(i << 2) + 3] = this.x.toFloatBits();
            }
            return;
        }
        float floatBits = color.toFloatBits();
        for (int i2 = 0; i2 < this.k; i2++) {
            this.l[(i2 << 2) + 3] = floatBits;
        }
    }

    public final Color getColor() {
        return this.u;
    }

    private void a() {
        this.v.clear();
        this.v.add(0.0f);
        this.v.add(1.0f);
        int i = this.k;
        for (int i2 = 2; i2 < i; i2++) {
            this.v.add(FastRandom.getFloat());
        }
        this.v.sort();
        Vector2 nor = this.w.set(this.g.y, -this.g.x).nor();
        float f = 80.0f * this.i;
        float f2 = 1.0f / f;
        float f3 = 0.0f;
        this.l[0] = this.d;
        this.l[1] = this.e;
        for (int i3 = 1; i3 < this.k; i3++) {
            float f4 = this.v.items[i3];
            float f5 = this.f * f2 * (f4 - this.v.items[i3 - 1]);
            float f6 = f4 > 0.95f ? 20.0f * (1.0f - f4) : 1.0f;
            float f7 = (-f) + (FastRandom.getFloat() * f * 2.0f);
            float f8 = (f7 - ((f7 - f3) * (1.0f - f5))) * f6;
            this.l[i3 << 2] = this.d + (f4 * this.g.x) + (f8 * nor.x);
            this.l[(i3 << 2) + 1] = this.e + (f4 * this.g.y) + (f8 * nor.y);
            f3 = f8;
        }
        this.j.setNodesWithCount(this.l, this.k);
        t.f889a = 1.0f;
        this.j.setTint(t);
        this.m = true;
    }

    public final void update(float f) {
        this.n += f;
        if (!this.m || this.n > this.o) {
            a();
            this.n = 0.0f;
        }
        this.s += f;
        if (this.q) {
            float f2 = 1.0f - (this.s / this.p);
            float f3 = f2;
            if (f2 < 0.0f) {
                f3 = 0.0f;
            }
            t.f889a = f3;
            this.j.setTint(t);
        }
    }

    public final boolean isFinished() {
        return this.s > this.p;
    }

    @Override // com.prineside.tdi2.Shape, com.prineside.tdi2.ProjectileTrail
    public final void draw(Batch batch) {
        if (this.m) {
            this.j.draw(batch);
        }
    }

    public final void free() {
        Game.i.shapeManager.F.CHAIN_LIGHTNING.free(this);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/shapes/ChainLightning$ChainLightningFactory.class */
    public static class ChainLightningFactory extends Shape.Factory<ChainLightning> {
        @Override // com.prineside.tdi2.Shape.Factory
        protected final /* synthetic */ ChainLightning a() {
            return b();
        }

        @Override // com.prineside.tdi2.Shape.Factory
        public void setup() {
        }

        private static ChainLightning b() {
            return new ChainLightning((byte) 0);
        }
    }
}
