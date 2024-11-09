package com.vladsch.flexmark.util.sequence.builder;

import java.util.Arrays;
import java.util.function.BiFunction;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/SegmentOptimizer.class */
public interface SegmentOptimizer extends BiFunction<CharSequence, Object[], Object[]> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.function.BiFunction
    Object[] apply(CharSequence charSequence, Object[] objArr);

    static Object[] insert(Object[] objArr, int i) {
        if (i < objArr.length) {
            Object[] objArr2 = new Object[objArr.length + 1];
            if (i == 0) {
                System.arraycopy(objArr, 0, objArr2, 1, objArr.length);
            } else {
                System.arraycopy(objArr, 0, objArr2, 0, i);
                System.arraycopy(objArr, i, objArr2, i + 1, objArr.length - i);
            }
            return objArr2;
        }
        return Arrays.copyOf(objArr, objArr.length + 1);
    }
}
