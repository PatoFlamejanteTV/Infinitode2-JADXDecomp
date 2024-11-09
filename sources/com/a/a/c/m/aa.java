package com.a.a.c.m;

import com.a.a.a.s;
import java.util.Collections;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/a/a/c/m/aa.class */
public final class aa extends com.a.a.c.f.s {

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.a f703b;
    private com.a.a.c.f.j c;
    private com.a.a.c.v d;
    private com.a.a.c.w e;
    private s.b f;

    private aa(com.a.a.c.a aVar, com.a.a.c.f.j jVar, com.a.a.c.w wVar, com.a.a.c.v vVar, s.b bVar) {
        this.f703b = aVar;
        this.c = jVar;
        this.e = wVar;
        this.d = vVar == null ? com.a.a.c.v.f767b : vVar;
        this.f = bVar;
    }

    public static aa a(com.a.a.c.b.q<?> qVar, com.a.a.c.f.j jVar, com.a.a.c.w wVar) {
        return a(qVar, jVar, wVar, (com.a.a.c.v) null, f472a);
    }

    public static aa a(com.a.a.c.b.q<?> qVar, com.a.a.c.f.j jVar, com.a.a.c.w wVar, com.a.a.c.v vVar, s.a aVar) {
        return new aa(qVar.j(), jVar, wVar, vVar, (aVar == null || aVar == s.a.USE_DEFAULTS) ? f472a : s.b.a(aVar, (s.a) null));
    }

    private static aa a(com.a.a.c.b.q<?> qVar, com.a.a.c.f.j jVar, com.a.a.c.w wVar, com.a.a.c.v vVar, s.b bVar) {
        return new aa(qVar.j(), jVar, wVar, vVar, bVar);
    }

    @Override // com.a.a.c.f.s, com.a.a.c.m.v
    public final String a() {
        return this.e.b();
    }

    @Override // com.a.a.c.f.s
    public final com.a.a.c.w b() {
        return this.e;
    }

    @Override // com.a.a.c.f.s
    public final boolean a(com.a.a.c.w wVar) {
        return this.e.equals(wVar);
    }

    @Override // com.a.a.c.f.s
    public final com.a.a.c.w c() {
        if (this.f703b == null || this.c == null) {
            return null;
        }
        return com.a.a.c.a.b();
    }

    @Override // com.a.a.c.f.s
    public final boolean d() {
        return false;
    }

    @Override // com.a.a.c.f.s
    public final boolean e() {
        return false;
    }

    @Override // com.a.a.c.f.s
    public final com.a.a.c.v h() {
        return this.d;
    }

    @Override // com.a.a.c.f.s
    public final com.a.a.c.j f() {
        if (this.c == null) {
            return com.a.a.c.l.o.b();
        }
        return this.c.c();
    }

    @Override // com.a.a.c.f.s
    public final Class<?> g() {
        if (this.c == null) {
            return Object.class;
        }
        return this.c.d();
    }

    @Override // com.a.a.c.f.s
    public final s.b B() {
        return this.f;
    }

    @Override // com.a.a.c.f.s
    public final boolean k() {
        return o() != null;
    }

    @Override // com.a.a.c.f.s
    public final boolean l() {
        return this.c instanceof com.a.a.c.f.h;
    }

    @Override // com.a.a.c.f.s
    public final boolean m() {
        return this.c instanceof com.a.a.c.f.n;
    }

    @Override // com.a.a.c.f.s
    public final com.a.a.c.f.k n() {
        if ((this.c instanceof com.a.a.c.f.k) && ((com.a.a.c.f.k) this.c).f() == 0) {
            return (com.a.a.c.f.k) this.c;
        }
        return null;
    }

    @Override // com.a.a.c.f.s
    public final com.a.a.c.f.k o() {
        if ((this.c instanceof com.a.a.c.f.k) && ((com.a.a.c.f.k) this.c).f() == 1) {
            return (com.a.a.c.f.k) this.c;
        }
        return null;
    }

    @Override // com.a.a.c.f.s
    public final com.a.a.c.f.h p() {
        if (this.c instanceof com.a.a.c.f.h) {
            return (com.a.a.c.f.h) this.c;
        }
        return null;
    }

    @Override // com.a.a.c.f.s
    public final com.a.a.c.f.n q() {
        if (this.c instanceof com.a.a.c.f.n) {
            return (com.a.a.c.f.n) this.c;
        }
        return null;
    }

    @Override // com.a.a.c.f.s
    public final Iterator<com.a.a.c.f.n> r() {
        com.a.a.c.f.n q = q();
        if (q == null) {
            return i.a();
        }
        return Collections.singleton(q).iterator();
    }

    @Override // com.a.a.c.f.s
    public final com.a.a.c.f.j v() {
        return this.c;
    }
}
