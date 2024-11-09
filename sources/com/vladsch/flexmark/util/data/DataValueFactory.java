package com.vladsch.flexmark.util.data;

import java.util.function.Function;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/data/DataValueFactory.class */
public interface DataValueFactory<T> extends Function<DataHolder, T> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.function.Function
    T apply(DataHolder dataHolder);
}
