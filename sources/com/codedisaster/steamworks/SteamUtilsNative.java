package com.codedisaster.steamworks;

import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUtilsNative.class */
final class SteamUtilsNative {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long createCallback(SteamUtilsCallbackAdapter steamUtilsCallbackAdapter);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getSecondsSinceAppActive();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getSecondsSinceComputerActive();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getConnectedUniverse();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getServerRealTime();

    static native String getIPCountry();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getImageWidth(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getImageHeight(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getImageSize(int i, int[] iArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getImageRGBA(int i, ByteBuffer byteBuffer, int i2, int i3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getAppID();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setOverlayNotificationPosition(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean isAPICallCompleted(long j, boolean[] zArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getAPICallFailureReason(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void enableWarningMessageHook(long j, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean isOverlayEnabled();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean isSteamRunningOnSteamDeck();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean showFloatingGamepadTextInput(int i, int i2, int i3, int i4, int i5);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean dismissFloatingGamepadTextInput();

    SteamUtilsNative() {
    }
}
