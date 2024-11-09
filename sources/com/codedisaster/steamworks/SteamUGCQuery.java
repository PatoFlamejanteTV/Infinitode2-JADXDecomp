package com.codedisaster.steamworks;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUGCQuery.class */
public class SteamUGCQuery extends SteamNativeHandle {
    public SteamUGCQuery(long j) {
        super(j);
    }

    public boolean isValid() {
        return this.handle != -1;
    }
}
