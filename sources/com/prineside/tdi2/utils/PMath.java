package com.prineside.tdi2.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.ObjectSet;
import com.esotericsoftware.kryo.io.Input;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameListener;
import com.prineside.tdi2.ListenerGroup;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/PMath.class */
public class PMath {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3874a = TLog.forClass(PMath.class);

    /* renamed from: b, reason: collision with root package name */
    private static final ObjectSet<Class> f3875b;
    private static final String[] c;

    static {
        ObjectSet<Class> objectSet = new ObjectSet<>();
        f3875b = objectSet;
        objectSet.add(Character.class);
        f3875b.add(Byte.class);
        f3875b.add(Short.class);
        f3875b.add(Long.class);
        f3875b.add(Float.class);
        f3875b.add(Integer.class);
        f3875b.add(Double.class);
        f3875b.add(Boolean.class);
        f3875b.add(String.class);
        c = new String[8192];
    }

    public static Color abgr8888ToColor(int i) {
        Color color = new Color();
        color.f889a = ((i & (-16777216)) >>> 24) / 255.0f;
        color.f888b = ((i >>> 16) & 255) / 255.0f;
        color.g = ((i >>> 8) & 255) / 255.0f;
        color.r = (i & 255) / 255.0f;
        return color;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static boolean isFinite(float f) {
        return (Float.isInfinite(f) || Float.isNaN(f)) ? false : true;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static boolean isFinite(double d) {
        return (Double.isInfinite(d) || Double.isNaN(d)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/PMath$Sin.class */
    public static class Sin {

        /* renamed from: a, reason: collision with root package name */
        static final float[] f3876a = new float[8192];

        private Sin() {
        }

        static {
            for (int i = 0; i < 8192; i++) {
                f3876a[i] = (float) StrictMath.sin(((r1 + 0.5f) / 8192.0f) * 6.2831855f);
            }
            for (int i2 = 0; i2 < 360; i2 += 90) {
                f3876a[((int) (i2 * 22.755556f)) & 8191] = (float) StrictMath.sin(i2 * 0.017453292f);
            }
        }
    }

    public static void removeArrayIndicesDirect(Array<?> array, IntArray intArray) {
        Object[] objArr = array.items;
        int i = array.size;
        for (int i2 = 0; i2 < intArray.size; i2++) {
            i--;
            objArr[intArray.items[i2]] = objArr[i];
        }
        array.size = i;
        int i3 = i;
        Arrays.fill(objArr, i3, i3 + intArray.size, (Object) null);
    }

    public static float sin(float f) {
        return Sin.f3876a[((int) (f * 1303.7972f)) & 8191];
    }

    public static float cos(float f) {
        return Sin.f3876a[((int) ((f + 1.5707964f) * 1303.7972f)) & 8191];
    }

    public static float sinDeg(float f) {
        return Sin.f3876a[((int) (f * 22.755556f)) & 8191];
    }

    public static float cosDeg(float f) {
        return Sin.f3876a[((int) ((f + 90.0f) * 22.755556f)) & 8191];
    }

    public static long generateNewId() {
        return (Game.getTimestampSeconds() << 32) + FastRandom.random.nextInt();
    }

    public static String toString(int i) {
        if (i >= 0 && i < c.length) {
            String str = c[i];
            if (str == null) {
                c[i] = Integer.toString(i);
                return c[i];
            }
            return str;
        }
        return Integer.toString(i);
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static int getByChance(RandomXS128 randomXS128, int[] iArr, int i) {
        if (i % 2 != 0) {
            throw new IllegalArgumentException("chances must contain pairs (chance, index)");
        }
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3 += 2) {
            i2 += iArr[i3];
        }
        int nextInt = randomXS128.nextInt(i2);
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5 += 2) {
            if (nextInt < i4 + iArr[i5]) {
                return iArr[i5 + 1];
            }
            i4 += iArr[i5];
        }
        throw new RuntimeException("Something gone wrong");
    }

    public static Date addDays(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, i);
        return calendar.getTime();
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static int getByChance(RandomXS128 randomXS128, IntArray intArray) {
        return getByChance(randomXS128, intArray.items, intArray.size);
    }

    public static float loopedDistance(float f, float f2, float f3) {
        float f4 = (((f % f3) + f3) % f3) - (((f2 % f3) + f3) % f3);
        float f5 = f4 - f3;
        float f6 = f4 + f3;
        float abs = StrictMath.abs(f4);
        float abs2 = StrictMath.abs(f5);
        float abs3 = StrictMath.abs(f6);
        if (abs <= abs2 && abs <= abs3) {
            return f4;
        }
        if (abs2 <= abs && abs2 <= abs3) {
            return f5;
        }
        return f6;
    }

    public static float randomTriangular(RandomXS128 randomXS128) {
        return randomXS128.nextFloat() - randomXS128.nextFloat();
    }

    public static float randomTriangularMax(float f, RandomXS128 randomXS128) {
        return (randomXS128.nextFloat() - randomXS128.nextFloat()) * f;
    }

    public static float randomTriangularMinMax(float f, float f2, RandomXS128 randomXS128) {
        return randomTriangularMinMaxMode(f, f2, (f + f2) * 0.5f, randomXS128);
    }

    public static float randomTriangularMinMaxMode(float f, float f2, float f3, RandomXS128 randomXS128) {
        return randomXS128.nextFloat() <= (f3 - f) / (f2 - f) ? f + ((float) StrictMath.sqrt(r0 * r0 * (f3 - f))) : f2 - ((float) StrictMath.sqrt(((1.0f - r0) * r0) * (f2 - f3)));
    }

    public static void getBezierCurvePos(Vector2 vector2, Vector2 vector22, Vector2 vector23, Vector2 vector24, float f) {
        float f2 = ((vector23.x - vector22.x) * f) + vector22.x;
        float f3 = ((vector23.y - vector22.y) * f) + vector22.y;
        vector2.x = vector24.x;
        vector2.y = vector24.y;
        vector2.sub(vector23);
        vector2.x *= f;
        vector2.y *= f;
        vector2.add(vector23);
        vector2.sub(f2, f3);
        vector2.x *= f;
        vector2.y *= f;
        vector2.add(f2, f3);
    }

    public static void interpolatePoint(Vector2 vector2, Vector2 vector22, float f) {
        vector2.x += (vector22.x - vector2.x) * f;
        vector2.y += (vector22.y - vector2.y) * f;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static float getDistanceBetweenPoints(float f, float f2, float f3, float f4) {
        return (float) StrictMath.sqrt(((f - f3) * (f - f3)) + ((f2 - f4) * (f2 - f4)));
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static float getDistanceBetweenPoints(Vector2 vector2, Vector2 vector22) {
        return (float) StrictMath.sqrt(((vector2.x - vector22.x) * (vector2.x - vector22.x)) + ((vector2.y - vector22.y) * (vector2.y - vector22.y)));
    }

    public static float getSquareDistanceBetweenPoints(float f, float f2, float f3, float f4) {
        return ((f - f3) * (f - f3)) + ((f2 - f4) * (f2 - f4));
    }

    public static float normalizeAngle(float f) {
        return ((f % 360.0f) + 360.0f) % 360.0f;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static float getAngleBetweenPoints(float f, float f2, float f3, float f4) {
        return (57.295776f * MathUtils.atan2(f4 - f2, f3 - f)) - 90.0f;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static float getAngleBetweenPoints(Vector2 vector2, Vector2 vector22) {
        return (57.295776f * MathUtils.atan2(vector22.y - vector2.y, vector22.x - vector2.x)) - 90.0f;
    }

    public static float getDistanceBetweenAngles(float f, float f2) {
        float normalizeAngle = normalizeAngle(f2) - normalizeAngle(f);
        if (normalizeAngle < -180.0f) {
            return normalizeAngle + 360.0f;
        }
        if (normalizeAngle > 180.0f) {
            return normalizeAngle - 360.0f;
        }
        return normalizeAngle;
    }

    public static int addWithoutOverflow(int i, int i2) {
        if (willAdditionOverflow(i, i2)) {
            return Integer.MAX_VALUE;
        }
        return i + i2;
    }

    public static int multiplyWithoutOverflow(int i, int i2) {
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("left and right must be positive, " + i + " and " + i2 + " given");
        }
        long j = i * i2;
        if (j > 2147483647L || j < 0) {
            return Integer.MAX_VALUE;
        }
        return i * i2;
    }

    public static boolean willAdditionOverflow(int i, int i2) {
        if (i2 >= 0 || i2 == Integer.MIN_VALUE) {
            return (((i ^ i2) ^ (-1)) & (i ^ (i + i2))) < 0;
        }
        return willSubtractionOverflow(i, -i2);
    }

    public static boolean willSubtractionOverflow(int i, int i2) {
        if (i2 < 0) {
            return willAdditionOverflow(i, -i2);
        }
        return ((i ^ i2) & (i ^ (i - i2))) < 0;
    }

    public static void getPointByAngleFromPoint(float f, float f2, float f3, float f4, Vector2 vector2) {
        float f5 = 0.017453292f * f3;
        vector2.x = f + ((-sin(f5)) * f4);
        vector2.y = f2 + (f4 * cos(f5));
    }

    public static void shiftPointByAngle(Vector2 vector2, float f, float f2) {
        float f3 = 0.017453292f * f;
        vector2.x += (-sin(f3)) * f2;
        vector2.y += f2 * cos(f3);
    }

    public static boolean circleIntersectsRect(float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        return StrictMath.pow((double) (f - MathUtils.clamp(f, f4, f4 + f6)), 2.0d) + StrictMath.pow((double) (f2 - MathUtils.clamp(f2, f5, f5 + f7)), 2.0d) < ((double) (f3 * f3));
    }

    public static boolean circleIntersectsCircle(float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = f - f4;
        float f8 = f2 - f5;
        return (f3 + f6) * (f3 + f6) > (f7 * f7) + (f8 * f8);
    }

    public static boolean circleIntersectsCircleV(Vector2 vector2, float f, Vector2 vector22, float f2) {
        return circleIntersectsCircle(vector2.x, vector2.y, f, vector22.x, vector22.y, f2);
    }

    private static boolean a(float f, float f2, float f3, float f4, float f5) {
        if (f5 == 0.0f) {
            return false;
        }
        float f6 = f3 - f;
        float f7 = f4 - f2;
        return (f6 * f6) + (f7 * f7) <= f5;
    }

    public static boolean getLineCircleIntersection(Vector2 vector2, Vector2 vector22, Vector2 vector23, float f, Vector2 vector24) {
        return getLineCircleIntersectionFloats(vector2.x, vector2.y, vector22.x, vector22.y, vector23.x, vector23.y, f, vector24);
    }

    public static boolean getLineCircleIntersectionFloats(float f, float f2, float f3, float f4, float f5, float f6, float f7, Vector2 vector2) {
        if (a(f, f2, f5, f6, f7)) {
            vector2.x = f;
            vector2.y = f2;
            return true;
        }
        float f8 = f3 - f;
        float f9 = f4 - f2;
        float f10 = f5 - f;
        float f11 = f6 - f2;
        float f12 = (f8 * f8) + (f9 * f9);
        float f13 = f8;
        float f14 = f9;
        if (f12 > 0.0f) {
            float f15 = ((f10 * f8) + (f11 * f9)) / f12;
            f13 = f8 * f15;
            f14 = f9 * f15;
        }
        vector2.x = f + f13;
        vector2.y = f2 + f14;
        float f16 = f13;
        float f17 = f14;
        return a(vector2.x, vector2.y, f5, f6, f7) && (f16 * f16) + (f17 * f17) <= f12 && (f13 * f8) + (f14 * f9) >= 0.0f;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static int hash(int i) {
        return i;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static int hash(float f) {
        return hash(Float.floatToIntBits(f));
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static int hash(long j) {
        return hash(((int) (j >> 32)) + ((int) j));
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static int hash(String str) {
        int i = 1;
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            i = (i * 31) + hash((int) str.charAt(i2));
        }
        return i;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static int hash(double d) {
        return hash(Double.doubleToLongBits(d));
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static int hash(boolean z) {
        return z ? 1 : 0;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static int hash(Vector2 vector2) {
        return (hash(vector2.x) * 31) + hash(vector2.y);
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static int hash(Enum r2) {
        if (r2 == null) {
            return -1;
        }
        return r2.ordinal();
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static int hash(Array[] arrayArr) {
        int i = 1;
        for (Array array : arrayArr) {
            i = (i * 31) + array.size;
        }
        return i;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static int hash(boolean[] zArr) {
        int i = 1;
        for (boolean z : zArr) {
            i = (i * 31) + (z ? 1 : 0);
        }
        return i;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static int hash(byte[] bArr) {
        int i = 1;
        for (byte b2 : bArr) {
            i = (i * 31) + b2;
        }
        return i;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static int hash(int[] iArr) {
        int i = 1;
        for (int i2 : iArr) {
            i = (i * 31) + i2;
        }
        return i;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static int hash(IntArray intArray) {
        int i = 1;
        for (int i2 = 0; i2 < intArray.size; i2++) {
            i = (i * 31) + intArray.items[i2];
        }
        return i;
    }

    public static int intHash(byte[] bArr, int i, int i2) {
        int i3 = 1;
        int i4 = i + i2;
        for (int i5 = i; i5 < i4; i5++) {
            i3 = (i3 * 23) + bArr[i5];
        }
        return i3;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static int hash(float[] fArr) {
        int i = 1;
        for (float f : fArr) {
            i = (i * 11) + hash(f);
        }
        return i;
    }

    public static int parseUnsignedInt(String str, int i) {
        if (str == null) {
            throw new NumberFormatException("null");
        }
        int length = str.length();
        if (length > 0) {
            if (str.charAt(0) == '-') {
                throw new NumberFormatException(String.format("Illegal leading minus sign on unsigned string %s.", str));
            }
            if (length <= 5 || (i == 10 && length <= 9)) {
                return Integer.parseInt(str, i);
            }
            long parseLong = Long.parseLong(str, i);
            if ((parseLong & (-4294967296L)) == 0) {
                return (int) parseLong;
            }
            throw new NumberFormatException(String.format("String value %s exceeds range of unsigned int.", str));
        }
        throw new IllegalArgumentException("Invalid input: " + str);
    }

    public static int hashGameListeners(ListenerGroup listenerGroup) {
        int i = 1;
        listenerGroup.begin();
        for (int i2 = 0; i2 < listenerGroup.size(); i2++) {
            GameListener gameListener = listenerGroup.get(i2);
            if (gameListener.affectsGameState()) {
                i = (i * 31) + hash(gameListener.getConstantId());
            }
        }
        listenerGroup.end();
        return hash(i);
    }

    public static boolean compareFingerprints(String str, Enum[] enumArr, byte[] bArr, Input input) {
        boolean z = false;
        for (Enum r0 : enumArr) {
            if (input.readByte() != bArr[r0.ordinal()]) {
                f3874a.d(r0.name(), new Object[0]);
                z = true;
            }
        }
        return z;
    }
}
