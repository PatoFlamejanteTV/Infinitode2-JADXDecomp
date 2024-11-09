package org.a.a.a;

import java.io.IOException;
import java.security.PrivilegedAction;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/a/a/a/g.class */
public class g implements PrivilegedAction {

    /* renamed from: a, reason: collision with root package name */
    private final ClassLoader f4188a;

    /* renamed from: b, reason: collision with root package name */
    private final String f4189b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(ClassLoader classLoader, String str) {
        this.f4188a = classLoader;
        this.f4189b = str;
    }

    @Override // java.security.PrivilegedAction
    public final Object run() {
        try {
            if (this.f4188a != null) {
                return this.f4188a.getResources(this.f4189b);
            }
            return ClassLoader.getSystemResources(this.f4189b);
        } catch (IOException e) {
            if (c.c()) {
                c.a(new StringBuffer("Exception while trying to find configuration file ").append(this.f4189b).append(":").append(e.getMessage()).toString());
                return null;
            }
            return null;
        } catch (NoSuchMethodError unused) {
            return null;
        }
    }
}
