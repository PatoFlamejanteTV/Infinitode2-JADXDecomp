package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Matrix3.class */
public class Matrix3 implements Serializable {
    private static final long serialVersionUID = 7907569533774959788L;
    public static final int M00 = 0;
    public static final int M01 = 3;
    public static final int M02 = 6;
    public static final int M10 = 1;
    public static final int M11 = 4;
    public static final int M12 = 7;
    public static final int M20 = 2;
    public static final int M21 = 5;
    public static final int M22 = 8;
    public float[] val;
    private float[] tmp;

    public Matrix3() {
        this.val = new float[9];
        this.tmp = new float[9];
        this.tmp[8] = 1.0f;
        idt();
    }

    public Matrix3(Matrix3 matrix3) {
        this.val = new float[9];
        this.tmp = new float[9];
        this.tmp[8] = 1.0f;
        set(matrix3);
    }

    public Matrix3(float[] fArr) {
        this.val = new float[9];
        this.tmp = new float[9];
        this.tmp[8] = 1.0f;
        set(fArr);
    }

    public Matrix3 idt() {
        float[] fArr = this.val;
        fArr[0] = 1.0f;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        fArr[4] = 1.0f;
        fArr[5] = 0.0f;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = 1.0f;
        return this;
    }

    public Matrix3 mul(Matrix3 matrix3) {
        float[] fArr = this.val;
        float f = (fArr[0] * matrix3.val[0]) + (fArr[3] * matrix3.val[1]) + (fArr[6] * matrix3.val[2]);
        float f2 = (fArr[0] * matrix3.val[3]) + (fArr[3] * matrix3.val[4]) + (fArr[6] * matrix3.val[5]);
        float f3 = (fArr[0] * matrix3.val[6]) + (fArr[3] * matrix3.val[7]) + (fArr[6] * matrix3.val[8]);
        float f4 = (fArr[1] * matrix3.val[0]) + (fArr[4] * matrix3.val[1]) + (fArr[7] * matrix3.val[2]);
        float f5 = (fArr[1] * matrix3.val[3]) + (fArr[4] * matrix3.val[4]) + (fArr[7] * matrix3.val[5]);
        float f6 = (fArr[1] * matrix3.val[6]) + (fArr[4] * matrix3.val[7]) + (fArr[7] * matrix3.val[8]);
        float f7 = (fArr[2] * matrix3.val[0]) + (fArr[5] * matrix3.val[1]) + (fArr[8] * matrix3.val[2]);
        float f8 = (fArr[2] * matrix3.val[3]) + (fArr[5] * matrix3.val[4]) + (fArr[8] * matrix3.val[5]);
        float f9 = (fArr[2] * matrix3.val[6]) + (fArr[5] * matrix3.val[7]) + (fArr[8] * matrix3.val[8]);
        fArr[0] = f;
        fArr[1] = f4;
        fArr[2] = f7;
        fArr[3] = f2;
        fArr[4] = f5;
        fArr[5] = f8;
        fArr[6] = f3;
        fArr[7] = f6;
        fArr[8] = f9;
        return this;
    }

    public Matrix3 mulLeft(Matrix3 matrix3) {
        float[] fArr = this.val;
        float f = (matrix3.val[0] * fArr[0]) + (matrix3.val[3] * fArr[1]) + (matrix3.val[6] * fArr[2]);
        float f2 = (matrix3.val[0] * fArr[3]) + (matrix3.val[3] * fArr[4]) + (matrix3.val[6] * fArr[5]);
        float f3 = (matrix3.val[0] * fArr[6]) + (matrix3.val[3] * fArr[7]) + (matrix3.val[6] * fArr[8]);
        float f4 = (matrix3.val[1] * fArr[0]) + (matrix3.val[4] * fArr[1]) + (matrix3.val[7] * fArr[2]);
        float f5 = (matrix3.val[1] * fArr[3]) + (matrix3.val[4] * fArr[4]) + (matrix3.val[7] * fArr[5]);
        float f6 = (matrix3.val[1] * fArr[6]) + (matrix3.val[4] * fArr[7]) + (matrix3.val[7] * fArr[8]);
        float f7 = (matrix3.val[2] * fArr[0]) + (matrix3.val[5] * fArr[1]) + (matrix3.val[8] * fArr[2]);
        float f8 = (matrix3.val[2] * fArr[3]) + (matrix3.val[5] * fArr[4]) + (matrix3.val[8] * fArr[5]);
        float f9 = (matrix3.val[2] * fArr[6]) + (matrix3.val[5] * fArr[7]) + (matrix3.val[8] * fArr[8]);
        fArr[0] = f;
        fArr[1] = f4;
        fArr[2] = f7;
        fArr[3] = f2;
        fArr[4] = f5;
        fArr[5] = f8;
        fArr[6] = f3;
        fArr[7] = f6;
        fArr[8] = f9;
        return this;
    }

