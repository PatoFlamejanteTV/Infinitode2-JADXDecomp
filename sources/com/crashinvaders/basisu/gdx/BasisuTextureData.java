package com.crashinvaders.basisu.gdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.crashinvaders.basisu.gdx.BasisuTextureFormatSelector;
import com.crashinvaders.basisu.wrapper.BasisuFileInfo;
import com.crashinvaders.basisu.wrapper.BasisuImageInfo;
import com.crashinvaders.basisu.wrapper.BasisuTextureType;
import com.crashinvaders.basisu.wrapper.BasisuTranscoderTextureFormat;
import com.crashinvaders.basisu.wrapper.BasisuWrapper;
import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/crashinvaders/basisu/gdx/BasisuTextureData.class */
public class BasisuTextureData implements TextureData {
    private static final String TAG = BasisuTextureData.class.getSimpleName();
    private BasisuTextureFormatSelector formatSelector;
    private final FileHandle file;
    private final int imageIndex;
    private final int mipmapLevel;
    private BasisuData basisuData;
    private ByteBuffer transcodedData;
    private BasisuTranscoderTextureFormat transcodeFormat;
    private int width;
    private int height;
    private boolean isPrepared;

    public BasisuTextureData(FileHandle fileHandle) {
        this(fileHandle, 0, 0);
    }

    public BasisuTextureData(FileHandle fileHandle, int i) {
        this(fileHandle, i, 0);
    }

    public BasisuTextureData(FileHandle fileHandle, int i, int i2) {
        this.formatSelector = BasisuGdxUtils.defaultFormatSelector;
        this.transcodedData = null;
        this.transcodeFormat = null;
        this.width = 0;
        this.height = 0;
        this.isPrepared = false;
        this.file = fileHandle;
        this.imageIndex = i;
        this.mipmapLevel = i2;
        this.basisuData = null;
    }

    public BasisuTextureData(BasisuData basisuData) {
        this(basisuData, 0);
    }

    public BasisuTextureData(BasisuData basisuData, int i) {
        this(basisuData, i, 0);
    }

    public BasisuTextureData(BasisuData basisuData, int i, int i2) {
        this.formatSelector = BasisuGdxUtils.defaultFormatSelector;
        this.transcodedData = null;
        this.transcodeFormat = null;
        this.width = 0;
        this.height = 0;
        this.isPrepared = false;
        this.file = null;
        this.imageIndex = i;
        this.mipmapLevel = i2;
        this.basisuData = basisuData;
    }

    public BasisuTextureFormatSelector getTextureFormatSelector() {
        return this.formatSelector;
    }

    public void setTextureFormatSelector(BasisuTextureFormatSelector basisuTextureFormatSelector) {
        this.formatSelector = basisuTextureFormatSelector;
    }

    public void setTextureFormatSelector(BasisuTranscoderTextureFormat basisuTranscoderTextureFormat) {
        this.formatSelector = new BasisuTextureFormatSelector.Fixed(basisuTranscoderTextureFormat);
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public TextureData.TextureDataType getType() {
        return TextureData.TextureDataType.Custom;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean isPrepared() {
        return this.isPrepared;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public void prepare() {
        if (this.isPrepared) {
            throw new GdxRuntimeException("Already prepared");
        }
        if (this.file == null && this.basisuData == null) {
            throw new GdxRuntimeException("Can only load once from BasisuData");
        }
        if (this.file != null) {
            this.basisuData = new BasisuData(this.file);
        }
        BasisuFileInfo fileInfo = this.basisuData.getFileInfo();
        int totalImages = fileInfo.getTotalImages();
        if (this.imageIndex < 0 || this.imageIndex >= totalImages) {
            throw new BasisuGdxException("imageIndex " + this.imageIndex + " exceeds the total number of images (" + totalImages + ") in the basis file.");
        }
        int i = fileInfo.getImageMipmapLevels()[this.imageIndex];
        if (this.mipmapLevel < 0 || this.mipmapLevel >= i) {
            throw new BasisuGdxException("mipmapLevel " + this.mipmapLevel + " exceeds the total number of mipmap levels (" + i + ") in the basis file.");
        }
        BasisuTextureType textureType = fileInfo.getTextureType();
        if (textureType != BasisuTextureType.REGULAR_2D) {
            throw new BasisuGdxException("textureType " + textureType + " is not supported at the moment. Only BasisuTextureType.REGULAR_2D texture type is allowed.");
        }
        BasisuImageInfo imageInfo = this.basisuData.getImageInfo(this.imageIndex);
        this.width = imageInfo.getWidth();
        this.height = imageInfo.getHeight();
        this.transcodeFormat = this.formatSelector.resolveTextureFormat(this.basisuData, this.imageIndex);
        Gdx.app.debug(TAG, (this.file != null ? "[" + this.file.path() + "] " : "") + "Transcoding to the " + this.transcodeFormat + " format");
        this.transcodedData = this.basisuData.transcode(this.imageIndex, this.mipmapLevel, this.transcodeFormat);
        Gdx.app.debug(TAG, (this.file != null ? "[" + this.file.path() + "] " : "") + "Transcoded texture size: " + MathUtils.round(this.transcodedData.capacity() / 1024.0f) + "kB");
        this.basisuData.dispose();
        this.basisuData = null;
        this.isPrepared = true;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public void consumeCustomData(int i) {
        if (!this.isPrepared) {
            throw new GdxRuntimeException("Call prepare() before calling consumeCompressedData()");
        }
        int glTextureFormat = BasisuGdxUtils.toGlTextureFormat(this.transcodeFormat);
        if (this.transcodeFormat.isCompressedFormat()) {
            BasisuGdxGl.glCompressedTexImage2D(i, 0, glTextureFormat, this.width, this.height, 0, this.transcodedData.capacity(), this.transcodedData);
        } else {
            Gdx.gl.glTexImage2D(i, 0, glTextureFormat, this.width, this.height, 0, glTextureFormat, BasisuGdxUtils.toUncompressedGlTextureType(this.transcodeFormat), this.transcodedData);
        }
        BasisuWrapper.disposeNativeBuffer(this.transcodedData);
        this.transcodedData = null;
        this.transcodeFormat = null;
        this.isPrepared = false;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public Pixmap consumePixmap() {
        throw new GdxRuntimeException("This TextureData implementation does not return a Pixmap.");
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean disposePixmap() {
        throw new GdxRuntimeException("This TextureData implementation does not return a Pixmap.");
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public int getWidth() {
        return this.width;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public int getHeight() {
        return this.height;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public Pixmap.Format getFormat() {
        throw new GdxRuntimeException("This TextureData implementation does not return a Pixmap");
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean useMipMaps() {
        return false;
    }

    @Override // com.badlogic.gdx.graphics.TextureData
    public boolean isManaged() {
        return true;
    }
}
