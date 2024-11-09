package com.codedisaster.steamworks;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamNativeIntHandle.class */
public abstract class SteamNativeIntHandle {
    int handle;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SteamNativeIntHandle(int i) {
        this.handle = i;
    }

    public static <T extends SteamNativeIntHandle> int getNativeHandle(T t) {
        return t.handle;
    }

    public int hashCode() {
        return Integer.valueOf(this.handle).hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof SteamNativeIntHandle) && this.handle == ((SteamNativeIntHandle) obj).handle;
    }

    public String toString() {
        return Integer.toHexString(this.handle);
    }
}