    public Matrix3 setToRotation(float f) {
        return setToRotationRad(0.017453292f * f);
    }

    public Matrix3 setToRotationRad(float f) {
        float cos = (float) Math.cos(f);
        float sin = (float) Math.sin(f);
        float[] fArr = this.val;
        fArr[0] = cos;
        fArr[1] = sin;
        fArr[2] = 0.0f;
        fArr[3] = -sin;
        fArr[4] = cos;
        fArr[5] = 0.0f;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = 1.0f;
        return this;
    }

    public Matrix3 setToRotation(Vector3 vector3, float f) {
        return setToRotation(vector3, MathUtils.cosDeg(f), MathUtils.sinDeg(f));
    }

    public Matrix3 setToRotation(Vector3 vector3, float f, float f2) {
        float[] fArr = this.val;
        float f3 = 1.0f - f;
        fArr[0] = (f3 * vector3.x * vector3.x) + f;
        fArr[3] = ((f3 * vector3.x) * vector3.y) - (vector3.z * f2);
        fArr[6] = (f3 * vector3.z * vector3.x) + (vector3.y * f2);
        fArr[1] = (f3 * vector3.x * vector3.y) + (vector3.z * f2);
        fArr[4] = (f3 * vector3.y * vector3.y) + f;
        fArr[7] = ((f3 * vector3.y) * vector3.z) - (vector3.x * f2);
        fArr[2] = ((f3 * vector3.z) * vector3.x) - (vector3.y * f2);
        fArr[5] = (f3 * vector3.y * vector3.z) + (vector3.x * f2);
        fArr[8] = (f3 * vector3.z * vector3.z) + f;
        return this;
    }

    public Matrix3 setToTranslation(float f, float f2) {
        float[] fArr = this.val;
        fArr[0] = 1.0f;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        fArr[4] = 1.0f;
        fArr[5] = 0.0f;
        fArr[6] = f;
        fArr[7] = f2;
        fArr[8] = 1.0f;
        return this;
    }

    public Matrix3 setToTranslation(Vector2 vector2) {
        float[] fArr = this.val;
        fArr[0] = 1.0f;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        fArr[4] = 1.0f;
        fArr[5] = 0.0f;
        fArr[6] = vector2.x;
        fArr[7] = vector2.y;
        fArr[8] = 1.0f;
        return this;
    }

    public Matrix3 setToScaling(float f, float f2) {
        float[] fArr = this.val;
        fArr[0] = f;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        fArr[4] = f2;
        fArr[5] = 0.0f;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = 1.0f;
        return this;
    }

    public Matrix3 setToScaling(Vector2 vector2) {
        float[] fArr = this.val;
        fArr[0] = vector2.x;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        fArr[4] = vector2.y;
        fArr[5] = 0.0f;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = 1.0f;
        return this;
    }

    public String toString() {
        float[] fArr = this.val;
        return "[" + fArr[0] + "|" + fArr[3] + "|" + fArr[6] + "]\n[" + fArr[1] + "|" + fArr[4] + "|" + fArr[7] + "]\n[" + fArr[2] + "|" + fArr[5] + "|" + fArr[8] + "]";
    }

    public float det() {
        float[] fArr = this.val;
        return ((((((fArr[0] * fArr[4]) * fArr[8]) + ((fArr[3] * fArr[7]) * fArr[2])) + ((fArr[6] * fArr[1]) * fArr[5])) - ((fArr[0] * fArr[7]) * fArr[5])) - ((fArr[3] * fArr[1]) * fArr[8])) - ((fArr[6] * fArr[4]) * fArr[2]);
    }

