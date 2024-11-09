package com.crashinvaders.basisu.wrapper;

import java.io.Closeable;

/* loaded from: infinitode-2.jar:com/crashinvaders/basisu/wrapper/BasisuFileInfo.class */
public class BasisuFileInfo implements Closeable {
    long addr;

    private native int jniGetTextureType(long j);

    private native int jniGetTextureFormat(long j);

    private native int jniGetVersion(long j);

    private native int jniGetTotalHeaderSize(long j);

    private native int jniGetTotalSelectors(long j);

    private native int jniGetSelectorCodebookSize(long j);

    private native int jniGetTotalEndpoints(long j);

    private native int jniGetEndpointCodebookSize(long j);

    private native int jniGetTablesSize(long j);

    private native int jniGetSlicesSize(long j);

    private native int jniGetUsPerFrame(long j);

    private native int jniGetTotalImages(long j);

    private native int[] jniGetImageMipmapLevels(long j);

    private native int jniGetUserdata0(long j);

    private native int jniGetUserdata1(long j);

    private native boolean jniIsFlippedY(long j);

    private native boolean jniIsEtc1s(long j);

    private native boolean jniHasAlphaSlices(long j);

    private static native long jniCreate();

    private static native void jniDispose(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public BasisuFileInfo() {
        this.addr = 0L;
        this.addr = jniCreate();
    }

    BasisuFileInfo(Object obj) {
        this.addr = 0L;
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

    public BasisuTextureType getTextureType() {
        return (BasisuTextureType) UniqueIdUtils.findOrThrow(BasisuTextureType.values(), jniGetTextureType(this.addr));
    }

    public BasisuTextureFormat getTextureFormat() {
        return (BasisuTextureFormat) UniqueIdUtils.findOrThrow(BasisuTextureFormat.values(), jniGetTextureFormat(this.addr));
    }

    public int getVersion() {
        return jniGetVersion(this.addr);
    }

    public int getTotalHeaderSize() {
        return jniGetTotalHeaderSize(this.addr);
    }

    public int getTotalSelectors() {
        return jniGetTotalSelectors(this.addr);
    }

    public int getSelectorCodebookSize() {
        return jniGetSelectorCodebookSize(this.addr);
    }

    public int getTotalEndpoints() {
        return jniGetTotalEndpoints(this.addr);
    }

    public int getEndpointCodebookSize() {
        return jniGetEndpointCodebookSize(this.addr);
    }

    public int getTablesSize() {
        return jniGetTablesSize(this.addr);
    }

    public int getSlicesSize() {
        return jniGetSlicesSize(this.addr);
    }

    public int getUsPerFrame() {
        return jniGetUsPerFrame(this.addr);
    }

    public int getTotalImages() {
        return jniGetTotalImages(this.addr);
    }

    public int[] getImageMipmapLevels() {
        return jniGetImageMipmapLevels(this.addr);
    }

    public int getUserdata0() {
        return jniGetUserdata0(this.addr);
    }

    public int getUserdata1() {
        return jniGetUserdata1(this.addr);
    }

    public boolean isFlippedY() {
        return jniIsFlippedY(this.addr);
    }

    public boolean isEtc1s() {
        return jniIsEtc1s(this.addr);
    }

    public boolean hasAlphaSlices() {
        return jniHasAlphaSlices(this.addr);
    }
}
