package com.a.a.b.a;

import com.a.a.b.c.e;
import com.a.a.b.c.h;
import com.a.a.b.g.q;
import com.a.a.b.k;
import com.a.a.b.l;
import com.a.a.b.o;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: infinitode-2.jar:com/a/a/b/a/c.class */
public abstract class c extends l {
    protected static final byte[] u = new byte[0];
    protected static final BigInteger v = BigInteger.valueOf(-2147483648L);
    protected static final BigInteger w = BigInteger.valueOf(2147483647L);
    protected static final BigInteger x = BigInteger.valueOf(Long.MIN_VALUE);
    protected static final BigInteger y = BigInteger.valueOf(Long.MAX_VALUE);
    protected static final BigDecimal z = new BigDecimal(x);
    protected static final BigDecimal A = new BigDecimal(y);
    protected static final BigDecimal B = new BigDecimal(v);
    protected static final BigDecimal C = new BigDecimal(w);
    protected o D;

    @Override // com.a.a.b.l
    public abstract o g();

    protected abstract void Y();

    @Override // com.a.a.b.l
    public abstract String u();

    @Override // com.a.a.b.l
    public abstract String w();

    /* JADX INFO: Access modifiers changed from: protected */
    public c() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public c(int i) {
        super(i);
    }

    @Override // com.a.a.b.l
    public final o k() {
        return this.D;
    }

    @Override // com.a.a.b.l
    public final int l() {
        o oVar = this.D;
        if (oVar == null) {
            return 0;
        }
        return oVar.a();
    }

    @Override // com.a.a.b.l
    public final o m() {
        return this.D;
    }

    @Override // com.a.a.b.l
    @Deprecated
    public final int n() {
        o oVar = this.D;
        if (oVar == null) {
            return 0;
        }
        return oVar.a();
    }

    @Override // com.a.a.b.l
    public final boolean o() {
        return this.D != null;
    }

    @Override // com.a.a.b.l
    public final boolean c(int i) {
        o oVar = this.D;
        return oVar == null ? i == 0 : oVar.a() == i;
    }

    @Override // com.a.a.b.l
    public final boolean a(o oVar) {
        return this.D == oVar;
    }

    @Override // com.a.a.b.l
    public final boolean p() {
        return this.D == o.START_ARRAY;
    }

    @Override // com.a.a.b.l
    public final boolean q() {
        return this.D == o.START_OBJECT;
    }

    @Override // com.a.a.b.l
    public final boolean r() {
        return this.D == o.VALUE_NUMBER_INT;
    }

    @Override // com.a.a.b.l
    public final l j() {
        if (this.D != o.START_OBJECT && this.D != o.START_ARRAY) {
            return this;
        }
        int i = 1;
        while (true) {
            o g = g();
            if (g == null) {
                Y();
                return this;
            }
            if (g.e()) {
                i++;
            } else if (g.f()) {
                i--;
                if (i == 0) {
                    return this;
                }
            } else if (g == o.NOT_AVAILABLE) {
                a("Not enough content available for `skipChildren()`: non-blocking parser? (%s)", getClass().getName());
            }
        }
    }

    @Override // com.a.a.b.l
    public final void t() {
        if (this.D != null) {
            this.D = null;
        }
    }

    @Override // com.a.a.b.l
    public int P() {
        o oVar = this.D;
        if (oVar == o.VALUE_NUMBER_INT || oVar == o.VALUE_NUMBER_FLOAT) {
            return G();
        }
        return d(0);
    }

    @Override // com.a.a.b.l
    public int d(int i) {
        o oVar = this.D;
        if (oVar == o.VALUE_NUMBER_INT || oVar == o.VALUE_NUMBER_FLOAT) {
            return G();
        }
        if (oVar != null) {
            switch (oVar.a()) {
                case 6:
                    String w2 = w();
                    if (h(w2)) {
                        return 0;
                    }
                    return h.a(w2, i);
                case 9:
                    return 1;
                case 10:
                    return 0;
                case 11:
                    return 0;
                case 12:
                    Object N = N();
                    if (N instanceof Number) {
                        return ((Number) N).intValue();
                    }
                    break;
            }
        }
        return i;
    }

    @Override // com.a.a.b.l
    public final long Q() {
        o oVar = this.D;
        if (oVar == o.VALUE_NUMBER_INT || oVar == o.VALUE_NUMBER_FLOAT) {
            return H();
        }
        return a(0L);
    }

