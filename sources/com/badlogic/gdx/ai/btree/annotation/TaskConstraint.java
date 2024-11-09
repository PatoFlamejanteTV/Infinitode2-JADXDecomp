package com.badlogic.gdx.ai.btree.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Documented
/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/annotation/TaskConstraint.class */
public @interface TaskConstraint {
    int minChildren() default 0;

    int maxChildren() default Integer.MAX_VALUE;
}
