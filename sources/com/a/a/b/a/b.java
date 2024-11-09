package com.a.a.b.a;

import com.a.a.b.c.h;
import com.a.a.b.d.d;
import com.a.a.b.g.i;
import com.a.a.b.j;
import com.a.a.b.l;
import com.a.a.b.o;
import com.a.a.b.s;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/a/a/b/a/b.class */
public abstract class b extends c {
    protected static final i<s> c = f176a;
    protected final com.a.a.b.c.a d;
    private boolean E;
    protected int e;
    protected int f;
    protected long g;
    protected int h;
    protected int i;
    protected long j;
    protected int k;
    protected int l;
    protected d m;
    protected o n;
    protected final com.a.a.b.g.o o;
    protected char[] p;
    protected boolean q;
    private com.a.a.b.g.c F;
    protected byte[] r;
    protected int s;
    protected int t;
    private long G;
    private float H;
    private double I;
    private BigInteger J;
    private BigDecimal K;
    private String L;
    private boolean M;
    private int N;

    protected abstract void W();

    /* JADX INFO: Access modifiers changed from: protected */
    public b(com.a.a.b.c.a aVar, int i) {
        super(i);
        this.h = 1;
        this.k = 1;
        this.s = 0;
        this.d = aVar;
        this.o = aVar.d();
        this.m = d.b(l.a.STRICT_DUPLICATE_DETECTION.a(i) ? com.a.a.b.d.b.a(this) : null);
    }

    @Override // com.a.a.b.l
    public final void a(Object obj) {
        this.m.a(obj);
    }

    @Override // com.a.a.b.l
    @Deprecated
    public final l a(int i) {
        int i2 = this.f177b ^ i;
        if (i2 != 0) {
            this.f177b = i;
            b(i, i2);
        }
        return this;
    }

    @Override // com.a.a.b.l
    public final l a(int i, int i2) {
        int i3 = this.f177b;
        int i4 = (i3 & (i2 ^ (-1))) | (i & i2);
        int i5 = i3 ^ i4;
        if (i5 != 0) {
            this.f177b = i4;
            b(i4, i5);
        }
        return this;
    }

    private void b(int i, int i2) {
        int c2 = l.a.STRICT_DUPLICATE_DETECTION.c();
        if ((i2 & c2) != 0 && (i & c2) != 0) {
            if (this.m.k() == null) {
                this.m = this.m.a(com.a.a.b.d.b.a(this));
            } else {
                this.m = this.m.a((com.a.a.b.d.b) null);
            }
        }
    }

    @Override // com.a.a.b.a.c, com.a.a.b.l
    public final String u() {
        d a2;
        if ((this.D == o.START_OBJECT || this.D == o.START_ARRAY) && (a2 = this.m.a()) != null) {
            return a2.g();
        }
        return this.m.g();
    }

