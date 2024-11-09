package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamFriends;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamFriendsNative.class */
final class SteamFriendsNative {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long createCallback(SteamFriendsCallbackAdapter steamFriendsCallbackAdapter);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native String getPersonaName();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long setPersonaName(long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getPersonaState();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getFriendCount(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long getFriendByIndex(int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getFriendRelationship(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getFriendPersonaState(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native String getFriendPersonaName(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getFriendGamePlayed(long j, SteamFriends.FriendGameInfo friendGameInfo);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setInGameVoiceSpeaking(long j, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getSmallFriendAvatar(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getMediumFriendAvatar(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getLargeFriendAvatar(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean requestUserInformation(long j, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void activateGameOverlay(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void activateGameOverlayToUser(String str, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void activateGameOverlayToWebPage(String str, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void activateGameOverlayToStore(int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setPlayedWith(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void activateGameOverlayInviteDialog(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setRichPresence(String str, String str2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void clearRichPresence();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native String getFriendRichPresence(long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getFriendRichPresenceKeyCount(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native String getFriendRichPresenceKeyByIndex(long j, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void requestFriendRichPresence(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean inviteUserToGame(long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getCoplayFriendCount();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long getCoplayFriend(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getFriendCoplayTime(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getFriendCoplayGame(long j);

    SteamFriendsNative() {
    }
}
