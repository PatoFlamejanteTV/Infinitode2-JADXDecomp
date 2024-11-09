package org.a.a.a;

import java.security.PrivilegedAction;

/* loaded from: infinitode-2.jar:org/a/a/a/i.class */
class i implements PrivilegedAction {

    /* renamed from: a, reason: collision with root package name */
    private final String f4191a;

    /* renamed from: b, reason: collision with root package name */
    private final String f4192b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(String str, String str2) {
        this.f4191a = str;
        this.f4192b = str2;
    }

    @Override // java.security.PrivilegedAction
    public final Object run() {
        return System.getProperty(this.f4191a, this.f4192b);
    }
}
