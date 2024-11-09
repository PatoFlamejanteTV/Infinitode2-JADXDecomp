package com.a.a.b.d;

import com.a.a.b.h;
import com.a.a.b.n;
import com.a.a.b.p;
import com.a.a.b.r;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: infinitode-2.jar:com/a/a/b/d/i.class */
public final class i extends c {
    private static char[] l = com.a.a.b.c.b.a(true);
    private static char[] m = com.a.a.b.c.b.a(false);
    private Writer n;
    private char o;
    private char[] p;
    private int q;
    private int r;
    private int s;
    private char[] t;
    private r u;
    private char[] v;

    private char[] p() {
        return this.k ? l : m;
    }

    public i(com.a.a.b.c.a aVar, int i, p pVar, Writer writer, char c) {
        super(aVar, i, pVar);
        this.n = writer;
        this.p = aVar.h();
        this.s = this.p.length;
        this.o = c;
        if (c != '\"') {
            this.f = com.a.a.b.c.b.a(c);
        }
    }

    @Override // com.a.a.b.h
    public final void a(String str) {
        int a2 = this.d.a(str);
        if (a2 == 4) {
            f("Can not write a field name, expecting a value");
        }
        a(str, a2 == 1);
    }

    @Override // com.a.a.b.a.a, com.a.a.b.h
    public final void b(r rVar) {
        int a2 = this.d.a(rVar.a());
        if (a2 == 4) {
            f("Can not write a field name, expecting a value");
        }
        a(rVar, a2 == 1);
    }

    private void a(String str, boolean z) {
        if (this.f170b != null) {
            b(str, z);
            return;
        }
        if (this.r + 1 >= this.s) {
            s();
        }
        if (z) {
            char[] cArr = this.p;
            int i = this.r;
            this.r = i + 1;
            cArr[i] = ',';
        }
        if (this.j) {
            k(str);
            return;
        }
        char[] cArr2 = this.p;
        int i2 = this.r;
        this.r = i2 + 1;
        cArr2[i2] = this.o;
        k(str);
        if (this.r >= this.s) {
            s();
        }
        char[] cArr3 = this.p;
        int i3 = this.r;
        this.r = i3 + 1;
        cArr3[i3] = this.o;
    }

    private void a(r rVar, boolean z) {
        if (this.f170b != null) {
            b(rVar, z);
            return;
        }
        if (this.r + 1 >= this.s) {
            s();
        }
        if (z) {
            char[] cArr = this.p;
            int i = this.r;
            this.r = i + 1;
            cArr[i] = ',';
        }
        if (this.j) {
            char[] b2 = rVar.b();
            b(b2, 0, b2.length);
            return;
        }
        char[] cArr2 = this.p;
        int i2 = this.r;
        this.r = i2 + 1;
        cArr2[i2] = this.o;
        int a2 = rVar.a(this.p, this.r);
        if (a2 < 0) {
            f(rVar);
            return;
        }
        this.r += a2;
        if (this.r >= this.s) {
            s();
        }
        char[] cArr3 = this.p;
        int i3 = this.r;
        this.r = i3 + 1;
        cArr3[i3] = this.o;
    }

    private final void f(r rVar) {
        char[] b2 = rVar.b();
        b(b2, 0, b2.length);
        if (this.r >= this.s) {
            s();
        }
        char[] cArr = this.p;
        int i = this.r;
        this.r = i + 1;
        cArr[i] = this.o;
    }

    @Override // com.a.a.b.h
    public final void g() {
        g("start an array");
        this.d = this.d.i();
        if (this.f170b != null) {
            this.f170b.e(this);
            return;
        }
        if (this.r >= this.s) {
            s();
        }
        char[] cArr = this.p;
        int i = this.r;
        this.r = i + 1;
        cArr[i] = '[';
    }

    @Override // com.a.a.b.h
    public final void b(Object obj) {
        g("start an array");
        this.d = this.d.b(obj);
        if (this.f170b != null) {
            this.f170b.e(this);
            return;
        }
        if (this.r >= this.s) {
            s();
        }
        char[] cArr = this.p;
        int i = this.r;
        this.r = i + 1;
        cArr[i] = '[';
    }

    @Override // com.a.a.b.h
    public final void a(Object obj, int i) {
        g("start an array");
        this.d = this.d.b(obj);
        if (this.f170b != null) {
            this.f170b.e(this);
            return;
        }
        if (this.r >= this.s) {
            s();
        }
        char[] cArr = this.p;
        int i2 = this.r;
        this.r = i2 + 1;
        cArr[i2] = '[';
    }

    @Override // com.a.a.b.h
    public final void h() {
        if (!this.d.b()) {
            f("Current context not Array but " + this.d.e());
        }
        if (this.f170b != null) {
            this.f170b.b(this, this.d.f());
        } else {
            if (this.r >= this.s) {
                s();
            }
            char[] cArr = this.p;
            int i = this.r;
            this.r = i + 1;
            cArr[i] = ']';
        }
        this.d = this.d.l();
    }

    @Override // com.a.a.b.h
    public final void i() {
        g("start an object");
        this.d = this.d.j();
        if (this.f170b != null) {
            this.f170b.b(this);
            return;
        }
        if (this.r >= this.s) {
            s();
        }
        char[] cArr = this.p;
        int i = this.r;
        this.r = i + 1;
        cArr[i] = '{';
    }

    @Override // com.a.a.b.a.a, com.a.a.b.h
    public final void c(Object obj) {
        g("start an object");
        this.d = this.d.c(obj);
        if (this.f170b != null) {
            this.f170b.b(this);
            return;
        }
        if (this.r >= this.s) {
            s();
        }
        char[] cArr = this.p;
        int i = this.r;
        this.r = i + 1;
        cArr[i] = '{';
    }

    @Override // com.a.a.b.h
    public final void d(Object obj) {
        c(obj);
    }

