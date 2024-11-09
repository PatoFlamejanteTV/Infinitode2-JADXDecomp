package com.codedisaster.steamworks;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUGCCallback.class */
public interface SteamUGCCallback {
    void onUGCQueryCompleted(SteamUGCQuery steamUGCQuery, int i, int i2, boolean z, SteamResult steamResult);

    void onSubscribeItem(SteamPublishedFileID steamPublishedFileID, SteamResult steamResult);

    void onUnsubscribeItem(SteamPublishedFileID steamPublishedFileID, SteamResult steamResult);

    void onRequestUGCDetails(SteamUGCDetails steamUGCDetails, SteamResult steamResult);

    void onCreateItem(SteamPublishedFileID steamPublishedFileID, boolean z, SteamResult steamResult);

    void onSubmitItemUpdate(SteamPublishedFileID steamPublishedFileID, boolean z, SteamResult steamResult);

    void onDownloadItemResult(int i, SteamPublishedFileID steamPublishedFileID, SteamResult steamResult);

    void onUserFavoriteItemsListChanged(SteamPublishedFileID steamPublishedFileID, boolean z, SteamResult steamResult);

    void onSetUserItemVote(SteamPublishedFileID steamPublishedFileID, boolean z, SteamResult steamResult);

    void onGetUserItemVote(SteamPublishedFileID steamPublishedFileID, boolean z, boolean z2, boolean z3, SteamResult steamResult);

    void onStartPlaytimeTracking(SteamResult steamResult);

    void onStopPlaytimeTracking(SteamResult steamResult);

    void onStopPlaytimeTrackingForAllItems(SteamResult steamResult);

    void onDeleteItem(SteamPublishedFileID steamPublishedFileID, SteamResult steamResult);
}
