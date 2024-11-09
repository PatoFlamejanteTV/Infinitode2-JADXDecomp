package com.a.a.c.j;

import com.a.a.c.aa;
import java.math.BigDecimal;

/* loaded from: infinitode-2.jar:com/a/a/c/j/g.class */
public final class g extends q {

    /* renamed from: a, reason: collision with root package name */
    public static final g f531a = new g(BigDecimal.ZERO);

    /* renamed from: b, reason: collision with root package name */
    private BigDecimal f532b;

    static {
        BigDecimal.valueOf(-2147483648L);
        BigDecimal.valueOf(2147483647L);
        BigDecimal.valueOf(Long.MIN_VALUE);
        BigDecimal.valueOf(Long.MAX_VALUE);
    }

    private g(BigDecimal bigDecimal) {
        this.f532b = bigDecimal;
    }

    public static g a(BigDecimal bigDecimal) {
        return new g(bigDecimal);
    }

    @Override // com.a.a.c.j.u
    public final com.a.a.b.o p() {
        return com.a.a.b.o.VALUE_NUMBER_FLOAT;
    }

    @Override // com.a.a.c.j.q, com.a.a.c.m
    public final int g() {
        return this.f532b.intValue();
    }

    @Override // com.a.a.c.j.q, com.a.a.c.m
    public final double h() {
        return this.f532b.doubleValue();
    }

    @Override // com.a.a.c.m
    public final String i() {
        return this.f532b.toString();
    }

    @Override // com.a.a.c.j.b, com.a.a.c.n
    public final void a(com.a.a.b.h hVar, aa aaVar) {
        hVar.a(this.f532b);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && (obj instanceof g) && ((g) obj).f532b.compareTo(this.f532b) == 0;
    }

    public final int hashCode() {
        return Double.valueOf(h()).hashCode();
    }
}
