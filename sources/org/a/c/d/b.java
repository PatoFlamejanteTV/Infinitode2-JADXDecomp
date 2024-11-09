package org.a.c.d;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:org/a/c/d/b.class */
public final class b implements Cloneable, e {

    /* renamed from: a, reason: collision with root package name */
    private int f4416a;

    /* renamed from: b, reason: collision with root package name */
    private List<byte[]> f4417b;
    private byte[] c;
    private long d;
    private int e;
    private long f;
    private int g;
    private int h;

    public b() {
        this(1024);
    }

    private b(int i) {
        this.f4416a = 1024;
        this.f4417b = null;
        this.f4417b = new ArrayList();
        this.f4416a = i;
        this.c = new byte[this.f4416a];
        this.f4417b.add(this.c);
        this.d = 0L;
        this.e = 0;
        this.f = 0L;
        this.g = 0;
        this.h = 0;
    }

    public b(byte[] bArr) {
        this.f4416a = 1024;
        this.f4417b = null;
        this.f4417b = new ArrayList(1);
        this.f4416a = bArr.length;
        this.c = bArr;
        this.f4417b.add(this.c);
        this.d = 0L;
        this.e = 0;
        this.f = this.f4416a;
        this.g = 0;
        this.h = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public b clone() {
        b bVar = new b(this.f4416a);
        bVar.f4417b = new ArrayList(this.f4417b.size());
        for (byte[] bArr : this.f4417b) {
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            bVar.f4417b.add(bArr2);
        }
        if (this.c != null) {
            bVar.c = bVar.f4417b.get(bVar.f4417b.size() - 1);
        } else {
            bVar.c = null;
        }
        bVar.d = this.d;
        bVar.e = this.e;
        bVar.f = this.f;
        bVar.g = this.g;
        bVar.h = this.h;
        return bVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.c = null;
        this.f4417b.clear();
        this.d = 0L;
        this.e = 0;
        this.f = 0L;
        this.g = 0;
    }

    @Override // org.a.c.d.e
    public final void a(long j) {
        j();
        if (j < 0) {
            throw new IOException("Invalid position " + j);
        }
        this.d = j;
        if (this.d < this.f) {
            this.g = (int) (this.d / this.f4416a);
            this.e = (int) (this.d % this.f4416a);
            this.c = this.f4417b.get(this.g);
        } else {
            this.g = this.h;
            this.c = this.f4417b.get(this.g);
            this.e = (int) (this.f % this.f4416a);
        }
    }

    @Override // org.a.c.d.e
    public final long a() {
        j();
        return this.d;
    }

    @Override // org.a.c.d.e
    public final int b() {
        j();
        if (this.d >= this.f) {
            return -1;
        }
        if (this.e >= this.f4416a) {
            if (this.g >= this.h) {
                return -1;
            }
            List<byte[]> list = this.f4417b;
            int i = this.g + 1;
            this.g = i;
            this.c = list.get(i);
            this.e = 0;
        }
        this.d++;
        byte[] bArr = this.c;
        int i2 = this.e;
        this.e = i2 + 1;
        return bArr[i2] & 255;
    }

    @Override // org.a.c.d.e
    public final int a(byte[] bArr, int i, int i2) {
        j();
        if (this.d >= this.f) {
            return 0;
        }
        int c = c(bArr, i, i2);
        while (c < i2 && k() > 0) {
            c += c(bArr, i + c, i2 - c);
            if (this.e == this.f4416a) {
                i();
            }
        }
        return c;
    }

    private int c(byte[] bArr, int i, int i2) {
        if (this.d >= this.f) {
            return 0;
        }
        int min = (int) Math.min(i2, this.f - this.d);
        int i3 = this.f4416a - this.e;
        if (i3 == 0) {
            return 0;
        }
        if (min >= i3) {
            System.arraycopy(this.c, this.e, bArr, i, i3);
            this.e += i3;
            this.d += i3;
            return i3;
        }
        System.arraycopy(this.c, this.e, bArr, i, min);
        this.e += min;
        this.d += min;
        return min;
    }

    @Override // org.a.c.d.e
    public final long c() {
        j();
        return this.f;
    }

    @Override // org.a.c.d.f
    public final void a(int i) {
        j();
        if (this.e >= this.f4416a) {
            if (this.d + this.f4416a >= 2147483647L) {
                throw new IOException("RandomAccessBuffer overflow");
            }
            h();
        }
        byte[] bArr = this.c;
        int i2 = this.e;
        this.e = i2 + 1;
        bArr[i2] = (byte) i;
        this.d++;
        if (this.d > this.f) {
            this.f = this.d;
        }
        if (this.e >= this.f4416a) {
            if (this.d + this.f4416a >= 2147483647L) {
                throw new IOException("RandomAccessBuffer overflow");
            }
            h();
        }
    }

    @Override // org.a.c.d.f
    public final void a(byte[] bArr) {
        b(bArr, 0, bArr.length);
    }

    @Override // org.a.c.d.f
    public final void b(byte[] bArr, int i, int i2) {
        j();
        long j = this.d + i2;
        int i3 = this.f4416a - this.e;
        if (i2 >= i3) {
            if (j > 2147483647L) {
                throw new IOException("RandomAccessBuffer overflow");
            }
            byte[] bArr2 = this.c;
            int i4 = this.e;
            System.arraycopy(bArr, i, bArr2, i4, i3);
            int i5 = i + i3;
            int i6 = (i2 - i3) / this.f4416a;
            for (int i7 = 0; i7 < i6; i7++) {
                h();
                byte[] bArr3 = this.c;
                i4 = this.e;
                System.arraycopy(bArr, i5, bArr3, i4, this.f4416a);
                i5 += this.f4416a;
            }
            int i8 = i4;
            if (i4 - (i6 * this.f4416a) >= 0) {
                h();
                if (i8 > 0) {
                    System.arraycopy(bArr, i5, this.c, this.e, i8);
                }
                this.e = i8;
            }
        } else {
            System.arraycopy(bArr, i, this.c, this.e, i2);
            this.e += i2;
        }
        this.d += i2;
        if (this.d > this.f) {
            this.f = this.d;
        }
    }

    private void h() {
        if (this.h > this.g) {
            i();
            return;
        }
        this.c = new byte[this.f4416a];
        this.f4417b.add(this.c);
        this.e = 0;
        this.h++;
        this.g++;
    }

    private void i() {
        if (this.g == this.h) {
            throw new IOException("No more chunks available, end of buffer reached");
        }
        this.e = 0;
        List<byte[]> list = this.f4417b;
        int i = this.g + 1;
        this.g = i;
        this.c = list.get(i);
    }

    private void j() {
        if (this.c == null) {
            throw new IOException("RandomAccessBuffer already closed");
        }
    }

    @Override // org.a.c.d.e
    public final boolean d() {
        return this.c == null;
    }

    @Override // org.a.c.d.e
    public final boolean e() {
        j();
        return this.d >= this.f;
    }

    private int k() {
        return (int) Math.min(c() - a(), 2147483647L);
    }

    @Override // org.a.c.d.e
    public final int f() {
        int b2 = b();
        if (b2 != -1) {
            b(1);
        }
        return b2;
    }

    @Override // org.a.c.d.e
    public final void b(int i) {
        j();
        a(a() - i);
    }

    @Override // org.a.c.d.e
    public final byte[] c(int i) {
        byte[] bArr = new byte[i];
        int b2 = b(bArr);
        while (true) {
            int i2 = b2;
            if (i2 < i) {
                b2 = i2 + a(bArr, i2, i - i2);
            } else {
                return bArr;
            }
        }
    }

    @Override // org.a.c.d.e
    public final int b(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }
}
