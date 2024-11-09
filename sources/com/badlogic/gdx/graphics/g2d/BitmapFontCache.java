package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.NumberUtils;
import com.badlogic.gdx.utils.Pools;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/BitmapFontCache.class */
public class BitmapFontCache {
    private static final Color tempColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
    private final BitmapFont font;
    private boolean integer;
    private final Array<GlyphLayout> layouts;
    private final Array<GlyphLayout> pooledLayouts;
    private int glyphCount;
    private float x;
    private float y;
    private final Color color;
    private float currentTint;
    private float[][] pageVertices;
    private int[] idx;
    private IntArray[] pageGlyphIndices;
    private int[] tempGlyphCount;

    public BitmapFontCache(BitmapFont bitmapFont) {
        this(bitmapFont, bitmapFont.usesIntegerPositions());
    }

    /* JADX WARN: Type inference failed for: r1v7, types: [float[], float[][]] */
    public BitmapFontCache(BitmapFont bitmapFont, boolean z) {
        this.layouts = new Array<>(1);
        this.pooledLayouts = new Array<>(0);
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.font = bitmapFont;
        this.integer = z;
        int i = bitmapFont.regions.size;
        if (i == 0) {
            throw new IllegalArgumentException("The specified font must contain at least one texture page.");
        }
        this.pageVertices = new float[i];
        this.idx = new int[i];
        if (i > 1) {
            this.pageGlyphIndices = new IntArray[i];
            int length = this.pageGlyphIndices.length;
            for (int i2 = 0; i2 < length; i2++) {
                this.pageGlyphIndices[i2] = new IntArray();
            }
        }
        this.tempGlyphCount = new int[i];
    }

    public void setPosition(float f, float f2) {
        translate(f - this.x, f2 - this.y);
    }

    public void translate(float f, float f2) {
        if (f == 0.0f && f2 == 0.0f) {
            return;
        }
        if (this.integer) {
            f = Math.round(f);
            f2 = Math.round(f2);
        }
        this.x += f;
        this.y += f2;
        float[][] fArr = this.pageVertices;
        int length = fArr.length;
        for (int i = 0; i < length; i++) {
            float[] fArr2 = fArr[i];
            int i2 = this.idx[i];
            for (int i3 = 0; i3 < i2; i3 += 5) {
                int i4 = i3;
                fArr2[i4] = fArr2[i4] + f;
                int i5 = i3 + 1;
                fArr2[i5] = fArr2[i5] + f2;
            }
        }
    }

    public void tint(Color color) {
        float floatBits = color.toFloatBits();
        if (this.currentTint == floatBits) {
            return;
        }
        this.currentTint = floatBits;
        float[][] fArr = this.pageVertices;
        Color color2 = tempColor;
        int[] iArr = this.tempGlyphCount;
        Arrays.fill(iArr, 0);
        int i = this.layouts.size;
        for (int i2 = 0; i2 < i; i2++) {
            GlyphLayout glyphLayout = this.layouts.get(i2);
            IntArray intArray = glyphLayout.colors;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            float f = 0.0f;
            int i6 = glyphLayout.runs.size;
            for (int i7 = 0; i7 < i6; i7++) {
                GlyphLayout.GlyphRun glyphRun = glyphLayout.runs.get(i7);
                BitmapFont.Glyph[] glyphArr = glyphRun.glyphs.items;
                int i8 = glyphRun.glyphs.size;
                for (int i9 = 0; i9 < i8; i9++) {
                    int i10 = i5;
                    i5++;
                    if (i10 == i4) {
                        int i11 = i3 + 1;
                        Color.abgr8888ToColor(color2, intArray.get(i11));
                        f = color2.mul(color).toFloatBits();
                        i3 = i11 + 1;
                        i4 = i3 < intArray.size ? intArray.get(i3) : -1;
                    }
                    int i12 = glyphArr[i9].page;
                    int i13 = (iArr[i12] * 20) + 2;
                    iArr[i12] = iArr[i12] + 1;
                    float[] fArr2 = fArr[i12];
                    fArr2[i13] = f;
                    fArr2[i13 + 5] = f;
                    fArr2[i13 + 10] = f;
                    fArr2[i13 + 15] = f;
                }
            }
        }
    }

