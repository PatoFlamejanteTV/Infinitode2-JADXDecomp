package com.c.b;

/* loaded from: infinitode-2.jar:com/c/b/o.class */
class o {

    /* renamed from: a, reason: collision with root package name */
    int f934a;

    /* renamed from: b, reason: collision with root package name */
    int f935b;
    int c;
    int d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(float[] fArr, int[] iArr, int i, int i2, float[] fArr2, int i3, float f, float f2) {
        float f3;
        float f4;
        float f5 = 3.1415927f / i2;
        for (int i4 = 0; i4 < i3; i4++) {
            fArr2[i4] = m.a(fArr2[i4]);
        }
        int i5 = (i3 / 2) << 1;
        int i6 = 0;
        while (i6 < i) {
            int i7 = iArr[i6];
            float f6 = 0.70710677f;
            float f7 = 0.70710677f;
            float a2 = m.a(f5 * i7);
            for (int i8 = 0; i8 < i5; i8 += 2) {
                f7 *= fArr2[i8] - a2;
                f6 *= fArr2[i8 + 1] - a2;
            }
            if ((i3 & 1) != 0) {
                float f8 = f7 * (fArr2[i3 - 1] - a2);
                f3 = f8 * f8;
                float f9 = f6;
                f4 = f9 * f9 * (1.0f - (a2 * a2));
            } else {
                float f10 = f7;
                f3 = f10 * f10 * (a2 + 1.0f);
                float f11 = f6;
                f4 = f11 * f11 * (1.0f - a2);
            }
            float f12 = f4 + f3;
            float f13 = f12;
            int floatToIntBits = Float.floatToIntBits(f12);
            int i9 = Integer.MAX_VALUE & floatToIntBits;
            int i10 = 0;
            if (i9 < 2139095040 && i9 != 0) {
                if (i9 < 8388608) {
                    floatToIntBits = Float.floatToIntBits((float) (f13 * 3.3554432E7d));
                    i9 = Integer.MAX_VALUE & floatToIntBits;
                    i10 = -25;
                }
                i10 += (i9 >>> 23) - 126;
                f13 = Float.intBitsToFloat((floatToIntBits & (-2139095041)) | 1056964608);
            }
            float c = m.c(((f * m.b(f13)) * m.a(i10 + i3)) - f2);
            do {
                int i11 = i6;
                i6++;
                fArr[i11] = fArr[i11] * c;
                if (i6 < i) {
                }
            } while (iArr[i6] == i7);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(int i) {
        int i2 = 0;
        while (i != 0) {
            i2++;
            i >>>= 1;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(int i) {
        int i2 = 0;
        while (i > 1) {
            i2++;
            i >>>= 1;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c(int i) {
        int i2 = 0;
        while (i != 0) {
            i2 += i & 1;
            i >>>= 1;
        }
        return i2;
    }
}
