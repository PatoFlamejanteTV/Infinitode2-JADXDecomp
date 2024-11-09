package com.b.a.a;

import java.io.InputStream;
import java.security.PrivilegedAction;

/* loaded from: infinitode-2.jar:com/b/a/a/j.class */
class j implements PrivilegedAction<InputStream> {

    /* renamed from: a, reason: collision with root package name */
    private /* synthetic */ Class f807a;

    /* renamed from: b, reason: collision with root package name */
    private /* synthetic */ String f808b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(Class cls, String str) {
        this.f807a = cls;
        this.f808b = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.security.PrivilegedAction
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public InputStream run() {
        return this.f807a.getResourceAsStream(this.f808b);
    }
}
