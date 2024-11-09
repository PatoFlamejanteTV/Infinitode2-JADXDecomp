package org.a.c.c;

import com.a.a.a.am;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/a/c/c/a.class */
public final class a extends l {
    @Override // org.a.c.c.l
    public final k a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar, int i) {
        b bVar = null;
        try {
            b bVar2 = new b(inputStream);
            bVar = bVar2;
            am.a(bVar2, outputStream);
            outputStream.flush();
            am.a((Closeable) bVar);
            return new k(dVar);
        } catch (Throwable th) {
            am.a((Closeable) bVar);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.a.c.c.l
    public final void a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar) {
        c cVar = new c(outputStream);
        am.a(inputStream, cVar);
        cVar.close();
        outputStream.flush();
    }
}
