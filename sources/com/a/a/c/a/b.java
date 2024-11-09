package com.a.a.c.a;

import com.a.a.a.s;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: infinitode-2.jar:com/a/a/c/a/b.class */
public @interface b {

    /* loaded from: infinitode-2.jar:com/a/a/c/a/b$a.class */
    public @interface a {
        String a();

        String b() default "";

        String c() default "";

        s.a d() default s.a.NON_NULL;

        boolean e() default false;
    }

    /* renamed from: com.a.a.c.a.b$b, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:com/a/a/c/a/b$b.class */
    public @interface InterfaceC0006b {
        Class<? extends com.a.a.c.k.s> a();

        String b() default "";

        String c() default "";

        s.a d() default s.a.NON_NULL;

        boolean e() default false;

        Class<?> f() default Object.class;
    }

    a[] a() default {};

    InterfaceC0006b[] b() default {};

    boolean c() default false;
}
