package com.b.a.a;

import java.io.InputStream;
import java.security.AccessController;
import java.util.MissingResourceException;

/* loaded from: infinitode-2.jar:com/b/a/a/i.class */
public final class i {
    private static InputStream a(Class<?> cls, String str, boolean z) {
        InputStream resourceAsStream;
        if (System.getSecurityManager() != null) {
            resourceAsStream = (InputStream) AccessController.doPrivileged(new j(cls, str));
        } else {
            resourceAsStream = cls.getResourceAsStream(str);
        }
        return resourceAsStream;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InputStream a(ClassLoader classLoader, String str, boolean z) {
        InputStream resourceAsStream;
        if (System.getSecurityManager() != null) {
            resourceAsStream = (InputStream) AccessController.doPrivileged(new k(classLoader, str));
        } else {
            resourceAsStream = classLoader.getResourceAsStream(str);
        }
        if (resourceAsStream == null && z) {
            throw new MissingResourceException("could not locate data", classLoader.toString(), str);
        }
        return resourceAsStream;
    }

    public static InputStream a(String str) {
        return a((Class<?>) i.class, str, false);
    }
}
