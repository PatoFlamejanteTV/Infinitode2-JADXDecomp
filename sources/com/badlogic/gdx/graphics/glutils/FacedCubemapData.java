package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Cubemap;
import com.badlogic.gdx.graphics.CubemapData;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.utils.GdxRuntimeException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/FacedCubemapData.class */
public class FacedCubemapData implements CubemapData {
    protected final TextureData[] data;

    public FacedCubemapData() {
        this((TextureData) null, (TextureData) null, (TextureData) null, (TextureData) null, (TextureData) null, (TextureData) null);
    }

    public FacedCubemapData(FileHandle fileHandle, FileHandle fileHandle2, FileHandle fileHandle3, FileHandle fileHandle4, FileHandle fileHandle5, FileHandle fileHandle6) {
        this(TextureData.Factory.loadFromFile(fileHandle, false), TextureData.Factory.loadFromFile(fileHandle2, false), TextureData.Factory.loadFromFile(fileHandle3, false), TextureData.Factory.loadFromFile(fileHandle4, false), TextureData.Factory.loadFromFile(fileHandle5, false), TextureData.Factory.loadFromFile(fileHandle6, false));
    }

    public FacedCubemapData(FileHandle fileHandle, FileHandle fileHandle2, FileHandle fileHandle3, FileHandle fileHandle4, FileHandle fileHandle5, FileHandle fileHandle6, boolean z) {
        this(TextureData.Factory.loadFromFile(fileHandle, z), TextureData.Factory.loadFromFile(fileHandle2, z), TextureData.Factory.loadFromFile(fileHandle3, z), TextureData.Factory.loadFromFile(fileHandle4, z), TextureData.Factory.loadFromFile(fileHandle5, z), TextureData.Factory.loadFromFile(fileHandle6, z));
    }

    public FacedCubemapData(Pixmap pixmap, Pixmap pixmap2, Pixmap pixmap3, Pixmap pixmap4, Pixmap pixmap5, Pixmap pixmap6) {
        this(pixmap, pixmap2, pixmap3, pixmap4, pixmap5, pixmap6, false);
    }

    public FacedCubemapData(Pixmap pixmap, Pixmap pixmap2, Pixmap pixmap3, Pixmap pixmap4, Pixmap pixmap5, Pixmap pixmap6, boolean z) {
        this(pixmap == null ? null : new PixmapTextureData(pixmap, null, z, false), pixmap2 == null ? null : new PixmapTextureData(pixmap2, null, z, false), pixmap3 == null ? null : new PixmapTextureData(pixmap3, null, z, false), pixmap4 == null ? null : new PixmapTextureData(pixmap4, null, z, false), pixmap5 == null ? null : new PixmapTextureData(pixmap5, null, z, false), pixmap6 == null ? null : new PixmapTextureData(pixmap6, null, z, false));
    }

    public FacedCubemapData(int i, int i2, int i3, Pixmap.Format format) {
        this(new PixmapTextureData(new Pixmap(i3, i2, format), null, false, true), new PixmapTextureData(new Pixmap(i3, i2, format), null, false, true), new PixmapTextureData(new Pixmap(i, i3, format), null, false, true), new PixmapTextureData(new Pixmap(i, i3, format), null, false, true), new PixmapTextureData(new Pixmap(i, i2, format), null, false, true), new PixmapTextureData(new Pixmap(i, i2, format), null, false, true));
    }

    public FacedCubemapData(TextureData textureData, TextureData textureData2, TextureData textureData3, TextureData textureData4, TextureData textureData5, TextureData textureData6) {
        this.data = new TextureData[6];
        this.data[0] = textureData;
        this.data[1] = textureData2;
        this.data[2] = textureData3;
        this.data[3] = textureData4;
        this.data[4] = textureData5;
        this.data[5] = textureData6;
    }

    @Override // com.badlogic.gdx.graphics.CubemapData
    public boolean isManaged() {
        for (TextureData textureData : this.data) {
            if (!textureData.isManaged()) {
                return false;
            }
        }
        return true;
    }

    public void load(Cubemap.CubemapSide cubemapSide, FileHandle fileHandle) {
        this.data[cubemapSide.index] = TextureData.Factory.loadFromFile(fileHandle, false);
    }

