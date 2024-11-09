package com.a.a.c.k.b;

import com.a.a.a.l;
import java.util.Collection;
import java.util.Objects;

/* loaded from: infinitode-2.jar:com/a/a/c/k/b/ah.class */
public abstract class ah<T extends Collection<?>> extends ao<T> implements com.a.a.c.k.k {

    /* renamed from: b, reason: collision with root package name */
    protected final Boolean f602b;

    public abstract com.a.a.c.o<?> a(Boolean bool);

    @Override // com.a.a.c.o
    public abstract void a(T t, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.i.i iVar);

    @Override // com.a.a.c.o
    public final /* synthetic */ boolean a(com.a.a.c.aa aaVar, Object obj) {
        return b((Collection) obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ah(Class<?> cls) {
        super(cls, (byte) 0);
        this.f602b = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ah(ah<?> ahVar, Boolean bool) {
        super(ahVar);
        this.f602b = bool;
    }

    @Override // com.a.a.c.k.k
    public final com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.c cVar) {
        Object p;
        com.a.a.c.o<Object> oVar = null;
        Boolean bool = null;
        if (cVar != null) {
            com.a.a.c.a d = aaVar.d();
            com.a.a.c.f.j e = cVar.e();
            if (e != null && (p = d.p(e)) != null) {
                oVar = aaVar.b(e, p);
            }
        }
        l.d a2 = a(aaVar, cVar, (Class<?>) a());
        if (a2 != null) {
            bool = a2.a(l.a.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
        }
        com.a.a.c.o<?> a3 = a(aaVar, cVar, (com.a.a.c.o<?>) oVar);
        com.a.a.c.o<?> oVar2 = a3;
        if (a3 == null) {
            oVar2 = aaVar.c(String.class, cVar);
        }
        if (a(oVar2)) {
            if (Objects.equals(bool, this.f602b)) {
                return this;
            }
            return a(bool);
        }
        return new k(aaVar.a(String.class), true, null, oVar2);
    }

    private static boolean b(T t) {
        return t == null || t.isEmpty();
    }
}
