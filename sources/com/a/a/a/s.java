package com.a.a.a;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: infinitode-2.jar:com/a/a/a/s.class */
public @interface s {

    /* loaded from: infinitode-2.jar:com/a/a/a/s$a.class */
    public enum a {
        ALWAYS,
        NON_NULL,
        NON_ABSENT,
        NON_EMPTY,
        NON_DEFAULT,
        CUSTOM,
        USE_DEFAULTS
    }

    a a() default a.ALWAYS;

    a b() default a.ALWAYS;

    Class<?> c() default Void.class;

    Class<?> d() default Void.class;

    /* loaded from: infinitode-2.jar:com/a/a/a/s$b.class */
    public static class b implements Serializable {

        /* renamed from: a, reason: collision with root package name */
        private static b f77a;

        /* renamed from: b, reason: collision with root package name */
        private a f78b;
        private a c;
        private Class<?> d;
        private Class<?> e;

        static {
            a aVar = a.USE_DEFAULTS;
            f77a = new b(aVar, aVar, null, null);
        }

        private b(a aVar, a aVar2, Class<?> cls, Class<?> cls2) {
            this.f78b = aVar == null ? a.USE_DEFAULTS : aVar;
            this.c = aVar2 == null ? a.USE_DEFAULTS : aVar2;
            this.d = cls == Void.class ? null : cls;
            this.e = cls2 == Void.class ? null : cls2;
        }

        public static b a() {
            return f77a;
        }

        public static b a(b bVar, b bVar2) {
            return bVar == null ? bVar2 : bVar.a(bVar2);
        }

        public static b a(b... bVarArr) {
            b bVar = null;
            for (int i = 0; i < 3; i++) {
                b bVar2 = bVarArr[i];
                if (bVar2 != null) {
                    bVar = bVar == null ? bVar2 : bVar.a(bVar2);
                }
            }
            return bVar;
        }

        public final b a(b bVar) {
            if (bVar == null || bVar == f77a) {
                return this;
            }
            a aVar = bVar.f78b;
            a aVar2 = bVar.c;
            Class<?> cls = bVar.d;
            Class<?> cls2 = bVar.e;
            boolean z = (aVar == this.f78b || aVar == a.USE_DEFAULTS) ? false : true;
            boolean z2 = (aVar2 == this.c || aVar2 == a.USE_DEFAULTS) ? false : true;
            boolean z3 = (cls == this.d && cls2 == this.d) ? false : true;
            if (z) {
                if (z2) {
                    return new b(aVar, aVar2, cls, cls2);
                }
                return new b(aVar, this.c, cls, cls2);
            }
            if (z2) {
                return new b(this.f78b, aVar2, cls, cls2);
            }
            if (z3) {
                return new b(this.f78b, this.c, cls, cls2);
            }
            return this;
        }

        public static b a(a aVar, a aVar2) {
            if ((aVar == a.USE_DEFAULTS || aVar == null) && (aVar2 == a.USE_DEFAULTS || aVar2 == null)) {
                return f77a;
            }
            return new b(aVar, aVar2, null, null);
        }

        private static b a(a aVar, a aVar2, Class<?> cls, Class<?> cls2) {
            if (cls == Void.class) {
                cls = null;
            }
            if (cls2 == Void.class) {
                cls2 = null;
            }
            if ((aVar == a.USE_DEFAULTS || aVar == null) && ((aVar2 == a.USE_DEFAULTS || aVar2 == null) && cls == null && cls2 == null)) {
                return f77a;
            }
            return new b(aVar, aVar2, cls, cls2);
        }

        public static b a(s sVar) {
            if (sVar == null) {
                return f77a;
            }
            a a2 = sVar.a();
            a b2 = sVar.b();
            if (a2 == a.USE_DEFAULTS && b2 == a.USE_DEFAULTS) {
                return f77a;
            }
            Class<?> c = sVar.c();
            Class<?> cls = c;
            if (c == Void.class) {
                cls = null;
            }
            Class<?> d = sVar.d();
            Class<?> cls2 = d;
            if (d == Void.class) {
                cls2 = null;
            }
            return new b(a2, b2, cls, cls2);
        }

        public final b a(a aVar) {
            return aVar == this.f78b ? this : new b(aVar, this.c, this.d, this.e);
        }

        public final b a(Class<?> cls) {
            a aVar;
            if (cls == null || cls == Void.class) {
                aVar = a.USE_DEFAULTS;
                cls = null;
            } else {
                aVar = a.CUSTOM;
            }
            return a(this.f78b, aVar, this.d, cls);
        }

        public final b b(a aVar) {
            return aVar == this.c ? this : new b(this.f78b, aVar, this.d, this.e);
        }

        public final a b() {
            return this.f78b;
        }

        public final a c() {
            return this.c;
        }

        public final Class<?> d() {
            return this.d;
        }

        public final Class<?> e() {
            return this.e;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder(80);
            sb.append("JsonInclude.Value(value=").append(this.f78b).append(",content=").append(this.c);
            if (this.d != null) {
                sb.append(",valueFilter=").append(this.d.getName()).append(".class");
            }
            if (this.e != null) {
                sb.append(",contentFilter=").append(this.e.getName()).append(".class");
            }
            return sb.append(')').toString();
        }

        public final int hashCode() {
            return (this.f78b.hashCode() << 2) + this.c.hashCode();
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            b bVar = (b) obj;
            return bVar.f78b == this.f78b && bVar.c == this.c && bVar.d == this.d && bVar.e == this.e;
        }
    }
}
