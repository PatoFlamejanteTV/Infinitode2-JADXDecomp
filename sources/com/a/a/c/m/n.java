package com.a.a.c.m;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/a/a/c/m/n.class */
public class n {

    /* renamed from: a, reason: collision with root package name */
    private int f735a;

    /* renamed from: b, reason: collision with root package name */
    private Class<?> f736b;
    private com.a.a.c.j c;
    private boolean d;

    public static boolean a(Object obj, Collection<String> collection, Collection<String> collection2) {
        if (collection == null && collection2 == null) {
            return false;
        }
        if (collection2 == null) {
            return collection.contains(obj);
        }
        return collection == null ? !collection2.contains(obj) : !collection2.contains(obj) || collection.contains(obj);
    }

    public static a a(Set<String> set, Set<String> set2) {
        if (set2 == null && (set == null || set.isEmpty())) {
            return null;
        }
        return a.a(set, set2);
    }

    public static Set<String> b(Set<String> set, Set<String> set2) {
        if (set == null) {
            return set2;
        }
        if (set2 == null) {
            return set;
        }
        HashSet hashSet = new HashSet();
        for (String str : set2) {
            if (set.contains(str)) {
                hashSet.add(str);
            }
        }
        return hashSet;
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/m/n$a.class */
    public static final class a implements Serializable {

        /* renamed from: a, reason: collision with root package name */
        private final Set<String> f737a;

        /* renamed from: b, reason: collision with root package name */
        private final Set<String> f738b;

        private a(Set<String> set, Set<String> set2) {
            this.f737a = set == null ? Collections.emptySet() : set;
            this.f738b = set2;
        }

        public static a a(Set<String> set, Set<String> set2) {
            return new a(set, set2);
        }

        public final boolean a(Object obj) {
            return !(this.f738b == null || this.f738b.contains(obj)) || this.f737a.contains(obj);
        }
    }

    public n() {
    }

    public n(Class<?> cls, boolean z) {
        this.f736b = cls;
        this.c = null;
        this.d = z;
        this.f735a = z ? b(cls) : a(cls);
    }

    public n(com.a.a.c.j jVar, boolean z) {
        this.c = jVar;
        this.f736b = null;
        this.d = z;
        this.f735a = z ? b(jVar) : a(jVar);
    }

    public static int a(Class<?> cls) {
        return cls.getName().hashCode();
    }

    public static int b(Class<?> cls) {
        return cls.getName().hashCode() + 1;
    }

    public static int a(com.a.a.c.j jVar) {
        return jVar.hashCode() - 1;
    }

    public static int b(com.a.a.c.j jVar) {
        return jVar.hashCode() - 2;
    }

    public boolean a() {
        return this.d;
    }

    public Class<?> b() {
        return this.f736b;
    }

    public com.a.a.c.j c() {
        return this.c;
    }

    public int hashCode() {
        return this.f735a;
    }

    public String toString() {
        if (this.f736b != null) {
            return "{class: " + this.f736b.getName() + ", typed? " + this.d + "}";
        }
        return "{type: " + this.c + ", typed? " + this.d + "}";
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        n nVar = (n) obj;
        if (nVar.d == this.d) {
            if (this.f736b != null) {
                return nVar.f736b == this.f736b;
            }
            return this.c.equals(nVar.c);
        }
        return false;
    }
}
