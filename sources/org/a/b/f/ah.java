package org.a.b.f;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;

/* loaded from: infinitode-2.jar:org/a/b/f/ah.class */
final class ah extends ak {

    /* renamed from: a, reason: collision with root package name */
    private RandomAccessFile f4274a;

    /* renamed from: b, reason: collision with root package name */
    private File f4275b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ah(File file, String str) {
        this.f4274a = null;
        this.f4275b = null;
        this.f4274a = new a(file, str, 16384);
        this.f4275b = file;
    }

    @Override // org.a.b.f.ak
    public final short d() {
        return this.f4274a.readShort();
    }

    @Override // org.a.b.f.ak
    public final long e() {
        return this.f4274a.getFilePointer();
    }

    @Override // org.a.b.f.ak, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f4274a != null) {
            this.f4274a.close();
            this.f4274a = null;
        }
    }

    @Override // org.a.b.f.ak
    public final int b() {
        return this.f4274a.read();
    }

    @Override // org.a.b.f.ak
    public final int c() {
        return this.f4274a.readUnsignedShort();
    }

    @Override // org.a.b.f.ak
    public final long a() {
        return this.f4274a.readLong();
    }

    @Override // org.a.b.f.ak
    public final void a(long j) {
        this.f4274a.seek(j);
    }

    @Override // org.a.b.f.ak
    public final int a(byte[] bArr, int i, int i2) {
        return this.f4274a.read(bArr, i, i2);
    }

    @Override // org.a.b.f.ak
    public final InputStream f() {
        return new FileInputStream(this.f4275b);
    }

    @Override // org.a.b.f.ak
    public final long g() {
        return this.f4275b.length();
    }
}
