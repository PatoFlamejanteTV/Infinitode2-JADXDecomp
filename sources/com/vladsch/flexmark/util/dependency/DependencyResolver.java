package com.vladsch.flexmark.util.dependency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/dependency/DependencyResolver.class */
public class DependencyResolver {
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !DependencyResolver.class.desiredAssertionStatus();
    }

    public static <D extends Dependent> List<D> resolveFlatDependencies(List<D> list, Function<DependentItemMap<D>, DependentItemMap<D>> function, Function<? super D, Class<?>> function2) {
        List resolveDependencies = resolveDependencies(list, function, function2);
        if (resolveDependencies.isEmpty()) {
            return Collections.emptyList();
        }
        if (resolveDependencies.size() == 1) {
            return (List) resolveDependencies.get(0);
        }
        int i = 0;
        Iterator it = resolveDependencies.iterator();
        while (it.hasNext()) {
            i += ((List) it.next()).size();
        }
        ArrayList arrayList = new ArrayList(i);
        Iterator it2 = resolveDependencies.iterator();
        while (it2.hasNext()) {
            arrayList.addAll((List) it2.next());
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:161:0x02b1, code lost:            continue;     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static <D extends com.vladsch.flexmark.util.dependency.Dependent> java.util.List<java.util.List<D>> resolveDependencies(java.util.List<D> r7, java.util.function.Function<com.vladsch.flexmark.util.dependency.DependentItemMap<D>, com.vladsch.flexmark.util.dependency.DependentItemMap<D>> r8, java.util.function.Function<? super D, java.lang.Class<?>> r9) {
        /*
            Method dump skipped, instructions count: 919
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vladsch.flexmark.util.dependency.DependencyResolver.resolveDependencies(java.util.List, java.util.function.Function, java.util.function.Function):java.util.List");
    }
}
