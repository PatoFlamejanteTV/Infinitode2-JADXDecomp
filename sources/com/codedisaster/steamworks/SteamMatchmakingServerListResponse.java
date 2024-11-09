package com.codedisaster.steamworks;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamMatchmakingServerListResponse.class */
public abstract class SteamMatchmakingServerListResponse extends SteamInterface {
    public abstract void serverResponded(SteamServerListRequest steamServerListRequest, int i);

    public abstract void serverFailedToRespond(SteamServerListRequest steamServerListRequest, int i);

    public abstract void refreshComplete(SteamServerListRequest steamServerListRequest, Response response);

    private static native long createProxy(SteamMatchmakingServerListResponse steamMatchmakingServerListResponse);

    @Override // com.codedisaster.steamworks.SteamInterface
    public /* bridge */ /* synthetic */ void dispose() {
        super.dispose();
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamMatchmakingServerListResponse$Response.class */
    public enum Response {
        ServerResponded,
        ServerFailedToRespond,
        NoServersListedOnMasterServer;

        private static final Response[] values = values();

        static Response byOrdinal(int i) {
            return values[i];
        }
    }

    protected SteamMatchmakingServerListResponse() {
        super(-1L);
        this.callback = createProxy(this);
    }

    void serverResponded(long j, int i) {
        serverResponded(new SteamServerListRequest(j), i);
    }

    void serverFailedToRespond(long j, int i) {
        serverFailedToRespond(new SteamServerListRequest(j), i);
    }

    void refreshComplete(long j, int i) {
        refreshComplete(new SteamServerListRequest(j), Response.byOrdinal(i));
    }
}
