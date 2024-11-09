package com.badlogic.gdx.math;

import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Matrix4.class */
public class Matrix4 implements Serializable {
    private static final long serialVersionUID = -2717655254359579617L;
    public static final int M00 = 0;
    public static final int M01 = 4;
    public static final int M02 = 8;
    public static final int M03 = 12;
    public static final int M10 = 1;
    public static final int M11 = 5;
    public static final int M12 = 9;
    public static final int M13 = 13;
    public static final int M20 = 2;
    public static final int M21 = 6;
    public static final int M22 = 10;
    public static final int M23 = 14;
    public static final int M30 = 3;
    public static final int M31 = 7;
    public static final int M32 = 11;
    public static final int M33 = 15;
    static final Quaternion quat = new Quaternion();
    static final Quaternion quat2 = new Quaternion();
    static final Vector3 l_vez = new Vector3();
    static final Vector3 l_vex = new Vector3();
    static final Vector3 l_vey = new Vector3();
    static final Vector3 tmpVec = new Vector3();
    static final Matrix4 tmpMat = new Matrix4();
    static final Vector3 right = new Vector3();
    static final Vector3 tmpForward = new Vector3();
    static final Vector3 tmpUp = new Vector3();
    public final float[] val;

    public static native void mulVec(float[] fArr, float[] fArr2, int i, int i2, int i3);

    public static native void prj(float[] fArr, float[] fArr2, int i, int i2, int i3);

    public static native void rot(float[] fArr, float[] fArr2, int i, int i2, int i3);

    public Matrix4() {
        this.val = new float[16];
        this.val[0] = 1.0f;
        this.val[5] = 1.0f;
        this.val[10] = 1.0f;
        this.val[15] = 1.0f;
    }

    public Matrix4(Matrix4 matrix4) {
        this.val = new float[16];
        set(matrix4);
    }

    public Matrix4(float[] fArr) {
        this.val = new float[16];
        set(fArr);
    }

    public Matrix4(Quaternion quaternion) {
        this.val = new float[16];
        set(quaternion);
    }

    public Matrix4(Vector3 vector3, Quaternion quaternion, Vector3 vector32) {
        this.val = new float[16];
        set(vector3, quaternion, vector32);
    }

    public Matrix4 set(Matrix4 matrix4) {
        return set(matrix4.val);
    }

    public Matrix4 set(float[] fArr) {
        System.arraycopy(fArr, 0, this.val, 0, this.val.length);
        return this;
    }

    public Matrix4 set(Quaternion quaternion) {
        return set(quaternion.x, quaternion.y, quaternion.z, quaternion.w);
    }

    public Matrix4 set(float f, float f2, float f3, float f4) {
        return set(0.0f, 0.0f, 0.0f, f, f2, f3, f4);
    }

    public Matrix4 set(Vector3 vector3, Quaternion quaternion) {
        return set(vector3.x, vector3.y, vector3.z, quaternion.x, quaternion.y, quaternion.z, quaternion.w);
    }

    public Matrix4 set(float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        float f8 = f4 * 2.0f;
        float f9 = f5 * 2.0f;
        float f10 = f6 * 2.0f;
        float f11 = f7 * f8;
        float f12 = f7 * f9;
        float f13 = f7 * f10;
        float f14 = f4 * f8;
        float f15 = f4 * f9;
        float f16 = f4 * f10;
        float f17 = f5 * f9;
        float f18 = f5 * f10;
        float f19 = f6 * f10;
        this.val[0] = 1.0f - (f17 + f19);
        this.val[4] = f15 - f13;
        this.val[8] = f16 + f12;
        this.val[12] = f;
        this.val[1] = f15 + f13;
        this.val[5] = 1.0f - (f14 + f19);
        this.val[9] = f18 - f11;
        this.val[13] = f2;
        this.val[2] = f16 - f12;
        this.val[6] = f18 + f11;
        this.val[10] = 1.0f - (f14 + f17);
        this.val[14] = f3;
        this.val[3] = 0.0f;
        this.val[7] = 0.0f;
        this.val[11] = 0.0f;
        this.val[15] = 1.0f;
        return this;
    }

    public Matrix4 set(Vector3 vector3, Quaternion quaternion, Vector3 vector32) {
        return set(vector3.x, vector3.y, vector3.z, quaternion.x, quaternion.y, quaternion.z, quaternion.w, vector32.x, vector32.y, vector32.z);
    }

    public Matrix4 set(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        float f11 = f4 * 2.0f;
        float f12 = f5 * 2.0f;
        float f13 = f6 * 2.0f;
        float f14 = f7 * f11;
        float f15 = f7 * f12;
        float f16 = f7 * f13;
        float f17 = f4 * f11;
        float f18 = f4 * f12;
        float f19 = f4 * f13;
        float f20 = f5 * f12;
        float f21 = f5 * f13;
        float f22 = f6 * f13;
        this.val[0] = f8 * (1.0f - (f20 + f22));
        this.val[4] = f9 * (f18 - f16);
        this.val[8] = f10 * (f19 + f15);
        this.val[12] = f;
        this.val[1] = f8 * (f18 + f16);
        this.val[5] = f9 * (1.0f - (f17 + f22));
        this.val[9] = f10 * (f21 - f14);
        this.val[13] = f2;
        this.val[2] = f8 * (f19 - f15);
        this.val[6] = f9 * (f21 + f14);
        this.val[10] = f10 * (1.0f - (f17 + f20));
        this.val[14] = f3;
        this.val[3] = 0.0f;
        this.val[7] = 0.0f;
        this.val[11] = 0.0f;
        this.val[15] = 1.0f;
        return this;
    }

    public Matrix4 set(Vector3 vector3, Vector3 vector32, Vector3 vector33, Vector3 vector34) {
        this.val[0] = vector3.x;
        this.val[4] = vector3.y;
        this.val[8] = vector3.z;
        this.val[1] = vector32.x;
        this.val[5] = vector32.y;
        this.val[9] = vector32.z;
        this.val[2] = vector33.x;
        this.val[6] = vector33.y;
        this.val[10] = vector33.z;
        this.val[12] = vector34.x;
        this.val[13] = vector34.y;
        this.val[14] = vector34.z;
        this.val[3] = 0.0f;
        this.val[7] = 0.0f;
        this.val[11] = 0.0f;
        this.val[15] = 1.0f;
        return this;
    }

    public Matrix4 cpy() {
        return new Matrix4(this);
    }

    public Matrix4 trn(Vector3 vector3) {
        float[] fArr = this.val;
        fArr[12] = fArr[12] + vector3.x;
        float[] fArr2 = this.val;
        fArr2[13] = fArr2[13] + vector3.y;
        float[] fArr3 = this.val;
        fArr3[14] = fArr3[14] + vector3.z;
        return this;
    }

    public Matrix4 trn(float f, float f2, float f3) {
        float[] fArr = this.val;
        fArr[12] = fArr[12] + f;
        float[] fArr2 = this.val;
        fArr2[13] = fArr2[13] + f2;
        float[] fArr3 = this.val;
        fArr3[14] = fArr3[14] + f3;
        return this;
    }

    public float[] getValues() {
        return this.val;
    }

    public Matrix4 mul(Matrix4 matrix4) {
        mul(this.val, matrix4.val);
        return this;
    }

    public Matrix4 mulLeft(Matrix4 matrix4) {
        tmpMat.set(matrix4);
        mul(tmpMat.val, this.val);
        return set(tmpMat);
    }

