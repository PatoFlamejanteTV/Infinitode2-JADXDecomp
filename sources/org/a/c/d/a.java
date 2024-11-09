package org.a.c.d;

import java.io.File;

/* loaded from: infinitode-2.jar:org/a/c/d/a.class */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private final boolean f4414a;

    /* renamed from: b, reason: collision with root package name */
    private final boolean f4415b;
    private final long c;
    private final long d;
    private File e;

    private a(boolean z, boolean z2, long j, long j2) {
        long j3 = j;
        long j4 = j2 > 0 ? j2 : -1L;
        j3 = j3 < -1 ? -1L : j3;
        j3 = j3 == 0 ? j4 : j3;
        if (j4 > -1 && (j3 == -1 || j3 > j4)) {
            j4 = j3;
        }
        this.f4414a = true;
        this.f4415b = false;
        this.c = j3;
        this.d = j4;
    }

    public static a a() {
        return a(-1L);
    }

    private static a a(long j) {
        return new a(true, false, -1L, -1L);
    }

    public final boolean b() {
        return this.f4414a;
    }

    public final boolean c() {
        return this.f4415b;
    }

    public final boolean d() {
        return this.c >= 0;
    }

    public final boolean e() {
        return this.d > 0;
    }

    public final long f() {
        return this.c;
    }

    public final long g() {
        return this.d;
    }

    public final File h() {
        return this.e;
    }

    public final String toString() {
        if (!this.f4414a) {
            return e() ? "Scratch file only with max. of " + this.d + " bytes" : "Scratch file only with no size restriction";
        }
        if (this.f4415b) {
            return "Mixed mode with max. of " + this.c + " main memory bytes" + (e() ? " and max. of " + this.d + " storage bytes" : " and unrestricted scratch file size");
        }
        return d() ? "Main memory only with max. of " + this.c + " bytes" : "Main memory only with no size restriction";
    }
}
