package com.a.a.b;

/* loaded from: infinitode-2.jar:com/a/a/b/s.class */
public enum s implements com.a.a.b.g.h {
    DUPLICATE_PROPERTIES(false),
    SCALARS_AS_OBJECTS(false),
    UNTYPED_SCALARS(false),
    EXACT_FLOATS(false);

    private final boolean e = false;
    private final int f = 1 << ordinal();

    s(boolean z) {
    }

    @Override // com.a.a.b.g.h
    public final boolean a() {
        return this.e;
    }

    @Override // com.a.a.b.g.h
    public final boolean a(int i) {
        return (i & this.f) != 0;
    }

    @Override // com.a.a.b.g.h
    public final int b() {
        return this.f;
    }
}
