package com.a.a.c.m;

/* loaded from: infinitode-2.jar:com/a/a/c/m/p.class */
public final class p<T> {

    /* renamed from: a, reason: collision with root package name */
    private final T f740a;

    /* renamed from: b, reason: collision with root package name */
    private p<T> f741b;

    public p(T t, p<T> pVar) {
        this.f740a = t;
        this.f741b = pVar;
    }

    public final void a(p<T> pVar) {
        if (this.f741b != null) {
            throw new IllegalStateException();
        }
        this.f741b = pVar;
    }

    public final p<T> a() {
        return this.f741b;
    }

    public final T b() {
        return this.f740a;
    }
}
