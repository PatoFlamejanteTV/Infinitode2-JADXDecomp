package com.a.a.b.g;

import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/b/g/n.class */
public final class n implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private final char f162a;

    /* renamed from: b, reason: collision with root package name */
    private final char f163b;
    private final char c;

    public static n a() {
        return new n();
    }

    public n() {
        this(':', ',', ',');
    }

    private n(char c, char c2, char c3) {
        this.f162a = ':';
        this.f163b = ',';
        this.c = ',';
    }

    public final char b() {
        return this.f162a;
    }

    public final char c() {
        return this.f163b;
    }

    public final char d() {
        return this.c;
    }
}
