package com.codedisaster.steamworks;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamAppsNative.class */
final class SteamAppsNative {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean isSubscribed();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean isLowViolence();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean isCybercafe();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean isVACBanned();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native String getCurrentGameLanguage();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native String getAvailableGameLanguages();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean isSubscribedApp(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean isDlcInstalled(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getEarliestPurchaseUnixTime(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean isSubscribedFromFreeWeekend();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getDLCCount();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void installDLC(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void uninstallDLC(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long getAppOwner();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getAppBuildId();

    SteamAppsNative() {
    }
}