    public Matrix4 tra() {
        float f = this.val[4];
        float f2 = this.val[8];
        float f3 = this.val[12];
        float f4 = this.val[9];
        float f5 = this.val[13];
        float f6 = this.val[14];
        this.val[4] = this.val[1];
        this.val[8] = this.val[2];
        this.val[12] = this.val[3];
        this.val[1] = f;
        this.val[9] = this.val[6];
        this.val[13] = this.val[7];
        this.val[2] = f2;
        this.val[6] = f4;
        this.val[14] = this.val[11];
        this.val[3] = f3;
        this.val[7] = f5;
        this.val[11] = f6;
        return this;
    }

    public Matrix4 idt() {
        this.val[0] = 1.0f;
        this.val[4] = 0.0f;
        this.val[8] = 0.0f;
        this.val[12] = 0.0f;
        this.val[1] = 0.0f;
        this.val[5] = 1.0f;
        this.val[9] = 0.0f;
        this.val[13] = 0.0f;
        this.val[2] = 0.0f;
        this.val[6] = 0.0f;
        this.val[10] = 1.0f;
        this.val[14] = 0.0f;
        this.val[3] = 0.0f;
        this.val[7] = 0.0f;
        this.val[11] = 0.0f;
        this.val[15] = 1.0f;
        return this;
    }

    public Matrix4 inv() {
        float f = (((((((((((((((((((((((((this.val[3] * this.val[6]) * this.val[9]) * this.val[12]) - (((this.val[2] * this.val[7]) * this.val[9]) * this.val[12])) - (((this.val[3] * this.val[5]) * this.val[10]) * this.val[12])) + (((this.val[1] * this.val[7]) * this.val[10]) * this.val[12])) + (((this.val[2] * this.val[5]) * this.val[11]) * this.val[12])) - (((this.val[1] * this.val[6]) * this.val[11]) * this.val[12])) - (((this.val[3] * this.val[6]) * this.val[8]) * this.val[13])) + (((this.val[2] * this.val[7]) * this.val[8]) * this.val[13])) + (((this.val[3] * this.val[4]) * this.val[10]) * this.val[13])) - (((this.val[0] * this.val[7]) * this.val[10]) * this.val[13])) - (((this.val[2] * this.val[4]) * this.val[11]) * this.val[13])) + (((this.val[0] * this.val[6]) * this.val[11]) * this.val[13])) + (((this.val[3] * this.val[5]) * this.val[8]) * this.val[14])) - (((this.val[1] * this.val[7]) * this.val[8]) * this.val[14])) - (((this.val[3] * this.val[4]) * this.val[9]) * this.val[14])) + (((this.val[0] * this.val[7]) * this.val[9]) * this.val[14])) + (((this.val[1] * this.val[4]) * this.val[11]) * this.val[14])) - (((this.val[0] * this.val[5]) * this.val[11]) * this.val[14])) - (((this.val[2] * this.val[5]) * this.val[8]) * this.val[15])) + (((this.val[1] * this.val[6]) * this.val[8]) * this.val[15])) + (((this.val[2] * this.val[4]) * this.val[9]) * this.val[15])) - (((this.val[0] * this.val[6]) * this.val[9]) * this.val[15])) - (((this.val[1] * this.val[4]) * this.val[10]) * this.val[15])) + (this.val[0] * this.val[5] * this.val[10] * this.val[15]);
        if (f == 0.0f) {
            throw new RuntimeException("non-invertible matrix");
        }
        float f2 = ((((((this.val[9] * this.val[14]) * this.val[7]) - ((this.val[13] * this.val[10]) * this.val[7])) + ((this.val[13] * this.val[6]) * this.val[11])) - ((this.val[5] * this.val[14]) * this.val[11])) - ((this.val[9] * this.val[6]) * this.val[15])) + (this.val[5] * this.val[10] * this.val[15]);
        float f3 = ((((((this.val[12] * this.val[10]) * this.val[7]) - ((this.val[8] * this.val[14]) * this.val[7])) - ((this.val[12] * this.val[6]) * this.val[11])) + ((this.val[4] * this.val[14]) * this.val[11])) + ((this.val[8] * this.val[6]) * this.val[15])) - ((this.val[4] * this.val[10]) * this.val[15]);
        float f4 = ((((((this.val[8] * this.val[13]) * this.val[7]) - ((this.val[12] * this.val[9]) * this.val[7])) + ((this.val[12] * this.val[5]) * this.val[11])) - ((this.val[4] * this.val[13]) * this.val[11])) - ((this.val[8] * this.val[5]) * this.val[15])) + (this.val[4] * this.val[9] * this.val[15]);
        float f5 = ((((((this.val[12] * this.val[9]) * this.val[6]) - ((this.val[8] * this.val[13]) * this.val[6])) - ((this.val[12] * this.val[5]) * this.val[10])) + ((this.val[4] * this.val[13]) * this.val[10])) + ((this.val[8] * this.val[5]) * this.val[14])) - ((this.val[4] * this.val[9]) * this.val[14]);
        float f6 = ((((((this.val[13] * this.val[10]) * this.val[3]) - ((this.val[9] * this.val[14]) * this.val[3])) - ((this.val[13] * this.val[2]) * this.val[11])) + ((this.val[1] * this.val[14]) * this.val[11])) + ((this.val[9] * this.val[2]) * this.val[15])) - ((this.val[1] * this.val[10]) * this.val[15]);
        float f7 = ((((((this.val[8] * this.val[14]) * this.val[3]) - ((this.val[12] * this.val[10]) * this.val[3])) + ((this.val[12] * this.val[2]) * this.val[11])) - ((this.val[0] * this.val[14]) * this.val[11])) - ((this.val[8] * this.val[2]) * this.val[15])) + (this.val[0] * this.val[10] * this.val[15]);
        float f8 = ((((((this.val[12] * this.val[9]) * this.val[3]) - ((this.val[8] * this.val[13]) * this.val[3])) - ((this.val[12] * this.val[1]) * this.val[11])) + ((this.val[0] * this.val[13]) * this.val[11])) + ((this.val[8] * this.val[1]) * this.val[15])) - ((this.val[0] * this.val[9]) * this.val[15]);
        float f9 = ((((((this.val[8] * this.val[13]) * this.val[2]) - ((this.val[12] * this.val[9]) * this.val[2])) + ((this.val[12] * this.val[1]) * this.val[10])) - ((this.val[0] * this.val[13]) * this.val[10])) - ((this.val[8] * this.val[1]) * this.val[14])) + (this.val[0] * this.val[9] * this.val[14]);
        float f10 = ((((((this.val[5] * this.val[14]) * this.val[3]) - ((this.val[13] * this.val[6]) * this.val[3])) + ((this.val[13] * this.val[2]) * this.val[7])) - ((this.val[1] * this.val[14]) * this.val[7])) - ((this.val[5] * this.val[2]) * this.val[15])) + (this.val[1] * this.val[6] * this.val[15]);
        float f11 = ((((((this.val[12] * this.val[6]) * this.val[3]) - ((this.val[4] * this.val[14]) * this.val[3])) - ((this.val[12] * this.val[2]) * this.val[7])) + ((this.val[0] * this.val[14]) * this.val[7])) + ((this.val[4] * this.val[2]) * this.val[15])) - ((this.val[0] * this.val[6]) * this.val[15]);
        float f12 = ((((((this.val[4] * this.val[13]) * this.val[3]) - ((this.val[12] * this.val[5]) * this.val[3])) + ((this.val[12] * this.val[1]) * this.val[7])) - ((this.val[0] * this.val[13]) * this.val[7])) - ((this.val[4] * this.val[1]) * this.val[15])) + (this.val[0] * this.val[5] * this.val[15]);
        float f13 = ((((((this.val[12] * this.val[5]) * this.val[2]) - ((this.val[4] * this.val[13]) * this.val[2])) - ((this.val[12] * this.val[1]) * this.val[6])) + ((this.val[0] * this.val[13]) * this.val[6])) + ((this.val[4] * this.val[1]) * this.val[14])) - ((this.val[0] * this.val[5]) * this.val[14]);
        float f14 = ((((((this.val[9] * this.val[6]) * this.val[3]) - ((this.val[5] * this.val[10]) * this.val[3])) - ((this.val[9] * this.val[2]) * this.val[7])) + ((this.val[1] * this.val[10]) * this.val[7])) + ((this.val[5] * this.val[2]) * this.val[11])) - ((this.val[1] * this.val[6]) * this.val[11]);
        float f15 = ((((((this.val[4] * this.val[10]) * this.val[3]) - ((this.val[8] * this.val[6]) * this.val[3])) + ((this.val[8] * this.val[2]) * this.val[7])) - ((this.val[0] * this.val[10]) * this.val[7])) - ((this.val[4] * this.val[2]) * this.val[11])) + (this.val[0] * this.val[6] * this.val[11]);
        float f16 = ((((((this.val[8] * this.val[5]) * this.val[3]) - ((this.val[4] * this.val[9]) * this.val[3])) - ((this.val[8] * this.val[1]) * this.val[7])) + ((this.val[0] * this.val[9]) * this.val[7])) + ((this.val[4] * this.val[1]) * this.val[11])) - ((this.val[0] * this.val[5]) * this.val[11]);
        float f17 = ((((((this.val[4] * this.val[9]) * this.val[2]) - ((this.val[8] * this.val[5]) * this.val[2])) + ((this.val[8] * this.val[1]) * this.val[6])) - ((this.val[0] * this.val[9]) * this.val[6])) - ((this.val[4] * this.val[1]) * this.val[10])) + (this.val[0] * this.val[5] * this.val[10]);
        float f18 = 1.0f / f;
        this.val[0] = f2 * f18;
        this.val[1] = f6 * f18;
        this.val[2] = f10 * f18;
        this.val[3] = f14 * f18;
        this.val[4] = f3 * f18;
        this.val[5] = f7 * f18;
        this.val[6] = f11 * f18;
        this.val[7] = f15 * f18;
        this.val[8] = f4 * f18;
        this.val[9] = f8 * f18;
        this.val[10] = f12 * f18;
        this.val[11] = f16 * f18;
        this.val[12] = f5 * f18;
        this.val[13] = f9 * f18;
        this.val[14] = f13 * f18;
        this.val[15] = f17 * f18;
        return this;
    }

