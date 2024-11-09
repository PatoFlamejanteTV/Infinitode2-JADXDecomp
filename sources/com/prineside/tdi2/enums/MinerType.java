package com.prineside.tdi2.enums;

import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enums/MinerType.class */
public enum MinerType {
    SCALAR,
    VECTOR,
    MATRIX,
    TENSOR,
    INFIAR;

    public static final ResourceType[] toResourceType;
    public static final MinerType[] values;

    static {
        ResourceType[] resourceTypeArr = new ResourceType[5];
        toResourceType = resourceTypeArr;
        resourceTypeArr[SCALAR.ordinal()] = ResourceType.SCALAR;
        toResourceType[VECTOR.ordinal()] = ResourceType.VECTOR;
        toResourceType[MATRIX.ordinal()] = ResourceType.MATRIX;
        toResourceType[TENSOR.ordinal()] = ResourceType.TENSOR;
        toResourceType[INFIAR.ordinal()] = ResourceType.INFIAR;
        values = values();
    }
}
