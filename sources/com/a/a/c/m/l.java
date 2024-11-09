package com.a.a.c.m;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/m/l.class */
public final class l implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private Class<Enum<?>> f731a;

    /* renamed from: b, reason: collision with root package name */
    private Enum<?>[] f732b;
    private HashMap<String, Enum<?>> c;
    private Enum<?> d;
    private boolean e;
    private boolean f;

    private l(Class<Enum<?>> cls, Enum<?>[] enumArr, HashMap<String, Enum<?>> hashMap, Enum<?> r7, boolean z, boolean z2) {
        this.f731a = cls;
        this.f732b = enumArr;
        this.c = hashMap;
        this.d = r7;
        this.e = z;
        this.f = z2;
    }

    public static l a(com.a.a.c.f fVar, Class<?> cls) {
        return a(cls, fVar.j(), fVar.a(com.a.a.c.q.ACCEPT_CASE_INSENSITIVE_ENUMS));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.String[], java.lang.String[][]] */
    private static l a(Class<?> cls, com.a.a.c.a aVar, boolean z) {
        Class<Enum<?>> a2 = a(cls);
        Enum<?>[] b2 = b(cls);
        String[] a3 = aVar.a(a2, b2, new String[b2.length]);
        ?? r0 = new String[a3.length];
        aVar.a(a2, b2, (String[][]) r0);
        HashMap hashMap = new HashMap();
        int length = b2.length;
        for (int i = 0; i < length; i++) {
            Enum<?> r02 = b2[i];
            String str = a3[i];
            String str2 = str;
            if (str == null) {
                str2 = r02.name();
            }
            hashMap.put(str2, r02);
            Object[] objArr = r0[i];
            if (objArr != 0) {
                for (Object[] objArr2 : objArr) {
                    if (!hashMap.containsKey(objArr2)) {
                        hashMap.put(objArr2, r02);
                    }
                }
            }
        }
        return new l(a2, b2, hashMap, a(aVar, a2), z, false);
    }

    public static l b(com.a.a.c.f fVar, Class<?> cls) {
        return b(cls, fVar.j(), fVar.a(com.a.a.c.q.ACCEPT_CASE_INSENSITIVE_ENUMS));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.String[], java.lang.String[][]] */
    private static l b(Class<?> cls, com.a.a.c.a aVar, boolean z) {
        Class<Enum<?>> a2 = a(cls);
        Enum<?>[] b2 = b(cls);
        HashMap hashMap = new HashMap();
        ?? r0 = new String[b2.length];
        if (aVar != 0) {
            aVar.a(a2, b2, (String[][]) r0);
        }
        int length = b2.length;
        while (true) {
            length--;
            if (length >= 0) {
                Enum<?> r02 = b2[length];
                hashMap.put(r02.toString(), r02);
                Object[] objArr = r0[length];
                if (objArr != 0) {
                    for (Object[] objArr2 : objArr) {
                        if (!hashMap.containsKey(objArr2)) {
                            hashMap.put(objArr2, r02);
                        }
                    }
                }
            } else {
                return new l(a2, b2, hashMap, a(aVar, a2), z, false);
            }
        }
    }

    public static l a(com.a.a.c.f fVar, Class<?> cls, com.a.a.c.f.j jVar) {
        return a(cls, jVar, fVar.j(), fVar.a(com.a.a.c.q.ACCEPT_CASE_INSENSITIVE_ENUMS));
    }

    private static l a(Class<?> cls, com.a.a.c.f.j jVar, com.a.a.c.a aVar, boolean z) {
        Class<Enum<?>> a2 = a(cls);
        Enum<?>[] b2 = b(cls);
        HashMap hashMap = new HashMap();
        int length = b2.length;
        while (true) {
            length--;
            if (length >= 0) {
                Enum<?> r0 = b2[length];
                try {
                    Object b3 = jVar.b(r0);
                    if (b3 != null) {
                        hashMap.put(b3.toString(), r0);
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException("Failed to access @JsonValue of Enum value " + r0 + ": " + e.getMessage());
                }
            } else {
                return new l(a2, b2, hashMap, a(aVar, a2), z, c(jVar.d()));
            }
        }
    }

    public final j a() {
        return j.a(this.c);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static Class<Enum<?>> a(Class<?> cls) {
        return cls;
    }

    private static Enum<?>[] b(Class<?> cls) {
        Enum<?>[] enumConstants = a(cls).getEnumConstants();
        if (enumConstants == null) {
            throw new IllegalArgumentException("No enum constants for class " + cls.getName());
        }
        return enumConstants;
    }

    private static Enum<?> a(com.a.a.c.a aVar, Class<?> cls) {
        if (aVar != null) {
            return aVar.a(a(cls));
        }
        return null;
    }

    private static boolean c(Class<?> cls) {
        if (cls.isPrimitive()) {
            cls = i.i(cls);
        }
        return cls == Long.class || cls == Integer.class || cls == Short.class || cls == Byte.class;
    }

    public final Enum<?> a(String str) {
        Enum<?> r0 = this.c.get(str);
        if (r0 == null && this.e) {
            return b(str);
        }
        return r0;
    }

    private Enum<?> b(String str) {
        for (Map.Entry<String, Enum<?>> entry : this.c.entrySet()) {
            if (str.equalsIgnoreCase(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public final Enum<?> b() {
        return this.d;
    }

    public final Enum<?>[] c() {
        return this.f732b;
    }

    public final Collection<String> d() {
        return this.c.keySet();
    }

    public final Class<Enum<?>> e() {
        return this.f731a;
    }

    public final boolean f() {
        return this.f;
    }
}
