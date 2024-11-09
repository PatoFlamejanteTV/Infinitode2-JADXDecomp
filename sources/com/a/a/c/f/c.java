package com.a.a.c.f;

import com.a.a.c.f.b;

/* loaded from: infinitode-2.jar:com/a/a/c/f/c.class */
public final class c<A extends b, M> {

    /* renamed from: a, reason: collision with root package name */
    public final A f447a;

    /* renamed from: b, reason: collision with root package name */
    public final M f448b;

    private c(A a2, M m) {
        this.f447a = a2;
        this.f448b = m;
    }

    public static <A extends b, M> c<A, M> a(A a2, M m) {
        return new c<>(a2, m);
    }
}
