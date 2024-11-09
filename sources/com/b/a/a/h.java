package com.b.a.a;

import java.security.PrivilegedAction;

/* loaded from: infinitode-2.jar:com/b/a/a/h.class */
class h implements PrivilegedAction<String> {

    /* renamed from: a, reason: collision with root package name */
    private /* synthetic */ String f806a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(String str) {
        this.f806a = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.security.PrivilegedAction
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String run() {
        return System.getProperty(this.f806a);
    }
}
