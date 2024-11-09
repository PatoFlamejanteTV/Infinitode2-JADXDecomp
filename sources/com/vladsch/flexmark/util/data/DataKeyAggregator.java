package com.vladsch.flexmark.util.data;

import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/data/DataKeyAggregator.class */
public interface DataKeyAggregator {
    DataHolder aggregate(DataHolder dataHolder);

    DataHolder aggregateActions(DataHolder dataHolder, DataHolder dataHolder2, DataHolder dataHolder3);

    DataHolder clean(DataHolder dataHolder);

    Set<Class<?>> invokeAfterSet();
}
