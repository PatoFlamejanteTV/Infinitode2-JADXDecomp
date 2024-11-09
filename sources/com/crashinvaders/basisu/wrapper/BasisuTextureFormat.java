package com.crashinvaders.basisu.wrapper;

import com.crashinvaders.basisu.wrapper.UniqueIdUtils;

/* loaded from: infinitode-2.jar:com/crashinvaders/basisu/wrapper/BasisuTextureFormat.class */
public enum BasisuTextureFormat implements UniqueIdUtils.UniqueIdValue {
    ETC1S(0),
    UASTC4x4(1);

    private final int id;

    BasisuTextureFormat(int i) {
        this.id = i;
    }

    @Override // com.crashinvaders.basisu.wrapper.UniqueIdUtils.UniqueIdValue
    public final int getId() {
        return this.id;
    }
}
