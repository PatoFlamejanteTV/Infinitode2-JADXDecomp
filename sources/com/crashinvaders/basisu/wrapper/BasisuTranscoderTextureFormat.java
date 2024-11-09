package com.crashinvaders.basisu.wrapper;

import com.crashinvaders.basisu.wrapper.UniqueIdUtils;

/* loaded from: infinitode-2.jar:com/crashinvaders/basisu/wrapper/BasisuTranscoderTextureFormat.class */
public enum BasisuTranscoderTextureFormat implements UniqueIdUtils.UniqueIdValue {
    ETC1_RGB(0),
    ETC2_RGBA(1),
    ETC2_EAC_R11(20),
    ETC2_EAC_RG11(21),
    BC1_RGB(2),
    BC3_RGBA(3),
    BC4_R(4),
    BC5_RG(5),
    BC7_RGBA(6),
    PVRTC1_4_RGB(8),
    PVRTC1_4_RGBA(9),
    PVRTC2_4_RGB(18),
    PVRTC2_4_RGBA(19),
    ASTC_4x4_RGBA(10),
    ATC_RGB(11),
    ATC_RGBA(12),
    RGBA32(13),
    RGB565(14),
    RGBA4444(16);

    private final int id;

    BasisuTranscoderTextureFormat(int i) {
        this.id = i;
    }

    @Override // com.crashinvaders.basisu.wrapper.UniqueIdUtils.UniqueIdValue
    public final int getId() {
        return this.id;
    }

    public final boolean isCompressedFormat() {
        return (this == RGBA32 || this == RGB565 || this == RGBA4444) ? false : true;
    }
}
