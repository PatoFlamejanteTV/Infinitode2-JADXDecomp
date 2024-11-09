package com.vladsch.flexmark.util.misc;

import java.util.Map;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/misc/Paired.class */
public interface Paired<K, V> extends Map.Entry<K, V> {
    K getFirst();

    V getSecond();

    default K component1() {
        return getFirst();
    }

    default V component2() {
        return getSecond();
    }
}
