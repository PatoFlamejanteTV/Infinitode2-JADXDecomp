package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.NumberUtils;
import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Vector3.class */
public class Vector3 implements Vector<Vector3>, Serializable {
    private static final long serialVersionUID = 3840054589595372522L;
    public float x;
    public float y;
    public float z;
    public static final Vector3 X = new Vector3(1.0f, 0.0f, 0.0f);
    public static final Vector3 Y = new Vector3(0.0f, 1.0f, 0.0f);
    public static final Vector3 Z = new Vector3(0.0f, 0.0f, 1.0f);
    public static final Vector3 Zero = new Vector3(0.0f, 0.0f, 0.0f);
    private static final Matrix4 tmpMat = new Matrix4();

    public Vector3() {
    }

    public Vector3(float f, float f2, float f3) {
        set(f, f2, f3);
    }

    public Vector3(Vector3 vector3) {
        set(vector3);
    }

    public Vector3(float[] fArr) {
        set(fArr[0], fArr[1], fArr[2]);
    }

    public Vector3(Vector2 vector2, float f) {
        set(vector2.x, vector2.y, f);
    }

    public Vector3 set(float f, float f2, float f3) {
        this.x = f;
        this.y = f2;
        this.z = f3;
        return this;
    }

    @Override // com.badlogic.gdx.math.Vector
    public Vector3 set(Vector3 vector3) {
        return set(vector3.x, vector3.y, vector3.z);
    }

    public Vector3 set(float[] fArr) {
        return set(fArr[0], fArr[1], fArr[2]);
    }

    public Vector3 set(Vector2 vector2, float f) {
        return set(vector2.x, vector2.y, f);
    }

