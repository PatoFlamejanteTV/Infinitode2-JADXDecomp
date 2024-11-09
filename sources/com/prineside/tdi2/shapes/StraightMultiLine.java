package com.prineside.tdi2.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.NumberUtils;
import com.prineside.tdi2.Shape;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/shapes/StraightMultiLine.class */
public final class StraightMultiLine extends Shape {

    /* renamed from: a, reason: collision with root package name */
    private float[] f2919a;

    /* renamed from: b, reason: collision with root package name */
    private float[] f2920b;
    private int c;
    private float d;
    private final Color e;
    private boolean f;
    private TextureRegion g;
    private TextureRegion h;
    private final Vector2 i;
    private final Vector2 j;
    private final Vector2 k;

    /* synthetic */ StraightMultiLine(byte b2) {
        this();
    }

    private StraightMultiLine() {
        this.f2919a = new float[0];
        this.f2920b = new float[0];
        this.c = 0;
        this.d = 16.0f;
        this.e = Color.WHITE.cpy();
        this.f = true;
        this.i = new Vector2();
        this.j = new Vector2();
        this.k = new Vector2();
    }

    public final void setWidth(float f) {
        this.d = f * 0.5f;
        updateAllNodes();
    }

    public final void setNodesSliced(float[] fArr, int i) {
        b(i);
        this.c = i;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * 3;
            int i4 = i2 * 5;
            this.f2920b[i4] = fArr[i3];
            this.f2920b[i4 + 1] = fArr[i3 + 1];
            this.f2920b[i4 + 2] = fArr[i3 + 2];
        }
        updateAllNodes();
        this.f = true;
    }

    public final void setNodes(float[] fArr) {
        setNodesSliced(fArr, fArr.length / 3);
    }

    public final int getNodeCount() {
        return this.c;
    }

    private static int a(int i) {
        if (i < 3) {
            return 3;
        }
        return (i - 1) * 3;
    }

    private void b(int i) {
        int a2 = a(i) * 20;
        if (this.f2919a.length < a2) {
            float[] fArr = new float[MathUtils.nextPowerOfTwo(a2)];
            System.arraycopy(this.f2919a, 0, fArr, 0, this.f2919a.length);
            this.f2919a = fArr;
        }
        int i2 = i * 5;
        if (this.f2920b.length < i2) {
            float[] fArr2 = new float[MathUtils.nextPowerOfTwo(i2)];
            System.arraycopy(this.f2920b, 0, fArr2, 0, this.f2920b.length);
            this.f2920b = fArr2;
            this.f = true;
        }
    }

    public final TextureRegion getMainTexture() {
        return this.g;
    }

    public final void setTextureRegion(TextureRegion textureRegion, TextureRegion textureRegion2) {
        this.g = textureRegion;
        this.h = textureRegion2;
        for (int i = 1; i < this.c; i++) {
            d(i);
        }
    }

    public final void appendNode(float f, float f2, float f3, boolean z) {
        b(this.c + 1);
        int i = this.c;
        int i2 = i * 5;
        this.c++;
        this.f2920b[i2] = f;
        this.f2920b[i2 + 1] = f2;
        this.f2920b[i2 + 2] = f3;
        if (z) {
            if (i != 0) {
                c(i - 1);
            }
            c(i);
        }
        if (i != 0) {
            e(i);
        }
    }

    public final void setNodePosition(int i, float f, float f2) {
        int i2 = i * 5;
        this.f2920b[i2] = f;
        this.f2920b[i2 + 1] = f2;
    }

    public final void updateAllNodes() {
        for (int i = 1; i < this.c; i++) {
            d(i);
        }
        this.f = true;
    }

    private void c(int i) {
        if (i != 0) {
            d(i);
        }
        if (i != this.c - 1) {
            d(i + 1);
        }
        this.f = true;
    }

    private void d(int i) {
        if (i == 0) {
            return;
        }
        int i2 = i * 5;
        int i3 = (i - 1) * 5;
        float u2 = this.g.getU2();
        float u = this.g.getU();
        float v2 = this.g.getV2();
        float v = this.g.getV();
        float u22 = this.h.getU2();
        float u3 = this.h.getU();
        float v22 = this.h.getV2();
        float v3 = this.h.getV();
        float f = this.f2920b[i3];
        float f2 = this.f2920b[i3 + 1];
        float f3 = this.f2920b[i2];
        float f4 = this.f2920b[i2 + 1];
        this.i.set(this.f2920b[i2] - this.f2920b[i3], this.f2920b[i2 + 1] - this.f2920b[i3 + 1]).nor().scl(this.d);
        this.j.set(this.i).rotate90(1);
        int i4 = (i - 1) * 3 * 20;
        this.k.set(this.j).scl(-1.0f).add(f, f2);
        this.f2919a[i4] = this.k.x;
        this.f2919a[i4 + 1] = this.k.y;
        this.f2919a[i4 + 3] = u22;
        this.f2919a[i4 + 4] = v3;
        this.k.add(-this.i.x, -this.i.y);
        this.f2919a[i4 + 5] = this.k.x;
        this.f2919a[i4 + 6] = this.k.y;
        this.f2919a[i4 + 8] = u3;
        this.f2919a[i4 + 9] = v3;
        this.k.add(this.j.x * 2.0f, this.j.y * 2.0f);
        this.f2919a[i4 + 10] = this.k.x;
        this.f2919a[i4 + 11] = this.k.y;
        this.f2919a[i4 + 13] = u3;
        this.f2919a[i4 + 14] = v22;
        this.k.add(this.i.x, this.i.y);
        this.f2919a[i4 + 15] = this.k.x;
        this.f2919a[i4 + 16] = this.k.y;
        this.f2919a[i4 + 18] = u22;
        this.f2919a[i4 + 19] = v22;
        int i5 = (((i - 1) * 3) + 1) * 20;
        this.k.set(this.j).scl(-1.0f).add(f3, f4);
        this.f2919a[i5] = this.k.x;
        this.f2919a[i5 + 1] = this.k.y;
        this.f2919a[i5 + 3] = u2;
        this.f2919a[i5 + 4] = v;
        this.k.set(this.j).scl(-1.0f).add(f, f2);
        this.f2919a[i5 + 5] = this.k.x;
        this.f2919a[i5 + 6] = this.k.y;
        this.f2919a[i5 + 8] = u;
        this.f2919a[i5 + 9] = v;
        this.k.add(this.j.x * 2.0f, this.j.y * 2.0f);
        this.f2919a[i5 + 10] = this.k.x;
        this.f2919a[i5 + 11] = this.k.y;
        this.f2919a[i5 + 13] = u;
        this.f2919a[i5 + 14] = v2;
        this.k.set(this.j).add(f3, f4);
        this.f2919a[i5 + 15] = this.k.x;
        this.f2919a[i5 + 16] = this.k.y;
        this.f2919a[i5 + 18] = u2;
        this.f2919a[i5 + 19] = v2;
        int i6 = (((i - 1) * 3) + 2) * 20;
        this.k.set(this.j).scl(-1.0f).add(f3, f4).add(this.i.x, this.i.y);
        this.f2919a[i6] = this.k.x;
        this.f2919a[i6 + 1] = this.k.y;
        this.f2919a[i6 + 3] = u3;
        this.f2919a[i6 + 4] = v3;
        this.k.set(this.j).scl(-1.0f).add(f3, f4);
        this.f2919a[i6 + 5] = this.k.x;
        this.f2919a[i6 + 6] = this.k.y;
        this.f2919a[i6 + 8] = u22;
        this.f2919a[i6 + 9] = v3;
        this.k.add(this.j.x * 2.0f, this.j.y * 2.0f);
        this.f2919a[i6 + 10] = this.k.x;
        this.f2919a[i6 + 11] = this.k.y;
        this.f2919a[i6 + 13] = u22;
        this.f2919a[i6 + 14] = v22;
        this.k.add(this.i.x, this.i.y);
        this.f2919a[i6 + 15] = this.k.x;
        this.f2919a[i6 + 16] = this.k.y;
        this.f2919a[i6 + 18] = u3;
        this.f2919a[i6 + 19] = v22;
        this.f = true;
    }

    public final void setNodeColor(int i, float f) {
        this.f2920b[(i * 5) + 2] = f;
        if (i != 0) {
            e(i);
        }
    }

    public final void setTint(Color color) {
        if (color == null) {
            color = Color.WHITE;
        }
        if (this.e.f889a == color.f889a && this.e.r == color.r && this.e.g == color.g && this.e.f888b == color.f888b) {
            return;
        }
        this.e.set(color);
        this.f = true;
    }

    public final void setTintAndAlpha(Color color, float f) {
        if (color == null) {
            color = Color.WHITE;
        }
        if (this.e.f889a == f && this.e.r == color.r && this.e.g == color.g && this.e.f888b == color.f888b) {
            return;
        }
        this.e.set(color);
        this.e.f889a = f;
        this.f = true;
    }

    public final Color getTint() {
        return this.e;
    }

    private void e(int i) {
        int floatToIntColor = NumberUtils.floatToIntColor(this.f2920b[(i * 5) + 2]);
        float intToFloatColor = NumberUtils.intToFloatColor(((int) ((floatToIntColor & 255) * this.e.r)) + (((int) (((floatToIntColor >> 8) & 255) * this.e.g)) << 8) + (((int) (((floatToIntColor >> 16) & 255) * this.e.f888b)) << 16) + (((int) (((floatToIntColor & (-16777216)) >>> 24) * this.e.f889a)) << 24));
        int floatToIntColor2 = NumberUtils.floatToIntColor(this.f2920b[((i - 1) * 5) + 2]);
        float intToFloatColor2 = NumberUtils.intToFloatColor(((int) ((floatToIntColor2 & 255) * this.e.r)) + (((int) (((floatToIntColor2 >> 8) & 255) * this.e.g)) << 8) + (((int) (((floatToIntColor2 >> 16) & 255) * this.e.f888b)) << 16) + (((int) (((floatToIntColor2 & (-16777216)) >>> 24) * this.e.f889a)) << 24));
        int i2 = (i - 1) * 3 * 20;
        this.f2919a[i2 + 2] = intToFloatColor2;
        this.f2919a[i2 + 7] = intToFloatColor2;
        this.f2919a[i2 + 12] = intToFloatColor2;
        this.f2919a[i2 + 17] = intToFloatColor2;
        int i3 = (((i - 1) * 3) + 1) * 20;
        this.f2919a[i3 + 2] = intToFloatColor;
        this.f2919a[i3 + 7] = intToFloatColor2;
        this.f2919a[i3 + 12] = intToFloatColor2;
        this.f2919a[i3 + 17] = intToFloatColor;
        int i4 = (((i - 1) * 3) + 2) * 20;
        this.f2919a[i4 + 2] = intToFloatColor;
        this.f2919a[i4 + 7] = intToFloatColor;
        this.f2919a[i4 + 12] = intToFloatColor;
        this.f2919a[i4 + 17] = intToFloatColor;
    }

    private void a() {
        for (int i = 1; i < this.c; i++) {
            e(i);
        }
        this.f = false;
    }

    @Override // com.prineside.tdi2.Shape, com.prineside.tdi2.ProjectileTrail
    public final void draw(Batch batch) {
        if (this.c < 2) {
            return;
        }
        if (this.f) {
            a();
        }
        batch.draw(this.g.getTexture(), this.f2919a, 0, a(this.c) * 20);
    }

    @Override // com.badlogic.gdx.utils.Pool.Poolable
    public final void reset() {
        this.c = 0;
        this.g = null;
        this.h = null;
        this.f = true;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/shapes/StraightMultiLine$StraightMultiLineFactory.class */
    public static class StraightMultiLineFactory extends Shape.Factory<StraightMultiLine> {
        @Override // com.prineside.tdi2.Shape.Factory
        protected final /* synthetic */ StraightMultiLine a() {
            return b();
        }

        @Override // com.prineside.tdi2.Shape.Factory
        public void setup() {
        }

        private static StraightMultiLine b() {
            return new StraightMultiLine((byte) 0);
        }
    }
}
