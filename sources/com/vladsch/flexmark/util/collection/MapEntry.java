package com.vladsch.flexmark.util.collection;

import java.util.Map;
import java.util.Objects;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/collection/MapEntry.class */
public final class MapEntry<K, V> implements Map.Entry<K, V> {
    private final K key;
    private final V value;

    public MapEntry(K k, V v) {
        this.key = k;
        this.value = v;
    }

    @Override // java.util.Map.Entry
    public final K getKey() {
        return this.key;
    }

    @Override // java.util.Map.Entry
    public final V getValue() {
        return this.value;
    }

    @Override // java.util.Map.Entry
    public final V setValue(V v) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MapEntry mapEntry = (MapEntry) obj;
        if (Objects.equals(this.key, mapEntry.key)) {
            return Objects.equals(this.value, mapEntry.value);
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        return (this.key.hashCode() * 31) + (this.value != null ? this.value.hashCode() : 0);
    }
}
