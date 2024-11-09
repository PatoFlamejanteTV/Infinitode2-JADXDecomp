package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.PixmapTextureData;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.OrderedMap;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/PixmapPacker.class */
public class PixmapPacker implements Disposable {
    boolean packToTexture;
    boolean disposed;
    int pageWidth;
    int pageHeight;
    Pixmap.Format pageFormat;
    int padding;
    boolean duplicateBorder;
    boolean stripWhitespaceX;
    boolean stripWhitespaceY;
    int alphaThreshold;
    Color transparentColor;
    final Array<Page> pages;
    PackStrategy packStrategy;
    static Pattern indexPattern = Pattern.compile("(.+)_(\\d+)$");
    private Color c;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/PixmapPacker$PackStrategy.class */
    public interface PackStrategy {
        void sort(Array<Pixmap> array);

        Page pack(PixmapPacker pixmapPacker, String str, Rectangle rectangle);
    }

    public PixmapPacker(int i, int i2, Pixmap.Format format, int i3, boolean z) {
        this(i, i2, format, i3, z, false, false, new GuillotineStrategy());
    }

    public PixmapPacker(int i, int i2, Pixmap.Format format, int i3, boolean z, PackStrategy packStrategy) {
        this(i, i2, format, i3, z, false, false, packStrategy);
    }

    public PixmapPacker(int i, int i2, Pixmap.Format format, int i3, boolean z, boolean z2, boolean z3, PackStrategy packStrategy) {
        this.transparentColor = new Color(0.0f, 0.0f, 0.0f, 0.0f);
        this.pages = new Array<>();
        this.c = new Color();
        this.pageWidth = i;
        this.pageHeight = i2;
        this.pageFormat = format;
        this.padding = i3;
        this.duplicateBorder = z;
        this.stripWhitespaceX = z2;
        this.stripWhitespaceY = z3;
        this.packStrategy = packStrategy;
    }

    public void sort(Array<Pixmap> array) {
        this.packStrategy.sort(array);
    }

    public synchronized PixmapPackerRectangle pack(Pixmap pixmap) {
        return pack(null, pixmap);
    }

