package com.a.a.c.j;

import com.a.a.c.aa;

/* loaded from: infinitode-2.jar:com/a/a/c/j/t.class */
public final class t extends u {

    /* renamed from: a, reason: collision with root package name */
    private static t f552a = new t("");

    /* renamed from: b, reason: collision with root package name */
    private String f553b;

    private t(String str) {
        this.f553b = str;
    }

    public static t d(String str) {
        if (str == null) {
            return null;
        }
        if (str.isEmpty()) {
            return f552a;
        }
        return new t(str);
    }

    @Override // com.a.a.c.m
    public final m d() {
        return m.STRING;
    }

    @Override // com.a.a.c.j.u
    public final com.a.a.b.o p() {
        return com.a.a.b.o.VALUE_STRING;
    }

    @Override // com.a.a.c.m
    public final String i() {
        return this.f553b;
    }

    @Override // com.a.a.c.m
    public final String c(String str) {
        return this.f553b == null ? str : this.f553b;
    }

    @Override // com.a.a.c.m
    public final boolean a(boolean z) {
        if (this.f553b != null) {
            String trim = this.f553b.trim();
            if ("true".equals(trim)) {
                return true;
            }
            if ("false".equals(trim)) {
                return false;
            }
            return false;
        }
        return false;
    }

    @Override // com.a.a.c.m
    public final int b(int i) {
        return com.a.a.b.c.h.a(this.f553b, 0);
    }

    @Override // com.a.a.c.m
    public final double a(double d) {
        return com.a.a.b.c.h.a(this.f553b, 0.0d);
    }

    @Override // com.a.a.c.j.b, com.a.a.c.n
    public final void a(com.a.a.b.h hVar, aa aaVar) {
        if (this.f553b == null) {
            hVar.k();
        } else {
            hVar.b(this.f553b);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof t)) {
            return ((t) obj).f553b.equals(this.f553b);
        }
        return false;
    }

    public final int hashCode() {
        return this.f553b.hashCode();
    }
}
