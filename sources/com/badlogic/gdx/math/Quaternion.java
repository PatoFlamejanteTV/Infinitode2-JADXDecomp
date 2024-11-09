package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.NumberUtils;
import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Quaternion.class */
public class Quaternion implements Serializable {
    private static final long serialVersionUID = -7661875440774897168L;
    private static Quaternion tmp1 = new Quaternion(0.0f, 0.0f, 0.0f, 0.0f);
    private static Quaternion tmp2 = new Quaternion(0.0f, 0.0f, 0.0f, 0.0f);
    public float x;
    public float y;
    public float z;
    public float w;

    public Quaternion(float f, float f2, float f3, float f4) {
        set(f, f2, f3, f4);
    }

    public Quaternion() {
        idt();
    }

    public Quaternion(Quaternion quaternion) {
        set(quaternion);
    }

    public Quaternion(Vector3 vector3, float f) {
        set(vector3, f);
    }

    public Quaternion set(float f, float f2, float f3, float f4) {
        this.x = f;
        this.y = f2;
        this.z = f3;
        this.w = f4;
        return this;
    }

    public Quaternion set(Quaternion quaternion) {
        return set(quaternion.x, quaternion.y, quaternion.z, quaternion.w);
    }

    public Quaternion set(Vector3 vector3, float f) {
        return setFromAxis(vector3.x, vector3.y, vector3.z, f);
    }

    public Quaternion cpy() {
        return new Quaternion(this);
    }

    public static final float len(float f, float f2, float f3, float f4) {
        return (float) Math.sqrt((f * f) + (f2 * f2) + (f3 * f3) + (f4 * f4));
    }

