package com.a.a.c.j;

import com.a.a.c.aa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:com/a/a/c/j/a.class */
public class a extends f<a> implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private final List<com.a.a.c.m> f524a;

    public a(l lVar) {
        super(lVar);
        this.f524a = new ArrayList();
    }

    @Override // com.a.a.c.n.a
    public final boolean o() {
        return this.f524a.isEmpty();
    }

    @Override // com.a.a.c.m
    public final m d() {
        return m.ARRAY;
    }

    @Override // com.a.a.c.m
    public final boolean b() {
        return true;
    }

    @Override // com.a.a.c.m
    public final int a() {
        return this.f524a.size();
    }

    @Override // com.a.a.c.m
    public final Iterator<com.a.a.c.m> m() {
        return this.f524a.iterator();
    }

    @Override // com.a.a.c.m
    public final com.a.a.c.m a(int i) {
        if (i >= 0 && i < this.f524a.size()) {
            return this.f524a.get(i);
        }
        return null;
    }

    @Override // com.a.a.c.m
    public final com.a.a.c.m a(String str) {
        return null;
    }

    @Override // com.a.a.c.m
    public final com.a.a.c.m b(String str) {
        return o.q();
    }

    @Override // com.a.a.c.j.b, com.a.a.c.n
    public final void a(com.a.a.b.h hVar, aa aaVar) {
        List<com.a.a.c.m> list = this.f524a;
        int size = list.size();
        hVar.a(this, size);
        for (int i = 0; i < size; i++) {
            list.get(i).a(hVar, aaVar);
        }
        hVar.h();
    }

    @Override // com.a.a.c.n
    public final void a(com.a.a.b.h hVar, aa aaVar, com.a.a.c.i.i iVar) {
        com.a.a.b.f.a a2 = iVar.a(hVar, iVar.a(this, com.a.a.b.o.START_ARRAY));
        Iterator<com.a.a.c.m> it = this.f524a.iterator();
        while (it.hasNext()) {
            ((b) it.next()).a(hVar, aaVar);
        }
        iVar.b(hVar, a2);
    }

    public final a a(com.a.a.c.m mVar) {
        if (mVar == null) {
            mVar = p();
        }
        b(mVar);
        return this;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof a)) {
            return this.f524a.equals(((a) obj).f524a);
        }
        return false;
    }

    public int hashCode() {
        return this.f524a.hashCode();
    }

    private a b(com.a.a.c.m mVar) {
        this.f524a.add(mVar);
        return this;
    }
}
