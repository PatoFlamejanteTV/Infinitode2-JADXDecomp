package com.google.common.base;

import java.util.Arrays;

@ElementTypesAreNonnullByDefault
/* loaded from: infinitode-2.jar:com/google/common/base/Objects.class */
public final class Objects extends ExtraObjectsMethodsForWeb {
    private Objects() {
    }

    public static boolean equal(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static int hashCode(Object... objArr) {
        return Arrays.hashCode(objArr);
    }
}
