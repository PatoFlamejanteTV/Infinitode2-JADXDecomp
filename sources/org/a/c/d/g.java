package org.a.c.d;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.BitSet;

/* loaded from: infinitode-2.jar:org/a/c/d/g.class */
public class g implements Closeable {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4421a = org.a.a.a.c.a(g.class);
    private final File c;
    private File d;
    private RandomAccessFile e;
    private volatile byte[][] h;
    private final int i;
    private final int j;
    private final boolean k;
    private final boolean l;

    /* renamed from: b, reason: collision with root package name */
    private final Object f4422b = new Object();
    private volatile int f = 0;
    private final BitSet g = new BitSet();
    private volatile boolean m = false;

    /* JADX WARN: Type inference failed for: r1v28, types: [byte[], byte[][]] */
    public g(a aVar) {
        int i;
        this.l = !aVar.b() || aVar.d();
        this.k = this.l ? aVar.c() : false;
        this.c = this.k ? aVar.h() : null;
        if (this.c != null && !this.c.isDirectory()) {
            throw new IOException("Scratch file directory does not exist: " + this.c);
        }
        this.j = aVar.e() ? (int) Math.min(2147483647L, aVar.g() / 4096) : Integer.MAX_VALUE;
        if (aVar.b()) {
            i = aVar.d() ? (int) Math.min(2147483647L, aVar.f() / 4096) : Integer.MAX_VALUE;
        } else {
            i = 0;
        }
        this.i = i;
        this.h = new byte[this.l ? this.i : 100000];
        this.g.set(0, this.h.length);
    }

    public static g a() {
        try {
            return new g(a.a());
        } catch (IOException e) {
            new StringBuilder("Unexpected exception occurred creating main memory scratch file instance: ").append(e.getMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int b() {
        int i;
        synchronized (this.g) {
            int nextSetBit = this.g.nextSetBit(0);
            int i2 = nextSetBit;
            if (nextSetBit < 0) {
                e();
                int nextSetBit2 = this.g.nextSetBit(0);
                i2 = nextSetBit2;
                if (nextSetBit2 < 0) {
                    throw new IOException("Maximum allowed scratch file memory exceeded.");
                }
            }
            this.g.clear(i2);
            if (i2 >= this.f) {
                this.f = i2 + 1;
            }
            i = i2;
        }
        return i;
    }

    /* JADX WARN: Type inference failed for: r0v19, types: [java.lang.Object, byte[], byte[][]] */
    private void e() {
        synchronized (this.f4422b) {
            c();
            if (this.f >= this.j) {
                return;
            }
            if (this.k) {
                if (this.e == null) {
                    this.d = File.createTempFile("PDFBox", ".tmp", this.c);
                    try {
                        this.e = new RandomAccessFile(this.d, "rw");
                    } catch (IOException e) {
                        if (!this.d.delete()) {
                            new StringBuilder("Error deleting scratch file: ").append(this.d.getAbsolutePath());
                        }
                        throw e;
                    }
                }
                long length = this.e.length();
                long j = (this.f - this.i) << 12;
                if (j != length) {
                    throw new IOException("Expected scratch file size of " + j + " but found " + length);
                }
                if (this.f + 16 > this.f) {
                    this.e.setLength(length + 65536);
                    this.g.set(this.f, this.f + 16);
                }
            } else if (!this.l) {
                int length2 = this.h.length;
                int min = (int) Math.min(length2 << 1, 2147483647L);
                if (min > length2) {
                    ?? r0 = new byte[min];
                    System.arraycopy(this.h, 0, r0, 0, length2);
                    this.h = r0;
                    this.g.set(length2, min);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final byte[] a(int i) {
        byte[] bArr;
        if (i < 0 || i >= this.f) {
            c();
            throw new IOException("Page index out of range: " + i + ". Max value: " + (this.f - 1));
        }
        if (i < this.i) {
            byte[] bArr2 = this.h[i];
            if (bArr2 == null) {
                c();
                throw new IOException("Requested page with index " + i + " was not written before.");
            }
            return bArr2;
        }
        synchronized (this.f4422b) {
            if (this.e == null) {
                c();
                throw new IOException("Missing scratch file to read page with index " + i + " from.");
            }
            bArr = new byte[4096];
            this.e.seek((i - this.i) << 12);
            this.e.readFully(bArr);
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i, byte[] bArr) {
        if (i < 0 || i >= this.f) {
            c();
            throw new IOException("Page index out of range: " + i + ". Max value: " + (this.f - 1));
        }
        if (bArr.length != 4096) {
            throw new IOException("Wrong page size to write: " + bArr.length + ". Expected: 4096");
        }
        if (i < this.i) {
            if (this.l) {
                this.h[i] = bArr;
            } else {
                synchronized (this.f4422b) {
                    this.h[i] = bArr;
                }
            }
            c();
            return;
        }
        synchronized (this.f4422b) {
            c();
            this.e.seek((i - this.i) << 12);
            this.e.write(bArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c() {
        if (this.m) {
            throw new IOException("Scratch file already closed");
        }
    }

    public final e d() {
        return new h(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int[] iArr, int i, int i2) {
        synchronized (this.g) {
            for (int i3 = 0; i3 < i2; i3++) {
                int i4 = iArr[i3];
                if (i4 >= 0 && i4 < this.f && !this.g.get(i4)) {
                    this.g.set(i4);
                    if (i4 < this.i) {
                        this.h[i4] = null;
                    }
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v25, types: [java.lang.Throwable] */
    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Object obj = null;
        synchronized (this.f4422b) {
            if (this.m) {
                return;
            }
            this.m = true;
            RandomAccessFile randomAccessFile = this.e;
            if (randomAccessFile != null) {
                try {
                    randomAccessFile = this.e;
                    randomAccessFile.close();
                } catch (IOException e) {
                    obj = randomAccessFile;
                }
            }
            if (this.d != null && !this.d.delete() && this.d.exists() && obj == null) {
                obj = new IOException("Error deleting scratch file: " + this.d.getAbsolutePath());
            }
            synchronized (this.g) {
                this.g.clear();
                this.f = 0;
            }
            if (obj != null) {
                throw obj;
            }
        }
    }
}
