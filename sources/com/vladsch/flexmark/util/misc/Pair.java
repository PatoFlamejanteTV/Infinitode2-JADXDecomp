package com.vladsch.flexmark.util.misc;

import java.util.Map;
import java.util.Objects;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/misc/Pair.class */
public final class Pair<K, V> implements Paired<K, V> {
    private final K first;
    private final V second;

    public static <K1, V1> Pair<K1, V1> of(K1 k1, V1 v1) {
        return new Pair<>(k1, v1);
    }

    public Pair(K k, V v) {
        this.first = k;
        this.second = v;
    }

    @Override // com.vladsch.flexmark.util.misc.Paired
    public final K getFirst() {
        return this.first;
    }

    @Override // com.vladsch.flexmark.util.misc.Paired
    public final V getSecond() {
        return this.second;
    }

    @Override // java.util.Map.Entry
    public final K getKey() {
        return this.first;
    }

    @Override // java.util.Map.Entry
    public final V getValue() {
        return this.second;
    }

    @Override // java.util.Map.Entry
    public final V setValue(V v) {
        throw new IllegalStateException("setValue not supported");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        if (this.first == null) {
            sb.append("null");
        } else {
            sb.append(this.first);
        }
        sb.append(", ");
        if (this.second == null) {
            sb.append("null");
        } else {
            sb.append(this.second);
        }
        sb.append(')');
        return sb.toString();
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (Objects.equals(this.first, entry.getKey())) {
            return Objects.equals(this.second, entry.getValue());
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        return ((this.first != null ? this.first.hashCode() : 0) * 31) + (this.second != null ? this.second.hashCode() : 0);
    }
}
