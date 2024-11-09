package com.a.a.c.j;

import com.a.a.c.aa;
import java.math.BigInteger;

/* loaded from: infinitode-2.jar:com/a/a/c/j/c.class */
public final class c extends q {

    /* renamed from: a, reason: collision with root package name */
    private BigInteger f525a;

    static {
        BigInteger.valueOf(-2147483648L);
        BigInteger.valueOf(2147483647L);
        BigInteger.valueOf(Long.MIN_VALUE);
        BigInteger.valueOf(Long.MAX_VALUE);
    }

    private c(BigInteger bigInteger) {
        this.f525a = bigInteger;
    }

    public static c a(BigInteger bigInteger) {
        return new c(bigInteger);
    }

    @Override // com.a.a.c.j.u
    public final com.a.a.b.o p() {
        return com.a.a.b.o.VALUE_NUMBER_INT;
    }

    @Override // com.a.a.c.j.q, com.a.a.c.m
    public final int g() {
        return this.f525a.intValue();
    }

    @Override // com.a.a.c.j.q, com.a.a.c.m
    public final double h() {
        return this.f525a.doubleValue();
    }

    @Override // com.a.a.c.m
    public final String i() {
        return this.f525a.toString();
    }

    @Override // com.a.a.c.m
    public final boolean a(boolean z) {
        return !BigInteger.ZERO.equals(this.f525a);
    }

    @Override // com.a.a.c.j.b, com.a.a.c.n
    public final void a(com.a.a.b.h hVar, aa aaVar) {
        hVar.a(this.f525a);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof c)) {
            return false;
        }
        return ((c) obj).f525a.equals(this.f525a);
    }

    public final int hashCode() {
        return this.f525a.hashCode();
    }
}
