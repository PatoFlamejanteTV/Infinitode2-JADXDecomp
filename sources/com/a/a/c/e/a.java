package com.a.a.c.e;

import com.a.a.c.k;
import com.a.a.c.m.i;
import com.a.a.c.o;

/* loaded from: infinitode-2.jar:com/a/a/c/e/a.class */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    private static final a f419a;

    public abstract k<?> a(Class<?> cls);

    public abstract o<?> b(Class<?> cls);

    static {
        a aVar = null;
        try {
            aVar = (a) i.b((Class) Class.forName("com.a.a.c.e.b"), false);
        } catch (Throwable unused) {
        }
        f419a = aVar;
    }

    public static a a() {
        return f419a;
    }
}
