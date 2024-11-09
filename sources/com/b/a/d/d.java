package com.b.a.d;

import java.util.concurrent.ConcurrentHashMap;

/* loaded from: infinitode-2.jar:com/b/a/d/d.class */
public final class d implements Comparable<d> {

    /* renamed from: a, reason: collision with root package name */
    private static d f879a;

    /* renamed from: b, reason: collision with root package name */
    private int f880b;
    private static final ConcurrentHashMap<Integer, d> c = new ConcurrentHashMap<>();
    private static volatile String d;

    public static d a(int i, int i2, int i3, int i4) {
        if (i < 0 || i > 255 || i2 < 0 || i2 > 255 || i3 < 0 || i3 > 255 || i4 < 0 || i4 > 255) {
            throw new IllegalArgumentException("Invalid version number: Version number may be negative or greater than 255");
        }
        int b2 = b(i, i2, i3, i4);
        Integer valueOf = Integer.valueOf(b2);
        d dVar = c.get(valueOf);
        d dVar2 = dVar;
        if (dVar == null) {
            dVar2 = new d(b2);
            d putIfAbsent = c.putIfAbsent(valueOf, dVar2);
            if (putIfAbsent != null) {
                dVar2 = putIfAbsent;
            }
        }
        return dVar2;
    }

    private static d a(int i) {
        return a(i, 0, 0, 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(7);
        sb.append(a());
        sb.append('.');
        sb.append(b());
        sb.append('.');
        sb.append(c());
        sb.append('.');
        sb.append(d());
        return sb.toString();
    }

    private int a() {
        return this.f880b >>> 24;
    }

    private int b() {
        return (this.f880b >> 16) & 255;
    }

    private int c() {
        return (this.f880b >> 8) & 255;
    }

    private int d() {
        return this.f880b & 255;
    }

    public final boolean equals(Object obj) {
        return obj == this;
    }

    public final int hashCode() {
        return this.f880b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(d dVar) {
        return this.f880b - dVar.f880b;
    }

    static {
        a(1, 0, 0, 0);
        a(1, 0, 1, 0);
        a(1, 1, 0, 0);
        a(1, 1, 5, 0);
        a(2, 0, 0, 0);
        a(2, 1, 2, 0);
        a(2, 1, 5, 0);
        a(2, 1, 8, 0);
        a(2, 1, 9, 0);
        a(3, 0, 0, 0);
        a(3, 0, 1, 0);
        a(3, 1, 0, 0);
        a(3, 1, 1, 0);
        a(3, 2, 0, 0);
        a(4, 0, 0, 0);
        a(4, 0, 1, 0);
        a(4, 1, 0, 0);
        a(5, 0, 0, 0);
        a(5, 1, 0, 0);
        a(5, 2, 0, 0);
        a(6, 0, 0, 0);
        a(6, 1, 0, 0);
        a(6, 2, 0, 0);
        a(6, 3, 0, 0);
        a(7, 0, 0, 0);
        a(8, 0, 0, 0);
        f879a = a(9, 0, 0, 0);
        a(59, 1, 0, 0);
        a(59, 1, 0, 0);
        a(9);
        a(9);
        a(1);
        d = null;
    }

    private d(int i) {
        this.f880b = i;
    }

    private static int b(int i, int i2, int i3, int i4) {
        return (i << 24) | (i2 << 16) | (i3 << 8) | i4;
    }
}
