package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.NumberUtils;
import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Vector4.class */
public class Vector4 implements Vector<Vector4>, Serializable {
    private static final long serialVersionUID = -5394070284130414492L;
    public float x;
    public float y;
    public float z;
    public float w;
    public static final Vector4 X = new Vector4(1.0f, 0.0f, 0.0f, 0.0f);
    public static final Vector4 Y = new Vector4(0.0f, 1.0f, 0.0f, 0.0f);
    public static final Vector4 Z = new Vector4(0.0f, 0.0f, 1.0f, 0.0f);
    public static final Vector4 W = new Vector4(0.0f, 0.0f, 0.0f, 1.0f);
    public static final Vector4 Zero = new Vector4(0.0f, 0.0f, 0.0f, 0.0f);

    public Vector4() {
    }

    public Vector4(float f, float f2, float f3, float f4) {
        set(f, f2, f3, f4);
    }

    public Vector4(Vector4 vector4) {
        set(vector4.x, vector4.y, vector4.z, vector4.w);
    }

    public Vector4(float[] fArr) {
        set(fArr[0], fArr[1], fArr[2], fArr[3]);
    }

    public Vector4(Vector2 vector2, float f, float f2) {
        set(vector2.x, vector2.y, f, f2);
    }

    public Vector4(Vector3 vector3, float f) {
        set(vector3.x, vector3.y, vector3.z, f);
    }

    public Vector4 set(float f, float f2, float f3, float f4) {
        this.x = f;
        this.y = f2;
        this.z = f3;
        this.w = f4;
        return this;
    }

    @Override // com.badlogic.gdx.math.Vector
    public Vector4 set(Vector4 vector4) {
        return set(vector4.x, vector4.y, vector4.z, vector4.w);
    }

    public Vector4 set(float[] fArr) {
        return set(fArr[0], fArr[1], fArr[2], fArr[3]);
    }

    public Vector4 set(Vector2 vector2, float f, float f2) {
        return set(vector2.x, vector2.y, f, f2);
    }

