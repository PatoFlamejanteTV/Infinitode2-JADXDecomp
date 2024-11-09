package com.prineside.tdi2.utils.luaTests;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/luaTests/TestAnnotationRuntimeRetention.class */
public @interface TestAnnotationRuntimeRetention {
}
