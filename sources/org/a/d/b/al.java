package org.a.d.b;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: infinitode-2.jar:org/a/d/b/al.class */
public @interface al {
    String a();

    String b();
}