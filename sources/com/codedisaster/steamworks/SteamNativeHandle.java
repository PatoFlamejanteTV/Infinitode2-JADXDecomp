package com.codedisaster.steamworks;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamNativeHandle.class */
public abstract class SteamNativeHandle {
    long handle;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SteamNativeHandle(long j) {
        this.handle = j;
    }

    public static <T extends SteamNativeHandle> long getNativeHandle(T t) {
        return t.handle;
    }

    public int hashCode() {
        return Long.valueOf(this.handle).hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof SteamNativeHandle) && this.handle == ((SteamNativeHandle) obj).handle;
    }

    public String toString() {
        return Long.toHexString(this.handle);
    }
}
