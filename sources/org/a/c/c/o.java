package org.a.c.c;

import com.a.a.a.am;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: infinitode-2.jar:org/a/c/c/o.class */
final class o extends l {
    @Override // org.a.c.c.l
    public final k a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar, int i) {
        am.a(inputStream, outputStream);
        outputStream.flush();
        return new k(dVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.a.c.c.l
    public final void a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar) {
        am.a(inputStream, outputStream);
        outputStream.flush();
    }
}
