package com.badlogic.gdx.ai.btree.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/annotation/TaskAttribute.class */
public @interface TaskAttribute {
    String name() default "";

    boolean required() default false;
}
