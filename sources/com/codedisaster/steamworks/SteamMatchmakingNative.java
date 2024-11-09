package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamMatchmaking;
import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamMatchmakingNative.class */
final class SteamMatchmakingNative {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long createCallback(SteamMatchmakingCallbackAdapter steamMatchmakingCallbackAdapter);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getFavoriteGameCount();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getFavoriteGame(int i, int[] iArr, int[] iArr2, short[] sArr, short[] sArr2, int[] iArr3, int[] iArr4);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int addFavoriteGame(int i, int i2, short s, short s2, int i3, int i4);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean removeFavoriteGame(int i, int i2, short s, short s2, int i3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long requestLobbyList(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void addRequestLobbyListStringFilter(String str, String str2, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void addRequestLobbyListNumericalFilter(String str, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void addRequestLobbyListNearValueFilter(String str, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void addRequestLobbyListFilterSlotsAvailable(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void addRequestLobbyListDistanceFilter(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void addRequestLobbyListResultCountFilter(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void addRequestLobbyListCompatibleMembersFilter(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long getLobbyByIndex(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long createLobby(long j, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long joinLobby(long j, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void leaveLobby(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean inviteUserToLobby(long j, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getNumLobbyMembers(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long getLobbyMemberByIndex(long j, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native String getLobbyData(long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setLobbyData(long j, String str, String str2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native String getLobbyMemberData(long j, long j2, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setLobbyMemberData(long j, String str, String str2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getLobbyDataCount(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getLobbyDataByIndex(long j, int i, SteamMatchmakingKeyValuePair steamMatchmakingKeyValuePair);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean deleteLobbyData(long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean sendLobbyChatMsg(long j, ByteBuffer byteBuffer, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean sendLobbyChatMsg(long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getLobbyChatEntry(long j, int i, SteamMatchmaking.ChatEntry chatEntry, ByteBuffer byteBuffer, int i2, int i3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean requestLobbyData(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setLobbyGameServer(long j, int i, short s, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getLobbyGameServer(long j, int[] iArr, short[] sArr, long[] jArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setLobbyMemberLimit(long j, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getLobbyMemberLimit(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setLobbyType(long j, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setLobbyJoinable(long j, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long getLobbyOwner(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setLobbyOwner(long j, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setLinkedLobby(long j, long j2);

    SteamMatchmakingNative() {
    }
}
