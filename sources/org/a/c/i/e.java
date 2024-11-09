package org.a.c.i;

/* loaded from: infinitode-2.jar:org/a/c/i/e.class */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    private static final long[] f4659a;

    /* renamed from: b, reason: collision with root package name */
    private static final int[] f4660b;

    static {
        long[] jArr = new long[19];
        f4659a = jArr;
        jArr[0] = 1;
        for (int i = 1; i < f4659a.length; i++) {
            long[] jArr2 = f4659a;
            jArr2[i] = jArr2[i - 1] * 10;
        }
        int[] iArr = new int[10];
        f4660b = iArr;
        iArr[0] = 1;
        for (int i2 = 1; i2 < f4660b.length; i2++) {
            int[] iArr2 = f4660b;
            iArr2[i2] = iArr2[i2 - 1] * 10;
        }
    }

    public static int a(float f, int i, byte[] bArr) {
        if (Float.isNaN(f) || Float.isInfinite(f) || f > 9.223372E18f || f <= -9.223372E18f || i > 5) {
            return -1;
        }
        int i2 = 0;
        long j = f;
        if (f < 0.0f) {
            i2 = 0 + 1;
            bArr[0] = 45;
            j = -j;
        }
        long abs = (long) (((Math.abs(f) - j) * f4659a[i]) + 0.5d);
        long j2 = abs;
        if (abs >= f4659a[i]) {
            j++;
            j2 -= f4659a[i];
        }
        long j3 = j;
        int a2 = a(j3, a(j3), false, bArr, i2);
        if (j2 > 0 && i > 0) {
            bArr[a2] = 46;
            a2 = a(j2, i - 1, true, bArr, a2 + 1);
        }
        return a2;
    }

    private static int a(long j, int i, boolean z, byte[] bArr, int i2) {
        int i3 = i2;
        long j2 = j;
        while (j2 > 2147483647L && (!z || j2 > 0)) {
            j2 -= (j2 / f4659a[i]) * f4659a[i];
            int i4 = i3;
            i3++;
            bArr[i4] = (byte) (r0 + 48);
            i--;
        }
        int i5 = (int) j2;
        while (i >= 0 && (!z || i5 > 0)) {
            int i6 = i5 / f4660b[i];
            i5 -= i6 * f4660b[i];
            int i7 = i3;
            i3++;
            bArr[i7] = (byte) (i6 + 48);
            i--;
        }
        return i3;
    }

    private static int a(long j) {
        for (int i = 0; i < f4659a.length - 1; i++) {
            if (j < f4659a[i + 1]) {
                return i;
            }
        }
        return f4659a.length - 1;
    }
}
