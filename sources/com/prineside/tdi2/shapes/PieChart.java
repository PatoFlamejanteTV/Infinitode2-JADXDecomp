package com.prineside.tdi2.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Shape;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/shapes/PieChart.class */
public class PieChart extends Shape {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2913a = TLog.forClass(PieChart.class);

    /* renamed from: b, reason: collision with root package name */
    private float[] f2914b;
    private int c;
    private int d;
    private float e;
    private float f;
    private float g;
    private int h;
    private boolean i;
    private Array<ChartEntryConfig> j;
    public float innerRadius;
    public float angleShiftRad;
    public float rotationDirection;
    private final Vector2 k;
    private final Vector2 l;
    private final Vector2 m;
    private final Vector2 n;
    private final Vector2 o;
    private final Vector2 p;
    private final TextureRegion q;
    private float r;
    private float s;
    private final Color t;
    private boolean u;

    /* synthetic */ PieChart(byte b2) {
        this();
    }

    private PieChart() {
        this.f2914b = new float[0];
        this.innerRadius = 0.0f;
        this.angleShiftRad = 0.0f;
        this.rotationDirection = 1.0f;
        this.k = new Vector2();
        this.l = new Vector2();
        this.m = new Vector2();
        this.n = new Vector2();
        this.o = new Vector2();
        this.p = new Vector2();
        this.t = new Color();
        this.u = false;
        this.q = Game.i.assetManager.getBlankWhiteTextureRegion();
        float u2 = this.q.getU2() - this.q.getU();
        float v2 = this.q.getV2() - this.q.getV();
        this.r = this.q.getU() + (u2 * 0.5f);
        this.s = this.q.getV() + (v2 * 0.5f);
    }

    public void requestVerticesRebuild() {
        this.u = false;
    }

    public void setShadowSegments(int i) {
        this.h = i;
        this.u = false;
    }

    public void setFadeToOut(boolean z) {
        this.i = z;
        this.u = false;
    }

    public float getX() {
        return this.e;
    }

    public float getY() {
        return this.f;
    }

    public void setup(float f, float f2, float f3, int i, Array<ChartEntryConfig> array) {
        if (i < 3) {
            throw new IllegalArgumentException("Min segment count is 3, " + i + " given");
        }
        this.d = i;
        this.e = f;
        this.f = f2;
        this.g = f3;
        this.j = array;
        this.u = false;
    }

