package com.a.a.c.i.a;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: infinitode-2.jar:com/a/a/c/i/a/t.class */
public final class t extends s {
    private com.a.a.c.b.q<?> c;
    private ConcurrentHashMap<String, String> d;
    private Map<String, com.a.a.c.j> e;
    private boolean f;

    private t(com.a.a.c.b.q<?> qVar, com.a.a.c.j jVar, ConcurrentHashMap<String, String> concurrentHashMap, HashMap<String, com.a.a.c.j> hashMap) {
        super(jVar, qVar.p());
        this.c = qVar;
        this.d = concurrentHashMap;
        this.e = hashMap;
        this.f = qVar.a(com.a.a.c.q.ACCEPT_CASE_INSENSITIVE_VALUES);
    }

    public static t a(com.a.a.c.b.q<?> qVar, com.a.a.c.j jVar, Collection<com.a.a.c.i.b> collection, boolean z, boolean z2) {
        HashMap hashMap;
        ConcurrentHashMap concurrentHashMap;
        if (z == z2) {
            throw new IllegalArgumentException();
        }
        if (z) {
            concurrentHashMap = new ConcurrentHashMap();
            hashMap = null;
        } else {
            hashMap = new HashMap();
            concurrentHashMap = new ConcurrentHashMap(4);
        }
        boolean a2 = qVar.a(com.a.a.c.q.ACCEPT_CASE_INSENSITIVE_VALUES);
        if (collection != null) {
            for (com.a.a.c.i.b bVar : collection) {
                Class<?> a3 = bVar.a();
                String b2 = bVar.c() ? bVar.b() : b(a3);
                if (z) {
                    concurrentHashMap.put(a3.getName(), b2);
                }
                if (z2) {
                    if (a2) {
                        b2 = b2.toLowerCase();
                    }
                    com.a.a.c.j jVar2 = (com.a.a.c.j) hashMap.get(b2);
                    if (jVar2 == null || !a3.isAssignableFrom(jVar2.b())) {
                        hashMap.put(b2, qVar.b(a3));
                    }
                }
            }
        }
        return new t(qVar, jVar, concurrentHashMap, hashMap);
    }

    @Override // com.a.a.c.i.g
    public final String a(Object obj) {
        return a(obj.getClass());
    }

    private String a(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        String name = cls.getName();
        String str = this.d.get(name);
        String str2 = str;
        if (str == null) {
            Class<?> b2 = this.f513a.a((Type) cls).b();
            if (this.c.f()) {
                str2 = this.c.j().d(this.c.c(b2).d());
            }
            if (str2 == null) {
                str2 = b(b2);
            }
            this.d.put(name, str2);
        }
        return str2;
    }

    @Override // com.a.a.c.i.g
    public final String a(Object obj, Class<?> cls) {
        if (obj == null) {
            return a(cls);
        }
        return a(obj);
    }

    @Override // com.a.a.c.i.a.s, com.a.a.c.i.g
    public final com.a.a.c.j a(com.a.a.c.d dVar, String str) {
        return a(str);
    }

    private com.a.a.c.j a(String str) {
        if (this.f) {
            str = str.toLowerCase();
        }
        return this.e.get(str);
    }

    @Override // com.a.a.c.i.a.s, com.a.a.c.i.g
    public final String b() {
        TreeSet treeSet = new TreeSet();
        for (Map.Entry<String, com.a.a.c.j> entry : this.e.entrySet()) {
            if (entry.getValue().e()) {
                treeSet.add(entry.getKey());
            }
        }
        return treeSet.toString();
    }

    public final String toString() {
        return String.format("[%s; id-to-type=%s]", getClass().getName(), this.e);
    }

    private static String b(Class<?> cls) {
        String name = cls.getName();
        int lastIndexOf = name.lastIndexOf(46);
        return lastIndexOf < 0 ? name : name.substring(lastIndexOf + 1);
    }
}
