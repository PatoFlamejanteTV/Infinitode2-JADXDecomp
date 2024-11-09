package com.a.a.c.j;

import com.a.a.c.aa;

/* loaded from: infinitode-2.jar:com/a/a/c/j/h.class */
public final class h extends q {

    /* renamed from: a, reason: collision with root package name */
    private double f533a;

    private h(double d) {
        this.f533a = d;
    }

    public static h b(double d) {
        return new h(d);
    }

    @Override // com.a.a.c.j.u
    public final com.a.a.b.o p() {
        return com.a.a.b.o.VALUE_NUMBER_FLOAT;
    }

    @Override // com.a.a.c.j.q, com.a.a.c.m
    public final int g() {
        return (int) this.f533a;
    }

    @Override // com.a.a.c.j.q, com.a.a.c.m
    public final double h() {
        return this.f533a;
    }

    @Override // com.a.a.c.m
    public final String i() {
        return com.a.a.b.c.i.a(this.f533a);
    }

    @Override // com.a.a.c.j.b, com.a.a.c.n
    public final void a(com.a.a.b.h hVar, aa aaVar) {
        hVar.a(this.f533a);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof h)) {
            return Double.compare(this.f533a, ((h) obj).f533a) == 0;
        }
        return false;
    }

    public final int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.f533a);
        return ((int) doubleToLongBits) ^ ((int) (doubleToLongBits >> 32));
    }
}
