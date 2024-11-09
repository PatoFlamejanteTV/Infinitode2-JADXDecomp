package com.crashinvaders.basisu.gdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.crashinvaders.basisu.gdx.BasisuTextureFormatSelector;
import com.crashinvaders.basisu.wrapper.BasisuTranscoderTextureFormat;
import com.crashinvaders.basisu.wrapper.BasisuWrapper;
import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/crashinvaders/basisu/gdx/Ktx2TextureData.class */
public class Ktx2TextureData implements TextureData {
    private static final String TAG = Ktx2TextureData.class.getSimpleName();
    private BasisuTextureFormatSelector formatSelector;
    private final FileHandle file;
    private final int mipmapLevel;
    private Ktx2Data ktx2Data;
    private ByteBuffer transcodedData;
    private BasisuTranscoderTextureFormat transcodeFormat;
    private int width;
    private int height;
    private boolean isPrepared;

    public Ktx2TextureData(FileHandle fileHandle) {
        this(fileHandle, 0);
    }

    public Ktx2TextureData(FileHandle fileHandle, int i) {
        this.formatSelector = BasisuGdxUtils.defaultFormatSelector;
        this.transcodedData = null;
        this.transcodeFormat = null;
        this.width = 0;
        this.height = 0;
        this.isPrepared = false;
        this.file = fileHandle;
        this.mipmapLevel = i;
        this.ktx2Data = null;
    }

    public Ktx2TextureData(Ktx2Data ktx2Data) {
        this(ktx2Data, 0);
    }

    public Ktx2TextureData(Ktx2Data ktx2Data, int i) {
        this.formatSelector = BasisuGdxUtils.defaultFormatSelector;
        this.transcodedData = null;
        this.transcodeFormat = null;
        this.width = 0;
        this.height = 0;
        this.isPrepared = false;
        this.file = null;
        this.mipmapLevel = i;
        this.ktx2Data = ktx2Data;
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
        if (this.file == null && this.ktx2Data == null) {
            throw new GdxRuntimeException("Can only load once from ktx2Data");
        }
        if (this.file != null) {
            this.ktx2Data = new Ktx2Data(this.file);
        }
        int totalMipmapLevels = this.ktx2Data.getTotalMipmapLevels();
        if (this.mipmapLevel < 0 || this.mipmapLevel >= totalMipmapLevels) {
            throw new BasisuGdxException("mipmapLevel " + this.mipmapLevel + " exceeds the total number of mipmap levels (" + totalMipmapLevels + ") in the basis file.");
        }
        this.width = this.ktx2Data.getImageWidth();
        this.height = this.ktx2Data.getImageHeight();
        this.transcodeFormat = this.formatSelector.resolveTextureFormat(this.ktx2Data);
        Gdx.app.debug(TAG, (this.file != null ? "[" + this.file.path() + "] " : "") + "Transcoding to the " + this.transcodeFormat + " format");
        this.transcodedData = this.ktx2Data.transcode(0, this.mipmapLevel, this.transcodeFormat);
        Gdx.app.debug(TAG, (this.file != null ? "[" + this.file.path() + "] " : "") + "Transcoded texture size: " + MathUtils.round(this.transcodedData.capacity() / 1024.0f) + "kB");
        this.ktx2Data.dispose();
        this.ktx2Data = null;
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
