package com.vladsch.flexmark.util.dependency;

import java.util.List;

@Deprecated
/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/dependency/ResolvedDependencies.class */
public class ResolvedDependencies<T> {
    private final List<T> dependentStages;

    public ResolvedDependencies(List<T> list) {
        this.dependentStages = list;
    }

    public List<T> getDependentStages() {
        return this.dependentStages;
    }

    public boolean isEmpty() {
        return getDependentStages().isEmpty();
    }
}
