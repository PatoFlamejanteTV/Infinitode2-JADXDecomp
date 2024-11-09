package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamRemoteStorage;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUGC.class */
public class SteamUGC extends SteamInterface {

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUGC$ItemStatistic.class */
    public enum ItemStatistic {
        NumSubscriptions,
        NumFavorites,
        NumFollowers,
        NumUniqueSubscriptions,
        NumUniqueFavorites,
        NumUniqueFollowers,
        NumUniqueWebsiteViews,
        ReportScore,
        NumSecondsPlayed,
        NumPlaytimeSessions,
        NumComments,
        NumSecondsPlayedDuringTimePeriod,
        NumPlaytimeSessionsDuringTimePeriod
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUGC$UGCQueryType.class */
    public enum UGCQueryType {
        RankedByVote,
        RankedByPublicationDate,
        AcceptedForGameRankedByAcceptanceDate,
        RankedByTrend,
        FavoritedByFriendsRankedByPublicationDate,
        CreatedByFriendsRankedByPublicationDate,
        RankedByNumTimesReported,
        CreatedByFollowedUsersRankedByPublicationDate,
        NotYetRated,
        RankedByTotalVotesAsc,
        RankedByVotesUp,
        RankedByTextSearch,
        RankedByTotalUniqueSubscriptions,
        RankedByPlaytimeTrend,
        RankedByTotalPlaytime,
        RankedByAveragePlaytimeTrend,
        RankedByLifetimeAveragePlaytime,
        RankedByPlaytimeSessionsTrend,
        RankedByLifetimePlaytimeSessions
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUGC$UserUGCList.class */
    public enum UserUGCList {
        Published,
        VotedOn,
        VotedUp,
        VotedDown,
        WillVoteLater,
        Favorited,
        Subscribed,
        UsedOrPlayed,
        Followed
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUGC$UserUGCListSortOrder.class */
    public enum UserUGCListSortOrder {
        CreationOrderDesc,
        CreationOrderAsc,
        TitleAsc,
        LastUpdatedDesc,
        SubscriptionDateDesc,
        VoteScoreDesc,
        ForModeration
    }

