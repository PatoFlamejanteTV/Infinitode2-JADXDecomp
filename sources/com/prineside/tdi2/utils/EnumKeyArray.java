package com.prineside.tdi2.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/EnumKeyArray.class */
public @interface EnumKeyArray {
    Class<? extends Enum> enumerator();
}