    public float det() {
        return (((((((((((((((((((((((((this.val[3] * this.val[6]) * this.val[9]) * this.val[12]) - (((this.val[2] * this.val[7]) * this.val[9]) * this.val[12])) - (((this.val[3] * this.val[5]) * this.val[10]) * this.val[12])) + (((this.val[1] * this.val[7]) * this.val[10]) * this.val[12])) + (((this.val[2] * this.val[5]) * this.val[11]) * this.val[12])) - (((this.val[1] * this.val[6]) * this.val[11]) * this.val[12])) - (((this.val[3] * this.val[6]) * this.val[8]) * this.val[13])) + (((this.val[2] * this.val[7]) * this.val[8]) * this.val[13])) + (((this.val[3] * this.val[4]) * this.val[10]) * this.val[13])) - (((this.val[0] * this.val[7]) * this.val[10]) * this.val[13])) - (((this.val[2] * this.val[4]) * this.val[11]) * this.val[13])) + (((this.val[0] * this.val[6]) * this.val[11]) * this.val[13])) + (((this.val[3] * this.val[5]) * this.val[8]) * this.val[14])) - (((this.val[1] * this.val[7]) * this.val[8]) * this.val[14])) - (((this.val[3] * this.val[4]) * this.val[9]) * this.val[14])) + (((this.val[0] * this.val[7]) * this.val[9]) * this.val[14])) + (((this.val[1] * this.val[4]) * this.val[11]) * this.val[14])) - (((this.val[0] * this.val[5]) * this.val[11]) * this.val[14])) - (((this.val[2] * this.val[5]) * this.val[8]) * this.val[15])) + (((this.val[1] * this.val[6]) * this.val[8]) * this.val[15])) + (((this.val[2] * this.val[4]) * this.val[9]) * this.val[15])) - (((this.val[0] * this.val[6]) * this.val[9]) * this.val[15])) - (((this.val[1] * this.val[4]) * this.val[10]) * this.val[15])) + (this.val[0] * this.val[5] * this.val[10] * this.val[15]);
    }

    public float det3x3() {
        return ((((((this.val[0] * this.val[5]) * this.val[10]) + ((this.val[4] * this.val[9]) * this.val[2])) + ((this.val[8] * this.val[1]) * this.val[6])) - ((this.val[0] * this.val[9]) * this.val[6])) - ((this.val[4] * this.val[1]) * this.val[10])) - ((this.val[8] * this.val[5]) * this.val[2]);
    }

    public Matrix4 setToProjection(float f, float f2, float f3, float f4) {
        idt();
        float tan = (float) (1.0d / Math.tan((f3 * 0.017453292519943295d) / 2.0d));
        this.val[0] = tan / f4;
        this.val[1] = 0.0f;
        this.val[2] = 0.0f;
        this.val[3] = 0.0f;
        this.val[4] = 0.0f;
        this.val[5] = tan;
        this.val[6] = 0.0f;
        this.val[7] = 0.0f;
        this.val[8] = 0.0f;
        this.val[9] = 0.0f;
        this.val[10] = (f2 + f) / (f - f2);
        this.val[11] = -1.0f;
        this.val[12] = 0.0f;
        this.val[13] = 0.0f;
        this.val[14] = ((f2 * 2.0f) * f) / (f - f2);
        this.val[15] = 0.0f;
        return this;
    }

    public Matrix4 setToProjection(float f, float f2, float f3, float f4, float f5, float f6) {
        this.val[0] = (f5 * 2.0f) / (f2 - f);
        this.val[1] = 0.0f;
        this.val[2] = 0.0f;
        this.val[3] = 0.0f;
        this.val[4] = 0.0f;
        this.val[5] = (f5 * 2.0f) / (f4 - f3);
        this.val[6] = 0.0f;
        this.val[7] = 0.0f;
        this.val[8] = (f2 + f) / (f2 - f);
        this.val[9] = (f4 + f3) / (f4 - f3);
        this.val[10] = (f6 + f5) / (f5 - f6);
        this.val[11] = -1.0f;
        this.val[12] = 0.0f;
        this.val[13] = 0.0f;
        this.val[14] = ((f6 * 2.0f) * f5) / (f5 - f6);
        this.val[15] = 0.0f;
        return this;
    }

    public Matrix4 setToOrtho2D(float f, float f2, float f3, float f4) {
        setToOrtho(f, f + f3, f2, f2 + f4, 0.0f, 1.0f);
        return this;
    }

    public Matrix4 setToOrtho2D(float f, float f2, float f3, float f4, float f5, float f6) {
        setToOrtho(f, f + f3, f2, f2 + f4, f5, f6);
        return this;
    }

