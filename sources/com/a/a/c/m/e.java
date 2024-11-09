package com.a.a.c.m;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: infinitode-2.jar:com/a/a/c/m/e.class */
public final class e<T> implements Iterable<T>, Iterator<T> {

    /* renamed from: a, reason: collision with root package name */
    private final T[] f717a;

    /* renamed from: b, reason: collision with root package name */
    private int f718b = 0;

    public e(T[] tArr) {
        this.f717a = tArr;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f718b < this.f717a.length;
    }

    @Override // java.util.Iterator
    public final T next() {
        if (this.f718b >= this.f717a.length) {
            throw new NoSuchElementException();
        }
        T[] tArr = this.f717a;
        int i = this.f718b;
        this.f718b = i + 1;
        return tArr[i];
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.lang.Iterable
    public final Iterator<T> iterator() {
        return this;
    }
}
