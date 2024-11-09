package com.codedisaster.steamworks;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamMatchmakingPlayersResponse.class */
public abstract class SteamMatchmakingPlayersResponse extends SteamInterface {
    public abstract void addPlayerToList(String str, int i, float f);

    public abstract void playersFailedToRespond();

    public abstract void playersRefreshComplete();

    private static native long createProxy(SteamMatchmakingPlayersResponse steamMatchmakingPlayersResponse);

    @Override // com.codedisaster.steamworks.SteamInterface
    public /* bridge */ /* synthetic */ void dispose() {
        super.dispose();
    }

    protected SteamMatchmakingPlayersResponse() {
        super(-1L);
        this.callback = createProxy(this);
    }
}