    private void a() {
        if (this.j == null || this.j.size == 0) {
            return;
        }
        float f = 0.0f;
        int i = this.j.size;
        for (int i2 = 0; i2 < i; i2++) {
            f += this.j.get(i2).f2915a;
        }
        if (f <= 0.0f || Float.isNaN(f) || Float.isInfinite(f)) {
            f2913a.e("invalid values sum: " + f, new Object[0]);
            int i3 = this.j.size;
            for (int i4 = 0; i4 < i3; i4++) {
                f2913a.e(new StringBuilder().append(this.j.get(i4).f2915a).toString(), new Object[0]);
            }
            this.c = 0;
            this.u = true;
            return;
        }
        int i5 = 0;
        int i6 = this.j.size;
        for (int i7 = 0; i7 < i6; i7++) {
            ChartEntryConfig chartEntryConfig = this.j.get(i7);
            if (chartEntryConfig.f2915a < 0.0f) {
                throw new IllegalArgumentException("Config #" + i7 + " value must be >= 0, " + chartEntryConfig.f2915a + " given");
            }
            chartEntryConfig.f2916b = MathUtils.round((chartEntryConfig.f2915a / f) * this.d);
            i5 += chartEntryConfig.f2916b;
        }
        this.c = i5 * 20;
        if (this.h != 0) {
            this.c += (this.j.size << 1) * 20;
        }
        if (this.f2914b.length < this.c) {
            this.f2914b = new float[MathUtils.nextPowerOfTwo(this.c)];
        }
        float f2 = 6.2831855f / i5;
        int i8 = 0;
        int abs = StrictMath.abs(this.h);
        int i9 = this.j.size;
        for (int i10 = 0; i10 < i9; i10++) {
            ChartEntryConfig chartEntryConfig2 = this.j.get(i10);
            if (chartEntryConfig2.segmentShift > 0.0f && this.j.size > 1) {
                this.p.set(1.0f, 0.0f).rotateRad((chartEntryConfig2.f2916b * f2 * 0.5f) + (f2 * i8)).scl(chartEntryConfig2.segmentShift);
            } else {
                this.p.set(0.0f, 0.0f);
            }
            float floatBits = chartEntryConfig2.color.toFloatBits();
            float floatBits2 = this.t.set(chartEntryConfig2.color).mul(1.2f, 1.2f, 1.2f, 1.0f).toFloatBits();
            float floatBits3 = this.t.set(chartEntryConfig2.color).mul(0.8f, 0.8f, 0.8f, 1.0f).toFloatBits();
            float floatBits4 = this.t.set(chartEntryConfig2.color).mul(1.0f, 1.0f, 1.0f, 0.0f).toFloatBits();
            for (int i11 = 0; i11 < chartEntryConfig2.f2916b; i11++) {
                this.o.set(1.0f, 0.0f).rotateRad((f2 * i8 * this.rotationDirection) + this.angleShiftRad);
                this.l.set(this.o).scl(this.g).add(this.e, this.f);
                this.k.set(this.o).scl(this.innerRadius).add(this.e, this.f);
                this.o.rotateRad((f2 * this.rotationDirection) + this.angleShiftRad);
                this.n.set(this.o).scl(this.g).add(this.e, this.f);
                this.m.set(this.o).scl(this.innerRadius).add(this.e, this.f);
                int i12 = i8 * 20;
                float f3 = floatBits;
                if (i11 < abs) {
                    if (this.h < 0) {
                        f3 = 0.0f;
                    } else {
                        f3 = floatBits2;
                    }
                } else if (i11 > (chartEntryConfig2.f2916b - 1) - abs) {
                    if (this.h < 0) {
                        f3 = 0.0f;
                    } else {
                        f3 = floatBits3;
                    }
                }
                this.f2914b[i12] = this.k.x + this.p.x;
                this.f2914b[i12 + 1] = this.k.y + this.p.y;
                this.f2914b[i12 + 2] = f3;
                this.f2914b[i12 + 3] = this.r;
                this.f2914b[i12 + 4] = this.s;
                this.f2914b[i12 + 5] = this.m.x + this.p.x;
                this.f2914b[i12 + 6] = this.m.y + this.p.y;
                this.f2914b[i12 + 7] = f3;
                this.f2914b[i12 + 8] = this.r;
                this.f2914b[i12 + 9] = this.s;
                this.f2914b[i12 + 10] = this.n.x + this.p.x;
                this.f2914b[i12 + 11] = this.n.y + this.p.y;
                this.f2914b[i12 + 12] = this.i ? floatBits4 : f3;
                this.f2914b[i12 + 13] = this.r;
                this.f2914b[i12 + 14] = this.s;
                this.f2914b[i12 + 15] = this.l.x + this.p.x;
                this.f2914b[i12 + 16] = this.l.y + this.p.y;
                this.f2914b[i12 + 17] = this.i ? floatBits4 : f3;
                this.f2914b[i12 + 18] = this.r;
                this.f2914b[i12 + 19] = this.s;
                i8++;
            }
        }
        this.u = true;
    }

    @Override // com.prineside.tdi2.Shape, com.prineside.tdi2.ProjectileTrail
    public void draw(Batch batch) {
        if (this.j == null || this.j.size == 0) {
            return;
        }
        if (!this.u) {
            a();
        }
        if (this.c == 0) {
            return;
        }
        batch.draw(this.q.getTexture(), this.f2914b, 0, this.c);
    }

    @Override // com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        this.j = null;
        this.h = 0;
        this.u = false;
        this.i = false;
    }

    public void free() {
        Game.i.shapeManager.F.PIE_CHART.free(this);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/shapes/PieChart$ChartEntryConfig.class */
    public static class ChartEntryConfig {
        public Color color = new Color();

        /* renamed from: a, reason: collision with root package name */
        private float f2915a;
        public float segmentShift;
        public Object userObject;

        /* renamed from: b, reason: collision with root package name */
        private int f2916b;

        public ChartEntryConfig(Color color, float f, float f2) {
            this.color.set(color);
            this.f2915a = f;
            this.segmentShift = f2;
        }

        public float getValue() {
            return this.f2915a;
        }

        public void setValue(float f) {
            Preconditions.checkArgument(PMath.isFinite(f) && f >= 0.0f, "Value must be >= 0, %s given", Float.valueOf(f));
            this.f2915a = f;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/shapes/PieChart$PieChartFactory.class */
    public static class PieChartFactory extends Shape.Factory<PieChart> {
        @Override // com.prineside.tdi2.Shape.Factory
        protected final /* synthetic */ PieChart a() {
            return b();
        }

        @Override // com.prineside.tdi2.Shape.Factory
        public void setup() {
        }

        private static PieChart b() {
            return new PieChart((byte) 0);
        }
    }
}
