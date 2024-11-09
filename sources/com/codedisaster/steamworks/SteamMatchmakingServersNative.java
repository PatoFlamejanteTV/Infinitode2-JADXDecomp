package com.codedisaster.steamworks;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamMatchmakingServersNative.class */
final class SteamMatchmakingServersNative {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long requestInternetServerList(int i, SteamMatchmakingKeyValuePair[] steamMatchmakingKeyValuePairArr, int i2, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long requestLANServerList(int i, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long requestFriendsServerList(int i, SteamMatchmakingKeyValuePair[] steamMatchmakingKeyValuePairArr, int i2, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long requestFavoritesServerList(int i, SteamMatchmakingKeyValuePair[] steamMatchmakingKeyValuePairArr, int i2, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long requestHistoryServerList(int i, SteamMatchmakingKeyValuePair[] steamMatchmakingKeyValuePairArr, int i2, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long requestSpectatorServerList(int i, SteamMatchmakingKeyValuePair[] steamMatchmakingKeyValuePairArr, int i2, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void releaseRequest(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getServerDetails(long j, int i, SteamMatchmakingGameServerItem steamMatchmakingGameServerItem);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void cancelQuery(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void refreshQuery(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean isRefreshing(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getServerCount(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void refreshServer(long j, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int pingServer(int i, short s, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int playerDetails(int i, short s, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int serverRules(int i, short s, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void cancelServerQuery(int i);

    SteamMatchmakingServersNative() {
    }
}
