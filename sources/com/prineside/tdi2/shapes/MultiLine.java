package com.prineside.tdi2.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.NumberUtils;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.Shape;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.utils.MaterialColor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/shapes/MultiLine.class */
public class MultiLine extends Shape {

    /* renamed from: a, reason: collision with root package name */
    private float[] f2911a;

    /* renamed from: b, reason: collision with root package name */
    private float[] f2912b;
    private int c;
    private final Color d;
    private boolean e;
    private TextureRegion f;
    private boolean g;
    private boolean h;
    private float i;
    private float j;
    private float k;
    private float l;
    private float m;
    private final Vector2 n;

    /* synthetic */ MultiLine(byte b2) {
        this();
    }

    private MultiLine() {
        this.f2911a = new float[0];
        this.f2912b = new float[0];
        this.c = 0;
        this.d = Color.WHITE.cpy();
        this.e = true;
        this.n = new Vector2();
    }

    public void setNodesWithCount(float[] fArr, int i) {
        b(i);
        this.c = i;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 << 2;
            int i4 = i2 * 6;
            this.f2912b[i4] = fArr[i3];
            this.f2912b[i4 + 1] = fArr[i3 + 1];
            this.f2912b[i4 + 2] = fArr[i3 + 2] / 2.0f;
            this.f2912b[i4 + 3] = fArr[i3 + 3];
        }
        updateAllNodes();
        this.e = true;
    }

    public void setNodes(float[] fArr) {
        setNodesWithCount(fArr, fArr.length / 4);
    }

    public int getNodeCount() {
        return this.c;
    }

    private static int a(int i) {
        if (i < 3) {
            return 2;
        }
        return (i - 1) << 1;
    }

    private void b(int i) {
        int a2 = a(i) * 20;
        if (this.f2911a.length < a2) {
            float[] fArr = new float[MathUtils.nextPowerOfTwo(a2)];
            System.arraycopy(this.f2911a, 0, fArr, 0, this.f2911a.length);
            this.f2911a = fArr;
        }
        int i2 = i * 6;
        if (this.f2912b.length < i2) {
            float[] fArr2 = new float[MathUtils.nextPowerOfTwo(i2)];
            System.arraycopy(this.f2912b, 0, fArr2, 0, this.f2912b.length);
            this.f2912b = fArr2;
            this.e = true;
        }
    }

    public TextureRegion getTextureRegion() {
        return this.f;
    }

    public boolean getMirror() {
        return this.g;
    }

    public boolean getFlip() {
        return this.h;
    }

    public void setTextureRegion(TextureRegion textureRegion, boolean z, boolean z2) {
        this.f = textureRegion;
        this.g = z;
        this.h = z2;
        this.i = textureRegion.getU();
        this.j = textureRegion.getU2();
        if (z) {
            if (!z2) {
                this.l = textureRegion.getV();
                this.k = textureRegion.getV2();
            } else {
                this.l = textureRegion.getV2();
                this.k = textureRegion.getV();
            }
            this.m = this.k;
        } else {
            this.l = textureRegion.getV() + ((textureRegion.getV2() - textureRegion.getV()) * 0.5f);
            if (z2) {
                this.k = textureRegion.getV2();
                this.m = textureRegion.getV();
            } else {
                this.k = textureRegion.getV();
                this.m = textureRegion.getV2();
            }
        }
        for (int i = 1; i < this.c; i++) {
            e(i);
        }
    }

    public void appendNode(float f, float f2, float f3, float f4, boolean z) {
        b(this.c + 1);
        int i = this.c;
        int i2 = i * 6;
        this.c++;
        this.f2912b[i2] = f;
        this.f2912b[i2 + 1] = f2;
        this.f2912b[i2 + 2] = f3 / 2.0f;
        this.f2912b[i2 + 3] = f4;
        if (z) {
            if (i != 0) {
                d(i - 1);
            }
            d(i);
        }
        if (i != 0) {
            f(i);
        }
    }

    public void setNodePosition(int i, float f, float f2) {
        int i2 = i * 6;
        this.f2912b[i2] = f;
        this.f2912b[i2 + 1] = f2;
    }

    private void c(int i) {
        float f;
        float f2;
        if (this.c < 2) {
            return;
        }
        int i2 = i * 6;
        if (i == 0) {
            int i3 = i2 + 6;
            f = this.f2912b[i3] - this.f2912b[i2];
            f2 = this.f2912b[i3 + 1] - this.f2912b[i2 + 1];
        } else if (i == this.c - 1) {
            int i4 = i2 - 6;
            f = this.f2912b[i2] - this.f2912b[i4];
            f2 = this.f2912b[i2 + 1] - this.f2912b[i4 + 1];
        } else {
            int i5 = i2 - 6;
            int i6 = i2 + 6;
            f = this.f2912b[i6] - this.f2912b[i5];
            f2 = this.f2912b[i6 + 1] - this.f2912b[i5 + 1];
        }
        this.n.set(f, f2).nor();
        this.f2912b[i2 + 4] = -this.n.y;
        this.f2912b[i2 + 5] = this.n.x;
    }

    public void updateAllNodes() {
        for (int i = 0; i < this.c; i++) {
            c(i);
        }
        for (int i2 = 1; i2 < this.c; i2++) {
            e(i2);
        }
        this.e = true;
    }

    private void d(int i) {
        c(i);
        if (i != 0) {
            e(i);
        }
        if (i != this.c - 1) {
            e(i + 1);
        }
        this.e = true;
    }

    private void e(int i) {
        if (i == 0) {
            return;
        }
        int i2 = i * 6;
        int i3 = (i - 1) * 6;
        int i4 = ((i - 1) << 1) * 20;
        this.f2911a[i4] = this.f2912b[i3];
        this.f2911a[i4 + 1] = this.f2912b[i3 + 1];
        this.f2911a[i4 + 3] = this.j;
        this.f2911a[i4 + 4] = this.l;
        this.f2911a[i4 + 5] = this.f2912b[i2];
        this.f2911a[i4 + 6] = this.f2912b[i2 + 1];
        this.f2911a[i4 + 8] = this.i;
        this.f2911a[i4 + 9] = this.l;
        this.n.set(this.f2912b[i2 + 4], this.f2912b[i2 + 5]).scl(this.f2912b[i2 + 2]);
        this.f2911a[i4 + 10] = this.f2912b[i2] + this.n.x;
        this.f2911a[i4 + 11] = this.f2912b[i2 + 1] + this.n.y;
        this.f2911a[i4 + 13] = this.i;
        this.f2911a[i4 + 14] = this.k;
        this.n.set(this.f2912b[i3 + 4], this.f2912b[i3 + 5]).scl(this.f2912b[i3 + 2]);
        this.f2911a[i4 + 15] = this.f2912b[i3] + this.n.x;
        this.f2911a[i4 + 16] = this.f2912b[i3 + 1] + this.n.y;
        this.f2911a[i4 + 18] = this.j;
        this.f2911a[i4 + 19] = this.k;
        int i5 = (((i - 1) << 1) + 1) * 20;
        this.n.set(this.f2912b[i3 + 4], this.f2912b[i3 + 5]).scl(-this.f2912b[i3 + 2]);
        this.f2911a[i5] = this.f2912b[i3] + this.n.x;
        this.f2911a[i5 + 1] = this.f2912b[i3 + 1] + this.n.y;
        this.f2911a[i5 + 3] = this.j;
        this.f2911a[i5 + 4] = this.m;
        this.n.set(this.f2912b[i2 + 4], this.f2912b[i2 + 5]).scl(-this.f2912b[i2 + 2]);
        this.f2911a[i5 + 5] = this.f2912b[i2] + this.n.x;
        this.f2911a[i5 + 6] = this.f2912b[i2 + 1] + this.n.y;
        this.f2911a[i5 + 8] = this.i;
        this.f2911a[i5 + 9] = this.m;
        this.f2911a[i5 + 10] = this.f2912b[i2];
        this.f2911a[i5 + 11] = this.f2912b[i2 + 1];
        this.f2911a[i5 + 13] = this.i;
        this.f2911a[i5 + 14] = this.l;
        this.f2911a[i5 + 15] = this.f2912b[i3];
        this.f2911a[i5 + 16] = this.f2912b[i3 + 1];
        this.f2911a[i5 + 18] = this.j;
        this.f2911a[i5 + 19] = this.l;
        this.e = true;
    }

    public void setNodeColor(int i, float f) {
        this.f2912b[(i * 6) + 3] = f;
        if (i != 0) {
            f(i);
        }
    }

    public void setTint(Color color) {
        if (color == null) {
            color = Color.WHITE;
        }
        if (this.d.f889a == color.f889a && this.d.r == color.r && this.d.g == color.g && this.d.f888b == color.f888b) {
            return;
        }
        this.d.set(color);
        this.e = true;
    }

    public void setTintWithAlpha(Color color, float f) {
        if (color == null) {
            color = Color.WHITE;
        }
        if (this.d.f889a == f && this.d.r == color.r && this.d.g == color.g && this.d.f888b == color.f888b) {
            return;
        }
        this.d.set(color);
        this.d.f889a = f;
        this.e = true;
    }

    public Color getTint() {
        return this.d;
    }

    private void f(int i) {
        int floatToIntColor = NumberUtils.floatToIntColor(this.f2912b[(i * 6) + 3]);
        float intToFloatColor = NumberUtils.intToFloatColor(((int) ((floatToIntColor & 255) * this.d.r)) + (((int) (((floatToIntColor >> 8) & 255) * this.d.g)) << 8) + (((int) (((floatToIntColor >> 16) & 255) * this.d.f888b)) << 16) + (((int) (((floatToIntColor & (-16777216)) >>> 24) * this.d.f889a)) << 24));
        int floatToIntColor2 = NumberUtils.floatToIntColor(this.f2912b[((i - 1) * 6) + 3]);
        float intToFloatColor2 = NumberUtils.intToFloatColor(((int) ((floatToIntColor2 & 255) * this.d.r)) + (((int) (((floatToIntColor2 >> 8) & 255) * this.d.g)) << 8) + (((int) (((floatToIntColor2 >> 16) & 255) * this.d.f888b)) << 16) + (((int) (((floatToIntColor2 & (-16777216)) >>> 24) * this.d.f889a)) << 24));
        int i2 = ((i - 1) << 1) * 20;
        this.f2911a[i2 + 2] = intToFloatColor2;
        this.f2911a[i2 + 7] = intToFloatColor;
        this.f2911a[i2 + 12] = intToFloatColor;
        this.f2911a[i2 + 17] = intToFloatColor2;
        int i3 = (((i - 1) << 1) + 1) * 20;
        this.f2911a[i3 + 2] = intToFloatColor2;
        this.f2911a[i3 + 7] = intToFloatColor;
        this.f2911a[i3 + 12] = intToFloatColor;
        this.f2911a[i3 + 17] = intToFloatColor2;
    }

    private void a() {
        for (int i = 1; i < this.c; i++) {
            f(i);
        }
        this.e = false;
    }

    public void bakeVerticesColorIfNeeded() {
        if (this.e) {
            a();
        }
    }

    @Override // com.prineside.tdi2.Shape, com.prineside.tdi2.ProjectileTrail
    public void draw(Batch batch) {
        if (this.c < 2) {
            return;
        }
        bakeVerticesColorIfNeeded();
        batch.draw(this.f.getTexture(), this.f2911a, 0, a(this.c) * 20);
    }

    public void drawDebug(Batch batch) {
        ResourcePack.AtlasTextureRegion atlasTextureRegion = AssetManager.TextureRegions.i().smallCircle;
        int a2 = a(this.c) * 20;
        batch.setColor(MaterialColor.YELLOW.P500);
        for (int i = 0; i < a2; i += 5) {
            batch.draw(atlasTextureRegion, this.f2911a[i] - 1.0f, this.f2911a[i + 1] - 1.0f, 2.0f, 2.0f);
        }
        batch.setColor(Color.WHITE);
    }

    @Override // com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        this.c = 0;
        this.f = null;
        this.g = false;
        this.e = true;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/shapes/MultiLine$MultiLineFactory.class */
    public static class MultiLineFactory extends Shape.Factory<MultiLine> {
        @Override // com.prineside.tdi2.Shape.Factory
        protected final /* synthetic */ MultiLine a() {
            return b();
        }

        @Override // com.prineside.tdi2.Shape.Factory
        public void setup() {
        }

        private static MultiLine b() {
            return new MultiLine((byte) 0);
        }
    }
}
