package com.a.a.c.m;

import com.a.a.b.h;
import com.a.a.b.l;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.TreeMap;

/* loaded from: infinitode-2.jar:com/a/a/c/m/ac.class */
public class ac extends com.a.a.b.h {
    private static int c = h.a.a();
    private com.a.a.b.p d;
    private com.a.a.b.n e;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private b k;
    private b l;
    private int m;
    private Object n;
    private Object o;
    private boolean p = false;
    private int f = c;
    private com.a.a.b.d.f q = com.a.a.b.d.f.b((com.a.a.b.d.b) null);

    public ac(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        this.d = lVar.a();
        this.e = lVar.d();
        b bVar = new b();
        this.l = bVar;
        this.k = bVar;
        this.m = 0;
        this.g = lVar.T();
        this.h = lVar.S();
        this.i = this.g || this.h;
        this.j = gVar.a(com.a.a.c.i.USE_BIG_DECIMAL_FOR_FLOATS);
    }

    public final com.a.a.b.l o() {
        return a(this.d);
    }

    public final com.a.a.b.l p() {
        com.a.a.b.l a2 = a(this.d);
        a2.g();
        return a2;
    }

    private com.a.a.b.l a(com.a.a.b.p pVar) {
        return new a(this.k, pVar, this.g, this.h, this.e);
    }

    public final com.a.a.b.l c(com.a.a.b.l lVar) {
        a aVar = new a(this.k, lVar.a(), this.g, this.h, this.e);
        aVar.a(lVar.f());
        return aVar;
    }

    public final com.a.a.b.o q() {
        return this.k.a(0);
    }

    public final ac a(ac acVar) {
        if (!this.g) {
            this.g = acVar.e();
        }
        if (!this.h) {
            this.h = acVar.d();
        }
        this.i = this.g || this.h;
        com.a.a.b.l o = acVar.o();
        while (o.g() != null) {
            b(o);
        }
        return this;
    }

    public final void a(com.a.a.b.h hVar) {
        b bVar = this.k;
        int i = -1;
        boolean z = this.i;
        boolean z2 = z && bVar.b();
        while (true) {
            i++;
            if (i >= 16) {
                i = 0;
                b a2 = bVar.a();
                bVar = a2;
                if (a2 != null) {
                    z2 = z && bVar.b();
                } else {
                    return;
                }
            }
            com.a.a.b.o a3 = bVar.a(i);
            if (a3 != null) {
                if (z2) {
                    Object c2 = bVar.c(i);
                    if (c2 != null) {
                        hVar.f(c2);
                    }
                    Object d = bVar.d(i);
                    if (d != null) {
                        hVar.g(d);
                    }
                }
                switch (ad.f708a[a3.ordinal()]) {
                    case 1:
                        hVar.i();
                        break;
                    case 2:
                        hVar.j();
                        break;
                    case 3:
                        hVar.g();
                        break;
                    case 4:
                        hVar.h();
                        break;
                    case 5:
                        Object b2 = bVar.b(i);
                        if (b2 instanceof com.a.a.b.r) {
                            hVar.b((com.a.a.b.r) b2);
                            break;
                        } else {
                            hVar.a((String) b2);
                            break;
                        }
                    case 6:
                        Object b3 = bVar.b(i);
                        if (b3 instanceof com.a.a.b.r) {
                            hVar.c((com.a.a.b.r) b3);
                            break;
                        } else {
                            hVar.b((String) b3);
                            break;
                        }
                    case 7:
                        Object b4 = bVar.b(i);
                        if (b4 instanceof Integer) {
                            hVar.c(((Integer) b4).intValue());
                            break;
                        } else if (b4 instanceof BigInteger) {
                            hVar.a((BigInteger) b4);
                            break;
                        } else if (b4 instanceof Long) {
                            hVar.b(((Long) b4).longValue());
                            break;
                        } else if (b4 instanceof Short) {
                            hVar.a(((Short) b4).shortValue());
                            break;
                        } else {
                            hVar.c(((Number) b4).intValue());
                            break;
                        }
                    case 8:
                        Object b5 = bVar.b(i);
                        if (b5 instanceof Double) {
                            hVar.a(((Double) b5).doubleValue());
                            break;
                        } else if (b5 instanceof BigDecimal) {
                            hVar.a((BigDecimal) b5);
                            break;
                        } else if (b5 instanceof Float) {
                            hVar.a(((Float) b5).floatValue());
                            break;
                        } else if (b5 == null) {
                            hVar.k();
                            break;
                        } else if (b5 instanceof String) {
                            hVar.e((String) b5);
                            break;
                        } else {
                            f(String.format("Unrecognized value type for VALUE_NUMBER_FLOAT: %s, cannot serialize", b5.getClass().getName()));
                            break;
                        }
                    case 9:
                        hVar.a(true);
                        break;
                    case 10:
                        hVar.a(false);
                        break;
                    case 11:
                        hVar.k();
                        break;
                    case 12:
                        Object b6 = bVar.b(i);
                        if (b6 instanceof y) {
                            ((y) b6).a(hVar);
                            break;
                        } else if (b6 instanceof com.a.a.c.n) {
                            hVar.h(b6);
                            break;
                        } else {
                            hVar.e(b6);
                            break;
                        }
                    default:
                        throw new RuntimeException("Internal error: should never end up through this code path");
                }
            } else {
                return;
            }
        }
    }

