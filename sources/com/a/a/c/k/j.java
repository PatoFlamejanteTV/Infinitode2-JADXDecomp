package com.a.a.c.k;

import com.a.a.c.k.b.ao;

/* loaded from: infinitode-2.jar:com/a/a/c/k/j.class */
public abstract class j<T> extends ao<T> {
    public abstract boolean a(T t);

    protected abstract j<?> b(com.a.a.c.i.i iVar);

    /* JADX INFO: Access modifiers changed from: protected */
    public j(Class<T> cls) {
        super(cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public j(com.a.a.c.j jVar) {
        super(jVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public j(Class<?> cls, boolean z) {
        super(cls, (byte) 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public j(j<?> jVar) {
        super(jVar.h, (byte) 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final j<?> a(com.a.a.c.i.i iVar) {
        return iVar == null ? this : b(iVar);
    }
}
