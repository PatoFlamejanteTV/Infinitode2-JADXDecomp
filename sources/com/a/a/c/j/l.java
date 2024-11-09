package com.a.a.c.j;

import com.a.a.c.m.y;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: infinitode-2.jar:com/a/a/c/j/l.class */
public final class l implements Serializable {

    /* renamed from: b, reason: collision with root package name */
    private final boolean f543b;
    private static final l c = new l(false);

    /* renamed from: a, reason: collision with root package name */
    public static final l f544a;

    static {
        new l(true);
        f544a = c;
    }

    private l(boolean z) {
        this.f543b = z;
    }

    protected l() {
        this(false);
    }

    public static e a(boolean z) {
        return z ? e.q() : e.r();
    }

    public static p a() {
        return p.q();
    }

    public static com.a.a.c.m b() {
        return o.q();
    }

    public static q a(int i) {
        return j.c(i);
    }

    public static q a(long j) {
        return n.a(j);
    }

    public final u a(BigInteger bigInteger) {
        if (bigInteger == null) {
            return a();
        }
        return c.a(bigInteger);
    }

    public static q a(float f) {
        return i.a(f);
    }

    public static q a(double d) {
        return h.b(d);
    }

    public final u a(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return a();
        }
        if (this.f543b) {
            return g.a(bigDecimal);
        }
        if (bigDecimal.signum() == 0) {
            return g.f531a;
        }
        try {
            bigDecimal = bigDecimal.stripTrailingZeros();
        } catch (ArithmeticException unused) {
        }
        return g.a(bigDecimal);
    }

    public static t a(String str) {
        return t.d(str);
    }

    public static d a(byte[] bArr) {
        return d.a(bArr);
    }

    public final a c() {
        return new a(this);
    }

    public final r d() {
        return new r(this);
    }

    public static u a(Object obj) {
        return new s(obj);
    }

    public static u a(y yVar) {
        return new s(yVar);
    }
}
