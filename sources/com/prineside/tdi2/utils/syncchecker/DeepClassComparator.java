package com.prineside.tdi2.utils.syncchecker;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/DeepClassComparator.class */
public abstract class DeepClassComparator<T> {
    public abstract Class<T> forClass();

    public abstract void compare(T t, T t2, DeepClassComparisonConfig deepClassComparisonConfig);
}
