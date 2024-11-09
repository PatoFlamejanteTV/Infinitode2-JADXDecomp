package org.lwjgl.stb;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.stb.STBRPRect;
import org.lwjgl.stb.STBTTBakedChar;
import org.lwjgl.stb.STBTTKerningentry;
import org.lwjgl.stb.STBTTPackRange;
import org.lwjgl.stb.STBTTPackedchar;
import org.lwjgl.stb.STBTTVertex;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;

/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBTruetype.class */
public class STBTruetype {
    public static final byte STBTT_vmove = 1;
    public static final byte STBTT_vline = 2;
    public static final byte STBTT_vcurve = 3;
    public static final byte STBTT_vcubic = 4;
    public static final int STBTT_MACSTYLE_DONTCARE = 0;
    public static final int STBTT_MACSTYLE_BOLD = 1;
    public static final int STBTT_MACSTYLE_ITALIC = 2;
    public static final int STBTT_MACSTYLE_UNDERSCORE = 4;
    public static final int STBTT_MACSTYLE_NONE = 8;
    public static final int STBTT_PLATFORM_ID_UNICODE = 0;
    public static final int STBTT_PLATFORM_ID_MAC = 1;
    public static final int STBTT_PLATFORM_ID_ISO = 2;
    public static final int STBTT_PLATFORM_ID_MICROSOFT = 3;
    public static final int STBTT_UNICODE_EID_UNICODE_1_0 = 0;
    public static final int STBTT_UNICODE_EID_UNICODE_1_1 = 1;
    public static final int STBTT_UNICODE_EID_ISO_10646 = 2;
    public static final int STBTT_UNICODE_EID_UNICODE_2_0_BMP = 3;
    public static final int STBTT_UNICODE_EID_UNICODE_2_0_FULL = 4;
    public static final int STBTT_MS_EID_SYMBOL = 0;
    public static final int STBTT_MS_EID_UNICODE_BMP = 1;
    public static final int STBTT_MS_EID_SHIFTJIS = 2;
    public static final int STBTT_MS_EID_UNICODE_FULL = 10;
    public static final int STBTT_MAC_EID_ROMAN = 0;
    public static final int STBTT_MAC_EID_JAPANESE = 1;
    public static final int STBTT_MAC_EID_CHINESE_TRAD = 2;
    public static final int STBTT_MAC_EID_KOREAN = 3;
    public static final int STBTT_MAC_EID_ARABIC = 4;
    public static final int STBTT_MAC_EID_HEBREW = 5;
    public static final int STBTT_MAC_EID_GREEK = 6;
    public static final int STBTT_MAC_EID_RUSSIAN = 7;
    public static final int STBTT_MS_LANG_ENGLISH = 1033;
    public static final int STBTT_MS_LANG_CHINESE = 2052;
    public static final int STBTT_MS_LANG_DUTCH = 1043;
    public static final int STBTT_MS_LANG_FRENCH = 1036;
    public static final int STBTT_MS_LANG_GERMAN = 1031;
    public static final int STBTT_MS_LANG_HEBREW = 1037;
    public static final int STBTT_MS_LANG_ITALIAN = 1040;
    public static final int STBTT_MS_LANG_JAPANESE = 1041;
    public static final int STBTT_MS_LANG_KOREAN = 1042;
    public static final int STBTT_MS_LANG_RUSSIAN = 1049;
    public static final int STBTT_MS_LANG_SPANISH = 1033;
    public static final int STBTT_MS_LANG_SWEDISH = 1053;
    public static final int STBTT_MAC_LANG_ENGLISH = 0;
    public static final int STBTT_MAC_LANG_ARABIC = 12;
    public static final int STBTT_MAC_LANG_DUTCH = 4;
    public static final int STBTT_MAC_LANG_FRENCH = 1;
    public static final int STBTT_MAC_LANG_GERMAN = 2;
    public static final int STBTT_MAC_LANG_HEBREW = 10;
    public static final int STBTT_MAC_LANG_ITALIAN = 3;
    public static final int STBTT_MAC_LANG_JAPANESE = 11;
    public static final int STBTT_MAC_LANG_KOREAN = 23;
    public static final int STBTT_MAC_LANG_RUSSIAN = 32;
    public static final int STBTT_MAC_LANG_SPANISH = 6;
    public static final int STBTT_MAC_LANG_SWEDISH = 5;
    public static final int STBTT_MAC_LANG_CHINESE_SIMPLIFIED = 33;
    public static final int STBTT_MAC_LANG_CHINESE_TRAD = 19;

    public static native int nstbtt_BakeFontBitmap(long j, int i, float f, long j2, int i2, int i3, int i4, int i5, long j3);

    public static native void nstbtt_GetBakedQuad(long j, int i, int i2, int i3, long j2, long j3, long j4, int i4);

    public static native void nstbtt_GetScaledFontVMetrics(long j, int i, float f, long j2, long j3, long j4);

    public static native int nstbtt_PackBegin(long j, long j2, int i, int i2, int i3, int i4, long j3);

    public static native void nstbtt_PackEnd(long j);

    public static native int nstbtt_PackFontRange(long j, long j2, int i, float f, int i2, int i3, long j3);

    public static native int nstbtt_PackFontRanges(long j, long j2, int i, long j3, int i2);

    public static native void nstbtt_PackSetOversampling(long j, int i, int i2);

    public static native void nstbtt_PackSetSkipMissingCodepoints(long j, int i);

    public static native void nstbtt_GetPackedQuad(long j, int i, int i2, int i3, long j2, long j3, long j4, int i4);

    public static native int nstbtt_PackFontRangesGatherRects(long j, long j2, long j3, int i, long j4);

    public static native void nstbtt_PackFontRangesPackRects(long j, long j2, int i);

    public static native int nstbtt_PackFontRangesRenderIntoRects(long j, long j2, long j3, int i, long j4);

    public static native int nstbtt_GetNumberOfFonts(long j);

    public static native int nstbtt_GetFontOffsetForIndex(long j, int i);

    public static native int nstbtt_InitFont(long j, long j2, int i);

    public static native int nstbtt_FindGlyphIndex(long j, int i);

    public static native float nstbtt_ScaleForPixelHeight(long j, float f);

    public static native float nstbtt_ScaleForMappingEmToPixels(long j, float f);

    public static native void nstbtt_GetFontVMetrics(long j, long j2, long j3, long j4);

    public static native int nstbtt_GetFontVMetricsOS2(long j, long j2, long j3, long j4);

    public static native void nstbtt_GetFontBoundingBox(long j, long j2, long j3, long j4, long j5);

    public static native void nstbtt_GetCodepointHMetrics(long j, int i, long j2, long j3);

    public static native int nstbtt_GetCodepointKernAdvance(long j, int i, int i2);

