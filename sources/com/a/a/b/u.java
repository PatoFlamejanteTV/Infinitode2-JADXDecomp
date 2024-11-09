package com.a.a.b;

/* loaded from: infinitode-2.jar:com/a/a/b/u.class */
public enum u implements com.a.a.b.g.h {
    CAN_WRITE_BINARY_NATIVELY(false),
    CAN_WRITE_FORMATTED_NUMBERS(false);

    private final boolean c = false;
    private final int d = 1 << ordinal();

    u(boolean z) {
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
}
