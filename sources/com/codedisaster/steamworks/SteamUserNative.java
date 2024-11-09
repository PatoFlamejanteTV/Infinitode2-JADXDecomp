package com.codedisaster.steamworks;

import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUserNative.class */
final class SteamUserNative {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long createCallback(SteamUserCallbackAdapter steamUserCallbackAdapter);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long getSteamID();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int initiateGameConnection(ByteBuffer byteBuffer, int i, int i2, long j, int i3, short s, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void terminateGameConnection(int i, short s);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void startVoiceRecording();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void stopVoiceRecording();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getAvailableVoice(int[] iArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getVoice(ByteBuffer byteBuffer, int i, int i2, int[] iArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int decompressVoice(ByteBuffer byteBuffer, int i, int i2, ByteBuffer byteBuffer2, int i3, int i4, int[] iArr, int i5);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getVoiceOptimalSampleRate();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getAuthSessionTicket(ByteBuffer byteBuffer, int i, int i2, int[] iArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int beginAuthSession(ByteBuffer byteBuffer, int i, int i2, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void endAuthSession(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void cancelAuthTicket(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int userHasLicenseForApp(long j, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long requestEncryptedAppTicket(long j, ByteBuffer byteBuffer, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getEncryptedAppTicket(ByteBuffer byteBuffer, int i, int i2, int[] iArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean isBehindNAT();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void advertiseGame(long j, int i, short s);

    SteamUserNative() {
    }
}
