package com.a.a.c.b;

import com.a.a.a.ac;
import com.a.a.a.l;
import com.a.a.a.s;
import com.a.a.c.b.q;
import com.a.a.c.f.a;
import com.a.a.c.f.ab;
import com.a.a.c.f.ap;
import com.a.a.c.f.t;
import com.a.a.c.x;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: infinitode-2.jar:com/a/a/c/b/q.class */
public abstract class q<T extends q<T>> implements t.a, Serializable {

    /* renamed from: a, reason: collision with root package name */
    protected final long f240a;

    /* renamed from: b, reason: collision with root package name */
    private a f241b;

    public abstract g d(Class<?> cls);

    public abstract s.b e(Class<?> cls);

    public abstract s.b a(Class<?> cls, Class<?> cls2);

    public abstract l.d f(Class<?> cls);

    public abstract ap<?> a(Class<?> cls, com.a.a.c.f.d dVar);

    public abstract ac.a q();

    public abstract Boolean r();

    static {
        s.b.a();
        l.d.a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public q(a aVar, long j) {
        this.f241b = aVar;
        this.f240a = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public q(q<T> qVar, long j) {
        this.f241b = qVar.f241b;
        this.f240a = j;
    }

    public static <F extends Enum<F> & com.a.a.b.c> int a(Class<F> cls) {
        int i = 0;
        for (Object obj : (Enum[]) cls.getEnumConstants()) {
            if (((com.a.a.b.c) obj).a()) {
                i |= ((com.a.a.b.c) obj).b();
            }
        }
        return i;
    }

    public final boolean a(com.a.a.c.q qVar) {
        return qVar.a(this.f240a);
    }

    public final boolean f() {
        return a(com.a.a.c.q.USE_ANNOTATIONS);
    }

    public final boolean g() {
        return a(com.a.a.c.q.CAN_OVERRIDE_ACCESS_MODIFIERS);
    }

    public final boolean h() {
        return a(com.a.a.c.q.SORT_PROPERTIES_ALPHABETICALLY);
    }

    public static com.a.a.b.r a(String str) {
        return new com.a.a.b.c.k(str);
    }

    public final com.a.a.c.f.t i() {
        return this.f241b.a();
    }

    public final com.a.a.c.a j() {
        if (a(com.a.a.c.q.USE_ANNOTATIONS)) {
            return this.f241b.b();
        }
        return ab.f426a;
    }

    public final x k() {
        return this.f241b.c();
    }

    public final a.AbstractC0008a l() {
        return this.f241b.d();
    }

    public final com.a.a.c.k.a.d m() {
        return this.f241b.i();
    }

    public final com.a.a.c.i.h<?> n() {
        return this.f241b.f();
    }

    public final com.a.a.c.i.c o() {
        com.a.a.c.i.c g = this.f241b.g();
        com.a.a.c.i.c cVar = g;
        if (g == com.a.a.c.i.a.l.f503a && a(com.a.a.c.q.BLOCK_UNSAFE_POLYMORPHIC_BASE_TYPES)) {
            cVar = new com.a.a.c.i.a();
        }
        return cVar;
    }

    public final com.a.a.c.l.o p() {
        return this.f241b.e();
    }

    public final com.a.a.c.j b(Class<?> cls) {
        return p().a((Type) cls);
    }

    public final com.a.a.c.b c(Class<?> cls) {
        return d(b(cls));
    }

    public final com.a.a.c.b d(com.a.a.c.j jVar) {
        return i().a((q<?>) this, jVar, (t.a) this);
    }

    public final s.b a(Class<?> cls, s.b bVar) {
        s.b c = d(cls).c();
        if (c != null) {
            return c;
        }
        return bVar;
    }

    public final s.b a(Class<?> cls, Class<?> cls2, s.b bVar) {
        return s.b.a(bVar, d(cls).c(), d(cls2).d());
    }

    public final DateFormat s() {
        return this.f241b.h();
    }

    public final Locale t() {
        return this.f241b.j();
    }

    public final TimeZone u() {
        return this.f241b.k();
    }

    public final com.a.a.b.a v() {
        return this.f241b.l();
    }

    public final com.a.a.c.i.h<?> a(com.a.a.c.f.b bVar, Class<? extends com.a.a.c.i.h<?>> cls) {
        com.a.a.c.i.h<?> g;
        com.a.a.c.k.a.d m = m();
        if (m != null && (g = m.g()) != null) {
            return g;
        }
        return (com.a.a.c.i.h) com.a.a.c.m.i.b(cls, g());
    }

    public final com.a.a.c.i.g b(com.a.a.c.f.b bVar, Class<? extends com.a.a.c.i.g> cls) {
        com.a.a.c.i.g h;
        com.a.a.c.k.a.d m = m();
        if (m != null && (h = m.h()) != null) {
            return h;
        }
        return (com.a.a.c.i.g) com.a.a.c.m.i.b(cls, g());
    }
}