    @Override // com.a.a.b.l, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (!this.E) {
            this.e = Math.max(this.e, this.f);
            this.E = true;
            try {
                W();
            } finally {
                X();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.b.l
    /* renamed from: am, reason: merged with bridge method [inline-methods] */
    public d d() {
        return this.m;
    }

    @Override // com.a.a.b.l
    public j f() {
        return new j(ag(), -1L, an(), ao(), ap());
    }

    @Override // com.a.a.b.l
    public j e() {
        return new j(ag(), -1L, this.g + this.e, this.h, (this.e - this.i) + 1);
    }

    @Override // com.a.a.b.l
    public final boolean A() {
        if (this.D == o.VALUE_STRING) {
            return true;
        }
        if (this.D == o.FIELD_NAME) {
            return this.q;
        }
        return false;
    }

    @Override // com.a.a.b.l
    public byte[] a(com.a.a.b.a aVar) {
        if (this.r == null) {
            if (this.D != o.VALUE_STRING) {
                g("Current token (" + this.D + ") not VALUE_STRING, can not access as binary");
            }
            com.a.a.b.g.c aa = aa();
            a(w(), aa, aVar);
            this.r = aa.b();
        }
        return this.r;
    }

    private long an() {
        return this.j;
    }

    private int ao() {
        return this.k;
    }

    private int ap() {
        int i = this.l;
        return i < 0 ? i : i + 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void X() {
        this.o.a();
        char[] cArr = this.p;
        if (cArr != null) {
            this.p = null;
            this.d.c(cArr);
        }
    }

    @Override // com.a.a.b.a.c
    protected final void Y() {
        if (!this.m.c()) {
            b(String.format(": expected close marker for %s (start marker at %s)", this.m.b() ? "Array" : "Object", this.m.a(ag())), (o) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int Z() {
        Y();
        return -1;
    }

    public final com.a.a.b.g.c aa() {
        if (this.F == null) {
            this.F = new com.a.a.b.g.c();
        } else {
            this.F.a();
        }
        return this.F;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final o a(boolean z, int i) {
        this.M = z;
        this.N = i;
        this.s = 0;
        return o.VALUE_NUMBER_INT;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final o a(boolean z, int i, int i2, int i3) {
        this.M = z;
        this.N = i;
        this.s = 0;
        return o.VALUE_NUMBER_FLOAT;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final o a(String str, double d) {
        this.o.a(str);
        this.I = d;
        this.s = 8;
        return o.VALUE_NUMBER_FLOAT;
    }

    @Override // com.a.a.b.l
    public final boolean s() {
        if (this.D == o.VALUE_NUMBER_FLOAT && (this.s & 8) != 0) {
            double d = this.I;
            return Double.isNaN(d) || Double.isInfinite(d);
        }
        return false;
    }

    @Override // com.a.a.b.l
    public final Number B() {
        if (this.s == 0) {
            h(0);
        }
        if (this.D == o.VALUE_NUMBER_INT) {
            if ((this.s & 1) != 0) {
                return Integer.valueOf(this.t);
            }
            if ((this.s & 2) != 0) {
                return Long.valueOf(this.G);
            }
            if ((this.s & 4) != 0) {
                return av();
            }
            al();
        }
        if ((this.s & 16) != 0) {
            return aw();
        }
        if ((this.s & 32) != 0) {
            return Float.valueOf(this.H);
        }
        if ((this.s & 8) == 0) {
            al();
        }
        return Double.valueOf(this.I);
    }

    @Override // com.a.a.b.l
    public final Number C() {
        if (this.D == o.VALUE_NUMBER_INT) {
            if (this.s == 0) {
                h(0);
            }
            if ((this.s & 1) != 0) {
                return Integer.valueOf(this.t);
            }
            if ((this.s & 2) != 0) {
                return Long.valueOf(this.G);
            }
            if ((this.s & 4) != 0) {
                return av();
            }
            al();
        }
        if (this.s == 0) {
            h(16);
        }
        if ((this.s & 16) != 0) {
            return aw();
        }
        if ((this.s & 32) != 0) {
            return Float.valueOf(this.H);
        }
        if ((this.s & 8) == 0) {
            al();
        }
        return Double.valueOf(this.I);
    }

    @Override // com.a.a.b.l
    public final l.b D() {
        if (this.s == 0) {
            h(0);
        }
        if (this.D == o.VALUE_NUMBER_INT) {
            if ((this.s & 1) != 0) {
                return l.b.INT;
            }
            if ((this.s & 2) != 0) {
                return l.b.LONG;
            }
            return l.b.BIG_INTEGER;
        }
        if ((this.s & 16) != 0) {
            return l.b.BIG_DECIMAL;
        }
        if ((this.s & 32) != 0) {
            return l.b.FLOAT;
        }
        return l.b.DOUBLE;
    }

    @Override // com.a.a.b.l
    public final int G() {
        if ((this.s & 1) == 0) {
            if (this.s == 0) {
                return ab();
            }
            if ((this.s & 1) == 0) {
                ac();
            }
        }
        return this.t;
    }

    @Override // com.a.a.b.l
    public final long H() {
        if ((this.s & 2) == 0) {
            if (this.s == 0) {
                h(2);
            }
            if ((this.s & 2) == 0) {
                aq();
            }
        }
        return this.G;
    }

    @Override // com.a.a.b.l
    public final BigInteger I() {
        if ((this.s & 4) == 0) {
            if (this.s == 0) {
                h(4);
            }
            if ((this.s & 4) == 0) {
                ar();
            }
        }
        return av();
    }

    @Override // com.a.a.b.l
    public final float J() {
        if ((this.s & 32) == 0) {
            if (this.s == 0) {
                h(32);
            }
            if ((this.s & 32) == 0) {
                at();
            }
        }
        return this.H;
    }

    @Override // com.a.a.b.l
    public final double K() {
        if ((this.s & 8) == 0) {
            if (this.s == 0) {
                h(8);
            }
            if ((this.s & 8) == 0) {
                as();
            }
        }
        return this.I;
    }

    @Override // com.a.a.b.l
    public final BigDecimal L() {
        if ((this.s & 16) == 0) {
            if (this.s == 0) {
                h(16);
            }
            if ((this.s & 16) == 0) {
                au();
            }
        }
        return aw();
    }

    private void h(int i) {
        if (this.E) {
            g("Internal error: _parseNumericValue called when parser instance closed");
        }
        if (this.D == o.VALUE_NUMBER_INT) {
            int i2 = this.N;
            if (i2 <= 9) {
                this.t = this.o.c(this.M);
                this.s = 1;
                return;
            }
            if (i2 <= 18) {
                long d = this.o.d(this.M);
                if (i2 == 10) {
                    if (this.M) {
                        if (d >= -2147483648L) {
                            this.t = (int) d;
                            this.s = 1;
                            return;
                        }
                    } else if (d <= 2147483647L) {
                        this.t = (int) d;
                        this.s = 1;
                        return;
                    }
                }
                this.G = d;
                this.s = 2;
                return;
            }
            j(i);
            return;
        }
        if (this.D == o.VALUE_NUMBER_FLOAT) {
            i(i);
        } else {
            a("Current token (%s) not numeric, can not use numeric value accessors", (Object) this.D);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int ab() {
        if (this.E) {
            g("Internal error: _parseNumericValue called when parser instance closed");
        }
        if (this.D == o.VALUE_NUMBER_INT && this.N <= 9) {
            int c2 = this.o.c(this.M);
            this.t = c2;
            this.s = 1;
            return c2;
        }
        h(1);
        if ((this.s & 1) == 0) {
            ac();
        }
        return this.t;
    }

    private void i(int i) {
        try {
            if (i == 16) {
                this.K = null;
                this.L = this.o.e();
                this.s = 16;
            } else if (i == 32) {
                this.H = this.o.b(a(l.a.USE_FAST_DOUBLE_PARSER));
                this.s = 32;
            } else {
                this.I = this.o.a(a(l.a.USE_FAST_DOUBLE_PARSER));
                this.s = 8;
            }
        } catch (NumberFormatException e) {
            a("Malformed numeric value (" + f(this.o.e()) + ")", (Throwable) e);
        }
    }

    private void j(int i) {
        String e = this.o.e();
        try {
            int i2 = this.N;
            char[] d = this.o.d();
            int c2 = this.o.c();
            if (this.M) {
                c2++;
            }
            if (h.a(d, c2, i2, this.M)) {
                this.G = Long.parseLong(e);
                this.s = 2;
                return;
            }
            if (i == 1 || i == 2) {
                d(i, e);
            }
            if (i == 8 || i == 32) {
                this.I = h.b(e, a(l.a.USE_FAST_DOUBLE_PARSER));
                this.s = 8;
            } else {
                this.J = null;
                this.L = e;
                this.s = 4;
            }
        } catch (NumberFormatException e2) {
            a("Malformed numeric value (" + f(e) + ")", (Throwable) e2);
        }
    }

    private void d(int i, String str) {
        if (i == 1) {
            d(str);
        } else {
            e(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ac() {
        if ((this.s & 2) != 0) {
            int i = (int) this.G;
            if (i != this.G) {
                a(w(), k());
            }
            this.t = i;
        } else if ((this.s & 4) != 0) {
            BigInteger av = av();
            if (v.compareTo(av) > 0 || w.compareTo(av) < 0) {
                ai();
            }
            this.t = av.intValue();
        } else if ((this.s & 8) != 0) {
            if (this.I < -2.147483648E9d || this.I > 2.147483647E9d) {
                ai();
            }
            this.t = (int) this.I;
        } else if ((this.s & 16) != 0) {
            BigDecimal aw = aw();
            if (B.compareTo(aw) > 0 || C.compareTo(aw) < 0) {
                ai();
            }
            this.t = aw.intValue();
        } else {
            al();
        }
        this.s |= 1;
    }

    private void aq() {
        if ((this.s & 1) != 0) {
            this.G = this.t;
        } else if ((this.s & 4) != 0) {
            BigInteger av = av();
            if (x.compareTo(av) > 0 || y.compareTo(av) < 0) {
                aj();
            }
            this.G = av.longValue();
        } else if ((this.s & 8) != 0) {
            if (this.I < -9.223372036854776E18d || this.I > 9.223372036854776E18d) {
                aj();
            }
            this.G = (long) this.I;
        } else if ((this.s & 16) != 0) {
            BigDecimal aw = aw();
            if (z.compareTo(aw) > 0 || A.compareTo(aw) < 0) {
                aj();
            }
            this.G = aw.longValue();
        } else {
            al();
        }
        this.s |= 2;
    }

    private void ar() {
        if ((this.s & 16) != 0) {
            this.J = aw().toBigInteger();
        } else if ((this.s & 2) != 0) {
            this.J = BigInteger.valueOf(this.G);
        } else if ((this.s & 1) != 0) {
            this.J = BigInteger.valueOf(this.t);
        } else if ((this.s & 8) != 0) {
            this.J = BigDecimal.valueOf(this.I).toBigInteger();
        } else {
            al();
        }
        this.s |= 4;
    }

    private void as() {
        if ((this.s & 16) != 0) {
            this.I = aw().doubleValue();
        } else if ((this.s & 4) != 0) {
            this.I = av().doubleValue();
        } else if ((this.s & 2) != 0) {
            this.I = this.G;
        } else if ((this.s & 1) != 0) {
            this.I = this.t;
        } else if ((this.s & 32) != 0) {
            this.I = this.H;
        } else {
            al();
        }
        this.s |= 8;
    }

    private void at() {
        if ((this.s & 16) != 0) {
            this.H = aw().floatValue();
        } else if ((this.s & 4) != 0) {
            this.H = av().floatValue();
        } else if ((this.s & 2) != 0) {
            this.H = (float) this.G;
        } else if ((this.s & 1) != 0) {
            this.H = this.t;
        } else if ((this.s & 8) != 0) {
            this.H = (float) this.I;
        } else {
            al();
        }
        this.s |= 32;
    }

    private void au() {
        if ((this.s & 8) != 0) {
            this.K = h.d(w());
        } else if ((this.s & 4) != 0) {
            this.K = new BigDecimal(av());
        } else if ((this.s & 2) != 0) {
            this.K = BigDecimal.valueOf(this.G);
        } else if ((this.s & 1) != 0) {
            this.K = BigDecimal.valueOf(this.t);
        } else {
            al();
        }
        this.s |= 16;
    }

    private BigInteger av() {
        if (this.J != null) {
            return this.J;
        }
        if (this.L == null) {
            throw new IllegalStateException("cannot get BigInteger from current parser state");
        }
        this.J = h.e(this.L);
        this.L = null;
        return this.J;
    }

    private BigDecimal aw() {
        if (this.K != null) {
            return this.K;
        }
        if (this.L == null) {
            throw new IllegalStateException("cannot get BigDecimal from current parser state");
        }
        this.K = h.d(this.L);
        this.L = null;
        return this.K;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(int i, char c2) {
        d d = d();
        g(String.format("Unexpected close marker '%s': expected '%c' (for %s starting at %s)", Character.valueOf((char) i), Character.valueOf(c2), d.e(), d.a(ag())));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final char a(char c2) {
        if (a(l.a.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)) {
            return c2;
        }
        if (c2 == '\'' && a(l.a.ALLOW_SINGLE_QUOTES)) {
            return c2;
        }
        g("Unrecognized character escape " + g(c2));
        return c2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(int i, String str) {
        if (!a(l.a.ALLOW_UNQUOTED_CONTROL_CHARS) || i > 32) {
            g("Illegal unquoted character (" + g((char) i) + "): has to be escaped using backslash to be included in " + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String ad() {
        return ae();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String ae() {
        if (a(l.a.ALLOW_NON_NUMERIC_NUMBERS)) {
            return "(JSON String, Number (or 'NaN'/'INF'/'+INF'), Array, Object or token 'null', 'true' or 'false')";
        }
        return "(JSON String, Number, Array, Object or token 'null', 'true' or 'false')";
    }

    protected char af() {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int a(com.a.a.b.a aVar, int i, int i2) {
        if (i != 92) {
            throw b(aVar, i, i2);
        }
        char af = af();
        if (af <= ' ' && i2 == 0) {
            return -1;
        }
        int b2 = aVar.b((int) af);
        if (b2 < 0 && b2 != -2) {
            throw b(aVar, af, i2);
        }
        return b2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int a(com.a.a.b.a aVar, char c2, int i) {
        if (c2 != '\\') {
            throw b(aVar, c2, i);
        }
        char af = af();
        if (af <= ' ' && i == 0) {
            return -1;
        }
        int b2 = aVar.b(af);
        if (b2 < 0 && (b2 != -2 || i < 2)) {
            throw b(aVar, af, i);
        }
        return b2;
    }

    private IllegalArgumentException b(com.a.a.b.a aVar, int i, int i2) {
        return a(aVar, i, i2, (String) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static IllegalArgumentException a(com.a.a.b.a aVar, int i, int i2, String str) {
        String str2;
        if (i <= 32) {
            str2 = String.format("Illegal white space character (code 0x%s) as character #%d of 4-char base64 unit: can only used between units", Integer.toHexString(i), Integer.valueOf(i2 + 1));
        } else if (aVar.a(i)) {
            str2 = "Unexpected padding character ('" + aVar.b() + "') as character #" + (i2 + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
        } else if (!Character.isDefined(i) || Character.isISOControl(i)) {
            str2 = "Illegal character (code 0x" + Integer.toHexString(i) + ") in base64 content";
        } else {
            str2 = "Illegal character '" + ((char) i) + "' (code 0x" + Integer.toHexString(i) + ") in base64 content";
        }
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        return new IllegalArgumentException(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void b(com.a.a.b.a aVar) {
        g(aVar.d());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.b.c.d ag() {
        if (l.a.INCLUDE_SOURCE_IN_LOCATION.a(this.f177b)) {
            return this.d.c();
        }
        return com.a.a.b.c.d.a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int[] a(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        return Arrays.copyOf(iArr, iArr.length + i);
    }

    protected void ah() {
    }
}