    @Override // com.a.a.b.h
    public final void j() {
        if (!this.d.d()) {
            f("Current context not Object but " + this.d.e());
        }
        if (this.f170b != null) {
            this.f170b.a(this, this.d.f());
        } else {
            if (this.r >= this.s) {
                s();
            }
            char[] cArr = this.p;
            int i = this.r;
            this.r = i + 1;
            cArr[i] = '}';
        }
        this.d = this.d.l();
    }

    private void b(String str, boolean z) {
        if (z) {
            this.f170b.c(this);
        } else {
            this.f170b.h(this);
        }
        if (this.j) {
            k(str);
            return;
        }
        if (this.r >= this.s) {
            s();
        }
        char[] cArr = this.p;
        int i = this.r;
        this.r = i + 1;
        cArr[i] = this.o;
        k(str);
        if (this.r >= this.s) {
            s();
        }
        char[] cArr2 = this.p;
        int i2 = this.r;
        this.r = i2 + 1;
        cArr2[i2] = this.o;
    }

    private void b(r rVar, boolean z) {
        if (z) {
            this.f170b.c(this);
        } else {
            this.f170b.h(this);
        }
        char[] b2 = rVar.b();
        if (this.j) {
            b(b2, 0, b2.length);
            return;
        }
        if (this.r >= this.s) {
            s();
        }
        char[] cArr = this.p;
        int i = this.r;
        this.r = i + 1;
        cArr[i] = this.o;
        b(b2, 0, b2.length);
        if (this.r >= this.s) {
            s();
        }
        char[] cArr2 = this.p;
        int i2 = this.r;
        this.r = i2 + 1;
        cArr2[i2] = this.o;
    }

    @Override // com.a.a.b.h
    public final void b(String str) {
        g("write a string");
        if (str == null) {
            q();
            return;
        }
        if (this.r >= this.s) {
            s();
        }
        char[] cArr = this.p;
        int i = this.r;
        this.r = i + 1;
        cArr[i] = this.o;
        k(str);
        if (this.r >= this.s) {
            s();
        }
        char[] cArr2 = this.p;
        int i2 = this.r;
        this.r = i2 + 1;
        cArr2[i2] = this.o;
    }

    @Override // com.a.a.b.h
    public final void a(char[] cArr, int i, int i2) {
        g("write a string");
        if (this.r >= this.s) {
            s();
        }
        char[] cArr2 = this.p;
        int i3 = this.r;
        this.r = i3 + 1;
        cArr2[i3] = this.o;
        d(cArr, i, i2);
        if (this.r >= this.s) {
            s();
        }
        char[] cArr3 = this.p;
        int i4 = this.r;
        this.r = i4 + 1;
        cArr3[i4] = this.o;
    }

    @Override // com.a.a.b.a.a, com.a.a.b.h
    public final void c(r rVar) {
        g("write a string");
        if (this.r >= this.s) {
            s();
        }
        char[] cArr = this.p;
        int i = this.r;
        this.r = i + 1;
        cArr[i] = this.o;
        int a2 = rVar.a(this.p, this.r);
        if (a2 < 0) {
            g(rVar);
            return;
        }
        this.r += a2;
        if (this.r >= this.s) {
            s();
        }
        char[] cArr2 = this.p;
        int i2 = this.r;
        this.r = i2 + 1;
        cArr2[i2] = this.o;
    }

    private void g(r rVar) {
        char[] b2 = rVar.b();
        int length = b2.length;
        if (length < 32) {
            if (length > this.s - this.r) {
                s();
            }
            System.arraycopy(b2, 0, this.p, this.r, length);
            this.r += length;
        } else {
            s();
            this.n.write(b2, 0, length);
        }
        if (this.r >= this.s) {
            s();
        }
        char[] cArr = this.p;
        int i = this.r;
        this.r = i + 1;
        cArr[i] = this.o;
    }

    @Override // com.a.a.b.h
    public final void c(String str) {
        int length = str.length();
        int i = this.s - this.r;
        int i2 = i;
        if (i == 0) {
            s();
            i2 = this.s - this.r;
        }
        if (i2 >= length) {
            str.getChars(0, length, this.p, this.r);
            this.r += length;
        } else {
            i(str);
        }
    }

    @Override // com.a.a.b.h
    public final void d(r rVar) {
        int b2 = rVar.b(this.p, this.r);
        if (b2 < 0) {
            c(rVar.a());
        } else {
            this.r += b2;
        }
    }

    @Override // com.a.a.b.h
    public final void b(char[] cArr, int i, int i2) {
        c(cArr, 0, i2);
        if (i2 < 32) {
            if (i2 > this.s - this.r) {
                s();
            }
            System.arraycopy(cArr, 0, this.p, this.r, i2);
            this.r += i2;
            return;
        }
        s();
        this.n.write(cArr, 0, i2);
    }

    @Override // com.a.a.b.h
    public final void a(char c) {
        if (this.r >= this.s) {
            s();
        }
        char[] cArr = this.p;
        int i = this.r;
        this.r = i + 1;
        cArr[i] = c;
    }

    private void i(String str) {
        int i = this.s - this.r;
        str.getChars(0, i, this.p, this.r);
        this.r += i;
        s();
        int i2 = i;
        int length = str.length();
        int i3 = i;
        while (true) {
            int i4 = length - i3;
            if (i4 > this.s) {
                int i5 = this.s;
                int i6 = i2;
                str.getChars(i6, i6 + i5, this.p, 0);
                this.q = 0;
                this.r = i5;
                s();
                i2 += i5;
                length = i4;
                i3 = i5;
            } else {
                int i7 = i2;
                str.getChars(i7, i7 + i4, this.p, 0);
                this.q = 0;
                this.r = i4;
                return;
            }
        }
    }

