package com.a.a.c;

import com.a.a.a.al;
import com.a.a.a.an;
import com.a.a.a.l;
import com.a.a.c.c.a.ab;
import com.a.a.c.m.ac;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: infinitode-2.jar:com/a/a/c/g.class */
public abstract class g extends d implements Serializable {

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.c.p f486b;
    private com.a.a.c.c.q c;

    /* renamed from: a, reason: collision with root package name */
    protected final f f487a;
    private int d;
    private com.a.a.b.g.i<com.a.a.b.s> e;
    private Class<?> f;
    private transient com.a.a.b.l g;
    private com.a.a.c.k.a.d h;
    private transient com.a.a.c.m.c i;
    private transient com.a.a.c.m.f j;
    private transient DateFormat k;
    private com.a.a.c.m.p<j> l;

    public abstract com.a.a.c.c.a.z a(Object obj, al<?> alVar, an anVar);

    public abstract k<Object> b(com.a.a.c.f.b bVar, Object obj);

    public abstract p c(com.a.a.c.f.b bVar, Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public g(com.a.a.c.c.q qVar, com.a.a.c.c.p pVar) {
        if (qVar == null) {
            throw new NullPointerException("Cannot pass null DeserializerFactory");
        }
        this.c = qVar;
        this.f486b = pVar == null ? new com.a.a.c.c.p() : pVar;
        this.d = 0;
        this.e = null;
        this.f487a = null;
        this.h = null;
        this.f = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public g(g gVar, f fVar, com.a.a.b.l lVar, com.a.a.c.k.a.d dVar) {
        this.f486b = gVar.f486b;
        this.c = gVar.c;
        this.e = lVar == null ? null : lVar.c();
        this.f487a = fVar;
        this.d = fVar.b();
        this.f = fVar.y();
        this.g = lVar;
        this.h = dVar;
        fVar.z();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public g(g gVar, f fVar) {
        this.f486b = gVar.f486b;
        this.c = gVar.c;
        this.e = null;
        this.f487a = fVar;
        this.d = fVar.b();
        this.f = null;
        this.g = null;
        this.h = null;
    }

    @Override // com.a.a.c.d
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public final f a() {
        return this.f487a;
    }

    public final Class<?> d() {
        return this.f;
    }

    public final boolean e() {
        return this.f487a.g();
    }

    public final boolean a(q qVar) {
        return this.f487a.a(qVar);
    }

    public final boolean a(com.a.a.c.b.k kVar) {
        return this.f487a.a(kVar);
    }

    public final l.d a(Class<?> cls) {
        return this.f487a.f(cls);
    }

    public final a f() {
        return this.f487a.j();
    }

    @Override // com.a.a.c.d
    public final com.a.a.c.l.o b() {
        return this.f487a.p();
    }

    public final j a(j jVar, Class<?> cls) {
        if (jVar.a(cls)) {
            return jVar;
        }
        return a().p().a(jVar, cls, false);
    }

    public final Locale g() {
        return this.f487a.t();
    }

    public final TimeZone h() {
        return this.f487a.u();
    }

    public final boolean a(i iVar) {
        return (this.d & iVar.b()) != 0;
    }

    public final boolean a(com.a.a.b.s sVar) {
        return this.e.b(sVar);
    }

    public final int i() {
        return this.d;
    }

    public final boolean a(int i) {
        return (this.d & i) != 0;
    }

    public final com.a.a.b.l j() {
        return this.g;
    }

    public final Object a(Object obj, c cVar, Object obj2) {
        if (this.h == null) {
            return a(com.a.a.c.m.i.a(obj), String.format("No 'injectableValues' configured, cannot inject value with id [%s]", obj));
        }
        return this.h.c();
    }

    public final com.a.a.b.a k() {
        return this.f487a.v();
    }

    public final com.a.a.c.j.l l() {
        return this.f487a.d();
    }

    public final com.a.a.c.b.b a(com.a.a.c.l.f fVar, Class<?> cls, com.a.a.c.b.f fVar2) {
        return this.f487a.a(fVar, cls, fVar2);
    }

    public final com.a.a.c.b.b a(com.a.a.c.l.f fVar, Class<?> cls, com.a.a.c.b.b bVar) {
        return this.f487a.a(fVar, cls, bVar);
    }

    public final ac a(com.a.a.b.l lVar) {
        return new ac(lVar, this);
    }

    public final ac m() {
        return a(j());
    }

    public final ac b(com.a.a.b.l lVar) {
        ac a2 = a(lVar);
        a2.b(lVar);
        return a2;
    }

    public final k<Object> a(j jVar, c cVar) {
        k<Object> a2 = this.f486b.a(this, this.c, jVar);
        k<Object> kVar = a2;
        if (a2 != null) {
            kVar = b((k<?>) kVar, cVar, jVar);
        }
        return kVar;
    }

    public final k<Object> a(j jVar) {
        return this.f486b.a(this, this.c, jVar);
    }

    public final k<Object> b(j jVar) {
        k<Object> a2 = this.f486b.a(this, this.c, jVar);
        if (a2 == null) {
            return null;
        }
        k<?> b2 = b((k<?>) a2, (c) null, jVar);
        com.a.a.c.i.e b3 = this.c.b(this.f487a, jVar);
        if (b3 != null) {
            return new ab(b3.a(null), b2);
        }
        return b2;
    }

    public final p b(j jVar, c cVar) {
        p pVar;
        try {
            pVar = this.f486b.b(this, this.c, jVar);
        } catch (IllegalArgumentException e) {
            a(jVar, com.a.a.c.m.i.g(e));
            pVar = null;
        }
        if (pVar instanceof com.a.a.c.c.l) {
            pVar = ((com.a.a.c.c.l) pVar).a();
        }
        return pVar;
    }

    public final j b(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        return this.f487a.b(cls);
    }

    public final Class<?> b(String str) {
        return b().a(str);
    }

    public final com.a.a.c.m.f n() {
        com.a.a.c.m.f fVar = this.j;
        com.a.a.c.m.f fVar2 = fVar;
        if (fVar == null) {
            fVar2 = new com.a.a.c.m.f();
        } else {
            this.j = null;
        }
        return fVar2;
    }

    public final void a(com.a.a.c.m.f fVar) {
        if (this.j == null || fVar.b() >= this.j.b()) {
            this.j = fVar;
        }
    }

    public final com.a.a.c.m.c o() {
        if (this.i == null) {
            this.i = new com.a.a.c.m.c();
        }
        return this.i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final k<?> a(k<?> kVar, c cVar, j jVar) {
        boolean z = kVar instanceof com.a.a.c.c.k;
        k<?> kVar2 = kVar;
        if (z) {
            this.l = new com.a.a.c.m.p<>(jVar, this.l);
            try {
                kVar2 = ((com.a.a.c.c.k) kVar).a(this, cVar);
            } finally {
                this.l = this.l.a();
            }
        }
        return kVar2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final k<?> b(k<?> kVar, c cVar, j jVar) {
        boolean z = kVar instanceof com.a.a.c.c.k;
        k<?> kVar2 = kVar;
        if (z) {
            this.l = new com.a.a.c.m.p<>(jVar, this.l);
            try {
                kVar2 = ((com.a.a.c.c.k) kVar).a(this, cVar);
            } finally {
                this.l = this.l.a();
            }
        }
        return kVar2;
    }

    public final Date c(String str) {
        try {
            return p().parse(str);
        } catch (ParseException e) {
            throw new IllegalArgumentException(String.format("Failed to parse Date value '%s': %s", str, com.a.a.c.m.i.g(e)));
        }
    }

    public final Calendar a(Date date) {
        Calendar calendar = Calendar.getInstance(h());
        calendar.setTime(date);
        return calendar;
    }

    public final String a(com.a.a.b.l lVar, Class<?> cls) {
        return (String) a(cls, lVar);
    }

    public final <T> T b(com.a.a.b.l lVar, Class<T> cls) {
        return (T) a(lVar, b().a(cls));
    }

    private <T> T a(com.a.a.b.l lVar, j jVar) {
        k<Object> b2 = b(jVar);
        if (b2 == null) {
            return (T) a(jVar, "Could not find JsonDeserializer for type " + com.a.a.c.m.i.b(jVar));
        }
        return (T) b2.a(lVar, this);
    }

    public final boolean a(com.a.a.b.l lVar, k<?> kVar, Object obj, String str) {
        com.a.a.c.m.p<com.a.a.c.c.o> c = this.f487a.c();
        while (true) {
            com.a.a.c.m.p<com.a.a.c.c.o> pVar = c;
            if (pVar == null) {
                break;
            }
            pVar.b();
            c = pVar.a();
        }
        if (!a(i.FAIL_ON_UNKNOWN_PROPERTIES)) {
            lVar.j();
            return true;
        }
        throw com.a.a.c.d.h.a(this.g, obj, str, kVar.d());
    }

    public final Object a(Class<?> cls, String str, String str2, Object... objArr) {
        String a2 = a(str2, objArr);
        com.a.a.c.m.p<com.a.a.c.c.o> c = this.f487a.c();
        while (true) {
            com.a.a.c.m.p<com.a.a.c.c.o> pVar = c;
            if (pVar != null) {
                pVar.b();
                Object a3 = com.a.a.c.c.o.a();
                if (a3 != com.a.a.c.c.o.f408a) {
                    if (a3 == null || cls.isInstance(a3)) {
                        return a3;
                    }
                    throw a(str, cls, String.format("DeserializationProblemHandler.handleWeirdStringValue() for type %s returned value of type %s", com.a.a.c.m.i.c((Object) cls), com.a.a.c.m.i.c(a3)));
                }
                c = pVar.a();
            } else {
                throw a(cls, str, a2);
            }
        }
    }

    public final Object b(Class<?> cls, String str, String str2, Object... objArr) {
        String a2 = a(str2, objArr);
        com.a.a.c.m.p<com.a.a.c.c.o> c = this.f487a.c();
        while (true) {
            com.a.a.c.m.p<com.a.a.c.c.o> pVar = c;
            if (pVar != null) {
                pVar.b();
                Object b2 = com.a.a.c.c.o.b();
                if (b2 != com.a.a.c.c.o.f408a) {
                    if (a(cls, b2)) {
                        return b2;
                    }
                    throw a(str, cls, String.format("DeserializationProblemHandler.handleWeirdStringValue() for type %s returned value of type %s", com.a.a.c.m.i.c((Object) cls), com.a.a.c.m.i.c(b2)));
                }
                c = pVar.a();
            } else {
                throw a(str, cls, a2);
            }
        }
    }

    public final Object a(Class<?> cls, Number number, String str, Object... objArr) {
        String a2 = a(str, objArr);
        com.a.a.c.m.p<com.a.a.c.c.o> c = this.f487a.c();
        while (true) {
            com.a.a.c.m.p<com.a.a.c.c.o> pVar = c;
            if (pVar != null) {
                pVar.b();
                Object c2 = com.a.a.c.c.o.c();
                if (c2 != com.a.a.c.c.o.f408a) {
                    if (a(cls, c2)) {
                        return c2;
                    }
                    throw a(number, cls, a("DeserializationProblemHandler.handleWeirdNumberValue() for type %s returned value of type %s", com.a.a.c.m.i.c((Object) cls), com.a.a.c.m.i.c(c2)));
                }
                c = pVar.a();
            } else {
                throw a(number, cls, a2);
            }
        }
    }

    public final Object a(j jVar, Object obj, com.a.a.b.l lVar) {
        Class<?> b2 = jVar.b();
        for (com.a.a.c.m.p<com.a.a.c.c.o> c = this.f487a.c(); c != null; c = c.a()) {
            c.b();
            Object d = com.a.a.c.c.o.d();
            if (d != com.a.a.c.c.o.f408a) {
                if (d == null || b2.isInstance(d)) {
                    return d;
                }
                throw l.a(lVar, a("DeserializationProblemHandler.handleWeirdNativeValue() for type %s returned value of type %s", com.a.a.c.m.i.c(jVar), com.a.a.c.m.i.c(d)));
            }
        }
        throw a(obj, b2);
    }

    public final Object a(Class<?> cls, com.a.a.c.c.x xVar, com.a.a.b.l lVar, String str, Object... objArr) {
        if (lVar == null) {
            lVar = j();
        }
        String a2 = a(str, objArr);
        com.a.a.c.m.p<com.a.a.c.c.o> c = this.f487a.c();
        while (true) {
            com.a.a.c.m.p<com.a.a.c.c.o> pVar = c;
            if (pVar != null) {
                Object a3 = pVar.b().a(this, cls, lVar, a2);
                if (a3 != com.a.a.c.c.o.f408a) {
                    if (a(cls, a3)) {
                        return a3;
                    }
                    a(b(cls), String.format("DeserializationProblemHandler.handleMissingInstantiator() for type %s returned value of type %s", com.a.a.c.m.i.c((Object) cls), com.a.a.c.m.i.c(a3)));
                }
                c = pVar.a();
            } else {
                if (xVar == null) {
                    return a(cls, String.format("Cannot construct instance of %s: %s", com.a.a.c.m.i.g(cls), a2));
                }
                if (!xVar.d()) {
                    return a(cls, String.format("Cannot construct instance of %s (no Creators, like default constructor, exist): %s", com.a.a.c.m.i.g(cls), a2));
                }
                return a(cls, String.format("Cannot construct instance of %s (although at least one Creator exists): %s", com.a.a.c.m.i.g(cls), a2), new Object[0]);
            }
        }
    }

    public final Object a(Class<?> cls, Object obj, Throwable th) {
        com.a.a.c.m.p<com.a.a.c.c.o> c = this.f487a.c();
        while (true) {
            com.a.a.c.m.p<com.a.a.c.c.o> pVar = c;
            if (pVar != null) {
                pVar.b();
                Object e = com.a.a.c.c.o.e();
                if (e != com.a.a.c.c.o.f408a) {
                    if (a(cls, e)) {
                        return e;
                    }
                    a(b(cls), String.format("DeserializationProblemHandler.handleInstantiationProblem() for type %s returned value of type %s", com.a.a.c.m.i.c((Object) cls), com.a.a.c.m.i.d(e)));
                }
                c = pVar.a();
            } else {
                com.a.a.c.m.i.c(th);
                if (!a(i.WRAP_EXCEPTIONS)) {
                    com.a.a.c.m.i.b(th);
                }
                throw a(cls, th);
            }
        }
    }

    public final Object a(Class<?> cls, com.a.a.b.l lVar) {
        return a(b(cls), lVar.k(), lVar, (String) null, new Object[0]);
    }

    public final Object a(Class<?> cls, com.a.a.b.o oVar, com.a.a.b.l lVar, String str, Object... objArr) {
        return a(b(cls), oVar, lVar, str, objArr);
    }

    public final Object a(j jVar, com.a.a.b.l lVar) {
        return a(jVar, lVar.k(), lVar, (String) null, new Object[0]);
    }

    public final Object a(j jVar, com.a.a.b.o oVar, com.a.a.b.l lVar, String str, Object... objArr) {
        String a2 = a(str, objArr);
        com.a.a.c.m.p<com.a.a.c.c.o> c = this.f487a.c();
        while (true) {
            com.a.a.c.m.p<com.a.a.c.c.o> pVar = c;
            if (pVar != null) {
                Object a3 = pVar.b().a(this, jVar, oVar, lVar, a2);
                if (a3 != com.a.a.c.c.o.f408a) {
                    if (a(jVar.b(), a3)) {
                        return a3;
                    }
                    a(jVar, String.format("DeserializationProblemHandler.handleUnexpectedToken() for type %s returned value of type %s", com.a.a.c.m.i.b(jVar), com.a.a.c.m.i.d(a3)));
                }
                c = pVar.a();
            } else {
                if (a2 == null) {
                    String b2 = com.a.a.c.m.i.b(jVar);
                    if (oVar == null) {
                        a2 = String.format("Unexpected end-of-input when trying read value of type %s", b2);
                    } else {
                        a2 = String.format("Cannot deserialize value of type %s from %s (token `JsonToken.%s`)", b2, a(oVar), oVar);
                    }
                }
                if (oVar != null && oVar.g()) {
                    lVar.w();
                }
                a(jVar, a2, new Object[0]);
                return null;
            }
        }
    }

    public final j a(j jVar, String str, com.a.a.c.i.g gVar, String str2) {
        com.a.a.c.m.p<com.a.a.c.c.o> c = this.f487a.c();
        while (true) {
            com.a.a.c.m.p<com.a.a.c.c.o> pVar = c;
            if (pVar != null) {
                pVar.b();
                j f = com.a.a.c.c.o.f();
                if (f != null) {
                    if (f.a(Void.class)) {
                        return null;
                    }
                    if (f.b(jVar.b())) {
                        return f;
                    }
                    throw a(jVar, str, "problem handler tried to resolve into non-subtype: " + com.a.a.c.m.i.b(f));
                }
                c = pVar.a();
            } else {
                if (!a(i.FAIL_ON_INVALID_SUBTYPE)) {
                    return null;
                }
                throw a(jVar, str, str2);
            }
        }
    }

    public final j a(j jVar, com.a.a.c.i.g gVar, String str) {
        com.a.a.c.m.p<com.a.a.c.c.o> c = this.f487a.c();
        while (true) {
            com.a.a.c.m.p<com.a.a.c.c.o> pVar = c;
            if (pVar != null) {
                pVar.b();
                j g = com.a.a.c.c.o.g();
                if (g != null) {
                    if (g.a(Void.class)) {
                        return null;
                    }
                    if (g.b(jVar.b())) {
                        return g;
                    }
                    throw a(jVar, (String) null, "problem handler tried to resolve into non-subtype: " + com.a.a.c.m.i.b(g));
                }
                c = pVar.a();
            } else {
                throw b(jVar, str);
            }
        }
    }

    public final void a(k<?> kVar) {
        if (!a(q.IGNORE_MERGE_FOR_UNMERGEABLE)) {
            j b2 = b(kVar.a());
            throw com.a.a.c.d.b.a(j(), String.format("Invalid configuration: values of type %s cannot be merged", com.a.a.c.m.i.b(b2)), b2);
        }
    }

    private static boolean a(Class<?> cls, Object obj) {
        if (obj == null || cls.isInstance(obj)) {
            return true;
        }
        return cls.isPrimitive() && com.a.a.c.m.i.i(cls).isInstance(obj);
    }

    public final void a(k<?> kVar, com.a.a.b.o oVar, String str, Object... objArr) {
        throw a(j(), kVar.a(), oVar, a(str, objArr));
    }

    public final void a(j jVar, com.a.a.b.o oVar, String str, Object... objArr) {
        throw a(j(), jVar, oVar, a(str, objArr));
    }

    public final void a(Class<?> cls, com.a.a.b.o oVar, String str, Object... objArr) {
        throw a(j(), cls, oVar, a(str, objArr));
    }

    public final <T> T a(com.a.a.c.c.a.s sVar, Object obj) {
        return (T) a(sVar.d, String.format("No Object Id found for an instance of %s, to assign to property '%s'", com.a.a.c.m.i.d(obj), sVar.f280a), new Object[0]);
    }

    public final <T> T a(k<?> kVar, String str, Object... objArr) {
        throw com.a.a.c.d.f.a(j(), kVar.a(), a(str, objArr));
    }

    public final <T> T a(Class<?> cls, String str, Object... objArr) {
        throw com.a.a.c.d.f.a(j(), cls, a(str, objArr));
    }

    public final <T> T a(j jVar, String str, Object... objArr) {
        throw com.a.a.c.d.f.a(j(), jVar, a(str, objArr));
    }

    public final <T> T a(c cVar, String str, Object... objArr) {
        com.a.a.c.f.j e;
        com.a.a.c.d.f a2 = com.a.a.c.d.f.a(j(), cVar == null ? null : cVar.c(), a(str, objArr));
        if (cVar != null && (e = cVar.e()) != null) {
            a2.a(e.h(), cVar.a());
        }
        throw a2;
    }

    public final <T> T c(Class<?> cls, String str, String str2, Object... objArr) {
        com.a.a.c.d.f a2 = com.a.a.c.d.f.a(j(), cls, a(str2, objArr));
        if (str != null) {
            a2.a(cls, str);
        }
        throw a2;
    }

    public final <T> T a(j jVar, String str, String str2, Object... objArr) {
        return (T) c(jVar.b(), str, str2, objArr);
    }

    public final <T> T a(Class<?> cls, Object obj, String str, Object... objArr) {
        throw com.a.a.c.d.c.a(j(), a(str, objArr), obj, cls);
    }

    public static <T> T a(Class<?> cls, com.a.a.b.l lVar, com.a.a.b.o oVar) {
        throw com.a.a.c.d.f.a(lVar, cls, String.format("Trailing token (of type %s) found after value (bound as %s): not allowed as per `DeserializationFeature.FAIL_ON_TRAILING_TOKENS`", oVar, com.a.a.c.m.i.g(cls)));
    }

    public final <T> T a(b bVar, String str, Object... objArr) {
        throw com.a.a.c.d.b.a(this.g, String.format("Invalid type definition for type %s: %s", com.a.a.c.m.i.g(bVar.b()), a(str, objArr)), bVar, (com.a.a.c.f.s) null);
    }

    public final <T> T a(b bVar, com.a.a.c.f.s sVar, String str, Object... objArr) {
        throw com.a.a.c.d.b.a(this.g, String.format("Invalid definition for property %s (of type %s): %s", com.a.a.c.m.i.a((com.a.a.c.m.v) sVar), com.a.a.c.m.i.g(bVar.b()), a(str, objArr)), bVar, sVar);
    }

    @Override // com.a.a.c.d
    public final <T> T a(j jVar, String str) {
        throw com.a.a.c.d.b.a(this.g, str, jVar);
    }

    private l a(com.a.a.b.l lVar, j jVar, com.a.a.b.o oVar, String str) {
        return com.a.a.c.d.f.a(lVar, jVar, a(String.format("Unexpected token (%s), expected %s", lVar.k(), oVar), str));
    }

    private l a(com.a.a.b.l lVar, Class<?> cls, com.a.a.b.o oVar, String str) {
        return com.a.a.c.d.f.a(lVar, cls, a(String.format("Unexpected token (%s), expected %s", lVar.k(), oVar), str));
    }

    private l a(Class<?> cls, String str, String str2) {
        return com.a.a.c.d.c.a(this.g, String.format("Cannot deserialize Map key of type %s from String %s: %s", com.a.a.c.m.i.g(cls), a(str), str2), str, cls);
    }

    public final l a(String str, Class<?> cls, String str2) {
        return com.a.a.c.d.c.a(this.g, String.format("Cannot deserialize value of type %s from String %s: %s", com.a.a.c.m.i.g(cls), a(str), str2), str, cls);
    }

    private l a(Number number, Class<?> cls, String str) {
        return com.a.a.c.d.c.a(this.g, String.format("Cannot deserialize value of type %s from number %s: %s", com.a.a.c.m.i.g(cls), String.valueOf(number), str), number, cls);
    }

    private l a(Object obj, Class<?> cls) {
        return com.a.a.c.d.c.a(this.g, String.format("Cannot deserialize value of type %s from native value (`JsonToken.VALUE_EMBEDDED_OBJECT`) of type %s: incompatible types", com.a.a.c.m.i.g(cls), com.a.a.c.m.i.d(obj)), obj, cls);
    }

    public final l a(Class<?> cls, Throwable th) {
        String str;
        if (th == null) {
            str = "N/A";
        } else {
            String g = com.a.a.c.m.i.g(th);
            str = g;
            if (g == null) {
                str = com.a.a.c.m.i.g(th.getClass());
            }
        }
        return com.a.a.c.d.i.a(this.g, String.format("Cannot construct instance of %s, problem: %s", com.a.a.c.m.i.g(cls), str), b(cls), th);
    }

    @Override // com.a.a.c.d
    public final l a(j jVar, String str, String str2) {
        return com.a.a.c.d.e.a(this.g, a(String.format("Could not resolve type id '%s' as a subtype of %s", str, com.a.a.c.m.i.b(jVar)), str2), jVar, str);
    }

    private l b(j jVar, String str) {
        return com.a.a.c.d.e.a(this.g, a(String.format("Could not resolve subtype of %s", jVar), str), jVar, null);
    }

    private DateFormat p() {
        if (this.k != null) {
            return this.k;
        }
        DateFormat dateFormat = (DateFormat) this.f487a.s().clone();
        this.k = dateFormat;
        return dateFormat;
    }

    private static String a(com.a.a.b.o oVar) {
        if (oVar != null) {
            switch (h.f496a[oVar.ordinal()]) {
                case 1:
                case 2:
                case 3:
                    return "Object value";
                case 4:
                case 5:
                    return "Array value";
                case 6:
                case 7:
                    return "Boolean value";
                case 8:
                    return "Embedded Object";
                case 9:
                    return "Floating-point value";
                case 10:
                    return "Integer value";
                case 11:
                    return "String value";
                case 12:
                    return "Null value";
                default:
                    return "[Unavailable value]";
            }
        }
        return "<end of input>";
    }
}
