package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamNetworking;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamNetworkingCallbackAdapter.class */
class SteamNetworkingCallbackAdapter extends SteamCallbackAdapter<SteamNetworkingCallback> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SteamNetworkingCallbackAdapter(SteamNetworkingCallback steamNetworkingCallback) {
        super(steamNetworkingCallback);
    }

    void onP2PSessionConnectFail(long j, int i) {
        ((SteamNetworkingCallback) this.callback).onP2PSessionConnectFail(new SteamID(j), SteamNetworking.P2PSessionError.byOrdinal(i));
    }

    void onP2PSessionRequest(long j) {
        ((SteamNetworkingCallback) this.callback).onP2PSessionRequest(new SteamID(j));
    }
}
