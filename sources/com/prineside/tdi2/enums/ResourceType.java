package com.prineside.tdi2.enums;

import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enums/ResourceType.class */
public enum ResourceType {
    SCALAR,
    VECTOR,
    MATRIX,
    TENSOR,
    INFIAR;

    public static final MinerType[] toMinerType;
    public static final ResourceType[] values;

    static {
        MinerType[] minerTypeArr = new MinerType[5];
        toMinerType = minerTypeArr;
        minerTypeArr[SCALAR.ordinal()] = MinerType.SCALAR;
        toMinerType[VECTOR.ordinal()] = MinerType.VECTOR;
        toMinerType[MATRIX.ordinal()] = MinerType.MATRIX;
        toMinerType[TENSOR.ordinal()] = MinerType.TENSOR;
        toMinerType[INFIAR.ordinal()] = MinerType.INFIAR;
        values = values();
    }
}
