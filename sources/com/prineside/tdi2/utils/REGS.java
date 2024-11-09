package com.prineside.tdi2.utils;

import com.esotericsoftware.kryo.Serializer;
import com.prineside.tdi2.serializers.GameStateSerializer;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/REGS.class */
public @interface REGS {
    int arrayLevels() default 0;

    Class<? extends Serializer> serializer() default GameStateSerializer.class;

    boolean classOnly() default false;
}
