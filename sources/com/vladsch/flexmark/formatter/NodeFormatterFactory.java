package com.vladsch.flexmark.formatter;

import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.dependency.Dependent;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/NodeFormatterFactory.class */
public interface NodeFormatterFactory extends Dependent {
    NodeFormatter create(DataHolder dataHolder);

    @Override // com.vladsch.flexmark.util.dependency.Dependent
    default Set<Class<?>> getAfterDependents() {
        return null;
    }

    @Override // com.vladsch.flexmark.util.dependency.Dependent
    default Set<Class<?>> getBeforeDependents() {
        return null;
    }

    @Override // com.vladsch.flexmark.util.dependency.Dependent
    default boolean affectsGlobalScope() {
        return false;
    }
}
