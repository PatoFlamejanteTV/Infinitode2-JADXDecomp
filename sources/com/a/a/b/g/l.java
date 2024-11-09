package com.a.a.b.g;

import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/b/g/l.class */
public final class l implements com.a.a.b.q, Serializable {
    private String c;
    private n d;

    public l() {
        this(f188b.toString());
    }

    private l(String str) {
        this.c = str;
        this.d = f187a;
    }

    @Override // com.a.a.b.q
    public final void a(com.a.a.b.h hVar) {
        if (this.c != null) {
            hVar.c(this.c);
        }
    }

    @Override // com.a.a.b.q
    public final void b(com.a.a.b.h hVar) {
        hVar.a('{');
    }

    @Override // com.a.a.b.q
    public final void h(com.a.a.b.h hVar) {
    }

    @Override // com.a.a.b.q
    public final void d(com.a.a.b.h hVar) {
        hVar.a(this.d.b());
    }

    @Override // com.a.a.b.q
    public final void c(com.a.a.b.h hVar) {
        hVar.a(this.d.c());
    }

    @Override // com.a.a.b.q
    public final void a(com.a.a.b.h hVar, int i) {
        hVar.a('}');
    }

    @Override // com.a.a.b.q
    public final void e(com.a.a.b.h hVar) {
        hVar.a('[');
    }

    @Override // com.a.a.b.q
    public final void g(com.a.a.b.h hVar) {
    }

    @Override // com.a.a.b.q
    public final void f(com.a.a.b.h hVar) {
        hVar.a(this.d.d());
    }

    @Override // com.a.a.b.q
    public final void b(com.a.a.b.h hVar, int i) {
        hVar.a(']');
    }
}
