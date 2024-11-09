package com.a.a.c.f;

import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Member;

/* loaded from: infinitode-2.jar:com/a/a/c/f/ao.class */
public final class ao extends j implements Serializable {
    private Class<?> c;
    private com.a.a.c.j d;
    private String e;

    @Override // com.a.a.c.f.b
    public final /* synthetic */ AnnotatedElement a() {
        return e();
    }

    public ao(an anVar, Class<?> cls, String str, com.a.a.c.j jVar) {
        super(anVar, null);
        this.c = cls;
        this.d = jVar;
        this.e = str;
    }

    @Override // com.a.a.c.f.j
    public final b a(aa aaVar) {
        return this;
    }

    private static Field e() {
        return null;
    }

    @Override // com.a.a.c.f.b
    public final String b() {
        return this.e;
    }

    @Override // com.a.a.c.f.b
    public final Class<?> d() {
        return this.d.b();
    }

    @Override // com.a.a.c.f.b
    public final com.a.a.c.j c() {
        return this.d;
    }

    @Override // com.a.a.c.f.j
    public final Class<?> h() {
        return this.c;
    }

    @Override // com.a.a.c.f.j
    public final Member i() {
        return null;
    }

    @Override // com.a.a.c.f.j
    public final void a(Object obj, Object obj2) {
        throw new IllegalArgumentException("Cannot set virtual property '" + this.e + "'");
    }

    @Override // com.a.a.c.f.j
    public final Object b(Object obj) {
        throw new IllegalArgumentException("Cannot get virtual property '" + this.e + "'");
    }

    @Override // com.a.a.c.f.b
    public final int hashCode() {
        return this.e.hashCode();
    }

    @Override // com.a.a.c.f.b
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!com.a.a.c.m.i.a(obj, getClass())) {
            return false;
        }
        ao aoVar = (ao) obj;
        return aoVar.c == this.c && aoVar.e.equals(this.e);
    }

    @Override // com.a.a.c.f.b
    public final String toString() {
        return "[virtual " + j() + "]";
    }
}