    public static native int nstbtt_GetCodepointBox(long j, int i, long j2, long j3, long j4, long j5);

    public static native void nstbtt_GetGlyphHMetrics(long j, int i, long j2, long j3);

    public static native int nstbtt_GetGlyphKernAdvance(long j, int i, int i2);

    public static native int nstbtt_GetGlyphBox(long j, int i, long j2, long j3, long j4, long j5);

    public static native int nstbtt_GetKerningTableLength(long j);

    public static native int nstbtt_GetKerningTable(long j, long j2, int i);

    public static native int nstbtt_IsGlyphEmpty(long j, int i);

    public static native int nstbtt_GetCodepointShape(long j, int i, long j2);

    public static native int nstbtt_GetGlyphShape(long j, int i, long j2);

    public static native void nstbtt_FreeShape(long j, long j2);

    public static native long nstbtt_FindSVGDoc(long j, int i);

    public static native int nstbtt_GetCodepointSVG(long j, int i, long j2);

    public static native int nstbtt_GetGlyphSVG(long j, int i, long j2);

    public static native void nstbtt_FreeBitmap(long j, long j2);

    public static native long nstbtt_GetCodepointBitmap(long j, float f, float f2, int i, long j2, long j3, long j4, long j5);

    public static native long nstbtt_GetCodepointBitmapSubpixel(long j, float f, float f2, float f3, float f4, int i, long j2, long j3, long j4, long j5);

    public static native void nstbtt_MakeCodepointBitmap(long j, long j2, int i, int i2, int i3, float f, float f2, int i4);

    public static native void nstbtt_MakeCodepointBitmapSubpixel(long j, long j2, int i, int i2, int i3, float f, float f2, float f3, float f4, int i4);

    public static native void nstbtt_MakeCodepointBitmapSubpixelPrefilter(long j, long j2, int i, int i2, int i3, float f, float f2, float f3, float f4, int i4, int i5, long j3, long j4, int i6);

    public static native void nstbtt_GetCodepointBitmapBox(long j, int i, float f, float f2, long j2, long j3, long j4, long j5);

    public static native void nstbtt_GetCodepointBitmapBoxSubpixel(long j, int i, float f, float f2, float f3, float f4, long j2, long j3, long j4, long j5);

    public static native long nstbtt_GetGlyphBitmap(long j, float f, float f2, int i, long j2, long j3, long j4, long j5);

    public static native long nstbtt_GetGlyphBitmapSubpixel(long j, float f, float f2, float f3, float f4, int i, long j2, long j3, long j4, long j5);

    public static native void nstbtt_MakeGlyphBitmap(long j, long j2, int i, int i2, int i3, float f, float f2, int i4);

    public static native void nstbtt_MakeGlyphBitmapSubpixel(long j, long j2, int i, int i2, int i3, float f, float f2, float f3, float f4, int i4);

    public static native void nstbtt_MakeGlyphBitmapSubpixelPrefilter(long j, long j2, int i, int i2, int i3, float f, float f2, float f3, float f4, int i4, int i5, long j3, long j4, int i6);

    public static native void nstbtt_GetGlyphBitmapBox(long j, int i, float f, float f2, long j2, long j3, long j4, long j5);

    public static native void nstbtt_GetGlyphBitmapBoxSubpixel(long j, int i, float f, float f2, float f3, float f4, long j2, long j3, long j4, long j5);

    public static native void nstbtt_Rasterize(long j, float f, long j2, int i, float f2, float f3, float f4, float f5, int i2, int i3, int i4, long j3);

    public static native void nstbtt_FreeSDF(long j, long j2);

    public static native long nstbtt_GetGlyphSDF(long j, float f, int i, int i2, byte b2, float f2, long j2, long j3, long j4, long j5);

    public static native long nstbtt_GetCodepointSDF(long j, float f, int i, int i2, byte b2, float f2, long j2, long j3, long j4, long j5);

    public static native int nstbtt_FindMatchingFont(long j, long j2, int i);

    public static native int nstbtt_CompareUTF8toUTF16_bigendian(long j, int i, long j2, int i2);

    public static native long nstbtt_GetFontNameString(long j, long j2, int i, int i2, int i3, int i4);

    public static native void nstbtt_GetBakedQuad(long j, int i, int i2, int i3, float[] fArr, float[] fArr2, long j2, int i4);

    public static native void nstbtt_GetScaledFontVMetrics(long j, int i, float f, float[] fArr, float[] fArr2, float[] fArr3);

    public static native void nstbtt_GetPackedQuad(long j, int i, int i2, int i3, float[] fArr, float[] fArr2, long j2, int i4);

    public static native void nstbtt_GetFontVMetrics(long j, int[] iArr, int[] iArr2, int[] iArr3);

    public static native int nstbtt_GetFontVMetricsOS2(long j, int[] iArr, int[] iArr2, int[] iArr3);

    public static native void nstbtt_GetFontBoundingBox(long j, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4);

    public static native void nstbtt_GetCodepointHMetrics(long j, int i, int[] iArr, int[] iArr2);

    public static native int nstbtt_GetCodepointBox(long j, int i, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4);

    public static native void nstbtt_GetGlyphHMetrics(long j, int i, int[] iArr, int[] iArr2);

    public static native int nstbtt_GetGlyphBox(long j, int i, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4);

    public static native long nstbtt_GetCodepointBitmap(long j, float f, float f2, int i, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4);

    public static native long nstbtt_GetCodepointBitmapSubpixel(long j, float f, float f2, float f3, float f4, int i, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4);

    public static native void nstbtt_MakeCodepointBitmapSubpixelPrefilter(long j, long j2, int i, int i2, int i3, float f, float f2, float f3, float f4, int i4, int i5, float[] fArr, float[] fArr2, int i6);

    public static native void nstbtt_GetCodepointBitmapBox(long j, int i, float f, float f2, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4);

    public static native void nstbtt_GetCodepointBitmapBoxSubpixel(long j, int i, float f, float f2, float f3, float f4, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4);

    public static native long nstbtt_GetGlyphBitmap(long j, float f, float f2, int i, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4);

    public static native long nstbtt_GetGlyphBitmapSubpixel(long j, float f, float f2, float f3, float f4, int i, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4);

    public static native void nstbtt_MakeGlyphBitmapSubpixelPrefilter(long j, long j2, int i, int i2, int i3, float f, float f2, float f3, float f4, int i4, int i5, float[] fArr, float[] fArr2, int i6);

    public static native void nstbtt_GetGlyphBitmapBox(long j, int i, float f, float f2, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4);

    public static native void nstbtt_GetGlyphBitmapBoxSubpixel(long j, int i, float f, float f2, float f3, float f4, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4);

