package org.a.a.a;

import java.security.PrivilegedAction;

/* loaded from: infinitode-2.jar:org/a/a/a/f.class */
class f implements PrivilegedAction {

    /* renamed from: a, reason: collision with root package name */
    private final ClassLoader f4186a;

    /* renamed from: b, reason: collision with root package name */
    private final String f4187b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(ClassLoader classLoader, String str) {
        this.f4186a = classLoader;
        this.f4187b = str;
    }

    @Override // java.security.PrivilegedAction
    public final Object run() {
        if (this.f4186a != null) {
            return this.f4186a.getResourceAsStream(this.f4187b);
        }
        return ClassLoader.getSystemResourceAsStream(this.f4187b);
    }
}
