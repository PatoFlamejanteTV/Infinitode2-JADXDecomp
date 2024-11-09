package com.google.common.base;

import java.util.Collections;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: infinitode-2.jar:com/google/common/base/Present.class */
public final class Present<T> extends Optional<T> {
    private final T reference;
    private static final long serialVersionUID = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Present(T t) {
        this.reference = t;
    }

    @Override // com.google.common.base.Optional
    public final boolean isPresent() {
        return true;
    }

    @Override // com.google.common.base.Optional
    public final T get() {
        return this.reference;
    }

    @Override // com.google.common.base.Optional
    public final T or(T t) {
        Preconditions.checkNotNull(t, "use Optional.orNull() instead of Optional.or(null)");
        return this.reference;
    }

    @Override // com.google.common.base.Optional
    public final Optional<T> or(Optional<? extends T> optional) {
        Preconditions.checkNotNull(optional);
        return this;
    }

    @Override // com.google.common.base.Optional
    public final T or(Supplier<? extends T> supplier) {
        Preconditions.checkNotNull(supplier);
        return this.reference;
    }

    @Override // com.google.common.base.Optional
    public final T orNull() {
        return this.reference;
    }

    @Override // com.google.common.base.Optional
    public final Set<T> asSet() {
        return Collections.singleton(this.reference);
    }

    @Override // com.google.common.base.Optional
    public final <V> Optional<V> transform(Function<? super T, V> function) {
        return new Present(Preconditions.checkNotNull(function.apply(this.reference), "the Function passed to Optional.transform() must not return null."));
    }

    @Override // com.google.common.base.Optional
    public final boolean equals(Object obj) {
        if (obj instanceof Present) {
            return this.reference.equals(((Present) obj).reference);
        }
        return false;
    }

    @Override // com.google.common.base.Optional
    public final int hashCode() {
        return 1502476572 + this.reference.hashCode();
    }

    @Override // com.google.common.base.Optional
    public final String toString() {
        String valueOf = String.valueOf(this.reference);
        return new StringBuilder(13 + String.valueOf(valueOf).length()).append("Optional.of(").append(valueOf).append(")").toString();
    }
}
