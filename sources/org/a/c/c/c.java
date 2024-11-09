package org.a.c.c;

import java.io.FilterOutputStream;
import java.io.OutputStream;

/* loaded from: infinitode-2.jar:org/a/c/c/c.class */
final class c extends FilterOutputStream {

    /* renamed from: a, reason: collision with root package name */
    private int f4387a;

    /* renamed from: b, reason: collision with root package name */
    private int f4388b;
    private byte[] c;
    private byte[] d;
    private int e;
    private boolean f;
    private char g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(OutputStream outputStream) {
        super(outputStream);
        this.f4387a = 72;
        this.e = 72;
        this.f4388b = 0;
        this.c = new byte[4];
        this.d = new byte[5];
        this.f = true;
        this.g = '~';
    }

    private void a() {
        long j = ((((this.c[0] << 8) | (this.c[1] & 255)) << 16) | ((this.c[2] & 255) << 8) | (this.c[3] & 255)) & 4294967295L;
        if (j == 0) {
            this.d[0] = 122;
            this.d[1] = 0;
            return;
        }
        this.d[0] = (byte) (r0 + 33);
        long j2 = j - (((((j / 52200625) * 85) * 85) * 85) * 85);
        this.d[1] = (byte) (r0 + 33);
        long j3 = j2 - ((((j2 / 614125) * 85) * 85) * 85);
        this.d[2] = (byte) (r0 + 33);
        long j4 = j3 - (((j3 / 7225) * 85) * 85);
        this.d[3] = (byte) ((j4 / 85) + 33);
        this.d[4] = (byte) ((j4 % 85) + 33);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public final void write(int i) {
        this.f = false;
        byte[] bArr = this.c;
        int i2 = this.f4388b;
        this.f4388b = i2 + 1;
        bArr[i2] = (byte) i;
        if (this.f4388b < 4) {
            return;
        }
        a();
        for (int i3 = 0; i3 < 5 && this.d[i3] != 0; i3++) {
            this.out.write(this.d[i3]);
            int i4 = this.f4387a - 1;
            this.f4387a = i4;
            if (i4 == 0) {
                this.out.write(10);
                this.f4387a = this.e;
            }
        }
        this.f4388b = 0;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public final void flush() {
        if (this.f) {
            return;
        }
        if (this.f4388b > 0) {
            for (int i = this.f4388b; i < 4; i++) {
                this.c[i] = 0;
            }
            a();
            if (this.d[0] == 122) {
                for (int i2 = 0; i2 < 5; i2++) {
                    this.d[i2] = 33;
                }
            }
            for (int i3 = 0; i3 < this.f4388b + 1; i3++) {
                this.out.write(this.d[i3]);
                int i4 = this.f4387a - 1;
                this.f4387a = i4;
                if (i4 == 0) {
                    this.out.write(10);
                    this.f4387a = this.e;
                }
            }
        }
        int i5 = this.f4387a - 1;
        this.f4387a = i5;
        if (i5 == 0) {
            this.out.write(10);
        }
        this.out.write(this.g);
        this.out.write(62);
        this.out.write(10);
        this.f4388b = 0;
        this.f4387a = this.e;
        this.f = true;
        super.flush();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        try {
            flush();
            super.close();
        } finally {
            byte[] bArr = null;
            this.d = bArr;
            this.c = bArr;
        }
    }
}