    @Override // com.a.a.b.h
    public final void a(com.a.a.b.a aVar, byte[] bArr, int i, int i2) {
        b(bArr, i, i2);
        g("write a binary value");
        if (this.r >= this.s) {
            s();
        }
        char[] cArr = this.p;
        int i3 = this.r;
        this.r = i3 + 1;
        cArr[i3] = this.o;
        b(aVar, bArr, i, i + i2);
        if (this.r >= this.s) {
            s();
        }
        char[] cArr2 = this.p;
        int i4 = this.r;
        this.r = i4 + 1;
        cArr2[i4] = this.o;
    }

    @Override // com.a.a.b.a.a, com.a.a.b.h
    public final int a(com.a.a.b.a aVar, InputStream inputStream, int i) {
        int i2;
        g("write a binary value");
        if (this.r >= this.s) {
            s();
        }
        char[] cArr = this.p;
        int i3 = this.r;
        this.r = i3 + 1;
        cArr[i3] = this.o;
        byte[] f = this.e.f();
        try {
            if (i < 0) {
                i2 = a(aVar, inputStream, f);
            } else {
                int a2 = a(aVar, inputStream, f, i);
                if (a2 > 0) {
                    f("Too few bytes available: missing " + a2 + " bytes (out of " + i + ")");
                }
                i2 = i;
            }
            if (this.r >= this.s) {
                s();
            }
            char[] cArr2 = this.p;
            int i4 = this.r;
            this.r = i4 + 1;
            cArr2[i4] = this.o;
            return i2;
        } finally {
            this.e.b(f);
        }
    }

    @Override // com.a.a.b.h
    public final void a(short s) {
        g("write a number");
        if (this.c) {
            b(s);
            return;
        }
        if (this.r + 6 >= this.s) {
            s();
        }
        this.r = com.a.a.b.c.i.a((int) s, this.p, this.r);
    }

    private void b(short s) {
        if (this.r + 8 >= this.s) {
            s();
        }
        char[] cArr = this.p;
        int i = this.r;
        this.r = i + 1;
        cArr[i] = this.o;
        this.r = com.a.a.b.c.i.a((int) s, this.p, this.r);
        char[] cArr2 = this.p;
        int i2 = this.r;
        this.r = i2 + 1;
        cArr2[i2] = this.o;
    }

    @Override // com.a.a.b.h
    public final void c(int i) {
        g("write a number");
        if (this.c) {
            d(i);
            return;
        }
        if (this.r + 11 >= this.s) {
            s();
        }
        this.r = com.a.a.b.c.i.a(i, this.p, this.r);
    }

    private void d(int i) {
        if (this.r + 13 >= this.s) {
            s();
        }
        char[] cArr = this.p;
        int i2 = this.r;
        this.r = i2 + 1;
        cArr[i2] = this.o;
        this.r = com.a.a.b.c.i.a(i, this.p, this.r);
        char[] cArr2 = this.p;
        int i3 = this.r;
        this.r = i3 + 1;
        cArr2[i3] = this.o;
    }

    @Override // com.a.a.b.h
    public final void b(long j) {
        g("write a number");
        if (this.c) {
            c(j);
            return;
        }
        if (this.r + 21 >= this.s) {
            s();
        }
        this.r = com.a.a.b.c.i.a(j, this.p, this.r);
    }

    private void c(long j) {
        if (this.r + 23 >= this.s) {
            s();
        }
        char[] cArr = this.p;
        int i = this.r;
        this.r = i + 1;
        cArr[i] = this.o;
        this.r = com.a.a.b.c.i.a(j, this.p, this.r);
        char[] cArr2 = this.p;
        int i2 = this.r;
        this.r = i2 + 1;
        cArr2[i2] = this.o;
    }

    @Override // com.a.a.b.h
    public final void a(BigInteger bigInteger) {
        g("write a number");
        if (bigInteger == null) {
            q();
        } else if (this.c) {
            j(bigInteger.toString());
        } else {
            c(bigInteger.toString());
        }
    }

    @Override // com.a.a.b.h
    public final void a(double d) {
        if (this.c || (com.a.a.b.c.i.b(d) && b(h.a.QUOTE_NON_NUMERIC_NUMBERS))) {
            b(com.a.a.b.c.i.a(d, b(h.a.USE_FAST_DOUBLE_WRITER)));
        } else {
            g("write a number");
            c(com.a.a.b.c.i.a(d, b(h.a.USE_FAST_DOUBLE_WRITER)));
        }
    }

    @Override // com.a.a.b.h
    public final void a(float f) {
        if (this.c || (com.a.a.b.c.i.b(f) && b(h.a.QUOTE_NON_NUMERIC_NUMBERS))) {
            b(com.a.a.b.c.i.a(f, b(h.a.USE_FAST_DOUBLE_WRITER)));
        } else {
            g("write a number");
            c(com.a.a.b.c.i.a(f, b(h.a.USE_FAST_DOUBLE_WRITER)));
        }
    }

    @Override // com.a.a.b.h
    public final void a(BigDecimal bigDecimal) {
        g("write a number");
        if (bigDecimal == null) {
            q();
        } else if (this.c) {
            j(b(bigDecimal));
        } else {
            c(b(bigDecimal));
        }
    }

    @Override // com.a.a.b.h
    public final void e(String str) {
        g("write a number");
        if (str == null) {
            q();
        } else if (this.c) {
            j(str);
        } else {
            c(str);
        }
    }

    private void j(String str) {
        if (this.r >= this.s) {
            s();
        }
        char[] cArr = this.p;
        int i = this.r;
        this.r = i + 1;
        cArr[i] = this.o;
        c(str);
        if (this.r >= this.s) {
            s();
        }
        char[] cArr2 = this.p;
        int i2 = this.r;
        this.r = i2 + 1;
        cArr2[i2] = this.o;
    }

