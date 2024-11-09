package com.d.c.a;

import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/d/c/a/d.class */
public final class d {
    private int t;
    private final String u;
    private final c v;
    private final c w;
    private static final Map r = new HashMap();
    private static int s = 0;

    /* renamed from: a, reason: collision with root package name */
    public static final d f970a = a("top-left-corner", c.aJ, c.al);

    /* renamed from: b, reason: collision with root package name */
    public static final d f971b = a("top-left", c.aa, c.al);
    public static final d c = a("top-center", c.n, c.al);
    public static final d d = a("top-right", c.aJ, c.al);
    public static final d e = a("top-right-corner", c.aa, c.al);
    public static final d f = a("bottom-left-corner", c.aJ, c.al);
    public static final d g = a("bottom-left", c.aa, c.al);
    public static final d h = a("bottom-center", c.n, c.al);
    public static final d i = a("bottom-right", c.aJ, c.al);
    public static final d j = a("bottom-right-corner", c.aa, c.al);
    public static final d k = a("left-top", c.n, c.bi);
    public static final d l = a("left-middle", c.n, c.al);
    public static final d m = a("left-bottom", c.n, c.l);
    public static final d n = a("right-top", c.n, c.bi);
    public static final d o = a("right-middle", c.n, c.al);
    public static final d p = a("right-bottom", c.n, c.l);
    public static final d q = a("-fs-pdf-xmp-metadata", c.bi, c.aa);

    private d(String str, c cVar, c cVar2) {
        this.u = str;
        this.v = cVar;
        this.w = cVar2;
        int i2 = s;
        s = i2 + 1;
        this.t = i2;
    }

    private static final d a(String str, c cVar, c cVar2) {
        d dVar = new d(str, cVar, cVar2);
        r.put(str, dVar);
        return dVar;
    }

    public final String toString() {
        return this.u;
    }

    public static d a(String str) {
        return (d) r.get(str);
    }

    public final int hashCode() {
        return this.t;
    }

    public final boolean equals(Object obj) {
        return obj != null && (obj instanceof d) && this.t == ((d) obj).t;
    }

    public final c a() {
        return this.v;
    }

    public final c b() {
        return this.w;
    }
}
