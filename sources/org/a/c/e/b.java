package org.a.c.e;

import com.a.a.a.am;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.a.c.b.d;
import org.a.c.b.j;
import org.a.c.b.m;
import org.a.c.b.p;
import org.a.c.h.a.c;

/* loaded from: infinitode-2.jar:org/a/c/e/b.class */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.c.h.b f4427a;

    /* renamed from: b, reason: collision with root package name */
    private final Map<Object, org.a.c.b.b> f4428b = new HashMap();
    private final Set<org.a.c.b.b> c = new HashSet();

    public b(org.a.c.h.b bVar) {
        this.f4427a = bVar;
    }

    public final org.a.c.b.b a(Object obj) {
        org.a.c.b.b bVar;
        if (obj == null) {
            return null;
        }
        org.a.c.b.b bVar2 = this.f4428b.get(obj);
        if (bVar2 != null) {
            return bVar2;
        }
        if ((obj instanceof org.a.c.b.b) && this.c.contains(obj)) {
            return (org.a.c.b.b) obj;
        }
        if (obj instanceof List) {
            org.a.c.b.a aVar = new org.a.c.b.a();
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                aVar.a(a(it.next()));
            }
            bVar = aVar;
        } else if ((obj instanceof c) && !(obj instanceof org.a.c.b.b)) {
            bVar = a(((c) obj).f());
        } else if (obj instanceof m) {
            bVar = a(((m) obj).a());
        } else if (obj instanceof org.a.c.b.a) {
            org.a.c.b.a aVar2 = new org.a.c.b.a();
            org.a.c.b.a aVar3 = (org.a.c.b.a) obj;
            for (int i = 0; i < aVar3.b(); i++) {
                aVar2.a(a(aVar3.b(i)));
            }
            bVar = aVar2;
        } else if (obj instanceof p) {
            p pVar = (p) obj;
            p a2 = this.f4427a.a().a();
            OutputStream m = a2.m();
            InputStream j = pVar.j();
            am.a(j, m);
            j.close();
            m.close();
            this.f4428b.put(obj, a2);
            for (Map.Entry<j, org.a.c.b.b> entry : pVar.e()) {
                a2.a(entry.getKey(), a(entry.getValue()));
            }
            bVar = a2;
        } else if (obj instanceof d) {
            bVar = new d();
            this.f4428b.put(obj, bVar);
            for (Map.Entry<j, org.a.c.b.b> entry2 : ((d) obj).e()) {
                ((d) bVar).a(entry2.getKey(), a(entry2.getValue()));
            }
        } else {
            bVar = (org.a.c.b.b) obj;
        }
        this.f4428b.put(obj, bVar);
        this.c.add(bVar);
        return bVar;
    }

    public final void a(c cVar, c cVar2) {
        if (cVar == null) {
            return;
        }
        org.a.c.b.b bVar = this.f4428b.get(cVar);
        org.a.c.b.b bVar2 = bVar;
        if (bVar != null) {
            return;
        }
        if (!(cVar instanceof org.a.c.b.b)) {
            a(cVar.f(), cVar2.f());
        } else if (cVar instanceof m) {
            if (cVar2 instanceof m) {
                a(((m) cVar).a(), ((m) cVar2).a());
            } else if ((cVar2 instanceof d) || (cVar2 instanceof org.a.c.b.a)) {
                a(((m) cVar).a(), cVar2);
            }
        } else if (cVar instanceof org.a.c.b.a) {
            org.a.c.b.a aVar = (org.a.c.b.a) cVar;
            for (int i = 0; i < aVar.b(); i++) {
                ((org.a.c.b.a) cVar2).a(a(aVar.b(i)));
            }
        } else if (cVar instanceof p) {
            p pVar = (p) cVar;
            p a2 = this.f4427a.a().a();
            OutputStream a3 = a2.a(pVar.o());
            am.a(pVar.k(), a3);
            a3.close();
            this.f4428b.put(cVar, a2);
            for (Map.Entry<j, org.a.c.b.b> entry : pVar.e()) {
                a2.a(entry.getKey(), a(entry.getValue()));
            }
            bVar2 = a2;
        } else if (cVar instanceof d) {
            this.f4428b.put(cVar, bVar2);
            for (Map.Entry<j, org.a.c.b.b> entry2 : ((d) cVar).e()) {
                j key = entry2.getKey();
                c cVar3 = (org.a.c.b.b) entry2.getValue();
                if (((d) cVar2).n(key) != null) {
                    a(cVar3, ((d) cVar2).n(key));
                } else {
                    ((d) cVar2).a(key, a(cVar3));
                }
            }
        } else {
            bVar2 = (org.a.c.b.b) cVar;
        }
        this.f4428b.put(cVar, bVar2);
        this.c.add(bVar2);
    }
}
