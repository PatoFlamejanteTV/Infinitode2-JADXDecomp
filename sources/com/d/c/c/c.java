package com.d.c.c;

import com.d.i.a.r;
import com.d.m.k;
import com.d.m.l;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* loaded from: infinitode-2.jar:com/d/c/c/c.class */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    private a f985a;

    /* renamed from: b, reason: collision with root package name */
    private com.d.c.b.a f986b;
    private com.d.c.b.d c;
    private com.d.c.b.c d;
    private Map<Object, a> e;
    private Set<Object> f;
    private Set<Object> g;
    private Set<Object> h;
    private Set<Object> i;
    private final List<com.d.c.e.c> j = new ArrayList();
    private final List<com.d.c.e.a> k = new ArrayList();

    public c(com.d.c.b.d dVar, com.d.c.b.a aVar, com.d.c.b.c cVar, List<r> list, String str) {
        b();
        this.c = dVar;
        this.f986b = aVar;
        this.d = cVar;
        this.f985a = a(list, str);
    }

    public final com.d.c.c.a a(Object obj, boolean z) {
        a a2;
        if (!z) {
            a2 = b(obj);
        } else {
            a2 = a(obj);
        }
        return a2.b(obj);
    }

    public final com.d.c.c.a a(Object obj, String str) {
        return b(obj).a(str);
    }

    public final e a(String str, String str2) {
        com.d.c.c.a aVar;
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        for (com.d.c.e.c cVar : this.j) {
            if (cVar.a(str, str2)) {
                arrayList.addAll(cVar.b().a());
                hashMap.putAll(cVar.c());
            }
        }
        if (arrayList.isEmpty()) {
            aVar = com.d.c.c.a.f977a;
        } else {
            aVar = new com.d.c.c.a(arrayList.iterator());
        }
        return new e(arrayList, aVar, hashMap);
    }

    public final List<com.d.c.e.a> a() {
        return this.k;
    }

    private a a(Object obj) {
        a a2;
        Object a3 = this.c.a(obj);
        if (a3 != null) {
            a2 = b(a3).a(obj);
        } else {
            a2 = this.f985a.a(obj);
        }
        return a2;
    }

    private a a(List<r> list, String str) {
        TreeMap<String, f> treeMap = new TreeMap<>();
        a(list, treeMap, str);
        l.f("Matcher created with " + treeMap.size() + " selectors");
        return new a(treeMap.values());
    }

    private void a(List<r> list, TreeMap<String, f> treeMap, String str) {
        int i = 0;
        int i2 = 0;
        for (r rVar : list) {
            for (Object obj : rVar.c()) {
                if (obj instanceof com.d.c.e.d) {
                    for (f fVar : ((com.d.c.e.d) obj).b()) {
                        i++;
                        fVar.c(i);
                        treeMap.put(fVar.m(), fVar);
                    }
                } else if (obj instanceof com.d.c.e.c) {
                    i2++;
                    ((com.d.c.e.c) obj).a(i2);
                    this.j.add((com.d.c.e.c) obj);
                } else if (obj instanceof com.d.c.e.b) {
                    com.d.c.e.b bVar = (com.d.c.e.b) obj;
                    if (bVar.b(str)) {
                        Iterator<com.d.c.e.d> it = bVar.b().iterator();
                        while (it.hasNext()) {
                            for (f fVar2 : it.next().b()) {
                                i++;
                                fVar2.c(i);
                                treeMap.put(fVar2.m(), fVar2);
                            }
                        }
                    }
                }
            }
            this.k.addAll(rVar.e());
        }
        Collections.sort(this.j, new d(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Object obj, a aVar) {
        this.e.put(obj, aVar);
    }

    private void b() {
        this.e = new HashMap();
        this.f = new HashSet();
        this.g = new HashSet();
        this.h = new HashSet();
        this.i = new HashSet();
    }

    private a b(Object obj) {
        a aVar = this.e.get(obj);
        if (aVar != null) {
            return aVar;
        }
        return a(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.d.c.e.d c(Object obj) {
        if (this.f986b == null || this.d == null) {
            return null;
        }
        String d = this.f986b.d(obj);
        if (!k.a(d)) {
            return this.d.a(2, d);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.d.c.e.d d(Object obj) {
        if (this.f986b == null || this.d == null) {
            return null;
        }
        String c = this.f986b.c(obj);
        if (!k.a(c)) {
            return this.d.a(2, c);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/c/c/c$a.class */
    public class a {

        /* renamed from: a, reason: collision with root package name */
        private List<f> f987a;

        /* renamed from: b, reason: collision with root package name */
        private HashMap<String, List<f>> f988b;
        private List<f> c;
        private Map<String, a> d;

        a(Collection<f> collection) {
            this.f987a = new ArrayList(collection);
        }

        private a() {
        }

        final a a(Object obj) {
            ArrayList arrayList = new ArrayList(this.f987a.size() + 10);
            HashMap<String, List<f>> hashMap = new HashMap<>();
            ArrayList arrayList2 = new ArrayList();
            StringBuilder sb = new StringBuilder();
            for (f fVar : this.f987a) {
                if (fVar.i() == 0) {
                    arrayList.add(fVar);
                } else if (fVar.i() == 2) {
                    throw new RuntimeException();
                }
                if (fVar.a(obj, c.this.f986b, c.this.c)) {
                    String f = fVar.f();
                    if (f != null) {
                        List<f> list = hashMap.get(f);
                        List<f> list2 = list;
                        if (list == null) {
                            list2 = new ArrayList();
                            hashMap.put(f, list2);
                        }
                        list2.add(fVar);
                        sb.append(fVar.n()).append(":");
                    } else {
                        if (fVar.b(2)) {
                            c.this.i.add(obj);
                        }
                        if (fVar.b(8)) {
                            c.this.g.add(obj);
                        }
                        if (fVar.b(4)) {
                            c.this.f.add(obj);
                        }
                        if (fVar.b(16)) {
                            c.this.h.add(obj);
                        }
                        if (fVar.b(obj, c.this.f986b, c.this.c)) {
                            sb.append(fVar.n()).append(":");
                            f g = fVar.g();
                            if (g == null) {
                                arrayList2.add(fVar);
                            } else {
                                if (g.i() == 2) {
                                    throw new RuntimeException();
                                }
                                arrayList.add(g);
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
            if (this.d == null) {
                this.d = new HashMap();
            }
            a aVar = this.d.get(sb.toString());
            a aVar2 = aVar;
            if (aVar == null) {
                a aVar3 = new a();
                aVar2 = aVar3;
                aVar3.f987a = arrayList;
                aVar2.f988b = hashMap;
                aVar2.c = arrayList2;
                this.d.put(sb.toString(), aVar2);
            }
            c.this.a(obj, aVar2);
            return aVar2;
        }

        final com.d.c.c.a b(Object obj) {
            com.d.c.c.a aVar;
            com.d.c.e.d c = c.this.c(obj);
            com.d.c.e.d d = c.this.d(obj);
            ArrayList arrayList = new ArrayList();
            if (d != null) {
                arrayList.addAll(d.a());
            }
            Iterator<f> it = this.c.iterator();
            while (it.hasNext()) {
                arrayList.addAll(it.next().h().a());
            }
            if (c != null) {
                arrayList.addAll(c.a());
            }
            if (arrayList.size() == 0) {
                aVar = com.d.c.c.a.f977a;
            } else {
                aVar = new com.d.c.c.a(arrayList.iterator());
            }
            return aVar;
        }

        public final com.d.c.c.a a(String str) {
            List<f> list;
            com.d.c.c.a aVar;
            if (!this.f988b.entrySet().iterator().hasNext() || (list = this.f988b.get(str)) == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Iterator<f> it = list.iterator();
            while (it.hasNext()) {
                arrayList.addAll(it.next().h().a());
            }
            if (arrayList.size() == 0) {
                aVar = com.d.c.c.a.f977a;
            } else {
                aVar = new com.d.c.c.a(arrayList.iterator());
            }
            return aVar;
        }
    }
}
