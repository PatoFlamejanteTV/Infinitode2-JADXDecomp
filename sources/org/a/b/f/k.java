package org.a.b.f;

import java.awt.geom.GeneralPath;

/* loaded from: infinitode-2.jar:org/a/b/f/k.class */
public final class k {

    /* renamed from: a, reason: collision with root package name */
    private short f4302a;

    /* renamed from: b, reason: collision with root package name */
    private short f4303b;
    private short c;
    private short d;
    private short e;
    private i f = null;

    public final void a(p pVar, ak akVar, int i) {
        this.e = akVar.d();
        this.f4302a = akVar.d();
        this.f4303b = akVar.d();
        this.c = akVar.d();
        this.d = akVar.d();
        new org.a.b.h.a(this.f4302a, this.f4303b, this.c, this.d);
        if (this.e >= 0) {
            this.f = new j(this.e, akVar, (short) (i - this.f4302a));
        } else {
            this.f = new h(akVar, pVar);
        }
    }

    public final l a() {
        return this.f;
    }

    public final GeneralPath b() {
        return new m(this.f).a();
    }

    public final short c() {
        return this.d;
    }
}
