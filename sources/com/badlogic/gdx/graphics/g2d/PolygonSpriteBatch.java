package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/PolygonSpriteBatch.class */
public class PolygonSpriteBatch implements PolygonBatch {
    private Mesh mesh;
    private final float[] vertices;
    private final short[] triangles;
    private int vertexIndex;
    private int triangleIndex;
    private Texture lastTexture;
    private float invTexWidth;
    private float invTexHeight;
    private boolean drawing;
    private final Matrix4 transformMatrix;
    private final Matrix4 projectionMatrix;
    private final Matrix4 combinedMatrix;
    private boolean blendingDisabled;
    private int blendSrcFunc;
    private int blendDstFunc;
    private int blendSrcFuncAlpha;
    private int blendDstFuncAlpha;
    private final ShaderProgram shader;
    private ShaderProgram customShader;
    private boolean ownsShader;
    private final Color color;
    float colorPacked;
    public int renderCalls;
    public int totalRenderCalls;
    public int maxTrianglesInBatch;

    public PolygonSpriteBatch() {
        this(2000, null);
    }

    public PolygonSpriteBatch(int i) {
        this(i, i << 1, null);
    }

    public PolygonSpriteBatch(int i, ShaderProgram shaderProgram) {
        this(i, i << 1, shaderProgram);
    }

