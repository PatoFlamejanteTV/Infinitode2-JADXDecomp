package com.codedisaster.steamworks;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamMatchmakingServers.class */
public class SteamMatchmakingServers extends SteamInterface {
    @Override // com.codedisaster.steamworks.SteamInterface
    public /* bridge */ /* synthetic */ void dispose() {
        super.dispose();
    }

    public SteamMatchmakingServers() {
        super(-1L);
    }

    public SteamServerListRequest requestInternetServerList(int i, SteamMatchmakingKeyValuePair[] steamMatchmakingKeyValuePairArr, SteamMatchmakingServerListResponse steamMatchmakingServerListResponse) {
        return new SteamServerListRequest(SteamMatchmakingServersNative.requestInternetServerList(i, steamMatchmakingKeyValuePairArr, steamMatchmakingKeyValuePairArr.length, steamMatchmakingServerListResponse.callback));
    }

    public SteamServerListRequest requestLANServerList(int i, SteamMatchmakingServerListResponse steamMatchmakingServerListResponse) {
        return new SteamServerListRequest(SteamMatchmakingServersNative.requestLANServerList(i, steamMatchmakingServerListResponse.callback));
    }

    public SteamServerListRequest requestFriendsServerList(int i, SteamMatchmakingKeyValuePair[] steamMatchmakingKeyValuePairArr, SteamMatchmakingServerListResponse steamMatchmakingServerListResponse) {
        return new SteamServerListRequest(SteamMatchmakingServersNative.requestFriendsServerList(i, steamMatchmakingKeyValuePairArr, steamMatchmakingKeyValuePairArr.length, steamMatchmakingServerListResponse.callback));
    }

    public SteamServerListRequest requestFavoritesServerList(int i, SteamMatchmakingKeyValuePair[] steamMatchmakingKeyValuePairArr, SteamMatchmakingServerListResponse steamMatchmakingServerListResponse) {
        return new SteamServerListRequest(SteamMatchmakingServersNative.requestFavoritesServerList(i, steamMatchmakingKeyValuePairArr, steamMatchmakingKeyValuePairArr.length, steamMatchmakingServerListResponse.callback));
    }

    public SteamServerListRequest requestHistoryServerList(int i, SteamMatchmakingKeyValuePair[] steamMatchmakingKeyValuePairArr, SteamMatchmakingServerListResponse steamMatchmakingServerListResponse) {
        return new SteamServerListRequest(SteamMatchmakingServersNative.requestHistoryServerList(i, steamMatchmakingKeyValuePairArr, steamMatchmakingKeyValuePairArr.length, steamMatchmakingServerListResponse.callback));
    }

    public SteamServerListRequest requestSpectatorServerList(int i, SteamMatchmakingKeyValuePair[] steamMatchmakingKeyValuePairArr, SteamMatchmakingServerListResponse steamMatchmakingServerListResponse) {
        return new SteamServerListRequest(SteamMatchmakingServersNative.requestSpectatorServerList(i, steamMatchmakingKeyValuePairArr, steamMatchmakingKeyValuePairArr.length, steamMatchmakingServerListResponse.callback));
    }

    public void releaseRequest(SteamServerListRequest steamServerListRequest) {
        SteamMatchmakingServersNative.releaseRequest(steamServerListRequest.handle);
    }

    public boolean getServerDetails(SteamServerListRequest steamServerListRequest, int i, SteamMatchmakingGameServerItem steamMatchmakingGameServerItem) {
        return SteamMatchmakingServersNative.getServerDetails(steamServerListRequest.handle, i, steamMatchmakingGameServerItem);
    }

    public void cancelQuery(SteamServerListRequest steamServerListRequest) {
        SteamMatchmakingServersNative.cancelQuery(steamServerListRequest.handle);
    }

    public void refreshQuery(SteamServerListRequest steamServerListRequest) {
        SteamMatchmakingServersNative.refreshQuery(steamServerListRequest.handle);
    }

    public boolean isRefreshing(SteamServerListRequest steamServerListRequest) {
        return SteamMatchmakingServersNative.isRefreshing(steamServerListRequest.handle);
    }

    public int getServerCount(SteamServerListRequest steamServerListRequest) {
        return SteamMatchmakingServersNative.getServerCount(steamServerListRequest.handle);
    }

    public void refreshServer(SteamServerListRequest steamServerListRequest, int i) {
        SteamMatchmakingServersNative.refreshServer(steamServerListRequest.handle, i);
    }

    public SteamServerQuery pingServer(int i, short s, SteamMatchmakingPingResponse steamMatchmakingPingResponse) {
        return new SteamServerQuery(SteamMatchmakingServersNative.pingServer(i, s, steamMatchmakingPingResponse.callback));
    }

    public SteamServerQuery playerDetails(int i, short s, SteamMatchmakingPlayersResponse steamMatchmakingPlayersResponse) {
        return new SteamServerQuery(SteamMatchmakingServersNative.playerDetails(i, s, steamMatchmakingPlayersResponse.callback));
    }

    public SteamServerQuery serverRules(int i, short s, SteamMatchmakingRulesResponse steamMatchmakingRulesResponse) {
        return new SteamServerQuery(SteamMatchmakingServersNative.serverRules(i, s, steamMatchmakingRulesResponse.callback));
    }

    public void cancelServerQuery(SteamServerQuery steamServerQuery) {
        SteamMatchmakingServersNative.cancelServerQuery(steamServerQuery.handle);
    }
}
