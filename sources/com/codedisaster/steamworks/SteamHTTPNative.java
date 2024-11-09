package com.codedisaster.steamworks;

import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamHTTPNative.class */
final class SteamHTTPNative {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long createCallback(SteamHTTPCallbackAdapter steamHTTPCallbackAdapter);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long createHTTPRequest(boolean z, int i, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setHTTPRequestContextValue(boolean z, long j, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setHTTPRequestNetworkActivityTimeout(boolean z, long j, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setHTTPRequestHeaderValue(boolean z, long j, String str, String str2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean setHTTPRequestGetOrPostParameter(boolean z, long j, String str, String str2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long sendHTTPRequest(boolean z, long j, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long sendHTTPRequestAndStreamResponse(boolean z, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getHTTPResponseHeaderSize(boolean z, long j, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getHTTPResponseHeaderValue(boolean z, long j, String str, ByteBuffer byteBuffer, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getHTTPResponseBodySize(boolean z, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getHTTPResponseBodyData(boolean z, long j, ByteBuffer byteBuffer, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean getHTTPStreamingResponseBodyData(boolean z, long j, int i, ByteBuffer byteBuffer, int i2, int i3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean releaseHTTPRequest(boolean z, long j);

    SteamHTTPNative() {
    }
}