    public Vector3 setFromSpherical(float f, float f2) {
        float cos = MathUtils.cos(f2);
        float sin = MathUtils.sin(f2);
        return set(MathUtils.cos(f) * sin, MathUtils.sin(f) * sin, cos);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.math.Vector
    public Vector3 setToRandomDirection() {
        return setFromSpherical(6.2831855f * MathUtils.random(), (float) Math.acos((MathUtils.random() * 2.0f) - 1.0f));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.math.Vector
    public Vector3 cpy() {
        return new Vector3(this);
    }

    @Override // com.badlogic.gdx.math.Vector
    public Vector3 add(Vector3 vector3) {
        return add(vector3.x, vector3.y, vector3.z);
    }

    public Vector3 add(float f, float f2, float f3) {
        return set(this.x + f, this.y + f2, this.z + f3);
    }

    public Vector3 add(float f) {
        return set(this.x + f, this.y + f, this.z + f);
    }

    @Override // com.badlogic.gdx.math.Vector
    public Vector3 sub(Vector3 vector3) {
        return sub(vector3.x, vector3.y, vector3.z);
    }

    public Vector3 sub(float f, float f2, float f3) {
        return set(this.x - f, this.y - f2, this.z - f3);
    }

    public Vector3 sub(float f) {
        return set(this.x - f, this.y - f, this.z - f);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.math.Vector
    public Vector3 scl(float f) {
        return set(this.x * f, this.y * f, this.z * f);
    }

    @Override // com.badlogic.gdx.math.Vector
    public Vector3 scl(Vector3 vector3) {
        return set(this.x * vector3.x, this.y * vector3.y, this.z * vector3.z);
    }

    public Vector3 scl(float f, float f2, float f3) {
        return set(this.x * f, this.y * f2, this.z * f3);
    }

    @Override // com.badlogic.gdx.math.Vector
    public Vector3 mulAdd(Vector3 vector3, float f) {
        this.x += vector3.x * f;
        this.y += vector3.y * f;
        this.z += vector3.z * f;
        return this;
    }

    @Override // com.badlogic.gdx.math.Vector
    public Vector3 mulAdd(Vector3 vector3, Vector3 vector32) {
        this.x += vector3.x * vector32.x;
        this.y += vector3.y * vector32.y;
        this.z += vector3.z * vector32.z;
        return this;
    }

    public static float len(float f, float f2, float f3) {
        return (float) Math.sqrt((f * f) + (f2 * f2) + (f3 * f3));
    }

    @Override // com.badlogic.gdx.math.Vector
    public float len() {
        return (float) Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z));
    }

    public static float len2(float f, float f2, float f3) {
        return (f * f) + (f2 * f2) + (f3 * f3);
    }

    @Override // com.badlogic.gdx.math.Vector
    public float len2() {
        return (this.x * this.x) + (this.y * this.y) + (this.z * this.z);
    }

    public boolean idt(Vector3 vector3) {
        return this.x == vector3.x && this.y == vector3.y && this.z == vector3.z;
    }

    public static float dst(float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = f4 - f;
        float f8 = f5 - f2;
        float f9 = f6 - f3;
        return (float) Math.sqrt((f7 * f7) + (f8 * f8) + (f9 * f9));
    }

    @Override // com.badlogic.gdx.math.Vector
    public float dst(Vector3 vector3) {
        float f = vector3.x - this.x;
        float f2 = vector3.y - this.y;
        float f3 = vector3.z - this.z;
        return (float) Math.sqrt((f * f) + (f2 * f2) + (f3 * f3));
    }

    public float dst(float f, float f2, float f3) {
        float f4 = f - this.x;
        float f5 = f2 - this.y;
        float f6 = f3 - this.z;
        return (float) Math.sqrt((f4 * f4) + (f5 * f5) + (f6 * f6));
    }

    public static float dst2(float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = f4 - f;
        float f8 = f5 - f2;
        float f9 = f6 - f3;
        return (f7 * f7) + (f8 * f8) + (f9 * f9);
    }

    @Override // com.badlogic.gdx.math.Vector
    public float dst2(Vector3 vector3) {
        float f = vector3.x - this.x;
        float f2 = vector3.y - this.y;
        float f3 = vector3.z - this.z;
        return (f * f) + (f2 * f2) + (f3 * f3);
    }

    public float dst2(float f, float f2, float f3) {
        float f4 = f - this.x;
        float f5 = f2 - this.y;
        float f6 = f3 - this.z;
        return (f4 * f4) + (f5 * f5) + (f6 * f6);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.math.Vector
    public Vector3 nor() {
        float len2 = len2();
        return (len2 == 0.0f || len2 == 1.0f) ? this : scl(1.0f / ((float) Math.sqrt(len2)));
    }

    public static float dot(float f, float f2, float f3, float f4, float f5, float f6) {
        return (f * f4) + (f2 * f5) + (f3 * f6);
    }

    @Override // com.badlogic.gdx.math.Vector
    public float dot(Vector3 vector3) {
        return (this.x * vector3.x) + (this.y * vector3.y) + (this.z * vector3.z);
    }

    public float dot(float f, float f2, float f3) {
        return (this.x * f) + (this.y * f2) + (this.z * f3);
    }

    public Vector3 crs(Vector3 vector3) {
        return set((this.y * vector3.z) - (this.z * vector3.y), (this.z * vector3.x) - (this.x * vector3.z), (this.x * vector3.y) - (this.y * vector3.x));
    }

    public Vector3 crs(float f, float f2, float f3) {
        return set((this.y * f3) - (this.z * f2), (this.z * f) - (this.x * f3), (this.x * f2) - (this.y * f));
    }

    public Vector3 mul4x3(float[] fArr) {
        return set((this.x * fArr[0]) + (this.y * fArr[3]) + (this.z * fArr[6]) + fArr[9], (this.x * fArr[1]) + (this.y * fArr[4]) + (this.z * fArr[7]) + fArr[10], (this.x * fArr[2]) + (this.y * fArr[5]) + (this.z * fArr[8]) + fArr[11]);
    }

    public Vector3 mul(Matrix4 matrix4) {
        float[] fArr = matrix4.val;
        return set((this.x * fArr[0]) + (this.y * fArr[4]) + (this.z * fArr[8]) + fArr[12], (this.x * fArr[1]) + (this.y * fArr[5]) + (this.z * fArr[9]) + fArr[13], (this.x * fArr[2]) + (this.y * fArr[6]) + (this.z * fArr[10]) + fArr[14]);
    }

    public Vector3 traMul(Matrix4 matrix4) {
        float[] fArr = matrix4.val;
        return set((this.x * fArr[0]) + (this.y * fArr[1]) + (this.z * fArr[2]) + fArr[3], (this.x * fArr[4]) + (this.y * fArr[5]) + (this.z * fArr[6]) + fArr[7], (this.x * fArr[8]) + (this.y * fArr[9]) + (this.z * fArr[10]) + fArr[11]);
    }

    public Vector3 mul(Matrix3 matrix3) {
        float[] fArr = matrix3.val;
        return set((this.x * fArr[0]) + (this.y * fArr[3]) + (this.z * fArr[6]), (this.x * fArr[1]) + (this.y * fArr[4]) + (this.z * fArr[7]), (this.x * fArr[2]) + (this.y * fArr[5]) + (this.z * fArr[8]));
    }

    public Vector3 traMul(Matrix3 matrix3) {
        float[] fArr = matrix3.val;
        return set((this.x * fArr[0]) + (this.y * fArr[1]) + (this.z * fArr[2]), (this.x * fArr[3]) + (this.y * fArr[4]) + (this.z * fArr[5]), (this.x * fArr[6]) + (this.y * fArr[7]) + (this.z * fArr[8]));
    }

    public Vector3 mul(Quaternion quaternion) {
        return quaternion.transform(this);
    }

    public Vector3 prj(Matrix4 matrix4) {
        float[] fArr = matrix4.val;
        float f = 1.0f / ((((this.x * fArr[3]) + (this.y * fArr[7])) + (this.z * fArr[11])) + fArr[15]);
        return set(((this.x * fArr[0]) + (this.y * fArr[4]) + (this.z * fArr[8]) + fArr[12]) * f, ((this.x * fArr[1]) + (this.y * fArr[5]) + (this.z * fArr[9]) + fArr[13]) * f, ((this.x * fArr[2]) + (this.y * fArr[6]) + (this.z * fArr[10]) + fArr[14]) * f);
    }

    public Vector3 rot(Matrix4 matrix4) {
        float[] fArr = matrix4.val;
        return set((this.x * fArr[0]) + (this.y * fArr[4]) + (this.z * fArr[8]), (this.x * fArr[1]) + (this.y * fArr[5]) + (this.z * fArr[9]), (this.x * fArr[2]) + (this.y * fArr[6]) + (this.z * fArr[10]));
    }

    public Vector3 unrotate(Matrix4 matrix4) {
        float[] fArr = matrix4.val;
        return set((this.x * fArr[0]) + (this.y * fArr[1]) + (this.z * fArr[2]), (this.x * fArr[4]) + (this.y * fArr[5]) + (this.z * fArr[6]), (this.x * fArr[8]) + (this.y * fArr[9]) + (this.z * fArr[10]));
    }

    public Vector3 untransform(Matrix4 matrix4) {
        float[] fArr = matrix4.val;
        this.x -= fArr[12];
        this.y -= fArr[12];
        this.z -= fArr[12];
        return set((this.x * fArr[0]) + (this.y * fArr[1]) + (this.z * fArr[2]), (this.x * fArr[4]) + (this.y * fArr[5]) + (this.z * fArr[6]), (this.x * fArr[8]) + (this.y * fArr[9]) + (this.z * fArr[10]));
    }

    public Vector3 rotate(float f, float f2, float f3, float f4) {
        return mul(tmpMat.setToRotation(f2, f3, f4, f));
    }

    public Vector3 rotateRad(float f, float f2, float f3, float f4) {
        return mul(tmpMat.setToRotationRad(f2, f3, f4, f));
    }

    public Vector3 rotate(Vector3 vector3, float f) {
        tmpMat.setToRotation(vector3, f);
        return mul(tmpMat);
    }

    public Vector3 rotateRad(Vector3 vector3, float f) {
        tmpMat.setToRotationRad(vector3, f);
        return mul(tmpMat);
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
        return this.x == 0.0f && this.y == 0.0f && this.z == 0.0f;
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean isZero(float f) {
        return len2() < f;
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean isOnLine(Vector3 vector3, float f) {
        return len2((this.y * vector3.z) - (this.z * vector3.y), (this.z * vector3.x) - (this.x * vector3.z), (this.x * vector3.y) - (this.y * vector3.x)) <= f;
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean isOnLine(Vector3 vector3) {
        return len2((this.y * vector3.z) - (this.z * vector3.y), (this.z * vector3.x) - (this.x * vector3.z), (this.x * vector3.y) - (this.y * vector3.x)) <= 1.0E-6f;
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean isCollinear(Vector3 vector3, float f) {
        return isOnLine(vector3, f) && hasSameDirection(vector3);
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean isCollinear(Vector3 vector3) {
        return isOnLine(vector3) && hasSameDirection(vector3);
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean isCollinearOpposite(Vector3 vector3, float f) {
        return isOnLine(vector3, f) && hasOppositeDirection(vector3);
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean isCollinearOpposite(Vector3 vector3) {
        return isOnLine(vector3) && hasOppositeDirection(vector3);
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean isPerpendicular(Vector3 vector3) {
        return MathUtils.isZero(dot(vector3));
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean isPerpendicular(Vector3 vector3, float f) {
        return MathUtils.isZero(dot(vector3), f);
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean hasSameDirection(Vector3 vector3) {
        return dot(vector3) > 0.0f;
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean hasOppositeDirection(Vector3 vector3) {
        return dot(vector3) < 0.0f;
    }

    @Override // com.badlogic.gdx.math.Vector
    public Vector3 lerp(Vector3 vector3, float f) {
        this.x += f * (vector3.x - this.x);
        this.y += f * (vector3.y - this.y);
        this.z += f * (vector3.z - this.z);
        return this;
    }

    @Override // com.badlogic.gdx.math.Vector
    public Vector3 interpolate(Vector3 vector3, float f, Interpolation interpolation) {
        return lerp(vector3, interpolation.apply(0.0f, 1.0f, f));
    }

    public Vector3 slerp(Vector3 vector3, float f) {
        float dot = dot(vector3);
        if (dot > 0.9995d || dot < -0.9995d) {
            return lerp(vector3, f);
        }
        float acos = ((float) Math.acos(dot)) * f;
        float sin = (float) Math.sin(acos);
        float f2 = vector3.x - (this.x * dot);
        float f3 = vector3.y - (this.y * dot);
        float f4 = vector3.z - (this.z * dot);
        float f5 = (f2 * f2) + (f3 * f3) + (f4 * f4);
        float sqrt = sin * (f5 < 1.0E-4f ? 1.0f : 1.0f / ((float) Math.sqrt(f5)));
        return scl((float) Math.cos(acos)).add(f2 * sqrt, f3 * sqrt, f4 * sqrt).nor();
    }

    public String toString() {
        return "(" + this.x + "," + this.y + "," + this.z + ")";
    }

    public Vector3 fromString(String str) {
        int indexOf = str.indexOf(44, 1);
        int indexOf2 = str.indexOf(44, indexOf + 1);
        if (indexOf != -1 && indexOf2 != -1 && str.charAt(0) == '(' && str.charAt(str.length() - 1) == ')') {
            try {
                return set(Float.parseFloat(str.substring(1, indexOf)), Float.parseFloat(str.substring(indexOf + 1, indexOf2)), Float.parseFloat(str.substring(indexOf2 + 1, str.length() - 1)));
            } catch (NumberFormatException unused) {
            }
        }
        throw new GdxRuntimeException("Malformed Vector3: " + str);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.math.Vector
    public Vector3 limit(float f) {
        return limit2(f * f);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.math.Vector
    public Vector3 limit2(float f) {
        if (len2() > f) {
            scl((float) Math.sqrt(f / r0));
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.math.Vector
    public Vector3 setLength(float f) {
        return setLength2(f * f);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.math.Vector
    public Vector3 setLength2(float f) {
        float len2 = len2();
        return (len2 == 0.0f || len2 == f) ? this : scl((float) Math.sqrt(f / len2));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.math.Vector
    public Vector3 clamp(float f, float f2) {
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
        return ((((31 + NumberUtils.floatToIntBits(this.x)) * 31) + NumberUtils.floatToIntBits(this.y)) * 31) + NumberUtils.floatToIntBits(this.z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vector3 vector3 = (Vector3) obj;
        return NumberUtils.floatToIntBits(this.x) == NumberUtils.floatToIntBits(vector3.x) && NumberUtils.floatToIntBits(this.y) == NumberUtils.floatToIntBits(vector3.y) && NumberUtils.floatToIntBits(this.z) == NumberUtils.floatToIntBits(vector3.z);
    }

    @Override // com.badlogic.gdx.math.Vector
    public boolean epsilonEquals(Vector3 vector3, float f) {
        return vector3 != null && Math.abs(vector3.x - this.x) <= f && Math.abs(vector3.y - this.y) <= f && Math.abs(vector3.z - this.z) <= f;
    }

    public boolean epsilonEquals(float f, float f2, float f3, float f4) {
        return Math.abs(f - this.x) <= f4 && Math.abs(f2 - this.y) <= f4 && Math.abs(f3 - this.z) <= f4;
    }

    public boolean epsilonEquals(Vector3 vector3) {
        return epsilonEquals(vector3, 1.0E-6f);
    }

    public boolean epsilonEquals(float f, float f2, float f3) {
        return epsilonEquals(f, f2, f3, 1.0E-6f);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.math.Vector
    public Vector3 setZero() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        return this;
    }
}
