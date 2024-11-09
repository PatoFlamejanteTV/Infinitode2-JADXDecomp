package com.d.h;

import com.d.g.a.b;
import java.io.File;
import java.io.OutputStream;
import java.util.logging.Level;

/* loaded from: infinitode-2.jar:com/d/h/x.class */
public class x extends com.d.g.a.b<x, y> {

    /* loaded from: infinitode-2.jar:com/d/h/x$b.class */
    public enum b {
        PDF_FONT_METRICS
    }

    public x() {
        super(new y());
        for (b bVar : b.values()) {
            ((y) this.f1196a).F.put(bVar, com.d.d.a.a.f1096a);
        }
    }

    public final q a() {
        q qVar = new q(new com.d.g.a.a(((y) this.f1196a).e, ((y) this.f1196a).d, ((y) this.f1196a).f, ((y) this.f1196a).u, ((y) this.f1196a).t), new com.d.g.a.h(((y) this.f1196a).p, ((y) this.f1196a).o, ((y) this.f1196a).j, ((y) this.f1196a).m, ((y) this.f1196a).l, ((y) this.f1196a).n, ((y) this.f1196a).q, ((y) this.f1196a).k), new com.d.g.a.g(((y) this.f1196a).r, ((y) this.f1196a).s, false), (y) this.f1196a);
        f b2 = qVar.b();
        for (a aVar : ((y) this.f1196a).A) {
            com.d.c.a.c cVar = null;
            if (a.a(aVar) == b.EnumC0025b.NORMAL) {
                cVar = com.d.c.a.c.aq;
            } else if (a.a(aVar) == b.EnumC0025b.ITALIC) {
                cVar = com.d.c.a.c.W;
            } else if (a.a(aVar) == b.EnumC0025b.OBLIQUE) {
                cVar = com.d.c.a.c.at;
            }
            if (a.b(aVar) != null) {
                b2.a(a.b(aVar), a.c(aVar), a.d(aVar), cVar, a.e(aVar));
            } else {
                try {
                    b2.a(a.f(aVar), a.c(aVar), a.d(aVar), cVar, a.e(aVar));
                } catch (Exception e) {
                    com.d.m.l.d(Level.WARNING, "Font " + a.f(aVar) + " could not be loaded", e);
                }
            }
        }
        return qVar;
    }

    public final x a(OutputStream outputStream) {
        ((y) this.f1196a).B = outputStream;
        return this;
    }

    /* loaded from: infinitode-2.jar:com/d/h/x$a.class */
    static class a {
        static /* synthetic */ b.EnumC0025b a(a aVar) {
            throw null;
        }

        static /* synthetic */ com.d.d.f b(a aVar) {
            throw null;
        }

        static /* synthetic */ String c(a aVar) {
            throw null;
        }

        static /* synthetic */ Integer d(a aVar) {
            throw null;
        }

        static /* synthetic */ boolean e(a aVar) {
            throw null;
        }

        static /* synthetic */ File f(a aVar) {
            throw null;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/h/x$c.class */
    public enum c {
        NONE(-1, ""),
        PDFA_1_A(1, "A"),
        PDFA_1_B(1, "B"),
        PDFA_2_A(2, "A"),
        PDFA_2_B(2, "B"),
        PDFA_2_U(2, "U"),
        PDFA_3_A(3, "A"),
        PDFA_3_B(3, "B"),
        PDFA_3_U(3, "U");

        private final int j;
        private final String k;

        c(int i, String str) {
            this.j = i;
            this.k = str;
        }

        public final String a() {
            return this.k;
        }

        public final int b() {
            return this.j;
        }
    }
}
