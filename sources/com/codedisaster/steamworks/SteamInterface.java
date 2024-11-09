package com.codedisaster.steamworks;

import java.nio.Buffer;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamInterface.class */
abstract class SteamInterface {
    protected long callback;

    protected static native void deleteCallback(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public SteamInterface() {
        this(0L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SteamInterface(long j) {
        this.callback = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCallback(long j) {
        this.callback = j;
    }

    public void dispose() {
        deleteCallback(this.callback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void checkBuffer(Buffer buffer) {
        if (!buffer.isDirect()) {
            throw new SteamException("Direct buffer required.");
        }
    }

    void checkArray(byte[] bArr, int i) {
        if (bArr.length < i) {
            throw new SteamException("Array too small, " + bArr.length + " found but " + i + " expected.");
        }
    }
}
