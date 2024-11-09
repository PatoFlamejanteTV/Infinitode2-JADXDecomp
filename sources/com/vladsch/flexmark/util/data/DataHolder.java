package com.vladsch.flexmark.util.data;

import java.util.Collection;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/data/DataHolder.class */
public interface DataHolder extends MutableDataSetter {
    public static final DataHolder NULL = new DataSet();

    Map<? extends DataKeyBase<?>, Object> getAll();

    Collection<? extends DataKeyBase<?>> getKeys();

    boolean contains(DataKeyBase<?> dataKeyBase);

    Object getOrCompute(DataKeyBase<?> dataKeyBase, DataValueFactory<?> dataValueFactory);

    MutableDataHolder toMutable();

    DataHolder toImmutable();

    @Deprecated
    default <T> T get(DataKey<T> dataKey) {
        return dataKey.get(this);
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataSetter
    default MutableDataHolder setIn(MutableDataHolder mutableDataHolder) {
        return mutableDataHolder.setAll(this);
    }

    default DataSet toDataSet() {
        return this instanceof DataSet ? (DataSet) this : this instanceof MutableDataHolder ? new MutableDataSet(this) : new DataSet(this);
    }
}
