package com.codedisaster.steamworks;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamServerListRequest.class */
public class SteamServerListRequest extends SteamNativeHandle {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SteamServerListRequest(long j) {
        super(j);
    }

    public boolean isValid() {
        return this.handle != 0;
    }
}
