package com.codedisaster.steamworks;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamMatchmakingGameServerItem.class */
public class SteamMatchmakingGameServerItem {
    SteamMatchmakingServerNetAdr netAdr = new SteamMatchmakingServerNetAdr();
    int ping;
    boolean hadSuccessfulResponse;
    boolean doNotRefresh;
    String gameDir;
    String map;
    String gameDescription;
    int appID;
    int players;
    int maxPlayers;
    int botPlayers;
    boolean password;
    boolean secure;
    int timeLastPlayed;
    int serverVersion;
    String serverName;
    String gameTags;
    long steamID;

    public SteamMatchmakingServerNetAdr getNetAdr() {
        return this.netAdr;
    }

    public int getPing() {
        return this.ping;
    }

    public boolean hadSuccessfulResponse() {
        return this.hadSuccessfulResponse;
    }

    public boolean doNotRefresh() {
        return this.doNotRefresh;
    }

    public String getGameDir() {
        return this.gameDir;
    }

    public String getMap() {
        return this.map;
    }

    public String getGameDescription() {
        return this.gameDescription;
    }

    public int getAppID() {
        return this.appID;
    }

    public int getPlayers() {
        return this.players;
    }

    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    public int getBotPlayers() {
        return this.botPlayers;
    }

    public boolean hasPassword() {
        return this.password;
    }

    public boolean isSecure() {
        return this.secure;
    }

    public int getTimeLastPlayed() {
        return this.timeLastPlayed;
    }

    public int getServerVersion() {
        return this.serverVersion;
    }

    public String getServerName() {
        return this.serverName;
    }

    public String getGameTags() {
        return this.gameTags;
    }

    public SteamID getSteamID() {
        return new SteamID(this.steamID);
    }
}
