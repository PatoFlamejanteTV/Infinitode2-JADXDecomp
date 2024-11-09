package com.codedisaster.steamworks;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamApps.class */
public class SteamApps extends SteamInterface {
    @Override // com.codedisaster.steamworks.SteamInterface
    public /* bridge */ /* synthetic */ void dispose() {
        super.dispose();
    }

    public boolean isSubscribed() {
        return SteamAppsNative.isSubscribed();
    }

    public boolean isLowViolence() {
        return SteamAppsNative.isLowViolence();
    }

    public boolean isCybercafe() {
        return SteamAppsNative.isCybercafe();
    }

    public boolean isVACBanned() {
        return SteamAppsNative.isVACBanned();
    }

    public String getCurrentGameLanguage() {
        return SteamAppsNative.getCurrentGameLanguage();
    }

    public String getAvailableGameLanguages() {
        return SteamAppsNative.getAvailableGameLanguages();
    }

    public boolean isSubscribedApp(int i) {
        return SteamAppsNative.isSubscribedApp(i);
    }

    public boolean isDlcInstalled(int i) {
        return SteamAppsNative.isDlcInstalled(i);
    }

    public int getEarliestPurchaseUnixTime(int i) {
        return SteamAppsNative.getEarliestPurchaseUnixTime(i);
    }

    public boolean isSubscribedFromFreeWeekend() {
        return SteamAppsNative.isSubscribedFromFreeWeekend();
    }

    public int getDLCCount() {
        return SteamAppsNative.getDLCCount();
    }

    public void installDLC(int i) {
        SteamAppsNative.installDLC(i);
    }

    public void uninstallDLC(int i) {
        SteamAppsNative.uninstallDLC(i);
    }

    public SteamID getAppOwner() {
        return new SteamID(SteamAppsNative.getAppOwner());
    }

    public int getAppBuildId() {
        return SteamAppsNative.getAppBuildId();
    }
}
