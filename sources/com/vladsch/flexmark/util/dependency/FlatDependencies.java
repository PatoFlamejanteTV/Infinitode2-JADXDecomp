package com.vladsch.flexmark.util.dependency;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Deprecated
/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/dependency/FlatDependencies.class */
public class FlatDependencies<T> extends ResolvedDependencies<FlatDependencyStage<T>> {
    ArrayList<T> dependencies;

    public FlatDependencies(List<FlatDependencyStage<T>> list) {
        super(list);
        ArrayList<T> arrayList = new ArrayList<>();
        Iterator<FlatDependencyStage<T>> it = list.iterator();
        while (it.hasNext()) {
            arrayList.addAll(it.next().getDependents());
        }
        this.dependencies = arrayList;
    }
}
