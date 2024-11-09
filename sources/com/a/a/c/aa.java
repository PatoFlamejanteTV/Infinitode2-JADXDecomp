package com.a.a.c;

import com.a.a.a.al;
import com.a.a.a.l;
import com.a.a.a.s;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: infinitode-2.jar:com/a/a/c/aa.class */
public abstract class aa extends d {

    /* renamed from: b, reason: collision with root package name */
    private static o<Object> f205b = new com.a.a.c.k.a.c("Null key for a Map not allowed in JSON (use a converting NullKeySerializer?)");
    private static o<Object> c = new com.a.a.c.k.a.r();

    /* renamed from: a, reason: collision with root package name */
    protected final y f206a;
    private Class<?> d;
    private com.a.a.c.k.r e;
    private com.a.a.c.g.a f;
    private transient com.a.a.c.b.j g;
    private o<Object> h;
    private o<Object> i;
    private o<Object> j;
    private o<Object> k;
    private com.a.a.c.k.a.l l;
    private DateFormat m;
    private boolean n;

    public abstract com.a.a.c.k.a.v a(Object obj, al<?> alVar);

    public abstract o<Object> b(com.a.a.c.f.b bVar, Object obj);

    public abstract Object a(com.a.a.c.f.s sVar, Class<?> cls);

    public abstract boolean b(Object obj);