    public final ac a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        com.a.a.b.o g;
        if (!lVar.a(com.a.a.b.o.FIELD_NAME)) {
            b(lVar);
            return this;
        }
        i();
        do {
            b(lVar);
            g = lVar.g();
        } while (g == com.a.a.b.o.FIELD_NAME);
        if (g != com.a.a.b.o.END_OBJECT) {
            gVar.a(ac.class, com.a.a.b.o.END_OBJECT, "Expected END_OBJECT after copying contents of a JsonParser into TokenBuffer, got " + g, new Object[0]);
        }
        j();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[TokenBuffer: ");
        com.a.a.b.l o = o();
        int i = 0;
        boolean z = this.g || this.h;
        while (true) {
            try {
                com.a.a.b.o g = o.g();
                if (g == null) {
                    break;
                }
                if (z) {
                    a(sb);
                }
                if (i < 100) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(g.toString());
                    if (g == com.a.a.b.o.FIELD_NAME) {
                        sb.append('(');
                        sb.append(o.v());
                        sb.append(')');
                    }
                }
                i++;
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        if (i >= 100) {
            sb.append(" ... (truncated ").append(i - 100).append(" entries)");
        }
        sb.append(']');
        return sb.toString();
    }

    private final void a(StringBuilder sb) {
        Object c2 = this.l.c(this.m - 1);
        if (c2 != null) {
            sb.append("[objectId=").append(String.valueOf(c2)).append(']');
        }
        Object d = this.l.d(this.m - 1);
        if (d != null) {
            sb.append("[typeId=").append(String.valueOf(d)).append(']');
        }
    }

    @Override // com.a.a.b.h
    public final com.a.a.b.h a(h.a aVar) {
        this.f &= aVar.b() ^ (-1);
        return this;
    }

    @Override // com.a.a.b.h
    public final boolean b(h.a aVar) {
        return (this.f & aVar.b()) != 0;
    }

    @Override // com.a.a.b.h
    public final int b() {
        return this.f;
    }

    @Override // com.a.a.b.h
    @Deprecated
    public final com.a.a.b.h a(int i) {
        this.f = i;
        return this;
    }

    @Override // com.a.a.b.h
    public final com.a.a.b.h a(int i, int i2) {
        this.f = (b() & (i2 ^ (-1))) | (i & i2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.b.h
    /* renamed from: r, reason: merged with bridge method [inline-methods] */
    public com.a.a.b.d.f a() {
        return this.q;
    }

    @Override // com.a.a.b.h
    public final boolean f() {
        return true;
    }

    @Override // com.a.a.b.h, java.io.Flushable
    public void flush() {
    }

    @Override // com.a.a.b.h, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // com.a.a.b.h
    public final void g() {
        this.q.n();
        b(com.a.a.b.o.START_ARRAY);
        this.q = this.q.i();
    }

    @Override // com.a.a.b.h
    public final void b(Object obj) {
        this.q.n();
        b(com.a.a.b.o.START_ARRAY);
        this.q = this.q.b(obj);
    }

    @Override // com.a.a.b.h
    public final void a(Object obj, int i) {
        this.q.n();
        b(com.a.a.b.o.START_ARRAY);
        this.q = this.q.b(obj);
    }

    @Override // com.a.a.b.h
    public final void h() {
        c(com.a.a.b.o.END_ARRAY);
        com.a.a.b.d.f a2 = this.q.a();
        if (a2 != null) {
            this.q = a2;
        }
    }

    @Override // com.a.a.b.h
    public final void i() {
        this.q.n();
        b(com.a.a.b.o.START_OBJECT);
        this.q = this.q.j();
    }

    @Override // com.a.a.b.h
    public final void c(Object obj) {
        this.q.n();
        b(com.a.a.b.o.START_OBJECT);
        this.q = this.q.c(obj);
    }

    @Override // com.a.a.b.h
    public final void d(Object obj) {
        this.q.n();
        b(com.a.a.b.o.START_OBJECT);
        this.q = this.q.c(obj);
    }

    @Override // com.a.a.b.h
    public final void j() {
        c(com.a.a.b.o.END_OBJECT);
        com.a.a.b.d.f a2 = this.q.a();
        if (a2 != null) {
            this.q = a2;
        }
    }

    @Override // com.a.a.b.h
    public final void a(String str) {
        this.q.a(str);
        j(str);
    }

    @Override // com.a.a.b.h
    public final void b(com.a.a.b.r rVar) {
        this.q.a(rVar.a());
        j(rVar);
    }

    @Override // com.a.a.b.h
    public final void b(String str) {
        if (str == null) {
            k();
        } else {
            a(com.a.a.b.o.VALUE_STRING, str);
        }
    }

    @Override // com.a.a.b.h
    public final void a(char[] cArr, int i, int i2) {
        b(new String(cArr, i, i2));
    }

    @Override // com.a.a.b.h
    public final void c(com.a.a.b.r rVar) {
        if (rVar == null) {
            k();
        } else {
            a(com.a.a.b.o.VALUE_STRING, rVar);
        }
    }

    @Override // com.a.a.b.h
    public final void c(String str) {
        n();
    }

    @Override // com.a.a.b.h
    public final void d(com.a.a.b.r rVar) {
        n();
    }

    @Override // com.a.a.b.h
    public final void b(char[] cArr, int i, int i2) {
        n();
    }

    @Override // com.a.a.b.h
    public final void a(char c2) {
        n();
    }

    @Override // com.a.a.b.h
    public final void d(String str) {
        a(com.a.a.b.o.VALUE_EMBEDDED_OBJECT, new y(str));
    }

    @Override // com.a.a.b.h
    public final void a(short s) {
        a(com.a.a.b.o.VALUE_NUMBER_INT, Short.valueOf(s));
    }

    @Override // com.a.a.b.h
    public final void c(int i) {
        a(com.a.a.b.o.VALUE_NUMBER_INT, Integer.valueOf(i));
    }

    @Override // com.a.a.b.h
    public final void b(long j) {
        a(com.a.a.b.o.VALUE_NUMBER_INT, Long.valueOf(j));
    }

    @Override // com.a.a.b.h
    public final void a(double d) {
        a(com.a.a.b.o.VALUE_NUMBER_FLOAT, Double.valueOf(d));
    }

    @Override // com.a.a.b.h
    public final void a(float f) {
        a(com.a.a.b.o.VALUE_NUMBER_FLOAT, Float.valueOf(f));
    }

    @Override // com.a.a.b.h
    public final void a(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            k();
        } else {
            a(com.a.a.b.o.VALUE_NUMBER_FLOAT, bigDecimal);
        }
    }

    @Override // com.a.a.b.h
    public final void a(BigInteger bigInteger) {
        if (bigInteger == null) {
            k();
        } else {
            a(com.a.a.b.o.VALUE_NUMBER_INT, bigInteger);
        }
    }

    @Override // com.a.a.b.h
    public final void e(String str) {
        a(com.a.a.b.o.VALUE_NUMBER_FLOAT, str);
    }

    @Override // com.a.a.b.h
    public final void a(boolean z) {
        a(z ? com.a.a.b.o.VALUE_TRUE : com.a.a.b.o.VALUE_FALSE);
    }

    @Override // com.a.a.b.h
    public final void k() {
        a(com.a.a.b.o.VALUE_NULL);
    }

    @Override // com.a.a.b.h
    public final void h(Object obj) {
        if (obj == null) {
            k();
            return;
        }
        if (obj.getClass() == byte[].class || (obj instanceof y)) {
            a(com.a.a.b.o.VALUE_EMBEDDED_OBJECT, obj);
        } else if (this.d == null) {
            a(com.a.a.b.o.VALUE_EMBEDDED_OBJECT, obj);
        } else {
            this.d.a(this, obj);
        }
    }

    @Override // com.a.a.b.h
    public final void a(com.a.a.b.a aVar, byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        h(bArr2);
    }

    @Override // com.a.a.b.h
    public final int a(com.a.a.b.a aVar, InputStream inputStream, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.a.a.b.h
    public final boolean e() {
        return this.g;
    }

    @Override // com.a.a.b.h
    public final boolean d() {
        return this.h;
    }

    @Override // com.a.a.b.h
    public final void g(Object obj) {
        this.n = obj;
        this.p = true;
    }

    @Override // com.a.a.b.h
    public final void f(Object obj) {
        this.o = obj;
        this.p = true;
    }

    @Override // com.a.a.b.h
    public final void e(Object obj) {
        a(com.a.a.b.o.VALUE_EMBEDDED_OBJECT, obj);
    }

    @Override // com.a.a.b.h
    public final void a(com.a.a.b.l lVar) {
        if (this.i) {
            e(lVar);
        }
        switch (ad.f708a[lVar.k().ordinal()]) {
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
                a(lVar.v());
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
                switch (ad.f709b[lVar.D().ordinal()]) {
                    case 1:
                        c(lVar.G());
                        return;
                    case 2:
                        a(lVar.I());
                        return;
                    default:
                        b(lVar.H());
                        return;
                }
            case 8:
                if (this.j) {
                    a(lVar.L());
                    return;
                }
                switch (ad.f709b[lVar.D().ordinal()]) {
                    case 3:
                        a(lVar.L());
                        return;
                    case 4:
                        a(lVar.J());
                        return;
                    default:
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
            default:
                throw new RuntimeException("Internal error: unexpected token: " + lVar.k());
        }
    }

    @Override // com.a.a.b.h
    public final void b(com.a.a.b.l lVar) {
        com.a.a.b.o k = lVar.k();
        com.a.a.b.o oVar = k;
        if (k == com.a.a.b.o.FIELD_NAME) {
            if (this.i) {
                e(lVar);
            }
            a(lVar.v());
            oVar = lVar.g();
        } else if (oVar == null) {
            throw new IllegalStateException("No token available from argument `JsonParser`");
        }
        switch (ad.f708a[oVar.ordinal()]) {
            case 1:
                if (this.i) {
                    e(lVar);
                }
                i();
                d(lVar);
                return;
            case 2:
                j();
                return;
            case 3:
                if (this.i) {
                    e(lVar);
                }
                g();
                d(lVar);
                return;
            case 4:
                h();
                return;
            default:
                a(lVar, oVar);
                return;
        }
    }

    private void d(com.a.a.b.l lVar) {
        int i = 1;
        while (true) {
            com.a.a.b.o g = lVar.g();
            if (g != null) {
                switch (ad.f708a[g.ordinal()]) {
                    case 1:
                        if (this.i) {
                            e(lVar);
                        }
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
                        if (this.i) {
                            e(lVar);
                        }
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
                        if (this.i) {
                            e(lVar);
                        }
                        a(lVar.v());
                        break;
                    default:
                        a(lVar, g);
                        break;
                }
            } else {
                return;
            }
        }
    }

    private void a(com.a.a.b.l lVar, com.a.a.b.o oVar) {
        if (this.i) {
            e(lVar);
        }
        switch (ad.f708a[oVar.ordinal()]) {
            case 6:
                if (lVar.A()) {
                    a(lVar.x(), lVar.z(), lVar.y());
                    return;
                } else {
                    b(lVar.w());
                    return;
                }
            case 7:
                switch (ad.f709b[lVar.D().ordinal()]) {
                    case 1:
                        c(lVar.G());
                        return;
                    case 2:
                        a(lVar.I());
                        return;
                    default:
                        b(lVar.H());
                        return;
                }
            case 8:
                if (this.j) {
                    a(lVar.L());
                    return;
                } else {
                    a(com.a.a.b.o.VALUE_NUMBER_FLOAT, lVar.C());
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
            default:
                throw new RuntimeException("Internal error: unexpected token: " + oVar);
        }
    }

    private final void e(com.a.a.b.l lVar) {
        Object V = lVar.V();
        this.n = V;
        if (V != null) {
            this.p = true;
        }
        Object U = lVar.U();
        this.o = U;
        if (U != null) {
            this.p = true;
        }
    }

    private void a(com.a.a.b.o oVar) {
        b a2;
        this.q.n();
        if (this.p) {
            a2 = this.l.a(this.m, oVar, this.o, this.n);
        } else {
            a2 = this.l.a(this.m, oVar);
        }
        if (a2 == null) {
            this.m++;
        } else {
            this.l = a2;
            this.m = 1;
        }
    }

    private void a(com.a.a.b.o oVar, Object obj) {
        b a2;
        this.q.n();
        if (this.p) {
            a2 = this.l.a(this.m, oVar, obj, this.o, this.n);
        } else {
            a2 = this.l.a(this.m, oVar, obj);
        }
        if (a2 == null) {
            this.m++;
        } else {
            this.l = a2;
            this.m = 1;
        }
    }

    private void j(Object obj) {
        b a2;
        if (this.p) {
            a2 = this.l.a(this.m, com.a.a.b.o.FIELD_NAME, obj, this.o, this.n);
        } else {
            a2 = this.l.a(this.m, com.a.a.b.o.FIELD_NAME, obj);
        }
        if (a2 == null) {
            this.m++;
        } else {
            this.l = a2;
            this.m = 1;
        }
    }

    private void b(com.a.a.b.o oVar) {
        b a2;
        if (this.p) {
            a2 = this.l.a(this.m, oVar, this.o, this.n);
        } else {
            a2 = this.l.a(this.m, oVar);
        }
        if (a2 == null) {
            this.m++;
        } else {
            this.l = a2;
            this.m = 1;
        }
    }

    private void c(com.a.a.b.o oVar) {
        b a2 = this.l.a(this.m, oVar);
        if (a2 == null) {
            this.m++;
        } else {
            this.l = a2;
            this.m = 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.a.a.b.h
    public final void n() {
        throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/m/ac$a.class */
    public static final class a extends com.a.a.b.a.c {
        private com.a.a.b.p c;
        private boolean d;
        private boolean e;
        private b f;
        private ae h;
        private boolean i;
        private transient com.a.a.b.g.c j;
        private com.a.a.b.j k = null;
        private int g = -1;

        public a(b bVar, com.a.a.b.p pVar, boolean z, boolean z2, com.a.a.b.n nVar) {
            this.f = bVar;
            this.c = pVar;
            this.h = ae.a(nVar);
            this.d = z;
            this.e = z2;
        }

        public final void a(com.a.a.b.j jVar) {
            this.k = jVar;
        }

        @Override // com.a.a.b.l
        public final com.a.a.b.p a() {
            return this.c;
        }

        @Override // com.a.a.b.l
        public final com.a.a.b.g.i<com.a.a.b.s> c() {
            return f176a;
        }

        @Override // com.a.a.b.l, java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            if (!this.i) {
                this.i = true;
            }
        }

        @Override // com.a.a.b.a.c, com.a.a.b.l
        public final com.a.a.b.o g() {
            if (this.i || this.f == null) {
                return null;
            }
            int i = this.g + 1;
            this.g = i;
            if (i >= 16) {
                this.g = 0;
                this.f = this.f.a();
                if (this.f == null) {
                    return null;
                }
            }
            this.D = this.f.a(this.g);
            if (this.D == com.a.a.b.o.FIELD_NAME) {
                Object W = W();
                this.h.a(W instanceof String ? (String) W : W.toString());
            } else if (this.D == com.a.a.b.o.START_OBJECT) {
                this.h = this.h.j();
            } else if (this.D == com.a.a.b.o.START_ARRAY) {
                this.h = this.h.i();
            } else if (this.D == com.a.a.b.o.END_OBJECT || this.D == com.a.a.b.o.END_ARRAY) {
                this.h = this.h.k();
            } else {
                this.h.l();
            }
            return this.D;
        }

        @Override // com.a.a.b.l
        public final String h() {
            if (this.i || this.f == null) {
                return null;
            }
            int i = this.g + 1;
            if (i < 16 && this.f.a(i) == com.a.a.b.o.FIELD_NAME) {
                this.g = i;
                this.D = com.a.a.b.o.FIELD_NAME;
                Object b2 = this.f.b(i);
                String obj = b2 instanceof String ? (String) b2 : b2.toString();
                this.h.a(obj);
                return obj;
            }
            if (g() == com.a.a.b.o.FIELD_NAME) {
                return v();
            }
            return null;
        }

        @Override // com.a.a.b.l
        public final com.a.a.b.n d() {
            return this.h;
        }

        @Override // com.a.a.b.l
        public final com.a.a.b.j f() {
            return e();
        }

        @Override // com.a.a.b.l
        public final com.a.a.b.j e() {
            return this.k == null ? com.a.a.b.j.f174a : this.k;
        }

        @Override // com.a.a.b.l
        public final String v() {
            if (this.D == com.a.a.b.o.START_OBJECT || this.D == com.a.a.b.o.START_ARRAY) {
                return this.h.a().g();
            }
            return this.h.g();
        }

        @Override // com.a.a.b.a.c, com.a.a.b.l
        public final String u() {
            return v();
        }

        @Override // com.a.a.b.a.c, com.a.a.b.l
        public final String w() {
            if (this.D == com.a.a.b.o.VALUE_STRING || this.D == com.a.a.b.o.FIELD_NAME) {
                Object W = W();
                if (W instanceof String) {
                    return (String) W;
                }
                return i.b(W);
            }
            if (this.D == null) {
                return null;
            }
            switch (ad.f708a[this.D.ordinal()]) {
                case 7:
                case 8:
                    return i.b(W());
                default:
                    return this.D.b();
            }
        }

        @Override // com.a.a.b.l
        public final char[] x() {
            String w = w();
            if (w == null) {
                return null;
            }
            return w.toCharArray();
        }

        @Override // com.a.a.b.l
        public final int y() {
            String w = w();
            if (w == null) {
                return 0;
            }
            return w.length();
        }

        @Override // com.a.a.b.l
        public final int z() {
            return 0;
        }

        @Override // com.a.a.b.l
        public final boolean A() {
            return false;
        }

        @Override // com.a.a.b.l
        public final boolean s() {
            if (this.D == com.a.a.b.o.VALUE_NUMBER_FLOAT) {
                Object W = W();
                if (W instanceof Double) {
                    Double d = (Double) W;
                    return d.isNaN() || d.isInfinite();
                }
                if (W instanceof Float) {
                    Float f = (Float) W;
                    return f.isNaN() || f.isInfinite();
                }
                return false;
            }
            return false;
        }

        @Override // com.a.a.b.l
        public final BigInteger I() {
            Number B = B();
            if (B instanceof BigInteger) {
                return (BigInteger) B;
            }
            if (D() == l.b.BIG_DECIMAL) {
                return ((BigDecimal) B).toBigInteger();
            }
            return BigInteger.valueOf(B.longValue());
        }

        @Override // com.a.a.b.l
        public final BigDecimal L() {
            Number B = B();
            if (B instanceof BigDecimal) {
                return (BigDecimal) B;
            }
            switch (ad.f709b[D().ordinal()]) {
                case 1:
                case 5:
                    return BigDecimal.valueOf(B.longValue());
                case 2:
                    return new BigDecimal((BigInteger) B);
                case 3:
                case 4:
                default:
                    return BigDecimal.valueOf(B.doubleValue());
            }
        }

        @Override // com.a.a.b.l
        public final double K() {
            return B().doubleValue();
        }

        @Override // com.a.a.b.l
        public final float J() {
            return B().floatValue();
        }

        @Override // com.a.a.b.l
        public final int G() {
            Number B = this.D == com.a.a.b.o.VALUE_NUMBER_INT ? (Number) W() : B();
            Number number = B;
            if ((B instanceof Integer) || a(number)) {
                return number.intValue();
            }
            return c(number);
        }

        @Override // com.a.a.b.l
        public final long H() {
            Number B = this.D == com.a.a.b.o.VALUE_NUMBER_INT ? (Number) W() : B();
            Number number = B;
            if ((B instanceof Long) || b(number)) {
                return number.longValue();
            }
            return d(number);
        }

        @Override // com.a.a.b.l
        public final l.b D() {
            Number B = B();
            if (B instanceof Integer) {
                return l.b.INT;
            }
            if (B instanceof Long) {
                return l.b.LONG;
            }
            if (B instanceof Double) {
                return l.b.DOUBLE;
            }
            if (B instanceof BigDecimal) {
                return l.b.BIG_DECIMAL;
            }
            if (B instanceof BigInteger) {
                return l.b.BIG_INTEGER;
            }
            if (B instanceof Float) {
                return l.b.FLOAT;
            }
            if (B instanceof Short) {
                return l.b.INT;
            }
            return null;
        }

        @Override // com.a.a.b.l
        public final Number B() {
            X();
            Object W = W();
            if (W instanceof Number) {
                return (Number) W;
            }
            if (W instanceof String) {
                String str = (String) W;
                if (str.indexOf(46) >= 0) {
                    return Double.valueOf(com.a.a.b.c.h.b(str, a(com.a.a.b.t.USE_FAST_DOUBLE_PARSER)));
                }
                return Long.valueOf(com.a.a.b.c.h.b(str));
            }
            if (W == null) {
                return null;
            }
            throw new IllegalStateException("Internal error: entry should be a Number, but is of type " + W.getClass().getName());
        }

        private static boolean a(Number number) {
            return (number instanceof Short) || (number instanceof Byte);
        }

        private static boolean b(Number number) {
            return (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
        }

        private int c(Number number) {
            if (number instanceof Long) {
                long longValue = number.longValue();
                int i = (int) longValue;
                if (i != longValue) {
                    ai();
                }
                return i;
            }
            if (number instanceof BigInteger) {
                BigInteger bigInteger = (BigInteger) number;
                if (v.compareTo(bigInteger) > 0 || w.compareTo(bigInteger) < 0) {
                    ai();
                }
            } else {
                if ((number instanceof Double) || (number instanceof Float)) {
                    double doubleValue = number.doubleValue();
                    if (doubleValue < -2.147483648E9d || doubleValue > 2.147483647E9d) {
                        ai();
                    }
                    return (int) doubleValue;
                }
                if (number instanceof BigDecimal) {
                    BigDecimal bigDecimal = (BigDecimal) number;
                    if (B.compareTo(bigDecimal) > 0 || C.compareTo(bigDecimal) < 0) {
                        ai();
                    }
                } else {
                    al();
                }
            }
            return number.intValue();
        }

        private long d(Number number) {
            if (number instanceof BigInteger) {
                BigInteger bigInteger = (BigInteger) number;
                if (x.compareTo(bigInteger) > 0 || y.compareTo(bigInteger) < 0) {
                    aj();
                }
            } else {
                if ((number instanceof Double) || (number instanceof Float)) {
                    double doubleValue = number.doubleValue();
                    if (doubleValue < -9.223372036854776E18d || doubleValue > 9.223372036854776E18d) {
                        aj();
                    }
                    return (long) doubleValue;
                }
                if (number instanceof BigDecimal) {
                    BigDecimal bigDecimal = (BigDecimal) number;
                    if (z.compareTo(bigDecimal) > 0 || A.compareTo(bigDecimal) < 0) {
                        aj();
                    }
                } else {
                    al();
                }
            }
            return number.longValue();
        }

        @Override // com.a.a.b.l
        public final Object N() {
            if (this.D == com.a.a.b.o.VALUE_EMBEDDED_OBJECT) {
                return W();
            }
            return null;
        }

        @Override // com.a.a.b.l
        public final byte[] a(com.a.a.b.a aVar) {
            if (this.D == com.a.a.b.o.VALUE_EMBEDDED_OBJECT) {
                Object W = W();
                if (W instanceof byte[]) {
                    return (byte[]) W;
                }
            }
            if (this.D != com.a.a.b.o.VALUE_STRING) {
                throw b("Current token (" + this.D + ") not VALUE_STRING (or VALUE_EMBEDDED_OBJECT with byte[]), cannot access as binary");
            }
            String w = w();
            if (w == null) {
                return null;
            }
            com.a.a.b.g.c cVar = this.j;
            com.a.a.b.g.c cVar2 = cVar;
            if (cVar == null) {
                com.a.a.b.g.c cVar3 = new com.a.a.b.g.c(100);
                cVar2 = cVar3;
                this.j = cVar3;
            } else {
                this.j.a();
            }
            a(w, cVar2, aVar);
            return cVar2.b();
        }

        @Override // com.a.a.b.l
        public final int a(com.a.a.b.a aVar, OutputStream outputStream) {
            byte[] a2 = a(aVar);
            if (a2 != null) {
                outputStream.write(a2, 0, a2.length);
                return a2.length;
            }
            return 0;
        }

        @Override // com.a.a.b.l
        public final boolean S() {
            return this.e;
        }

        @Override // com.a.a.b.l
        public final boolean T() {
            return this.d;
        }

        @Override // com.a.a.b.l
        public final Object V() {
            return this.f.d(this.g);
        }

        @Override // com.a.a.b.l
        public final Object U() {
            return this.f.c(this.g);
        }

        private Object W() {
            return this.f.b(this.g);
        }

        private void X() {
            if (this.D == null || !this.D.d()) {
                throw b("Current token (" + this.D + ") not numeric, cannot use numeric value accessors");
            }
        }

        @Override // com.a.a.b.a.c
        protected final void Y() {
            al();
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/m/ac$b.class */
    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        private static final com.a.a.b.o[] f706a = new com.a.a.b.o[16];

        /* renamed from: b, reason: collision with root package name */
        private b f707b;
        private long c;
        private Object[] d = new Object[16];
        private TreeMap<Integer, Object> e;

        static {
            com.a.a.b.o[] values = com.a.a.b.o.values();
            System.arraycopy(values, 1, f706a, 1, Math.min(15, values.length - 1));
        }

        public final com.a.a.b.o a(int i) {
            long j = this.c;
            if (i > 0) {
                j >>= i << 2;
            }
            return f706a[((int) j) & 15];
        }

        public final Object b(int i) {
            return this.d[i];
        }

        public final b a() {
            return this.f707b;
        }

        public final boolean b() {
            return this.e != null;
        }

        public final b a(int i, com.a.a.b.o oVar) {
            if (i < 16) {
                b(i, oVar);
                return null;
            }
            this.f707b = new b();
            this.f707b.b(0, oVar);
            return this.f707b;
        }

        public final b a(int i, com.a.a.b.o oVar, Object obj, Object obj2) {
            if (i < 16) {
                b(i, oVar, obj, obj2);
                return null;
            }
            this.f707b = new b();
            this.f707b.b(0, oVar, obj, obj2);
            return this.f707b;
        }

        public final b a(int i, com.a.a.b.o oVar, Object obj) {
            if (i < 16) {
                b(i, oVar, obj);
                return null;
            }
            this.f707b = new b();
            this.f707b.b(0, oVar, obj);
            return this.f707b;
        }

        public final b a(int i, com.a.a.b.o oVar, Object obj, Object obj2, Object obj3) {
            if (i < 16) {
                b(i, oVar, obj, obj2, obj3);
                return null;
            }
            this.f707b = new b();
            this.f707b.b(0, oVar, obj, obj2, obj3);
            return this.f707b;
        }

        private void b(int i, com.a.a.b.o oVar) {
            long ordinal = oVar.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            this.c |= ordinal;
        }

        private void b(int i, com.a.a.b.o oVar, Object obj, Object obj2) {
            long ordinal = oVar.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            this.c |= ordinal;
            a(i, obj, obj2);
        }

        private void b(int i, com.a.a.b.o oVar, Object obj) {
            this.d[i] = obj;
            long ordinal = oVar.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            this.c |= ordinal;
        }

        private void b(int i, com.a.a.b.o oVar, Object obj, Object obj2, Object obj3) {
            this.d[i] = obj;
            long ordinal = oVar.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            this.c |= ordinal;
            a(i, obj2, obj3);
        }

        private final void a(int i, Object obj, Object obj2) {
            if (this.e == null) {
                this.e = new TreeMap<>();
            }
            if (obj != null) {
                this.e.put(Integer.valueOf(f(i)), obj);
            }
            if (obj2 != null) {
                this.e.put(Integer.valueOf(e(i)), obj2);
            }
        }

        final Object c(int i) {
            if (this.e == null) {
                return null;
            }
            return this.e.get(Integer.valueOf(f(i)));
        }

        final Object d(int i) {
            if (this.e == null) {
                return null;
            }
            return this.e.get(Integer.valueOf(e(i)));
        }

        private static int e(int i) {
            return i + i;
        }

        private static int f(int i) {
            return i + i + 1;
        }
    }
}
