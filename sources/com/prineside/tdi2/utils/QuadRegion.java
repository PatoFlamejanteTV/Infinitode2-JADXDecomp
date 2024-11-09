package com.prineside.tdi2.utils;

import com.a.a.c.m;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.NumberUtils;
import com.badlogic.gdx.utils.StringBuilder;
import com.google.common.base.Preconditions;
import com.prineside.kryo.FixedInput;
import com.prineside.kryo.FixedOutput;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.scene2d.utils.TransformDrawable;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import java.util.Iterator;
import java.util.Objects;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/QuadRegion.class */
public final class QuadRegion extends AbstractDrawable implements TransformDrawable {
    public static final byte NINE_PATH_NONE = 0;
    public static final byte COLOR_MODE_INHERIT = 0;
    public static final byte COLOR_MODE_STATIC_RGB = 1;
    public static final byte COLOR_MODE_STATIC_RGBA = 2;
    public String regionName;
    public String quadName;
    private final float[] f;
    private CornerColors g;
    private float h;
    private float i;
    private float j;
    private float k;
    private TextureRegion l;
    private byte m;
    private byte n;
    private float o;
    private float p;

    /* renamed from: b, reason: collision with root package name */
    private static final TLog f3894b = TLog.forClass(QuadRegion.class);
    private static final int c = Color.WHITE.toIntBits();
    private static final float[] d = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    private static final float[] e = new float[32];

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/QuadRegion$CornerColors.class */
    public interface CornerColors {
        boolean sameAs(CornerColors cornerColors);

        Color get(int i);

        boolean isSingleColor();

        CornerColors cpyCornerColors();

        void toJson5String(StringBuilder stringBuilder);

        void toJson(Json json);

        CornerColors simplifyIfNeeded();
    }

    private QuadRegion() {
        this.f = new float[8];
        this.g = AllWhiteCornerColors.INSTANCE;
        this.m = (byte) 0;
    }

    public QuadRegion(QuadRegion quadRegion) {
        this.f = new float[8];
        this.g = AllWhiteCornerColors.INSTANCE;
        this.m = (byte) 0;
        set(quadRegion);
    }

    public QuadRegion(float[] fArr, float f, float f2) {
        this.f = new float[8];
        this.g = AllWhiteCornerColors.INSTANCE;
        this.m = (byte) 0;
        this.h = f;
        this.i = f2;
        setRegionName(null);
        setCornerPositions(fArr);
    }

    public QuadRegion(ResourcePack.AtlasTextureRegion atlasTextureRegion, float f, float f2, float f3, float f4) {
        this.f = new float[8];
        this.g = AllWhiteCornerColors.INSTANCE;
        this.m = (byte) 0;
        if (Game.i.assetManager != null) {
            Preconditions.checkNotNull(atlasTextureRegion, "region can not be null");
        }
        this.l = atlasTextureRegion;
        this.regionName = atlasTextureRegion.name;
        this.h = f;
        this.i = f2;
        setCornerPositions(0.0f, 0.0f, 0.0f, f4, f3, f4, f3, 0.0f);
    }

    public QuadRegion(ResourcePack.AtlasTextureRegion atlasTextureRegion, float f, float f2, float f3, float f4, Color color) {
        this(atlasTextureRegion, f, f2, f3, f4);
        setSameCornerColors(color);
    }

    public final boolean sameAs(QuadRegion quadRegion) {
        if (quadRegion == this) {
            return true;
        }
        if (!Objects.equals(this.regionName, quadRegion.regionName) || !Objects.equals(this.quadName, quadRegion.quadName)) {
            return false;
        }
        for (int i = 0; i < this.f.length; i++) {
            if (this.f[i] != quadRegion.f[i]) {
                return false;
            }
        }
        return this.g.sameAs(quadRegion.g) && this.h == quadRegion.h && this.i == quadRegion.i && this.j == quadRegion.j && this.k == quadRegion.k && getColorMode() == quadRegion.getColorMode() && this.m == quadRegion.m && isVisible() == quadRegion.isVisible();
    }

    @Null
    public final String getRegionName() {
        return this.regionName;
    }

    @Null
    public final String getQuadName() {
        return this.quadName;
    }

    public final boolean isDebugging() {
        return (this.n & 8) != 0;
    }

    public final boolean isSingleColor() {
        return this.g.isSingleColor();
    }

    public final boolean isVisible() {
        return (this.n & 4) == 0;
    }

    public final void setVisible(boolean z) {
        if (z) {
            this.n = (byte) (this.n & (-5));
        } else {
            this.n = (byte) (this.n | 4);
        }
    }

    public final void setDebugging(boolean z) {
        if (!z) {
            this.n = (byte) (this.n & (-9));
        } else {
            this.n = (byte) (this.n | 8);
        }
    }

    public final byte getNinePathRegion() {
        return this.m;
    }

    public final void setNinePathRegion(byte b2) {
        this.m = b2;
    }

    public final byte getColorMode() {
        return (byte) (this.n & 3);
    }

    public final void setColorMode(byte b2) {
        this.n = (byte) (this.n & 252);
        this.n = (byte) (this.n | (b2 & 3));
    }

    public final void setRegionName(@Null String str) {
        if (str == null || str.length() == 0) {
            str = AssetManager.BLANK_REGION_NAME;
        }
        this.regionName = str;
        if (Game.i.assetManager != null) {
            this.l = Game.i.assetManager.getTextureRegionSetThrowing(str, false);
            if (this.l == null) {
                throw new IllegalArgumentException("failed to set region name - specified region provider does not have 'blank' region");
            }
        }
    }

    public final void setRegionNameFromProvider(String str, AssetProvider<TextureRegion> assetProvider) {
        this.regionName = str;
        if (str == null || str.length() == 0) {
            this.l = assetProvider.get(AssetManager.BLANK_REGION_NAME);
        } else {
            this.l = assetProvider.get(str);
            if (this.l == null) {
                f3894b.i("region with name '" + str + "' not found in the specified regionProvider, falling back to blank", new Object[0]);
                this.l = assetProvider.get(AssetManager.BLANK_REGION_NAME);
            }
        }
        if (this.l == null) {
            throw new IllegalArgumentException("failed to set region name - specified region provider does not have 'blank' region");
        }
    }

