package com.a.a.c.k.b;

import com.a.a.a.l;
import java.util.Objects;

/* loaded from: infinitode-2.jar:com/a/a/c/k/b/a.class */
public abstract class a<T> extends com.a.a.c.k.j<T> implements com.a.a.c.k.k {

    /* renamed from: b, reason: collision with root package name */
    protected final com.a.a.c.c f590b;
    protected final Boolean c;

    public abstract com.a.a.c.o<?> a(com.a.a.c.c cVar, Boolean bool);

    protected abstract void b(T t, com.a.a.b.h hVar, com.a.a.c.aa aaVar);

    /* JADX INFO: Access modifiers changed from: protected */
    public a(Class<T> cls) {
        super(cls);
        this.f590b = null;
        this.c = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public a(a<?> aVar, com.a.a.c.c cVar, Boolean bool) {
        super(aVar.h, false);
        this.f590b = cVar;
        this.c = bool;
    }

    public com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.c cVar) {
        l.d a2;
        if (cVar != null && (a2 = a(aaVar, cVar, (Class<?>) a())) != null) {
            Boolean a3 = a2.a(l.a.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
            if (!Objects.equals(a3, this.c)) {
                return a(cVar, a3);
            }
        }
        return this;
    }

    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    public void a(T t, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        if (a(aaVar) && a((a<T>) t)) {
            b((a<T>) t, hVar, aaVar);
            return;
        }
        hVar.b(t);
        b((a<T>) t, hVar, aaVar);
        hVar.h();
    }

    @Override // com.a.a.c.o
    public final void a(T t, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.i.i iVar) {
        com.a.a.b.f.a a2 = iVar.a(hVar, iVar.a(t, com.a.a.b.o.START_ARRAY));
        hVar.a(t);
        b((a<T>) t, hVar, aaVar);
        iVar.b(hVar, a2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean a(com.a.a.c.aa aaVar) {
        if (this.c == null) {
            return aaVar.a(com.a.a.c.z.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
        }
        return this.c.booleanValue();
    }
}
