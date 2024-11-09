package com.a.a.c.b;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/b/j.class */
public abstract class j {
    public abstract Object a(Object obj);

    public abstract j a(Object obj, Object obj2);

    public static j a() {
        return a.b();
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/b/j$a.class */
    public static class a extends j implements Serializable {

        /* renamed from: a, reason: collision with root package name */
        private static a f228a = new a(Collections.emptyMap());

        /* renamed from: b, reason: collision with root package name */
        private static Object f229b = new Object();
        private Map<?, ?> c;
        private transient Map<Object, Object> d;

        private a(Map<?, ?> map) {
            this.c = map;
            this.d = null;
        }

        private a(Map<?, ?> map, Map<Object, Object> map2) {
            this.c = map;
            this.d = map2;
        }

        public static j b() {
            return f228a;
        }

        @Override // com.a.a.c.b.j
        public final Object a(Object obj) {
            Object obj2;
            if (this.d != null && (obj2 = this.d.get(obj)) != null) {
                if (obj2 == f229b) {
                    return null;
                }
                return obj2;
            }
            return this.c.get(obj);
        }

        @Override // com.a.a.c.b.j
        public final j a(Object obj, Object obj2) {
            if (obj2 == null) {
                if (this.c.containsKey(obj)) {
                    obj2 = f229b;
                } else {
                    if (this.d == null || !this.d.containsKey(obj)) {
                        return this;
                    }
                    this.d.remove(obj);
                    return this;
                }
            }
            if (this.d == null) {
                return b(obj, obj2);
            }
            this.d.put(obj, obj2);
            return this;
        }

        private j b(Object obj, Object obj2) {
            HashMap hashMap = new HashMap();
            if (obj2 == null) {
                obj2 = f229b;
            }
            hashMap.put(obj, obj2);
            return new a(this.c, hashMap);
        }
    }
}
