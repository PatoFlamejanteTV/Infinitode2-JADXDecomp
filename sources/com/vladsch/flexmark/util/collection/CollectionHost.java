package com.vladsch.flexmark.util.collection;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/CollectionHost.class */
public interface CollectionHost<K> {
    void adding(int i, K k, Object obj);

    Object removing(int i, K k);

    void clearing();

    void addingNulls(int i);

    boolean skipHostUpdate();

    int getIteratorModificationCount();
}
