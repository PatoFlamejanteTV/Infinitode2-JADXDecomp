package com.a.a.c.c.b;

import java.util.concurrent.atomic.AtomicLong;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/d.class */
public final class d extends ai<AtomicLong> {
    public d() {
        super((Class<?>) AtomicLong.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public AtomicLong a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (lVar.r()) {
            return new AtomicLong(lVar.H());
        }
        if (c(lVar, gVar, AtomicLong.class) == null) {
            return null;
        }
        return new AtomicLong(r0.intValue());
    }

    @Override // com.a.a.c.c.b.ai, com.a.a.c.k
    public final com.a.a.c.l.f b() {
        return com.a.a.c.l.f.Integer;
    }

    @Override // com.a.a.c.k
    public final Object c(com.a.a.c.g gVar) {
        return new AtomicLong();
    }
}
