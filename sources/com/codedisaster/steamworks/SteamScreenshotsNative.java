package com.codedisaster.steamworks;

import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamScreenshotsNative.class */
final class SteamScreenshotsNative {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long createCallback(SteamScreenshotsCallbackAdapter steamScreenshotsCallbackAdapter);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int writeScreenshot(ByteBuffer byteBuffer, int i, int i2, int i3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int addScreenshotToLibrary(String str, String str2, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void triggerScreenshot();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void hookScreenshots(boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setLocation(int i, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean tagUser(int i, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean tagPublishedFile(int i, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean isScreenshotsHooked();

    SteamScreenshotsNative() {
    }
}
