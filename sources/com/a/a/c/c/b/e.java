package com.a.a.c.c.b;

import java.util.concurrent.atomic.AtomicReference;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/e.class */
public final class e extends ab<AtomicReference<Object>> {
    @Override // com.a.a.c.c.b.ab
    public final /* bridge */ /* synthetic */ Object a(AtomicReference<Object> atomicReference) {
        return a2(atomicReference);
    }

    @Override // com.a.a.c.c.b.ab
    public final /* bridge */ /* synthetic */ AtomicReference<Object> a(AtomicReference<Object> atomicReference, Object obj) {
        return a2(atomicReference, obj);
    }

    @Override // com.a.a.c.c.b.ab
    public final /* synthetic */ AtomicReference<Object> b(Object obj) {
        return c(obj);
    }

    @Override // com.a.a.c.c.b.ab
    public final /* synthetic */ ab<AtomicReference<Object>> a(com.a.a.c.i.e eVar, com.a.a.c.k kVar) {
        return b(eVar, (com.a.a.c.k<?>) kVar);
    }

    public e(com.a.a.c.j jVar, com.a.a.c.c.x xVar, com.a.a.c.i.e eVar, com.a.a.c.k<?> kVar) {
        super(jVar, xVar, eVar, kVar);
    }

    private e b(com.a.a.c.i.e eVar, com.a.a.c.k<?> kVar) {
        return new e(this.f303a, this.f304b, eVar, kVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.c.b.ab, com.a.a.c.k, com.a.a.c.c.s
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public AtomicReference<Object> a(com.a.a.c.g gVar) {
        return new AtomicReference<>(this.c.a(gVar));
    }

    @Override // com.a.a.c.c.b.ab, com.a.a.c.k
    public final Object c(com.a.a.c.g gVar) {
        return a(gVar);
    }

    @Override // com.a.a.c.k, com.a.a.c.c.s
    public final Object b(com.a.a.c.g gVar) {
        return null;
    }

    private static AtomicReference<Object> c(Object obj) {
        return new AtomicReference<>(obj);
    }

    /* renamed from: a, reason: avoid collision after fix types in other method */
    private static Object a2(AtomicReference<Object> atomicReference) {
        return atomicReference.get();
    }

    /* renamed from: a, reason: avoid collision after fix types in other method */
    private static AtomicReference<Object> a2(AtomicReference<Object> atomicReference, Object obj) {
        atomicReference.set(obj);
        return atomicReference;
    }

    @Override // com.a.a.c.c.b.ab, com.a.a.c.k
    public final Boolean a(com.a.a.c.f fVar) {
        return Boolean.TRUE;
    }
}
