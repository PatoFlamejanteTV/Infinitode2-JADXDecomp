package com.a.a.a;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: infinitode-2.jar:com/a/a/a/q.class */
public @interface q {
    String[] a() default {};

    boolean b() default false;

    boolean c() default false;

    boolean d() default false;

    /* loaded from: infinitode-2.jar:com/a/a/a/q$a.class */
    public static class a implements Serializable {

        /* renamed from: a, reason: collision with root package name */
        private static a f73a = new a(Collections.emptySet(), false, false, false, true);

        /* renamed from: b, reason: collision with root package name */
        private Set<String> f74b;
        private boolean c;
        private boolean d;
        private boolean e;
        private boolean f;

        private a(Set<String> set, boolean z, boolean z2, boolean z3, boolean z4) {
            if (set == null) {
                this.f74b = Collections.emptySet();
            } else {
                this.f74b = set;
            }
            this.c = z;
            this.d = z2;
            this.e = z3;
            this.f = z4;
        }

        public static a a(q qVar) {
            if (qVar == null) {
                return f73a;
            }
            return a(a(qVar.a()), qVar.b(), qVar.c(), qVar.d(), false);
        }

        private static a a(Set<String> set, boolean z, boolean z2, boolean z3, boolean z4) {
            if (b(set, z, z2, z3, z4)) {
                return f73a;
            }
            return new a(set, z, z2, z3, z4);
        }

        public static a a() {
            return f73a;
        }

        public static a a(a aVar, a aVar2) {
            return aVar == null ? aVar2 : aVar.a(aVar2);
        }

        private a a(a aVar) {
            if (aVar == null || aVar == f73a) {
                return this;
            }
            if (!aVar.f) {
                return aVar;
            }
            if (b(this, aVar)) {
                return this;
            }
            return a(a(this.f74b, aVar.f74b), this.c || aVar.c, this.d || aVar.d, this.e || aVar.e, true);
        }

        public final Set<String> b() {
            if (this.d) {
                return Collections.emptySet();
            }
            return this.f74b;
        }

        public final Set<String> c() {
            if (this.e) {
                return Collections.emptySet();
            }
            return this.f74b;
        }

        public final boolean d() {
            return this.c;
        }

        public final String toString() {
            return String.format("JsonIgnoreProperties.Value(ignored=%s,ignoreUnknown=%s,allowGetters=%s,allowSetters=%s,merge=%s)", this.f74b, Boolean.valueOf(this.c), Boolean.valueOf(this.d), Boolean.valueOf(this.e), Boolean.valueOf(this.f));
        }

        public final int hashCode() {
            return this.f74b.size() + (this.c ? 1 : -3) + (this.d ? 3 : -7) + (this.e ? 7 : -11) + (this.f ? 11 : -13);
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return obj != null && obj.getClass() == getClass() && b(this, (a) obj);
        }

        private static boolean b(a aVar, a aVar2) {
            return aVar.c == aVar2.c && aVar.f == aVar2.f && aVar.d == aVar2.d && aVar.e == aVar2.e && aVar.f74b.equals(aVar2.f74b);
        }

        private static Set<String> a(String[] strArr) {
            if (strArr == null || strArr.length == 0) {
                return Collections.emptySet();
            }
            HashSet hashSet = new HashSet(strArr.length);
            for (String str : strArr) {
                hashSet.add(str);
            }
            return hashSet;
        }

        private static Set<String> a(Set<String> set, Set<String> set2) {
            if (set.isEmpty()) {
                return set2;
            }
            if (set2.isEmpty()) {
                return set;
            }
            HashSet hashSet = new HashSet(set.size() + set2.size());
            hashSet.addAll(set);
            hashSet.addAll(set2);
            return hashSet;
        }

        private static boolean b(Set<String> set, boolean z, boolean z2, boolean z3, boolean z4) {
            if (z == f73a.c && z2 == f73a.d && z3 == f73a.e && z4 == f73a.f) {
                return set == null || set.size() == 0;
            }
            return false;
        }
    }
}
