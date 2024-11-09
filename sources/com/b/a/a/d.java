package com.b.a.a;

import java.security.AccessController;

/* loaded from: infinitode-2.jar:com/b/a/a/d.class */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static volatile ClassLoader f797a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/b/a/a/d$a.class */
    public static class a extends ClassLoader {
        /* JADX INFO: Access modifiers changed from: package-private */
        public a() {
            super(Object.class.getClassLoader());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [java.lang.ClassLoader] */
    private static ClassLoader a() {
        a aVar;
        if (f797a == null) {
            synchronized (d.class) {
                if (f797a == null) {
                    if (System.getSecurityManager() != null) {
                        aVar = (ClassLoader) AccessController.doPrivileged(new e());
                    } else {
                        aVar = new a();
                    }
                    f797a = aVar;
                }
            }
        }
        return f797a;
    }

    public static ClassLoader a(Class<?> cls) {
        ClassLoader classLoader = cls.getClassLoader();
        ClassLoader classLoader2 = classLoader;
        if (classLoader == null) {
            classLoader2 = b();
        }
        return classLoader2;
    }

    private static ClassLoader b() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader classLoader = contextClassLoader;
        if (contextClassLoader == null) {
            ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
            classLoader = systemClassLoader;
            if (systemClassLoader == null) {
                classLoader = a();
            }
        }
        return classLoader;
    }
}
