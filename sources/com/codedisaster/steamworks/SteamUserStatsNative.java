package com.codedisaster.steamworks;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUserStatsNative.class */
final class SteamUserStatsNative {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long createCallback(SteamUserStatsCallbackAdapter steamUserStatsCallbackAdapter);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean requestCurrentStats();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getStat(String str, int[] iArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setStat(String str, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getStat(String str, float[] fArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setStat(String str, float f);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getAchievement(String str, boolean[] zArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setAchievement(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean clearAchievement(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean storeStats();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean indicateAchievementProgress(String str, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getNumAchievements();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native String getAchievementName(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean resetAllStats(boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long findOrCreateLeaderboard(long j, String str, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long findLeaderboard(long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native String getLeaderboardName(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getLeaderboardEntryCount(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getLeaderboardSortMethod(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getLeaderboardDisplayType(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long downloadLeaderboardEntries(long j, long j2, int i, int i2, int i3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long downloadLeaderboardEntriesForUsers(long j, long j2, long[] jArr, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getDownloadedLeaderboardEntry(long j, int i, SteamLeaderboardEntry steamLeaderboardEntry, int[] iArr, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getDownloadedLeaderboardEntry(long j, int i, SteamLeaderboardEntry steamLeaderboardEntry);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long uploadLeaderboardScore(long j, long j2, int i, int i2, int[] iArr, int i3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long uploadLeaderboardScore(long j, long j2, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long getNumberOfCurrentPlayers(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long requestGlobalStats(long j, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getGlobalStat(String str, long[] jArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getGlobalStat(String str, double[] dArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getGlobalStatHistory(String str, long[] jArr, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getGlobalStatHistory(String str, double[] dArr, int i);

    SteamUserStatsNative() {
    }
}
