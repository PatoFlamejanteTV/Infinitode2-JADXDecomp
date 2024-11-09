package com.a.a.b;

import com.a.a.b.f.a;
import com.a.a.b.l;
import com.a.a.c.f.w;
import java.io.Closeable;
import java.io.Flushable;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: infinitode-2.jar:com/a/a/b/h.class */
public abstract class h implements Closeable, Flushable {
    private static com.a.a.b.g.i<u> c;

    /* renamed from: a, reason: collision with root package name */
    protected static final com.a.a.b.g.i<u> f169a;

    /* renamed from: b, reason: collision with root package name */
    protected q f170b;

    public abstract n a();

    public abstract h a(a aVar);

    public abstract boolean b(a aVar);

    public abstract int b();

    @Deprecated
    public abstract h a(int i);

    public abstract void g();

    public abstract void h();

    public abstract void i();

    public abstract void j();

    public abstract void a(String str);

    public abstract void b(r rVar);

    public abstract void b(String str);

    public abstract void a(char[] cArr, int i, int i2);

    public abstract void c(r rVar);

    public abstract void c(String str);

    public abstract void b(char[] cArr, int i, int i2);

    public abstract void a(char c2);

    public abstract void d(String str);

    public abstract void a(com.a.a.b.a aVar, byte[] bArr, int i, int i2);

    public abstract int a(com.a.a.b.a aVar, InputStream inputStream, int i);

    public abstract void c(int i);

    public abstract void b(long j);

    public abstract void a(BigInteger bigInteger);

    public abstract void a(double d);

    public abstract void a(float f);

    public abstract void a(BigDecimal bigDecimal);

    public abstract void e(String str);

    public abstract void a(boolean z);

    public abstract void k();

    public abstract void h(Object obj);

    public abstract void flush();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    static {
        com.a.a.b.g.i<u> a2 = com.a.a.b.g.i.a(u.values());
        c = a2;
        f169a = a2.a((com.a.a.b.g.i<u>) u.CAN_WRITE_FORMATTED_NUMBERS);
        c.a((com.a.a.b.g.i<u>) u.CAN_WRITE_BINARY_NATIVELY);
    }

    /* loaded from: infinitode-2.jar:com/a/a/b/h$a.class */
    public enum a {
        AUTO_CLOSE_TARGET(true),
        AUTO_CLOSE_JSON_CONTENT(true),
        FLUSH_PASSED_TO_STREAM(true),
        QUOTE_FIELD_NAMES(true),
        QUOTE_NON_NUMERIC_NUMBERS(true),
        ESCAPE_NON_ASCII(false),
        WRITE_NUMBERS_AS_STRINGS(false),
        WRITE_BIGDECIMAL_AS_PLAIN(false),
        STRICT_DUPLICATE_DETECTION(false),
        IGNORE_UNKNOWN(false),
        USE_FAST_DOUBLE_WRITER(false),
        WRITE_HEX_UPPER_CASE(true);

        private final boolean m;
        private final int n = 1 << ordinal();

        public static int a() {
            int i = 0;
            for (a aVar : values()) {
                if (aVar.c()) {
                    i |= aVar.b();
                }
            }
            return i;
        }

        a(boolean z) {
            this.m = z;
        }

        private boolean c() {
            return this.m;
        }

        public final boolean a(int i) {
            return (i & this.n) != 0;
        }

        public final int b() {
            return this.n;
        }
    }

    public void a(Object obj) {
        n a2 = a();
        if (a2 != null) {
            a2.a(obj);
        }
    }

    public h a(int i, int i2) {
        return a((b() & (i2 ^ (-1))) | (i & i2));
    }

    public final void a(w.a aVar) {
        throw new UnsupportedOperationException(String.format("Generator of type %s does not support schema of type '%s'", getClass().getName(), aVar.b()));
    }

    public final h a(q qVar) {
        this.f170b = qVar;
        return this;
    }

    public final q c() {
        return this.f170b;
    }

    public h b(int i) {
        return this;
    }

    public h a(com.a.a.b.c.c cVar) {
        return this;
    }

    public h a(r rVar) {
        throw new UnsupportedOperationException();
    }

    public boolean d() {
        return false;
    }

    public boolean e() {
        return false;
    }

    public boolean f() {
        return false;
    }

    @Deprecated
    private void o() {
        g();
    }

    public void b(Object obj) {
        g();
        a(obj);
    }

    public void a(Object obj, int i) {
        o();
        a(obj);
    }

    public void c(Object obj) {
        i();
        a(obj);
    }

    public void d(Object obj) {
        c(obj);
    }

    public final void a(long j) {
        a(Long.toString(j));
    }

