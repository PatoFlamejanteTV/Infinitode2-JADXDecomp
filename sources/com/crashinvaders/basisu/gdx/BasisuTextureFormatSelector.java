package com.crashinvaders.basisu.gdx;

import com.crashinvaders.basisu.wrapper.BasisuFileInfo;
import com.crashinvaders.basisu.wrapper.BasisuImageInfo;
import com.crashinvaders.basisu.wrapper.BasisuTextureFormat;
import com.crashinvaders.basisu.wrapper.BasisuTranscoderTextureFormat;

/* loaded from: infinitode-2.jar:com/crashinvaders/basisu/gdx/BasisuTextureFormatSelector.class */
public interface BasisuTextureFormatSelector {
    BasisuTranscoderTextureFormat resolveTextureFormat(BasisuData basisuData, int i);

    BasisuTranscoderTextureFormat resolveTextureFormat(Ktx2Data ktx2Data);

    /* loaded from: infinitode-2.jar:com/crashinvaders/basisu/gdx/BasisuTextureFormatSelector$Default.class */
    public static class Default implements BasisuTextureFormatSelector {
        @Override // com.crashinvaders.basisu.gdx.BasisuTextureFormatSelector
        public BasisuTranscoderTextureFormat resolveTextureFormat(BasisuData basisuData, int i) {
            BasisuFileInfo fileInfo = basisuData.getFileInfo();
            BasisuImageInfo imageInfo = basisuData.getImageInfo(i);
            return resolveTextureFormat(fileInfo.getTextureFormat(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.hasAlphaFlag());
        }

        @Override // com.crashinvaders.basisu.gdx.BasisuTextureFormatSelector
        public BasisuTranscoderTextureFormat resolveTextureFormat(Ktx2Data ktx2Data) {
            return resolveTextureFormat(ktx2Data.getTextureFormat(), ktx2Data.getImageWidth(), ktx2Data.getImageHeight(), ktx2Data.hasAlpha());
        }

        private BasisuTranscoderTextureFormat resolveTextureFormat(BasisuTextureFormat basisuTextureFormat, int i, int i2, boolean z) {
            switch (basisuTextureFormat) {
                case ETC1S:
                    return resolveForEtc1s(i, i2, z);
                case UASTC4x4:
                    return resolveForUastc(i, i2, z);
                default:
                    throw new BasisuGdxException("Unexpected texture format: " + basisuTextureFormat);
            }
        }

        private static BasisuTranscoderTextureFormat resolveForEtc1s(int i, int i2, boolean z) {
            BasisuTextureFormat basisuTextureFormat = BasisuTextureFormat.ETC1S;
            if (z) {
                if (BasisuGdxUtils.isBasisuFormatSupported(BasisuTranscoderTextureFormat.ETC2_RGBA, basisuTextureFormat)) {
                    return BasisuTranscoderTextureFormat.ETC2_RGBA;
                }
                if (BasisuGdxUtils.isBasisuFormatSupported(BasisuTranscoderTextureFormat.BC7_RGBA, basisuTextureFormat)) {
                    return BasisuTranscoderTextureFormat.BC7_RGBA;
                }
                if (BasisuGdxUtils.isBasisuFormatSupported(BasisuTranscoderTextureFormat.BC3_RGBA, basisuTextureFormat) && BasisuGdxUtils.isMultipleOfFour(i, i2)) {
                    return BasisuTranscoderTextureFormat.BC3_RGBA;
                }
                if (BasisuGdxUtils.isBasisuFormatSupported(BasisuTranscoderTextureFormat.ASTC_4x4_RGBA, basisuTextureFormat)) {
                    return BasisuTranscoderTextureFormat.ASTC_4x4_RGBA;
                }
                if (BasisuGdxUtils.isBasisuFormatSupported(BasisuTranscoderTextureFormat.ATC_RGBA, basisuTextureFormat)) {
                    return BasisuTranscoderTextureFormat.ATC_RGBA;
                }
                if (BasisuGdxUtils.isBasisuFormatSupported(BasisuTranscoderTextureFormat.PVRTC1_4_RGBA, basisuTextureFormat) && BasisuGdxUtils.isSquareAndPowerOfTwo(i, i2)) {
                    return BasisuTranscoderTextureFormat.PVRTC1_4_RGBA;
                }
                if (BasisuGdxUtils.isBasisuFormatSupported(BasisuTranscoderTextureFormat.PVRTC2_4_RGBA, basisuTextureFormat)) {
                    return BasisuTranscoderTextureFormat.PVRTC2_4_RGBA;
                }
                return BasisuTranscoderTextureFormat.RGBA32;
            }
            if (BasisuGdxUtils.isBasisuFormatSupported(BasisuTranscoderTextureFormat.ETC1_RGB, basisuTextureFormat)) {
                return BasisuTranscoderTextureFormat.ETC1_RGB;
            }
            if (BasisuGdxUtils.isBasisuFormatSupported(BasisuTranscoderTextureFormat.BC1_RGB, basisuTextureFormat) && BasisuGdxUtils.isMultipleOfFour(i, i2)) {
                return BasisuTranscoderTextureFormat.BC1_RGB;
            }
            if (BasisuGdxUtils.isBasisuFormatSupported(BasisuTranscoderTextureFormat.ATC_RGB, basisuTextureFormat)) {
                return BasisuTranscoderTextureFormat.ATC_RGB;
            }
            if (BasisuGdxUtils.isBasisuFormatSupported(BasisuTranscoderTextureFormat.PVRTC2_4_RGB, basisuTextureFormat)) {
                return BasisuTranscoderTextureFormat.PVRTC2_4_RGB;
            }
            if (BasisuGdxUtils.isBasisuFormatSupported(BasisuTranscoderTextureFormat.PVRTC1_4_RGB, basisuTextureFormat) && BasisuGdxUtils.isSquareAndPowerOfTwo(i, i2)) {
                return BasisuTranscoderTextureFormat.PVRTC1_4_RGB;
            }
            return BasisuTranscoderTextureFormat.RGB565;
        }

        private BasisuTranscoderTextureFormat resolveForUastc(int i, int i2, boolean z) {
            BasisuTextureFormat basisuTextureFormat = BasisuTextureFormat.UASTC4x4;
            if (z) {
                if (BasisuGdxUtils.isBasisuFormatSupported(BasisuTranscoderTextureFormat.ASTC_4x4_RGBA, basisuTextureFormat)) {
                    return BasisuTranscoderTextureFormat.ASTC_4x4_RGBA;
                }
                if (BasisuGdxUtils.isBasisuFormatSupported(BasisuTranscoderTextureFormat.BC7_RGBA, basisuTextureFormat)) {
                    return BasisuTranscoderTextureFormat.BC7_RGBA;
                }
                if (BasisuGdxUtils.isBasisuFormatSupported(BasisuTranscoderTextureFormat.BC3_RGBA, basisuTextureFormat) && BasisuGdxUtils.isMultipleOfFour(i, i2)) {
                    return BasisuTranscoderTextureFormat.BC3_RGBA;
                }
                if (BasisuGdxUtils.isBasisuFormatSupported(BasisuTranscoderTextureFormat.ETC2_RGBA, basisuTextureFormat)) {
                    return BasisuTranscoderTextureFormat.ETC2_RGBA;
                }
                if (BasisuGdxUtils.isBasisuFormatSupported(BasisuTranscoderTextureFormat.ATC_RGBA, basisuTextureFormat)) {
                    return BasisuTranscoderTextureFormat.ATC_RGBA;
                }
                if (BasisuGdxUtils.isBasisuFormatSupported(BasisuTranscoderTextureFormat.PVRTC1_4_RGBA, basisuTextureFormat) && BasisuGdxUtils.isSquareAndPowerOfTwo(i, i2)) {
                    return BasisuTranscoderTextureFormat.PVRTC1_4_RGBA;
                }
                if (BasisuGdxUtils.isBasisuFormatSupported(BasisuTranscoderTextureFormat.PVRTC2_4_RGBA, basisuTextureFormat)) {
                    return BasisuTranscoderTextureFormat.PVRTC2_4_RGBA;
                }
                return BasisuTranscoderTextureFormat.RGBA32;
            }
            if (BasisuGdxUtils.isBasisuFormatSupported(BasisuTranscoderTextureFormat.BC1_RGB, basisuTextureFormat) && BasisuGdxUtils.isMultipleOfFour(i, i2)) {
                return BasisuTranscoderTextureFormat.BC1_RGB;
            }
            if (BasisuGdxUtils.isBasisuFormatSupported(BasisuTranscoderTextureFormat.ETC1_RGB, basisuTextureFormat)) {
                return BasisuTranscoderTextureFormat.ETC1_RGB;
            }
            if (BasisuGdxUtils.isBasisuFormatSupported(BasisuTranscoderTextureFormat.ATC_RGB, basisuTextureFormat)) {
                return BasisuTranscoderTextureFormat.ATC_RGB;
            }
            if (BasisuGdxUtils.isBasisuFormatSupported(BasisuTranscoderTextureFormat.PVRTC2_4_RGB, basisuTextureFormat)) {
                return BasisuTranscoderTextureFormat.PVRTC2_4_RGB;
            }
            if (BasisuGdxUtils.isBasisuFormatSupported(BasisuTranscoderTextureFormat.PVRTC1_4_RGB, basisuTextureFormat) && BasisuGdxUtils.isSquareAndPowerOfTwo(i, i2)) {
                return BasisuTranscoderTextureFormat.PVRTC1_4_RGB;
            }
            return BasisuTranscoderTextureFormat.RGB565;
        }
    }

    /* loaded from: infinitode-2.jar:com/crashinvaders/basisu/gdx/BasisuTextureFormatSelector$Fixed.class */
    public static class Fixed implements BasisuTextureFormatSelector {
        private final BasisuTranscoderTextureFormat format;

        public Fixed(BasisuTranscoderTextureFormat basisuTranscoderTextureFormat) {
            this.format = basisuTranscoderTextureFormat;
        }

        @Override // com.crashinvaders.basisu.gdx.BasisuTextureFormatSelector
        public BasisuTranscoderTextureFormat resolveTextureFormat(BasisuData basisuData, int i) {
            return this.format;
        }

        @Override // com.crashinvaders.basisu.gdx.BasisuTextureFormatSelector
        public BasisuTranscoderTextureFormat resolveTextureFormat(Ktx2Data ktx2Data) {
            return this.format;
        }
    }
}