    public void setAlphas(float f) {
        int i = ((int) (254.0f * f)) << 24;
        float f2 = 0.0f;
        float f3 = 0.0f;
        int length = this.pageVertices.length;
        for (int i2 = 0; i2 < length; i2++) {
            float[] fArr = this.pageVertices[i2];
            int i3 = this.idx[i2];
            for (int i4 = 2; i4 < i3; i4 += 5) {
                float f4 = fArr[i4];
                if (f4 == f2 && i4 != 2) {
                    fArr[i4] = f3;
                } else {
                    f2 = f4;
                    f3 = NumberUtils.intToFloatColor((NumberUtils.floatToIntColor(f4) & 16777215) | i);
                    fArr[i4] = f3;
                }
            }
        }
    }

    public void setColors(float f) {
        int length = this.pageVertices.length;
        for (int i = 0; i < length; i++) {
            float[] fArr = this.pageVertices[i];
            int i2 = this.idx[i];
            for (int i3 = 2; i3 < i2; i3 += 5) {
                fArr[i3] = f;
            }
        }
    }

    public void setColors(Color color) {
        setColors(color.toFloatBits());
    }

    public void setColors(float f, float f2, float f3, float f4) {
        setColors(NumberUtils.intToFloatColor((((int) (255.0f * f4)) << 24) | (((int) (255.0f * f3)) << 16) | (((int) (255.0f * f2)) << 8) | ((int) (255.0f * f))));
    }

    public void setColors(Color color, int i, int i2) {
        setColors(color.toFloatBits(), i, i2);
    }

    public void setColors(float f, int i, int i2) {
        int i3;
        if (this.pageVertices.length == 1) {
            float[] fArr = this.pageVertices[0];
            int min = Math.min(i2 * 20, this.idx[0]);
            for (int i4 = (i * 20) + 2; i4 < min; i4 += 5) {
                fArr[i4] = f;
            }
            return;
        }
        int length = this.pageVertices.length;
        for (int i5 = 0; i5 < length; i5++) {
            float[] fArr2 = this.pageVertices[i5];
            IntArray intArray = this.pageGlyphIndices[i5];
            int i6 = intArray.size;
            for (int i7 = 0; i7 < i6 && (i3 = intArray.items[i7]) < i2; i7++) {
                if (i3 >= i) {
                    int i8 = (i7 * 20) + 2;
                    fArr2[i8] = f;
                    fArr2[i8 + 5] = f;
                    fArr2[i8 + 10] = f;
                    fArr2[i8 + 15] = f;
                }
            }
        }
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public void setColor(float f, float f2, float f3, float f4) {
        this.color.set(f, f2, f3, f4);
    }

    public void draw(Batch batch) {
        Array<TextureRegion> regions = this.font.getRegions();
        int length = this.pageVertices.length;
        for (int i = 0; i < length; i++) {
            if (this.idx[i] > 0) {
                batch.draw(regions.get(i).getTexture(), this.pageVertices[i], 0, this.idx[i]);
            }
        }
    }

    public void draw(Batch batch, int i, int i2) {
        int i3;
        if (this.pageVertices.length == 1) {
            batch.draw(this.font.getRegion().getTexture(), this.pageVertices[0], i * 20, (i2 - i) * 20);
            return;
        }
        Array<TextureRegion> regions = this.font.getRegions();
        int length = this.pageVertices.length;
        for (int i4 = 0; i4 < length; i4++) {
            int i5 = -1;
            int i6 = 0;
            IntArray intArray = this.pageGlyphIndices[i4];
            int i7 = intArray.size;
            for (int i8 = 0; i8 < i7 && (i3 = intArray.get(i8)) < i2; i8++) {
                if (i5 == -1 && i3 >= i) {
                    i5 = i8;
                }
                if (i3 >= i) {
                    i6++;
                }
            }
            if (i5 != -1 && i6 != 0) {
                batch.draw(regions.get(i4).getTexture(), this.pageVertices[i4], i5 * 20, i6 * 20);
            }
        }
    }

    public void draw(Batch batch, float f) {
        if (f == 1.0f) {
            draw(batch);
            return;
        }
        Color color = getColor();
        float f2 = color.f889a;
        color.f889a *= f;
        setColors(color);
        draw(batch);
        color.f889a = f2;
        setColors(color);
    }

    public void clear() {
        this.x = 0.0f;
        this.y = 0.0f;
        Pools.freeAll(this.pooledLayouts, true);
        this.pooledLayouts.clear();
        this.layouts.clear();
        int length = this.idx.length;
        for (int i = 0; i < length; i++) {
            if (this.pageGlyphIndices != null) {
                this.pageGlyphIndices[i].clear();
            }
            this.idx[i] = 0;
        }
    }

    private void requireGlyphs(GlyphLayout glyphLayout) {
        if (this.pageVertices.length == 1) {
            requirePageGlyphs(0, glyphLayout.glyphCount);
            return;
        }
        int[] iArr = this.tempGlyphCount;
        Arrays.fill(iArr, 0);
        int i = glyphLayout.runs.size;
        for (int i2 = 0; i2 < i; i2++) {
            Array<BitmapFont.Glyph> array = glyphLayout.runs.get(i2).glyphs;
            BitmapFont.Glyph[] glyphArr = array.items;
            int i3 = array.size;
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = glyphArr[i4].page;
                iArr[i5] = iArr[i5] + 1;
            }
        }
        int length = iArr.length;
        for (int i6 = 0; i6 < length; i6++) {
            requirePageGlyphs(i6, iArr[i6]);
        }
    }

