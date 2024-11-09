package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamNetworking;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamNetworkingCallback.class */
public interface SteamNetworkingCallback {
    void onP2PSessionConnectFail(SteamID steamID, SteamNetworking.P2PSessionError p2PSessionError);

    void onP2PSessionRequest(SteamID steamID);
}
