package com.a.a.b;

/* loaded from: infinitode-2.jar:com/a/a/b/e.class */
public enum e {
    UTF8("UTF-8", false, 8),
    UTF16_BE("UTF-16BE", true, 16),
    UTF16_LE("UTF-16LE", false, 16),
    UTF32_BE("UTF-32BE", true, 32),
    UTF32_LE("UTF-32LE", false, 32);

    private final String f;
    private final boolean g;
    private final int h;

    e(String str, boolean z, int i2) {
        this.f = str;
        this.g = z;
        this.h = i2;
    }

    public final String a() {
        return this.f;
    }

    public final boolean b() {
        return this.g;
    }

    public final int c() {
        return this.h;
    }
}
