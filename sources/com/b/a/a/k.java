package com.b.a.a;

import java.io.InputStream;
import java.security.PrivilegedAction;

/* loaded from: infinitode-2.jar:com/b/a/a/k.class */
class k implements PrivilegedAction<InputStream> {

    /* renamed from: a, reason: collision with root package name */
    private /* synthetic */ ClassLoader f809a;

    /* renamed from: b, reason: collision with root package name */
    private /* synthetic */ String f810b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(ClassLoader classLoader, String str) {
        this.f809a = classLoader;
        this.f810b = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.security.PrivilegedAction
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public InputStream run() {
        return this.f809a.getResourceAsStream(this.f810b);
    }
}
