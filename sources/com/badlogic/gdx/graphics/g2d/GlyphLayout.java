package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/GlyphLayout.class */
public class GlyphLayout implements Pool.Poolable {
    private static final Pool<GlyphRun> glyphRunPool = Pools.get(GlyphRun.class);
    private static final IntArray colorStack = new IntArray(4);
    private static final float epsilon = 1.0E-4f;
    public final Array<GlyphRun> runs = new Array<>(1);
    public final IntArray colors = new IntArray(2);
    public int glyphCount;
    public float width;
    public float height;

    public GlyphLayout() {
    }

    public GlyphLayout(BitmapFont bitmapFont, CharSequence charSequence) {
        setText(bitmapFont, charSequence);
    }

    public GlyphLayout(BitmapFont bitmapFont, CharSequence charSequence, Color color, float f, int i, boolean z) {
        setText(bitmapFont, charSequence, color, f, i, z);
    }

    public GlyphLayout(BitmapFont bitmapFont, CharSequence charSequence, int i, int i2, Color color, float f, int i3, boolean z, String str) {
        setText(bitmapFont, charSequence, i, i2, color, f, i3, z, str);
    }

    public void setText(BitmapFont bitmapFont, CharSequence charSequence) {
        setText(bitmapFont, charSequence, 0, charSequence.length(), bitmapFont.getColor(), 0.0f, 8, false, null);
    }

