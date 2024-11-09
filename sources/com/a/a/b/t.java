package com.a.a.b;

import com.a.a.b.l;

/* loaded from: infinitode-2.jar:com/a/a/b/t.class */
public enum t implements com.a.a.b.g.h {
    AUTO_CLOSE_SOURCE(l.a.AUTO_CLOSE_SOURCE),
    STRICT_DUPLICATE_DETECTION(l.a.STRICT_DUPLICATE_DETECTION),
    IGNORE_UNDEFINED(l.a.IGNORE_UNDEFINED),
    INCLUDE_SOURCE_IN_LOCATION(l.a.INCLUDE_SOURCE_IN_LOCATION),
    USE_FAST_DOUBLE_PARSER(l.a.USE_FAST_DOUBLE_PARSER);

    private final boolean f;
    private final int g;
    private final l.a h;

    t(l.a aVar) {
        this.h = aVar;
        this.g = aVar.c();
        this.f = aVar.b();
    }

    @Override // com.a.a.b.g.h
    public final boolean a() {
        return this.f;
    }

    @Override // com.a.a.b.g.h
    public final boolean a(int i2) {
        return (i2 & this.g) != 0;
    }

    @Override // com.a.a.b.g.h
    public final int b() {
        return this.g;
    }

    public final l.a c() {
        return this.h;
    }
}