    private void requirePageGlyphs(int i, int i2) {
        if (this.pageGlyphIndices != null && i2 > this.pageGlyphIndices[i].items.length) {
            IntArray intArray = this.pageGlyphIndices[i];
            intArray.ensureCapacity(i2 - intArray.size);
        }
        int i3 = this.idx[i] + (i2 * 20);
        float[] fArr = this.pageVertices[i];
        if (fArr == null) {
            this.pageVertices[i] = new float[i3];
        } else if (fArr.length < i3) {
            float[] fArr2 = new float[i3];
            System.arraycopy(fArr, 0, fArr2, 0, this.idx[i]);
            this.pageVertices[i] = fArr2;
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [float[], float[][], java.lang.Object] */
    private void setPageCount(int i) {
        ?? r0 = new float[i];
        System.arraycopy(this.pageVertices, 0, r0, 0, this.pageVertices.length);
        this.pageVertices = r0;
        int[] iArr = new int[i];
        System.arraycopy(this.idx, 0, iArr, 0, this.idx.length);
        this.idx = iArr;
        IntArray[] intArrayArr = new IntArray[i];
        int i2 = 0;
        if (this.pageGlyphIndices != null) {
            i2 = this.pageGlyphIndices.length;
            System.arraycopy(this.pageGlyphIndices, 0, intArrayArr, 0, this.pageGlyphIndices.length);
        }
        for (int i3 = i2; i3 < i; i3++) {
            intArrayArr[i3] = new IntArray();
        }
        this.pageGlyphIndices = intArrayArr;
        this.tempGlyphCount = new int[i];
    }

    private void addToCache(GlyphLayout glyphLayout, float f, float f2) {
        int i = glyphLayout.runs.size;
        if (i == 0) {
            return;
        }
        if (this.pageVertices.length < this.font.regions.size) {
            setPageCount(this.font.regions.size);
        }
        this.layouts.add(glyphLayout);
        requireGlyphs(glyphLayout);
        IntArray intArray = glyphLayout.colors;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        float f3 = 0.0f;
        for (int i5 = 0; i5 < i; i5++) {
            GlyphLayout.GlyphRun glyphRun = glyphLayout.runs.get(i5);
            BitmapFont.Glyph[] glyphArr = glyphRun.glyphs.items;
            float[] fArr = glyphRun.xAdvances.items;
            float f4 = f + glyphRun.x;
            float f5 = f2 + glyphRun.y;
            int i6 = glyphRun.glyphs.size;
            for (int i7 = 0; i7 < i6; i7++) {
                int i8 = i4;
                i4++;
                if (i8 == i3) {
                    int i9 = i2 + 1;
                    f3 = NumberUtils.intToFloatColor(intArray.get(i9));
                    i2 = i9 + 1;
                    i3 = i2 < intArray.size ? intArray.get(i2) : -1;
                }
                f4 += fArr[i7];
                addGlyph(glyphArr[i7], f4, f5, f3);
            }
        }
        this.currentTint = Color.WHITE_FLOAT_BITS;
    }

    private void addGlyph(BitmapFont.Glyph glyph, float f, float f2, float f3) {
        float f4 = this.font.data.scaleX;
        float f5 = this.font.data.scaleY;
        float f6 = f + (glyph.xoffset * f4);
        float f7 = f2 + (glyph.yoffset * f5);
        float f8 = glyph.width * f4;
        float f9 = glyph.height * f5;
        float f10 = glyph.u;
        float f11 = glyph.u2;
        float f12 = glyph.v;
        float f13 = glyph.v2;
        if (this.integer) {
            f6 = Math.round(f6);
            f7 = Math.round(f7);
            f8 = Math.round(f8);
            f9 = Math.round(f9);
        }
        float f14 = f6 + f8;
        float f15 = f7 + f9;
        int i = glyph.page;
        int i2 = this.idx[i];
        int[] iArr = this.idx;
        iArr[i] = iArr[i] + 20;
        if (this.pageGlyphIndices != null) {
            IntArray intArray = this.pageGlyphIndices[i];
            int i3 = this.glyphCount;
            this.glyphCount = i3 + 1;
            intArray.add(i3);
        }
        float[] fArr = this.pageVertices[i];
        int i4 = i2 + 1;
        fArr[i2] = f6;
        int i5 = i4 + 1;
        fArr[i4] = f7;
        int i6 = i5 + 1;
        fArr[i5] = f3;
        int i7 = i6 + 1;
        fArr[i6] = f10;
        int i8 = i7 + 1;
        fArr[i7] = f12;
        int i9 = i8 + 1;
        fArr[i8] = f6;
        int i10 = i9 + 1;
        fArr[i9] = f15;
        int i11 = i10 + 1;
        fArr[i10] = f3;
        int i12 = i11 + 1;
        fArr[i11] = f10;
        int i13 = i12 + 1;
        fArr[i12] = f13;
        int i14 = i13 + 1;
        fArr[i13] = f14;
        int i15 = i14 + 1;
        fArr[i14] = f15;
        int i16 = i15 + 1;
        fArr[i15] = f3;
        int i17 = i16 + 1;
        fArr[i16] = f11;
        int i18 = i17 + 1;
        fArr[i17] = f13;
        int i19 = i18 + 1;
        fArr[i18] = f14;
        int i20 = i19 + 1;
        fArr[i19] = f7;
        int i21 = i20 + 1;
        fArr[i20] = f3;
        fArr[i21] = f11;
        fArr[i21 + 1] = f12;
    }

    public GlyphLayout setText(CharSequence charSequence, float f, float f2) {
        clear();
        return addText(charSequence, f, f2, 0, charSequence.length(), 0.0f, 8, false);
    }

    public GlyphLayout setText(CharSequence charSequence, float f, float f2, float f3, int i, boolean z) {
        clear();
        return addText(charSequence, f, f2, 0, charSequence.length(), f3, i, z);
    }

    public GlyphLayout setText(CharSequence charSequence, float f, float f2, int i, int i2, float f3, int i3, boolean z) {
        clear();
        return addText(charSequence, f, f2, i, i2, f3, i3, z);
    }

    public GlyphLayout setText(CharSequence charSequence, float f, float f2, int i, int i2, float f3, int i3, boolean z, String str) {
        clear();
        return addText(charSequence, f, f2, i, i2, f3, i3, z, str);
    }

    public void setText(GlyphLayout glyphLayout, float f, float f2) {
        clear();
        addText(glyphLayout, f, f2);
    }

    public GlyphLayout addText(CharSequence charSequence, float f, float f2) {
        return addText(charSequence, f, f2, 0, charSequence.length(), 0.0f, 8, false, null);
    }

    public GlyphLayout addText(CharSequence charSequence, float f, float f2, float f3, int i, boolean z) {
        return addText(charSequence, f, f2, 0, charSequence.length(), f3, i, z, null);
    }

    public GlyphLayout addText(CharSequence charSequence, float f, float f2, int i, int i2, float f3, int i3, boolean z) {
        return addText(charSequence, f, f2, i, i2, f3, i3, z, null);
    }

    public GlyphLayout addText(CharSequence charSequence, float f, float f2, int i, int i2, float f3, int i3, boolean z, String str) {
        GlyphLayout glyphLayout = (GlyphLayout) Pools.obtain(GlyphLayout.class);
        this.pooledLayouts.add(glyphLayout);
        glyphLayout.setText(this.font, charSequence, i, i2, this.color, f3, i3, z, str);
        addText(glyphLayout, f, f2);
        return glyphLayout;
    }

    public void addText(GlyphLayout glyphLayout, float f, float f2) {
        addToCache(glyphLayout, f, f2 + this.font.data.ascent);
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public BitmapFont getFont() {
        return this.font;
    }

    public void setUseIntegerPositions(boolean z) {
        this.integer = z;
    }

    public boolean usesIntegerPositions() {
        return this.integer;
    }

    public float[] getVertices() {
        return getVertices(0);
    }

    public float[] getVertices(int i) {
        return this.pageVertices[i];
    }

    public int getVertexCount(int i) {
        return this.idx[i];
    }

    public Array<GlyphLayout> getLayouts() {
        return this.layouts;
    }
}
