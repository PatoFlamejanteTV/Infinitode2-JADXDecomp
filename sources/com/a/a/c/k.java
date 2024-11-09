package com.a.a.c;

import java.util.Collection;

/* loaded from: infinitode-2.jar:com/a/a/c/k.class */
public abstract class k<T> implements com.a.a.c.c.s {
    public abstract T a(com.a.a.b.l lVar, g gVar);

    public T a(com.a.a.b.l lVar, g gVar, T t) {
        gVar.a((k<?>) this);
        return a(lVar, gVar);
    }

    public Object a(com.a.a.b.l lVar, g gVar, com.a.a.c.i.e eVar) {
        return eVar.d(lVar, gVar);
    }

    public final Object b(com.a.a.b.l lVar, g gVar, com.a.a.c.i.e eVar) {
        gVar.a((k<?>) this);
        return a(lVar, gVar, eVar);
    }

    public k<T> a(com.a.a.c.m.r rVar) {
        return this;
    }

    public Class<?> a() {
        return null;
    }

    public com.a.a.c.l.f b() {
        return null;
    }

    public boolean c() {
        return false;
    }

    public Collection<Object> d() {
        return null;
    }

    @Override // com.a.a.c.c.s
    public T a(g gVar) {
        return (T) g();
    }

    @Override // com.a.a.c.c.s
    public Object b(g gVar) {
        return a(gVar);
    }

    public Object c(g gVar) {
        return a(gVar);
    }

    public com.a.a.c.m.a e() {
        return com.a.a.c.m.a.DYNAMIC;
    }

    public com.a.a.c.c.a.s f() {
        return null;
    }

    public com.a.a.c.c.v a(String str) {
        throw new IllegalArgumentException("Cannot handle managed/back reference '" + str + "': type: value deserializer of type " + getClass().getName() + " does not support them");
    }

    public Boolean a(f fVar) {
        return null;
    }

    @Deprecated
    private static T g() {
        return null;
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/k$a.class */
    public static abstract class a extends k<Object> {
        private a() {
        }
    }
}