    public Vector4 set(Vector3 vector3, float f) {
        return set(vector3.x, vector3.y, vector3.z, f);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.math.Vector
    public Vector4 setToRandomDirection() {
        float random;
        float random2;
        float f;
        while (true) {
            random = (MathUtils.random() - 0.5f) * 2.0f;
            random2 = (MathUtils.random() - 0.5f) * 2.0f;
            f = (random * random) + (random2 * random2);
            if (f < 1.0f && f != 0.0f) {
                break;
            }
        }
        float sqrt = (float) Math.sqrt(((-2.0d) * Math.log(f)) / f);
        this.x = random * sqrt;
        this.y = random2 * sqrt;
        while (true) {
            float random3 = (MathUtils.random() - 0.5f) * 2.0f;
            float random4 = (MathUtils.random() - 0.5f) * 2.0f;
            float f2 = (random3 * random3) + (random4 * random4);
            if (f2 < 1.0f && f2 != 0.0f) {
                float sqrt2 = (float) Math.sqrt(((-2.0d) * Math.log(f2)) / f2);
                this.z = random3 * sqrt2;
                this.w = random4 * sqrt2;
                return nor();
            }
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.math.Vector
    public Vector4 cpy() {
        return new Vector4(this);
    }

    @Override // com.badlogic.gdx.math.Vector
    public Vector4 add(Vector4 vector4) {
        return add(vector4.x, vector4.y, vector4.z, vector4.w);
    }

    public Vector4 add(float f, float f2, float f3, float f4) {
        return set(this.x + f, this.y + f2, this.z + f3, this.w + f4);
    }

    public Vector4 add(float f) {
        return set(this.x + f, this.y + f, this.z + f, this.w + f);
    }

    @Override // com.badlogic.gdx.math.Vector
    public Vector4 sub(Vector4 vector4) {
        return sub(vector4.x, vector4.y, vector4.z, vector4.w);
    }

    public Vector4 sub(float f, float f2, float f3, float f4) {
        return set(this.x - f, this.y - f2, this.z - f3, this.w - f4);
    }

    public Vector4 sub(float f) {
        return set(this.x - f, this.y - f, this.z - f, this.w - f);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.math.Vector
    public Vector4 scl(float f) {
        return set(this.x * f, this.y * f, this.z * f, this.w * f);
    }

    @Override // com.badlogic.gdx.math.Vector
    public Vector4 scl(Vector4 vector4) {
        return set(this.x * vector4.x, this.y * vector4.y, this.z * vector4.z, this.w * vector4.w);
    }

    public Vector4 scl(float f, float f2, float f3, float f4) {
        return set(this.x * f, this.y * f2, this.z * f3, this.w * f4);
    }

    @Override // com.badlogic.gdx.math.Vector
    public Vector4 mulAdd(Vector4 vector4, float f) {
        this.x += vector4.x * f;
        this.y += vector4.y * f;
        this.z += vector4.z * f;
        this.w += vector4.w * f;
        return this;
    }

    @Override // com.badlogic.gdx.math.Vector
    public Vector4 mulAdd(Vector4 vector4, Vector4 vector42) {
        this.x += vector4.x * vector42.x;
        this.y += vector4.y * vector42.y;
        this.z += vector4.z * vector42.z;
        this.w += vector4.w * vector42.w;
        return this;
    }

    public static float len(float f, float f2, float f3, float f4) {
        return (float) Math.sqrt((f * f) + (f2 * f2) + (f3 * f3) + (f4 * f4));
    }

    @Override // com.badlogic.gdx.math.Vector
    public float len() {
        return (float) Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z) + (this.w * this.w));
    }

    public static float len2(float f, float f2, float f3, float f4) {
        return (f * f) + (f2 * f2) + (f3 * f3) + (f4 * f4);
    }

    @Override // com.badlogic.gdx.math.Vector
    public float len2() {
        return (this.x * this.x) + (this.y * this.y) + (this.z * this.z) + (this.w * this.w);
    }

    public boolean idt(Vector4 vector4) {
        return this.x == vector4.x && this.y == vector4.y && this.z == vector4.z && this.w == vector4.w;
    }

    public static float dst(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        float f9 = f5 - f;
        float f10 = f6 - f2;
        float f11 = f7 - f3;
        float f12 = f8 - f4;
        return (float) Math.sqrt((f9 * f9) + (f10 * f10) + (f11 * f11) + (f12 * f12));
    }

    @Override // com.badlogic.gdx.math.Vector
    public float dst(Vector4 vector4) {
        float f = vector4.x - this.x;
        float f2 = vector4.y - this.y;
        float f3 = vector4.z - this.z;
        float f4 = vector4.w - this.w;
        return (float) Math.sqrt((f * f) + (f2 * f2) + (f3 * f3) + (f4 * f4));
    }

    public float dst(float f, float f2, float f3, float f4) {
        float f5 = f - this.x;
        float f6 = f2 - this.y;
        float f7 = f3 - this.z;
        float f8 = f4 - this.w;
        return (float) Math.sqrt((f5 * f5) + (f6 * f6) + (f7 * f7) + (f8 * f8));
    }

    public static float dst2(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        float f9 = f5 - f;
        float f10 = f6 - f2;
        float f11 = f7 - f3;
        float f12 = f8 - f4;
        return (f9 * f9) + (f10 * f10) + (f11 * f11) + (f12 * f12);
    }

    @Override // com.badlogic.gdx.math.Vector
    public float dst2(Vector4 vector4) {
        float f = vector4.x - this.x;
        float f2 = vector4.y - this.y;
        float f3 = vector4.z - this.z;
        float f4 = vector4.w - this.w;
        return (f * f) + (f2 * f2) + (f3 * f3) + (f4 * f4);
    }

    public float dst2(float f, float f2, float f3, float f4) {
        float f5 = f - this.x;
        float f6 = f2 - this.y;
        float f7 = f3 - this.z;
        float f8 = f4 - this.w;
        return (f5 * f5) + (f6 * f6) + (f7 * f7) + (f8 * f8);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.math.Vector
    public Vector4 nor() {
        float len2 = len2();
        return (len2 == 0.0f || len2 == 1.0f) ? this : scl(1.0f / ((float) Math.sqrt(len2)));
    }

    public static float dot(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        return (f * f5) + (f2 * f6) + (f3 * f7) + (f4 * f8);
    }

    @Override // com.badlogic.gdx.math.Vector
    public float dot(Vector4 vector4) {
        return (this.x * vector4.x) + (this.y * vector4.y) + (this.z * vector4.z) + (this.w * vector4.w);
    }

    public float dot(float f, float f2, float f3, float f4) {
        return (this.x * f) + (this.y * f2) + (this.z * f3) + (this.w * f4);
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean isUnit() {
        return isUnit(1.0E-9f);
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean isUnit(float f) {
        return Math.abs(len2() - 1.0f) < f;
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean isZero() {
        return this.x == 0.0f && this.y == 0.0f && this.z == 0.0f && this.w == 0.0f;
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean isZero(float f) {
        return len2() < f;
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean isOnLine(Vector4 vector4, float f) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = false;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        if (MathUtils.isZero(this.x, f)) {
            if (!MathUtils.isZero(vector4.x, f)) {
                return false;
            }
        } else {
            f2 = this.x / vector4.x;
            z4 = true;
        }
        if (MathUtils.isZero(this.y, f)) {
            z = z4;
            if (!MathUtils.isZero(vector4.y, f)) {
                return false;
            }
        } else {
            f3 = this.y / vector4.y;
            z = ((z4 ? 1 : 0) | 2) == true ? 1 : 0;
        }
        if (MathUtils.isZero(this.z, f)) {
            z2 = z;
            if (!MathUtils.isZero(vector4.z, f)) {
                return false;
            }
        } else {
            f4 = this.z / vector4.z;
            z2 = ((z ? 1 : 0) | 4) == true ? 1 : 0;
        }
        if (MathUtils.isZero(this.w, f)) {
            z3 = z2;
            if (!MathUtils.isZero(vector4.w, f)) {
                return false;
            }
        } else {
            f5 = this.w / vector4.w;
            z3 = ((z2 ? 1 : 0) | 8) == true ? 1 : 0;
        }
        switch (z3) {
            case false:
            case true:
            case true:
            case true:
            case true:
                return true;
            case true:
                return MathUtils.isEqual(f2, f3, f);
            case true:
                return MathUtils.isEqual(f2, f4, f);
            case true:
                return MathUtils.isEqual(f3, f4, f);
            case true:
                return MathUtils.isEqual(f2, f3, f) && MathUtils.isEqual(f2, f4, f);
            case true:
                return MathUtils.isEqual(f2, f5, f);
            case true:
                return MathUtils.isEqual(f3, f5, f);
            case true:
                return MathUtils.isEqual(f2, f3, f) && MathUtils.isEqual(f2, f5, f);
            case true:
                return MathUtils.isEqual(f4, f5, f);
            case true:
                return MathUtils.isEqual(f2, f4, f) && MathUtils.isEqual(f2, f5, f);
            case true:
                return MathUtils.isEqual(f3, f4, f) && MathUtils.isEqual(f3, f5, f);
            default:
                return MathUtils.isEqual(f2, f3, f) && MathUtils.isEqual(f2, f4, f) && MathUtils.isEqual(f2, f5, f);
        }
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean isOnLine(Vector4 vector4) {
        return isOnLine(vector4, 1.0E-6f);
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean isCollinear(Vector4 vector4, float f) {
        return isOnLine(vector4, f) && hasSameDirection(vector4);
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean isCollinear(Vector4 vector4) {
        return isOnLine(vector4) && hasSameDirection(vector4);
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean isCollinearOpposite(Vector4 vector4, float f) {
        return isOnLine(vector4, f) && hasOppositeDirection(vector4);
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean isCollinearOpposite(Vector4 vector4) {
        return isOnLine(vector4) && hasOppositeDirection(vector4);
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean isPerpendicular(Vector4 vector4) {
        return MathUtils.isZero(dot(vector4));
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean isPerpendicular(Vector4 vector4, float f) {
        return MathUtils.isZero(dot(vector4), f);
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean hasSameDirection(Vector4 vector4) {
        return dot(vector4) > 0.0f;
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean hasOppositeDirection(Vector4 vector4) {
        return dot(vector4) < 0.0f;
    }

    @Override // com.badlogic.gdx.math.Vector
    public Vector4 lerp(Vector4 vector4, float f) {
        this.x += f * (vector4.x - this.x);
        this.y += f * (vector4.y - this.y);
        this.z += f * (vector4.z - this.z);
        this.w += f * (vector4.w - this.w);
        return this;
    }

    @Override // com.badlogic.gdx.math.Vector
    public Vector4 interpolate(Vector4 vector4, float f, Interpolation interpolation) {
        return lerp(vector4, interpolation.apply(f));
    }

    public String toString() {
        return "(" + this.x + "," + this.y + "," + this.z + "," + this.w + ")";
    }

    public Vector4 fromString(String str) {
        int indexOf = str.indexOf(44, 1);
        int indexOf2 = str.indexOf(44, indexOf + 1);
        int indexOf3 = str.indexOf(44, indexOf2 + 1);
        if (indexOf != -1 && indexOf2 != -1 && str.charAt(0) == '(' && str.charAt(str.length() - 1) == ')') {
            try {
                return set(Float.parseFloat(str.substring(1, indexOf)), Float.parseFloat(str.substring(indexOf + 1, indexOf2)), Float.parseFloat(str.substring(indexOf2 + 1, indexOf3)), Float.parseFloat(str.substring(indexOf3 + 1, str.length() - 1)));
            } catch (NumberFormatException unused) {
            }
        }
        throw new GdxRuntimeException("Malformed Vector4: " + str);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.math.Vector
    public Vector4 limit(float f) {
        return limit2(f * f);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.math.Vector
    public Vector4 limit2(float f) {
        if (len2() > f) {
            scl((float) Math.sqrt(f / r0));
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.math.Vector
    public Vector4 setLength(float f) {
        return setLength2(f * f);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.math.Vector
    public Vector4 setLength2(float f) {
        float len2 = len2();
        return (len2 == 0.0f || len2 == f) ? this : scl((float) Math.sqrt(f / len2));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.math.Vector
    public Vector4 clamp(float f, float f2) {
        float len2 = len2();
        if (len2 == 0.0f) {
            return this;
        }
        if (len2 > f2 * f2) {
            return scl((float) Math.sqrt(r0 / len2));
        }
        if (len2 < f * f) {
            return scl((float) Math.sqrt(r0 / len2));
        }
        return this;
    }

    public int hashCode() {
        return ((((((31 + NumberUtils.floatToIntBits(this.x)) * 31) + NumberUtils.floatToIntBits(this.y)) * 31) + NumberUtils.floatToIntBits(this.z)) * 31) + NumberUtils.floatToIntBits(this.w);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vector4 vector4 = (Vector4) obj;
        return NumberUtils.floatToIntBits(this.x) == NumberUtils.floatToIntBits(vector4.x) && NumberUtils.floatToIntBits(this.y) == NumberUtils.floatToIntBits(vector4.y) && NumberUtils.floatToIntBits(this.z) == NumberUtils.floatToIntBits(vector4.z) && NumberUtils.floatToIntBits(this.w) == NumberUtils.floatToIntBits(vector4.w);
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean epsilonEquals(Vector4 vector4, float f) {
        return vector4 != null && Math.abs(vector4.x - this.x) <= f && Math.abs(vector4.y - this.y) <= f && Math.abs(vector4.z - this.z) <= f && Math.abs(vector4.w - this.w) <= f;
    }

    public boolean epsilonEquals(float f, float f2, float f3, float f4, float f5) {
        return Math.abs(f - this.x) <= f5 && Math.abs(f2 - this.y) <= f5 && Math.abs(f3 - this.z) <= f5 && Math.abs(f4 - this.w) <= f5;
    }

    public boolean epsilonEquals(Vector4 vector4) {
        return epsilonEquals(vector4, 1.0E-6f);
    }

    public boolean epsilonEquals(float f, float f2, float f3, float f4) {
        return epsilonEquals(f, f2, f3, f4, 1.0E-6f);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.math.Vector
    public Vector4 setZero() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.w = 0.0f;
        return this;
    }
}
