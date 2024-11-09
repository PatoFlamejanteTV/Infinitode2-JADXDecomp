package com.vladsch.flexmark.util.collection.iteration;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/iteration/Indexed.class */
public interface Indexed<E> {
    E get(int i);

    void set(int i, E e);

    void removeAt(int i);

    int size();

    int modificationCount();
}
