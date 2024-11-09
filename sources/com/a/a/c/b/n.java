package com.a.a.c.b;

/* loaded from: infinitode-2.jar:com/a/a/c/b/n.class */
public enum n implements k {
    BOGUS_FEATURE(false);


    /* renamed from: b, reason: collision with root package name */
    private final boolean f236b = false;
    private final int c = 1 << ordinal();

    n(boolean z) {
    }

    @Override // com.a.a.b.g.h
    public final boolean a() {
        return this.f236b;
    }

    @Override // com.a.a.b.g.h
    public final boolean a(int i) {
        return (i & this.c) != 0;
    }

    @Override // com.a.a.b.g.h
    public final int b() {
        return this.c;
    }

    @Override // com.a.a.c.b.k
    public final int c() {
        return 0;
    }
}
