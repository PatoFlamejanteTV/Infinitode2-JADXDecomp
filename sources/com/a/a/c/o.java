package com.a.a.c;

/* loaded from: infinitode-2.jar:com/a/a/c/o.class */
public abstract class o<T> {

    /* loaded from: infinitode-2.jar:com/a/a/c/o$a.class */
    public static abstract class a extends o<Object> {
    }

    public abstract void a(T t, com.a.a.b.h hVar, aa aaVar);

    public o<T> a(com.a.a.c.m.r rVar) {
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void a(T t, com.a.a.b.h hVar, aa aaVar, com.a.a.c.i.i iVar) {
        Class a2 = a();
        Class cls = a2;
        if (a2 == null) {
            cls = t.getClass();
        }
        aaVar.a((Class<?>) cls, String.format("Type id handling not implemented for type %s (by serializer of type %s)", cls.getName(), getClass().getName()));
    }

    public Class<T> a() {
        return null;
    }

    public boolean a(aa aaVar, T t) {
        return t == null;
    }

    public boolean b() {
        return false;
    }

    public boolean c() {
        return false;
    }
}
