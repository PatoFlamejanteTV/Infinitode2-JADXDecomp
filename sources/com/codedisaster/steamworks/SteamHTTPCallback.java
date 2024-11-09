package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamHTTP;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamHTTPCallback.class */
public interface SteamHTTPCallback {
    void onHTTPRequestCompleted(SteamHTTPRequestHandle steamHTTPRequestHandle, long j, boolean z, SteamHTTP.HTTPStatusCode hTTPStatusCode, int i);

    void onHTTPRequestHeadersReceived(SteamHTTPRequestHandle steamHTTPRequestHandle, long j);

    void onHTTPRequestDataReceived(SteamHTTPRequestHandle steamHTTPRequestHandle, long j, int i, int i2);
}
