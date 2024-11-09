package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Affine2.class */
public final class Affine2 implements Serializable {
    private static final long serialVersionUID = 1524569123485049187L;
    public float m00 = 1.0f;
    public float m01 = 0.0f;
    public float m02 = 0.0f;
    public float m10 = 0.0f;
    public float m11 = 1.0f;
    public float m12 = 0.0f;

    public Affine2() {
    }

    public Affine2(Affine2 affine2) {
        set(affine2);
    }

    public final Affine2 idt() {
        this.m00 = 1.0f;
        this.m01 = 0.0f;
        this.m02 = 0.0f;
        this.m10 = 0.0f;
        this.m11 = 1.0f;
        this.m12 = 0.0f;
        return this;
    }

    public final Affine2 set(Affine2 affine2) {
        this.m00 = affine2.m00;
        this.m01 = affine2.m01;
        this.m02 = affine2.m02;
        this.m10 = affine2.m10;
        this.m11 = affine2.m11;
        this.m12 = affine2.m12;
        return this;
    }

    public final Affine2 set(Matrix3 matrix3) {
        float[] fArr = matrix3.val;
        this.m00 = fArr[0];
        this.m01 = fArr[3];
        this.m02 = fArr[6];
        this.m10 = fArr[1];
        this.m11 = fArr[4];
        this.m12 = fArr[7];
        return this;
    }

    public final Affine2 set(Matrix4 matrix4) {
        float[] fArr = matrix4.val;
        this.m00 = fArr[0];
        this.m01 = fArr[4];
        this.m02 = fArr[12];
        this.m10 = fArr[1];
        this.m11 = fArr[5];
        this.m12 = fArr[13];
        return this;
    }

    public final Affine2 setToTranslation(float f, float f2) {
        this.m00 = 1.0f;
        this.m01 = 0.0f;
        this.m02 = f;
        this.m10 = 0.0f;
        this.m11 = 1.0f;
        this.m12 = f2;
        return this;
    }

    public final Affine2 setToTranslation(Vector2 vector2) {
        return setToTranslation(vector2.x, vector2.y);
    }

    public final Affine2 setToScaling(float f, float f2) {
        this.m00 = f;
        this.m01 = 0.0f;
        this.m02 = 0.0f;
        this.m10 = 0.0f;
        this.m11 = f2;
        this.m12 = 0.0f;
        return this;
    }

    public final Affine2 setToScaling(Vector2 vector2) {
        return setToScaling(vector2.x, vector2.y);
    }

    public final Affine2 setToRotation(float f) {
        float cosDeg = MathUtils.cosDeg(f);
        float sinDeg = MathUtils.sinDeg(f);
        this.m00 = cosDeg;
        this.m01 = -sinDeg;
        this.m02 = 0.0f;
        this.m10 = sinDeg;
        this.m11 = cosDeg;
        this.m12 = 0.0f;
        return this;
    }

    public final Affine2 setToRotationRad(float f) {
        float cos = MathUtils.cos(f);
        float sin = MathUtils.sin(f);
        this.m00 = cos;
        this.m01 = -sin;
        this.m02 = 0.0f;
        this.m10 = sin;
        this.m11 = cos;
        this.m12 = 0.0f;
        return this;
    }

    public final Affine2 setToRotation(float f, float f2) {
        this.m00 = f;
        this.m01 = -f2;
        this.m02 = 0.0f;
        this.m10 = f2;
        this.m11 = f;
        this.m12 = 0.0f;
        return this;
    }

    public final Affine2 setToShearing(float f, float f2) {
        this.m00 = 1.0f;
        this.m01 = f;
        this.m02 = 0.0f;
        this.m10 = f2;
        this.m11 = 1.0f;
        this.m12 = 0.0f;
        return this;
    }

    public final Affine2 setToShearing(Vector2 vector2) {
        return setToShearing(vector2.x, vector2.y);
    }

