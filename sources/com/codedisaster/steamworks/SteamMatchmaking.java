package com.codedisaster.steamworks;

import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamMatchmaking.class */
public class SteamMatchmaking extends SteamInterface {

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamMatchmaking$LobbyDistanceFilter.class */
    public enum LobbyDistanceFilter {
        Close,
        Default,
        Far,
        Worldwide
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamMatchmaking$LobbyType.class */
    public enum LobbyType {
        Private,
        FriendsOnly,
        Public,
        Invisible,
        PrivateUnique
    }

    @Override // com.codedisaster.steamworks.SteamInterface
    public /* bridge */ /* synthetic */ void dispose() {
        super.dispose();
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamMatchmaking$LobbyComparison.class */
    public enum LobbyComparison {
        EqualToOrLessThan(-2),
        LessThan(-1),
        Equal(0),
        GreaterThan(1),
        EqualToOrGreaterThan(2),
        NotEqual(3);

        private final int value;

        LobbyComparison(int i) {
            this.value = i;
        }
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamMatchmaking$ChatRoomEnterResponse.class */
    public enum ChatRoomEnterResponse {
        Success(1),
        DoesntExist(2),
        NotAllowed(3),
        Full(4),
        Error(5),
        Banned(6),
        Limited(7),
        ClanDisabled(8),
        CommunityBan(9),
        MemberBlockedYou(10),
        YouBlockedMember(11),
        RatelimitExceeded(15);

        private final int code;
        private static final ChatRoomEnterResponse[] values = values();

        ChatRoomEnterResponse(int i) {
            this.code = i;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static ChatRoomEnterResponse byValue(int i) {
            for (ChatRoomEnterResponse chatRoomEnterResponse : values) {
                if (chatRoomEnterResponse.code == i) {
                    return chatRoomEnterResponse;
                }
            }
            return Error;
        }
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamMatchmaking$ChatMemberStateChange.class */
    public enum ChatMemberStateChange {
        Entered(1),
        Left(2),
        Disconnected(4),
        Kicked(8),
        Banned(16);

        private final int bits;

        ChatMemberStateChange(int i) {
            this.bits = i;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static boolean isSet(ChatMemberStateChange chatMemberStateChange, int i) {
            return (chatMemberStateChange.bits & i) == chatMemberStateChange.bits;
        }
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamMatchmaking$ChatEntryType.class */
    public enum ChatEntryType {
        Invalid(0),
        ChatMsg(1),
        Typing(2),
        InviteGame(3),
        Emote(4),
        LeftConversation(6),
        Entered(7),
        WasKicked(8),
        WasBanned(9),
        Disconnected(10),
        HistoricalChat(11),
        Reserved1(12),
        Reserved2(13),
        LinkBlocked(14);

        private final int code;
        private static final ChatEntryType[] values = values();

        ChatEntryType(int i) {
            this.code = i;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static ChatEntryType byValue(int i) {
            for (ChatEntryType chatEntryType : values) {
                if (chatEntryType.code == i) {
                    return chatEntryType;
                }
            }
            return Invalid;
        }
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamMatchmaking$ChatEntry.class */
    public static class ChatEntry {
        private long steamIDUser;
        private int chatEntryType;

        public SteamID getSteamIDUser() {
            return new SteamID(this.steamIDUser);
        }

        public ChatEntryType getChatEntryType() {
            return ChatEntryType.byValue(this.chatEntryType);
        }
    }

    public SteamMatchmaking(SteamMatchmakingCallback steamMatchmakingCallback) {
        super(SteamMatchmakingNative.createCallback(new SteamMatchmakingCallbackAdapter(steamMatchmakingCallback)));
    }

    public int getFavoriteGameCount() {
        return SteamMatchmakingNative.getFavoriteGameCount();
    }

    public boolean getFavoriteGame(int i, int[] iArr, int[] iArr2, short[] sArr, short[] sArr2, int[] iArr3, int[] iArr4) {
        return SteamMatchmakingNative.getFavoriteGame(i, iArr, iArr2, sArr, sArr2, iArr3, iArr4);
    }

    public int addFavoriteGame(int i, int i2, short s, short s2, int i3, int i4) {
        return SteamMatchmakingNative.addFavoriteGame(i, i2, s, s2, i3, i4);
    }

    public boolean removeFavoriteGame(int i, int i2, short s, short s2, int i3) {
        return SteamMatchmakingNative.removeFavoriteGame(i, i2, s, s2, i3);
    }

    public SteamAPICall requestLobbyList() {
        return new SteamAPICall(SteamMatchmakingNative.requestLobbyList(this.callback));
    }

    public void addRequestLobbyListStringFilter(String str, String str2, LobbyComparison lobbyComparison) {
        SteamMatchmakingNative.addRequestLobbyListStringFilter(str, str2, lobbyComparison.value);
    }

    public void addRequestLobbyListNumericalFilter(String str, int i, LobbyComparison lobbyComparison) {
        SteamMatchmakingNative.addRequestLobbyListNumericalFilter(str, i, lobbyComparison.value);
    }

    public void addRequestLobbyListNearValueFilter(String str, int i) {
        SteamMatchmakingNative.addRequestLobbyListNearValueFilter(str, i);
    }

    public void addRequestLobbyListFilterSlotsAvailable(int i) {
        SteamMatchmakingNative.addRequestLobbyListFilterSlotsAvailable(i);
    }

    public void addRequestLobbyListDistanceFilter(LobbyDistanceFilter lobbyDistanceFilter) {
        SteamMatchmakingNative.addRequestLobbyListDistanceFilter(lobbyDistanceFilter.ordinal());
    }

    public void addRequestLobbyListResultCountFilter(int i) {
        SteamMatchmakingNative.addRequestLobbyListResultCountFilter(i);
    }

    public void addRequestLobbyListCompatibleMembersFilter(SteamID steamID) {
        SteamMatchmakingNative.addRequestLobbyListCompatibleMembersFilter(steamID.handle);
    }

    public SteamID getLobbyByIndex(int i) {
        return new SteamID(SteamMatchmakingNative.getLobbyByIndex(i));
    }

    public SteamAPICall createLobby(LobbyType lobbyType, int i) {
        return new SteamAPICall(SteamMatchmakingNative.createLobby(this.callback, lobbyType.ordinal(), i));
    }

    public SteamAPICall joinLobby(SteamID steamID) {
        return new SteamAPICall(SteamMatchmakingNative.joinLobby(this.callback, steamID.handle));
    }

    public void leaveLobby(SteamID steamID) {
        SteamMatchmakingNative.leaveLobby(steamID.handle);
    }

    public boolean inviteUserToLobby(SteamID steamID, SteamID steamID2) {
        return SteamMatchmakingNative.inviteUserToLobby(steamID.handle, steamID2.handle);
    }

    public int getNumLobbyMembers(SteamID steamID) {
        return SteamMatchmakingNative.getNumLobbyMembers(steamID.handle);
    }

    public SteamID getLobbyMemberByIndex(SteamID steamID, int i) {
        return new SteamID(SteamMatchmakingNative.getLobbyMemberByIndex(steamID.handle, i));
    }

    public String getLobbyData(SteamID steamID, String str) {
        return SteamMatchmakingNative.getLobbyData(steamID.handle, str);
    }

    public boolean setLobbyData(SteamID steamID, String str, String str2) {
        return SteamMatchmakingNative.setLobbyData(steamID.handle, str, str2);
    }

    public boolean setLobbyData(SteamID steamID, SteamMatchmakingKeyValuePair steamMatchmakingKeyValuePair) {
        return SteamMatchmakingNative.setLobbyData(steamID.handle, steamMatchmakingKeyValuePair.getKey(), steamMatchmakingKeyValuePair.getValue());
    }

    public String getLobbyMemberData(SteamID steamID, SteamID steamID2, String str) {
        return SteamMatchmakingNative.getLobbyMemberData(steamID.handle, steamID2.handle, str);
    }

    public void setLobbyMemberData(SteamID steamID, String str, String str2) {
        SteamMatchmakingNative.setLobbyMemberData(steamID.handle, str, str2);
    }

    public void setLobbyMemberData(SteamID steamID, SteamMatchmakingKeyValuePair steamMatchmakingKeyValuePair) {
        SteamMatchmakingNative.setLobbyMemberData(steamID.handle, steamMatchmakingKeyValuePair.getKey(), steamMatchmakingKeyValuePair.getValue());
    }

    public int getLobbyDataCount(SteamID steamID) {
        return SteamMatchmakingNative.getLobbyDataCount(steamID.handle);
    }

    public boolean getLobbyDataByIndex(SteamID steamID, int i, SteamMatchmakingKeyValuePair steamMatchmakingKeyValuePair) {
        return SteamMatchmakingNative.getLobbyDataByIndex(steamID.handle, i, steamMatchmakingKeyValuePair);
    }

    public boolean deleteLobbyData(SteamID steamID, String str) {
        return SteamMatchmakingNative.deleteLobbyData(steamID.handle, str);
    }

    public boolean sendLobbyChatMsg(SteamID steamID, ByteBuffer byteBuffer) {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        return SteamMatchmakingNative.sendLobbyChatMsg(steamID.handle, byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    public boolean sendLobbyChatMsg(SteamID steamID, String str) {
        return SteamMatchmakingNative.sendLobbyChatMsg(steamID.handle, str);
    }

    public int getLobbyChatEntry(SteamID steamID, int i, ChatEntry chatEntry, ByteBuffer byteBuffer) {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        return SteamMatchmakingNative.getLobbyChatEntry(steamID.handle, i, chatEntry, byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    public boolean requestLobbyData(SteamID steamID) {
        return SteamMatchmakingNative.requestLobbyData(steamID.handle);
    }

    public void setLobbyGameServer(SteamID steamID, int i, short s, SteamID steamID2) {
        SteamMatchmakingNative.setLobbyGameServer(steamID.handle, i, s, steamID2.handle);
    }

    public boolean getLobbyGameServer(SteamID steamID, int[] iArr, short[] sArr, SteamID steamID2) {
        long[] jArr = new long[1];
        if (SteamMatchmakingNative.getLobbyGameServer(steamID.handle, iArr, sArr, jArr)) {
            steamID2.handle = jArr[0];
            return true;
        }
        return false;
    }

    public boolean setLobbyMemberLimit(SteamID steamID, int i) {
        return SteamMatchmakingNative.setLobbyMemberLimit(steamID.handle, i);
    }

    public int getLobbyMemberLimit(SteamID steamID) {
        return SteamMatchmakingNative.getLobbyMemberLimit(steamID.handle);
    }

    public boolean setLobbyType(SteamID steamID, LobbyType lobbyType) {
        return SteamMatchmakingNative.setLobbyType(steamID.handle, lobbyType.ordinal());
    }

    public boolean setLobbyJoinable(SteamID steamID, boolean z) {
        return SteamMatchmakingNative.setLobbyJoinable(steamID.handle, z);
    }

    public SteamID getLobbyOwner(SteamID steamID) {
        return new SteamID(SteamMatchmakingNative.getLobbyOwner(steamID.handle));
    }

    public boolean setLobbyOwner(SteamID steamID, SteamID steamID2) {
        return SteamMatchmakingNative.setLobbyOwner(steamID.handle, steamID2.handle);
    }

    public boolean setLinkedLobby(SteamID steamID, SteamID steamID2) {
        return SteamMatchmakingNative.setLinkedLobby(steamID.handle, steamID2.handle);
    }
}
