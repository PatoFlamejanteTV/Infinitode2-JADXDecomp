package com.codedisaster.steamworks;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamAuthTicket.class */
public class SteamAuthTicket extends SteamNativeHandle {
    static final long AuthTicketInvalid = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SteamAuthTicket(long j) {
        super(j);
    }

    public boolean isValid() {
        return this.handle != 0;
    }
}
