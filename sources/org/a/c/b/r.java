package org.a.c.b;

import java.io.FilterOutputStream;
import java.io.OutputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/a/c/b/r.class */
public class r extends FilterOutputStream {

    /* renamed from: a, reason: collision with root package name */
    private /* synthetic */ p f4380a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(p pVar, OutputStream outputStream) {
        super(outputStream);
        this.f4380a = pVar;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) {
        this.out.write(bArr, i, i2);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        org.a.c.d.e eVar;
        super.close();
        p pVar = this.f4380a;
        j jVar = j.bX;
        eVar = this.f4380a.f4378b;
        pVar.a(jVar, (int) eVar.c());
        p.a(this.f4380a, false);
    }
}
