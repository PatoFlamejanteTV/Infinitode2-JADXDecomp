package com.vladsch.flexmark.util.collection;

import java.util.Map;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/IndexedItemSetMap.class */
public interface IndexedItemSetMap<M, S, K> extends Map<M, S> {
    M mapKey(K k);

    S newSet();

    boolean addSetItem(S s, int i);

    boolean removeSetItem(S s, int i);

    boolean containsSetItem(S s, int i);

    boolean addItem(K k, int i);

    boolean removeItem(K k, int i);

    boolean containsItem(K k, int i);
}
