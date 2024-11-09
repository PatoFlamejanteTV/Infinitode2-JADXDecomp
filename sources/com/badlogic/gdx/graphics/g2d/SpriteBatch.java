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
import java.nio.ShortBuffer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/SpriteBatch.class */
public class SpriteBatch implements Batch {

    @Deprecated
    public static Mesh.VertexDataType defaultVertexDataType = Mesh.VertexDataType.VertexArray;
    private Mesh mesh;
    final float[] vertices;
    int idx;
    Texture lastTexture;
    float invTexWidth;
    float invTexHeight;
    boolean drawing;
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
    public int maxSpritesInBatch;

    public SpriteBatch() {
        this(1000, null);
    }

    public SpriteBatch(int i) {
        this(i, null);
    }

    public SpriteBatch(int i, ShaderProgram shaderProgram) {
        this.idx = 0;
        this.lastTexture = null;
        this.invTexWidth = 0.0f;
        this.invTexHeight = 0.0f;
        this.drawing = false;
        this.transformMatrix = new Matrix4();
        this.projectionMatrix = new Matrix4();
        this.combinedMatrix = new Matrix4();
        this.blendingDisabled = false;
        this.blendSrcFunc = 770;
        this.blendDstFunc = 771;
        this.blendSrcFuncAlpha = 770;
        this.blendDstFuncAlpha = 771;
        this.customShader = null;
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.colorPacked = Color.WHITE_FLOAT_BITS;
        this.renderCalls = 0;
        this.totalRenderCalls = 0;
        this.maxSpritesInBatch = 0;
        if (i > 8191) {
            throw new IllegalArgumentException("Can't have more than 8191 sprites per batch: " + i);
        }
        Mesh.VertexDataType vertexDataType = Gdx.gl30 != null ? Mesh.VertexDataType.VertexBufferObjectWithVAO : defaultVertexDataType;
        this.mesh = new Mesh(vertexDataType, false, i << 2, i * 6, new VertexAttribute(1, 2, ShaderProgram.POSITION_ATTRIBUTE), new VertexAttribute(4, 4, ShaderProgram.COLOR_ATTRIBUTE), new VertexAttribute(16, 2, "a_texCoord0"));
        this.projectionMatrix.setToOrtho2D(0.0f, 0.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.vertices = new float[i * 20];
        int i2 = i * 6;
        short[] sArr = new short[i2];
        short s = 0;
        int i3 = 0;
        while (i3 < i2) {
            sArr[i3] = s;
            sArr[i3 + 1] = (short) (s + 1);
            sArr[i3 + 2] = (short) (s + 2);
            sArr[i3 + 3] = (short) (s + 2);
            sArr[i3 + 4] = (short) (s + 3);
            sArr[i3 + 5] = s;
            i3 += 6;
            s = (short) (s + 4);
        }
        this.mesh.setIndices(sArr);
        if (shaderProgram == null) {
            this.shader = createDefaultShader();
            this.ownsShader = true;
        } else {
            this.shader = shaderProgram;
        }
        if (vertexDataType != Mesh.VertexDataType.VertexArray) {
            this.mesh.bind(this.shader);
            this.mesh.unbind(this.shader);
        }
    }

    public static ShaderProgram createDefaultShader() {
        ShaderProgram shaderProgram = new ShaderProgram("attribute vec4 a_position;\nattribute vec4 a_color;\nattribute vec2 a_texCoord0;\nuniform mat4 u_projTrans;\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\n\nvoid main()\n{\n   v_color = a_color;\n   v_color.a = v_color.a * (255.0/254.0);\n   v_texCoords = a_texCoord0;\n   gl_Position =  u_projTrans * a_position;\n}\n", "#ifdef GL_ES\n#define LOWP lowp\nprecision mediump float;\n#else\n#define LOWP \n#endif\nvarying LOWP vec4 v_color;\nvarying vec2 v_texCoords;\nuniform sampler2D u_texture;\nvoid main()\n{\n  gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n}");
        if (!shaderProgram.isCompiled()) {
            throw new IllegalArgumentException("Error compiling shader: " + shaderProgram.getLog());
        }
        return shaderProgram;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void begin() {
        if (this.drawing) {
            throw new IllegalStateException("SpriteBatch.end must be called before begin.");
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
            throw new IllegalStateException("SpriteBatch.begin must be called before end.");
        }
        if (this.idx > 0) {
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
    public Color getColor() {
        return this.color;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void setPackedColor(float f) {
        Color.abgr8888ToColor(this.color, f);
        this.colorPacked = f;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public float getPackedColor() {
        return this.colorPacked;
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
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] fArr = this.vertices;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.idx == fArr.length) {
            flush();
        }
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
        int i5 = this.idx;
        fArr[i5] = f32;
        fArr[i5 + 1] = f33;
        fArr[i5 + 2] = f44;
        fArr[i5 + 3] = f40;
        fArr[i5 + 4] = f41;
        fArr[i5 + 5] = f34;
        fArr[i5 + 6] = f35;
        fArr[i5 + 7] = f44;
        fArr[i5 + 8] = f40;
        fArr[i5 + 9] = f43;
        fArr[i5 + 10] = f36;
        fArr[i5 + 11] = f37;
        fArr[i5 + 12] = f44;
        fArr[i5 + 13] = f42;
        fArr[i5 + 14] = f43;
        fArr[i5 + 15] = f38;
        fArr[i5 + 16] = f39;
        fArr[i5 + 17] = f44;
        fArr[i5 + 18] = f42;
        fArr[i5 + 19] = f41;
        this.idx = i5 + 20;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f, float f2, float f3, float f4, int i, int i2, int i3, int i4, boolean z, boolean z2) {
        if (!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] fArr = this.vertices;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.idx == fArr.length) {
            flush();
        }
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
        int i5 = this.idx;
        fArr[i5] = f;
        fArr[i5 + 1] = f2;
        fArr[i5 + 2] = f11;
        fArr[i5 + 3] = f5;
        fArr[i5 + 4] = f6;
        fArr[i5 + 5] = f;
        fArr[i5 + 6] = f10;
        fArr[i5 + 7] = f11;
        fArr[i5 + 8] = f5;
        fArr[i5 + 9] = f8;
        fArr[i5 + 10] = f9;
        fArr[i5 + 11] = f10;
        fArr[i5 + 12] = f11;
        fArr[i5 + 13] = f7;
        fArr[i5 + 14] = f8;
        fArr[i5 + 15] = f9;
        fArr[i5 + 16] = f2;
        fArr[i5 + 17] = f11;
        fArr[i5 + 18] = f7;
        fArr[i5 + 19] = f6;
        this.idx = i5 + 20;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f, float f2, int i, int i2, int i3, int i4) {
        if (!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] fArr = this.vertices;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.idx == fArr.length) {
            flush();
        }
        float f3 = i * this.invTexWidth;
        float f4 = (i2 + i4) * this.invTexHeight;
        float f5 = (i + i3) * this.invTexWidth;
        float f6 = i2 * this.invTexHeight;
        float f7 = f + i3;
        float f8 = f2 + i4;
        float f9 = this.colorPacked;
        int i5 = this.idx;
        fArr[i5] = f;
        fArr[i5 + 1] = f2;
        fArr[i5 + 2] = f9;
        fArr[i5 + 3] = f3;
        fArr[i5 + 4] = f4;
        fArr[i5 + 5] = f;
        fArr[i5 + 6] = f8;
        fArr[i5 + 7] = f9;
        fArr[i5 + 8] = f3;
        fArr[i5 + 9] = f6;
        fArr[i5 + 10] = f7;
        fArr[i5 + 11] = f8;
        fArr[i5 + 12] = f9;
        fArr[i5 + 13] = f5;
        fArr[i5 + 14] = f6;
        fArr[i5 + 15] = f7;
        fArr[i5 + 16] = f2;
        fArr[i5 + 17] = f9;
        fArr[i5 + 18] = f5;
        fArr[i5 + 19] = f4;
        this.idx = i5 + 20;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] fArr = this.vertices;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.idx == fArr.length) {
            flush();
        }
        float f9 = f + f3;
        float f10 = f2 + f4;
        float f11 = this.colorPacked;
        int i = this.idx;
        fArr[i] = f;
        fArr[i + 1] = f2;
        fArr[i + 2] = f11;
        fArr[i + 3] = f5;
        fArr[i + 4] = f6;
        fArr[i + 5] = f;
        fArr[i + 6] = f10;
        fArr[i + 7] = f11;
        fArr[i + 8] = f5;
        fArr[i + 9] = f8;
        fArr[i + 10] = f9;
        fArr[i + 11] = f10;
        fArr[i + 12] = f11;
        fArr[i + 13] = f7;
        fArr[i + 14] = f8;
        fArr[i + 15] = f9;
        fArr[i + 16] = f2;
        fArr[i + 17] = f11;
        fArr[i + 18] = f7;
        fArr[i + 19] = f6;
        this.idx = i + 20;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f, float f2) {
        draw(texture, f, f2, texture.getWidth(), texture.getHeight());
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float f, float f2, float f3, float f4) {
        if (!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] fArr = this.vertices;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.idx == fArr.length) {
            flush();
        }
        float f5 = f + f3;
        float f6 = f2 + f4;
        float f7 = this.colorPacked;
        int i = this.idx;
        fArr[i] = f;
        fArr[i + 1] = f2;
        fArr[i + 2] = f7;
        fArr[i + 3] = 0.0f;
        fArr[i + 4] = 1.0f;
        fArr[i + 5] = f;
        fArr[i + 6] = f6;
        fArr[i + 7] = f7;
        fArr[i + 8] = 0.0f;
        fArr[i + 9] = 0.0f;
        fArr[i + 10] = f5;
        fArr[i + 11] = f6;
        fArr[i + 12] = f7;
        fArr[i + 13] = 1.0f;
        fArr[i + 14] = 0.0f;
        fArr[i + 15] = f5;
        fArr[i + 16] = f2;
        fArr[i + 17] = f7;
        fArr[i + 18] = 1.0f;
        fArr[i + 19] = 1.0f;
        this.idx = i + 20;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(Texture texture, float[] fArr, int i, int i2) {
        if (!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        int length = this.vertices.length;
        int i3 = length;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else {
            int i4 = length - this.idx;
            i3 = i4;
            if (i4 == 0) {
                flush();
                i3 = length;
            }
        }
        int min = Math.min(i3, i2);
        System.arraycopy(fArr, i, this.vertices, this.idx, min);
        this.idx += min;
        while (true) {
            i2 -= min;
            if (i2 > 0) {
                i += min;
                flush();
                min = Math.min(length, i2);
                System.arraycopy(fArr, i, this.vertices, 0, min);
                this.idx += min;
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
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] fArr = this.vertices;
        Texture texture = textureRegion.texture;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.idx == fArr.length) {
            flush();
        }
        float f5 = f + f3;
        float f6 = f2 + f4;
        float f7 = textureRegion.u;
        float f8 = textureRegion.v2;
        float f9 = textureRegion.u2;
        float f10 = textureRegion.v;
        float f11 = this.colorPacked;
        int i = this.idx;
        fArr[i] = f;
        fArr[i + 1] = f2;
        fArr[i + 2] = f11;
        fArr[i + 3] = f7;
        fArr[i + 4] = f8;
        fArr[i + 5] = f;
        fArr[i + 6] = f6;
        fArr[i + 7] = f11;
        fArr[i + 8] = f7;
        fArr[i + 9] = f10;
        fArr[i + 10] = f5;
        fArr[i + 11] = f6;
        fArr[i + 12] = f11;
        fArr[i + 13] = f9;
        fArr[i + 14] = f10;
        fArr[i + 15] = f5;
        fArr[i + 16] = f2;
        fArr[i + 17] = f11;
        fArr[i + 18] = f9;
        fArr[i + 19] = f8;
        this.idx = i + 20;
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
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] fArr = this.vertices;
        Texture texture = textureRegion.texture;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.idx == fArr.length) {
            flush();
        }
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
        int i = this.idx;
        fArr[i] = f32;
        fArr[i + 1] = f33;
        fArr[i + 2] = f44;
        fArr[i + 3] = f40;
        fArr[i + 4] = f41;
        fArr[i + 5] = f34;
        fArr[i + 6] = f35;
        fArr[i + 7] = f44;
        fArr[i + 8] = f40;
        fArr[i + 9] = f43;
        fArr[i + 10] = f36;
        fArr[i + 11] = f37;
        fArr[i + 12] = f44;
        fArr[i + 13] = f42;
        fArr[i + 14] = f43;
        fArr[i + 15] = f38;
        fArr[i + 16] = f39;
        fArr[i + 17] = f44;
        fArr[i + 18] = f42;
        fArr[i + 19] = f41;
        this.idx = i + 20;
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
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] fArr = this.vertices;
        Texture texture = textureRegion.texture;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.idx == fArr.length) {
            flush();
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
        float f48 = this.colorPacked;
        int i = this.idx;
        fArr[i] = f40;
        fArr[i + 1] = f41;
        fArr[i + 2] = f48;
        fArr[i + 3] = f18;
        fArr[i + 4] = f19;
        fArr[i + 5] = f42;
        fArr[i + 6] = f43;
        fArr[i + 7] = f48;
        fArr[i + 8] = f20;
        fArr[i + 9] = f21;
        fArr[i + 10] = f44;
        fArr[i + 11] = f45;
        fArr[i + 12] = f48;
        fArr[i + 13] = f22;
        fArr[i + 14] = f23;
        fArr[i + 15] = f46;
        fArr[i + 16] = f47;
        fArr[i + 17] = f48;
        fArr[i + 18] = f24;
        fArr[i + 19] = f25;
        this.idx = i + 20;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void draw(TextureRegion textureRegion, float f, float f2, Affine2 affine2) {
        if (!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] fArr = this.vertices;
        Texture texture = textureRegion.texture;
        if (texture != this.lastTexture) {
            switchTexture(texture);
        } else if (this.idx == fArr.length) {
            flush();
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
        float f15 = this.colorPacked;
        int i = this.idx;
        fArr[i] = f3;
        fArr[i + 1] = f4;
        fArr[i + 2] = f15;
        fArr[i + 3] = f11;
        fArr[i + 4] = f12;
        fArr[i + 5] = f5;
        fArr[i + 6] = f6;
        fArr[i + 7] = f15;
        fArr[i + 8] = f11;
        fArr[i + 9] = f14;
        fArr[i + 10] = f7;
        fArr[i + 11] = f8;
        fArr[i + 12] = f15;
        fArr[i + 13] = f13;
        fArr[i + 14] = f14;
        fArr[i + 15] = f9;
        fArr[i + 16] = f10;
        fArr[i + 17] = f15;
        fArr[i + 18] = f13;
        fArr[i + 19] = f12;
        this.idx = i + 20;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void flush() {
        if (this.idx == 0) {
            return;
        }
        this.renderCalls++;
        this.totalRenderCalls++;
        int i = this.idx / 20;
        if (i > this.maxSpritesInBatch) {
            this.maxSpritesInBatch = i;
        }
        int i2 = i * 6;
        this.lastTexture.bind();
        Mesh mesh = this.mesh;
        mesh.setVertices(this.vertices, 0, this.idx);
        ShortBuffer indicesBuffer = mesh.getIndicesBuffer(false);
        indicesBuffer.position(0);
        indicesBuffer.limit(i2);
        if (this.blendingDisabled) {
            Gdx.gl.glDisable(3042);
        } else {
            Gdx.gl.glEnable(3042);
            if (this.blendSrcFunc != -1) {
                Gdx.gl.glBlendFuncSeparate(this.blendSrcFunc, this.blendDstFunc, this.blendSrcFuncAlpha, this.blendDstFuncAlpha);
            }
        }
        mesh.render(this.customShader != null ? this.customShader : this.shader, 4, 0, i2);
        this.idx = 0;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void disableBlending() {
        if (this.blendingDisabled) {
            return;
        }
        flush();
        this.blendingDisabled = true;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void enableBlending() {
        if (this.blendingDisabled) {
            flush();
            this.blendingDisabled = false;
        }
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

    /* JADX INFO: Access modifiers changed from: protected */
    public void switchTexture(Texture texture) {
        flush();
        this.lastTexture = texture;
        this.invTexWidth = 1.0f / texture.getWidth();
        this.invTexHeight = 1.0f / texture.getHeight();
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public void setShader(ShaderProgram shaderProgram) {
        if (shaderProgram == this.customShader) {
            return;
        }
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
