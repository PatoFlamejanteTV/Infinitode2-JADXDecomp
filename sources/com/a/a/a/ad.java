package com.a.a.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: infinitode-2.jar:com/a/a/a/ad.class */
public @interface ad {

    /* loaded from: infinitode-2.jar:com/a/a/a/ad$a.class */
    public @interface a {
        Class<?> a();

        String b() default "";

        String[] c() default {};
    }

    a[] a();

    boolean b() default false;
}