    public Matrix4 setToOrtho(float f, float f2, float f3, float f4, float f5, float f6) {
        this.val[0] = 2.0f / (f2 - f);
        this.val[1] = 0.0f;
        this.val[2] = 0.0f;
        this.val[3] = 0.0f;
        this.val[4] = 0.0f;
        this.val[5] = 2.0f / (f4 - f3);
        this.val[6] = 0.0f;
        this.val[7] = 0.0f;
        this.val[8] = 0.0f;
        this.val[9] = 0.0f;
        this.val[10] = (-2.0f) / (f6 - f5);
        this.val[11] = 0.0f;
        this.val[12] = (-(f2 + f)) / (f2 - f);
        this.val[13] = (-(f4 + f3)) / (f4 - f3);
        this.val[14] = (-(f6 + f5)) / (f6 - f5);
        this.val[15] = 1.0f;
        return this;
    }

    public Matrix4 setTranslation(Vector3 vector3) {
        this.val[12] = vector3.x;
        this.val[13] = vector3.y;
        this.val[14] = vector3.z;
        return this;
    }

    public Matrix4 setTranslation(float f, float f2, float f3) {
        this.val[12] = f;
        this.val[13] = f2;
        this.val[14] = f3;
        return this;
    }

    public Matrix4 setToTranslation(Vector3 vector3) {
        idt();
        this.val[12] = vector3.x;
        this.val[13] = vector3.y;
        this.val[14] = vector3.z;
        return this;
    }

    public Matrix4 setToTranslation(float f, float f2, float f3) {
        idt();
        this.val[12] = f;
        this.val[13] = f2;
        this.val[14] = f3;
        return this;
    }

    public Matrix4 setToTranslationAndScaling(Vector3 vector3, Vector3 vector32) {
        idt();
        this.val[12] = vector3.x;
        this.val[13] = vector3.y;
        this.val[14] = vector3.z;
        this.val[0] = vector32.x;
        this.val[5] = vector32.y;
        this.val[10] = vector32.z;
        return this;
    }

    public Matrix4 setToTranslationAndScaling(float f, float f2, float f3, float f4, float f5, float f6) {
        idt();
        this.val[12] = f;
        this.val[13] = f2;
        this.val[14] = f3;
        this.val[0] = f4;
        this.val[5] = f5;
        this.val[10] = f6;
        return this;
    }

    public Matrix4 setToRotation(Vector3 vector3, float f) {
        if (f == 0.0f) {
            idt();
            return this;
        }
        return set(quat.set(vector3, f));
    }

    public Matrix4 setToRotationRad(Vector3 vector3, float f) {
        if (f == 0.0f) {
            idt();
            return this;
        }
        return set(quat.setFromAxisRad(vector3, f));
    }

    public Matrix4 setToRotation(float f, float f2, float f3, float f4) {
        if (f4 == 0.0f) {
            idt();
            return this;
        }
        return set(quat.setFromAxis(f, f2, f3, f4));
    }

    public Matrix4 setToRotationRad(float f, float f2, float f3, float f4) {
        if (f4 == 0.0f) {
            idt();
            return this;
        }
        return set(quat.setFromAxisRad(f, f2, f3, f4));
    }

    public Matrix4 setToRotation(Vector3 vector3, Vector3 vector32) {
        return set(quat.setFromCross(vector3, vector32));
    }

    public Matrix4 setToRotation(float f, float f2, float f3, float f4, float f5, float f6) {
        return set(quat.setFromCross(f, f2, f3, f4, f5, f6));
    }

    public Matrix4 setFromEulerAngles(float f, float f2, float f3) {
        quat.setEulerAngles(f, f2, f3);
        return set(quat);
    }

    public Matrix4 setFromEulerAnglesRad(float f, float f2, float f3) {
        quat.setEulerAnglesRad(f, f2, f3);
        return set(quat);
    }

    public Matrix4 setToScaling(Vector3 vector3) {
        idt();
        this.val[0] = vector3.x;
        this.val[5] = vector3.y;
        this.val[10] = vector3.z;
        return this;
    }

    public Matrix4 setToScaling(float f, float f2, float f3) {
        idt();
        this.val[0] = f;
        this.val[5] = f2;
        this.val[10] = f3;
        return this;
    }

    public Matrix4 setToLookAt(Vector3 vector3, Vector3 vector32) {
        l_vez.set(vector3).nor();
        l_vex.set(vector3).crs(vector32).nor();
        l_vey.set(l_vex).crs(l_vez).nor();
        idt();
        this.val[0] = l_vex.x;
        this.val[4] = l_vex.y;
        this.val[8] = l_vex.z;
        this.val[1] = l_vey.x;
        this.val[5] = l_vey.y;
        this.val[9] = l_vey.z;
        this.val[2] = -l_vez.x;
        this.val[6] = -l_vez.y;
        this.val[10] = -l_vez.z;
        return this;
    }

    public Matrix4 setToLookAt(Vector3 vector3, Vector3 vector32, Vector3 vector33) {
        tmpVec.set(vector32).sub(vector3);
        setToLookAt(tmpVec, vector33);
        mul(tmpMat.setToTranslation(-vector3.x, -vector3.y, -vector3.z));
        return this;
    }

    public Matrix4 setToWorld(Vector3 vector3, Vector3 vector32, Vector3 vector33) {
        tmpForward.set(vector32).nor();
        right.set(tmpForward).crs(vector33).nor();
        tmpUp.set(right).crs(tmpForward).nor();
        set(right, tmpUp, tmpForward.scl(-1.0f), vector3);
        return this;
    }

    public Matrix4 lerp(Matrix4 matrix4, float f) {
        for (int i = 0; i < 16; i++) {
            float[] fArr = this.val;
            fArr[i] = (fArr[i] * (1.0f - f)) + (matrix4.val[i] * f);
        }
        return this;
    }

    public Matrix4 avg(Matrix4 matrix4, float f) {
        getScale(tmpVec);
        matrix4.getScale(tmpForward);
        getRotation(quat);
        matrix4.getRotation(quat2);
        getTranslation(tmpUp);
        matrix4.getTranslation(right);
        setToScaling(tmpVec.scl(f).add(tmpForward.scl(1.0f - f)));
        rotate(quat.slerp(quat2, 1.0f - f));
        setTranslation(tmpUp.scl(f).add(right.scl(1.0f - f)));
        return this;
    }

    public Matrix4 avg(Matrix4[] matrix4Arr) {
        float length = 1.0f / matrix4Arr.length;
        tmpVec.set(matrix4Arr[0].getScale(tmpUp).scl(length));
        quat.set(matrix4Arr[0].getRotation(quat2).exp(length));
        tmpForward.set(matrix4Arr[0].getTranslation(tmpUp).scl(length));
        for (int i = 1; i < matrix4Arr.length; i++) {
            tmpVec.add(matrix4Arr[i].getScale(tmpUp).scl(length));
            quat.mul(matrix4Arr[i].getRotation(quat2).exp(length));
            tmpForward.add(matrix4Arr[i].getTranslation(tmpUp).scl(length));
        }
        quat.nor();
        setToScaling(tmpVec);
        rotate(quat);
        setTranslation(tmpForward);
        return this;
    }

