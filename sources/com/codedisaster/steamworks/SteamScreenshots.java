package com.codedisaster.steamworks;

import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamScreenshots.class */
public class SteamScreenshots extends SteamInterface {
    @Override // com.codedisaster.steamworks.SteamInterface
    public /* bridge */ /* synthetic */ void dispose() {
        super.dispose();
    }

    public SteamScreenshots(SteamScreenshotsCallback steamScreenshotsCallback) {
        super(SteamScreenshotsNative.createCallback(new SteamScreenshotsCallbackAdapter(steamScreenshotsCallback)));
    }

    public SteamScreenshotHandle writeScreenshot(ByteBuffer byteBuffer, int i, int i2) {
        return new SteamScreenshotHandle(SteamScreenshotsNative.writeScreenshot(byteBuffer, byteBuffer.remaining(), i, i2));
    }

    public SteamScreenshotHandle addScreenshotToLibrary(String str, String str2, int i, int i2) {
        return new SteamScreenshotHandle(SteamScreenshotsNative.addScreenshotToLibrary(str, str2, i, i2));
    }

    public void triggerScreenshot() {
        SteamScreenshotsNative.triggerScreenshot();
    }

    public void hookScreenshots(boolean z) {
        SteamScreenshotsNative.hookScreenshots(z);
    }

    public boolean setLocation(SteamScreenshotHandle steamScreenshotHandle, String str) {
        return SteamScreenshotsNative.setLocation(steamScreenshotHandle.handle, str);
    }

    public boolean tagUser(SteamScreenshotHandle steamScreenshotHandle, SteamID steamID) {
        return SteamScreenshotsNative.tagUser(steamScreenshotHandle.handle, steamID.handle);
    }

    public boolean tagPublishedFile(SteamScreenshotHandle steamScreenshotHandle, SteamPublishedFileID steamPublishedFileID) {
        return SteamScreenshotsNative.tagPublishedFile(steamScreenshotHandle.handle, steamPublishedFileID.handle);
    }

    public boolean isScreenshotsHooked() {
        return SteamScreenshotsNative.isScreenshotsHooked();
    }
}
