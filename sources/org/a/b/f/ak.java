package org.a.b.f;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/* loaded from: infinitode-2.jar:org/a/b/f/ak.class */
abstract class ak implements Closeable {
    public abstract int b();

    public abstract long a();

    public abstract int c();

    public abstract short d();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    public abstract void a(long j);

    public abstract int a(byte[] bArr, int i, int i2);

    public abstract long e();

    public abstract InputStream f();

    public abstract long g();

    public final float h() {
        return (float) (d() + (c() / 65536.0d));
    }

    public final String a(int i) {
        return a(i, org.a.b.h.b.f4351a);
    }

    public final String a(int i, Charset charset) {
        return new String(d(i), charset);
    }

    public final int i() {
        int b2 = b();
        return b2 <= 127 ? b2 : b2 - 256;
    }

    public final int j() {
        int b2 = b();
        if (b2 == -1) {
            throw new EOFException("premature EOF");
        }
        return b2;
    }

    public final long k() {
        long b2 = b();
        long b3 = b();
        long b4 = b();
        long b5 = b();
        if (b5 >= 0) {
            return (b2 << 24) + (b3 << 16) + (b4 << 8) + b5;
        }
        throw new EOFException();
    }

    public final int[] b(int i) {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = b();
        }
        return iArr;
    }

    public final int[] c(int i) {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = c();
        }
        return iArr;
    }

    public final Calendar l() {
        long a2 = a();
        Calendar gregorianCalendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("UTC"));
        gregorianCalendar.set(1904, 0, 1, 0, 0, 0);
        gregorianCalendar.set(14, 0);
        gregorianCalendar.setTimeInMillis(gregorianCalendar.getTimeInMillis() + (a2 * 1000));
        return gregorianCalendar;
    }

    public final String m() {
        return new String(d(4), org.a.b.h.b.d);
    }

    public final byte[] d(int i) {
        int i2;
        int a2;
        byte[] bArr = new byte[i];
        int i3 = 0;
        while (true) {
            i2 = i3;
            if (i2 >= i || (a2 = a(bArr, i2, i - i2)) == -1) {
                break;
            }
            i3 = i2 + a2;
        }
        if (i2 == i) {
            return bArr;
        }
        throw new IOException("Unexpected end of TTF stream reached");
    }
}
