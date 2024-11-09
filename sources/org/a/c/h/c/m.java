package org.a.c.h.c;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.Provider;

/* loaded from: infinitode-2.jar:org/a/c/h/c/m.class */
public final class m {

    /* renamed from: a, reason: collision with root package name */
    private static Provider f4510a = null;

    public static Provider a() {
        if (f4510a == null) {
            try {
                f4510a = (Provider) Class.forName("org.bouncycastle.jce.provider.BouncyCastleProvider").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (ClassNotFoundException e) {
                throw new IOException(e);
            } catch (IllegalAccessException e2) {
                throw new IOException(e2);
            } catch (InstantiationException e3) {
                throw new IOException(e3);
            } catch (NoSuchMethodException e4) {
                throw new IOException(e4);
            } catch (InvocationTargetException e5) {
                throw new IOException(e5);
            }
        }
        return f4510a;
    }
}
