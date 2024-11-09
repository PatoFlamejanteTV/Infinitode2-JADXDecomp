package com.a.a.c.a;

import com.a.a.c.m.k;
import com.a.a.c.o;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: infinitode-2.jar:com/a/a/c/a/f.class */
public @interface f {

    @Deprecated
    /* loaded from: infinitode-2.jar:com/a/a/c/a/f$a.class */
    public enum a {
        ALWAYS,
        NON_NULL,
        NON_DEFAULT,
        NON_EMPTY,
        DEFAULT_INCLUSION
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/a/f$b.class */
    public enum b {
        DYNAMIC,
        STATIC,
        DEFAULT_TYPING
    }

    Class<? extends o> a() default o.a.class;

    Class<? extends o> b() default o.a.class;

    Class<? extends o> c() default o.a.class;

    Class<? extends o> d() default o.a.class;

    Class<?> e() default Void.class;

    Class<?> f() default Void.class;

    Class<?> g() default Void.class;

    b h() default b.DEFAULT_TYPING;

    Class<? extends k> i() default k.a.class;

    Class<? extends k> j() default k.a.class;

    @Deprecated
    a k() default a.DEFAULT_INCLUSION;
}
