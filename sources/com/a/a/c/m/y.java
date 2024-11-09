package com.a.a.c.m;

/* loaded from: infinitode-2.jar:com/a/a/c/m/y.class */
public final class y implements com.a.a.c.n {

    /* renamed from: a, reason: collision with root package name */
    private Object f752a;

    public y(String str) {
        this.f752a = str;
    }

    @Override // com.a.a.c.n
    public final void a(com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        if (this.f752a instanceof com.a.a.c.n) {
            ((com.a.a.c.n) this.f752a).a(hVar, aaVar);
        } else {
            b(hVar);
        }
    }

    @Override // com.a.a.c.n
    public final void a(com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.i.i iVar) {
        if (this.f752a instanceof com.a.a.c.n) {
            ((com.a.a.c.n) this.f752a).a(hVar, aaVar, iVar);
        } else if (this.f752a instanceof com.a.a.b.r) {
            a(hVar, aaVar);
        }
    }

    public final void a(com.a.a.b.h hVar) {
        if (this.f752a instanceof com.a.a.c.n) {
            hVar.h(this.f752a);
        } else {
            b(hVar);
        }
    }

    private void b(com.a.a.b.h hVar) {
        if (this.f752a instanceof com.a.a.b.r) {
            hVar.e((com.a.a.b.r) this.f752a);
        } else {
            hVar.d(String.valueOf(this.f752a));
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof y)) {
            return false;
        }
        y yVar = (y) obj;
        if (this.f752a == yVar.f752a) {
            return true;
        }
        return this.f752a != null && this.f752a.equals(yVar.f752a);
    }

    public final int hashCode() {
        if (this.f752a == null) {
            return 0;
        }
        return this.f752a.hashCode();
    }

    public final String toString() {
        return String.format("[RawValue of type %s]", i.d(this.f752a));
    }
}
