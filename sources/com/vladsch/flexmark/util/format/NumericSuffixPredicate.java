package com.vladsch.flexmark.util.format;

import java.util.function.Predicate;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/NumericSuffixPredicate.class */
public interface NumericSuffixPredicate extends Predicate<String> {
    default boolean sortSuffix(String str) {
        return true;
    }
}