    public PolygonSpriteBatch(int i, int i2, ShaderProgram shaderProgram) {
        this.invTexWidth = 0.0f;
        this.invTexHeight = 0.0f;
        this.transformMatrix = new Matrix4();
        this.projectionMatrix = new Matrix4();
        this.combinedMatrix = new Matrix4();
        this.blendSrcFunc = 770;
        this.blendDstFunc = 771;
        this.blendSrcFuncAlpha = 770;
        this.blendDstFuncAlpha = 771;
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.colorPacked = Color.WHITE_FLOAT_BITS;
        this.renderCalls = 0;
        this.totalRenderCalls = 0;
        this.maxTrianglesInBatch = 0;
        if (i > 32767) {
            throw new IllegalArgumentException("Can't have more than 32767 vertices per batch: " + i);
        }
        this.mesh = new Mesh(Gdx.gl30 != null ? Mesh.VertexDataType.VertexBufferObjectWithVAO : Mesh.VertexDataType.VertexArray, false, i, i2 * 3, new VertexAttribute(1, 2, ShaderProgram.POSITION_ATTRIBUTE), new VertexAttribute(4, 4, ShaderProgram.COLOR_ATTRIBUTE), new VertexAttribute(16, 2, "a_texCoord0"));
        this.vertices = new float[i * 5];
        this.triangles = new short[i2 * 3];
        if (shaderProgram == null) {
            this.shader = SpriteBatch.createDefaultShader();
            this.ownsShader = true;
        } else {
            this.shader = shaderProgram;
        }
        this.projectionMatrix.setToOrtho2D(0.0f, 0.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void begin() {
        if (this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.end must be called before begin.");
        }
        this.renderCalls = 0;
        Gdx.gl.glDepthMask(false);
        if (this.customShader != null) {
            this.customShader.bind();
        } else {
            this.shader.bind();
        }
        setupMatrices();
        this.drawing = true;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void end() {
        if (!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before end.");
        }
        if (this.vertexIndex > 0) {
            flush();
        }
        this.lastTexture = null;
        this.drawing = false;
        GL20 gl20 = Gdx.gl;
        gl20.glDepthMask(true);
        if (isBlendingEnabled()) {
            gl20.glDisable(3042);
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void setColor(Color color) {
        this.color.set(color);
        this.colorPacked = color.toFloatBits();
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void setColor(float f, float f2, float f3, float f4) {
        this.color.set(f, f2, f3, f4);
        this.colorPacked = this.color.toFloatBits();
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void setPackedColor(float f) {
        Color.abgr8888ToColor(this.color, f);
        this.colorPacked = f;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public Color getColor() {
        return this.color;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public float getPackedColor() {
        return this.colorPacked;
    }

    @Override // com.badlogic.gdx.graphics.g2d.PolygonBatch
    public void draw(PolygonRegion polygonRegion, float f, float f2) {
        if (!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] sArr = this.triangles;
        short[] sArr2 = polygonRegion.triangles;
        int length = sArr2.length;
        float[] fArr = polygonRegion.vertices;
        int length2 = fArr.length;
        Texture texture = polygonRegion.region.texture;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.triangleIndex + length > sArr.length || this.vertexIndex + ((length2 * 5) / 2) > this.vertices.length) {
            flush();
        }
        int i = this.triangleIndex;
        int i2 = this.vertexIndex;
        int i3 = i2;
        int i4 = i2 / 5;
        for (short s : sArr2) {
            int i5 = i;
            i++;
            sArr[i5] = (short) (s + i4);
        }
        this.triangleIndex = i;
        float[] fArr2 = this.vertices;
        float f3 = this.colorPacked;
        float[] fArr3 = polygonRegion.textureCoords;
        for (int i6 = 0; i6 < length2; i6 += 2) {
            int i7 = i3;
            int i8 = i3 + 1;
            fArr2[i7] = fArr[i6] + f;
            int i9 = i8 + 1;
            fArr2[i8] = fArr[i6 + 1] + f2;
            int i10 = i9 + 1;
            fArr2[i9] = f3;
            int i11 = i10 + 1;
            fArr2[i10] = fArr3[i6];
            i3 = i11 + 1;
            fArr2[i11] = fArr3[i6 + 1];
        }
        this.vertexIndex = i3;
    }

    @Override // com.badlogic.gdx.graphics.g2d.PolygonBatch
    public void draw(PolygonRegion polygonRegion, float f, float f2, float f3, float f4) {
        if (!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] sArr = this.triangles;
        short[] sArr2 = polygonRegion.triangles;
        int length = sArr2.length;
        float[] fArr = polygonRegion.vertices;
        int length2 = fArr.length;
        Texture texture = polygonRegion.region.texture;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.triangleIndex + length > sArr.length || this.vertexIndex + ((length2 * 5) / 2) > this.vertices.length) {
            flush();
        }
        int i = this.triangleIndex;
        int i2 = this.vertexIndex;
        int i3 = i2;
        int i4 = i2 / 5;
        for (short s : sArr2) {
            int i5 = i;
            i++;
            sArr[i5] = (short) (s + i4);
        }
        this.triangleIndex = i;
        float[] fArr2 = this.vertices;
        float f5 = this.colorPacked;
        float[] fArr3 = polygonRegion.textureCoords;
        float f6 = f3 / r0.regionWidth;
        float f7 = f4 / r0.regionHeight;
        for (int i6 = 0; i6 < length2; i6 += 2) {
            int i7 = i3;
            int i8 = i3 + 1;
            fArr2[i7] = (fArr[i6] * f6) + f;
            int i9 = i8 + 1;
            fArr2[i8] = (fArr[i6 + 1] * f7) + f2;
            int i10 = i9 + 1;
            fArr2[i9] = f5;
            int i11 = i10 + 1;
            fArr2[i10] = fArr3[i6];
            i3 = i11 + 1;
            fArr2[i11] = fArr3[i6 + 1];
        }
        this.vertexIndex = i3;
    }

    @Override // com.badlogic.gdx.graphics.g2d.PolygonBatch
    public void draw(PolygonRegion polygonRegion, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        if (!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] sArr = this.triangles;
        short[] sArr2 = polygonRegion.triangles;
        int length = sArr2.length;
        float[] fArr = polygonRegion.vertices;
        int length2 = fArr.length;
        Texture texture = polygonRegion.region.texture;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.triangleIndex + length > sArr.length || this.vertexIndex + ((length2 * 5) / 2) > this.vertices.length) {
            flush();
        }
        int i = this.triangleIndex;
        int i2 = this.vertexIndex;
        int i3 = i2;
        int i4 = i2 / 5;
        for (short s : sArr2) {
            int i5 = i;
            i++;
            sArr[i5] = (short) (s + i4);
        }
        this.triangleIndex = i;
        float[] fArr2 = this.vertices;
        float f10 = this.colorPacked;
        float[] fArr3 = polygonRegion.textureCoords;
        float f11 = f + f3;
        float f12 = f2 + f4;
        float f13 = f5 / r0.regionWidth;
        float f14 = f6 / r0.regionHeight;
        float cosDeg = MathUtils.cosDeg(f9);
        float sinDeg = MathUtils.sinDeg(f9);
        for (int i6 = 0; i6 < length2; i6 += 2) {
            float f15 = ((fArr[i6] * f13) - f3) * f7;
            float f16 = ((fArr[i6 + 1] * f14) - f4) * f8;
            int i7 = i3;
            int i8 = i3 + 1;
            fArr2[i7] = ((cosDeg * f15) - (sinDeg * f16)) + f11;
            int i9 = i8 + 1;
            fArr2[i8] = (sinDeg * f15) + (cosDeg * f16) + f12;
            int i10 = i9 + 1;
            fArr2[i9] = f10;
            int i11 = i10 + 1;
            fArr2[i10] = fArr3[i6];
            i3 = i11 + 1;
            fArr2[i11] = fArr3[i6 + 1];
        }
        this.vertexIndex = i3;
    }

    @Override // com.badlogic.gdx.graphics.g2d.PolygonBatch
    public void draw(Texture texture, float[] fArr, int i, int i2, short[] sArr, int i3, int i4) {
        if (!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] sArr2 = this.triangles;
        float[] fArr2 = this.vertices;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.triangleIndex + i4 > sArr2.length || this.vertexIndex + i2 > fArr2.length) {
            flush();
        }
        int i5 = this.triangleIndex;
        int i6 = this.vertexIndex;
        int i7 = i6 / 5;
        int i8 = i3 + i4;
        for (int i9 = i3; i9 < i8; i9++) {
            int i10 = i5;
            i5++;
            sArr2[i10] = (short) (sArr[i9] + i7);
        }
        this.triangleIndex = i5;
        System.arraycopy(fArr, i, fArr2, i6, i2);
        this.vertexIndex += i2;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int i, int i2, int i3, int i4, boolean z, boolean z2) {
        float f10;
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
        if (!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] sArr = this.triangles;
        float[] fArr = this.vertices;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.triangleIndex + 6 > sArr.length || this.vertexIndex + 20 > fArr.length) {
            flush();
        }
        int i5 = this.triangleIndex;
        int i6 = this.vertexIndex / 5;
        int i7 = i5 + 1;
        sArr[i5] = (short) i6;
        int i8 = i7 + 1;
        sArr[i7] = (short) (i6 + 1);
        int i9 = i8 + 1;
        sArr[i8] = (short) (i6 + 2);
        int i10 = i9 + 1;
        sArr[i9] = (short) (i6 + 2);
        int i11 = i10 + 1;
        sArr[i10] = (short) (i6 + 3);
        sArr[i11] = (short) i6;
        this.triangleIndex = i11 + 1;
        float f18 = f + f3;
        float f19 = f2 + f4;
        float f20 = -f3;
        float f21 = -f4;
        float f22 = f5 - f3;
        float f23 = f6 - f4;
        if (f7 != 1.0f || f8 != 1.0f) {
            f20 *= f7;
            f21 *= f8;
            f22 *= f7;
            f23 *= f8;
        }
        float f24 = f20;
        float f25 = f21;
        float f26 = f20;
        float f27 = f23;
        float f28 = f22;
        float f29 = f23;
        float f30 = f22;
        float f31 = f21;
        if (f9 != 0.0f) {
            float cosDeg = MathUtils.cosDeg(f9);
            float sinDeg = MathUtils.sinDeg(f9);
            f10 = (cosDeg * f24) - (sinDeg * f25);
            f11 = (sinDeg * f24) + (cosDeg * f25);
            f12 = (cosDeg * f26) - (sinDeg * f27);
            f13 = (sinDeg * f26) + (cosDeg * f27);
            f14 = (cosDeg * f28) - (sinDeg * f29);
            f15 = (sinDeg * f28) + (cosDeg * f29);
            f16 = f10 + (f14 - f12);
            f17 = f15 - (f13 - f11);
        } else {
            f10 = f24;
            f11 = f25;
            f12 = f26;
            f13 = f27;
            f14 = f28;
            f15 = f29;
            f16 = f30;
            f17 = f31;
        }
        float f32 = f10 + f18;
        float f33 = f11 + f19;
        float f34 = f12 + f18;
        float f35 = f13 + f19;
        float f36 = f14 + f18;
        float f37 = f15 + f19;
        float f38 = f16 + f18;
        float f39 = f17 + f19;
        float f40 = i * this.invTexWidth;
        float f41 = (i2 + i4) * this.invTexHeight;
        float f42 = (i + i3) * this.invTexWidth;
        float f43 = i2 * this.invTexHeight;
        if (z) {
            f40 = f42;
            f42 = f40;
        }
        if (z2) {
            f41 = f43;
            f43 = f41;
        }
        float f44 = this.colorPacked;
        int i12 = this.vertexIndex;
        int i13 = i12 + 1;
        fArr[i12] = f32;
        int i14 = i13 + 1;
        fArr[i13] = f33;
        int i15 = i14 + 1;
        fArr[i14] = f44;
        int i16 = i15 + 1;
        fArr[i15] = f40;
        int i17 = i16 + 1;
        fArr[i16] = f41;
        int i18 = i17 + 1;
        fArr[i17] = f34;
        int i19 = i18 + 1;
        fArr[i18] = f35;
        int i20 = i19 + 1;
        fArr[i19] = f44;
        int i21 = i20 + 1;
        fArr[i20] = f40;
        int i22 = i21 + 1;
        fArr[i21] = f43;
        int i23 = i22 + 1;
        fArr[i22] = f36;
        int i24 = i23 + 1;
        fArr[i23] = f37;
        int i25 = i24 + 1;
        fArr[i24] = f44;
        int i26 = i25 + 1;
        fArr[i25] = f42;
        int i27 = i26 + 1;
        fArr[i26] = f43;
        int i28 = i27 + 1;
        fArr[i27] = f38;
        int i29 = i28 + 1;
        fArr[i28] = f39;
        int i30 = i29 + 1;
        fArr[i29] = f44;
        int i31 = i30 + 1;
        fArr[i30] = f42;
        fArr[i31] = f41;
        this.vertexIndex = i31 + 1;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f, float f2, float f3, float f4, int i, int i2, int i3, int i4, boolean z, boolean z2) {
        if (!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] sArr = this.triangles;
        float[] fArr = this.vertices;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.triangleIndex + 6 > sArr.length || this.vertexIndex + 20 > fArr.length) {
            flush();
        }
        int i5 = this.triangleIndex;
        int i6 = this.vertexIndex / 5;
        int i7 = i5 + 1;
        sArr[i5] = (short) i6;
        int i8 = i7 + 1;
        sArr[i7] = (short) (i6 + 1);
        int i9 = i8 + 1;
        sArr[i8] = (short) (i6 + 2);
        int i10 = i9 + 1;
        sArr[i9] = (short) (i6 + 2);
        int i11 = i10 + 1;
        sArr[i10] = (short) (i6 + 3);
        sArr[i11] = (short) i6;
        this.triangleIndex = i11 + 1;
        float f5 = i * this.invTexWidth;
        float f6 = (i2 + i4) * this.invTexHeight;
        float f7 = (i + i3) * this.invTexWidth;
        float f8 = i2 * this.invTexHeight;
        float f9 = f + f3;
        float f10 = f2 + f4;
        if (z) {
            f5 = f7;
            f7 = f5;
        }
        if (z2) {
            f6 = f8;
            f8 = f6;
        }
        float f11 = this.colorPacked;
        int i12 = this.vertexIndex;
        int i13 = i12 + 1;
        fArr[i12] = f;
        int i14 = i13 + 1;
        fArr[i13] = f2;
        int i15 = i14 + 1;
        fArr[i14] = f11;
        int i16 = i15 + 1;
        fArr[i15] = f5;
        int i17 = i16 + 1;
        fArr[i16] = f6;
        int i18 = i17 + 1;
        fArr[i17] = f;
        int i19 = i18 + 1;
        fArr[i18] = f10;
        int i20 = i19 + 1;
        fArr[i19] = f11;
        int i21 = i20 + 1;
        fArr[i20] = f5;
        int i22 = i21 + 1;
        fArr[i21] = f8;
        int i23 = i22 + 1;
        fArr[i22] = f9;
        int i24 = i23 + 1;
        fArr[i23] = f10;
        int i25 = i24 + 1;
        fArr[i24] = f11;
        int i26 = i25 + 1;
        fArr[i25] = f7;
        int i27 = i26 + 1;
        fArr[i26] = f8;
        int i28 = i27 + 1;
        fArr[i27] = f9;
        int i29 = i28 + 1;
        fArr[i28] = f2;
        int i30 = i29 + 1;
        fArr[i29] = f11;
        int i31 = i30 + 1;
        fArr[i30] = f7;
        fArr[i31] = f6;
        this.vertexIndex = i31 + 1;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f, float f2, int i, int i2, int i3, int i4) {
        if (!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] sArr = this.triangles;
        float[] fArr = this.vertices;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.triangleIndex + 6 > sArr.length || this.vertexIndex + 20 > fArr.length) {
            flush();
        }
        int i5 = this.triangleIndex;
        int i6 = this.vertexIndex / 5;
        int i7 = i5 + 1;
        sArr[i5] = (short) i6;
        int i8 = i7 + 1;
        sArr[i7] = (short) (i6 + 1);
        int i9 = i8 + 1;
        sArr[i8] = (short) (i6 + 2);
        int i10 = i9 + 1;
        sArr[i9] = (short) (i6 + 2);
        int i11 = i10 + 1;
        sArr[i10] = (short) (i6 + 3);
        sArr[i11] = (short) i6;
        this.triangleIndex = i11 + 1;
        float f3 = i * this.invTexWidth;
        float f4 = (i2 + i4) * this.invTexHeight;
        float f5 = (i + i3) * this.invTexWidth;
        float f6 = i2 * this.invTexHeight;
        float f7 = f + i3;
        float f8 = f2 + i4;
        float f9 = this.colorPacked;
        int i12 = this.vertexIndex;
        int i13 = i12 + 1;
        fArr[i12] = f;
        int i14 = i13 + 1;
        fArr[i13] = f2;
        int i15 = i14 + 1;
        fArr[i14] = f9;
        int i16 = i15 + 1;
        fArr[i15] = f3;
        int i17 = i16 + 1;
        fArr[i16] = f4;
        int i18 = i17 + 1;
        fArr[i17] = f;
        int i19 = i18 + 1;
        fArr[i18] = f8;
        int i20 = i19 + 1;
        fArr[i19] = f9;
        int i21 = i20 + 1;
        fArr[i20] = f3;
        int i22 = i21 + 1;
        fArr[i21] = f6;
        int i23 = i22 + 1;
        fArr[i22] = f7;
        int i24 = i23 + 1;
        fArr[i23] = f8;
        int i25 = i24 + 1;
        fArr[i24] = f9;
        int i26 = i25 + 1;
        fArr[i25] = f5;
        int i27 = i26 + 1;
        fArr[i26] = f6;
        int i28 = i27 + 1;
        fArr[i27] = f7;
        int i29 = i28 + 1;
        fArr[i28] = f2;
        int i30 = i29 + 1;
        fArr[i29] = f9;
        int i31 = i30 + 1;
        fArr[i30] = f5;
        fArr[i31] = f4;
        this.vertexIndex = i31 + 1;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] sArr = this.triangles;
        float[] fArr = this.vertices;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.triangleIndex + 6 > sArr.length || this.vertexIndex + 20 > fArr.length) {
            flush();
        }
        int i = this.triangleIndex;
        int i2 = this.vertexIndex / 5;
        int i3 = i + 1;
        sArr[i] = (short) i2;
        int i4 = i3 + 1;
        sArr[i3] = (short) (i2 + 1);
        int i5 = i4 + 1;
        sArr[i4] = (short) (i2 + 2);
        int i6 = i5 + 1;
        sArr[i5] = (short) (i2 + 2);
        int i7 = i6 + 1;
        sArr[i6] = (short) (i2 + 3);
        sArr[i7] = (short) i2;
        this.triangleIndex = i7 + 1;
        float f9 = f + f3;
        float f10 = f2 + f4;
        float f11 = this.colorPacked;
        int i8 = this.vertexIndex;
        int i9 = i8 + 1;
        fArr[i8] = f;
        int i10 = i9 + 1;
        fArr[i9] = f2;
        int i11 = i10 + 1;
        fArr[i10] = f11;
        int i12 = i11 + 1;
        fArr[i11] = f5;
        int i13 = i12 + 1;
        fArr[i12] = f6;
        int i14 = i13 + 1;
        fArr[i13] = f;
        int i15 = i14 + 1;
        fArr[i14] = f10;
        int i16 = i15 + 1;
        fArr[i15] = f11;
        int i17 = i16 + 1;
        fArr[i16] = f5;
        int i18 = i17 + 1;
        fArr[i17] = f8;
        int i19 = i18 + 1;
        fArr[i18] = f9;
        int i20 = i19 + 1;
        fArr[i19] = f10;
        int i21 = i20 + 1;
        fArr[i20] = f11;
        int i22 = i21 + 1;
        fArr[i21] = f7;
        int i23 = i22 + 1;
        fArr[i22] = f8;
        int i24 = i23 + 1;
        fArr[i23] = f9;
        int i25 = i24 + 1;
        fArr[i24] = f2;
        int i26 = i25 + 1;
        fArr[i25] = f11;
        int i27 = i26 + 1;
        fArr[i26] = f7;
        fArr[i27] = f6;
        this.vertexIndex = i27 + 1;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f, float f2) {
        draw(texture, f, f2, texture.getWidth(), texture.getHeight());
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f, float f2, float f3, float f4) {
        if (!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] sArr = this.triangles;
        float[] fArr = this.vertices;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.triangleIndex + 6 > sArr.length || this.vertexIndex + 20 > fArr.length) {
            flush();
        }
        int i = this.triangleIndex;
        int i2 = this.vertexIndex / 5;
        int i3 = i + 1;
        sArr[i] = (short) i2;
        int i4 = i3 + 1;
        sArr[i3] = (short) (i2 + 1);
        int i5 = i4 + 1;
        sArr[i4] = (short) (i2 + 2);
        int i6 = i5 + 1;
        sArr[i5] = (short) (i2 + 2);
        int i7 = i6 + 1;
        sArr[i6] = (short) (i2 + 3);
        sArr[i7] = (short) i2;
        this.triangleIndex = i7 + 1;
        float f5 = f + f3;
        float f6 = f2 + f4;
        float f7 = this.colorPacked;
        int i8 = this.vertexIndex;
        int i9 = i8 + 1;
        fArr[i8] = f;
        int i10 = i9 + 1;
        fArr[i9] = f2;
        int i11 = i10 + 1;
        fArr[i10] = f7;
        int i12 = i11 + 1;
        fArr[i11] = 0.0f;
        int i13 = i12 + 1;
        fArr[i12] = 1.0f;
        int i14 = i13 + 1;
        fArr[i13] = f;
        int i15 = i14 + 1;
        fArr[i14] = f6;
        int i16 = i15 + 1;
        fArr[i15] = f7;
        int i17 = i16 + 1;
        fArr[i16] = 0.0f;
        int i18 = i17 + 1;
        fArr[i17] = 0.0f;
        int i19 = i18 + 1;
        fArr[i18] = f5;
        int i20 = i19 + 1;
        fArr[i19] = f6;
        int i21 = i20 + 1;
        fArr[i20] = f7;
        int i22 = i21 + 1;
        fArr[i21] = 1.0f;
        int i23 = i22 + 1;
        fArr[i22] = 0.0f;
        int i24 = i23 + 1;
        fArr[i23] = f5;
        int i25 = i24 + 1;
        fArr[i24] = f2;
        int i26 = i25 + 1;
        fArr[i25] = f7;
        int i27 = i26 + 1;
        fArr[i26] = 1.0f;
        fArr[i27] = 1.0f;
        this.vertexIndex = i27 + 1;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float[] fArr, int i, int i2) {
        int i3;
        if (!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] sArr = this.triangles;
        float[] fArr2 = this.vertices;
        int i4 = (i2 / 20) * 6;
        if (texture != this.lastTexture) {
            switchTexture(texture);
            int min = Math.min(Math.min(i2, fArr2.length - (fArr2.length % 20)), (sArr.length / 6) * 20);
            i3 = min;
            i4 = (min / 20) * 6;
        } else if (this.triangleIndex + i4 > sArr.length || this.vertexIndex + i2 > fArr2.length) {
            flush();
            int min2 = Math.min(Math.min(i2, fArr2.length - (fArr2.length % 20)), (sArr.length / 6) * 20);
            i3 = min2;
            i4 = (min2 / 20) * 6;
        } else {
            i3 = i2;
        }
        int i5 = this.vertexIndex;
        int i6 = i5;
        short s = (short) (i5 / 5);
        int i7 = this.triangleIndex;
        int i8 = i7;
        int i9 = i7 + i4;
        while (i8 < i9) {
            sArr[i8] = s;
            sArr[i8 + 1] = (short) (s + 1);
            sArr[i8 + 2] = (short) (s + 2);
            sArr[i8 + 3] = (short) (s + 2);
            sArr[i8 + 4] = (short) (s + 3);
            sArr[i8 + 5] = s;
            i8 += 6;
            s = (short) (s + 4);
        }
        while (true) {
            System.arraycopy(fArr, i, fArr2, i6, i3);
            this.vertexIndex = i6 + i3;
            this.triangleIndex = i8;
            int i10 = i2 - i3;
            i2 = i10;
            if (i10 != 0) {
                i += i3;
                flush();
                i6 = 0;
                if (i3 > i2) {
                    int min3 = Math.min(i2, (sArr.length / 6) * 20);
                    i3 = min3;
                    i8 = (min3 / 20) * 6;
                }
            } else {
                return;
            }
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion, float f, float f2) {
        draw(textureRegion, f, f2, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion, float f, float f2, float f3, float f4) {
        if (!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] sArr = this.triangles;
        float[] fArr = this.vertices;
        Texture texture = textureRegion.texture;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.triangleIndex + 6 > sArr.length || this.vertexIndex + 20 > fArr.length) {
            flush();
        }
        int i = this.triangleIndex;
        int i2 = this.vertexIndex / 5;
        int i3 = i + 1;
        sArr[i] = (short) i2;
        int i4 = i3 + 1;
        sArr[i3] = (short) (i2 + 1);
        int i5 = i4 + 1;
        sArr[i4] = (short) (i2 + 2);
        int i6 = i5 + 1;
        sArr[i5] = (short) (i2 + 2);
        int i7 = i6 + 1;
        sArr[i6] = (short) (i2 + 3);
        sArr[i7] = (short) i2;
        this.triangleIndex = i7 + 1;
        float f5 = f + f3;
        float f6 = f2 + f4;
        float f7 = textureRegion.u;
        float f8 = textureRegion.v2;
        float f9 = textureRegion.u2;
        float f10 = textureRegion.v;
        float f11 = this.colorPacked;
        int i8 = this.vertexIndex;
        int i9 = i8 + 1;
        fArr[i8] = f;
        int i10 = i9 + 1;
        fArr[i9] = f2;
        int i11 = i10 + 1;
        fArr[i10] = f11;
        int i12 = i11 + 1;
        fArr[i11] = f7;
        int i13 = i12 + 1;
        fArr[i12] = f8;
        int i14 = i13 + 1;
        fArr[i13] = f;
        int i15 = i14 + 1;
        fArr[i14] = f6;
        int i16 = i15 + 1;
        fArr[i15] = f11;
        int i17 = i16 + 1;
        fArr[i16] = f7;
        int i18 = i17 + 1;
        fArr[i17] = f10;
        int i19 = i18 + 1;
        fArr[i18] = f5;
        int i20 = i19 + 1;
        fArr[i19] = f6;
        int i21 = i20 + 1;
        fArr[i20] = f11;
        int i22 = i21 + 1;
        fArr[i21] = f9;
        int i23 = i22 + 1;
        fArr[i22] = f10;
        int i24 = i23 + 1;
        fArr[i23] = f5;
        int i25 = i24 + 1;
        fArr[i24] = f2;
        int i26 = i25 + 1;
        fArr[i25] = f11;
        int i27 = i26 + 1;
        fArr[i26] = f9;
        fArr[i27] = f8;
        this.vertexIndex = i27 + 1;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        float f10;
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
        if (!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] sArr = this.triangles;
        float[] fArr = this.vertices;
        Texture texture = textureRegion.texture;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.triangleIndex + 6 > sArr.length || this.vertexIndex + 20 > fArr.length) {
            flush();
        }
        int i = this.triangleIndex;
        int i2 = this.vertexIndex / 5;
        int i3 = i + 1;
        sArr[i] = (short) i2;
        int i4 = i3 + 1;
        sArr[i3] = (short) (i2 + 1);
        int i5 = i4 + 1;
        sArr[i4] = (short) (i2 + 2);
        int i6 = i5 + 1;
        sArr[i5] = (short) (i2 + 2);
        int i7 = i6 + 1;
        sArr[i6] = (short) (i2 + 3);
        sArr[i7] = (short) i2;
        this.triangleIndex = i7 + 1;
        float f18 = f + f3;
        float f19 = f2 + f4;
        float f20 = -f3;
        float f21 = -f4;
        float f22 = f5 - f3;
        float f23 = f6 - f4;
        if (f7 != 1.0f || f8 != 1.0f) {
            f20 *= f7;
            f21 *= f8;
            f22 *= f7;
            f23 *= f8;
        }
        float f24 = f20;
        float f25 = f21;
        float f26 = f20;
        float f27 = f23;
        float f28 = f22;
        float f29 = f23;
        float f30 = f22;
        float f31 = f21;
        if (f9 != 0.0f) {
            float cosDeg = MathUtils.cosDeg(f9);
            float sinDeg = MathUtils.sinDeg(f9);
            f10 = (cosDeg * f24) - (sinDeg * f25);
            f11 = (sinDeg * f24) + (cosDeg * f25);
            f12 = (cosDeg * f26) - (sinDeg * f27);
            f13 = (sinDeg * f26) + (cosDeg * f27);
            f14 = (cosDeg * f28) - (sinDeg * f29);
            f15 = (sinDeg * f28) + (cosDeg * f29);
            f16 = f10 + (f14 - f12);
            f17 = f15 - (f13 - f11);
        } else {
            f10 = f24;
            f11 = f25;
            f12 = f26;
            f13 = f27;
            f14 = f28;
            f15 = f29;
            f16 = f30;
            f17 = f31;
        }
        float f32 = f10 + f18;
        float f33 = f11 + f19;
        float f34 = f12 + f18;
        float f35 = f13 + f19;
        float f36 = f14 + f18;
        float f37 = f15 + f19;
        float f38 = f16 + f18;
        float f39 = f17 + f19;
        float f40 = textureRegion.u;
        float f41 = textureRegion.v2;
        float f42 = textureRegion.u2;
        float f43 = textureRegion.v;
        float f44 = this.colorPacked;
        int i8 = this.vertexIndex;
        int i9 = i8 + 1;
        fArr[i8] = f32;
        int i10 = i9 + 1;
        fArr[i9] = f33;
        int i11 = i10 + 1;
        fArr[i10] = f44;
        int i12 = i11 + 1;
        fArr[i11] = f40;
        int i13 = i12 + 1;
        fArr[i12] = f41;
        int i14 = i13 + 1;
        fArr[i13] = f34;
        int i15 = i14 + 1;
        fArr[i14] = f35;
        int i16 = i15 + 1;
        fArr[i15] = f44;
        int i17 = i16 + 1;
        fArr[i16] = f40;
        int i18 = i17 + 1;
        fArr[i17] = f43;
        int i19 = i18 + 1;
        fArr[i18] = f36;
        int i20 = i19 + 1;
        fArr[i19] = f37;
        int i21 = i20 + 1;
        fArr[i20] = f44;
        int i22 = i21 + 1;
        fArr[i21] = f42;
        int i23 = i22 + 1;
        fArr[i22] = f43;
        int i24 = i23 + 1;
        fArr[i23] = f38;
        int i25 = i24 + 1;
        fArr[i24] = f39;
        int i26 = i25 + 1;
        fArr[i25] = f44;
        int i27 = i26 + 1;
        fArr[i26] = f42;
        fArr[i27] = f41;
        this.vertexIndex = i27 + 1;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, boolean z) {
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
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] sArr = this.triangles;
        float[] fArr = this.vertices;
        Texture texture = textureRegion.texture;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.triangleIndex + 6 > sArr.length || this.vertexIndex + 20 > fArr.length) {
            flush();
        }
        int i = this.triangleIndex;
        int i2 = this.vertexIndex / 5;
        int i3 = i + 1;
        sArr[i] = (short) i2;
        int i4 = i3 + 1;
        sArr[i3] = (short) (i2 + 1);
        int i5 = i4 + 1;
        sArr[i4] = (short) (i2 + 2);
        int i6 = i5 + 1;
        sArr[i5] = (short) (i2 + 2);
        int i7 = i6 + 1;
        sArr[i6] = (short) (i2 + 3);
        sArr[i7] = (short) i2;
        this.triangleIndex = i7 + 1;
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
        float f48 = this.colorPacked;
        int i8 = this.vertexIndex;
        int i9 = i8 + 1;
        fArr[i8] = f40;
        int i10 = i9 + 1;
        fArr[i9] = f41;
        int i11 = i10 + 1;
        fArr[i10] = f48;
        int i12 = i11 + 1;
        fArr[i11] = f18;
        int i13 = i12 + 1;
        fArr[i12] = f19;
        int i14 = i13 + 1;
        fArr[i13] = f42;
        int i15 = i14 + 1;
        fArr[i14] = f43;
        int i16 = i15 + 1;
        fArr[i15] = f48;
        int i17 = i16 + 1;
        fArr[i16] = f20;
        int i18 = i17 + 1;
        fArr[i17] = f21;
        int i19 = i18 + 1;
        fArr[i18] = f44;
        int i20 = i19 + 1;
        fArr[i19] = f45;
        int i21 = i20 + 1;
        fArr[i20] = f48;
        int i22 = i21 + 1;
        fArr[i21] = f22;
        int i23 = i22 + 1;
        fArr[i22] = f23;
        int i24 = i23 + 1;
        fArr[i23] = f46;
        int i25 = i24 + 1;
        fArr[i24] = f47;
        int i26 = i25 + 1;
        fArr[i25] = f48;
        int i27 = i26 + 1;
        fArr[i26] = f24;
        fArr[i27] = f25;
        this.vertexIndex = i27 + 1;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion, float f, float f2, Affine2 affine2) {
        if (!this.drawing) {
            throw new IllegalStateException("PolygonSpriteBatch.begin must be called before draw.");
        }
        short[] sArr = this.triangles;
        float[] fArr = this.vertices;
        Texture texture = textureRegion.texture;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.triangleIndex + 6 > sArr.length || this.vertexIndex + 20 > fArr.length) {
            flush();
        }
        int i = this.triangleIndex;
        int i2 = this.vertexIndex / 5;
        int i3 = i + 1;
        sArr[i] = (short) i2;
        int i4 = i3 + 1;
        sArr[i3] = (short) (i2 + 1);
        int i5 = i4 + 1;
        sArr[i4] = (short) (i2 + 2);
        int i6 = i5 + 1;
        sArr[i5] = (short) (i2 + 2);
        int i7 = i6 + 1;
        sArr[i6] = (short) (i2 + 3);
        sArr[i7] = (short) i2;
        this.triangleIndex = i7 + 1;
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
        float f15 = this.colorPacked;
        int i8 = this.vertexIndex;
        int i9 = i8 + 1;
        fArr[i8] = f3;
        int i10 = i9 + 1;
        fArr[i9] = f4;
        int i11 = i10 + 1;
        fArr[i10] = f15;
        int i12 = i11 + 1;
        fArr[i11] = f11;
        int i13 = i12 + 1;
        fArr[i12] = f12;
        int i14 = i13 + 1;
        fArr[i13] = f5;
        int i15 = i14 + 1;
        fArr[i14] = f6;
        int i16 = i15 + 1;
        fArr[i15] = f15;
        int i17 = i16 + 1;
        fArr[i16] = f11;
        int i18 = i17 + 1;
        fArr[i17] = f14;
        int i19 = i18 + 1;
        fArr[i18] = f7;
        int i20 = i19 + 1;
        fArr[i19] = f8;
        int i21 = i20 + 1;
        fArr[i20] = f15;
        int i22 = i21 + 1;
        fArr[i21] = f13;
        int i23 = i22 + 1;
        fArr[i22] = f14;
        int i24 = i23 + 1;
        fArr[i23] = f9;
        int i25 = i24 + 1;
        fArr[i24] = f10;
        int i26 = i25 + 1;
        fArr[i25] = f15;
        int i27 = i26 + 1;
        fArr[i26] = f13;
        fArr[i27] = f12;
        this.vertexIndex = i27 + 1;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void flush() {
        if (this.vertexIndex == 0) {
            return;
        }
        this.renderCalls++;
        this.totalRenderCalls++;
        int i = this.triangleIndex;
        if (i > this.maxTrianglesInBatch) {
            this.maxTrianglesInBatch = i;
        }
        this.lastTexture.bind();
        Mesh mesh = this.mesh;
        mesh.setVertices(this.vertices, 0, this.vertexIndex);
        mesh.setIndices(this.triangles, 0, i);
        if (this.blendingDisabled) {
            Gdx.gl.glDisable(3042);
        } else {
            Gdx.gl.glEnable(3042);
            if (this.blendSrcFunc != -1) {
                Gdx.gl.glBlendFuncSeparate(this.blendSrcFunc, this.blendDstFunc, this.blendSrcFuncAlpha, this.blendDstFuncAlpha);
            }
        }
        mesh.render(this.customShader != null ? this.customShader : this.shader, 4, 0, i);
        this.vertexIndex = 0;
        this.triangleIndex = 0;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void disableBlending() {
        flush();
        this.blendingDisabled = true;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void enableBlending() {
        flush();
        this.blendingDisabled = false;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void setBlendFunction(int i, int i2) {
        setBlendFunctionSeparate(i, i2, i, i2);
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void setBlendFunctionSeparate(int i, int i2, int i3, int i4) {
        if (this.blendSrcFunc == i && this.blendDstFunc == i2 && this.blendSrcFuncAlpha == i3 && this.blendDstFuncAlpha == i4) {
            return;
        }
        flush();
        this.blendSrcFunc = i;
        this.blendDstFunc = i2;
        this.blendSrcFuncAlpha = i3;
        this.blendDstFuncAlpha = i4;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public int getBlendSrcFunc() {
        return this.blendSrcFunc;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public int getBlendDstFunc() {
        return this.blendDstFunc;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public int getBlendSrcFuncAlpha() {
        return this.blendSrcFuncAlpha;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public int getBlendDstFuncAlpha() {
        return this.blendDstFuncAlpha;
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.mesh.dispose();
        if (!this.ownsShader || this.shader == null) {
            return;
        }
        this.shader.dispose();
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public Matrix4 getProjectionMatrix() {
        return this.projectionMatrix;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public Matrix4 getTransformMatrix() {
        return this.transformMatrix;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void setProjectionMatrix(Matrix4 matrix4) {
        if (this.drawing) {
            flush();
        }
        this.projectionMatrix.set(matrix4);
        if (this.drawing) {
            setupMatrices();
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void setTransformMatrix(Matrix4 matrix4) {
        if (this.drawing) {
            flush();
        }
        this.transformMatrix.set(matrix4);
        if (this.drawing) {
            setupMatrices();
        }
    }

    protected void setupMatrices() {
        this.combinedMatrix.set(this.projectionMatrix).mul(this.transformMatrix);
        if (this.customShader != null) {
            this.customShader.setUniformMatrix("u_projTrans", this.combinedMatrix);
            this.customShader.setUniformi("u_texture", 0);
        } else {
            this.shader.setUniformMatrix("u_projTrans", this.combinedMatrix);
            this.shader.setUniformi("u_texture", 0);
        }
    }

    private void switchTexture(Texture texture) {
        flush();
        this.lastTexture = texture;
        this.invTexWidth = 1.0f / texture.getWidth();
        this.invTexHeight = 1.0f / texture.getHeight();
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void setShader(ShaderProgram shaderProgram) {
        if (this.drawing) {
            flush();
        }
        this.customShader = shaderProgram;
        if (this.drawing) {
            if (this.customShader != null) {
                this.customShader.bind();
            } else {
                this.shader.bind();
            }
            setupMatrices();
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public ShaderProgram getShader() {
        if (this.customShader == null) {
            return this.shader;
        }
        return this.customShader;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public boolean isBlendingEnabled() {
        return !this.blendingDisabled;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public boolean isDrawing() {
        return this.drawing;
    }
}
