package com.a.a.b.c.a;

import com.a.a.b.c.a.d;
import org.lwjgl.system.linux.liburing.LibIOURing;

/* loaded from: infinitode-2.jar:com/a/a/b/c/a/f.class */
final class f {

    /* renamed from: a, reason: collision with root package name */
    private static final float[] f98a = {1.0f, 10.0f, 100.0f, 1000.0f, 10000.0f, 100000.0f, 1000000.0f, 1.0E7f, 1.0E8f, 1.0E9f, 1.0E10f};

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float a(boolean z, long j, int i, boolean z2, int i2) {
        float f;
        if (j == 0) {
            return z ? -0.0f : 0.0f;
        }
        if (z2) {
            if (-45 <= i2 && i2 <= 38) {
                float a2 = a(z, j, i2);
                float a3 = a(z, j + 1, i2);
                if (!Float.isNaN(a2) && a3 == a2) {
                    return a2;
                }
            }
            f = Float.NaN;
        } else if (-45 <= i && i <= 38) {
            f = a(z, j, i);
        } else {
            f = Float.NaN;
        }
        return f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float b(boolean z, long j, int i, boolean z2, int i2) {
        float f;
        if (j == 0) {
            return z ? -0.0f : 0.0f;
        }
        if (z2) {
            if (-126 <= i2 && i2 <= 127) {
                float b2 = b(z, j, i2);
                float b3 = b(z, j + 1, i2);
                if (!Double.isNaN(b2) && b3 == b2) {
                    return b2;
                }
            }
            f = Float.NaN;
        } else if (-126 <= i && i <= 127) {
            f = b(z, j, i);
        } else {
            f = Float.NaN;
        }
        return f;
    }

    private static float a(boolean z, long j, int i) {
        float f;
        if (-10 <= i && i <= 10 && Long.compareUnsigned(j, 16777215L) <= 0) {
            float f2 = (float) j;
            if (i < 0) {
                f = f2 / f98a[-i];
            } else {
                f = f2 * f98a[i];
            }
            return z ? -f : f;
        }
        long j2 = d.f94a[i - (-325)];
        long j3 = ((217706 * i) >> 16) + 127 + 64;
        int numberOfLeadingZeros = Long.numberOfLeadingZeros(j);
        long j4 = j << numberOfLeadingZeros;
        d.a a2 = d.a(j4, j2);
        long j5 = a2.f97b;
        long j6 = a2.f96a;
        long j7 = j6;
        if ((j6 & 274877906943L) == 274877906943L && Long.compareUnsigned(j5 + j4, j5) < 0) {
            d.a a3 = d.a(j4, d.f95b[i - (-325)]);
            long j8 = a3.f97b;
            long j9 = j7;
            long j10 = j5 + a3.f96a;
            if (Long.compareUnsigned(j10, j5) < 0) {
                j9++;
            }
            if (j10 + 1 == 0 && (j9 & 549755813887L) == 549755813887L && j8 + Long.compareUnsigned(j4, j8) < 0) {
                return Float.NaN;
            }
            j7 = j9;
        }
        long j11 = j7 >>> 63;
        long j12 = j7 >>> ((int) (j11 + 38));
        int i2 = numberOfLeadingZeros + ((int) (1 ^ j11));
        if ((j7 & 274877906943L) == 274877906943L) {
            return Float.NaN;
        }
        if ((j7 & 274877906943L) == 0 && (j12 & 3) == 1) {
            return Float.NaN;
        }
        long j13 = (j12 + 1) >>> 1;
        long j14 = j13;
        if (j13 >= 16777216) {
            j14 = 8388608;
            i2--;
        }
        long j15 = j14 & (-8388609);
        long j16 = j3 - i2;
        if (j16 < 1 || j16 > 254) {
            return Float.NaN;
        }
        return Float.intBitsToFloat((int) (j15 | (j16 << 23) | (z ? LibIOURing.IORING_OFF_PBUF_RING : 0L)));
    }

    private static float b(boolean z, long j, int i) {
        if (j == 0 || i < -180) {
            return z ? -0.0f : 0.0f;
        }
        if (i > 127) {
            return z ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
        }
        if (Long.compareUnsigned(j, 9007199254740991L) <= 0) {
            float scalb = ((float) j) * Math.scalb(1.0f, i);
            if (z) {
                scalb = -scalb;
            }
            return scalb;
        }
        return Float.NaN;
    }
}
