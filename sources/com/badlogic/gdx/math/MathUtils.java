package com.badlogic.gdx.math;

import java.util.Random;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/MathUtils.class */
public final class MathUtils {
    public static final float nanoToSec = 1.0E-9f;
    public static final float FLOAT_ROUNDING_ERROR = 1.0E-6f;
    public static final float PI = 3.1415927f;
    public static final float PI2 = 6.2831855f;
    public static final float HALF_PI = 1.5707964f;
    public static final float E = 2.7182817f;
    private static final int SIN_BITS = 14;
    private static final int SIN_MASK = 16383;
    private static final int SIN_COUNT = 16384;
    private static final float radFull = 6.2831855f;
    private static final float degFull = 360.0f;
    private static final float radToIndex = 2607.5945f;
    private static final float degToIndex = 45.511112f;
    public static final float radiansToDegrees = 57.295776f;
    public static final float radDeg = 57.295776f;
    public static final float degreesToRadians = 0.017453292f;
    public static final float degRad = 0.017453292f;
    public static Random random = new RandomXS128();
    private static final int BIG_ENOUGH_INT = 16384;
    private static final double BIG_ENOUGH_FLOOR = 16384.0d;
    private static final double CEIL = 0.9999999d;
    private static final double BIG_ENOUGH_CEIL = 16384.999999999996d;
    private static final double BIG_ENOUGH_ROUND = 16384.5d;

