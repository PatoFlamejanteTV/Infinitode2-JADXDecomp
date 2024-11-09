package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureArrayData;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.utils.GdxRuntimeException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/FileTextureArrayData.class */
public class FileTextureArrayData implements TextureArrayData {
    private TextureData[] textureDatas;
    private boolean prepared;
    private Pixmap.Format format;
    private int depth;
    boolean useMipMaps;

    public FileTextureArrayData(Pixmap.Format format, boolean z, FileHandle[] fileHandleArr) {
        this.format = format;
        this.useMipMaps = z;
        this.depth = fileHandleArr.length;
        this.textureDatas = new TextureData[fileHandleArr.length];
        for (int i = 0; i < fileHandleArr.length; i++) {
            this.textureDatas[i] = TextureData.Factory.loadFromFile(fileHandleArr[i], format, z);
        }
    }

    @Override // com.badlogic.gdx.graphics.TextureArrayData
    public boolean isPrepared() {
        return this.prepared;
    }

    @Override // com.badlogic.gdx.graphics.TextureArrayData
    public void prepare() {
        int i = -1;
        int i2 = -1;
        for (TextureData textureData : this.textureDatas) {
            textureData.prepare();
            if (i != -1) {
                if (i != textureData.getWidth() || i2 != textureData.getHeight()) {
                    throw new GdxRuntimeException("Error whilst preparing TextureArray: TextureArray Textures must have equal dimensions.");
                }
            } else {
                i = textureData.getWidth();
                i2 = textureData.getHeight();
            }
        }
        this.prepared = true;
    }

    @Override // com.badlogic.gdx.graphics.TextureArrayData
    public void consumeTextureArrayData() {
        boolean z = false;
        for (int i = 0; i < this.textureDatas.length; i++) {
            if (this.textureDatas[i].getType() == TextureData.TextureDataType.Custom) {
                this.textureDatas[i].consumeCustomData(35866);
                z = true;
            } else {
                TextureData textureData = this.textureDatas[i];
                Pixmap consumePixmap = textureData.consumePixmap();
                boolean disposePixmap = textureData.disposePixmap();
                if (textureData.getFormat() != consumePixmap.getFormat()) {
                    Pixmap pixmap = new Pixmap(consumePixmap.getWidth(), consumePixmap.getHeight(), textureData.getFormat());
                    pixmap.setBlending(Pixmap.Blending.None);
                    pixmap.drawPixmap(consumePixmap, 0, 0, 0, 0, consumePixmap.getWidth(), consumePixmap.getHeight());
                    if (textureData.disposePixmap()) {
                        consumePixmap.dispose();
                    }
                    consumePixmap = pixmap;
                    disposePixmap = true;
                }
                Gdx.gl30.glTexSubImage3D(35866, 0, 0, 0, i, consumePixmap.getWidth(), consumePixmap.getHeight(), 1, consumePixmap.getGLInternalFormat(), consumePixmap.getGLType(), consumePixmap.getPixels());
                if (disposePixmap) {
                    consumePixmap.dispose();
                }
            }
        }
        if (this.useMipMaps && !z) {
            Gdx.gl20.glGenerateMipmap(35866);
        }
    }

    @Override // com.badlogic.gdx.graphics.TextureArrayData
    public int getWidth() {
        return this.textureDatas[0].getWidth();
    }

    @Override // com.badlogic.gdx.graphics.TextureArrayData
    public int getHeight() {
        return this.textureDatas[0].getHeight();
    }

    @Override // com.badlogic.gdx.graphics.TextureArrayData
    public int getDepth() {
        return this.depth;
    }

    @Override // com.badlogic.gdx.graphics.TextureArrayData
    public int getInternalFormat() {
        return Pixmap.Format.toGlFormat(this.format);
    }

    @Override // com.badlogic.gdx.graphics.TextureArrayData
    public int getGLType() {
        return Pixmap.Format.toGlType(this.format);
    }

    @Override // com.badlogic.gdx.graphics.TextureArrayData
    public boolean isManaged() {
        for (TextureData textureData : this.textureDatas) {
            if (!textureData.isManaged()) {
                return false;
            }
        }
        return true;
    }
}