    public final void a(int[] iArr, int i, int i2) {
        if (iArr == null) {
            throw new IllegalArgumentException("null array");
        }
        a(iArr.length, 0, i2);
        a(iArr, i2);
        int i3 = i2 + 0;
        for (int i4 = 0; i4 < i3; i4++) {
            c(iArr[i4]);
        }
        h();
    }

    public final void a(long[] jArr, int i, int i2) {
        if (jArr == null) {
            throw new IllegalArgumentException("null array");
        }
        a(jArr.length, 0, i2);
        a(jArr, i2);
        int i3 = i2 + 0;
        for (int i4 = 0; i4 < i3; i4++) {
            b(jArr[i4]);
        }
        h();
    }

    public final void a(double[] dArr, int i, int i2) {
        if (dArr == null) {
            throw new IllegalArgumentException("null array");
        }
        a(dArr.length, 0, i2);
        a(dArr, i2);
        int i3 = i2 + 0;
        for (int i4 = 0; i4 < i3; i4++) {
            a(dArr[i4]);
        }
        h();
    }

    public void d(r rVar) {
        c(rVar.a());
    }

    public void e(r rVar) {
        d(rVar.a());
    }

    public final void a(byte[] bArr, int i, int i2) {
        a(b.a(), bArr, i, i2);
    }

    public final void a(byte[] bArr) {
        a(b.a(), bArr, 0, bArr.length);
    }

    public final int a(InputStream inputStream, int i) {
        return a(b.a(), inputStream, i);
    }

    public void a(short s) {
        c(s);
    }

    public void e(Object obj) {
        if (obj == null) {
            k();
        } else {
            if (obj instanceof byte[]) {
                a((byte[]) obj);
                return;
            }
            throw new g("No native support for writing embedded objects of type " + obj.getClass().getName(), this);
        }
    }

    public void f(Object obj) {
        throw new g("No native support for writing Object Ids", this);
    }

    public final void l() {
        throw new g("No native support for writing Object Ids", this);
    }

    public void g(Object obj) {
        throw new g("No native support for writing Type Ids", this);
    }

    public final com.a.a.b.f.a a(com.a.a.b.f.a aVar) {
        Object obj = aVar.c;
        o oVar = aVar.f;
        if (e()) {
            aVar.g = false;
            g(obj);
        } else {
            String valueOf = obj instanceof String ? (String) obj : String.valueOf(obj);
            aVar.g = true;
            a.EnumC0003a enumC0003a = aVar.e;
            if (oVar != o.START_OBJECT && enumC0003a.a()) {
                a.EnumC0003a enumC0003a2 = a.EnumC0003a.WRAPPER_ARRAY;
                enumC0003a = enumC0003a2;
                aVar.e = enumC0003a2;
            }
            switch (i.f173a[enumC0003a.ordinal()]) {
                case 1:
                case 2:
                    break;
                case 3:
                    c(aVar.f144a);
                    a(aVar.d, valueOf);
                    return aVar;
                case 4:
                    i();
                    a(valueOf);
                    break;
                default:
                    g();
                    b(valueOf);
                    break;
            }
        }
        if (oVar == o.START_OBJECT) {
            c(aVar.f144a);
        } else if (oVar == o.START_ARRAY) {
            g();
        }
        return aVar;
    }

    public final com.a.a.b.f.a b(com.a.a.b.f.a aVar) {
        o oVar = aVar.f;
        if (oVar == o.START_OBJECT) {
            j();
        } else if (oVar == o.START_ARRAY) {
            h();
        }
        if (aVar.g) {
            switch (i.f173a[aVar.e.ordinal()]) {
                case 1:
                    Object obj = aVar.c;
                    a(aVar.d, obj instanceof String ? (String) obj : String.valueOf(obj));
                    break;
                case 2:
                case 3:
                    break;
                case 4:
                default:
                    j();
                    break;
                case 5:
                    h();
                    break;
            }
        }
        return aVar;
    }

    private void a(String str, String str2) {
        a(str);
        b(str2);
    }

    public void a(l lVar) {
        o k = lVar.k();
        switch (k == null ? -1 : k.a()) {
            case -1:
                f("No current event to copy");
                return;
            case 0:
            default:
                throw new IllegalStateException("Internal error: unknown current token, " + k);
            case 1:
                i();
                return;
            case 2:
                j();
                return;
            case 3:
                g();
                return;
            case 4:
                h();
                return;
            case 5:
                a(lVar.u());
                return;
            case 6:
                if (lVar.A()) {
                    a(lVar.x(), lVar.z(), lVar.y());
                    return;
                } else {
                    b(lVar.w());
                    return;
                }
            case 7:
                l.b D = lVar.D();
                if (D == l.b.INT) {
                    c(lVar.G());
                    return;
                } else if (D == l.b.BIG_INTEGER) {
                    a(lVar.I());
                    return;
                } else {
                    b(lVar.H());
                    return;
                }
            case 8:
                l.b D2 = lVar.D();
                if (D2 == l.b.BIG_DECIMAL) {
                    a(lVar.L());
                    return;
                } else if (D2 == l.b.FLOAT) {
                    a(lVar.J());
                    return;
                } else {
                    a(lVar.K());
                    return;
                }
            case 9:
                a(true);
                return;
            case 10:
                a(false);
                return;
            case 11:
                k();
                return;
            case 12:
                h(lVar.N());
                return;
        }
    }

