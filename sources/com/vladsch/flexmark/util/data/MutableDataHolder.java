package com.vladsch.flexmark.util.data;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/data/MutableDataHolder.class */
public interface MutableDataHolder extends DataHolder, MutableDataSetter {
    @Override // com.vladsch.flexmark.util.data.DataHolder
    Object getOrCompute(DataKeyBase<?> dataKeyBase, DataValueFactory<?> dataValueFactory);

    <T> MutableDataHolder set(DataKey<T> dataKey, T t);

    <T> MutableDataHolder set(NullableDataKey<T> nullableDataKey, T t);

    MutableDataHolder remove(DataKeyBase<?> dataKeyBase);

    MutableDataHolder setFrom(MutableDataSetter mutableDataSetter);

    MutableDataHolder setAll(DataHolder dataHolder);

    @Override // com.vladsch.flexmark.util.data.DataHolder, com.vladsch.flexmark.util.data.MutableDataSetter
    MutableDataHolder setIn(MutableDataHolder mutableDataHolder);

    MutableDataHolder clear();

    @Deprecated
    default <T> T get(DataKey<T> dataKey) {
        return dataKey.get(this);
    }
}
