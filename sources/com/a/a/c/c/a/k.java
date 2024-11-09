package com.a.a.c.c.a;

import com.a.a.c.c.x;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/k.class */
public abstract class k {
    public static com.a.a.c.c.x b(Class<?> cls) {
        if (cls == com.a.a.b.j.class) {
            return new com.a.a.c.c.b.s();
        }
        if (Collection.class.isAssignableFrom(cls)) {
            if (cls == ArrayList.class) {
                return a.f269a;
            }
            if (Collections.EMPTY_SET.getClass() == cls) {
                return new b(Collections.EMPTY_SET);
            }
            if (Collections.EMPTY_LIST.getClass() == cls) {
                return new b(Collections.EMPTY_LIST);
            }
            return null;
        }
        if (Map.class.isAssignableFrom(cls)) {
            if (cls == LinkedHashMap.class) {
                return d.f272a;
            }
            if (cls == HashMap.class) {
                return c.f271a;
            }
            if (Collections.EMPTY_MAP.getClass() == cls) {
                return new b(Collections.EMPTY_MAP);
            }
            return null;
        }
        return null;
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/a/k$a.class */
    static class a extends x.a implements Serializable {

        /* renamed from: a, reason: collision with root package name */
        public static final a f269a = new a();

        public a() {
            super((Class<?>) ArrayList.class);
        }

        @Override // com.a.a.c.c.x
        public final boolean d() {
            return true;
        }

        @Override // com.a.a.c.c.x
        public final boolean l() {
            return true;
        }

        @Override // com.a.a.c.c.x
        public final Object a(com.a.a.c.g gVar) {
            return new ArrayList();
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/a/k$c.class */
    static class c extends x.a implements Serializable {

        /* renamed from: a, reason: collision with root package name */
        public static final c f271a = new c();

        public c() {
            super((Class<?>) HashMap.class);
        }

        @Override // com.a.a.c.c.x
        public final boolean d() {
            return true;
        }

        @Override // com.a.a.c.c.x
        public final boolean l() {
            return true;
        }

        @Override // com.a.a.c.c.x
        public final Object a(com.a.a.c.g gVar) {
            return new HashMap();
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/a/k$d.class */
    static class d extends x.a implements Serializable {

        /* renamed from: a, reason: collision with root package name */
        public static final d f272a = new d();

        public d() {
            super((Class<?>) LinkedHashMap.class);
        }

        @Override // com.a.a.c.c.x
        public final boolean d() {
            return true;
        }

        @Override // com.a.a.c.c.x
        public final boolean l() {
            return true;
        }

        @Override // com.a.a.c.c.x
        public final Object a(com.a.a.c.g gVar) {
            return new LinkedHashMap();
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/a/k$b.class */
    static class b extends x.a implements Serializable {

        /* renamed from: a, reason: collision with root package name */
        private Object f270a;

        public b(Object obj) {
            super(obj.getClass());
            this.f270a = obj;
        }

        @Override // com.a.a.c.c.x
        public final boolean d() {
            return true;
        }

        @Override // com.a.a.c.c.x
        public final boolean l() {
            return true;
        }

        @Override // com.a.a.c.c.x
        public final Object a(com.a.a.c.g gVar) {
            return this.f270a;
        }
    }
}
