package com.prineside.tdi2.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.NumberUtils;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.RenderingManager;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/SpriteCacheExtended.class */
public final class SpriteCacheExtended extends SpriteCache implements Batch {
    public final boolean useIndices;
    public final int vertexPerAdd;
    public final int size;
    public final int maxAdds;
    public int addCount;
    public int lastCacheId;

    /* renamed from: a, reason: collision with root package name */
    private Batch f3906a;

    /* renamed from: b, reason: collision with root package name */
    private float f3907b;
    private static final float[] c = new float[20];

    public SpriteCacheExtended(String str, int i, ShaderProgram shaderProgram, boolean z) {
        super(i, shaderProgram == null ? createDefaultShader() : shaderProgram, z);
        this.lastCacheId = Integer.MIN_VALUE;
        this.f3907b = 1.0f;
        this.useIndices = z;
        this.vertexPerAdd = z ? 4 : 6;
        this.size = i;
        this.maxAdds = (int) (i * 0.67f);
    }

    public final void setOutputToBatch(Batch batch) {
        if (!this.useIndices) {
            throw new IllegalStateException("Can not be used without indices");
        }
        this.f3906a = batch;
    }

    public final void setAlphaMultiplier(float f) {
        this.f3907b = f;
    }

    public static ShaderProgram createDefaultShader() {
        return RenderingManager.createShader("SpriteCacheExtended");
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteCache
    public final void add(Texture texture, float[] fArr, int i, int i2) {
        if (this.f3906a == null) {
            if (i2 % (this.vertexPerAdd * 5) != 0) {
                throw new IllegalArgumentException("vertices array must be %" + (this.vertexPerAdd * 5) + " floats in length, " + i2 + " given. Check if useIndices match");
            }
            try {
                super.add(texture, fArr, i, i2);
                this.addCount++;
                return;
            } catch (Exception e) {
                throw new IllegalStateException("failed to add vertices to cache, ac " + this.addCount + " size " + this.size + " vpa " + this.vertexPerAdd + " ma " + this.maxAdds, e);
            }
        }
        if (i2 % 20 != 0) {
            throw new IllegalArgumentException("this cache uses indices and requires float[%20] as an input, float[" + i2 + "] given");
        }
        if (this.f3907b < 1.0f) {
            int round = MathUtils.round(MathUtils.clamp(this.f3907b, 0.0f, 1.0f) * 255.0f) << 24;
            for (int i3 = i; i3 < i2; i3 += 5) {
                fArr[i3 + 2] = NumberUtils.intToFloatColor((NumberUtils.floatToIntColor(fArr[i3 + 2]) & 16777215) | round);
            }
        }
        this.f3906a.draw(texture, fArr, i, i2);
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteCache
    public final void beginCache() {
        super.beginCache();
        this.addCount = 0;
    }

    public final boolean isFull() {
        return this.addCount >= this.maxAdds;
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteCache
    public final int endCache() {
        int endCache = super.endCache();
        this.lastCacheId = endCache;
        return endCache;
    }

    @Override // com.badlogic.gdx.graphics.g2d.SpriteCache
    public final void clear() {
        super.clear();
        this.lastCacheId = Integer.MIN_VALUE;
        this.addCount = 0;
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(Texture texture, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int i, int i2, int i3, int i4, boolean z, boolean z2) {
        add(texture, f, f2, f3, f4, f5, f6, f7, f8, f9, i, i2, i3, i4, z, z2);
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(Texture texture, float f, float f2, float f3, float f4, int i, int i2, int i3, int i4, boolean z, boolean z2) {
        add(texture, f, f2, f3, f4, i, i2, i3, i4, z, z2);
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(Texture texture, float f, float f2, int i, int i2, int i3, int i4) {
        add(texture, f, f2, i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @Deprecated
    public final void draw(Texture texture, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        add(texture, f, f2, (int) f3, (int) f4, f5, f6, f7, f8, getPackedColor());
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(Texture texture, float f, float f2) {
        add(texture, f, f2);
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(Texture texture, float f, float f2, float f3, float f4) {
        add(texture, f, f2, f3, f4, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(Texture texture, float[] fArr, int i, int i2) {
        add(texture, fArr, i, i2);
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(TextureRegion textureRegion, float f, float f2) {
        add(textureRegion, f, f2);
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(TextureRegion textureRegion, float f, float f2, float f3, float f4) {
        add(textureRegion, f, f2, f3, f4);
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(TextureRegion textureRegion, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        add(textureRegion, f, f2, f3, f4, f5, f6, f7, f8, f9);
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(TextureRegion textureRegion, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, boolean z) {
        add(textureRegion, f, f2, f3, f4, f5, f6, f7, f8, f9 + (z ? 90.0f : -90.0f));
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(TextureRegion textureRegion, float f, float f2, Affine2 affine2) {
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
        float packedColor = getPackedColor();
        float[] fArr = c;
        fArr[0] = f3;
        fArr[1] = f4;
        fArr[2] = packedColor;
        fArr[3] = u;
        fArr[4] = v2;
        fArr[5] = f5;
        fArr[6] = f6;
        fArr[7] = packedColor;
        fArr[8] = u;
        fArr[9] = v;
        fArr[10] = f7;
        fArr[11] = f8;
        fArr[12] = packedColor;
        fArr[13] = u2;
        fArr[14] = v;
        fArr[15] = f9;
        fArr[16] = f10;
        fArr[17] = packedColor;
        fArr[18] = u2;
        fArr[19] = v2;
        add(textureRegion.getTexture(), fArr, 0, 20);
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final void flush() {
        if (this.f3906a != null) {
            this.f3906a.flush();
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final void disableBlending() {
        throw new IllegalStateException("SpriteCache does not allow to change blending");
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final void enableBlending() {
        throw new IllegalStateException("SpriteCache does not allow to change blending");
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final void setBlendFunction(int i, int i2) {
        throw new IllegalStateException("SpriteCache does not allow to change blending");
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final void setBlendFunctionSeparate(int i, int i2, int i3, int i4) {
        throw new IllegalStateException("SpriteCache does not allow to change blending");
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final int getBlendSrcFunc() {
        throw new IllegalStateException("SpriteCache does not allow to change blending");
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final int getBlendDstFunc() {
        throw new IllegalStateException("SpriteCache does not allow to change blending");
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final int getBlendSrcFuncAlpha() {
        throw new IllegalStateException("SpriteCache does not allow to change blending");
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final int getBlendDstFuncAlpha() {
        throw new IllegalStateException("SpriteCache does not allow to change blending");
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final ShaderProgram getShader() {
        return getShader();
    }

    @Override // com.badlogic.gdx.graphics.g2d.Batch
    public final boolean isBlendingEnabled() {
        throw new IllegalStateException("SpriteCache does not allow to change blending");
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/SpriteCacheExtended$CacheArray.class */
    public static final class CacheArray implements Disposable {

        /* renamed from: a, reason: collision with root package name */
        private final Array<SpriteCacheExtended> f3908a = new Array<>(true, 1, SpriteCacheExtended.class);
        public boolean dirty;

        /* renamed from: b, reason: collision with root package name */
        private final String f3909b;
        private final String c;
        private final int d;
        private final boolean e;
        private final ShaderProgram f;
        private int g;
        private static final Array<SpriteCacheExtended> h = new Array<>(true, 1, SpriteCacheExtended.class);

        public CacheArray(String str, int i, ShaderProgram shaderProgram, boolean z) {
            this.f3909b = str;
            this.c = "SC_" + str;
            this.d = i;
            this.e = z;
            this.f = shaderProgram;
            a();
        }

        public final int getSizePerCache() {
            return this.d;
        }

        public final boolean isUseIndices() {
            return this.e;
        }

        public final ShaderProgram getShaderProgram() {
            return this.f;
        }

        private void a(int i) {
            while (this.f3908a.size < i) {
                this.f3908a.add(new SpriteCacheExtended(this.f3909b + " SC" + this.f3908a.size, this.d, this.f, this.e));
                a();
            }
        }

        public final SpriteCacheExtended start() {
            for (int i = 0; i < this.f3908a.size; i++) {
                this.f3908a.items[i].clear();
            }
            this.g = 0;
            a(this.g + 1);
            SpriteCacheExtended spriteCacheExtended = this.f3908a.items[this.g];
            spriteCacheExtended.beginCache();
            a();
            return spriteCacheExtended;
        }

        public final void end() {
            this.f3908a.items[this.g].endCache();
            a();
        }

        public final SpriteCacheExtended swapCachesIfFull() {
            SpriteCacheExtended spriteCacheExtended = this.f3908a.items[this.g];
            if (spriteCacheExtended.isFull()) {
                spriteCacheExtended.endCache();
                this.g++;
                a(this.g + 1);
                SpriteCacheExtended spriteCacheExtended2 = this.f3908a.items[this.g];
                spriteCacheExtended2.setColor(spriteCacheExtended.getColor());
                spriteCacheExtended2.beginCache();
                a();
                return spriteCacheExtended2;
            }
            return spriteCacheExtended;
        }

        public final Array<SpriteCacheExtended> getPreparedCaches() {
            a();
            h.clear();
            for (int i = 0; i < this.f3908a.size; i++) {
                SpriteCacheExtended spriteCacheExtended = this.f3908a.items[i];
                if (spriteCacheExtended.lastCacheId != Integer.MIN_VALUE && spriteCacheExtended.addCount != 0) {
                    h.add(spriteCacheExtended);
                }
            }
            return h;
        }

        private void a() {
            if (Game.i.debugManager.isEnabled()) {
                StringBuilder registerValue = Game.i.debugManager.registerValue(this.c);
                registerValue.append("s ").append(this.d).append(" ci ").append(this.g);
                for (int i = 0; i < this.f3908a.size; i++) {
                    SpriteCacheExtended spriteCacheExtended = this.f3908a.items[i];
                    registerValue.append(" / #").append(i).append(": ").append(spriteCacheExtended.lastCacheId == Integer.MIN_VALUE ? "N" : Integer.valueOf(spriteCacheExtended.lastCacheId)).append(",").append(spriteCacheExtended.addCount).append(",").append(spriteCacheExtended.totalRenderCalls);
                }
            }
        }

        @Override // com.badlogic.gdx.utils.Disposable
        public final void dispose() {
            for (int i = 0; i < this.f3908a.size; i++) {
                this.f3908a.items[i].dispose();
            }
            this.f3908a.clear();
            if (Game.i.debugManager.isEnabled()) {
                Game.i.debugManager.unregisterValue(this.c);
            }
        }
    }
}
