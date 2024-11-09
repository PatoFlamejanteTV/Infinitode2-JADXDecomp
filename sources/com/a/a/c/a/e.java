package com.a.a.c.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: infinitode-2.jar:com/a/a/c/a/e.class */
public @interface e {
    String a() default "build";

    String b() default "with";

    /* loaded from: infinitode-2.jar:com/a/a/c/a/e$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public final String f199a;

        /* renamed from: b, reason: collision with root package name */
        public final String f200b;

        public a(e eVar) {
            this(eVar.a(), eVar.b());
        }

        private a(String str, String str2) {
            this.f199a = str;
            this.f200b = str2;
        }
    }
}
