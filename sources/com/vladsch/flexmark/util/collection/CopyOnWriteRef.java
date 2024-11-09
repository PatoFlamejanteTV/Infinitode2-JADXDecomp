package com.vladsch.flexmark.util.collection;

import java.util.function.Function;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/CopyOnWriteRef.class */
public class CopyOnWriteRef<T> {
    private T value;
    private int referenceCount = 0;
    private final Function<T, T> copyFunction;

    public CopyOnWriteRef(T t, Function<T, T> function) {
        this.value = t;
        this.copyFunction = function;
    }

    public T getPeek() {
        return this.value;
    }

    public T getImmutable() {
        if (this.value != null) {
            this.referenceCount++;
        }
        return this.value;
    }

    public T getMutable() {
        if (this.referenceCount > 0) {
            this.value = this.copyFunction.apply(this.value);
            this.referenceCount = 0;
        }
        return this.value;
    }

    public void setValue(T t) {
        this.referenceCount = 0;
        this.value = this.copyFunction.apply(t);
    }

    public boolean isMutable() {
        return this.referenceCount == 0;
    }
}
