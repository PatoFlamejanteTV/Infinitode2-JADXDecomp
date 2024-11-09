package com.vladsch.flexmark.util.data;

import java.util.function.Supplier;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/data/NotNullValueSupplier.class */
public interface NotNullValueSupplier<T> extends Supplier<T> {
    @Override // java.util.function.Supplier
    T get();
}
