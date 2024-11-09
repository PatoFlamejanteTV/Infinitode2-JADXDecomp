package com.prineside.tdi2.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.FloatArray;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.ProjectileTrail;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.Shape;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.utils.IgnoreMethodOverloadLuaDefWarning;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/shapes/TrailMultiLine.class */
public final class TrailMultiLine extends Shape implements ProjectileTrail {

    /* renamed from: a, reason: collision with root package name */
    private float f2921a;

    /* renamed from: b, reason: collision with root package name */
    private float f2922b;
    private float c;
    private final Color d = new Color();
    private final FloatArray e = new FloatArray();
    private final FloatArray f = new FloatArray();
    private float g;
    private float h;
    private boolean i;
    private boolean j;

    static {
        TLog.forClass(TrailMultiLine.class);
    }

    public final TrailMultiLine cloneTrail() {
        Game game = Game.i;
        TrailMultiLine b2 = TrailMultiLineFactory.b();
        b2.setup(this.d, this.c * 2.0f, 1.0f / this.f2921a, (float) Math.sqrt(this.f2922b));
        return b2;
    }

    public final void setup(Color color, float f, float f2, float f3) {
        this.c = f * 0.5f;
        float max = Math.max(f3, this.c);
        this.f2921a = 1.0f / f2;
        this.f2922b = max * max;
        this.d.set(color);
        this.i = false;
        this.j = false;
        this.e.clear();
        this.f.clear();
    }

    @IgnoreMethodOverloadLuaDefWarning
    public final void setColor(Color color) {
        this.d.set(color);
    }

    @IgnoreMethodOverloadLuaDefWarning
    public final void setColor(float f, float f2, float f3, float f4) {
        this.d.set(f, f2, f3, f4);
    }

    public final Color getColor() {
        return this.d;
    }

    public final void setStartPoint(float f, float f2) {
        this.e.clear();
        this.f.clear();
        this.g = f;
        this.h = f2;
        this.e.setSize(10);
        this.e.items[0] = f;
        this.e.items[1] = f2;
        this.e.items[2] = 0.0f;
        this.e.items[3] = 0.0f;
        this.e.items[4] = 0.0f;
        this.e.items[5] = f;
        this.e.items[6] = f2;
        this.e.items[7] = 0.0f;
        this.e.items[8] = 0.0f;
        this.e.items[9] = 0.0f;
    }

    private void a() {
        float[] fArr = this.e.items;
        Vector2 vector2 = new Vector2();
        vector2.set(fArr[5] - fArr[0], fArr[6] - fArr[1]).nor().rotate90(0);
        fArr[2] = vector2.x;
        fArr[3] = vector2.y;
        if (this.e.size == 10) {
            vector2.setZero();
        } else {
            vector2.set(fArr[10] - fArr[5], fArr[11] - fArr[6]).nor().rotate90(0);
        }
        fArr[7] = vector2.x;
        fArr[8] = vector2.y;
    }

    public final void setHeadPositionAndAngle(float f, float f2, float f3) {
        if (isFinished()) {
            return;
        }
        if (PMath.getSquareDistanceBetweenPoints(this.g, this.h, f, f2) > this.f2922b) {
            this.e.setSize(this.e.size + 5);
            System.arraycopy(this.e.items, 0, this.e.items, 5, this.e.size - 5);
            this.g = f;
            this.h = f2;
        }
        Vector2 vector2 = new Vector2(1.0f, 0.0f);
        vector2.rotateDeg(f3);
        this.e.items[0] = f;
        this.e.items[1] = f2;
        this.e.items[2] = vector2.x;
        this.e.items[3] = vector2.y;
        this.e.items[4] = 1.0f;
    }

    public final void setHeadPosition(float f, float f2) {
        if (isFinished()) {
            return;
        }
        if (this.e.items[0] == f && this.e.items[1] == f2) {
            return;
        }
        if (PMath.getSquareDistanceBetweenPoints(this.g, this.h, f, f2) > this.f2922b) {
            this.e.setSize(this.e.size + 5);
            System.arraycopy(this.e.items, 0, this.e.items, 5, this.e.size - 5);
            this.g = f;
            this.h = f2;
        }
        this.e.items[0] = f;
        this.e.items[1] = f2;
        this.e.items[4] = 1.0f;
        a();
    }

    public final void allowCompletion() {
        this.j = true;
    }

