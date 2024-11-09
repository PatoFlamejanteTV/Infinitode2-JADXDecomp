package com.a.a.c;

import com.a.a.a.ac;
import com.a.a.a.b;
import com.a.a.a.i;
import com.a.a.a.l;
import com.a.a.a.q;
import com.a.a.a.s;
import com.a.a.a.t;
import com.a.a.a.x;
import com.a.a.c.a.e;
import com.a.a.c.a.f;
import com.a.a.c.f.ab;
import com.a.a.c.f.ad;
import com.a.a.c.f.ap;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.List;

/* loaded from: infinitode-2.jar:com/a/a/c/a.class */
public abstract class a implements Serializable {

    /* renamed from: com.a.a.c.a$a, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:com/a/a/c/a$a.class */
    public static class C0004a {

        /* renamed from: a, reason: collision with root package name */
        private final EnumC0005a f195a;

        /* renamed from: b, reason: collision with root package name */
        private final String f196b;

        /* renamed from: com.a.a.c.a$a$a, reason: collision with other inner class name */
        /* loaded from: infinitode-2.jar:com/a/a/c/a$a$a.class */
        public enum EnumC0005a {
            MANAGED_REFERENCE,
            BACK_REFERENCE
        }

        private C0004a(EnumC0005a enumC0005a, String str) {
            this.f195a = enumC0005a;
            this.f196b = str;
        }

        public static C0004a a(String str) {
            return new C0004a(EnumC0005a.MANAGED_REFERENCE, str);
        }

        public static C0004a b(String str) {
            return new C0004a(EnumC0005a.BACK_REFERENCE, str);
        }

        public final String a() {
            return this.f196b;
        }

        public final boolean b() {
            return this.f195a == EnumC0005a.MANAGED_REFERENCE;
        }

        public final boolean c() {
            return this.f195a == EnumC0005a.BACK_REFERENCE;
        }
    }

    public static a a() {
        return ab.f426a;
    }

    public boolean a(Annotation annotation) {
        return false;
    }

    public ad a(com.a.a.c.f.b bVar) {
        return null;
    }

    public ad a(com.a.a.c.f.b bVar, ad adVar) {
        return adVar;
    }

    public w a(com.a.a.c.f.d dVar) {
        return null;
    }

    public Boolean b(com.a.a.c.f.d dVar) {
        return null;
    }

    public q.a b(com.a.a.c.f.b bVar) {
        return e(bVar);
    }

    public t.a c(com.a.a.c.f.b bVar) {
        return t.a.a();
    }

    public Object d(com.a.a.c.f.b bVar) {
        return null;
    }

    public Object c(com.a.a.c.f.d dVar) {
        return null;
    }

    @Deprecated
    public q.a e(com.a.a.c.f.b bVar) {
        return q.a.a();
    }

    public ap<?> a(com.a.a.c.f.d dVar, ap<?> apVar) {
        return apVar;
    }

    public com.a.a.c.i.h<?> a(com.a.a.c.b.q<?> qVar, com.a.a.c.f.d dVar, j jVar) {
        return null;
    }

    public com.a.a.c.i.h<?> a(com.a.a.c.b.q<?> qVar, com.a.a.c.f.j jVar, j jVar2) {
        return null;
    }

    public com.a.a.c.i.h<?> b(com.a.a.c.b.q<?> qVar, com.a.a.c.f.j jVar, j jVar2) {
        return null;
    }

    public List<com.a.a.c.i.b> f(com.a.a.c.f.b bVar) {
        return null;
    }

    public String d(com.a.a.c.f.d dVar) {
        return null;
    }

    public Boolean a(com.a.a.c.f.j jVar) {
        return null;
    }

    public C0004a b(com.a.a.c.f.j jVar) {
        return null;
    }

    public com.a.a.c.m.r c(com.a.a.c.f.j jVar) {
        return null;
    }

    public boolean d(com.a.a.c.f.j jVar) {
        return false;
    }

    public b.a e(com.a.a.c.f.j jVar) {
        Object h = h(jVar);
        if (h != null) {
            return b.a.a(h);
        }
        return null;
    }

    public Boolean f(com.a.a.c.f.j jVar) {
        return null;
    }

    public Class<?>[] g(com.a.a.c.f.b bVar) {
        return null;
    }

    public l.d h(com.a.a.c.f.b bVar) {
        return l.d.a();
    }

    public static w b() {
        return null;
    }

    public String i(com.a.a.c.f.b bVar) {
        return null;
    }

    public String j(com.a.a.c.f.b bVar) {
        return null;
    }

    public Integer k(com.a.a.c.f.b bVar) {
        return null;
    }

    public String g(com.a.a.c.f.j jVar) {
        return null;
    }

