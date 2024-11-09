package com.prineside.tdi2.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.StringBuilder;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/AABBCounter.class */
public final class AABBCounter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3810a = TLog.forClass(AABBCounter.class);
    private final float d;

    /* renamed from: b, reason: collision with root package name */
    private final FloatArray f3811b = new FloatArray();
    private byte[] c = new byte[0];
    private short e = 0;
    private short f = 0;
    private float g = Float.MAX_VALUE;
    private float h = -3.4028235E38f;
    private float i = Float.MAX_VALUE;
    private float j = -3.4028235E38f;

    public AABBCounter(float f) {
        Preconditions.checkArgument(f > 0.001f, "Min cellSize is 0.001f");
        this.d = 1.0f / f;
    }

    public final float getMinX() {
        return this.g;
    }

    public final float getMaxX() {
        return this.h;
    }

    public final float getMinY() {
        return this.i;
    }

    public final float getMaxY() {
        return this.j;
    }

    public final float getCellSize() {
        return 1.0f / this.d;
    }

    public final short getCols() {
        return this.e;
    }

    public final short getRows() {
        return this.f;
    }

    public final void reset() {
        this.f3811b.clear();
    }

    public final void add(float f, float f2, float f3) {
        this.f3811b.add(f, f2, f3);
    }

    public final void bake() {
        this.g = Float.MAX_VALUE;
        this.h = -3.4028235E38f;
        this.i = Float.MAX_VALUE;
        this.j = -3.4028235E38f;
        if (this.f3811b.size == 0) {
            this.e = (short) 0;
            this.f = (short) 0;
            return;
        }
        for (int i = 0; i < this.f3811b.size; i += 3) {
            float f = this.f3811b.items[i];
            float f2 = this.f3811b.items[i + 1];
            float f3 = this.f3811b.items[i + 2];
            this.g = Math.min(this.g, f - f3);
            this.i = Math.min(this.i, f2 - f3);
            this.h = Math.max(this.h, f + f3);
            this.j = Math.max(this.j, f2 + f3);
        }
        float f4 = 1.0f / this.d;
        this.g -= Math.abs(this.g % f4) - 0.01f;
        this.i -= Math.abs(this.i % f4) - 0.01f;
        this.h = (this.h - (this.h % f4)) + f4 + 0.01f;
        this.j = (this.j - (this.j % f4)) + f4 + 0.01f;
        this.e = (short) MathUtils.ceil((this.h - this.g) * this.d);
        this.f = (short) MathUtils.ceil((this.j - this.i) * this.d);
        if (this.c.length < this.e * this.f) {
            this.c = new byte[this.e * this.f];
        } else {
            Arrays.fill(this.c, (byte) 0);
        }
        for (int i2 = 0; i2 < this.f3811b.size; i2 += 3) {
            float f5 = this.f3811b.items[i2] - this.g;
            float f6 = this.f3811b.items[i2 + 1] - this.i;
            float f7 = this.f3811b.items[i2 + 2];
            int i3 = (int) ((f5 - f7) * this.d);
            int i4 = (int) ((f6 - f7) * this.d);
            int i5 = (int) ((f5 + f7) * this.d);
            int i6 = (int) ((f6 + f7) * this.d);
            for (int i7 = i4; i7 <= i6; i7++) {
                for (int i8 = i3; i8 <= i5; i8++) {
                    int i9 = (i7 * this.e) + i8;
                    try {
                        if (this.c[i9] != Byte.MAX_VALUE) {
                            byte[] bArr = this.c;
                            bArr[i9] = (byte) (bArr[i9] + 1);
                        }
                    } catch (Exception e) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i10 = 0; i10 < this.f3811b.size; i10++) {
                            stringBuilder.append(this.f3811b.get(i10)).append("f,");
                        }
                        f3810a.e(stringBuilder.toString(), new Object[0]);
                        throw new IllegalStateException("Failed to fill in entry counts, idx " + i9 + ", iY " + i7 + ", iX " + i8 + ", minX " + i3 + ", minY " + i4 + ", maxX " + i5 + ", maxY " + i6, e);
                    }
                }
            }
        }
    }

    public final int getEntityCountByCellIdx(int i) {
        return this.c[i];
    }

    public final int getEntityCount(float f, float f2) {
        if (this.e == 0 || f > this.h || f < this.g || f2 > this.j || f2 < this.i) {
            return 0;
        }
        return this.c[(((int) ((f2 - this.i) * this.d)) * this.e) + ((int) ((f - this.g) * this.d))];
    }

    public final boolean entriesExistInRect(float f, float f2, float f3, float f4) {
        return f < this.h && f3 > this.g && f2 < this.j && f4 > this.i;
    }

    public final boolean lineCanHitEntry(float f, float f2, float f3, float f4) {
        float f5 = f;
        float f6 = f3;
        if (f > f3) {
            f5 = f3;
            f6 = f;
        }
        float f7 = f2;
        float f8 = f4;
        if (f2 > f4) {
            f7 = f4;
            f8 = f2;
        }
        return entriesExistInRect(f5, f7, f6, f8);
    }
}
