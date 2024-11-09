package com.vladsch.flexmark.util.collection;

import java.util.function.BiFunction;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/BoundedMaxAggregator.class */
public class BoundedMaxAggregator implements BiFunction<Integer, Integer, Integer> {
    public final int maxBound;

    public BoundedMaxAggregator(int i) {
        this.maxBound = i;
    }

    @Override // java.util.function.BiFunction
    public Integer apply(Integer num, Integer num2) {
        return (num2 == null || num2.intValue() >= this.maxBound) ? num : MaxAggregator.INSTANCE.apply(num, num2);
    }
}