    public final Affine2 setToTrnRotScl(float f, float f2, float f3, float f4, float f5) {
        this.m02 = f;
        this.m12 = f2;
        if (f3 == 0.0f) {
            this.m00 = f4;
            this.m01 = 0.0f;
            this.m10 = 0.0f;
            this.m11 = f5;
        } else {
            float sinDeg = MathUtils.sinDeg(f3);
            float cosDeg = MathUtils.cosDeg(f3);
            this.m00 = cosDeg * f4;
            this.m01 = (-sinDeg) * f5;
            this.m10 = sinDeg * f4;
            this.m11 = cosDeg * f5;
        }
        return this;
    }

    public final Affine2 setToTrnRotScl(Vector2 vector2, float f, Vector2 vector22) {
        return setToTrnRotScl(vector2.x, vector2.y, f, vector22.x, vector22.y);
    }

    public final Affine2 setToTrnRotRadScl(float f, float f2, float f3, float f4, float f5) {
        this.m02 = f;
        this.m12 = f2;
        if (f3 == 0.0f) {
            this.m00 = f4;
            this.m01 = 0.0f;
            this.m10 = 0.0f;
            this.m11 = f5;
        } else {
            float sin = MathUtils.sin(f3);
            float cos = MathUtils.cos(f3);
            this.m00 = cos * f4;
            this.m01 = (-sin) * f5;
            this.m10 = sin * f4;
            this.m11 = cos * f5;
        }
        return this;
    }

    public final Affine2 setToTrnRotRadScl(Vector2 vector2, float f, Vector2 vector22) {
        return setToTrnRotRadScl(vector2.x, vector2.y, f, vector22.x, vector22.y);
    }

    public final Affine2 setToTrnScl(float f, float f2, float f3, float f4) {
        this.m00 = f3;
        this.m01 = 0.0f;
        this.m02 = f;
        this.m10 = 0.0f;
        this.m11 = f4;
        this.m12 = f2;
        return this;
    }

    public final Affine2 setToTrnScl(Vector2 vector2, Vector2 vector22) {
        return setToTrnScl(vector2.x, vector2.y, vector22.x, vector22.y);
    }

    public final Affine2 setToProduct(Affine2 affine2, Affine2 affine22) {
        this.m00 = (affine2.m00 * affine22.m00) + (affine2.m01 * affine22.m10);
        this.m01 = (affine2.m00 * affine22.m01) + (affine2.m01 * affine22.m11);
        this.m02 = (affine2.m00 * affine22.m02) + (affine2.m01 * affine22.m12) + affine2.m02;
        this.m10 = (affine2.m10 * affine22.m00) + (affine2.m11 * affine22.m10);
        this.m11 = (affine2.m10 * affine22.m01) + (affine2.m11 * affine22.m11);
        this.m12 = (affine2.m10 * affine22.m02) + (affine2.m11 * affine22.m12) + affine2.m12;
        return this;
    }

    public final Affine2 inv() {
        float det = det();
        if (det == 0.0f) {
            throw new GdxRuntimeException("Can't invert a singular affine matrix");
        }
        float f = 1.0f / det;
        float f2 = this.m11;
        float f3 = -this.m01;
        float f4 = (this.m01 * this.m12) - (this.m11 * this.m02);
        float f5 = -this.m10;
        float f6 = this.m00;
        float f7 = (this.m10 * this.m02) - (this.m00 * this.m12);
        this.m00 = f * f2;
        this.m01 = f * f3;
        this.m02 = f * f4;
        this.m10 = f * f5;
        this.m11 = f * f6;
        this.m12 = f * f7;
        return this;
    }

