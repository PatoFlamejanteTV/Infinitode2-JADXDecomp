package com.a.a.c.c.b;

import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/b.class */
public final class b extends ai<AtomicBoolean> {
    public b() {
        super((Class<?>) AtomicBoolean.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public AtomicBoolean a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        com.a.a.b.o k = lVar.k();
        if (k == com.a.a.b.o.VALUE_TRUE) {
            return new AtomicBoolean(true);
        }
        if (k == com.a.a.b.o.VALUE_FALSE) {
            return new AtomicBoolean(false);
        }
        Boolean a2 = a(lVar, gVar, AtomicBoolean.class);
        if (a2 == null) {
            return null;
        }
        return new AtomicBoolean(a2.booleanValue());
    }

    @Override // com.a.a.c.c.b.ai, com.a.a.c.k
    public final com.a.a.c.l.f b() {
        return com.a.a.c.l.f.Boolean;
    }

    @Override // com.a.a.c.k
    public final Object c(com.a.a.c.g gVar) {
        return new AtomicBoolean(false);
    }
}
