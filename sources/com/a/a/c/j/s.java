package com.a.a.c.j;

import com.a.a.c.aa;

/* loaded from: infinitode-2.jar:com/a/a/c/j/s.class */
public final class s extends u {

    /* renamed from: a, reason: collision with root package name */
    private Object f551a;

    public s(Object obj) {
        this.f551a = obj;
    }

    @Override // com.a.a.c.m
    public final m d() {
        return m.POJO;
    }

    @Override // com.a.a.c.j.u
    public final com.a.a.b.o p() {
        return com.a.a.b.o.VALUE_EMBEDDED_OBJECT;
    }

    @Override // com.a.a.c.m
    public final String i() {
        return this.f551a == null ? "null" : this.f551a.toString();
    }

    @Override // com.a.a.c.m
    public final String c(String str) {
        return this.f551a == null ? str : this.f551a.toString();
    }

    @Override // com.a.a.c.m
    public final boolean a(boolean z) {
        if (this.f551a != null && (this.f551a instanceof Boolean)) {
            return ((Boolean) this.f551a).booleanValue();
        }
        return false;
    }

    @Override // com.a.a.c.m
    public final int b(int i) {
        if (this.f551a instanceof Number) {
            return ((Number) this.f551a).intValue();
        }
        return 0;
    }

    @Override // com.a.a.c.m
    public final double a(double d) {
        if (this.f551a instanceof Number) {
            return ((Number) this.f551a).doubleValue();
        }
        return 0.0d;
    }

    @Override // com.a.a.c.j.b, com.a.a.c.n
    public final void a(com.a.a.b.h hVar, aa aaVar) {
        if (this.f551a == null) {
            aaVar.a(hVar);
        } else if (this.f551a instanceof com.a.a.c.n) {
            ((com.a.a.c.n) this.f551a).a(hVar, aaVar);
        } else {
            aaVar.a(this.f551a, hVar);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof s)) {
            return a((s) obj);
        }
        return false;
    }

    private boolean a(s sVar) {
        if (this.f551a == null) {
            return sVar.f551a == null;
        }
        return this.f551a.equals(sVar.f551a);
    }

    public final int hashCode() {
        return this.f551a.hashCode();
    }
}
