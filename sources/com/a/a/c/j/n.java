package com.a.a.c.j;

import com.a.a.c.aa;

/* loaded from: infinitode-2.jar:com/a/a/c/j/n.class */
public final class n extends q {

    /* renamed from: a, reason: collision with root package name */
    private long f547a;

    private n(long j) {
        this.f547a = j;
    }

    public static n a(long j) {
        return new n(j);
    }

    @Override // com.a.a.c.j.u
    public final com.a.a.b.o p() {
        return com.a.a.b.o.VALUE_NUMBER_INT;
    }

    @Override // com.a.a.c.j.q, com.a.a.c.m
    public final int g() {
        return (int) this.f547a;
    }

    @Override // com.a.a.c.j.q, com.a.a.c.m
    public final double h() {
        return this.f547a;
    }

    @Override // com.a.a.c.m
    public final String i() {
        return com.a.a.b.c.i.a(this.f547a);
    }

    @Override // com.a.a.c.m
    public final boolean a(boolean z) {
        return this.f547a != 0;
    }

    @Override // com.a.a.c.j.b, com.a.a.c.n
    public final void a(com.a.a.b.h hVar, aa aaVar) {
        hVar.b(this.f547a);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && (obj instanceof n) && ((n) obj).f547a == this.f547a;
    }

    public final int hashCode() {
        return ((int) this.f547a) ^ ((int) (this.f547a >> 32));
    }
}
