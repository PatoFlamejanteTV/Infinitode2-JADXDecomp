package com.a.a.c.c.b;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/c/b/aj.class */
public final class aj extends com.a.a.c.c.x implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private String f320a;

    /* renamed from: b, reason: collision with root package name */
    private Class<?> f321b;
    private com.a.a.c.f.o c;
    private com.a.a.c.f.o d;
    private com.a.a.c.c.v[] e;
    private com.a.a.c.j f;
    private com.a.a.c.f.o g;
    private com.a.a.c.c.v[] h;
    private com.a.a.c.j i;
    private com.a.a.c.f.o j;
    private com.a.a.c.c.v[] k;
    private com.a.a.c.f.o l;
    private com.a.a.c.f.o m;
    private com.a.a.c.f.o n;
    private com.a.a.c.f.o o;
    private com.a.a.c.f.o p;
    private com.a.a.c.f.o q;
    private com.a.a.c.f.o r;

    public aj(com.a.a.c.j jVar) {
        this.f320a = jVar == null ? "UNKNOWN TYPE" : jVar.toString();
        this.f321b = jVar == null ? Object.class : jVar.b();
    }

    public final void a(com.a.a.c.f.o oVar, com.a.a.c.f.o oVar2, com.a.a.c.j jVar, com.a.a.c.c.v[] vVarArr, com.a.a.c.f.o oVar3, com.a.a.c.c.v[] vVarArr2) {
        this.c = oVar;
        this.g = oVar2;
        this.f = jVar;
        this.h = vVarArr;
        this.d = oVar3;
        this.e = vVarArr2;
    }

    public final void a(com.a.a.c.f.o oVar, com.a.a.c.j jVar, com.a.a.c.c.v[] vVarArr) {
        this.j = oVar;
        this.i = jVar;
        this.k = vVarArr;
    }

    public final void a(com.a.a.c.f.o oVar) {
        this.l = oVar;
    }

    public final void b(com.a.a.c.f.o oVar) {
        this.m = oVar;
    }

    public final void c(com.a.a.c.f.o oVar) {
        this.n = oVar;
    }

    public final void d(com.a.a.c.f.o oVar) {
        this.o = oVar;
    }

    public final void e(com.a.a.c.f.o oVar) {
        this.p = oVar;
    }

    public final void f(com.a.a.c.f.o oVar) {
        this.q = oVar;
    }

    public final void g(com.a.a.c.f.o oVar) {
        this.r = oVar;
    }

    @Override // com.a.a.c.c.x
    public final String c() {
        return this.f320a;
    }

    @Override // com.a.a.c.c.x
    public final Class<?> b() {
        return this.f321b;
    }

    @Override // com.a.a.c.c.x
    public final boolean e() {
        return this.l != null;
    }

    @Override // com.a.a.c.c.x
    public final boolean f() {
        return this.m != null;
    }

    @Override // com.a.a.c.c.x
    public final boolean g() {
        return this.n != null;
    }

    @Override // com.a.a.c.c.x
    public final boolean h() {
        return this.o != null;
    }

    @Override // com.a.a.c.c.x
    public final boolean i() {
        return this.p != null;
    }

    @Override // com.a.a.c.c.x
    public final boolean j() {
        return this.q != null;
    }

    @Override // com.a.a.c.c.x
    public final boolean k() {
        return this.r != null;
    }

    @Override // com.a.a.c.c.x
    public final boolean l() {
        return this.c != null;
    }

    @Override // com.a.a.c.c.x
    public final boolean m() {
        return this.f != null;
    }

    @Override // com.a.a.c.c.x
    public final boolean n() {
        return this.i != null;
    }

    @Override // com.a.a.c.c.x
    public final boolean o() {
        return this.d != null;
    }

    @Override // com.a.a.c.c.x
    public final boolean d() {
        return l() || m() || n() || o() || e() || f() || g() || i() || k();
    }

    @Override // com.a.a.c.c.x
    public final com.a.a.c.j p() {
        return this.f;
    }

    @Override // com.a.a.c.c.x
    public final com.a.a.c.j q() {
        return this.i;
    }

    @Override // com.a.a.c.c.x
    public final com.a.a.c.c.v[] a(com.a.a.c.f fVar) {
        return this.e;
    }

    @Override // com.a.a.c.c.x
    public final Object a(com.a.a.c.g gVar) {
        if (this.c == null) {
            return super.a(gVar);
        }
        try {
            return this.c.g();
        } catch (Exception e) {
            return gVar.a(this.f321b, (Object) null, b(gVar, (Throwable) e));
        }
    }

    @Override // com.a.a.c.c.x
    public final Object a(com.a.a.c.g gVar, Object[] objArr) {
        if (this.d == null) {
            return super.a(gVar, objArr);
        }
        try {
            return this.d.a(objArr);
        } catch (Exception e) {
            return gVar.a(this.f321b, objArr, b(gVar, (Throwable) e));
        }
    }

    @Override // com.a.a.c.c.x
    public final Object a(com.a.a.c.g gVar, Object obj) {
        if (this.g == null && this.j != null) {
            return a(this.j, this.k, gVar, obj);
        }
        return a(this.g, this.h, gVar, obj);
    }

    @Override // com.a.a.c.c.x
    public final Object b(com.a.a.c.g gVar, Object obj) {
        if (this.j == null && this.g != null) {
            return a(gVar, obj);
        }
        return a(this.j, this.k, gVar, obj);
    }

    @Override // com.a.a.c.c.x
    public final Object a(com.a.a.c.g gVar, String str) {
        if (this.l != null) {
            try {
                return this.l.a(str);
            } catch (Throwable th) {
                return gVar.a(this.l.h(), str, b(gVar, th));
            }
        }
        return super.a(gVar, str);
    }

    @Override // com.a.a.c.c.x
    public final Object a(com.a.a.c.g gVar, int i) {
        if (this.m != null) {
            Integer valueOf = Integer.valueOf(i);
            try {
                return this.m.a(valueOf);
            } catch (Throwable th) {
                return gVar.a(this.m.h(), valueOf, b(gVar, th));
            }
        }
        if (this.n != null) {
            Long valueOf2 = Long.valueOf(i);
            try {
                return this.n.a(valueOf2);
            } catch (Throwable th2) {
                return gVar.a(this.n.h(), valueOf2, b(gVar, th2));
            }
        }
        if (this.o != null) {
            BigInteger valueOf3 = BigInteger.valueOf(i);
            try {
                return this.o.a(valueOf3);
            } catch (Throwable th3) {
                return gVar.a(this.o.h(), valueOf3, b(gVar, th3));
            }
        }
        return super.a(gVar, i);
    }

    @Override // com.a.a.c.c.x
    public final Object a(com.a.a.c.g gVar, long j) {
        if (this.n != null) {
            Long valueOf = Long.valueOf(j);
            try {
                return this.n.a(valueOf);
            } catch (Throwable th) {
                return gVar.a(this.n.h(), valueOf, b(gVar, th));
            }
        }
        if (this.o != null) {
            BigInteger valueOf2 = BigInteger.valueOf(j);
            try {
                return this.o.a(valueOf2);
            } catch (Throwable th2) {
                return gVar.a(this.o.h(), valueOf2, b(gVar, th2));
            }
        }
        return super.a(gVar, j);
    }

    @Override // com.a.a.c.c.x
    public final Object a(com.a.a.c.g gVar, BigInteger bigInteger) {
        if (this.o != null) {
            try {
                return this.o.a(bigInteger);
            } catch (Throwable th) {
                return gVar.a(this.o.h(), bigInteger, b(gVar, th));
            }
        }
        return super.a(gVar, bigInteger);
    }

    @Override // com.a.a.c.c.x
    public final Object a(com.a.a.c.g gVar, double d) {
        if (this.p != null) {
            Double valueOf = Double.valueOf(d);
            try {
                return this.p.a(valueOf);
            } catch (Throwable th) {
                return gVar.a(this.p.h(), valueOf, b(gVar, th));
            }
        }
        if (this.q != null) {
            BigDecimal valueOf2 = BigDecimal.valueOf(d);
            try {
                return this.q.a(valueOf2);
            } catch (Throwable th2) {
                return gVar.a(this.q.h(), valueOf2, b(gVar, th2));
            }
        }
        return super.a(gVar, d);
    }

    @Override // com.a.a.c.c.x
    public final Object a(com.a.a.c.g gVar, BigDecimal bigDecimal) {
        Double a2;
        if (this.q != null) {
            try {
                return this.q.a(bigDecimal);
            } catch (Throwable th) {
                return gVar.a(this.q.h(), bigDecimal, b(gVar, th));
            }
        }
        if (this.p != null && (a2 = a(bigDecimal)) != null) {
            try {
                return this.p.a(a2);
            } catch (Throwable th2) {
                return gVar.a(this.p.h(), a2, b(gVar, th2));
            }
        }
        return super.a(gVar, bigDecimal);
    }

    private static Double a(BigDecimal bigDecimal) {
        double doubleValue = bigDecimal.doubleValue();
        if (Double.isInfinite(doubleValue)) {
            return null;
        }
        return Double.valueOf(doubleValue);
    }

    @Override // com.a.a.c.c.x
    public final Object a(com.a.a.c.g gVar, boolean z) {
        if (this.r == null) {
            return super.a(gVar, z);
        }
        Boolean valueOf = Boolean.valueOf(z);
        try {
            return this.r.a(valueOf);
        } catch (Throwable th) {
            return gVar.a(this.r.h(), valueOf, b(gVar, th));
        }
    }

    @Override // com.a.a.c.c.x
    public final com.a.a.c.f.o s() {
        return this.g;
    }

    @Override // com.a.a.c.c.x
    public final com.a.a.c.f.o t() {
        return this.j;
    }

    @Override // com.a.a.c.c.x
    public final com.a.a.c.f.o r() {
        return this.c;
    }

    private com.a.a.c.l a(com.a.a.c.g gVar, Throwable th) {
        if (th instanceof com.a.a.c.l) {
            return (com.a.a.c.l) th;
        }
        return gVar.a(b(), th);
    }

    private com.a.a.c.l b(com.a.a.c.g gVar, Throwable th) {
        Throwable cause;
        if (((th instanceof ExceptionInInitializerError) || (th instanceof InvocationTargetException)) && (cause = th.getCause()) != null) {
            th = cause;
        }
        return a(gVar, th);
    }

    private Object a(com.a.a.c.f.o oVar, com.a.a.c.c.v[] vVarArr, com.a.a.c.g gVar, Object obj) {
        if (oVar == null) {
            throw new IllegalStateException("No delegate constructor for " + c());
        }
        try {
            if (vVarArr == null) {
                return oVar.a(obj);
            }
            int length = vVarArr.length;
            Object[] objArr = new Object[length];
            for (int i = 0; i < length; i++) {
                com.a.a.c.c.v vVar = vVarArr[i];
                if (vVar == null) {
                    objArr[i] = obj;
                } else {
                    objArr[i] = gVar.a(vVar.i(), vVar, (Object) null);
                }
            }
            return oVar.a(objArr);
        } catch (Throwable th) {
            throw b(gVar, th);
        }
    }
}
