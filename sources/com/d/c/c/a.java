package com.d.c.c;

import com.d.c.d.j;
import com.d.i.v;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: infinitode-2.jar:com/d/c/c/a.class */
public final class a {

    /* renamed from: b, reason: collision with root package name */
    private Map<com.d.c.a.a, v> f976b;
    private String c;

    /* renamed from: a, reason: collision with root package name */
    public static final a f977a = new a();

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Iterator<v> it) {
        this();
        a(it);
    }

    public static a a(v[] vVarArr) {
        return new a(Arrays.asList(vVarArr).iterator());
    }

    public static a a(List<v> list) {
        return new a(list.iterator());
    }

    public static a a(a aVar, v[] vVarArr) {
        return new a(aVar, Arrays.asList(vVarArr).iterator());
    }

    public static v a(com.d.c.a.a aVar, com.d.c.a.c cVar) {
        return new v(aVar, new j(cVar), true, 1);
    }

    private a(a aVar, Iterator<v> it) {
        this.f976b = new TreeMap(aVar.f976b);
        a(it);
    }

    private a() {
        this.f976b = new TreeMap();
    }

    public static a a(com.d.c.a.c cVar) {
        return new a(Collections.singletonList(new v(com.d.c.a.a.G, new j(cVar), true, 1)).iterator());
    }

    private void a(Iterator<v> it) {
        List[] listArr = new List[6];
        while (it.hasNext()) {
            v next = it.next();
            List list = listArr[next.b()];
            List list2 = list;
            if (list == null) {
                list2 = new ArrayList();
                listArr[next.b()] = list2;
            }
            list2.add(next);
        }
        for (int i = 0; i < 6; i++) {
            List<v> list3 = listArr[i];
            if (list3 != null) {
                for (v vVar : list3) {
                    this.f976b.put(vVar.d(), vVar);
                }
            }
        }
    }

    public final v a(com.d.c.a.a aVar) {
        return this.f976b.get(aVar);
    }

    public final Collection<v> a() {
        return this.f976b.values();
    }

    public final String b() {
        if (this.c == null) {
            StringBuilder sb = new StringBuilder();
            Iterator<v> it = this.f976b.values().iterator();
            while (it.hasNext()) {
                sb.append(it.next().a());
            }
            this.c = sb.toString();
        }
        return this.c;
    }
}
