package com.codedisaster.steamworks;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUserStats.class */
public class SteamUserStats extends SteamInterface {

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUserStats$LeaderboardDataRequest.class */
    public enum LeaderboardDataRequest {
        Global,
        GlobalAroundUser,
        Friends,
        Users
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUserStats$LeaderboardDisplayType.class */
    public enum LeaderboardDisplayType {
        None,
        Numeric,
        TimeSeconds,
        TimeMilliSeconds
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUserStats$LeaderboardSortMethod.class */
    public enum LeaderboardSortMethod {
        None,
        Ascending,
        Descending
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUserStats$LeaderboardUploadScoreMethod.class */
    public enum LeaderboardUploadScoreMethod {
        None,
        KeepBest,
        ForceUpdate
    }

    @Override // com.codedisaster.steamworks.SteamInterface
    public /* bridge */ /* synthetic */ void dispose() {
        super.dispose();
    }

    public SteamUserStats(SteamUserStatsCallback steamUserStatsCallback) {
        super(SteamUserStatsNative.createCallback(new SteamUserStatsCallbackAdapter(steamUserStatsCallback)));
    }

    public boolean requestCurrentStats() {
        return SteamUserStatsNative.requestCurrentStats();
    }

    public int getStatI(String str, int i) {
        int[] iArr = new int[1];
        if (SteamUserStatsNative.getStat(str, iArr)) {
            return iArr[0];
        }
        return i;
    }

    public boolean setStatI(String str, int i) {
        return SteamUserStatsNative.setStat(str, i);
    }

    public float getStatF(String str, float f) {
        float[] fArr = new float[1];
        if (SteamUserStatsNative.getStat(str, fArr)) {
            return fArr[0];
        }
        return f;
    }

    public boolean setStatF(String str, float f) {
        return SteamUserStatsNative.setStat(str, f);
    }

    public boolean isAchieved(String str, boolean z) {
        boolean[] zArr = new boolean[1];
        if (SteamUserStatsNative.getAchievement(str, zArr)) {
            return zArr[0];
        }
        return z;
    }

    public boolean setAchievement(String str) {
        return SteamUserStatsNative.setAchievement(str);
    }

    public boolean clearAchievement(String str) {
        return SteamUserStatsNative.clearAchievement(str);
    }

    public boolean storeStats() {
        return SteamUserStatsNative.storeStats();
    }

    public boolean indicateAchievementProgress(String str, int i, int i2) {
        return SteamUserStatsNative.indicateAchievementProgress(str, i, i2);
    }

    public int getNumAchievements() {
        return SteamUserStatsNative.getNumAchievements();
    }

    public String getAchievementName(int i) {
        return SteamUserStatsNative.getAchievementName(i);
    }

    public boolean resetAllStats(boolean z) {
        return SteamUserStatsNative.resetAllStats(z);
    }

    public SteamAPICall findOrCreateLeaderboard(String str, LeaderboardSortMethod leaderboardSortMethod, LeaderboardDisplayType leaderboardDisplayType) {
        return new SteamAPICall(SteamUserStatsNative.findOrCreateLeaderboard(this.callback, str, leaderboardSortMethod.ordinal(), leaderboardDisplayType.ordinal()));
    }

    public SteamAPICall findLeaderboard(String str) {
        return new SteamAPICall(SteamUserStatsNative.findLeaderboard(this.callback, str));
    }

    public String getLeaderboardName(SteamLeaderboardHandle steamLeaderboardHandle) {
        return SteamUserStatsNative.getLeaderboardName(steamLeaderboardHandle.handle);
    }

    public int getLeaderboardEntryCount(SteamLeaderboardHandle steamLeaderboardHandle) {
        return SteamUserStatsNative.getLeaderboardEntryCount(steamLeaderboardHandle.handle);
    }