    @Override // com.a.a.b.h
    public final void a(boolean z) {
        int i;
        g("write a boolean value");
        if (this.r + 5 >= this.s) {
            s();
        }
        int i2 = this.r;
        char[] cArr = this.p;
        if (z) {
            cArr[i2] = 't';
            int i3 = i2 + 1;
            cArr[i3] = 'r';
            int i4 = i3 + 1;
            cArr[i4] = 'u';
            i = i4 + 1;
            cArr[i] = 'e';
        } else {
            cArr[i2] = 'f';
            int i5 = i2 + 1;
            cArr[i5] = 'a';
            int i6 = i5 + 1;
            cArr[i6] = 'l';
            int i7 = i6 + 1;
            cArr[i7] = 's';
            i = i7 + 1;
            cArr[i] = 'e';
        }
        this.r = i + 1;
    }

    @Override // com.a.a.b.h
    public final void k() {
        g("write a null");
        q();
    }

    @Override // com.a.a.b.a.a
    protected final void g(String str) {
        char c;
        int n = this.d.n();
        if (this.f170b != null) {
            a(str, n);
            return;
        }
        switch (n) {
            case 1:
                c = ',';
                break;
            case 2:
                c = ':';
                break;
            case 3:
                if (this.i != null) {
                    c(this.i.a());
                    return;
                }
                return;
            case 4:
            default:
                return;
            case 5:
                h(str);
                return;
        }
        if (this.r >= this.s) {
            s();
        }
        char[] cArr = this.p;
        int i = this.r;
        this.r = i + 1;
        cArr[i] = c;
    }

    @Override // com.a.a.b.h, java.io.Flushable
    public final void flush() {
        s();
        if (this.n != null && b(h.a.FLUSH_PASSED_TO_STREAM)) {
            this.n.flush();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v23, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.Throwable, java.lang.Exception] */
    @Override // com.a.a.b.a.a, com.a.a.b.h, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        super.close();
        i iVar = null;
        i iVar2 = null;
        try {
            if (this.p != null && b(h.a.AUTO_CLOSE_JSON_CONTENT)) {
                while (true) {
                    n a2 = a();
                    if (a2.b()) {
                        h();
                    } else if (!a2.d()) {
                        break;
                    } else {
                        j();
                    }
                }
            }
            iVar = this;
            iVar.s();
        } catch (IOException e) {
            iVar2 = iVar;
        }
        this.q = 0;
        this.r = 0;
        if (this.n != null) {
            try {
                if (this.e.b() || b(h.a.AUTO_CLOSE_TARGET)) {
                    this.n.close();
                } else if (b(h.a.FLUSH_PASSED_TO_STREAM)) {
                    this.n.flush();
                }
            } catch (IOException | RuntimeException e2) {
                if (iVar2 != null) {
                    e2.addSuppressed(iVar2);
                }
                throw e2;
            }
        }
        o();
        if (iVar2 != null) {
            throw iVar2;
        }
    }

    @Override // com.a.a.b.a.a
    protected final void o() {
        char[] cArr = this.p;
        if (cArr != null) {
            this.p = null;
            this.e.b(cArr);
        }
        char[] cArr2 = this.v;
        if (cArr2 != null) {
            this.v = null;
            this.e.c(cArr2);
        }
    }

    private void k(String str) {
        int length = str.length();
        if (length <= this.s) {
            if (this.r + length > this.s) {
                s();
            }
            str.getChars(0, length, this.p, this.r);
            if (this.h != null) {
                g(length);
                return;
            } else if (this.g != 0) {
                c(length, this.g);
                return;
            } else {
                e(length);
                return;
            }
        }
        l(str);
    }

    private void e(int i) {
        int i2;
        int i3 = this.r + i;
        int[] iArr = this.f;
        int length = iArr.length;
        while (this.r < i3) {
            do {
                char c = this.p[this.r];
                if (c >= length || iArr[c] == 0) {
                    i2 = this.r + 1;
                    this.r = i2;
                } else {
                    int i4 = this.r - this.q;
                    if (i4 > 0) {
                        this.n.write(this.p, this.q, i4);
                    }
                    char[] cArr = this.p;
                    int i5 = this.r;
                    this.r = i5 + 1;
                    char c2 = cArr[i5];
                    a(c2, iArr[c2]);
                }
            } while (i2 < i3);
            return;
        }
    }

    private void l(String str) {
        int i;
        s();
        int length = str.length();
        int i2 = 0;
        do {
            int i3 = this.s;
            int i4 = i2 + i3 > length ? length - i2 : i3;
            int i5 = i2;
            str.getChars(i5, i5 + i4, this.p, 0);
            if (this.h != null) {
                h(i4);
            } else if (this.g != 0) {
                d(i4, this.g);
            } else {
                f(i4);
            }
            i = i2 + i4;
            i2 = i;
        } while (i < length);
    }

