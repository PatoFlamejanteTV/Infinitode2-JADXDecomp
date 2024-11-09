package com.vladsch.flexmark.util.dependency;

import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/dependency/Dependent.class */
public interface Dependent {
    Set<Class<?>> getAfterDependents();

    Set<Class<?>> getBeforeDependents();

    boolean affectsGlobalScope();
}
