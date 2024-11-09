package org.a.c.h.f.a;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:org/a/c/h/f/a/l.class */
public final class l {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.c.b.d f4579a;

    public l() {
        this.f4579a = new org.a.c.b.d();
    }

    public l(org.a.c.b.d dVar) {
        this.f4579a = dVar;
    }

    public final f a() {
        org.a.c.b.b a2 = this.f4579a.a(org.a.c.b.j.ac);
        if (a2 == null) {
            return null;
        }
        return f.a(a2);
    }

    public final List<String> b() {
        ArrayList arrayList = new ArrayList();
        org.a.c.b.a aVar = (org.a.c.b.a) this.f4579a.a(org.a.c.b.j.ae);
        if (aVar == null) {
            return arrayList;
        }
        Iterator<org.a.c.b.b> it = aVar.iterator();
        while (it.hasNext()) {
            arrayList.add(((org.a.c.b.j) it.next()).a());
        }
        return arrayList;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Process{");
        try {
            sb.append(a());
            for (String str : b()) {
                sb.append(" \"");
                sb.append(str);
                sb.append('\"');
            }
        } catch (IOException unused) {
            sb.append("ERROR");
        }
        sb.append('}');
        return sb.toString();
    }
}
