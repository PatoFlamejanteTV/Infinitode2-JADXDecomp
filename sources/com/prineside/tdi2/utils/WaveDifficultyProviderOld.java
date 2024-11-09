package com.prineside.tdi2.utils;

import com.badlogic.gdx.math.MathUtils;
import com.google.common.base.Preconditions;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/WaveDifficultyProviderOld.class */
public final class WaveDifficultyProviderOld {

    /* renamed from: a, reason: collision with root package name */
    private final int[] f3920a;

    /* renamed from: b, reason: collision with root package name */
    private final int[] f3921b = new int[16];
    private final float[] c = new float[16];
    private int d;

    public WaveDifficultyProviderOld(int[] iArr) {
        Arrays.fill(this.f3921b, -1);
        this.d = 0;
        Preconditions.checkArgument(iArr.length == 4, "difficultyGrowWaves must be int[4]");
        this.f3920a = iArr;
    }

    private float a(float f, int i) {
        int i2 = 3;
        while (true) {
            if (i2 < 0) {
                break;
            }
            if (this.f3920a[i2] == 0 || i <= this.f3920a[i2]) {
                i2--;
            } else if (i2 == 0) {
                f = (float) (f + 0.01d);
            } else if (i2 == 1) {
                f = (float) (f + 0.02d);
            } else if (i2 == 2) {
                f = (float) (f + 0.03d + (StrictMath.pow(i - this.f3920a[2], 1.15d) * 0.004d));
            } else {
                f = (float) (f + 0.04d + (StrictMath.pow(i - this.f3920a[2], 1.15d) * 0.005d));
            }
        }
        return f;
    }

    private float a(int i) {
        int i2 = -1;
        for (int i3 = 0; i3 < 16; i3++) {
            int i4 = this.f3921b[i3];
            if (i4 != -1 && i4 <= i) {
                if (i2 != -1) {
                    if (this.f3921b[i2] < i4) {
                        i2 = i3;
                    }
                } else {
                    i2 = i3;
                }
            }
        }
        if (i2 != -1) {
            int i5 = this.f3921b[i2];
            float f = this.c[i2];
            if (i5 == i) {
                return f;
            }
            for (int i6 = i5 + 1; i6 <= i; i6++) {
                f = a(f, i6);
            }
            this.f3921b[this.d] = i;
            this.c[this.d] = f;
            this.d++;
            if (this.d == 16) {
                this.d = 0;
            }
            return f;
        }
        float f2 = 1.0f;
        for (int i7 = 1; i7 <= i; i7++) {
            f2 = a(f2, i7);
        }
        this.f3921b[this.d] = i;
        this.c[this.d] = f2;
        this.d++;
        if (this.d == 16) {
            this.d = 0;
        }
        return f2;
    }

    public final int getDifficultWavesMultiplier(int i, int i2) {
        float a2 = a(i);
        float f = 0.04f;
        int i3 = 3;
        while (true) {
            if (i3 < 0) {
                break;
            }
            if (this.f3920a[i3] == 0 || i <= this.f3920a[i3]) {
                i3--;
            } else if (i3 == 0) {
                f = 0.04f;
            } else if (i3 == 1) {
                f = 0.035f;
            } else if (i3 == 2) {
                f = 0.025f;
            } else {
                f = 0.015f;
            }
        }
        float sin = PMath.sin(i + 90) * f;
        if (sin < 0.0f) {
            a2 += a2 * sin;
        }
        return MathUtils.round(i2 * a2);
    }
}
