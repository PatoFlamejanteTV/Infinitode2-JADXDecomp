package com.d.c.d;

/* loaded from: infinitode-2.jar:com/d/c/d/h.class */
public final class h implements g {

    /* renamed from: a, reason: collision with root package name */
    public static final h f1056a = new h(0, 0, 0);

    /* renamed from: b, reason: collision with root package name */
    public static final h f1057b = new h(255, 0, 0);
    public static final h c = new h(0, 255, 0);
    public static final h d = new h(0, 0, 255);
    public static final h e = new h(0, 0, 0);
    private int f;
    private int g;
    private int h;

    public h(int i, int i2, int i3) {
        if (i < 0 || i > 255) {
            throw new IllegalArgumentException();
        }
        if (i2 < 0 || i2 > 255) {
            throw new IllegalArgumentException();
        }
        if (i3 < 0 || i3 > 255) {
            throw new IllegalArgumentException();
        }
        this.f = i;
        this.g = i2;
        this.h = i3;
    }

    public h(int i) {
        this((i >> 16) & 255, (i >> 8) & 255, i & 255);
    }

    public final int a() {
        return this.h;
    }

    public final int b() {
        return this.g;
    }

    public final int c() {
        return this.f;
    }

    public final String toString() {
        return "#" + a(this.f) + a(this.g) + a(this.h);
    }

    private static String a(int i) {
        String hexString = Integer.toHexString(i);
        if (hexString.length() == 1) {
            return "0" + hexString;
        }
        return hexString;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return this.h == hVar.h && this.g == hVar.g && this.f == hVar.f;
    }

    public final int hashCode() {
        return (((this.f * 31) + this.g) * 31) + this.h;
    }

    @Override // com.d.c.d.g
    public final g e() {
        float[] a2 = a(c(), b(), a(), null);
        int[] a3 = a(a2[0], a2[1], 0.56f * a2[2]);
        return new h(a3[0], a3[1], a3[2]);
    }

    private static float[] a(int i, int i2, int i3, float[] fArr) {
        float f;
        float f2;
        float f3;
        if (fArr == null) {
            fArr = new float[3];
        }
        int i4 = i > i2 ? i : i2;
        if (i3 > i4) {
            i4 = i3;
        }
        int i5 = i < i2 ? i : i2;
        if (i3 < i5) {
            i5 = i3;
        }
        float f4 = i4 / 255.0f;
        if (i4 != 0) {
            f = (i4 - i5) / i4;
        } else {
            f = 0.0f;
        }
        if (f == 0.0f) {
            f3 = 0.0f;
        } else {
            float f5 = (i4 - i) / (i4 - i5);
            float f6 = (i4 - i2) / (i4 - i5);
            float f7 = (i4 - i3) / (i4 - i5);
            if (i == i4) {
                f2 = f7 - f6;
            } else if (i2 == i4) {
                f2 = (f5 + 2.0f) - f7;
            } else {
                f2 = (f6 + 4.0f) - f5;
            }
            float f8 = f2 / 6.0f;
            f3 = f8;
            if (f8 < 0.0f) {
                f3 += 1.0f;
            }
        }
        fArr[0] = f3;
        fArr[1] = f;
        fArr[2] = f4;
        return fArr;
    }

    private static int[] a(float f, float f2, float f3) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        if (f2 == 0.0f) {
            int i4 = (int) ((f3 * 255.0f) + 0.5f);
            i3 = i4;
            i2 = i4;
            i = i4;
        } else {
            float floor = (f - ((float) Math.floor(f))) * 6.0f;
            float floor2 = floor - ((float) Math.floor(floor));
            float f4 = f3 * (1.0f - f2);
            float f5 = f3 * (1.0f - (f2 * floor2));
            float f6 = f3 * (1.0f - (f2 * (1.0f - floor2)));
            switch ((int) floor) {
                case 0:
                    i = (int) ((f3 * 255.0f) + 0.5f);
                    i2 = (int) ((f6 * 255.0f) + 0.5f);
                    i3 = (int) ((f4 * 255.0f) + 0.5f);
                    break;
                case 1:
                    i = (int) ((f5 * 255.0f) + 0.5f);
                    i2 = (int) ((f3 * 255.0f) + 0.5f);
                    i3 = (int) ((f4 * 255.0f) + 0.5f);
                    break;
                case 2:
                    i = (int) ((f4 * 255.0f) + 0.5f);
                    i2 = (int) ((f3 * 255.0f) + 0.5f);
                    i3 = (int) ((f6 * 255.0f) + 0.5f);
                    break;
                case 3:
                    i = (int) ((f4 * 255.0f) + 0.5f);
                    i2 = (int) ((f5 * 255.0f) + 0.5f);
                    i3 = (int) ((f3 * 255.0f) + 0.5f);
                    break;
                case 4:
                    i = (int) ((f6 * 255.0f) + 0.5f);
                    i2 = (int) ((f4 * 255.0f) + 0.5f);
                    i3 = (int) ((f3 * 255.0f) + 0.5f);
                    break;
                case 5:
                    i = (int) ((f3 * 255.0f) + 0.5f);
                    i2 = (int) ((f4 * 255.0f) + 0.5f);
                    i3 = (int) ((f5 * 255.0f) + 0.5f);
                    break;
            }
        }
        return new int[]{i, i2, i3};
    }
}
