package com.a.a.c.c.b;

import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/c.class */
public final class c extends ai<AtomicInteger> {
    public c() {
        super((Class<?>) AtomicInteger.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public AtomicInteger a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (lVar.r()) {
            return new AtomicInteger(lVar.G());
        }
        Integer b2 = b(lVar, gVar, AtomicInteger.class);
        if (b2 == null) {
            return null;
        }
        return new AtomicInteger(b2.intValue());
    }

    @Override // com.a.a.c.c.b.ai, com.a.a.c.k
    public final com.a.a.c.l.f b() {
        return com.a.a.c.l.f.Integer;
    }

    @Override // com.a.a.c.k
    public final Object c(com.a.a.c.g gVar) {
        return new AtomicInteger();
    }
}
