package com.a.a.c.f;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

/* loaded from: infinitode-2.jar:com/a/a/c/f/f.class */
public final class f extends o {
    private Constructor<?> d;

    public f(an anVar, Constructor<?> constructor, aa aaVar, aa[] aaVarArr) {
        super(anVar, aaVar, aaVarArr);
        if (constructor == null) {
            throw new IllegalArgumentException("Null constructor not allowed");
        }
        this.d = constructor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.f.j
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public f a(aa aaVar) {
        return new f(this.f457a, this.d, aaVar, this.c);
    }

    @Override // com.a.a.c.f.b
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public final Constructor<?> a() {
        return this.d;
    }

    @Override // com.a.a.c.f.b
    public final String b() {
        return this.d.getName();
    }

    @Override // com.a.a.c.f.b
    public final com.a.a.c.j c() {
        return this.f457a.a(d());
    }

    @Override // com.a.a.c.f.b
    public final Class<?> d() {
        return this.d.getDeclaringClass();
    }

    @Override // com.a.a.c.f.o
    public final int f() {
        return this.d.getParameterCount();
    }

    @Override // com.a.a.c.f.o
    public final Class<?> a(int i) {
        Class<?>[] parameterTypes = this.d.getParameterTypes();
        if (i >= parameterTypes.length) {
            return null;
        }
        return parameterTypes[i];
    }

    @Override // com.a.a.c.f.o
    public final com.a.a.c.j b(int i) {
        Type[] genericParameterTypes = this.d.getGenericParameterTypes();
        if (i >= genericParameterTypes.length) {
            return null;
        }
        return this.f457a.a(genericParameterTypes[i]);
    }

    @Override // com.a.a.c.f.o
    public final Object g() {
        return this.d.newInstance((Object[]) null);
    }

    @Override // com.a.a.c.f.o
    public final Object a(Object[] objArr) {
        return this.d.newInstance(objArr);
    }

    @Override // com.a.a.c.f.o
    public final Object a(Object obj) {
        return this.d.newInstance(obj);
    }

    @Override // com.a.a.c.f.j
    public final Class<?> h() {
        return this.d.getDeclaringClass();
    }

    @Override // com.a.a.c.f.j
    public final Member i() {
        return this.d;
    }

    @Override // com.a.a.c.f.j
    public final void a(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Cannot call setValue() on constructor of " + h().getName());
    }

    @Override // com.a.a.c.f.j
    public final Object b(Object obj) {
        throw new UnsupportedOperationException("Cannot call getValue() on constructor of " + h().getName());
    }

    @Override // com.a.a.c.f.b
    public final String toString() {
        int parameterCount = this.d.getParameterCount();
        Object[] objArr = new Object[4];
        objArr[0] = com.a.a.c.m.i.g(this.d.getDeclaringClass());
        objArr[1] = Integer.valueOf(parameterCount);
        objArr[2] = parameterCount == 1 ? "" : "s";
        objArr[3] = this.f458b;
        return String.format("[constructor for %s (%d arg%s), annotations: %s", objArr);
    }

    @Override // com.a.a.c.f.b
    public final int hashCode() {
        return this.d.getName().hashCode();
    }

    @Override // com.a.a.c.f.b
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!com.a.a.c.m.i.a(obj, getClass())) {
            return false;
        }
        f fVar = (f) obj;
        if (fVar.d == null) {
            return this.d == null;
        }
        return fVar.d.equals(this.d);
    }
}
