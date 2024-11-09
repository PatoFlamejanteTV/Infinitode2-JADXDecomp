package com.vladsch.flexmark.util.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/data/MutableScopedDataSet.class */
public class MutableScopedDataSet extends MutableDataSet {
    protected final DataHolder parent;

    public MutableScopedDataSet(DataHolder dataHolder) {
        this.parent = dataHolder;
    }

    public MutableScopedDataSet(DataHolder dataHolder, MutableDataHolder mutableDataHolder) {
        super(mutableDataHolder);
        this.parent = dataHolder;
    }

    public DataHolder getParent() {
        return this.parent;
    }

    @Override // com.vladsch.flexmark.util.data.DataSet, com.vladsch.flexmark.util.data.DataHolder
    public Map<? extends DataKeyBase<?>, Object> getAll() {
        if (this.parent != null) {
            HashMap hashMap = new HashMap(super.getAll());
            for (DataKeyBase<?> dataKeyBase : this.parent.getKeys()) {
                if (!contains(dataKeyBase)) {
                    hashMap.put(dataKeyBase, dataKeyBase.get(this.parent));
                }
            }
            return hashMap;
        }
        return super.getAll();
    }

    @Override // com.vladsch.flexmark.util.data.DataSet, com.vladsch.flexmark.util.data.DataHolder
    public Collection<? extends DataKeyBase<?>> getKeys() {
        if (this.parent != null) {
            ArrayList arrayList = new ArrayList(super.getKeys());
            for (DataKeyBase<?> dataKeyBase : this.parent.getKeys()) {
                if (!contains(dataKeyBase)) {
                    arrayList.add(dataKeyBase);
                }
            }
            return arrayList;
        }
        return super.getKeys();
    }

    @Override // com.vladsch.flexmark.util.data.DataSet, com.vladsch.flexmark.util.data.DataHolder
    public boolean contains(DataKeyBase<?> dataKeyBase) {
        if (super.contains(dataKeyBase)) {
            return true;
        }
        return this.parent != null && this.parent.contains(dataKeyBase);
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataSet, com.vladsch.flexmark.util.data.DataSet, com.vladsch.flexmark.util.data.DataHolder
    public Object getOrCompute(DataKeyBase<?> dataKeyBase, DataValueFactory<?> dataValueFactory) {
        if (this.parent == null || super.contains(dataKeyBase) || !this.parent.contains(dataKeyBase)) {
            return super.getOrCompute(dataKeyBase, dataValueFactory);
        }
        return this.parent.getOrCompute(dataKeyBase, dataValueFactory);
    }
}
