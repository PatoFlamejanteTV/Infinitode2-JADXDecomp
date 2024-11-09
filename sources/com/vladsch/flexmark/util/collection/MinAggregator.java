package com.vladsch.flexmark.util.collection;

import java.util.function.BiFunction;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/MinAggregator.class */
public class MinAggregator implements BiFunction<Integer, Integer, Integer> {
    public static final MinAggregator INSTANCE = new MinAggregator();

    private MinAggregator() {
    }

    @Override // java.util.function.BiFunction
    public Integer apply(Integer num, Integer num2) {
        return (num2 == null || (num != null && num.intValue() <= num2.intValue())) ? num : num2;
    }
}