    public Matrix4 avg(Matrix4[] matrix4Arr, float[] fArr) {
        tmpVec.set(matrix4Arr[0].getScale(tmpUp).scl(fArr[0]));
        quat.set(matrix4Arr[0].getRotation(quat2).exp(fArr[0]));
        tmpForward.set(matrix4Arr[0].getTranslation(tmpUp).scl(fArr[0]));
        for (int i = 1; i < matrix4Arr.length; i++) {
            tmpVec.add(matrix4Arr[i].getScale(tmpUp).scl(fArr[i]));
            quat.mul(matrix4Arr[i].getRotation(quat2).exp(fArr[i]));
            tmpForward.add(matrix4Arr[i].getTranslation(tmpUp).scl(fArr[i]));
        }
        quat.nor();
        setToScaling(tmpVec);
        rotate(quat);
        setTranslation(tmpForward);
        return this;
    }

    public Matrix4 set(Matrix3 matrix3) {
        this.val[0] = matrix3.val[0];
        this.val[1] = matrix3.val[1];
        this.val[2] = matrix3.val[2];
        this.val[3] = 0.0f;
        this.val[4] = matrix3.val[3];
        this.val[5] = matrix3.val[4];
        this.val[6] = matrix3.val[5];
        this.val[7] = 0.0f;
        this.val[8] = 0.0f;
        this.val[9] = 0.0f;
        this.val[10] = 1.0f;
        this.val[11] = 0.0f;
        this.val[12] = matrix3.val[6];
        this.val[13] = matrix3.val[7];
        this.val[14] = 0.0f;
        this.val[15] = matrix3.val[8];
        return this;
    }

    public Matrix4 set(Affine2 affine2) {
        this.val[0] = affine2.m00;
        this.val[1] = affine2.m10;
        this.val[2] = 0.0f;
        this.val[3] = 0.0f;
        this.val[4] = affine2.m01;
        this.val[5] = affine2.m11;
        this.val[6] = 0.0f;
        this.val[7] = 0.0f;
        this.val[8] = 0.0f;
        this.val[9] = 0.0f;
        this.val[10] = 1.0f;
        this.val[11] = 0.0f;
        this.val[12] = affine2.m02;
        this.val[13] = affine2.m12;
        this.val[14] = 0.0f;
        this.val[15] = 1.0f;
        return this;
    }

    public Matrix4 setAsAffine(Affine2 affine2) {
        this.val[0] = affine2.m00;
        this.val[1] = affine2.m10;
        this.val[4] = affine2.m01;
        this.val[5] = affine2.m11;
        this.val[12] = affine2.m02;
        this.val[13] = affine2.m12;
        return this;
    }

    public Matrix4 setAsAffine(Matrix4 matrix4) {
        this.val[0] = matrix4.val[0];
        this.val[1] = matrix4.val[1];
        this.val[4] = matrix4.val[4];
        this.val[5] = matrix4.val[5];
        this.val[12] = matrix4.val[12];
        this.val[13] = matrix4.val[13];
        return this;
    }

    public Matrix4 scl(Vector3 vector3) {
        float[] fArr = this.val;
        fArr[0] = fArr[0] * vector3.x;
        float[] fArr2 = this.val;
        fArr2[5] = fArr2[5] * vector3.y;
        float[] fArr3 = this.val;
        fArr3[10] = fArr3[10] * vector3.z;
        return this;
    }

    public Matrix4 scl(float f, float f2, float f3) {
        float[] fArr = this.val;
        fArr[0] = fArr[0] * f;
        float[] fArr2 = this.val;
        fArr2[5] = fArr2[5] * f2;
        float[] fArr3 = this.val;
        fArr3[10] = fArr3[10] * f3;
        return this;
    }

    public Matrix4 scl(float f) {
        float[] fArr = this.val;
        fArr[0] = fArr[0] * f;
        float[] fArr2 = this.val;
        fArr2[5] = fArr2[5] * f;
        float[] fArr3 = this.val;
        fArr3[10] = fArr3[10] * f;
        return this;
    }

    public Vector3 getTranslation(Vector3 vector3) {
        vector3.x = this.val[12];
        vector3.y = this.val[13];
        vector3.z = this.val[14];
        return vector3;
    }

    public Quaternion getRotation(Quaternion quaternion, boolean z) {
        return quaternion.setFromMatrix(z, this);
    }

    public Quaternion getRotation(Quaternion quaternion) {
        return quaternion.setFromMatrix(this);
    }

    public float getScaleXSquared() {
        return (this.val[0] * this.val[0]) + (this.val[4] * this.val[4]) + (this.val[8] * this.val[8]);
    }

    public float getScaleYSquared() {
        return (this.val[1] * this.val[1]) + (this.val[5] * this.val[5]) + (this.val[9] * this.val[9]);
    }

    public float getScaleZSquared() {
        return (this.val[2] * this.val[2]) + (this.val[6] * this.val[6]) + (this.val[10] * this.val[10]);
    }

    public float getScaleX() {
        return (MathUtils.isZero(this.val[4]) && MathUtils.isZero(this.val[8])) ? Math.abs(this.val[0]) : (float) Math.sqrt(getScaleXSquared());
    }

    public float getScaleY() {
        return (MathUtils.isZero(this.val[1]) && MathUtils.isZero(this.val[9])) ? Math.abs(this.val[5]) : (float) Math.sqrt(getScaleYSquared());
    }

    public float getScaleZ() {
        return (MathUtils.isZero(this.val[2]) && MathUtils.isZero(this.val[6])) ? Math.abs(this.val[10]) : (float) Math.sqrt(getScaleZSquared());
    }

    public Vector3 getScale(Vector3 vector3) {
        return vector3.set(getScaleX(), getScaleY(), getScaleZ());
    }

    public Matrix4 toNormalMatrix() {
        this.val[12] = 0.0f;
        this.val[13] = 0.0f;
        this.val[14] = 0.0f;
        return inv().tra();
    }

    public String toString() {
        return "[" + this.val[0] + "|" + this.val[4] + "|" + this.val[8] + "|" + this.val[12] + "]\n[" + this.val[1] + "|" + this.val[5] + "|" + this.val[9] + "|" + this.val[13] + "]\n[" + this.val[2] + "|" + this.val[6] + "|" + this.val[10] + "|" + this.val[14] + "]\n[" + this.val[3] + "|" + this.val[7] + "|" + this.val[11] + "|" + this.val[15] + "]\n";
    }

