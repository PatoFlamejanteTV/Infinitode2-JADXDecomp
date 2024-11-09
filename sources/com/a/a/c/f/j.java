package com.a.a.c.f;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Member;

/* loaded from: infinitode-2.jar:com/a/a/c/f/j.class */
public abstract class j extends b implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    protected final transient an f457a;

    /* renamed from: b, reason: collision with root package name */
    protected final transient aa f458b;

    public abstract b a(aa aaVar);

    public abstract Class<?> h();

    public abstract Member i();

    public abstract void a(Object obj, Object obj2);

    public abstract Object b(Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public j(an anVar, aa aaVar) {
        this.f457a = anVar;
        this.f458b = aaVar;
    }

    public String j() {
        return h().getName() + "#" + b();
    }

    @Override // com.a.a.c.f.b
    public final <A extends Annotation> A a(Class<A> cls) {
        if (this.f458b == null) {
            return null;
        }
        return (A) this.f458b.a((Class) cls);
    }

    @Override // com.a.a.c.f.b
    public final boolean b(Class<?> cls) {
        if (this.f458b == null) {
            return false;
        }
        return this.f458b.b(cls);
    }

    @Override // com.a.a.c.f.b
    public final boolean a(Class<? extends Annotation>[] clsArr) {
        if (this.f458b == null) {
            return false;
        }
        return this.f458b.a(clsArr);
    }

    public final aa k() {
        return this.f458b;
    }

    public final void a(boolean z) {
        Member i = i();
        if (i != null) {
            com.a.a.c.m.i.a(i, z);
        }
    }
}
