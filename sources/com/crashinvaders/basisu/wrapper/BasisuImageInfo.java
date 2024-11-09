package com.crashinvaders.basisu.wrapper;

import java.io.Closeable;

/* loaded from: infinitode-2.jar:com/crashinvaders/basisu/wrapper/BasisuImageInfo.class */
public class BasisuImageInfo implements Closeable {
    long addr;

    private native int jniGetImageIndex(long j);

    private native int jniGetTotalLevels(long j);

    private native int jniGetOrigWidth(long j);

    private native int jniGetOrigHeight(long j);

    private native int jniGetWidth(long j);

    private native int jniGetHeight(long j);

    private native int jniGetNumBlocksX(long j);

    private native int jniGetNumBlocksY(long j);

    private native int jniGetTotalBlocks(long j);

    private native int jniGetFirstSliceIndex(long j);

    private native boolean jniHasAlphaFlag(long j);

    private native boolean jniHasIframeFlag(long j);

    private static native long jniCreate();

    private static native void jniDispose(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public BasisuImageInfo() {
        this.addr = jniCreate();
    }

    BasisuImageInfo(Object obj) {
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

    public int getImageIndex() {
        return jniGetImageIndex(this.addr);
    }

    public int getTotalLevels() {
        return jniGetTotalLevels(this.addr);
    }

    public int getOrigWidth() {
        return jniGetOrigWidth(this.addr);
    }

    public int getOrigHeight() {
        return jniGetOrigHeight(this.addr);
    }

    public int getWidth() {
        return jniGetWidth(this.addr);
    }

    public int getHeight() {
        return jniGetHeight(this.addr);
    }

    public int getNumBlocksX() {
        return jniGetNumBlocksX(this.addr);
    }

    public int getNumBlocksY() {
        return jniGetNumBlocksY(this.addr);
    }

    public int getTotalBlocks() {
        return jniGetTotalBlocks(this.addr);
    }

    public int getFirstSliceIndex() {
        return jniGetFirstSliceIndex(this.addr);
    }

    public boolean hasAlphaFlag() {
        return jniHasAlphaFlag(this.addr);
    }

    public boolean hasIframeFlag() {
        return jniHasIframeFlag(this.addr);
    }
}
