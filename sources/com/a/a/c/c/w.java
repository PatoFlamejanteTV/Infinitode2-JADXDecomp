package com.a.a.c.c;

import com.a.a.c.c.a.z;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:com/a/a/c/c/w.class */
public final class w extends com.a.a.c.l {

    /* renamed from: b, reason: collision with root package name */
    private z f416b;
    private List<com.a.a.b.c.a.e> c;

    public w(com.a.a.b.l lVar, String str, com.a.a.b.j jVar, z zVar) {
        super(lVar, str, jVar);
        this.f416b = zVar;
    }

    public final z d() {
        return this.f416b;
    }

    public final Object f() {
        return this.f416b.a().f46a;
    }

    @Override // com.a.a.c.l, com.a.a.b.m, java.lang.Throwable
    public final String getMessage() {
        String message = super.getMessage();
        if (this.c == null) {
            return message;
        }
        StringBuilder sb = new StringBuilder(message);
        Iterator<com.a.a.b.c.a.e> it = this.c.iterator();
        if (it.hasNext()) {
            it.next();
            throw null;
        }
        sb.append('.');
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.lang.Throwable
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public synchronized w fillInStackTrace() {
        return this;
    }
}
