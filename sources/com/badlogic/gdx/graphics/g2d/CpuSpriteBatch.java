package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.GdxRuntimeException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/CpuSpriteBatch.class */
public class CpuSpriteBatch extends SpriteBatch {
    private final Matrix4 virtualMatrix;
    private final Affine2 adjustAffine;
    private boolean adjustNeeded;
    private boolean haveIdentityRealMatrix;
    private final Affine2 tmpAffine;

    public CpuSpriteBatch() {
        this(1000);
    }

    public CpuSpriteBatch(int i) {
        this(i, null);
    }

    public CpuSpriteBatch(int i, ShaderProgram shaderProgram) {
        super(i, shaderProgram);
        this.virtualMatrix = new Matrix4();
        this.adjustAffine = new Affine2();
        this.haveIdentityRealMatrix = true;
        this.tmpAffine = new Affine2();
    }

    public void flushAndSyncTransformMatrix() {
        flush();
        if (this.adjustNeeded) {
            this.haveIdentityRealMatrix = checkIdt(this.virtualMatrix);
            if (!this.haveIdentityRealMatrix && this.virtualMatrix.det() == 0.0f) {
                throw new GdxRuntimeException("Transform matrix is singular, can't sync");
            }
            this.adjustNeeded = false;
            super.setTransformMatrix(this.virtualMatrix);
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public Matrix4 getTransformMatrix() {
        return this.adjustNeeded ? this.virtualMatrix : super.getTransformMatrix();
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void setTransformMatrix(Matrix4 matrix4) {
        Matrix4 transformMatrix = super.getTransformMatrix();
        if (checkEqual(transformMatrix, matrix4)) {
            this.adjustNeeded = false;
            return;
        }
        if (isDrawing()) {
            this.virtualMatrix.setAsAffine(matrix4);
            this.adjustNeeded = true;
            if (this.haveIdentityRealMatrix) {
                this.adjustAffine.set(matrix4);
                return;
            } else {
                this.tmpAffine.set(matrix4);
                this.adjustAffine.set(transformMatrix).inv().mul(this.tmpAffine);
                return;
            }
        }
        transformMatrix.setAsAffine(matrix4);
        this.haveIdentityRealMatrix = checkIdt(transformMatrix);
    }

    public void setTransformMatrix(Affine2 affine2) {
        Matrix4 transformMatrix = super.getTransformMatrix();
        if (checkEqual(transformMatrix, affine2)) {
            this.adjustNeeded = false;
            return;
        }
        this.virtualMatrix.setAsAffine(affine2);
        if (isDrawing()) {
            this.adjustNeeded = true;
            if (!this.haveIdentityRealMatrix) {
                this.adjustAffine.set(transformMatrix).inv().mul(affine2);
                return;
            } else {
                this.adjustAffine.set(affine2);
                return;
            }
        }
        transformMatrix.setAsAffine(affine2);
        this.haveIdentityRealMatrix = checkIdt(transformMatrix);
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int i, int i2, int i3, int i4, boolean z, boolean z2) {
        if (!this.adjustNeeded) {
            super.draw(texture, f, f2, f3, f4, f5, f6, f7, f8, f9, i, i2, i3, i4, z, z2);
        } else {
            drawAdjusted(texture, f, f2, f3, f4, f5, f6, f7, f8, f9, i, i2, i3, i4, z, z2);
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f, float f2, float f3, float f4, int i, int i2, int i3, int i4, boolean z, boolean z2) {
        if (!this.adjustNeeded) {
            super.draw(texture, f, f2, f3, f4, i, i2, i3, i4, z, z2);
        } else {
            drawAdjusted(texture, f, f2, 0.0f, 0.0f, f3, f4, 1.0f, 1.0f, 0.0f, i, i2, i3, i4, z, z2);
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f, float f2, int i, int i2, int i3, int i4) {
        if (!this.adjustNeeded) {
            super.draw(texture, f, f2, i, i2, i3, i4);
        } else {
            drawAdjusted(texture, f, f2, 0.0f, 0.0f, i3, i4, 1.0f, 1.0f, 0.0f, i, i2, i3, i4, false, false);
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (!this.adjustNeeded) {
            super.draw(texture, f, f2, f3, f4, f5, f6, f7, f8);
        } else {
            drawAdjustedUV(texture, f, f2, 0.0f, 0.0f, f3, f4, 1.0f, 1.0f, 0.0f, f5, f6, f7, f8, false, false);
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f, float f2) {
        if (!this.adjustNeeded) {
            super.draw(texture, f, f2);
        } else {
            drawAdjusted(texture, f, f2, 0.0f, 0.0f, texture.getWidth(), texture.getHeight(), 1.0f, 1.0f, 0.0f, 0, 1, 1, 0, false, false);
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f, float f2, float f3, float f4) {
        if (!this.adjustNeeded) {
            super.draw(texture, f, f2, f3, f4);
        } else {
            drawAdjusted(texture, f, f2, 0.0f, 0.0f, f3, f4, 1.0f, 1.0f, 0.0f, 0, 1, 1, 0, false, false);
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion, float f, float f2) {
        if (!this.adjustNeeded) {
            super.draw(textureRegion, f, f2);
        } else {
            drawAdjusted(textureRegion, f, f2, 0.0f, 0.0f, textureRegion.getRegionWidth(), textureRegion.getRegionHeight(), 1.0f, 1.0f, 0.0f);
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion, float f, float f2, float f3, float f4) {
        if (!this.adjustNeeded) {
            super.draw(textureRegion, f, f2, f3, f4);
        } else {
            drawAdjusted(textureRegion, f, f2, 0.0f, 0.0f, f3, f4, 1.0f, 1.0f, 0.0f);
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        if (!this.adjustNeeded) {
            super.draw(textureRegion, f, f2, f3, f4, f5, f6, f7, f8, f9);
        } else {
            drawAdjusted(textureRegion, f, f2, f3, f4, f5, f6, f7, f8, f9);
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, boolean z) {
        if (!this.adjustNeeded) {
            super.draw(textureRegion, f, f2, f3, f4, f5, f6, f7, f8, f9, z);
        } else {
            drawAdjusted(textureRegion, f, f2, f3, f4, f5, f6, f7, f8, f9, z);
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float[] fArr, int i, int i2) {
        if (i2 % 20 != 0) {
            throw new GdxRuntimeException("invalid vertex count");
        }
        if (!this.adjustNeeded) {
            super.draw(texture, fArr, i, i2);
        } else {
            drawAdjusted(texture, fArr, i, i2);
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteBatch, com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion, float f, float f2, Affine2 affine2) {
        if (!this.adjustNeeded) {
            super.draw(textureRegion, f, f2, affine2);
        } else {
            drawAdjusted(textureRegion, f, f2, affine2);
        }
    }

    private void drawAdjusted(TextureRegion textureRegion, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        drawAdjustedUV(textureRegion.texture, f, f2, f3, f4, f5, f6, f7, f8, f9, textureRegion.u, textureRegion.v2, textureRegion.u2, textureRegion.v, false, false);
    }

    private void drawAdjusted(Texture texture, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int i, int i2, int i3, int i4, boolean z, boolean z2) {
        float width = 1.0f / texture.getWidth();
        float height = 1.0f / texture.getHeight();
        drawAdjustedUV(texture, f, f2, f3, f4, f5, f6, f7, f8, f9, i * width, (i2 + i4) * height, (i + i3) * width, i2 * height, z, z2);
    }

    private void drawAdjustedUV(Texture texture, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, boolean z, boolean z2) {
        float f14;
        float f15;
        float f16;
        float f17;
        float f18;
        float f19;
        float f20;
        float f21;
        if (!this.drawing) {
            throw new IllegalStateException("CpuSpriteBatch.begin must be called before draw.");
        }
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.idx == this.vertices.length) {
            super.flush();
        }
        float f22 = f + f3;
        float f23 = f2 + f4;
        float f24 = -f3;
        float f25 = -f4;
        float f26 = f5 - f3;
        float f27 = f6 - f4;
        if (f7 != 1.0f || f8 != 1.0f) {
            f24 *= f7;
            f25 *= f8;
            f26 *= f7;
            f27 *= f8;
        }
        float f28 = f24;
        float f29 = f25;
        float f30 = f24;
        float f31 = f27;
        float f32 = f26;
        float f33 = f27;
        float f34 = f26;
        float f35 = f25;
        if (f9 != 0.0f) {
            float cosDeg = MathUtils.cosDeg(f9);
            float sinDeg = MathUtils.sinDeg(f9);
            f14 = (cosDeg * f28) - (sinDeg * f29);
            f15 = (sinDeg * f28) + (cosDeg * f29);
            f16 = (cosDeg * f30) - (sinDeg * f31);
            f17 = (sinDeg * f30) + (cosDeg * f31);
            f18 = (cosDeg * f32) - (sinDeg * f33);
            f19 = (sinDeg * f32) + (cosDeg * f33);
            f20 = f14 + (f18 - f16);
            f21 = f19 - (f17 - f15);
        } else {
            f14 = f28;
            f15 = f29;
            f16 = f30;
            f17 = f31;
            f18 = f32;
            f19 = f33;
            f20 = f34;
            f21 = f35;
        }
        float f36 = f14 + f22;
        float f37 = f15 + f23;
        float f38 = f16 + f22;
        float f39 = f17 + f23;
        float f40 = f18 + f22;
        float f41 = f19 + f23;
        float f42 = f20 + f22;
        float f43 = f21 + f23;
        if (z) {
            f10 = f12;
            f12 = f10;
        }
        if (z2) {
            f11 = f13;
            f13 = f11;
        }
        Affine2 affine2 = this.adjustAffine;
        this.vertices[this.idx] = (affine2.m00 * f36) + (affine2.m01 * f37) + affine2.m02;
        this.vertices[this.idx + 1] = (affine2.m10 * f36) + (affine2.m11 * f37) + affine2.m12;
        this.vertices[this.idx + 2] = this.colorPacked;
        this.vertices[this.idx + 3] = f10;
        this.vertices[this.idx + 4] = f11;
        this.vertices[this.idx + 5] = (affine2.m00 * f38) + (affine2.m01 * f39) + affine2.m02;
        this.vertices[this.idx + 6] = (affine2.m10 * f38) + (affine2.m11 * f39) + affine2.m12;
        this.vertices[this.idx + 7] = this.colorPacked;
        this.vertices[this.idx + 8] = f10;
        this.vertices[this.idx + 9] = f13;
        this.vertices[this.idx + 10] = (affine2.m00 * f40) + (affine2.m01 * f41) + affine2.m02;
        this.vertices[this.idx + 11] = (affine2.m10 * f40) + (affine2.m11 * f41) + affine2.m12;
        this.vertices[this.idx + 12] = this.colorPacked;
        this.vertices[this.idx + 13] = f12;
        this.vertices[this.idx + 14] = f13;
        this.vertices[this.idx + 15] = (affine2.m00 * f42) + (affine2.m01 * f43) + affine2.m02;
        this.vertices[this.idx + 16] = (affine2.m10 * f42) + (affine2.m11 * f43) + affine2.m12;
        this.vertices[this.idx + 17] = this.colorPacked;
        this.vertices[this.idx + 18] = f12;
        this.vertices[this.idx + 19] = f11;
        this.idx += 20;
    }

    private void drawAdjusted(TextureRegion textureRegion, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, boolean z) {
        float f10;
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
        float f18;
        float f19;
        float f20;
        float f21;
        float f22;
        float f23;
        float f24;
        float f25;
        if (!this.drawing) {
            throw new IllegalStateException("CpuSpriteBatch.begin must be called before draw.");
        }
        if (textureRegion.texture != this.lastTexture) {
            switchTexture(textureRegion.texture);
        } else if (this.idx == this.vertices.length) {
            super.flush();
        }
        float f26 = f + f3;
        float f27 = f2 + f4;
        float f28 = -f3;
        float f29 = -f4;
        float f30 = f5 - f3;
        float f31 = f6 - f4;
        if (f7 != 1.0f || f8 != 1.0f) {
            f28 *= f7;
            f29 *= f8;
            f30 *= f7;
            f31 *= f8;
        }
        float f32 = f28;
        float f33 = f29;
        float f34 = f28;
        float f35 = f31;
        float f36 = f30;
        float f37 = f31;
        float f38 = f30;
        float f39 = f29;
        if (f9 != 0.0f) {
            float cosDeg = MathUtils.cosDeg(f9);
            float sinDeg = MathUtils.sinDeg(f9);
            f10 = (cosDeg * f32) - (sinDeg * f33);
            f11 = (sinDeg * f32) + (cosDeg * f33);
            f12 = (cosDeg * f34) - (sinDeg * f35);
            f13 = (sinDeg * f34) + (cosDeg * f35);
            f14 = (cosDeg * f36) - (sinDeg * f37);
            f15 = (sinDeg * f36) + (cosDeg * f37);
            f16 = f10 + (f14 - f12);
            f17 = f15 - (f13 - f11);
        } else {
            f10 = f32;
            f11 = f33;
            f12 = f34;
            f13 = f35;
            f14 = f36;
            f15 = f37;
            f16 = f38;
            f17 = f39;
        }
        float f40 = f10 + f26;
        float f41 = f11 + f27;
        float f42 = f12 + f26;
        float f43 = f13 + f27;
        float f44 = f14 + f26;
        float f45 = f15 + f27;
        float f46 = f16 + f26;
        float f47 = f17 + f27;
        if (z) {
            f18 = textureRegion.u2;
            f19 = textureRegion.v2;
            f20 = textureRegion.u;
            f21 = textureRegion.v2;
            f22 = textureRegion.u;
            f23 = textureRegion.v;
            f24 = textureRegion.u2;
            f25 = textureRegion.v;
        } else {
            f18 = textureRegion.u;
            f19 = textureRegion.v;
            f20 = textureRegion.u2;
            f21 = textureRegion.v;
            f22 = textureRegion.u2;
            f23 = textureRegion.v2;
            f24 = textureRegion.u;
            f25 = textureRegion.v2;
        }
        Affine2 affine2 = this.adjustAffine;
        this.vertices[this.idx] = (affine2.m00 * f40) + (affine2.m01 * f41) + affine2.m02;
        this.vertices[this.idx + 1] = (affine2.m10 * f40) + (affine2.m11 * f41) + affine2.m12;
        this.vertices[this.idx + 2] = this.colorPacked;
        this.vertices[this.idx + 3] = f18;
        this.vertices[this.idx + 4] = f19;
        this.vertices[this.idx + 5] = (affine2.m00 * f42) + (affine2.m01 * f43) + affine2.m02;
        this.vertices[this.idx + 6] = (affine2.m10 * f42) + (affine2.m11 * f43) + affine2.m12;
        this.vertices[this.idx + 7] = this.colorPacked;
        this.vertices[this.idx + 8] = f20;
        this.vertices[this.idx + 9] = f21;
        this.vertices[this.idx + 10] = (affine2.m00 * f44) + (affine2.m01 * f45) + affine2.m02;
        this.vertices[this.idx + 11] = (affine2.m10 * f44) + (affine2.m11 * f45) + affine2.m12;
        this.vertices[this.idx + 12] = this.colorPacked;
        this.vertices[this.idx + 13] = f22;
        this.vertices[this.idx + 14] = f23;
        this.vertices[this.idx + 15] = (affine2.m00 * f46) + (affine2.m01 * f47) + affine2.m02;
        this.vertices[this.idx + 16] = (affine2.m10 * f46) + (affine2.m11 * f47) + affine2.m12;
        this.vertices[this.idx + 17] = this.colorPacked;
        this.vertices[this.idx + 18] = f24;
        this.vertices[this.idx + 19] = f25;
        this.idx += 20;
    }

    private void drawAdjusted(TextureRegion textureRegion, float f, float f2, Affine2 affine2) {
        if (!this.drawing) {
            throw new IllegalStateException("CpuSpriteBatch.begin must be called before draw.");
        }
        if (textureRegion.texture != this.lastTexture) {
            switchTexture(textureRegion.texture);
        } else if (this.idx == this.vertices.length) {
            super.flush();
        }
        float f3 = affine2.m02;
        float f4 = affine2.m12;
        float f5 = (affine2.m01 * f2) + affine2.m02;
        float f6 = (affine2.m11 * f2) + affine2.m12;
        float f7 = (affine2.m00 * f) + (affine2.m01 * f2) + affine2.m02;
        float f8 = (affine2.m10 * f) + (affine2.m11 * f2) + affine2.m12;
        float f9 = (affine2.m00 * f) + affine2.m02;
        float f10 = (affine2.m10 * f) + affine2.m12;
        float f11 = textureRegion.u;
        float f12 = textureRegion.v2;
        float f13 = textureRegion.u2;
        float f14 = textureRegion.v;
        Affine2 affine22 = this.adjustAffine;
        this.vertices[this.idx] = (affine22.m00 * f3) + (affine22.m01 * f4) + affine22.m02;
        this.vertices[this.idx + 1] = (affine22.m10 * f3) + (affine22.m11 * f4) + affine22.m12;
        this.vertices[this.idx + 2] = this.colorPacked;
        this.vertices[this.idx + 3] = f11;
        this.vertices[this.idx + 4] = f12;
        this.vertices[this.idx + 5] = (affine22.m00 * f5) + (affine22.m01 * f6) + affine22.m02;
        this.vertices[this.idx + 6] = (affine22.m10 * f5) + (affine22.m11 * f6) + affine22.m12;
        this.vertices[this.idx + 7] = this.colorPacked;
        this.vertices[this.idx + 8] = f11;
        this.vertices[this.idx + 9] = f14;
        this.vertices[this.idx + 10] = (affine22.m00 * f7) + (affine22.m01 * f8) + affine22.m02;
        this.vertices[this.idx + 11] = (affine22.m10 * f7) + (affine22.m11 * f8) + affine22.m12;
        this.vertices[this.idx + 12] = this.colorPacked;
        this.vertices[this.idx + 13] = f13;
        this.vertices[this.idx + 14] = f14;
        this.vertices[this.idx + 15] = (affine22.m00 * f9) + (affine22.m01 * f10) + affine22.m02;
        this.vertices[this.idx + 16] = (affine22.m10 * f9) + (affine22.m11 * f10) + affine22.m12;
        this.vertices[this.idx + 17] = this.colorPacked;
        this.vertices[this.idx + 18] = f13;
        this.vertices[this.idx + 19] = f12;
        this.idx += 20;
    }

    private void drawAdjusted(Texture texture, float[] fArr, int i, int i2) {
        if (!this.drawing) {
            throw new IllegalStateException("CpuSpriteBatch.begin must be called before draw.");
        }
        if (texture != this.lastTexture) {
            switchTexture(texture);
        }
        Affine2 affine2 = this.adjustAffine;
        int min = Math.min(this.vertices.length - this.idx, i2);
        do {
            i2 -= min;
            while (min > 0) {
                float f = fArr[i];
                float f2 = fArr[i + 1];
                this.vertices[this.idx] = (affine2.m00 * f) + (affine2.m01 * f2) + affine2.m02;
                this.vertices[this.idx + 1] = (affine2.m10 * f) + (affine2.m11 * f2) + affine2.m12;
                this.vertices[this.idx + 2] = fArr[i + 2];
                this.vertices[this.idx + 3] = fArr[i + 3];
                this.vertices[this.idx + 4] = fArr[i + 4];
                this.idx += 5;
                i += 5;
                min -= 5;
            }
            if (i2 > 0) {
                super.flush();
                min = Math.min(this.vertices.length, i2);
            }
        } while (i2 > 0);
    }

    private static boolean checkEqual(Matrix4 matrix4, Matrix4 matrix42) {
        if (matrix4 == matrix42) {
            return true;
        }
        return matrix4.val[0] == matrix42.val[0] && matrix4.val[1] == matrix42.val[1] && matrix4.val[4] == matrix42.val[4] && matrix4.val[5] == matrix42.val[5] && matrix4.val[12] == matrix42.val[12] && matrix4.val[13] == matrix42.val[13];
    }

    private static boolean checkEqual(Matrix4 matrix4, Affine2 affine2) {
        float[] values = matrix4.getValues();
        return values[0] == affine2.m00 && values[1] == affine2.m10 && values[4] == affine2.m01 && values[5] == affine2.m11 && values[12] == affine2.m02 && values[13] == affine2.m12;
    }

    private static boolean checkIdt(Matrix4 matrix4) {
        float[] values = matrix4.getValues();
        return values[0] == 1.0f && values[1] == 0.0f && values[4] == 0.0f && values[5] == 1.0f && values[12] == 0.0f && values[13] == 0.0f;
    }
}
