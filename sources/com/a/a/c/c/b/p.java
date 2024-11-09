package com.a.a.c.c.b;

import java.io.IOException;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/p.class */
final class p extends ae<Object> implements com.a.a.c.c.k {

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.c.j f357a;

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.f.k f358b;
    private com.a.a.c.k<?> c;
    private com.a.a.c.c.x d;
    private com.a.a.c.c.v[] e;
    private boolean f;
    private transient com.a.a.c.c.a.v g;

    public p(Class<?> cls, com.a.a.c.f.k kVar, com.a.a.c.j jVar, com.a.a.c.c.x xVar, com.a.a.c.c.v[] vVarArr) {
        super(cls);
        this.f358b = kVar;
        this.f = true;
        this.f357a = (jVar.a(String.class) || jVar.a(CharSequence.class)) ? null : jVar;
        this.c = null;
        this.d = xVar;
        this.e = vVarArr;
    }

    public p(Class<?> cls, com.a.a.c.f.k kVar) {
        super(cls);
        this.f358b = kVar;
        this.f = false;
        this.f357a = null;
        this.c = null;
        this.d = null;
        this.e = null;
    }

    private p(p pVar, com.a.a.c.k<?> kVar) {
        super(pVar.s);
        this.f357a = pVar.f357a;
        this.f358b = pVar.f358b;
        this.f = pVar.f;
        this.d = pVar.d;
        this.e = pVar.e;
        this.c = kVar;
    }

    @Override // com.a.a.c.c.k
    public final com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.c cVar) {
        if (this.c == null && this.f357a != null && this.e == null) {
            return new p(this, (com.a.a.c.k<?>) gVar.a(this.f357a, cVar));
        }
        return this;
    }

    @Override // com.a.a.c.k
    public final Boolean a(com.a.a.c.f fVar) {
        return Boolean.FALSE;
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.l.f b() {
        return com.a.a.c.l.f.Enum;
    }

    @Override // com.a.a.c.k
    public final boolean c() {
        return true;
    }

    @Override // com.a.a.c.c.b.ae
    public final com.a.a.c.c.x i() {
        return this.d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v49 */
    /* JADX WARN: Type inference failed for: r0v5, types: [com.a.a.b.l] */
    /* JADX WARN: Type inference failed for: r0v50, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v60, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v64 */
    /* JADX WARN: Type inference failed for: r0v65 */
    /* JADX WARN: Type inference failed for: r0v66 */
    /* JADX WARN: Type inference failed for: r0v67 */
    @Override // com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        Object obj;
        ?? r0;
        if (this.c != null) {
            Object a2 = this.c.a(lVar, gVar);
            obj = a2;
            r0 = a2;
        } else if (this.f) {
            if (this.e != null) {
                if (!lVar.q()) {
                    com.a.a.c.j e = e(gVar);
                    gVar.a(e, "Input mismatch reading Enum %s: properties-based `@JsonCreator` (%s) expects JSON Object (JsonToken.START_OBJECT), got JsonToken.%s", com.a.a.c.m.i.b(e), this.f358b, lVar.k());
                }
                if (this.g == null) {
                    this.g = com.a.a.c.c.a.v.a(gVar, this.d, this.e, gVar.a(com.a.a.c.q.ACCEPT_CASE_INSENSITIVE_PROPERTIES));
                }
                lVar.g();
                return a(lVar, gVar, this.g);
            }
            com.a.a.b.o k = lVar.k();
            com.a.a.b.o oVar = k;
            boolean z = k == com.a.a.b.o.START_ARRAY && gVar.a(com.a.a.c.i.UNWRAP_SINGLE_VALUE_ARRAYS);
            boolean z2 = z;
            if (z) {
                oVar = lVar.g();
            }
            if (oVar == null || !oVar.g()) {
                obj = "";
                lVar.j();
            } else {
                obj = lVar.R();
            }
            boolean z3 = z2;
            r0 = z3;
            if (z3) {
                com.a.a.b.o g = lVar.g();
                r0 = g;
                if (g != com.a.a.b.o.END_ARRAY) {
                    p pVar = this;
                    pVar.j(gVar);
                    r0 = pVar;
                }
            }
        } else {
            ?? j = lVar.j();
            try {
                j = this.f358b.g();
                return j;
            } catch (Exception e2) {
                return gVar.a(this.s, (Object) null, com.a.a.c.m.i.e((Throwable) j));
            }
        }
        try {
            r0 = this.f358b.a((Object) this.s, obj);
            return r0;
        } catch (Exception e3) {
            Throwable e4 = com.a.a.c.m.i.e((Throwable) r0);
            if ((e4 instanceof IllegalArgumentException) && gVar.a(com.a.a.c.i.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                return null;
            }
            return gVar.a(this.s, obj, e4);
        }
    }

    @Override // com.a.a.c.c.b.ae, com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        if (this.c == null) {
            return a(lVar, gVar);
        }
        return eVar.d(lVar, gVar);
    }

    private Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.c.a.v vVar) {
        com.a.a.c.c.a.y a2 = vVar.a(lVar, gVar, null);
        com.a.a.b.o k = lVar.k();
        while (k == com.a.a.b.o.FIELD_NAME) {
            String v = lVar.v();
            lVar.g();
            com.a.a.c.c.v a3 = vVar.a(v);
            if (!a2.a(v) || a3 != null) {
                if (a3 != null) {
                    a2.a(a3, a(lVar, gVar, a3));
                } else {
                    lVar.j();
                }
            }
            k = lVar.g();
        }
        return vVar.a(gVar, a2);
    }

    private Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.c.v vVar) {
        try {
            return vVar.a(lVar, gVar);
        } catch (Exception e) {
            return a(e, a(), vVar.a(), gVar);
        }
    }

    private Object a(Throwable th, Object obj, String str, com.a.a.c.g gVar) {
        throw com.a.a.c.l.a(a(th, gVar), obj, str);
    }

    private static Throwable a(Throwable th, com.a.a.c.g gVar) {
        Throwable d = com.a.a.c.m.i.d(th);
        com.a.a.c.m.i.a(d);
        boolean z = gVar == null || gVar.a(com.a.a.c.i.WRAP_EXCEPTIONS);
        if (d instanceof IOException) {
            if (!z || !(d instanceof com.a.a.b.d)) {
                throw ((IOException) d);
            }
        } else if (!z) {
            com.a.a.c.m.i.b(d);
        }
        return d;
    }
}
