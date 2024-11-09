package com.crashinvaders.basisu.gdx;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.IntMap;
import com.crashinvaders.basisu.wrapper.BasisuFileInfo;
import com.crashinvaders.basisu.wrapper.BasisuImageInfo;
import com.crashinvaders.basisu.wrapper.BasisuTranscoderTextureFormat;
import com.crashinvaders.basisu.wrapper.BasisuWrapper;
import java.nio.ByteBuffer;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/crashinvaders/basisu/gdx/BasisuData.class */
public class BasisuData implements Disposable {
    private final ByteBuffer encodedData;
    private final BasisuFileInfo fileInfo;
    private IntMap<BasisuImageInfo> imageInfoIndex;

    public BasisuData(FileHandle fileHandle) {
        this(BasisuGdxUtils.readFileIntoBuffer(fileHandle));
    }

    public BasisuData(ByteBuffer byteBuffer) {
        this.imageInfoIndex = null;
        BasisuNativeLibLoader.loadIfNeeded();
        this.encodedData = byteBuffer;
        if (!BasisuWrapper.basisValidateHeader(byteBuffer)) {
            throw new BasisuGdxException("Cannot validate header of the basis universal data.");
        }
        this.fileInfo = BasisuWrapper.basisGetFileInfo(byteBuffer);
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.fileInfo.close();
        if (this.imageInfoIndex != null) {
            Iterator<BasisuImageInfo> it = this.imageInfoIndex.values().iterator();
            while (it.hasNext()) {
                it.next().close();
            }
            this.imageInfoIndex.clear();
        }
        if (BasisuBufferUtils.isUnsafeByteBuffer(this.encodedData)) {
            BasisuBufferUtils.disposeUnsafeByteBuffer(this.encodedData);
        }
    }

    public ByteBuffer getEncodedData() {
        return this.encodedData;
    }

    public BasisuFileInfo getFileInfo() {
        return this.fileInfo;
    }

    public BasisuImageInfo getImageInfo(int i) {
        if (this.imageInfoIndex == null) {
            this.imageInfoIndex = new IntMap<>();
        }
        BasisuImageInfo basisuImageInfo = this.imageInfoIndex.get(i);
        BasisuImageInfo basisuImageInfo2 = basisuImageInfo;
        if (basisuImageInfo == null) {
            basisuImageInfo2 = BasisuWrapper.basisGetImageInfo(this.encodedData, i);
            this.imageInfoIndex.put(i, basisuImageInfo2);
        }
        return basisuImageInfo2;
    }

    public ByteBuffer transcode(int i, int i2, BasisuTranscoderTextureFormat basisuTranscoderTextureFormat) {
        return BasisuWrapper.basisTranscode(this.encodedData, i, i2, basisuTranscoderTextureFormat);
    }
}
