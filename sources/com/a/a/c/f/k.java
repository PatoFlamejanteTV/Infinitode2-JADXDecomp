package com.a.a.c.f;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/* loaded from: infinitode-2.jar:com/a/a/c/f/k.class */
public final class k extends o implements Serializable {
    private transient Method d;
    private Class<?>[] e;

    public k(an anVar, Method method, aa aaVar, aa[] aaVarArr) {
        super(anVar, aaVar, aaVarArr);
        if (method == null) {
            throw new IllegalArgumentException("Cannot construct AnnotatedMethod with null Method");
        }
        this.d = method;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.f.j
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public k a(aa aaVar) {
        return new k(this.f457a, this.d, aaVar, this.c);
    }

    @Override // com.a.a.c.f.b
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public final Method a() {
        return this.d;
    }

    @Override // com.a.a.c.f.b
    public final String b() {
        return this.d.getName();
    }

    @Override // com.a.a.c.f.b
    public final com.a.a.c.j c() {
        return this.f457a.a(this.d.getGenericReturnType());
    }

    @Override // com.a.a.c.f.b
    public final Class<?> d() {
        return this.d.getReturnType();
    }

    @Override // com.a.a.c.f.o
    public final Object g() {
        return this.d.invoke(null, new Object[0]);
    }

    @Override // com.a.a.c.f.o
    public final Object a(Object[] objArr) {
        return this.d.invoke(null, objArr);
    }

    @Override // com.a.a.c.f.o
    public final Object a(Object obj) {
        return this.d.invoke(null, obj);
    }

    public final Object a(Object obj, Object... objArr) {
        return this.d.invoke(obj, objArr);
    }

    @Override // com.a.a.c.f.o
    public final int f() {
        return this.d.getParameterCount();
    }

    @Override // com.a.a.c.f.o
    public final Class<?> a(int i) {
        Class<?>[] n = n();
        if (i >= n.length) {
            return null;
        }
        return n[i];
    }

    @Override // com.a.a.c.f.o
    public final com.a.a.c.j b(int i) {
        Type[] genericParameterTypes = this.d.getGenericParameterTypes();
        if (i >= genericParameterTypes.length) {
            return null;
        }
        return this.f457a.a(genericParameterTypes[i]);
    }

    @Override // com.a.a.c.f.j
    public final Class<?> h() {
        return this.d.getDeclaringClass();
    }

    @Override // com.a.a.c.f.j
    /* renamed from: l, reason: merged with bridge method [inline-methods] */
    public final Method i() {
        return this.d;
    }

    @Override // com.a.a.c.f.j
    public final void a(Object obj, Object obj2) {
        try {
            this.d.invoke(obj, obj2);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Failed to setValue() with method " + j() + ": " + com.a.a.c.m.i.g(e), e);
        }
    }

    @Override // com.a.a.c.f.j
    public final Object b(Object obj) {
        try {
            return this.d.invoke(obj, (Object[]) null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Failed to getValue() with method " + j() + ": " + com.a.a.c.m.i.g(e), e);
        }
    }

    @Override // com.a.a.c.f.j
    public final String j() {
        String j = super.j();
        switch (f()) {
            case 0:
                return j + "()";
            case 1:
                return j + "(" + a(0).getName() + ")";
            default:
                return String.format("%s(%d params)", super.j(), Integer.valueOf(f()));
        }
    }

    private Class<?>[] n() {
        if (this.e == null) {
            this.e = this.d.getParameterTypes();
        }
        return this.e;
    }

    public final Class<?> m() {
        return this.d.getReturnType();
    }

    @Override // com.a.a.c.f.b
    public final String toString() {
        return "[method " + j() + "]";
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
        k kVar = (k) obj;
        if (kVar.d == null) {
            return this.d == null;
        }
        return kVar.d.equals(this.d);
    }
}
