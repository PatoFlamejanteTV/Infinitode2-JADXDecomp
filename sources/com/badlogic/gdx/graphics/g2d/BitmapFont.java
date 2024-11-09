package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/BitmapFont.class */
public class BitmapFont implements Disposable {
    private static final int LOG2_PAGE_SIZE = 9;
    private static final int PAGE_SIZE = 512;
    private static final int PAGES = 128;
    final BitmapFontData data;
    Array<TextureRegion> regions;
    private final BitmapFontCache cache;
    private boolean flipped;
    boolean integer;
    private boolean ownsTexture;

    public BitmapFont() {
        this(Gdx.files.classpath("com/badlogic/gdx/utils/lsans-15.fnt"), Gdx.files.classpath("com/badlogic/gdx/utils/lsans-15.png"), false, true);
    }

    public BitmapFont(boolean z) {
        this(Gdx.files.classpath("com/badlogic/gdx/utils/lsans-15.fnt"), Gdx.files.classpath("com/badlogic/gdx/utils/lsans-15.png"), z, true);
    }

    public BitmapFont(FileHandle fileHandle, TextureRegion textureRegion) {
        this(fileHandle, textureRegion, false);
    }

    public BitmapFont(FileHandle fileHandle, TextureRegion textureRegion, boolean z) {
        this(new BitmapFontData(fileHandle, z), textureRegion, true);
    }

    public BitmapFont(FileHandle fileHandle) {
        this(fileHandle, false);
    }

    public BitmapFont(FileHandle fileHandle, boolean z) {
        this(new BitmapFontData(fileHandle, z), (TextureRegion) null, true);
    }

    public BitmapFont(FileHandle fileHandle, FileHandle fileHandle2, boolean z) {
        this(fileHandle, fileHandle2, z, true);
    }

    public BitmapFont(FileHandle fileHandle, FileHandle fileHandle2, boolean z, boolean z2) {
        this(new BitmapFontData(fileHandle, z), new TextureRegion(new Texture(fileHandle2, false)), z2);
        this.ownsTexture = true;
    }

    public BitmapFont(BitmapFontData bitmapFontData, TextureRegion textureRegion, boolean z) {
        this(bitmapFontData, (Array<TextureRegion>) (textureRegion != null ? Array.with(textureRegion) : null), z);
    }

