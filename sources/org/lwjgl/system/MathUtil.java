package org.lwjgl.system;

import android.R;

/* loaded from: infinitode-2.jar:org/lwjgl/system/MathUtil.class */
public final class MathUtil {
    private MathUtil() {
    }

    public static boolean mathIsPoT(int i) {
        return Integer.bitCount(i) == 1;
    }

    public static int mathRoundPoT(int i) {
        return 1 << (32 - Integer.numberOfLeadingZeros(i - 1));
    }

    public static boolean mathHasZeroByte(int i) {
        return (((i - R.attr.cacheColorHint) & (i ^ (-1))) & (-2139062144)) != 0;
    }

    public static boolean mathHasZeroByte(long j) {
        return (((j - 72340172838076673L) & (j ^ (-1))) & (-9187201950435737472L)) != 0;
    }

    public static boolean mathHasZeroShort(int i) {
        return (((i - 65537) & (i ^ (-1))) & (-2147450880)) != 0;
    }

    public static boolean mathHasZeroShort(long j) {
        return (((j - 281479271743489L) & (j ^ (-1))) & (-9223231297218904064L)) != 0;
    }

    public static long mathMultiplyHighU64(long j, long j2) {
        long j3 = j & 4294967295L;
        long j4 = j >>> 32;
        long j5 = j2 & 4294967295L;
        long j6 = j2 >>> 32;
        long j7 = (j4 * j5) + ((j3 * j5) >>> 32);
        return (j4 * j6) + (j7 >>> 32) + (((j7 & 4294967295L) + (j3 * j6)) >>> 32);
    }

    public static long mathMultiplyHighS64(long j, long j2) {
        long j3 = j & 4294967295L;
        long j4 = j >> 32;
        long j5 = j2 & 4294967295L;
        long j6 = j2 >> 32;
        long j7 = (j4 * j5) + ((j3 * j5) >>> 32);
        return (j4 * j6) + (j7 >> 32) + (((j7 & 4294967295L) + (j3 * j6)) >> 32);
    }

    public static long mathDivideUnsigned(long j, long j2) {
        return 0 <= j2 ? 0 <= j ? j / j2 : udivdi3(j, j2) : Long.compareUnsigned(j, j2) < 0 ? 0L : 1L;
    }

    public static long mathRemainderUnsigned(long j, long j2) {
        if (0 >= j || 0 >= j2) {
            return Long.compareUnsigned(j, j2) < 0 ? j : j - (j2 * udivdi3(j, j2));
        }
        return j % j2;
    }

    private static long udivdi3(long j, long j2) {
        if ((j2 >>> 32) != 0) {
            int numberOfLeadingZeros = Long.numberOfLeadingZeros(j2);
            long j3 = (((j >>> 1) / ((j2 << numberOfLeadingZeros) >>> 32)) << numberOfLeadingZeros) >>> 31;
            long j4 = j3;
            if (j3 != 0) {
                j4--;
            }
            if (Long.compareUnsigned(j - (j4 * j2), j2) >= 0) {
                j4++;
            }
            return j4;
        }
        if ((j >>> 32) < j2) {
            long numberOfLeadingZeros2 = (((j >>> 1) / j2) << Long.numberOfLeadingZeros(j2)) >>> 31;
            if (j - (numberOfLeadingZeros2 * j2) >= j2) {
                numberOfLeadingZeros2++;
            }
            return numberOfLeadingZeros2;
        }
        long j5 = j >>> 32;
        long j6 = j5 / j2;
        return (j6 << 32) | ((((j5 - (j6 * j2)) << 32) | (j & 4294967295L)) / j2);
    }
}
