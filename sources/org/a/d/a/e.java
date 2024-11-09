package org.a.d.a;

import org.a.d.b.al;
import org.a.d.b.s;

@al(b = "pdfaid", a = "http://www.aiim.org/pdfa/ns/id/")
/* loaded from: infinitode-2.jar:org/a/d/a/e.class */
public class e extends l {
    public e(org.a.d.b bVar) {
        super(bVar);
    }

    private void a(int i) {
        a((s) a("part", Integer.valueOf(i)));
    }

    public final void a(Integer num) {
        a(num.intValue());
    }

    public final void a(String str) {
        if (str.equals("A") || str.equals("B") || str.equals("U")) {
            a(d("conformance", str));
            return;
        }
        throw new org.a.d.b.h("The property given not seems to be a PDF/A conformance level (must be A, B or U)");
    }
}