    public BitmapFont(BitmapFontData bitmapFontData, Array<TextureRegion> array, boolean z) {
        FileHandle fileHandle;
        this.flipped = bitmapFontData.flipped;
        this.data = bitmapFontData;
        this.integer = z;
        if (array == null || array.size == 0) {
            if (bitmapFontData.imagePaths == null) {
                throw new IllegalArgumentException("If no regions are specified, the font data must have an images path.");
            }
            int length = bitmapFontData.imagePaths.length;
            this.regions = new Array<>(length);
            for (int i = 0; i < length; i++) {
                if (bitmapFontData.fontFile == null) {
                    fileHandle = Gdx.files.internal(bitmapFontData.imagePaths[i]);
                } else {
                    fileHandle = Gdx.files.getFileHandle(bitmapFontData.imagePaths[i], bitmapFontData.fontFile.type());
                }
                this.regions.add(new TextureRegion(new Texture(fileHandle, false)));
            }
            this.ownsTexture = true;
        } else {
            this.regions = array;
            this.ownsTexture = false;
        }
        this.cache = newFontCache();
        load(bitmapFontData);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void load(BitmapFontData bitmapFontData) {
        for (Glyph[] glyphArr : bitmapFontData.glyphs) {
            if (glyphArr != null) {
                for (Glyph glyph : glyphArr) {
                    if (glyph != null) {
                        bitmapFontData.setGlyphRegion(glyph, this.regions.get(glyph.page));
                    }
                }
            }
        }
        if (bitmapFontData.missingGlyph != null) {
            bitmapFontData.setGlyphRegion(bitmapFontData.missingGlyph, this.regions.get(bitmapFontData.missingGlyph.page));
        }
    }

    public GlyphLayout draw(Batch batch, CharSequence charSequence, float f, float f2) {
        this.cache.clear();
        GlyphLayout addText = this.cache.addText(charSequence, f, f2);
        this.cache.draw(batch);
        return addText;
    }

    public GlyphLayout draw(Batch batch, CharSequence charSequence, float f, float f2, float f3, int i, boolean z) {
        this.cache.clear();
        GlyphLayout addText = this.cache.addText(charSequence, f, f2, f3, i, z);
        this.cache.draw(batch);
        return addText;
    }

    public GlyphLayout draw(Batch batch, CharSequence charSequence, float f, float f2, int i, int i2, float f3, int i3, boolean z) {
        this.cache.clear();
        GlyphLayout addText = this.cache.addText(charSequence, f, f2, i, i2, f3, i3, z);
        this.cache.draw(batch);
        return addText;
    }

    public GlyphLayout draw(Batch batch, CharSequence charSequence, float f, float f2, int i, int i2, float f3, int i3, boolean z, String str) {
        this.cache.clear();
        GlyphLayout addText = this.cache.addText(charSequence, f, f2, i, i2, f3, i3, z, str);
        this.cache.draw(batch);
        return addText;
    }

    public void draw(Batch batch, GlyphLayout glyphLayout, float f, float f2) {
        this.cache.clear();
        this.cache.addText(glyphLayout, f, f2);
        this.cache.draw(batch);
    }

    public Color getColor() {
        return this.cache.getColor();
    }

    public void setColor(Color color) {
        this.cache.getColor().set(color);
    }

    public void setColor(float f, float f2, float f3, float f4) {
        this.cache.getColor().set(f, f2, f3, f4);
    }

    public float getScaleX() {
        return this.data.scaleX;
    }

    public float getScaleY() {
        return this.data.scaleY;
    }

    public TextureRegion getRegion() {
        return this.regions.first();
    }

    public Array<TextureRegion> getRegions() {
        return this.regions;
    }

    public TextureRegion getRegion(int i) {
        return this.regions.get(i);
    }

    public float getLineHeight() {
        return this.data.lineHeight;
    }

    public float getSpaceXadvance() {
        return this.data.spaceXadvance;
    }

    public float getXHeight() {
        return this.data.xHeight;
    }

    public float getCapHeight() {
        return this.data.capHeight;
    }

    public float getAscent() {
        return this.data.ascent;
    }

    public float getDescent() {
        return this.data.descent;
    }

    public boolean isFlipped() {
        return this.flipped;
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if (this.ownsTexture) {
            for (int i = 0; i < this.regions.size; i++) {
                this.regions.get(i).getTexture().dispose();
            }
        }
    }

    public void setFixedWidthGlyphs(CharSequence charSequence) {
        BitmapFontData bitmapFontData = this.data;
        int i = 0;
        int length = charSequence.length();
        for (int i2 = 0; i2 < length; i2++) {
            Glyph glyph = bitmapFontData.getGlyph(charSequence.charAt(i2));
            if (glyph != null && glyph.xadvance > i) {
                i = glyph.xadvance;
            }
        }
        int length2 = charSequence.length();
        for (int i3 = 0; i3 < length2; i3++) {
            Glyph glyph2 = bitmapFontData.getGlyph(charSequence.charAt(i3));
            if (glyph2 != null) {
                glyph2.xoffset += (i - glyph2.xadvance) / 2;
                glyph2.xadvance = i;
                glyph2.kerning = null;
                glyph2.fixedWidth = true;
            }
        }
    }

    public void setUseIntegerPositions(boolean z) {
        this.integer = z;
        this.cache.setUseIntegerPositions(z);
    }

    public boolean usesIntegerPositions() {
        return this.integer;
    }

    public BitmapFontCache getCache() {
        return this.cache;
    }

    public BitmapFontData getData() {
        return this.data;
    }

    public boolean ownsTexture() {
        return this.ownsTexture;
    }

    public void setOwnsTexture(boolean z) {
        this.ownsTexture = z;
    }

    public BitmapFontCache newFontCache() {
        return new BitmapFontCache(this, this.integer);
    }

    public String toString() {
        return this.data.name != null ? this.data.name : super.toString();
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/BitmapFont$Glyph.class */
    public static class Glyph {
        public int id;
        public int srcX;
        public int srcY;
        public int width;
        public int height;
        public float u;
        public float v;
        public float u2;
        public float v2;
        public int xoffset;
        public int yoffset;
        public int xadvance;
        public byte[][] kerning;
        public boolean fixedWidth;
        public int page = 0;

        public int getKerning(char c) {
            byte[] bArr;
            if (this.kerning != null && (bArr = this.kerning[c >>> '\t']) != null) {
                return bArr[c & 511];
            }
            return 0;
        }

        /* JADX WARN: Type inference failed for: r1v8, types: [byte[], byte[][]] */
        public void setKerning(int i, int i2) {
            if (this.kerning == null) {
                this.kerning = new byte[128];
            }
            byte[] bArr = this.kerning[i >>> 9];
            byte[] bArr2 = bArr;
            if (bArr == null) {
                byte[] bArr3 = new byte[512];
                bArr2 = bArr3;
                this.kerning[i >>> 9] = bArr3;
            }
            bArr2[i & 511] = (byte) i2;
        }

        public String toString() {
            return Character.toString((char) this.id);
        }
    }

    static int indexOf(CharSequence charSequence, char c, int i) {
        int length = charSequence.length();
        while (i < length) {
            if (charSequence.charAt(i) == c) {
                return i;
            }
            i++;
        }
        return length;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/BitmapFont$BitmapFontData.class */
    public static class BitmapFontData {
        public String name;
        public String[] imagePaths;
        public FileHandle fontFile;
        public boolean flipped;
        public float padTop;
        public float padRight;
        public float padBottom;
        public float padLeft;
        public float lineHeight;
        public float capHeight;
        public float ascent;
        public float descent;
        public float down;
        public float blankLineScale;
        public float scaleX;
        public float scaleY;
        public boolean markupEnabled;
        public float cursorX;
        public final Glyph[][] glyphs;
        public Glyph missingGlyph;
        public float spaceXadvance;
        public float xHeight;
        public char[] breakChars;
        public char[] xChars;
        public char[] capChars;

        /* JADX WARN: Type inference failed for: r1v5, types: [com.badlogic.gdx.graphics.g2d.BitmapFont$Glyph[], com.badlogic.gdx.graphics.g2d.BitmapFont$Glyph[][]] */
        public BitmapFontData() {
            this.capHeight = 1.0f;
            this.blankLineScale = 1.0f;
            this.scaleX = 1.0f;
            this.scaleY = 1.0f;
            this.glyphs = new Glyph[128];
            this.xHeight = 1.0f;
            this.xChars = new char[]{'x', 'e', 'a', 'o', 'n', 's', 'r', 'c', 'u', 'm', 'v', 'w', 'z'};
            this.capChars = new char[]{'M', 'N', 'B', 'D', 'C', 'E', 'F', 'K', 'A', 'G', 'H', 'I', 'J', 'L', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        }

        /* JADX WARN: Type inference failed for: r1v5, types: [com.badlogic.gdx.graphics.g2d.BitmapFont$Glyph[], com.badlogic.gdx.graphics.g2d.BitmapFont$Glyph[][]] */
        public BitmapFontData(FileHandle fileHandle, boolean z) {
            this.capHeight = 1.0f;
            this.blankLineScale = 1.0f;
            this.scaleX = 1.0f;
            this.scaleY = 1.0f;
            this.glyphs = new Glyph[128];
            this.xHeight = 1.0f;
            this.xChars = new char[]{'x', 'e', 'a', 'o', 'n', 's', 'r', 'c', 'u', 'm', 'v', 'w', 'z'};
            this.capChars = new char[]{'M', 'N', 'B', 'D', 'C', 'E', 'F', 'K', 'A', 'G', 'H', 'I', 'J', 'L', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
            this.fontFile = fileHandle;
            this.flipped = z;
            load(fileHandle, z);
        }

        public void load(FileHandle fileHandle, boolean z) {
            String readLine;
            if (this.imagePaths != null) {
                throw new IllegalStateException("Already loaded.");
            }
            this.name = fileHandle.nameWithoutExtension();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileHandle.read()), 512);
            try {
                try {
                    String readLine2 = bufferedReader.readLine();
                    if (readLine2 == null) {
                        throw new GdxRuntimeException("File is empty.");
                    }
                    String substring = readLine2.substring(readLine2.indexOf("padding=") + 8);
                    if (substring.substring(0, substring.indexOf(32)).split(",", 4).length != 4) {
                        throw new GdxRuntimeException("Invalid padding.");
                    }
                    this.padTop = Integer.parseInt(r0[0]);
                    this.padRight = Integer.parseInt(r0[1]);
                    this.padBottom = Integer.parseInt(r0[2]);
                    this.padLeft = Integer.parseInt(r0[3]);
                    float f = this.padTop + this.padBottom;
                    String readLine3 = bufferedReader.readLine();
                    if (readLine3 == null) {
                        throw new GdxRuntimeException("Missing common header.");
                    }
                    String[] split = readLine3.split(SequenceUtils.SPACE, 9);
                    if (split.length < 3) {
                        throw new GdxRuntimeException("Invalid common header.");
                    }
                    if (!split[1].startsWith("lineHeight=")) {
                        throw new GdxRuntimeException("Missing: lineHeight");
                    }
                    this.lineHeight = Integer.parseInt(split[1].substring(11));
                    if (!split[2].startsWith("base=")) {
                        throw new GdxRuntimeException("Missing: base");
                    }
                    float parseInt = Integer.parseInt(split[2].substring(5));
                    int i = 1;
                    if (split.length >= 6 && split[5] != null && split[5].startsWith("pages=")) {
                        try {
                            i = Math.max(1, Integer.parseInt(split[5].substring(6)));
                        } catch (NumberFormatException unused) {
                        }
                    }
                    this.imagePaths = new String[i];
                    for (int i2 = 0; i2 < i; i2++) {
                        String readLine4 = bufferedReader.readLine();
                        if (readLine4 == null) {
                            throw new GdxRuntimeException("Missing additional page definitions.");
                        }
                        Matcher matcher = Pattern.compile(".*id=(\\d+)").matcher(readLine4);
                        if (matcher.find()) {
                            String group = matcher.group(1);
                            try {
                                if (Integer.parseInt(group) != i2) {
                                    throw new GdxRuntimeException("Page IDs must be indices starting at 0: " + group);
                                }
                            } catch (NumberFormatException e) {
                                throw new GdxRuntimeException("Invalid page id: " + group, e);
                            }
                        }
                        Matcher matcher2 = Pattern.compile(".*file=\"?([^\"]+)\"?").matcher(readLine4);
                        if (!matcher2.find()) {
                            throw new GdxRuntimeException("Missing: file");
                        }
                        this.imagePaths[i2] = fileHandle.parent().child(matcher2.group(1)).path().replaceAll("\\\\", "/");
                    }
                    this.descent = 0.0f;
                    while (true) {
                        String readLine5 = bufferedReader.readLine();
                        if (readLine5 == null || readLine5.startsWith("kernings ") || readLine5.startsWith("metrics ")) {
                            break;
                        }
                        if (readLine5.startsWith("char ")) {
                            Glyph glyph = new Glyph();
                            StringTokenizer stringTokenizer = new StringTokenizer(readLine5, " =");
                            stringTokenizer.nextToken();
                            stringTokenizer.nextToken();
                            int parseInt2 = Integer.parseInt(stringTokenizer.nextToken());
                            if (parseInt2 <= 0) {
                                this.missingGlyph = glyph;
                            } else if (parseInt2 <= 65535) {
                                setGlyph(parseInt2, glyph);
                            }
                            glyph.id = parseInt2;
                            stringTokenizer.nextToken();
                            glyph.srcX = Integer.parseInt(stringTokenizer.nextToken());
                            stringTokenizer.nextToken();
                            glyph.srcY = Integer.parseInt(stringTokenizer.nextToken());
                            stringTokenizer.nextToken();
                            glyph.width = Integer.parseInt(stringTokenizer.nextToken());
                            stringTokenizer.nextToken();
                            glyph.height = Integer.parseInt(stringTokenizer.nextToken());
                            stringTokenizer.nextToken();
                            glyph.xoffset = Integer.parseInt(stringTokenizer.nextToken());
                            stringTokenizer.nextToken();
                            if (z) {
                                glyph.yoffset = Integer.parseInt(stringTokenizer.nextToken());
                            } else {
                                glyph.yoffset = -(glyph.height + Integer.parseInt(stringTokenizer.nextToken()));
                            }
                            stringTokenizer.nextToken();
                            glyph.xadvance = Integer.parseInt(stringTokenizer.nextToken());
                            if (stringTokenizer.hasMoreTokens()) {
                                stringTokenizer.nextToken();
                            }
                            if (stringTokenizer.hasMoreTokens()) {
                                try {
                                    glyph.page = Integer.parseInt(stringTokenizer.nextToken());
                                } catch (NumberFormatException unused2) {
                                }
                            }
                            if (glyph.width > 0 && glyph.height > 0) {
                                this.descent = Math.min(parseInt + glyph.yoffset, this.descent);
                            }
                        }
                    }
                    this.descent += this.padBottom;
                    while (true) {
                        readLine = bufferedReader.readLine();
                        if (readLine == null || !readLine.startsWith("kerning ")) {
                            break;
                        }
                        StringTokenizer stringTokenizer2 = new StringTokenizer(readLine, " =");
                        stringTokenizer2.nextToken();
                        stringTokenizer2.nextToken();
                        int parseInt3 = Integer.parseInt(stringTokenizer2.nextToken());
                        stringTokenizer2.nextToken();
                        int parseInt4 = Integer.parseInt(stringTokenizer2.nextToken());
                        if (parseInt3 >= 0 && parseInt3 <= 65535 && parseInt4 >= 0 && parseInt4 <= 65535) {
                            Glyph glyph2 = getGlyph((char) parseInt3);
                            stringTokenizer2.nextToken();
                            int parseInt5 = Integer.parseInt(stringTokenizer2.nextToken());
                            if (glyph2 != null) {
                                glyph2.setKerning(parseInt4, parseInt5);
                            }
                        }
                    }
                    boolean z2 = false;
                    float f2 = 0.0f;
                    float f3 = 0.0f;
                    float f4 = 0.0f;
                    float f5 = 0.0f;
                    float f6 = 0.0f;
                    float f7 = 0.0f;
                    float f8 = 0.0f;
                    if (readLine != null && readLine.startsWith("metrics ")) {
                        z2 = true;
                        StringTokenizer stringTokenizer3 = new StringTokenizer(readLine, " =");
                        stringTokenizer3.nextToken();
                        stringTokenizer3.nextToken();
                        f2 = Float.parseFloat(stringTokenizer3.nextToken());
                        stringTokenizer3.nextToken();
                        f3 = Float.parseFloat(stringTokenizer3.nextToken());
                        stringTokenizer3.nextToken();
                        f4 = Float.parseFloat(stringTokenizer3.nextToken());
                        stringTokenizer3.nextToken();
                        f5 = Float.parseFloat(stringTokenizer3.nextToken());
                        stringTokenizer3.nextToken();
                        f6 = Float.parseFloat(stringTokenizer3.nextToken());
                        stringTokenizer3.nextToken();
                        f7 = Float.parseFloat(stringTokenizer3.nextToken());
                        stringTokenizer3.nextToken();
                        f8 = Float.parseFloat(stringTokenizer3.nextToken());
                    }
                    Glyph glyph3 = getGlyph(' ');
                    Glyph glyph4 = glyph3;
                    if (glyph3 == null) {
                        Glyph glyph5 = new Glyph();
                        glyph4 = glyph5;
                        glyph5.id = 32;
                        Glyph glyph6 = getGlyph('l');
                        Glyph glyph7 = glyph6;
                        if (glyph6 == null) {
                            glyph7 = getFirstGlyph();
                        }
                        glyph4.xadvance = glyph7.xadvance;
                        setGlyph(32, glyph4);
                    }
                    if (glyph4.width == 0) {
                        glyph4.width = (int) (this.padLeft + glyph4.xadvance + this.padRight);
                        glyph4.xoffset = (int) (-this.padLeft);
                    }
                    this.spaceXadvance = glyph4.xadvance;
                    Glyph glyph8 = null;
                    for (char c : this.xChars) {
                        Glyph glyph9 = getGlyph(c);
                        glyph8 = glyph9;
                        if (glyph9 != null) {
                            break;
                        }
                    }
                    if (glyph8 == null) {
                        glyph8 = getFirstGlyph();
                    }
                    this.xHeight = glyph8.height - f;
                    Glyph glyph10 = null;
                    for (char c2 : this.capChars) {
                        Glyph glyph11 = getGlyph(c2);
                        glyph10 = glyph11;
                        if (glyph11 != null) {
                            break;
                        }
                    }
                    if (glyph10 == null) {
                        for (Glyph[] glyphArr : this.glyphs) {
                            if (glyphArr != null) {
                                for (Glyph glyph12 : glyphArr) {
                                    if (glyph12 != null && glyph12.height != 0 && glyph12.width != 0) {
                                        this.capHeight = Math.max(this.capHeight, glyph12.height);
                                    }
                                }
                            }
                        }
                    } else {
                        this.capHeight = glyph10.height;
                    }
                    this.capHeight -= f;
                    this.ascent = parseInt - this.capHeight;
                    this.down = -this.lineHeight;
                    if (z) {
                        this.ascent = -this.ascent;
                        this.down = -this.down;
                    }
                    if (z2) {
                        this.ascent = f2;
                        this.descent = f3;
                        this.down = f4;
                        this.capHeight = f5;
                        this.lineHeight = f6;
                        this.spaceXadvance = f7;
                        this.xHeight = f8;
                    }
                } catch (Exception e2) {
                    throw new GdxRuntimeException("Error loading font file: " + fileHandle, e2);
                }
            } finally {
                StreamUtils.closeQuietly(bufferedReader);
            }
        }

        public void setGlyphRegion(Glyph glyph, TextureRegion textureRegion) {
            Texture texture = textureRegion.getTexture();
            float width = 1.0f / texture.getWidth();
            float height = 1.0f / texture.getHeight();
            float f = 0.0f;
            float f2 = 0.0f;
            float f3 = textureRegion.u;
            float f4 = textureRegion.v;
            float regionWidth = textureRegion.getRegionWidth();
            float regionHeight = textureRegion.getRegionHeight();
            if (textureRegion instanceof TextureAtlas.AtlasRegion) {
                TextureAtlas.AtlasRegion atlasRegion = (TextureAtlas.AtlasRegion) textureRegion;
                f = atlasRegion.offsetX;
                f2 = (atlasRegion.originalHeight - atlasRegion.packedHeight) - atlasRegion.offsetY;
            }
            float f5 = glyph.srcX;
            float f6 = glyph.srcX + glyph.width;
            float f7 = glyph.srcY;
            float f8 = glyph.srcY + glyph.height;
            if (f > 0.0f) {
                float f9 = f5 - f;
                f5 = f9;
                if (f9 < 0.0f) {
                    glyph.width = (int) (glyph.width + f5);
                    glyph.xoffset = (int) (glyph.xoffset - f5);
                    f5 = 0.0f;
                }
                float f10 = f6 - f;
                f6 = f10;
                if (f10 > regionWidth) {
                    glyph.width = (int) (glyph.width - (f6 - regionWidth));
                    f6 = regionWidth;
                }
            }
            if (f2 > 0.0f) {
                float f11 = f7 - f2;
                f7 = f11;
                if (f11 < 0.0f) {
                    glyph.height = (int) (glyph.height + f7);
                    if (glyph.height < 0) {
                        glyph.height = 0;
                    }
                    f7 = 0.0f;
                }
                float f12 = f8 - f2;
                f8 = f12;
                if (f12 > regionHeight) {
                    float f13 = f8 - regionHeight;
                    glyph.height = (int) (glyph.height - f13);
                    glyph.yoffset = (int) (glyph.yoffset + f13);
                    f8 = regionHeight;
                }
            }
            glyph.u = f3 + (f5 * width);
            glyph.u2 = f3 + (f6 * width);
            if (this.flipped) {
                glyph.v = f4 + (f7 * height);
                glyph.v2 = f4 + (f8 * height);
            } else {
                glyph.v2 = f4 + (f7 * height);
                glyph.v = f4 + (f8 * height);
            }
        }

        public void setLineHeight(float f) {
            this.lineHeight = f * this.scaleY;
            this.down = this.flipped ? this.lineHeight : -this.lineHeight;
        }

        public void setGlyph(int i, Glyph glyph) {
            Glyph[] glyphArr = this.glyphs[i / 512];
            Glyph[] glyphArr2 = glyphArr;
            if (glyphArr == null) {
                Glyph[] glyphArr3 = new Glyph[512];
                glyphArr2 = glyphArr3;
                this.glyphs[i / 512] = glyphArr3;
            }
            glyphArr2[i & 511] = glyph;
        }

        public Glyph getFirstGlyph() {
            for (Glyph[] glyphArr : this.glyphs) {
                if (glyphArr != null) {
                    for (Glyph glyph : glyphArr) {
                        if (glyph != null && glyph.height != 0 && glyph.width != 0) {
                            return glyph;
                        }
                    }
                }
            }
            throw new GdxRuntimeException("No glyphs found.");
        }

        public boolean hasGlyph(char c) {
            return (this.missingGlyph == null && getGlyph(c) == null) ? false : true;
        }

        public Glyph getGlyph(char c) {
            Glyph[] glyphArr = this.glyphs[c / 512];
            if (glyphArr != null) {
                return glyphArr[c & 511];
            }
            return null;
        }

        public void getGlyphs(GlyphLayout.GlyphRun glyphRun, CharSequence charSequence, int i, int i2, Glyph glyph) {
            float kerning;
            int i3 = i2 - i;
            if (i3 == 0) {
                return;
            }
            boolean z = this.markupEnabled;
            float f = this.scaleX;
            Array<Glyph> array = glyphRun.glyphs;
            FloatArray floatArray = glyphRun.xAdvances;
            array.ensureCapacity(i3);
            glyphRun.xAdvances.ensureCapacity(i3 + 1);
            do {
                int i4 = i;
                i++;
                char charAt = charSequence.charAt(i4);
                if (charAt != '\r') {
                    Glyph glyph2 = getGlyph(charAt);
                    Glyph glyph3 = glyph2;
                    if (glyph2 == null) {
                        if (this.missingGlyph != null) {
                            glyph3 = this.missingGlyph;
                        }
                    }
                    array.add(glyph3);
                    if (glyph != null) {
                        kerning = (glyph.xadvance + glyph.getKerning(charAt)) * f;
                    } else {
                        kerning = glyph3.fixedWidth ? 0.0f : ((-glyph3.xoffset) * f) - this.padLeft;
                    }
                    floatArray.add(kerning);
                    glyph = glyph3;
                    if (z && charAt == '[' && i < i2 && charSequence.charAt(i) == '[') {
                        i++;
                    }
                }
            } while (i < i2);
            if (glyph != null) {
                floatArray.add(glyph.fixedWidth ? glyph.xadvance * f : ((glyph.width + glyph.xoffset) * f) - this.padRight);
            }
        }

        public int getWrapIndex(Array<Glyph> array, int i) {
            int i2 = i - 1;
            Glyph[] glyphArr = array.items;
            char c = (char) glyphArr[i2].id;
            if (isWhitespace(c)) {
                return i2;
            }
            if (isBreakChar(c)) {
                i2--;
            }
            while (i2 > 0) {
                char c2 = (char) glyphArr[i2].id;
                if (isWhitespace(c2) || isBreakChar(c2)) {
                    return i2 + 1;
                }
                i2--;
            }
            return 0;
        }

        public boolean isBreakChar(char c) {
            if (this.breakChars == null) {
                return false;
            }
            for (char c2 : this.breakChars) {
                if (c == c2) {
                    return true;
                }
            }
            return false;
        }

        public boolean isWhitespace(char c) {
            switch (c) {
                case '\t':
                case '\n':
                case '\r':
                case ' ':
                    return true;
                default:
                    return false;
            }
        }

        public String getImagePath(int i) {
            return this.imagePaths[i];
        }

        public String[] getImagePaths() {
            return this.imagePaths;
        }

        public FileHandle getFontFile() {
            return this.fontFile;
        }

        public void setScale(float f, float f2) {
            if (f == 0.0f) {
                throw new IllegalArgumentException("scaleX cannot be 0.");
            }
            if (f2 == 0.0f) {
                throw new IllegalArgumentException("scaleY cannot be 0.");
            }
            float f3 = f / this.scaleX;
            float f4 = f2 / this.scaleY;
            this.lineHeight *= f4;
            this.spaceXadvance *= f3;
            this.xHeight *= f4;
            this.capHeight *= f4;
            this.ascent *= f4;
            this.descent *= f4;
            this.down *= f4;
            this.padLeft *= f3;
            this.padRight *= f3;
            this.padTop *= f4;
            this.padBottom *= f4;
            this.scaleX = f;
            this.scaleY = f2;
        }

        public void setScale(float f) {
            setScale(f, f);
        }

        public void scale(float f) {
            setScale(this.scaleX + f, this.scaleY + f);
        }

        public String toString() {
            return this.name != null ? this.name : super.toString();
        }
    }
}
