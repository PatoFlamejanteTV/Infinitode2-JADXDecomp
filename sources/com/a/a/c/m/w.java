package com.a.a.c.m;

import java.lang.reflect.InvocationTargetException;

/* loaded from: infinitode-2.jar:com/a/a/c/m/w.class */
public final class w {

    /* renamed from: a, reason: collision with root package name */
    private static final boolean f747a;

    static {
        f747a = System.getProperty("org.graalvm.nativeimage.imagecode") != null;
    }

    private static boolean a() {
        return f747a && "runtime".equals(System.getProperty("org.graalvm.nativeimage.imagecode"));
    }

    public static boolean a(Throwable th) {
        if (!a()) {
            return false;
        }
        if (th instanceof InvocationTargetException) {
            th = th.getCause();
        }
        return th.getClass().getName().equals("com.oracle.svm.core.jdk.UnsupportedFeatureError");
    }

    public static boolean a(Class<?> cls) {
        if (a()) {
            return (cls.getDeclaredFields().length == 0 || i.f(cls)) && cls.getDeclaredMethods().length == 0 && cls.getDeclaredConstructors().length == 0;
        }
        return false;
    }
}
