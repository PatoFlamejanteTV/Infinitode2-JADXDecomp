package com.codedisaster.steamworks;

import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamNetworking.class */
public class SteamNetworking extends SteamInterface {
    private final boolean isServer;
    private final int[] tmpIntResult;
    private final long[] tmpLongResult;

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamNetworking$P2PSend.class */
    public enum P2PSend {
        Unreliable,
        UnreliableNoDelay,
        Reliable,
        ReliableWithBuffering
    }

    @Override // com.codedisaster.steamworks.SteamInterface
    public /* bridge */ /* synthetic */ void dispose() {
        super.dispose();
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamNetworking$P2PSessionError.class */
    public enum P2PSessionError {
        None,
        NotRunningApp,
        NoRightsToApp,
        DestinationNotLoggedIn,
        Timeout;

        private static final P2PSessionError[] values = values();

        public static P2PSessionError byOrdinal(int i) {
            return values[i];
        }
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamNetworking$P2PSessionState.class */
    public static class P2PSessionState {
        byte connectionActive;
        byte connecting;
        byte sessionError;
        byte usingRelay;
        int bytesQueuedForSend;
        int packetsQueuedForSend;
        int remoteIP;
        short remotePort;

        public boolean isConnectionActive() {
            return this.connectionActive != 0;
        }

        public boolean isConnecting() {
            return this.connecting != 0;
        }

        public P2PSessionError getLastSessionError() {
            return P2PSessionError.byOrdinal(this.sessionError);
        }

        public boolean isUsingRelay() {
            return this.usingRelay != 0;
        }

        public int getBytesQueuedForSend() {
            return this.bytesQueuedForSend;
        }

        public int getPacketsQueuedForSend() {
            return this.packetsQueuedForSend;
        }

        public int getRemoteIP() {
            return this.remoteIP;
        }

        public short getRemotePort() {
            return this.remotePort;
        }
    }

    public SteamNetworking(SteamNetworkingCallback steamNetworkingCallback) {
        this(false, SteamNetworkingNative.createCallback(new SteamNetworkingCallbackAdapter(steamNetworkingCallback)));
    }

    SteamNetworking(boolean z, long j) {
        super(j);
        this.tmpIntResult = new int[1];
        this.tmpLongResult = new long[1];
        this.isServer = z;
    }

    public boolean sendP2PPacket(SteamID steamID, ByteBuffer byteBuffer, P2PSend p2PSend, int i) {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        return SteamNetworkingNative.sendP2PPacket(this.isServer, steamID.handle, byteBuffer, byteBuffer.position(), byteBuffer.remaining(), p2PSend.ordinal(), i);
    }

    public boolean isP2PPacketAvailable(int i, int[] iArr) {
        return SteamNetworkingNative.isP2PPacketAvailable(this.isServer, iArr, i);
    }

    public int readP2PPacket(SteamID steamID, ByteBuffer byteBuffer, int i) {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        if (SteamNetworkingNative.readP2PPacket(this.isServer, byteBuffer, byteBuffer.position(), byteBuffer.remaining(), this.tmpIntResult, this.tmpLongResult, i)) {
            steamID.handle = this.tmpLongResult[0];
            return this.tmpIntResult[0];
        }
        return 0;
    }

    public boolean acceptP2PSessionWithUser(SteamID steamID) {
        return SteamNetworkingNative.acceptP2PSessionWithUser(this.isServer, steamID.handle);
    }

    public boolean closeP2PSessionWithUser(SteamID steamID) {
        return SteamNetworkingNative.closeP2PSessionWithUser(this.isServer, steamID.handle);
    }

    public boolean closeP2PChannelWithUser(SteamID steamID, int i) {
        return SteamNetworkingNative.closeP2PChannelWithUser(this.isServer, steamID.handle, i);
    }

    public boolean getP2PSessionState(SteamID steamID, P2PSessionState p2PSessionState) {
        return SteamNetworkingNative.getP2PSessionState(this.isServer, steamID.handle, p2PSessionState);
    }

    public boolean allowP2PPacketRelay(boolean z) {
        return SteamNetworkingNative.allowP2PPacketRelay(this.isServer, z);
    }
}