    public List<w> l(com.a.a.c.f.b bVar) {
        return null;
    }

    public x.a m(com.a.a.c.f.b bVar) {
        return null;
    }

    public com.a.a.c.f.k a(com.a.a.c.f.k kVar, com.a.a.c.f.k kVar2) {
        return null;
    }

    public w c() {
        return null;
    }

    @Deprecated
    public Object h(com.a.a.c.f.j jVar) {
        return null;
    }

    public Object n(com.a.a.c.f.b bVar) {
        return null;
    }

    public Object o(com.a.a.c.f.b bVar) {
        return null;
    }

    public Object p(com.a.a.c.f.b bVar) {
        return null;
    }

    public Object q(com.a.a.c.f.b bVar) {
        return null;
    }

    public f.b r(com.a.a.c.f.b bVar) {
        return null;
    }

    public Object s(com.a.a.c.f.b bVar) {
        return null;
    }

    public Object i(com.a.a.c.f.j jVar) {
        return null;
    }

    public s.b t(com.a.a.c.f.b bVar) {
        return s.b.a();
    }

    public j a(com.a.a.c.b.q<?> qVar, com.a.a.c.f.b bVar, j jVar) {
        return jVar;
    }

    public String[] e(com.a.a.c.f.d dVar) {
        return null;
    }

    public Boolean u(com.a.a.c.f.b bVar) {
        return null;
    }

    public void a(com.a.a.c.b.q<?> qVar, com.a.a.c.f.d dVar, List<com.a.a.c.k.e> list) {
    }

    public w v(com.a.a.c.f.b bVar) {
        return null;
    }

    public Boolean w(com.a.a.c.f.b bVar) {
        return null;
    }

    public Boolean x(com.a.a.c.f.b bVar) {
        if ((bVar instanceof com.a.a.c.f.k) && a((com.a.a.c.f.k) bVar)) {
            return Boolean.TRUE;
        }
        return null;
    }

    public Boolean y(com.a.a.c.f.b bVar) {
        if ((bVar instanceof com.a.a.c.f.k) && b((com.a.a.c.f.k) bVar)) {
            return Boolean.TRUE;
        }
        return null;
    }

    public String[] a(Class<?> cls, Enum<?>[] enumArr, String[] strArr) {
        return strArr;
    }

    public void a(Class<?> cls, Enum<?>[] enumArr, String[][] strArr) {
    }

    public Enum<?> a(Class<Enum<?>> cls) {
        return null;
    }

    @Deprecated
    public boolean a(com.a.a.c.f.k kVar) {
        return false;
    }

    @Deprecated
    public boolean b(com.a.a.c.f.k kVar) {
        return false;
    }

    public Object z(com.a.a.c.f.b bVar) {
        return null;
    }

    public Object A(com.a.a.c.f.b bVar) {
        return null;
    }

    public Object B(com.a.a.c.f.b bVar) {
        return null;
    }

    public Object C(com.a.a.c.f.b bVar) {
        return null;
    }

    public Object j(com.a.a.c.f.j jVar) {
        return null;
    }

    public j b(com.a.a.c.b.q<?> qVar, com.a.a.c.f.b bVar, j jVar) {
        return jVar;
    }

    public Object f(com.a.a.c.f.d dVar) {
        return null;
    }

    public Class<?> g(com.a.a.c.f.d dVar) {
        return null;
    }

    public e.a h(com.a.a.c.f.d dVar) {
        return null;
    }

    public w D(com.a.a.c.f.b bVar) {
        return null;
    }

    public Boolean E(com.a.a.c.f.b bVar) {
        return null;
    }

    public ac.a F(com.a.a.c.f.b bVar) {
        return ac.a.a();
    }

    public Boolean G(com.a.a.c.f.b bVar) {
        return null;
    }

    public i.a a(com.a.a.c.b.q<?> qVar, com.a.a.c.f.b bVar) {
        if (H(bVar)) {
            i.a I = I(bVar);
            i.a aVar = I;
            if (I == null) {
                aVar = i.a.DEFAULT;
            }
            return aVar;
        }
        return null;
    }

    @Deprecated
    public boolean H(com.a.a.c.f.b bVar) {
        return false;
    }

    @Deprecated
    public i.a I(com.a.a.c.f.b bVar) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <A extends Annotation> A a(com.a.a.c.f.b bVar, Class<A> cls) {
        return (A) bVar.a(cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean b(com.a.a.c.f.b bVar, Class<? extends Annotation> cls) {
        return bVar.b(cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean a(com.a.a.c.f.b bVar, Class<? extends Annotation>[] clsArr) {
        return bVar.a(clsArr);
    }
}
