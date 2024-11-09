package com.a.a.c.j;

import com.a.a.c.aa;

/* loaded from: infinitode-2.jar:com/a/a/c/j/i.class */
public final class i extends q {

    /* renamed from: a, reason: collision with root package name */
    private float f534a;

    private i(float f) {
        this.f534a = f;
    }

    public static i a(float f) {
        return new i(f);
    }

    @Override // com.a.a.c.j.u
    public final com.a.a.b.o p() {
        return com.a.a.b.o.VALUE_NUMBER_FLOAT;
    }

    @Override // com.a.a.c.j.q, com.a.a.c.m
    public final int g() {
        return (int) this.f534a;
    }

    @Override // com.a.a.c.j.q, com.a.a.c.m
    public final double h() {
        return this.f534a;
    }

    @Override // com.a.a.c.m
    public final String i() {
        return com.a.a.b.c.i.a(this.f534a);
    }

    @Override // com.a.a.c.j.b, com.a.a.c.n
    public final void a(com.a.a.b.h hVar, aa aaVar) {
        hVar.a(this.f534a);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof i)) {
            return Float.compare(this.f534a, ((i) obj).f534a) == 0;
        }
        return false;
    }

    public final int hashCode() {
        return Float.floatToIntBits(this.f534a);
    }
}
