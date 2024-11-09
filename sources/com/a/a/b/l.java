package com.a.a.b;

import java.io.Closeable;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: infinitode-2.jar:com/a/a/b/l.class */
public abstract class l implements Closeable {

    /* renamed from: a, reason: collision with root package name */
    protected static final com.a.a.b.g.i<s> f176a = com.a.a.b.g.i.a(s.values());

    /* renamed from: b, reason: collision with root package name */
    protected int f177b;
    private transient com.a.a.b.g.m c;

    /* loaded from: infinitode-2.jar:com/a/a/b/l$b.class */
    public enum b {
        INT,
        LONG,
        BIG_INTEGER,
        FLOAT,
        DOUBLE,
        BIG_DECIMAL
    }

    public abstract p a();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    public abstract n d();

    public abstract j e();

    public abstract j f();

    public abstract o g();

    public abstract l j();

    public abstract o m();

    @Deprecated
    public abstract int n();

    public abstract boolean o();

    public abstract boolean c(int i);

    public abstract boolean a(o oVar);

    public abstract void t();

    public abstract String u();

    public abstract String w();

    public abstract char[] x();

    public abstract int y();

    public abstract int z();

    public abstract boolean A();

    public abstract Number B();

    public abstract b D();

    public abstract int G();

    public abstract long H();

    public abstract BigInteger I();

    public abstract float J();

    public abstract double K();

    public abstract BigDecimal L();

    public abstract byte[] a(com.a.a.b.a aVar);

    public abstract String a(String str);

    /* loaded from: infinitode-2.jar:com/a/a/b/l$a.class */
    public enum a {
        AUTO_CLOSE_SOURCE(true),
        ALLOW_COMMENTS(false),
        ALLOW_YAML_COMMENTS(false),
        ALLOW_UNQUOTED_FIELD_NAMES(false),
        ALLOW_SINGLE_QUOTES(false),
        ALLOW_UNQUOTED_CONTROL_CHARS(false),
        ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER(false),
        ALLOW_NUMERIC_LEADING_ZEROS(false),
        ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS(false),
        ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS(false),
        ALLOW_TRAILING_DECIMAL_POINT_FOR_NUMBERS(false),
        ALLOW_NON_NUMERIC_NUMBERS(false),
        ALLOW_MISSING_VALUES(false),
        ALLOW_TRAILING_COMMA(false),
        STRICT_DUPLICATE_DETECTION(false),
        IGNORE_UNDEFINED(false),
        INCLUDE_SOURCE_IN_LOCATION(true),
        USE_FAST_DOUBLE_PARSER(false);

        private final boolean s;
        private final int t = 1 << ordinal();

        public static int a() {
            int i = 0;
            for (a aVar : values()) {
                if (aVar.b()) {
                    i |= aVar.c();
                }
            }
            return i;
        }

        a(boolean z) {
            this.s = z;
        }

        public final boolean b() {
            return this.s;
        }

        public final boolean a(int i) {
            return (i & this.t) != 0;
        }

        public final int c() {
            return this.t;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public l() {
        this.f177b = f.f142a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public l(int i) {
        this.f177b = i;
    }

    public boolean b() {
        return false;
    }

    public com.a.a.b.g.i<s> c() {
        return f176a;
    }

    public void a(Object obj) {
        n d = d();
        if (d != null) {
            d.a(obj);
        }
    }

    public boolean a(a aVar) {
        return aVar.a(this.f177b);
    }

    public final boolean a(t tVar) {
        return tVar.c().a(this.f177b);
    }

    @Deprecated
    public l a(int i) {
        this.f177b = i;
        return this;
    }

    public l a(int i, int i2) {
        return a((this.f177b & (i2 ^ (-1))) | (i & i2));
    }

    public String h() {
        if (g() == o.FIELD_NAME) {
            return u();
        }
        return null;
    }

    public String i() {
        if (g() == o.VALUE_STRING) {
            return w();
        }
        return null;
    }

    public int b(int i) {
        return g() == o.VALUE_NUMBER_INT ? G() : i;
    }

    public o k() {
        return m();
    }

    public int l() {
        return n();
    }

    public boolean p() {
        return k() == o.START_ARRAY;
    }

    public boolean q() {
        return k() == o.START_OBJECT;
    }

    public boolean r() {
        return k() == o.VALUE_NUMBER_INT;
    }

    public boolean s() {
        return false;
    }

    public String v() {
        return u();
    }

    public Number C() {
        return B();
    }

    public byte E() {
        int G = G();
        if (G < -128 || G > 255) {
            throw new com.a.a.b.b.a(this, String.format("Numeric value (%s) out of range of Java byte", w()), o.VALUE_NUMBER_INT, Byte.TYPE);
        }
        return (byte) G;
    }

    public short F() {
        int G = G();
        if (G < -32768 || G > 32767) {
            throw new com.a.a.b.b.a(this, String.format("Numeric value (%s) out of range of Java short", w()), o.VALUE_NUMBER_INT, Short.TYPE);
        }
        return (short) G;
    }

    public boolean M() {
        o k = k();
        if (k == o.VALUE_TRUE) {
            return true;
        }
        if (k == o.VALUE_FALSE) {
            return false;
        }
        throw new k(this, String.format("Current token (%s) not of boolean type", k)).a(this.c);
    }

    public Object N() {
        return null;
    }

    public final byte[] O() {
        return a(com.a.a.b.b.a());
    }

    public int a(com.a.a.b.a aVar, OutputStream outputStream) {
        W();
        return 0;
    }

    public int P() {
        return d(0);
    }

    public int d(int i) {
        return i;
    }

    public long Q() {
        return a(0L);
    }

    public long a(long j) {
        return j;
    }

    public String R() {
        return a((String) null);
    }

    public boolean S() {
        return false;
    }

    public boolean T() {
        return false;
    }

    public Object U() {
        return null;
    }

    public Object V() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final k b(String str) {
        return new k(this, str).a(this.c);
    }

    private void W() {
        throw new UnsupportedOperationException("Operation not supported by parser of type " + getClass().getName());
    }
}
