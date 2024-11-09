package com.d.c.a;

import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/d/c/a/e.class */
public final class e {
    private int g;
    private final String h;
    private static final Map e = new HashMap();
    private static int f = 0;

    /* renamed from: a, reason: collision with root package name */
    public static final e f972a = b("start");

    /* renamed from: b, reason: collision with root package name */
    public static final e f973b = b("first");
    public static final e c = b("last");
    public static final e d = b("last-except");

    private e(String str) {
        this.h = str;
        int i = f;
        f = i + 1;
        this.g = i;
    }

    private static final e b(String str) {
        e eVar = new e(str);
        e.put(str, eVar);
        return eVar;
    }

    public final String toString() {
        return this.h;
    }

    public static e a(String str) {
        return (e) e.get(str);
    }

    public final int hashCode() {
        return this.g;
    }

    public final boolean equals(Object obj) {
        return obj != null && (obj instanceof e) && this.g == ((e) obj).g;
    }
}
