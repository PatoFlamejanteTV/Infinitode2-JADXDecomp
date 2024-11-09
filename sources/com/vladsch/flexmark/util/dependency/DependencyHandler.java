package com.vladsch.flexmark.util.dependency;

import com.vladsch.flexmark.util.dependency.Dependent;
import com.vladsch.flexmark.util.dependency.ResolvedDependencies;
import java.util.List;

@Deprecated
/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/dependency/DependencyHandler.class */
public abstract class DependencyHandler<D extends Dependent, S, R extends ResolvedDependencies<S>> {
    static final /* synthetic */ boolean $assertionsDisabled;

    protected abstract S createStage(List<D> list);

    protected abstract Class getDependentClass(D d);

    protected abstract R createResolvedDependencies(List<S> list);

    static {
        $assertionsDisabled = !DependencyHandler.class.desiredAssertionStatus();
    }

    /* JADX WARN: Code restructure failed: missing block: B:117:0x021c, code lost:            continue;     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public R resolveDependencies(java.util.List<D> r8) {
        /*
            Method dump skipped, instructions count: 783
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vladsch.flexmark.util.dependency.DependencyHandler.resolveDependencies(java.util.List):com.vladsch.flexmark.util.dependency.ResolvedDependencies");
    }

    protected DependentItemMap<D> prioritize(DependentItemMap<D> dependentItemMap) {
        return dependentItemMap;
    }
}