    public static void mul(float[] fArr, float[] fArr2) {
        float f = (fArr[0] * fArr2[0]) + (fArr[4] * fArr2[1]) + (fArr[8] * fArr2[2]) + (fArr[12] * fArr2[3]);
        float f2 = (fArr[0] * fArr2[4]) + (fArr[4] * fArr2[5]) + (fArr[8] * fArr2[6]) + (fArr[12] * fArr2[7]);
        float f3 = (fArr[0] * fArr2[8]) + (fArr[4] * fArr2[9]) + (fArr[8] * fArr2[10]) + (fArr[12] * fArr2[11]);
        float f4 = (fArr[0] * fArr2[12]) + (fArr[4] * fArr2[13]) + (fArr[8] * fArr2[14]) + (fArr[12] * fArr2[15]);
        float f5 = (fArr[1] * fArr2[0]) + (fArr[5] * fArr2[1]) + (fArr[9] * fArr2[2]) + (fArr[13] * fArr2[3]);
        float f6 = (fArr[1] * fArr2[4]) + (fArr[5] * fArr2[5]) + (fArr[9] * fArr2[6]) + (fArr[13] * fArr2[7]);
        float f7 = (fArr[1] * fArr2[8]) + (fArr[5] * fArr2[9]) + (fArr[9] * fArr2[10]) + (fArr[13] * fArr2[11]);
        float f8 = (fArr[1] * fArr2[12]) + (fArr[5] * fArr2[13]) + (fArr[9] * fArr2[14]) + (fArr[13] * fArr2[15]);
        float f9 = (fArr[2] * fArr2[0]) + (fArr[6] * fArr2[1]) + (fArr[10] * fArr2[2]) + (fArr[14] * fArr2[3]);
        float f10 = (fArr[2] * fArr2[4]) + (fArr[6] * fArr2[5]) + (fArr[10] * fArr2[6]) + (fArr[14] * fArr2[7]);
        float f11 = (fArr[2] * fArr2[8]) + (fArr[6] * fArr2[9]) + (fArr[10] * fArr2[10]) + (fArr[14] * fArr2[11]);
        float f12 = (fArr[2] * fArr2[12]) + (fArr[6] * fArr2[13]) + (fArr[10] * fArr2[14]) + (fArr[14] * fArr2[15]);
        float f13 = (fArr[3] * fArr2[0]) + (fArr[7] * fArr2[1]) + (fArr[11] * fArr2[2]) + (fArr[15] * fArr2[3]);
        float f14 = (fArr[3] * fArr2[4]) + (fArr[7] * fArr2[5]) + (fArr[11] * fArr2[6]) + (fArr[15] * fArr2[7]);
        float f15 = (fArr[3] * fArr2[8]) + (fArr[7] * fArr2[9]) + (fArr[11] * fArr2[10]) + (fArr[15] * fArr2[11]);
        float f16 = (fArr[3] * fArr2[12]) + (fArr[7] * fArr2[13]) + (fArr[11] * fArr2[14]) + (fArr[15] * fArr2[15]);
        fArr[0] = f;
        fArr[1] = f5;
        fArr[2] = f9;
        fArr[3] = f13;
        fArr[4] = f2;
        fArr[5] = f6;
        fArr[6] = f10;
        fArr[7] = f14;
        fArr[8] = f3;
        fArr[9] = f7;
        fArr[10] = f11;
        fArr[11] = f15;
        fArr[12] = f4;
        fArr[13] = f8;
        fArr[14] = f12;
        fArr[15] = f16;
    }

    public static void mulVec(float[] fArr, float[] fArr2) {
        float f = (fArr2[0] * fArr[0]) + (fArr2[1] * fArr[4]) + (fArr2[2] * fArr[8]) + fArr[12];
        float f2 = (fArr2[0] * fArr[1]) + (fArr2[1] * fArr[5]) + (fArr2[2] * fArr[9]) + fArr[13];
        float f3 = (fArr2[0] * fArr[2]) + (fArr2[1] * fArr[6]) + (fArr2[2] * fArr[10]) + fArr[14];
        fArr2[0] = f;
        fArr2[1] = f2;
        fArr2[2] = f3;
    }

    public static void prj(float[] fArr, float[] fArr2) {
        float f = 1.0f / ((((fArr2[0] * fArr[3]) + (fArr2[1] * fArr[7])) + (fArr2[2] * fArr[11])) + fArr[15]);
        float f2 = ((fArr2[0] * fArr[0]) + (fArr2[1] * fArr[4]) + (fArr2[2] * fArr[8]) + fArr[12]) * f;
        float f3 = ((fArr2[0] * fArr[1]) + (fArr2[1] * fArr[5]) + (fArr2[2] * fArr[9]) + fArr[13]) * f;
        float f4 = ((fArr2[0] * fArr[2]) + (fArr2[1] * fArr[6]) + (fArr2[2] * fArr[10]) + fArr[14]) * f;
        fArr2[0] = f2;
        fArr2[1] = f3;
        fArr2[2] = f4;
    }

    public static void rot(float[] fArr, float[] fArr2) {
        float f = (fArr2[0] * fArr[0]) + (fArr2[1] * fArr[4]) + (fArr2[2] * fArr[8]);
        float f2 = (fArr2[0] * fArr[1]) + (fArr2[1] * fArr[5]) + (fArr2[2] * fArr[9]);
        float f3 = (fArr2[0] * fArr[2]) + (fArr2[1] * fArr[6]) + (fArr2[2] * fArr[10]);
        fArr2[0] = f;
        fArr2[1] = f2;
        fArr2[2] = f3;
    }

