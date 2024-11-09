package com.a.a.c.a;

import com.a.a.c.k;
import com.a.a.c.m.k;
import com.a.a.c.p;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: infinitode-2.jar:com/a/a/c/a/c.class */
public @interface c {
    Class<? extends k> a() default k.a.class;

    Class<? extends k> b() default k.a.class;

    Class<? extends p> c() default p.a.class;

    Class<?> d() default Void.class;

    Class<? extends com.a.a.c.m.k> e() default k.a.class;

    Class<? extends com.a.a.c.m.k> f() default k.a.class;

    Class<?> g() default Void.class;

    Class<?> h() default Void.class;

    Class<?> i() default Void.class;
}
