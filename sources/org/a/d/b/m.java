package org.a.d.b;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:org/a/d/b/m.class */
public final class m {

    /* renamed from: a, reason: collision with root package name */
    private final List<b> f4685a = new ArrayList();

    public final void a(b bVar) {
        if (c(bVar)) {
            b(bVar);
        }
        this.f4685a.add(bVar);
    }

    public final List<b> a() {
        return this.f4685a;
    }

    private List<b> b(String str) {
        List<b> a2 = a();
        if (a2 != null) {
            ArrayList arrayList = new ArrayList();
            for (b bVar : a2) {
                if (bVar.e().equals(str)) {
                    arrayList.add(bVar);
                }
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            return arrayList;
        }
        return null;
    }

    private static boolean a(b bVar, b bVar2) {
        if (bVar.getClass().equals(bVar2.getClass())) {
            String e = bVar.e();
            String e2 = bVar2.e();
            if (e == null) {
                return e2 == null;
            }
            if (e.equals(e2)) {
                return bVar.equals(bVar2);
            }
            return false;
        }
        return false;
    }

    private boolean c(b bVar) {
        Iterator<b> it = a().iterator();
        while (it.hasNext()) {
            if (a(it.next(), bVar)) {
                return true;
            }
        }
        return false;
    }

    public final void b(b bVar) {
        if (c(bVar)) {
            this.f4685a.remove(bVar);
        }
    }

    public final void a(String str) {
        List<b> b2;
        if (this.f4685a.isEmpty() || (b2 = b(str)) == null) {
            return;
        }
        Iterator<b> it = b2.iterator();
        while (it.hasNext()) {
            this.f4685a.remove(it.next());
        }
    }
}