    public static boolean inv(float[] fArr) {
        float det = det(fArr);
        if (det == 0.0f) {
            return false;
        }
        float f = ((((((fArr[9] * fArr[14]) * fArr[7]) - ((fArr[13] * fArr[10]) * fArr[7])) + ((fArr[13] * fArr[6]) * fArr[11])) - ((fArr[5] * fArr[14]) * fArr[11])) - ((fArr[9] * fArr[6]) * fArr[15])) + (fArr[5] * fArr[10] * fArr[15]);
        float f2 = ((((((fArr[12] * fArr[10]) * fArr[7]) - ((fArr[8] * fArr[14]) * fArr[7])) - ((fArr[12] * fArr[6]) * fArr[11])) + ((fArr[4] * fArr[14]) * fArr[11])) + ((fArr[8] * fArr[6]) * fArr[15])) - ((fArr[4] * fArr[10]) * fArr[15]);
        float f3 = ((((((fArr[8] * fArr[13]) * fArr[7]) - ((fArr[12] * fArr[9]) * fArr[7])) + ((fArr[12] * fArr[5]) * fArr[11])) - ((fArr[4] * fArr[13]) * fArr[11])) - ((fArr[8] * fArr[5]) * fArr[15])) + (fArr[4] * fArr[9] * fArr[15]);
        float f4 = ((((((fArr[12] * fArr[9]) * fArr[6]) - ((fArr[8] * fArr[13]) * fArr[6])) - ((fArr[12] * fArr[5]) * fArr[10])) + ((fArr[4] * fArr[13]) * fArr[10])) + ((fArr[8] * fArr[5]) * fArr[14])) - ((fArr[4] * fArr[9]) * fArr[14]);
        float f5 = ((((((fArr[13] * fArr[10]) * fArr[3]) - ((fArr[9] * fArr[14]) * fArr[3])) - ((fArr[13] * fArr[2]) * fArr[11])) + ((fArr[1] * fArr[14]) * fArr[11])) + ((fArr[9] * fArr[2]) * fArr[15])) - ((fArr[1] * fArr[10]) * fArr[15]);
        float f6 = ((((((fArr[8] * fArr[14]) * fArr[3]) - ((fArr[12] * fArr[10]) * fArr[3])) + ((fArr[12] * fArr[2]) * fArr[11])) - ((fArr[0] * fArr[14]) * fArr[11])) - ((fArr[8] * fArr[2]) * fArr[15])) + (fArr[0] * fArr[10] * fArr[15]);
        float f7 = ((((((fArr[12] * fArr[9]) * fArr[3]) - ((fArr[8] * fArr[13]) * fArr[3])) - ((fArr[12] * fArr[1]) * fArr[11])) + ((fArr[0] * fArr[13]) * fArr[11])) + ((fArr[8] * fArr[1]) * fArr[15])) - ((fArr[0] * fArr[9]) * fArr[15]);
        float f8 = ((((((fArr[8] * fArr[13]) * fArr[2]) - ((fArr[12] * fArr[9]) * fArr[2])) + ((fArr[12] * fArr[1]) * fArr[10])) - ((fArr[0] * fArr[13]) * fArr[10])) - ((fArr[8] * fArr[1]) * fArr[14])) + (fArr[0] * fArr[9] * fArr[14]);
        float f9 = ((((((fArr[5] * fArr[14]) * fArr[3]) - ((fArr[13] * fArr[6]) * fArr[3])) + ((fArr[13] * fArr[2]) * fArr[7])) - ((fArr[1] * fArr[14]) * fArr[7])) - ((fArr[5] * fArr[2]) * fArr[15])) + (fArr[1] * fArr[6] * fArr[15]);
        float f10 = ((((((fArr[12] * fArr[6]) * fArr[3]) - ((fArr[4] * fArr[14]) * fArr[3])) - ((fArr[12] * fArr[2]) * fArr[7])) + ((fArr[0] * fArr[14]) * fArr[7])) + ((fArr[4] * fArr[2]) * fArr[15])) - ((fArr[0] * fArr[6]) * fArr[15]);
        float f11 = ((((((fArr[4] * fArr[13]) * fArr[3]) - ((fArr[12] * fArr[5]) * fArr[3])) + ((fArr[12] * fArr[1]) * fArr[7])) - ((fArr[0] * fArr[13]) * fArr[7])) - ((fArr[4] * fArr[1]) * fArr[15])) + (fArr[0] * fArr[5] * fArr[15]);
        float f12 = ((((((fArr[12] * fArr[5]) * fArr[2]) - ((fArr[4] * fArr[13]) * fArr[2])) - ((fArr[12] * fArr[1]) * fArr[6])) + ((fArr[0] * fArr[13]) * fArr[6])) + ((fArr[4] * fArr[1]) * fArr[14])) - ((fArr[0] * fArr[5]) * fArr[14]);
        float f13 = ((((((fArr[9] * fArr[6]) * fArr[3]) - ((fArr[5] * fArr[10]) * fArr[3])) - ((fArr[9] * fArr[2]) * fArr[7])) + ((fArr[1] * fArr[10]) * fArr[7])) + ((fArr[5] * fArr[2]) * fArr[11])) - ((fArr[1] * fArr[6]) * fArr[11]);
        float f14 = ((((((fArr[4] * fArr[10]) * fArr[3]) - ((fArr[8] * fArr[6]) * fArr[3])) + ((fArr[8] * fArr[2]) * fArr[7])) - ((fArr[0] * fArr[10]) * fArr[7])) - ((fArr[4] * fArr[2]) * fArr[11])) + (fArr[0] * fArr[6] * fArr[11]);
        float f15 = ((((((fArr[8] * fArr[5]) * fArr[3]) - ((fArr[4] * fArr[9]) * fArr[3])) - ((fArr[8] * fArr[1]) * fArr[7])) + ((fArr[0] * fArr[9]) * fArr[7])) + ((fArr[4] * fArr[1]) * fArr[11])) - ((fArr[0] * fArr[5]) * fArr[11]);
        float f16 = ((((((fArr[4] * fArr[9]) * fArr[2]) - ((fArr[8] * fArr[5]) * fArr[2])) + ((fArr[8] * fArr[1]) * fArr[6])) - ((fArr[0] * fArr[9]) * fArr[6])) - ((fArr[4] * fArr[1]) * fArr[10])) + (fArr[0] * fArr[5] * fArr[10]);
        float f17 = 1.0f / det;
        fArr[0] = f * f17;
        fArr[1] = f5 * f17;
        fArr[2] = f9 * f17;
        fArr[3] = f13 * f17;
        fArr[4] = f2 * f17;
        fArr[5] = f6 * f17;
        fArr[6] = f10 * f17;
        fArr[7] = f14 * f17;
        fArr[8] = f3 * f17;
        fArr[9] = f7 * f17;
        fArr[10] = f11 * f17;
        fArr[11] = f15 * f17;
        fArr[12] = f4 * f17;
        fArr[13] = f8 * f17;
        fArr[14] = f12 * f17;
        fArr[15] = f16 * f17;
        return true;
    }

    public static float det(float[] fArr) {
        return (((((((((((((((((((((((((fArr[3] * fArr[6]) * fArr[9]) * fArr[12]) - (((fArr[2] * fArr[7]) * fArr[9]) * fArr[12])) - (((fArr[3] * fArr[5]) * fArr[10]) * fArr[12])) + (((fArr[1] * fArr[7]) * fArr[10]) * fArr[12])) + (((fArr[2] * fArr[5]) * fArr[11]) * fArr[12])) - (((fArr[1] * fArr[6]) * fArr[11]) * fArr[12])) - (((fArr[3] * fArr[6]) * fArr[8]) * fArr[13])) + (((fArr[2] * fArr[7]) * fArr[8]) * fArr[13])) + (((fArr[3] * fArr[4]) * fArr[10]) * fArr[13])) - (((fArr[0] * fArr[7]) * fArr[10]) * fArr[13])) - (((fArr[2] * fArr[4]) * fArr[11]) * fArr[13])) + (((fArr[0] * fArr[6]) * fArr[11]) * fArr[13])) + (((fArr[3] * fArr[5]) * fArr[8]) * fArr[14])) - (((fArr[1] * fArr[7]) * fArr[8]) * fArr[14])) - (((fArr[3] * fArr[4]) * fArr[9]) * fArr[14])) + (((fArr[0] * fArr[7]) * fArr[9]) * fArr[14])) + (((fArr[1] * fArr[4]) * fArr[11]) * fArr[14])) - (((fArr[0] * fArr[5]) * fArr[11]) * fArr[14])) - (((fArr[2] * fArr[5]) * fArr[8]) * fArr[15])) + (((fArr[1] * fArr[6]) * fArr[8]) * fArr[15])) + (((fArr[2] * fArr[4]) * fArr[9]) * fArr[15])) - (((fArr[0] * fArr[6]) * fArr[9]) * fArr[15])) - (((fArr[1] * fArr[4]) * fArr[10]) * fArr[15])) + (fArr[0] * fArr[5] * fArr[10] * fArr[15]);
    }

    public Matrix4 translate(Vector3 vector3) {
        return translate(vector3.x, vector3.y, vector3.z);
    }

    public Matrix4 translate(float f, float f2, float f3) {
        float[] fArr = this.val;
        fArr[12] = fArr[12] + (this.val[0] * f) + (this.val[4] * f2) + (this.val[8] * f3);
        float[] fArr2 = this.val;
        fArr2[13] = fArr2[13] + (this.val[1] * f) + (this.val[5] * f2) + (this.val[9] * f3);
        float[] fArr3 = this.val;
        fArr3[14] = fArr3[14] + (this.val[2] * f) + (this.val[6] * f2) + (this.val[10] * f3);
        float[] fArr4 = this.val;
        fArr4[15] = fArr4[15] + (this.val[3] * f) + (this.val[7] * f2) + (this.val[11] * f3);
        return this;
    }

    public Matrix4 rotate(Vector3 vector3, float f) {
        if (f == 0.0f) {
            return this;
        }
        quat.set(vector3, f);
        return rotate(quat);
    }

    public Matrix4 rotateRad(Vector3 vector3, float f) {
        if (f == 0.0f) {
            return this;
        }
        quat.setFromAxisRad(vector3, f);
        return rotate(quat);
    }

    public Matrix4 rotate(float f, float f2, float f3, float f4) {
        if (f4 == 0.0f) {
            return this;
        }
        quat.setFromAxis(f, f2, f3, f4);
        return rotate(quat);
    }

    public Matrix4 rotateRad(float f, float f2, float f3, float f4) {
        if (f4 == 0.0f) {
            return this;
        }
        quat.setFromAxisRad(f, f2, f3, f4);
        return rotate(quat);
    }