    public LeaderboardSortMethod getLeaderboardSortMethod(SteamLeaderboardHandle steamLeaderboardHandle) {
        return LeaderboardSortMethod.values()[SteamUserStatsNative.getLeaderboardSortMethod(steamLeaderboardHandle.handle)];
    }

    public LeaderboardDisplayType getLeaderboardDisplayType(SteamLeaderboardHandle steamLeaderboardHandle) {
        return LeaderboardDisplayType.values()[SteamUserStatsNative.getLeaderboardDisplayType(steamLeaderboardHandle.handle)];
    }

    public SteamAPICall downloadLeaderboardEntries(SteamLeaderboardHandle steamLeaderboardHandle, LeaderboardDataRequest leaderboardDataRequest, int i, int i2) {
        return new SteamAPICall(SteamUserStatsNative.downloadLeaderboardEntries(this.callback, steamLeaderboardHandle.handle, leaderboardDataRequest.ordinal(), i, i2));
    }

    public SteamAPICall downloadLeaderboardEntriesForUsers(SteamLeaderboardHandle steamLeaderboardHandle, SteamID[] steamIDArr) {
        int length = steamIDArr.length;
        long[] jArr = new long[length];
        for (int i = 0; i < length; i++) {
            jArr[i] = steamIDArr[i].handle;
        }
        return new SteamAPICall(SteamUserStatsNative.downloadLeaderboardEntriesForUsers(this.callback, steamLeaderboardHandle.handle, jArr, length));
    }

    public boolean getDownloadedLeaderboardEntry(SteamLeaderboardEntriesHandle steamLeaderboardEntriesHandle, int i, SteamLeaderboardEntry steamLeaderboardEntry, int[] iArr) {
        if (iArr == null) {
            return SteamUserStatsNative.getDownloadedLeaderboardEntry(steamLeaderboardEntriesHandle.handle, i, steamLeaderboardEntry);
        }
        return SteamUserStatsNative.getDownloadedLeaderboardEntry(steamLeaderboardEntriesHandle.handle, i, steamLeaderboardEntry, iArr, iArr.length);
    }

    public SteamAPICall uploadLeaderboardScore(SteamLeaderboardHandle steamLeaderboardHandle, LeaderboardUploadScoreMethod leaderboardUploadScoreMethod, int i, int[] iArr) {
        long uploadLeaderboardScore;
        if (iArr == null) {
            uploadLeaderboardScore = SteamUserStatsNative.uploadLeaderboardScore(this.callback, steamLeaderboardHandle.handle, leaderboardUploadScoreMethod.ordinal(), i);
        } else {
            uploadLeaderboardScore = SteamUserStatsNative.uploadLeaderboardScore(this.callback, steamLeaderboardHandle.handle, leaderboardUploadScoreMethod.ordinal(), i, iArr, iArr.length);
        }
        return new SteamAPICall(uploadLeaderboardScore);
    }

    public SteamAPICall getNumberOfCurrentPlayers() {
        return new SteamAPICall(SteamUserStatsNative.getNumberOfCurrentPlayers(this.callback));
    }

    public SteamAPICall requestGlobalStats(int i) {
        return new SteamAPICall(SteamUserStatsNative.requestGlobalStats(this.callback, i));
    }

    public long getGlobalStat(String str, long j) {
        long[] jArr = new long[1];
        if (SteamUserStatsNative.getGlobalStat(str, jArr)) {
            return jArr[0];
        }
        return j;
    }

    public double getGlobalStat(String str, double d) {
        double[] dArr = new double[1];
        if (SteamUserStatsNative.getGlobalStat(str, dArr)) {
            return dArr[0];
        }
        return d;
    }

    public int getGlobalStatHistory(String str, long[] jArr) {
        return SteamUserStatsNative.getGlobalStatHistory(str, jArr, jArr.length);
    }

    public int getGlobalStatHistory(String str, double[] dArr) {
        return SteamUserStatsNative.getGlobalStatHistory(str, dArr, dArr.length);
    }
}
