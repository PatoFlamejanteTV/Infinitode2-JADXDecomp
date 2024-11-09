package com.prineside.tdi2.utils;

import java.util.Objects;

@FunctionalInterface
/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/IntObjectConsumer.class */
public interface IntObjectConsumer<T> {
    void accept(int i, T t);

    default IntObjectConsumer<T> andThen(IntObjectConsumer<? super T> intObjectConsumer) {
        Objects.requireNonNull(intObjectConsumer);
        return (i, obj) -> {
            accept(i, obj);
            intObjectConsumer.accept(i, obj);
        };
    }
}
