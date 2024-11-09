package com.vladsch.flexmark.util.dependency;

import java.util.BitSet;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/dependency/DependentItem.class */
public class DependentItem<D> {
    public final int index;
    public final D dependent;
    public final Class<?> dependentClass;
    public final boolean isGlobalScope;
    BitSet dependencies;
    BitSet dependents;

    public DependentItem(int i, D d, Class<?> cls, boolean z) {
        this.index = i;
        this.dependent = d;
        this.dependentClass = cls;
        this.isGlobalScope = z;
    }

    public void addDependency(DependentItem<D> dependentItem) {
        if (this.dependencies == null) {
            this.dependencies = new BitSet();
        }
        this.dependencies.set(dependentItem.index);
    }

    public void addDependency(BitSet bitSet) {
        if (this.dependencies == null) {
            this.dependencies = new BitSet();
        }
        this.dependencies.or(bitSet);
    }

    public boolean removeDependency(DependentItem<D> dependentItem) {
        if (this.dependencies != null) {
            this.dependencies.clear(dependentItem.index);
        }
        return hasDependencies();
    }

    public boolean removeDependency(BitSet bitSet) {
        if (this.dependencies != null) {
            this.dependencies.andNot(bitSet);
        }
        return hasDependencies();
    }

    public void addDependent(DependentItem<D> dependentItem) {
        if (this.dependents == null) {
            this.dependents = new BitSet();
        }
        this.dependents.set(dependentItem.index);
    }

    public void addDependent(BitSet bitSet) {
        if (this.dependents == null) {
            this.dependents = new BitSet();
        }
        this.dependents.or(bitSet);
    }

    public void removeDependent(DependentItem<D> dependentItem) {
        if (this.dependents != null) {
            this.dependents.clear(dependentItem.index);
        }
    }

    public void removeDependent(BitSet bitSet) {
        if (this.dependents != null) {
            this.dependents.andNot(bitSet);
        }
    }

    public boolean hasDependencies() {
        return (this.dependencies == null || this.dependencies.nextSetBit(0) == -1) ? false : true;
    }

    public boolean hasDependents() {
        return (this.dependents == null || this.dependents.nextSetBit(0) == -1) ? false : true;
    }
}
