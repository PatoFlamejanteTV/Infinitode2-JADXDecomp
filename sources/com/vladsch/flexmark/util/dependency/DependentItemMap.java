package com.vladsch.flexmark.util.dependency;

import com.vladsch.flexmark.util.collection.CollectionHost;
import com.vladsch.flexmark.util.collection.OrderedMap;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/dependency/DependentItemMap.class */
public class DependentItemMap<D> extends OrderedMap<Class<?>, DependentItem<D>> {
    public DependentItemMap() {
    }

    public DependentItemMap(int i) {
        super(i);
    }

    public DependentItemMap(CollectionHost<Class<?>> collectionHost) {
        super(collectionHost);
    }

    public DependentItemMap(int i, CollectionHost<Class<?>> collectionHost) {
        super(i, collectionHost);
    }
}