    private void f(int i) {
        char c;
        int[] iArr = this.f;
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i2 >= i) {
                return;
            }
            do {
                c = this.p[i2];
                if (c < length && iArr[c] != 0) {
                    break;
                } else {
                    i2++;
                }
            } while (i2 < i);
            int i5 = i2 - i4;
            if (i5 > 0) {
                this.n.write(this.p, i4, i5);
                if (i2 >= i) {
                    return;
                }
            }
            i2++;
            i3 = a(this.p, i2, i, c, iArr[c]);
        }
    }

    private void d(char[] cArr, int i, int i2) {
        if (this.h != null) {
            e(cArr, i, i2);
            return;
        }
        if (this.g != 0) {
            a(cArr, i, i2, this.g);
            return;
        }
        int i3 = i2 + i;
        int[] iArr = this.f;
        int length = iArr.length;
        while (i < i3) {
            int i4 = i;
            do {
                char c = cArr[i];
                if (c < length && iArr[c] != 0) {
                    break;
                } else {
                    i++;
                }
            } while (i < i3);
            int i5 = i - i4;
            if (i5 < 32) {
                if (this.r + i5 > this.s) {
                    s();
                }
                if (i5 > 0) {
                    System.arraycopy(cArr, i4, this.p, this.r, i5);
                    this.r += i5;
                }
            } else {
                s();
                this.n.write(cArr, i4, i5);
            }
            if (i < i3) {
                int i6 = i;
                i++;
                char c2 = cArr[i6];
                b(c2, iArr[c2]);
            } else {
                return;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0078 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void c(int r6, int r7) {
        /*
            r5 = this;
            r0 = r5
            int r0 = r0.r
            r1 = r6
            int r0 = r0 + r1
            r6 = r0
            r0 = r5
            int[] r0 = r0.f
            r1 = r0
            r8 = r1
            int r0 = r0.length
            r1 = r7
            r2 = 1
            int r1 = r1 + r2
            int r0 = java.lang.Math.min(r0, r1)
            r9 = r0
        L16:
            r0 = r5
            int r0 = r0.r
            r1 = r6
            if (r0 >= r1) goto L8d
        L1e:
            r0 = r5
            char[] r0 = r0.p
            r1 = r5
            int r1 = r1.r
            char r0 = r0[r1]
            r1 = r0
            r11 = r1
            r1 = r9
            if (r0 >= r1) goto L3c
            r0 = r8
            r1 = r11
            r0 = r0[r1]
            r1 = r0
            r10 = r1
            if (r0 == 0) goto L48
            goto L58
        L3c:
            r0 = r11
            r1 = r7
            if (r0 <= r1) goto L48
            r0 = -1
            r10 = r0
            goto L58
        L48:
            r0 = r5
            r1 = r0
            int r1 = r1.r
            r2 = 1
            int r1 = r1 + r2
            r2 = r1; r1 = r0; r0 = r2; 
            r1.r = r2
            r1 = r6
            if (r0 < r1) goto L1e
            return
        L58:
            r0 = r5
            int r0 = r0.r
            r1 = r5
            int r1 = r1.q
            int r0 = r0 - r1
            r1 = r0
            r12 = r1
            if (r0 <= 0) goto L78
            r0 = r5
            java.io.Writer r0 = r0.n
            r1 = r5
            char[] r1 = r1.p
            r2 = r5
            int r2 = r2.q
            r3 = r12
            r0.write(r1, r2, r3)
        L78:
            r0 = r5
            r1 = r0
            int r1 = r1.r
            r2 = 1
            int r1 = r1 + r2
            r0.r = r1
            r0 = r5
            r1 = r11
            r2 = r10
            r0.a(r1, r2)
            goto L16
        L8d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.b.d.i.c(int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x004f A[EDGE_INSN: B:10:0x004f->B:11:0x004f BREAK  A[LOOP:1: B:4:0x001e->B:21:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[LOOP:1: B:4:0x001e->B:21:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void d(int r8, int r9) {
        /*
            r7 = this;
            r0 = r7
            int[] r0 = r0.f
            r1 = r0
            r10 = r1
            int r0 = r0.length
            r1 = r9
            r2 = 1
            int r1 = r1 + r2
            int r0 = java.lang.Math.min(r0, r1)
            r11 = r0
            r0 = 0
            r12 = r0
            r0 = 0
            r13 = r0
            r0 = 0
            r14 = r0
        L18:
            r0 = r12
            r1 = r8
            if (r0 >= r1) goto L86
        L1e:
            r0 = r7
            char[] r0 = r0.p
            r1 = r12
            char r0 = r0[r1]
            r1 = r0
            r15 = r1
            r1 = r11
            if (r0 >= r1) goto L3a
            r0 = r10
            r1 = r15
            r0 = r0[r1]
            r1 = r0
            r13 = r1
            if (r0 == 0) goto L46
            goto L4f
        L3a:
            r0 = r15
            r1 = r9
            if (r0 <= r1) goto L46
            r0 = -1
            r13 = r0
            goto L4f
        L46:
            int r12 = r12 + 1
            r0 = r12
            r1 = r8
            if (r0 < r1) goto L1e
        L4f:
            r0 = r12
            r1 = r14
            int r0 = r0 - r1
            r1 = r0
            r16 = r1
            if (r0 <= 0) goto L6f
            r0 = r7
            java.io.Writer r0 = r0.n
            r1 = r7
            char[] r1 = r1.p
            r2 = r14
            r3 = r16
            r0.write(r1, r2, r3)
            r0 = r12
            r1 = r8
            if (r0 >= r1) goto L86
        L6f:
            int r12 = r12 + 1
            r0 = r7
            r1 = r0
            char[] r1 = r1.p
            r2 = r12
            r3 = r8
            r4 = r15
            r5 = r13
            int r0 = r0.a(r1, r2, r3, r4, r5)
            r14 = r0
            goto L18
        L86:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.b.d.i.d(int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x004e A[EDGE_INSN: B:11:0x004e->B:12:0x004e BREAK  A[LOOP:1: B:5:0x0020->B:27:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[LOOP:1: B:5:0x0020->B:27:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(char[] r7, int r8, int r9, int r10) {
        /*
            r6 = this;
            r0 = r9
            r1 = r8
            int r0 = r0 + r1
            r9 = r0
            r0 = r6
            int[] r0 = r0.f
            r1 = r0
            r11 = r1
            int r0 = r0.length
            r1 = r10
            r2 = 1
            int r1 = r1 + r2
            int r0 = java.lang.Math.min(r0, r1)
            r12 = r0
            r0 = 0
            r13 = r0
        L18:
            r0 = r8
            r1 = r9
            if (r0 >= r1) goto Lb2
            r0 = r8
            r14 = r0
        L20:
            r0 = r7
            r1 = r8
            char r0 = r0[r1]
            r1 = r0
            r15 = r1
            r1 = r12
            if (r0 >= r1) goto L39
            r0 = r11
            r1 = r15
            r0 = r0[r1]
            r1 = r0
            r13 = r1
            if (r0 == 0) goto L46
            goto L4e
        L39:
            r0 = r15
            r1 = r10
            if (r0 <= r1) goto L46
            r0 = -1
            r13 = r0
            goto L4e
        L46:
            int r8 = r8 + 1
            r0 = r8
            r1 = r9
            if (r0 < r1) goto L20
        L4e:
            r0 = r8
            r1 = r14
            int r0 = r0 - r1
            r1 = r0
            r16 = r1
            r1 = 32
            if (r0 >= r1) goto L8f
            r0 = r6
            int r0 = r0.r
            r1 = r16
            int r0 = r0 + r1
            r1 = r6
            int r1 = r1.s
            if (r0 <= r1) goto L6c
            r0 = r6
            r0.s()
        L6c:
            r0 = r16
            if (r0 <= 0) goto L9f
            r0 = r7
            r1 = r14
            r2 = r6
            char[] r2 = r2.p
            r3 = r6
            int r3 = r3.r
            r4 = r16
            java.lang.System.arraycopy(r0, r1, r2, r3, r4)
            r0 = r6
            r1 = r0
            int r1 = r1.r
            r2 = r16
            int r1 = r1 + r2
            r0.r = r1
            goto L9f
        L8f:
            r0 = r6
            r0.s()
            r0 = r6
            java.io.Writer r0 = r0.n
            r1 = r7
            r2 = r14
            r3 = r16
            r0.write(r1, r2, r3)
        L9f:
            r0 = r8
            r1 = r9
            if (r0 >= r1) goto Lb2
            int r8 = r8 + 1
            r0 = r6
            r1 = r15
            r2 = r13
            r0.b(r1, r2)
            goto L18
        Lb2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.b.d.i.a(char[], int, int, int):void");
    }

    private void g(int i) {
        int i2;
        int i3;
        int i4 = this.r + i;
        int[] iArr = this.f;
        int i5 = this.g <= 0 ? 65535 : this.g;
        int min = Math.min(iArr.length, i5 + 1);
        com.a.a.b.c.c cVar = this.h;
        while (this.r < i4) {
            do {
                char c = this.p[this.r];
                if (c < min) {
                    int i6 = iArr[c];
                    i2 = i6;
                    if (i6 == 0) {
                        i3 = this.r + 1;
                        this.r = i3;
                    }
                } else {
                    if (c <= i5) {
                        throw null;
                    }
                    i2 = -1;
                }
                int i7 = this.r - this.q;
                if (i7 > 0) {
                    this.n.write(this.p, this.q, i7);
                }
                this.r++;
                a(c, i2);
            } while (i3 < i4);
            return;
        }
    }

    private void h(int i) {
        char c;
        int i2;
        int[] iArr = this.f;
        int i3 = this.g <= 0 ? 65535 : this.g;
        int min = Math.min(iArr.length, i3 + 1);
        com.a.a.b.c.c cVar = this.h;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            int i6 = i5;
            if (i4 >= i) {
                return;
            }
            while (true) {
                c = this.p[i4];
                if (c < min) {
                    int i7 = iArr[c];
                    i2 = i7;
                    if (i7 != 0) {
                        break;
                    }
                    i4++;
                    if (i4 >= i) {
                        break;
                    }
                } else {
                    if (c <= i3) {
                        throw null;
                    }
                    i2 = -1;
                }
            }
            int i8 = i4 - i6;
            if (i8 > 0) {
                this.n.write(this.p, i6, i8);
                if (i4 >= i) {
                    return;
                }
            }
            i4++;
            i5 = a(this.p, i4, i, c, i2);
        }
    }

    private void e(char[] cArr, int i, int i2) {
        char c;
        int i3;
        int i4 = i2 + i;
        int[] iArr = this.f;
        int i5 = this.g <= 0 ? 65535 : this.g;
        int min = Math.min(iArr.length, i5 + 1);
        com.a.a.b.c.c cVar = this.h;
        while (i < i4) {
            int i6 = i;
            while (true) {
                c = cArr[i];
                if (c < min) {
                    int i7 = iArr[c];
                    i3 = i7;
                    if (i7 != 0) {
                        break;
                    }
                    i++;
                    if (i >= i4) {
                        break;
                    }
                } else {
                    if (c <= i5) {
                        throw null;
                    }
                    i3 = -1;
                }
            }
            int i8 = i - i6;
            if (i8 < 32) {
                if (this.r + i8 > this.s) {
                    s();
                }
                if (i8 > 0) {
                    System.arraycopy(cArr, i6, this.p, this.r, i8);
                    this.r += i8;
                }
            } else {
                s();
                this.n.write(cArr, i6, i8);
            }
            if (i < i4) {
                i++;
                b(c, i3);
            } else {
                return;
            }
        }
    }

    private void b(com.a.a.b.a aVar, byte[] bArr, int i, int i2) {
        int i3 = i2 - 3;
        int i4 = this.s - 6;
        int c = aVar.c() >> 2;
        while (i <= i3) {
            if (this.r > i4) {
                s();
            }
            int i5 = i;
            int i6 = i + 1;
            int i7 = i6 + 1;
            int i8 = ((bArr[i5] << 8) | (bArr[i6] & 255)) << 8;
            i = i7 + 1;
            this.r = aVar.a(i8 | (bArr[i7] & 255), this.p, this.r);
            c--;
            if (c <= 0) {
                char[] cArr = this.p;
                int i9 = this.r;
                this.r = i9 + 1;
                cArr[i9] = '\\';
                char[] cArr2 = this.p;
                int i10 = this.r;
                this.r = i10 + 1;
                cArr2[i10] = 'n';
                c = aVar.c() >> 2;
            }
        }
        int i11 = i2 - i;
        if (i11 > 0) {
            if (this.r > i4) {
                s();
            }
            int i12 = i;
            int i13 = i + 1;
            int i14 = bArr[i12] << 16;
            if (i11 == 2) {
                i14 |= (bArr[i13] & 255) << 8;
            }
            this.r = aVar.a(i14, i11, this.p, this.r);
        }
    }

    private int a(com.a.a.b.a aVar, InputStream inputStream, byte[] bArr, int i) {
        int a2;
        int i2;
        int i3 = 0;
        int i4 = 0;
        int i5 = -3;
        int i6 = this.s - 6;
        int c = aVar.c() >> 2;
        while (i > 2) {
            if (i3 > i5) {
                i4 = a(inputStream, bArr, i3, i4, i);
                i3 = 0;
                if (i4 < 3) {
                    break;
                }
                i5 = i4 - 3;
            }
            if (this.r > i6) {
                s();
            }
            int i7 = i3;
            int i8 = i3 + 1;
            int i9 = i8 + 1;
            int i10 = ((bArr[i7] << 8) | (bArr[i8] & 255)) << 8;
            i3 = i9 + 1;
            i -= 3;
            this.r = aVar.a(i10 | (bArr[i9] & 255), this.p, this.r);
            c--;
            if (c <= 0) {
                char[] cArr = this.p;
                int i11 = this.r;
                this.r = i11 + 1;
                cArr[i11] = '\\';
                char[] cArr2 = this.p;
                int i12 = this.r;
                this.r = i12 + 1;
                cArr2[i12] = 'n';
                c = aVar.c() >> 2;
            }
        }
        if (i > 0 && (a2 = a(inputStream, bArr, i3, i4, i)) > 0) {
            if (this.r > i6) {
                s();
            }
            int i13 = bArr[0] << 16;
            if (1 < a2) {
                i13 |= (bArr[1] & 255) << 8;
                i2 = 2;
            } else {
                i2 = 1;
            }
            this.r = aVar.a(i13, i2, this.p, this.r);
            i -= i2;
        }
        return i;
    }

    private int a(com.a.a.b.a aVar, InputStream inputStream, byte[] bArr) {
        int i = 0;
        int i2 = 0;
        int i3 = -3;
        int i4 = 0;
        int i5 = this.s - 6;
        int c = aVar.c() >> 2;
        while (true) {
            if (i > i3) {
                i2 = a(inputStream, bArr, i, i2, bArr.length);
                i = 0;
                if (i2 < 3) {
                    break;
                }
                i3 = i2 - 3;
            }
            if (this.r > i5) {
                s();
            }
            int i6 = i;
            int i7 = i + 1;
            int i8 = i7 + 1;
            int i9 = ((bArr[i6] << 8) | (bArr[i7] & 255)) << 8;
            i = i8 + 1;
            i4 += 3;
            this.r = aVar.a(i9 | (bArr[i8] & 255), this.p, this.r);
            c--;
            if (c <= 0) {
                char[] cArr = this.p;
                int i10 = this.r;
                this.r = i10 + 1;
                cArr[i10] = '\\';
                char[] cArr2 = this.p;
                int i11 = this.r;
                this.r = i11 + 1;
                cArr2[i11] = 'n';
                c = aVar.c() >> 2;
            }
        }
        if (i2 > 0) {
            if (this.r > i5) {
                s();
            }
            int i12 = bArr[0] << 16;
            int i13 = 1;
            if (1 < i2) {
                i12 |= (bArr[1] & 255) << 8;
                i13 = 2;
            }
            i4 += i13;
            this.r = aVar.a(i12, i13, this.p, this.r);
        }
        return i4;
    }

    private static int a(InputStream inputStream, byte[] bArr, int i, int i2, int i3) {
        int i4;
        int i5 = 0;
        while (i < i2) {
            int i6 = i5;
            i5++;
            int i7 = i;
            i++;
            bArr[i6] = bArr[i7];
        }
        int i8 = i5;
        int min = Math.min(i3, bArr.length);
        do {
            int i9 = min - i8;
            if (i9 == 0) {
                break;
            }
            int read = inputStream.read(bArr, i8, i9);
            if (read >= 0) {
                i4 = i8 + read;
                i8 = i4;
            } else {
                return i8;
            }
        } while (i4 < 3);
        return i8;
    }

    private final void q() {
        if (this.r + 4 >= this.s) {
            s();
        }
        int i = this.r;
        char[] cArr = this.p;
        cArr[i] = 'n';
        int i2 = i + 1;
        cArr[i2] = 'u';
        int i3 = i2 + 1;
        cArr[i3] = 'l';
        int i4 = i3 + 1;
        cArr[i4] = 'l';
        this.r = i4 + 1;
    }

    private void a(char c, int i) {
        int i2;
        if (i >= 0) {
            if (this.r >= 2) {
                int i3 = this.r - 2;
                this.q = i3;
                this.p[i3] = '\\';
                this.p[i3 + 1] = (char) i;
                return;
            }
            char[] cArr = this.t;
            char[] cArr2 = cArr;
            if (cArr == null) {
                cArr2 = r();
            }
            this.q = this.r;
            cArr2[1] = (char) i;
            this.n.write(cArr2, 0, 2);
            return;
        }
        if (i != -2) {
            char[] p = p();
            if (this.r >= 6) {
                char[] cArr3 = this.p;
                int i4 = this.r - 6;
                this.q = i4;
                cArr3[i4] = '\\';
                int i5 = i4 + 1;
                cArr3[i5] = 'u';
                if (c > 255) {
                    int i6 = (c >> '\b') & 255;
                    int i7 = i5 + 1;
                    cArr3[i7] = p[i6 >> 4];
                    i2 = i7 + 1;
                    cArr3[i2] = p[i6 & 15];
                    c = (char) (c & 255);
                } else {
                    int i8 = i5 + 1;
                    cArr3[i8] = '0';
                    i2 = i8 + 1;
                    cArr3[i2] = '0';
                }
                int i9 = i2 + 1;
                cArr3[i9] = p[c >> 4];
                cArr3[i9 + 1] = p[c & 15];
                return;
            }
            char[] cArr4 = this.t;
            char[] cArr5 = cArr4;
            if (cArr4 == null) {
                cArr5 = r();
            }
            this.q = this.r;
            if (c > 255) {
                int i10 = (c >> '\b') & 255;
                int i11 = c & 255;
                cArr5[10] = p[i10 >> 4];
                cArr5[11] = p[i10 & 15];
                cArr5[12] = p[i11 >> 4];
                cArr5[13] = p[i11 & 15];
                this.n.write(cArr5, 8, 6);
                return;
            }
            cArr5[6] = p[c >> 4];
            cArr5[7] = p[c & 15];
            this.n.write(cArr5, 2, 6);
            return;
        }
        if (this.u == null) {
            com.a.a.b.c.c cVar = this.h;
            throw null;
        }
        String a2 = this.u.a();
        this.u = null;
        int length = a2.length();
        if (this.r >= length) {
            int i12 = this.r - length;
            this.q = i12;
            a2.getChars(0, length, this.p, i12);
        } else {
            this.q = this.r;
            this.n.write(a2);
        }
    }

    private int a(char[] cArr, int i, int i2, char c, int i3) {
        int i4;
        if (i3 >= 0) {
            if (i > 1 && i < i2) {
                i -= 2;
                cArr[i] = '\\';
                cArr[i + 1] = (char) i3;
            } else {
                char[] cArr2 = this.t;
                char[] cArr3 = cArr2;
                if (cArr2 == null) {
                    cArr3 = r();
                }
                cArr3[1] = (char) i3;
                this.n.write(cArr3, 0, 2);
            }
            return i;
        }
        if (i3 != -2) {
            char[] p = p();
            if (i > 5 && i < i2) {
                int i5 = i - 6;
                int i6 = i5 + 1;
                cArr[i5] = '\\';
                int i7 = i6 + 1;
                cArr[i6] = 'u';
                if (c > 255) {
                    int i8 = (c >> '\b') & 255;
                    int i9 = i7 + 1;
                    cArr[i7] = p[i8 >> 4];
                    i4 = i9 + 1;
                    cArr[i9] = p[i8 & 15];
                    c = (char) (c & 255);
                } else {
                    int i10 = i7 + 1;
                    cArr[i7] = '0';
                    i4 = i10 + 1;
                    cArr[i10] = '0';
                }
                int i11 = i4;
                int i12 = i4 + 1;
                cArr[i11] = p[c >> 4];
                cArr[i12] = p[c & 15];
                i = i12 - 5;
            } else {
                char[] cArr4 = this.t;
                char[] cArr5 = cArr4;
                if (cArr4 == null) {
                    cArr5 = r();
                }
                this.q = this.r;
                if (c > 255) {
                    int i13 = (c >> '\b') & 255;
                    int i14 = c & 255;
                    cArr5[10] = p[i13 >> 4];
                    cArr5[11] = p[i13 & 15];
                    cArr5[12] = p[i14 >> 4];
                    cArr5[13] = p[i14 & 15];
                    this.n.write(cArr5, 8, 6);
                } else {
                    cArr5[6] = p[c >> 4];
                    cArr5[7] = p[c & 15];
                    this.n.write(cArr5, 2, 6);
                }
            }
            return i;
        }
        if (this.u == null) {
            com.a.a.b.c.c cVar = this.h;
            throw null;
        }
        String a2 = this.u.a();
        this.u = null;
        int length = a2.length();
        if (i >= length && i < i2) {
            i -= length;
            a2.getChars(0, length, cArr, i);
        } else {
            this.n.write(a2);
        }
        return i;
    }

    private void b(char c, int i) {
        int i2;
        if (i >= 0) {
            if (this.r + 2 > this.s) {
                s();
            }
            char[] cArr = this.p;
            int i3 = this.r;
            this.r = i3 + 1;
            cArr[i3] = '\\';
            char[] cArr2 = this.p;
            int i4 = this.r;
            this.r = i4 + 1;
            cArr2[i4] = (char) i;
            return;
        }
        if (i != -2) {
            if (this.r + 5 >= this.s) {
                s();
            }
            int i5 = this.r;
            char[] cArr3 = this.p;
            char[] p = p();
            int i6 = i5 + 1;
            cArr3[i5] = '\\';
            int i7 = i6 + 1;
            cArr3[i6] = 'u';
            if (c > 255) {
                int i8 = (c >> '\b') & 255;
                int i9 = i7 + 1;
                cArr3[i7] = p[i8 >> 4];
                i2 = i9 + 1;
                cArr3[i9] = p[i8 & 15];
                c = (char) (c & 255);
            } else {
                int i10 = i7 + 1;
                cArr3[i7] = '0';
                i2 = i10 + 1;
                cArr3[i10] = '0';
            }
            int i11 = i2;
            int i12 = i2 + 1;
            cArr3[i11] = p[c >> 4];
            cArr3[i12] = p[c & 15];
            this.r = i12 + 1;
            return;
        }
        if (this.u == null) {
            com.a.a.b.c.c cVar = this.h;
            throw null;
        }
        String a2 = this.u.a();
        this.u = null;
        int length = a2.length();
        if (this.r + length > this.s) {
            s();
            if (length > this.s) {
                this.n.write(a2);
                return;
            }
        }
        a2.getChars(0, length, this.p, this.r);
        this.r += length;
    }

    private char[] r() {
        char[] cArr = {'\\', 0, '\\', 'u', '0', '0', 0, 0, '\\', 'u'};
        this.t = cArr;
        return cArr;
    }

    private void s() {
        int i = this.r - this.q;
        if (i > 0) {
            int i2 = this.q;
            this.q = 0;
            this.r = 0;
            this.n.write(this.p, i2, i);
        }
    }
}
