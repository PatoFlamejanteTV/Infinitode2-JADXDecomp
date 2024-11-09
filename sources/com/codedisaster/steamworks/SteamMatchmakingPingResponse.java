package com.codedisaster.steamworks;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamMatchmakingPingResponse.class */
public abstract class SteamMatchmakingPingResponse extends SteamInterface {
    public abstract void serverResponded(SteamMatchmakingGameServerItem steamMatchmakingGameServerItem);

    public abstract void serverFailedToRespond();

    private static native long createProxy(SteamMatchmakingPingResponse steamMatchmakingPingResponse);

    @Override // com.codedisaster.steamworks.SteamInterface
    public /* bridge */ /* synthetic */ void dispose() {
        super.dispose();
    }

    protected SteamMatchmakingPingResponse() {
        super(-1L);
        this.callback = createProxy(this);
    }
}
