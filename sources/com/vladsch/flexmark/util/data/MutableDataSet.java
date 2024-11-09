package com.vladsch.flexmark.util.data;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/data/MutableDataSet.class */
public class MutableDataSet extends DataSet implements MutableDataHolder {
    @Override // com.vladsch.flexmark.util.data.MutableDataHolder
    public /* bridge */ /* synthetic */ MutableDataHolder remove(DataKeyBase dataKeyBase) {
        return remove((DataKeyBase<?>) dataKeyBase);
    }

    public /* bridge */ /* synthetic */ MutableDataHolder set(NullableDataKey nullableDataKey, Object obj) {
        return set((NullableDataKey<NullableDataKey>) nullableDataKey, (NullableDataKey) obj);
    }

    public /* bridge */ /* synthetic */ MutableDataHolder set(DataKey dataKey, Object obj) {
        return set((DataKey<DataKey>) dataKey, (DataKey) obj);
    }

    public MutableDataSet() {
    }

    public MutableDataSet(DataHolder dataHolder) {
        super(dataHolder);
    }

    public <T> MutableDataSet set(DataKey<T> dataKey, T t) {
        return set((DataKeyBase<DataKey<T>>) dataKey, (DataKey<T>) t);
    }

    public <T> MutableDataSet set(NullableDataKey<T> nullableDataKey, T t) {
        return set((DataKeyBase<NullableDataKey<T>>) nullableDataKey, (NullableDataKey<T>) t);
    }

    private <T> MutableDataSet set(DataKeyBase<T> dataKeyBase, T t) {
        this.dataSet.put(dataKeyBase, t);
        return this;
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataHolder
    public MutableDataSet setFrom(MutableDataSetter mutableDataSetter) {
        mutableDataSetter.setIn(this);
        return this;
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataHolder
    public MutableDataSet setAll(DataHolder dataHolder) {
        this.dataSet.putAll(dataHolder.getAll());
        return this;
    }

    public static MutableDataSet merge(DataHolder... dataHolderArr) {
        MutableDataSet mutableDataSet = new MutableDataSet();
        for (DataHolder dataHolder : dataHolderArr) {
            if (dataHolder != null) {
                mutableDataSet.dataSet.putAll(dataHolder.getAll());
            }
        }
        return mutableDataSet;
    }

    @Override // com.vladsch.flexmark.util.data.DataHolder, com.vladsch.flexmark.util.data.MutableDataSetter
    public MutableDataHolder setIn(MutableDataHolder mutableDataHolder) {
        mutableDataHolder.setAll(this);
        return mutableDataHolder;
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataHolder
    public MutableDataSet remove(DataKeyBase<?> dataKeyBase) {
        this.dataSet.remove(dataKeyBase);
        return this;
    }

    @Override // com.vladsch.flexmark.util.data.DataSet, com.vladsch.flexmark.util.data.DataHolder
    public Object getOrCompute(DataKeyBase<?> dataKeyBase, DataValueFactory<?> dataValueFactory) {
        if (this.dataSet.containsKey(dataKeyBase)) {
            return this.dataSet.get(dataKeyBase);
        }
        Object apply = dataValueFactory.apply((DataHolder) this);
        this.dataSet.put(dataKeyBase, apply);
        return apply;
    }

    @Override // com.vladsch.flexmark.util.data.DataSet, com.vladsch.flexmark.util.data.DataHolder
    public MutableDataSet toMutable() {
        return this;
    }

    @Override // com.vladsch.flexmark.util.data.DataSet, com.vladsch.flexmark.util.data.DataHolder
    public DataSet toImmutable() {
        return new DataSet(this);
    }

    @Override // com.vladsch.flexmark.util.data.DataSet, com.vladsch.flexmark.util.data.DataHolder
    public MutableDataSet toDataSet() {
        return this;
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataHolder
    public MutableDataSet clear() {
        this.dataSet.clear();
        return this;
    }
}
