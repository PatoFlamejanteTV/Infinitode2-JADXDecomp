package com.a.a.c.m;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/a/a/c/m/u.class */
public class u extends r {

    /* renamed from: b, reason: collision with root package name */
    private /* synthetic */ String f746b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public u(String str) {
        this.f746b = str;
    }

    @Override // com.a.a.c.m.r
    public final String a(String str) {
        return str + this.f746b;
    }

    public final String toString() {
        return "[SuffixTransformer('" + this.f746b + "')]";
    }
}
