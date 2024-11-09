package com.codedisaster.steamworks;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamScreenshotsCallbackAdapter.class */
public class SteamScreenshotsCallbackAdapter extends SteamCallbackAdapter<SteamScreenshotsCallback> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SteamScreenshotsCallbackAdapter(SteamScreenshotsCallback steamScreenshotsCallback) {
        super(steamScreenshotsCallback);
    }

    void onScreenshotReady(int i, int i2) {
        ((SteamScreenshotsCallback) this.callback).onScreenshotReady(new SteamScreenshotHandle(i), SteamResult.byValue(i2));
    }

    void onScreenshotRequested() {
        ((SteamScreenshotsCallback) this.callback).onScreenshotRequested();
    }
}
