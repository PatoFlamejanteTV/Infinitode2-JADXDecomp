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
/* loaded from: infinitode-2.jar:com/a/a/a/t.class */
public @interface t {
    String[] a() default {};

    /* loaded from: infinitode-2.jar:com/a/a/a/t$a.class */
    public static class a implements Serializable {

        /* renamed from: a, reason: collision with root package name */
        private static a f79a = new a(null);

        /* renamed from: b, reason: collision with root package name */
        private Set<String> f80b;

        private a(Set<String> set) {
            this.f80b = set;
        }

        public static a a(t tVar) {
            if (tVar == null) {
                return f79a;
            }
            return new a(a(tVar.a()));
        }

        public static a a() {
            return f79a;
        }

        public final Set<String> b() {
            return this.f80b;
        }

        public final String toString() {
            return String.format("JsonIncludeProperties.Value(included=%s)", this.f80b);
        }

        public final int hashCode() {
            if (this.f80b == null) {
                return 0;
            }
            return this.f80b.size();
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return obj != null && obj.getClass() == getClass() && a(this.f80b, ((a) obj).f80b);
        }

        private static boolean a(Set<String> set, Set<String> set2) {
            if (set == null) {
                return set2 == null;
            }
            return set.equals(set2);
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
    }
}
