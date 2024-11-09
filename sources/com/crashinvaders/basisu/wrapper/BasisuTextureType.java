package com.crashinvaders.basisu.wrapper;

import com.crashinvaders.basisu.wrapper.UniqueIdUtils;

/* loaded from: infinitode-2.jar:com/crashinvaders/basisu/wrapper/BasisuTextureType.class */
public enum BasisuTextureType implements UniqueIdUtils.UniqueIdValue {
    REGULAR_2D(0),
    REGULAR_2D_ARRAY(1),
    CUBEMAP_ARRAY(2),
    VIDEO_FRAMES(3),
    VOLUME(4);

    private final int id;

    BasisuTextureType(int i) {
        this.id = i;
    }

    @Override // com.crashinvaders.basisu.wrapper.UniqueIdUtils.UniqueIdValue
    public final int getId() {
        return this.id;
    }
}
