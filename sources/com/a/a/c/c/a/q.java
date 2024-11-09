package com.a.a.c.c.a;

import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/q.class */
public final class q implements com.a.a.c.c.s, Serializable {

    /* renamed from: a, reason: collision with root package name */
    private static final q f276a = new q(null);

    /* renamed from: b, reason: collision with root package name */
    private static final q f277b = new q(null);
    private Object c;

    private q(Object obj) {
        this.c = obj;
        if (this.c == null) {
            com.a.a.c.m.a aVar = com.a.a.c.m.a.ALWAYS_NULL;
        } else {
            com.a.a.c.m.a aVar2 = com.a.a.c.m.a.CONSTANT;
        }
    }

    public static q a() {
        return f276a;
    }

    public static q b() {
        return f277b;
    }

    public static q a(Object obj) {
        if (obj == null) {
            return f277b;
        }
        return new q(obj);
    }

    public static boolean a(com.a.a.c.c.s sVar) {
        return sVar == f276a;
    }

    @Override // com.a.a.c.c.s
    public final Object a(com.a.a.c.g gVar) {
        return this.c;
    }
}
