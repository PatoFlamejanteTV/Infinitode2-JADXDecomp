package com.codedisaster.steamworks;

import java.util.Collection;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamFriends.class */
public class SteamFriends extends SteamInterface {

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamFriends$OverlayToStoreFlag.class */
    public enum OverlayToStoreFlag {
        None,
        AddToCart,
        AddToCartAndShow
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamFriends$OverlayToWebPageMode.class */
    public enum OverlayToWebPageMode {
        Default,
        Modal
    }

    @Override // com.codedisaster.steamworks.SteamInterface
    public /* bridge */ /* synthetic */ void dispose() {
        super.dispose();
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamFriends$FriendRelationship.class */
    public enum FriendRelationship {
        None,
        Blocked,
        Recipient,
        Friend,
        RequestInitiator,
        Ignored,
        IgnoredFriend,
        Suggested_DEPRECATED,
        Max;

        private static final FriendRelationship[] values = values();

        static FriendRelationship byOrdinal(int i) {
            return values[i];
        }
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamFriends$PersonaState.class */
    public enum PersonaState {
        Offline,
        Online,
        Busy,
        Away,
        Snooze,
        LookingToTrade,
        LookingToPlay,
        Invisible;

        private static final PersonaState[] values = values();

        static PersonaState byOrdinal(int i) {
            return values[i];
        }
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamFriends$FriendFlags.class */
    public enum FriendFlags {
        None(0),
        Blocked(1),
        FriendshipRequested(2),
        Immediate(4),
        ClanMember(8),
        OnGameServer(16),
        RequestingFriendship(128),
        RequestingInfo(256),
        Ignored(512),
        IgnoredFriend(1024),
        ChatMember(4096),
        All(65535);

        private final int bits;

        FriendFlags(int i) {
            this.bits = i;
        }

        static int asBits(Collection<FriendFlags> collection) {
            int i = 0;
            Iterator<FriendFlags> it = collection.iterator();
            while (it.hasNext()) {
                i |= it.next().bits;
            }
            return i;
        }
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamFriends$PersonaChange.class */
    public enum PersonaChange {
        Name(1),
        Status(2),
        ComeOnline(4),
        GoneOffline(8),
        GamePlayed(16),
        GameServer(32),
        Avatar(64),
        JoinedSource(128),
        LeftSource(256),
        RelationshipChanged(512),
        NameFirstSet(1024),
        Broadcast(2048),
        Nickname(4096),
        SteamLevel(8192),
        RichPresence(16384);

        private final int bits;

        PersonaChange(int i) {
            this.bits = i;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static boolean isSet(PersonaChange personaChange, int i) {
            return (personaChange.bits & i) == personaChange.bits;
        }
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamFriends$FriendGameInfo.class */
    public static class FriendGameInfo {
        private long gameID;
        private int gameIP;
        private short gamePort;
        private short queryPort;
        private long steamIDLobby;

        public long getGameID() {
            return this.gameID;
        }

        public int getGameIP() {
            return this.gameIP;
        }

        public short getGamePort() {
            return this.gamePort;
        }

        public short getQueryPort() {
            return this.queryPort;
        }

        public SteamID getSteamIDLobby() {
            return new SteamID(this.steamIDLobby);
        }
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamFriends$OverlayDialog.class */
    public enum OverlayDialog {
        Friends("Friends"),
        Community("Community"),
        Players("Players"),
        Settings("Settings"),
        OfficialGameGroup("OfficialGameGroup"),
        Stats("Stats"),
        Achievements("Achievements");

        private final String id;

        OverlayDialog(String str) {
            this.id = str;
        }
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamFriends$OverlayToUserDialog.class */
    public enum OverlayToUserDialog {
        SteamID("steamid"),
        Chat("chat"),
        JoinTrade("jointrade"),
        Stats("stats"),
        Achievements("achievements"),
        FriendAdd("friendadd"),
        FriendRemove("friendremove"),
        FriendRequestAccept("friendrequestaccept"),
        FriendRequestIgnore("friendrequestignore");

        private final String id;

        OverlayToUserDialog(String str) {
            this.id = str;
        }
    }

    public SteamFriends(SteamFriendsCallback steamFriendsCallback) {
        super(SteamFriendsNative.createCallback(new SteamFriendsCallbackAdapter(steamFriendsCallback)));
    }

    public String getPersonaName() {
        return SteamFriendsNative.getPersonaName();
    }

    public SteamAPICall setPersonaName(String str) {
        return new SteamAPICall(SteamFriendsNative.setPersonaName(this.callback, str));
    }

    public PersonaState getPersonaState() {
        return PersonaState.byOrdinal(SteamFriendsNative.getPersonaState());
    }

    public int getFriendCount(FriendFlags friendFlags) {
        return SteamFriendsNative.getFriendCount(friendFlags.bits);
    }

    public int getFriendCount(Collection<FriendFlags> collection) {
        return SteamFriendsNative.getFriendCount(FriendFlags.asBits(collection));
    }

    public SteamID getFriendByIndex(int i, FriendFlags friendFlags) {
        return new SteamID(SteamFriendsNative.getFriendByIndex(i, friendFlags.bits));
    }

    public SteamID getFriendByIndex(int i, Collection<FriendFlags> collection) {
        return new SteamID(SteamFriendsNative.getFriendByIndex(i, FriendFlags.asBits(collection)));
    }

    public FriendRelationship getFriendRelationship(SteamID steamID) {
        return FriendRelationship.byOrdinal(SteamFriendsNative.getFriendRelationship(steamID.handle));
    }

    public PersonaState getFriendPersonaState(SteamID steamID) {
        return PersonaState.byOrdinal(SteamFriendsNative.getFriendPersonaState(steamID.handle));
    }

    public String getFriendPersonaName(SteamID steamID) {
        return SteamFriendsNative.getFriendPersonaName(steamID.handle);
    }

    public boolean getFriendGamePlayed(SteamID steamID, FriendGameInfo friendGameInfo) {
        return SteamFriendsNative.getFriendGamePlayed(steamID.handle, friendGameInfo);
    }

    public void setInGameVoiceSpeaking(SteamID steamID, boolean z) {
        SteamFriendsNative.setInGameVoiceSpeaking(steamID.handle, z);
    }

    public int getSmallFriendAvatar(SteamID steamID) {
        return SteamFriendsNative.getSmallFriendAvatar(steamID.handle);
    }

    public int getMediumFriendAvatar(SteamID steamID) {
        return SteamFriendsNative.getMediumFriendAvatar(steamID.handle);
    }

    public int getLargeFriendAvatar(SteamID steamID) {
        return SteamFriendsNative.getLargeFriendAvatar(steamID.handle);
    }

    public boolean requestUserInformation(SteamID steamID, boolean z) {
        return SteamFriendsNative.requestUserInformation(steamID.handle, z);
    }

    public void activateGameOverlay(OverlayDialog overlayDialog) {
        SteamFriendsNative.activateGameOverlay(overlayDialog.id);
    }

    public void activateGameOverlayToUser(OverlayToUserDialog overlayToUserDialog, SteamID steamID) {
        SteamFriendsNative.activateGameOverlayToUser(overlayToUserDialog.id, steamID.handle);
    }

    public void activateGameOverlayToWebPage(String str, OverlayToWebPageMode overlayToWebPageMode) {
        SteamFriendsNative.activateGameOverlayToWebPage(str, overlayToWebPageMode.ordinal());
    }

    public void activateGameOverlayToStore(int i, OverlayToStoreFlag overlayToStoreFlag) {
        SteamFriendsNative.activateGameOverlayToStore(i, overlayToStoreFlag.ordinal());
    }

    public void setPlayedWith(SteamID steamID) {
        SteamFriendsNative.setPlayedWith(steamID.handle);
    }

    public void activateGameOverlayInviteDialog(SteamID steamID) {
        SteamFriendsNative.activateGameOverlayInviteDialog(steamID.handle);
    }

    public boolean setRichPresence(String str, String str2) {
        return SteamFriendsNative.setRichPresence(str, str2 != null ? str2 : "");
    }

    public void clearRichPresence() {
        SteamFriendsNative.clearRichPresence();
    }

    public String getFriendRichPresence(SteamID steamID, String str) {
        return SteamFriendsNative.getFriendRichPresence(steamID.handle, str);
    }

    public int getFriendRichPresenceKeyCount(SteamID steamID) {
        return SteamFriendsNative.getFriendRichPresenceKeyCount(steamID.handle);
    }

    public String getFriendRichPresenceKeyByIndex(SteamID steamID, int i) {
        return SteamFriendsNative.getFriendRichPresenceKeyByIndex(steamID.handle, i);
    }

    public void requestFriendRichPresence(SteamID steamID) {
        SteamFriendsNative.requestFriendRichPresence(steamID.handle);
    }

    public boolean inviteUserToGame(SteamID steamID, String str) {
        return SteamFriendsNative.inviteUserToGame(steamID.handle, str);
    }

    public int getCoplayFriendCount() {
        return SteamFriendsNative.getCoplayFriendCount();
    }

    public SteamID getCoplayFriend(int i) {
        return new SteamID(SteamFriendsNative.getCoplayFriend(i));
    }

    public int getFriendCoplayTime(SteamID steamID) {
        return SteamFriendsNative.getFriendCoplayTime(steamID.handle);
    }

    public int getFriendCoplayGame(SteamID steamID) {
        return SteamFriendsNative.getFriendCoplayGame(steamID.handle);
    }
}