    public Matrix3 inv() {
        float det = det();
        if (det == 0.0f) {
            throw new GdxRuntimeException("Can't invert a singular matrix");
        }
        float f = 1.0f / det;
        float[] fArr = this.val;
        float f2 = (fArr[4] * fArr[8]) - (fArr[5] * fArr[7]);
        float f3 = (fArr[2] * fArr[7]) - (fArr[1] * fArr[8]);
        float f4 = (fArr[1] * fArr[5]) - (fArr[2] * fArr[4]);
        float f5 = (fArr[5] * fArr[6]) - (fArr[3] * fArr[8]);
        float f6 = (fArr[0] * fArr[8]) - (fArr[2] * fArr[6]);
        float f7 = (fArr[2] * fArr[3]) - (fArr[0] * fArr[5]);
        float f8 = (fArr[3] * fArr[7]) - (fArr[4] * fArr[6]);
        float f9 = (fArr[1] * fArr[6]) - (fArr[0] * fArr[7]);
        float f10 = (fArr[0] * fArr[4]) - (fArr[1] * fArr[3]);
        fArr[0] = f * f2;
        fArr[1] = f * f3;
        fArr[2] = f * f4;
        fArr[3] = f * f5;
        fArr[4] = f * f6;
        fArr[5] = f * f7;
        fArr[6] = f * f8;
        fArr[7] = f * f9;
        fArr[8] = f * f10;
        return this;
    }

    public Matrix3 set(Matrix3 matrix3) {
        System.arraycopy(matrix3.val, 0, this.val, 0, this.val.length);
        return this;
    }

    public Matrix3 set(Affine2 affine2) {
        float[] fArr = this.val;
        fArr[0] = affine2.m00;
        fArr[1] = affine2.m10;
        fArr[2] = 0.0f;
        fArr[3] = affine2.m01;
        fArr[4] = affine2.m11;
        fArr[5] = 0.0f;
        fArr[6] = affine2.m02;
        fArr[7] = affine2.m12;
        fArr[8] = 1.0f;
        return this;
    }

    public Matrix3 set(Matrix4 matrix4) {
        float[] fArr = this.val;
        fArr[0] = matrix4.val[0];
        fArr[1] = matrix4.val[1];
        fArr[2] = matrix4.val[2];
        fArr[3] = matrix4.val[4];
        fArr[4] = matrix4.val[5];
        fArr[5] = matrix4.val[6];
        fArr[6] = matrix4.val[8];
        fArr[7] = matrix4.val[9];
        fArr[8] = matrix4.val[10];
        return this;
    }

    public Matrix3 set(float[] fArr) {
        System.arraycopy(fArr, 0, this.val, 0, this.val.length);
        return this;
    }

    public Matrix3 trn(Vector2 vector2) {
        float[] fArr = this.val;
        fArr[6] = fArr[6] + vector2.x;
        float[] fArr2 = this.val;
        fArr2[7] = fArr2[7] + vector2.y;
        return this;
    }

    public Matrix3 trn(float f, float f2) {
        float[] fArr = this.val;
        fArr[6] = fArr[6] + f;
        float[] fArr2 = this.val;
        fArr2[7] = fArr2[7] + f2;
        return this;
    }

    public Matrix3 trn(Vector3 vector3) {
        float[] fArr = this.val;
        fArr[6] = fArr[6] + vector3.x;
        float[] fArr2 = this.val;
        fArr2[7] = fArr2[7] + vector3.y;
        return this;
    }

    public Matrix3 translate(float f, float f2) {
        float[] fArr = this.tmp;
        fArr[0] = 1.0f;
        fArr[1] = 0.0f;
        fArr[3] = 0.0f;
        fArr[4] = 1.0f;
        fArr[6] = f;
        fArr[7] = f2;
        mul(this.val, fArr);
        return this;
    }

    public Matrix3 translate(Vector2 vector2) {
        float[] fArr = this.tmp;
        fArr[0] = 1.0f;
        fArr[1] = 0.0f;
        fArr[3] = 0.0f;
        fArr[4] = 1.0f;
        fArr[6] = vector2.x;
        fArr[7] = vector2.y;
        mul(this.val, fArr);
        return this;
    }

    public Matrix3 rotate(float f) {
        return rotateRad(0.017453292f * f);
    }

    public Matrix3 rotateRad(float f) {
        if (f == 0.0f) {
            return this;
        }
        float cos = (float) Math.cos(f);
        float sin = (float) Math.sin(f);
        float[] fArr = this.tmp;
        fArr[0] = cos;
        fArr[1] = sin;
        fArr[3] = -sin;
        fArr[4] = cos;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        mul(this.val, fArr);
        return this;
    }

