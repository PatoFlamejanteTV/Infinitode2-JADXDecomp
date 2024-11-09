package com.vladsch.flexmark.util.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/data/ScopedDataSet.class */
public class ScopedDataSet extends DataSet {
    protected final DataHolder parent;

    public ScopedDataSet(DataHolder dataHolder) {
        this.parent = dataHolder;
    }

    public ScopedDataSet(DataHolder dataHolder, DataHolder dataHolder2) {
        super(dataHolder2);
        this.parent = dataHolder;
    }

    public DataHolder getParent() {
        return this.parent;
    }

    @Override // com.vladsch.flexmark.util.data.DataSet, com.vladsch.flexmark.util.data.DataHolder
    public Map<? extends DataKeyBase<?>, Object> getAll() {
        if (this.parent != null) {
            HashMap hashMap = new HashMap(this.parent.getAll());
            hashMap.putAll(super.getAll());
            return hashMap;
        }
        return super.getAll();
    }

    @Override // com.vladsch.flexmark.util.data.DataSet, com.vladsch.flexmark.util.data.DataHolder
    public Collection<? extends DataKeyBase<?>> getKeys() {
        if (this.parent != null) {
            HashSet hashSet = new HashSet(this.parent.getKeys());
            hashSet.addAll(super.getKeys());
            return hashSet;
        }
        return super.getKeys();
    }

    @Override // com.vladsch.flexmark.util.data.DataSet, com.vladsch.flexmark.util.data.DataHolder
    public MutableDataSet toMutable() {
        MutableDataSet mutableDataSet = new MutableDataSet();
        mutableDataSet.dataSet.putAll(super.getAll());
        return this.parent != null ? new MutableScopedDataSet(this.parent, mutableDataSet) : mutableDataSet;
    }

    @Override // com.vladsch.flexmark.util.data.DataSet, com.vladsch.flexmark.util.data.DataHolder
    public boolean contains(DataKeyBase<?> dataKeyBase) {
        if (super.contains(dataKeyBase)) {
            return true;
        }
        return this.parent != null && this.parent.contains(dataKeyBase);
    }

    @Override // com.vladsch.flexmark.util.data.DataSet, com.vladsch.flexmark.util.data.DataHolder
    public Object getOrCompute(DataKeyBase<?> dataKeyBase, DataValueFactory<?> dataValueFactory) {
        if (this.parent == null || super.contains(dataKeyBase) || !this.parent.contains(dataKeyBase)) {
            return super.getOrCompute(dataKeyBase, dataValueFactory);
        }
        return this.parent.getOrCompute(dataKeyBase, dataValueFactory);
    }
}
