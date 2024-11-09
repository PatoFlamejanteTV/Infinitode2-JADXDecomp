package com.a.a.c.k.b;

import java.util.concurrent.atomic.AtomicReference;

/* loaded from: infinitode-2.jar:com/a/a/c/k/b/c.class */
public final class c extends ae<AtomicReference<?>> {
    @Override // com.a.a.c.k.b.ae
    protected final /* synthetic */ Object a(AtomicReference<?> atomicReference) {
        return c2(atomicReference);
    }

    @Override // com.a.a.c.k.b.ae
    protected final /* bridge */ /* synthetic */ Object b(AtomicReference<?> atomicReference) {
        return b2(atomicReference);
    }

    @Override // com.a.a.c.k.b.ae
    protected final /* synthetic */ boolean c(AtomicReference<?> atomicReference) {
        return a2(atomicReference);
    }

    public c(com.a.a.c.l.j jVar, boolean z, com.a.a.c.i.i iVar, com.a.a.c.o<Object> oVar) {
        super(jVar, iVar, oVar);
    }

    private c(c cVar, com.a.a.c.c cVar2, com.a.a.c.i.i iVar, com.a.a.c.o<?> oVar, com.a.a.c.m.r rVar, Object obj, boolean z) {
        super(cVar, cVar2, iVar, oVar, rVar, obj, z);
    }

    @Override // com.a.a.c.k.b.ae
    protected final ae<AtomicReference<?>> a(com.a.a.c.c cVar, com.a.a.c.i.i iVar, com.a.a.c.o<?> oVar, com.a.a.c.m.r rVar) {
        return new c(this, cVar, iVar, oVar, rVar, this.e, this.f);
    }

    @Override // com.a.a.c.k.b.ae
    public final ae<AtomicReference<?>> a(Object obj, boolean z) {
        return new c(this, this.f598a, this.f599b, this.c, this.d, obj, z);
    }

    /* renamed from: a, reason: avoid collision after fix types in other method */
    private static boolean a2(AtomicReference<?> atomicReference) {
        return atomicReference.get() != null;
    }

    /* renamed from: b, reason: avoid collision after fix types in other method */
    private static Object b2(AtomicReference<?> atomicReference) {
        return atomicReference.get();
    }

    /* renamed from: c, reason: avoid collision after fix types in other method */
    private static Object c2(AtomicReference<?> atomicReference) {
        return atomicReference.get();
    }
}
