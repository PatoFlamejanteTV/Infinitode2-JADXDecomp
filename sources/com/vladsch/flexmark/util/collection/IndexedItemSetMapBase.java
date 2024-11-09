package com.vladsch.flexmark.util.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/IndexedItemSetMapBase.class */
public abstract class IndexedItemSetMapBase<K, S, M> implements IndexedItemSetMap<K, S, M> {
    protected final HashMap<K, S> bag;

    @Override // com.vladsch.flexmark.util.collection.IndexedItemSetMap
    public abstract K mapKey(M m);

    @Override // com.vladsch.flexmark.util.collection.IndexedItemSetMap
    public abstract S newSet();

    @Override // com.vladsch.flexmark.util.collection.IndexedItemSetMap
    public abstract boolean addSetItem(S s, int i);

    @Override // com.vladsch.flexmark.util.collection.IndexedItemSetMap
    public abstract boolean removeSetItem(S s, int i);

    @Override // com.vladsch.flexmark.util.collection.IndexedItemSetMap
    public abstract boolean containsSetItem(S s, int i);

    public IndexedItemSetMapBase() {
        this(0);
    }

    public IndexedItemSetMapBase(int i) {
        this.bag = new HashMap<>();
    }

    @Override // com.vladsch.flexmark.util.collection.IndexedItemSetMap
    public boolean addItem(M m, int i) {
        K mapKey = mapKey(m);
        S s = this.bag.get(mapKey);
        S s2 = s;
        if (s == null) {
            s2 = newSet();
            this.bag.put(mapKey, s2);
        }
        return addSetItem(s2, i);
    }

    @Override // com.vladsch.flexmark.util.collection.IndexedItemSetMap
    public boolean removeItem(M m, int i) {
        S s = this.bag.get(mapKey(m));
        return s != null && removeSetItem(s, i);
    }

    @Override // com.vladsch.flexmark.util.collection.IndexedItemSetMap
    public boolean containsItem(M m, int i) {
        S s = this.bag.get(mapKey(m));
        return s != null && containsSetItem(s, i);
    }

    @Override // java.util.Map
    public int size() {
        return this.bag.size();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.bag.isEmpty();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.bag.containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.bag.containsValue(obj);
    }

    @Override // java.util.Map
    public S get(Object obj) {
        return this.bag.get(obj);
    }

    @Override // java.util.Map
    public S put(K k, S s) {
        return this.bag.put(k, s);
    }

    @Override // java.util.Map
    public S remove(Object obj) {
        return this.bag.remove(obj);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends S> map) {
        this.bag.putAll(map);
    }

    @Override // java.util.Map
    public void clear() {
        this.bag.clear();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return this.bag.keySet();
    }

    @Override // java.util.Map
    public Collection<S> values() {
        return this.bag.values();
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, S>> entrySet() {
        return this.bag.entrySet();
    }
}
