package com.d.e;

import java.awt.Point;

/* loaded from: infinitode-2.jar:com/d/e/b.class */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private int f1124a = 0;

    /* renamed from: b, reason: collision with root package name */
    private int f1125b = 0;
    private final z c;

    public b(com.d.i.c cVar, v vVar) {
        this.c = new z(cVar);
    }

    public final Point a() {
        return new Point(this.f1124a, this.f1125b);
    }

    public final void a(int i, int i2) {
        this.f1124a -= i;
        this.f1125b -= i2;
    }

    public final n b() {
        return this.c.a();
    }

    public final int a(com.d.c.f.d dVar, com.d.i.u uVar, int i) {
        return b().b(dVar, this, uVar, i);
    }

    public final int b(com.d.c.f.d dVar, com.d.i.u uVar, int i) {
        return b().c(dVar, this, uVar, i);
    }

    public final int c(com.d.c.f.d dVar, com.d.i.u uVar, int i) {
        return a(dVar, uVar, i) + b(dVar, uVar, i);
    }

    public final int d(com.d.c.f.d dVar, com.d.i.u uVar, int i) {
        return b().a(dVar, this, uVar, i);
    }

    public final void a(v vVar, com.d.i.c cVar) {
        b().a(vVar, vVar.o(), this, cVar);
    }

    public final void a(v vVar, com.d.i.f fVar) {
        b().a(vVar, this, fVar);
    }

    public final String toString() {
        return "BlockFormattingContext: (" + this.f1124a + "," + this.f1125b + ")";
    }
}
