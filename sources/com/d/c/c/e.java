package com.d.c.c;

import com.d.c.d.j;
import com.d.i.v;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/d/c/c/e.class */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    private final a f989a;

    /* renamed from: b, reason: collision with root package name */
    private final Map<com.d.c.a.d, List<v>> f990b;
    private final List c;

    public e(List list, a aVar, Map<com.d.c.a.d, List<v>> map) {
        this.f989a = aVar;
        this.f990b = map;
        this.c = map.remove(com.d.c.a.d.q);
    }

    public final a a() {
        return this.f989a;
    }

    public final a a(com.d.c.a.d dVar, boolean z) {
        ArrayList arrayList;
        List<v> list = this.f990b.get(dVar);
        if ((list == null || list.size() == 0) && !z) {
            return null;
        }
        if (list != null) {
            ArrayList arrayList2 = new ArrayList(list.size() + 3);
            arrayList = arrayList2;
            arrayList2.addAll(list);
        } else {
            arrayList = new ArrayList(3);
        }
        arrayList.add(a.a(com.d.c.a.a.G, com.d.c.a.c.aX));
        arrayList.add(new v(com.d.c.a.a.as, new j(dVar.b()), false, 0));
        arrayList.add(new v(com.d.c.a.a.an, new j(dVar.a()), false, 0));
        return new a(arrayList.iterator());
    }

    public final boolean a(com.d.c.a.d[] dVarArr) {
        for (com.d.c.a.d dVar : dVarArr) {
            if (this.f990b.containsKey(dVar)) {
                return true;
            }
        }
        return false;
    }

    public final List b() {
        return this.c;
    }
}
