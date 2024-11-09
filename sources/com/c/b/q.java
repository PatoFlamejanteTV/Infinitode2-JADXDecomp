package com.c.b;

/* loaded from: infinitode-2.jar:com/c/b/q.class */
final class q {

    /* renamed from: a, reason: collision with root package name */
    private int f941a;

    /* renamed from: b, reason: collision with root package name */
    private int f942b;
    private float[] c;
    private int[] d;
    private float[] e = new float[1024];
    private float[] f = new float[1024];

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i) {
        this.d = new int[i / 4];
        this.c = new float[i + (i / 4)];
        this.f942b = (int) Math.rint(Math.log(i) / Math.log(2.0d));
        this.f941a = i;
        int i2 = 0 + (i / 2);
        int i3 = i2 + 1;
        int i4 = i2 + (i / 2);
        int i5 = i4 + 1;
        for (int i6 = 0; i6 < i / 4; i6++) {
            this.c[0 + (i6 << 1)] = (float) Math.cos((3.141592653589793d / i) * 4 * i6);
            this.c[1 + (i6 << 1)] = (float) (-Math.sin((3.141592653589793d / i) * 4 * i6));
            this.c[i2 + (i6 << 1)] = (float) Math.cos((3.141592653589793d / (2 * i)) * ((2 * i6) + 1));
            this.c[i3 + (i6 << 1)] = (float) Math.sin((3.141592653589793d / (2 * i)) * ((2 * i6) + 1));
        }
        for (int i7 = 0; i7 < i / 8; i7++) {
            this.c[i4 + (i7 << 1)] = (float) Math.cos((3.141592653589793d / i) * ((4 * i7) + 2));
            this.c[i5 + (i7 << 1)] = (float) (-Math.sin((3.141592653589793d / i) * ((4 * i7) + 2)));
        }
        int i8 = (1 << (this.f942b - 1)) - 1;
        int i9 = 1 << (this.f942b - 2);
        for (int i10 = 0; i10 < i / 8; i10++) {
            int i11 = 0;
            for (int i12 = 0; (i9 >>> i12) != 0; i12++) {
                if (((i9 >>> i12) & i10) != 0) {
                    i11 |= 1 << i12;
                }
            }
            this.d[i10 << 1] = (i11 ^ (-1)) & i8;
            this.d[(i10 << 1) + 1] = i11;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void a(float[] fArr, float[] fArr2) {
        if (this.e.length < this.f941a / 2) {
            this.e = new float[this.f941a / 2];
        }
        if (this.f.length < this.f941a / 2) {
            this.f = new float[this.f941a / 2];
        }
        float[] fArr3 = this.e;
        float[] fArr4 = this.f;
        int i = this.f941a >>> 1;
        int i2 = this.f941a >>> 2;
        int i3 = this.f941a >>> 3;
        int i4 = 1;
        int i5 = 0;
        int i6 = i;
        for (int i7 = 0; i7 < i3; i7++) {
            i6 -= 2;
            int i8 = i5;
            int i9 = i5 + 1;
            fArr3[i8] = ((-fArr[i4 + 2]) * this.c[i6 + 1]) - (fArr[i4] * this.c[i6]);
            i5 = i9 + 1;
            fArr3[i9] = (fArr[i4] * this.c[i6 + 1]) - (fArr[i4 + 2] * this.c[i6]);
            i4 += 4;
        }
        int i10 = i - 4;
        for (int i11 = 0; i11 < i3; i11++) {
            i6 -= 2;
            int i12 = i5;
            int i13 = i5 + 1;
            fArr3[i12] = (fArr[i10] * this.c[i6 + 1]) + (fArr[i10 + 2] * this.c[i6]);
            i5 = i13 + 1;
            fArr3[i13] = (fArr[i10] * this.c[i6]) - (fArr[i10 + 2] * this.c[i6 + 1]);
            i10 -= 4;
        }
        float[] a2 = a(fArr3, fArr4, this.f941a, i, i2, i3);
        int i14 = 0;
        int i15 = i;
        int i16 = i2;
        int i17 = i2 - 1;
        int i18 = i2 + i;
        int i19 = i18;
        int i20 = i18 - 1;
        for (int i21 = 0; i21 < i2; i21++) {
            float f = (a2[i14] * this.c[i15 + 1]) - (a2[i14 + 1] * this.c[i15]);
            float f2 = -((a2[i14] * this.c[i15]) + (a2[i14 + 1] * this.c[i15 + 1]));
            fArr2[i16] = -f;
            fArr2[i17] = f;
            fArr2[i19] = f2;
            fArr2[i20] = f2;
            i16++;
            i17--;
            i19++;
            i20--;
            i14 += 2;
            i15 += 2;
        }
    }

    private float[] a(float[] fArr, float[] fArr2, int i, int i2, int i3, int i4) {
        int i5 = i3;
        int i6 = 0;
        int i7 = i2;
        int i8 = 0;
        while (i8 < i3) {
            float f = fArr[i5] - fArr[i6];
            int i9 = i5;
            int i10 = i5 + 1;
            int i11 = i6;
            int i12 = i6 + 1;
            fArr2[i3 + i8] = fArr[i9] + fArr[i11];
            float f2 = fArr[i10] - fArr[i12];
            i7 -= 4;
            int i13 = i8;
            int i14 = i8 + 1;
            fArr2[i13] = (f * this.c[i7]) + (f2 * this.c[i7 + 1]);
            fArr2[i14] = (f2 * this.c[i7]) - (f * this.c[i7 + 1]);
            i5 = i10 + 1;
            i6 = i12 + 1;
            fArr2[i3 + i14] = fArr[i10] + fArr[i12];
            i8 = i14 + 1;
        }
        for (int i15 = 0; i15 < this.f942b - 3; i15++) {
            int i16 = i >>> (i15 + 2);
            int i17 = 1 << (i15 + 3);
            int i18 = i2 - 2;
            int i19 = 0;
            for (int i20 = 0; i20 < (i16 >>> 2); i20++) {
                int i21 = i18;
                int i22 = i21;
                int i23 = i21 - (i16 >> 1);
                float f3 = this.c[i19];
                float f4 = this.c[i19 + 1];
                i18 -= 2;
                int i24 = i16 + 1;
                for (int i25 = 0; i25 < (2 << i15); i25++) {
                    float f5 = fArr2[i22] - fArr2[i23];
                    fArr[i22] = fArr2[i22] + fArr2[i23];
                    int i26 = i22 + 1;
                    int i27 = i23 + 1;
                    float f6 = fArr2[i26] - fArr2[i27];
                    fArr[i26] = fArr2[i26] + fArr2[i27];
                    fArr[i27] = (f6 * f3) - (f5 * f4);
                    fArr[i27 - 1] = (f5 * f3) + (f6 * f4);
                    i22 = i26 - i24;
                    i23 = i27 - i24;
                }
                i16 = i24 - 1;
                i19 += i17;
            }
            float[] fArr3 = fArr2;
            fArr2 = fArr;
            fArr = fArr3;
        }
        int i28 = i;
        int i29 = 0;
        int i30 = 0;
        int i31 = i2 - 1;
        for (int i32 = 0; i32 < i4; i32++) {
            int i33 = i29;
            int i34 = i29 + 1;
            int i35 = this.d[i33];
            i29 = i34 + 1;
            int i36 = this.d[i34];
            float f7 = fArr2[i35] - fArr2[i36 + 1];
            float f8 = fArr2[i35 - 1] + fArr2[i36];
            float f9 = fArr2[i35] + fArr2[i36 + 1];
            float f10 = fArr2[i35 - 1] - fArr2[i36];
            float f11 = f7 * this.c[i28];
            int i37 = i28;
            int i38 = i28 + 1;
            float f12 = f8 * this.c[i37];
            float f13 = f7 * this.c[i38];
            i28 = i38 + 1;
            float f14 = f8 * this.c[i38];
            int i39 = i30;
            int i40 = i30 + 1;
            fArr[i39] = (f9 + f13 + f12) * 0.5f;
            int i41 = i31;
            int i42 = i31 - 1;
            fArr[i41] = (((-f10) + f14) - f11) * 0.5f;
            i30 = i40 + 1;
            fArr[i40] = ((f10 + f14) - f11) * 0.5f;
            i31 = i42 - 1;
            fArr[i42] = ((f9 - f13) - f12) * 0.5f;
        }
        return fArr;
    }
}
