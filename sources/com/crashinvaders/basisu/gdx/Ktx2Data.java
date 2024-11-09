package com.crashinvaders.basisu.gdx;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.IntMap;
import com.crashinvaders.basisu.wrapper.BasisuTextureFormat;
import com.crashinvaders.basisu.wrapper.BasisuTranscoderTextureFormat;
import com.crashinvaders.basisu.wrapper.BasisuWrapper;
import com.crashinvaders.basisu.wrapper.Ktx2FileInfo;
import com.crashinvaders.basisu.wrapper.Ktx2ImageLevelInfo;
import java.nio.ByteBuffer;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/crashinvaders/basisu/gdx/Ktx2Data.class */
public class Ktx2Data implements Disposable {
    private final ByteBuffer encodedData;
    private final Ktx2FileInfo fileInfo;
    private final IntMap<IntMap<Ktx2ImageLevelInfo>> imageInfoIndex;

    public Ktx2Data(FileHandle fileHandle) {
        this(BasisuGdxUtils.readFileIntoBuffer(fileHandle));
    }

    public Ktx2Data(ByteBuffer byteBuffer) {
        this.imageInfoIndex = new IntMap<>();
        BasisuNativeLibLoader.loadIfNeeded();
        this.encodedData = byteBuffer;
        this.fileInfo = BasisuWrapper.ktx2GetFileInfo(byteBuffer);
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Iterator<IntMap<Ktx2ImageLevelInfo>> it = this.imageInfoIndex.values().iterator();
        while (it.hasNext()) {
            Iterator<Ktx2ImageLevelInfo> it2 = it.next().values().iterator();
            while (it2.hasNext()) {
                it2.next().close();
            }
        }
        this.imageInfoIndex.clear();
        this.fileInfo.close();
        if (BasisuBufferUtils.isUnsafeByteBuffer(this.encodedData)) {
            BasisuBufferUtils.disposeUnsafeByteBuffer(this.encodedData);
        }
    }

    public int getTotalLayers() {
        return this.fileInfo.getTotalLayers();
    }

    public int getTotalMipmapLevels() {
        return this.fileInfo.getTotalMipmapLevels();
    }

    public int getImageWidth() {
        return this.fileInfo.getImageWidth();
    }

    public int getImageHeight() {
        return this.fileInfo.getImageHeight();
    }

    public boolean hasAlpha() {
        return this.fileInfo.hasAlpha();
    }

    public BasisuTextureFormat getTextureFormat() {
        return this.fileInfo.getTextureFormat();
    }

    public ByteBuffer getEncodedData() {
        return this.encodedData;
    }

    public Ktx2ImageLevelInfo getImageLevelInfo(int i, int i2) {
        IntMap<Ktx2ImageLevelInfo> intMap = this.imageInfoIndex.get(i);
        IntMap<Ktx2ImageLevelInfo> intMap2 = intMap;
        if (intMap == null) {
            intMap2 = new IntMap<>();
            this.imageInfoIndex.put(i, intMap2);
        }
        Ktx2ImageLevelInfo ktx2ImageLevelInfo = intMap2.get(i);
        Ktx2ImageLevelInfo ktx2ImageLevelInfo2 = ktx2ImageLevelInfo;
        if (ktx2ImageLevelInfo == null) {
            ktx2ImageLevelInfo2 = BasisuWrapper.ktx2GetImageLevelInfo(this.encodedData, i, i2);
            intMap2.put(i, ktx2ImageLevelInfo2);
        }
        return ktx2ImageLevelInfo2;
    }

    public ByteBuffer transcode(int i, int i2, BasisuTranscoderTextureFormat basisuTranscoderTextureFormat) {
        return BasisuWrapper.ktx2Transcode(this.encodedData, i, i2, basisuTranscoderTextureFormat);
    }
}
