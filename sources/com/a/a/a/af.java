package com.a.a.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: infinitode-2.jar:com/a/a/a/af.class */
public @interface af {

    /* loaded from: infinitode-2.jar:com/a/a/a/af$a.class */
    public enum a {
        PROPERTY,
        WRAPPER_OBJECT,
        WRAPPER_ARRAY,
        EXTERNAL_PROPERTY,
        EXISTING_PROPERTY
    }

    @Deprecated
    /* loaded from: infinitode-2.jar:com/a/a/a/af$c.class */
    public static abstract class c {
    }

    b a();

    a b() default a.PROPERTY;

    String c() default "";

    Class<?> d() default af.class;

    boolean e() default false;

    /* loaded from: infinitode-2.jar:com/a/a/a/af$b.class */
    public enum b {
        NONE(null),
        CLASS("@class"),
        MINIMAL_CLASS("@c"),
        NAME("@type"),
        DEDUCTION(null),
        CUSTOM(null);

        private final String g;

        b(String str) {
            this.g = str;
        }

        public final String a() {
            return this.g;
        }
    }
}
