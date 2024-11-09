package com.a.a.c.j;

import com.a.a.c.aa;

/* loaded from: infinitode-2.jar:com/a/a/c/j/e.class */
public final class e extends u {

    /* renamed from: a, reason: collision with root package name */
    private static e f528a = new e(true);

    /* renamed from: b, reason: collision with root package name */
    private static e f529b = new e(false);
    private final boolean c;

    private e(boolean z) {
        this.c = z;
    }

    public static e q() {
        return f528a;
    }

    public static e r() {
        return f529b;
    }

    @Override // com.a.a.c.m
    public final m d() {
        return m.BOOLEAN;
    }

    @Override // com.a.a.c.j.u
    public final com.a.a.b.o p() {
        return this.c ? com.a.a.b.o.VALUE_TRUE : com.a.a.b.o.VALUE_FALSE;
    }

    @Override // com.a.a.c.m
    public final String i() {
        return this.c ? "true" : "false";
    }

    @Override // com.a.a.c.m
    public final boolean l() {
        return this.c;
    }

    @Override // com.a.a.c.m
    public final boolean a(boolean z) {
        return this.c;
    }

    @Override // com.a.a.c.m
    public final int b(int i) {
        return this.c ? 1 : 0;
    }

    @Override // com.a.a.c.m
    public final double a(double d) {
        return this.c ? 1.0d : 0.0d;
    }

    @Override // com.a.a.c.j.b, com.a.a.c.n
    public final void a(com.a.a.b.h hVar, aa aaVar) {
        hVar.a(this.c);
    }

    public final int hashCode() {
        return this.c ? 3 : 1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && (obj instanceof e) && this.c == ((e) obj).c;
    }
}
