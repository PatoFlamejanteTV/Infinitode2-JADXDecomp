package com.a.a.a;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: infinitode-2.jar:com/a/a/a/b.class */
public @interface b {
    String a() default "";

    ao b() default ao.DEFAULT;

    /* loaded from: infinitode-2.jar:com/a/a/a/b$a.class */
    public static class a implements Serializable {

        /* renamed from: a, reason: collision with root package name */
        private static a f55a = new a(null, null);

        /* renamed from: b, reason: collision with root package name */
        private Object f56b;
        private Boolean c;

        private a(Object obj, Boolean bool) {
            this.f56b = obj;
            this.c = bool;
        }

        private static a a(Object obj, Boolean bool) {
            if ("".equals(obj)) {
                obj = null;
            }
            if (b(obj, bool)) {
                return f55a;
            }
            return new a(obj, bool);
        }

        public static a a(b bVar) {
            if (bVar == null) {
                return f55a;
            }
            return a(bVar.a(), bVar.b().a());
        }

        public static a a(Object obj) {
            return a(obj, null);
        }

        public final a b(Object obj) {
            if (obj == null) {
                if (this.f56b == null) {
                    return this;
                }
            } else if (obj.equals(this.f56b)) {
                return this;
            }
            return new a(obj, this.c);
        }

        public final Object a() {
            return this.f56b;
        }

        public final boolean b() {
            return this.f56b != null;
        }

        public final boolean a(boolean z) {
            if (this.c == null) {
                return true;
            }
            return this.c.booleanValue();
        }

        public final String toString() {
            return String.format("JacksonInject.Value(id=%s,useInput=%s)", this.f56b, this.c);
        }

        public final int hashCode() {
            int i = 1;
            if (this.f56b != null) {
                i = 1 + this.f56b.hashCode();
            }
            if (this.c != null) {
                i += this.c.hashCode();
            }
            return i;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj != null && obj.getClass() == getClass()) {
                a aVar = (a) obj;
                if (ao.a(this.c, aVar.c)) {
                    if (this.f56b == null) {
                        return aVar.f56b == null;
                    }
                    return this.f56b.equals(aVar.f56b);
                }
                return false;
            }
            return false;
        }

        private static boolean b(Object obj, Boolean bool) {
            return obj == null && bool == null;
        }
    }
}
