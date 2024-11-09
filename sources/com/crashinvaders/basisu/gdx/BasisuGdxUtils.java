package com.crashinvaders.basisu.gdx;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.StreamUtils;
import com.crashinvaders.basisu.gdx.BasisuTextureFormatSelector;
import com.crashinvaders.basisu.wrapper.BasisuTextureFormat;
import com.crashinvaders.basisu.wrapper.BasisuTranscoderTextureFormat;
import com.crashinvaders.basisu.wrapper.BasisuTranscoderTextureFormatSupportIndex;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/crashinvaders/basisu/gdx/BasisuGdxUtils.class */
public class BasisuGdxUtils {
    public static final int GL_TEX_ETC1_RGB8 = 36196;
    public static final int GL_TEX_ETC2_RGB8 = 37492;
    public static final int GL_TEX_ETC2_RGBA8 = 37496;
    public static final int GL_TEX_ETC2_R11 = 37488;
    public static final int GL_TEX_ETC2_RG11 = 37490;
    public static final int GL_TEX_BC1_DXT1_RGB = 33776;
    public static final int GL_TEX_BC3_DXT5_RGBA = 33779;
    public static final int GL_TEX_BC4_RGTC1_RED = 36283;
    public static final int GL_TEX_BC5_RGTC2_RG = 36285;
    public static final int GL_TEX_BC7_BPTC_RGBA = 36492;
    public static final int GL_TEX_ASTC_4X4_RGBA = 37808;
    public static final int GL_TEX_ATC_RGB = 35986;
    public static final int GL_TEX_ATC_RGBA_INTERPOLATED = 34798;
    public static final int GL_TEX_FXT1_RGB = 34480;
    public static final int GL_TEX_PVRTC1_4BPP_RGB = 35840;
    public static final int GL_TEX_PVRTC1_4BPP_RGBA = 35842;
    public static final int GL_TEX_PVRTC2_4BPP_RGBA = 37176;
    public static BasisuTextureFormatSelector defaultFormatSelector = new BasisuTextureFormatSelector.Default();
    private static final IntSet supportedGlTextureFormats = new IntSet();
    private static boolean supportedGlTextureFormatsInitialized = false;

    public static boolean isTranscoderTextureFormatSupported(BasisuTranscoderTextureFormat basisuTranscoderTextureFormat, BasisuTextureFormat basisuTextureFormat) {
        return BasisuTranscoderTextureFormatSupportIndex.isTextureFormatSupported(basisuTranscoderTextureFormat, basisuTextureFormat);
    }

