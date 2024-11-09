package com.a.a.c.j;

import com.a.a.c.aa;

/* loaded from: infinitode-2.jar:com/a/a/c/j/j.class */
public final class j extends q {

    /* renamed from: a, reason: collision with root package name */
    private static final j[] f535a = new j[12];

    /* renamed from: b, reason: collision with root package name */
    private int f536b;

    static {
        for (int i = 0; i < 12; i++) {
            f535a[i] = new j(i - 1);
        }
    }

    private j(int i) {
        this.f536b = i;
    }

    public static j c(int i) {
        return (i > 10 || i < -1) ? new j(i) : f535a[i - (-1)];
    }

    @Override // com.a.a.c.j.u
    public final com.a.a.b.o p() {
        return com.a.a.b.o.VALUE_NUMBER_INT;
    }

    @Override // com.a.a.c.j.q, com.a.a.c.m
    public final int g() {
        return this.f536b;
    }

    @Override // com.a.a.c.j.q, com.a.a.c.m
    public final double h() {
        return this.f536b;
    }

    @Override // com.a.a.c.m
    public final String i() {
        return com.a.a.b.c.i.a(this.f536b);
    }

    @Override // com.a.a.c.m
    public final boolean a(boolean z) {
        return this.f536b != 0;
    }

    @Override // com.a.a.c.j.b, com.a.a.c.n
    public final void a(com.a.a.b.h hVar, aa aaVar) {
        hVar.c(this.f536b);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && (obj instanceof j) && ((j) obj).f536b == this.f536b;
    }

    public final int hashCode() {
        return this.f536b;
    }
}
