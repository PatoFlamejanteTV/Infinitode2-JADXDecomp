package com.prineside.tdi2.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.utils.logging.TLog;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/ParallelBatch.class */
public final class ParallelBatch implements Batch {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3877a = TLog.forClass(ParallelBatch.class);

    @Deprecated
    public static Mesh.VertexDataType defaultVertexDataType = Mesh.VertexDataType.VertexArray;
    public static final float[] FLUSH_VERTICES = new float[163840];

    /* renamed from: b, reason: collision with root package name */
    private FloatBuffer f3878b;
    private Array<Object> c;
    private float[] d;
    private int e;
    private Texture f;
    private float g;
    private float h;
    public boolean drawing;
    private final Matrix4 i;
    private final Matrix4 j;
    private final Matrix4 k;
    private boolean l;
    private int m;
    private int n;
    private int o;
    private int p;
    private final ShaderProgram q;
    private ShaderProgram r;
    private final Color s;
    private float t;
    public int renderCalls;
    public int totalRenderCalls;
    public int maxSpritesInBatch;

    public ParallelBatch() {
        this(1000, null);
    }

    public ParallelBatch(int i) {
        this(i, null);
    }

    public ParallelBatch(int i, ShaderProgram shaderProgram) {
        this.c = new Array<>();
        this.e = 0;
        this.f = null;
        this.g = 0.0f;
        this.h = 0.0f;
        this.drawing = false;
        this.i = new Matrix4();
        this.j = new Matrix4();
        this.k = new Matrix4();
        this.l = false;
        this.m = 770;
        this.n = 771;
        this.o = 770;
        this.p = 771;
        this.r = null;
        this.s = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.t = Color.WHITE_FLOAT_BITS;
        this.renderCalls = 0;
        this.totalRenderCalls = 0;
        this.maxSpritesInBatch = 0;
        if (i > 8191) {
            throw new IllegalArgumentException("Can't have more than 8191 sprites per batch: " + i);
        }
        this.j.setToOrtho2D(0.0f, 0.0f, Game.i.uiManager.getScreenWidth(), Game.i.uiManager.getScreenHeight());
        this.d = new float[i * 20];
        if (shaderProgram == null) {
            this.q = SpriteBatch.createDefaultShader();
        } else {
            this.q = shaderProgram;
        }
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(i << 2);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.f3878b = allocateDirect.asFloatBuffer();
    }

    public final void reset() {
        this.c.clear();
        this.f3878b.clear();
    }

