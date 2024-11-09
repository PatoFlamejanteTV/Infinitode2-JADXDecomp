package com.vladsch.flexmark.util.dependency;

import com.vladsch.flexmark.util.dependency.Dependent;
import java.util.List;

@Deprecated
/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/dependency/FlatDependencyHandler.class */
public class FlatDependencyHandler<T extends Dependent> extends DependencyHandler<T, FlatDependencyStage<T>, FlatDependencies<T>> {
    /* JADX WARN: Multi-variable type inference failed */
    public List<T> resolvedDependencies(List<T> list) {
        return resolveDependencies(list).dependencies;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vladsch.flexmark.util.dependency.DependencyHandler
    public FlatDependencyStage<T> createStage(List<T> list) {
        return new FlatDependencyStage<>(list);
    }

    @Override // com.vladsch.flexmark.util.dependency.DependencyHandler
    protected Class<? extends T> getDependentClass(T t) {
        return (Class<? extends T>) t.getClass();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vladsch.flexmark.util.dependency.DependencyHandler
    public FlatDependencies<T> createResolvedDependencies(List<FlatDependencyStage<T>> list) {
        return new FlatDependencies<>(list);
    }

    public static <T extends Dependent> List<T> computeDependencies(List<T> list) {
        return new FlatDependencyHandler().resolvedDependencies(list);
    }
}