    public void b(l lVar) {
        o k = lVar.k();
        int a2 = k == null ? -1 : k.a();
        int i = a2;
        if (a2 == 5) {
            a(lVar.u());
            o g = lVar.g();
            i = g == null ? -1 : g.a();
        }
        switch (i) {
            case 1:
                i();
                c(lVar);
                return;
            case 3:
                g();
                c(lVar);
                return;
            default:
                a(lVar);
                return;
        }
    }

    private void c(l lVar) {
        int i = 1;
        while (true) {
            o g = lVar.g();
            if (g != null) {
                switch (g.a()) {
                    case 1:
                        i();
                        i++;
                        break;
                    case 2:
                        j();
                        i--;
                        if (i != 0) {
                            break;
                        } else {
                            return;
                        }
                    case 3:
                        g();
                        i++;
                        break;
                    case 4:
                        h();
                        i--;
                        if (i != 0) {
                            break;
                        } else {
                            return;
                        }
                    case 5:
                        a(lVar.u());
                        break;
                    case 6:
                        if (lVar.A()) {
                            a(lVar.x(), lVar.z(), lVar.y());
                            break;
                        } else {
                            b(lVar.w());
                            break;
                        }
                    case 7:
                        l.b D = lVar.D();
                        if (D == l.b.INT) {
                            c(lVar.G());
                            break;
                        } else if (D == l.b.BIG_INTEGER) {
                            a(lVar.I());
                            break;
                        } else {
                            b(lVar.H());
                            break;
                        }
                    case 8:
                        l.b D2 = lVar.D();
                        if (D2 == l.b.BIG_DECIMAL) {
                            a(lVar.L());
                            break;
                        } else if (D2 == l.b.FLOAT) {
                            a(lVar.J());
                            break;
                        } else {
                            a(lVar.K());
                            break;
                        }
                    case 9:
                        a(true);
                        break;
                    case 10:
                        a(false);
                        break;
                    case 11:
                        k();
                        break;
                    case 12:
                        h(lVar.N());
                        break;
                    default:
                        throw new IllegalStateException("Internal error: unknown current token, " + g);
                }
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void f(String str) {
        throw new g(str, this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void m() {
        com.a.a.b.g.q.a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void n() {
        throw new UnsupportedOperationException("Operation not supported by generator of type " + getClass().getName());
    }

    private static void a(int i, int i2, int i3) {
        if (i2 < 0 || i2 + i3 > i) {
            throw new IllegalArgumentException(String.format("invalid argument(s) (offset=%d, length=%d) for input array of %d element", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i)));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void i(Object obj) {
        if (obj == null) {
            k();
            return;
        }
        if (obj instanceof String) {
            b((String) obj);
            return;
        }
        if (obj instanceof Number) {
            Number number = (Number) obj;
            if (number instanceof Integer) {
                c(number.intValue());
                return;
            }
            if (number instanceof Long) {
                b(number.longValue());
                return;
            }
            if (number instanceof Double) {
                a(number.doubleValue());
                return;
            }
            if (number instanceof Float) {
                a(number.floatValue());
                return;
            }
            if (number instanceof Short) {
                a(number.shortValue());
                return;
            }
            if (number instanceof Byte) {
                a(number.byteValue());
                return;
            }
            if (number instanceof BigInteger) {
                a((BigInteger) number);
                return;
            }
            if (number instanceof BigDecimal) {
                a((BigDecimal) number);
                return;
            } else if (number instanceof AtomicInteger) {
                c(((AtomicInteger) number).get());
                return;
            } else if (number instanceof AtomicLong) {
                b(((AtomicLong) number).get());
                return;
            }
        } else if (obj instanceof byte[]) {
            a((byte[]) obj);
            return;
        } else if (obj instanceof Boolean) {
            a(((Boolean) obj).booleanValue());
            return;
        } else if (obj instanceof AtomicBoolean) {
            a(((AtomicBoolean) obj).get());
            return;
        }
        throw new IllegalStateException("No ObjectCodec defined for the generator, can only serialize simple wrapper types (type passed " + obj.getClass().getName() + ")");
    }
}
