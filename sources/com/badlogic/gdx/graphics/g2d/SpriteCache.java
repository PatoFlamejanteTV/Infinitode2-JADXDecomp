package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.IntArray;
import java.nio.FloatBuffer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/SpriteCache.class */
public class SpriteCache implements Disposable {
    private static final float[] tempVertices = new float[30];
    private final Mesh mesh;
    private boolean drawing;
    private final Matrix4 transformMatrix;
    private final Matrix4 projectionMatrix;
    private Array<Cache> caches;
    private final Matrix4 combinedMatrix;
    private final ShaderProgram shader;
    private Cache currentCache;
    private final Array<Texture> textures;
    private final IntArray counts;
    private final Color color;
    private float colorPacked;
    private ShaderProgram customShader;
    public int renderCalls;
    public int totalRenderCalls;

    public SpriteCache() {
        this(1000, false);
    }

    public SpriteCache(int i, boolean z) {
        this(i, createDefaultShader(), z);
    }

    public SpriteCache(int i, ShaderProgram shaderProgram, boolean z) {
        this.transformMatrix = new Matrix4();
        this.projectionMatrix = new Matrix4();
        this.caches = new Array<>();
        this.combinedMatrix = new Matrix4();
        this.textures = new Array<>(8);
        this.counts = new IntArray(8);
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.colorPacked = Color.WHITE_FLOAT_BITS;
        this.customShader = null;
        this.renderCalls = 0;
        this.totalRenderCalls = 0;
        this.shader = shaderProgram;
        if (z && i > 8191) {
            throw new IllegalArgumentException("Can't have more than 8191 sprites per batch: " + i);
        }
        this.mesh = new Mesh(true, i * (z ? 4 : 6), z ? i * 6 : 0, new VertexAttribute(1, 2, ShaderProgram.POSITION_ATTRIBUTE), new VertexAttribute(4, 4, ShaderProgram.COLOR_ATTRIBUTE), new VertexAttribute(16, 2, "a_texCoord0"));
        this.mesh.setAutoBind(false);
        if (z) {
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
        }
        this.projectionMatrix.setToOrtho2D(0.0f, 0.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void setColor(Color color) {
        this.color.set(color);
        this.colorPacked = color.toFloatBits();
    }

    public void setColor(float f, float f2, float f3, float f4) {
        this.color.set(f, f2, f3, f4);
        this.colorPacked = this.color.toFloatBits();
    }

    public Color getColor() {
        return this.color;
    }

    public void setPackedColor(float f) {
        Color.abgr8888ToColor(this.color, f);
        this.colorPacked = f;
    }

    public float getPackedColor() {
        return this.colorPacked;
    }

    public void beginCache() {
        if (this.drawing) {
            throw new IllegalStateException("end must be called before beginCache");
        }
        if (this.currentCache != null) {
            throw new IllegalStateException("endCache must be called before begin.");
        }
        this.mesh.getNumIndices();
        FloatBuffer verticesBuffer = this.mesh.getVerticesBuffer(true);
        this.currentCache = new Cache(this.caches.size, verticesBuffer.limit());
        this.caches.add(this.currentCache);
        verticesBuffer.compact();
    }

    public void beginCache(int i) {
        if (this.drawing) {
            throw new IllegalStateException("end must be called before beginCache");
        }
        if (this.currentCache != null) {
            throw new IllegalStateException("endCache must be called before begin.");
        }
        FloatBuffer verticesBuffer = this.mesh.getVerticesBuffer(true);
        if (i == this.caches.size - 1) {
            verticesBuffer.limit(this.caches.removeIndex(i).offset);
            beginCache();
        } else {
            this.currentCache = this.caches.get(i);
            verticesBuffer.position(this.currentCache.offset);
        }
    }

    public int endCache() {
        if (this.currentCache == null) {
            throw new IllegalStateException("beginCache must be called before endCache.");
        }
        Cache cache = this.currentCache;
        int position = this.mesh.getVerticesBuffer(false).position() - cache.offset;
        if (cache.textures == null) {
            cache.maxCount = position;
            cache.textureCount = this.textures.size;
            cache.textures = (Texture[]) this.textures.toArray(Texture.class);
            cache.counts = new int[cache.textureCount];
            int i = this.counts.size;
            for (int i2 = 0; i2 < i; i2++) {
                cache.counts[i2] = this.counts.get(i2);
            }
            this.mesh.getVerticesBuffer(true).flip();
        } else {
            if (position > cache.maxCount) {
                throw new GdxRuntimeException("If a cache is not the last created, it cannot be redefined with more entries than when it was first created: " + position + " (" + cache.maxCount + " max)");
            }
            cache.textureCount = this.textures.size;
            if (cache.textures.length < cache.textureCount) {
                cache.textures = new Texture[cache.textureCount];
            }
            int i3 = cache.textureCount;
            for (int i4 = 0; i4 < i3; i4++) {
                cache.textures[i4] = this.textures.get(i4);
            }
            if (cache.counts.length < cache.textureCount) {
                cache.counts = new int[cache.textureCount];
            }
            int i5 = cache.textureCount;
            for (int i6 = 0; i6 < i5; i6++) {
                cache.counts[i6] = this.counts.get(i6);
            }
            FloatBuffer verticesBuffer = this.mesh.getVerticesBuffer(true);
            verticesBuffer.position(0);
            Cache cache2 = this.caches.get(this.caches.size - 1);
            verticesBuffer.limit(cache2.offset + cache2.maxCount);
        }
        this.currentCache = null;
        this.textures.clear();
        this.counts.clear();
        return cache.id;
    }

    public void clear() {
        this.caches.clear();
        this.mesh.getVerticesBuffer(true).clear().flip();
    }

    public void add(Texture texture, float[] fArr, int i, int i2) {
        if (this.currentCache == null) {
            throw new IllegalStateException("beginCache must be called before add.");
        }
        int i3 = (i2 / ((this.mesh.getNumIndices() > 0 ? 4 : 6) * 5)) * 6;
        int i4 = this.textures.size - 1;
        if (i4 >= 0 && this.textures.get(i4) == texture) {
            this.counts.incr(i4, i3);
        } else {
            this.textures.add(texture);
            this.counts.add(i3);
        }
        this.mesh.getVerticesBuffer(true).put(fArr, i, i2);
    }

    public void add(Texture texture, float f, float f2) {
        float width = f + texture.getWidth();
        float height = f2 + texture.getHeight();
        tempVertices[0] = f;
        tempVertices[1] = f2;
        tempVertices[2] = this.colorPacked;
        tempVertices[3] = 0.0f;
        tempVertices[4] = 1.0f;
        tempVertices[5] = f;
        tempVertices[6] = height;
        tempVertices[7] = this.colorPacked;
        tempVertices[8] = 0.0f;
        tempVertices[9] = 0.0f;
        tempVertices[10] = width;
        tempVertices[11] = height;
        tempVertices[12] = this.colorPacked;
        tempVertices[13] = 1.0f;
        tempVertices[14] = 0.0f;
        if (this.mesh.getNumIndices() > 0) {
            tempVertices[15] = width;
            tempVertices[16] = f2;
            tempVertices[17] = this.colorPacked;
            tempVertices[18] = 1.0f;
            tempVertices[19] = 1.0f;
            add(texture, tempVertices, 0, 20);
            return;
        }
        tempVertices[15] = width;
        tempVertices[16] = height;
        tempVertices[17] = this.colorPacked;
        tempVertices[18] = 1.0f;
        tempVertices[19] = 0.0f;
        tempVertices[20] = width;
        tempVertices[21] = f2;
        tempVertices[22] = this.colorPacked;
        tempVertices[23] = 1.0f;
        tempVertices[24] = 1.0f;
        tempVertices[25] = f;
        tempVertices[26] = f2;
        tempVertices[27] = this.colorPacked;
        tempVertices[28] = 0.0f;
        tempVertices[29] = 1.0f;
        add(texture, tempVertices, 0, 30);
    }

    public void add(Texture texture, float f, float f2, int i, int i2, float f3, float f4, float f5, float f6, float f7) {
        float f8 = f + i;
        float f9 = f2 + i2;
        tempVertices[0] = f;
        tempVertices[1] = f2;
        tempVertices[2] = f7;
        tempVertices[3] = f3;
        tempVertices[4] = f4;
        tempVertices[5] = f;
        tempVertices[6] = f9;
        tempVertices[7] = f7;
        tempVertices[8] = f3;
        tempVertices[9] = f6;
        tempVertices[10] = f8;
        tempVertices[11] = f9;
        tempVertices[12] = f7;
        tempVertices[13] = f5;
        tempVertices[14] = f6;
        if (this.mesh.getNumIndices() > 0) {
            tempVertices[15] = f8;
            tempVertices[16] = f2;
            tempVertices[17] = f7;
            tempVertices[18] = f5;
            tempVertices[19] = f4;
            add(texture, tempVertices, 0, 20);
            return;
        }
        tempVertices[15] = f8;
        tempVertices[16] = f9;
        tempVertices[17] = f7;
        tempVertices[18] = f5;
        tempVertices[19] = f6;
        tempVertices[20] = f8;
        tempVertices[21] = f2;
        tempVertices[22] = f7;
        tempVertices[23] = f5;
        tempVertices[24] = f4;
        tempVertices[25] = f;
        tempVertices[26] = f2;
        tempVertices[27] = f7;
        tempVertices[28] = f3;
        tempVertices[29] = f4;
        add(texture, tempVertices, 0, 30);
    }

    public void add(Texture texture, float f, float f2, int i, int i2, int i3, int i4) {
        float width = 1.0f / texture.getWidth();
        float height = 1.0f / texture.getHeight();
        float f3 = i * width;
        float f4 = (i2 + i4) * height;
        float f5 = (i + i3) * width;
        float f6 = i2 * height;
        float f7 = f + i3;
        float f8 = f2 + i4;
        tempVertices[0] = f;
        tempVertices[1] = f2;
        tempVertices[2] = this.colorPacked;
        tempVertices[3] = f3;
        tempVertices[4] = f4;
        tempVertices[5] = f;
        tempVertices[6] = f8;
        tempVertices[7] = this.colorPacked;
        tempVertices[8] = f3;
        tempVertices[9] = f6;
        tempVertices[10] = f7;
        tempVertices[11] = f8;
        tempVertices[12] = this.colorPacked;
        tempVertices[13] = f5;
        tempVertices[14] = f6;
        if (this.mesh.getNumIndices() > 0) {
            tempVertices[15] = f7;
            tempVertices[16] = f2;
            tempVertices[17] = this.colorPacked;
            tempVertices[18] = f5;
            tempVertices[19] = f4;
            add(texture, tempVertices, 0, 20);
            return;
        }
        tempVertices[15] = f7;
        tempVertices[16] = f8;
        tempVertices[17] = this.colorPacked;
        tempVertices[18] = f5;
        tempVertices[19] = f6;
        tempVertices[20] = f7;
        tempVertices[21] = f2;
        tempVertices[22] = this.colorPacked;
        tempVertices[23] = f5;
        tempVertices[24] = f4;
        tempVertices[25] = f;
        tempVertices[26] = f2;
        tempVertices[27] = this.colorPacked;
        tempVertices[28] = f3;
        tempVertices[29] = f4;
        add(texture, tempVertices, 0, 30);
    }

    public void add(Texture texture, float f, float f2, float f3, float f4, int i, int i2, int i3, int i4, boolean z, boolean z2) {
        float width = 1.0f / texture.getWidth();
        float height = 1.0f / texture.getHeight();
        float f5 = i * width;
        float f6 = (i2 + i4) * height;
        float f7 = (i + i3) * width;
        float f8 = i2 * height;
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
        tempVertices[0] = f;
        tempVertices[1] = f2;
        tempVertices[2] = this.colorPacked;
        tempVertices[3] = f5;
        tempVertices[4] = f6;
        tempVertices[5] = f;
        tempVertices[6] = f10;
        tempVertices[7] = this.colorPacked;
        tempVertices[8] = f5;
        tempVertices[9] = f8;
        tempVertices[10] = f9;
        tempVertices[11] = f10;
        tempVertices[12] = this.colorPacked;
        tempVertices[13] = f7;
        tempVertices[14] = f8;
        if (this.mesh.getNumIndices() > 0) {
            tempVertices[15] = f9;
            tempVertices[16] = f2;
            tempVertices[17] = this.colorPacked;
            tempVertices[18] = f7;
            tempVertices[19] = f6;
            add(texture, tempVertices, 0, 20);
            return;
        }
        tempVertices[15] = f9;
        tempVertices[16] = f10;
        tempVertices[17] = this.colorPacked;
        tempVertices[18] = f7;
        tempVertices[19] = f8;
        tempVertices[20] = f9;
        tempVertices[21] = f2;
        tempVertices[22] = this.colorPacked;
        tempVertices[23] = f7;
        tempVertices[24] = f6;
        tempVertices[25] = f;
        tempVertices[26] = f2;
        tempVertices[27] = this.colorPacked;
        tempVertices[28] = f5;
        tempVertices[29] = f6;
        add(texture, tempVertices, 0, 30);
    }

    public void add(Texture texture, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int i, int i2, int i3, int i4, boolean z, boolean z2) {
        float f10;
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
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
        float width = 1.0f / texture.getWidth();
        float height = 1.0f / texture.getHeight();
        float f40 = i * width;
        float f41 = (i2 + i4) * height;
        float f42 = (i + i3) * width;
        float f43 = i2 * height;
        if (z) {
            f40 = f42;
            f42 = f40;
        }
        if (z2) {
            f41 = f43;
            f43 = f41;
        }
        tempVertices[0] = f32;
        tempVertices[1] = f33;
        tempVertices[2] = this.colorPacked;
        tempVertices[3] = f40;
        tempVertices[4] = f41;
        tempVertices[5] = f34;
        tempVertices[6] = f35;
        tempVertices[7] = this.colorPacked;
        tempVertices[8] = f40;
        tempVertices[9] = f43;
        tempVertices[10] = f36;
        tempVertices[11] = f37;
        tempVertices[12] = this.colorPacked;
        tempVertices[13] = f42;
        tempVertices[14] = f43;
        if (this.mesh.getNumIndices() > 0) {
            tempVertices[15] = f38;
            tempVertices[16] = f39;
            tempVertices[17] = this.colorPacked;
            tempVertices[18] = f42;
            tempVertices[19] = f41;
            add(texture, tempVertices, 0, 20);
            return;
        }
        tempVertices[15] = f36;
        tempVertices[16] = f37;
        tempVertices[17] = this.colorPacked;
        tempVertices[18] = f42;
        tempVertices[19] = f43;
        tempVertices[20] = f38;
        tempVertices[21] = f39;
        tempVertices[22] = this.colorPacked;
        tempVertices[23] = f42;
        tempVertices[24] = f41;
        tempVertices[25] = f32;
        tempVertices[26] = f33;
        tempVertices[27] = this.colorPacked;
        tempVertices[28] = f40;
        tempVertices[29] = f41;
        add(texture, tempVertices, 0, 30);
    }

    public void add(TextureRegion textureRegion, float f, float f2) {
        add(textureRegion, f, f2, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
    }

    public void add(TextureRegion textureRegion, float f, float f2, float f3, float f4) {
        float f5 = f + f3;
        float f6 = f2 + f4;
        float f7 = textureRegion.u;
        float f8 = textureRegion.v2;
        float f9 = textureRegion.u2;
        float f10 = textureRegion.v;
        tempVertices[0] = f;
        tempVertices[1] = f2;
        tempVertices[2] = this.colorPacked;
        tempVertices[3] = f7;
        tempVertices[4] = f8;
        tempVertices[5] = f;
        tempVertices[6] = f6;
        tempVertices[7] = this.colorPacked;
        tempVertices[8] = f7;
        tempVertices[9] = f10;
        tempVertices[10] = f5;
        tempVertices[11] = f6;
        tempVertices[12] = this.colorPacked;
        tempVertices[13] = f9;
        tempVertices[14] = f10;
        if (this.mesh.getNumIndices() > 0) {
            tempVertices[15] = f5;
            tempVertices[16] = f2;
            tempVertices[17] = this.colorPacked;
            tempVertices[18] = f9;
            tempVertices[19] = f8;
            add(textureRegion.texture, tempVertices, 0, 20);
            return;
        }
        tempVertices[15] = f5;
        tempVertices[16] = f6;
        tempVertices[17] = this.colorPacked;
        tempVertices[18] = f9;
        tempVertices[19] = f10;
        tempVertices[20] = f5;
        tempVertices[21] = f2;
        tempVertices[22] = this.colorPacked;
        tempVertices[23] = f9;
        tempVertices[24] = f8;
        tempVertices[25] = f;
        tempVertices[26] = f2;
        tempVertices[27] = this.colorPacked;
        tempVertices[28] = f7;
        tempVertices[29] = f8;
        add(textureRegion.texture, tempVertices, 0, 30);
    }

    public void add(TextureRegion textureRegion, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        float f10;
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
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
        tempVertices[0] = f32;
        tempVertices[1] = f33;
        tempVertices[2] = this.colorPacked;
        tempVertices[3] = f40;
        tempVertices[4] = f41;
        tempVertices[5] = f34;
        tempVertices[6] = f35;
        tempVertices[7] = this.colorPacked;
        tempVertices[8] = f40;
        tempVertices[9] = f43;
        tempVertices[10] = f36;
        tempVertices[11] = f37;
        tempVertices[12] = this.colorPacked;
        tempVertices[13] = f42;
        tempVertices[14] = f43;
        if (this.mesh.getNumIndices() > 0) {
            tempVertices[15] = f38;
            tempVertices[16] = f39;
            tempVertices[17] = this.colorPacked;
            tempVertices[18] = f42;
            tempVertices[19] = f41;
            add(textureRegion.texture, tempVertices, 0, 20);
            return;
        }
        tempVertices[15] = f36;
        tempVertices[16] = f37;
        tempVertices[17] = this.colorPacked;
        tempVertices[18] = f42;
        tempVertices[19] = f43;
        tempVertices[20] = f38;
        tempVertices[21] = f39;
        tempVertices[22] = this.colorPacked;
        tempVertices[23] = f42;
        tempVertices[24] = f41;
        tempVertices[25] = f32;
        tempVertices[26] = f33;
        tempVertices[27] = this.colorPacked;
        tempVertices[28] = f40;
        tempVertices[29] = f41;
        add(textureRegion.texture, tempVertices, 0, 30);
    }

    public void add(Sprite sprite) {
        if (this.mesh.getNumIndices() > 0) {
            add(sprite.getTexture(), sprite.getVertices(), 0, 20);
            return;
        }
        float[] vertices = sprite.getVertices();
        System.arraycopy(vertices, 0, tempVertices, 0, 15);
        System.arraycopy(vertices, 10, tempVertices, 15, 5);
        System.arraycopy(vertices, 15, tempVertices, 20, 5);
        System.arraycopy(vertices, 0, tempVertices, 25, 5);
        add(sprite.getTexture(), tempVertices, 0, 30);
    }

    public void begin() {
        if (this.drawing) {
            throw new IllegalStateException("end must be called before begin.");
        }
        if (this.currentCache != null) {
            throw new IllegalStateException("endCache must be called before begin");
        }
        this.renderCalls = 0;
        this.combinedMatrix.set(this.projectionMatrix).mul(this.transformMatrix);
        Gdx.gl20.glDepthMask(false);
        if (this.customShader != null) {
            this.customShader.bind();
            this.customShader.setUniformMatrix("u_proj", this.projectionMatrix);
            this.customShader.setUniformMatrix("u_trans", this.transformMatrix);
            this.customShader.setUniformMatrix("u_projTrans", this.combinedMatrix);
            this.customShader.setUniformi("u_texture", 0);
            this.mesh.bind(this.customShader);
        } else {
            this.shader.bind();
            this.shader.setUniformMatrix("u_projectionViewMatrix", this.combinedMatrix);
            this.shader.setUniformi("u_texture", 0);
            this.mesh.bind(this.shader);
        }
        this.drawing = true;
    }

    public void end() {
        if (!this.drawing) {
            throw new IllegalStateException("begin must be called before end.");
        }
        this.drawing = false;
        Gdx.gl20.glDepthMask(true);
        if (this.customShader != null) {
            this.mesh.unbind(this.customShader);
        } else {
            this.mesh.unbind(this.shader);
        }
    }

    public void draw(int i) {
        if (!this.drawing) {
            throw new IllegalStateException("SpriteCache.begin must be called before draw.");
        }
        Cache cache = this.caches.get(i);
        int i2 = (cache.offset / ((this.mesh.getNumIndices() > 0 ? 4 : 6) * 5)) * 6;
        Texture[] textureArr = cache.textures;
        int[] iArr = cache.counts;
        int i3 = cache.textureCount;
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = iArr[i4];
            textureArr[i4].bind();
            if (this.customShader != null) {
                this.mesh.render(this.customShader, 4, i2, i5);
            } else {
                this.mesh.render(this.shader, 4, i2, i5);
            }
            i2 += i5;
        }
        this.renderCalls += i3;
        this.totalRenderCalls += i3;
    }

    public void draw(int i, int i2, int i3) {
        if (!this.drawing) {
            throw new IllegalStateException("SpriteCache.begin must be called before draw.");
        }
        Cache cache = this.caches.get(i);
        int i4 = ((cache.offset / ((this.mesh.getNumIndices() > 0 ? 4 : 6) * 5)) * 6) + (i2 * 6);
        int i5 = i3 * 6;
        Texture[] textureArr = cache.textures;
        int[] iArr = cache.counts;
        int i6 = cache.textureCount;
        int i7 = 0;
        while (i7 < i6) {
            textureArr[i7].bind();
            int i8 = iArr[i7];
            int i9 = i8;
            if (i8 > i5) {
                i7 = i6;
                i9 = i5;
            } else {
                i5 -= i9;
            }
            if (this.customShader != null) {
                this.mesh.render(this.customShader, 4, i4, i9);
            } else {
                this.mesh.render(this.shader, 4, i4, i9);
            }
            i4 += i9;
            i7++;
        }
        this.renderCalls += cache.textureCount;
        this.totalRenderCalls += i6;
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.mesh.dispose();
        if (this.shader != null) {
            this.shader.dispose();
        }
    }

    public Matrix4 getProjectionMatrix() {
        return this.projectionMatrix;
    }

    public void setProjectionMatrix(Matrix4 matrix4) {
        if (this.drawing) {
            throw new IllegalStateException("Can't set the matrix within begin/end.");
        }
        this.projectionMatrix.set(matrix4);
    }

    public Matrix4 getTransformMatrix() {
        return this.transformMatrix;
    }

    public void setTransformMatrix(Matrix4 matrix4) {
        if (this.drawing) {
            throw new IllegalStateException("Can't set the matrix within begin/end.");
        }
        this.transformMatrix.set(matrix4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/SpriteCache$Cache.class */
    public static class Cache {
        final int id;
        final int offset;
        int maxCount;
        int textureCount;
        Texture[] textures;
        int[] counts;

        public Cache(int i, int i2) {
            this.id = i;
            this.offset = i2;
        }
    }

    static ShaderProgram createDefaultShader() {
        ShaderProgram shaderProgram = new ShaderProgram("attribute vec4 a_position;\nattribute vec4 a_color;\nattribute vec2 a_texCoord0;\nuniform mat4 u_projectionViewMatrix;\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\n\nvoid main()\n{\n   v_color = a_color;\n   v_color.a = v_color.a * (255.0/254.0);\n   v_texCoords = a_texCoord0;\n   gl_Position =  u_projectionViewMatrix * a_position;\n}\n", "#ifdef GL_ES\nprecision mediump float;\n#endif\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\nuniform sampler2D u_texture;\nvoid main()\n{\n  gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n}");
        if (!shaderProgram.isCompiled()) {
            throw new IllegalArgumentException("Error compiling shader: " + shaderProgram.getLog());
        }
        return shaderProgram;
    }

    public void setShader(ShaderProgram shaderProgram) {
        this.customShader = shaderProgram;
    }

    public ShaderProgram getCustomShader() {
        return this.customShader;
    }

    public boolean isDrawing() {
        return this.drawing;
    }
}
