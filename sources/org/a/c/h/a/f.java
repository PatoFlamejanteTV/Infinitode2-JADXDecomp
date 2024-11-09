package org.a.c.h.a;

import java.io.OutputStream;
import org.a.c.b.j;

/* loaded from: infinitode-2.jar:org/a/c/h/a/f.class */
public final class f extends i {
    public f(org.a.c.h.b bVar) {
        super(bVar);
        f().a(j.dN, "Metadata");
        f().a(j.dE, "XML");
    }

    public final void a(byte[] bArr) {
        OutputStream b2 = b();
        b2.write(bArr);
        b2.close();
    }
}
