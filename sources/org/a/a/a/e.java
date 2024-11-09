package org.a.a.a;

import java.security.PrivilegedAction;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/a/a/a/e.class */
public class e implements PrivilegedAction {

    /* renamed from: a, reason: collision with root package name */
    private final String f4184a;

    /* renamed from: b, reason: collision with root package name */
    private final ClassLoader f4185b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(String str, ClassLoader classLoader) {
        this.f4184a = str;
        this.f4185b = classLoader;
    }

    @Override // java.security.PrivilegedAction
    public final Object run() {
        return c.a(this.f4184a, this.f4185b);
    }
}
