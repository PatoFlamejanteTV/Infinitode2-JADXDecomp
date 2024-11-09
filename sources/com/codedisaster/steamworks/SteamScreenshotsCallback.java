package com.codedisaster.steamworks;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamScreenshotsCallback.class */
public interface SteamScreenshotsCallback {
    void onScreenshotReady(SteamScreenshotHandle steamScreenshotHandle, SteamResult steamResult);

    void onScreenshotRequested();
}
