package com.codedisaster.steamworks;

import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamRemoteStorage.class */
public class SteamRemoteStorage extends SteamInterface {

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamRemoteStorage$PublishedFileVisibility.class */
    public enum PublishedFileVisibility {
        Public,
        FriendsOnly,
        Private
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamRemoteStorage$UGCReadAction.class */
    public enum UGCReadAction {
        ContinueReadingUntilFinished,
        ContinueReading,
        Close
    }

    @Override // com.codedisaster.steamworks.SteamInterface
    public /* bridge */ /* synthetic */ void dispose() {
        super.dispose();
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamRemoteStorage$RemoteStoragePlatform.class */
    public enum RemoteStoragePlatform {
        None(0),
        Windows(1),
        OSX(2),
        PS3(4),
        Linux(8),
        Reserved2(16),
        Android(32),
        IOS(64),
        All(-1);

        private final int mask;
        private static final RemoteStoragePlatform[] values = values();

        RemoteStoragePlatform(int i) {
            this.mask = i;
        }

        static RemoteStoragePlatform[] byMask(int i) {
            RemoteStoragePlatform[] remoteStoragePlatformArr = new RemoteStoragePlatform[Integer.bitCount(i)];
            int i2 = 0;
            for (RemoteStoragePlatform remoteStoragePlatform : values) {
                if ((remoteStoragePlatform.mask & i) != 0) {
                    int i3 = i2;
                    i2++;
                    remoteStoragePlatformArr[i3] = remoteStoragePlatform;
                }
            }
            return remoteStoragePlatformArr;
        }
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamRemoteStorage$WorkshopFileType.class */
    public enum WorkshopFileType {
        Community,
        Microtransaction,
        Collection,
        Art,
        Video,
        Screenshot,
        Game,
        Software,
        Concept,
        WebGuide,
        IntegratedGuide,
        Merch,
        ControllerBinding,
        SteamworksAccessInvite,
        SteamVideo,
        GameManagedItem;

        private static final WorkshopFileType[] values = values();

        /* JADX INFO: Access modifiers changed from: package-private */
        public static WorkshopFileType byOrdinal(int i) {
            return values[i];
        }
    }

    public SteamRemoteStorage(SteamRemoteStorageCallback steamRemoteStorageCallback) {
        super(SteamRemoteStorageNative.createCallback(new SteamRemoteStorageCallbackAdapter(steamRemoteStorageCallback)));
    }

    public boolean fileWrite(String str, ByteBuffer byteBuffer) {
        checkBuffer(byteBuffer);
        return SteamRemoteStorageNative.fileWrite(str, byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    public int fileRead(String str, ByteBuffer byteBuffer) {
        checkBuffer(byteBuffer);
        return SteamRemoteStorageNative.fileRead(str, byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    public SteamAPICall fileWriteAsync(String str, ByteBuffer byteBuffer) {
        checkBuffer(byteBuffer);
        return new SteamAPICall(SteamRemoteStorageNative.fileWriteAsync(this.callback, str, byteBuffer, byteBuffer.position(), byteBuffer.remaining()));
    }

    public SteamAPICall fileReadAsync(String str, int i, int i2) {
        return new SteamAPICall(SteamRemoteStorageNative.fileReadAsync(this.callback, str, i, i2));
    }

    public boolean fileReadAsyncComplete(SteamAPICall steamAPICall, ByteBuffer byteBuffer, int i) {
        return SteamRemoteStorageNative.fileReadAsyncComplete(steamAPICall.handle, byteBuffer, byteBuffer.position(), i);
    }

    public boolean fileForget(String str) {
        return SteamRemoteStorageNative.fileForget(str);
    }

    public boolean fileDelete(String str) {
        return SteamRemoteStorageNative.fileDelete(str);
    }

    public SteamAPICall fileShare(String str) {
        return new SteamAPICall(SteamRemoteStorageNative.fileShare(this.callback, str));
    }

    public boolean setSyncPlatforms(String str, RemoteStoragePlatform remoteStoragePlatform) {
        return SteamRemoteStorageNative.setSyncPlatforms(str, remoteStoragePlatform.mask);
    }

    public SteamUGCFileWriteStreamHandle fileWriteStreamOpen(String str) {
        return new SteamUGCFileWriteStreamHandle(SteamRemoteStorageNative.fileWriteStreamOpen(str));
    }

    public boolean fileWriteStreamWriteChunk(SteamUGCFileWriteStreamHandle steamUGCFileWriteStreamHandle, ByteBuffer byteBuffer) {
        return SteamRemoteStorageNative.fileWriteStreamWriteChunk(steamUGCFileWriteStreamHandle.handle, byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    public boolean fileWriteStreamClose(SteamUGCFileWriteStreamHandle steamUGCFileWriteStreamHandle) {
        return SteamRemoteStorageNative.fileWriteStreamClose(steamUGCFileWriteStreamHandle.handle);
    }

    public boolean fileWriteStreamCancel(SteamUGCFileWriteStreamHandle steamUGCFileWriteStreamHandle) {
        return SteamRemoteStorageNative.fileWriteStreamCancel(steamUGCFileWriteStreamHandle.handle);
    }

    public boolean fileExists(String str) {
        return SteamRemoteStorageNative.fileExists(str);
    }

    public boolean filePersisted(String str) {
        return SteamRemoteStorageNative.filePersisted(str);
    }

    public int getFileSize(String str) {
        return SteamRemoteStorageNative.getFileSize(str);
    }

    public long getFileTimestamp(String str) {
        return SteamRemoteStorageNative.getFileTimestamp(str);
    }

    public RemoteStoragePlatform[] getSyncPlatforms(String str) {
        return RemoteStoragePlatform.byMask(SteamRemoteStorageNative.getSyncPlatforms(str));
    }

    public int getFileCount() {
        return SteamRemoteStorageNative.getFileCount();
    }

    public String getFileNameAndSize(int i, int[] iArr) {
        return SteamRemoteStorageNative.getFileNameAndSize(i, iArr);
    }

    public boolean getQuota(long[] jArr, long[] jArr2) {
        return SteamRemoteStorageNative.getQuota(jArr, jArr2);
    }

    public boolean isCloudEnabledForAccount() {
        return SteamRemoteStorageNative.isCloudEnabledForAccount();
    }

    public boolean isCloudEnabledForApp() {
        return SteamRemoteStorageNative.isCloudEnabledForApp();
    }

    public void setCloudEnabledForApp(boolean z) {
        SteamRemoteStorageNative.setCloudEnabledForApp(z);
    }

    public SteamAPICall ugcDownload(SteamUGCHandle steamUGCHandle, int i) {
        return new SteamAPICall(SteamRemoteStorageNative.ugcDownload(this.callback, steamUGCHandle.handle, i));
    }

    public boolean getUGCDownloadProgress(SteamUGCHandle steamUGCHandle, int[] iArr, int[] iArr2) {
        return SteamRemoteStorageNative.getUGCDownloadProgress(steamUGCHandle.handle, iArr, iArr2);
    }

    public int ugcRead(SteamUGCHandle steamUGCHandle, ByteBuffer byteBuffer, int i, int i2, UGCReadAction uGCReadAction) {
        return SteamRemoteStorageNative.ugcRead(steamUGCHandle.handle, byteBuffer, byteBuffer.position(), i, i2, uGCReadAction.ordinal());
    }

    public int getCachedUGCCount() {
        return SteamRemoteStorageNative.getCachedUGCCount();
    }

    public SteamUGCHandle getCachedUGCHandle(int i) {
        return new SteamUGCHandle(SteamRemoteStorageNative.getCachedUGCHandle(i));
    }

    public SteamAPICall publishWorkshopFile(String str, String str2, int i, String str3, String str4, PublishedFileVisibility publishedFileVisibility, String[] strArr, WorkshopFileType workshopFileType) {
        return new SteamAPICall(SteamRemoteStorageNative.publishWorkshopFile(this.callback, str, str2, i, str3, str4, publishedFileVisibility.ordinal(), strArr, strArr != null ? strArr.length : 0, workshopFileType.ordinal()));
    }

    public SteamPublishedFileUpdateHandle createPublishedFileUpdateRequest(SteamPublishedFileID steamPublishedFileID) {
        return new SteamPublishedFileUpdateHandle(SteamRemoteStorageNative.createPublishedFileUpdateRequest(steamPublishedFileID.handle));
    }

    public boolean updatePublishedFileFile(SteamPublishedFileUpdateHandle steamPublishedFileUpdateHandle, String str) {
        return SteamRemoteStorageNative.updatePublishedFileFile(steamPublishedFileUpdateHandle.handle, str);
    }

    public boolean updatePublishedFilePreviewFile(SteamPublishedFileUpdateHandle steamPublishedFileUpdateHandle, String str) {
        return SteamRemoteStorageNative.updatePublishedFilePreviewFile(steamPublishedFileUpdateHandle.handle, str);
    }

    public boolean updatePublishedFileTitle(SteamPublishedFileUpdateHandle steamPublishedFileUpdateHandle, String str) {
        return SteamRemoteStorageNative.updatePublishedFileTitle(steamPublishedFileUpdateHandle.handle, str);
    }

    public boolean updatePublishedFileDescription(SteamPublishedFileUpdateHandle steamPublishedFileUpdateHandle, String str) {
        return SteamRemoteStorageNative.updatePublishedFileDescription(steamPublishedFileUpdateHandle.handle, str);
    }

    public boolean updatePublishedFileVisibility(SteamPublishedFileUpdateHandle steamPublishedFileUpdateHandle, PublishedFileVisibility publishedFileVisibility) {
        return SteamRemoteStorageNative.updatePublishedFileVisibility(steamPublishedFileUpdateHandle.handle, publishedFileVisibility.ordinal());
    }

    public boolean updatePublishedFileTags(SteamPublishedFileUpdateHandle steamPublishedFileUpdateHandle, String[] strArr) {
        return SteamRemoteStorageNative.updatePublishedFileTags(steamPublishedFileUpdateHandle.handle, strArr, strArr != null ? strArr.length : 0);
    }

    public SteamAPICall commitPublishedFileUpdate(SteamPublishedFileUpdateHandle steamPublishedFileUpdateHandle) {
        return new SteamAPICall(SteamRemoteStorageNative.commitPublishedFileUpdate(this.callback, steamPublishedFileUpdateHandle.handle));
    }
}
