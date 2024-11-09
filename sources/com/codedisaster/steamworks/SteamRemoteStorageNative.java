package com.codedisaster.steamworks;

import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamRemoteStorageNative.class */
final class SteamRemoteStorageNative {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long createCallback(SteamRemoteStorageCallbackAdapter steamRemoteStorageCallbackAdapter);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean fileWrite(String str, ByteBuffer byteBuffer, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int fileRead(String str, ByteBuffer byteBuffer, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long fileWriteAsync(long j, String str, ByteBuffer byteBuffer, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long fileReadAsync(long j, String str, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean fileReadAsyncComplete(long j, ByteBuffer byteBuffer, long j2, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean fileForget(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean fileDelete(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long fileShare(long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setSyncPlatforms(String str, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long fileWriteStreamOpen(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean fileWriteStreamWriteChunk(long j, ByteBuffer byteBuffer, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean fileWriteStreamClose(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean fileWriteStreamCancel(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean fileExists(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean filePersisted(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getFileSize(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long getFileTimestamp(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getSyncPlatforms(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getFileCount();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native String getFileNameAndSize(int i, int[] iArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getQuota(long[] jArr, long[] jArr2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean isCloudEnabledForAccount();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean isCloudEnabledForApp();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setCloudEnabledForApp(boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long ugcDownload(long j, long j2, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getUGCDownloadProgress(long j, int[] iArr, int[] iArr2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int ugcRead(long j, ByteBuffer byteBuffer, int i, int i2, int i3, int i4);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getCachedUGCCount();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long getCachedUGCHandle(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long publishWorkshopFile(long j, String str, String str2, int i, String str3, String str4, int i2, String[] strArr, int i3, int i4);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long createPublishedFileUpdateRequest(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean updatePublishedFileFile(long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean updatePublishedFilePreviewFile(long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean updatePublishedFileTitle(long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean updatePublishedFileDescription(long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean updatePublishedFileVisibility(long j, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean updatePublishedFileTags(long j, String[] strArr, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long commitPublishedFileUpdate(long j, long j2);

    SteamRemoteStorageNative() {
    }
}
