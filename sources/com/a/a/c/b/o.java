package com.a.a.c.b;

/* loaded from: infinitode-2.jar:com/a/a/c/b/o.class */
public enum o implements k {
    READ_NULL_PROPERTIES(true),
    WRITE_NULL_PROPERTIES(true);

    private final boolean c = true;
    private final int d = 1 << ordinal();

    o(boolean z) {
    }

    @Override // com.a.a.b.g.h
    public final boolean a() {
        return this.c;
    }

    @Override // com.a.a.b.g.h
    public final boolean a(int i) {
        return (i & this.d) != 0;
    }

    @Override // com.a.a.b.g.h
    public final int b() {
        return this.d;
    }

    @Override // com.a.a.c.b.k
    public final int c() {
        return 1;
    }
}