    public aa() {
        this.h = c;
        this.j = com.a.a.c.k.b.x.f636a;
        this.k = f205b;
        this.f206a = null;
        this.e = null;
        this.f = new com.a.a.c.g.a();
        this.l = null;
        this.d = null;
        this.g = null;
        this.n = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public aa(aa aaVar, y yVar, com.a.a.c.k.r rVar) {
        this.h = c;
        this.j = com.a.a.c.k.b.x.f636a;
        this.k = f205b;
        this.e = rVar;
        this.f206a = yVar;
        this.f = aaVar.f;
        this.h = aaVar.h;
        this.i = aaVar.i;
        this.j = aaVar.j;
        this.k = aaVar.k;
        this.n = this.j == f205b;
        this.d = yVar.y();
        this.g = yVar.z();
        this.l = this.f.a();
    }

    @Override // com.a.a.c.d
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public final y a() {
        return this.f206a;
    }

    public final a d() {
        return this.f206a.j();
    }

    @Override // com.a.a.c.d
    public final com.a.a.c.l.o b() {
        return this.f206a.p();
    }

    public final j a(j jVar, Class<?> cls) {
        if (jVar.a(cls)) {
            return jVar;
        }
        return a().p().a(jVar, cls, true);
    }

    public final Class<?> e() {
        return this.d;
    }

    public final boolean f() {
        return this.f206a.g();
    }

    public final boolean a(q qVar) {
        return this.f206a.a(qVar);
    }

    public final boolean a(com.a.a.c.b.k kVar) {
        return this.f206a.a(kVar);
    }

    public final l.d a(Class<?> cls) {
        return this.f206a.f(cls);
    }

    public final s.b b(Class<?> cls) {
        return this.f206a.e(cls);
    }

    public final Locale g() {
        return this.f206a.t();
    }

    public final TimeZone h() {
        return this.f206a.u();
    }

    public final Object a(Object obj) {
        return this.g.a(obj);
    }

    public final aa a(Object obj, Object obj2) {
        this.g = this.g.a(obj, obj2);
        return this;
    }

    public final boolean a(z zVar) {
        return this.f206a.a(zVar);
    }

    public final com.a.a.c.k.a.d i() {
        return this.f206a.b();
    }

    public com.a.a.b.h j() {
        return null;
    }

    public final o<Object> a(Class<?> cls, c cVar) {
        o<Object> b2 = this.l.b(cls);
        o<Object> oVar = b2;
        if (b2 == null) {
            o<Object> b3 = this.f.b(cls);
            oVar = b3;
            if (b3 == null) {
                o<Object> a2 = this.f.a(this.f206a.b(cls));
                oVar = a2;
                if (a2 == null) {
                    o<Object> e = e(cls);
                    oVar = e;
                    if (e == null) {
                        return d(cls);
                    }
                }
            }
        }
        return b((o<?>) oVar, cVar);
    }

    public final o<Object> a(j jVar, c cVar) {
        if (jVar == null) {
            b("Null passed for `valueType` of `findValueSerializer()`", new Object[0]);
        }
        o<Object> b2 = this.l.b(jVar);
        o<Object> oVar = b2;
        if (b2 == null) {
            o<Object> a2 = this.f.a(jVar);
            oVar = a2;
            if (a2 == null) {
                o<Object> b3 = b(jVar);
                oVar = b3;
                if (b3 == null) {
                    return d(jVar.b());
                }
            }
        }
        return b((o<?>) oVar, cVar);
    }

    public final o<Object> c(Class<?> cls) {
        o<Object> b2 = this.l.b(cls);
        o<Object> oVar = b2;
        if (b2 == null) {
            o<Object> b3 = this.f.b(cls);
            oVar = b3;
            if (b3 == null) {
                o<Object> a2 = this.f.a(this.f206a.b(cls));
                oVar = a2;
                if (a2 == null) {
                    o<Object> e = e(cls);
                    oVar = e;
                    if (e == null) {
                        oVar = d(cls);
                    }
                }
            }
        }
        return oVar;
    }

    public final o<Object> a(j jVar) {
        o<Object> b2 = this.l.b(jVar);
        o<Object> oVar = b2;
        if (b2 == null) {
            o<Object> a2 = this.f.a(jVar);
            oVar = a2;
            if (a2 == null) {
                o<Object> b3 = b(jVar);
                oVar = b3;
                if (b3 == null) {
                    oVar = d(jVar.b());
                }
            }
        }
        return oVar;
    }

    public final o<Object> b(j jVar, c cVar) {
        o<Object> b2 = this.l.b(jVar);
        o<Object> oVar = b2;
        if (b2 == null) {
            o<Object> a2 = this.f.a(jVar);
            oVar = a2;
            if (a2 == null) {
                o<Object> b3 = b(jVar);
                oVar = b3;
                if (b3 == null) {
                    return d(jVar.b());
                }
            }
        }
        return a((o<?>) oVar, cVar);
    }

    public final o<Object> b(Class<?> cls, c cVar) {
        o<Object> b2 = this.l.b(cls);
        o<Object> oVar = b2;
        if (b2 == null) {
            o<Object> b3 = this.f.b(cls);
            oVar = b3;
            if (b3 == null) {
                o<Object> a2 = this.f.a(this.f206a.b(cls));
                oVar = a2;
                if (a2 == null) {
                    o<Object> e = e(cls);
                    oVar = e;
                    if (e == null) {
                        return d(cls);
                    }
                }
            }
        }
        return a((o<?>) oVar, cVar);
    }

    public final o<Object> c(j jVar, c cVar) {
        o<Object> b2 = this.l.b(jVar);
        o<Object> oVar = b2;
        if (b2 == null) {
            o<Object> a2 = this.f.a(jVar);
            oVar = a2;
            if (a2 == null) {
                o<Object> b3 = b(jVar);
                oVar = b3;
                if (b3 == null) {
                    return d(jVar.b());
                }
            }
        }
        return b((o<?>) oVar, cVar);
    }

    public final o<Object> c(Class<?> cls, c cVar) {
        o<Object> b2 = this.l.b(cls);
        o<Object> oVar = b2;
        if (b2 == null) {
            o<Object> b3 = this.f.b(cls);
            oVar = b3;
            if (b3 == null) {
                o<Object> a2 = this.f.a(this.f206a.b(cls));
                oVar = a2;
                if (a2 == null) {
                    o<Object> e = e(cls);
                    oVar = e;
                    if (e == null) {
                        return d(cls);
                    }
                }
            }
        }
        return b((o<?>) oVar, cVar);
    }

    public final o<Object> a(Class<?> cls, boolean z, c cVar) {
        o<Object> a2 = this.l.a(cls);
        if (a2 != null) {
            return a2;
        }
        o<Object> c2 = this.f.c(cls);
        if (c2 != null) {
            return c2;
        }
        o<Object> a3 = a(cls, cVar);
        com.a.a.c.i.i a4 = this.e.a(this.f206a, this.f206a.b(cls));
        if (a4 != null) {
            a3 = new com.a.a.c.k.a.q(a4.a(cVar), a3);
        }
        this.f.a(cls, a3);
        return a3;
    }

    public final o<Object> a(j jVar, boolean z, c cVar) {
        o<Object> a2 = this.l.a(jVar);
        if (a2 != null) {
            return a2;
        }
        o<Object> b2 = this.f.b(jVar);
        if (b2 != null) {
            return b2;
        }
        o<Object> a3 = a(jVar, cVar);
        com.a.a.c.i.i a4 = this.e.a(this.f206a, jVar);
        if (a4 != null) {
            a3 = new com.a.a.c.k.a.q(a4.a(cVar), a3);
        }
        this.f.a(jVar, a3);
        return a3;
    }

    public final o<Object> d(j jVar, c cVar) {
        return c((o<?>) this.e.a(this, jVar, this.i), cVar);
    }

    public final o<Object> d(Class<?> cls, c cVar) {
        return d(this.f206a.b(cls), cVar);
    }

    public final o<Object> k() {
        return this.j;
    }

    public final o<Object> l() {
        return this.k;
    }

    public final o<Object> m() {
        return this.j;
    }

    public final o<Object> d(Class<?> cls) {
        if (cls == Object.class) {
            return this.h;
        }
        return new com.a.a.c.k.a.r(cls);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final o<?> a(o<?> oVar, c cVar) {
        o<?> oVar2 = oVar;
        if (oVar != 0) {
            boolean z = oVar instanceof com.a.a.c.k.k;
            oVar2 = oVar;
            if (z) {
                oVar2 = ((com.a.a.c.k.k) oVar).a(this, cVar);
            }
        }
        return oVar2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final o<?> b(o<?> oVar, c cVar) {
        o<?> oVar2 = oVar;
        if (oVar != 0) {
            boolean z = oVar instanceof com.a.a.c.k.k;
            oVar2 = oVar;
            if (z) {
                oVar2 = ((com.a.a.c.k.k) oVar).a(this, cVar);
            }
        }
        return oVar2;
    }

    public final void a(Object obj, com.a.a.b.h hVar) {
        if (obj == null) {
            if (this.n) {
                hVar.k();
                return;
            } else {
                this.j.a(null, hVar, this);
                return;
            }
        }
        a(obj.getClass(), true, (c) null).a(obj, hVar, this);
    }

    public final void a(Date date, com.a.a.b.h hVar) {
        if (a(z.WRITE_DATES_AS_TIMESTAMPS)) {
            hVar.b(date.getTime());
        } else {
            hVar.b(n().format(date));
        }
    }

    public final void a(long j, com.a.a.b.h hVar) {
        if (a(z.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
            hVar.a(String.valueOf(j));
        } else {
            hVar.a(n().format(new Date(j)));
        }
    }

    public final void b(Date date, com.a.a.b.h hVar) {
        if (a(z.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
            hVar.a(String.valueOf(date.getTime()));
        } else {
            hVar.a(n().format(date));
        }
    }

    public final void a(com.a.a.b.h hVar) {
        if (this.n) {
            hVar.k();
        } else {
            this.j.a(null, hVar, this);
        }
    }

    public final void b(String str, Object... objArr) {
        throw c(str, objArr);
    }

    public final <T> T a(b bVar, String str, Object... objArr) {
        String str2 = "N/A";
        if (bVar != null) {
            str2 = com.a.a.c.m.i.g(bVar.b());
        }
        throw com.a.a.c.d.b.a(j(), String.format("Invalid type definition for type %s: %s", str2, a(str, objArr)), bVar, (com.a.a.c.f.s) null);
    }

    public final <T> T a(b bVar, com.a.a.c.f.s sVar, String str, Object... objArr) {
        String a2 = a(str, objArr);
        String str2 = "N/A";
        if (sVar != null) {
            str2 = a(sVar.a());
        }
        String str3 = "N/A";
        if (bVar != null) {
            str3 = com.a.a.c.m.i.g(bVar.b());
        }
        throw com.a.a.c.d.b.a(j(), String.format("Invalid definition for property %s (of type %s): %s", str2, str3, a2), bVar, sVar);
    }

    @Override // com.a.a.c.d
    public final <T> T a(j jVar, String str) {
        throw com.a.a.c.d.b.a(j(), str, jVar);
    }

    public final <T> T a(Class<?> cls, String str, Throwable th) {
        throw com.a.a.c.d.b.a(j(), str, a((Type) cls)).a(th);
    }

    private void a(Throwable th, String str, Object... objArr) {
        throw l.a(j(), a(str, objArr), th);
    }

    @Override // com.a.a.c.d
    public final l a(j jVar, String str, String str2) {
        return com.a.a.c.d.e.a(null, a(String.format("Could not resolve type id '%s' as a subtype of %s", str, com.a.a.c.m.i.b(jVar)), str2), jVar, str);
    }

    @Deprecated
    private l c(String str, Object... objArr) {
        return l.a(j(), a(str, objArr));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(Object obj, j jVar) {
        if (jVar.l() && com.a.a.c.m.i.i(jVar.b()).isAssignableFrom(obj.getClass())) {
            return;
        }
        a(jVar, String.format("Incompatible types: declared root type (%s) vs %s", jVar, com.a.a.c.m.i.d(obj)));
    }

    private o<Object> e(Class<?> cls) {
        o<Object> oVar;
        j b2 = this.f206a.b(cls);
        try {
            oVar = c(b2);
        } catch (IllegalArgumentException e) {
            a(b2, com.a.a.c.m.i.g(e));
            oVar = null;
        }
        if (oVar != null) {
            this.f.a(cls, b2, oVar, this);
        }
        return oVar;
    }

    private o<Object> b(j jVar) {
        o<Object> oVar;
        try {
            oVar = c(jVar);
        } catch (IllegalArgumentException e) {
            oVar = null;
            a(e, com.a.a.c.m.i.g(e), new Object[0]);
        }
        if (oVar != null) {
            this.f.a(jVar, oVar, this);
        }
        return oVar;
    }

    private o<Object> c(j jVar) {
        return this.e.a(this, jVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private o<Object> c(o<?> oVar, c cVar) {
        if (oVar instanceof com.a.a.c.k.q) {
            ((com.a.a.c.k.q) oVar).a(this);
        }
        return b(oVar, cVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public final o<Object> a(o<?> oVar) {
        if (oVar instanceof com.a.a.c.k.q) {
            ((com.a.a.c.k.q) oVar).a(this);
        }
        return oVar;
    }

    private DateFormat n() {
        if (this.m != null) {
            return this.m;
        }
        DateFormat dateFormat = (DateFormat) this.f206a.s().clone();
        this.m = dateFormat;
        return dateFormat;
    }
}
