package com.prineside.tdi2.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/WaveDifficultyProvider.class */
public final class WaveDifficultyProvider {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3918a = TLog.forClass(WaveDifficultyProvider.class);

    /* renamed from: b, reason: collision with root package name */
    private static final float[] f3919b = {0.2f, 0.25f, 0.3f, 0.35f, 0.4f, 0.45f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f};
    private static final float c;
    private float d;
    private float e;
    private final int[] f = new int[64];
    private final RandomXS128 g;

    static {
        float f = 0.0f;
        for (float f2 : f3919b) {
            f += f2;
        }
        c = f;
    }

    public WaveDifficultyProvider(int i, float f, float f2) {
        this.e = 1.0f;
        this.d = f;
        this.e = f2;
        if (f2 < -1.0f) {
            f3918a.i("limiting expectedPlaytime to -1 (" + f2 + " given)", new Object[0]);
            this.e = -1.0f;
        }
        this.g = new RandomXS128(i);
        for (int i2 = 0; i2 < 64; i2++) {
            this.f[i2] = this.g.nextInt();
        }
    }

    private void a(int i) {
        this.g.setSeed(this.f[r8 % 64] + ((i + 64) << 32));
    }

    public final int getDifficultWavesMultiplier(int i) {
        int i2 = (int) (15.0f + (5.0f * this.e));
        int i3 = (int) (25.0f + (15.0f * this.e));
        int i4 = (int) (35.0f + (25.0f * this.e));
        int i5 = (int) (55.0f + (35.0f * this.e));
        float f = this.d * 0.01f;
        float max = 1.0f + (i * 0.01f * Math.max(f - 1.0f, 0.0f));
        float f2 = 1.0f + (f * 0.05f);
        float max2 = max + (Math.max(0, i - i2) * 0.01f * f2) + (Math.max(0, i - i3) * 0.02f * f2) + (Math.max(0, i - i4) * (0.031f + ((((float) Math.pow((r0 * 0.03f) + 1.0f, 1.13f + (f2 * 0.05f))) - 1.0f) * 0.01f)));
        int max3 = Math.max(0, i - i5);
        float f3 = this.e + 1.0f;
        float f4 = f3;
        if (f3 > 2.0f) {
            f4 = 2.0f;
        }
        float pow = max2 + (max3 * (0.04f + ((((float) Math.pow((max3 * (0.062f + (0.02f * ((2.0f - f4) * 0.5f)))) + 1.0f, (1.16f + (f2 * 0.05f)) + (max3 * 1.8E-4f))) - 1.0f) * 0.037f)));
        float f5 = 0.0f;
        for (int i6 = 0; i6 < f3919b.length; i6++) {
            a(i - i6);
            f5 += this.g.nextFloat() * f3919b[i6];
        }
        return MathUtils.round(pow * ((MathUtils.clamp(((f5 / c) - 0.25f) * 2.0f, 0.0f, 1.0f) * 0.2f) + 0.8f) * this.d);
    }
}
