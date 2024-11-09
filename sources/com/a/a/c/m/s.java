package com.a.a.c.m;

/* loaded from: infinitode-2.jar:com/a/a/c/m/s.class */
class s extends r {

    /* renamed from: b, reason: collision with root package name */
    private /* synthetic */ String f744b;
    private /* synthetic */ String c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public s(String str, String str2) {
        this.f744b = str;
        this.c = str2;
    }

    @Override // com.a.a.c.m.r
    public final String a(String str) {
        return this.f744b + str + this.c;
    }

    public final String toString() {
        return "[PreAndSuffixTransformer('" + this.f744b + "','" + this.c + "')]";
    }
}