    public final void setPosition(float f, float f2) {
        this.h = f;
        this.i = f2;
    }

    public final void set(QuadRegion quadRegion) {
        this.h = quadRegion.h;
        this.i = quadRegion.i;
        this.j = quadRegion.j;
        this.k = quadRegion.k;
        System.arraycopy(quadRegion.f, 0, this.f, 0, 8);
        this.g = quadRegion.g.cpyCornerColors();
        if (Game.i.assetManager != null) {
            Preconditions.checkNotNull(quadRegion.l);
        }
        this.l = quadRegion.l;
        this.regionName = quadRegion.regionName;
        this.quadName = quadRegion.quadName;
        this.m = quadRegion.m;
        this.n = quadRegion.n;
        this.o = quadRegion.o;
        this.p = quadRegion.p;
        if (quadRegion.f3812a != null) {
            a();
            System.arraycopy(quadRegion.f3812a, 0, this.f3812a, 0, this.f3812a.length);
        }
    }

    public final float getX() {
        return this.h;
    }

    public final float getY() {
        return this.i;
    }

    public final float getWidth() {
        return this.j;
    }

    public final float getHeight() {
        return this.k;
    }

    public final float[] getCornerPositions() {
        return this.f;
    }

    public final CornerColors getCornerColors() {
        return this.g;
    }

    public final void setCornerColorsSeparate(Color color, Color color2, Color color3, Color color4) {
        int intBits = color.toIntBits();
        int intBits2 = color2.toIntBits();
        int intBits3 = color3.toIntBits();
        int intBits4 = color4.toIntBits();
        if (intBits == c && intBits2 == c && intBits3 == c && intBits4 == c) {
            this.g = AllWhiteCornerColors.INSTANCE;
            return;
        }
        if (intBits == intBits2 && intBits == intBits3 && intBits == intBits4) {
            if (this.g instanceof SingleCornerColor) {
                ((SingleCornerColor) this.g).set(color);
                return;
            } else {
                this.g = new SingleCornerColor(color);
                return;
            }
        }
        if (this.g instanceof FourCornerColors) {
            FourCornerColors fourCornerColors = (FourCornerColors) this.g;
            fourCornerColors.f3895a.set(color);
            fourCornerColors.f3896b.set(color2);
            fourCornerColors.c.set(color3);
            fourCornerColors.d.set(color4);
            return;
        }
        this.g = new FourCornerColors(color, color2, color3, color4);
    }

    public final void setSameCornerColors(Color color) {
        if (color.toIntBits() == c) {
            this.g = AllWhiteCornerColors.INSTANCE;
        } else if (this.g instanceof SingleCornerColor) {
            ((SingleCornerColor) this.g).set(color);
        } else {
            this.g = new SingleCornerColor(color);
        }
    }

    public final void multiplyCornerColors(Color color) {
        if (color.toIntBits() == c) {
            return;
        }
        if (this.g == AllWhiteCornerColors.INSTANCE) {
            this.g = new SingleCornerColor(color);
            return;
        }
        if (this.g instanceof SingleCornerColor) {
            ((SingleCornerColor) this.g).mul(color);
            this.g = this.g.simplifyIfNeeded();
            return;
        }
        FourCornerColors fourCornerColors = (FourCornerColors) this.g;
        fourCornerColors.f3895a.mul(color);
        fourCornerColors.f3896b.mul(color);
        fourCornerColors.c.mul(color);
        fourCornerColors.d.mul(color);
        this.g = fourCornerColors.simplifyIfNeeded();
    }

