package com.a.a.c.c.b;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/an.class */
public class an extends com.a.a.c.c.d {
    @Deprecated
    private an(com.a.a.c.c.d dVar) {
        super(dVar);
        this.g = false;
    }

    public static an a(com.a.a.c.c.d dVar) {
        return new an(dVar);
    }

    private an(com.a.a.c.c.d dVar, com.a.a.c.m.r rVar) {
        super(dVar, rVar);
    }

    @Override // com.a.a.c.c.d, com.a.a.c.c.f, com.a.a.c.k
    public final com.a.a.c.k<Object> a(com.a.a.c.m.r rVar) {
        if (getClass() != an.class) {
            return this;
        }
        return new an(this, rVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v92, types: [java.lang.Object[]] */
    @Override // com.a.a.c.c.d, com.a.a.c.c.f
    public final Object b(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (this.e != null) {
            return c(lVar, gVar);
        }
        if (this.c != null) {
            return this.f402b.a(gVar, this.c.a(lVar, gVar));
        }
        if (this.f401a.d()) {
            return gVar.a(a(), i(), lVar, "abstract type (need to add/enable type information?)", new Object[0]);
        }
        boolean e = this.f402b.e();
        boolean l = this.f402b.l();
        if (!e && !l) {
            return gVar.a(a(), i(), lVar, "Throwable needs a default constructor, a single-String-arg constructor; or explicit @JsonCreator", new Object[0]);
        }
        Throwable th = null;
        com.a.a.c.c.v[] vVarArr = null;
        Throwable[] thArr = null;
        int i = 0;
        while (!lVar.a(com.a.a.b.o.END_OBJECT)) {
            String v = lVar.v();
            com.a.a.c.c.v a2 = this.h.a(v);
            lVar.g();
            if (a2 != null) {
                if (th != null) {
                    a2.a(lVar, gVar, th);
                } else {
                    if (vVarArr == null) {
                        int b2 = this.h.b();
                        vVarArr = new Object[b2 + b2];
                    }
                    int i2 = i;
                    int i3 = i + 1;
                    vVarArr[i2] = a2;
                    i = i3 + 1;
                    vVarArr[i3] = a2.a(lVar, gVar);
                }
            } else if ("message".equalsIgnoreCase(v) && e) {
                th = (Throwable) this.f402b.a(gVar, lVar.R());
            } else if (this.k != null && this.k.contains(v)) {
                lVar.j();
            } else if ("suppressed".equalsIgnoreCase(v)) {
                thArr = (Throwable[]) gVar.b(lVar, Throwable[].class);
            } else if ("localizedMessage".equalsIgnoreCase(v)) {
                lVar.j();
            } else if (this.j != null) {
                this.j.a(lVar, gVar, th, v);
            } else {
                b(lVar, gVar, th, v);
            }
            lVar.g();
        }
        if (th == null) {
            if (e) {
                th = (Throwable) this.f402b.a(gVar, (String) null);
            } else {
                th = (Throwable) this.f402b.a(gVar);
            }
        }
        if (vVarArr != null) {
            int i4 = i;
            for (int i5 = 0; i5 < i4; i5 += 2) {
                vVarArr[i5].a(th, vVarArr[i5 + 1]);
            }
        }
        if (thArr != null) {
            for (Throwable th2 : thArr) {
                th.addSuppressed(th2);
            }
        }
        return th;
    }
}