    @Override // com.a.a.b.l
    public final long a(long j) {
        o oVar = this.D;
        if (oVar == o.VALUE_NUMBER_INT || oVar == o.VALUE_NUMBER_FLOAT) {
            return H();
        }
        if (oVar != null) {
            switch (oVar.a()) {
                case 6:
                    String w2 = w();
                    if (h(w2)) {
                        return 0L;
                    }
                    return h.a(w2, j);
                case 9:
                    return 1L;
                case 10:
                case 11:
                    return 0L;
                case 12:
                    Object N = N();
                    if (N instanceof Number) {
                        return ((Number) N).longValue();
                    }
                    break;
            }
        }
        return j;
    }

    @Override // com.a.a.b.l
    public String R() {
        return a((String) null);
    }

    @Override // com.a.a.b.l
    public String a(String str) {
        if (this.D == o.VALUE_STRING) {
            return w();
        }
        if (this.D == o.FIELD_NAME) {
            return u();
        }
        if (this.D == null || this.D == o.VALUE_NULL || !this.D.g()) {
            return str;
        }
        return w();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(String str, com.a.a.b.g.c cVar, com.a.a.b.a aVar) {
        try {
            aVar.a(str, cVar);
        } catch (IllegalArgumentException e) {
            g(e.getMessage());
        }
    }

    private static boolean h(String str) {
        return "null".equals(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final <T> T b(int i, String str) {
        g(String.format("Unexpected character (%s) in numeric value", g(i)) + ": " + str);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void c(String str) {
        g("Invalid numeric value: " + str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ai() {
        d(w());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void d(String str) {
        a(str, k());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(String str, o oVar) {
        a(String.format("Numeric value (%s) out of range of int (%d - %s)", i(str), Integer.MIN_VALUE, Integer.MAX_VALUE), oVar, Integer.TYPE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void aj() {
        e(w());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void e(String str) {
        c(str, k());
    }

    private void c(String str, o oVar) {
        a(String.format("Numeric value (%s) out of range of long (%d - %s)", i(str), Long.MIN_VALUE, Long.MAX_VALUE), oVar, Long.TYPE);
    }

    private void a(String str, o oVar, Class<?> cls) {
        throw new com.a.a.b.b.a(this, str, oVar, cls);
    }

    private static String i(String str) {
        int length = str.length();
        int i = length;
        if (length < 1000) {
            return str;
        }
        if (str.startsWith("-")) {
            i--;
        }
        return String.format("[Integer with %d digits]", Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String f(String str) {
        int length = str.length();
        int i = length;
        if (length < 1000) {
            return str;
        }
        if (str.startsWith("-")) {
            i--;
        }
        return String.format("[number with %d characters]", Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void c(int i, String str) {
        if (i < 0) {
            ak();
        }
        String format = String.format("Unexpected character (%s)", g(i));
        if (str != null) {
            format = format + ": " + str;
        }
        g(format);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ak() {
        b(" in " + this.D, this.D);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void b(o oVar) {
        String str;
        if (oVar == o.VALUE_STRING) {
            str = " in a String value";
        } else if (oVar == o.VALUE_NUMBER_INT || oVar == o.VALUE_NUMBER_FLOAT) {
            str = " in a Number value";
        } else {
            str = " in a value";
        }
        b(str, oVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void b(String str, o oVar) {
        throw new e(this, oVar, "Unexpected end-of-input" + str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void e(int i) {
        c(i, "Expected space separating root-level values");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void f(int i) {
        g("Illegal character (" + g((char) i) + "): only regular white space (\\r, \\n, \\t) is allowed between tokens");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final String g(int i) {
        char c = (char) i;
        if (Character.isISOControl(c)) {
            return "(CTRL-CHAR, code " + i + ")";
        }
        return i > 255 ? "'" + c + "' (code " + i + " / 0x" + Integer.toHexString(i) + ")" : "'" + c + "' (code " + i + ")";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void g(String str) {
        throw b(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(String str, Object obj) {
        throw b(String.format(str, obj));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(String str, Object obj, Object obj2) {
        throw b(String.format(str, obj, obj2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(String str, Throwable th) {
        throw b(str, th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void al() {
        q.a();
    }

    private k b(String str, Throwable th) {
        return new k(this, str, th);
    }
}
