package com.a.a.c.k.b;

import java.util.EnumSet;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/a/a/c/k/b/o.class */
public final class o extends b<EnumSet<? extends Enum<?>>> {
    @Override // com.a.a.c.k.j
    public final /* synthetic */ com.a.a.c.k.j b(com.a.a.c.i.i iVar) {
        return d();
    }

    @Override // com.a.a.c.k.j
    public final /* synthetic */ boolean a(Object obj) {
        return b((EnumSet<? extends Enum<?>>) obj);
    }

    @Override // com.a.a.c.o
    public final /* bridge */ /* synthetic */ boolean a(com.a.a.c.aa aaVar, Object obj) {
        return a((EnumSet<? extends Enum<?>>) obj);
    }

    public o(com.a.a.c.j jVar) {
        super((Class<?>) EnumSet.class, jVar, true, (com.a.a.c.i.i) null, (com.a.a.c.o<Object>) null);
    }

    private o(o oVar, com.a.a.c.c cVar, com.a.a.c.i.i iVar, com.a.a.c.o<?> oVar2, Boolean bool) {
        super(oVar, cVar, iVar, oVar2, bool);
    }

    private o d() {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.b
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public o a(com.a.a.c.c cVar, com.a.a.c.i.i iVar, com.a.a.c.o<?> oVar, Boolean bool) {
        return new o(this, cVar, iVar, oVar, bool);
    }

    private static boolean a(EnumSet<? extends Enum<?>> enumSet) {
        return enumSet.isEmpty();
    }

    private static boolean b(EnumSet<? extends Enum<?>> enumSet) {
        return enumSet.size() == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.b, com.a.a.c.k.b.ao, com.a.a.c.o
    public void a(EnumSet<? extends Enum<?>> enumSet, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        int size = enumSet.size();
        if (size == 1 && ((this.c == null && aaVar.a(com.a.a.c.z.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) || this.c == Boolean.TRUE)) {
            b(enumSet, hVar, aaVar);
            return;
        }
        hVar.a(enumSet, size);
        b(enumSet, hVar, aaVar);
        hVar.h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.b
    public void b(EnumSet<? extends Enum<?>> enumSet, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        com.a.a.c.o<Object> oVar = this.e;
        Iterator it = enumSet.iterator();
        while (it.hasNext()) {
            Enum r0 = (Enum) it.next();
            if (oVar == null) {
                oVar = aaVar.c(r0.getDeclaringClass(), this.f615b);
            }
            oVar.a(r0, hVar, aaVar);
        }
    }
}
