package com.crashinvaders.basisu.wrapper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/crashinvaders/basisu/wrapper/BasisuTranscoderTextureFormatSupportIndex.class */
public class BasisuTranscoderTextureFormatSupportIndex {
    static final HashMap<BasisuTextureFormat, Set<BasisuTranscoderTextureFormat>> supportMap = new HashMap<>();

    public static synchronized boolean isTextureFormatSupported(BasisuTranscoderTextureFormat basisuTranscoderTextureFormat, BasisuTextureFormat basisuTextureFormat) {
        return getSupportedTextureFormats(basisuTextureFormat).contains(basisuTranscoderTextureFormat);
    }

    public static synchronized Set<BasisuTranscoderTextureFormat> getSupportedTextureFormats(BasisuTextureFormat basisuTextureFormat) {
        Set<BasisuTranscoderTextureFormat> set = supportMap.get(basisuTextureFormat);
        Set<BasisuTranscoderTextureFormat> set2 = set;
        if (set == null) {
            set2 = new HashSet();
            collectSupportedTextureFormats(basisuTextureFormat, set2);
            supportMap.put(basisuTextureFormat, set2);
        }
        return set2;
    }

    private static void collectSupportedTextureFormats(BasisuTextureFormat basisuTextureFormat, Set<BasisuTranscoderTextureFormat> set) {
        for (BasisuTranscoderTextureFormat basisuTranscoderTextureFormat : BasisuTranscoderTextureFormat.values()) {
            if (BasisuWrapper.isTranscoderTexFormatSupported(basisuTranscoderTextureFormat, basisuTextureFormat)) {
                set.add(basisuTranscoderTextureFormat);
            }
        }
    }
}
