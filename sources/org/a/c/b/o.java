package org.a.c.b;

import java.io.FilterOutputStream;
import java.io.OutputStream;
import java.util.List;

/* loaded from: infinitode-2.jar:org/a/c/b/o.class */
public final class o extends FilterOutputStream {

    /* renamed from: a, reason: collision with root package name */
    private final List<org.a.c.c.l> f4376a;

    /* renamed from: b, reason: collision with root package name */
    private final d f4377b;
    private final org.a.c.d.g c;
    private org.a.c.d.e d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public o(List<org.a.c.c.l> list, d dVar, OutputStream outputStream, org.a.c.d.g gVar) {
        super(outputStream);
        this.f4376a = list;
        this.f4377b = dVar;
        this.c = gVar;
        if (list.isEmpty()) {
            this.d = null;
        } else {
            this.d = gVar.d();
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public final void write(byte[] bArr) {
        if (this.d != null) {
            this.d.a(bArr);
        } else {
            super.write(bArr);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) {
        if (this.d != null) {
            this.d.b(bArr, i, i2);
        } else {
            super.write(bArr, i, i2);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public final void write(int i) {
        if (this.d != null) {
            this.d.a(i);
        } else {
            super.write(i);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public final void flush() {
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        try {
            if (this.d != null) {
                try {
                    for (int size = this.f4376a.size() - 1; size >= 0; size--) {
                        org.a.c.d.c cVar = new org.a.c.d.c(this.d);
                        if (size == 0) {
                            try {
                                this.f4376a.get(size).b(cVar, this.out, this.f4377b);
                            } catch (Throwable th) {
                                cVar.close();
                                throw th;
                            }
                        } else {
                            org.a.c.d.e d = this.c.d();
                            try {
                                org.a.c.d.d dVar = new org.a.c.d.d(d);
                                try {
                                    this.f4376a.get(size).b(cVar, dVar, this.f4377b);
                                    dVar.close();
                                    org.a.c.d.e eVar = this.d;
                                    this.d = d;
                                    eVar.close();
                                } finally {
                                }
                            } finally {
                            }
                        }
                        cVar.close();
                    }
                    this.d.close();
                    this.d = null;
                } catch (Throwable th2) {
                    this.d.close();
                    this.d = null;
                    throw th2;
                }
            }
        } finally {
            super.close();
        }
    }
}