    public Matrix4 rotate(Quaternion quaternion) {
        float f = quaternion.x;
        float f2 = quaternion.y;
        float f3 = quaternion.z;
        float f4 = quaternion.w;
        float f5 = f * f;
        float f6 = f * f2;
        float f7 = f * f3;
        float f8 = f * f4;
        float f9 = f2 * f2;
        float f10 = f2 * f3;
        float f11 = f2 * f4;
        float f12 = f3 * f3;
        float f13 = f3 * f4;
        float f14 = 1.0f - (2.0f * (f9 + f12));
        float f15 = 2.0f * (f6 - f13);
        float f16 = 2.0f * (f7 + f11);
        float f17 = 2.0f * (f6 + f13);
        float f18 = 1.0f - (2.0f * (f5 + f12));
        float f19 = 2.0f * (f10 - f8);
        float f20 = 2.0f * (f7 - f11);
        float f21 = 2.0f * (f10 + f8);
        float f22 = 1.0f - (2.0f * (f5 + f9));
        float f23 = (this.val[0] * f14) + (this.val[4] * f17) + (this.val[8] * f20);
        float f24 = (this.val[0] * f15) + (this.val[4] * f18) + (this.val[8] * f21);
        float f25 = (this.val[0] * f16) + (this.val[4] * f19) + (this.val[8] * f22);
        float f26 = (this.val[1] * f14) + (this.val[5] * f17) + (this.val[9] * f20);
        float f27 = (this.val[1] * f15) + (this.val[5] * f18) + (this.val[9] * f21);
        float f28 = (this.val[1] * f16) + (this.val[5] * f19) + (this.val[9] * f22);
        float f29 = (this.val[2] * f14) + (this.val[6] * f17) + (this.val[10] * f20);
        float f30 = (this.val[2] * f15) + (this.val[6] * f18) + (this.val[10] * f21);
        float f31 = (this.val[2] * f16) + (this.val[6] * f19) + (this.val[10] * f22);
        float f32 = (this.val[3] * f14) + (this.val[7] * f17) + (this.val[11] * f20);
        float f33 = (this.val[3] * f15) + (this.val[7] * f18) + (this.val[11] * f21);
        float f34 = (this.val[3] * f16) + (this.val[7] * f19) + (this.val[11] * f22);
        this.val[0] = f23;
        this.val[1] = f26;
        this.val[2] = f29;
        this.val[3] = f32;
        this.val[4] = f24;
        this.val[5] = f27;
        this.val[6] = f30;
        this.val[7] = f33;
        this.val[8] = f25;
        this.val[9] = f28;
        this.val[10] = f31;
        this.val[11] = f34;
        return this;
    }

    public Matrix4 rotate(Vector3 vector3, Vector3 vector32) {
        return rotate(quat.setFromCross(vector3, vector32));
    }

    public Matrix4 rotateTowardDirection(Vector3 vector3, Vector3 vector32) {
        l_vez.set(vector3).nor();
        l_vex.set(vector3).crs(vector32).nor();
        l_vey.set(l_vex).crs(l_vez).nor();
        float f = (this.val[0] * l_vex.x) + (this.val[4] * l_vex.y) + (this.val[8] * l_vex.z);
        float f2 = (this.val[0] * l_vey.x) + (this.val[4] * l_vey.y) + (this.val[8] * l_vey.z);
        float f3 = (this.val[0] * (-l_vez.x)) + (this.val[4] * (-l_vez.y)) + (this.val[8] * (-l_vez.z));
        float f4 = (this.val[1] * l_vex.x) + (this.val[5] * l_vex.y) + (this.val[9] * l_vex.z);
        float f5 = (this.val[1] * l_vey.x) + (this.val[5] * l_vey.y) + (this.val[9] * l_vey.z);
        float f6 = (this.val[1] * (-l_vez.x)) + (this.val[5] * (-l_vez.y)) + (this.val[9] * (-l_vez.z));
        float f7 = (this.val[2] * l_vex.x) + (this.val[6] * l_vex.y) + (this.val[10] * l_vex.z);
        float f8 = (this.val[2] * l_vey.x) + (this.val[6] * l_vey.y) + (this.val[10] * l_vey.z);
        float f9 = (this.val[2] * (-l_vez.x)) + (this.val[6] * (-l_vez.y)) + (this.val[10] * (-l_vez.z));
        float f10 = (this.val[3] * l_vex.x) + (this.val[7] * l_vex.y) + (this.val[11] * l_vex.z);
        float f11 = (this.val[3] * l_vey.x) + (this.val[7] * l_vey.y) + (this.val[11] * l_vey.z);
        float f12 = (this.val[3] * (-l_vez.x)) + (this.val[7] * (-l_vez.y)) + (this.val[11] * (-l_vez.z));
        this.val[0] = f;
        this.val[1] = f4;
        this.val[2] = f7;
        this.val[3] = f10;
        this.val[4] = f2;
        this.val[5] = f5;
        this.val[6] = f8;
        this.val[7] = f11;
        this.val[8] = f3;
        this.val[9] = f6;
        this.val[10] = f9;
        this.val[11] = f12;
        return this;
    }

    public Matrix4 rotateTowardTarget(Vector3 vector3, Vector3 vector32) {
        tmpVec.set(vector3.x - this.val[12], vector3.y - this.val[13], vector3.z - this.val[14]);
        return rotateTowardDirection(tmpVec, vector32);
    }

    public Matrix4 scale(float f, float f2, float f3) {
        float[] fArr = this.val;
        fArr[0] = fArr[0] * f;
        float[] fArr2 = this.val;
        fArr2[4] = fArr2[4] * f2;
        float[] fArr3 = this.val;
        fArr3[8] = fArr3[8] * f3;
        float[] fArr4 = this.val;
        fArr4[1] = fArr4[1] * f;
        float[] fArr5 = this.val;
        fArr5[5] = fArr5[5] * f2;
        float[] fArr6 = this.val;
        fArr6[9] = fArr6[9] * f3;
        float[] fArr7 = this.val;
        fArr7[2] = fArr7[2] * f;
        float[] fArr8 = this.val;
        fArr8[6] = fArr8[6] * f2;
        float[] fArr9 = this.val;
        fArr9[10] = fArr9[10] * f3;
        float[] fArr10 = this.val;
        fArr10[3] = fArr10[3] * f;
        float[] fArr11 = this.val;
        fArr11[7] = fArr11[7] * f2;
        float[] fArr12 = this.val;
        fArr12[11] = fArr12[11] * f3;
        return this;
    }

    public void extract4x3Matrix(float[] fArr) {
        fArr[0] = this.val[0];
        fArr[1] = this.val[1];
        fArr[2] = this.val[2];
        fArr[3] = this.val[4];
        fArr[4] = this.val[5];
        fArr[5] = this.val[6];
        fArr[6] = this.val[8];
        fArr[7] = this.val[9];
        fArr[8] = this.val[10];
        fArr[9] = this.val[12];
        fArr[10] = this.val[13];
        fArr[11] = this.val[14];
    }

    public boolean hasRotationOrScaling() {
        return (MathUtils.isEqual(this.val[0], 1.0f) && MathUtils.isEqual(this.val[5], 1.0f) && MathUtils.isEqual(this.val[10], 1.0f) && MathUtils.isZero(this.val[4]) && MathUtils.isZero(this.val[8]) && MathUtils.isZero(this.val[1]) && MathUtils.isZero(this.val[9]) && MathUtils.isZero(this.val[2]) && MathUtils.isZero(this.val[6])) ? false : true;
    }
}
