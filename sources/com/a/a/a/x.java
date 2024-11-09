package com.a.a.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: infinitode-2.jar:com/a/a/a/x.class */
public @interface x {

    /* loaded from: infinitode-2.jar:com/a/a/a/x$a.class */
    public enum a {
        AUTO,
        READ_ONLY,
        WRITE_ONLY,
        READ_WRITE
    }

    String a() default "";

    String b() default "";

    boolean c() default false;

    int d() default -1;

    String e() default "";

    a f() default a.AUTO;
}