    public void load(Cubemap.CubemapSide cubemapSide, Pixmap pixmap) {
        this.data[cubemapSide.index] = pixmap == null ? null : new PixmapTextureData(pixmap, null, false, false);
    }

    public boolean isComplete() {
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i] == null) {
                return false;
            }
        }
        return true;
    }

    public TextureData getTextureData(Cubemap.CubemapSide cubemapSide) {
        return this.data[cubemapSide.index];
    }

    @Override // com.badlogic.gdx.graphics.CubemapData
    public int getWidth() {
        int width;
        int width2;
        int width3;
        int width4;
        int i = 0;
        if (this.data[Cubemap.CubemapSide.PositiveZ.index] != null && (width4 = this.data[Cubemap.CubemapSide.PositiveZ.index].getWidth()) > 0) {
            i = width4;
        }
        if (this.data[Cubemap.CubemapSide.NegativeZ.index] != null && (width3 = this.data[Cubemap.CubemapSide.NegativeZ.index].getWidth()) > i) {
            i = width3;
        }
        if (this.data[Cubemap.CubemapSide.PositiveY.index] != null && (width2 = this.data[Cubemap.CubemapSide.PositiveY.index].getWidth()) > i) {
            i = width2;
        }
        if (this.data[Cubemap.CubemapSide.NegativeY.index] != null && (width = this.data[Cubemap.CubemapSide.NegativeY.index].getWidth()) > i) {
            i = width;
        }
        return i;
    }

    @Override // com.badlogic.gdx.graphics.CubemapData
    public int getHeight() {
        int height;
        int height2;
        int height3;
        int height4;
        int i = 0;
        if (this.data[Cubemap.CubemapSide.PositiveZ.index] != null && (height4 = this.data[Cubemap.CubemapSide.PositiveZ.index].getHeight()) > 0) {
            i = height4;
        }
        if (this.data[Cubemap.CubemapSide.NegativeZ.index] != null && (height3 = this.data[Cubemap.CubemapSide.NegativeZ.index].getHeight()) > i) {
            i = height3;
        }
        if (this.data[Cubemap.CubemapSide.PositiveX.index] != null && (height2 = this.data[Cubemap.CubemapSide.PositiveX.index].getHeight()) > i) {
            i = height2;
        }
        if (this.data[Cubemap.CubemapSide.NegativeX.index] != null && (height = this.data[Cubemap.CubemapSide.NegativeX.index].getHeight()) > i) {
            i = height;
        }
        return i;
    }

    @Override // com.badlogic.gdx.graphics.CubemapData
    public boolean isPrepared() {
        return false;
    }

    @Override // com.badlogic.gdx.graphics.CubemapData
    public void prepare() {
        if (!isComplete()) {
            throw new GdxRuntimeException("You need to complete your cubemap data before using it");
        }
        for (int i = 0; i < this.data.length; i++) {
            if (!this.data[i].isPrepared()) {
                this.data[i].prepare();
            }
        }
    }

    @Override // com.badlogic.gdx.graphics.CubemapData
    public void consumeCubemapData() {
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i].getType() == TextureData.TextureDataType.Custom) {
                this.data[i].consumeCustomData(i + 34069);
            } else {
                Pixmap consumePixmap = this.data[i].consumePixmap();
                boolean disposePixmap = this.data[i].disposePixmap();
                if (this.data[i].getFormat() != consumePixmap.getFormat()) {
                    Pixmap pixmap = new Pixmap(consumePixmap.getWidth(), consumePixmap.getHeight(), this.data[i].getFormat());
                    pixmap.setBlending(Pixmap.Blending.None);
                    pixmap.drawPixmap(consumePixmap, 0, 0, 0, 0, consumePixmap.getWidth(), consumePixmap.getHeight());
                    if (this.data[i].disposePixmap()) {
                        consumePixmap.dispose();
                    }
                    consumePixmap = pixmap;
                    disposePixmap = true;
                }
                Gdx.gl.glPixelStorei(3317, 1);
                Gdx.gl.glTexImage2D(i + 34069, 0, consumePixmap.getGLInternalFormat(), consumePixmap.getWidth(), consumePixmap.getHeight(), 0, consumePixmap.getGLFormat(), consumePixmap.getGLType(), consumePixmap.getPixels());
                if (disposePixmap) {
                    consumePixmap.dispose();
                }
            }
        }
    }
}
