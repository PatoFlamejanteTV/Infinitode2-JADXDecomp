package com.a.a.c.f;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/* loaded from: infinitode-2.jar:com/a/a/c/f/z.class */
public final class z {

    /* renamed from: a, reason: collision with root package name */
    private static Class<?>[] f484a = new Class[0];

    /* renamed from: b, reason: collision with root package name */
    private String f485b;
    private Class<?>[] c;

    public z(Method method) {
        this(method.getName(), method.getParameterTypes());
    }

    public z(Constructor<?> constructor) {
        this("", constructor.getParameterTypes());
    }

    public z(String str, Class<?>[] clsArr) {
        this.f485b = str;
        this.c = clsArr == null ? f484a : clsArr;
    }

    public final String a() {
        return this.f485b;
    }

    public final int b() {
        return this.c.length;
    }

    public final String toString() {
        return this.f485b + "(" + this.c.length + "-args)";
    }

    public final int hashCode() {
        return this.f485b.hashCode() + this.c.length;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        z zVar = (z) obj;
        if (!this.f485b.equals(zVar.f485b)) {
            return false;
        }
        Class<?>[] clsArr = zVar.c;
        int length = this.c.length;
        if (clsArr.length != length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (clsArr[i] != this.c[i]) {
                return false;
            }
        }
        return true;
    }
}