    public void setText(BitmapFont bitmapFont, CharSequence charSequence, Color color, float f, int i, boolean z) {
        setText(bitmapFont, charSequence, 0, charSequence.length(), color, f, i, z, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:111:0x0077, code lost:            continue;     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0197, code lost:            if (r22 == null) goto L89;     */
    /* JADX WARN: Failed to find 'out' block for switch in B:21:0x009b. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setText(com.badlogic.gdx.graphics.g2d.BitmapFont r8, java.lang.CharSequence r9, int r10, int r11, com.badlogic.gdx.graphics.Color r12, float r13, int r14, boolean r15, @com.badlogic.gdx.utils.Null java.lang.String r16) {
        /*
            Method dump skipped, instructions count: 829
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.graphics.g2d.GlyphLayout.setText(com.badlogic.gdx.graphics.g2d.BitmapFont, java.lang.CharSequence, int, int, com.badlogic.gdx.graphics.Color, float, int, boolean, java.lang.String):void");
    }

    private void calculateWidths(BitmapFont.BitmapFontData bitmapFontData) {
        float f = 0.0f;
        GlyphRun[] glyphRunArr = this.runs.items;
        int i = this.runs.size;
        for (int i2 = 0; i2 < i; i2++) {
            GlyphRun glyphRun = glyphRunArr[i2];
            float[] fArr = glyphRun.xAdvances.items;
            float f2 = glyphRun.x + fArr[0];
            float f3 = 0.0f;
            BitmapFont.Glyph[] glyphArr = glyphRun.glyphs.items;
            int i3 = 0;
            int i4 = glyphRun.glyphs.size;
            while (i3 < i4) {
                f3 = Math.max(f3, f2 + getGlyphWidth(glyphArr[i3], bitmapFontData));
                i3++;
                f2 += fArr[i3];
            }
            glyphRun.width = Math.max(f2, f3) - glyphRun.x;
            f = Math.max(f, glyphRun.x + glyphRun.width);
        }
        this.width = f;
    }

    private void alignRuns(float f, int i) {
        if ((i & 8) == 0) {
            boolean z = (i & 1) != 0;
            GlyphRun[] glyphRunArr = this.runs.items;
            int i2 = this.runs.size;
            for (int i3 = 0; i3 < i2; i3++) {
                GlyphRun glyphRun = glyphRunArr[i3];
                glyphRun.x += z ? 0.5f * (f - glyphRun.width) : f - glyphRun.width;
            }
        }
    }

    private void truncate(BitmapFont.BitmapFontData bitmapFontData, GlyphRun glyphRun, float f, String str) {
        int i = glyphRun.glyphs.size;
        GlyphRun obtain = glyphRunPool.obtain();
        bitmapFontData.getGlyphs(obtain, str, 0, str.length(), null);
        float f2 = 0.0f;
        if (obtain.xAdvances.size > 0) {
            setLastGlyphXAdvance(bitmapFontData, obtain);
            float[] fArr = obtain.xAdvances.items;
            int i2 = obtain.xAdvances.size;
            for (int i3 = 1; i3 < i2; i3++) {
                f2 += fArr[i3];
            }
        }
        float f3 = f - f2;
        int i4 = 0;
        float f4 = glyphRun.x;
        float[] fArr2 = glyphRun.xAdvances.items;
        while (i4 < glyphRun.xAdvances.size) {
            float f5 = f4 + fArr2[i4];
            f4 = f5;
            if (f5 > f3) {
                break;
            } else {
                i4++;
            }
        }
        if (i4 > 1) {
            glyphRun.glyphs.truncate(i4 - 1);
            glyphRun.xAdvances.truncate(i4);
            setLastGlyphXAdvance(bitmapFontData, glyphRun);
            if (obtain.xAdvances.size > 0) {
                glyphRun.xAdvances.addAll(obtain.xAdvances, 1, obtain.xAdvances.size - 1);
            }
        } else {
            glyphRun.glyphs.clear();
            glyphRun.xAdvances.clear();
            glyphRun.xAdvances.addAll(obtain.xAdvances);
        }
        int i5 = i - glyphRun.glyphs.size;
        if (i5 > 0) {
            this.glyphCount -= i5;
            if (bitmapFontData.markupEnabled) {
                while (this.colors.size > 2 && this.colors.get(this.colors.size - 2) >= this.glyphCount) {
                    this.colors.size -= 2;
                }
            }
        }
        glyphRun.glyphs.addAll(obtain.glyphs);
        this.glyphCount += str.length();
        glyphRunPool.free(obtain);
    }

    private GlyphRun wrap(BitmapFont.BitmapFontData bitmapFontData, GlyphRun glyphRun, int i) {
        int i2;
        Array<BitmapFont.Glyph> array = glyphRun.glyphs;
        int i3 = glyphRun.glyphs.size;
        FloatArray floatArray = glyphRun.xAdvances;
        int i4 = i;
        while (i4 > 0 && bitmapFontData.isWhitespace((char) array.get(i4 - 1).id)) {
            i4--;
        }
        int i5 = i;
        while (i5 < i3 && bitmapFontData.isWhitespace((char) array.get(i5).id)) {
            i5++;
        }
        GlyphRun glyphRun2 = null;
        if (i5 < i3) {
            GlyphRun obtain = glyphRunPool.obtain();
            glyphRun2 = obtain;
            Array<BitmapFont.Glyph> array2 = obtain.glyphs;
            array2.addAll(array, 0, i4);
            array.removeRange(0, i5 - 1);
            glyphRun.glyphs = array2;
            glyphRun2.glyphs = array;
            FloatArray floatArray2 = glyphRun2.xAdvances;
            floatArray2.addAll(floatArray, 0, i4 + 1);
            floatArray.removeRange(1, i5);
            floatArray.items[0] = getLineOffset(array, bitmapFontData);
            glyphRun.xAdvances = floatArray2;
            glyphRun2.xAdvances = floatArray;
            int i6 = glyphRun.glyphs.size;
            int i7 = glyphRun2.glyphs.size;
            int i8 = (i3 - i6) - i7;
            this.glyphCount -= i8;
            if (bitmapFontData.markupEnabled && i8 > 0) {
                int i9 = this.glyphCount - i7;
                for (int i10 = this.colors.size - 2; i10 >= 2 && (i2 = this.colors.get(i10)) > i9; i10 -= 2) {
                    this.colors.set(i10, i2 - i8);
                }
            }
        } else {
            array.truncate(i4);
            floatArray.truncate(i4 + 1);
            int i11 = i5 - i4;
            if (i11 > 0) {
                this.glyphCount -= i11;
                if (bitmapFontData.markupEnabled && this.colors.get(this.colors.size - 2) > this.glyphCount) {
                    int peek = this.colors.peek();
                    while (this.colors.get(this.colors.size - 2) > this.glyphCount) {
                        this.colors.size -= 2;
                    }
                    this.colors.set(this.colors.size - 2, this.glyphCount);
                    this.colors.set(this.colors.size - 1, peek);
                }
            }
        }
        if (i4 == 0) {
            glyphRunPool.free(glyphRun);
            this.runs.pop();
        } else {
            setLastGlyphXAdvance(bitmapFontData, glyphRun);
        }
        return glyphRun2;
    }

    private void setLastGlyphXAdvance(BitmapFont.BitmapFontData bitmapFontData, GlyphRun glyphRun) {
        BitmapFont.Glyph peek = glyphRun.glyphs.peek();
        if (!peek.fixedWidth) {
            glyphRun.xAdvances.items[glyphRun.xAdvances.size - 1] = getGlyphWidth(peek, bitmapFontData);
        }
    }

    private float getGlyphWidth(BitmapFont.Glyph glyph, BitmapFont.BitmapFontData bitmapFontData) {
        return ((glyph.fixedWidth ? glyph.xadvance : glyph.width + glyph.xoffset) * bitmapFontData.scaleX) - bitmapFontData.padRight;
    }

    private float getLineOffset(Array<BitmapFont.Glyph> array, BitmapFont.BitmapFontData bitmapFontData) {
        return (array.first().fixedWidth ? 0.0f : (-r0.xoffset) * bitmapFontData.scaleX) - bitmapFontData.padLeft;
    }

    private int parseColorMarkup(CharSequence charSequence, int i, int i2) {
        if (i == i2) {
            return -1;
        }
        switch (charSequence.charAt(i)) {
            case '#':
                int i3 = 0;
                for (int i4 = i + 1; i4 < i2; i4++) {
                    char charAt = charSequence.charAt(i4);
                    if (charAt != ']') {
                        int i5 = (i3 << 4) + charAt;
                        if (charAt >= '0' && charAt <= '9') {
                            i3 = i5 - 48;
                        } else if (charAt >= 'A' && charAt <= 'F') {
                            i3 = i5 - 55;
                        } else {
                            if (charAt < 'a' || charAt > 'f') {
                                return -1;
                            }
                            i3 = i5 - 87;
                        }
                    } else {
                        if (i4 >= i + 2 && i4 <= i + 9) {
                            if (i4 - i < 8) {
                                i3 = (i3 << ((9 - (i4 - i)) << 2)) | 255;
                            }
                            colorStack.add(Integer.reverseBytes(i3));
                            return i4 - i;
                        }
                        return -1;
                    }
                }
                return -1;
            case '[':
                return -2;
            case ']':
                if (colorStack.size > 1) {
                    colorStack.pop();
                    return 0;
                }
                return 0;
            default:
                for (int i6 = i + 1; i6 < i2; i6++) {
                    if (charSequence.charAt(i6) == ']') {
                        Color color = Colors.get(charSequence.subSequence(i, i6).toString());
                        if (color == null) {
                            return -1;
                        }
                        colorStack.add(color.toIntBits());
                        return i6 - i;
                    }
                }
                return -1;
        }
    }

    @Override // com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        glyphRunPool.freeAll(this.runs);
        this.runs.clear();
        this.colors.clear();
        this.glyphCount = 0;
        this.width = 0.0f;
        this.height = 0.0f;
    }

    public String toString() {
        if (this.runs.size == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(128);
        sb.append(this.width);
        sb.append('x');
        sb.append(this.height);
        sb.append('\n');
        int i = this.runs.size;
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(this.runs.get(i2).toString());
            sb.append('\n');
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/GlyphLayout$GlyphRun.class */
    public static class GlyphRun implements Pool.Poolable {
        public Array<BitmapFont.Glyph> glyphs = new Array<>();
        public FloatArray xAdvances = new FloatArray();
        public float x;
        public float y;
        public float width;

        void appendRun(GlyphRun glyphRun) {
            this.glyphs.addAll(glyphRun.glyphs);
            if (this.xAdvances.notEmpty()) {
                this.xAdvances.size--;
            }
            this.xAdvances.addAll(glyphRun.xAdvances);
        }

        @Override // com.badlogic.gdx.utils.Pool.Poolable
        public void reset() {
            this.glyphs.clear();
            this.xAdvances.clear();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(this.glyphs.size + 32);
            Array<BitmapFont.Glyph> array = this.glyphs;
            int i = array.size;
            for (int i2 = 0; i2 < i; i2++) {
                sb.append((char) array.get(i2).id);
            }
            sb.append(", ");
            sb.append(this.x);
            sb.append(", ");
            sb.append(this.y);
            sb.append(", ");
            sb.append(this.width);
            return sb.toString();
        }
    }
}