    public final Affine2 mul(Affine2 affine2) {
        float f = (this.m00 * affine2.m00) + (this.m01 * affine2.m10);
        float f2 = (this.m00 * affine2.m01) + (this.m01 * affine2.m11);
        float f3 = (this.m00 * affine2.m02) + (this.m01 * affine2.m12) + this.m02;
        float f4 = (this.m10 * affine2.m00) + (this.m11 * affine2.m10);
        float f5 = (this.m10 * affine2.m01) + (this.m11 * affine2.m11);
        float f6 = (this.m10 * affine2.m02) + (this.m11 * affine2.m12) + this.m12;
        this.m00 = f;
        this.m01 = f2;
        this.m02 = f3;
        this.m10 = f4;
        this.m11 = f5;
        this.m12 = f6;
        return this;
    }

    public final Affine2 preMul(Affine2 affine2) {
        float f = (affine2.m00 * this.m00) + (affine2.m01 * this.m10);
        float f2 = (affine2.m00 * this.m01) + (affine2.m01 * this.m11);
        float f3 = (affine2.m00 * this.m02) + (affine2.m01 * this.m12) + affine2.m02;
        float f4 = (affine2.m10 * this.m00) + (affine2.m11 * this.m10);
        float f5 = (affine2.m10 * this.m01) + (affine2.m11 * this.m11);
        float f6 = (affine2.m10 * this.m02) + (affine2.m11 * this.m12) + affine2.m12;
        this.m00 = f;
        this.m01 = f2;
        this.m02 = f3;
        this.m10 = f4;
        this.m11 = f5;
        this.m12 = f6;
        return this;
    }

    public final Affine2 translate(float f, float f2) {
        this.m02 += (this.m00 * f) + (this.m01 * f2);
        this.m12 += (this.m10 * f) + (this.m11 * f2);
        return this;
    }

    public final Affine2 translate(Vector2 vector2) {
        return translate(vector2.x, vector2.y);
    }

    public final Affine2 preTranslate(float f, float f2) {
        this.m02 += f;
        this.m12 += f2;
        return this;
    }

    public final Affine2 preTranslate(Vector2 vector2) {
        return preTranslate(vector2.x, vector2.y);
    }

    public final Affine2 scale(float f, float f2) {
        this.m00 *= f;
        this.m01 *= f2;
        this.m10 *= f;
        this.m11 *= f2;
        return this;
    }

    public final Affine2 scale(Vector2 vector2) {
        return scale(vector2.x, vector2.y);
    }

    public final Affine2 preScale(float f, float f2) {
        this.m00 *= f;
        this.m01 *= f;
        this.m02 *= f;
        this.m10 *= f2;
        this.m11 *= f2;
        this.m12 *= f2;
        return this;
    }

    public final Affine2 preScale(Vector2 vector2) {
        return preScale(vector2.x, vector2.y);
    }

    public final Affine2 rotate(float f) {
        if (f == 0.0f) {
            return this;
        }
        float cosDeg = MathUtils.cosDeg(f);
        float sinDeg = MathUtils.sinDeg(f);
        float f2 = (this.m00 * cosDeg) + (this.m01 * sinDeg);
        float f3 = (this.m00 * (-sinDeg)) + (this.m01 * cosDeg);
        float f4 = (this.m10 * cosDeg) + (this.m11 * sinDeg);
        float f5 = (this.m10 * (-sinDeg)) + (this.m11 * cosDeg);
        this.m00 = f2;
        this.m01 = f3;
        this.m10 = f4;
        this.m11 = f5;
        return this;
    }

    public final Affine2 rotateRad(float f) {
        if (f == 0.0f) {
            return this;
        }
        float cos = MathUtils.cos(f);
        float sin = MathUtils.sin(f);
        float f2 = (this.m00 * cos) + (this.m01 * sin);
        float f3 = (this.m00 * (-sin)) + (this.m01 * cos);
        float f4 = (this.m10 * cos) + (this.m11 * sin);
        float f5 = (this.m10 * (-sin)) + (this.m11 * cos);
        this.m00 = f2;
        this.m01 = f3;
        this.m10 = f4;
        this.m11 = f5;
        return this;
    }

