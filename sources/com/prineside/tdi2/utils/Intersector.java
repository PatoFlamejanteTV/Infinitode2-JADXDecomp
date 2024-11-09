package com.prineside.tdi2.utils;

import com.badlogic.gdx.math.Vector2;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/Intersector.class */
public final class Intersector {
    private Intersector() {
    }

    public static boolean intersectSegmentRect(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (intersectSegmentSegment(f, f2, f3, f4, f5, f6, f5, f8) || intersectSegmentSegment(f, f2, f3, f4, f5, f6, f7, f6) || intersectSegmentSegment(f, f2, f3, f4, f7, f6, f7, f8) || intersectSegmentSegment(f, f2, f3, f4, f5, f8, f7, f8)) {
            return true;
        }
        return isPointInRect(f, f2, f5, f6, f7, f8);
    }

    public static boolean isPointInRect(float f, float f2, float f3, float f4, float f5, float f6) {
        return f >= f3 && f <= f5 && f2 >= f4 && f2 <= f6;
    }

    public static boolean rectanglesOverlap(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        return f < f7 && f2 < f8 && f3 > f5 && f4 > f6;
    }

    public static boolean intersectSegmentSegment(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        float f9 = ((f8 - f6) * (f3 - f)) - ((f7 - f5) * (f4 - f2));
        if (f9 == 0.0f) {
            return false;
        }
        float f10 = f2 - f6;
        float f11 = f - f5;
        float f12 = (((f7 - f5) * f10) - ((f8 - f6) * f11)) / f9;
        if (f12 < 0.0f || f12 > 1.0f) {
            return false;
        }
        float f13 = (((f3 - f) * f10) - ((f4 - f2) * f11)) / f9;
        return f13 >= 0.0f && f13 <= 1.0f;
    }

    public static boolean intersectSegmentCircle(float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        float f8;
        float f9;
        float f10 = f3 - f;
        float f11 = f4 - f2;
        float f12 = f5 - f;
        float f13 = f6 - f2;
        float sqrt = (float) Math.sqrt((f10 * f10) + (f11 * f11));
        float f14 = f10;
        float f15 = f11;
        if (sqrt != 0.0f) {
            f14 = f10 / sqrt;
            f15 = f11 / sqrt;
        }
        float f16 = (f12 * f14) + (f13 * f15);
        if (f16 <= 0.0f) {
            f8 = f;
            f9 = f2;
        } else if (f16 < sqrt) {
            f8 = (f14 * f16) + f;
            f9 = (f15 * f16) + f2;
        } else {
            f8 = f3;
            f9 = f4;
        }
        float f17 = f5 - f8;
        float f18 = f6 - f9;
        return (f17 * f17) + (f18 * f18) <= f7;
    }

    public static boolean intersectSegmentCircleV(Vector2 vector2, Vector2 vector22, Vector2 vector23, float f) {
        return intersectSegmentCircle(vector2.x, vector2.y, vector22.x, vector22.y, vector23.x, vector23.y, f);
    }
}
