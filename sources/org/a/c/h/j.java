package org.a.c.h;

import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.a.c.b.m;
import org.a.c.h.e.u;
import org.a.c.h.e.w;
import org.a.c.h.f.a.t;

/* loaded from: infinitode-2.jar:org/a/c/h/j.class */
public final class j implements org.a.c.h.a.c {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.c.b.d f4649a;

    /* renamed from: b, reason: collision with root package name */
    private final k f4650b;
    private final Map<org.a.c.b.j, SoftReference<u>> c;

    public j() {
        this.c = new HashMap();
        this.f4649a = new org.a.c.b.d();
        this.f4650b = null;
    }

    public j(org.a.c.b.d dVar, k kVar) {
        this.c = new HashMap();
        if (dVar == null) {
            throw new IllegalArgumentException("resourceDictionary is null");
        }
        this.f4649a = dVar;
        this.f4650b = kVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // org.a.c.h.a.c
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public org.a.c.b.d f() {
        return this.f4649a;
    }

    public final u a(org.a.c.b.j jVar) {
        SoftReference<u> softReference;
        u uVar;
        m a2 = a(org.a.c.b.j.be, jVar);
        if (this.f4650b != null && a2 != null) {
            u a3 = this.f4650b.a(a2);
            if (a3 != null) {
                return a3;
            }
        } else if (a2 == null && (softReference = this.c.get(jVar)) != null && (uVar = softReference.get()) != null) {
            return uVar;
        }
        u uVar2 = null;
        org.a.c.b.d dVar = (org.a.c.b.d) b(org.a.c.b.j.be, jVar);
        if (dVar != null) {
            uVar2 = w.a(dVar, this.f4650b);
        }
        if (this.f4650b != null && a2 != null) {
            this.f4650b.a(a2, uVar2);
        } else if (a2 == null) {
            this.c.put(jVar, new SoftReference<>(uVar2));
        }
        return uVar2;
    }

    public final org.a.c.h.f.a.f b(org.a.c.b.j jVar) {
        return a(jVar, false);
    }

    public final org.a.c.h.f.a.f a(org.a.c.b.j jVar, boolean z) {
        org.a.c.h.f.a.f a2;
        org.a.c.h.f.a.f b2;
        m a3 = a(org.a.c.b.j.ac, jVar);
        if (this.f4650b != null && a3 != null && (b2 = this.f4650b.b(a3)) != null) {
            return b2;
        }
        org.a.c.b.b b3 = b(org.a.c.b.j.ac, jVar);
        if (b3 != null) {
            a2 = org.a.c.h.f.a.f.a(b3, this, z);
        } else {
            a2 = org.a.c.h.f.a.f.a(jVar, this, z);
        }
        if (this.f4650b != null && !(a2 instanceof t)) {
            this.f4650b.a(a3, a2);
        }
        return a2;
    }

    public final boolean c(org.a.c.b.j jVar) {
        return b(org.a.c.b.j.ac, jVar) != null;
    }

    private m a(org.a.c.b.j jVar, org.a.c.b.j jVar2) {
        org.a.c.b.d dVar = (org.a.c.b.d) this.f4649a.a(jVar);
        if (dVar == null) {
            return null;
        }
        org.a.c.b.b n = dVar.n(jVar2);
        if (n instanceof m) {
            return (m) n;
        }
        return null;
    }

    private org.a.c.b.b b(org.a.c.b.j jVar, org.a.c.b.j jVar2) {
        org.a.c.b.d dVar = (org.a.c.b.d) this.f4649a.a(jVar);
        if (dVar == null) {
            return null;
        }
        return dVar.a(jVar2);
    }

    public final Iterable<org.a.c.b.j> a() {
        return d(org.a.c.b.j.be);
    }

    private Iterable<org.a.c.b.j> d(org.a.c.b.j jVar) {
        org.a.c.b.d dVar = (org.a.c.b.d) this.f4649a.a(jVar);
        if (dVar == null) {
            return Collections.emptySet();
        }
        return dVar.d();
    }

    public final org.a.c.b.j a(u uVar) {
        return a(org.a.c.b.j.be, "F", uVar);
    }

    public final org.a.c.b.j a(org.a.c.h.f.a.f fVar) {
        return a(org.a.c.b.j.ac, "cs", fVar);
    }

    public final org.a.c.b.j a(org.a.c.h.f.c.b bVar) {
        return a(org.a.c.b.j.ee, "Im", bVar);
    }

    private org.a.c.b.j a(org.a.c.b.j jVar, String str, org.a.c.h.a.c cVar) {
        org.a.c.b.d dVar = (org.a.c.b.d) this.f4649a.a(jVar);
        if (dVar != null && dVar.a(cVar.f())) {
            return dVar.b(cVar.f());
        }
        if (dVar != null && org.a.c.b.j.be.equals(jVar)) {
            for (Map.Entry<org.a.c.b.j, org.a.c.b.b> entry : dVar.e()) {
                if ((entry.getValue() instanceof m) && cVar.f() == ((m) entry.getValue()).a()) {
                    return entry.getKey();
                }
            }
        }
        org.a.c.b.j a2 = a(jVar, str);
        a(jVar, a2, cVar);
        return a2;
    }

    private org.a.c.b.j a(org.a.c.b.j jVar, String str) {
        String str2;
        org.a.c.b.d dVar = (org.a.c.b.d) this.f4649a.a(jVar);
        if (dVar == null) {
            return org.a.c.b.j.a(str + 1);
        }
        int size = dVar.d().size();
        do {
            size++;
            str2 = str + size;
        } while (dVar.b(str2));
        return org.a.c.b.j.a(str2);
    }

    private void a(org.a.c.b.j jVar, org.a.c.b.j jVar2, org.a.c.h.a.c cVar) {
        org.a.c.b.d dVar = (org.a.c.b.d) this.f4649a.a(jVar);
        org.a.c.b.d dVar2 = dVar;
        if (dVar == null) {
            dVar2 = new org.a.c.b.d();
            this.f4649a.a(jVar, (org.a.c.b.b) dVar2);
        }
        dVar2.a(jVar2, cVar);
    }

    public final void a(org.a.c.b.j jVar, u uVar) {
        a(org.a.c.b.j.be, jVar, uVar);
    }

    public final k b() {
        return this.f4650b;
    }
}
