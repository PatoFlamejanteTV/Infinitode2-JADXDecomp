package org.a.c.f;

import com.a.a.a.am;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: infinitode-2.jar:org/a/c/f/f.class */
public class f extends b {
    private static final org.a.a.a.a f = org.a.a.a.c.a(f.class);

    public f(org.a.c.d.e eVar, String str, InputStream inputStream, String str2, org.a.c.d.g gVar) {
        super(eVar, str, inputStream, str2);
        this.d = eVar.c();
        a(gVar);
    }

    private void a(org.a.c.d.g gVar) {
        String property = System.getProperty("org.apache.pdfbox.pdfparser.nonSequentialPDFParser.eofLookupRange");
        if (property != null) {
            try {
                e(Integer.parseInt(property));
            } catch (NumberFormatException unused) {
                new StringBuilder("System property org.apache.pdfbox.pdfparser.nonSequentialPDFParser.eofLookupRange does not contain an integer value, but: '").append(property).append("'");
            }
        }
        this.f4430b = new org.a.c.b.e(gVar);
    }

    public final org.a.c.h.b w() {
        org.a.c.h.b bVar = new org.a.c.h.b(t(), this.c, v());
        bVar.a(u());
        return bVar;
    }

    private void y() {
        org.a.c.b.d p = p();
        org.a.c.b.b b2 = b(p);
        if (!(b2 instanceof org.a.c.b.d)) {
            throw new IOException("Expected root dictionary, but got this: " + b2);
        }
        org.a.c.b.d dVar = (org.a.c.b.d) b2;
        if (q() && !dVar.o(org.a.c.b.j.dN)) {
            dVar.a(org.a.c.b.j.dN, (org.a.c.b.b) org.a.c.b.j.N);
        }
        a(dVar, (org.a.c.b.j[]) null);
        org.a.c.b.b a2 = p.a(org.a.c.b.j.bI);
        if (a2 instanceof org.a.c.b.d) {
            a((org.a.c.b.d) a2, (org.a.c.b.j[]) null);
        }
        a(dVar);
        if (!(dVar.a(org.a.c.b.j.cL) instanceof org.a.c.b.d)) {
            throw new IOException("Page tree root must be a dictionary");
        }
        this.e = true;
    }

    public final void x() {
        try {
            if (!r() && !s()) {
                throw new IOException("Error: Header doesn't contain versioninfo");
            }
            if (!this.e) {
                y();
            }
        } catch (Throwable th) {
            if (this.f4430b != null) {
                am.a(this.f4430b);
                this.f4430b = null;
            }
            throw th;
        }
    }
}
