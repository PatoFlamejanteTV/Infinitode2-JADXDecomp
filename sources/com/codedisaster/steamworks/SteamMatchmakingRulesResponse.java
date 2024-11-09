package com.codedisaster.steamworks;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamMatchmakingRulesResponse.class */
public abstract class SteamMatchmakingRulesResponse extends SteamInterface {
    public abstract void rulesResponded(String str, String str2);

    public abstract void rulesFailedToRespond();

    public abstract void rulesRefreshComplete();

    private static native long createProxy(SteamMatchmakingRulesResponse steamMatchmakingRulesResponse);

    @Override // com.codedisaster.steamworks.SteamInterface
    public /* bridge */ /* synthetic */ void dispose() {
        super.dispose();
    }

    protected SteamMatchmakingRulesResponse() {
        super(-1L);
        this.callback = createProxy(this);
    }
}