    @Override // com.prineside.tdi2.ProjectileTrail
    public final boolean isFinished() {
        return this.i && this.j;
    }

    @Override // com.prineside.tdi2.ProjectileTrail
    public final void update(float f) {
        float f2 = this.f2921a * f;
        float[] fArr = this.e.items;
        int i = this.e.size / 5;
        boolean z = false;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            float f3 = fArr[(i3 * 5) + 4] - f2;
            if (f3 > 0.0f) {
                fArr[(i3 * 5) + 4] = f3;
                z = true;
                i2++;
            } else {
                if (!z) {
                    break;
                }
                fArr[(i3 * 5) + 4] = 0.0f;
                i2++;
                z = false;
            }
        }
        this.i = i2 == 0;
        this.e.setSize(Math.max(10, i2 * 5));
        ResourcePack.AtlasTextureRegion atlasTextureRegion = AssetManager.TextureRegions.i().bulletTraceThin;
        float u = atlasTextureRegion.getU();
        float u2 = atlasTextureRegion.getU2();
        float v = atlasTextureRegion.getV();
        float v2 = atlasTextureRegion.getV2();
        int i4 = (this.e.size / 5) - 1;
        this.f.setSize(i4 * 20);
        float[] fArr2 = this.f.items;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i5 * 5;
            int i7 = (i5 + 1) * 5;
            int i8 = i5 * 20;
            float f4 = fArr[i6];
            float f5 = fArr[i6 + 1];
            float f6 = fArr[i7];
            float f7 = fArr[i7 + 1];
            float f8 = fArr[i6 + 2];
            float f9 = fArr[i6 + 3];
            float f10 = fArr[i6 + 4];
            float floatBits = Color.toFloatBits(this.d.r, this.d.g, this.d.f888b, this.d.f889a * f10);
            float f11 = f8 * this.c * f10;
            float f12 = f9 * this.c * f10;
            float f13 = fArr[i7 + 2];
            float f14 = fArr[i7 + 3];
            float f15 = fArr[i7 + 4];
            float floatBits2 = Color.toFloatBits(this.d.r, this.d.g, this.d.f888b, this.d.f889a * f15);
            float f16 = f13 * this.c * f15;
            float f17 = f14 * this.c * f15;
            fArr2[i8] = f6 - f16;
            fArr2[i8 + 1] = f7 - f17;
            fArr2[i8 + 2] = floatBits2;
            fArr2[i8 + 3] = u2;
            fArr2[i8 + 4] = v;
            fArr2[i8 + 5] = f4 - f11;
            fArr2[i8 + 6] = f5 - f12;
            fArr2[i8 + 7] = floatBits;
            fArr2[i8 + 8] = u;
            fArr2[i8 + 9] = v;
            fArr2[i8 + 10] = f4 + f11;
            fArr2[i8 + 11] = f5 + f12;
            fArr2[i8 + 12] = floatBits;
            fArr2[i8 + 13] = u;
            fArr2[i8 + 14] = v2;
            fArr2[i8 + 15] = f6 + f16;
            fArr2[i8 + 16] = f7 + f17;
            fArr2[i8 + 17] = floatBits2;
            fArr2[i8 + 18] = u2;
            fArr2[i8 + 19] = v2;
        }
    }

    @Override // com.prineside.tdi2.Shape, com.prineside.tdi2.ProjectileTrail
    public final void draw(Batch batch) {
        if (this.f.size != 0) {
            batch.draw(AssetManager.TextureRegions.i().bulletTraceThin.getTexture(), this.f.items, 0, this.f.size);
        }
    }

    @Override // com.prineside.tdi2.ProjectileTrail
    public final void free() {
        Game.i.shapeManager.F.TRAIL_MULTI_LINE.free(this);
    }

    @Override // com.badlogic.gdx.utils.Pool.Poolable
    public final void reset() {
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/shapes/TrailMultiLine$TrailMultiLineFactory.class */
    public static class TrailMultiLineFactory extends Shape.Factory<TrailMultiLine> {
        @Override // com.prineside.tdi2.Shape.Factory
        protected final /* synthetic */ TrailMultiLine a() {
            return b();
        }

        @Override // com.prineside.tdi2.Shape.Factory
        public void setup() {
        }

        protected static TrailMultiLine b() {
            return new TrailMultiLine();
        }
    }
}
