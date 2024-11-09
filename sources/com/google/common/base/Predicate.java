package com.google.common.base;

@ElementTypesAreNonnullByDefault
/* loaded from: infinitode-2.jar:com/google/common/base/Predicate.class */
public interface Predicate<T> {
    boolean apply(@ParametricNullness T t);

    boolean equals(Object obj);
}
