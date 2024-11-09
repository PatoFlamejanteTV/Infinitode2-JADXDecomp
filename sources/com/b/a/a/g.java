package com.b.a.a;

import java.io.IOException;
import java.io.InputStream;
import java.security.AccessControlException;
import java.security.AccessController;
import java.util.MissingResourceException;
import java.util.Properties;

/* loaded from: infinitode-2.jar:com/b/a/a/g.class */
public final class g {

    /* renamed from: a, reason: collision with root package name */
    private static final Properties f805a = new Properties();

    static {
        try {
            InputStream a2 = i.a("/com/ibm/icu/ICUConfig.properties");
            if (a2 != null) {
                try {
                    f805a.load(a2);
                    a2.close();
                } catch (Throwable th) {
                    a2.close();
                    throw th;
                }
            }
        } catch (IOException unused) {
        } catch (MissingResourceException unused2) {
        }
    }

    public static String a(String str) {
        return a(str, null);
    }

    private static String a(String str, String str2) {
        String str3 = null;
        if (System.getSecurityManager() != null) {
            try {
                str3 = (String) AccessController.doPrivileged(new h(str));
            } catch (AccessControlException unused) {
            }
        } else {
            str3 = System.getProperty(str);
        }
        if (str3 == null) {
            str3 = f805a.getProperty(str, str2);
        }
        return str3;
    }
}
