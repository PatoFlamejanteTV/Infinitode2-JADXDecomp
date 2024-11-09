package com.a.a.a;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: infinitode-2.jar:com/a/a/a/ac.class */
public @interface ac {
    String a() default "";

    ak b() default ak.DEFAULT;

    ak c() default ak.DEFAULT;

    /* loaded from: infinitode-2.jar:com/a/a/a/ac$a.class */
    public static class a implements Serializable {

        /* renamed from: a, reason: collision with root package name */
        private final ak f37a;

        /* renamed from: b, reason: collision with root package name */
        private final ak f38b;
        private static a c;

        static {
            ak akVar = ak.DEFAULT;
            c = new a(akVar, akVar);
        }

        private a(ak akVar, ak akVar2) {
            this.f37a = akVar;
            this.f38b = akVar2;
        }

        public static a a(ac acVar) {
            if (acVar == null) {
                return c;
            }
            return a(acVar.b(), acVar.c());
        }

        private static a a(ak akVar, ak akVar2) {
            if (akVar == null) {
                akVar = ak.DEFAULT;
            }
            if (akVar2 == null) {
                akVar2 = ak.DEFAULT;
            }
            if (b(akVar, akVar2)) {
                return c;
            }
            return new a(akVar, akVar2);
        }

        public static a a() {
            return c;
        }

        public final ak b() {
            return this.f38b;
        }

        public final ak c() {
            if (this.f37a == ak.DEFAULT) {
                return null;
            }
            return this.f37a;
        }

        public final ak d() {
            if (this.f38b == ak.DEFAULT) {
                return null;
            }
            return this.f38b;
        }

        public final String toString() {
            return String.format("JsonSetter.Value(valueNulls=%s,contentNulls=%s)", this.f37a, this.f38b);
        }

        public final int hashCode() {
            return this.f37a.ordinal() + (this.f38b.ordinal() << 2);
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj != null && obj.getClass() == getClass()) {
                a aVar = (a) obj;
                return aVar.f37a == this.f37a && aVar.f38b == this.f38b;
            }
            return false;
        }

        private static boolean b(ak akVar, ak akVar2) {
            return akVar == ak.DEFAULT && akVar2 == ak.DEFAULT;
        }
    }
}
