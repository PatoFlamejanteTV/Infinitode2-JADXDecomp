package com.vladsch.flexmark.util.data;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/data/DataValueNullableFactory.class */
public interface DataValueNullableFactory<T> extends DataValueFactory<T> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.data.DataValueFactory, java.util.function.Function
    T apply(DataHolder dataHolder);
}