    public Matrix3 scale(float f, float f2) {
        float[] fArr = this.tmp;
        fArr[0] = f;
        fArr[1] = 0.0f;
        fArr[3] = 0.0f;
        fArr[4] = f2;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        mul(this.val, fArr);
        return this;
    }

    public Matrix3 scale(Vector2 vector2) {
        float[] fArr = this.tmp;
        fArr[0] = vector2.x;
        fArr[1] = 0.0f;
        fArr[3] = 0.0f;
        fArr[4] = vector2.y;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        mul(this.val, fArr);
        return this;
    }

    public float[] getValues() {
        return this.val;
    }

    public Vector2 getTranslation(Vector2 vector2) {
        vector2.x = this.val[6];
        vector2.y = this.val[7];
        return vector2;
    }

    public Vector2 getScale(Vector2 vector2) {
        float[] fArr = this.val;
        vector2.x = (float) Math.sqrt((fArr[0] * fArr[0]) + (fArr[3] * fArr[3]));
        vector2.y = (float) Math.sqrt((fArr[1] * fArr[1]) + (fArr[4] * fArr[4]));
        return vector2;
    }

    public float getRotation() {
        return 57.295776f * ((float) Math.atan2(this.val[1], this.val[0]));
    }

    public float getRotationRad() {
        return (float) Math.atan2(this.val[1], this.val[0]);
    }

    public Matrix3 scl(float f) {
        float[] fArr = this.val;
        fArr[0] = fArr[0] * f;
        float[] fArr2 = this.val;
        fArr2[4] = fArr2[4] * f;
        return this;
    }

    public Matrix3 scl(Vector2 vector2) {
        float[] fArr = this.val;
        fArr[0] = fArr[0] * vector2.x;
        float[] fArr2 = this.val;
        fArr2[4] = fArr2[4] * vector2.y;
        return this;
    }

    public Matrix3 scl(Vector3 vector3) {
        float[] fArr = this.val;
        fArr[0] = fArr[0] * vector3.x;
        float[] fArr2 = this.val;
        fArr2[4] = fArr2[4] * vector3.y;
        return this;
    }

    public Matrix3 transpose() {
        float[] fArr = this.val;
        float f = fArr[1];
        float f2 = fArr[2];
        float f3 = fArr[3];
        float f4 = fArr[5];
        float f5 = fArr[6];
        float f6 = fArr[7];
        fArr[3] = f;
        fArr[6] = f2;
        fArr[1] = f3;
        fArr[7] = f4;
        fArr[2] = f5;
        fArr[5] = f6;
        return this;
    }

    private static void mul(float[] fArr, float[] fArr2) {
        float f = (fArr[0] * fArr2[0]) + (fArr[3] * fArr2[1]) + (fArr[6] * fArr2[2]);
        float f2 = (fArr[0] * fArr2[3]) + (fArr[3] * fArr2[4]) + (fArr[6] * fArr2[5]);
        float f3 = (fArr[0] * fArr2[6]) + (fArr[3] * fArr2[7]) + (fArr[6] * fArr2[8]);
        float f4 = (fArr[1] * fArr2[0]) + (fArr[4] * fArr2[1]) + (fArr[7] * fArr2[2]);
        float f5 = (fArr[1] * fArr2[3]) + (fArr[4] * fArr2[4]) + (fArr[7] * fArr2[5]);
        float f6 = (fArr[1] * fArr2[6]) + (fArr[4] * fArr2[7]) + (fArr[7] * fArr2[8]);
        float f7 = (fArr[2] * fArr2[0]) + (fArr[5] * fArr2[1]) + (fArr[8] * fArr2[2]);
        float f8 = (fArr[2] * fArr2[3]) + (fArr[5] * fArr2[4]) + (fArr[8] * fArr2[5]);
        float f9 = (fArr[2] * fArr2[6]) + (fArr[5] * fArr2[7]) + (fArr[8] * fArr2[8]);
        fArr[0] = f;
        fArr[1] = f4;
        fArr[2] = f7;
        fArr[3] = f2;
        fArr[4] = f5;
        fArr[5] = f8;
        fArr[6] = f3;
        fArr[7] = f6;
        fArr[8] = f9;
    }
}
