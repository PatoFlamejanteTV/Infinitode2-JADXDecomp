package com.a.a.c.e;

import com.a.a.c.f.n;
import com.a.a.c.m.i;
import com.a.a.c.w;

/* loaded from: infinitode-2.jar:com/a/a/c/e/c.class */
public abstract class c {

    /* renamed from: a, reason: collision with root package name */
    private static final c f421a;

    public abstract Boolean a(com.a.a.c.f.b bVar);

    public abstract Boolean b(com.a.a.c.f.b bVar);

    public abstract w a(n nVar);

    static {
        c cVar = null;
        try {
            cVar = (c) i.b((Class) Class.forName("com.a.a.c.e.d"), false);
        } catch (Throwable unused) {
        }
        f421a = cVar;
    }

    public static c a() {
        return f421a;
    }
}
