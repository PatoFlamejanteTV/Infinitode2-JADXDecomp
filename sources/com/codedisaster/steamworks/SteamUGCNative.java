package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamUGC;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUGCNative.class */
final class SteamUGCNative {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long createCallback(SteamUGCCallbackAdapter steamUGCCallbackAdapter);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long createQueryUserUGCRequest(int i, int i2, int i3, int i4, int i5, int i6, int i7);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long createQueryAllUGCRequest(int i, int i2, int i3, int i4, int i5);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long createQueryUGCDetailsRequest(long[] jArr, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long sendQueryUGCRequest(long j, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getQueryUGCResult(long j, int i, SteamUGCDetails steamUGCDetails);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native String getQueryUGCPreviewURL(long j, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native String getQueryUGCMetadata(long j, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long getQueryUGCStatistic(long j, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getQueryUGCNumAdditionalPreviews(long j, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getQueryUGCAdditionalPreview(long j, int i, int i2, SteamUGC.ItemAdditionalPreview itemAdditionalPreview);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getQueryUGCNumKeyValueTags(long j, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getQueryUGCKeyValueTag(long j, int i, int i2, String[] strArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean releaseQueryUserUGCRequest(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean addRequiredTag(long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean addExcludedTag(long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setReturnOnlyIDs(long j, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setReturnKeyValueTags(long j, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setReturnLongDescription(long j, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setReturnMetadata(long j, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setReturnChildren(long j, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setReturnAdditionalPreviews(long j, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setReturnTotalOnly(long j, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setReturnPlaytimeStats(long j, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setLanguage(long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setAllowCachedResponse(long j, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setCloudFileNameFilter(long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setMatchAnyTag(long j, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setSearchText(long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setRankedByTrendDays(long j, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean addRequiredKeyValueTag(long j, String str, String str2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long requestUGCDetails(long j, long j2, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long createItem(long j, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long startItemUpdate(int i, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setItemTitle(long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setItemDescription(long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setItemUpdateLanguage(long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setItemMetadata(long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setItemVisibility(long j, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setItemTags(long j, String[] strArr, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setItemContent(long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setItemPreview(long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean removeItemKeyValueTags(long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean addItemKeyValueTag(long j, String str, String str2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long submitItemUpdate(long j, long j2, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getItemUpdateProgress(long j, long[] jArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long setUserItemVote(long j, long j2, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long getUserItemVote(long j, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long addItemToFavorites(long j, int i, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long removeItemFromFavorites(long j, int i, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long subscribeItem(long j, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long unsubscribeItem(long j, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getNumSubscribedItems();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getSubscribedItems(long[] jArr, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getItemState(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getItemInstallInfo(long j, SteamUGC.ItemInstallInfo itemInstallInfo);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getItemDownloadInfo(long j, long[] jArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long deleteItem(long j, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean downloadItem(long j, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean initWorkshopForGameServer(int i, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void suspendDownloads(boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long startPlaytimeTracking(long j, long[] jArr, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long stopPlaytimeTracking(long j, long[] jArr, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long stopPlaytimeTrackingForAllItems(long j);

    SteamUGCNative() {
    }
}
