package com.codedisaster.steamworks;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUGCUpdateHandle.class */
public class SteamUGCUpdateHandle extends SteamNativeHandle {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SteamUGCUpdateHandle(long j) {
        super(j);
    }

    public boolean isValid() {
        return this.handle != -1;
    }
}
