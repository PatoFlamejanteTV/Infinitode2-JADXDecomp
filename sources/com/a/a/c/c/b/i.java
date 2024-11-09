package com.a.a.c.c.b;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/i.class */
public abstract class i<T> extends ae<T> {

    /* renamed from: b, reason: collision with root package name */
    protected final com.a.a.c.j f344b;
    protected final com.a.a.c.c.s c;
    protected final boolean d;
    protected final Boolean e;

    public abstract com.a.a.c.k<Object> g();

    /* JADX INFO: Access modifiers changed from: protected */
    public i(com.a.a.c.j jVar, com.a.a.c.c.s sVar, Boolean bool) {
        super(jVar);
        this.f344b = jVar;
        this.e = bool;
        this.c = sVar;
        this.d = com.a.a.c.c.a.q.a(sVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public i(com.a.a.c.j jVar) {
        this(jVar, (com.a.a.c.c.s) null, (Boolean) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public i(i<?> iVar) {
        this(iVar, iVar.c, iVar.e);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public i(i<?> iVar, com.a.a.c.c.s sVar, Boolean bool) {
        super(iVar.f344b);
        this.f344b = iVar.f344b;
        this.c = sVar;
        this.e = bool;
        this.d = com.a.a.c.c.a.q.a(sVar);
    }

    @Override // com.a.a.c.c.b.ae
    public com.a.a.c.j h() {
        return this.f344b;
    }

    @Override // com.a.a.c.k
    public final Boolean a(com.a.a.c.f fVar) {
        return Boolean.TRUE;
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.c.v a(String str) {
        com.a.a.c.k<Object> g = g();
        if (g == null) {
            throw new IllegalArgumentException(String.format("Cannot handle managed/back reference '%s': type: container deserializer of type %s returned null for 'getContentDeserializer()'", str, getClass().getName()));
        }
        return g.a(str);
    }

    @Override // com.a.a.c.k
    public com.a.a.c.m.a e() {
        return com.a.a.c.m.a.DYNAMIC;
    }

    @Override // com.a.a.c.k
    public Object c(com.a.a.c.g gVar) {
        com.a.a.c.c.x i = i();
        if (i == null || !i.l()) {
            com.a.a.c.j h = h();
            gVar.a(h, String.format("Cannot create empty instance of %s, no default Creator", h));
        }
        try {
            return i.a(gVar);
        } catch (IOException e) {
            return com.a.a.c.m.i.a(gVar, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <BOGUS> BOGUS a(com.a.a.c.g gVar, Throwable th, Object obj, String str) {
        while ((th instanceof InvocationTargetException) && th.getCause() != null) {
            th = th.getCause();
        }
        com.a.a.c.m.i.a(th);
        if (gVar != null && !gVar.a(com.a.a.c.i.WRAP_EXCEPTIONS)) {
            com.a.a.c.m.i.b(th);
        }
        if ((th instanceof IOException) && !(th instanceof com.a.a.c.l)) {
            throw ((IOException) th);
        }
        throw com.a.a.c.l.a(th, obj, (String) com.a.a.c.m.i.a(str, "N/A"));
    }
}