    public float len() {
        return (float) Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z) + (this.w * this.w));
    }

    public String toString() {
        return "[" + this.x + "|" + this.y + "|" + this.z + "|" + this.w + "]";
    }

    public Quaternion setEulerAngles(float f, float f2, float f3) {
        return setEulerAnglesRad(f * 0.017453292f, f2 * 0.017453292f, f3 * 0.017453292f);
    }

    public Quaternion setEulerAnglesRad(float f, float f2, float f3) {
        float f4 = f3 * 0.5f;
        float sin = (float) Math.sin(f4);
        float cos = (float) Math.cos(f4);
        float f5 = f2 * 0.5f;
        float sin2 = (float) Math.sin(f5);
        float cos2 = (float) Math.cos(f5);
        float f6 = f * 0.5f;
        float sin3 = (float) Math.sin(f6);
        float cos3 = (float) Math.cos(f6);
        float f7 = cos3 * sin2;
        float f8 = sin3 * cos2;
        float f9 = cos3 * cos2;
        float f10 = sin3 * sin2;
        this.x = (f7 * cos) + (f8 * sin);
        this.y = (f8 * cos) - (f7 * sin);
        this.z = (f9 * sin) - (f10 * cos);
        this.w = (f9 * cos) + (f10 * sin);
        return this;
    }

    public int getGimbalPole() {
        float f = (this.y * this.x) + (this.z * this.w);
        if (f > 0.499f) {
            return 1;
        }
        return f < -0.499f ? -1 : 0;
    }

    public float getRollRad() {
        int gimbalPole = getGimbalPole();
        return gimbalPole == 0 ? MathUtils.atan2(2.0f * ((this.w * this.z) + (this.y * this.x)), 1.0f - (2.0f * ((this.x * this.x) + (this.z * this.z)))) : gimbalPole * 2.0f * MathUtils.atan2(this.y, this.w);
    }

    public float getRoll() {
        return getRollRad() * 57.295776f;
    }

    public float getPitchRad() {
        int gimbalPole = getGimbalPole();
        return gimbalPole == 0 ? (float) Math.asin(MathUtils.clamp(2.0f * ((this.w * this.x) - (this.z * this.y)), -1.0f, 1.0f)) : gimbalPole * 3.1415927f * 0.5f;
    }

    public float getPitch() {
        return getPitchRad() * 57.295776f;
    }

    public float getYawRad() {
        if (getGimbalPole() == 0) {
            return MathUtils.atan2(2.0f * ((this.y * this.w) + (this.x * this.z)), 1.0f - (2.0f * ((this.y * this.y) + (this.x * this.x))));
        }
        return 0.0f;
    }

    public float getYaw() {
        return getYawRad() * 57.295776f;
    }

    public static final float len2(float f, float f2, float f3, float f4) {
        return (f * f) + (f2 * f2) + (f3 * f3) + (f4 * f4);
    }

    public float len2() {
        return (this.x * this.x) + (this.y * this.y) + (this.z * this.z) + (this.w * this.w);
    }

    public Quaternion nor() {
        float len2 = len2();
        if (len2 != 0.0f && !MathUtils.isEqual(len2, 1.0f)) {
            float sqrt = (float) Math.sqrt(len2);
            this.w /= sqrt;
            this.x /= sqrt;
            this.y /= sqrt;
            this.z /= sqrt;
        }
        return this;
    }

    public Quaternion conjugate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        return this;
    }

    public Vector3 transform(Vector3 vector3) {
        tmp2.set(this);
        tmp2.conjugate();
        tmp2.mulLeft(tmp1.set(vector3.x, vector3.y, vector3.z, 0.0f)).mulLeft(this);
        vector3.x = tmp2.x;
        vector3.y = tmp2.y;
        vector3.z = tmp2.z;
        return vector3;
    }

    public Quaternion mul(Quaternion quaternion) {
        float f = (((this.w * quaternion.x) + (this.x * quaternion.w)) + (this.y * quaternion.z)) - (this.z * quaternion.y);
        float f2 = (((this.w * quaternion.y) + (this.y * quaternion.w)) + (this.z * quaternion.x)) - (this.x * quaternion.z);
        float f3 = (((this.w * quaternion.z) + (this.z * quaternion.w)) + (this.x * quaternion.y)) - (this.y * quaternion.x);
        float f4 = (((this.w * quaternion.w) - (this.x * quaternion.x)) - (this.y * quaternion.y)) - (this.z * quaternion.z);
        this.x = f;
        this.y = f2;
        this.z = f3;
        this.w = f4;
        return this;
    }

    public Quaternion mul(float f, float f2, float f3, float f4) {
        float f5 = (((this.w * f) + (this.x * f4)) + (this.y * f3)) - (this.z * f2);
        float f6 = (((this.w * f2) + (this.y * f4)) + (this.z * f)) - (this.x * f3);
        float f7 = (((this.w * f3) + (this.z * f4)) + (this.x * f2)) - (this.y * f);
        float f8 = (((this.w * f4) - (this.x * f)) - (this.y * f2)) - (this.z * f3);
        this.x = f5;
        this.y = f6;
        this.z = f7;
        this.w = f8;
        return this;
    }

    public Quaternion mulLeft(Quaternion quaternion) {
        float f = (((quaternion.w * this.x) + (quaternion.x * this.w)) + (quaternion.y * this.z)) - (quaternion.z * this.y);
        float f2 = (((quaternion.w * this.y) + (quaternion.y * this.w)) + (quaternion.z * this.x)) - (quaternion.x * this.z);
        float f3 = (((quaternion.w * this.z) + (quaternion.z * this.w)) + (quaternion.x * this.y)) - (quaternion.y * this.x);
        float f4 = (((quaternion.w * this.w) - (quaternion.x * this.x)) - (quaternion.y * this.y)) - (quaternion.z * this.z);
        this.x = f;
        this.y = f2;
        this.z = f3;
        this.w = f4;
        return this;
    }

    public Quaternion mulLeft(float f, float f2, float f3, float f4) {
        float f5 = (((f4 * this.x) + (f * this.w)) + (f2 * this.z)) - (f3 * this.y);
        float f6 = (((f4 * this.y) + (f2 * this.w)) + (f3 * this.x)) - (f * this.z);
        float f7 = (((f4 * this.z) + (f3 * this.w)) + (f * this.y)) - (f2 * this.x);
        float f8 = (((f4 * this.w) - (f * this.x)) - (f2 * this.y)) - (f3 * this.z);
        this.x = f5;
        this.y = f6;
        this.z = f7;
        this.w = f8;
        return this;
    }

    public Quaternion add(Quaternion quaternion) {
        this.x += quaternion.x;
        this.y += quaternion.y;
        this.z += quaternion.z;
        this.w += quaternion.w;
        return this;
    }

    public Quaternion add(float f, float f2, float f3, float f4) {
        this.x += f;
        this.y += f2;
        this.z += f3;
        this.w += f4;
        return this;
    }

    public void toMatrix(float[] fArr) {
        float f = this.x * this.x;
        float f2 = this.x * this.y;
        float f3 = this.x * this.z;
        float f4 = this.x * this.w;
        float f5 = this.y * this.y;
        float f6 = this.y * this.z;
        float f7 = this.y * this.w;
        float f8 = this.z * this.z;
        float f9 = this.z * this.w;
        fArr[0] = 1.0f - (2.0f * (f5 + f8));
        fArr[4] = 2.0f * (f2 - f9);
        fArr[8] = 2.0f * (f3 + f7);
        fArr[12] = 0.0f;
        fArr[1] = 2.0f * (f2 + f9);
        fArr[5] = 1.0f - (2.0f * (f + f8));
        fArr[9] = 2.0f * (f6 - f4);
        fArr[13] = 0.0f;
        fArr[2] = 2.0f * (f3 - f7);
        fArr[6] = 2.0f * (f6 + f4);
        fArr[10] = 1.0f - (2.0f * (f + f5));
        fArr[14] = 0.0f;
        fArr[3] = 0.0f;
        fArr[7] = 0.0f;
        fArr[11] = 0.0f;
        fArr[15] = 1.0f;
    }

    public Quaternion idt() {
        return set(0.0f, 0.0f, 0.0f, 1.0f);
    }

    public boolean isIdentity() {
        return MathUtils.isZero(this.x) && MathUtils.isZero(this.y) && MathUtils.isZero(this.z) && MathUtils.isEqual(this.w, 1.0f);
    }

    public boolean isIdentity(float f) {
        return MathUtils.isZero(this.x, f) && MathUtils.isZero(this.y, f) && MathUtils.isZero(this.z, f) && MathUtils.isEqual(this.w, 1.0f, f);
    }

    public Quaternion setFromAxis(Vector3 vector3, float f) {
        return setFromAxis(vector3.x, vector3.y, vector3.z, f);
    }

    public Quaternion setFromAxisRad(Vector3 vector3, float f) {
        return setFromAxisRad(vector3.x, vector3.y, vector3.z, f);
    }

    public Quaternion setFromAxis(float f, float f2, float f3, float f4) {
        return setFromAxisRad(f, f2, f3, f4 * 0.017453292f);
    }

    public Quaternion setFromAxisRad(float f, float f2, float f3, float f4) {
        float len = Vector3.len(f, f2, f3);
        if (len == 0.0f) {
            return idt();
        }
        float f5 = 1.0f / len;
        float f6 = f4 < 0.0f ? 6.2831855f - ((-f4) % 6.2831855f) : f4 % 6.2831855f;
        float sin = (float) Math.sin(r0 / 2.0f);
        return set(f5 * f * sin, f5 * f2 * sin, f5 * f3 * sin, (float) Math.cos(f6 / 2.0f)).nor();
    }

    public Quaternion setFromMatrix(boolean z, Matrix4 matrix4) {
        return setFromAxes(z, matrix4.val[0], matrix4.val[4], matrix4.val[8], matrix4.val[1], matrix4.val[5], matrix4.val[9], matrix4.val[2], matrix4.val[6], matrix4.val[10]);
    }

    public Quaternion setFromMatrix(Matrix4 matrix4) {
        return setFromMatrix(false, matrix4);
    }

    public Quaternion setFromMatrix(boolean z, Matrix3 matrix3) {
        return setFromAxes(z, matrix3.val[0], matrix3.val[3], matrix3.val[6], matrix3.val[1], matrix3.val[4], matrix3.val[7], matrix3.val[2], matrix3.val[5], matrix3.val[8]);
    }

    public Quaternion setFromMatrix(Matrix3 matrix3) {
        return setFromMatrix(false, matrix3);
    }

    public Quaternion setFromAxes(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        return setFromAxes(false, f, f2, f3, f4, f5, f6, f7, f8, f9);
    }

    public Quaternion setFromAxes(boolean z, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        if (z) {
            float len = 1.0f / Vector3.len(f, f2, f3);
            float len2 = 1.0f / Vector3.len(f4, f5, f6);
            float len3 = 1.0f / Vector3.len(f7, f8, f9);
            f *= len;
            f2 *= len;
            f3 *= len;
            f4 *= len2;
            f5 *= len2;
            f6 *= len2;
            f7 *= len3;
            f8 *= len3;
            f9 *= len3;
        }
        if (f + f5 + f9 >= 0.0f) {
            float sqrt = (float) Math.sqrt(r0 + 1.0f);
            this.w = 0.5f * sqrt;
            float f10 = 0.5f / sqrt;
            this.x = (f8 - f6) * f10;
            this.y = (f3 - f7) * f10;
            this.z = (f4 - f2) * f10;
        } else if (f > f5 && f > f9) {
            float sqrt2 = (float) Math.sqrt(((1.0d + f) - f5) - f9);
            this.x = sqrt2 * 0.5f;
            float f11 = 0.5f / sqrt2;
            this.y = (f4 + f2) * f11;
            this.z = (f3 + f7) * f11;
            this.w = (f8 - f6) * f11;
        } else if (f5 > f9) {
            float sqrt3 = (float) Math.sqrt(((1.0d + f5) - f) - f9);
            this.y = sqrt3 * 0.5f;
            float f12 = 0.5f / sqrt3;
            this.x = (f4 + f2) * f12;
            this.z = (f8 + f6) * f12;
            this.w = (f3 - f7) * f12;
        } else {
            float sqrt4 = (float) Math.sqrt(((1.0d + f9) - f) - f5);
            this.z = sqrt4 * 0.5f;
            float f13 = 0.5f / sqrt4;
            this.x = (f3 + f7) * f13;
            this.y = (f8 + f6) * f13;
            this.w = (f4 - f2) * f13;
        }
        return this;
    }

    public Quaternion setFromCross(Vector3 vector3, Vector3 vector32) {
        return setFromAxisRad((vector3.y * vector32.z) - (vector3.z * vector32.y), (vector3.z * vector32.x) - (vector3.x * vector32.z), (vector3.x * vector32.y) - (vector3.y * vector32.x), (float) Math.acos(MathUtils.clamp(vector3.dot(vector32), -1.0f, 1.0f)));
    }

    public Quaternion setFromCross(float f, float f2, float f3, float f4, float f5, float f6) {
        return setFromAxisRad((f2 * f6) - (f3 * f5), (f3 * f4) - (f * f6), (f * f5) - (f2 * f4), (float) Math.acos(MathUtils.clamp(Vector3.dot(f, f2, f3, f4, f5, f6), -1.0f, 1.0f)));
    }

    public Quaternion slerp(Quaternion quaternion, float f) {
        float f2 = (this.x * quaternion.x) + (this.y * quaternion.y) + (this.z * quaternion.z) + (this.w * quaternion.w);
        float f3 = f2 < 0.0f ? -f2 : f2;
        float f4 = 1.0f - f;
        float f5 = f;
        if (1.0f - f3 > 0.1d) {
            float sin = 1.0f / ((float) Math.sin((float) Math.acos(f3)));
            f4 = ((float) Math.sin(f4 * r0)) * sin;
            f5 = ((float) Math.sin(f * r0)) * sin;
        }
        if (f2 < 0.0f) {
            f5 = -f5;
        }
        this.x = (f4 * this.x) + (f5 * quaternion.x);
        this.y = (f4 * this.y) + (f5 * quaternion.y);
        this.z = (f4 * this.z) + (f5 * quaternion.z);
        this.w = (f4 * this.w) + (f5 * quaternion.w);
        return this;
    }

    public Quaternion slerp(Quaternion[] quaternionArr) {
        float length = 1.0f / quaternionArr.length;
        set(quaternionArr[0]).exp(length);
        for (int i = 1; i < quaternionArr.length; i++) {
            mul(tmp1.set(quaternionArr[i]).exp(length));
        }
        nor();
        return this;
    }

    public Quaternion slerp(Quaternion[] quaternionArr, float[] fArr) {
        set(quaternionArr[0]).exp(fArr[0]);
        for (int i = 1; i < quaternionArr.length; i++) {
            mul(tmp1.set(quaternionArr[i]).exp(fArr[i]));
        }
        nor();
        return this;
    }

    public Quaternion exp(float f) {
        float sin;
        float len = len();
        float pow = (float) Math.pow(len, f);
        float acos = (float) Math.acos(this.w / len);
        if (Math.abs(acos) < 0.001d) {
            sin = (pow * f) / len;
        } else {
            sin = (float) ((pow * Math.sin(f * acos)) / (len * Math.sin(acos)));
        }
        this.w = (float) (pow * Math.cos(f * acos));
        this.x *= sin;
        this.y *= sin;
        this.z *= sin;
        nor();
        return this;
    }

    public int hashCode() {
        return ((((((31 + NumberUtils.floatToRawIntBits(this.w)) * 31) + NumberUtils.floatToRawIntBits(this.x)) * 31) + NumberUtils.floatToRawIntBits(this.y)) * 31) + NumberUtils.floatToRawIntBits(this.z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Quaternion)) {
            return false;
        }
        Quaternion quaternion = (Quaternion) obj;
        return NumberUtils.floatToRawIntBits(this.w) == NumberUtils.floatToRawIntBits(quaternion.w) && NumberUtils.floatToRawIntBits(this.x) == NumberUtils.floatToRawIntBits(quaternion.x) && NumberUtils.floatToRawIntBits(this.y) == NumberUtils.floatToRawIntBits(quaternion.y) && NumberUtils.floatToRawIntBits(this.z) == NumberUtils.floatToRawIntBits(quaternion.z);
    }

    public static final float dot(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        return (f * f5) + (f2 * f6) + (f3 * f7) + (f4 * f8);
    }

    public float dot(Quaternion quaternion) {
        return (this.x * quaternion.x) + (this.y * quaternion.y) + (this.z * quaternion.z) + (this.w * quaternion.w);
    }

    public float dot(float f, float f2, float f3, float f4) {
        return (this.x * f) + (this.y * f2) + (this.z * f3) + (this.w * f4);
    }

    public Quaternion mul(float f) {
        this.x *= f;
        this.y *= f;
        this.z *= f;
        this.w *= f;
        return this;
    }

    public float getAxisAngle(Vector3 vector3) {
        return getAxisAngleRad(vector3) * 57.295776f;
    }

    public float getAxisAngleRad(Vector3 vector3) {
        if (this.w > 1.0f) {
            nor();
        }
        float acos = (float) (2.0d * Math.acos(this.w));
        double sqrt = Math.sqrt(1.0f - (this.w * this.w));
        if (sqrt < 9.999999974752427E-7d) {
            vector3.x = this.x;
            vector3.y = this.y;
            vector3.z = this.z;
        } else {
            vector3.x = (float) (this.x / sqrt);
            vector3.y = (float) (this.y / sqrt);
            vector3.z = (float) (this.z / sqrt);
        }
        return acos;
    }

    public float getAngleRad() {
        return (float) (2.0d * Math.acos(this.w > 1.0f ? this.w / len() : this.w));
    }

    public float getAngle() {
        return getAngleRad() * 57.295776f;
    }

    public void getSwingTwist(float f, float f2, float f3, Quaternion quaternion, Quaternion quaternion2) {
        float dot = Vector3.dot(this.x, this.y, this.z, f, f2, f3);
        quaternion2.set(f * dot, f2 * dot, f3 * dot, this.w).nor();
        if (dot < 0.0f) {
            quaternion2.mul(-1.0f);
        }
        quaternion.set(quaternion2).conjugate().mulLeft(this);
    }

    public void getSwingTwist(Vector3 vector3, Quaternion quaternion, Quaternion quaternion2) {
        getSwingTwist(vector3.x, vector3.y, vector3.z, quaternion, quaternion2);
    }

    public float getAngleAroundRad(float f, float f2, float f3) {
        float dot = Vector3.dot(this.x, this.y, this.z, f, f2, f3);
        if (MathUtils.isZero(len2(f * dot, f2 * dot, f3 * dot, this.w))) {
            return 0.0f;
        }
        return (float) (2.0d * Math.acos(MathUtils.clamp((float) ((dot < 0.0f ? -this.w : this.w) / Math.sqrt(r0)), -1.0f, 1.0f)));
    }

    public float getAngleAroundRad(Vector3 vector3) {
        return getAngleAroundRad(vector3.x, vector3.y, vector3.z);
    }

    public float getAngleAround(float f, float f2, float f3) {
        return getAngleAroundRad(f, f2, f3) * 57.295776f;
    }

    public float getAngleAround(Vector3 vector3) {
        return getAngleAround(vector3.x, vector3.y, vector3.z);
    }
}