    private MathUtils() {
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/math/MathUtils$Sin.class */
    private static class Sin {
        static final float[] table = new float[16384];

        private Sin() {
        }

        static {
            for (int i = 0; i < 16384; i++) {
                table[i] = (float) Math.sin(((r1 + 0.5f) / 16384.0f) * 6.2831855f);
            }
            table[0] = 0.0f;
            table[4096] = 1.0f;
            table[8192] = 0.0f;
            table[12288] = -1.0f;
        }
    }

    public static float sin(float f) {
        return Sin.table[((int) (f * radToIndex)) & 16383];
    }

    public static float cos(float f) {
        return Sin.table[((int) ((f + 1.5707964f) * radToIndex)) & 16383];
    }

    public static float sinDeg(float f) {
        return Sin.table[((int) (f * degToIndex)) & 16383];
    }

    public static float cosDeg(float f) {
        return Sin.table[((int) ((f + 90.0f) * degToIndex)) & 16383];
    }

    public static float tan(float f) {
        float f2 = (f / 3.1415927f) + 0.5f;
        float floor = (((float) (f2 - Math.floor(f2))) - 0.5f) * 3.1415927f;
        float f3 = floor * floor;
        float f4 = f3 * f3;
        return (floor * (((0.0010582011f * f4) - (0.11111111f * f3)) + 1.0f)) / (((0.015873017f * f4) - (0.44444445f * f3)) + 1.0f);
    }

    public static float tanDeg(float f) {
        float f2 = (f * 0.0055555557f) + 0.5f;
        float floor = (((float) (f2 - Math.floor(f2))) - 0.5f) * 3.1415927f;
        float f3 = floor * floor;
        float f4 = f3 * f3;
        return (floor * (((0.0010582011f * f4) - (0.11111111f * f3)) + 1.0f)) / (((0.015873017f * f4) - (0.44444445f * f3)) + 1.0f);
    }

    public static float atanUnchecked(double d) {
        double abs = Math.abs(d);
        double d2 = (abs - 1.0d) / (abs + 1.0d);
        double d3 = d2 * d2;
        double d4 = d2 * d3;
        double d5 = d4 * d3;
        double d6 = d5 * d3;
        double d7 = d6 * d3;
        return (float) (Math.signum(d) * (0.7853981633974483d + ((((((d2 * 0.99997726d) - (d4 * 0.33262347d)) + (d5 * 0.19354346d)) - (d6 * 0.11643287d)) + (d7 * 0.05265332d)) - ((d7 * d3) * 0.0117212d))));
    }

    public static float atan2(float f, float f2) {
        float f3 = f / f2;
        float f4 = f3;
        if (f3 != f4) {
            f4 = f == f2 ? 1.0f : -1.0f;
        } else if (f4 - f4 != f4 - f4) {
            f2 = 0.0f;
        }
        if (f2 > 0.0f) {
            return atanUnchecked(f4);
        }
        if (f2 < 0.0f) {
            return f >= 0.0f ? atanUnchecked(f4) + 3.1415927f : atanUnchecked(f4) - 3.1415927f;
        }
        if (f > 0.0f) {
            return f2 + 1.5707964f;
        }
        return f < 0.0f ? f2 - 1.5707964f : f2 + f;
    }

    public static double atanUncheckedDeg(double d) {
        double abs = Math.abs(d);
        double d2 = (abs - 1.0d) / (abs + 1.0d);
        double d3 = d2 * d2;
        double d4 = d2 * d3;
        double d5 = d4 * d3;
        double d6 = d5 * d3;
        double d7 = d6 * d3;
        return Math.signum(d) * (45.0d + ((((((d2 * 57.2944766070562d) - (d4 * 19.05792099799635d)) + (d5 * 11.089223410359068d)) - (d6 * 6.6711120475953765d)) + (d7 * 3.016813013351768d)) - ((d7 * d3) * 0.6715752908287405d)));
    }

    public static float atan2Deg(float f, float f2) {
        float f3 = f / f2;
        float f4 = f3;
        if (f3 != f4) {
            f4 = f == f2 ? 1.0f : -1.0f;
        } else if (f4 - f4 != f4 - f4) {
            f2 = 0.0f;
        }
        if (f2 > 0.0f) {
            return (float) atanUncheckedDeg(f4);
        }
        if (f2 < 0.0f) {
            return f >= 0.0f ? (float) (atanUncheckedDeg(f4) + 180.0d) : (float) (atanUncheckedDeg(f4) - 180.0d);
        }
        if (f > 0.0f) {
            return f2 + 90.0f;
        }
        return f < 0.0f ? f2 - 90.0f : f2 + f;
    }

    public static float atan2Deg360(float f, float f2) {
        float f3 = f / f2;
        float f4 = f3;
        if (f3 != f4) {
            f4 = f == f2 ? 1.0f : -1.0f;
        } else if (f4 - f4 != f4 - f4) {
            f2 = 0.0f;
        }
        if (f2 > 0.0f) {
            if (f >= 0.0f) {
                return (float) atanUncheckedDeg(f4);
            }
            return (float) (atanUncheckedDeg(f4) + 360.0d);
        }
        if (f2 < 0.0f) {
            return (float) (atanUncheckedDeg(f4) + 180.0d);
        }
        if (f > 0.0f) {
            return f2 + 90.0f;
        }
        return f < 0.0f ? f2 + 270.0f : f2 + f;
    }

    public static float acos(float f) {
        float f2 = f * f;
        float f3 = f * f2;
        if (f >= 0.0f) {
            return ((float) Math.sqrt(1.0f - f)) * (((1.5707288f - (0.2121144f * f)) + (0.074261f * f2)) - (0.0187293f * f3));
        }
        return 3.1415927f - (((float) Math.sqrt(f + 1.0f)) * (((1.5707288f + (0.2121144f * f)) + (0.074261f * f2)) + (0.0187293f * f3)));
    }

    public static float asin(float f) {
        float f2 = f * f;
        float f3 = f * f2;
        if (f >= 0.0f) {
            return 1.5707964f - (((float) Math.sqrt(1.0f - f)) * (((1.5707288f - (0.2121144f * f)) + (0.074261f * f2)) - (0.0187293f * f3)));
        }
        return (-1.5707964f) + (((float) Math.sqrt(f + 1.0f)) * (1.5707288f + (0.2121144f * f) + (0.074261f * f2) + (0.0187293f * f3)));
    }

    public static float atan(float f) {
        double min = Math.min(Math.abs(f), Double.MAX_VALUE);
        double d = (min - 1.0d) / (min + 1.0d);
        double d2 = d * d;
        double d3 = d * d2;
        double d4 = d3 * d2;
        double d5 = d4 * d2;
        double d6 = d5 * d2;
        return Math.signum(f) * ((float) (0.7853981633974483d + ((((((d * 0.99997726d) - (d3 * 0.33262347d)) + (d4 * 0.19354346d)) - (d5 * 0.11643287d)) + (d6 * 0.05265332d)) - ((d6 * d2) * 0.0117212d))));
    }

    public static float asinDeg(float f) {
        float f2 = f * f;
        float f3 = f * f2;
        if (f >= 0.0f) {
            return 90.0f - (((float) Math.sqrt(1.0f - f)) * (((89.99613f - (12.15326f * f)) + (4.254842f * f2)) - (1.0731099f * f3)));
        }
        return (((float) Math.sqrt(f + 1.0f)) * (((89.99613f + (12.15326f * f)) + (4.254842f * f2)) + (1.0731099f * f3))) - 90.0f;
    }

    public static float acosDeg(float f) {
        float f2 = f * f;
        float f3 = f * f2;
        if (f >= 0.0f) {
            return ((float) Math.sqrt(1.0f - f)) * (((89.99613f - (12.153259f * f)) + (4.254842f * f2)) - (1.0731097f * f3));
        }
        return 180.0f - (((float) Math.sqrt(f + 1.0f)) * (((89.99613f + (12.153259f * f)) + (4.254842f * f2)) + (1.0731097f * f3)));
    }

    public static float atanDeg(float f) {
        double min = Math.min(Math.abs(f), Double.MAX_VALUE);
        double d = (min - 1.0d) / (min + 1.0d);
        double d2 = d * d;
        double d3 = d * d2;
        double d4 = d3 * d2;
        double d5 = d4 * d2;
        double d6 = d5 * d2;
        return (float) (Math.signum(f) * (45.0d + ((((((d * 57.2944766070562d) - (d3 * 19.05792099799635d)) + (d4 * 11.089223410359068d)) - (d5 * 6.6711120475953765d)) + (d6 * 3.016813013351768d)) - ((d6 * d2) * 0.6715752908287405d))));
    }

    public static int random(int i) {
        return random.nextInt(i + 1);
    }

    public static int random(int i, int i2) {
        return i + random.nextInt((i2 - i) + 1);
    }

    public static long random(long j) {
        return random(0L, j);
    }

    public static long random(long j, long j2) {
        long nextLong = random.nextLong();
        if (j2 < j) {
            j2 = j;
            j = j2;
        }
        long j3 = (j2 - j) + 1;
        long j4 = nextLong & 4294967295L;
        long j5 = j3 & 4294967295L;
        long j6 = nextLong >>> 32;
        long j7 = j3 >>> 32;
        return j + ((j6 * j5) >>> 32) + ((j4 * j7) >>> 32) + (j6 * j7);
    }

    public static boolean randomBoolean() {
        return random.nextBoolean();
    }

    public static boolean randomBoolean(float f) {
        return random() < f;
    }

    public static float random() {
        return random.nextFloat();
    }

    public static float random(float f) {
        return random.nextFloat() * f;
    }

    public static float random(float f, float f2) {
        return f + (random.nextFloat() * (f2 - f));
    }

    public static int randomSign() {
        return 1 | (random.nextInt() >> 31);
    }

    public static float randomTriangular() {
        return random.nextFloat() - random.nextFloat();
    }

    public static float randomTriangular(float f) {
        return (random.nextFloat() - random.nextFloat()) * f;
    }

    public static float randomTriangular(float f, float f2) {
        return randomTriangular(f, f2, (f + f2) * 0.5f);
    }

    public static float randomTriangular(float f, float f2, float f3) {
        return random.nextFloat() <= (f3 - f) / (f2 - f) ? f + ((float) Math.sqrt(r0 * r0 * (f3 - f))) : f2 - ((float) Math.sqrt(((1.0f - r0) * r0) * (f2 - f3)));
    }

    public static int nextPowerOfTwo(int i) {
        if (i == 0) {
            return 1;
        }
        int i2 = i - 1;
        int i3 = i2 | (i2 >> 1);
        int i4 = i3 | (i3 >> 2);
        int i5 = i4 | (i4 >> 4);
        int i6 = i5 | (i5 >> 8);
        return (i6 | (i6 >> 16)) + 1;
    }

    public static boolean isPowerOfTwo(int i) {
        return i != 0 && (i & (i - 1)) == 0;
    }

    public static short clamp(short s, short s2, short s3) {
        return s < s2 ? s2 : s > s3 ? s3 : s;
    }

    public static int clamp(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    public static long clamp(long j, long j2, long j3) {
        return j < j2 ? j2 : j > j3 ? j3 : j;
    }

    public static float clamp(float f, float f2, float f3) {
        return f < f2 ? f2 : f > f3 ? f3 : f;
    }

    public static double clamp(double d, double d2, double d3) {
        return d < d2 ? d2 : d > d3 ? d3 : d;
    }

    public static float lerp(float f, float f2, float f3) {
        return f + ((f2 - f) * f3);
    }

    public static float norm(float f, float f2, float f3) {
        return (f3 - f) / (f2 - f);
    }

    public static float map(float f, float f2, float f3, float f4, float f5) {
        return f3 + (((f5 - f) * (f4 - f3)) / (f2 - f));
    }

    public static float lerpAngle(float f, float f2, float f3) {
        return (((f + (((((((f2 - f) % 6.2831855f) + 6.2831855f) + 3.1415927f) % 6.2831855f) - 3.1415927f) * f3)) % 6.2831855f) + 6.2831855f) % 6.2831855f;
    }

    public static float lerpAngleDeg(float f, float f2, float f3) {
        return (((f + (((((((f2 - f) % degFull) + degFull) + 180.0f) % degFull) - 180.0f) * f3)) % degFull) + degFull) % degFull;
    }

    public static int floor(float f) {
        return ((int) (f + BIG_ENOUGH_FLOOR)) - 16384;
    }

    public static int floorPositive(float f) {
        return (int) f;
    }

    public static int ceil(float f) {
        return 16384 - ((int) (BIG_ENOUGH_FLOOR - f));
    }

    public static int ceilPositive(float f) {
        return (int) (f + CEIL);
    }

    public static int round(float f) {
        return ((int) (f + BIG_ENOUGH_ROUND)) - 16384;
    }

    public static int roundPositive(float f) {
        return (int) (f + 0.5f);
    }

    public static boolean isZero(float f) {
        return Math.abs(f) <= 1.0E-6f;
    }

    public static boolean isZero(float f, float f2) {
        return Math.abs(f) <= f2;
    }

    public static boolean isEqual(float f, float f2) {
        return Math.abs(f - f2) <= 1.0E-6f;
    }

    public static boolean isEqual(float f, float f2, float f3) {
        return Math.abs(f - f2) <= f3;
    }

    public static float log(float f, float f2) {
        return (float) (Math.log(f2) / Math.log(f));
    }

    public static float log2(float f) {
        return log(2.0f, f);
    }
}
