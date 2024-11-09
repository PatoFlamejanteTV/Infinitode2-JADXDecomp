package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamNetworking;
import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamNetworkingNative.class */
final class SteamNetworkingNative {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long createCallback(SteamNetworkingCallbackAdapter steamNetworkingCallbackAdapter);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean sendP2PPacket(boolean z, long j, ByteBuffer byteBuffer, int i, int i2, int i3, int i4);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean isP2PPacketAvailable(boolean z, int[] iArr, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean readP2PPacket(boolean z, ByteBuffer byteBuffer, int i, int i2, int[] iArr, long[] jArr, int i3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean acceptP2PSessionWithUser(boolean z, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean closeP2PSessionWithUser(boolean z, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean closeP2PChannelWithUser(boolean z, long j, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getP2PSessionState(boolean z, long j, SteamNetworking.P2PSessionState p2PSessionState);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean allowP2PPacketRelay(boolean z, boolean z2);

    SteamNetworkingNative() {
    }
}