    @Override // com.codedisaster.steamworks.SteamInterface
    public /* bridge */ /* synthetic */ void dispose() {
        super.dispose();
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUGC$MatchingUGCType.class */
    public enum MatchingUGCType {
        Items(0),
        ItemsMtx(1),
        ItemsReadyToUse(2),
        Collections(3),
        Artwork(4),
        Videos(5),
        Screenshots(6),
        AllGuides(7),
        WebGuides(8),
        IntegratedGuides(9),
        UsableInGame(10),
        ControllerBindings(11),
        GameManagedItems(12),
        All(-1);

        private final int value;

        MatchingUGCType(int i) {
            this.value = i;
        }
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUGC$ItemUpdateStatus.class */
    public enum ItemUpdateStatus {
        Invalid,
        PreparingConfig,
        PreparingContent,
        UploadingContent,
        UploadingPreviewFile,
        CommittingChanges;

        private static final ItemUpdateStatus[] values = values();

        static ItemUpdateStatus byOrdinal(int i) {
            return values[i];
        }
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUGC$ItemUpdateInfo.class */
    public static class ItemUpdateInfo {
        long bytesProcessed;
        long bytesTotal;

        public long getBytesProcessed() {
            return this.bytesProcessed;
        }

        public long getBytesTotal() {
            return this.bytesTotal;
        }
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUGC$ItemState.class */
    public enum ItemState {
        None(0),
        Subscribed(1),
        LegacyItem(2),
        Installed(4),
        NeedsUpdate(8),
        Downloading(16),
        DownloadPending(32);

        private final int bits;
        private static final ItemState[] values = values();

        ItemState(int i) {
            this.bits = i;
        }

        static Collection<ItemState> fromBits(int i) {
            EnumSet noneOf = EnumSet.noneOf(ItemState.class);
            for (ItemState itemState : values) {
                if ((i & itemState.bits) == itemState.bits) {
                    noneOf.add(itemState);
                }
            }
            return noneOf;
        }
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUGC$ItemPreviewType.class */
    public enum ItemPreviewType {
        Image(0),
        YouTubeVideo(1),
        Sketchfab(2),
        EnvironmentMap_HorizontalCross(3),
        EnvironmentMap_LatLong(4),
        ReservedMax(255),
        UnknownPreviewType_NotImplementedByAPI(-1);

        private final int value;
        private static final ItemPreviewType[] values = values();

        ItemPreviewType(int i) {
            this.value = i;
        }

        static ItemPreviewType byValue(int i) {
            for (ItemPreviewType itemPreviewType : values) {
                if (itemPreviewType.value == i) {
                    return itemPreviewType;
                }
            }
            return UnknownPreviewType_NotImplementedByAPI;
        }
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUGC$ItemInstallInfo.class */
    public static class ItemInstallInfo {
        private String folder;
        private int sizeOnDisk;

        public String getFolder() {
            return this.folder;
        }

        public int getSizeOnDisk() {
            return this.sizeOnDisk;
        }
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUGC$ItemDownloadInfo.class */
    public static class ItemDownloadInfo {
        long bytesDownloaded;
        long bytesTotal;

        public long getBytesDownloaded() {
            return this.bytesDownloaded;
        }

        public long getBytesTotal() {
            return this.bytesTotal;
        }
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUGC$ItemAdditionalPreview.class */
    public static class ItemAdditionalPreview {
        private String urlOrVideoID;
        private String originalFileName;
        private int previewType;

        public String getUrlOrVideoID() {
            return this.urlOrVideoID;
        }

        public String getOriginalFileName() {
            return this.originalFileName;
        }

        public ItemPreviewType getPreviewType() {
            return ItemPreviewType.byValue(this.previewType);
        }
    }

    public SteamUGC(SteamUGCCallback steamUGCCallback) {
        super(SteamUGCNative.createCallback(new SteamUGCCallbackAdapter(steamUGCCallback)));
    }

    public SteamUGCQuery createQueryUserUGCRequest(int i, UserUGCList userUGCList, MatchingUGCType matchingUGCType, UserUGCListSortOrder userUGCListSortOrder, int i2, int i3, int i4) {
        return new SteamUGCQuery(SteamUGCNative.createQueryUserUGCRequest(i, userUGCList.ordinal(), matchingUGCType.value, userUGCListSortOrder.ordinal(), i2, i3, i4));
    }

    public SteamUGCQuery createQueryAllUGCRequest(UGCQueryType uGCQueryType, MatchingUGCType matchingUGCType, int i, int i2, int i3) {
        return new SteamUGCQuery(SteamUGCNative.createQueryAllUGCRequest(uGCQueryType.ordinal(), matchingUGCType.value, i, i2, i3));
    }

    public SteamUGCQuery createQueryUGCDetailsRequest(SteamPublishedFileID steamPublishedFileID) {
        return new SteamUGCQuery(SteamUGCNative.createQueryUGCDetailsRequest(new long[]{steamPublishedFileID.handle}, 1));
    }

    public SteamUGCQuery createQueryUGCDetailsRequest(Collection<SteamPublishedFileID> collection) {
        int size = collection.size();
        long[] jArr = new long[size];
        int i = 0;
        Iterator<SteamPublishedFileID> it = collection.iterator();
        while (it.hasNext()) {
            int i2 = i;
            i++;
            jArr[i2] = it.next().handle;
        }
        return new SteamUGCQuery(SteamUGCNative.createQueryUGCDetailsRequest(jArr, size));
    }

    public SteamAPICall sendQueryUGCRequest(SteamUGCQuery steamUGCQuery) {
        return new SteamAPICall(SteamUGCNative.sendQueryUGCRequest(this.callback, steamUGCQuery.handle));
    }

    public boolean getQueryUGCResult(SteamUGCQuery steamUGCQuery, int i, SteamUGCDetails steamUGCDetails) {
        return SteamUGCNative.getQueryUGCResult(steamUGCQuery.handle, i, steamUGCDetails);
    }

    public String getQueryUGCPreviewURL(SteamUGCQuery steamUGCQuery, int i) {
        return SteamUGCNative.getQueryUGCPreviewURL(steamUGCQuery.handle, i);
    }

    public String getQueryUGCMetadata(SteamUGCQuery steamUGCQuery, int i) {
        return SteamUGCNative.getQueryUGCMetadata(steamUGCQuery.handle, i);
    }

    public long getQueryUGCStatistic(SteamUGCQuery steamUGCQuery, int i, ItemStatistic itemStatistic) {
        return SteamUGCNative.getQueryUGCStatistic(steamUGCQuery.handle, i, itemStatistic.ordinal());
    }

    public int getQueryUGCNumAdditionalPreviews(SteamUGCQuery steamUGCQuery, int i) {
        return SteamUGCNative.getQueryUGCNumAdditionalPreviews(steamUGCQuery.handle, i);
    }

    public boolean getQueryUGCAdditionalPreview(SteamUGCQuery steamUGCQuery, int i, int i2, ItemAdditionalPreview itemAdditionalPreview) {
        return SteamUGCNative.getQueryUGCAdditionalPreview(steamUGCQuery.handle, i, i2, itemAdditionalPreview);
    }

    public int getQueryUGCNumKeyValueTags(SteamUGCQuery steamUGCQuery, int i) {
        return SteamUGCNative.getQueryUGCNumKeyValueTags(steamUGCQuery.handle, i);
    }

    public boolean getQueryUGCKeyValueTag(SteamUGCQuery steamUGCQuery, int i, int i2, String[] strArr) {
        return SteamUGCNative.getQueryUGCKeyValueTag(steamUGCQuery.handle, i, i2, strArr);
    }

    public boolean releaseQueryUserUGCRequest(SteamUGCQuery steamUGCQuery) {
        return SteamUGCNative.releaseQueryUserUGCRequest(steamUGCQuery.handle);
    }

    public boolean addRequiredTag(SteamUGCQuery steamUGCQuery, String str) {
        return SteamUGCNative.addRequiredTag(steamUGCQuery.handle, str);
    }

    public boolean addExcludedTag(SteamUGCQuery steamUGCQuery, String str) {
        return SteamUGCNative.addExcludedTag(steamUGCQuery.handle, str);
    }

    public boolean setReturnOnlyIDs(SteamUGCQuery steamUGCQuery, boolean z) {
        return SteamUGCNative.setReturnOnlyIDs(steamUGCQuery.handle, z);
    }

    public boolean setReturnKeyValueTags(SteamUGCQuery steamUGCQuery, boolean z) {
        return SteamUGCNative.setReturnKeyValueTags(steamUGCQuery.handle, z);
    }

    public boolean setReturnLongDescription(SteamUGCQuery steamUGCQuery, boolean z) {
        return SteamUGCNative.setReturnLongDescription(steamUGCQuery.handle, z);
    }

    public boolean setReturnMetadata(SteamUGCQuery steamUGCQuery, boolean z) {
        return SteamUGCNative.setReturnMetadata(steamUGCQuery.handle, z);
    }

    public boolean setReturnChildren(SteamUGCQuery steamUGCQuery, boolean z) {
        return SteamUGCNative.setReturnChildren(steamUGCQuery.handle, z);
    }

    public boolean setReturnAdditionalPreviews(SteamUGCQuery steamUGCQuery, boolean z) {
        return SteamUGCNative.setReturnAdditionalPreviews(steamUGCQuery.handle, z);
    }

    public boolean setReturnTotalOnly(SteamUGCQuery steamUGCQuery, boolean z) {
        return SteamUGCNative.setReturnTotalOnly(steamUGCQuery.handle, z);
    }

    public boolean setReturnPlaytimeStats(SteamUGCQuery steamUGCQuery, int i) {
        return SteamUGCNative.setReturnPlaytimeStats(steamUGCQuery.handle, i);
    }

    public boolean setLanguage(SteamUGCQuery steamUGCQuery, String str) {
        return SteamUGCNative.setLanguage(steamUGCQuery.handle, str);
    }

    public boolean setAllowCachedResponse(SteamUGCQuery steamUGCQuery, int i) {
        return SteamUGCNative.setAllowCachedResponse(steamUGCQuery.handle, i);
    }

    public boolean setCloudFileNameFilter(SteamUGCQuery steamUGCQuery, String str) {
        return SteamUGCNative.setCloudFileNameFilter(steamUGCQuery.handle, str);
    }

    public boolean setMatchAnyTag(SteamUGCQuery steamUGCQuery, boolean z) {
        return SteamUGCNative.setMatchAnyTag(steamUGCQuery.handle, z);
    }

    public boolean setSearchText(SteamUGCQuery steamUGCQuery, String str) {
        return SteamUGCNative.setSearchText(steamUGCQuery.handle, str);
    }

    public boolean setRankedByTrendDays(SteamUGCQuery steamUGCQuery, int i) {
        return SteamUGCNative.setRankedByTrendDays(steamUGCQuery.handle, i);
    }

    public boolean addRequiredKeyValueTag(SteamUGCQuery steamUGCQuery, String str, String str2) {
        return SteamUGCNative.addRequiredKeyValueTag(steamUGCQuery.handle, str, str2);
    }

    @Deprecated
    public SteamAPICall requestUGCDetails(SteamPublishedFileID steamPublishedFileID, int i) {
        return new SteamAPICall(SteamUGCNative.requestUGCDetails(this.callback, steamPublishedFileID.handle, i));
    }

    public SteamAPICall createItem(int i, SteamRemoteStorage.WorkshopFileType workshopFileType) {
        return new SteamAPICall(SteamUGCNative.createItem(this.callback, i, workshopFileType.ordinal()));
    }

    public SteamUGCUpdateHandle startItemUpdate(int i, SteamPublishedFileID steamPublishedFileID) {
        return new SteamUGCUpdateHandle(SteamUGCNative.startItemUpdate(i, steamPublishedFileID.handle));
    }

    public boolean setItemTitle(SteamUGCUpdateHandle steamUGCUpdateHandle, String str) {
        return SteamUGCNative.setItemTitle(steamUGCUpdateHandle.handle, str);
    }

    public boolean setItemDescription(SteamUGCUpdateHandle steamUGCUpdateHandle, String str) {
        return SteamUGCNative.setItemDescription(steamUGCUpdateHandle.handle, str);
    }

    public boolean setItemUpdateLanguage(SteamUGCUpdateHandle steamUGCUpdateHandle, String str) {
        return SteamUGCNative.setItemUpdateLanguage(steamUGCUpdateHandle.handle, str);
    }

    public boolean setItemMetadata(SteamUGCUpdateHandle steamUGCUpdateHandle, String str) {
        return SteamUGCNative.setItemMetadata(steamUGCUpdateHandle.handle, str);
    }

    public boolean setItemVisibility(SteamUGCUpdateHandle steamUGCUpdateHandle, SteamRemoteStorage.PublishedFileVisibility publishedFileVisibility) {
        return SteamUGCNative.setItemVisibility(steamUGCUpdateHandle.handle, publishedFileVisibility.ordinal());
    }

    public boolean setItemTags(SteamUGCUpdateHandle steamUGCUpdateHandle, String[] strArr) {
        return SteamUGCNative.setItemTags(steamUGCUpdateHandle.handle, strArr, strArr.length);
    }

    public boolean setItemContent(SteamUGCUpdateHandle steamUGCUpdateHandle, String str) {
        return SteamUGCNative.setItemContent(steamUGCUpdateHandle.handle, str);
    }

    public boolean setItemPreview(SteamUGCUpdateHandle steamUGCUpdateHandle, String str) {
        return SteamUGCNative.setItemPreview(steamUGCUpdateHandle.handle, str);
    }

    public boolean removeItemKeyValueTags(SteamUGCUpdateHandle steamUGCUpdateHandle, String str) {
        return SteamUGCNative.removeItemKeyValueTags(steamUGCUpdateHandle.handle, str);
    }

    public boolean addItemKeyValueTag(SteamUGCUpdateHandle steamUGCUpdateHandle, String str, String str2) {
        return SteamUGCNative.addItemKeyValueTag(steamUGCUpdateHandle.handle, str, str2);
    }

    public SteamAPICall submitItemUpdate(SteamUGCUpdateHandle steamUGCUpdateHandle, String str) {
        if (str == null) {
            str = "";
        }
        return new SteamAPICall(SteamUGCNative.submitItemUpdate(this.callback, steamUGCUpdateHandle.handle, str));
    }

    public ItemUpdateStatus getItemUpdateProgress(SteamUGCUpdateHandle steamUGCUpdateHandle, ItemUpdateInfo itemUpdateInfo) {
        long[] jArr = new long[2];
        ItemUpdateStatus byOrdinal = ItemUpdateStatus.byOrdinal(SteamUGCNative.getItemUpdateProgress(steamUGCUpdateHandle.handle, jArr));
        itemUpdateInfo.bytesProcessed = jArr[0];
        itemUpdateInfo.bytesTotal = jArr[1];
        return byOrdinal;
    }

    public SteamAPICall setUserItemVote(SteamPublishedFileID steamPublishedFileID, boolean z) {
        return new SteamAPICall(SteamUGCNative.setUserItemVote(this.callback, steamPublishedFileID.handle, z));
    }

    public SteamAPICall getUserItemVote(SteamPublishedFileID steamPublishedFileID) {
        return new SteamAPICall(SteamUGCNative.getUserItemVote(this.callback, steamPublishedFileID.handle));
    }

    public SteamAPICall addItemToFavorites(int i, SteamPublishedFileID steamPublishedFileID) {
        return new SteamAPICall(SteamUGCNative.addItemToFavorites(this.callback, i, steamPublishedFileID.handle));
    }

    public SteamAPICall removeItemFromFavorites(int i, SteamPublishedFileID steamPublishedFileID) {
        return new SteamAPICall(SteamUGCNative.removeItemFromFavorites(this.callback, i, steamPublishedFileID.handle));
    }

    public SteamAPICall subscribeItem(SteamPublishedFileID steamPublishedFileID) {
        return new SteamAPICall(SteamUGCNative.subscribeItem(this.callback, steamPublishedFileID.handle));
    }

    public SteamAPICall unsubscribeItem(SteamPublishedFileID steamPublishedFileID) {
        return new SteamAPICall(SteamUGCNative.unsubscribeItem(this.callback, steamPublishedFileID.handle));
    }

    public int getNumSubscribedItems() {
        return SteamUGCNative.getNumSubscribedItems();
    }

    public int getSubscribedItems(SteamPublishedFileID[] steamPublishedFileIDArr) {
        long[] jArr = new long[steamPublishedFileIDArr.length];
        int subscribedItems = SteamUGCNative.getSubscribedItems(jArr, steamPublishedFileIDArr.length);
        for (int i = 0; i < subscribedItems; i++) {
            steamPublishedFileIDArr[i] = new SteamPublishedFileID(jArr[i]);
        }
        return subscribedItems;
    }

    public Collection<ItemState> getItemState(SteamPublishedFileID steamPublishedFileID) {
        return ItemState.fromBits(SteamUGCNative.getItemState(steamPublishedFileID.handle));
    }

    public boolean getItemInstallInfo(SteamPublishedFileID steamPublishedFileID, ItemInstallInfo itemInstallInfo) {
        return SteamUGCNative.getItemInstallInfo(steamPublishedFileID.handle, itemInstallInfo);
    }

    public boolean getItemDownloadInfo(SteamPublishedFileID steamPublishedFileID, ItemDownloadInfo itemDownloadInfo) {
        long[] jArr = new long[2];
        if (SteamUGCNative.getItemDownloadInfo(steamPublishedFileID.handle, jArr)) {
            itemDownloadInfo.bytesDownloaded = jArr[0];
            itemDownloadInfo.bytesTotal = jArr[1];
            return true;
        }
        return false;
    }

    public SteamAPICall deleteItem(SteamPublishedFileID steamPublishedFileID) {
        return new SteamAPICall(SteamUGCNative.deleteItem(this.callback, steamPublishedFileID.handle));
    }

    public boolean downloadItem(SteamPublishedFileID steamPublishedFileID, boolean z) {
        return SteamUGCNative.downloadItem(steamPublishedFileID.handle, z);
    }

    public boolean initWorkshopForGameServer(int i, String str) {
        return SteamUGCNative.initWorkshopForGameServer(i, str);
    }

    public void suspendDownloads(boolean z) {
        SteamUGCNative.suspendDownloads(z);
    }

    public SteamAPICall startPlaytimeTracking(SteamPublishedFileID[] steamPublishedFileIDArr) {
        long[] jArr = new long[steamPublishedFileIDArr.length];
        for (int i = 0; i < jArr.length; i++) {
            jArr[i] = steamPublishedFileIDArr[i].handle;
        }
        return new SteamAPICall(SteamUGCNative.startPlaytimeTracking(this.callback, jArr, jArr.length));
    }

    public SteamAPICall stopPlaytimeTracking(SteamPublishedFileID[] steamPublishedFileIDArr) {
        long[] jArr = new long[steamPublishedFileIDArr.length];
        for (int i = 0; i < jArr.length; i++) {
            jArr[i] = steamPublishedFileIDArr[i].handle;
        }
        return new SteamAPICall(SteamUGCNative.stopPlaytimeTracking(this.callback, jArr, jArr.length));
    }

    public SteamAPICall stopPlaytimeTrackingForAllItems() {
        return new SteamAPICall(SteamUGCNative.stopPlaytimeTrackingForAllItems(this.callback));
    }
}
