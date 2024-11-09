package com.crashinvaders.basisu.wrapper;

import java.io.Closeable;

/* loaded from: infinitode-2.jar:com/crashinvaders/basisu/wrapper/Ktx2FileInfo.class */
public class Ktx2FileInfo implements Closeable {
    long addr;

    private native int getTotalLayersNative(long j);

    private native int getTotalMipmapLevelsNative(long j);

    private native int getImageWidthNative(long j);

    private native int getImageHeightNative(long j);

    private native boolean hasAlphaNative(long j);

    private native int getTextureFormatNative(long j);

    private static native long jniCreate();

    private static native void jniDispose(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public Ktx2FileInfo() {
        this.addr = jniCreate();
    }

    Ktx2FileInfo(Object obj) {
        throw new UnsupportedOperationException("This constructor exists solely for GWT compilation compatibility.");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.addr == 0) {
            throw new IllegalStateException("Object was already closed!");
        }
        jniDispose(this.addr);
        this.addr = 0L;
    }

    public int getTotalLayers() {
        return getTotalLayersNative(this.addr);
    }

    public int getTotalMipmapLevels() {
        return getTotalMipmapLevelsNative(this.addr);
    }

    public int getImageWidth() {
        return getImageWidthNative(this.addr);
    }

    public int getImageHeight() {
        return getImageHeightNative(this.addr);
    }

    public boolean hasAlpha() {
        return hasAlphaNative(this.addr);
    }

    public BasisuTextureFormat getTextureFormat() {
        return (BasisuTextureFormat) UniqueIdUtils.findOrThrow(BasisuTextureFormat.values(), getTextureFormatNative(this.addr));
    }
}
