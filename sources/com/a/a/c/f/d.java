package com.a.a.c.f;

import com.a.a.c.f.t;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/* loaded from: infinitode-2.jar:com/a/a/c/f/d.class */
public final class d extends b implements an {

    /* renamed from: a, reason: collision with root package name */
    private static final a f449a = new a(null, Collections.emptyList(), Collections.emptyList());

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.j f450b;
    private Class<?> c;
    private com.a.a.c.l.n d;
    private List<com.a.a.c.j> e;
    private com.a.a.c.a f;
    private com.a.a.c.l.o g;
    private t.a h;
    private Class<?> i;
    private boolean j;
    private com.a.a.c.m.b k;
    private a l;
    private m m;
    private List<h> n;
    private transient Boolean o;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(com.a.a.c.j jVar, Class<?> cls, List<com.a.a.c.j> list, Class<?> cls2, com.a.a.c.m.b bVar, com.a.a.c.l.n nVar, com.a.a.c.a aVar, t.a aVar2, com.a.a.c.l.o oVar, boolean z) {
        this.f450b = jVar;
        this.c = cls;
        this.e = list;
        this.i = cls2;
        this.k = bVar;
        this.d = nVar;
        this.f = aVar;
        this.h = aVar2;
        this.g = oVar;
        this.j = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(Class<?> cls) {
        this.f450b = null;
        this.c = cls;
        this.e = Collections.emptyList();
        this.i = null;
        this.k = p.a();
        this.d = com.a.a.c.l.n.a();
        this.f = null;
        this.h = null;
        this.g = null;
        this.j = false;
    }

    @Override // com.a.a.c.f.an
    public final com.a.a.c.j a(Type type) {
        return this.g.a(type, this.d);
    }

    @Override // com.a.a.c.f.b
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public final Class<?> a() {
        return this.c;
    }

    @Override // com.a.a.c.f.b
    public final String b() {
        return this.c.getName();
    }

    @Override // com.a.a.c.f.b
    public final <A extends Annotation> A a(Class<A> cls) {
        return (A) this.k.a(cls);
    }

    @Override // com.a.a.c.f.b
    public final boolean b(Class<?> cls) {
        return this.k.b(cls);
    }

    @Override // com.a.a.c.f.b
    public final boolean a(Class<? extends Annotation>[] clsArr) {
        return this.k.a(clsArr);
    }

    @Override // com.a.a.c.f.b
    public final Class<?> d() {
        return this.c;
    }

    @Override // com.a.a.c.f.b
    public final com.a.a.c.j c() {
        return this.f450b;
    }

    public final com.a.a.c.m.b f() {
        return this.k;
    }

    public final boolean g() {
        return this.k.a() > 0;
    }

    public final f h() {
        return p().f451a;
    }

    public final List<f> i() {
        return p().f452b;
    }

    public final List<k> j() {
        return p().c;
    }

    public final Iterable<k> k() {
        return o();
    }

    public final k a(String str, Class<?>[] clsArr) {
        return o().a(str, clsArr);
    }

    public final Iterable<h> l() {
        return n();
    }

    public final boolean m() {
        Boolean bool = this.o;
        Boolean bool2 = bool;
        if (bool == null) {
            Boolean valueOf = Boolean.valueOf(com.a.a.c.m.i.n(this.c));
            bool2 = valueOf;
            this.o = valueOf;
        }
        return bool2.booleanValue();
    }

    private final List<h> n() {
        List<h> list = this.n;
        List<h> list2 = list;
        if (list == null) {
            if (this.f450b == null) {
                list2 = Collections.emptyList();
            } else {
                list2 = i.a(this.f, this, this.h, this.g, this.f450b, this.j);
            }
            this.n = list2;
        }
        return list2;
    }

    private final m o() {
        m mVar = this.m;
        m mVar2 = mVar;
        if (mVar == null) {
            if (this.f450b == null) {
                mVar2 = new m();
            } else {
                mVar2 = l.a(this.f, this, this.h, this.g, this.f450b, this.e, this.i, this.j);
            }
            this.m = mVar2;
        }
        return mVar2;
    }

    private final a p() {
        a aVar = this.l;
        a aVar2 = aVar;
        if (aVar == null) {
            if (this.f450b == null) {
                aVar2 = f449a;
            } else {
                aVar2 = g.a(this.f, this.g, this, this.f450b, this.i, this.j);
            }
            this.l = aVar2;
        }
        return aVar2;
    }

    @Override // com.a.a.c.f.b
    public final String toString() {
        return "[AnnotedClass " + this.c.getName() + "]";
    }

    @Override // com.a.a.c.f.b
    public final int hashCode() {
        return this.c.getName().hashCode();
    }

    @Override // com.a.a.c.f.b
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return com.a.a.c.m.i.a(obj, getClass()) && ((d) obj).c == this.c;
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/f/d$a.class */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public final f f451a;

        /* renamed from: b, reason: collision with root package name */
        public final List<f> f452b;
        public final List<k> c;

        public a(f fVar, List<f> list, List<k> list2) {
            this.f451a = fVar;
            this.f452b = list;
            this.c = list2;
        }
    }
}