    public synchronized PixmapPackerRectangle pack(String str, Pixmap pixmap) {
        PixmapPackerRectangle pixmapPackerRectangle;
        if (this.disposed) {
            return null;
        }
        if (str != null && getRect(str) != null) {
            throw new GdxRuntimeException("Pixmap has already been packed with name: " + str);
        }
        Pixmap pixmap2 = null;
        if (str != null && str.endsWith(".9")) {
            pixmapPackerRectangle = new PixmapPackerRectangle(0, 0, pixmap.getWidth() - 2, pixmap.getHeight() - 2);
            Pixmap pixmap3 = new Pixmap(pixmap.getWidth() - 2, pixmap.getHeight() - 2, pixmap.getFormat());
            pixmap2 = pixmap3;
            pixmap3.setBlending(Pixmap.Blending.None);
            pixmapPackerRectangle.splits = getSplits(pixmap);
            pixmapPackerRectangle.pads = getPads(pixmap, pixmapPackerRectangle.splits);
            pixmap2.drawPixmap(pixmap, 0, 0, 1, 1, pixmap.getWidth() - 1, pixmap.getHeight() - 1);
            pixmap = pixmap2;
            str = str.split("\\.")[0];
        } else if (this.stripWhitespaceX || this.stripWhitespaceY) {
            int width = pixmap.getWidth();
            int height = pixmap.getHeight();
            int i = 0;
            int height2 = pixmap.getHeight();
            if (this.stripWhitespaceY) {
                loop0: for (int i2 = 0; i2 < pixmap.getHeight(); i2++) {
                    for (int i3 = 0; i3 < pixmap.getWidth(); i3++) {
                        if ((pixmap.getPixel(i3, i2) & 255) > this.alphaThreshold) {
                            break loop0;
                        }
                    }
                    i++;
                }
                int height3 = pixmap.getHeight();
                loop2: while (true) {
                    height3--;
                    if (height3 < i) {
                        break;
                    }
                    for (int i4 = 0; i4 < pixmap.getWidth(); i4++) {
                        if ((pixmap.getPixel(i4, height3) & 255) > this.alphaThreshold) {
                            break loop2;
                        }
                    }
                    height2--;
                }
            }
            int i5 = 0;
            int width2 = pixmap.getWidth();
            if (this.stripWhitespaceX) {
                loop4: for (int i6 = 0; i6 < pixmap.getWidth(); i6++) {
                    for (int i7 = i; i7 < height2; i7++) {
                        if ((pixmap.getPixel(i6, i7) & 255) > this.alphaThreshold) {
                            break loop4;
                        }
                    }
                    i5++;
                }
                int width3 = pixmap.getWidth();
                loop6: while (true) {
                    width3--;
                    if (width3 < i5) {
                        break;
                    }
                    for (int i8 = i; i8 < height2; i8++) {
                        if ((pixmap.getPixel(width3, i8) & 255) > this.alphaThreshold) {
                            break loop6;
                        }
                    }
                    width2--;
                }
            }
            int i9 = width2 - i5;
            int i10 = height2 - i;
            Pixmap pixmap4 = new Pixmap(i9, i10, pixmap.getFormat());
            pixmap2 = pixmap4;
            pixmap4.setBlending(Pixmap.Blending.None);
            pixmap2.drawPixmap(pixmap, 0, 0, i5, i, i9, i10);
            pixmap = pixmap2;
            pixmapPackerRectangle = new PixmapPackerRectangle(0, 0, i9, i10, i5, i, width, height);
        } else {
            pixmapPackerRectangle = new PixmapPackerRectangle(0, 0, pixmap.getWidth(), pixmap.getHeight());
        }
        if (pixmapPackerRectangle.getWidth() > this.pageWidth || pixmapPackerRectangle.getHeight() > this.pageHeight) {
            if (str == null) {
                throw new GdxRuntimeException("Page size too small for pixmap.");
            }
            throw new GdxRuntimeException("Page size too small for pixmap: " + str);
        }
        Page pack = this.packStrategy.pack(this, str, pixmapPackerRectangle);
        if (str != null) {
            pack.rects.put(str, pixmapPackerRectangle);
            pack.addedRects.add(str);
        }
        int i11 = (int) pixmapPackerRectangle.x;
        int i12 = (int) pixmapPackerRectangle.y;
        int i13 = (int) pixmapPackerRectangle.width;
        int i14 = (int) pixmapPackerRectangle.height;
        if (this.packToTexture && !this.duplicateBorder && pack.texture != null && !pack.dirty) {
            pack.texture.bind();
            Gdx.gl.glTexSubImage2D(pack.texture.glTarget, 0, i11, i12, i13, i14, pixmap.getGLFormat(), pixmap.getGLType(), pixmap.getPixels());
        } else {
            pack.dirty = true;
        }
        pack.image.drawPixmap(pixmap, i11, i12);
        if (this.duplicateBorder) {
            int width4 = pixmap.getWidth();
            int height4 = pixmap.getHeight();
            pack.image.drawPixmap(pixmap, 0, 0, 1, 1, i11 - 1, i12 - 1, 1, 1);
            pack.image.drawPixmap(pixmap, width4 - 1, 0, 1, 1, i11 + i13, i12 - 1, 1, 1);
            pack.image.drawPixmap(pixmap, 0, height4 - 1, 1, 1, i11 - 1, i12 + i14, 1, 1);
            pack.image.drawPixmap(pixmap, width4 - 1, height4 - 1, 1, 1, i11 + i13, i12 + i14, 1, 1);
            pack.image.drawPixmap(pixmap, 0, 0, width4, 1, i11, i12 - 1, i13, 1);
            pack.image.drawPixmap(pixmap, 0, height4 - 1, width4, 1, i11, i12 + i14, i13, 1);
            pack.image.drawPixmap(pixmap, 0, 0, 1, height4, i11 - 1, i12, 1, i14);
            pack.image.drawPixmap(pixmap, width4 - 1, 0, 1, height4, i11 + i13, i12, 1, i14);
        }
        if (pixmap2 != null) {
            pixmap2.dispose();
        }
        pixmapPackerRectangle.page = pack;
        return pixmapPackerRectangle;
    }

