package com.a.a.c.j;

import com.a.a.c.aa;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/a/a/c/j/d.class */
public final class d extends u {

    /* renamed from: a, reason: collision with root package name */
    private static d f526a = new d(new byte[0]);

    /* renamed from: b, reason: collision with root package name */
    private byte[] f527b;

    private d(byte[] bArr) {
        this.f527b = bArr;
    }

    public static d a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return f526a;
        }
        return new d(bArr);
    }

    @Override // com.a.a.c.m
    public final m d() {
        return m.BINARY;
    }

    @Override // com.a.a.c.j.u
    public final com.a.a.b.o p() {
        return com.a.a.b.o.VALUE_EMBEDDED_OBJECT;
    }

    @Override // com.a.a.c.m
    public final String i() {
        return com.a.a.b.b.a().a(this.f527b, false);
    }

    @Override // com.a.a.c.j.b, com.a.a.c.n
    public final void a(com.a.a.b.h hVar, aa aaVar) {
        hVar.a(aaVar.a().v(), this.f527b, 0, this.f527b.length);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof d)) {
            return false;
        }
        return Arrays.equals(((d) obj).f527b, this.f527b);
    }

    public final int hashCode() {
        if (this.f527b == null) {
            return -1;
        }
        return this.f527b.length;
    }
}
