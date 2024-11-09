package com.a.a.b.a;

import com.a.a.b.d.f;
import com.a.a.b.h;
import com.a.a.b.n;
import com.a.a.b.p;
import com.a.a.b.r;
import java.io.InputStream;
import java.math.BigDecimal;

/* loaded from: infinitode-2.jar:com/a/a/b/a/a.class */
public abstract class a extends h {
    private static int e = (h.a.WRITE_NUMBERS_AS_STRINGS.b() | h.a.ESCAPE_NON_ASCII.b()) | h.a.STRICT_DUPLICATE_DETECTION.b();
    private p f;
    private int g;
    protected boolean c;
    protected f d;

    protected abstract void o();

    protected abstract void g(String str);

    /* JADX INFO: Access modifiers changed from: protected */
    public a(int i, p pVar) {
        this.g = i;
        this.f = pVar;
        this.d = f.b(h.a.STRICT_DUPLICATE_DETECTION.a(i) ? com.a.a.b.d.b.a(this) : null);
        this.c = h.a.WRITE_NUMBERS_AS_STRINGS.a(i);
    }

    @Override // com.a.a.b.h
    public final void a(Object obj) {
        if (this.d != null) {
            this.d.a(obj);
        }
    }

    @Override // com.a.a.b.h
    public final boolean b(h.a aVar) {
        return (this.g & aVar.b()) != 0;
    }

    @Override // com.a.a.b.h
    public final int b() {
        return this.g;
    }

    @Override // com.a.a.b.h
    public h a(h.a aVar) {
        int b2 = aVar.b();
        this.g &= b2 ^ (-1);
        if ((b2 & e) != 0) {
            if (aVar == h.a.WRITE_NUMBERS_AS_STRINGS) {
                this.c = false;
            } else if (aVar == h.a.ESCAPE_NON_ASCII) {
                b(0);
            } else if (aVar == h.a.STRICT_DUPLICATE_DETECTION) {
                this.d = this.d.a((com.a.a.b.d.b) null);
            }
        }
        return this;
    }

    @Override // com.a.a.b.h
    @Deprecated
    public final h a(int i) {
        int i2 = i ^ this.g;
        this.g = i;
        if (i2 != 0) {
            b(i, i2);
        }
        return this;
    }

    @Override // com.a.a.b.h
    public final h a(int i, int i2) {
        int i3 = this.g;
        int i4 = (i3 & (i2 ^ (-1))) | (i & i2);
        int i5 = i3 ^ i4;
        if (i5 != 0) {
            this.g = i4;
            b(i4, i5);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(int i, int i2) {
        if ((i2 & e) == 0) {
            return;
        }
        this.c = h.a.WRITE_NUMBERS_AS_STRINGS.a(i);
        if (h.a.ESCAPE_NON_ASCII.a(i2)) {
            if (h.a.ESCAPE_NON_ASCII.a(i)) {
                b(127);
            } else {
                b(0);
            }
        }
        if (h.a.STRICT_DUPLICATE_DETECTION.a(i2)) {
            if (h.a.STRICT_DUPLICATE_DETECTION.a(i)) {
                if (this.d.m() == null) {
                    this.d = this.d.a(com.a.a.b.d.b.a(this));
                    return;
                }
                return;
            }
            this.d = this.d.a((com.a.a.b.d.b) null);
        }
    }

    @Override // com.a.a.b.h
    public final n a() {
        return this.d;
    }

    @Override // com.a.a.b.h
    public void c(Object obj) {
        i();
        if (obj != null) {
            a(obj);
        }
    }

    @Override // com.a.a.b.h
    public void b(r rVar) {
        a(rVar.a());
    }

    @Override // com.a.a.b.h
    public void c(r rVar) {
        b(rVar.a());
    }

    @Override // com.a.a.b.h
    public final void d(String str) {
        g("write raw value");
        c(str);
    }

    @Override // com.a.a.b.h
    public final void e(r rVar) {
        g("write raw value");
        d(rVar);
    }

    @Override // com.a.a.b.h
    public int a(com.a.a.b.a aVar, InputStream inputStream, int i) {
        n();
        return 0;
    }

    @Override // com.a.a.b.h
    public final void h(Object obj) {
        if (obj == null) {
            k();
        } else if (this.f != null) {
            this.f.a(this, obj);
        } else {
            i(obj);
        }
    }

    @Override // com.a.a.b.h, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String b(BigDecimal bigDecimal) {
        if (h.a.WRITE_BIGDECIMAL_AS_PLAIN.a(this.g)) {
            int scale = bigDecimal.scale();
            if (scale < -9999 || scale > 9999) {
                f(String.format("Attempt to write plain `java.math.BigDecimal` (see JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN) with illegal scale (%d): needs to be between [-%d, %d]", Integer.valueOf(scale), 9999, 9999));
            }
            return bigDecimal.toPlainString();
        }
        return bigDecimal.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void b(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            f("Invalid `byte[]` argument: `null`");
        }
        int length = bArr.length;
        int i3 = i + i2;
        if ((i | i2 | i3 | (length - i3)) < 0) {
            f(String.format("Invalid 'offset' (%d) and/or 'len' (%d) arguments for `byte[]` of length %d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(length)));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void c(char[] cArr, int i, int i2) {
        if (cArr == null) {
            f("Invalid `char[]` argument: `null`");
        }
        int length = cArr.length;
        int i3 = i + i2;
        if ((i | i2 | i3 | (length - i3)) < 0) {
            f(String.format("Invalid 'offset' (%d) and/or 'len' (%d) arguments for `char[]` of length %d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(length)));
        }
    }
}