    public Array<Page> getPages() {
        return this.pages;
    }

    public synchronized Rectangle getRect(String str) {
        Array.ArrayIterator<Page> it = this.pages.iterator();
        while (it.hasNext()) {
            PixmapPackerRectangle pixmapPackerRectangle = it.next().rects.get(str);
            if (pixmapPackerRectangle != null) {
                return pixmapPackerRectangle;
            }
        }
        return null;
    }

    public synchronized Page getPage(String str) {
        Array.ArrayIterator<Page> it = this.pages.iterator();
        while (it.hasNext()) {
            Page next = it.next();
            if (next.rects.get(str) != null) {
                return next;
            }
        }
        return null;
    }

    public synchronized int getPageIndex(String str) {
        for (int i = 0; i < this.pages.size; i++) {
            if (this.pages.get(i).rects.get(str) != null) {
                return i;
            }
        }
        return -1;
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public synchronized void dispose() {
        Array.ArrayIterator<Page> it = this.pages.iterator();
        while (it.hasNext()) {
            Page next = it.next();
            if (next.texture == null) {
                next.image.dispose();
            }
        }
        this.disposed = true;
    }

    public synchronized TextureAtlas generateTextureAtlas(Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter2, boolean z) {
        TextureAtlas textureAtlas = new TextureAtlas();
        updateTextureAtlas(textureAtlas, textureFilter, textureFilter2, z);
        return textureAtlas;
    }

    public synchronized void updateTextureAtlas(TextureAtlas textureAtlas, Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter2, boolean z) {
        updateTextureAtlas(textureAtlas, textureFilter, textureFilter2, z, true);
    }

    /* JADX WARN: Type inference failed for: r1v30, types: [int[], int[][]] */
    public synchronized void updateTextureAtlas(TextureAtlas textureAtlas, Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter2, boolean z, boolean z2) {
        updatePageTextures(textureFilter, textureFilter2, z);
        Array.ArrayIterator<Page> it = this.pages.iterator();
        while (it.hasNext()) {
            Page next = it.next();
            if (next.addedRects.size > 0) {
                Array.ArrayIterator<String> it2 = next.addedRects.iterator();
                while (it2.hasNext()) {
                    String next2 = it2.next();
                    PixmapPackerRectangle pixmapPackerRectangle = next.rects.get(next2);
                    TextureAtlas.AtlasRegion atlasRegion = new TextureAtlas.AtlasRegion(next.texture, (int) pixmapPackerRectangle.x, (int) pixmapPackerRectangle.y, (int) pixmapPackerRectangle.width, (int) pixmapPackerRectangle.height);
                    if (pixmapPackerRectangle.splits != null) {
                        atlasRegion.names = new String[]{"split", "pad"};
                        atlasRegion.values = new int[]{pixmapPackerRectangle.splits, pixmapPackerRectangle.pads};
                    }
                    int i = -1;
                    String str = next2;
                    if (z2) {
                        Matcher matcher = indexPattern.matcher(str);
                        if (matcher.matches()) {
                            str = matcher.group(1);
                            i = Integer.parseInt(matcher.group(2));
                        }
                    }
                    atlasRegion.name = str;
                    atlasRegion.index = i;
                    atlasRegion.offsetX = pixmapPackerRectangle.offsetX;
                    atlasRegion.offsetY = (int) ((pixmapPackerRectangle.originalHeight - pixmapPackerRectangle.height) - pixmapPackerRectangle.offsetY);
                    atlasRegion.originalWidth = pixmapPackerRectangle.originalWidth;
                    atlasRegion.originalHeight = pixmapPackerRectangle.originalHeight;
                    textureAtlas.getRegions().add(atlasRegion);
                }
                next.addedRects.clear();
                textureAtlas.getTextures().add(next.texture);
            }
        }
    }

    public synchronized void updateTextureRegions(Array<TextureRegion> array, Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter2, boolean z) {
        updatePageTextures(textureFilter, textureFilter2, z);
        while (array.size < this.pages.size) {
            array.add(new TextureRegion(this.pages.get(array.size).texture));
        }
    }

    public synchronized void updatePageTextures(Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter2, boolean z) {
        Array.ArrayIterator<Page> it = this.pages.iterator();
        while (it.hasNext()) {
            it.next().updateTexture(textureFilter, textureFilter2, z);
        }
    }

    public int getPageWidth() {
        return this.pageWidth;
    }

    public void setPageWidth(int i) {
        this.pageWidth = i;
    }

    public int getPageHeight() {
        return this.pageHeight;
    }

    public void setPageHeight(int i) {
        this.pageHeight = i;
    }

    public Pixmap.Format getPageFormat() {
        return this.pageFormat;
    }

    public void setPageFormat(Pixmap.Format format) {
        this.pageFormat = format;
    }

    public int getPadding() {
        return this.padding;
    }

    public void setPadding(int i) {
        this.padding = i;
    }

    public boolean getDuplicateBorder() {
        return this.duplicateBorder;
    }

    public void setDuplicateBorder(boolean z) {
        this.duplicateBorder = z;
    }

    public boolean getPackToTexture() {
        return this.packToTexture;
    }

    public void setPackToTexture(boolean z) {
        this.packToTexture = z;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/PixmapPacker$Page.class */
    public static class Page {
        Pixmap image;
        Texture texture;
        boolean dirty;
        OrderedMap<String, PixmapPackerRectangle> rects = new OrderedMap<>();
        final Array<String> addedRects = new Array<>();

        public Page(PixmapPacker pixmapPacker) {
            this.image = new Pixmap(pixmapPacker.pageWidth, pixmapPacker.pageHeight, pixmapPacker.pageFormat);
            this.image.setBlending(Pixmap.Blending.None);
            this.image.setColor(pixmapPacker.getTransparentColor());
            this.image.fill();
        }

        public Pixmap getPixmap() {
            return this.image;
        }

        public OrderedMap<String, PixmapPackerRectangle> getRects() {
            return this.rects;
        }

        public Texture getTexture() {
            return this.texture;
        }

        public boolean updateTexture(Texture.TextureFilter textureFilter, Texture.TextureFilter textureFilter2, boolean z) {
            if (this.texture != null) {
                if (!this.dirty) {
                    return false;
                }
                this.texture.load(this.texture.getTextureData());
            } else {
                this.texture = new Texture(new PixmapTextureData(this.image, this.image.getFormat(), z, false, true)) { // from class: com.badlogic.gdx.graphics.g2d.PixmapPacker.Page.1
                    @Override // com.badlogic.gdx.graphics.Texture, com.badlogic.gdx.graphics.GLTexture, com.badlogic.gdx.utils.Disposable
                    public void dispose() {
                        super.dispose();
                        Page.this.image.dispose();
                    }
                };
                this.texture.setFilter(textureFilter, textureFilter2);
            }
            this.dirty = false;
            return true;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/PixmapPacker$GuillotineStrategy.class */
    public static class GuillotineStrategy implements PackStrategy {
        Comparator<Pixmap> comparator;

        @Override // com.badlogic.gdx.graphics.g2d.PixmapPacker.PackStrategy
        public void sort(Array<Pixmap> array) {
            if (this.comparator == null) {
                this.comparator = new Comparator<Pixmap>() { // from class: com.badlogic.gdx.graphics.g2d.PixmapPacker.GuillotineStrategy.1
                    @Override // java.util.Comparator
                    public int compare(Pixmap pixmap, Pixmap pixmap2) {
                        return Math.max(pixmap.getWidth(), pixmap.getHeight()) - Math.max(pixmap2.getWidth(), pixmap2.getHeight());
                    }
                };
            }
            array.sort(this.comparator);
        }

        @Override // com.badlogic.gdx.graphics.g2d.PixmapPacker.PackStrategy
        public Page pack(PixmapPacker pixmapPacker, String str, Rectangle rectangle) {
            GuillotinePage guillotinePage;
            if (pixmapPacker.pages.size == 0) {
                guillotinePage = new GuillotinePage(pixmapPacker);
                pixmapPacker.pages.add(guillotinePage);
            } else {
                guillotinePage = (GuillotinePage) pixmapPacker.pages.peek();
            }
            int i = pixmapPacker.padding;
            rectangle.width += i;
            rectangle.height += i;
            Node insert = insert(guillotinePage.root, rectangle);
            Node node = insert;
            if (insert == null) {
                guillotinePage = new GuillotinePage(pixmapPacker);
                pixmapPacker.pages.add(guillotinePage);
                node = insert(guillotinePage.root, rectangle);
            }
            node.full = true;
            rectangle.set(node.rect.x, node.rect.y, node.rect.width - i, node.rect.height - i);
            return guillotinePage;
        }

        private Node insert(Node node, Rectangle rectangle) {
            if (!node.full && node.leftChild != null && node.rightChild != null) {
                Node insert = insert(node.leftChild, rectangle);
                Node node2 = insert;
                if (insert == null) {
                    node2 = insert(node.rightChild, rectangle);
                }
                return node2;
            }
            if (node.full) {
                return null;
            }
            if (node.rect.width == rectangle.width && node.rect.height == rectangle.height) {
                return node;
            }
            if (node.rect.width < rectangle.width || node.rect.height < rectangle.height) {
                return null;
            }
            node.leftChild = new Node();
            node.rightChild = new Node();
            if (((int) node.rect.width) - ((int) rectangle.width) > ((int) node.rect.height) - ((int) rectangle.height)) {
                node.leftChild.rect.x = node.rect.x;
                node.leftChild.rect.y = node.rect.y;
                node.leftChild.rect.width = rectangle.width;
                node.leftChild.rect.height = node.rect.height;
                node.rightChild.rect.x = node.rect.x + rectangle.width;
                node.rightChild.rect.y = node.rect.y;
                node.rightChild.rect.width = node.rect.width - rectangle.width;
                node.rightChild.rect.height = node.rect.height;
            } else {
                node.leftChild.rect.x = node.rect.x;
                node.leftChild.rect.y = node.rect.y;
                node.leftChild.rect.width = node.rect.width;
                node.leftChild.rect.height = rectangle.height;
                node.rightChild.rect.x = node.rect.x;
                node.rightChild.rect.y = node.rect.y + rectangle.height;
                node.rightChild.rect.width = node.rect.width;
                node.rightChild.rect.height = node.rect.height - rectangle.height;
            }
            return insert(node.leftChild, rectangle);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/PixmapPacker$GuillotineStrategy$Node.class */
        public static final class Node {
            public Node leftChild;
            public Node rightChild;
            public final Rectangle rect = new Rectangle();
            public boolean full;

            Node() {
            }
        }

        /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/PixmapPacker$GuillotineStrategy$GuillotinePage.class */
        static class GuillotinePage extends Page {
            Node root;

            public GuillotinePage(PixmapPacker pixmapPacker) {
                super(pixmapPacker);
                this.root = new Node();
                this.root.rect.x = pixmapPacker.padding;
                this.root.rect.y = pixmapPacker.padding;
                this.root.rect.width = pixmapPacker.pageWidth - (pixmapPacker.padding << 1);
                this.root.rect.height = pixmapPacker.pageHeight - (pixmapPacker.padding << 1);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/PixmapPacker$SkylineStrategy.class */
    public static class SkylineStrategy implements PackStrategy {
        Comparator<Pixmap> comparator;

        @Override // com.badlogic.gdx.graphics.g2d.PixmapPacker.PackStrategy
        public void sort(Array<Pixmap> array) {
            if (this.comparator == null) {
                this.comparator = new Comparator<Pixmap>() { // from class: com.badlogic.gdx.graphics.g2d.PixmapPacker.SkylineStrategy.1
                    @Override // java.util.Comparator
                    public int compare(Pixmap pixmap, Pixmap pixmap2) {
                        return pixmap.getHeight() - pixmap2.getHeight();
                    }
                };
            }
            array.sort(this.comparator);
        }

        @Override // com.badlogic.gdx.graphics.g2d.PixmapPacker.PackStrategy
        public Page pack(PixmapPacker pixmapPacker, String str, Rectangle rectangle) {
            int i = pixmapPacker.padding;
            int i2 = pixmapPacker.pageWidth - (i << 1);
            int i3 = pixmapPacker.pageHeight - (i << 1);
            int i4 = ((int) rectangle.width) + i;
            int i5 = ((int) rectangle.height) + i;
            int i6 = pixmapPacker.pages.size;
            for (int i7 = 0; i7 < i6; i7++) {
                SkylinePage skylinePage = (SkylinePage) pixmapPacker.pages.get(i7);
                SkylinePage.Row row = null;
                int i8 = skylinePage.rows.size - 1;
                for (int i9 = 0; i9 < i8; i9++) {
                    SkylinePage.Row row2 = skylinePage.rows.get(i9);
                    if (row2.x + i4 < i2 && row2.y + i5 < i3 && i5 <= row2.height && (row == null || row2.height < row.height)) {
                        row = row2;
                    }
                }
                if (row == null) {
                    SkylinePage.Row peek = skylinePage.rows.peek();
                    if (peek.y + i5 >= i3) {
                        continue;
                    } else if (peek.x + i4 >= i2) {
                        if (peek.y + peek.height + i5 < i3) {
                            SkylinePage.Row row3 = new SkylinePage.Row();
                            row = row3;
                            row3.y = peek.y + peek.height;
                            row.height = i5;
                            skylinePage.rows.add(row);
                        }
                    } else {
                        peek.height = Math.max(peek.height, i5);
                        row = peek;
                    }
                }
                if (row != null) {
                    rectangle.x = row.x;
                    rectangle.y = row.y;
                    row.x += i4;
                    return skylinePage;
                }
            }
            SkylinePage skylinePage2 = new SkylinePage(pixmapPacker);
            pixmapPacker.pages.add(skylinePage2);
            SkylinePage.Row row4 = new SkylinePage.Row();
            row4.x = i + i4;
            row4.y = i;
            row4.height = i5;
            skylinePage2.rows.add(row4);
            rectangle.x = i;
            rectangle.y = i;
            return skylinePage2;
        }

        /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/PixmapPacker$SkylineStrategy$SkylinePage.class */
        static class SkylinePage extends Page {
            Array<Row> rows;

            public SkylinePage(PixmapPacker pixmapPacker) {
                super(pixmapPacker);
                this.rows = new Array<>();
            }

            /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/PixmapPacker$SkylineStrategy$SkylinePage$Row.class */
            static class Row {
                int x;
                int y;
                int height;

                Row() {
                }
            }
        }
    }

    public Color getTransparentColor() {
        return this.transparentColor;
    }

    public void setTransparentColor(Color color) {
        this.transparentColor.set(color);
    }

    private int[] getSplits(Pixmap pixmap) {
        int width;
        int height;
        int splitPoint = getSplitPoint(pixmap, 1, 0, true, true);
        int splitPoint2 = getSplitPoint(pixmap, splitPoint, 0, false, true);
        int splitPoint3 = getSplitPoint(pixmap, 0, 1, true, false);
        int splitPoint4 = getSplitPoint(pixmap, 0, splitPoint3, false, false);
        getSplitPoint(pixmap, splitPoint2 + 1, 0, true, true);
        getSplitPoint(pixmap, 0, splitPoint4 + 1, true, false);
        if (splitPoint == 0 && splitPoint2 == 0 && splitPoint3 == 0 && splitPoint4 == 0) {
            return null;
        }
        if (splitPoint != 0) {
            splitPoint--;
            width = (pixmap.getWidth() - 2) - (splitPoint2 - 1);
        } else {
            width = pixmap.getWidth() - 2;
        }
        if (splitPoint3 != 0) {
            splitPoint3--;
            height = (pixmap.getHeight() - 2) - (splitPoint4 - 1);
        } else {
            height = pixmap.getHeight() - 2;
        }
        return new int[]{splitPoint, width, splitPoint3, height};
    }

    private int[] getPads(Pixmap pixmap, int[] iArr) {
        int width;
        int height;
        int height2 = pixmap.getHeight() - 1;
        int width2 = pixmap.getWidth() - 1;
        int splitPoint = getSplitPoint(pixmap, 1, height2, true, true);
        int splitPoint2 = getSplitPoint(pixmap, width2, 1, true, false);
        int i = 0;
        int i2 = 0;
        if (splitPoint != 0) {
            i = getSplitPoint(pixmap, splitPoint + 1, height2, false, true);
        }
        if (splitPoint2 != 0) {
            i2 = getSplitPoint(pixmap, width2, splitPoint2 + 1, false, false);
        }
        getSplitPoint(pixmap, i + 1, height2, true, true);
        getSplitPoint(pixmap, width2, i2 + 1, true, false);
        if (splitPoint == 0 && i == 0 && splitPoint2 == 0 && i2 == 0) {
            return null;
        }
        if (splitPoint == 0 && i == 0) {
            splitPoint = -1;
            width = -1;
        } else if (splitPoint > 0) {
            splitPoint--;
            width = (pixmap.getWidth() - 2) - (i - 1);
        } else {
            width = pixmap.getWidth() - 2;
        }
        if (splitPoint2 == 0 && i2 == 0) {
            splitPoint2 = -1;
            height = -1;
        } else if (splitPoint2 > 0) {
            splitPoint2--;
            height = (pixmap.getHeight() - 2) - (i2 - 1);
        } else {
            height = pixmap.getHeight() - 2;
        }
        int[] iArr2 = {splitPoint, width, splitPoint2, height};
        if (iArr != null && Arrays.equals(iArr2, iArr)) {
            return null;
        }
        return iArr2;
    }

    private int getSplitPoint(Pixmap pixmap, int i, int i2, boolean z, boolean z2) {
        int[] iArr = new int[4];
        int width = z2 ? pixmap.getWidth() : pixmap.getHeight();
        int i3 = z ? 255 : 0;
        int i4 = i;
        int i5 = i2;
        for (int i6 = z2 ? i : i2; i6 != width; i6++) {
            if (z2) {
                i4 = i6;
            } else {
                i5 = i6;
            }
            this.c.set(pixmap.getPixel(i4, i5));
            iArr[0] = (int) (this.c.r * 255.0f);
            iArr[1] = (int) (this.c.g * 255.0f);
            iArr[2] = (int) (this.c.f888b * 255.0f);
            iArr[3] = (int) (this.c.f889a * 255.0f);
            if (iArr[3] == i3) {
                return i6;
            }
            if (!z && (iArr[0] != 0 || iArr[1] != 0 || iArr[2] != 0 || iArr[3] != 255)) {
                System.out.println(i4 + "  " + i5 + SequenceUtils.SPACE + iArr + SequenceUtils.SPACE);
            }
        }
        return 0;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/PixmapPacker$PixmapPackerRectangle.class */
    public static class PixmapPackerRectangle extends Rectangle {
        public Page page;
        public int[] splits;
        public int[] pads;
        public int offsetX;
        public int offsetY;
        public int originalWidth;
        public int originalHeight;

        PixmapPackerRectangle(int i, int i2, int i3, int i4) {
            super(i, i2, i3, i4);
            this.offsetX = 0;
            this.offsetY = 0;
            this.originalWidth = i3;
            this.originalHeight = i4;
        }

        PixmapPackerRectangle(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            super(i, i2, i3, i4);
            this.offsetX = i5;
            this.offsetY = i6;
            this.originalWidth = i7;
            this.originalHeight = i8;
        }
    }
}
