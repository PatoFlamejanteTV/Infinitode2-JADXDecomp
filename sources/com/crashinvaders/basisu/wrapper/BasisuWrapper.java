package com.crashinvaders.basisu.wrapper;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/crashinvaders/basisu/wrapper/BasisuWrapper.class */
public class BasisuWrapper {
    private static native boolean isTranscoderTexFormatSupportedNative(int i, int i2);

    public static native boolean basisValidateHeader(Buffer buffer);

    public static native boolean basisValidateChecksum(Buffer buffer, boolean z);

    private static native ByteBuffer basisTranscodeNative(Buffer buffer, int i, int i2, int i3, int i4);

    private static native void basisGetFileInfoNative(Buffer buffer, int i, long j);

    private static native void basisGetImageInfoNative(Buffer buffer, int i, int i2, long j);

    private static native void ktx2GetFileInfoNative(Buffer buffer, int i, long j);

    private static native void ktx2GetImageLevelInfoNative(Buffer buffer, int i, int i2, int i3, long j);

    private static native ByteBuffer ktx2TranscodeNative(Buffer buffer, int i, int i2, int i3, int i4);

    public static native void disposeNativeBuffer(ByteBuffer byteBuffer);

    public static boolean isTranscoderTexFormatSupported(BasisuTranscoderTextureFormat basisuTranscoderTextureFormat, BasisuTextureFormat basisuTextureFormat) {
        return isTranscoderTexFormatSupportedNative(basisuTranscoderTextureFormat.getId(), basisuTextureFormat.getId());
    }

    public static ByteBuffer basisTranscode(Buffer buffer, int i, int i2, BasisuTranscoderTextureFormat basisuTranscoderTextureFormat) {
        return basisTranscodeNative(buffer, buffer.capacity(), i, i2, basisuTranscoderTextureFormat.getId());
    }

    public static BasisuFileInfo basisGetFileInfo(Buffer buffer) {
        BasisuFileInfo basisuFileInfo = new BasisuFileInfo();
        basisGetFileInfoNative(buffer, buffer.capacity(), basisuFileInfo.addr);
        return basisuFileInfo;
    }

    public static BasisuImageInfo basisGetImageInfo(Buffer buffer, int i) {
        BasisuImageInfo basisuImageInfo = new BasisuImageInfo();
        basisGetImageInfoNative(buffer, buffer.capacity(), i, basisuImageInfo.addr);
        return basisuImageInfo;
    }

    public static Ktx2FileInfo ktx2GetFileInfo(Buffer buffer) {
        Ktx2FileInfo ktx2FileInfo = new Ktx2FileInfo();
        ktx2GetFileInfoNative(buffer, buffer.capacity(), ktx2FileInfo.addr);
        return ktx2FileInfo;
    }

    public static Ktx2ImageLevelInfo ktx2GetImageLevelInfo(Buffer buffer, int i, int i2) {
        Ktx2ImageLevelInfo ktx2ImageLevelInfo = new Ktx2ImageLevelInfo();
        ktx2GetImageLevelInfoNative(buffer, buffer.capacity(), i, i2, ktx2ImageLevelInfo.addr);
        return ktx2ImageLevelInfo;
    }

    public static ByteBuffer ktx2Transcode(Buffer buffer, int i, int i2, BasisuTranscoderTextureFormat basisuTranscoderTextureFormat) {
        return ktx2TranscodeNative(buffer, buffer.capacity(), i, i2, basisuTranscoderTextureFormat.getId());
    }
}
