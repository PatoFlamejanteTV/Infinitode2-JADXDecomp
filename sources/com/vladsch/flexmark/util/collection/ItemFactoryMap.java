package com.vladsch.flexmark.util.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/ItemFactoryMap.class */
public class ItemFactoryMap<I, P> implements Map<Function<P, I>, I> {
    protected final HashMap<Function<P, I>, I> itemMap;
    protected final P param;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
        return put((Function<P, Function<P, I>>) obj, (Function<P, I>) obj2);
    }

    public ItemFactoryMap(P p) {
        this(p, 0);
    }

    public ItemFactoryMap(P p, int i) {
        this.itemMap = new HashMap<>(i);
        this.param = p;
    }

    public I getItem(Function<P, I> function) {
        I i = this.itemMap.get(function);
        I i2 = i;
        if (i == null) {
            i2 = function.apply(this.param);
            this.itemMap.put(function, i2);
        }
        return i2;
    }

    @Override // java.util.Map
    public I get(Object obj) {
        if (obj instanceof Function) {
            return getItem((Function) obj);
        }
        return null;
    }

    @Override // java.util.Map
    public int size() {
        return this.itemMap.size();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.itemMap.isEmpty();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.itemMap.containsKey(obj);
    }

    public I put(Function<P, I> function, I i) {
        return this.itemMap.put(function, i);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends Function<P, I>, ? extends I> map) {
        this.itemMap.putAll(map);
    }

    @Override // java.util.Map
    public I remove(Object obj) {
        return this.itemMap.remove(obj);
    }

    @Override // java.util.Map
    public void clear() {
        this.itemMap.clear();
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.itemMap.containsValue(obj);
    }

    @Override // java.util.Map
    public Set<Function<P, I>> keySet() {
        return this.itemMap.keySet();
    }

    @Override // java.util.Map
    public Collection<I> values() {
        return this.itemMap.values();
    }

    @Override // java.util.Map
    public Set<Map.Entry<Function<P, I>, I>> entrySet() {
        return this.itemMap.entrySet();
    }
}
