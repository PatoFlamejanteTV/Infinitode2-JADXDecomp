package com.a.a.c.i.a;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/a/a/c/i/a/n.class */
public final class n extends com.a.a.c.i.d implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private LinkedHashSet<com.a.a.c.i.b> f504a;

    @Override // com.a.a.c.i.d
    public final Collection<com.a.a.c.i.b> a(com.a.a.c.b.q<?> qVar, com.a.a.c.f.j jVar, com.a.a.c.j jVar2) {
        Class<?> d;
        List<com.a.a.c.i.b> f;
        com.a.a.c.a j = qVar.j();
        if (jVar2 != null) {
            d = jVar2.b();
        } else if (jVar != null) {
            d = jVar.d();
        } else {
            throw new IllegalArgumentException("Both property and base type are nulls");
        }
        HashMap<com.a.a.c.i.b, com.a.a.c.i.b> hashMap = new HashMap<>();
        if (this.f504a != null) {
            Iterator<com.a.a.c.i.b> it = this.f504a.iterator();
            while (it.hasNext()) {
                com.a.a.c.i.b next = it.next();
                if (d.isAssignableFrom(next.a())) {
                    a(com.a.a.c.f.e.a(qVar, next.a()), next, qVar, j, hashMap);
                }
            }
        }
        if (jVar != null && (f = j.f((com.a.a.c.f.b) jVar)) != null) {
            for (com.a.a.c.i.b bVar : f) {
                a(com.a.a.c.f.e.a(qVar, bVar.a()), bVar, qVar, j, hashMap);
            }
        }
        a(com.a.a.c.f.e.a(qVar, d), new com.a.a.c.i.b(d, null), qVar, j, hashMap);
        return new ArrayList(hashMap.values());
    }

    @Override // com.a.a.c.i.d
    public final Collection<com.a.a.c.i.b> a(com.a.a.c.b.q<?> qVar, com.a.a.c.f.d dVar) {
        com.a.a.c.a j = qVar.j();
        HashMap<com.a.a.c.i.b, com.a.a.c.i.b> hashMap = new HashMap<>();
        if (this.f504a != null) {
            Class<?> d = dVar.d();
            Iterator<com.a.a.c.i.b> it = this.f504a.iterator();
            while (it.hasNext()) {
                com.a.a.c.i.b next = it.next();
                if (d.isAssignableFrom(next.a())) {
                    a(com.a.a.c.f.e.a(qVar, next.a()), next, qVar, j, hashMap);
                }
            }
        }
        a(dVar, new com.a.a.c.i.b(dVar.d(), null), qVar, j, hashMap);
        return new ArrayList(hashMap.values());
    }

    @Override // com.a.a.c.i.d
    public final Collection<com.a.a.c.i.b> b(com.a.a.c.b.q<?> qVar, com.a.a.c.f.j jVar, com.a.a.c.j jVar2) {
        List<com.a.a.c.i.b> f;
        com.a.a.c.a j = qVar.j();
        Class<?> b2 = jVar2.b();
        HashSet hashSet = new HashSet();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        a(com.a.a.c.f.e.a(qVar, b2), new com.a.a.c.i.b(b2, null), qVar, hashSet, linkedHashMap);
        if (jVar != null && (f = j.f((com.a.a.c.f.b) jVar)) != null) {
            for (com.a.a.c.i.b bVar : f) {
                a(com.a.a.c.f.e.a(qVar, bVar.a()), bVar, qVar, hashSet, linkedHashMap);
            }
        }
        if (this.f504a != null) {
            Iterator<com.a.a.c.i.b> it = this.f504a.iterator();
            while (it.hasNext()) {
                com.a.a.c.i.b next = it.next();
                if (b2.isAssignableFrom(next.a())) {
                    a(com.a.a.c.f.e.a(qVar, next.a()), next, qVar, hashSet, linkedHashMap);
                }
            }
        }
        return a(b2, hashSet, linkedHashMap);
    }

    @Override // com.a.a.c.i.d
    public final Collection<com.a.a.c.i.b> b(com.a.a.c.b.q<?> qVar, com.a.a.c.f.d dVar) {
        Class<?> d = dVar.d();
        HashSet hashSet = new HashSet();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        a(dVar, new com.a.a.c.i.b(d, null), qVar, hashSet, linkedHashMap);
        if (this.f504a != null) {
            Iterator<com.a.a.c.i.b> it = this.f504a.iterator();
            while (it.hasNext()) {
                com.a.a.c.i.b next = it.next();
                if (d.isAssignableFrom(next.a())) {
                    a(com.a.a.c.f.e.a(qVar, next.a()), next, qVar, hashSet, linkedHashMap);
                }
            }
        }
        return a(d, hashSet, linkedHashMap);
    }

    private void a(com.a.a.c.f.d dVar, com.a.a.c.i.b bVar, com.a.a.c.b.q<?> qVar, com.a.a.c.a aVar, HashMap<com.a.a.c.i.b, com.a.a.c.i.b> hashMap) {
        String d;
        if (!bVar.c() && (d = aVar.d(dVar)) != null) {
            bVar = new com.a.a.c.i.b(bVar.a(), d);
        }
        com.a.a.c.i.b bVar2 = new com.a.a.c.i.b(bVar.a());
        if (hashMap.containsKey(bVar2)) {
            if (bVar.c() && !hashMap.get(bVar2).c()) {
                hashMap.put(bVar2, bVar);
                return;
            }
            return;
        }
        hashMap.put(bVar2, bVar);
        List<com.a.a.c.i.b> f = aVar.f((com.a.a.c.f.b) dVar);
        if (f != null && !f.isEmpty()) {
            for (com.a.a.c.i.b bVar3 : f) {
                a(com.a.a.c.f.e.a(qVar, bVar3.a()), bVar3, qVar, aVar, hashMap);
            }
        }
    }

    private void a(com.a.a.c.f.d dVar, com.a.a.c.i.b bVar, com.a.a.c.b.q<?> qVar, Set<Class<?>> set, Map<String, com.a.a.c.i.b> map) {
        List<com.a.a.c.i.b> f;
        String d;
        com.a.a.c.a j = qVar.j();
        if (!bVar.c() && (d = j.d(dVar)) != null) {
            bVar = new com.a.a.c.i.b(bVar.a(), d);
        }
        if (bVar.c()) {
            map.put(bVar.b(), bVar);
        }
        if (set.add(bVar.a()) && (f = j.f((com.a.a.c.f.b) dVar)) != null && !f.isEmpty()) {
            for (com.a.a.c.i.b bVar2 : f) {
                a(com.a.a.c.f.e.a(qVar, bVar2.a()), bVar2, qVar, set, map);
            }
        }
    }

    private static Collection<com.a.a.c.i.b> a(Class<?> cls, Set<Class<?>> set, Map<String, com.a.a.c.i.b> map) {
        ArrayList arrayList = new ArrayList(map.values());
        Iterator<com.a.a.c.i.b> it = map.values().iterator();
        while (it.hasNext()) {
            set.remove(it.next().a());
        }
        for (Class<?> cls2 : set) {
            if (cls2 != cls || !Modifier.isAbstract(cls2.getModifiers())) {
                arrayList.add(new com.a.a.c.i.b(cls2));
            }
        }
        return arrayList;
    }
}
