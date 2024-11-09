package com.a.a.c.c.b;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/a.class */
public final class a extends h {
    public a(com.a.a.c.j jVar, com.a.a.c.k<Object> kVar, com.a.a.c.i.e eVar, com.a.a.c.c.x xVar) {
        super(jVar, kVar, eVar, xVar);
    }

    private a(com.a.a.c.j jVar, com.a.a.c.k<Object> kVar, com.a.a.c.i.e eVar, com.a.a.c.c.x xVar, com.a.a.c.k<Object> kVar2, com.a.a.c.c.s sVar, Boolean bool) {
        super(jVar, kVar, eVar, xVar, kVar2, sVar, bool);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.c.b.h
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public a a(com.a.a.c.k<?> kVar, com.a.a.c.k<?> kVar2, com.a.a.c.i.e eVar, com.a.a.c.c.s sVar, Boolean bool) {
        return new a(this.f344b, kVar2, eVar, this.f339a, kVar, sVar, bool);
    }

    @Override // com.a.a.c.c.b.h
    protected final Collection<Object> d(com.a.a.c.g gVar) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.a.a.c.c.b.h
    public final Collection<Object> a(com.a.a.b.l lVar, com.a.a.c.g gVar, Collection<Object> collection) {
        if (collection == null) {
            collection = new ArrayList();
        }
        Collection<Object> a2 = super.a(lVar, gVar, collection);
        if (!a2.isEmpty()) {
            return new ArrayBlockingQueue(a2.size(), false, a2);
        }
        return new ArrayBlockingQueue(1, false);
    }

    @Override // com.a.a.c.c.b.h, com.a.a.c.c.b.ae, com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        return eVar.b(lVar, gVar);
    }
}
