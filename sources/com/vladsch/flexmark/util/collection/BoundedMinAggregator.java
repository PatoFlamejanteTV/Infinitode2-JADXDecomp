package com.vladsch.flexmark.util.collection;

import java.util.function.BiFunction;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/BoundedMinAggregator.class */
public class BoundedMinAggregator implements BiFunction<Integer, Integer, Integer> {
    public final int minBound;

    public BoundedMinAggregator(int i) {
        this.minBound = i;
    }

    @Override // java.util.function.BiFunction
    public Integer apply(Integer num, Integer num2) {
        return (num2 == null || num2.intValue() <= this.minBound) ? num : MinAggregator.INSTANCE.apply(num, num2);
    }
}
