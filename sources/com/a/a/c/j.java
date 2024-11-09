package com.a.a.c;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.List;

/* loaded from: infinitode-2.jar:com/a/a/c/j.class */
public abstract class j extends com.a.a.c.c.a.l implements Serializable, Type {

    /* renamed from: a, reason: collision with root package name */
    protected final Class<?> f522a;
    private int e;

    /* renamed from: b, reason: collision with root package name */
    protected final Object f523b;
    protected final Object c;
    protected final boolean d;

    public abstract j a(j jVar);

    public abstract j a();

    public abstract j a(Object obj);

    public abstract j b(Object obj);

    public abstract j c(Object obj);

    public abstract j d(Object obj);

    public abstract j a(Class<?> cls, com.a.a.c.l.n nVar, j jVar, j[] jVarArr);

    public abstract boolean n();

    public abstract int w();

    public abstract j a(int i);

    public abstract com.a.a.c.l.n x();

    public abstract j d(Class<?> cls);

    public abstract j y();

    public abstract List<j> z();

    public abstract StringBuilder a(StringBuilder sb);

    public abstract StringBuilder b(StringBuilder sb);

    public abstract String toString();

    public abstract boolean equals(Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public j(Class<?> cls, int i, Object obj, Object obj2, boolean z) {
        this.f522a = cls;
        this.e = cls.getName().hashCode() + i;
        this.f523b = obj;
        this.c = obj2;
        this.d = z;
    }

    public j b(j jVar) {
        j jVar2 = this;
        Object B = jVar.B();
        if (B != this.c) {
            jVar2 = jVar2.a(B);
        }
        Object A = jVar.A();
        if (A != this.f523b) {
            jVar2 = jVar2.c(A);
        }
        return jVar2;
    }

    public final Class<?> b() {
        return this.f522a;
    }

    public final boolean a(Class<?> cls) {
        return this.f522a == cls;
    }

    public boolean c() {
        return true;
    }

    public final boolean b(Class<?> cls) {
        return this.f522a == cls || cls.isAssignableFrom(this.f522a);
    }

    public final boolean c(Class<?> cls) {
        return this.f522a == cls || this.f522a.isAssignableFrom(cls);
    }

    public boolean d() {
        return Modifier.isAbstract(this.f522a.getModifiers());
    }

    public boolean e() {
        if ((this.f522a.getModifiers() & 1536) == 0) {
            return true;
        }
        return this.f522a.isPrimitive();
    }

    public final boolean f() {
        return Throwable.class.isAssignableFrom(this.f522a);
    }

    public boolean g() {
        return false;
    }

    public final boolean h() {
        return com.a.a.c.m.i.k(this.f522a);
    }

    public final boolean i() {
        return com.a.a.c.m.i.k(this.f522a) && this.f522a != Enum.class;
    }

    public final boolean j() {
        return com.a.a.c.m.i.f(this.f522a);
    }

    public final boolean k() {
        return this.f522a.isInterface();
    }

    public final boolean l() {
        return this.f522a.isPrimitive();
    }

    public final boolean m() {
        return Modifier.isFinal(this.f522a.getModifiers());
    }

    public boolean o() {
        return false;
    }

    public boolean p() {
        return false;
    }

    public final boolean q() {
        return this.f522a == Object.class;
    }

    public final boolean r() {
        return this.d;
    }

    public boolean s() {
        return w() > 0;
    }

    public j t() {
        return null;
    }

    public j u() {
        return null;
    }

    @Override // com.a.a.c.c.a.l
    /* renamed from: v, reason: merged with bridge method [inline-methods] */
    public j E() {
        return null;
    }

    public final j b(int i) {
        j a2 = a(i);
        return a2 == null ? com.a.a.c.l.o.b() : a2;
    }

    public final <T> T A() {
        return (T) this.f523b;
    }

    public final <T> T B() {
        return (T) this.c;
    }

    public boolean C() {
        return (this.c == null && this.f523b == null) ? false : true;
    }

    public final String D() {
        StringBuilder sb = new StringBuilder(40);
        a(sb);
        return sb.toString();
    }

    public final int hashCode() {
        return this.e;
    }
}
