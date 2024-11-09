package com.crashinvaders.basisu.wrapper;

import java.io.Closeable;

/* loaded from: infinitode-2.jar:com/crashinvaders/basisu/wrapper/Ktx2ImageLevelInfo.class */
public class Ktx2ImageLevelInfo implements Closeable {
    long addr;

    private native int jniGetLevelIndex(long j);

    private native int jniGetLayerIndex(long j);

    private native int jniGetFaceIndex(long j);

    private native int jniGetOrigWidth(long j);

    private native int jniGetOrigHeight(long j);

    private native int jniGetWidth(long j);

    private native int jniGetHeight(long j);

    private native int jniGetNumBlocksX(long j);

    private native int jniGetNumBlocksY(long j);

    private native int jniGetTotalBlocks(long j);

    private native boolean jniGetAlphaFlag(long j);

    private native boolean jniGetIframeFlag(long j);

    private static native long jniCreate();

    private static native void jniDispose(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public Ktx2ImageLevelInfo() {
        this.addr = jniCreate();
    }

    Ktx2ImageLevelInfo(Object obj) {
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

    public int getLevelIndex() {
        return jniGetLevelIndex(this.addr);
    }

    public int getLayerIndex() {
        return jniGetLayerIndex(this.addr);
    }

    public int getFaceIndex() {
        return jniGetFaceIndex(this.addr);
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

    public boolean getAlphaFlag() {
        return jniGetAlphaFlag(this.addr);
    }

    public boolean getIframeFlag() {
        return jniGetIframeFlag(this.addr);
    }
}