    public static native long nstbtt_GetGlyphSDF(long j, float f, int i, int i2, byte b2, float f2, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4);

    public static native long nstbtt_GetCodepointSDF(long j, float f, int i, int i2, byte b2, float f2, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4);

    static {
        LibSTB.initialize();
    }

    protected STBTruetype() {
        throw new UnsupportedOperationException();
    }

    public static int stbtt_BakeFontBitmap(@NativeType("unsigned char const *") ByteBuffer byteBuffer, float f, @NativeType("unsigned char *") ByteBuffer byteBuffer2, int i, int i2, int i3, @NativeType("stbtt_bakedchar *") STBTTBakedChar.Buffer buffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer2, i * i2);
        }
        return nstbtt_BakeFontBitmap(MemoryUtil.memAddress(byteBuffer), 0, f, MemoryUtil.memAddress(byteBuffer2), i, i2, i3, buffer.remaining(), buffer.address());
    }

    public static void stbtt_GetBakedQuad(@NativeType("stbtt_bakedchar const *") STBTTBakedChar.Buffer buffer, int i, int i2, int i3, @NativeType("float *") FloatBuffer floatBuffer, @NativeType("float *") FloatBuffer floatBuffer2, @NativeType("stbtt_aligned_quad *") STBTTAlignedQuad sTBTTAlignedQuad, @NativeType("int") boolean z) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) buffer, i3 + 1);
            Checks.check((Buffer) floatBuffer, 1);
            Checks.check((Buffer) floatBuffer2, 1);
        }
        nstbtt_GetBakedQuad(buffer.address(), i, i2, i3, MemoryUtil.memAddress(floatBuffer), MemoryUtil.memAddress(floatBuffer2), sTBTTAlignedQuad.address(), z ? 1 : 0);
    }

    public static void stbtt_GetScaledFontVMetrics(@NativeType("unsigned char const *") ByteBuffer byteBuffer, int i, float f, @NativeType("float *") FloatBuffer floatBuffer, @NativeType("float *") FloatBuffer floatBuffer2, @NativeType("float *") FloatBuffer floatBuffer3) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
            Checks.check((Buffer) floatBuffer2, 1);
            Checks.check((Buffer) floatBuffer3, 1);
        }
        nstbtt_GetScaledFontVMetrics(MemoryUtil.memAddress(byteBuffer), i, f, MemoryUtil.memAddress(floatBuffer), MemoryUtil.memAddress(floatBuffer2), MemoryUtil.memAddress(floatBuffer3));
    }

    @NativeType("int")
    public static boolean stbtt_PackBegin(@NativeType("stbtt_pack_context *") STBTTPackContext sTBTTPackContext, @NativeType("unsigned char *") ByteBuffer byteBuffer, int i, int i2, int i3, int i4, @NativeType("void *") long j) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) byteBuffer, (i3 != 0 ? i3 : i) * i2);
        }
        return nstbtt_PackBegin(sTBTTPackContext.address(), MemoryUtil.memAddressSafe(byteBuffer), i, i2, i3, i4, j) != 0;
    }

    @NativeType("int")
    public static boolean stbtt_PackBegin(@NativeType("stbtt_pack_context *") STBTTPackContext sTBTTPackContext, @NativeType("unsigned char *") ByteBuffer byteBuffer, int i, int i2, int i3, int i4) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) byteBuffer, (i3 != 0 ? i3 : i) * i2);
        }
        return nstbtt_PackBegin(sTBTTPackContext.address(), MemoryUtil.memAddressSafe(byteBuffer), i, i2, i3, i4, 0L) != 0;
    }

    public static void stbtt_PackEnd(@NativeType("stbtt_pack_context *") STBTTPackContext sTBTTPackContext) {
        nstbtt_PackEnd(sTBTTPackContext.address());
    }

    public static int STBTT_POINT_SIZE(int i) {
        return -i;
    }

    @NativeType("int")
    public static boolean stbtt_PackFontRange(@NativeType("stbtt_pack_context *") STBTTPackContext sTBTTPackContext, @NativeType("unsigned char const *") ByteBuffer byteBuffer, int i, float f, int i2, @NativeType("stbtt_packedchar *") STBTTPackedchar.Buffer buffer) {
        return nstbtt_PackFontRange(sTBTTPackContext.address(), MemoryUtil.memAddress(byteBuffer), i, f, i2, buffer.remaining(), buffer.address()) != 0;
    }

    @NativeType("int")
    public static boolean stbtt_PackFontRanges(@NativeType("stbtt_pack_context *") STBTTPackContext sTBTTPackContext, @NativeType("unsigned char const *") ByteBuffer byteBuffer, int i, @NativeType("stbtt_pack_range *") STBTTPackRange.Buffer buffer) {
        if (Checks.CHECKS) {
            Struct.validate(buffer.address(), buffer.remaining(), STBTTPackRange.SIZEOF, STBTTPackRange::validate);
        }
        return nstbtt_PackFontRanges(sTBTTPackContext.address(), MemoryUtil.memAddress(byteBuffer), i, buffer.address(), buffer.remaining()) != 0;
    }

    public static void stbtt_PackSetOversampling(@NativeType("stbtt_pack_context *") STBTTPackContext sTBTTPackContext, @NativeType("unsigned int") int i, @NativeType("unsigned int") int i2) {
        nstbtt_PackSetOversampling(sTBTTPackContext.address(), i, i2);
    }

    public static void stbtt_PackSetSkipMissingCodepoints(@NativeType("stbtt_pack_context *") STBTTPackContext sTBTTPackContext, @NativeType("int") boolean z) {
        nstbtt_PackSetSkipMissingCodepoints(sTBTTPackContext.address(), z ? 1 : 0);
    }

    public static void stbtt_GetPackedQuad(@NativeType("stbtt_packedchar const *") STBTTPackedchar.Buffer buffer, int i, int i2, int i3, @NativeType("float *") FloatBuffer floatBuffer, @NativeType("float *") FloatBuffer floatBuffer2, @NativeType("stbtt_aligned_quad *") STBTTAlignedQuad sTBTTAlignedQuad, @NativeType("int") boolean z) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) buffer, i3 + 1);
            Checks.check((Buffer) floatBuffer, 1);
            Checks.check((Buffer) floatBuffer2, 1);
        }
        nstbtt_GetPackedQuad(buffer.address(), i, i2, i3, MemoryUtil.memAddress(floatBuffer), MemoryUtil.memAddress(floatBuffer2), sTBTTAlignedQuad.address(), z ? 1 : 0);
    }

    public static int stbtt_PackFontRangesGatherRects(@NativeType("stbtt_pack_context *") STBTTPackContext sTBTTPackContext, @NativeType("stbtt_fontinfo *") STBTTFontinfo sTBTTFontinfo, @NativeType("stbtt_pack_range *") STBTTPackRange.Buffer buffer, @NativeType("stbrp_rect *") STBRPRect.Buffer buffer2) {
        if (Checks.CHECKS) {
            Struct.validate(buffer.address(), buffer.remaining(), STBTTPackRange.SIZEOF, STBTTPackRange::validate);
        }
        return nstbtt_PackFontRangesGatherRects(sTBTTPackContext.address(), sTBTTFontinfo.address(), buffer.address(), buffer.remaining(), buffer2.address());
    }

    public static void stbtt_PackFontRangesPackRects(@NativeType("stbtt_pack_context *") STBTTPackContext sTBTTPackContext, @NativeType("stbrp_rect *") STBRPRect.Buffer buffer) {
        nstbtt_PackFontRangesPackRects(sTBTTPackContext.address(), buffer.address(), buffer.remaining());
    }

    @NativeType("int")
    public static boolean stbtt_PackFontRangesRenderIntoRects(@NativeType("stbtt_pack_context *") STBTTPackContext sTBTTPackContext, @NativeType("stbtt_fontinfo *") STBTTFontinfo sTBTTFontinfo, @NativeType("stbtt_pack_range *") STBTTPackRange.Buffer buffer, @NativeType("stbrp_rect *") STBRPRect.Buffer buffer2) {
        if (Checks.CHECKS) {
            Struct.validate(buffer.address(), buffer.remaining(), STBTTPackRange.SIZEOF, STBTTPackRange::validate);
        }
        return nstbtt_PackFontRangesRenderIntoRects(sTBTTPackContext.address(), sTBTTFontinfo.address(), buffer.address(), buffer.remaining(), buffer2.address()) != 0;
    }

    public static int stbtt_GetNumberOfFonts(@NativeType("unsigned char const *") ByteBuffer byteBuffer) {
        return nstbtt_GetNumberOfFonts(MemoryUtil.memAddress(byteBuffer));
    }

    public static int stbtt_GetFontOffsetForIndex(@NativeType("unsigned char const *") ByteBuffer byteBuffer, int i) {
        return nstbtt_GetFontOffsetForIndex(MemoryUtil.memAddress(byteBuffer), i);
    }

    @NativeType("int")
    public static boolean stbtt_InitFont(@NativeType("stbtt_fontinfo *") STBTTFontinfo sTBTTFontinfo, @NativeType("unsigned char const *") ByteBuffer byteBuffer, int i) {
        return nstbtt_InitFont(sTBTTFontinfo.address(), MemoryUtil.memAddress(byteBuffer), i) != 0;
    }

    @NativeType("int")
    public static boolean stbtt_InitFont(@NativeType("stbtt_fontinfo *") STBTTFontinfo sTBTTFontinfo, @NativeType("unsigned char const *") ByteBuffer byteBuffer) {
        return nstbtt_InitFont(sTBTTFontinfo.address(), MemoryUtil.memAddress(byteBuffer), 0) != 0;
    }

    public static int stbtt_FindGlyphIndex(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i) {
        return nstbtt_FindGlyphIndex(sTBTTFontinfo.address(), i);
    }

    public static float stbtt_ScaleForPixelHeight(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, float f) {
        return nstbtt_ScaleForPixelHeight(sTBTTFontinfo.address(), f);
    }

    public static float stbtt_ScaleForMappingEmToPixels(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, float f) {
        return nstbtt_ScaleForMappingEmToPixels(sTBTTFontinfo.address(), f);
    }

    public static void stbtt_GetFontVMetrics(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.checkSafe((Buffer) intBuffer2, 1);
            Checks.checkSafe((Buffer) intBuffer3, 1);
        }
        nstbtt_GetFontVMetrics(sTBTTFontinfo.address(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2), MemoryUtil.memAddressSafe(intBuffer3));
    }

    @NativeType("int")
    public static boolean stbtt_GetFontVMetricsOS2(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.checkSafe((Buffer) intBuffer2, 1);
            Checks.checkSafe((Buffer) intBuffer3, 1);
        }
        return nstbtt_GetFontVMetricsOS2(sTBTTFontinfo.address(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2), MemoryUtil.memAddressSafe(intBuffer3)) != 0;
    }

    public static void stbtt_GetFontBoundingBox(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, @NativeType("int *") IntBuffer intBuffer4) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
            Checks.check((Buffer) intBuffer4, 1);
        }
        nstbtt_GetFontBoundingBox(sTBTTFontinfo.address(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), MemoryUtil.memAddress(intBuffer4));
    }

    public static void stbtt_GetCodepointHMetrics(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.checkSafe((Buffer) intBuffer2, 1);
        }
        nstbtt_GetCodepointHMetrics(sTBTTFontinfo.address(), i, MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2));
    }

    public static int stbtt_GetCodepointKernAdvance(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i, int i2) {
        return nstbtt_GetCodepointKernAdvance(sTBTTFontinfo.address(), i, i2);
    }

    @NativeType("int")
    public static boolean stbtt_GetCodepointBox(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, @NativeType("int *") IntBuffer intBuffer4) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.checkSafe((Buffer) intBuffer2, 1);
            Checks.checkSafe((Buffer) intBuffer3, 1);
            Checks.checkSafe((Buffer) intBuffer4, 1);
        }
        return nstbtt_GetCodepointBox(sTBTTFontinfo.address(), i, MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2), MemoryUtil.memAddressSafe(intBuffer3), MemoryUtil.memAddressSafe(intBuffer4)) != 0;
    }

    public static void stbtt_GetGlyphHMetrics(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.checkSafe((Buffer) intBuffer2, 1);
        }
        nstbtt_GetGlyphHMetrics(sTBTTFontinfo.address(), i, MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2));
    }

    public static int stbtt_GetGlyphKernAdvance(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i, int i2) {
        return nstbtt_GetGlyphKernAdvance(sTBTTFontinfo.address(), i, i2);
    }

    @NativeType("int")
    public static boolean stbtt_GetGlyphBox(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, @NativeType("int *") IntBuffer intBuffer4) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.checkSafe((Buffer) intBuffer2, 1);
            Checks.checkSafe((Buffer) intBuffer3, 1);
            Checks.checkSafe((Buffer) intBuffer4, 1);
        }
        return nstbtt_GetGlyphBox(sTBTTFontinfo.address(), i, MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2), MemoryUtil.memAddressSafe(intBuffer3), MemoryUtil.memAddressSafe(intBuffer4)) != 0;
    }

    public static int stbtt_GetKerningTableLength(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo) {
        return nstbtt_GetKerningTableLength(sTBTTFontinfo.address());
    }

    public static int stbtt_GetKerningTable(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, @NativeType("stbtt_kerningentry *") STBTTKerningentry.Buffer buffer) {
        return nstbtt_GetKerningTable(sTBTTFontinfo.address(), buffer.address(), buffer.remaining());
    }

    @NativeType("int")
    public static boolean stbtt_IsGlyphEmpty(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i) {
        return nstbtt_IsGlyphEmpty(sTBTTFontinfo.address(), i) != 0;
    }

    public static int stbtt_GetCodepointShape(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i, @NativeType("stbtt_vertex **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nstbtt_GetCodepointShape(sTBTTFontinfo.address(), i, MemoryUtil.memAddress(pointerBuffer));
    }

    @NativeType("int")
    public static STBTTVertex.Buffer stbtt_GetCodepointShape(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            PointerBuffer pointers = stackGet.pointers(0L);
            return STBTTVertex.createSafe(pointers.get(0), nstbtt_GetCodepointShape(sTBTTFontinfo.address(), i, MemoryUtil.memAddress(pointers)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static int stbtt_GetGlyphShape(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i, @NativeType("stbtt_vertex **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nstbtt_GetGlyphShape(sTBTTFontinfo.address(), i, MemoryUtil.memAddress(pointerBuffer));
    }

    @NativeType("int")
    public static STBTTVertex.Buffer stbtt_GetGlyphShape(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            PointerBuffer pointers = stackGet.pointers(0L);
            return STBTTVertex.createSafe(pointers.get(0), nstbtt_GetGlyphShape(sTBTTFontinfo.address(), i, MemoryUtil.memAddress(pointers)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void stbtt_FreeShape(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, @NativeType("stbtt_vertex *") STBTTVertex.Buffer buffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) buffer, 1);
        }
        nstbtt_FreeShape(sTBTTFontinfo.address(), buffer.address());
    }

    @NativeType("unsigned char *")
    public static long stbtt_FindSVGDoc(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i) {
        return nstbtt_FindSVGDoc(sTBTTFontinfo.address(), i);
    }

    public static int stbtt_GetCodepointSVG(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i, @NativeType("char const **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nstbtt_GetCodepointSVG(sTBTTFontinfo.address(), i, MemoryUtil.memAddress(pointerBuffer));
    }

    public static int stbtt_GetGlyphSVG(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i, @NativeType("char const **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nstbtt_GetGlyphSVG(sTBTTFontinfo.address(), i, MemoryUtil.memAddress(pointerBuffer));
    }

    public static void stbtt_FreeBitmap(@NativeType("unsigned char *") ByteBuffer byteBuffer, @NativeType("void *") long j) {
        nstbtt_FreeBitmap(MemoryUtil.memAddress(byteBuffer), j);
    }

    public static void stbtt_FreeBitmap(@NativeType("unsigned char *") ByteBuffer byteBuffer) {
        nstbtt_FreeBitmap(MemoryUtil.memAddress(byteBuffer), 0L);
    }

    @NativeType("unsigned char *")
    public static ByteBuffer stbtt_GetCodepointBitmap(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, float f, float f2, int i, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, @NativeType("int *") IntBuffer intBuffer4) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.checkSafe((Buffer) intBuffer3, 1);
            Checks.checkSafe((Buffer) intBuffer4, 1);
        }
        return MemoryUtil.memByteBufferSafe(nstbtt_GetCodepointBitmap(sTBTTFontinfo.address(), f, f2, i, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddressSafe(intBuffer3), MemoryUtil.memAddressSafe(intBuffer4)), intBuffer.get(intBuffer.position()) * intBuffer2.get(intBuffer2.position()));
    }

    @NativeType("unsigned char *")
    public static ByteBuffer stbtt_GetCodepointBitmapSubpixel(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, float f, float f2, float f3, float f4, int i, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, @NativeType("int *") IntBuffer intBuffer4) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.checkSafe((Buffer) intBuffer3, 1);
            Checks.checkSafe((Buffer) intBuffer4, 1);
        }
        return MemoryUtil.memByteBufferSafe(nstbtt_GetCodepointBitmapSubpixel(sTBTTFontinfo.address(), f, f2, f3, f4, i, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddressSafe(intBuffer3), MemoryUtil.memAddressSafe(intBuffer4)), intBuffer.get(intBuffer.position()) * intBuffer2.get(intBuffer2.position()));
    }

    public static void stbtt_MakeCodepointBitmap(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, @NativeType("unsigned char *") ByteBuffer byteBuffer, int i, int i2, int i3, float f, float f2, int i4) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, (i3 != 0 ? i3 : i) * i2);
        }
        nstbtt_MakeCodepointBitmap(sTBTTFontinfo.address(), MemoryUtil.memAddress(byteBuffer), i, i2, i3, f, f2, i4);
    }

    public static void stbtt_MakeCodepointBitmapSubpixel(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, @NativeType("unsigned char *") ByteBuffer byteBuffer, int i, int i2, int i3, float f, float f2, float f3, float f4, int i4) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, (i3 != 0 ? i3 : i) * i2);
        }
        nstbtt_MakeCodepointBitmapSubpixel(sTBTTFontinfo.address(), MemoryUtil.memAddress(byteBuffer), i, i2, i3, f, f2, f3, f4, i4);
    }

    public static void stbtt_MakeCodepointBitmapSubpixelPrefilter(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, @NativeType("unsigned char *") ByteBuffer byteBuffer, int i, int i2, int i3, float f, float f2, float f3, float f4, int i4, int i5, @NativeType("float *") FloatBuffer floatBuffer, @NativeType("float *") FloatBuffer floatBuffer2, int i6) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, (i3 != 0 ? i3 : i) * i2);
            Checks.check((Buffer) floatBuffer, 1);
            Checks.check((Buffer) floatBuffer2, 1);
        }
        nstbtt_MakeCodepointBitmapSubpixelPrefilter(sTBTTFontinfo.address(), MemoryUtil.memAddress(byteBuffer), i, i2, i3, f, f2, f3, f4, i4, i5, MemoryUtil.memAddress(floatBuffer), MemoryUtil.memAddress(floatBuffer2), i6);
    }

    public static void stbtt_GetCodepointBitmapBox(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i, float f, float f2, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, @NativeType("int *") IntBuffer intBuffer4) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.checkSafe((Buffer) intBuffer2, 1);
            Checks.checkSafe((Buffer) intBuffer3, 1);
            Checks.checkSafe((Buffer) intBuffer4, 1);
        }
        nstbtt_GetCodepointBitmapBox(sTBTTFontinfo.address(), i, f, f2, MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2), MemoryUtil.memAddressSafe(intBuffer3), MemoryUtil.memAddressSafe(intBuffer4));
    }

    public static void stbtt_GetCodepointBitmapBoxSubpixel(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i, float f, float f2, float f3, float f4, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, @NativeType("int *") IntBuffer intBuffer4) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.checkSafe((Buffer) intBuffer2, 1);
            Checks.checkSafe((Buffer) intBuffer3, 1);
            Checks.checkSafe((Buffer) intBuffer4, 1);
        }
        nstbtt_GetCodepointBitmapBoxSubpixel(sTBTTFontinfo.address(), i, f, f2, f3, f4, MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2), MemoryUtil.memAddressSafe(intBuffer3), MemoryUtil.memAddressSafe(intBuffer4));
    }

    @NativeType("unsigned char *")
    public static ByteBuffer stbtt_GetGlyphBitmap(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, float f, float f2, int i, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, @NativeType("int *") IntBuffer intBuffer4) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.checkSafe((Buffer) intBuffer3, 1);
            Checks.checkSafe((Buffer) intBuffer4, 1);
        }
        return MemoryUtil.memByteBufferSafe(nstbtt_GetGlyphBitmap(sTBTTFontinfo.address(), f, f2, i, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddressSafe(intBuffer3), MemoryUtil.memAddressSafe(intBuffer4)), intBuffer.get(intBuffer.position()) * intBuffer2.get(intBuffer2.position()));
    }

    @NativeType("unsigned char *")
    public static ByteBuffer stbtt_GetGlyphBitmapSubpixel(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, float f, float f2, float f3, float f4, int i, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, @NativeType("int *") IntBuffer intBuffer4) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.checkSafe((Buffer) intBuffer3, 1);
            Checks.checkSafe((Buffer) intBuffer4, 1);
        }
        return MemoryUtil.memByteBufferSafe(nstbtt_GetGlyphBitmapSubpixel(sTBTTFontinfo.address(), f, f2, f3, f4, i, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddressSafe(intBuffer3), MemoryUtil.memAddressSafe(intBuffer4)), intBuffer.get(intBuffer.position()) * intBuffer2.get(intBuffer2.position()));
    }

    public static void stbtt_MakeGlyphBitmap(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, @NativeType("unsigned char *") ByteBuffer byteBuffer, int i, int i2, int i3, float f, float f2, int i4) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, (i3 != 0 ? i3 : i) * i2);
        }
        nstbtt_MakeGlyphBitmap(sTBTTFontinfo.address(), MemoryUtil.memAddress(byteBuffer), i, i2, i3, f, f2, i4);
    }

    public static void stbtt_MakeGlyphBitmapSubpixel(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, @NativeType("unsigned char *") ByteBuffer byteBuffer, int i, int i2, int i3, float f, float f2, float f3, float f4, int i4) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, (i3 != 0 ? i3 : i) * i2);
        }
        nstbtt_MakeGlyphBitmapSubpixel(sTBTTFontinfo.address(), MemoryUtil.memAddress(byteBuffer), i, i2, i3, f, f2, f3, f4, i4);
    }

    public static void stbtt_MakeGlyphBitmapSubpixelPrefilter(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, @NativeType("unsigned char *") ByteBuffer byteBuffer, int i, int i2, int i3, float f, float f2, float f3, float f4, int i4, int i5, @NativeType("float *") FloatBuffer floatBuffer, @NativeType("float *") FloatBuffer floatBuffer2, int i6) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, (i3 != 0 ? i3 : i) * i2);
            Checks.check((Buffer) floatBuffer, 1);
            Checks.check((Buffer) floatBuffer2, 1);
        }
        nstbtt_MakeGlyphBitmapSubpixelPrefilter(sTBTTFontinfo.address(), MemoryUtil.memAddress(byteBuffer), i, i2, i3, f, f2, f3, f4, i4, i5, MemoryUtil.memAddress(floatBuffer), MemoryUtil.memAddress(floatBuffer2), i6);
    }

    public static void stbtt_GetGlyphBitmapBox(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i, float f, float f2, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, @NativeType("int *") IntBuffer intBuffer4) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.checkSafe((Buffer) intBuffer2, 1);
            Checks.checkSafe((Buffer) intBuffer3, 1);
            Checks.checkSafe((Buffer) intBuffer4, 1);
        }
        nstbtt_GetGlyphBitmapBox(sTBTTFontinfo.address(), i, f, f2, MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2), MemoryUtil.memAddressSafe(intBuffer3), MemoryUtil.memAddressSafe(intBuffer4));
    }

    public static void stbtt_GetGlyphBitmapBoxSubpixel(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i, float f, float f2, float f3, float f4, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, @NativeType("int *") IntBuffer intBuffer4) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.checkSafe((Buffer) intBuffer2, 1);
            Checks.checkSafe((Buffer) intBuffer3, 1);
            Checks.checkSafe((Buffer) intBuffer4, 1);
        }
        nstbtt_GetGlyphBitmapBoxSubpixel(sTBTTFontinfo.address(), i, f, f2, f3, f4, MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2), MemoryUtil.memAddressSafe(intBuffer3), MemoryUtil.memAddressSafe(intBuffer4));
    }

    public static void stbtt_Rasterize(@NativeType("stbtt__bitmap *") STBTTBitmap sTBTTBitmap, float f, @NativeType("stbtt_vertex *") STBTTVertex.Buffer buffer, float f2, float f3, float f4, float f5, int i, int i2, @NativeType("int") boolean z) {
        nstbtt_Rasterize(sTBTTBitmap.address(), f, buffer.address(), buffer.remaining(), f2, f3, f4, f5, i, i2, z ? 1 : 0, 0L);
    }

    public static void stbtt_FreeSDF(@NativeType("unsigned char *") ByteBuffer byteBuffer, @NativeType("void *") long j) {
        nstbtt_FreeSDF(MemoryUtil.memAddress(byteBuffer), j);
    }

    public static void stbtt_FreeSDF(@NativeType("unsigned char *") ByteBuffer byteBuffer) {
        nstbtt_FreeSDF(MemoryUtil.memAddress(byteBuffer), 0L);
    }

    @NativeType("unsigned char *")
    public static ByteBuffer stbtt_GetGlyphSDF(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, float f, int i, int i2, @NativeType("unsigned char") byte b2, float f2, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, @NativeType("int *") IntBuffer intBuffer4) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
            Checks.check((Buffer) intBuffer4, 1);
        }
        return MemoryUtil.memByteBufferSafe(nstbtt_GetGlyphSDF(sTBTTFontinfo.address(), f, i, i2, b2, f2, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), MemoryUtil.memAddress(intBuffer4)), intBuffer.get(intBuffer.position()) * intBuffer2.get(intBuffer2.position()));
    }

    @NativeType("unsigned char *")
    public static ByteBuffer stbtt_GetCodepointSDF(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, float f, int i, int i2, @NativeType("unsigned char") byte b2, float f2, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, @NativeType("int *") IntBuffer intBuffer4) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
            Checks.check((Buffer) intBuffer4, 1);
        }
        return MemoryUtil.memByteBufferSafe(nstbtt_GetCodepointSDF(sTBTTFontinfo.address(), f, i, i2, b2, f2, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), MemoryUtil.memAddress(intBuffer4)), intBuffer.get(intBuffer.position()) * intBuffer2.get(intBuffer2.position()));
    }

    public static int stbtt_FindMatchingFont(@NativeType("unsigned char const *") ByteBuffer byteBuffer, @NativeType("char const *") ByteBuffer byteBuffer2, int i) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer2);
        }
        return nstbtt_FindMatchingFont(MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(byteBuffer2), i);
    }

    public static int stbtt_FindMatchingFont(@NativeType("unsigned char const *") ByteBuffer byteBuffer, @NativeType("char const *") CharSequence charSequence, int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nstbtt_FindMatchingFont(MemoryUtil.memAddress(byteBuffer), stackGet.getPointerAddress(), i);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("int")
    public static boolean stbtt_CompareUTF8toUTF16_bigendian(@NativeType("char const *") ByteBuffer byteBuffer, @NativeType("char const *") ByteBuffer byteBuffer2) {
        return nstbtt_CompareUTF8toUTF16_bigendian(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer2), byteBuffer2.remaining()) != 0;
    }

    @NativeType("char const *")
    public static ByteBuffer stbtt_GetFontNameString(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i, int i2, int i3, int i4) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return MemoryUtil.memByteBufferSafe(nstbtt_GetFontNameString(sTBTTFontinfo.address(), MemoryUtil.memAddress(callocInt), i, i2, i3, i4), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void stbtt_GetBakedQuad(@NativeType("stbtt_bakedchar const *") STBTTBakedChar.Buffer buffer, int i, int i2, int i3, @NativeType("float *") float[] fArr, @NativeType("float *") float[] fArr2, @NativeType("stbtt_aligned_quad *") STBTTAlignedQuad sTBTTAlignedQuad, @NativeType("int") boolean z) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) buffer, i3 + 1);
            Checks.check(fArr, 1);
            Checks.check(fArr2, 1);
        }
        nstbtt_GetBakedQuad(buffer.address(), i, i2, i3, fArr, fArr2, sTBTTAlignedQuad.address(), z ? 1 : 0);
    }

    public static void stbtt_GetScaledFontVMetrics(@NativeType("unsigned char const *") ByteBuffer byteBuffer, int i, float f, @NativeType("float *") float[] fArr, @NativeType("float *") float[] fArr2, @NativeType("float *") float[] fArr3) {
        if (Checks.CHECKS) {
            Checks.check(fArr, 1);
            Checks.check(fArr2, 1);
            Checks.check(fArr3, 1);
        }
        nstbtt_GetScaledFontVMetrics(MemoryUtil.memAddress(byteBuffer), i, f, fArr, fArr2, fArr3);
    }

    public static void stbtt_GetPackedQuad(@NativeType("stbtt_packedchar const *") STBTTPackedchar.Buffer buffer, int i, int i2, int i3, @NativeType("float *") float[] fArr, @NativeType("float *") float[] fArr2, @NativeType("stbtt_aligned_quad *") STBTTAlignedQuad sTBTTAlignedQuad, @NativeType("int") boolean z) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) buffer, i3 + 1);
            Checks.check(fArr, 1);
            Checks.check(fArr2, 1);
        }
        nstbtt_GetPackedQuad(buffer.address(), i, i2, i3, fArr, fArr2, sTBTTAlignedQuad.address(), z ? 1 : 0);
    }

    public static void stbtt_GetFontVMetrics(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3) {
        if (Checks.CHECKS) {
            Checks.checkSafe(iArr, 1);
            Checks.checkSafe(iArr2, 1);
            Checks.checkSafe(iArr3, 1);
        }
        nstbtt_GetFontVMetrics(sTBTTFontinfo.address(), iArr, iArr2, iArr3);
    }

    @NativeType("int")
    public static boolean stbtt_GetFontVMetricsOS2(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3) {
        if (Checks.CHECKS) {
            Checks.checkSafe(iArr, 1);
            Checks.checkSafe(iArr2, 1);
            Checks.checkSafe(iArr3, 1);
        }
        return nstbtt_GetFontVMetricsOS2(sTBTTFontinfo.address(), iArr, iArr2, iArr3) != 0;
    }

    public static void stbtt_GetFontBoundingBox(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, @NativeType("int *") int[] iArr4) {
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
            Checks.check(iArr4, 1);
        }
        nstbtt_GetFontBoundingBox(sTBTTFontinfo.address(), iArr, iArr2, iArr3, iArr4);
    }

    public static void stbtt_GetCodepointHMetrics(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2) {
        if (Checks.CHECKS) {
            Checks.checkSafe(iArr, 1);
            Checks.checkSafe(iArr2, 1);
        }
        nstbtt_GetCodepointHMetrics(sTBTTFontinfo.address(), i, iArr, iArr2);
    }

    @NativeType("int")
    public static boolean stbtt_GetCodepointBox(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, @NativeType("int *") int[] iArr4) {
        if (Checks.CHECKS) {
            Checks.checkSafe(iArr, 1);
            Checks.checkSafe(iArr2, 1);
            Checks.checkSafe(iArr3, 1);
            Checks.checkSafe(iArr4, 1);
        }
        return nstbtt_GetCodepointBox(sTBTTFontinfo.address(), i, iArr, iArr2, iArr3, iArr4) != 0;
    }

    public static void stbtt_GetGlyphHMetrics(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2) {
        if (Checks.CHECKS) {
            Checks.checkSafe(iArr, 1);
            Checks.checkSafe(iArr2, 1);
        }
        nstbtt_GetGlyphHMetrics(sTBTTFontinfo.address(), i, iArr, iArr2);
    }

    @NativeType("int")
    public static boolean stbtt_GetGlyphBox(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, @NativeType("int *") int[] iArr4) {
        if (Checks.CHECKS) {
            Checks.checkSafe(iArr, 1);
            Checks.checkSafe(iArr2, 1);
            Checks.checkSafe(iArr3, 1);
            Checks.checkSafe(iArr4, 1);
        }
        return nstbtt_GetGlyphBox(sTBTTFontinfo.address(), i, iArr, iArr2, iArr3, iArr4) != 0;
    }

    @NativeType("unsigned char *")
    public static ByteBuffer stbtt_GetCodepointBitmap(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, float f, float f2, int i, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, @NativeType("int *") int[] iArr4) {
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.checkSafe(iArr3, 1);
            Checks.checkSafe(iArr4, 1);
        }
        return MemoryUtil.memByteBufferSafe(nstbtt_GetCodepointBitmap(sTBTTFontinfo.address(), f, f2, i, iArr, iArr2, iArr3, iArr4), iArr[0] * iArr2[0]);
    }

    @NativeType("unsigned char *")
    public static ByteBuffer stbtt_GetCodepointBitmapSubpixel(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, float f, float f2, float f3, float f4, int i, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, @NativeType("int *") int[] iArr4) {
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.checkSafe(iArr3, 1);
            Checks.checkSafe(iArr4, 1);
        }
        return MemoryUtil.memByteBufferSafe(nstbtt_GetCodepointBitmapSubpixel(sTBTTFontinfo.address(), f, f2, f3, f4, i, iArr, iArr2, iArr3, iArr4), iArr[0] * iArr2[0]);
    }

    public static void stbtt_MakeCodepointBitmapSubpixelPrefilter(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, @NativeType("unsigned char *") ByteBuffer byteBuffer, int i, int i2, int i3, float f, float f2, float f3, float f4, int i4, int i5, @NativeType("float *") float[] fArr, @NativeType("float *") float[] fArr2, int i6) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, (i3 != 0 ? i3 : i) * i2);
            Checks.check(fArr, 1);
            Checks.check(fArr2, 1);
        }
        nstbtt_MakeCodepointBitmapSubpixelPrefilter(sTBTTFontinfo.address(), MemoryUtil.memAddress(byteBuffer), i, i2, i3, f, f2, f3, f4, i4, i5, fArr, fArr2, i6);
    }

    public static void stbtt_GetCodepointBitmapBox(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i, float f, float f2, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, @NativeType("int *") int[] iArr4) {
        if (Checks.CHECKS) {
            Checks.checkSafe(iArr, 1);
            Checks.checkSafe(iArr2, 1);
            Checks.checkSafe(iArr3, 1);
            Checks.checkSafe(iArr4, 1);
        }
        nstbtt_GetCodepointBitmapBox(sTBTTFontinfo.address(), i, f, f2, iArr, iArr2, iArr3, iArr4);
    }

    public static void stbtt_GetCodepointBitmapBoxSubpixel(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i, float f, float f2, float f3, float f4, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, @NativeType("int *") int[] iArr4) {
        if (Checks.CHECKS) {
            Checks.checkSafe(iArr, 1);
            Checks.checkSafe(iArr2, 1);
            Checks.checkSafe(iArr3, 1);
            Checks.checkSafe(iArr4, 1);
        }
        nstbtt_GetCodepointBitmapBoxSubpixel(sTBTTFontinfo.address(), i, f, f2, f3, f4, iArr, iArr2, iArr3, iArr4);
    }

    @NativeType("unsigned char *")
    public static ByteBuffer stbtt_GetGlyphBitmap(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, float f, float f2, int i, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, @NativeType("int *") int[] iArr4) {
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.checkSafe(iArr3, 1);
            Checks.checkSafe(iArr4, 1);
        }
        return MemoryUtil.memByteBufferSafe(nstbtt_GetGlyphBitmap(sTBTTFontinfo.address(), f, f2, i, iArr, iArr2, iArr3, iArr4), iArr[0] * iArr2[0]);
    }

    @NativeType("unsigned char *")
    public static ByteBuffer stbtt_GetGlyphBitmapSubpixel(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, float f, float f2, float f3, float f4, int i, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, @NativeType("int *") int[] iArr4) {
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.checkSafe(iArr3, 1);
            Checks.checkSafe(iArr4, 1);
        }
        return MemoryUtil.memByteBufferSafe(nstbtt_GetGlyphBitmapSubpixel(sTBTTFontinfo.address(), f, f2, f3, f4, i, iArr, iArr2, iArr3, iArr4), iArr[0] * iArr2[0]);
    }

    public static void stbtt_MakeGlyphBitmapSubpixelPrefilter(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, @NativeType("unsigned char *") ByteBuffer byteBuffer, int i, int i2, int i3, float f, float f2, float f3, float f4, int i4, int i5, @NativeType("float *") float[] fArr, @NativeType("float *") float[] fArr2, int i6) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, (i3 != 0 ? i3 : i) * i2);
            Checks.check(fArr, 1);
            Checks.check(fArr2, 1);
        }
        nstbtt_MakeGlyphBitmapSubpixelPrefilter(sTBTTFontinfo.address(), MemoryUtil.memAddress(byteBuffer), i, i2, i3, f, f2, f3, f4, i4, i5, fArr, fArr2, i6);
    }

    public static void stbtt_GetGlyphBitmapBox(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i, float f, float f2, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, @NativeType("int *") int[] iArr4) {
        if (Checks.CHECKS) {
            Checks.checkSafe(iArr, 1);
            Checks.checkSafe(iArr2, 1);
            Checks.checkSafe(iArr3, 1);
            Checks.checkSafe(iArr4, 1);
        }
        nstbtt_GetGlyphBitmapBox(sTBTTFontinfo.address(), i, f, f2, iArr, iArr2, iArr3, iArr4);
    }

    public static void stbtt_GetGlyphBitmapBoxSubpixel(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, int i, float f, float f2, float f3, float f4, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, @NativeType("int *") int[] iArr4) {
        if (Checks.CHECKS) {
            Checks.checkSafe(iArr, 1);
            Checks.checkSafe(iArr2, 1);
            Checks.checkSafe(iArr3, 1);
            Checks.checkSafe(iArr4, 1);
        }
        nstbtt_GetGlyphBitmapBoxSubpixel(sTBTTFontinfo.address(), i, f, f2, f3, f4, iArr, iArr2, iArr3, iArr4);
    }

    @NativeType("unsigned char *")
    public static ByteBuffer stbtt_GetGlyphSDF(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, float f, int i, int i2, @NativeType("unsigned char") byte b2, float f2, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, @NativeType("int *") int[] iArr4) {
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
            Checks.check(iArr4, 1);
        }
        return MemoryUtil.memByteBufferSafe(nstbtt_GetGlyphSDF(sTBTTFontinfo.address(), f, i, i2, b2, f2, iArr, iArr2, iArr3, iArr4), iArr[0] * iArr2[0]);
    }

    @NativeType("unsigned char *")
    public static ByteBuffer stbtt_GetCodepointSDF(@NativeType("stbtt_fontinfo const *") STBTTFontinfo sTBTTFontinfo, float f, int i, int i2, @NativeType("unsigned char") byte b2, float f2, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, @NativeType("int *") int[] iArr4) {
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
            Checks.check(iArr4, 1);
        }
        return MemoryUtil.memByteBufferSafe(nstbtt_GetCodepointSDF(sTBTTFontinfo.address(), f, i, i2, b2, f2, iArr, iArr2, iArr3, iArr4), iArr[0] * iArr2[0]);
    }
}
