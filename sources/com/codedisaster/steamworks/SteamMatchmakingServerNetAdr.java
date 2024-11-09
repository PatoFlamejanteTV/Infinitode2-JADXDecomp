package com.codedisaster.steamworks;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamMatchmakingServerNetAdr.class */
public class SteamMatchmakingServerNetAdr {
    short connectionPort;
    short queryPort;
    int ip;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SteamMatchmakingServerNetAdr() {
    }

    public SteamMatchmakingServerNetAdr(int i, short s, short s2) {
        this.ip = i;
        this.queryPort = s;
        this.connectionPort = s2;
    }

    public short getConnectionPort() {
        return this.connectionPort;
    }

    public short getQueryPort() {
        return this.queryPort;
    }

    public int getIP() {
        return this.ip;
    }

    public String getConnectionAddressString() {
        return toString(this.ip, this.connectionPort);
    }

    public String getQueryAddressString() {
        return toString(this.ip, this.queryPort);
    }

    private static String toString(int i, short s) {
        return String.format("%d.%d.%d.%d:%d", Integer.valueOf(i >>> 24), Integer.valueOf((i >> 16) & 255), Integer.valueOf((i >> 8) & 255), Integer.valueOf(i & 255), Short.valueOf(s));
    }
}
