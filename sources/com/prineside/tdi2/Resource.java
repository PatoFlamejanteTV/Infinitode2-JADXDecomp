package com.prineside.tdi2;

import com.prineside.tdi2.enums.ResourceType;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/Resource.class */
public class Resource {
    public static final String[] NAMES;
    public static final String[] TEXTURE_REGION_NAMES;

    static {
        String[] strArr = new String[ResourceType.values.length];
        NAMES = strArr;
        strArr[ResourceType.SCALAR.ordinal()] = "Scalar";
        NAMES[ResourceType.VECTOR.ordinal()] = "Vector";
        NAMES[ResourceType.MATRIX.ordinal()] = "Matrix";
        NAMES[ResourceType.TENSOR.ordinal()] = "Tensor";
        NAMES[ResourceType.INFIAR.ordinal()] = "Infiar";
        String[] strArr2 = new String[ResourceType.values.length];
        TEXTURE_REGION_NAMES = strArr2;
        strArr2[ResourceType.SCALAR.ordinal()] = "resource-scalar";
        TEXTURE_REGION_NAMES[ResourceType.VECTOR.ordinal()] = "resource-vector";
        TEXTURE_REGION_NAMES[ResourceType.MATRIX.ordinal()] = "resource-matrix";
        TEXTURE_REGION_NAMES[ResourceType.TENSOR.ordinal()] = "resource-tensor";
        TEXTURE_REGION_NAMES[ResourceType.INFIAR.ordinal()] = "resource-infiar";
    }
}