    public static int toGlTextureFormat(BasisuTranscoderTextureFormat basisuTranscoderTextureFormat) {
        switch (basisuTranscoderTextureFormat) {
            case ETC1_RGB:
                if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
                    return 37492;
                }
                return GL_TEX_ETC1_RGB8;
            case ETC2_RGBA:
                return 37496;
            case ETC2_EAC_R11:
                return 37488;
            case ETC2_EAC_RG11:
                return 37490;
            case BC1_RGB:
                return 33776;
            case BC3_RGBA:
                return 33779;
            case BC4_R:
                return 36283;
            case BC5_RG:
                return 36285;
            case BC7_RGBA:
                return 36492;
            case ASTC_4x4_RGBA:
                return 37808;
            case ATC_RGB:
                return GL_TEX_ATC_RGB;
            case ATC_RGBA:
                return GL_TEX_ATC_RGBA_INTERPOLATED;
            case PVRTC1_4_RGB:
                return GL_TEX_PVRTC1_4BPP_RGB;
            case PVRTC1_4_RGBA:
                return GL_TEX_PVRTC1_4BPP_RGBA;
            case PVRTC2_4_RGB:
            case PVRTC2_4_RGBA:
                return GL_TEX_PVRTC2_4BPP_RGBA;
            case RGB565:
                return 6407;
            case RGBA32:
            case RGBA4444:
                return 6408;
            default:
                throw new BasisuGdxException("Unsupported basis texture format: " + basisuTranscoderTextureFormat);
        }
    }

    public static int toUncompressedGlTextureType(BasisuTranscoderTextureFormat basisuTranscoderTextureFormat) {
        if (basisuTranscoderTextureFormat.isCompressedFormat()) {
            throw new BasisuGdxException("The \"basisuFormat\" parameter is not an uncompressed texture format: " + basisuTranscoderTextureFormat);
        }
        switch (basisuTranscoderTextureFormat) {
            case RGB565:
                return 33635;
            case RGBA32:
                return 5121;
            case RGBA4444:
                return 32819;
            default:
                throw new BasisuGdxException("Unexpected basis texture format: " + basisuTranscoderTextureFormat);
        }
    }

    public static boolean isBasisuFormatSupported(BasisuTranscoderTextureFormat basisuTranscoderTextureFormat, BasisuTextureFormat basisuTextureFormat) {
        switch (basisuTranscoderTextureFormat) {
            case RGB565:
            case RGBA32:
            case RGBA4444:
                return true;
            default:
                return isGlTextureFormatSupported(toGlTextureFormat(basisuTranscoderTextureFormat)) && isTranscoderTextureFormatSupported(basisuTranscoderTextureFormat, basisuTextureFormat);
        }
    }

    public static IntSet getSupportedGlTextureFormats() {
        initSupportedGlTextureFormats();
        return supportedGlTextureFormats;
    }

    public static synchronized void initSupportedGlTextureFormats() {
        if (supportedGlTextureFormatsInitialized) {
            return;
        }
        supportedGlTextureFormatsInitialized = true;
        supportedGlTextureFormats.addAll(BasisuGdxGl.getSupportedTextureFormats());
    }

    public static boolean isGlTextureFormatSupported(int i) {
        initSupportedGlTextureFormats();
        return supportedGlTextureFormats.contains(i);
    }

    public static boolean isSquareAndPowerOfTwo(int i, int i2) {
        return i == i2 && MathUtils.isPowerOfTwo(i);
    }

    public static boolean isMultipleOfFour(int i, int i2) {
        return i % 4 == 0 && i2 % 4 == 0;
    }

    public static ByteBuffer readFileIntoBuffer(FileHandle fileHandle) {
        ByteBuffer newUnsafeByteBuffer;
        byte[] bArr = new byte[10240];
        DataInputStream dataInputStream = null;
        try {
            try {
                dataInputStream = new DataInputStream(new BufferedInputStream(fileHandle.read()));
                int length = (int) fileHandle.length();
                if (Gdx.app.getType() == Application.ApplicationType.WebGL) {
                    newUnsafeByteBuffer = BufferUtils.newByteBuffer(length);
                } else {
                    newUnsafeByteBuffer = BasisuBufferUtils.newUnsafeByteBuffer(length);
                }
                while (true) {
                    int read = dataInputStream.read(bArr);
                    if (read != -1) {
                        newUnsafeByteBuffer.put(bArr, 0, read);
                    } else {
                        newUnsafeByteBuffer.position(0);
                        ByteBuffer byteBuffer = newUnsafeByteBuffer;
                        byteBuffer.limit(byteBuffer.capacity());
                        ByteBuffer byteBuffer2 = newUnsafeByteBuffer;
                        StreamUtils.closeQuietly(dataInputStream);
                        return byteBuffer2;
                    }
                }
            } catch (Exception e) {
                throw new BasisuGdxException("Couldn't load file '" + fileHandle + "'", e);
            }
        } catch (Throwable th) {
            StreamUtils.closeQuietly(dataInputStream);
            throw th;
        }
    }

    public static String reportAvailableTranscoderFormats(BasisuTextureFormat basisuTextureFormat) {
        StringBuilder sb = new StringBuilder();
        sb.append("===== AVAILABLE TRANSCODER FORMATS | ").append(basisuTextureFormat.name()).append(" | (\"+\" if supported by the platform) =====");
        ArrayList arrayList = new ArrayList(BasisuTranscoderTextureFormatSupportIndex.getSupportedTextureFormats(basisuTextureFormat));
        Collections.sort(arrayList, (basisuTranscoderTextureFormat, basisuTranscoderTextureFormat2) -> {
            return basisuTranscoderTextureFormat.ordinal() - basisuTranscoderTextureFormat2.ordinal();
        });
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BasisuTranscoderTextureFormat basisuTranscoderTextureFormat3 = (BasisuTranscoderTextureFormat) it.next();
            sb.append(SequenceUtils.EOL).append(isBasisuFormatSupported(basisuTranscoderTextureFormat3, basisuTextureFormat) ? "+ " : "  ").append(basisuTranscoderTextureFormat3);
        }
        return sb.toString();
    }
}
