package com.a.a.c.b;

import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/c/b/i.class */
public final class i implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    public static final i f224a = new i(a.HEURISTIC);

    /* renamed from: b, reason: collision with root package name */
    private a f225b;
    private boolean c;
    private boolean d;

    /* loaded from: infinitode-2.jar:com/a/a/c/b/i$a.class */
    public enum a {
        DELEGATING,
        PROPERTIES,
        HEURISTIC,
        REQUIRE_MODE
    }

    static {
        new i(a.PROPERTIES);
        new i(a.DELEGATING);
        new i(a.REQUIRE_MODE);
    }

    private i(a aVar, boolean z, boolean z2) {
        this.f225b = aVar;
        this.c = false;
        this.d = false;
    }

    private i(a aVar) {
        this(aVar, false, false);
    }

    public final a a() {
        return this.f225b;
    }

    public final boolean b() {
        return this.c;
    }

    public final boolean c() {
        return this.f225b == a.DELEGATING;
    }

    public final boolean d() {
        return this.f225b == a.PROPERTIES;
    }

    public final boolean a(Class<?> cls) {
        if (this.c) {
            return false;
        }
        if (!this.d && com.a.a.c.m.i.m(cls) && !Throwable.class.isAssignableFrom(cls)) {
            return false;
        }
        return true;
    }
}
