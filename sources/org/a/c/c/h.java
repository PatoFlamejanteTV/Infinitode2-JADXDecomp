package org.a.c.c;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: infinitode-2.jar:org/a/c/c/h.class */
final class h extends l {
    @Override // org.a.c.c.l
    public final k a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar, int i) {
        org.a.c.b.j jVar = (org.a.c.b.j) dVar.a(org.a.c.b.j.cp);
        if (jVar == null || jVar.equals(org.a.c.b.j.bB)) {
            new o().a(inputStream, outputStream, dVar, i);
            return new k(dVar);
        }
        throw new IOException("Unsupported crypt filter " + jVar.a());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.a.c.c.l
    public final void a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar) {
        org.a.c.b.j jVar = (org.a.c.b.j) dVar.a(org.a.c.b.j.cp);
        if (jVar == null || jVar.equals(org.a.c.b.j.bB)) {
            new o().a(inputStream, outputStream, dVar);
            return;
        }
        throw new IOException("Unsupported crypt filter " + jVar.a());
    }
}