    public final void render(Mesh mesh) {
        for (int i = 0; i < this.c.size; i++) {
            Object obj = this.c.items[i];
            if (obj instanceof OpBegin) {
                Gdx.gl.glDepthMask(false);
                ((OpBegin) obj).f3879a.bind();
            } else if (!(obj instanceof OpEnd)) {
                if (!(obj instanceof OpSetupMatrices)) {
                    if (obj instanceof OpFlush) {
                        OpFlush opFlush = (OpFlush) obj;
                        opFlush.f3882b.bind();
                        this.f3878b.position(opFlush.c);
                        this.f3878b.get(FLUSH_VERTICES, 0, opFlush.d);
                        mesh.setVertices(FLUSH_VERTICES, 0, opFlush.d);
                        mesh.getIndicesBuffer().position(0);
                        mesh.getIndicesBuffer().limit(opFlush.e);
                        if (opFlush.f) {
                            Gdx.gl.glDisable(3042);
                        } else {
                            Gdx.gl.glEnable(3042);
                            if (opFlush.g != -1) {
                                Gdx.gl.glBlendFuncSeparate(opFlush.g, opFlush.h, opFlush.i, opFlush.j);
                            }
                        }
                        mesh.render(opFlush.f3881a, 4, 0, opFlush.e);
                    }
                } else {
                    ShaderProgram shaderProgram = ((OpSetupMatrices) obj).f3883a;
                    shaderProgram.setUniformMatrix("u_projTrans", ((OpSetupMatrices) obj).f3884b);
                    shaderProgram.setUniformi("u_texture", 0);
                }
            } else {
                GL20 gl20 = Gdx.gl;
                gl20.glDepthMask(true);
                if (((OpEnd) obj).f3880a) {
                    gl20.glDisable(3042);
                }
            }
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final void begin() {
        if (this.drawing) {
            throw new IllegalStateException("SpriteBatch.end must be called before begin.");
        }
        this.renderCalls = 0;
        OpBegin opBegin = new OpBegin((byte) 0);
        if (this.r != null) {
            opBegin.f3879a = this.r;
        } else {
            opBegin.f3879a = this.q;
        }
        this.c.add(opBegin);
        a();
        this.drawing = true;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final void end() {
        if (!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before end.");
        }
        if (this.e > 0) {
            flush();
        }
        this.f = null;
        this.drawing = false;
        OpEnd opEnd = new OpEnd((byte) 0);
        opEnd.f3880a = isBlendingEnabled();
        this.c.add(opEnd);
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void setColor(Color color) {
        this.s.set(color);
        this.t = color.toFloatBits();
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void setColor(float f, float f2, float f3, float f4) {
        this.s.set(f, f2, f3, f4);
        this.t = this.s.toFloatBits();
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final Color getColor() {
        return this.s;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final void setPackedColor(float f) {
        Color.abgr8888ToColor(this.s, f);
        this.t = f;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final float getPackedColor() {
        return this.t;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(Texture texture, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int i, int i2, int i3, int i4, boolean z, boolean z2) {
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
        float[] fArr = this.d;
        if (texture != this.f) {
            a(texture);
        } else if (this.e == fArr.length) {
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
        float f40 = i * this.g;
        float f41 = (i2 + i4) * this.h;
        float f42 = (i + i3) * this.g;
        float f43 = i2 * this.h;
        if (z) {
            f40 = f42;
            f42 = f40;
        }
        if (z2) {
            f41 = f43;
            f43 = f41;
        }
        float f44 = this.t;
        int i5 = this.e;
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
        this.e = i5 + 20;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(Texture texture, float f, float f2, float f3, float f4, int i, int i2, int i3, int i4, boolean z, boolean z2) {
        if (!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] fArr = this.d;
        if (texture != this.f) {
            a(texture);
        } else if (this.e == fArr.length) {
            flush();
        }
        float f5 = i * this.g;
        float f6 = (i2 + i4) * this.h;
        float f7 = (i + i3) * this.g;
        float f8 = i2 * this.h;
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
        float f11 = this.t;
        int i5 = this.e;
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
        this.e = i5 + 20;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(Texture texture, float f, float f2, int i, int i2, int i3, int i4) {
        if (!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] fArr = this.d;
        if (texture != this.f) {
            a(texture);
        } else if (this.e == fArr.length) {
            flush();
        }
        float f3 = i * this.g;
        float f4 = (i2 + i4) * this.h;
        float f5 = (i + i3) * this.g;
        float f6 = i2 * this.h;
        float f7 = f + i3;
        float f8 = f2 + i4;
        float f9 = this.t;
        int i5 = this.e;
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
        this.e = i5 + 20;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(Texture texture, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] fArr = this.d;
        if (texture != this.f) {
            a(texture);
        } else if (this.e == fArr.length) {
            flush();
        }
        float f9 = f + f3;
        float f10 = f2 + f4;
        float f11 = this.t;
        int i = this.e;
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
        this.e = i + 20;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(Texture texture, float f, float f2) {
        draw(texture, f, f2, texture.getWidth(), texture.getHeight());
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(Texture texture, float f, float f2, float f3, float f4) {
        if (!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] fArr = this.d;
        if (texture != this.f) {
            a(texture);
        } else if (this.e == fArr.length) {
            flush();
        }
        float f5 = f + f3;
        float f6 = f2 + f4;
        float f7 = this.t;
        int i = this.e;
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
        this.e = i + 20;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(Texture texture, float[] fArr, int i, int i2) {
        if (!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        int length = this.d.length;
        int i3 = length;
        if (texture != this.f) {
            a(texture);
        } else {
            int i4 = length - this.e;
            i3 = i4;
            if (i4 == 0) {
                flush();
                i3 = length;
            }
        }
        int min = Math.min(i3, i2);
        System.arraycopy(fArr, i, this.d, this.e, min);
        this.e += min;
        while (true) {
            i2 -= min;
            if (i2 > 0) {
                i += min;
                flush();
                min = Math.min(length, i2);
                System.arraycopy(fArr, i, this.d, 0, min);
                this.e += min;
            } else {
                return;
            }
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(TextureRegion textureRegion, float f, float f2) {
        draw(textureRegion, f, f2, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(TextureRegion textureRegion, float f, float f2, float f3, float f4) {
        if (!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] fArr = this.d;
        Texture texture = textureRegion.getTexture();
        if (texture != this.f) {
            a(texture);
        } else if (this.e == fArr.length) {
            flush();
        }
        float f5 = f + f3;
        float f6 = f2 + f4;
        float u = textureRegion.getU();
        float v2 = textureRegion.getV2();
        float u2 = textureRegion.getU2();
        float v = textureRegion.getV();
        float f7 = this.t;
        int i = this.e;
        fArr[i] = f;
        fArr[i + 1] = f2;
        fArr[i + 2] = f7;
        fArr[i + 3] = u;
        fArr[i + 4] = v2;
        fArr[i + 5] = f;
        fArr[i + 6] = f6;
        fArr[i + 7] = f7;
        fArr[i + 8] = u;
        fArr[i + 9] = v;
        fArr[i + 10] = f5;
        fArr[i + 11] = f6;
        fArr[i + 12] = f7;
        fArr[i + 13] = u2;
        fArr[i + 14] = v;
        fArr[i + 15] = f5;
        fArr[i + 16] = f2;
        fArr[i + 17] = f7;
        fArr[i + 18] = u2;
        fArr[i + 19] = v2;
        this.e = i + 20;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(TextureRegion textureRegion, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
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
        float[] fArr = this.d;
        Texture texture = textureRegion.getTexture();
        if (texture != this.f) {
            a(texture);
        } else if (this.e == fArr.length) {
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
        float u = textureRegion.getU();
        float v2 = textureRegion.getV2();
        float u2 = textureRegion.getU2();
        float v = textureRegion.getV();
        float f40 = this.t;
        int i = this.e;
        fArr[i] = f32;
        fArr[i + 1] = f33;
        fArr[i + 2] = f40;
        fArr[i + 3] = u;
        fArr[i + 4] = v2;
        fArr[i + 5] = f34;
        fArr[i + 6] = f35;
        fArr[i + 7] = f40;
        fArr[i + 8] = u;
        fArr[i + 9] = v;
        fArr[i + 10] = f36;
        fArr[i + 11] = f37;
        fArr[i + 12] = f40;
        fArr[i + 13] = u2;
        fArr[i + 14] = v;
        fArr[i + 15] = f38;
        fArr[i + 16] = f39;
        fArr[i + 17] = f40;
        fArr[i + 18] = u2;
        fArr[i + 19] = v2;
        this.e = i + 20;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(TextureRegion textureRegion, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, boolean z) {
        float f10;
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
        float u;
        float v;
        float u2;
        float v2;
        float u22;
        float v22;
        float u3;
        float v23;
        if (!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] fArr = this.d;
        Texture texture = textureRegion.getTexture();
        if (texture != this.f) {
            a(texture);
        } else if (this.e == fArr.length) {
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
        if (z) {
            u = textureRegion.getU2();
            v = textureRegion.getV2();
            u2 = textureRegion.getU();
            v2 = textureRegion.getV2();
            u22 = textureRegion.getU();
            v22 = textureRegion.getV();
            u3 = textureRegion.getU2();
            v23 = textureRegion.getV();
        } else {
            u = textureRegion.getU();
            v = textureRegion.getV();
            u2 = textureRegion.getU2();
            v2 = textureRegion.getV();
            u22 = textureRegion.getU2();
            v22 = textureRegion.getV2();
            u3 = textureRegion.getU();
            v23 = textureRegion.getV2();
        }
        float f40 = this.t;
        int i = this.e;
        fArr[i] = f32;
        fArr[i + 1] = f33;
        fArr[i + 2] = f40;
        fArr[i + 3] = u;
        fArr[i + 4] = v;
        fArr[i + 5] = f34;
        fArr[i + 6] = f35;
        fArr[i + 7] = f40;
        fArr[i + 8] = u2;
        fArr[i + 9] = v2;
        fArr[i + 10] = f36;
        fArr[i + 11] = f37;
        fArr[i + 12] = f40;
        fArr[i + 13] = u22;
        fArr[i + 14] = v22;
        fArr[i + 15] = f38;
        fArr[i + 16] = f39;
        fArr[i + 17] = f40;
        fArr[i + 18] = u3;
        fArr[i + 19] = v23;
        this.e = i + 20;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(TextureRegion textureRegion, float f, float f2, Affine2 affine2) {
        if (!this.drawing) {
            throw new IllegalStateException("SpriteBatch.begin must be called before draw.");
        }
        float[] fArr = this.d;
        Texture texture = textureRegion.getTexture();
        if (texture != this.f) {
            a(texture);
        } else if (this.e == fArr.length) {
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
        float u = textureRegion.getU();
        float v2 = textureRegion.getV2();
        float u2 = textureRegion.getU2();
        float v = textureRegion.getV();
        float f11 = this.t;
        int i = this.e;
        fArr[i] = f3;
        fArr[i + 1] = f4;
        fArr[i + 2] = f11;
        fArr[i + 3] = u;
        fArr[i + 4] = v2;
        fArr[i + 5] = f5;
        fArr[i + 6] = f6;
        fArr[i + 7] = f11;
        fArr[i + 8] = u;
        fArr[i + 9] = v;
        fArr[i + 10] = f7;
        fArr[i + 11] = f8;
        fArr[i + 12] = f11;
        fArr[i + 13] = u2;
        fArr[i + 14] = v;
        fArr[i + 15] = f9;
        fArr[i + 16] = f10;
        fArr[i + 17] = f11;
        fArr[i + 18] = u2;
        fArr[i + 19] = v2;
        this.e = i + 20;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final void flush() {
        if (this.e == 0) {
            return;
        }
        this.renderCalls++;
        this.totalRenderCalls++;
        int i = this.e / 20;
        if (i > this.maxSpritesInBatch) {
            this.maxSpritesInBatch = i;
        }
        int i2 = i * 6;
        OpFlush opFlush = new OpFlush((byte) 0);
        opFlush.f3882b = this.f;
        opFlush.c = this.f3878b.position();
        opFlush.d = this.e;
        opFlush.e = i2;
        if (this.f3878b.remaining() < this.e) {
            int ceil = MathUtils.ceil(((this.f3878b.position() + this.e) << 2) * 1.2f);
            f3877a.i("enlarging allVertices from " + this.f3878b.capacity() + " to " + ceil, new Object[0]);
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(ceil);
            allocateDirect.order(ByteOrder.nativeOrder());
            FloatBuffer floatBuffer = this.f3878b;
            this.f3878b = allocateDirect.asFloatBuffer();
            floatBuffer.rewind();
            for (int i3 = 0; i3 < floatBuffer.position(); i3++) {
                this.f3878b.put(floatBuffer.get());
            }
            f3877a.i("copied buffer, capacity " + this.f3878b.capacity() + ", remaining " + this.f3878b.remaining(), new Object[0]);
        }
        this.f3878b.put(this.d, 0, this.e);
        opFlush.f = this.l;
        opFlush.g = this.m;
        opFlush.h = this.n;
        opFlush.i = this.o;
        opFlush.j = this.p;
        opFlush.f3881a = this.r != null ? this.r : this.q;
        this.c.add(opFlush);
        this.e = 0;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final void disableBlending() {
        if (this.l) {
            return;
        }
        flush();
        this.l = true;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final void enableBlending() {
        if (this.l) {
            flush();
            this.l = false;
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final void setBlendFunction(int i, int i2) {
        setBlendFunctionSeparate(i, i2, i, i2);
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final void setBlendFunctionSeparate(int i, int i2, int i3, int i4) {
        if (this.m == i && this.n == i2 && this.o == i3 && this.p == i4) {
            return;
        }
        flush();
        this.m = i;
        this.n = i2;
        this.o = i3;
        this.p = i4;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final int getBlendSrcFunc() {
        return this.m;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final int getBlendDstFunc() {
        return this.n;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final int getBlendSrcFuncAlpha() {
        return this.o;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final int getBlendDstFuncAlpha() {
        return this.p;
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public final void dispose() {
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final Matrix4 getProjectionMatrix() {
        return this.j;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final Matrix4 getTransformMatrix() {
        return this.i;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final void setProjectionMatrix(Matrix4 matrix4) {
        if (this.drawing) {
            flush();
        }
        this.j.set(matrix4);
        if (this.drawing) {
            a();
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final void setTransformMatrix(Matrix4 matrix4) {
        if (this.drawing) {
            flush();
        }
        this.i.set(matrix4);
        if (this.drawing) {
            a();
        }
    }

    private void a() {
        this.k.set(this.j).mul(this.i);
        OpSetupMatrices opSetupMatrices = new OpSetupMatrices((byte) 0);
        if (this.r != null) {
            opSetupMatrices.f3883a = this.r;
            opSetupMatrices.f3884b.set(this.k);
        } else {
            opSetupMatrices.f3883a = this.q;
            opSetupMatrices.f3884b.set(this.k);
        }
        this.c.add(opSetupMatrices);
    }

    private void a(Texture texture) {
        flush();
        this.f = texture;
        this.g = 1.0f / texture.getWidth();
        this.h = 1.0f / texture.getHeight();
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final void setShader(ShaderProgram shaderProgram) {
        if (this.drawing) {
            flush();
        }
        this.r = shaderProgram;
        if (this.drawing) {
            if (this.r != null) {
                this.r.bind();
            } else {
                this.q.bind();
            }
            a();
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final ShaderProgram getShader() {
        if (this.r == null) {
            return this.q;
        }
        return this.r;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final boolean isBlendingEnabled() {
        return !this.l;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final boolean isDrawing() {
        return this.drawing;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/ParallelBatch$OpBegin.class */
    private static final class OpBegin {

        /* renamed from: a, reason: collision with root package name */
        private ShaderProgram f3879a;

        private OpBegin() {
        }

        /* synthetic */ OpBegin(byte b2) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/ParallelBatch$OpSetupMatrices.class */
    public static final class OpSetupMatrices {

        /* renamed from: a, reason: collision with root package name */
        private ShaderProgram f3883a;

        /* renamed from: b, reason: collision with root package name */
        private Matrix4 f3884b;

        private OpSetupMatrices() {
            this.f3884b = new Matrix4();
        }

        /* synthetic */ OpSetupMatrices(byte b2) {
            this();
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/ParallelBatch$OpEnd.class */
    private static final class OpEnd {

        /* renamed from: a, reason: collision with root package name */
        private boolean f3880a;

        private OpEnd() {
        }

        /* synthetic */ OpEnd(byte b2) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/ParallelBatch$OpFlush.class */
    public static final class OpFlush {

        /* renamed from: a, reason: collision with root package name */
        private ShaderProgram f3881a;

        /* renamed from: b, reason: collision with root package name */
        private Texture f3882b;
        private int c;
        private int d;
        private int e;
        private boolean f;
        private int g;
        private int h;
        private int i;
        private int j;

        private OpFlush() {
        }

        /* synthetic */ OpFlush(byte b2) {
            this();
        }
    }
}
