package com.a.a.c.c;

import com.a.a.c.c.a.y;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: infinitode-2.jar:com/a/a/c/c/x.class */
public abstract class x {
    public final x a() {
        return this;
    }

    public Class<?> b() {
        return Object.class;
    }

    public String c() {
        Class<?> b2 = b();
        if (b2 == null) {
            return "UNKNOWN";
        }
        return b2.getName();
    }

    public boolean d() {
        return l() || m() || n() || o() || e() || f() || g() || i() || k();
    }

    public boolean e() {
        return false;
    }

    public boolean f() {
        return false;
    }

    public boolean g() {
        return false;
    }

    public boolean h() {
        return false;
    }

    public boolean i() {
        return false;
    }

    public boolean j() {
        return false;
    }

    public boolean k() {
        return false;
    }

    public boolean l() {
        return r() != null;
    }

    public boolean m() {
        return false;
    }

    public boolean n() {
        return false;
    }

    public boolean o() {
        return false;
    }

    public v[] a(com.a.a.c.f fVar) {
        return null;
    }

    public com.a.a.c.j p() {
        return null;
    }

    public com.a.a.c.j q() {
        return null;
    }

    public Object a(com.a.a.c.g gVar) {
        return gVar.a(b(), this, (com.a.a.b.l) null, "no default no-arguments constructor found", new Object[0]);
    }

    public Object a(com.a.a.c.g gVar, Object[] objArr) {
        return gVar.a(b(), this, (com.a.a.b.l) null, "no creator with arguments specified", new Object[0]);
    }

    public final Object a(com.a.a.c.g gVar, v[] vVarArr, y yVar) {
        return a(gVar, yVar.a(vVarArr));
    }

    public Object a(com.a.a.c.g gVar, Object obj) {
        return gVar.a(b(), this, (com.a.a.b.l) null, "no delegate creator specified", new Object[0]);
    }

    public Object b(com.a.a.c.g gVar, Object obj) {
        return gVar.a(b(), this, (com.a.a.b.l) null, "no array delegate creator specified", new Object[0]);
    }

    public Object a(com.a.a.c.g gVar, String str) {
        return gVar.a(b(), this, gVar.j(), "no String-argument constructor/factory method to deserialize from String value ('%s')", str);
    }

    public Object a(com.a.a.c.g gVar, int i) {
        return gVar.a(b(), this, (com.a.a.b.l) null, "no int/Int-argument constructor/factory method to deserialize from Number value (%s)", Integer.valueOf(i));
    }

    public Object a(com.a.a.c.g gVar, long j) {
        return gVar.a(b(), this, (com.a.a.b.l) null, "no long/Long-argument constructor/factory method to deserialize from Number value (%s)", Long.valueOf(j));
    }

    public Object a(com.a.a.c.g gVar, BigInteger bigInteger) {
        return gVar.a(b(), this, (com.a.a.b.l) null, "no BigInteger-argument constructor/factory method to deserialize from Number value (%s)", bigInteger);
    }

    public Object a(com.a.a.c.g gVar, double d) {
        return gVar.a(b(), this, (com.a.a.b.l) null, "no double/Double-argument constructor/factory method to deserialize from Number value (%s)", Double.valueOf(d));
    }

    public Object a(com.a.a.c.g gVar, BigDecimal bigDecimal) {
        return gVar.a(b(), this, (com.a.a.b.l) null, "no BigDecimal/double/Double-argument constructor/factory method to deserialize from Number value (%s)", bigDecimal);
    }

    public Object a(com.a.a.c.g gVar, boolean z) {
        return gVar.a(b(), this, (com.a.a.b.l) null, "no boolean/Boolean-argument constructor/factory method to deserialize from boolean value (%s)", Boolean.valueOf(z));
    }

    public com.a.a.c.f.o r() {
        return null;
    }

    public com.a.a.c.f.o s() {
        return null;
    }

    public com.a.a.c.f.o t() {
        return null;
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/x$a.class */
    public static class a extends x implements Serializable {

        /* renamed from: a, reason: collision with root package name */
        private Class<?> f417a;

        public a(Class<?> cls) {
            this.f417a = cls;
        }

        public a(com.a.a.c.j jVar) {
            this.f417a = jVar.b();
        }

        @Override // com.a.a.c.c.x
        public final String c() {
            return this.f417a.getName();
        }

        @Override // com.a.a.c.c.x
        public final Class<?> b() {
            return this.f417a;
        }
    }
}