    public final Affine2 preRotate(float f) {
        if (f == 0.0f) {
            return this;
        }
        float cosDeg = MathUtils.cosDeg(f);
        float sinDeg = MathUtils.sinDeg(f);
        float f2 = (cosDeg * this.m00) - (sinDeg * this.m10);
        float f3 = (cosDeg * this.m01) - (sinDeg * this.m11);
        float f4 = (cosDeg * this.m02) - (sinDeg * this.m12);
        float f5 = (sinDeg * this.m00) + (cosDeg * this.m10);
        float f6 = (sinDeg * this.m01) + (cosDeg * this.m11);
        float f7 = (sinDeg * this.m02) + (cosDeg * this.m12);
        this.m00 = f2;
        this.m01 = f3;
        this.m02 = f4;
        this.m10 = f5;
        this.m11 = f6;
        this.m12 = f7;
        return this;
    }

    public final Affine2 preRotateRad(float f) {
        if (f == 0.0f) {
            return this;
        }
        float cos = MathUtils.cos(f);
        float sin = MathUtils.sin(f);
        float f2 = (cos * this.m00) - (sin * this.m10);
        float f3 = (cos * this.m01) - (sin * this.m11);
        float f4 = (cos * this.m02) - (sin * this.m12);
        float f5 = (sin * this.m00) + (cos * this.m10);
        float f6 = (sin * this.m01) + (cos * this.m11);
        float f7 = (sin * this.m02) + (cos * this.m12);
        this.m00 = f2;
        this.m01 = f3;
        this.m02 = f4;
        this.m10 = f5;
        this.m11 = f6;
        this.m12 = f7;
        return this;
    }

    public final Affine2 shear(float f, float f2) {
        float f3 = this.m00 + (f2 * this.m01);
        float f4 = this.m01 + (f * this.m00);
        this.m00 = f3;
        this.m01 = f4;
        float f5 = this.m10 + (f2 * this.m11);
        float f6 = this.m11 + (f * this.m10);
        this.m10 = f5;
        this.m11 = f6;
        return this;
    }

    public final Affine2 shear(Vector2 vector2) {
        return shear(vector2.x, vector2.y);
    }

    public final Affine2 preShear(float f, float f2) {
        float f3 = this.m00 + (f * this.m10);
        float f4 = this.m01 + (f * this.m11);
        float f5 = this.m02 + (f * this.m12);
        float f6 = this.m10 + (f2 * this.m00);
        float f7 = this.m11 + (f2 * this.m01);
        float f8 = this.m12 + (f2 * this.m02);
        this.m00 = f3;
        this.m01 = f4;
        this.m02 = f5;
        this.m10 = f6;
        this.m11 = f7;
        this.m12 = f8;
        return this;
    }

    public final Affine2 preShear(Vector2 vector2) {
        return preShear(vector2.x, vector2.y);
    }

    public final float det() {
        return (this.m00 * this.m11) - (this.m01 * this.m10);
    }

    public final Vector2 getTranslation(Vector2 vector2) {
        vector2.x = this.m02;
        vector2.y = this.m12;
        return vector2;
    }

    public final boolean isTranslation() {
        return this.m00 == 1.0f && this.m11 == 1.0f && this.m01 == 0.0f && this.m10 == 0.0f;
    }

    public final boolean isIdt() {
        return this.m00 == 1.0f && this.m02 == 0.0f && this.m12 == 0.0f && this.m11 == 1.0f && this.m01 == 0.0f && this.m10 == 0.0f;
    }

    public final void applyTo(Vector2 vector2) {
        float f = vector2.x;
        float f2 = vector2.y;
        vector2.x = (this.m00 * f) + (this.m01 * f2) + this.m02;
        vector2.y = (this.m10 * f) + (this.m11 * f2) + this.m12;
    }

    public final String toString() {
        return "[" + this.m00 + "|" + this.m01 + "|" + this.m02 + "]\n[" + this.m10 + "|" + this.m11 + "|" + this.m12 + "]\n[0.0|0.0|0.1]";
    }
}