    public final void setCornerColorsObject(CornerColors cornerColors) {
        this.g = cornerColors;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public final void setCornerPositions(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        this.f[0] = f;
        this.f[1] = f2;
        this.f[2] = f3;
        this.f[3] = f4;
        this.f[4] = f5;
        this.f[5] = f6;
        this.f[6] = f7;
        this.f[7] = f8;
        updateSize();
    }

    @IgnoreMethodOverloadLuaDefWarning
    public final void setCornerPositions(float[] fArr) {
        if (fArr != this.f) {
            if (fArr.length != 8) {
                throw new IllegalArgumentException("Must contain 8 floats");
            }
            setCornerPositions(fArr[0], fArr[1], fArr[2], fArr[3], fArr[4], fArr[5], fArr[6], fArr[7]);
            return;
        }
        updateSize();
    }

    public final void shiftPositionToMatchCorners() {
        float min = Math.min(this.f[0], Math.min(this.f[2], Math.min(this.f[4], this.f[6])));
        float min2 = Math.min(this.f[1], Math.min(this.f[3], Math.min(this.f[5], this.f[7])));
        this.h += min;
        this.i += min2;
        for (int i = 0; i < 4; i++) {
            float[] fArr = this.f;
            int i2 = i << 1;
            fArr[i2] = fArr[i2] - min;
            float[] fArr2 = this.f;
            int i3 = (i << 1) + 1;
            fArr2[i3] = fArr2[i3] - min2;
        }
        updateSize();
    }

    public final void translate(float f, float f2) {
        for (int i = 0; i < 4; i++) {
            float[] fArr = this.f;
            int i2 = i << 1;
            fArr[i2] = fArr[i2] + f;
            float[] fArr2 = this.f;
            int i3 = (i << 1) + 1;
            fArr2[i3] = fArr2[i3] + f2;
        }
    }

    public final void setTextureRegion(ResourcePack.AtlasTextureRegion atlasTextureRegion) {
        Preconditions.checkNotNull(atlasTextureRegion);
        this.l = atlasTextureRegion;
        this.regionName = atlasTextureRegion.name;
    }

    public final void updateSize() {
        this.j = Math.max(this.f[0], Math.max(this.f[2], Math.max(this.f[4], this.f[6])));
        this.k = Math.max(this.f[1], Math.max(this.f[3], Math.max(this.f[5], this.f[7])));
        setMinWidth(this.j);
        setMinHeight(this.k);
        this.o = 1.0f / Math.max(1.0E-4f, this.j);
        this.p = 1.0f / Math.max(1.0E-4f, this.k);
    }

    public final TextureRegion getTextureRegion() {
        return this.l != null ? this.l : Game.i.assetManager.getTextureRegion(this.regionName);
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(Batch batch, float f, float f2, float f3, float f4) {
        if (isDebugging()) {
            f3894b.i("draw (Batch, float, float, float, float) " + this.regionName, new Object[0]);
        }
        a(f, f2, f3, f4, batch.getColor());
        if (isDebugging()) {
            f3894b.i("draw vertices:", new Object[0]);
            for (int i = 0; i < 4; i++) {
                f3894b.i("- x: " + d[i * 5], new Object[0]);
                f3894b.i("- y: " + d[(i * 5) + 1], new Object[0]);
                f3894b.i("- color: " + Integer.toHexString(NumberUtils.floatToIntBits(d[(i * 5) + 2])), new Object[0]);
            }
        }
        batch.draw(getTextureRegion().getTexture(), d, 0, 20);
    }

    public final void drawToCache(SpriteCache spriteCache, float f, float f2, float f3, float f4) {
        a(f, f2, f3, f4, spriteCache.getColor());
        System.arraycopy(d, 0, e, 0, 15);
        System.arraycopy(d, 10, e, 15, 5);
        System.arraycopy(d, 15, e, 20, 5);
        System.arraycopy(d, 0, e, 25, 5);
        spriteCache.add(getTextureRegion().getTexture(), e, 0, 30);
    }

    @Override // com.prineside.tdi2.scene2d.utils.TransformDrawable
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(Batch batch, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        a(f, f2, f3, f4, f5, f6, f7, f8, f9, batch.getColor());
        batch.draw(getTextureRegion().getTexture(), d, 0, 20);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @IgnoreMethodOverloadLuaDefWarning
    public final void a(Batch batch, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        a(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, batch.getColor());
        batch.draw(getTextureRegion().getTexture(), d, 0, 20);
    }

    public final void rotateAround(float f, float f2, float f3) {
        a(0.0f, 0.0f, f - this.h, f2 - this.i, this.j, this.k, 1.0f, 1.0f, f3, Color.WHITE);
        setCornerPositions(d[0], d[1], d[5], d[6], d[10], d[11], d[15], d[16]);
        shiftPositionToMatchCorners();
    }

    private void a(Color color) {
        if (isDebugging()) {
            f3894b.i("computeVertexColor", new Object[0]);
        }
        float f = color.r;
        float f2 = color.g;
        float f3 = color.f888b;
        float f4 = color.f889a;
        byte colorMode = getColorMode();
        if (colorMode == 1) {
            f3 = 1.0f;
            f2 = 1.0f;
            f = 1.0f;
        } else if (colorMode == 2) {
            f4 = 1.0f;
            f3 = 1.0f;
            f2 = 1.0f;
            f = 1.0f;
        }
        if (isSingleColor()) {
            Color color2 = this.g.get(0);
            if (isDebugging()) {
                f3894b.i("computeVertexColor - single color " + Integer.toHexString(new Color(color2.r * f, color2.g * f2, color2.f888b * f3, color2.f889a * f4).toIntBits()), new Object[0]);
            }
            float floatBits = Color.toFloatBits(color2.r * f, color2.g * f2, color2.f888b * f3, color2.f889a * f4);
            for (int i = 0; i < 4; i++) {
                d[(i * 5) + 2] = floatBits;
            }
            return;
        }
        for (int i2 = 0; i2 < 4; i2++) {
            Color color3 = this.g.get(i2);
            d[(i2 * 5) + 2] = Color.toFloatBits(color3.r * f, color3.g * f2, color3.f888b * f3, color3.f889a * f4);
        }
    }

    private void a(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, Color color) {
        a(f, f2, f3, f4, f5, f6, f7, f8, MathUtils.cosDeg(f9), MathUtils.sinDeg(f9), color);
    }

    private void a(float f, float f2, float f3, float f4, Color color) {
        if (isDebugging()) {
            f3894b.i("computeVerticesSimple", new Object[0]);
        }
        float f5 = this.o;
        float f6 = this.p;
        float f7 = f3 * this.f[0] * f5;
        float f8 = f4 * this.f[1] * f6;
        float f9 = f3 * this.f[2] * f5;
        float f10 = f4 - (f4 * (1.0f - (this.f[3] * f6)));
        float f11 = f3 - (f3 * (1.0f - (this.f[4] * f5)));
        float f12 = f4 - (f4 * (1.0f - (this.f[5] * f6)));
        float f13 = f3 - (f3 * (1.0f - (this.f[6] * f5)));
        float f14 = f4 * this.f[7] * f6;
        TextureRegion textureRegion = getTextureRegion();
        float u = textureRegion.getU();
        float v = textureRegion.getV();
        float u2 = textureRegion.getU2();
        float v2 = textureRegion.getV2();
        d[0] = f7 + f;
        d[1] = f8 + f2;
        d[3] = u;
        d[4] = v2;
        d[5] = f9 + f;
        d[6] = f10 + f2;
        d[8] = u;
        d[9] = v;
        d[10] = f11 + f;
        d[11] = f12 + f2;
        d[13] = u2;
        d[14] = v;
        d[15] = f13 + f;
        d[16] = f14 + f2;
        d[18] = u2;
        d[19] = v2;
        a(color);
    }

    private void a(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, Color color) {
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
        float f18;
        float f19 = -f3;
        float f20 = -f4;
        float f21 = f5 - f3;
        float f22 = f6 - f4;
        if (f7 != 1.0f || f8 != 1.0f) {
            f19 *= f7;
            f20 *= f8;
            f21 *= f7;
            f22 *= f8;
        }
        float f23 = this.o;
        float f24 = this.p;
        float f25 = f19 + (f5 * this.f[0] * f23 * f7);
        float f26 = f20 + (f6 * this.f[1] * f24 * f8);
        float f27 = f19 + (f5 * this.f[2] * f23 * f7);
        float f28 = f22 - ((f6 * (1.0f - (this.f[3] * f24))) * f8);
        float f29 = f21 - ((f5 * (1.0f - (this.f[4] * f23))) * f7);
        float f30 = f22 - ((f6 * (1.0f - (this.f[5] * f24))) * f8);
        float f31 = f21 - ((f5 * (1.0f - (this.f[6] * f23))) * f7);
        float f32 = f20 + (f6 * this.f[7] * f24 * f8);
        if (f9 != 1.0f || f10 != 0.0f) {
            f11 = (f9 * f25) - (f10 * f26);
            f12 = (f10 * f25) + (f9 * f26);
            f13 = (f9 * f27) - (f10 * f28);
            f14 = (f10 * f27) + (f9 * f28);
            f15 = (f9 * f29) - (f10 * f30);
            f16 = (f10 * f29) + (f9 * f30);
            f17 = (f9 * f31) - (f10 * f32);
            f18 = (f10 * f31) + (f9 * f32);
        } else {
            f11 = f25;
            f12 = f26;
            f13 = f27;
            f14 = f28;
            f15 = f29;
            f16 = f30;
            f17 = f31;
            f18 = f32;
        }
        float f33 = f + f3;
        float f34 = f2 + f4;
        float f35 = f11 + f33;
        float f36 = f12 + f34;
        float f37 = f13 + f33;
        float f38 = f14 + f34;
        float f39 = f15 + f33;
        float f40 = f16 + f34;
        float f41 = f17 + f33;
        float f42 = f18 + f34;
        TextureRegion textureRegion = getTextureRegion();
        float u = textureRegion.getU();
        float v = textureRegion.getV();
        float u2 = textureRegion.getU2();
        float v2 = textureRegion.getV2();
        d[0] = f35;
        d[1] = f36;
        d[3] = u;
        d[4] = v2;
        d[5] = f37;
        d[6] = f38;
        d[8] = u;
        d[9] = v;
        d[10] = f39;
        d[11] = f40;
        d[13] = u2;
        d[14] = v;
        d[15] = f41;
        d[16] = f42;
        d[18] = u2;
        d[19] = v2;
        a(color);
    }

    public final void drawDebug(ShapeRenderer shapeRenderer, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        a(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, shapeRenderer.getColor());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        Gdx.gl.glEnable(3042);
        shapeRenderer.setColor(0.0f, 1.0f, 0.0f, 0.5f);
        shapeRenderer.line(d[0], d[1], d[5], d[6]);
        shapeRenderer.line(d[5], d[6], d[10], d[11]);
        shapeRenderer.line(d[10], d[11], d[15], d[16]);
        shapeRenderer.line(d[15], d[16], d[0], d[1]);
        shapeRenderer.end();
    }

    public static QuadRegion fromJson(m mVar, AssetProvider<TextureRegion> assetProvider) {
        QuadRegion quadRegion = new QuadRegion();
        quadRegion.setRegionNameFromProvider(null, assetProvider);
        if (mVar.b()) {
            int i = 0;
            m a2 = mVar.a(0);
            if (a2.e() || a2.f()) {
                if (a2.e()) {
                    quadRegion.setRegionNameFromProvider(mVar.a(0).i(), assetProvider);
                }
                i = 0 + 1;
            }
            m a3 = mVar.a(i);
            JsonAssertUtils.checkJsonType(a3, com.a.a.c.j.m.ARRAY, "[QR][position]");
            if (a3.a() == 2) {
                quadRegion.h = (float) a3.a(0).k();
                quadRegion.i = (float) a3.a(1).k();
            } else {
                quadRegion.h = 0.0f;
                quadRegion.i = 0.0f;
            }
            m a4 = mVar.a(i + 1);
            JsonAssertUtils.checkJsonType(a4, com.a.a.c.j.m.ARRAY, "[QR][vertices]");
            if (a4.a() == 2) {
                float k = (float) a4.a(0).k();
                float k2 = (float) a4.a(1).k();
                quadRegion.j = k;
                quadRegion.k = k2;
                quadRegion.f[0] = 0.0f;
                quadRegion.f[1] = 0.0f;
                quadRegion.f[2] = 0.0f;
                quadRegion.f[3] = k2;
                quadRegion.f[4] = k;
                quadRegion.f[5] = k2;
                quadRegion.f[6] = k;
                quadRegion.f[7] = 0.0f;
            } else {
                int i2 = 0;
                Iterator<m> it = a4.iterator();
                while (it.hasNext()) {
                    int i3 = i2;
                    i2++;
                    quadRegion.f[i3] = (float) it.next().k();
                    if (i2 == quadRegion.f.length) {
                        break;
                    }
                }
            }
            quadRegion.updateSize();
            if (mVar.a() >= i + 3) {
                m a5 = mVar.a(i + 2);
                if (a5.a() != 1) {
                    if (a5.a() == 4) {
                        quadRegion.g = FourCornerColors.of(a5);
                    }
                } else {
                    Color valueOf = Color.valueOf(a5.a(0).i());
                    if (valueOf.toIntBits() == c) {
                        quadRegion.g = AllWhiteCornerColors.INSTANCE;
                    } else {
                        quadRegion.g = new SingleCornerColor(valueOf);
                    }
                }
            }
            if (mVar.a() >= i + 4) {
                quadRegion.m = (byte) mVar.a(i + 3).j();
            }
        } else if (mVar.c()) {
            quadRegion.setRegionNameFromProvider(mVar.b("t").c(null), assetProvider);
            quadRegion.quadName = mVar.b(FlexmarkHtmlConverter.I_NODE).c(null);
            quadRegion.setColorMode((byte) mVar.b("cm").b(0));
            m a6 = mVar.a(FlexmarkHtmlConverter.P_NODE);
            if (a6 != null) {
                quadRegion.setPosition((float) a6.a(0).k(), (float) a6.a(1).k());
            }
            m a7 = mVar.a("v");
            if (a7.a() == 2) {
                float k3 = (float) a7.a(0).k();
                float k4 = (float) a7.a(1).k();
                quadRegion.j = k3;
                quadRegion.k = k4;
                quadRegion.f[0] = 0.0f;
                quadRegion.f[1] = 0.0f;
                quadRegion.f[2] = 0.0f;
                quadRegion.f[3] = k4;
                quadRegion.f[4] = k3;
                quadRegion.f[5] = k4;
                quadRegion.f[6] = k3;
                quadRegion.f[7] = 0.0f;
            } else {
                int i4 = 0;
                Iterator<m> it2 = a7.iterator();
                while (it2.hasNext()) {
                    int i5 = i4;
                    i4++;
                    quadRegion.f[i5] = (float) it2.next().k();
                    if (i4 == quadRegion.f.length) {
                        break;
                    }
                }
            }
            quadRegion.updateSize();
            m a8 = mVar.a("c");
            if (a8 != null) {
                if (a8.a() != 1) {
                    if (a8.a() == 4) {
                        quadRegion.g = FourCornerColors.of(a8);
                    }
                } else {
                    Color valueOf2 = Color.valueOf(a8.a(0).i());
                    if (valueOf2.toIntBits() == c) {
                        quadRegion.g = AllWhiteCornerColors.INSTANCE;
                    } else {
                        quadRegion.g = new SingleCornerColor(valueOf2);
                    }
                }
            }
            quadRegion.m = (byte) mVar.b("n").b(0);
        }
        return quadRegion;
    }

    public static QuadRegion fromJson(JsonValue jsonValue) {
        QuadRegion quadRegion = new QuadRegion();
        quadRegion.setRegionName(null);
        if (jsonValue.isArray()) {
            int i = 0;
            JsonValue jsonValue2 = jsonValue.get(0);
            if (jsonValue2.isString() || jsonValue2.isNull()) {
                if (jsonValue2.isString()) {
                    quadRegion.setRegionName(jsonValue.get(0).asString());
                }
                i = 0 + 1;
            }
            JsonValue jsonValue3 = jsonValue.get(i);
            if (jsonValue3.size == 2) {
                quadRegion.h = (float) jsonValue3.get(0).asDouble();
                quadRegion.i = (float) jsonValue3.get(1).asDouble();
            } else {
                quadRegion.h = 0.0f;
                quadRegion.i = 0.0f;
            }
            JsonValue jsonValue4 = jsonValue.get(i + 1);
            if (jsonValue4.size == 2) {
                float asDouble = (float) jsonValue4.get(0).asDouble();
                float asDouble2 = (float) jsonValue4.get(1).asDouble();
                quadRegion.j = asDouble;
                quadRegion.k = asDouble2;
                quadRegion.f[0] = 0.0f;
                quadRegion.f[1] = 0.0f;
                quadRegion.f[2] = 0.0f;
                quadRegion.f[3] = asDouble2;
                quadRegion.f[4] = asDouble;
                quadRegion.f[5] = asDouble2;
                quadRegion.f[6] = asDouble;
                quadRegion.f[7] = 0.0f;
            } else {
                int i2 = 0;
                Iterator<JsonValue> iterator2 = jsonValue4.iterator2();
                while (iterator2.hasNext()) {
                    int i3 = i2;
                    i2++;
                    quadRegion.f[i3] = (float) iterator2.next().asDouble();
                    if (i2 == quadRegion.f.length) {
                        break;
                    }
                }
            }
            quadRegion.updateSize();
            if (jsonValue.size >= i + 3) {
                JsonValue jsonValue5 = jsonValue.get(i + 2);
                if (jsonValue5.size != 1) {
                    if (jsonValue5.size == 4) {
                        quadRegion.g = FourCornerColors.of(jsonValue5);
                    }
                } else {
                    Color valueOf = Color.valueOf(jsonValue5.get(0).asString());
                    if (valueOf.toIntBits() == c) {
                        quadRegion.g = AllWhiteCornerColors.INSTANCE;
                    } else {
                        quadRegion.g = new SingleCornerColor(valueOf);
                    }
                }
            }
            if (jsonValue.size >= i + 4) {
                quadRegion.m = (byte) jsonValue.get(i + 3).asInt();
            }
        } else if (jsonValue.isObject()) {
            JsonValue jsonValue6 = jsonValue.get("t");
            if (jsonValue6 != null) {
                quadRegion.setRegionName(jsonValue6.asString());
            }
            JsonValue jsonValue7 = jsonValue.get(FlexmarkHtmlConverter.I_NODE);
            if (jsonValue7 != null) {
                quadRegion.quadName = jsonValue7.asString();
            }
            JsonValue jsonValue8 = jsonValue.get("cm");
            if (jsonValue8 != null) {
                quadRegion.setColorMode((byte) jsonValue8.asInt());
            } else {
                quadRegion.setColorMode((byte) 0);
            }
            JsonValue jsonValue9 = jsonValue.get(FlexmarkHtmlConverter.P_NODE);
            if (jsonValue9 != null) {
                quadRegion.setPosition((float) jsonValue9.get(0).asDouble(), (float) jsonValue9.get(1).asDouble());
            }
            JsonValue jsonValue10 = jsonValue.get("v");
            if (jsonValue10.size == 2) {
                float asDouble3 = (float) jsonValue10.get(0).asDouble();
                float asDouble4 = (float) jsonValue10.get(1).asDouble();
                quadRegion.j = asDouble3;
                quadRegion.k = asDouble4;
                quadRegion.f[0] = 0.0f;
                quadRegion.f[1] = 0.0f;
                quadRegion.f[2] = 0.0f;
                quadRegion.f[3] = asDouble4;
                quadRegion.f[4] = asDouble3;
                quadRegion.f[5] = asDouble4;
                quadRegion.f[6] = asDouble3;
                quadRegion.f[7] = 0.0f;
            } else {
                int i4 = 0;
                Iterator<JsonValue> iterator22 = jsonValue10.iterator2();
                while (iterator22.hasNext()) {
                    int i5 = i4;
                    i4++;
                    quadRegion.f[i5] = (float) iterator22.next().asDouble();
                    if (i4 == quadRegion.f.length) {
                        break;
                    }
                }
            }
            quadRegion.updateSize();
            JsonValue jsonValue11 = jsonValue.get("c");
            if (jsonValue11 != null) {
                if (jsonValue11.size != 1) {
                    if (jsonValue11.size == 4) {
                        quadRegion.g = FourCornerColors.of(jsonValue11);
                    }
                } else {
                    Color valueOf2 = Color.valueOf(jsonValue11.get(0).asString());
                    if (valueOf2.toIntBits() == c) {
                        quadRegion.g = AllWhiteCornerColors.INSTANCE;
                    } else {
                        quadRegion.g = new SingleCornerColor(valueOf2);
                    }
                }
            }
            JsonValue jsonValue12 = jsonValue.get("n");
            if (jsonValue12 != null) {
                quadRegion.m = (byte) jsonValue12.asInt();
            } else {
                quadRegion.m = (byte) 0;
            }
        }
        return quadRegion;
    }

    public final boolean isFullSizeRect() {
        return this.f[0] == 0.0f && this.f[1] == 0.0f && this.f[2] == 0.0f && this.f[3] == this.k && this.f[4] == this.j && this.f[5] == this.k && this.f[6] == this.j && this.f[7] == 0.0f;
    }

    public final void toJson5String(StringBuilder stringBuilder) {
        stringBuilder.append('{');
        if (this.quadName != null) {
            stringBuilder.append("i:\"");
            stringBuilder.append(this.quadName);
            stringBuilder.append("\",");
        }
        if (this.regionName != null) {
            stringBuilder.append("t:\"");
            stringBuilder.append(this.regionName);
            stringBuilder.append("\",");
        }
        if (getColorMode() != 0) {
            stringBuilder.append("cm:");
            stringBuilder.append((int) getColorMode());
            stringBuilder.append(",");
        }
        if (this.h != 0.0f || this.i != 0.0f) {
            stringBuilder.append("p:[");
            Quad.sbWriteIntOrFloat(stringBuilder, this.h);
            stringBuilder.append(',');
            Quad.sbWriteIntOrFloat(stringBuilder, this.i);
            stringBuilder.append("],");
        }
        stringBuilder.append("v:[");
        if (isFullSizeRect()) {
            Quad.sbWriteIntOrFloat(stringBuilder, this.j);
            stringBuilder.append(',');
            Quad.sbWriteIntOrFloat(stringBuilder, this.k);
        } else {
            for (int i = 0; i < 8; i++) {
                int i2 = i % 2;
                Quad.sbWriteIntOrFloat(stringBuilder, this.f[i]);
                if (i != 7) {
                    stringBuilder.append(',');
                }
            }
        }
        stringBuilder.append("],");
        int i3 = stringBuilder.length;
        this.g.toJson5String(stringBuilder);
        if (i3 != stringBuilder.length) {
            stringBuilder.append(',');
        }
        if (this.m != 0) {
            stringBuilder.append("n:").append((int) this.m);
        }
        if (stringBuilder.charAt(stringBuilder.length - 1) == ',') {
            stringBuilder.setLength(stringBuilder.length - 1);
        }
        stringBuilder.append('}');
    }

    public final void toBytes(FixedOutput fixedOutput) {
        boolean z = Quad.floatIsInt(this.h) && Quad.floatIsInt(this.i);
        int i = 0;
        while (true) {
            if (i >= 8) {
                break;
            }
            if (Quad.floatIsInt(this.f[i])) {
                i++;
            } else {
                z = false;
                break;
            }
        }
        boolean z2 = (this.regionName == null || AssetManager.BLANK_REGION_NAME.equals(this.regionName)) ? false : true;
        boolean isFullSizeRect = isFullSizeRect();
        boolean z3 = (this.h == 0.0f && this.i == 0.0f) ? false : true;
        boolean z4 = this.g != AllWhiteCornerColors.INSTANCE;
        boolean isSingleColor = this.g.isSingleColor();
        boolean z5 = this.m != 0;
        byte b2 = z ? (byte) 1 : (byte) 0;
        if (z2) {
            b2 = (byte) (b2 | 2);
        }
        if (isFullSizeRect) {
            b2 = (byte) (b2 | 4);
        }
        if (z3) {
            b2 = (byte) (b2 | 8);
        }
        if (z4) {
            b2 = (byte) (b2 | 16);
        }
        if (isSingleColor) {
            b2 = (byte) (b2 | 32);
        }
        if (z5) {
            b2 = (byte) (b2 | 64);
        }
        fixedOutput.writeByte(b2);
        if (z2) {
            fixedOutput.writeAscii(Quad.stripNonAscii(this.regionName));
        }
        if (z3) {
            if (z) {
                fixedOutput.writeVarInt(MathUtils.round(this.h), true);
                fixedOutput.writeVarInt(MathUtils.round(this.i), true);
            } else {
                fixedOutput.writeVarInt(MathUtils.round(this.h * 100.0f), true);
                fixedOutput.writeVarInt(MathUtils.round(this.i * 100.0f), true);
            }
        }
        if (isFullSizeRect) {
            if (z) {
                fixedOutput.writeVarInt(MathUtils.round(this.j), true);
                fixedOutput.writeVarInt(MathUtils.round(this.k), true);
            } else {
                fixedOutput.writeVarInt(MathUtils.round(this.j * 100.0f), true);
                fixedOutput.writeVarInt(MathUtils.round(this.k * 100.0f), true);
            }
        } else {
            for (int i2 = 0; i2 < 8; i2++) {
                if (z) {
                    fixedOutput.writeVarInt(MathUtils.round(this.f[i2]), true);
                } else {
                    fixedOutput.writeVarInt(MathUtils.round(this.f[i2] * 100.0f), true);
                }
            }
        }
        if (z4) {
            fixedOutput.writeInt(this.g.get(0).toIntBits(), false);
            if (!isSingleColor) {
                fixedOutput.writeInt(this.g.get(1).toIntBits(), false);
                fixedOutput.writeInt(this.g.get(2).toIntBits(), false);
                fixedOutput.writeInt(this.g.get(3).toIntBits(), false);
            }
        }
        if (z5) {
            fixedOutput.writeByte(this.m);
        }
        fixedOutput.writeByte(getColorMode());
    }

    public static QuadRegion fromBytes(FixedInput fixedInput) {
        float readVarInt;
        float readVarInt2;
        byte readByte = fixedInput.readByte();
        boolean z = (readByte & 1) != 0;
        boolean z2 = (readByte & 2) != 0;
        boolean z3 = (readByte & 4) != 0;
        boolean z4 = (readByte & 8) != 0;
        boolean z5 = (readByte & 16) != 0;
        boolean z6 = (readByte & 32) != 0;
        boolean z7 = (readByte & 64) != 0;
        QuadRegion quadRegion = new QuadRegion();
        if (z2) {
            quadRegion.setRegionName(fixedInput.readString());
        } else {
            quadRegion.setRegionName(null);
        }
        if (z4) {
            if (z) {
                quadRegion.h = fixedInput.readVarInt(true);
                quadRegion.i = fixedInput.readVarInt(true);
            } else {
                quadRegion.h = fixedInput.readVarInt(true) / 100.0f;
                quadRegion.i = fixedInput.readVarInt(true) / 100.0f;
            }
        }
        if (z3) {
            if (z) {
                readVarInt = fixedInput.readVarInt(true);
                readVarInt2 = fixedInput.readVarInt(true);
            } else {
                readVarInt = fixedInput.readVarInt(true) / 100.0f;
                readVarInt2 = fixedInput.readVarInt(true) / 100.0f;
            }
            quadRegion.setCornerPositions(0.0f, 0.0f, 0.0f, readVarInt2, readVarInt, readVarInt2, readVarInt, 0.0f);
        } else if (z) {
            quadRegion.setCornerPositions(fixedInput.readVarInt(true), fixedInput.readVarInt(true), fixedInput.readVarInt(true), fixedInput.readVarInt(true), fixedInput.readVarInt(true), fixedInput.readVarInt(true), fixedInput.readVarInt(true), fixedInput.readVarInt(true));
        } else {
            quadRegion.setCornerPositions(fixedInput.readVarInt(true) / 100.0f, fixedInput.readVarInt(true) / 100.0f, fixedInput.readVarInt(true) / 100.0f, fixedInput.readVarInt(true) / 100.0f, fixedInput.readVarInt(true) / 100.0f, fixedInput.readVarInt(true) / 100.0f, fixedInput.readVarInt(true) / 100.0f, fixedInput.readVarInt(true) / 100.0f);
        }
        if (z5) {
            Color abgr8888ToColor = PMath.abgr8888ToColor(fixedInput.readInt(false));
            if (z6) {
                quadRegion.setSameCornerColors(abgr8888ToColor);
            } else {
                quadRegion.setCornerColorsSeparate(abgr8888ToColor, PMath.abgr8888ToColor(fixedInput.readInt(false)), PMath.abgr8888ToColor(fixedInput.readInt(false)), PMath.abgr8888ToColor(fixedInput.readInt(false)));
            }
        }
        if (z7) {
            quadRegion.m = fixedInput.readByte();
        }
        quadRegion.setColorMode(fixedInput.readByte());
        quadRegion.updateSize();
        return quadRegion;
    }

    public final void toJson(Json json) {
        json.writeObjectStart();
        if (this.quadName != null) {
            json.writeValue(FlexmarkHtmlConverter.I_NODE, this.quadName);
        }
        if (this.regionName != null) {
            json.writeValue("t", this.regionName);
        }
        if (getColorMode() != 0) {
            json.writeValue("cm", Byte.valueOf(getColorMode()));
        }
        if (this.h != 0.0f || this.i != 0.0f) {
            json.writeArrayStart(FlexmarkHtmlConverter.P_NODE);
            Quad.jsonWriteIntOrFloat(json, this.h);
            Quad.jsonWriteIntOrFloat(json, this.i);
            json.writeArrayEnd();
        }
        json.writeArrayStart("v");
        if (isFullSizeRect()) {
            Quad.jsonWriteIntOrFloat(json, this.j);
            Quad.jsonWriteIntOrFloat(json, this.k);
        } else {
            for (int i = 0; i < 8; i++) {
                Quad.jsonWriteIntOrFloat(json, this.f[i]);
            }
        }
        json.writeArrayEnd();
        this.g.toJson(json);
        if (this.m != 0) {
            json.writeValue("n", Byte.valueOf(this.m));
        }
        json.writeObjectEnd();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/QuadRegion$AllWhiteCornerColors.class */
    public static final class AllWhiteCornerColors implements CornerColors {
        public static final AllWhiteCornerColors INSTANCE = new AllWhiteCornerColors();

        @Override // com.prineside.tdi2.utils.QuadRegion.CornerColors
        public final boolean sameAs(CornerColors cornerColors) {
            return cornerColors == INSTANCE;
        }

        @Override // com.prineside.tdi2.utils.QuadRegion.CornerColors
        public final Color get(int i) {
            return Color.WHITE;
        }

        @Override // com.prineside.tdi2.utils.QuadRegion.CornerColors
        public final boolean isSingleColor() {
            return true;
        }

        @Override // com.prineside.tdi2.utils.QuadRegion.CornerColors
        public final CornerColors cpyCornerColors() {
            return this;
        }

        @Override // com.prineside.tdi2.utils.QuadRegion.CornerColors
        public final void toJson5String(StringBuilder stringBuilder) {
        }

        @Override // com.prineside.tdi2.utils.QuadRegion.CornerColors
        public final void toJson(Json json) {
        }

        @Override // com.prineside.tdi2.utils.QuadRegion.CornerColors
        public final CornerColors simplifyIfNeeded() {
            return this;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/QuadRegion$SingleCornerColor.class */
    public static final class SingleCornerColor extends Color implements CornerColors {
        public SingleCornerColor() {
        }

        public SingleCornerColor(Color color) {
            set(color);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.utils.QuadRegion.CornerColors
        public final boolean sameAs(CornerColors cornerColors) {
            return (cornerColors instanceof SingleCornerColor) && toIntBits() == ((Color) cornerColors).toIntBits();
        }

        @Override // com.prineside.tdi2.utils.QuadRegion.CornerColors
        public final Color get(int i) {
            return this;
        }

        @Override // com.prineside.tdi2.utils.QuadRegion.CornerColors
        public final boolean isSingleColor() {
            return true;
        }

        @Override // com.prineside.tdi2.utils.QuadRegion.CornerColors
        public final CornerColors cpyCornerColors() {
            return new SingleCornerColor(this);
        }

        @Override // com.prineside.tdi2.utils.QuadRegion.CornerColors
        public final void toJson5String(StringBuilder stringBuilder) {
            stringBuilder.append("c:[");
            String str = "#" + this;
            String str2 = str;
            if (str.endsWith("ff")) {
                str2 = str2.substring(0, str2.length() - 2);
            }
            stringBuilder.append('\"');
            stringBuilder.append(str2);
            stringBuilder.append("\"]");
        }

        @Override // com.prineside.tdi2.utils.QuadRegion.CornerColors
        public final void toJson(Json json) {
            json.writeArrayStart("c");
            String str = "#" + this;
            String str2 = str;
            if (str.endsWith("ff")) {
                str2 = str2.substring(0, str2.length() - 2);
            }
            json.writeValue(str2);
            json.writeArrayEnd();
        }

        @Override // com.prineside.tdi2.utils.QuadRegion.CornerColors
        public final CornerColors simplifyIfNeeded() {
            return toIntBits() == QuadRegion.c ? AllWhiteCornerColors.INSTANCE : this;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/QuadRegion$FourCornerColors.class */
    public static final class FourCornerColors implements CornerColors {

        /* renamed from: a, reason: collision with root package name */
        private final Color f3895a = new Color();

        /* renamed from: b, reason: collision with root package name */
        private final Color f3896b = new Color();
        private final Color c = new Color();
        private final Color d = new Color();

        private FourCornerColors() {
        }

        public FourCornerColors(Color color, Color color2, Color color3, Color color4) {
            this.f3895a.set(color);
            this.f3896b.set(color2);
            this.c.set(color3);
            this.d.set(color4);
        }

        public static FourCornerColors of(JsonValue jsonValue) {
            return new FourCornerColors(Color.valueOf(jsonValue.get(0).asString()), Color.valueOf(jsonValue.get(1).asString()), Color.valueOf(jsonValue.get(2).asString()), Color.valueOf(jsonValue.get(3).asString()));
        }

        public static FourCornerColors of(m mVar) {
            return new FourCornerColors(Color.valueOf(mVar.a(0).i()), Color.valueOf(mVar.a(1).i()), Color.valueOf(mVar.a(2).i()), Color.valueOf(mVar.a(3).i()));
        }

        @Override // com.prineside.tdi2.utils.QuadRegion.CornerColors
        public final boolean sameAs(CornerColors cornerColors) {
            if (cornerColors instanceof FourCornerColors) {
                FourCornerColors fourCornerColors = (FourCornerColors) cornerColors;
                return this.f3895a.toIntBits() == fourCornerColors.f3895a.toIntBits() && this.f3896b.toIntBits() == fourCornerColors.f3896b.toIntBits() && this.c.toIntBits() == fourCornerColors.c.toIntBits() && this.d.toIntBits() == fourCornerColors.d.toIntBits();
            }
            return false;
        }

        @Override // com.prineside.tdi2.utils.QuadRegion.CornerColors
        public final Color get(int i) {
            switch (i) {
                case 0:
                    return this.f3895a;
                case 1:
                    return this.f3896b;
                case 2:
                    return this.c;
                case 3:
                    return this.d;
                default:
                    throw new IllegalArgumentException("Invalid index: " + i);
            }
        }

        @Override // com.prineside.tdi2.utils.QuadRegion.CornerColors
        public final boolean isSingleColor() {
            return false;
        }

        @Override // com.prineside.tdi2.utils.QuadRegion.CornerColors
        public final CornerColors cpyCornerColors() {
            return new FourCornerColors(this.f3895a, this.f3896b, this.c, this.d);
        }

        @Override // com.prineside.tdi2.utils.QuadRegion.CornerColors
        public final void toJson5String(StringBuilder stringBuilder) {
            stringBuilder.append("c:[");
            for (int i = 0; i < 4; i++) {
                String str = "#" + get(i);
                String str2 = str;
                if (str.endsWith("ff")) {
                    str2 = str2.substring(0, str2.length() - 2);
                }
                stringBuilder.append('\"');
                stringBuilder.append(str2);
                stringBuilder.append('\"');
                if (i != 3) {
                    stringBuilder.append(',');
                }
            }
            stringBuilder.append("]");
        }

        @Override // com.prineside.tdi2.utils.QuadRegion.CornerColors
        public final void toJson(Json json) {
            json.writeArrayStart("c");
            for (int i = 0; i < 4; i++) {
                String str = "#" + get(i);
                String str2 = str;
                if (str.endsWith("ff")) {
                    str2 = str2.substring(0, str2.length() - 2);
                }
                json.writeValue(str2);
            }
            json.writeArrayEnd();
        }

        @Override // com.prineside.tdi2.utils.QuadRegion.CornerColors
        public final CornerColors simplifyIfNeeded() {
            int intBits = this.f3895a.toIntBits();
            if (intBits != this.f3896b.toIntBits()) {
                return this;
            }
            if (intBits != this.f3895a.toIntBits()) {
                return this;
            }
            if (intBits != this.f3895a.toIntBits()) {
                return this;
            }
            if (intBits == QuadRegion.c) {
                return AllWhiteCornerColors.INSTANCE;
            }
            return new SingleCornerColor(this.f3895a);
        }
    }
}
