package com.vladsch.flexmark.util.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/data/DataSet.class */
public class DataSet implements DataHolder {
    protected final HashMap<DataKeyBase<?>, Object> dataSet;
    private static final ArrayList<DataKeyAggregator> ourDataKeyAggregators = new ArrayList<>();

    public DataSet() {
        this(null);
    }

    public DataSet(DataHolder dataHolder) {
        if (dataHolder == null) {
            this.dataSet = new HashMap<>();
        } else {
            this.dataSet = new HashMap<>(dataHolder.getAll());
        }
    }

    public static DataHolder aggregateActions(DataHolder dataHolder, DataHolder dataHolder2) {
        DataSet dataSet = new DataSet(dataHolder);
        DataSet dataSet2 = dataSet;
        dataSet.dataSet.putAll(dataHolder2.getAll());
        Iterator<DataKeyAggregator> it = ourDataKeyAggregators.iterator();
        while (it.hasNext()) {
            dataSet2 = it.next().aggregateActions(dataSet2, dataHolder, dataHolder2).toDataSet();
        }
        return dataSet2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v9, types: [com.vladsch.flexmark.util.data.DataHolder] */
    public DataHolder aggregate() {
        DataSet dataSet = this;
        Iterator<DataKeyAggregator> it = ourDataKeyAggregators.iterator();
        while (it.hasNext()) {
            dataSet = it.next().aggregate(dataSet);
        }
        return dataSet;
    }

    public static DataHolder aggregate(DataHolder dataHolder, DataHolder dataHolder2) {
        if (dataHolder == null && dataHolder2 == null) {
            return new DataSet();
        }
        if (dataHolder2 == null) {
            return dataHolder;
        }
        if (dataHolder == null) {
            return dataHolder2.toDataSet().aggregate().toImmutable();
        }
        return aggregateActions(dataHolder, dataHolder2).toDataSet().aggregate().toImmutable();
    }

    @Override // com.vladsch.flexmark.util.data.DataHolder
    public Map<? extends DataKeyBase<?>, Object> getAll() {
        return this.dataSet;
    }

    @Override // com.vladsch.flexmark.util.data.DataHolder
    public Collection<? extends DataKeyBase<?>> getKeys() {
        return this.dataSet.keySet();
    }

    @Override // com.vladsch.flexmark.util.data.DataHolder
    public boolean contains(DataKeyBase<?> dataKeyBase) {
        return this.dataSet.containsKey(dataKeyBase);
    }

    @Override // com.vladsch.flexmark.util.data.DataHolder
    public Object getOrCompute(DataKeyBase<?> dataKeyBase, DataValueFactory<?> dataValueFactory) {
        if (this.dataSet.containsKey(dataKeyBase)) {
            return this.dataSet.get(dataKeyBase);
        }
        return dataValueFactory.apply((DataHolder) this);
    }

    public static DataSet merge(DataHolder... dataHolderArr) {
        DataSet dataSet = new DataSet();
        for (DataHolder dataHolder : dataHolderArr) {
            dataSet.dataSet.putAll(dataHolder.getAll());
        }
        return dataSet;
    }

    @Override // com.vladsch.flexmark.util.data.DataHolder
    public MutableDataSet toMutable() {
        return new MutableDataSet(this);
    }

    @Override // com.vladsch.flexmark.util.data.DataHolder
    public DataSet toImmutable() {
        return this;
    }

    @Override // com.vladsch.flexmark.util.data.DataHolder
    public DataSet toDataSet() {
        return this;
    }

    public static void registerDataKeyAggregator(DataKeyAggregator dataKeyAggregator) {
        if (isAggregatorRegistered(dataKeyAggregator)) {
            throw new IllegalStateException("Aggregator " + dataKeyAggregator + " is already registered");
        }
        for (int i = 0; i < ourDataKeyAggregators.size(); i++) {
            DataKeyAggregator dataKeyAggregator2 = ourDataKeyAggregators.get(i);
            if (invokeSetContains(dataKeyAggregator2.invokeAfterSet(), dataKeyAggregator)) {
                if (invokeSetContains(dataKeyAggregator.invokeAfterSet(), dataKeyAggregator2)) {
                    throw new IllegalStateException("Circular invokeAfter dependencies for " + dataKeyAggregator + " and " + dataKeyAggregator2);
                }
                ourDataKeyAggregators.add(i, dataKeyAggregator);
                return;
            }
        }
        ourDataKeyAggregators.add(dataKeyAggregator);
    }

    static boolean isAggregatorRegistered(DataKeyAggregator dataKeyAggregator) {
        Iterator<DataKeyAggregator> it = ourDataKeyAggregators.iterator();
        while (it.hasNext()) {
            if (it.next().getClass() == dataKeyAggregator.getClass()) {
                return true;
            }
        }
        return false;
    }

    static boolean invokeSetContains(Set<Class<?>> set, DataKeyAggregator dataKeyAggregator) {
        if (set == null) {
            return false;
        }
        return set.contains(dataKeyAggregator.getClass());
    }

    public String toString() {
        return "DataSet{dataSet=" + this.dataSet + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DataSet) {
            return this.dataSet.equals(((DataSet) obj).dataSet);
        }
        return false;
    }

    public int hashCode() {
        return this.dataSet.hashCode();
    }
}
