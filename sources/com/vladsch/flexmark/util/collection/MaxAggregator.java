package com.vladsch.flexmark.util.collection;

import java.util.function.BiFunction;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/MaxAggregator.class */
public class MaxAggregator implements BiFunction<Integer, Integer, Integer> {
    public static final MaxAggregator INSTANCE = new MaxAggregator();

    private MaxAggregator() {
    }

    @Override // java.util.function.BiFunction
    public Integer apply(Integer num, Integer num2) {
        return (num2 == null || (num != null && num.intValue() >= num2.intValue())) ? num : num2;
    }
}
