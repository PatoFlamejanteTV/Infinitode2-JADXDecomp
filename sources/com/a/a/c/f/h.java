package com.a.a.c.f;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

/* loaded from: infinitode-2.jar:com/a/a/c/f/h.class */
public final class h extends j implements Serializable {
    private transient Field c;

    public h(an anVar, Field field, aa aaVar) {
        super(anVar, aaVar);
        this.c = field;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.f.j
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public h a(aa aaVar) {
        return new h(this.f457a, this.c, aaVar);
    }

    @Override // com.a.a.c.f.b
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public final Field a() {
        return this.c;
    }

    public final int f() {
        return this.c.getModifiers();
    }

    @Override // com.a.a.c.f.b
    public final String b() {
        return this.c.getName();
    }

    @Override // com.a.a.c.f.b
    public final Class<?> d() {
        return this.c.getType();
    }

    @Override // com.a.a.c.f.b
    public final com.a.a.c.j c() {
        return this.f457a.a(this.c.getGenericType());
    }

    @Override // com.a.a.c.f.j
    public final Class<?> h() {
        return this.c.getDeclaringClass();
    }

    @Override // com.a.a.c.f.j
    public final Member i() {
        return this.c;
    }

    @Override // com.a.a.c.f.j
    public final void a(Object obj, Object obj2) {
        try {
            this.c.set(obj, obj2);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Failed to setValue() for field " + j() + ": " + e.getMessage(), e);
        }
    }

    @Override // com.a.a.c.f.j
    public final Object b(Object obj) {
        try {
            return this.c.get(obj);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Failed to getValue() for field " + j() + ": " + e.getMessage(), e);
        }
    }

    public final boolean g() {
        return Modifier.isTransient(f());
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
        if (!com.a.a.c.m.i.a(obj, getClass())) {
            return false;
        }
        h hVar = (h) obj;
        if (hVar.c == null) {
            return this.c == null;
        }
        return hVar.c.equals(this.c);
    }

    @Override // com.a.a.c.f.b
    public final String toString() {
        return "[field " + j() + "]";
    }
}
