package com.a.a.c.j;

import com.a.a.c.aa;
import com.a.a.c.z;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/j/r.class */
public class r extends f<r> implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private Map<String, com.a.a.c.m> f550a;

    public r(l lVar) {
        super(lVar);
        this.f550a = new LinkedHashMap();
    }

    @Override // com.a.a.c.n.a
    public final boolean o() {
        return this.f550a.isEmpty();
    }

    @Override // com.a.a.c.m
    public final m d() {
        return m.OBJECT;
    }

    @Override // com.a.a.c.m
    public final boolean c() {
        return true;
    }

    @Override // com.a.a.c.m
    public final int a() {
        return this.f550a.size();
    }

    @Override // com.a.a.c.m
    public final Iterator<com.a.a.c.m> m() {
        return this.f550a.values().iterator();
    }

    @Override // com.a.a.c.m
    public final com.a.a.c.m a(int i) {
        return null;
    }

    @Override // com.a.a.c.m
    public final com.a.a.c.m a(String str) {
        return this.f550a.get(str);
    }

    @Override // com.a.a.c.m
    public final com.a.a.c.m b(String str) {
        com.a.a.c.m mVar = this.f550a.get(str);
        if (mVar != null) {
            return mVar;
        }
        return o.q();
    }

    @Override // com.a.a.c.m
    public final Iterator<Map.Entry<String, com.a.a.c.m>> n() {
        return this.f550a.entrySet().iterator();
    }

    @Override // com.a.a.c.j.b, com.a.a.c.n
    public final void a(com.a.a.b.h hVar, aa aaVar) {
        if (aaVar != null) {
            boolean z = !aaVar.a(z.WRITE_EMPTY_JSON_ARRAYS);
            boolean z2 = !aaVar.a((com.a.a.c.b.k) com.a.a.c.b.o.WRITE_NULL_PROPERTIES);
            if (z || z2) {
                hVar.c(this);
                a(hVar, aaVar, z, z2);
                hVar.j();
                return;
            }
        }
        hVar.c(this);
        for (Map.Entry<String, com.a.a.c.m> entry : this.f550a.entrySet()) {
            com.a.a.c.m value = entry.getValue();
            hVar.a(entry.getKey());
            value.a(hVar, aaVar);
        }
        hVar.j();
    }

    @Override // com.a.a.c.n
    public final void a(com.a.a.b.h hVar, aa aaVar, com.a.a.c.i.i iVar) {
        boolean z = false;
        boolean z2 = false;
        if (aaVar != null) {
            z = !aaVar.a(z.WRITE_EMPTY_JSON_ARRAYS);
            z2 = !aaVar.a((com.a.a.c.b.k) com.a.a.c.b.o.WRITE_NULL_PROPERTIES);
        }
        com.a.a.b.f.a a2 = iVar.a(hVar, iVar.a(this, com.a.a.b.o.START_OBJECT));
        if (z || z2) {
            a(hVar, aaVar, z, z2);
        } else {
            for (Map.Entry<String, com.a.a.c.m> entry : this.f550a.entrySet()) {
                com.a.a.c.m value = entry.getValue();
                hVar.a(entry.getKey());
                value.a(hVar, aaVar);
            }
        }
        iVar.b(hVar, a2);
    }

    private void a(com.a.a.b.h hVar, aa aaVar, boolean z, boolean z2) {
        for (Map.Entry<String, com.a.a.c.m> entry : this.f550a.entrySet()) {
            b bVar = (b) entry.getValue();
            if (!z || !bVar.b() || !bVar.o()) {
                if (!z2 || !bVar.f()) {
                    hVar.a(entry.getKey());
                    bVar.a(hVar, aaVar);
                }
            }
        }
    }

    public final <T extends com.a.a.c.m> T a(String str, com.a.a.c.m mVar) {
        if (mVar == null) {
            mVar = p();
        }
        this.f550a.put(str, mVar);
        return this;
    }

    public final com.a.a.c.m b(String str, com.a.a.c.m mVar) {
        if (mVar == null) {
            mVar = p();
        }
        return this.f550a.put(str, mVar);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof r)) {
            return a((r) obj);
        }
        return false;
    }

    private boolean a(r rVar) {
        return this.f550a.equals(rVar.f550a);
    }

    public int hashCode() {
        return this.f550a.hashCode();
    }
}
