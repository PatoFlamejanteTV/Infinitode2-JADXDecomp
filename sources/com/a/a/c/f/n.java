package com.a.a.c.f;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;

/* loaded from: infinitode-2.jar:com/a/a/c/f/n.class */
public final class n extends j {
    private o c;
    private com.a.a.c.j d;
    private int e;

    public n(o oVar, com.a.a.c.j jVar, an anVar, aa aaVar, int i) {
        super(anVar, aaVar);
        this.c = oVar;
        this.d = jVar;
        this.e = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.f.j
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public n a(aa aaVar) {
        if (aaVar == this.f458b) {
            return this;
        }
        return this.c.a(this.e, aaVar);
    }

    @Override // com.a.a.c.f.b
    public final AnnotatedElement a() {
        return null;
    }

    @Override // com.a.a.c.f.b
    public final String b() {
        return "";
    }

    @Override // com.a.a.c.f.b
    public final Class<?> d() {
        return this.d.b();
    }

    @Override // com.a.a.c.f.b
    public final com.a.a.c.j c() {
        return this.d;
    }

    @Override // com.a.a.c.f.j
    public final Class<?> h() {
        return this.c.h();
    }

    @Override // com.a.a.c.f.j
    public final Member i() {
        return this.c.i();
    }

    @Override // com.a.a.c.f.j
    public final void a(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Cannot call setValue() on constructor parameter of " + h().getName());
    }

    @Override // com.a.a.c.f.j
    public final Object b(Object obj) {
        throw new UnsupportedOperationException("Cannot call getValue() on constructor parameter of " + h().getName());
    }

    public final o e() {
        return this.c;
    }

    public final int f() {
        return this.e;
    }

    @Override // com.a.a.c.f.b
    public final int hashCode() {
        return this.c.hashCode() + this.e;
    }

    @Override // com.a.a.c.f.b
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!com.a.a.c.m.i.a(obj, getClass())) {
            return false;
        }
        n nVar = (n) obj;
        return nVar.c.equals(this.c) && nVar.e == this.e;
    }

    @Override // com.a.a.c.f.b
    public final String toString() {
        return "[parameter #" + f() + ", annotations: " + this.f458b + "]";
    }
}
